public class Main {
    public static Frame frame;
    public static MainMenu mainMenu;
    public static BrowseMenu browseMenu;
    public static GeneratedMazeGUI generatedMenu;

    public static void main(String[] args) {
        frame = new Frame();
        mainMenu = new MainMenu();
        browseMenu = new BrowseMenu();
        generatedMenu = new GeneratedMazeGUI();
    }
}
