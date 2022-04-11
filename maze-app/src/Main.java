import javax.swing.*;

public class Main {
    public static JFrame frame;
    public static MainGUI mainGui;
    public static BrowseGUI browseGui;

    public static void main(String[] args) {
        // Create a new JFrame called mainFrame and enable exit on close
        frame = new JFrame("MazeApp");

        // Set mainFrame window size to 800x800
        frame.setSize(800, 800);

        // Set mainFrame visibility to true
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainGui = new MainGUI();
        browseGui = new BrowseGUI();
    }

}
