import java.awt.*;

public class Maze {
    private String name;
    private String author;
    private Image image;
    private double created;
    private Edit[] edits;

    /***
     *
     * @param name The name of the new maze
     * @param author The full name of the author
     */
    public Maze(String name, String author, Image image) {
        this.name = name;
        this.author = author;
        this.image = image;
        this.created = 0.0; // Datetime calculation goes here
        this.edits = null; // Edits are initially empty
    }

    public void CreateEdit(Edit newEdit) {
        // Append new edit information (edit author and date) to Edit[] array
    }

}
