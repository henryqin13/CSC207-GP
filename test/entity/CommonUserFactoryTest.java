package entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CommonUserFactoryTest {

    @Test
    void testCreateValidUser() {
        // Arrange
        String expectedName = "TestUser";
        String expectedPassword = "TestPassword";
        CommonUserFactory factory = new CommonUserFactory();

        // Act
        User user = factory.create(expectedName, expectedPassword);

        // Assert
        assertNotNull(user, "The user should not be null");
        assertTrue(user instanceof CommonUser, "The user should be an instance of CommonUser");
        assertEquals(expectedName, user.getName(), "The name of the user should match the expected name");
        assertEquals(expectedPassword, user.getPassword(), "The password of the user should match the expected password");
    }

    @Test
    void testCreateUserWithEmptyName() {
        // Arrange
        String expectedName = "";
        String expectedPassword = "TestPassword";
        CommonUserFactory factory = new CommonUserFactory();

        // Act
        User user = factory.create(expectedName, expectedPassword);

        // Assert
        assertEquals(expectedName, user.getName(), "The name of the user should be empty as provided");
    }

    @Test
    void testCreateUserWithEmptyPassword() {
        // Arrange
        String expectedName = "TestUser";
        String expectedPassword = "";
        CommonUserFactory factory = new CommonUserFactory();

        // Act
        User user = factory.create(expectedName, expectedPassword);

        // Assert
        assertEquals(expectedPassword, user.getPassword(), "The password of the user should be empty as provided");
    }

    // Additional tests could be written to handle edge cases and invalid inputs,
    // depending on the constraints and requirements of the CommonUser class.
}