import javax.swing.*;

public class Frame extends JFrame {
    public final static int WIDTH = 1200;
    public final static int HEIGHT = 800;
    public final static int H_CENTER = WIDTH / 2;
    public final static int V_CENTER = HEIGHT / 2;

    public Frame() {
        setTitle("MazeApp");
        setSize(WIDTH, HEIGHT);
        setLocation(H_CENTER, V_CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
}
