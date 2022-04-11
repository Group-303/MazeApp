import javax.swing.*;
import java.awt.event.*;

/***
 * Class for managing spring MainGUI
 */

public class MainGUI implements ActionListener {
    private JButton CreateMazeButton, GenerateMazeButton, BrowseMazeButton;

    public MainGUI() {
        // Create a new JFrame called mainFrame and enable exit on close
        JFrame mainGUIFrame = new JFrame("MazeApp");

        // Set mainFrame window size to 800x800
        mainGUIFrame.setSize(800, 800);

        // Create JPanel for MainGUIFrame
        JPanel mainGUIPanel = new JPanel();

        // Set mainGUIPanel to be in the center of the mainGUIFrame
        mainGUIPanel.setLayout(null);
        mainGUIPanel.setBounds(0, 0, 800, 800);

        // Create JButtons called CreateMazeButton, GenerateMazeButton, and LoadMazeButton
        CreateMazeButton = new JButton("Create Your Own");
        GenerateMazeButton = new JButton("Generate");
        BrowseMazeButton = new JButton("Browse");

        // Add CreateMazeButton, GenerateMazeButton and LoadMazeButton to mainGUIPanel
        mainGUIPanel.add(CreateMazeButton);
        mainGUIPanel.add(GenerateMazeButton);
        mainGUIPanel.add(BrowseMazeButton);

        // Set the location of the buttons to the center of mainGUIFrame
        CreateMazeButton.setLocation(40, 60);
        GenerateMazeButton.setLocation(440, 60);
        BrowseMazeButton.setLocation(40, 410);

        // Set the size of CreateMazeButton, GenerateMazeButton and LoadMazeButton to 300x300
        CreateMazeButton.setSize(300, 300);
        GenerateMazeButton.setSize(300, 300);
        BrowseMazeButton.setSize(700, 300);

        // Add mainGUIPanel to mainGUIFrame
        mainGUIFrame.add(mainGUIPanel);

        // Add action listeners for CreateMazeButton, GenerateMazeButton, and LoadMazeButton
        CreateMazeButton.addActionListener(this);
        GenerateMazeButton.addActionListener(this);
        BrowseMazeButton.addActionListener(this);

        // Set mainFrame visibility to true
        mainGUIFrame.setLocationRelativeTo(null);
        mainGUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainGUIFrame.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getSource().toString()) {
            case "Create Maze":
                CreateMazeButton.setEnabled(false);
                break;
            case "Generate Maze":
                GenerateMazeButton.setEnabled(false);
                break;
            case "Load Maze":
                BrowseMazeButton.setEnabled(false);
                break;
        }
    }
}
