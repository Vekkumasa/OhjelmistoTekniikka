package studyrecord.dao;

import studyrecord.domain.User;
import java.util.List;

public interface UserDao {  
    /**
     * Retrieves user from the database
     * @param username
     * @param password
     * @return
     * @throws Exception 
     */
    
    User findByUsername(String username, String password) throws Exception;
    
    /**
     * Creates a new user
     * @param user
     * @return
     * @throws Exception 
     */
    
    User create(User user) throws Exception;
    
    /**
     * Retrieves user id from a specific user
     * @param user
     * @return
     * @throws Exception 
     */
    public int getUserId(User user) throws Exception;
}
