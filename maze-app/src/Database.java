import java.io.File;
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
        Properties properties = new Properties();
        FileInputStream inputStream = null;
        try {
            Class.forName("org.sqlite.JDBC");
            inputStream = new FileInputStream("E:\\CAB302\\Assignment\\out\\db.props");
//            inputStream = new FileInputStream("..\\out\\db.props");
            properties.load(inputStream);
            String url = properties.getProperty("jdbc.url");
            String username = properties.getProperty("jdbc.username");
            String password = properties.getProperty("jdbc.password");
            String schema = properties.getProperty("jdbc.schema");
            connection = DriverManager.getConnection(String.format("%s/%s", url, schema), username, password);
        } catch (ClassNotFoundException | SQLException | IOException e) {
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
        }
    }

//    public static boolean addEdit(Edit edit) throws SQLException {
//        try {
//            statement = connection.createStatement();
//            query = "INSERT INTO Mazes (id, height, title, creator, creationTime, hashmap, layout ) VALUES ('" + maze.getWidth() + ", " + maze.getHeight() + "', '" + maze.getTitle() + "', '" + maze.getCreator() + ")";//"', " + maze.getCreatedRaw() + "', " + maze.getHashmap() + "', " + maze.getLayout() + ")";
//            statement.executeUpdate(query);
//            query = "SELECT id FROM Mazes WHERE title=" + edit.getDescription() + " AND creator=" + edit.getEditor();
//            edit.setId(statement.executeQuery(query).getInt(0));
//            statement.close();
//            return true;
//        }
//        catch (SQLException e) {
//            return false;
//        }
//    }

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