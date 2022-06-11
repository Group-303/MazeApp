import javax.swing.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import java.awt.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Maze {
    private int id;
    private int width;
    private int height;
    private String title;
    private String creator;
    private Long creationTime;
    private HashMap<Image, Point> items = new HashMap<>();
    private List<Edit> edits = new ArrayList<>();
    private MazeGenerator generator;
    private ArrayList<JButton> mazeButtons;
    private boolean[][][] layout;
    
    private boolean[][] grid;

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

    //Overloaded constructor for loading a maze from the database
    public Maze(int ID, String title, String creator, int width, int height) {
        this.id = ID;
        this.title = title;
        this.creator = creator;
        this.width = width;
        this.height = height;
        this.creationTime = System.currentTimeMillis(); // Time in unix milliseconds
        this.generator = new MazeGenerator(this.width, this.height);
        this.layout = generator.getLayout();
        this.grid = new boolean[width*2 + 1][height*2 + 1];
    }

    //public void render(JPanel container) {
        //this.generator.render(container);
    //}

    public void render(JPanel container) {
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
       }
    }

    public void solve() {
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

        path = cullJunctions(junctions, visited);

        //while (junctionsFound) {
        //    junctionsFound = false;
        //    int count = 0;
        //    path = cullJunctions(junctions, visited);
        //    junctions = new ArrayList<>();
        //    for (Point point : path) {
        //        neighbours = getNeighbours(point);
        //        if (neighbours.size() > 2) {
        //            for (Point neighbour : neighbours) {
        //                if (path.contains(neighbour)) {
        //                    count++;
        //                }
        //            }
        //            if (count > 2) {
        //                junctions.add(point);
        //                junctionsFound = true;
        //            }
        //        }
        //    }
        //}
        
        System.out.println("JUNCTIONS: " + junctions.size());

        for (JButton button : this.mazeButtons) {
            for (Point point : path) {
                if (button.getText().equals(point.x + "," + point.y)) {
                    button.setBackground(Color.GREEN);
                }
            }
        }
        

        //while (!visited.isEmpty()) {
        //    current = visited.remove(0);
        //    neighbours = getNeighbours(current);
        //    if (current.x == goal.x && current.y == goal.y) {
        //        break;
        //    }
        //    if (neighbours.size() > 2) {
        //        lastJunction = current;
        //        
        //    }
        //    
        //}

    }

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
     * Creates a new edit on the maze
     * @param description Description of changes made to the maze
     * @param editAuthor Author of the edit
     */
    public void addEdit(String description, String editAuthor) {
        this.edits.add(new Edit(this.getEdits().size(), this.id, description, editAuthor));
    }

    /***
     * Adds an Image and Position object into the maze item list
     * @param image Image object that will be displayed on the Maze
     * @param point Point object that contains the X and Y of the Image object
     */
    public void addImage(Image image, Point point) {
        items.put(image, point);
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

    public void setWidth(int width) {
        this.width = width;
        clearMaze();
        System.out.println(this.generator);
        regenerateMaze();
    }

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

    public String getCreator() {
        return this.creator;
    }

    /***
     * Method for retrieving the creation date and time of the maze in readable form
     * Uses "MMMM dd, yyyy" and "HH:mma" formatting patterns
     * @return Returns a String containing the date and time the maze was created
     */
    //public String getCreatedReadable() {
    //    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
    //    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mma");
    //    return dateFormatter.format(this.creationTime) + " at " + timeFormatter.format(this.creationTime);
    //}

    /***
     * Method for retrieving the creation date and time of the maze
     * @return Returns a LocalDateTime object containing the date and time the maze was created
     */
    public Long getCreatedRaw() {
        return this.creationTime;
    }

    /***
     * Method for retrieving the list of edits on the maze
     * @return Returns a list of Edit objects
     */
    public List<Edit> getEdits() {
        return this.edits;
    }

    /***
     * Retrieves the current layout of the maze
     * @return Returns a 3D boolean array of the north and west walls of the stored maze
     */
    public boolean[][][] getLayout() {
        return layout;
    }

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

    public boolean setID(int newid) {
        if (this.id == 0) {
            this.id = newid;
            return true;
        }
        else {
            return false;
        }
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
    public void clearMaze() {
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
}
