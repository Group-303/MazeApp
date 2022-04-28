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
        back.addActionListener(this);
    }

    public void openMenu() {
        Main.frame.setTitle(Frame.TITLE_PREFIX + TITLE);
        generatePanel.setVisible(true);

        // Code that should be executed whenever a menu is opened
    }

    public void closeMenu() {
        generatePanel.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Back":
                closeMenu();
                Main.mainMenu.openMenu();
                break;
        }
    }
}
