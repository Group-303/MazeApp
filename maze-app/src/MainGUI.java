import javax.swing.*;

/***
 * Class for managing spring MainGUI
 */

public class MainGUI extends JFrame {
    public MainGUI() {
        // Create a new JFrame called mainFrame and enable exit on close
        this.setTitle("MazeApp");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set mainFrame window size to 800x600 (not working)
        this.setSize(800, 600);

        // Set mainFrame visibility to true
        this.setVisible(true);
        this.pack();
        this.setLocationRelativeTo(null);
    }


}
