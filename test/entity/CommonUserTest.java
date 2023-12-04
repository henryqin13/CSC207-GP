package entity;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CommonUserTest {

    private CommonUser user;

    @Before
    public void setUp() {
        user = new CommonUser("TestUser", "TestPassword");
    }

    @Test
    public void testGetName() {
        assertEquals("TestUser", user.getName());
    }

    @Test
    public void testGetPassword() {
        assertEquals("TestPassword", user.getPassword());
    }

    @Test
    public void testConstructorNotNull() {
        assertNotNull(new CommonUser("NewUser", "NewPassword"));
    }

    // If there are any constraints on the password (e.g., length, characters),
    // additional tests should be written to validate those constraints.
    // For example, if the password should be at least 8 characters long,

    /*
    @Test(expected = IllegalArgumentException.class)
    public void testPasswordTooShort() {
        new CommonUser("NewUser", "short");
    }
    */

    /*
    @Test(expected = IllegalArgumentException.class)
    public void testNullUsername() {
        new CommonUser(null, "ValidPassword");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyUsername() {
        new CommonUser("", "ValidPassword");
    }
    */
}