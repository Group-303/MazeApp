import javax.swing.*;
import java.awt.event.*;

/***
 * Class for managing spring MainGUI
 */

public class MainGUI implements ActionListener {
    private JButton CreateMazeButton, GenerateMazeButton, LoadMazeButton;

    public MainGUI() {
        // Create a new JFrame called mainFrame and enable exit on close
        JFrame mainGUIFrame = new JFrame("MazeApp");

        // Set mainFrame window size to 800x600
        mainGUIFrame.setSize(800, 600);

        // Create JPanel for MainGUIFrame
        JPanel mainGUIPanel = new JPanel();

        // Set mainGUIPanel to be in the center of the mainGUIFrame
        mainGUIPanel.setLayout(null);
        mainGUIPanel.setBounds(0, 0, 800, 600);

        // Create JButtons called CreateMazeButton, GenerateMazeButton, and LoadMazeButton
        CreateMazeButton = new JButton("Create Maze");
        GenerateMazeButton = new JButton("Generate Maze");
        LoadMazeButton = new JButton("Load Maze");

        // Add CreateMazeButton, GenerateMazeButton and LoadMazeButton to mainGUIPanel
        mainGUIPanel.add(CreateMazeButton);
        mainGUIPanel.add(GenerateMazeButton);
        mainGUIPanel.add(LoadMazeButton);

        // Set the location of the buttons to the center of mainGUIFrame
        CreateMazeButton.setLocation(300, 150);
        GenerateMazeButton.setLocation(300, 250);
        LoadMazeButton.setLocation(300, 350);

        // Set the size of CreateMazeButton, GenerateMazeButton and LoadMazeButton to 100x50
        CreateMazeButton.setSize(200, 50);
        GenerateMazeButton.setSize(200, 50);
        LoadMazeButton.setSize(200, 50);

        // Add mainGUIPanel to mainGUIFrame
        mainGUIFrame.add(mainGUIPanel);

        // Add action listeners for CreateMazeButton, GenerateMazeButton, and LoadMazeButton
        CreateMazeButton.addActionListener(this);
        GenerateMazeButton.addActionListener(this);
        LoadMazeButton.addActionListener(this);

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
                LoadMazeButton.setEnabled(false);
                break;
        }
    }
}
