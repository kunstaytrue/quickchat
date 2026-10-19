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
            
                        if (loginSuccess) {
                runQuickChat(scanner);
            }
        }
        

        scanner.close();
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
     * lets the user enter the set number of messages, one loop per message.
     */
    private static void sendMessages(Scanner scanner, int numberOfMessages) {
        for (int i = 0; i < numberOfMessages; i++) {
            System.out.println("\n--- Message " + (i + 1) + " of " + numberOfMessages + " ---");

            String recipient = askForRecipient(scanner);
            String messageText = askForMessageText(scanner);

            // The loop counter (i) is used as the message number
            Message message = new Message(i, recipient, messageText);
            System.out.println(message.describeMessageID());

            int choice = askForSendOption(scanner);
            System.out.println(message.sentMessage(choice));

            if (choice == 1) {
                System.out.println("\n" + message.getMessageDetails());
            } else if (choice == 2) {
                String input = "";
                while (!input.equals("0")) {
                    input = scanner.nextLine();
                }
                System.out.println("Message deleted.");
            }
        }

        System.out.println("\n=== Messages sent ===");
        System.out.println(Message.printMessages());
        System.out.println("Total messages sent: " + Message.returnTotalMessages());
    }

    /**
     * Keeps asking until a valid whole number above zero is entered.
     */
    private static int readPositiveNumber(Scanner scanner, String prompt) {
        int number = 0;
        while (number < 1) {
            System.out.print(prompt);
            try {
                number = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                number = 0;
            }
            if (number < 1) {
                System.out.println("Please enter a whole number of 1 or more.");
            }
        }
        return number;
    }

    /**
     */
    private static String askForRecipient(Scanner scanner) {
        String recipient = "";
        boolean valid = false;
        while (!valid) {
            System.out.print("Enter the recipient's cell phone number (e.g. +27718693002): ");
            recipient = scanner.nextLine().trim();
            System.out.println(Message.checkRecipientCell(recipient));
            valid = Message.isRecipientValid(recipient);
        }
        return recipient;
    }

    /**
     */
    private static String askForMessageText(Scanner scanner) {
        String messageText = "";
        boolean valid = false;
        while (!valid) {
            System.out.print("Enter your message (max 250 characters): ");
            messageText = scanner.nextLine();
            System.out.println(Message.checkMessageLength(messageText));
            valid = Message.isMessageLengthValid(messageText) && !messageText.trim().isEmpty();
        }
        return messageText;
    }

    /**
     * Asks whether to send, disregard or store the message.
     */
    private static int askForSendOption(Scanner scanner) {
        int choice = 0;
        while (choice < 1 || choice > 3) {
            System.out.println("What would you like to do with this message?");
            System.out.println("1) Send Message");
            System.out.println("2) Disregard Message");
            System.out.println("3) Store Message to send later");
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                choice = 0;
            }
        }
        return choice;
    }
}

