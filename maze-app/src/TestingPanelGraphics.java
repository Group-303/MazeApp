import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class TestingPanelGraphics extends JPanel {

    public TestingPanelGraphics(){
        setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1000,1000));
        this.add(new DrawStuff(), BorderLayout.CENTER);

        this.setVisible(true); //probably not necessary

    }

    public static class DrawStuff extends JComponent{


        @Override
        protected void paintComponent(Graphics g){

            super.paintComponent(g);

            Graphics2D graph2 = (Graphics2D) g;

            graph2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);



            Shape rootRect = new Rectangle2D.Float(25, 25, 100, 100);

            graph2.setColor(Color.BLACK);
            graph2.draw(rootRect);

            revalidate();
            repaint();
        }
    }
}