package studyrecord.dao;

import studyrecord.domain.Course;
import studyrecord.domain.User;
import java.util.List;

public interface CourseDao {
    /**
     * Creates a new course
     * @param course
     * @param user
     * @return
     * @throws Exception 
     */
    Course create(Course course, User user) throws Exception;
    /**
     * Retrieves all courses from a database
     * @see studyrecord.domain.Service.getCourses()
     * @param user
     * @return
     * @throws Exception 
     */
    List<Course> getAll(User user) throws Exception;
    /**
     * Sets course as completed and also sets the grade for the course
     * @param course
     * @param grade
     * @param user
     * @return
     * @throws Exception 
     */
    Course setCompleted(Course course, int grade, User user) throws Exception;
    /**
     * Sets the course canceled
     * @param course
     * @param user
     * @return
     * @throws Exception 
     */
    Course setCanceled(Course course, User user) throws Exception;
    /**
     * Retrieves the course id from a database
     * @param course
     * @param user
     * @return
     * @throws Exception 
     */
    int getCourseId(Course course, User user) throws Exception;
}
