import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static Frame frame;
    public static MainMenu mainMenu;
    public static BrowseMenu browseMenu;
    public static CreateMenu createMenu;
    public static Database database;
    public static Integer[] recentMazes; // Stores ID of 10 most recent mazes viewed

    // Testing
    public static Maze testMaze;
    public static Maze testMaze2;

    // Here for storage! Use this whenever a maze is saved or viewed.
    public static void viewMaze(int mazeID) {
        // Check if recentMazes has 10 values
            // Remove oldest value
        // Insert mazeID to index 0
        // Push all other values to index + 1
    }

    public static void main(String[] args) throws SQLException {
        database = new Database();
        testMaze = new Maze("Test Maze", "John Doe", 10, 10);
        testMaze.getGenerator().display();
        testMaze2 = new Maze("Test Maze", "John Doe", 10, 10);
        testMaze2.getGenerator().display();

        //testMaze.getGenerator().setWidth(5);
        //testMaze.getGenerator().display();

        frame = new Frame();
        mainMenu = new MainMenu();
        createMenu = new CreateMenu();
        browseMenu = new BrowseMenu();
    }
}
