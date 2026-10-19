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

