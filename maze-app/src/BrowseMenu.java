import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrowseMenu implements IMenu, ActionListener {
    public final static String TITLE = "Maze Browser";
    public JPanel browsePanel;
    private JPanel contentPanel;
    public boolean createReturn;
    private List<JButton> buttonList = new ArrayList<>();

    public BrowseMenu() {
        browsePanel = new JPanel(new GridBagLayout());
        browsePanel.setVisible(false);
        Main.frame.add(browsePanel);
        //panelLayout(JPanel containerPanel, Color c, int gridX, int gridY)
        int newHeight = (int) Math.round((Frame.HEIGHT) * 0.05);
        //creating panels for layout inside the container panel
        JPanel headerPanel = GUIHelper.panelLayout(browsePanel, Main.createMenu.headerGreen, 0,0, newHeight); //Header
        JPanel searchPanel = GUIHelper.panelLayout(browsePanel, Main.createMenu.subheader, 0,1, (int) Math.round(Frame.HEIGHT*0.15)); //sidebar
        contentPanel = GUIHelper.panelLayout(browsePanel, Color.WHITE, 0,2,(int) Math.round(Frame.HEIGHT*0.63)); // where the maze goes
        JPanel footerPanel = GUIHelper.panelLayout(browsePanel, Main.createMenu.headerGreen, 0,3,newHeight); //footer

        //Create labels
        JLabel labelTitle = GUIHelper.createLabel(TITLE, headerPanel, 0,0);

        //Label formatting for the Header
        //Label formatting for the Header
        labelTitle.setFont(new Font("Century Gothic", Font.BOLD, 40));
        labelTitle.setForeground(Color.WHITE);

        //for loop to format the JLabels in the SideBar
       // for (JLabel  text: labelList) {
       //     text.setFont(new Font("Century Gothic", Font.BOLD, 14));
        // }

       // JButton back = new JButton("Back");
        //footerPanel.add(back);
        JButton back = GUIHelper.newButton("Back", footerPanel, 0, 0, 5, 10, 5, (int) Math.round(Frame.WIDTH*0.9));

        back.addActionListener(this);
    }

    public void openMenu() {
        createReturn = false;
        Main.frame.setTitle(Frame.TITLE_PREFIX + TITLE);
        loadMazes();
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

    private void loadMazes() {
        List<Maze> mazeList;
        mazeList = Main.database.getAllMazes();
        if (mazeList != null) {
            for (Maze maze : mazeList) {
                buttonList.add(GUIHelper.newButton(maze.getTitle(), contentPanel, 0, 0, 5, 10, 5, (int) Math.round(Frame.WIDTH*0.9)));
            }
            for (JButton button : buttonList) {
                button.addActionListener(this);
                contentPanel.add(button);
            }
        }
        else {
            //print message that there are no mazes to the console
            System.out.println("No mazes to display");
        }

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
