import javax.swing.*;

public class BrowseMenu implements IMenu {
    public final static String TITLE = "Maze Browser";
    public JPanel browsePanel;

    public BrowseMenu() {
        browsePanel = new JPanel();
        browsePanel.setVisible(false);
        Main.frame.add(browsePanel);

        openMenu();
    }

    public void openMenu() {
        Main.frame.setTitle(Frame.TITLE_PREFIX + TITLE);

        // Code that should be executed whenever a menu is opened
    }
}
