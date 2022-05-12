import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrowseMenu implements IMenu, ActionListener {
    public final static String TITLE = "Maze Browser";
    public JPanel browsePanel;
    public boolean createReturn;

    public BrowseMenu() {
        browsePanel = new JPanel();
        browsePanel.setVisible(false);
        Main.frame.add(browsePanel);

        //creating panels for layout inside the container panel
        JPanel headerPanel = GUIHelper.panelLayout(browsePanel, Main.createMenu.headerGreen, BorderLayout.NORTH); //Header
        JPanel sidePanel = GUIHelper.panelLayout(browsePanel, Color.GRAY, null); //sidebar
        JPanel contentPanel = GUIHelper.panelLayout(browsePanel, Color.LIGHT_GRAY, BorderLayout.EAST); // where the maze goes
        JPanel footerPanel = GUIHelper.panelLayout(browsePanel, Main.createMenu.headerGreen, BorderLayout.SOUTH); //footer

        //the dimension of the panels
        int newHeight = (int) Math.round((Frame.HEIGHT) * 0.1);
        headerPanel.setPreferredSize(new Dimension(Frame.WIDTH, newHeight));
        sidePanel.setPreferredSize(new Dimension((int) Math.round((Frame.WIDTH)*0.30), (Frame.HEIGHT) - (newHeight * 2)));
        contentPanel.setPreferredSize(new Dimension((int) Math.round((Frame.WIDTH)*0.68), (Frame.HEIGHT) - (newHeight * 2)));
        footerPanel.setPreferredSize(new Dimension(Frame.WIDTH, newHeight));

        //Create labels
        JLabel labelTitle = GUIHelper.createLabel(TITLE,headerPanel);

        //Label formatting for the Header
        labelTitle.setFont(new Font("Century Gothic", Font.BOLD, 40));
        labelTitle.setForeground(Color.WHITE);

        JButton back = new JButton("Back");
        sidePanel.add(back);
        JButton foo = new JButton("Back");
        sidePanel.add(foo);
        back.addActionListener(this);
    }

    public void openMenu() {
        createReturn = false;
        Main.frame.setTitle(Frame.TITLE_PREFIX + TITLE);
        browsePanel.setVisible(true);
    }

    public void openMenu(boolean fromCreate) {
        createReturn = fromCreate;
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
                if (createReturn) {
                    Main.createMenu.openMenu();
                }
                else {
                    Main.mainMenu.openMenu();
                }

                break;
        }
        /*if (e.getSource() == back) {

        }*/
    }
}
