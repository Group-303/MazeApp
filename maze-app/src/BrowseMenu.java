import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BrowseMenu implements IMenu, ActionListener {
    public final static String TITLE = "Maze Browser";
    public JPanel browsePanel;
    private List<JButton> buttonList = new ArrayList<>();

    public BrowseMenu() {
        browsePanel = new JPanel();
        browsePanel.setVisible(false);
        Main.frame.add(browsePanel);

        buttonList.add(GUIHelper.newButton("Generate Solution", new Dimension(100, 500), new Point(Frame.H_CENTER, Frame.V_CENTER)));

        for (JButton button : buttonList) {
            browsePanel.add(button);
            button.addActionListener(this);
        }

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
