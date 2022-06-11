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
        // button.setLocation((int) location.getX(), (int) location.getY());
        button.setSize(size);
        return button;
    }

    /***
     * Overload of newButton for use in a grid bag layout
     * @param text String to be displayed on button
     * @param container Container for button
     * @param gridX X Grid
     * @param gridY Y Grid
     * @param topInset
     * @param leftInset
     * @param bottomInset
     * @param rightInset
     * @return Returns a created JButton
     */
    public static JButton newButton(String text, JPanel container, int gridX, int gridY, int topInset, int leftInset, int bottomInset, int rightInset) {
        JButton button = new JButton(text);
        button.setLayout(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();
        // Set the label in a gridh
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = gridX;
        constraint.gridy = gridY;
        constraint.ipadx = 10;
        constraint.ipady = 10;
        constraint.insets.set(topInset,leftInset, bottomInset,rightInset);
        container.add(button, constraint);
        return button;
    }

    /***
     * Overload of newButton for use in a grid bag layout
     * @param container Container for button
     * @param colour Colour of button
     * @param gridX X Grid
     * @param gridY Y Grid
     * @return Returns a created JButton
     */
    public static JButton newButton(JPanel container, Color colour, int gridX, int gridY) {
        String text = Integer.toString(gridX) + "," + Integer.toString(gridY);
        JButton button = new JButton(text);
        //set button text size to 1
        button.setFont(new Font("Arial", Font.PLAIN, 1));
        button.setForeground(colour);
        button.setLayout(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();
        // Set the label in a grid
        //constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.fill = GridBagConstraints.VERTICAL;
        constraint.weighty = 0.5;
        constraint.gridx = gridX;
        constraint.gridy = gridY;
        constraint.ipadx = 0;
        constraint.ipady = 0;
        button.setBackground(colour);
        container.add(button, constraint);
        return button;
    }

    /***
     * Overload of newButton for use in a grid bag layout
     * @param text String to be displayed on button
     * @param container Container for button
     * @param gridX X Grid
     * @param gridY Y Grid
     * @return Returns a created JButton
     */
    public static JButton newButton(String text, JPanel container, int gridX, int gridY) {
        JButton button = new JButton(text);
        button.setLayout(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();
        // Set the label in a grid
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = gridX;
        constraint.gridy = gridY;
        constraint.ipadx = 0;
        constraint.ipady = 10;
        container.add(button, constraint);
        return button;
    }

    /***
     * Overload of newTextField for use in a grid bag layout
     * @param size Dimension object containing the width and height of the JTextField
     * @param container Container for JTextField
     * @param gridX X Grid
     * @param gridY Y Grid
     * @return Returns a created JTextField
     */
    public static JTextField newTextField(Dimension size, JPanel container, int gridX, int gridY) {
        JTextField textField = new JTextField();
        textField.setEnabled(true);
        textField.setLayout(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();

        // set the label in a grid
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        //constraint.weighty = 1;
        constraint.gridx = gridX;
        constraint.gridy = gridY;
        constraint.ipady = 10;
        constraint.insets.set(0,0,10,20);
        textField.setSize(size);
        container.add(textField, constraint);
        return textField;
    }

    /***
     * Overload of newTextField for use in a grid bag layout
     * @param size Dimension object containing the width and height of the JTextField
     * @param container Container for JTextField
     * @param gridX X Grid
     * @param gridY Y Grid
     * @param enabled Boolean value to enable or disable the JTextField
     * @return Returns a created JTextField
     */
    public static JTextField newTextField(Dimension size, JPanel container, int gridX, int gridY, boolean enabled) {
        JTextField textField = new JTextField();
        textField.setEnabled(enabled);
        textField.setLayout(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();

        // set the label in a grid
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = gridX;
        constraint.gridy = gridY;
        constraint.ipady = 10;
        constraint.insets.set(0,0,10,20);
        textField.setSize(size);
        container.add(textField, constraint);
        return textField;
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
     * @return Returns a JMenuItem object with the provided fields
     */
    public JMenuItem newMenuItem(String text) {
        return new JMenuItem(text);
    }

    /***
     * Creates and returns a new JMenuItem object
     * @param text String to be displayed as the menu item's label
     * @param icon Icon to be displayed next to the text
     * @return Returns a JMenuItem object with the provided fields
     */
    public JMenuItem newMenuItem(String text, ImageIcon icon) {
        return new JMenuItem(text, icon);
    }

    /***
     * Creates and retruns a new ButtonGroup object
     * @param buttons Array of JRadioButton objects to be added to the ButtonGroup
     * @return Returns a ButtonGroup object
     */
    public ButtonGroup formButtonGroup(ArrayList<JRadioButton> radioButtons) {
        ButtonGroup group = new ButtonGroup();
        for (JRadioButton component : radioButtons) {
            group.add(component);
        }
        return group;
    }

    /***
     * Creates and returns a new JPanel object
     * @param containerPanel container panel for the new JPanel
     * @param c Colour of the new JPanel
     * @param layoutLocation Location of the new JPanel in the container panel
     * @return
     */
    public static JPanel panelLayout(JPanel containerPanel, Color c, String layoutLocation){
        //create a JPanel object
        JPanel panel = new JPanel(new GridBagLayout());

        //panel background color
        panel.setBackground(c);

        containerPanel.add(panel,layoutLocation);

        return panel;
    }

    /***
     * Creates and returns a new JPanel object with a GridBagLayout
     * @param containerPanel container panel for the new JPanel
     * @param c Colour of the new JPanel
     * @param gridX X Grid
     * @param gridY Y Grid
     * @param padX X padding
     * @param padY Y padding
     * @param span span of the new JPanel
     * @return Returns a JPanel object with the provided fields
     */
    public static JPanel panelLayout(JPanel containerPanel, Color c, int gridX, int gridY, int padY, int span){
        //create a JPanel object
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();

        // set the label in a grid
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = gridX;
        constraint.gridy = gridY;
        constraint.ipadx = 10;
        constraint.ipady = padY;
        constraint.gridwidth = span;

        //panel background color
        panel.setBackground(c);

        containerPanel.add(panel,constraint);

        return panel;
    }

    /***
     * Creates and returns a new JLabel object with a GridBagLayout
     * @param text String to be displayed as the label's text
     * @param container Panel for the new JLabel
     * @param gridX X Grid
     * @param gridY Y Grid
     * @return Returns a JLabel object with the provided fields
     */
    public static JLabel createLabel(String text, JPanel container, int gridX, int gridY) {
        //Create label
        JLabel l = new JLabel(text);
        l.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        l.setHorizontalAlignment((int) Math.round(container.getHeight() * 0.5));

        // set the label in a grid
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = gridX;
        c.gridy = gridY;
        c.ipadx = 10;
        c.ipady = 10;
        c.insets.set(0,0,10,0);

        //add label to panels
        container.add(l, c);
        return l;
    }
}
