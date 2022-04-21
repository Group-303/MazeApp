import java.util.Arrays;
import java.util.Collections;

public class MazeGenerator {
    private int DEFAULT_CX, DEFAULT_CY = 0;
    private int width;
    private int height;
    private int[][] layout;

    public MazeGenerator(int width, int height) {
        this.width = width;
        this.height = height;
        layout = new int[this.width][this.height];
        generate(0, 0);
    }

    public void display() {
        for (int i = 0; i < height; i++) {
            // draw the north edge
            for (int j = 0; j < width; j++) {
                System.out.print((layout[j][i] & 1) == 0 ? "+---" : "+   ");
            }
            System.out.println("+");
            // draw the west edge
            for (int j = 0; j < width; j++) {
                System.out.print((layout[j][i] & 8) == 0 ? "|   " : "    ");
            }
            System.out.println("|");
        }
        // draw the bottom line
        for (int j = 0; j < width; j++) {
            System.out.print("+---");
        }
        System.out.println("+");
    }

    /***
     *
     * @return Returns 3D boolean array
     */
    public boolean[][][] getLayout() {
        boolean[][][] format = new boolean[this.width][this.height][2];

        for (int i = 0; i < height; i++) {
            // draw the north edge
            for (int j = 0; j < width; j++) {
                if ((layout[j][i] & 1) == 0) {
                    format[j][i][0] = true;
                }
                else
                {
                    format[j][i][0] = false;
                }
            }

            for (int j = 0; j < width; j++) {
                if ((layout[j][i] & 8) == 0) {
                    format[j][i][1] = true;
                }
                else {
                    format[j][i][1] = false;
                }
            }
        }
        return format;
    }

    private static boolean isBetween(int v, int upper)
    {
        return (v >= 0) && (v < upper);
    }

    public void generate() {
        Direction[] directions = Direction.values();
        Collections.shuffle(Arrays.asList(directions));
        for (Direction direction : directions)
        {
            int nx = DEFAULT_CX + direction.getDx();
            int ny = DEFAULT_CY + direction.getDy();
            if (isBetween(nx, width) && isBetween(ny, height) && (layout[nx][ny] == 0))
            {
                layout[DEFAULT_CX][DEFAULT_CY] |= direction.getBit();
                layout[nx][ny] |= direction.opposite.getBit();
                generate(nx, ny);
            }
        }
    }

    public void generate(int cx, int cy) {
        Direction[] directions = Direction.values();
        Collections.shuffle(Arrays.asList(directions));
        for (Direction direction : directions)
        {
            int nx = cx + direction.getDx();
            int ny = cy + direction.getDy();
            if (isBetween(nx, width) && isBetween(ny, height) && (layout[nx][ny] == 0))
            {
                layout[cx][cy] |= direction.getBit();
                layout[nx][ny] |= direction.opposite.getBit();
                generate(nx, ny);
            }
        }
    }

    public void clear() {
        //this.layout = null;
        //System.gc();
        this.layout = new int[this.width][this.height];
    }

    public void setWidth() {
        //
    }
}
