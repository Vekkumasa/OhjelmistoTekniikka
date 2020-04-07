package OpintojenSeurantaJarjestelma.domain;

import OpintojenSeurantaJarjestelma.dao.dbUserDao;
import OpintojenSeurantaJarjestelma.domain.User;
import java.util.ArrayList;
import java.util.List;

public class Service {
    private User loggedIn;
    private dbUserDao userDao;
    
    public Service(dbUserDao userDao) {
        this.userDao = userDao;
    }
    
    public User getUser() {
        return this.loggedIn;
    }
    
    public boolean createUser(String username, String password) {
        User newUser = new User(username, password);
        try {
            userDao.create(newUser);
        } catch (Exception error) {
            System.out.println(error.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean logIn(String username, String password) {
        User user = null;
        System.out.println(username);
        try {
            user = userDao.findByUsername(username, password);
        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
        
        if (user == null) {
            return false;
        }

        loggedIn = user;
        return true;
    }
}
