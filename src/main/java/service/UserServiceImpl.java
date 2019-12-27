package service;

import entity.User;
import iface.UserService;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private List<User> users;

    // Constructor with empty list
    public UserServiceImpl() {
        this.users = new ArrayList<User>();
    }

    // Constructor with non-empty list
    public UserServiceImpl(List<User> users) {
        this.users = new ArrayList<User>(users);
    }

    public List<User> getAllUsers() {
        return this.users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void removeUserById(Long id) {
        for (User user : this.users)
        {
            if (user.getId().equals(id))
            {
                users.remove(user);
                break;
            }
        }
    }

}
