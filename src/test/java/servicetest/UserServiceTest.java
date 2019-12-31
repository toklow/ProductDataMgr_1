package servicetest;

import entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.UserServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceTest {

    @Test
    public void testGetAllUsers() throws IOException {
        //is
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "admin", "admin"));
        users.add(new User(2L,"pablo", "pablo"));

        //then
        UserServiceImpl userService = UserServiceImpl.getInstance();
        List<User> usersFromTestClass = userService.getAllUsers();

        //expected
        Assertions.assertEquals(users, usersFromTestClass);
    }


    @Test
    public void testAddUser() throws IOException {
        //is
        List<User> users = new ArrayList<>();
        User user = new User(100L,"admin", "admin");
        users.add(user);

        //then
        UserServiceImpl userService = UserServiceImpl.getInstance();
        userService.addUser(user);
        List<User> usersFromTestClass = userService.getAllUsers();

        //expected
        Assertions.assertEquals(users, usersFromTestClass);
    }


    @Test
    public void testRemoveUser() throws IOException {
        //is
        List<User> users = new ArrayList<>();
        User admin = new User(1L, "admin", "admin");
        User pablo = new User(2L, "pablo", "pablo");
        User enrico = new User(3L, "enrico", "enrico");
        users.add(admin);
        users.add(pablo);
        users.add(enrico);

        //then
        UserServiceImpl userService = UserServiceImpl.getInstance();
        userService.removeUserById(2L);
        users.remove(pablo);
        List<User> usersFromTestClass = userService.getAllUsers();

        //expected
        Assertions.assertEquals(users, usersFromTestClass);
    }
}
