import entity.Boots;
import entity.Cloth;
import entity.Product;
import entity.User;
import exception.NumberNotFoundException;
import exception.UserNotFoundException;
import iface.UserDao;
import service.ProductServiceImpl;
import service.UserServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static utils.ExceptionsLearning.isFoundNumber;
import static utils.FileUtils.enumProductType.*;

public class Main {

    public static void main(String[] args) throws IOException {

        List<User> users = new ArrayList<>();
        users.add(new User(1L, "admin", "adminpass"));
        users.add(new User(2L,"pablo", "pablopass"));
        users.add(new User(3L,"tokr", "tokrpass"));

        UserServiceImpl userService = UserServiceImpl.getInstance();
        for (User user : users) {
            userService.addUser(user);
        }

        List<User> usersFromFile = userService.getAllUsers();
        User user =  users.get(1);
        userService.removeUserById(user.getId());
        usersFromFile = userService.getAllUsers();


        List<Product> products = new ArrayList<>();
        products.add(new Product(100L, "Marvel", 200.75, 10.0, "Brown", 100.0));
        products.add(new Product(200L, "Shirt", 300.0, 0.4, "White", 5.0));
        products.add(new Product(210L, "Coat", 300.0, 0.4, "White", 0.0));
        products.add(new Product(300L, "Sneakers", 750.0, 0.8, "Brown", 15.0));

        ProductServiceImpl productService = ProductServiceImpl.getInstance(PRODUCT);
        productService.saveProducts(products);
        Integer count = productService.getCountProducts();
        boolean isProductOnStock = productService.isProductOnStockByName("Shirt");
        List<Product> productsFromFIle = productService.getAllProducts();


        List<Product> clothes = new ArrayList<>();
        clothes.add(new Cloth(1L, "T-shirt", 35.9f, 0.3f, "Black", 10f,"XL", "Cotton"));
        clothes.add(new Cloth(2L, "Pants", 48f, 0.7f, "Green", 23f,"L", "Cotton"));
        productService = ProductServiceImpl.getInstance(CLOTH);
        productService.saveProducts(clothes);
        count = productService.getCountProducts();
        isProductOnStock = productService.isProductOnStockByName("Shirt");
        isProductOnStock = productService.isProductOnStockByName("Pants");
        productsFromFIle = productService.getAllProducts();

        List<Product> boots = new ArrayList<>();
        boots.add(new Boots(1L, "High heels", 99.9f, .5f, "Red", 12f, 35, true));
        boots.add(new Boots(2L, "Sneakers", 99.9f, .5f, "Blue", 12f, 43, false));
        productService = ProductServiceImpl.getInstance(BOOTS);
        productService.saveProducts(boots);
        count = productService.getCountProducts();
        isProductOnStock = productService.isProductOnStockByName("Shirt");
        isProductOnStock = productService.isProductOnStockByName("Sneakers");
        productsFromFIle = productService.getAllProducts();
    }



    // For exceptions learning purposes
    private static boolean isNumberNotFoundException(int element) {
        int [] numbers = {1, 5, 3, 4, 9, 0};
        boolean isException = false;

        try {
            isException = !isFoundNumber(numbers, element);
            System.out.println("Number found: " + isFoundNumber(numbers, element));
        } catch (NumberNotFoundException e) {
            e.printStackTrace();
            isException = true;
        } finally {
            return isException;
        }
    }


    // For exceptions learning purposes
    private static boolean isUserNotFound(UserDao userDao, long Id) {
        boolean isException = false;

        try {
            User user = userDao.getUserById(Id);
            System.out.println("User found: " + user.getId() + " " + user.getLogin());
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            isException = true;
        } finally {return isException;}
    }


    // For exceptions learning purposes
    private static boolean isUserNotFound(UserDao userDao, String userName) {
        boolean isException = false;

        try {
            User user = userDao.getUserByLogin(userName);
            System.out.println("User found: " + user.getId() + " " + user.getLogin());
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            isException = true;
        } finally {return isException;}
    }

}
