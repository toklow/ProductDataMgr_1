package dao;

import entity.Employee;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class EmployeeDao {
    private final String databaseName = "people";
    private final String tableName = "employees";
    private final String employee = "root";
    private final String password = "admin";
    private Connection connection;

    public EmployeeDao() {
        init();
    }


    private void init() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + databaseName + "?useSSL=false", employee, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new LinkedList<>();
        Statement statement;
        try {
            statement = connection.createStatement();
            String query = "select * from " + tableName;
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastname");
                Integer age = resultSet.getInt("age");
                Employee employee = new Employee(id, name, lastName, age);
                employees.add(employee);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public void createEmployee(Employee employee) {
        PreparedStatement statement;
        try {
            String query = "insert into " + tableName + " (name, lastname, age) values(?, ?, ?)";
            statement = connection.prepareStatement(query);

            statement.setString(1, employee.getName());
            statement.setString(2, employee.getLastName());
            statement.setInt(3, employee.getAge());

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEmployee(Employee employee) {
        PreparedStatement statement;
        try {
            String query = "update " + tableName + " set name = ?, lastname = ?, age = ? where id = ?";
            statement = connection.prepareStatement(query);

            statement.setString(1, employee.getName());
            statement.setString(2, employee.getLastName());
            statement.setInt(3, employee.getAge());
            statement.setLong(4, employee.getId());

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployee(String lastName) {
        PreparedStatement statement;
        try {
            String query = "delete from " + tableName + " where lastname = ?";
            statement = connection.prepareStatement(query);

            statement.setString(1, lastName);

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
