import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUIHelper {
    public JButton newButton(String text, Dimension size, Position location) {
        JButton button = new JButton(text);
        button.setLocation(location.getX(), location.getY());
        button.setSize(size);
        return button;
    }
}
