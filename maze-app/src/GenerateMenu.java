import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateMenu implements IMenu, ActionListener {
    public final static String TITLE = "Maze Generator";
    public JPanel generatePanel;

    public GenerateMenu() {
        generatePanel = new JPanel();
        generatePanel.setVisible(false);
        Main.frame.add(generatePanel);
        JButton back = new JButton("Back");
        generatePanel.add(back);
        //back.addActionListener(this);

        openMenu();
    }

    public void openMenu() {
        Main.frame.setTitle(Frame.TITLE_PREFIX + TITLE);

        // Code that should be executed whenever a menu is opened
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //generatePanel.setVisible(false);
        // Insert action events here
        Main.mainMenu.openMenu();
    }
}
