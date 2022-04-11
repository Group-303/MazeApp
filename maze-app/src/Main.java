import javax.swing.*;

public class Main {
    public static JFrame frame;
    public static MainGUI mainGui;
    public static BrowseGUI browseGui;

    public static void main(String[] args) {
        // Create a new JFrame called mainFrame and enable exit on close
        //frame = new JFrame("MazeApp");

        // Set mainFrame window size to 800x800
        //frame.setSize(800, 800);
      
        // Set mainFrame visibility to true
        //frame.setLocationRelativeTo(null);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainGui = new MainGUI();
        browseGui = new BrowseGUI();
      
        // Create dummy maze
        Maze testMaze = new Maze("Test Maze", "John Smith", 6, 4);
        System.out.println(testMaze.getCreatedReadable());

        // Set the title of the GUI to the dummy maze name (see GUI class)
        //  gui.setTitle(testMaze.getName());

        //  gui.newLabel(gui.getContentPane().getName());

        // Create dummy maze edits
        testMaze.addEdit(new Edit("Changed position of the logo.", "Jane Doe"));
        testMaze.addEdit(new Edit("Moved walls around exit", "John Smith"));

        // Write out all the descriptions of edits to the dummy maze
        System.out.println("Total Edits: " + testMaze.getEdits().size());
        for (Edit edit : testMaze.getEdits()) {
            System.out.println(edit.getDescription());
        }

        // Add a third edit to demonstrate storage
        testMaze.addEdit(new Edit("Third edit to the", "Steve Johnson"));

        // Write out all the descriptions of edits to the dummy maze after the third edit is added
        System.out.println("Total Edits: " + testMaze.getEdits().size());
        for (Edit edit : testMaze.getEdits()) {
            System.out.println(edit.getDescription());
        }

    }

}
