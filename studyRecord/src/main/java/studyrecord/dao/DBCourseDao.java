package studyrecord.dao;

import java.sql.*;
import studyrecord.domain.Course;
import studyrecord.domain.User;
import studyrecord.dao.DBCourseDao;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;

public class DBCourseDao implements CourseDao {
    private Connection connection;
    private List<Course> courses;
    private DBUserDao userDao;
    
    public DBCourseDao(DBUserDao userDao, String database) throws Exception {
        this.courses = new ArrayList<Course>();
        this.userDao = userDao;
        connection = DriverManager.getConnection(database);
    }
    /**
     * Closes connection to database
     * @throws Exception 
     */
    public void closeConnection() throws Exception {
        connection.close();
    }
    
    /**
     * Creates a new course
     * @param course
     * @param user
     * @return
     * @throws Exception 
     */
    @Override
    public Course create(Course course, User user) throws Exception {
        String query = "INSERT INTO courses (courseName, credits, grade, completed, canceled, userID) VALUES (?,?,?,?,?,?);";
        int id = userDao.getUserId(user);
        int courseId = getCourseId(course, user);
        if (courseId == -1) {
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
                System.out.println("create " + error.getMessage());
            }
        }
        return course;
    }
    
    /**
     * Retrieves all courses from a database
     * @see studyrecord.domain.Service.getCourses()
     * @param user
     * @return
     * @throws Exception 
     */
    @Override
    public List<Course> getAll(User user) throws Exception {
        ArrayList<Course> courses = new ArrayList<Course>();
        String query = "SELECT courseName, credits, grade, completed, canceled, completionDate FROM courses WHERE userID = ?;";
        
        try (Statement statement = connection.createStatement()) {
            PreparedStatement prepared = connection.prepareStatement(query);
            prepared.setInt(1, userDao.getUserId(user));
            ResultSet rs = prepared.executeQuery();
            while (rs.next()) {
                Course course = new Course(rs.getString("courseName"), rs.getInt("credits"));
                course.setGrade(rs.getInt("grade"));
                course.setCompleted(rs.getBoolean("completed"));
                course.setCanceled(rs.getBoolean("canceled"));
                course.setCompletionDate(new DateTime(System.currentTimeMillis()));
                courses.add(course);
            }       
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }        
        return courses;
    }
    
    /**
     * Sets course as completed
     * @param course
     * @param grade
     * @param user
     * @return
     * @throws Exception 
     */
    @Override
    public Course setCompleted(Course course, int grade, User user) throws Exception {
        String query = "UPDATE courses SET completed=true, grade = ? WHERE userID = ? AND id = ?";
        int userId = userDao.getUserId(user);
        int id = getCourseId(course, user);
        try (Statement statement = connection.createStatement()) {
            PreparedStatement prepared = connection.prepareStatement(query);
            prepared.setInt(1, grade);
            prepared.setInt(2, userId);
            prepared.setInt(3, id);
            prepared.executeUpdate();
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
        return course;
    }
    
    /**
     * Sets cource canceled
     * @param course
     * @param user
     * @return
     * @throws Exception 
     */
    @Override
    public Course setCanceled(Course course, User user) throws Exception {
        String query = "UPDATE courses SET canceled=true WHERE userID = ? AND courseName = ?";
        int userId = userDao.getUserId(user);
        try (Statement statement = connection.createStatement()) {
            PreparedStatement prepared = connection.prepareStatement(query);
            prepared.setInt(1, userId);
            prepared.setString(2, course.getCourseName());
            prepared.executeUpdate();
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
        return course;
    }
    
    /**
     * Finds courses primary key from database
     * @param course
     * @param user
     * @return
     * @throws Exception 
     */
    @Override
    public int getCourseId(Course course, User user) throws Exception {
        String query = "SELECT id FROM Courses WHERE courseName = ? AND userID = ?;";
        int id = -1;
        int userId = userDao.getUserId(user);
        try (Statement statement = connection.createStatement()) {
            PreparedStatement prepared = connection.prepareStatement(query);
            prepared.setString(1, course.getCourseName());
            prepared.setInt(2, userId);
            ResultSet rs = prepared.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
        return id;
    }

    /**
     * Deletes course from database
     * @param course
     * @param user
     * @return
     * @throws Exception 
     */
    @Override
    public boolean deleteCourse(Course course, User user) throws Exception {
        String query = "DELETE FROM courses WHERE courseName = ? AND userID = ?";
        int id = userDao.getUserId(user);
        try (Statement statement = connection.createStatement()) {
            PreparedStatement prepared = connection.prepareStatement(query);
            prepared.setString(1, course.getCourseName());
            prepared.setInt(2, id);
            prepared.executeUpdate();
        } catch (SQLException error) {
            System.out.println(error.getMessage());
            return false;
        }
        return true;
    }
}
