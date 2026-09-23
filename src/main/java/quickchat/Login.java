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

    