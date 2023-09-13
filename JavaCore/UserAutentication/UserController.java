package JavaCore.UserAutentication;

public class UserController implements Person, Login {
    private String FirstName;
    private String LastName;
    private String UserName;
    private String Password;

    public UserController() {
        System.out.printf("This is a default consructor!");
        FirstName = "Tom Dick";
        LastName = "Harry";
    }

    public UserController(String FirstName, String LastName) {
        // this refers to the instance of this class
        this.FirstName = FirstName;
        this.LastName = LastName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getName() {
        return (FirstName + " " + LastName);
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getPassword() {
        return Password;
    }
}