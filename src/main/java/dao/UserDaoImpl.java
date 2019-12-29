package dao;

import entity.User;
import iface.UserDao;
import utils.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static entity.parser.UserParser.stringToUser;

public class UserDaoImpl implements UserDao {

    private String fileName;

    // Constructor with empty list
    public UserDaoImpl(String fileName) throws IOException {
        this.fileName = fileName;
        FileUtils.createNewFile(fileName);
    }

    // Constructor with non-empty list
    public UserDaoImpl(String fileName, List<User> users) throws IOException {
        this.fileName = fileName;
        FileUtils.createNewFile(fileName);

        this.saveUsers(users);
    }

    public List<User> getAllUsers() throws IOException {
        FileReader fileReader = new FileReader(this.fileName);
        BufferedReader reader = new BufferedReader(fileReader);
        List<User> users = new ArrayList<User>();
        String singleLineFromFile;

        while ((singleLineFromFile = reader.readLine()) != null) {
            users.add(stringToUser(singleLineFromFile));
        }
        return users;
    }

    public void saveUser(User user) throws IOException {
        List<User> users = getAllUsers();
        users.add(user);
        saveUsers(users);
    }

    public void saveUsers(List<User> users) throws FileNotFoundException {
        FileUtils.clearFile(fileName);

        PrintWriter printWriter = new PrintWriter(new FileOutputStream(this.fileName, true));
        for (User user : users) {
            printWriter.write(user.toString() + "\n");
        }
        printWriter.close();
    }


    public User getUserByLogin(String login) {
        return null;
    }

    public User getUserById(Long userId) {
        return null;
    }

    public void removeUserByLogin(String login) {

    }

    public void removeUserById(Long id) {

    }

}