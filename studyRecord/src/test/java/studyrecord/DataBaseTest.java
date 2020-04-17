package studyRecord;


import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.ArrayList;
import studyrecord.domain.Service;
import studyrecord.domain.User;
import studyrecord.domain.Course;
import studyrecord.dao.DBCourseDao;
import studyrecord.dao.DBUserDao;
import java.sql.*;
import java.util.List;

public class DataBaseTest {

    DBUserDao userDao;
    DBCourseDao courseDao;
    Service service;
    User user;
    Course course;
    
    @Before
    public void setUp() throws Exception {
        user = new User("testi", "password");
        course = new Course("kurssi", 5);
        userDao = new DBUserDao("jdbc:h2:./studiesTest");
        courseDao = new DBCourseDao(userDao, "jdbc:h2:./studiesTest");
        service = new Service(userDao, courseDao);
        service.createUser("testi", "password");
        service.createCourse("kurssi", 5, user);
    }
    
    @After
    public void tearDown() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:./studiesTest");
        Statement statement = connection.createStatement();
        statement.execute("DROP TABLE IF EXISTS courses");
        statement.execute("DROP TABLE IF EXISTS users");
    }
    
    @Test
    public void userAddedToDatabase() {
        boolean test = service.createUser("testi", "testi");
        assertThat(test, is(true));
    }
    
    @Test
    public void findUserId() {        
        User user = new User("testi","password");
        assertThat(service.getUserId(user), is(1));
    }
    
    @Test
    public void findByUsername() throws Exception {
        User user = userDao.findByUsername("testi", "password");
        assertThat(service.getUserId(user), is(1));
    }
    
    @Test
    public void courseIsAdded() {
        boolean test = service.createCourse("testi", 4, user);
        assertThat(test, is(true));
    }
    
    @Test
    public void courseCanBeSetCompleted() {
        service.setComplete(course, 3, user);
        List<Course> courses = service.getCourses(user);
        assertThat(courses.get(0).isCompleted(), is(true));
    }
    
    @Test
    public void courseCanBeSetCanceled() {
        service.setCanceled(course, user);
        List<Course> courses = service.getCourses(user);
        assertThat(courses.get(0).isCanceled(), is(true));
    }
    
    @Test
    public void logIn() {
        assertThat(service.logIn("testi", "password"), is(true));
    }
    
    @Test
    public void loginFails() {
        assertThat(service.logIn("pena", "penala"), is(false));
    }
    
    @Test
    public void logOut() {
        service.logIn("testi", "password");
        service.logOut();
        assertThat(service.getUser(), is(nullValue()));
    }
}
