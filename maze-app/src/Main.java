import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Entry point for the application.
public class Main {
    public static Frame frame;
    public static MainMenu mainMenu;
    public static BrowseMenu browseMenu;
    public static CreateMenu createMenu;
    public static Database database;

    public static void main(String[] args) throws SQLException {
        database = new Database();
        frame = new Frame();
        mainMenu = new MainMenu();
        createMenu = new CreateMenu();
        browseMenu = new BrowseMenu();
    }
}
