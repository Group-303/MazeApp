import javax.swing.*;
import java.awt.event.*;

/***
 * Class for managing spring MainGUI
 */

public class MainGUI implements ActionListener {

    public JPanel mainPanel;
    private JButton CreateMazeButton, GenerateMazeButton, BrowseMazeButton;

    public MainGUI() {
        // Create a new JFrame called mainFrame and enable exit on close
        Main.frame = new JFrame("MazeApp");

        // Set mainFrame window size to 800x800
        Main.frame.setSize(800, 800);

        // Create JPanel for MainGUIFrame
        mainPanel = new JPanel();

        // Set mainGUIPanel to be in the center of the frame
        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 0, 800, 800);

        // Create JButtons called CreateMazeButton, GenerateMazeButton, and LoadMazeButton
        CreateMazeButton = new JButton("Create Your Own");
        GenerateMazeButton = new JButton("Generate");
        BrowseMazeButton = new JButton("Browse");

        // Add CreateMazeButton, GenerateMazeButton and LoadMazeButton to mainGUIPanel
        mainPanel.add(CreateMazeButton);
        mainPanel.add(GenerateMazeButton);
        mainPanel.add(BrowseMazeButton);

        // Set the location of the buttons to the center of mainGUIFrame
        CreateMazeButton.setLocation(40, 60);
        GenerateMazeButton.setLocation(440, 60);
        BrowseMazeButton.setLocation(40, 410);

        // Set the size of CreateMazeButton, GenerateMazeButton and LoadMazeButton to 300x300
        CreateMazeButton.setSize(300, 300);
        GenerateMazeButton.setSize(300, 300);
        BrowseMazeButton.setSize(700, 300);

        // Add mainGUIPanel to mainGUIFrame
        Main.frame.add(mainPanel);

        // Set Main.frame visibility to true
        Main.frame.setVisible(true);

        // Add action listeners for CreateMazeButton, GenerateMazeButton, and LoadMazeButton
        CreateMazeButton.addActionListener(this);
        GenerateMazeButton.addActionListener(this);
        BrowseMazeButton.addActionListener(this);


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
