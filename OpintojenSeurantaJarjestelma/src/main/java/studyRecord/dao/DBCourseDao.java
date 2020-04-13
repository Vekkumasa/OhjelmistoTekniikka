package OpintojenSeurantaJarjestelma.dao;

import java.sql.*;
import OpintojenSeurantaJarjestelma.domain.Course;
import OpintojenSeurantaJarjestelma.domain.User;
import OpintojenSeurantaJarjestelma.dao.DBUserDao;
import java.util.ArrayList;
import java.util.List;

public class DBCourseDao implements CourseDao{
    private Connection connection;
    private List<Course> courses;
    private DBUserDao userDao;
    
    public DBCourseDao(DBUserDao userDao) throws Exception {
        this.courses = new ArrayList<Course>();
        this.userDao = userDao;
        connection = DriverManager.getConnection("jdbc:h2:./studies.db");
    }
    
    public void closeConnection() throws Exception {
        connection.close();
    }
    
    @Override
    public Course create(Course course, User user) throws Exception {
        String query = "INSERT INTO courses (courseName, credits, grade, completed, canceled, userID) VALUES (?,?,?,?,?,?);";
        int id = userDao.getUserId(user);
        try (Statement statement = connection.createStatement()) {
            PreparedStatement prepared = connection.prepareStatement(query);
            prepared.setString(1, course.getCourseName());
            prepared.setInt(2, course.getCredits());
            prepared.setInt(3, 0);
            prepared.setBoolean(4, false);
            prepared.setBoolean(5, false);
            prepared.setInt(6, id);
            prepared.executeUpdate();
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
        return course;
    }
    
    @Override
    public List<Course> getAll(User user) throws Exception {
        ArrayList<Course> courses = new ArrayList<Course>();
        int id = userDao.getUserId(user);
        String query = "SELECT courseName, credits, grade, completed, canceled FROM courses WHERE userID = ?;";
        
        try (Statement statement = connection.createStatement()) {
            PreparedStatement prepared = connection.prepareStatement(query);
            prepared.setInt(1, id);
            ResultSet rs = prepared.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("courseName") + " " + rs.getInt("credits") + " " + rs.getInt("grade") + " " + rs.getBoolean("completed") + " " + rs.getBoolean("canceled"));
                Course course = new Course(rs.getString("courseName"), rs.getInt("credits"));
                course.setGrade(rs.getInt("grade"));
                course.setCompleted(rs.getBoolean("completed"));
                course.setCanceled(rs.getBoolean("canceled"));   
                courses.add(course);
            }       
        } catch (SQLException error) {
            System.out.println("täällä");
            System.out.println(error.getMessage());
        }        
        return courses;
    }
}
