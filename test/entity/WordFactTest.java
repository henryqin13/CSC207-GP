package entity;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class WordFactTest {

    private WordFact wordFact;

    @Before
    public void setUp() {
        wordFact = new WordFact();
        wordFact.Fact("Test Category", "Test Value");
    }

    @Test
    public void testConstructor() {
        WordFact newWordFact = new WordFact();
        newWordFact.Fact("Category", "Value");
        assertEquals("Category", newWordFact.getCategory());
        assertEquals("Value", newWordFact.getValue());
    }

    @Test
    public void testSetCategory() {
        wordFact.setCategory("New Category");
        assertEquals("New Category", wordFact.getCategory());
    }

    @Test
    public void testSetValue() {
        wordFact.setValue("New Value");
        assertEquals("New Value", wordFact.getValue());
    }

    @Test
    public void testGetCategory() {
        assertEquals("Test Category", wordFact.getCategory());
    }

    @Test
    public void testGetValue() {
        assertEquals("Test Value", wordFact.getValue());
    }
}