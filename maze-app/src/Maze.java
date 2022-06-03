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
    private boolean[][][] layout;

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
        Main.database.addMaze(this);
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
    }


    /***
     * Adds a new Edit object into the list of edits on the maze
     * @param newEdit Object containing information on the edit
     */
    public void addEdit(Edit newEdit) {
        this.edits.add(newEdit);
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

    public boolean setID(int id) {
        if (this.id == 0) {
            this.id = id;
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
        this.generator.display();
    }

    public HashMap<Image, Point> getHashmap() {
        return this.items;
    }
}
