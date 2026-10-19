package quickchat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * Represents one QuickChat message and keeps track of every message sent or
 * stored while the program is running. 
 */
public class Message {

    private static final int MAX_MESSAGE_LENGTH = 250;

    
    private static ArrayList<Message> sentMessages = new ArrayList<>();
    private static ArrayList<Message> storedMessages = new ArrayList<>();

   
    private static String storageFile = "storedMessages.json";

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;

/**
     * Creates a message with a newly generated random ten digit ID.
     */
    public Message(int messageNumber, String recipient, String messageText) {
        this(generateMessageID(messageNumber), messageNumber, recipient, messageText);
    }

    /**
     * Creates a message with a given ID 
     */
    public Message(String messageID, int messageNumber, String recipient, String messageText) {
        this.messageID = messageID;
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = createMessageHash();
    }
    lly captured.";
        }
        return "Cell phone number is incorrectly fo/**
     * Builds a ten digit ID using string manipulation and the loop counter.
     * A random number is padded to 19 digits, substring takes nine random
     * digits from the end, and the last digit comes from the message number
     * (the loop counter), so the ID is always exactly ten digits.
     */
    private static String generateMessageID(int messageNumber) {
        Random random = new Random();
        long randomNumber = Math.abs(random.nextLong() % Long.MAX_VALUE);
        String digits = String.format("%019d", randomNumber);
        String randomPart = digits.substring(digits.length() - 9);
        String counterPart = String.valueOf(messageNumber % 10);
        return randomPart + counterPart;
    }

    /**
     */
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    /**
     * Returns the message used when an ID has been generated.
     */
    public String describeMessageID() {
        return "Message ID generated: " + messageID;
    }

    /**
     */
    public static boolean isRecipientValid(String recipient) {
        return Pattern.matches("^\\+27\\d{1,10}$", recipient);
    }

    /**
     */
    public static String checkRecipientCell(String recipient) {
        if (isRecipientValid(recipient)) {
            return "Cell phone number successfurmatted or does not contain an international code. Please correct the number and try again.";
    }
/**
     */
    public static boolean isMessageLengthValid(String messageText) {
        return messageText.length() <= MAX_MESSAGE_LENGTH;
    }

    /**
     */
    public static String checkMessageLength(String messageText) {
        if (isMessageLengthValid(messageText)) {
            return "Message ready to send.";
        }
        int excess = messageText.length() - MAX_MESSAGE_LENGTH;
        return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
    }

    /**
     * creates hagh
     */
    public String createMessageHash() {
        String[] words = messageText.trim().split("\\s+");
        String firstWord = removeSymbols(words[0]);
        String lastWord = removeSymbols(words[words.length - 1]);
        String firstTwoDigits = messageID.substring(0, 2);
        return (firstTwoDigits + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
    }

    /**
     */
    private String removeSymbols(String word) {
        return word.replaceAll("[^A-Za-z0-9]", "");
    }

    /**
     */
    public String sentMessage(int choice) {
        if (choice == 1) {
            sentMessages.add(this);
            return "Message successfully sent.";
        } else if (choice == 2) {
            return "Press 0 to delete the message.";
        } else if (choice == 3) {
            storeMessage();
            return "Message successfully stored.";
        }
        return "Invalid option, please choose 1, 2 or 3.";
    }

/**
     * Stores this message in the json file.
     *
     * Reference: the Gson library is used to turn the stored messages into
     * JSON. Google (n.d.) Gson user guide. Available at:
     * https://github.com/google/gson/blob/main/UserGuide.md
     * (Accessed: 19 October 2026).
     */
    public void storeMessage() {
        storedMessages.add(this);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File file = new File(storageFile);
        if (file.getParentFile() != null) {
            file.getParentFile().mkdirs();
        }

        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(storedMessages, writer);
        } catch (IOException e) {
            System.out.println("Could not write to the JSON file: " + e.getMessage());
        }
    }

    /**
     */
    public String getMessageDetails() {
        return "Message ID: " + messageID + "\n"
                + "Message Hash: " + messageHash + "\n"
                + "Recipient: " + recipient + "\n"
                + "Message: " + messageText;
    }

    /**
     * brings back the details of every message sent while the program is running.
     */
    public static String printMessages() {
        String allMessages = "";
        for (int i = 0; i < sentMessages.size(); i++) {
            allMessages += sentMessages.get(i).getMessageDetails() + "\n\n";
        }
        return allMessages;
    }

    /**
     * brings back  the total number of messages sent.
     */
    public static int returnTotalMessages() {
        return sentMessages.size();
    }

    /**
     * empties out the sent and stored lists 
     */
    public static void clearMessages() {
        sentMessages.clear();
        storedMessages.clear();
    }

    /**
     */
    public static void setStorageFile(String fileName) {
        storageFile = fileName;
    }

    public String getMessageID() {
        return messageID;
    }

    public String getMessageHash() {
        return messageHash;
    }
}