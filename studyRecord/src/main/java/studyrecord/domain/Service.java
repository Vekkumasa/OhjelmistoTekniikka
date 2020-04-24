package studyrecord.domain;

import studyrecord.dao.UserDao;
import studyrecord.dao.CourseDao;
import studyrecord.domain.User;
import java.util.ArrayList;
import java.util.List;

public class Service {
    private User loggedIn;
    private UserDao userDao;
    private CourseDao courseDao;
    
    public Service(UserDao userDao, CourseDao courseDao) {
        this.userDao = userDao;
        this.courseDao = courseDao;
    }
    
    /**
     * Retrieves user object who is currently logged in
     * 
     * @return Returns currently logged in user. 
     */
    public User getUser() {
        return this.loggedIn;
    }
    
    /**
     * Method creates a new course and adds it in to a database
     * @param courseName
     * @param credits
     * @param user
     * @return returns true if course is created, false if not.
     */
    
    public boolean createCourse(String courseName, int credits, User user) {
        Course course = new Course(courseName, credits);
        try {
            courseDao.create(course, user);
            System.out.println("ADDED");
        } catch (Exception error) {
            System.out.println(error.getMessage());
            return false;
        }
        return true;
    }
    
    /**
     * Retrieves user's id from database
     * @param user
     * @return Returns user:s id retrieved from database
     */
    public int getUserId(User user) {
        int id = 0;
        try {
            id = userDao.getUserId(user);
        } catch (Exception error) {
            System.out.println(error.getMessage());
        }        
        return id;
    }
    
    /**
     * Method sets course canceled from a specific user
     * @param course
     * @param user
     * @return Returns true if course is set canceled, false if not.
     */
    
    public boolean setCanceled(Course course, User user) {
        try {
            courseDao.setCanceled(course, user);
        } catch (Exception error) {
            System.out.println(error.getMessage());
            return false;
        }
        return true;
    }
    
    /**
     * Method sets course completed and sets grade for the course from a specific user
     * @param course
     * @param grade
     * @param user
     * @return Returns true if course is set ccompleted, false if not
     */
    public boolean setComplete(Course course, int grade, User user) {
        
        try {
            courseDao.setCompleted(course, grade, user);
        } catch (Exception error) {
            System.out.println(error.getMessage());
            return false;
        }
        return true;
    }
    
    /**
     * Retrieves all courses from a database
     * @param user
     * @return Returns list of courses from database.
     */ 
    public List<Course> getCourses(User user) {
        try {
            List<Course> test = courseDao.getAll(user);
            System.out.println("Array size " + test.size());
            return courseDao.getAll(user);
        } catch (Exception error) {
            System.out.println(error.getMessage());
            return new ArrayList<Course>();
        }
    }
    
    /**
     * Creates new user and inserts it into a database
     * @param username
     * @param password
     * @return True if user was created succesfully, false if not.
     */
    public boolean createUser(String username, String password) {
        User newUser = new User(username, password);
        try {
            userDao.create(newUser);
        } catch (Exception error) {
            System.out.println(error.getMessage());
            return false;
        }
        return true;
    }
    
    /**
     * Sets user as loggedin
     * @param username
     * @param password
     * @return Returns true if user is succesfully logged in, false if not.
     */
    public boolean logIn(String username, String password) {
        User user = null;
        System.out.println(username);
        try {
            user = userDao.findByUsername(username, password);
        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
        
        if (user == null) {
            return false;
        }

        loggedIn = user;
        return true;
    }
    
    /**
     * Logs out user
     */
    public void logOut() {
        this.loggedIn = null;
    }
}
