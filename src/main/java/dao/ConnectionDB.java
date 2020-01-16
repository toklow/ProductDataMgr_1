package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDB {

    private final static String databaseName = "people";
    private final static String databaselogin = "root";
    private final static String databasePassword = "admin";
    private static Connection connection = null;
    private static ConnectionDB instance = null;

    private ConnectionDB() {
        initConnection();
    }

    public static ConnectionDB getInstance() {
        if (instance == null) {
            instance = new ConnectionDB();
        }
        if (connection == null) {
            initConnection();
        }
        return instance;
    }

    public static Connection getConnection() {
        return connection;
    }

    private static void initConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + databaseName + "?useSSL=false", databaselogin, databasePassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
