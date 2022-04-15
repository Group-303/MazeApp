import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/***
 * Class for managing spring MainMenu
 */

public class MainMenu implements ActionListener {


    public JPanel mainPanel;
    private JButton CreateMazeButton, GenerateMazeButton, BrowseMazeButton;
    private List<JButton> componentList = new ArrayList<>();

    public MainMenu() {
        // Create JPanel for MainGUIFrame
        mainPanel = new JPanel();

        // Set mainGUIPanel to be in the center of the frame
        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 0, Frame.WIDTH, Frame.HEIGHT);

        // Add buttons to components list
        componentList.add(GUIHelper.newButton("Create New", new Dimension(300, 300), new Point(Frame.H_CENTER - 200, Frame.V_CENTER - 200)));
        componentList.add(GUIHelper.newButton("Generate New", new Dimension(300, 300), new Point(Frame.H_CENTER + 200, Frame.V_CENTER - 200)));
        componentList.add(GUIHelper.newButton("Browse Mazes", new Dimension(700, 300), new Point(Frame.H_CENTER, Frame.V_CENTER + 200)));

        for (JButton button : componentList) {
            mainPanel.add(button);
        }

        // Add mainGUIPanel to mainGUIFrame
        Main.frame.add(mainPanel);

        for (JButton button : componentList) {
            button.addActionListener(this);
        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainPanel.setVisible(false);

        switch (e.getSource().toString()) {
            case "Create Maze":
                CreateMazeButton.setEnabled(false);
                Main.frame.setTitle("MazeCo - Create New Maze");
                break;
            case "Generate Maze":
                GenerateMazeButton.setEnabled(false);
                Main.frame.setTitle("MazeCo - Generate A Maze");
                break;
            case "Browse Mazes":
                Main.browseMenu.browsePanel.setVisible(true);
                Main.frame.setTitle("MazeCo - Browse Mazes");
                break;
        }
    }
}
