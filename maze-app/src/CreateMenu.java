import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class CreateMenu implements IMenu, ActionListener {
    public final static String TITLE = "Maze Creator"; //title of the menu
    private Maze currentMaze = new Maze("", "", 20, 10); // create a new maze with default values
    private ArrayList<JButton> buttonList = new ArrayList<>(); // list of buttons in the side panel and footer panel
    private ArrayList<JLabel> labelList = new ArrayList<>(); // list of labels in the side panel
    private ArrayList<JTextField> fieldlist = new ArrayList<>(); // list of text fields in the side panel
    private JPanel contentPanel; // the panel that holds the maze
    private boolean solved = false; // boolean to check if the maze has been solved
    public JPanel createPanel; // the panel that holds all the components of the menu
    public Color headerGreen = Color.getHSBColor(0.35f, 0.7f, 0.6f); // greenish color of the header
    public Color subheader = Color.getHSBColor(0.35f, 0.1f, 0.8f); // greenish color of the subheader

    public CreateMenu() {
        //panel code
        createPanel = new JPanel(new BorderLayout());

        // Set boundary of panel
        createPanel.setBounds(0, 0, Frame.WIDTH, Frame.HEIGHT+10);

        //creating panels for layout inside the container panel
        JPanel headerPanel = GUIHelper.panelLayout(createPanel,headerGreen , BorderLayout.NORTH); //Header
        JPanel sidePanel = GUIHelper.panelLayout(createPanel, subheader, BorderLayout.WEST); //sidebar
        contentPanel = GUIHelper.panelLayout(createPanel, Color.WHITE, BorderLayout.EAST); // where the maze goes
        JPanel footerPanel = GUIHelper.panelLayout(createPanel, headerGreen, BorderLayout.SOUTH); //footer

        //the dimension of the panels
        int newHeight = (int) Math.round((Frame.HEIGHT) * 0.08);
        headerPanel.setPreferredSize(new Dimension(Frame.WIDTH, newHeight));
        sidePanel.setPreferredSize(new Dimension((int) Math.round((Frame.WIDTH)*0.3), (int) Math.round(Frame.HEIGHT*0.5)));
        contentPanel.setPreferredSize(new Dimension((int) Math.round((Frame.WIDTH)*0.7), (int) Math.round(Frame.HEIGHT*0.5)));
        footerPanel.setPreferredSize(new Dimension(Frame.WIDTH, newHeight));
        //panel5.setPreferredSize(new Dimension((int)Math.round((sidePanel.WIDTH) * 0.80), (int)Math.round((sidePanel.HEIGHT) * 0.80)));

        //Create labels for sidePanel
        JLabel labelTitle = GUIHelper.createLabel(TITLE, headerPanel, 0,0);
        labelList.add(GUIHelper.createLabel("Title: ", sidePanel, 0 ,0));
        labelList.add(GUIHelper.createLabel("Author: ", sidePanel, 0,1));
        labelList.add(GUIHelper.createLabel("Created: ", sidePanel,  0,2));
        labelList.add(GUIHelper.createLabel("Last Edited: ", sidePanel,  0,3));
        labelList.add(GUIHelper.createLabel("Width: ", sidePanel, 0,4));
        labelList.add(GUIHelper.createLabel("Height: ", sidePanel, 0,5));

        //Create text fields for sidePanel
        fieldlist.add(GUIHelper.newTextField(new Dimension(50, 50), sidePanel, 1, 0, true));
        fieldlist.add(GUIHelper.newTextField(new Dimension(50, 50), sidePanel, 1, 1, true));
        fieldlist.add(GUIHelper.newTextField(new Dimension(50, 50), sidePanel, 1, 2, false));
        fieldlist.add(GUIHelper.newTextField(new Dimension(50, 50), sidePanel, 1, 3, false));
        fieldlist.add(GUIHelper.newTextField(new Dimension(50, 50), sidePanel, 1, 4, true));
        fieldlist.add(GUIHelper.newTextField(new Dimension(50, 50), sidePanel, 1, 5, true));

        // Set the width and height text fields to the default mazes width and height
        fieldlist.get(4).setText("20");
        fieldlist.get(5).setText("10");

        //Label formatting for the Header
        labelTitle.setFont(new Font("Century Gothic", Font.BOLD, 40));
        labelTitle.setForeground(Color.WHITE);

        //for loop to format the JLabels in the SideBar
        for (JLabel  text: labelList) {
            text.setFont(new Font("Century Gothic", Font.BOLD, 14));
        }

        // Create buttons and add buttons to button list using GUIHelper
        buttonList.add(GUIHelper.newButton("Update Maze", sidePanel, 0, 6, 10, 10, 5, 5));
        buttonList.add(GUIHelper.newButton("Show Solution", sidePanel, 1, 6, 10, 5, 5, 10));
        buttonList.add(GUIHelper.newButton("Save Maze", sidePanel, 0, 7, 10, 10, 5, 5));
        buttonList.add(GUIHelper.newButton("Generate New Maze", sidePanel, 1, 7, 10, 5, 5, 10));
        buttonList.add(GUIHelper.newButton("Load Maze", sidePanel, 0, 8, 10, 10, 5, 5));
        buttonList.add(GUIHelper.newButton("Play", sidePanel, 1, 8, 10, 10, 5, 5));
        buttonList.add(GUIHelper.newButton("Clear", sidePanel, 0, 9, 10, 10, 5, 5));
        buttonList.add(GUIHelper.newButton("Back", footerPanel, 0, 0, 5, 10, 5, (int) Math.round(Frame.WIDTH*0.9)));

        //Adds panel to Main.frame
        Main.frame.add(createPanel);

        // Add JButtons in buttonList to sidePanel and add action listeners
        for (JButton button : buttonList) {
            button.addActionListener(this);
        }

        //render the default maze
        currentMaze.render(contentPanel);
        createPanel.setVisible(false);
    }

    // Standard open menu method
    public void openMenu() {
        Main.frame.setTitle(Frame.TITLE_PREFIX + TITLE);
        createPanel.setVisible(true);

        try {
            BufferedImage picture = ImageIO.read(new File("PlaceholderMaze.png"));
            JLabel imageLabel = new JLabel(new ImageIcon(picture));
            createPanel.add(imageLabel);
        } catch (IOException e) {
            //System.out.println("Invalid");
        }
    }

    // openMenu override for use with browsemenu
    public void openMenu(Maze maze) {
        currentMaze = maze;
        fieldlist.get(0).setText(currentMaze.getTitle());
        fieldlist.get(1).setText(currentMaze.getCreator());
        fieldlist.get(2).setText(currentMaze.getCreatedRaw().toString());	
        fieldlist.get(4).setText(currentMaze.getWidth() + "");
        fieldlist.get(5).setText(currentMaze.getHeight() + "");
        contentPanel.removeAll();
        currentMaze.render(contentPanel);
        Main.frame.setTitle(Frame.TITLE_PREFIX + TITLE);
        createPanel.setVisible(true);

        try {
            BufferedImage picture = ImageIO.read(new File("PlaceholderMaze.png"));
            JLabel imageLabel = new JLabel(new ImageIcon(picture));
            createPanel.add(imageLabel);
        } catch (IOException e) {
            //System.out.println("Invalid");
        }
    }

    // Standard close menu method
    public void closeMenu() {
        createPanel.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Switch statement to detect which button is pressed
        switch (e.getActionCommand()) {
            case "Load Maze": // Closes createMenu and opens browseMenu
                closeMenu();
                Main.browseMenu.openMenu(true);
                break;
            case "Save Maze": // Saves the current maze to the database
                if (!fieldlist.get(0).getText().isEmpty() && !fieldlist.get(1).getText().isEmpty() && !fieldlist.get(4).getText().isEmpty() && !fieldlist.get(5).getText().isEmpty()) {
                    currentMaze.setTitle(fieldlist.get(0).getText());
                    currentMaze.setCreator(fieldlist.get(1).getText());
                    currentMaze.setWidth(Integer.parseInt(fieldlist.get(4).getText()));
                    currentMaze.setHeight(Integer.parseInt(fieldlist.get(5).getText()));
                    Database.addMaze(currentMaze);
                    System.out.println("Maze saved");
                }                
                break;
            case "Update Maze": // Updates the current maze with the current values in the text fields
                currentMaze.setTitle(fieldlist.get(0).getText());
                currentMaze.setCreator(fieldlist.get(1).getText());
                currentMaze.setID(Main.database.getNextID());
                if (currentMaze.getWidth() != Integer.parseInt(fieldlist.get(4).getText()) && currentMaze.getHeight() != Integer.parseInt(fieldlist.get(5).getText())) {
                    currentMaze.setWidth(Integer.parseInt(fieldlist.get(4).getText()));
                    currentMaze.setHeight(Integer.parseInt(fieldlist.get(5).getText()));
                    currentMaze.regenerateMaze();
                    contentPanel.removeAll();
                    contentPanel.revalidate();
                    contentPanel.repaint();
                    currentMaze.render(contentPanel);
                }
                break;
            case "Show Solution":   // Shows the solution of the current maze
            case "Hide Solution":   // Hides the solution of the current maze
                if (!solved) {
                    buttonList.get(1).setText("Hide Solution");
                }
                else {
                    buttonList.get(1).setText("Show Solution");
                }
                currentMaze.solve(solved);
                contentPanel.revalidate();
                contentPanel.repaint();
                solved = !solved;
                break;
            case "Generate New Maze": // Generates a new maze with the current values in the text fields and renders it so long as the width and height are valid
                if (!fieldlist.get(4).getText().isEmpty() && !fieldlist.get(5).getText().isEmpty()) {
                    currentMaze = new Maze(fieldlist.get(0).getText(), fieldlist.get(1).getText(), Integer.parseInt(fieldlist.get(4).getText()), Integer.parseInt(fieldlist.get(5).getText()));
                    contentPanel.removeAll();
                    contentPanel.revalidate();
                    contentPanel.repaint();
                    currentMaze.render(contentPanel);
                }                
                break;
            case "Play" : // Plays the current maze in the playMenu
                closeMenu();
                PlayMenu playMenu = new PlayMenu(currentMaze);
                playMenu.openMenu();
                break;
            case "Clear": // Clears the current maze
                currentMaze.clearLayout();
                contentPanel.removeAll();
                currentMaze.render(contentPanel);
                break;

            case "Back":    // Closes the createMenu and opens the mainMenu
                closeMenu();
                Main.mainMenu.openMenu();
                break;
        }

    }

}