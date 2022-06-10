import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    /**
     * The singleton instance of the database connection.
     */
    private static Connection connection = null;
    private static Statement statement = null;
    private static String query = null;
    private static int mazeCount = 0;

    /**
     * Constructor initializes the connection.
     */
    Database() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:maze.db");
            statement = connection.createStatement();

            // Clears the maze table to prevent errors, comment out if you want to keep the data
            //String sql = "DROP TABLE IF EXISTS Mazes";
            //statement.executeUpdate(sql);

            query = "CREATE TABLE IF NOT EXISTS Mazes " +
                    "(ID INTEGER PRIMARY KEY NOT NULL," +
                    " WIDTH INTEGER NOT NULL," +
                    " HEIGHT INTEGER NOT NULL," +
                    " TITLE TEXT UNIQUE NOT NULL," +
                    " CREATOR TEXT NOT NULL," +
                    " CREATION_TIME INTEGER," +
                    " EDITS TEXT," +
                    " LAYOUT TEXT)";
            statement.executeUpdate(query);
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Table created successfully");
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

    public static boolean addMaze(Maze maze) {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:maze.db");
            statement = connection.createStatement();
            //Add maze to database if maze does not already exist
            query = "SELECT * FROM Mazes WHERE TITLE = '" + maze.getTitle() + "'";
            ResultSet result = statement.executeQuery(query);
            if (result.next()) {
                statement.close();
                connection.close();
                throw new Exception("Maze already exists");
            }
            else {

                query = "INSERT INTO Mazes (ID, WIDTH, HEIGHT, TITLE, CREATOR, CREATION_TIME, MAZE) VALUES ('" + maze.getId() + "', '" + maze.getWidth() + "', '" + maze.getHeight() + "', '" + maze.getTitle() + "', '" + maze.getCreator() + "', '" + maze.getCreatedRaw() + "', '" + maze.getLayoutJsonString() + "')";
                statement.executeUpdate(query);
                statement.close();
                connection.close();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean dropMaze(Maze maze) {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:maze.db");
            statement = connection.createStatement();
            query = "DELETE FROM Mazes WHERE ID=" + maze.getId();
            statement.executeUpdate(query);
            statement.close();
            return true;
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Maze getMaze(int mazeID) throws SQLException {
        Maze maze;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:maze.db");
            statement =  connection.createStatement();
            query = "SELECT * FROM Mazes WHERE id=" + mazeID;
            ResultSet result = statement.executeQuery(query);
            maze = new Maze(result.getString("TITLE"), result.getString("CREATOR"), result.getInt("WIDTH"), result.getInt("HEIGHT"));
            statement.close();
            return maze;
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Maze> getAllMazes() {
        List<Maze> mazeList = new ArrayList<>();

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:maze.db");
            statement = connection.createStatement();
            query = "SELECT * FROM Mazes";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                mazeList.add(new Maze(result.getInt("ID"), result.getString("TITLE"), result.getString("CREATOR"), result.getInt("WIDTH"), result.getInt("HEIGHT")));
            }
            if (mazeList == null) {
                statement.close();
                connection.close();
                throw new SQLException("No mazes found");
            }
            statement.close();
            connection.close();
            return mazeList;
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getNextID() {
        int nextID = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:maze.db");
            statement =  connection.createStatement();
            query = "SELECT MAX(ID) FROM Mazes";
            ResultSet result = statement.executeQuery(query);
            nextID = result.getInt(1) + 1;
            statement.close();
            return nextID;
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
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