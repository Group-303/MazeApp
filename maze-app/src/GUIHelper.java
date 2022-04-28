import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GUIHelper {
    public final static Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();

    /***
     * Creates and returns a new JButton.
     * JButton will be centered on the X and Y value of the Position object
     * @param text String to be displayed on the GUIHelper
     * @param size Dimension object containing the width and height of the JButton
     * @param location Position object containing the x and y position of the JButton
     * @return Returns a JButton object created with the specified arguments
     */
    public static JButton newButton(String text, Dimension size, Point location) {
        JButton button = new JButton(text);
        button.setLocation(((int) location.getX() - (size.width / 2)), ((int) location.getY() - (size.height / 2)));
        button.setSize(size);
        return button;
    }

    /***
     * Creates and returns a new JMenu object
     * @param text String to be displayed as the menu button's label
     * @param key Key pressed to open the menu
     * @return Returns a JMenu object with the provided fields
     */
    public JMenu newMenuTab(String text, int key) {
        JMenu newMenu = new JMenu(text);
        newMenu.setMnemonic(key);
        return newMenu;
    }

    /***
     * Creates and returns a new JMenuItem object
     * @param text String to be displayed as the menu item's label
     * @param icon Icon to be displayed next to the text
     * @return Returns a JMenuItem object with the provided fields
     */
    public JMenuItem newMenuItem(String text, ImageIcon icon) {
        //menuItem.setAccelerator(key); Functionality for key press shortcuts if possible
        return new JMenuItem(text, icon);
    }

    public ButtonGroup newButtonGroup() {
        return new ButtonGroup();
    }

    public JRadioButton newRButton(String text, Dimension size, Point location) {
        JRadioButton radioButton = new JRadioButton(text);
        radioButton.setLocation(((int) location.getX() - (size.width / 2)), ((int) location.getY() - (size.height / 2)));
        return radioButton;
    }

    public ButtonGroup formButtonGroup(ArrayList<JRadioButton> radioButtons) {
        ButtonGroup group = new ButtonGroup();
        for (JRadioButton component : radioButtons) {
            group.add(component);
        }
        return group;
    }

    // This is all spaghetti code from Oracle documentation
    // Will be removed when I have fully implemented the
    // dynamic menu instantiation above -CT
    public JMenuBar oracleMenuTest() {
        //Where the GUI is created:
        JMenuBar menuBar;
        JMenu fileMenu, submenu;
        JMenuItem menuItem;
        JRadioButtonMenuItem rbMenuItem;
        JCheckBoxMenuItem cbMenuItem;

        //Create the menu bar.
        menuBar = new JMenuBar();

        //Build the first menu.
        //fileMenu = new JMenu("File");
        //fileMenu.setMnemonic(KeyEvent.VK_F);
        //fileMenu.getAccessibleContext().setAccessibleDescription(
        //        "The only menu in this program that has menu items");
        //menuBar.add(fileMenu);

        //a group of JMenuItems
        menuItem = new JMenuItem("A text-only menu item",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, KeyEvent.SHIFT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "This doesn't really do anything");
        //fileMenu.add(menuItem);

        menuItem = new JMenuItem("Both text and icon",
                new ImageIcon("images/middle.gif"));
        menuItem.setMnemonic(KeyEvent.VK_B);
        //fileMenu.add(menuItem);

        menuItem = new JMenuItem(new ImageIcon("images/middle.gif"));
        menuItem.setMnemonic(KeyEvent.VK_D);
        //fileMenu.add(menuItem);

        //a group of radio button menu items
        //fileMenu.addSeparator();
        ButtonGroup group = new ButtonGroup();
        rbMenuItem = new JRadioButtonMenuItem("A radio button menu item");
        rbMenuItem.setSelected(true);
        rbMenuItem.setMnemonic(KeyEvent.VK_R);
        group.add(rbMenuItem);
        //fileMenu.add(rbMenuItem);

        rbMenuItem = new JRadioButtonMenuItem("Another one");
        rbMenuItem.setMnemonic(KeyEvent.VK_O);
        group.add(rbMenuItem);
        //fileMenu.add(rbMenuItem);

        //a group of check box menu items
        //fileMenu.addSeparator();
        cbMenuItem = new JCheckBoxMenuItem("A check box menu item");
        cbMenuItem.setMnemonic(KeyEvent.VK_C);
        //fileMenu.add(cbMenuItem);

        cbMenuItem = new JCheckBoxMenuItem("Another one");
        cbMenuItem.setMnemonic(KeyEvent.VK_H);
        //fileMenu.add(cbMenuItem);

        //a submenu
        //fileMenu.addSeparator();
        submenu = new JMenu("A submenu");
        submenu.setMnemonic(KeyEvent.VK_S);

        menuItem = new JMenuItem("An item in the submenu");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.ALT_MASK));
        submenu.add(menuItem);

        menuItem = new JMenuItem("Another item");
        submenu.add(menuItem);
        //fileMenu.add(submenu);

        //Build second menu in the menu bar.
        fileMenu = new JMenu("Another Menu");
        fileMenu.setMnemonic(KeyEvent.VK_N);
        fileMenu.getAccessibleContext().setAccessibleDescription(
                "This menu does nothing");
        menuBar.add(fileMenu);
        return menuBar;
    }
    // Remove above when dynamic menu has been implemented
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
}
