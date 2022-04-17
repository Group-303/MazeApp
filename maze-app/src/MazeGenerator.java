import java.util.Arrays;
import java.util.Collections;

public class MazeGenerator {
    private int x;
    private int y;
    private int[][] layout;

    public MazeGenerator(int x, int y) {
        this.x = x;
        this.y = y;
        layout = new int[x][y];
        generate(0, 0);
    }

    // Testing purposes only! Will be removed once drawing is implemented
    public void display() {
        for (int i = 0; i < y; i++) {
            // draw the north edge
            for (int j = 0; j < x; j++) {
                System.out.print((layout[j][i] & 1) == 0 ? "+---" : "+   ");
            }
            System.out.println("+");
            // draw the west edge
            for (int j = 0; j < x; j++) {
                System.out.print((layout[j][i] & 8) == 0 ? "|   " : "    ");
            }
            System.out.println("|");
        }
        // draw the bottom line
        for (int j = 0; j < x; j++) {
            System.out.print("+---");
        }
        System.out.println("+");
    }

    private static boolean isBetween(int v, int upper)
    {
        return (v >= 0) && (v < upper);
    }

    private void generate(int cx, int cy) {
        Direction[] directions = Direction.values();
        Collections.shuffle(Arrays.asList(directions));
        for (Direction direction : directions)
        {
            int nx = cx + direction.getDx();
            int ny = cy + direction.getDy();
            if (isBetween(nx, x) && isBetween(ny, y) && (layout[nx][ny] == 0))
            {
                layout[cx][cy] |= direction.getBit();
                layout[nx][ny] |= direction.opposite.getBit();
                generate(nx, ny);
            }
        }
    }
}
