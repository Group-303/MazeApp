import java.time.LocalDateTime;

public class Edit {
    private int id;
    private int mazeID;
    private String description;
    private String editor;
    private LocalDateTime time;

    public Edit(String description, String editor) {
        this.description = description;
        this.editor = editor;
        this.time = LocalDateTime.now();
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEditor() {
        return this.editor;
    }

    public String getDescription() {
        return this.description;
    }

    public LocalDateTime getTime() {
        return this.time;
    }
}
