package OpintojenSeurantaJarjestelma.dao;

import OpintojenSeurantaJarjestelma.domain.User;
import java.util.List;

public interface userDao {   
    User findByUsername(String username, String password) throws Exception;
    User create(User user) throws Exception;
//    List<User> getAll() throws Exception;
}
