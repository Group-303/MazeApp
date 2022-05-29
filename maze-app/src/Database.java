import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Database {

    /**
     * The singleton instance of the database connection.
     */
    private static Connection connection = null;
    private static Statement statement = null;
    private static String query = null;

    /**
     * Constructor initializes the connection.
     */
    Database() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:Cartographer.db");
            System.out.println("Connection to Cartographer.db has been established.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        //Properties props = new Properties();
       //FileInputStream in = null;
       //try {
       //    in = new FileInputStream("./db.props");
       //    props.load(in);
       //    in.close();

       //    // specify the data source, username and password
       //    String url = props.getProperty("jdbc.url");
       //    String username = props.getProperty("jdbc.username");
       //    String password = props.getProperty("jdbc.password");
       //    String schema = props.getProperty("jdbc.schema");

       //    // get a connection
       //    instance = DriverManager.getConnection(url + "/" + schema, username,
       //            password);
       //} catch (SQLException sqle) {
       //    System.err.println(sqle);
       //} catch (FileNotFoundException fnfe) {
       //    System.err.println(fnfe);
       //} catch (IOException ex) {
       //    ex.printStackTrace();
       //}
    }

    public static boolean addMaze(Maze maze) throws SQLException {
        try {
            statement = connection.createStatement();
            query = "INSERT INTO Mazes (width, height, title, creator, creationTime, hashmap, layout ) VALUES ('" + maze.getWidth() + ", " + maze.getHeight() + "', '" + maze.getTitle() + "', '" + maze.getCreator() + ")";//"', " + maze.getCreatedRaw() + "', " + maze.getHashmap() + "', " + maze.getLayout() + ")";
            statement.executeUpdate(query);
            query = "SELECT id FROM Mazes WHERE title=" + maze.getTitle() + " AND creator=" + maze.getCreator();
            maze.setID(statement.executeQuery(query).getInt(0));
            statement.close();
            return true;
        } catch (SQLException e) {
            return false;
            //System.out.println("Connection to Cartographer.db has been lost.");
            //connection = DriverManager.getConnection("jdbc:sqlite:Cartographer.db");
            //System.out.println("Connection to Cartographer.db has been reestablished.");
        }
    }

    public static boolean dropMaze(Maze maze) {
        try {
            statement = connection.createStatement();
            query = "DELETE FROM Mazes WHERE id=" + maze.getId();
            statement.executeUpdate(query);
            statement.close();
            return true;
        }
        catch (SQLException e) {
            return false;
        }
    }

    /**
     * Provides global access to the singleton instance of the UrlSet.
     *
     * @return a handle to the singleton instance of the UrlSet.
     */
   //public static Connection getInstance() {
   //    if (instance == null) {
   //        new Database();
   //    }
   //    return instance;
   //}
}