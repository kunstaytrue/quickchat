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
/**
     * shows the welcome message and the main menu until the user chooses to quit.
     */
    private static void runQuickChat(Scanner scanner) {
        System.out.println("\nWelcome to QuickChat.");

        int numberOfMessages = readPositiveNumber(scanner, "How many messages do you wish to enter? ");
        boolean messagesEntered = false;
        boolean running = true;

        while (running) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                if (messagesEntered) {
                    System.out.println("You have already entered the number of messages you set at the start.");
                } else {
                    sendMessages(scanner, numberOfMessages);
                    messagesEntered = true;
                }
            } else if (choice.equals("2")) {
                System.out.println("Coming Soon.");
            } else if (choice.equals("3")) {
                System.out.println("Goodbye.");
                running = false;
            } else {
                System.out.println("Invalid option, please enter 1, 2 or 3.");
            }
        }
    }
/**
* Shows the welcome message and the main menu until the user chooses to quit.
     */
    private static void runQuickChat(Scanner scanner) {
        System.out.println("\nWelcome to QuickChat.");

        int numberOfMessages = readPositiveNumber(scanner, "How many messages do you wish to enter? ");
        boolean messagesEntered = false;
        boolean running = true;

        while (running) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                if (messagesEntered) {
                    System.out.println("You have already entered the number of messages you set at the start.");
                } else {
                    sendMessages(scanner, numberOfMessages);
                    messagesEntered = true;
                }
            } else if (choice.equals("2")) {
                System.out.println("Coming Soon.");
            } else if (choice.equals("3")) {
                System.out.println("Goodbye.");
                running = false;
            } else {
                System.out.println("Invalid option, please enter 1, 2 or 3.");
            }
        }
    }

 