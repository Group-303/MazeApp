import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CreateMenu implements IMenu, ActionListener {
    private final static String TITLE = "Maze Creator";
    private JButton generateSolution, regenerate, save;
    private List<JButton> buttonList = new ArrayList<>();
    private JPanel[] panels;

    // Does this need to be public?
    public final static int PANELS = 5;

    public CreateMenu() {
        //panel code
        panels = new JPanel[PANELS];
        Color[] colours = {Color.GREEN, Color.gray, Color.GREEN, Color.WHITE, Color.WHITE};
        String[] layoutLocation = {BorderLayout.NORTH,
                BorderLayout.EAST,
                BorderLayout.SOUTH,
                BorderLayout.WEST,
                BorderLayout.CENTER
        };

        //create  panels 0 to 3
        for (int i = 0; i < PANELS - 1; i++){
            panels[i] = createPanel(colours[i]);
            Main.frame.getContentPane().add(panels[i],layoutLocation[i]);
        }

        //create panel 4 (will be placed inside panel 1)
        panels[4] = createPanel(colours[4]);
        panels[1].add(panels[4],layoutLocation[4]);

        // WIDTH is 1200 and HEIGHT is 800
        panels[1].setPreferredSize(new Dimension(300, Frame.HEIGHT));
        panels[3].setPreferredSize(new Dimension(900, Frame.HEIGHT));
        panels[4].setPreferredSize(new Dimension(800, (Frame.HEIGHT) - 100));

        // Create buttons and add buttons to button list using GUIHelper
        buttonList.add(GUIHelper.newButton("Generate Solution", new Dimension(100, 50), new Point(Frame.H_CENTER - 200, Frame.V_CENTER - 200)));
        buttonList.add(GUIHelper.newButton("Regenerate", new Dimension(100, 50), new Point(Frame.H_CENTER + 200, Frame.V_CENTER - 200)));
        buttonList.add(GUIHelper.newButton("Save", new Dimension(100, 50), new Point(Frame.H_CENTER, Frame.V_CENTER + 200)));

        //add buttons the Panel 3
         for (JButton button : buttonList) {
             panels[3].add(button);
          }

         for (JButton button : buttonList) {
             button.addActionListener(this);
         }

        Main.frame.repaint();
        Main.frame.setVisible(true);
    }

    public void openMenu() {

    }

    private JPanel createPanel(Color c){
        //create a JPanel object
        JPanel panel = new JPanel();

        //panel background color
        panel.setBackground(c);

        //return the panel
        return panel;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getSource().toString()) {
            //generateSolution, regenerate, save
            case "Generate Solution":
                generateSolution.setEnabled(false);
                Main.frame.setTitle("MazeCo - Create New Maze");
                break;
            case "Regenerate":
                regenerate.setEnabled(false);
                break;
            case "Save":
                save.setEnabled(false);
                break;
        }

    }

}