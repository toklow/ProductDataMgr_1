package dao;

import entity.User;
import exception.UserNotFoundException;
import iface.UserDao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private final String tableName = "users";
    private ConnectionDB connectionDB = null;


    public UserDaoImpl() {
        connectionDB = ConnectionDB.getInstance();
    }


    public List<User> getAllUsers() {
        String query = "select * from " + tableName;
        PreparedStatement statement = null;
        List<User> users = null;
        try {
            statement = connectionDB.getConnection().prepareStatement(query);
            users = getAllUsers(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void saveUsers(List<User> users) {
        for (User user : users) {
            saveUser(user);
        }
    }

    public void saveUser(User user) {
        PreparedStatement statement;

        try {
            String query = "insert into " + tableName + " (login, password, email, age, user_role_id) values(?, ?, ?, ?, ?)";
            statement = connectionDB.getConnection().prepareStatement(query);

            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setInt(4, user.getAge());
            statement.setInt(5, user.getRoleId());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUser(String login) throws UserNotFoundException {
        String query = "select * from " + tableName + " where login = values(?)";
        PreparedStatement statement = null;
        List<User> users = null;
        try {
            statement = connectionDB.getConnection().prepareStatement(query);
            statement.setString(1, login);
            users = getAllUsers(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (users.size() == 0) {
            throw new UserNotFoundException("User with login: " + login + " not found");
        }
        return users.get(0);
    }

    public User getUser(Long userId) throws UserNotFoundException {
        String query = "select * from " + tableName + " where id = values(?)";
        PreparedStatement statement = null;
        List<User> users = null;
        try {
            statement = connectionDB.getConnection().prepareStatement(query);
            statement.setLong(1, userId);
            users = getAllUsers(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (users.size() == 0) {
            throw new UserNotFoundException("User with id: " + userId + " not found");
        }
        return users.get(0);
    }

    private List<User> getAllUsers(PreparedStatement statement) {
        List<User> users = new LinkedList<>();

        try {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getLong("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        resultSet.getInt("age"),
                        resultSet.getInt("user_role_id"));

                users.add(user);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
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