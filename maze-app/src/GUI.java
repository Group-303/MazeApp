import javax.swing.*;

/***
 * Class for managing spring GUI
 */

public class GUI {
    JFrame mainFrame = new JFrame("MazeApp");

    public GUI() {
        // Create a new JFrame called mainFrame and enable exit on close
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set mainFrame window size to 800x600
        mainFrame.setSize(800, 600);

        // Set mainFrame visibility to true
        mainFrame.setVisible(true);
    }

    // Testing method for changing title of the GUI (possibly change title to the current opened maze?)
    public void setTitle(String title) {
        mainFrame.setTitle(title);
    }
}
