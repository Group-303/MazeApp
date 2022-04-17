import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class RenderGUI extends JFrame {
    private Point point1;
    private Point point2;

    public RenderGUI(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
        JPanel guiPanel = new JPanel();
        getContentPane().add(guiPanel);
        setSize(500, 500);
    }

    public void draw(Graphics graphics) {
        super.paint(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        Line2D line = new Line2D.Float();
        graphics2D.draw(line);
    }
}
