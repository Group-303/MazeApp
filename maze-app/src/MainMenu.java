import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/***
 * Class for managing swing MainMenu
 */

public class MainMenu implements IMenu, ActionListener {
    public final static String TITLE = "Main Menu";

    public JPanel mainPanel;
    private List<JButton> buttonList = new ArrayList<>();

    public MainMenu() {
        // Create JPanel for MainGUIFrame
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.getHSBColor(0.35f, 0.7f, 0.6f));

        // Set mainGUIPanel to be in the center of the frame
        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 0, Frame.WIDTH, Frame.HEIGHT);

        // Add buttons to components list
        buttonList.add(GUIHelper.newButton("Create New", new Dimension(700, 300), new Point(Frame.H_CENTER, Frame.V_CENTER - 200)));
        buttonList.add(GUIHelper.newButton("Browse Mazes", new Dimension(700, 300), new Point(Frame.H_CENTER, Frame.V_CENTER + 200)));

        // Add all components into the main panel and add action listeners to each JButton component
        for (JButton button : buttonList) {
            mainPanel.add(button);
            button.addActionListener(this);
        }

        // Add mainGUIPanel to mainGUIFrame
        Main.frame.add(mainPanel);

        openMenu();
    }

    // Standard openMenu method
    public void openMenu() {
        mainPanel.setVisible(true);
        Main.frame.setTitle(Frame.TITLE_PREFIX + TITLE);
    }

    // Standard closeMenu method
    public void closeMenu() {
        mainPanel.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        closeMenu();
        if (e.getSource() == buttonList.get(0)) {
            Main.createMenu.openMenu();
        }
        else if (e.getSource() == buttonList.get(1)) {
            Main.browseMenu.openMenu();
        }
    }
}
