import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

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
            connection = DriverManager.getConnection("jdbc:sqlite:maze.db");
            statement = connection.createStatement();
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
    }

    /**
     * Creates a new maze in the database.
     *
     * @param maze The maze to be created.
     * @return The true if the maze was passed to database successfully, false otherwise.
     */
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

                query = "INSERT INTO Mazes (ID, WIDTH, HEIGHT, TITLE, CREATOR, CREATION_TIME, LAYOUT) VALUES ('" + maze.getId() + "', '" + maze.getWidth() + "', '" + maze.getHeight() + "', '" + maze.getTitle() + "', '" + maze.getCreator() + "', '" + maze.getCreatedRaw() + "', '" + maze.getLayoutJsonString() + "')";
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

    /**
     * Deletes a maze from the database.
     *
     * @param maze The maze to be deleted.
     * @return The true if the maze was deleted from the database successfully, false otherwise.
     * @throws SQLException
     */
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

    /**
     * Gets a maze from the database.
     *
     * @param mazeID The ID of the maze to be retrieved.
     * @return The maze if it exists in the database, null otherwise.
     */
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

    /**
     * Gets all mazes from the database.
     *
     * @return A list of all mazes in the database.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<Maze> getAllMazes() {
        List<Maze> mazeList = new ArrayList<>();

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:maze.db");
            statement = connection.createStatement();
            query = "SELECT * FROM Mazes";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                boolean[][][] layout = new Gson().fromJson(result.getString("LAYOUT"), boolean[][][].class);
                mazeList.add(new Maze(result.getInt("ID"), result.getString("TITLE"), result.getString("CREATOR"), result.getInt("WIDTH"), result.getInt("HEIGHT"), result.getInt("CREATION_TIME"), layout));
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

    /**
     * Gets the next available maze ID.
     *
     * @return The next available maze ID.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
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
}