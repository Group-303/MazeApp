import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/***
 * Class for managing spring MainMenu
 */

public class MainMenu implements IMenu, ActionListener {
    public final static String TITLE = "Main Menu";

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

        // Add all components into the main panel
        for (JButton button : componentList) {
            mainPanel.add(button);
        }

        // Add mainGUIPanel to mainGUIFrame
        Main.frame.add(mainPanel);

        // Add action listeners to each JButton component
        for (JButton button : componentList) {
            button.addActionListener(this);
        }

        openMenu();

    }

    public void openMenu() {
        mainPanel.setVisible(true);
        Main.frame.setTitle(Frame.TITLE_PREFIX + TITLE);
        for (JButton button : componentList) {
            button.setEnabled(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainPanel.setVisible(false);

        if (e.getSource() == componentList.get(0)) {
            componentList.get(0).setEnabled(false);
            Main.createMenu.createPanel.setVisible(true);
        }
        else if (e.getSource() == componentList.get(1)) {
            componentList.get(1).setEnabled(false);
            Main.createMenu.openMenu();
        }
        else if (e.getSource() == componentList.get(2)) {
            componentList.get(2).setEnabled(false);
            Main.browseMenu.browsePanel.setVisible(true);
        }

        //switch (e.getSource().toString()) {
        //    case "Create Maze":
        //        Main.createMenu.createPanel.setVisible(true);
        //        Main.frame.setTitle("MazeCo - Create New Maze");
        //        break;
        //    case "Generate Maze":
        //        GenerateMazeButton.setEnabled(false);
        //        Main.frame.setTitle("MazeCo - Generate A Maze");
        //        break;
        //    case "Browse Mazes":
        //        Main.browseMenu.browsePanel.setVisible(true);
        //        Main.frame.setTitle("MazeCo - Browse Mazes");
        //        break;
        //}
    }
}
