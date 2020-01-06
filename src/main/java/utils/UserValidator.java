package utils;

import dao.UserDaoImpl;
import entity.User;
import exception.UserLoginAlreadyExistException;
import exception.UserNotFoundException;
import exception.UserShortLengthLoginException;
import exception.UserShortLengthPasswordException;
import iface.UserDao;

import java.io.IOException;

public class UserValidator {

    private final static int LOGIN_MIN_LENGTH = 4;
    private final static int PASSWORD_MIN_LENGTH = 6;
    private static UserValidator instance = null;

    private UserValidator () {
    }

    public static UserValidator getInstance() {
            if (instance == null) {
                instance = new UserValidator();
            }
            return instance;
        }

    public boolean isValidate(User user) throws UserLoginAlreadyExistException, UserShortLengthLoginException, UserShortLengthPasswordException {

        if (isUserByLoginExist(user.getLogin())) {
            throw new UserLoginAlreadyExistException("User with login: " + user.getLogin() + " already exists");
        }

        if (!hasLoginMinLength(user.getLogin())) {
            throw new UserShortLengthLoginException("User login: " + user.getLogin() + " to short. Minimum length: " + LOGIN_MIN_LENGTH);
        }

        if (!hasPasswordMinLength(user.getPassword())) {
            throw new UserShortLengthPasswordException( "To short User password. Minimum length: " + PASSWORD_MIN_LENGTH);
        }
        return true;
    }

    private boolean isUserByLoginExist (String login) {
        UserDao userDao = new UserDaoImpl("users.txt");

        try {
            User user = userDao.getUserByLogin(login);
            return true;
        }
        catch (UserNotFoundException | IOException e) {
            return false;
        }
    }

    private boolean hasLoginMinLength(String login) {
        if (login.length() < LOGIN_MIN_LENGTH) {
            return false;
        }
        return true;
    }

    private boolean hasPasswordMinLength(String password) {
        if (password.length() < PASSWORD_MIN_LENGTH) {
            return false;
        }
        return true;
    }

}

