package studyrecord.dao;

import java.sql.*;
import studyrecord.domain.User;
import java.util.ArrayList;
import java.util.List;

public class DBUserDao implements UserDao {
    private Connection connection;
    
    public DBUserDao(String database) throws Exception {
        connection = DriverManager.getConnection(database);
        String create = "CREATE TABLE IF NOT EXISTS courses "
                + "(id int NOT NULL AUTO_INCREMENT, "
                + "courseName varchar NOT NULL, "
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
    public boolean create(User user) throws Exception {
        if (user.getUsername() == "" || user.getUsername() == null) return false;
        String query = "INSERT INTO users (username, password) values (?, ?);";       
        try (Statement statement = connection.createStatement()) {
            PreparedStatement prepared = connection.prepareStatement(query);
            prepared.setString(1, user.getUsername());
            prepared.setString(2, user.getPassword());
            prepared.executeUpdate();
        } catch (SQLException error) {
            System.out.println(error.getMessage());
            return false;
        }
        return true;
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
                    User user = new User(username, password);
                    return user;
                }
            }            
        }       
        return null;
    }
    
    public double getAverage(User user) throws Exception {
        int id = getUserId(user);
        ArrayList<Integer> list = new ArrayList();
        double total = 0;
        String query = "SELECT grade FROM courses WHERE userID = ? AND completed = true";
        try (Statement statement = connection.createStatement()) {
            PreparedStatement prepared = connection.prepareStatement(query);
            prepared.setInt(1, id);
            ResultSet rs = prepared.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("grade"));
            }
        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
        for (int i = 0; i < list.size(); i++) {
            total += list.get(i);
        }
        return total / list.size();
    }
    
    public double getCredits(User user) throws Exception {
        int id = getUserId(user);
        double credits = 0;
        String query = "SELECT credits FROM courses WHERE userID = ? AND Completed = true";
        try (Statement statement = connection.createStatement()) {
            PreparedStatement prepared = connection.prepareStatement(query);
            prepared.setInt(1, id);
            ResultSet rs = prepared.executeQuery();
            while (rs.next()) {
                credits += rs.getInt("credits");
            }
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }       
        return credits;
    }
    
    
}
