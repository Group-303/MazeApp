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
    private ArrayList<JLabel> labelList = new ArrayList<>();
    public JPanel createPanel;
    public Color customGreen = Color.getHSBColor(0.35f,0.6f,0.5f);

    public CreateMenu() {
        //panel code
        createPanel = new JPanel();

        // Set boundary of panel
        createPanel.setBounds(0, 0, Frame.WIDTH, Frame.HEIGHT+Frame.H_MENU);

        //creating panels for layout inside the container panel
        // colours for panels 2 and 3 will be different
        JPanel headerPanel = GUIHelper.panelLayout(createPanel, customGreen, BorderLayout.NORTH); //Header
        JPanel sidePanel = GUIHelper.panelLayout(createPanel, Color.GRAY, BorderLayout.WEST); //sidebar
        JPanel contentPanel = GUIHelper.panelLayout(createPanel, Color.LIGHT_GRAY, BorderLayout.EAST); // where the maze goes
        JPanel footerPanel = GUIHelper.panelLayout(createPanel, customGreen, BorderLayout.SOUTH); //footer
        //JPanel panel5 = panelLayout (sidePanel, Color.WHITE, BorderLayout.CENTER);

        //the dimension of the panels
        int newHeight = (int) Math.round((Frame.HEIGHT) * 0.1);
        headerPanel.setPreferredSize(new Dimension(Frame.WIDTH, newHeight));
        sidePanel.setPreferredSize(new Dimension((int) Math.round((Frame.WIDTH)*0.30), (Frame.HEIGHT) - (newHeight * 2)));
        contentPanel.setPreferredSize(new Dimension((int) Math.round((Frame.WIDTH)*0.68), (Frame.HEIGHT) - (newHeight * 2)));
        footerPanel.setPreferredSize(new Dimension(Frame.WIDTH, newHeight));
        //panel5.setPreferredSize(new Dimension((int)Math.round((sidePanel.WIDTH) * 0.80), (int)Math.round((sidePanel.HEIGHT) * 0.80)));

        //Create labels
        JLabel labelTitle = GUIHelper.createLabel(TITLE,headerPanel);
        labelList.add( GUIHelper.createLabel("Title: ", sidePanel));
        labelList.add(GUIHelper.createLabel("Title: ", sidePanel));
        labelList.add(GUIHelper.createLabel("Author: ", sidePanel));
        labelList.add(GUIHelper.createLabel("Created: ", sidePanel));
        labelList.add(GUIHelper.createLabel("Last Edited: ", sidePanel));
        labelList.add(GUIHelper.createLabel("Width: ", sidePanel));
        labelList.add(GUIHelper.createLabel("Height: ", sidePanel));

        //Label formatting
        labelTitle.setFont(new Font("Century Gothic", Font.BOLD, 40));
        labelTitle.setForeground(Color.WHITE);

        //for loop to format the JLabels in the SideBar
        for (JLabel  text: labelList) {
            text.setFont(new Font("Century Gothic", Font.BOLD, 14));
        }

        // sidePanel horizontal and vertical centers
        int sidePanel_V_CENTER = (int) Math.round((sidePanel.getHeight()));
        int sidePanel_H_CENTER = (int) Math.round((sidePanel.getWidth()));

        // Create buttons and add buttons to button list using GUIHelper
        buttonList.add(GUIHelper.newButton("Upload maze", new Dimension(100, 500), new Point(sidePanel_H_CENTER, sidePanel_V_CENTER)));
        buttonList.add(GUIHelper.newButton("Generate Solution", new Dimension(100, 500), new Point(sidePanel_H_CENTER, sidePanel_V_CENTER)));
        buttonList.add(GUIHelper.newButton("Save", new Dimension(100, 500), new Point(sidePanel_H_CENTER, sidePanel_V_CENTER)));
        buttonList.add(GUIHelper.newButton("Back", new Dimension(100, 500), new Point(sidePanel_H_CENTER, sidePanel_V_CENTER)));



        JRadioButton confirmRButton = GUIHelper.newRButton("Confirm", new Dimension(100, 50), new Point(Frame.H_CENTER + 50, Frame.V_CENTER + 300));
        //buttonGroup.add(confirmRButton);
        createPanel.add(confirmRButton);


        //Adds panel to Main.frame
        Main.frame.add(createPanel);

        // Add JButtons in buttonList to sidePanel and add action listeners
        for (JButton button : buttonList) {
            sidePanel.add(button);
            button.addActionListener(this);
        }

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

    public void closeMenu() {
        createPanel.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        closeMenu();

        // Switch statement to detect which button is pressed
        switch (e.getActionCommand()) {
            case "Load":
                break;
            case "New":
                break;
            case "Save":
                break;
            case "Back":
                Main.mainMenu.openMenu();
                break;
        }

    }

}