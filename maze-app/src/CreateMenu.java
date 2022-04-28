import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateMenu implements IMenu, ActionListener {
    public final static String TITLE = "Maze Creator";
    //private ButtonGroup buttonGroup = new ButtonGroup();

    private JButton generateSolution, regenerate, save, back;

    private ArrayList<JButton> buttonList = new ArrayList<>();
    //private ArrayList<ButtonGroup> bgroupList = new ArrayList<>();
    //private ArrayList<JRadioButton> radioButtonList = new ArrayList<>();
    private List<Component> componentList = new ArrayList<>();
    private GUIHelper guiHelper = new GUIHelper();
    public JPanel createPanel;
    public Color customGreen = Color.getHSBColor(0.35f,0.6f,0.5f);

    public CreateMenu() {
        //panel code
        createPanel = new JPanel();

        // Set boundary of panel
        createPanel.setBounds(0, 0, Frame.WIDTH, Frame.HEIGHT+Frame.H_MENU);

        //creating panels for layout inside the container panel
        // colours for panels 2 and 3 will be different
        JPanel headerPanel = guiHelper.panelLayout(createPanel, customGreen, BorderLayout.NORTH); //Header
        JPanel sidePanel = guiHelper.panelLayout(createPanel, Color.GRAY, BorderLayout.WEST); //sidebar
        JPanel contentPanel = guiHelper.panelLayout(createPanel, Color.LIGHT_GRAY, BorderLayout.EAST); // where the maze goes
        JPanel footerPanel = guiHelper.panelLayout(createPanel, customGreen, BorderLayout.SOUTH); //footer
        //JPanel panel5 = panelLayout (sidePanel, Color.WHITE, BorderLayout.CENTER);

        //the dimension of the panels
        int newHeight = (int) Math.round((Frame.HEIGHT) * 0.1);
        headerPanel.setPreferredSize(new Dimension(Frame.WIDTH, newHeight));
        sidePanel.setPreferredSize(new Dimension((int) Math.round((Frame.WIDTH)*0.30), (Frame.HEIGHT) - (newHeight * 2)));
        contentPanel.setPreferredSize(new Dimension((int) Math.round((Frame.WIDTH)*0.68), (Frame.HEIGHT) - (newHeight * 2)));
        footerPanel.setPreferredSize(new Dimension(Frame.WIDTH, newHeight));
        //panel5.setPreferredSize(new Dimension((int)Math.round((sidePanel.WIDTH) * 0.80), (int)Math.round((sidePanel.HEIGHT) * 0.80)));

        //Create labels
        JLabel labelTitle = guiHelper.createLabel(TITLE,headerPanel);
        JLabel label1 = guiHelper.createLabel("Title: ", sidePanel);
        JLabel label2 = guiHelper.createLabel("Author: ", sidePanel);
        JLabel label3 = guiHelper.createLabel("Created: ", sidePanel);
        JLabel label4 = guiHelper.createLabel("Last Edited: ", sidePanel);
        JLabel label5 = guiHelper.createLabel("Width: ", sidePanel);
        JLabel label6 = guiHelper.createLabel("Height: ", sidePanel);

        // Horizontal center and verticle center of sidePanel

        // Create buttons and add buttons to button list using GUIHelper
        buttonList.add(guiHelper.newButton("Generate Solution", new Dimension(100, 50), new Point(Frame.H_CENTER - 200, Frame.V_CENTER - 200)));
        buttonList.add(guiHelper.newButton("Regenerate", new Dimension(100, 50), new Point(Frame.H_CENTER + 200, Frame.V_CENTER - 200)));
        buttonList.add(guiHelper.newButton("Save", new Dimension(100, 50), new Point(Frame.H_CENTER, Frame.V_CENTER + 200)));
        buttonList.add(guiHelper.newButton("Back", new Dimension(100, 50), new Point(Frame.H_CENTER, Frame.V_CENTER - 200)));


        JRadioButton confirmRButton = guiHelper.newRButton("Confirm", new Dimension(100, 50), new Point(Frame.H_CENTER + 50, Frame.V_CENTER + 300));
        //buttonGroup.add(confirmRButton);
        createPanel.add(confirmRButton);
        //bgroupList.add(guiHelper.newButtonGroup());
        //radioButtonList.add(guiHelper.newRButton("Test", KeyEvent.VK_A, new Dimension(100, 100), new Point(Frame.H_CENTER + 200, Frame.V_CENTER - 200), true));

        //for (JRadioButton radioButton : radioButtonList) {
        //    bgroupList.get(0).add(radioButton);
        //}

        //createPanel.add(guiHelper.formButtonGroup(radioButtonList));

        // Add JButtons in buttonList to sidePanel
        for (JButton button : buttonList) {
            sidePanel.add(button);
        }

        //position Jbuttons in sidePanel to run down the page
        int buttonHeight = (int) Math.round((sidePanel.getHeight()) * 0.1);
        int buttonWidth = (int) Math.round((sidePanel.getWidth()) * 0.1);
        int buttonX = (int) Math.round((sidePanel.getWidth()) * 0.1);
        int buttonY = (int) Math.round((sidePanel.getHeight()) * 0.1);
        for (JButton button : buttonList) {
            button.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
            buttonY += buttonHeight;
        }


        // Add action listeners
        for (JButton button : buttonList) {
             button.addActionListener(this);
        }

        //for (JRadioButton radioButton : radioButtonList) {
        //    radioButton.addActionListener(this);
        //}

        //Adds panel to Main.frame
        Main.frame.add(createPanel);

        //this.panelsVisible(false);
        createPanel.setVisible(false);
    }
/* Moved to GUIHelper
    public JPanel panelLayout (JPanel containerPanel, Color c, String layoutLocation){
        //create a JPanel object
        JPanel panel = new JPanel();

        //panel background color
        panel.setBackground(c);

        containerPanel.add(panel,layoutLocation);

        return panel;
        }

    public JLabel createLabel(String text, JPanel container) {
        //Create label
        JLabel l = new JLabel(text);


        //add label to panels
        container.add(l);
        return l;
    }

 */

    //Title doesn't change
    public void openMenu() {
        createPanel.setVisible(true);
        Main.frame.setTitle(Frame.TITLE_PREFIX + TITLE);
        for (JButton button : buttonList) {
            button.setEnabled(true);
        }

        try {
            BufferedImage picture = ImageIO.read(new File("PlaceholderMaze.png"));
            JLabel imageLabel = new JLabel(new ImageIcon(picture));
            createPanel.add(imageLabel);
        } catch (IOException e) {
            //System.out.println("Invalid");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        createPanel.setVisible(false);

        // Switch statement to detect which button is pressed
        switch (e.getActionCommand()) {
            case "New":
                break;
            case "Load":
                break;
            case "Save":
                break;
            case "Back":
                Main.mainMenu.openMenu();
                break;
        }

    }

}