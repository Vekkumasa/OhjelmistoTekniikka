package studyrecord.dao;

import java.sql.*;
import studyrecord.domain.User;
import java.util.ArrayList;
import java.util.List;

public class DBUserDao implements UserDao {
    private Connection connection;
    private List<User> users;
    
    public DBUserDao() throws Exception {
        this.users = new ArrayList<>();
        connection = DriverManager.getConnection("jdbc:h2:./studies");
        String create = "CREATE TABLE IF NOT EXISTS courses "
                + "(id int NOT NULL AUTO_INCREMENT ,"
                + "courseName varchar NOT NULL UNIQUE, "
                + "credits int, "
                + "grade int, "
                + "userID int, "
                + "completed boolean, "
                + "canceled boolean, "
                + "FOREIGN KEY (userID) REFERENCES Users(id), "
                + "PRIMARY KEY (id))";
        
        String query = "CREATE TABLE IF NOT EXISTS users "
                + "(id int NOT NULL AUTO_INCREMENT ,"
                + "username varchar NOT NULL UNIQUE, "
                + "password varchar NOT NULL, "
                + "PRIMARY KEY (id));";
        
        connection.createStatement().execute(query);
        connection.createStatement().execute(create);
    }
    
    public void closeConnection() throws Exception {
        connection.close();
    }
    
    @Override
    public User create(User user) throws Exception {
        String query = "INSERT INTO users (username, password) values (?, ?);";
        
        try (Statement statement = connection.createStatement()) {
            PreparedStatement prepared = connection.prepareStatement(query);
            prepared.setString(1, user.getUsername());
            prepared.setString(2, user.getPassword());
            prepared.executeUpdate();
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
        return user;
    }
    
    @Override
    public int getUserId(User user) throws Exception {
        String query = "SELECT id FROM Users WHERE username = ?;";
        int id = -1;
        try (Statement statement = connection.createStatement()) {
            PreparedStatement prepared = connection.prepareStatement(query);
            prepared.setString(1, user.getUsername());
            ResultSet rs = prepared.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
        return id;
    }
    
    @Override
    public User findByUsername(String username, String password) throws Exception {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";        
        try (Statement statement = connection.createStatement()) {
            PreparedStatement prepared = connection.prepareStatement(query);
            prepared.setString(1, username);
            prepared.setString(2, password);
            ResultSet rs = prepared.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("username");
                String pw = rs.getString("password");
                if (username.equals(name) && password.equals(pw)) {
                    System.out.println("ID: " + id);
                    User user = new User(username, password);
                    return user;
                }
            }            
        }       
        return null;
    }
    
    
}
