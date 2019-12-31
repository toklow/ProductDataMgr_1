package iface;

import entity.User;
import exception.UserNotFoundException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface UserDao {
    List<User> getAllUsers() throws IOException;
    void saveUser(User user) throws IOException;
    void saveUsers(List<User> users) throws FileNotFoundException;
    User getUserByLogin(String login) throws IOException, UserNotFoundException;
    User getUserById(Long userId) throws IOException, UserNotFoundException;
    void removeUserByLogin(String login);
    void removeUserById(Long id);
}
