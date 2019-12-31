import dao.ProductDaoImpl;
import dao.UserDaoImpl;
import entity.Boots;
import entity.Cloth;
import entity.User;
import exception.NumberNotFoundException;
import exception.UserNotFoundException;
import iface.ProductDao;
import iface.UserDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static utils.ExceptionsLearning.isFoundNumber;

public class Main {

    public static void main(String[] args) throws IOException {
        Cloth cloth = new Cloth(1L, "T-shirt", 35.9f, 0.3f, "Black", 10f,"XL", "Cotton");
        Boots boots = new Boots(1L, "High heels", 99.9f, .5f, "Red", 12f, 35, true);

        ProductDao productClothDao = new ProductDaoImpl("clothes.txt", "CLOTH");
        productClothDao.saveProduct(cloth);

        ProductDao productBootsDao = new ProductDaoImpl("boots.txt", "BOOTS");
        productBootsDao.saveProduct(boots);


        List<User> users = new ArrayList<>();
        users.add(new User(1L, "admin", "admin"));
        users.add(new User(2L,"pablo", "pablo"));
        users.add(new User(3L,"tokr", "tokr"));

        UserDao userDao = new UserDaoImpl("users.txt");
        userDao.saveUsers(users);

        // For exceptions learning purposes
        // boolean isException = isNumberNotFoundException(10);
        // isException = isUserNotFound(userDao,7L);
        // isException = isUserNotFound(userDao,"pabloP");

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
