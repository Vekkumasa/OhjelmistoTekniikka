package OpintojenSeurantaJarjestelma.dao;

import java.sql.*;
import OpintojenSeurantaJarjestelma.domain.User;
import java.util.ArrayList;
import java.util.List;

public class dbUserDao implements userDao {
    private Connection connection;
    private List<User> users;
    
    public dbUserDao() throws Exception {
        this.users = new ArrayList<>();
        connection = DriverManager.getConnection("jdbc:h2:./studies.db");
        String query = "CREATE TABLE IF NOT EXISTS users (name varchar NOT NULL, username varchar NOT NULL, password varchar NOT NULL);";
        connection.createStatement().execute(query);
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
    public User findByUsername(String username, String password) throws Exception {
        System.out.println("findbyusername: " + username + " " + password);
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";        
        try (Statement statement = connection.createStatement()) {
            PreparedStatement prepared = connection.prepareStatement(query);
            prepared.setString(1, username);
            prepared.setString(2, password);
            ResultSet rs = prepared.executeQuery();
            while (rs.next()) {
                String user_name = rs.getString("username");
                String pass_word = rs.getString("password");
                if (username.equals(user_name) && password.equals(pass_word)) {
                    User user = new User(username, password);
                    return user;
                }
            }            
        }       
        return null;
    }
    
    
}
