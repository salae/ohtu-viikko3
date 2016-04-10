package ohtu.data_access;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import ohtu.domain.User;


public class FileUserDAO implements UserDao{
    
    private String fileName;
    private File file;
    private FileWriter writer;
    private List<User> users;
    
    public FileUserDAO(String fileName){
        this.fileName = fileName;
        this.users = new ArrayList<User>();
        try {
            readUsers(fileName);
        } catch (Exception e) {
            System.out.println("Luodaan uusi tiedosto.");
            createNewFile(fileName);
        }
    }
    
    private void createNewFile(String filePath){
        try {
            file = new File(filePath);
            file.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(FileUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    @Override
    public List<User> listAll() {        
        return users;
    }

    @Override
    public User findByName(String name) {
        for (User user : users) {
            if (user.getUsername().equals(name)) {
                return user;
            }
        }
        return null;    
    }

    @Override
    public void add(User user) {
        try {
            users.add(user);
            writer = new FileWriter(fileName, true); //true, ettei kirjoita päälle
            writer.write(user.getUsername() + ": " + user.getPassword() + "\n");
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(FileUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    private void addToUserList(User user) {
        users.add(user);
    }
    
    private void readUsers(String fileName)throws Exception{
        Scanner lukija = new Scanner(new File(fileName));        
        while (lukija.hasNextLine()) {
            String rivi = lukija.nextLine();
            System.out.println(rivi);
            String[] tiedot = rivi.split(":", 2);
            addToUserList(new User(tiedot[0],tiedot[1]));
            printUsers();
        }
    }
    
    public void printUsers(){
        for(User user : users){
            System.out.println(user.getUsername() + " salasana: " + user.getPassword());
        }
    }
    
}
