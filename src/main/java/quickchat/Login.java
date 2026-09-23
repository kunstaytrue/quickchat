package quickchat;

import java.util.regex.Pattern;

/**
 */
public class Login {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String cellphone;

    public Login(String firstName, String lastName, String username, String password, String cellphone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cellphone = cellphone;
    }


    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    