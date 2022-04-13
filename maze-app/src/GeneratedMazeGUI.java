import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GeneratedMazeGUI implements ActionListener {
    private JButton generateSolution, regenerate, save;
    private List<JButton> buttonList = new ArrayList<>();

    public void GeneratedMazeGUI() {
        JFrame generatedFrame = new JFrame();

        //Create Panels
        JPanel panel1 = new JPanel(new BorderLayout());
        JPanel panel2 = new JPanel(new BorderLayout());
        JPanel panel3 = new JPanel(new BorderLayout());
        JPanel panel4 = new JPanel(new BorderLayout());
        JPanel panel5 = new JPanel(new BorderLayout());
        JPanel panel6 = new JPanel(new BorderLayout());

        //Set panel background colour
        //.setBackground(Color.gray);

        //Create buttons and add buttons to button list using GUIHelper
        buttonList.add(GUIHelper.newButton("Generate Solution", new Dimension(100, 50), new Position(Main.frame.H_CENTER - 200, Main.frame.V_CENTER - 200)));
        buttonList.add(GUIHelper.newButton("Regenerate", new Dimension(100, 50), new Position(Main.frame.H_CENTER + 200, Main.frame.V_CENTER - 200)));
        buttonList.add(GUIHelper.newButton("Save", new Dimension(100, 50), new Position(Main.frame.H_CENTER, Main.frame.V_CENTER + 200)));

        for (JButton button : buttonList) {
            panel5.add(button);
        }

        // Add mainGUIPanel to mainGUIFrame
        //Main.frame.add(panel5);

        for (JButton button : buttonList) {
            button.addActionListener(this);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}

