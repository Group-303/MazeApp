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
    public final static String TITLE = "Maze Creator";
    //private ButtonGroup buttonGroup = new ButtonGroup();

    //private JButton generateSolution, regenerate, save, back;
    private Maze currentMaze = new Maze("", "", 10, 10);

    private ArrayList<JButton> buttonList = new ArrayList<>();
    private ArrayList<JLabel> labelList = new ArrayList<>();
    private ArrayList<JTextField> fieldlist = new ArrayList<>();
    private ArrayList<JButton[][]> mazeButtons = new ArrayList<>();
    private ArrayList<JButton> mazeButtonList = new ArrayList<>();

    private JPanel contentPanel;

    public JPanel createPanel;
    public Color headerGreen = Color.getHSBColor(0.35f, 0.7f, 0.6f);
    public Color subheader = Color.getHSBColor(0.35f, 0.1f, 0.8f);

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

        //Create labels
        JLabel labelTitle = GUIHelper.createLabel(TITLE, headerPanel, 0,0);
        labelList.add(GUIHelper.createLabel("Title: ", sidePanel, 0 ,0));
        labelList.add(GUIHelper.createLabel("Author: ", sidePanel, 0,1));
        labelList.add(GUIHelper.createLabel("Created: ", sidePanel,  0,2));
        labelList.add(GUIHelper.createLabel("Last Edited: ", sidePanel,  0,3));
        labelList.add(GUIHelper.createLabel("Width: ", sidePanel, 0,4));
        labelList.add(GUIHelper.createLabel("Height: ", sidePanel, 0,5));

        fieldlist.add(GUIHelper.newTextField(new Dimension(50, 50), sidePanel, 1, 0, true));
        fieldlist.add(GUIHelper.newTextField(new Dimension(50, 50), sidePanel, 1, 1, true));
        fieldlist.add(GUIHelper.newTextField(new Dimension(50, 50), sidePanel, 1, 2, false));
        fieldlist.add(GUIHelper.newTextField(new Dimension(50, 50), sidePanel, 1, 3, false));
        fieldlist.add(GUIHelper.newTextField(new Dimension(50, 50), sidePanel, 1, 4, true));
        fieldlist.add(GUIHelper.newTextField(new Dimension(50, 50), sidePanel, 1, 5, true));

        contentPanel.add(new TestingPanelGraphics.DrawStuff());

        //Label formatting for the Header
        labelTitle.setFont(new Font("Century Gothic", Font.BOLD, 40));
        labelTitle.setForeground(Color.WHITE);

        //for loop to format the JLabels in the SideBar
        for (JLabel  text: labelList) {
            text.setFont(new Font("Century Gothic", Font.BOLD, 14));
        }

        // sidePanel horizontal and vertical centers
        //int sidePanel_V_CENTER = Math.round((sidePanel.getHeight()));
        //int sidePanel_H_CENTER = Math.round((sidePanel.getWidth()));
       // int sidePanel_V_CENTER = Math.round((sidePanel.getHeight()));
       // int sidePanel_H_CENTER = Math.round((sidePanel.getWidth()));

        // Create buttons and add buttons to button list using GUIHelper

        buttonList.add(GUIHelper.newButton("Update Maze", sidePanel, 0, 6, 10, 10, 5, 5));
        buttonList.add(GUIHelper.newButton("Generate Solution", sidePanel, 1, 6, 10, 5, 5, 10));
        buttonList.add(GUIHelper.newButton("Save Maze", sidePanel, 0, 7, 10, 10, 5, 5));
        buttonList.add(GUIHelper.newButton("Generate New Maze", sidePanel, 1, 7, 10, 5, 5, 10));
        buttonList.add(GUIHelper.newButton("Load Maze", sidePanel, 0, 8, 10, 10, 5, 5));
        buttonList.add(GUIHelper.newButton("Back", footerPanel, 0, 0, 5, 10, 5, (int) Math.round(Frame.WIDTH*0.9)));

       // JRadioButton confirmRButton = GUIHelper.newRButton("Confirm", new Dimension(100, 50), new Point(sidePanel_H_CENTER+ 50, sidePanel_V_CENTER + 300));
       // createPanel.add(confirmRButton);

        //Adds panel to Main.frame
        Main.frame.add(createPanel);

        // Add JButtons in buttonList to sidePanel and add action listeners
        for (JButton button : buttonList) {
            button.addActionListener(this);
        }

        currentMaze.render(contentPanel);

        createPanel.setVisible(false);
    }

    private void setCreated() {
        if (fieldlist.get(2).getText().isEmpty()) {
            fieldlist.get(2).setText(Long.toString(System.currentTimeMillis()));
        }
    }

    private void setEdited() {
        fieldlist.get(3).setText(Long.toString(System.currentTimeMillis()));
    }

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

    public void closeMenu() {
        createPanel.setVisible(false);
    }

    private void createMaze() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Switch statement to detect which button is pressed
        switch (e.getActionCommand()) {
            case "Load Maze":
                closeMenu();
                Main.browseMenu.openMenu();
                break;
            case "Save Maze":
                createMaze();
                setCreated();
                setEdited();
                break;
            case "Update Maze":
                currentMaze.setTitle(fieldlist.get(0).getText());
                currentMaze.setCreator(fieldlist.get(1).getText());
                currentMaze.setWidth(Integer.parseInt(fieldlist.get(4).getText()));
                currentMaze.setHeight(Integer.parseInt(fieldlist.get(5).getText()));
                currentMaze.regenerateMaze();
                contentPanel.removeAll();
                contentPanel.revalidate();
                contentPanel.repaint();
                currentMaze.render(contentPanel);
                break;
            case "Generate Solution":
                break;
            case "Generate New Maze":
                break;
            case "Back":
                closeMenu();
                Main.mainMenu.openMenu();
                break;
        }

    }

}