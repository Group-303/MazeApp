import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GeneratedMazeGUI implements ActionListener {
    public static final int PANELS = 5;
    private JButton generateSolution, regenerate, save;
    private List<JButton> buttonList = new ArrayList<>();
    private JFrame generatedFrame;
    private JPanel panels[];

    public void GeneratedMazeGUI() {
        //create frame
        JFrame generatedFrame = new JFrame();

        //panel code
        panels = new JPanel[PANELS];
        Color colours[] = {Color.GREEN, Color.gray, Color.GREEN, Color.WHITE, Color.WHITE};
        String layoutLocation[] = {BorderLayout.NORTH,
                BorderLayout.EAST,
                BorderLayout.SOUTH,
                BorderLayout.WEST,
                BorderLayout.CENTER
        };
        for (int i = 0; i < PANELS - 1; i++){
            panels[i] = createPanel(colours[i]);
            generatedFrame.getContentPane().add(panels[i],layoutLocation[i]);
        }
        panels[4] = createPanel(colours[4]);
        panels[1].add(panels[4],layoutLocation[4]);

        // WIDTH = 1200;
        // HEIGHT = 800;
        panels[1].setPreferredSize(new Dimension(300, Frame.HEIGHT));
        panels[3].setPreferredSize(new Dimension(900, Frame.HEIGHT));
        panels[4].setPreferredSize(new Dimension(800, (Frame.HEIGHT) - 100));

        //Create buttons and add buttons to button list using GUIHelper
        buttonList.add(GUIHelper.newButton("Generate Solution", new Dimension(100, 50), new Position(Main.frame.H_CENTER - 200, Main.frame.V_CENTER - 200)));
        buttonList.add(GUIHelper.newButton("Regenerate", new Dimension(100, 50), new Position(Main.frame.H_CENTER + 200, Main.frame.V_CENTER - 200)));
        buttonList.add(GUIHelper.newButton("Save", new Dimension(100, 50), new Position(Main.frame.H_CENTER, Main.frame.V_CENTER + 200)));

         for (JButton button : buttonList) {
             panels[4].add(button);
          }

        // Add mainGUIPanel to mainGUIFrame
        Main.frame.add(panels[4]);

         for (JButton button : buttonList) {
             button.addActionListener(this);
         }

        generatedFrame.repaint();
        generatedFrame.setVisible(true);
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

    }

}