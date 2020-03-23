
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import OpintojenSeurantaJarjestelma.domain.Course;

public class UserTest {

    ArrayList<Course> courseArray;

    @Before
    public void setUp() {
        this.courseArray = new ArrayList<Course>();
    }

    @Test
    public void hello() {
    
    }
    
    @Test
    public void creditsCanBeAddedToArray() {
        Course course = new Course("TESTI", 5);
        this.courseArray.add(course);
        assertEquals(1, this.courseArray.size());
    }
}
