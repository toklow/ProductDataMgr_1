package service;

import dao.UserDaoImpl;
import entity.User;
import exception.UserLoginAlreadyExistException;
import exception.UserShortLengthLoginException;
import exception.UserShortLengthPasswordException;
import iface.UserDao;
import iface.UserService;
import utils.UserValidator;

import java.io.IOException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final static String USER_FILE_NAME = "users.txt";
    private static UserServiceImpl instance = null;
    private static UserDao userDao = null;


    private UserServiceImpl() {
        userDao = new UserDaoImpl(USER_FILE_NAME);
    }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    private static boolean isUserValid (User user)
    {
        UserValidator userValidator = UserValidator.getInstance();

        try { return userValidator.isValidate(user); }
        catch (UserShortLengthLoginException | UserShortLengthPasswordException | UserLoginAlreadyExistException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<User> getAllUsers() throws IOException {
        return userDao.getAllUsers();
    }

    public void addUser(User user) throws IOException {
        if  (isUserValid(user)) {
             userDao.saveUser(user);
        }
      }

    public void removeUserById(Long id) throws IOException {
        userDao.removeUserById(id);
    }

}
