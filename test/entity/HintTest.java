package entity;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class HintTest {

    @Test
    public void testHintConstructorAndFields() {
        // Arrange
        String expectedHintDiff = "easy";
        String expectedKeyword = "test";

        // Act
        Hint hint = new Hint(expectedHintDiff, expectedKeyword);

        // Assert
        assertEquals("The hintDiff should match the expected value", expectedHintDiff, hint.hintDiff);
        assertEquals("The keyword should match the expected value", expectedKeyword, hint.keyword);
    }

    @Test
    public void testHintConstructorWithDifferentValues() {
        // Arrange
        String expectedHintDiff = "hard";
        String expectedKeyword = "example";

        // Act
        Hint hint = new Hint(expectedHintDiff, expectedKeyword);

        // Assert
        assertEquals("The hintDiff should match the expected value", expectedHintDiff, hint.hintDiff);
        assertEquals("The keyword should match the expected value", expectedKeyword, hint.keyword);
    }
}