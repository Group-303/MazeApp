import javax.swing.*;

/***
 * Class for managing spring GUI
 */

public class GUI {
    public GUI() {
        // Create a new JFrame called mainFrame and enable exit on close
        JFrame mainFrame = new JFrame("MazeApp");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set mainFrame window size to 800x600
        mainFrame.setSize(800, 600);

        // Set mainFrame visibility to true
        mainFrame.setVisible(true);
    }


}
