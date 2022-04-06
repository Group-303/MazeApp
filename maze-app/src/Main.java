public class Main {
    public static void main(String[] args) {
        GUI gui = new GUI();

        // Maze information testing
        Maze testMaze = new Maze("test maze", "John Smith");
        System.out.println(testMaze.getCreatedReadable());
        testMaze.CreateEdit(new Edit("Test description!", "Jane Doe"));
        for (Edit edit : testMaze.getEdits()) {
            System.out.println(edit.getDescription());
        }
    }
}
