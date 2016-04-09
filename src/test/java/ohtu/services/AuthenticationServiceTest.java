package ohtu.services;

import ohtu.data_access.InMemoryUserDao;
import ohtu.data_access.UserDao;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Anu
 */
public class AuthenticationServiceTest {
    UserDao userDao;
    AuthenticationService auth;
    
    public AuthenticationServiceTest() {
    }
    
    @Before
    public void setUp() {
        userDao = new InMemoryUserDao();
        auth = new AuthenticationService(userDao);
    }


    @Test
    public void testcreateUser() {
        System.out.println("createUser");
        String username = "pekkas";
        String password = "ee+oggggg";
        
        boolean expResult = true;
        boolean result = auth.createUser(username, password);
        assertEquals(expResult, result);
    }
    
}
