package entity;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerAnswerTest {
    private PlayerAnswer playerAnswer;
    private final String testAnswer = "Test Answer";

    @Before
    public void setUp() {
        playerAnswer = new PlayerAnswer(testAnswer);
    }

    @Test
    public void testConstructor() {
        assertNotNull("PlayerAnswer object should not be null", playerAnswer);
    }

    @Test
    public void testToString() {
        String expectedString = "PlayerAnswer{answer='" + testAnswer + "\'}";
        assertEquals("toString should return the correct string representation", expectedString, playerAnswer.toString());
    }
}