package quickchat;

import java.util.Scanner;
/**
 * registers a user, then lets
 * them log in with the details they just captured.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("QuickChat Registration ");

        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter a username (must contain an underscore and be no more than 5 characters): ");
        String username = scanner.nextLine();

        System.out.print("Enter a password (min 8 characters, a capital letter, a number and a special character): ");
        String password = scanner.nextLine();

        System.out.print("Enter your cell phone number (e.g. +27838968976): ");
        String cellphone = scanner.nextLine();

        Login login = new Login(firstName, lastName, username, password, cellphone);

        System.out.println(login.registerUser());

        boolean registeredSuccessfully = login.checkUserName(username)
                && login.checkPasswordComplexity(password)
                && login.checkCellPhoneNumber(cellphone);

        if (registeredSuccessfully) {
            System.out.println("\n QuickChat Login ");

            System.out.print("Enter your username: ");
            String loginUsername = scanner.nextLine();

            System.out.print("Enter your password: ");
            String loginPassword = scanner.nextLine();

            boolean loginSuccess = login.loginUser(loginUsername, loginPassword);
            System.out.println(login.returnLoginStatus(loginSuccess));
        }

        scanner.close();
    }
}
