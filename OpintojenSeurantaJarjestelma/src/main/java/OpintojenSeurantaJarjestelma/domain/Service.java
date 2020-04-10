package OpintojenSeurantaJarjestelma.domain;

import OpintojenSeurantaJarjestelma.dao.UserDao;
import OpintojenSeurantaJarjestelma.dao.CourseDao;
import OpintojenSeurantaJarjestelma.domain.User;
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
    
    public User getUser() {
        return this.loggedIn;
    }
    
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
    
    public void logOut() {
        this.loggedIn = null;
    }
}
