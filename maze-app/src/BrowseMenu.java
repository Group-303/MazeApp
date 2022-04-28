import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrowseMenu implements IMenu, ActionListener {
    public final static String TITLE = "Maze Browser";
    public JPanel browsePanel;

    public BrowseMenu() {
        browsePanel = new JPanel();
        browsePanel.setVisible(false);
        Main.frame.add(browsePanel);
        JButton back = new JButton("Back");
        browsePanel.add(back);
        back.addActionListener(this);
    }

    public void openMenu() {
        Main.frame.setTitle(Frame.TITLE_PREFIX + TITLE);
        browsePanel.setVisible(true);
    }

    public void closeMenu() {
        browsePanel.setVisible(false);
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
