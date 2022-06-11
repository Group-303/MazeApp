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
    //private ButtonGroup buttonGroup = new ButtonGroup();

    //private JButton generateSolution, regenerate, save, back;
    private Maze currentMaze;

    private ArrayList<JButton> buttonList = new ArrayList<>();
    private ArrayList<JLabel> labelList = new ArrayList<>();
    private ArrayList<JTextField> fieldlist = new ArrayList<>();
    private ArrayList<JButton[][]> mazeButtons = new ArrayList<>();
    private ArrayList<JButton> mazeButtonList = new ArrayList<>();

    private JPanel contentPanel;

    public JPanel createPanel;
    public Color headerGreen = Color.getHSBColor(0.35f, 0.7f, 0.6f);
    public Color subheader = Color.getHSBColor(0.35f, 0.1f, 0.8f);

    public PlayMenu(Maze maze) {
        this.currentMaze = maze;
        
        //panel code
        createPanel = new JPanel(new BorderLayout());

        // Set boundary of panel
        createPanel.setBounds(0, 0, Frame.WIDTH, Frame.HEIGHT+10);

        //creating panels for layout inside the container panel
        JPanel headerPanel = GUIHelper.panelLayout(createPanel,headerGreen , BorderLayout.NORTH); //Header
        //JPanel sidePanel = GUIHelper.panelLayout(createPanel, subheader, BorderLayout.WEST); //sidebar
        contentPanel = GUIHelper.panelLayout(createPanel, Color.GRAY, BorderLayout.CENTER); // where the maze goes
        JPanel footerPanel = GUIHelper.panelLayout(createPanel, headerGreen, BorderLayout.SOUTH); //footer

        //the dimension of the panels
        int newHeight = (int) Math.round((Frame.HEIGHT) * 0.08);
        headerPanel.setPreferredSize(new Dimension(Frame.WIDTH, newHeight));
        //sidePanel.setPreferredSize(new Dimension((int) Math.round((Frame.WIDTH)*0.3), (int) Math.round(Frame.HEIGHT*0.5)));
        contentPanel.setPreferredSize(new Dimension((int) Math.round((Frame.WIDTH)*0.7), (int) Math.round(Frame.HEIGHT*0.5)));
        footerPanel.setPreferredSize(new Dimension(Frame.WIDTH, newHeight));
        //panel5.setPreferredSize(new Dimension((int)Math.round((sidePanel.WIDTH) * 0.80), (int)Math.round((sidePanel.HEIGHT) * 0.80)));

        //Create labels
        JLabel labelTitle = GUIHelper.createLabel(TITLE, headerPanel, 0,0);


        contentPanel.add(new TestingPanelGraphics.DrawStuff());

        //Label formatting for the Header
        labelTitle.setFont(new Font("Century Gothic", Font.BOLD, 40));
        labelTitle.setForeground(Color.WHITE);

        //for loop to format the JLabels in the SideBar
        for (JLabel  text: labelList) {
            text.setFont(new Font("Century Gothic", Font.BOLD, 14));
        }

        buttonList.add(GUIHelper.newButton("Back", footerPanel, 0, 0, 5, 10, 5, (int) Math.round(Frame.WIDTH*0.9)));

       // JRadioButton confirmRButton = GUIHelper.newRButton("Confirm", new Dimension(100, 50), new Point(sidePanel_H_CENTER+ 50, sidePanel_V_CENTER + 300));
       // createPanel.add(confirmRButton);

        //Adds panel to Main.frame
        Main.frame.add(createPanel);

        // Add JButtons in buttonList to sidePanel and add action listeners
        for (JButton button : buttonList) {
            button.addActionListener(this);
        }

        currentMaze.render(contentPanel);

        createPanel.setVisible(false);
    }

    public void openMenu() {
        Main.frame.setTitle(Frame.TITLE_PREFIX + TITLE);
        createPanel.setVisible(true);

        try {
            BufferedImage picture = ImageIO.read(new File("PlaceholderMaze.png"));
            JLabel imageLabel = new JLabel(new ImageIcon(picture));
            createPanel.add(imageLabel);
        } catch (IOException e) {
            //System.out.println("Invalid");
        }
    }

    public void closeMenu() {
        createPanel.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Switch statement to detect which button is pressed
        closeMenu();
        Main.createMenu.openMenu();
    }

}