import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class PlayMenu implements IMenu, ActionListener {
    public final static String TITLE = "Maze Creator";
    private Maze currentMaze;
    private JButton backButton;
    private JPanel contentPanel;
    public JPanel playPanel;
    public Color headerGreen = Color.getHSBColor(0.35f, 0.7f, 0.6f);
    public Color subheader = Color.getHSBColor(0.35f, 0.1f, 0.8f);

    public PlayMenu(Maze maze) {
        this.currentMaze = maze;
        
        //panel code
        playPanel = new JPanel(new BorderLayout());

        // Set boundary of panel
        playPanel.setBounds(0, 0, Frame.WIDTH, Frame.HEIGHT+10);

        //creating panels for layout inside the container panel
        JPanel headerPanel = GUIHelper.panelLayout(playPanel,headerGreen , BorderLayout.NORTH); //Header
        contentPanel = GUIHelper.panelLayout(playPanel, Color.GRAY, BorderLayout.CENTER); // where the maze goes
        JPanel footerPanel = GUIHelper.panelLayout(playPanel, headerGreen, BorderLayout.SOUTH); //footer

        //the dimension of the panels
        int newHeight = (int) Math.round((Frame.HEIGHT) * 0.08);
        headerPanel.setPreferredSize(new Dimension(Frame.WIDTH, newHeight));
        contentPanel.setPreferredSize(new Dimension((int) Math.round((Frame.WIDTH)*0.7), (int) Math.round(Frame.HEIGHT*0.5)));
        footerPanel.setPreferredSize(new Dimension(Frame.WIDTH, newHeight));

        //Create labels
        JLabel labelTitle = GUIHelper.createLabel(TITLE, headerPanel, 0,0);

        //Label formatting for the Header
        labelTitle.setFont(new Font("Century Gothic", Font.BOLD, 40));
        labelTitle.setForeground(Color.WHITE);

        //Adds back button to the footer
        backButton = GUIHelper.newButton("Back", footerPanel, 0, 0, 5, 10, 5, (int) Math.round(Frame.WIDTH*0.9));
        backButton.addActionListener(this);

        //Adds panel to Main.frame
        Main.frame.add(playPanel);

        // Render the current maze
        currentMaze.render(contentPanel);

        // Open the menu
        playPanel.setVisible(false);
    }

    @Override
    public void openMenu() {
        Main.frame.setTitle(Frame.TITLE_PREFIX + TITLE);
        playPanel.setVisible(true);
    }

    @Override
    public void closeMenu() {
        playPanel.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Switch statement to detect which button is pressed
        closeMenu();
        Main.createMenu.openMenu();
    }

}