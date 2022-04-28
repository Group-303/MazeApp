import java.util.ArrayList;
import java.util.List;

public class Main {
    public static Frame frame;
    public static MainMenu mainMenu;
    public static BrowseMenu browseMenu;
    public static CreateMenu createMenu;
    public static GenerateMenu generateMenu;
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
        // Testing purposes! This will be incorporated inside of the Maze class
        //Maze testMaze = new Maze("Test Maze", "John Doe", 10, 10);
        //testMaze.regenerateMaze();
        //testMaze.display();

        MazeGenerator test = new MazeGenerator(10, 10);


        Maze testMaze = new Maze("Test Maze", "John Doe", 10, 10);

        //for (int i = 0; i < testMaze.getWidth(); i++) {
        //    for (int j = 0; j < testMaze.getHeight(); j++) {
        //            System.out.println("(" + i + ", " + j + ") North: " + testMaze.getLayout()[i][j][0] + " \nWest: " + testMaze.getLayout()[i][j][1] + "\n");
        //    }
        //}

        //Maze testMaze2 = new Maze("Test Maze 2", "John Doe", 10, 10);

        frame = new Frame();
        mainMenu = new MainMenu();
        createMenu = new CreateMenu();
        generateMenu = new GenerateMenu();
        browseMenu = new BrowseMenu();
    }
}
