import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static Frame frame;
    public static MainMenu mainMenu;
    public static BrowseMenu browseMenu;
    public static CreateGUI generatedMenu;
    public static List<Maze> mazeList = new ArrayList<>(); // Holds a list of mazes until the SQL DB can be implemented
    public static Integer[] recentMazes; // Stores ID of 10 most recent mazes viewed

    // Here for storage! Use this whenever a maze is saved or viewed.
    public static void viewMaze(int mazeID) {
        // Check if recentMazes has 10 values
            // Remove oldest value
        // Insert mazeID to index 0
        // Push all other values to index + 1
    }

    public static void main(String[] args) {
        frame = new Frame();
        mainMenu = new MainMenu();
        browseMenu = new BrowseMenu();
        generatedMenu = new CreateGUI();
    }
}
