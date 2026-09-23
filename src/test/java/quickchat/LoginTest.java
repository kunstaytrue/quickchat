package quickchat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    @Test
    public void testUsernameCorrectlyFormatted() {
        Login login = new Login("Kyle", "Daniels", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        Login login = new Login("Kyle", "Daniels", "kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }
    @Test
    public void testPasswordMeetsComplexity() {
        Login login = new Login("Kyle", "Daniels", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testPasswordDoesNotMeetComplexity() {
        Login login = new Login("Kyle", "Daniels", "kyl_1", "password", "+27838968976");
        assertFalse(login.checkPasswordComplexity("password"));
    }
    @Test
    public void testCellPhoneCorrectlyFormatted() {
        Login login = new Login("Kyle", "Daniels", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCellPhoneIncorrectlyFormatted() {
        Login login = new Login("Kyle", "Daniels", "kyl_1", "Ch&&sec@ke99!", "08966553");
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }
    @Test
    public void testLoginSuccessful() {
        Login login = new Login("Kyle", "Daniels", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginFailed() {
        Login login = new Login("Kyle", "Daniels", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(login.loginUser("kyl_1", "wrongPassword"));
    }