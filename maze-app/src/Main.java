public class Main {
    public static void main(String[] args) {
        GUI gui = new GUI();

        // Create dummy maze
        Maze testMaze = new Maze("test maze", "John Smith", 6, 4);
        System.out.println(testMaze.getCreatedReadable());

        // Set the title of the GUI to the dummy maze name (see GUI class)
        gui.setTitle(testMaze.getName());

        // Create dummy maze edits
        testMaze.addEdit(new Edit("First edit to the maze!", "Jane Doe"));
        testMaze.addEdit(new Edit("Second edit to the maze.", "John Smith"));

        // Write out all the descriptions of edits to the dummy maze
        for (Edit edit : testMaze.getEdits()) {
            System.out.println(edit.getDescription());
        }
    }
}
