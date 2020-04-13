package OpintojenSeurantaJarjestelma.dao;

import OpintojenSeurantaJarjestelma.domain.Course;
import OpintojenSeurantaJarjestelma.domain.User;
import java.util.List;

public interface CourseDao {
    Course create(Course course, User user) throws Exception;
    List<Course> getAll(User user) throws Exception;
}
