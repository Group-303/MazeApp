import javax.swing.*;
import java.awt.*;

/***
 * Class for managing spring GUI
 */

public class GUI extends JFrame {
    private final JFrame frame;

    public GUI() {
        this.frame = new JFrame("Maze App");
        // Create a new JFrame called mainFrame and enable exit on close
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setPreferredSize(new Dimension(800, 600));

        // Set mainFrame visibility to true
        this.frame.setVisible(true);
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
    }

    // EDIT: Disabled for ease of access. Was causing issues?
    // Testing method for changing title of the GUI (possibly change title to the current opened maze?)
    public void setTitle(String newTitle) {
        //JFrame.setTitle(newTitle);
    }

    // Not sure of this implementation?
    public void newPanel() {
        JPanel panel = new JPanel();
        this.frame.add(panel);
    }

    // Supposed to add new label to a particular panel?
    // Perhaps switch to a more static method and switch between
    public void newLabel(String labelText, JPanel panel) {
        panel.add(new JLabel(labelText));
    }

    // I think I made a mess of this, but we should switch this
    // to the 'extends ... implements ...' method rather than the
    // JFrame frameName = new JFrame() method.
    //
    // Also, I looked into making dialogue boxes:
    // String s = (String)JOptionPane.showInputDialog(this, "Text that is displayed on the dialogue box",
    //                "Title of the dialogue box", JOptionPane.PLAIN_MESSAGE, null, null, "");
    // This should display an input dialogue box if you need that.
    // Below are the input names and their corresponding types:
    // JOptionPane.showInputDialog(parentComponent:, description:, title:,   mes: , icon: , selectionValues:, initialSelectionValue:);
    //                            (     JFrame     ,    String   , String, JOption, Icon  ,     Object[]    ,        String         )
}
