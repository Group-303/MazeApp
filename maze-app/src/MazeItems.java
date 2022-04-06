import java.awt.*;
import java.util.HashMap;

public class MazeItems {
    private HashMap<Image, Position> items = new HashMap<>();

    public void addImage(Image image, Position position) {
        items.put(image, position);
    }
}
