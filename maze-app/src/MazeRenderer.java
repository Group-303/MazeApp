import java.awt.*;
import java.awt.geom.Rectangle2D;

public class MazeRenderer {

    // Currently unimplemented. Not shown in design document.
    public void drawLine(Point point1, Point point2) {
        // Unimplemented
    }

    public void drawTest(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;

        Shape mazeOutline = new Rectangle2D.Float(100, 100, 1000, 500);

        graphics.setColor(Color.BLACK);
        graphics.draw(mazeOutline);
    }
}
