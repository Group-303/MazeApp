import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class CreateMenu implements IMenu, ActionListener {
    public final static String TITLE = "Maze Creator";
    private JButton generateSolution, regenerate, save;
    //private ButtonGroup buttonGroup = new ButtonGroup();
    private ArrayList<JButton> buttonList = new ArrayList<>();
    //private ArrayList<ButtonGroup> bgroupList = new ArrayList<>();
    //private ArrayList<JRadioButton> radioButtonList = new ArrayList<>();
    private List<Component> componentList = new ArrayList<>();
    private GUIHelper guiHelper = new GUIHelper();
    public JPanel createPanel;

    // Does this need to be public?
    public final static int PANELS = 5;

    public CreateMenu() {
        //panel code
        createPanel = new JPanel();
        //Color[] colours = {Color.GREEN, Color.gray, Color.GREEN, Color.WHITE, Color.WHITE};
        //String[] layoutLocation = {BorderLayout.NORTH,
        //        BorderLayout.EAST,
        //        BorderLayout.SOUTH,
        //        BorderLayout.WEST,
        //        BorderLayout.CENTER
        //};

        //create  panels 0 to 3
        //for (int i = 0; i < PANELS - 1; i++){
        //    panels[i] = createPanel(colours[i]);
        //    Main.frame.getContentPane().add(panels[i],layoutLocation[i]);
        //}

        //create panel 4 (will be placed inside panel 1)
        //panels[4] = createPanel(colours[4]);
        //panels[1].add(panels[4],layoutLocation[4]);

        // Set boundary of panel
        createPanel.setBounds(0, 0, Frame.WIDTH, Frame.HEIGHT);

        // WIDTH is 1200 and HEIGHT is 800
        //panels[1].setPreferredSize(new Dimension(300, Frame.HEIGHT));
        //panels[3].setPreferredSize(new Dimension(900, Frame.HEIGHT));
        //panels[4].setPreferredSize(new Dimension(800, (Frame.HEIGHT) - 100));

        // Create buttons and add buttons to button list using GUIHelper
        buttonList.add(guiHelper.newButton("Generate Solution", new Dimension(100, 50), new Point(Frame.H_CENTER - 200, Frame.V_CENTER - 200)));
        buttonList.add(guiHelper.newButton("Regenerate", new Dimension(100, 50), new Point(Frame.H_CENTER + 200, Frame.V_CENTER - 200)));
        buttonList.add(guiHelper.newButton("Save", new Dimension(100, 50), new Point(Frame.H_CENTER, Frame.V_CENTER + 200)));

        JRadioButton confirmRButton = guiHelper.newRButton("Confirm", new Dimension(100, 50), new Point(Frame.H_CENTER + 50, Frame.V_CENTER + 300));
        //buttonGroup.add(confirmRButton);
        createPanel.add(confirmRButton);
        //bgroupList.add(guiHelper.newButtonGroup());
        //radioButtonList.add(guiHelper.newRButton("Test", KeyEvent.VK_A, new Dimension(100, 100), new Point(Frame.H_CENTER + 200, Frame.V_CENTER - 200), true));

        //for (JRadioButton radioButton : radioButtonList) {
        //    bgroupList.get(0).add(radioButton);
        //}

        //createPanel.add(guiHelper.formButtonGroup(radioButtonList));

        // Add buttons the createPanel
        for (JButton button : buttonList) {
             createPanel.add(button);
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

    }

    public void openMenu() {

    }

    // Simple function to set the visibility of all panels
    /*public void panelsVisible(boolean bool) {
        for (int i = 0; i < PANELS; i++) {
            panels[i].setVisible(bool);
        }
    }*/

    /*private JPanel createPanel(Color c){
        //create a JPanel object
        JPanel panel = new JPanel();

        //panel background color
        panel.setBackground(c);

        //return the panel
        return panel;
    }*/


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getSource().toString()) {
            //generateSolution, regenerate, save
            case "Generate Solution":
                generateSolution.setEnabled(false);
                break;
            case "Regenerate":
                regenerate.setEnabled(false);
                break;
            case "Save":
                save.setEnabled(false);
                break;
        }

    }

}