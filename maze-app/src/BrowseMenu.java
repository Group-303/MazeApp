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
        JPanel bodyPanel = GUIHelper.panelLayout(browsePanel, Color.WHITE, BorderLayout.CENTER);
        JPanel footerPanel = GUIHelper.panelLayout(browsePanel, Main.createMenu.headerGreen, BorderLayout.SOUTH); //footer

        //the dimension of the panels
        int newHeight = (int) Math.round((Frame.HEIGHT) * 0.1);
        headerPanel.setPreferredSize(new Dimension(Frame.WIDTH, newHeight));
        bodyPanel.setPreferredSize(new Dimension(Frame.WIDTH, Frame.HEIGHT - (newHeight*2)));
        footerPanel.setPreferredSize(new Dimension(Frame.WIDTH, newHeight));

        //Create labels
        JLabel labelTitle = GUIHelper.createLabel(TITLE, headerPanel,  0.5);

        //Label formatting for the Header
        labelTitle.setFont(new Font("Century Gothic", Font.BOLD, 40));
        labelTitle.setForeground(Color.WHITE);

        JButton back = new JButton("Back");
        bodyPanel.add(back);
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
