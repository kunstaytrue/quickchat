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

    /**
     *checks the input of cell number it has to start with (+27)
     * Reference: regular expression syntax adapted from the Java Pattern
     * class documentation, Oracle (n.d.) Pattern (Java SE 17 & JDK 17).
     * Available at: https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/regex/Pattern.html
     * (Accessed: 19 September 2026).
     */
    public boolean checkCellPhoneNumber(String cellphone) {
        String regex = "^\\+27\\d{1,10}$";
        return Pattern.matches(regex, cellphone);
    }
/**
     * checks whether the 3 previous inputs are valid
  
     */
    public String registerUser() {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(cellphone)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        return "Username successfully captured. Password successfully captured. Cell phone number successfully added.";
    }
    /**
     * Checks whether the entered username and password match the ones
     * captured at registration.
     */
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return enteredUsername.equals(username) && enteredPassword.equals(password);
    }

    /**
     * Returns the  message for a login attempt.
     */
    public String returnLoginStatus(boolean loginSuccess) {
        if (loginSuccess) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        }
        return "Username or password incorrect, please try again.";
    }
}