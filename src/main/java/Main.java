import dao.EmployeeDao;
import entity.*;
import enums.Colors;
import enums.Material;
import enums.Separators;
import enums.SkinType;
import exception.UserNotFoundException;
import iface.UserDao;
import service.ProductServiceImpl;
import service.UserServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Main {

    public static void main(String[] args) throws IOException {

        EmployeeDao employeeDao = new EmployeeDao();

        List<Employee> employees = new LinkedList<>();
        employees.add(new Employee("Jan", "Kowalski", 23));
        employees.add(new Employee("Michal", "Jankowski", 18));
        employees.add(new Employee("Piotr", "Nowak", 42));
        employees.add(new Employee("Ewa", "Jezierska", 18));
        for (Employee employee : employees) {
            employeeDao.createEmployee(employee);
        }

        employeeDao.deleteEmployee("Jankowski");
        employeeDao.deleteEmployee("Unknown");
        List<Employee> employeesFromDB = employeeDao.getAllEmployees();
        Employee employee = employeesFromDB.get(1);
        employee.setLastName(employee.getLastName() + "-Konieczny");
        employeeDao.updateEmployee(employee);
        System.out.println(employeesFromDB);


        List<User> users = new ArrayList<>();
        users.add(new User(1L, "admin", "adminpass"));
        users.add(new User(2L, "pablo", "pablopass"));
        users.add(new User(3L, "tokr", "tokrpass"));

        UserServiceImpl userService = UserServiceImpl.getInstance();
        for (User user : users) {
            userService.addUser(user);
        }

        List<User> usersFromFile = userService.getAllUsers();
        User user = users.get(1);
        userService.removeUserById(user.getId());
        usersFromFile = userService.getAllUsers();


        List<Product> products = new ArrayList<>();
        products.add(new Product(100L, "Marvel", 200.75, 10.0, Colors.BLACK, 100.0));
        products.add(new Product(200L, "Shirt", 300.0, 0.4, Colors.WHITE, 5.0));
        products.add(new Product(210L, "Coat", 300.0, 0.4, Colors.BLUE, 0.0));
        products.add(new Product(221L, "Socks - Negative count", 300.0, 0.4, Colors.BLUE, -5.0));
        products.add(new Product(222L, "", 300.0, 0.4, Colors.BLUE, 5.0));
        products.add(new Product(223L, "Socks - Price 0", 0.0, 0.4, Colors.BLUE, 5.0));
        products.add(new Product(224L, "Socks - Negative weight", 300.0, -0.4, Colors.BLUE, 5.0));
        products.add(new Product(225L, "Socks - OK", 300.0, 0.4, Colors.BLUE, 5.0));
        products.add(new Product(300L, "Sneakers", 750.0, 0.8, Colors.BLUE, 15.0));

        ProductServiceImpl productService = ProductServiceImpl.getInstance(Separators.PRODUCT_ID);
        productService.saveProducts(products);
        Integer count = productService.getCountProducts();
        boolean isProductOnStock = productService.isProductOnStockByName("Shirt");
        List<Product> productsFromFIle = productService.getAllProducts();


        List<Product> clothes = new ArrayList<>();
        clothes.add(new Cloth(1L, "T-shirt", 35.9f, 0.3f, Colors.RED, 10f, "XL", Material.COTTON));
        clothes.add(new Cloth(2L, "Pants", 48f, 0.7f, Colors.GREEN, 23f, "L", Material.WOOL));
        productService = ProductServiceImpl.getInstance(Separators.CLOTH_ID);
        productService.saveProducts(clothes);
        count = productService.getCountProducts();
        isProductOnStock = productService.isProductOnStockByName("Shirt");
        isProductOnStock = productService.isProductOnStockByName("Pants");
        productsFromFIle = productService.getAllProducts();

        List<Product> boots = new ArrayList<>();
        boots.add(new Boots(1L, "High heels", 99.9f, .5f, Colors.RED, 12f, 35, SkinType.NATURAL));
        boots.add(new Boots(2L, "Sneakers", 99.9f, .5f, Colors.YELLOW, 12f, 43, SkinType.ARTIFICIAL));
        productService = ProductServiceImpl.getInstance(Separators.BOOTS_ID);
        productService.saveProducts(boots);
        count = productService.getCountProducts();
        isProductOnStock = productService.isProductOnStockByName("Shirt");
        isProductOnStock = productService.isProductOnStockByName("Sneakers");
        productsFromFIle = productService.getAllProducts();
    }



    // For exceptions learning purposes
    private static boolean isUserNotFound(UserDao userDao, long Id) {
        try {
            User user = userDao.getUserById(Id);
            System.out.println("User found: " + user.getId() + " " + user.getLogin());
            return false;
        } catch (UserNotFoundException | IOException e) {
            e.printStackTrace();
            return true;
        }
    }


    // For exceptions learning purposes
    private static boolean isUserNotFound(UserDao userDao, String userName) {
        try {
            User user = userDao.getUserByLogin(userName);
            System.out.println("User found: " + user.getId() + " " + user.getLogin());
            return false;
        } catch (UserNotFoundException | IOException e) {
            e.printStackTrace();
            return true;
        }
    }

}
