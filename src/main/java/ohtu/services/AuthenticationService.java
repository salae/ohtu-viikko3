package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import ohtu.data_access.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class AuthenticationService {

    private UserDao userDao;

//    @Autowired
    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if(loginValidity(user, username, password)){
                return true;
            }
        }
        return false;
    }
    
    private boolean loginValidity(User user, String username, String password){
        return user.getUsername().equals(username)
                && user.getPassword().equals(password);
    }    


    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }
        if (invalid(username, password)) {
            return false;
        }
        userDao.add(new User(username, password));
        return true;
    }
    
    private boolean invalid(String username, String password){
        return invalidUsername(username) || invalidPassword(password);
    }

    private boolean invalidUsername(String username) {
        // validity check of username        
        return username.length() < 3 || username.matches(".*[^a-z].*");
    }
    
    private boolean invalidPassword(String password) {
        // validity check of password        
        return password.length() < 8 || password.matches("[a-zA-Z]*");
    }    
}
