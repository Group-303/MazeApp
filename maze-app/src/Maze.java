import javax.swing.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Maze implements ActionListener {
    private int id;
    private int width;
    private int height;
    private String title;
    private String creator;
    private Long creationTime;
    private HashMap<Image, Point> items = new HashMap<>();
    private MazeGenerator generator;
    private ArrayList<JButton> mazeButtons;
    private boolean[][][] layout;
    private Color bgcolour;
    private boolean[][] grid;
    private Point cursor = new Point(1, 1);

    /***
     * Maze class that stores the details of a maze.
     * @param title The title of the new maze
     * @param creator The full name of the author
     */
    public Maze(String title, String creator, int width, int height) {
        this.id = Main.database.getNextID();
        this.title = title;
        this.creator = creator;
        this.width = width;
        this.height = height;
        this.creationTime = System.currentTimeMillis(); // Time in unix milliseconds
        this.generator = new MazeGenerator(this.width, this.height);
        this.layout = generator.getLayout();
        this.grid = new boolean[width*2+1][height*2+1];
    }


    public Maze(int ID, String title, String creator, int width, int height, long creationTime, boolean[][][] layout) {
        this.id = ID;
        this.title = title;
        this.creator = creator;
        this.width = width;
        this.height = height;
        this.creationTime = creationTime; // Time in unix milliseconds
        this.layout = layout;
        this.grid = new boolean[width*2 + 1][height*2 + 1];
    }

    /***
     * Renders the maze as buttons to the provided JPanel
     * @param container The JPanel to render the maze to
     */
    public void render(JPanel container) {
        this.bgcolour = container.getBackground();
        this.mazeButtons = new ArrayList<>();
        boolean white = false;
        int xRegion = 0;
        int yRegion = 0;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color colour;
                if (layout[j][i][1]) {
                    colour = Color.BLACK;
                    white = false;
                } else {
                    colour = Color.WHITE;
                    white = true;
                }
                this.mazeButtons.add(GUIHelper.newButton(container, Color.BLACK, xRegion, yRegion)); //NW
                this.grid[xRegion][yRegion] = false;
                this.mazeButtons.add(GUIHelper.newButton(container, colour, xRegion, yRegion + 1)); //SW
                this.grid[xRegion][yRegion + 1] = white;
                xRegion++;

                if (layout[j][i][0]) {
                    colour = Color.BLACK;
                    white = false;
                } else {
                    colour = Color.WHITE;
                    white = true;
                }

                this.mazeButtons.add(GUIHelper.newButton(container, colour, xRegion, yRegion)); //NE
                this.grid[xRegion][yRegion] = white;
                this.mazeButtons.add(GUIHelper.newButton(container, Color.WHITE, xRegion, yRegion + 1)); //SE
                this.grid[xRegion][yRegion + 1] = true;
                xRegion++;
            }
            this.mazeButtons.add(GUIHelper.newButton(container, Color.BLACK, xRegion, yRegion)); //NE Border
            this.grid[xRegion][yRegion] = false;
            this.mazeButtons.add(GUIHelper.newButton(container, Color.BLACK, xRegion, yRegion + 1)); //SE Border
            this.grid[xRegion][yRegion + 1] = false;
            yRegion += 2;
            xRegion = 0;
        }
       for (int j = 0; j < width * 2 + 1; j++) {
           this.mazeButtons.add(GUIHelper.newButton(container, Color.BLACK, j, yRegion)); //S Border
              this.grid[j][yRegion] = false;
       }

       for (JButton button : this.mazeButtons) {
            //Find the start and end points and set them to green and red respectively
           if (button.getText().equals("1,1")) {
               button.setBackground(Color.GREEN);
           }
           if (button.getText().equals(Integer.toString(width * 2 - 1) + "," + Integer.toString(height * 2 - 1))) {
               button.setBackground(Color.RED);
           }
           // add action listeners to each button
              button.addActionListener(this);
       }
    }

    /***
     * Draws the solution to the maze on the mazeButtons grid
     * @param solved Boolean to indicate whether to display the solution or not
     */
    public void solve(boolean solved) {
        Color colour;
        Point root = new Point(1, 1);
        Point goal = new Point(width * 2 - 1, height * 2 - 1);
        //int count;

        //find the path from root to goal in this.grid using BFS adding each point on the most direct route
        Point current;
        ArrayList<Point> path = new ArrayList<>();
        ArrayList<Point> visited = new ArrayList<>();
        ArrayList<Point> queue = new ArrayList<>();
        ArrayList<Point> neighbours = new ArrayList<>();
        ArrayList<Point> junctions = new ArrayList<>();
        boolean junctionsFound = false;
        queue.add(root);
        while (!queue.isEmpty()) {
            current = queue.remove(0);
            if (current.x == goal.x && current.y == goal.y) {
                break;
            }
            visited.add(current);
            neighbours = getNeighbours(current);
            if (neighbours.size() > 2) {
                junctions.add(current);
                junctionsFound = true;
            }
            for (Point neighbour : neighbours) {
                if (!visited.contains(neighbour) && !queue.contains(neighbour)) {
                    queue.add(neighbour);
                }
            }
        }

        // This is a really stupid way of doing this, but its the only way i can see to get it to work
        // While / For loop doesnt work... Ive tried
        // Hours wasted: 9
        path = cullJunctions(junctions, visited);
        path = cullJunctions(junctions, visited);
        path = cullJunctions(junctions, visited);
        path = cullJunctions(junctions, visited);
        path = cullJunctions(junctions, visited);
        
        System.out.println("JUNCTIONS: " + junctions.size());

        if (solved) {
            colour = Color.WHITE;
        }
        else {
            colour = Color.GREEN;
        }

        for (JButton button : this.mazeButtons) {
            for (Point point : path) {
                if (button.getText().equals(point.x + "," + point.y)) {
                    button.setBackground(colour);
                }
            }
        }

    }

    /***
     * Returns the neighbours of a given point
     * @param point The point to find the neighbours of
     * @return An ArrayList of points representing the neighbours of the given point
     */
    private ArrayList<Point> getNeighbours(Point current)  {
        ArrayList<Point> neighbours = new ArrayList<>();
        if (this.grid[current.x - 1][current.y]) {
            neighbours.add(new Point(current.x - 1, current.y));
        }
        if (this.grid[current.x + 1][current.y]) {
            neighbours.add(new Point(current.x + 1, current.y));
        }
        if (this.grid[current.x][current.y - 1]) {
            neighbours.add(new Point(current.x, current.y - 1));
        }
        if (this.grid[current.x][current.y + 1]) {
            neighbours.add(new Point(current.x, current.y + 1));
        }
        return neighbours;
    }

    /***
     * Returns the path from the root to the goal
     * @param junctions An ArrayList of points representing the junctions in the maze
     * @param visited An ArrayList of points representing the visited points in the maze
     * @return An ArrayList of points representing the path from the root to the goal
     */
    private ArrayList<Point> cullJunctions(ArrayList<Point> junctions, ArrayList<Point> path) {
        ArrayList<Point> queue = new ArrayList<>();
        ArrayList<Point> neighbours = new ArrayList<>();
        ArrayList<Point> visited = new ArrayList<>();
        Point root = new Point(1, 1);
        Point goal = new Point(width * 2 - 1, height * 2 - 1);
        Point current;

        for (Point junction : junctions) {
            visited = new ArrayList<>();
            queue = new ArrayList<>();
            queue.add(junction);
            while (!queue.isEmpty()) {
                current = queue.remove(0);
                visited.add(current);
                neighbours = getNeighbours(current);
                if ((neighbours.size() == 1 && (!neighbours.contains(root) && !neighbours.contains(goal)))) {
                    path.remove(current);
                }
                else if ((neighbours.size() == 2 && (!path.containsAll(neighbours))) && (!neighbours.contains(root) && !neighbours.contains(goal))) {
                    path.remove(current);
                }
                else if ((neighbours.size() == 3 && (!neighbours.contains(root) && !neighbours.contains(goal)))) {
                    int count = 0;
                    for (Point neighbour : neighbours) {
                        if (!path.contains(neighbour)) {
                            count++;
                        }
                    }
                    if (count == 2) {
                        System.out.println("Junction3: " + current.x + "," + current.y);
                        path.remove(current);
                    }
                }
                for (Point neighbour : neighbours) {
                    if (!visited.contains(neighbour) && !queue.contains(neighbour)) {
                        queue.add(neighbour);
                    }
                }
            }

        }
        return path;
    }

    /***
     * Sets the title of the maze
     * @param title String containing the new title of the maze
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /***
     * Sets the creator of the maze
     * @param creator String containing the full name of the creator
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /***
     * Sets the width of the maze
     * @param width Integer containing the width of the maze
     */
    public void setWidth(int width) {
        this.width = width;
        clearMaze();
        System.out.println(this.generator);
        regenerateMaze();
    }

    /***
     * Sets the height of the maze
     * @param height Integer containing the height of the maze
     */
    public void setHeight(int height) {
        this.height = height;
        clearMaze();
        System.out.println(this.generator);
        regenerateMaze();
    }
    /***
     * Method for retrieving the ID of the maze
     * @return Returns an integer containing the ID of the maze
     */
    public int getId() {
        return this.id;
    }

    /***
     * Method for retrieving the title of the maze
     * @return Returns a String containing the title of the maze
     */
    public String getTitle() {
        return this.title;
    }

    /***
     * Method for retrieving the creator of the maze
     * @return Returns a String containing the creator of the maze
     */
    public String getCreator() {
        return this.creator;
    }

    /***
     * Method for retrieving the creation date and time of the maze
     * @return Returns a LocalDateTime object containing the date and time the maze was created
     */
    public Long getCreatedRaw() {
        return this.creationTime;
    }

    /***
     * Retrieves the current layout of the maze
     * @return Returns a 3D boolean array of the north and west walls of the stored maze
     */
    public boolean[][][] getLayout() {
        return layout;
    }

    /***
     * Retrieves the current layout of the maze as a Json string
     * @return Returns a String containing the Json representation of the maze
     */
    public String getLayoutJsonString() {
        Gson gson = new Gson();
        return gson.toJson(layout);
    }

    /***
     * Retrieves current width of the maze
     * @return Width of the maze in cells
     */
    public int getWidth() {
        return width;
    }

    /***
     * Retrieves current height of the maze
     * @return Height of the maze in cells
     */
    public int getHeight() {
        return height;
    }

    /***
     * Retreives the next available ID for a new maze
     * @return Returns an integer containing the next available ID
     */
    public int getID() {
        return id;
    }

    /***
     * Sets the ID of the maze
     * @param newid Integer containing the new ID of the maze
     */
    public void setID(int newid) {
        this.id = newid;
    }

    /***
     * Retrieves the maze generator of this maze
     * @return MazeGenerator object responsible for generation of the maze
     */
    public MazeGenerator getGenerator() {
        return this.generator;
    }

    /***
     * Clears the current maze stored in the
     */
    private void clearMaze() {
        this.generator = null;
        this.layout = null;
    }

    /***
     * Generates a new maze
     */
    public void regenerateMaze() {
        clearMaze();
        System.out.println(this.generator + " " + this.layout);
        this.generator = new MazeGenerator(this.width, this.height);
        this.layout = this.generator.getLayout();
        //this.generator.display();
    }


    public HashMap<Image, Point> getHashmap() {
        return this.items;
    }

    /***
     * Draws a line on the mazeButton grid from cursor to point
     * @param point Point object containing the X and Y of the end point
     * @param axis Integer containing the axis to draw the line on
     */
    private void drawLine(Point point, char axis) {
        ArrayList<Point> colouredCells = new ArrayList<Point>();
        
        if (axis == 'x') {
            if (cursor.y > point.y) {
                System.out.println("1");
                for (int i = cursor.y; i > point.y; i--) {
                    if (grid[cursor.x][i]) {
                        colouredCells.add(new Point(cursor.x, i));
                    }
                    else return;
                }
            }
            else if (cursor.y < point.y) {
                
                for (int i = cursor.y; i < point.y; i++) {
                    System.out.println("2");
                    if (grid[cursor.x][i]) {
                        colouredCells.add(new Point(cursor.x, i));
                    }
                    else return;
                }
            }
        }
        else if (axis == 'y') {
            if (cursor.x > point.x) {
                System.out.println("3");
                for (int i = cursor.x; i > point.x; i--) {
                    if (grid[i][cursor.y]) {
                        colouredCells.add(new Point(i, cursor.y));
                    }
                    else return;
                }
            }
            else if (cursor.x < point.x) {
                
                for (int i = cursor.x; i < point.x; i++) {
                    System.out.println("4");
                    if (grid[i][cursor.y]) {
                        colouredCells.add(new Point(i, cursor.y));
                    }
                    else return;
                }
            }
        }
        else return;

        colouredCells.add(point);

        for (Point p : colouredCells) {
            for (JButton b : mazeButtons) {
                if (b.getText().equals(p.x + "," + p.y)) {
                    b.setBackground(Color.GREEN);
                }
            }
        }

        this.cursor = point;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //get container of JButtons //get border of JPanel, playMenu is grey, createMenu is white
        String command = e.getActionCommand();
        
        if (this.bgcolour == Color.GRAY) {
            // position of comma in command
            int comma = command.indexOf(",");
            Point point = new Point(Integer.parseInt(command.substring(0, comma)), Integer.parseInt(command.substring(comma + 1)));
            if (this.cursor.x == point.x) {
                char axis = 'x';
                drawLine(point, axis);
            }
            else if (this.cursor.y == point.y) {
                char axis = 'y';
                drawLine(point, axis);
            }
        }
        else if (this.bgcolour == Color.WHITE) {
            return;
        }
    }
}
