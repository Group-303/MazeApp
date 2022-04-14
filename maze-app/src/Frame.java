import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Frame extends JFrame {
    public final static int WIDTH = 1200;
    public final static int HEIGHT = 800;
    public final static int H_CENTER = WIDTH / 2;
    public final static int V_CENTER = HEIGHT / 2;
    public final static int H_MENU = 46; // Height of the menu-bar

    public Frame() {
        setTitle("MazeCo");
        setJMenuBar(createMenus());
        setSize(WIDTH, HEIGHT + H_MENU);
        //setLocation(H_CENTER, V_CENTER);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public JMenuBar createMenus() {
        //Where the GUI is created:
        JMenuBar menuBar;
        JMenu fileMenu, submenu;
        JMenuItem menuItem;
        JRadioButtonMenuItem rbMenuItem;
        JCheckBoxMenuItem cbMenuItem;

        //Create the menu bar.
        menuBar = new JMenuBar();

        //Build the first menu.
        fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        menuBar.add(fileMenu);

        //a group of JMenuItems
        menuItem = new JMenuItem("A text-only menu item",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, KeyEvent.SHIFT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "This doesn't really do anything");
        fileMenu.add(menuItem);

        menuItem = new JMenuItem("Both text and icon",
                new ImageIcon("images/middle.gif"));
        menuItem.setMnemonic(KeyEvent.VK_B);
        fileMenu.add(menuItem);

        menuItem = new JMenuItem(new ImageIcon("images/middle.gif"));
        menuItem.setMnemonic(KeyEvent.VK_D);
        fileMenu.add(menuItem);

        //a group of radio button menu items
        fileMenu.addSeparator();
        ButtonGroup group = new ButtonGroup();
        rbMenuItem = new JRadioButtonMenuItem("A radio button menu item");
        rbMenuItem.setSelected(true);
        rbMenuItem.setMnemonic(KeyEvent.VK_R);
        group.add(rbMenuItem);
        fileMenu.add(rbMenuItem);

        rbMenuItem = new JRadioButtonMenuItem("Another one");
        rbMenuItem.setMnemonic(KeyEvent.VK_O);
        group.add(rbMenuItem);
        fileMenu.add(rbMenuItem);

        //a group of check box menu items
        fileMenu.addSeparator();
        cbMenuItem = new JCheckBoxMenuItem("A check box menu item");
        cbMenuItem.setMnemonic(KeyEvent.VK_C);
        fileMenu.add(cbMenuItem);

        cbMenuItem = new JCheckBoxMenuItem("Another one");
        cbMenuItem.setMnemonic(KeyEvent.VK_H);
        fileMenu.add(cbMenuItem);

        //a submenu
        fileMenu.addSeparator();
        submenu = new JMenu("A submenu");
        submenu.setMnemonic(KeyEvent.VK_S);

        menuItem = new JMenuItem("An item in the submenu");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.ALT_MASK));
        submenu.add(menuItem);

        menuItem = new JMenuItem("Another item");
        submenu.add(menuItem);
        fileMenu.add(submenu);

        //Build second menu in the menu bar.
        fileMenu = new JMenu("Another Menu");
        fileMenu.setMnemonic(KeyEvent.VK_N);
        fileMenu.getAccessibleContext().setAccessibleDescription(
                "This menu does nothing");
        menuBar.add(fileMenu);
        return menuBar;
    }
}
