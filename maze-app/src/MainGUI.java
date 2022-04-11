import javax.swing.*;

/***
 * Class for managing spring MainGUI
 */

public class MainGUI extends JFrame {
    public MainGUI() {
        // Create a new JFrame called mainFrame and enable exit on close
        super("MazeApp");

        // Set mainFrame window size to 800x600
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set mainFrame visibility to true
        setVisible(true);

        setLocationRelativeTo(null);
    }


}
