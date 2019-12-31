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

    private final static String USER_FILE_NAME = "Users.txt";
    private static UserServiceImpl instance = null;
    private static UserDao userDao = null;


    // Constructor with empty list
    private UserServiceImpl() throws IOException {
        userDao = new UserDaoImpl(USER_FILE_NAME);
    }


    public static UserServiceImpl getInstance() throws IOException {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    private static boolean isUserValid (User user)
    {
        UserValidator userValidator = UserValidator.getInstance();
        boolean rslt = false;
        try {
            rslt = userValidator.isValidate(user);
        } catch (UserShortLengthLoginException e) {
            e.printStackTrace();
        } catch (UserShortLengthPasswordException e) {
            e.printStackTrace();
        } catch (UserLoginAlreadyExistException e) {
            e.printStackTrace();
        } finally { return rslt;}
    }

    public List<User> getAllUsers() throws IOException {
        return userDao.getAllUsers();
    }

    public void addUser(User user) throws IOException {
        if  (isUserValid(user)) {
             userDao.saveUser(user);
        }
      }

    public void removeUserById(Long id) {
        userDao.removeUserById(id);
    }

}
