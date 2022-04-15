import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Frame extends JFrame {
    public final static int WIDTH = 1200;
    public final static int HEIGHT = 800;
    public final static int H_CENTER = WIDTH / 2;
    public final static int V_CENTER = HEIGHT / 2;
    public final static int H_MENU = 46; // Height of the menu-bar
    public final static String TITLE_PREFIX = "MazeCo Cartographer - ";

    public Frame() {
        setTitle("MazeCo Cartographer");
        setJMenuBar(createMenus());
        setSize(WIDTH, HEIGHT + H_MENU);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    /***
     * Creates the menubar and tabs on top of the GUI
     * @return Returns a JMenuBar object containing all the menu interactions
     */
    public JMenuBar createMenus() {
        JMenuBar menuBar = new JMenuBar();
        GUIHelper guiHelper = new GUIHelper();
        List<JMenu> menuList = new ArrayList<>();
        JMenu menu, submenu;
        JMenuItem item;

        // File Section
        menu = guiHelper.newMenuTab("File", KeyEvent.VK_F);

        // Create new mazes
        submenu = guiHelper.newMenuTab("New Maze", KeyEvent.VK_N);
        item = guiHelper.newMenuItem("Empty Maze", new ImageIcon("images/middle.gif"));
        submenu.add(item);
        item = guiHelper.newMenuItem("Generate New", new ImageIcon("images/middle.gif"));
        submenu.add(item);
        menu.add(submenu);

        menu.addSeparator();

        // Browse all and recent mazes
        item = guiHelper.newMenuItem("Browse Mazes", new ImageIcon("images/middle.gif"));
        menu.add(item);

        submenu = guiHelper.newMenuTab("Recent Mazes", KeyEvent.VK_R);
        item = guiHelper.newMenuItem("DummyData001", new ImageIcon("images/middle.gif"));
        submenu.add(item);
        item = guiHelper.newMenuItem("DummyData002", new ImageIcon("images/middle.gif"));
        submenu.add(item);
        menu.add(submenu);

        menuList.add(menu);

        // Edit Section
        menuList.add(guiHelper.newMenuTab("Edit", KeyEvent.VK_E));

        for (JMenu menuTab : menuList) {
            menuBar.add(menuTab);
        }

        return menuBar;
    }
}