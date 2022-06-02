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
import java.util.ArrayList;
import java.util.List;

public class CreateMenu implements IMenu, ActionListener {
    public final static String TITLE = "Maze Creator";
    //private ButtonGroup buttonGroup = new ButtonGroup();

    //private JButton generateSolution, regenerate, save, back;
    private Maze currentMaze = Main.testMaze;

    private ArrayList<JButton> buttonList = new ArrayList<>();
    private ArrayList<JLabel> labelList = new ArrayList<>();
    private ArrayList<JTextField> fieldlist = new ArrayList<>();
    private ArrayList<JButton[][]> mazeButtons = new ArrayList<>();

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
        JPanel contentPanel = GUIHelper.panelLayout(createPanel, Color.WHITE, BorderLayout.EAST); // where the maze goes
        JPanel footerPanel = GUIHelper.panelLayout(createPanel, headerGreen, BorderLayout.SOUTH); //footer

        //the dimension of the panels
        int newHeight = (int) Math.round((Frame.HEIGHT) * 0.08);
        headerPanel.setPreferredSize(new Dimension(Frame.WIDTH, newHeight));
        sidePanel.setPreferredSize(new Dimension((int) Math.round((Frame.WIDTH)*0.3), (int) Math.round(Frame.HEIGHT*0.5)));
        contentPanel.setPreferredSize(new Dimension((int) Math.round((Frame.WIDTH)*0.7), (int) Math.round(Frame.HEIGHT*0.5)));
        footerPanel.setPreferredSize(new Dimension(Frame.WIDTH, newHeight));
        //panel5.setPreferredSize(new Dimension((int)Math.round((sidePanel.WIDTH) * 0.80), (int)Math.round((sidePanel.HEIGHT) * 0.80)));

        /*
        int bodyHeight = (int) Math.round((Frame.HEIGHT*0.3));

        JPanel headerPanel = GUIHelper.panelLayout(createPanel, headerGreen, 0,0, newHeight, 2); //Header
        JPanel sidePanel = GUIHelper.panelLayout(createPanel, subheader, 0,1, bodyHeight,1); //sidebar
        JPanel contentPanel = GUIHelper.panelLayout(createPanel, Color.WHITE, 1,1,bodyHeight,1); // where the maze goes
        JPanel footerPanel = GUIHelper.panelLayout(createPanel, headerGreen, 0,2,newHeight, 2); //footer

        */

        //Create labels
        JLabel labelTitle = GUIHelper.createLabel(TITLE, headerPanel, 0,0);
        labelList.add(GUIHelper.createLabel("Title: ", sidePanel, 0 ,0));
        labelList.add(GUIHelper.createLabel("Author: ", sidePanel, 0,1));
        labelList.add(GUIHelper.createLabel("Created: ", sidePanel,  0,2));
        labelList.add(GUIHelper.createLabel("Last Edited: ", sidePanel,  0,3));
        labelList.add(GUIHelper.createLabel("Width: ", sidePanel, 0,4));
        labelList.add(GUIHelper.createLabel("Height: ", sidePanel, 0,5));

        fieldlist.add(GUIHelper.newTextField(new Dimension(50, 50), sidePanel, 1, 0));
        fieldlist.add(GUIHelper.newTextField(new Dimension(50, 50), sidePanel, 1, 1));
        fieldlist.add(GUIHelper.newTextField(new Dimension(50, 50), sidePanel, 1, 2));
        fieldlist.add(GUIHelper.newTextField(new Dimension(50, 50), sidePanel, 1, 3));

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
        buttonList.add(GUIHelper.newButton("Upload Maze", sidePanel, 0, 6, 10, 10, 5, 5));
        buttonList.add(GUIHelper.newButton("Generate Solution", sidePanel, 1, 6, 10, 5, 5, 10));
        buttonList.add(GUIHelper.newButton("Save", sidePanel, 0, 7, 10, 10, 5, 5));
        buttonList.add(GUIHelper.newButton("Regenerate Maze", sidePanel, 1, 7, 10, 5, 5, 10));
        buttonList.add(GUIHelper.newButton("Back", footerPanel, 0, 0, 5, 10, 5, (int) Math.round(Frame.WIDTH*0.9)));

       // JRadioButton confirmRButton = GUIHelper.newRButton("Confirm", new Dimension(100, 50), new Point(sidePanel_H_CENTER+ 50, sidePanel_V_CENTER + 300));
       // createPanel.add(confirmRButton);

        //Adds panel to Main.frame
        Main.frame.add(createPanel);

//        // Add JButtons in buttonList to sidePanel and add action listeners
        for (JButton button : buttonList) {
//            //sidePanel.add(button);
            button.addActionListener(this);
        }
//        System.out.println(currentMaze.getLayout().length);
//
//        for (int i = 0; i < currentMaze.getLayout().length; i++) {
//            mazeButtons.add(arrayItemToButtons(currentMaze.getLayout(), i, contentPanel));
//        }
//
////        for (int i = 0; i < currentMaze.getWidth())
//
//        for (JButton[][] button : mazeButtons) {
//            contentPanel.add(button[0][0]);
//            contentPanel.add(button[0][1]);
//            contentPanel.add(button[1][0]);
//            contentPanel.add(button[1][1]);
//        }

        createPanel.setVisible(false);
    }

    public JButton[][] arrayItemToButtons(boolean[][][] inputArray, int index, JPanel panel) {
        JButton nwButton, northButton, westButton, centerButton;
        JButton[][] buttons = new JButton[2][2];

        // Add JButtons in buttonList to sidePanel and add action listeners
        for (JButton button : buttonList) {
            //sidePanel.add(button);
            button.addActionListener(this);
        }
        System.out.println(currentMaze.getLayout().length);

//        for (int i = 0; i < currentMaze.getLayout().length; i++) {
//            mazeButtons.add(arrayItemToButtons(currentMaze.getLayout(), i, contentPanel));
//        }

//        for (int i = 0; i < currentMaze.getWidth())
        for (int i = 0; i < currentMaze.getWidth(); i++)
        {

        }

        for (JButton[][] button : mazeButtons) {
            panel.add(button[0][0]);
            panel.add(button[0][1]);
            panel.add(button[1][0]);
            panel.add(button[1][1]);
        }

//        nwButton = new JButton();
//        nwButton.setBackground(Color.BLACK);
//        nwButton.setSize(new Dimension(50, 50));
//        nwButton.setEnabled(false);
//
//        northButton = new JButton();
//        if (inputArray[index][index][0] == false) {
//            northButton.setBackground(Color.WHITE);
//        }
//        else {
//            northButton.setBackground(Color.BLACK);
//        }
//        northButton.setSize(new Dimension(50, 50));
//        northButton.setEnabled(true);
//
//        westButton = new JButton();
//        if (inputArray[index][index][1] == false) {
//            westButton.setBackground(Color.WHITE);
//        }
//        else {
//            westButton.setBackground(Color.BLACK);
//        }
//        westButton.setSize(new Dimension(50, 50));
//        westButton.setEnabled(true);
//
//        centerButton = new JButton();
//        centerButton.setBackground(Color.WHITE);
//        centerButton.setSize(new Dimension(50, 50));
//        centerButton.setEnabled(false);

        buttons[0][0] = GUIHelper.newButton("­", panel, index, index, 0, 0, 0, 0);
        buttons[0][0].setFocusPainted(false);
        buttons[0][0].setEnabled(false);

        buttons[1][0] = GUIHelper.newButton("­", panel, index + 1, index, 0, 0, 0, 0);
        buttons[1][0].setFocusPainted(false);

        buttons[0][1] = GUIHelper.newButton("­", panel, index, index + 1, 0, 0, 0, 0);
        buttons[0][1].setFocusPainted(false);

        buttons[1][1] = GUIHelper.newButton("­", panel, index + 1, index + 1, 0, 0, 0, 0);
        buttons[1][1].setFocusPainted(false);
        buttons[1][1].setEnabled(false);

        buttons[0][1].setBackground(Color.WHITE);

        if (inputArray[index][index][0] == false) {
            buttons[1][0].setBackground(Color.WHITE);
        }
        else {
            buttons[1][0].setBackground(Color.BLACK);
        }
        if (inputArray[index][index][1] == false) {
            buttons[0][1].setBackground(Color.WHITE);
        }
        else {
            buttons[0][1].setBackground(Color.BLACK);
        }


        return buttons;
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

    @Override
    public void actionPerformed(ActionEvent e) {
        // Switch statement to detect which button is pressed
        switch (e.getActionCommand()) {
            case "Load":
                break;
            case "New":
                break;
            case "Save":
                break;
            case "Regenerate Maze":
                currentMaze.regenerateMaze();
                break;
            case "Back":
                closeMenu();
                Main.mainMenu.openMenu();
                break;
        }
    }

}