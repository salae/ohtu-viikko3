package ohtu.data_access;

import ohtu.domain.User;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FileUserDAOTest {
    
    FileUserDAO fu;
    
    public FileUserDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
       
    }
    
    @Before
    public void setUp() {
        fu = new FileUserDAO("salasanat.txt");
    }

    @Test
    public void testListAll() {
        System.out.println("test listAll");
        fu.printUsers();
        assertEquals("ittna",fu.listAll().get(0).getPassword());
    }

    @Test
    public void testFindByName() {
        System.out.println("test findByName found");
        User found = fu.findByName("antti");
        assertEquals("ittna",found.getPassword());
    }
    
    @Test
    public void testFindByNameNotFound() {
        System.out.println("test findByName not found");
        User found = fu.findByName("leena");
        assertEquals(null,found);
    }    

    @Test
    public void testAdd() {
        System.out.println("test add");
        fu.add(new User("poni","heppa"));
        fu.add(new User("haamu","huu"));
        fu.printUsers();
    }
    
}
