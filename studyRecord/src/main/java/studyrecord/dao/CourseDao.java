package studyrecord.dao;

import studyrecord.domain.Course;
import studyrecord.domain.User;
import java.util.List;

public interface CourseDao {
    Course create(Course course, User user) throws Exception;
    List<Course> getAll(User user) throws Exception;
    Course setCompleted(Course course, int grade, User user) throws Exception;
    int getCourseId(Course course, User user) throws Exception;
}
