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
        JPanel panel1 = panelLayout (createPanel, customGreen, BorderLayout.NORTH); //Header
        JPanel panel2 = panelLayout (createPanel, Color.GRAY, BorderLayout.WEST); //sidebar
        JPanel panel3 = panelLayout (createPanel, Color.LIGHT_GRAY, BorderLayout.EAST); // where the maze goes
        JPanel panel4 = panelLayout (createPanel, customGreen, BorderLayout.SOUTH); //footer
        //JPanel panel5 = panelLayout (panel2, Color.WHITE, BorderLayout.CENTER);

        //the dimension of the panels
        int newHeight = (int) Math.round((Frame.HEIGHT) * 0.1);
        panel1.setPreferredSize(new Dimension(Frame.WIDTH, newHeight));
        panel2.setPreferredSize(new Dimension((int) Math.round((Frame.WIDTH)*0.30), (Frame.HEIGHT) - (newHeight * 2)));
        panel3.setPreferredSize(new Dimension((int) Math.round((Frame.WIDTH)*0.68), (Frame.HEIGHT) - (newHeight * 2)));
        panel4.setPreferredSize(new Dimension(Frame.WIDTH, newHeight));
        //panel5.setPreferredSize(new Dimension((int)Math.round((panel2.WIDTH) * 0.80), (int)Math.round((panel2.HEIGHT) * 0.80)));

        //Create label
        JLabel l = new JLabel(TITLE);


        //add label to panels
        panel1.add(l);

        /* Just commented out button stuff to work out layout

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

        // Add buttons the panel4
        for (JButton button : buttonList) {
             panel4.add(button);
        }


        // Add action listeners
        for (JButton button : buttonList) {
             button.addActionListener(this);
        }

        //for (JRadioButton radioButton : radioButtonList) {
        //    radioButton.addActionListener(this);
        //}

*/
        //Adds panel to Main.frame
        Main.frame.add(createPanel);

        //this.panelsVisible(false);

    }

    public JPanel panelLayout (JPanel containerPanel, Color c, String layoutLocation){
        //create a JPanel object
        JPanel panel = new JPanel();

        //panel background color
        panel.setBackground(c);

        containerPanel.add(panel,layoutLocation);

        return panel;
        }

    //Title doesn't change
    public void openMenu() {
        Main.frame.setTitle(Frame.TITLE_PREFIX + TITLE);


    // Simple function to set the visibility of all panels
    /*public void panelsVisible(boolean bool) {
        for (int i = 0; i < PANELS; i++) {
            panels[i].setVisible(bool);
        }
    }*/

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
        switch (e.getSource().toString()) {
            //generateSolution, regenerate, save, back
            case "Generate Solution":
                generateSolution.setEnabled(false);
                break;
            case "Regenerate":
                regenerate.setEnabled(false);
                break;
            case "Save":
                save.setEnabled(false);
                break;
            case "Back":
                back.setEnabled(false);
                Main.createMenu.createPanel.setVisible(false);
                Main.mainMenu.mainPanel.setVisible(true);
                break;
        }

    }

}