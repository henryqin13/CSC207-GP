package entity;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashMap;

public class CityTest {
    private City city;
    private HashMap<String, Fact> facts;

    @Before
    public void setUp() {
        facts = new HashMap<>();
        // Assuming Fact is a properly defined class with an appropriate constructor
        // Add a sample Fact to the facts HashMap if needed
        // facts.put("factKey", new Fact(...));

        city = new City("TestCity", facts);
    }

    @Test
    public void testGetName_initializedName_ShouldReturnName() {
        assertEquals("TestCity", city.getName());
    }

    @Test
    public void testSetName_newName_ShouldUpdateName() {
        city.setName("NewTestCity");
        assertEquals("NewTestCity", city.getName());
    }

    // If there are more behaviors (methods) in the City class, they should be tested here as well.
    // Since the provided City class does not have methods to interact with the 'facts' HashMap,
    // no tests are written for that. If such methods are added, corresponding tests should also be added.
}