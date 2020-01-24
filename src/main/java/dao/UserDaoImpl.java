package dao;

import entity.User;
import enums.Role;
import exception.UserNotFoundException;
import iface.UserDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private final String tableName = "users";
    private ConnectionDB connectionDB;


    public UserDaoImpl() {
        connectionDB = ConnectionDB.getInstance();
    }


    public List<User> getAllUsers() {
        String query = "select * from " + tableName;
        PreparedStatement statement;
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
            String query = "insert into " + tableName + " (login, password, role_id) values(?, ?, ?)";
            statement = connectionDB.getConnection().prepareStatement(query);

            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getRoleId());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUser(String login) throws UserNotFoundException {
        String query = "select * from " + tableName + " where login = ?";
        PreparedStatement statement;
        List<User> users = null;
        try {
            statement = connectionDB.getConnection().prepareStatement(query);
            statement.setString(1, login);
            users = getAllUsers(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (users.isEmpty()) {
            throw new UserNotFoundException("User with login: " + login + " not found");
        }
        return users.get(0);
    }

    public User getUser(Long userId) throws UserNotFoundException {
        String query = "select * from " + tableName + " where id = ?";
        PreparedStatement statement;
        List<User> users = null;
        try {
            statement = connectionDB.getConnection().prepareStatement(query);
            statement.setLong(1, userId);
            users = getAllUsers(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (users.isEmpty()) {
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
                        Role.valueToEnum(resultSet.getInt("role_id")));

                users.add(user);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void removeUser(String login) {
        String query = "delete from " + tableName + " where login = ?";
        PreparedStatement statement;

        try {
            statement = connectionDB.getConnection().prepareStatement(query);
            statement.setString(1, login);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUser(Long userId) {
        String query = "delete from " + tableName + " where id = ?";
        PreparedStatement statement;

        try {
            statement = connectionDB.getConnection().prepareStatement(query);
            statement.setLong(1, userId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}