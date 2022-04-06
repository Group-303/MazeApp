import java.awt.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;

public class Maze {
    private String name;
    private String author;
    private MazeItems items;
    private LocalDateTime created;
    private List<Edit> edits = new ArrayList<>();

    /***
     * Maze class that stores the details of a maze.
     * @param name The name of the new maze
     * @param author The full name of the author
     */
    public Maze(String name, String author) {
        this.name = name;
        this.author = author;
        this.created = LocalDateTime.now();
    }

    /***
     * Adds a new Edit object into the list of edits on the maze
     * @param newEdit Object containing information on the edit
     */
    public void CreateEdit(Edit newEdit) {
        this.edits.add(newEdit);
    }

    /***
     * Method for retrieving the name of the maze
     * @return Returns a String containing the name of the maze
     */
    public String getName() {
        return this.name;
    }

    /***
     * Method for retrieving the full name of the author of the maze
     * @return Returns a String containing the full name of the author of the maze
     */
    public String getAuthor() {
        return this.author;
    }

    /***
     * Method for retrieving the creation date and time of the maze in readable form
     * Uses "MMMM dd, yyyy" and "HH:mma" formatting patterns
     * @return Returns a String containing the date and time the maze was created
     */
    public String getCreatedReadable() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mma");
        return dateFormatter.format(this.created) + " at " + timeFormatter.format(this.created);
    }

    /***
     * Method for retrieving the creation date and time of the maze
     * @return Returns a LocalDateTime object containing the date and time the maze was created
     */
    public LocalDateTime getCreatedRaw() {
        return this.created;
    }

    /***
     * Method for retrieving the list of edits on the maze
     * @return Returns a list of Edit objects
     */
    public List<Edit> getEdits() {
        return this.edits;
    }
}
