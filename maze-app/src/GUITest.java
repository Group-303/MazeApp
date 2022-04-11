public class GUITest {
    public static void main(String[] args) {
        System.out.println("Nothing should happen here...");
    }
}
/*import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GUITest extends JFrame implements ActionListener, Runnable {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private JPanel pnlOne;
    private JPanel pnlTwo;
    private JPanel pnlThree;
    private JPanel pnlFour;
    private JPanel pnlFive;

    public GUITest(String borderLayout) {
        createGUI();
    }

    private void createGUI() {
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        pnlOne = createPanel(Color.WHITE);
        pnlTwo = createPanel(Color.GREEN);
        pnlThree = createPanel(Color.BLUE);
        pnlFour = createPanel(Color.RED);
        pnlFive = createPanel(Color.YELLOW);

        getContentPane().add(pnlOne,BorderLayout.CENTER);
        getContentPane().add(pnlTwo,BorderLayout.WEST);
        getContentPane().add(pnlThree,BorderLayout.SOUTH);
        getContentPane().add(pnlFour,BorderLayout.EAST);
        getContentPane().add(pnlFive,BorderLayout.NORTH);

        setVisible(true);



        String s = (String)JOptionPane.showInputDialog(this,"Description of edits made",
                "Edit Description", JOptionPane.PLAIN_MESSAGE, null, null, "");
        Maze mazeTest = new Maze("Test Maze", "John Smith", 8, 6);
        mazeTest.addEdit(new Edit(s, "Dummy Username"));

        for (Edit edit : mazeTest.getEdits()) {
            System.out.println("| " + edit.getTime() + " | " + edit.getEditor() + ": " + edit.getDescription());
        }
    }

    private JPanel createPanel(Color c) {
        JPanel panel = new JPanel();
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void run() {

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new GUITest("BorderLayout"));
    }
}*/

// Could not exclude the Class, so I commented everything out
// Feel free to remove after use