package data_access;

import entity.Fact;
import org.junit.jupiter.api.*;
import entity.City;

import java.util.HashMap;

class GameDataTest {

    private GameData gameData;

    @BeforeEach
    void setUp() {
        gameData = new GameData();
    }

    @Test
    void testSaveAndGetCity() {

        HashMap<String, Fact> facts = new HashMap<>();

        // Create a test city with the required facts
        City testCity = new City("TestCity", facts);

        // Save the city
        gameData.saveCity(testCity);

        // Retrieve the city
        City retrievedCity = gameData.getCity();

        // Verify the city is correctly retrieved
        Assertions.assertEquals(testCity.getName(), retrievedCity.getName());
        // Additional assertions may be needed to verify the facts are correctly retrieved
    }


}