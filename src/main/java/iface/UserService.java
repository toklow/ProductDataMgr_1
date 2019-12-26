package iface;

import entity.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void addUser(User user);
    void removeUserById(Long userId);
}
