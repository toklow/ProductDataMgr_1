import dao.ProductDaoImpl;
import dao.UserDaoImpl;
import entity.Boots;
import entity.Cloth;
import entity.User;
import iface.ProductDao;
import iface.UserDao;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        User user = new User(1L, "admin", "admin");
        Cloth cloth = new Cloth(1L, "T-shirt", 35.9f, 0.3f, "Black", 10f,"XL", "Cotton");
        Boots boots = new Boots(1L, "High heels", 99.9f, .5f, "Red", 12f, 35, true);

        ProductDao productClothDao = new ProductDaoImpl("clothes.txt", "CLOTH");
        productClothDao.saveProduct(cloth);

        ProductDao productBootsDao = new ProductDaoImpl("boots.txt", "BOOTS");
        productBootsDao.saveProduct(boots);

        UserDao userDao = new UserDaoImpl("users.txt");
        userDao.saveUser(user);
    }
}
