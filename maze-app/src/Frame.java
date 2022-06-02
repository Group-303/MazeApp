import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Frame extends JFrame implements ActionListener, ItemListener {
    public final static int WIDTH = (int) Math.round(0.85 * GUIHelper.SCREEN_SIZE.getWidth());
    public final static int HEIGHT = (int) Math.round(0.85 * GUIHelper.SCREEN_SIZE.getHeight());
    public final static int H_CENTER = WIDTH / 2;
    public final static int V_CENTER = HEIGHT / 2;
    public final static int H_MENU = 46; // Height of the menu-bar
    public final static String TITLE_PREFIX = "MazeCo Cartographer - ";

    public Frame() {
        setTitle(TITLE_PREFIX + "Loading...");
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

        menu.addSeparator();

        item = guiHelper.newMenuItem("Home");
        menu.add(item);

        menuList.add(menu);

        // Edit Section
        menu = guiHelper.newMenuTab("Edit", KeyEvent.VK_F);

        submenu = guiHelper.newMenuTab("Recent Maze", KeyEvent.VK_N);
        item = guiHelper.newMenuItem("Maze001");
        submenu.add(item);
        item = guiHelper.newMenuItem("Maze002");
        item.addActionListener(this);
        submenu.add(item);
        menu.add(submenu);

        menu.addSeparator();

        item = guiHelper.newMenuItem("Browse Mazes");
        item.addActionListener(this);
        menu.add(item);

        menuList.add(menu);

        for (JMenu menuTab : menuList) {
            menuBar.add(menuTab);
        }

        return menuBar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Empty Maze":
                Main.createMenu.openMenu();
                Main.browseMenu.closeMenu();
                Main.mainMenu.closeMenu();
                System.out.println("Working!");
                break;
            case "Home":
                Main.createMenu.closeMenu();
                Main.browseMenu.closeMenu();
                Main.mainMenu.openMenu();
                break;
            case "Save":
                break;

        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}