package JavaCore.UserAutentication;

public class TestUserLogin {
    public static void main(String[] args) {
        UserModel userData = new UserModel();
        UserController user = userData.getUserbyUserName("doyen.soram");
        
        Login LoginUser = userData.authenticate(user.getUserName(), user.getPassword());

        UserController Loggeduser = (UserController) LoginUser;
        
        System.out.println("Hello, " + Loggeduser.getName());
    }
}