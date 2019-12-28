package dao;

import entity.User;
import iface.UserDao;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;

public class UserDaoImpl implements UserDao {

    private String userFileName;

    // Constructor with empty list
    public UserDaoImpl(String userFileName) {
        this.userFileName = userFileName;
    }

    // Constructor with non-empty list
    public UserDaoImpl(String userFileName, List<User> users) throws FileNotFoundException {
        this.userFileName = userFileName;
        this.saveUsers(users);
    }

    public List<User> getAllUsers() {
        return null;
    }

    public void saveUser(User user) {
    }

    public void saveUsers(List<User> users) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(this.userFileName);

        for (User user : users) {
            printWriter.println(this.toString(user));
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


    private User parseStringToUser(String stringToParse) {
        String[] parse = new String[6];
        int beginIndex = 0;
        int endIndex;

        for (int i = 0; i < 5; i++) {
            if ((endIndex = stringToParse.indexOf('@', beginIndex)) == -1) {
                parse[i] = stringToParse.substring(beginIndex);
            } else {
                parse[i] = stringToParse.substring(beginIndex, endIndex);
                beginIndex = endIndex + 1;
            }
        }
        return new User(Long.parseLong(parse[0]), parse[1], parse[2], parse[3], Integer.parseInt(parse[5]));
    }

    private String toString(User user) {
        return String.format(Locale.ENGLISH, "%d@%s@%s@%s@%d", user.getId(), user.getLogin(), user.getPassword(), user.getEmail(), user.getAge());
    }
}