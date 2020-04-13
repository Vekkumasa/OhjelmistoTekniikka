package studyrecord.dao;

import studyrecord.domain.User;
import java.util.List;

public interface UserDao {   
    User findByUsername(String username, String password) throws Exception;
    User create(User user) throws Exception;
    public int getUserId(User user) throws Exception;
//    List<User> getAll() throws Exception;
}
