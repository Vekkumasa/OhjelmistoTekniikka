package studyRecord;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import studyrecord.domain.Course;
import studyrecord.domain.User;

public class UserTest {

    User user;
    ArrayList<Course> courseArray;

    @Before
    public void setUp() {
        this.courseArray = new ArrayList<Course>();
        this.user = new User("username", "password");
    }
    
    @Test
    public void creditsToArray() {
        Course course = new Course("TESTI", 5);
        user.addCredit(course);
        assertEquals(1, user.sizeOfArray());
    }
    
    @Test
    public void noDupesInArray() {
        Course course = new Course("TESTI", 5);
        user.addCredit(course);
        user.addCredit(course);
        assertEquals(1, user.sizeOfArray());
    }
    
    
    @Test
    public void usernameChangeable() {
        user.setUsername("pena");
        assertEquals("pena", user.getUsername());
    }
    
    @Test
    public void passwordChangeable() {
        user.setPassword("pena");
        assertEquals("pena", user.getPassword());
    }
    
    @Test
    public void userToString() {
        assertEquals("username: username", user.toString());
    }
    
}
