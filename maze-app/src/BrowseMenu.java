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
    public boolean createReturn;
    private List<JButton> buttonList = new ArrayList<>();
    private List<Maze> mazeList = new ArrayList<>();
    private Maze selectedMaze;
    private JPanel headerPanel;
    private JPanel searchPanel;
    private JPanel contentPanel;
    private JPanel footerPanel;

    public BrowseMenu() {
        browsePanel = new JPanel(new GridBagLayout());
        browsePanel.setVisible(false);
        Main.frame.add(browsePanel);
        int newHeight = (int) Math.round((Frame.HEIGHT) * 0.01);
        
        //creating panels for layout inside the container panel
        headerPanel = GUIHelper.panelLayout(browsePanel, Main.createMenu.headerGreen, 0,0, newHeight, 1); //Header
        searchPanel = GUIHelper.panelLayout(browsePanel, Main.createMenu.subheader, 0,1, (int) Math.round(Frame.HEIGHT*0.16), 1); //sidebar
        contentPanel = GUIHelper.panelLayout(browsePanel, Color.WHITE, 0,2,(int) Math.round(Frame.HEIGHT*0.67), 1); // where the maze goes
        footerPanel = GUIHelper.panelLayout(browsePanel, Main.createMenu.headerGreen, 0,3,newHeight, 1); //footer

        //Create labels
        JLabel labelTitle = GUIHelper.createLabel(TITLE, headerPanel, 0,0);

        //Label formatting for the Header
        labelTitle.setFont(new Font("Century Gothic", Font.BOLD, 40));
        labelTitle.setForeground(Color.WHITE);

        // Create back button
        JButton back = GUIHelper.newButton("Back", footerPanel, 0, 0, 5, 10, 5, (int) Math.round(Frame.WIDTH*0.9));

        // Add back button action listener
        back.addActionListener(this);
    }

    // Standard openMenu method
    public void openMenu() {
        createReturn = false;
        Main.frame.setTitle(Frame.TITLE_PREFIX + TITLE);
        loadMazes();
        browsePanel.setVisible(true);
    }

    // openMenu method for when the user opens from the create menu
    public void openMenu(boolean fromCreate) {
        createReturn = fromCreate;
        Main.frame.setTitle(Frame.TITLE_PREFIX + TITLE);
        loadMazes();
        browsePanel.setVisible(true);
    }

    // closeMenu method
    public void closeMenu() {
        browsePanel.setVisible(false);
        mazeList.clear();
        buttonList.clear();
        contentPanel.removeAll();
    }

    // Method to load all mazes from the database
    private void loadMazes() {
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
            default:
                for (Maze maze : mazeList) {
                    if (e.getActionCommand() == maze.getTitle()) {
                        
                        selectedMaze = maze;
                        break;
                    }
                }
                if (selectedMaze != null) {
                    closeMenu();
                    Main.createMenu.openMenu(selectedMaze);
                }
                else {
                    System.out.println("No maze selected");
                }
        }
    }
}
