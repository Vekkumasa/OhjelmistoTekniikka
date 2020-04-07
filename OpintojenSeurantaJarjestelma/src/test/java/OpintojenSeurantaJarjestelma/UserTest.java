package OpintojenSeurantaJarjestelma;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import OpintojenSeurantaJarjestelma.domain.Course;
import OpintojenSeurantaJarjestelma.domain.User;

public class UserTest {

    User user;
    ArrayList<Course> courseArray;

    @Before
    public void setUp() {
        this.courseArray = new ArrayList<Course>();
        this.user = new User("username", "password");
    }
    
    @Test
    public void creditsCanBeAddedToArray() {
        Course course = new Course("TESTI", 5);
        user.addCredit(course);
        assertEquals(1, user.sizeOfArray());
    }
    
    @Test
    public void ifCourseIsAlreadyInArrayItIsNotAdded() {
        Course course = new Course("TESTI", 5);
        user.addCredit(course);
        user.addCredit(course);
        assertEquals(1, user.sizeOfArray());
    }
    
    
    @Test
    public void usernameCanBeChanged() {
        user.setUsername("pena");
        assertEquals("pena", user.getUsername());
    }
    
    @Test
    public void passwordCanBeChanged() {
        user.setPassword("pena");
        assertEquals("pena", user.getPassword());
    }
    
    @Test
    public void userToString() {
        assertEquals("username: username", user.toString());
    }
    
}
