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
    public void courseCanBeSetCompleted() {
        course.setCompleted(true);
        assertTrue(course.isCompleted());
    }
    
    @Test
    public void courseCanBeSetCanceled() {
        course.setCanceled(true);
        assertTrue(course.isCanceled());
    }
    
    @Test
    public void courseGradeCanBeSet() {
        course.setGrade(5);
        assertEquals(5, course.getGrade());
    }
    
    @Test
    public void courseCreditsCanBeSet() {
        course.setCredits(8);
        assertEquals(8, course.getCredits());
    }
    
    @Test
    public void courseNameCanBeChanged() {
        course.setCourseName("OHTU");
        assertEquals("OHTU", course.getCourseName());
    }
    
    @Test
    public void toStringIsPrintable() {
        assertEquals("OHTE: 5 credits", course.toString());
    }
}
