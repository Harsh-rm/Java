package JavaCore.UserAutentication;

import java.util.*;

public class UserModel {
    private static Map<String, UserController> users = new HashMap<>();

    static {
        UserController user = new UserController("Doyen", "Soram");
        user.setUserName("doyen.soram");
        user.setPassword("Imphal@01");
        users.put(user.getUserName(), user);

        // System.out.println(users);
    }

    public UserController getUserbyUserName(String UserName) {
        return users.get(UserName);
    }

    public Login authenticate(String UserName, String Password) {
        UserController user = users.get(UserName);
        if (user != null && Password.equals(user.getPassword())) {
            return user;
        }
        throw new RuntimeException("Invalid Credentials!");
    }
}