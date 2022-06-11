import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MazeGenerator {
    private int width;
    private int height;
    private int[][] layout;

    public MazeGenerator(int width, int height) {
        this.width = width;
        this.height = height;
        layout = new int[this.width][this.height];
        generate();
    }

    /***
     *
     * @return Returns 3D boolean array
     */
    public boolean[][][] getLayout() {
        boolean[][][] format = new boolean[this.width][this.height][2];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                format[j][i][0] = ((layout[j][i] & 1) == 0);
            }
            for (int j = 0; j < width; j++) {
                format[j][i][1] = ((layout[j][i] & 8) == 0);
            }
        }
        return format;
    }

    private static boolean isBetween(int v, int upper)
    {
        return (v >= 0) && (v < upper);
    }

    public void generate() {
        int DEFAULT_CX = 0, DEFAULT_CY = 0;
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
        this.layout = new int[this.width][this.height];
    }

    public void regenerateMaze() {
        this.layout = new int[this.width][this.height];
        generate(0, 0);
    }

    public void setWidth(int width) {
        this.width = width;
        int[][] newSize = new int[this.width][this.height];
        //this.layout = newSize;

        System.arraycopy(newSize, 0, this.layout, 0, newSize.length);
        System.out.println(this.layout.length);
        regenerateMaze();
    }

    public void setHeight(int height) {
        this.height = height;
        int[][] newSize = new int[this.width][this.height];
        //this.layout = newSize;
        System.arraycopy(newSize, 0, this.layout, 0, newSize.length);
        System.out.println(this.layout.length);
        regenerateMaze();
    }
    
}
