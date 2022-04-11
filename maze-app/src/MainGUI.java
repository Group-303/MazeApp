import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/***
 * Class for managing spring MainGUI
 */

public class MainGUI implements ActionListener {
    private final static int WIDTH = 1200;
    private final static int HEIGHT = 800;
    private final static int CENTER = WIDTH / 2;

    public JPanel mainPanel;
    private JButton CreateMazeButton, GenerateMazeButton, BrowseMazeButton;
    private List<JButton> componentList = new ArrayList<>();

    public MainGUI() {
        // Create a new JFrame called mainFrame and enable exit on close
        Main.frame = new JFrame("MazeApp");

        // Set mainFrame window size to 800x800
        Main.frame.setSize(WIDTH, HEIGHT);

        // Create JPanel for MainGUIFrame
        mainPanel = new JPanel();

        // Set mainGUIPanel to be in the center of the frame
        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 0, WIDTH, HEIGHT);

        // Create new GUIHelper
        GUIHelper guiHelper = new GUIHelper();

        // Create JButtons called CreateMazeButton, GenerateMazeButton, and LoadMazeButton

        //CreateMazeButton = new JButton("Create Your Own");
        //GenerateMazeButton = new JButton("Generate");
        //BrowseMazeButton = new JButton("Browse");


        // Add CreateMazeButton, GenerateMazeButton and LoadMazeButton to mainGUIPanel

        //mainPanel.add(CreateMazeButton);
        //mainPanel.add(GenerateMazeButton);
        //mainPanel.add(BrowseMazeButton);

        // Add buttons to components list
        componentList.add(guiHelper.newButton("Create New", new Dimension(300, 300), new Position(CENTER - 350, 60)));
        componentList.add(guiHelper.newButton("Generate New", new Dimension(300, 300), new Position(CENTER + 50, 60)));
        componentList.add(guiHelper.newButton("Browse Mazes", new Dimension(700, 300), new Position(CENTER - 350, 410)));

        for (JButton button : componentList) {
            Main.frame.add(button);
        }

        // Set the location of the buttons to the center of mainGUIFrame

        //CreateMazeButton.setLocation(40, 60);
        //GenerateMazeButton.setLocation(440, 60);
        //BrowseMazeButton.setLocation(40, 410);

        // Set the size of CreateMazeButton, GenerateMazeButton and LoadMazeButton to 300x300

        //CreateMazeButton.setSize(300, 300);
        //GenerateMazeButton.setSize(300, 300);
        //BrowseMazeButton.setSize(700, 300);

        // Add mainGUIPanel to mainGUIFrame
        Main.frame.add(mainPanel);

        // Center the frame on the screen
        Main.frame.setLocationRelativeTo(null);

        // Set Main.frame visibility to true
        Main.frame.setVisible(true);

        // Prevent resizing of window
        Main.frame.setResizable(false);

        // Add action listeners for CreateMazeButton, GenerateMazeButton, and LoadMazeButton

        //CreateMazeButton.addActionListener(this);
        //GenerateMazeButton.addActionListener(this);
        //BrowseMazeButton.addActionListener(this);
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
                break;
            case "Generate Maze":
                GenerateMazeButton.setEnabled(false);
                break;
            case "Load Maze":
                Main.browseGui.browsePanel.setVisible(true);
                break;
        }
    }
}
