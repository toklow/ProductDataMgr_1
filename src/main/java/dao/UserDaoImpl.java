package dao;

import entity.User;
import exception.UserNotFoundException;
import iface.UserDao;
import utils.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static entity.parser.UserParser.stringToUser;

public class UserDaoImpl implements UserDao {

    private String fileName;

    // Constructor with empty list
    public UserDaoImpl(String fileName) {
        this.fileName = fileName;
        try {
            FileUtils.createNewFile(fileName);
        } catch (IOException e) {
            System.out.println("Error with file path");
            System.exit(-1);   // exit closes application
        }
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
        List<User> users = new ArrayList<>();
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


    public User getUserByLogin(String login) throws IOException, UserNotFoundException {
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        throw new UserNotFoundException("User with Login: " +  login + " not found");
    }

    public User getUserById(Long userId) throws IOException, UserNotFoundException {
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getId().compareTo(userId) == 0) {
                return user;
            }
        }
        throw new UserNotFoundException("User with Id:" +  userId + " not found");
    }

    public void removeUserByLogin(String login) {

    }

    public void removeUserById(Long id) throws IOException {
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getId().compareTo(id) == 0) {
                users.remove(user);
                saveUsers(users);
                break;
            }
        }
    }

}