package entity;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class NumFactTest {

    private NumFact numFact;
    private final String category = "Population";
    private final long value = 1000000L;

    @Before
    public void setUp() {
        // Initialize NumFact with a category and value
        numFact = new NumFact();
        numFact.Fact(category, value);
    }

    @Test
    public void testConstructor() {
        // Test the constructor through the getter methods
        assertEquals("Category should be set to Population", category, numFact.getCategory());
        assertEquals("Value should be set to 1000000", value, numFact.getValue());
    }

    @Test
    public void testSetCategory() {
        // Set a new category and test if it gets updated correctly
        String newCategory = "Area";
        numFact.setCategory(newCategory);
        assertEquals("Category should be updated to Area", newCategory, numFact.getCategory());
    }

    @Test
    public void testSetValue() {
        // Set a new value and test if it gets updated correctly
        long newValue = 500000L;
        numFact.setValue(newValue);
        assertEquals("Value should be updated to 500000", newValue, numFact.getValue());
    }

    @Test
    public void testGetCategory() {
        // Test if getCategory returns the correct category
        assertEquals("getCategory should return Population", category, numFact.getCategory());
    }

    @Test
    public void testGetValue() {
        // Test if getValue returns the correct value
        assertEquals("getValue should return 1000000", value, numFact.getValue());
    }
}