package OpintojenSeurantaJarjestelma;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import OpintojenSeurantaJarjestelma.domain.Course;

public class CourseTest {

    Course course;

    @Before
    public void setUp() {
        this.course = new Course("OHTE", 5);
    }
    
    @Test
    public void courseSetCompleted() {
        course.setCompleted(true);
        assertTrue(course.isCompleted());
    }
    
    @Test
    public void courseSetCanceled() {
        course.setCanceled(true);
        assertTrue(course.isCanceled());
    }
    
    @Test
    public void gradeCanBeSet() {
        course.setGrade(5);
        assertEquals(5, course.getGrade());
    }
    
    @Test
    public void creditsCanBeSet() {
        course.setCredits(8);
        assertEquals(8, course.getCredits());
    }
    
    @Test
    public void nameChanged() {
        course.setCourseName("OHTU");
        assertEquals("OHTU", course.getCourseName());
    }
    
    @Test
    public void toStringPrint() {
        assertEquals("OHTE: 5 credits", course.toString());
    }
}
