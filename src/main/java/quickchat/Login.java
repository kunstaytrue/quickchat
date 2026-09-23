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

    /**
     *checks whether the passowrds have the required things .
     */
    public boolean checkPasswordComplexity(String password) {
        boolean hasCapitalLetter = false;
        boolean hasNumber = false;
        boolean hasSpecialCharacter = false;

        for (int i = 0; i < password.length(); i++) {
            char currentChar = password.charAt(i);

            if (Character.isUpperCase(currentChar)) {
                hasCapitalLetter = true;
            }
            if (Character.isDigit(currentChar)) {
                hasNumber = true;
            }
            if (!Character.isLetterOrDigit(currentChar)) {
                hasSpecialCharacter = true;
            }
        }

        return password.length() >= 8 && hasCapitalLetter && hasNumber && hasSpecialCharacter;
    }

    
