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

    User getUser(String login) throws UserNotFoundException;

    User getUser(Long userId) throws UserNotFoundException;

    void removeUser(String login);

    void removeUserById(Long id) throws IOException;
}
