import java.time.LocalDateTime;

public class Created {
    private String creator;
    private LocalDateTime time;

    public Created(String creator) {
        this.creator = creator;
        this.time = LocalDateTime.now();
    }

    public String getCreator() {
        return this.creator;
    }

    public LocalDateTime getTime() {
        return this.time;
    }
}
