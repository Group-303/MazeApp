import java.awt.*;

/***
 * POSSIBLY TOO FAR OUT OF SCOPE
 * Class to generate a maze using the voronoi and kruscal algorithm
 */

//  Kruscal Method:
//  Create a list of all walls, and create a set for each cell, each containing just that one cell.
//  For each wall, in some random order:
//      If the cells divided by this wall belong to distinct sets:
//          Remove the current wall.
//          Join the sets of the formerly divided cells.

public class GeneratedMaze {
    public Image mazeImage;

    public GeneratedMaze(Image img) {
        this.mazeImage = kruscal(voronoi(img));
    }

    /***
     *
     * @param img Image of a shape for generating a Maze
     * @return An image split up in to polygons
     */
    private Image voronoi(Image img) {
        return img;
    }

    /***
     *
     * @param img Image of a shape split into polygons for generating a Maze
     * @return An image ready to be assigned to a new Maze
     */
    private Image kruscal(Image img) {
        return img;
    }
}
