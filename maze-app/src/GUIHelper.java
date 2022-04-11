import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUIHelper {

    /***
     * Creates and returns a new JButton.
     * JButton will be centered on the X and Y value of the Position object
     * @param text String to be displayed on the GUI
     * @param size Dimension object containing the width and height of the JButton
     * @param location Position object containing the x and y position of the JButton
     * @return Returns a JButton object created with the specified arguments
     */
    public JButton newButton(String text, Dimension size, Position location) {
        JButton button = new JButton(text);
        button.setLocation((location.getX() - (size.width / 2)), (location.getY() - (size.height / 2)));
        System.out.println((location.getX() + (size.width / 2)) + ", " + (location.getY() + (size.height / 2)));
        button.setSize(size);
        return button;
    }
}
