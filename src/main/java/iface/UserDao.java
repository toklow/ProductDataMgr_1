package iface;

import entity.User;
import exception.UserNotFoundException;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers() ;

    void saveUser(User user) ;

    void saveUsers(List<User> users) ;

    User getUser(String login) throws UserNotFoundException;

    User getUser(Long userId) throws UserNotFoundException;

    void removeUser(String login);

    void removeUser(Long id);
}
