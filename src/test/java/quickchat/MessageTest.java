package quickchat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    private static final String RECIPIENT_1 = "+27718693002";
    private static final String MESSAGE_1 = "Hi Mike, can you join us for dinner tonight?";
    private static final String RECIPIENT_2 = "08575975889";
    private static final String MESSAGE_2 = "Hi Keegan, did you receive the payment?";

    @BeforeEach
    public void setUp() {
        // Start every test with empty lists and a test jSON file inside target
        Message.clearMessages();
        Message.setStorageFile("target/test-storedMessages.json");
    }

@Test
    public void testMessageLengthSuccess() {
        assertEquals("Message ready to send.", Message.checkMessageLength(MESSAGE_1));
    }

    @Test
    public void testMessageLengthFailure() {
        String longMessage = "a".repeat(260);
        assertEquals("Message exceeds 250 characters by 10; please reduce the size.",
                Message.checkMessageLength(longMessage));
    }
    
    @Test
    public void testRecipientCorrectlyFormatted() {
        assertEquals("Cell phone number successfully captured.",
                Message.checkRecipientCell(RECIPIENT_1));
    }

    @Test
    public void testRecipientIncorrectlyFormatted() {
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
                Message.checkRecipientCell(RECIPIENT_2));
    }
    
@Test
    public void testMessageHashForTestCase1() {
        Message message = new Message("0012345678", 0, RECIPIENT_1, MESSAGE_1);
        assertEquals("00:0:HITONIGHT", message.createMessageHash());
    }

    @Test
    public void testMessageHashesInALoop() {
        String[] recipients = {RECIPIENT_1, RECIPIENT_2};
        String[] messages = {MESSAGE_1, MESSAGE_2};
        String[] expectedHashes = {"00:0:HITONIGHT", "00:1:HIPAYMENT"};

        for (int i = 0; i < messages.length; i++) {
            Message message = new Message("0098765432", i, recipients[i], messages[i]);
            assertEquals(expectedHashes[i], message.createMessageHash());
        }
    }
    
@Test
    public void testMessageIDCreated() {
        Message message = new Message(0, RECIPIENT_1, MESSAGE_1);
        assertTrue(message.checkMessageID());
        assertEquals(10, message.getMessageID().length());
        assertTrue(message.getMessageID().matches("\\d{10}"));
        assertTrue(message.getMessageID().endsWith("0"));

        Message secondMessage = new Message(1, RECIPIENT_2, MESSAGE_2);
        assertEquals(10, secondMessage.getMessageID().length());
        assertTrue(secondMessage.getMessageID().endsWith("1"));
        assertEquals("Message ID generated: " + message.getMessageID(), message.describeMessageID());
    }
