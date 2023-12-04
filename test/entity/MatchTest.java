package entity;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static org.junit.Assert.*;

public class MatchTest {
    private Match match;
    private City testCity;
    private List<PlayerAnswer> testUserAnswers;
    private HashMap<String, Fact> testFacts;

    @Before
    public void setUp() {
        testFacts = new HashMap<>();
        testCity = new City("Test City", testFacts);
        testUserAnswers = new ArrayList<>();
        testUserAnswers.add(new PlayerAnswer("Answer1"));
        testUserAnswers.add(new PlayerAnswer("Answer2"));
        match = new Match(testCity, testUserAnswers);
    }

    @Test
    public void testConstructor() {
        assertNotNull("Match object should be created", match);
    }

    @Test
    public void testGetCorrectAnswer() {
        assertEquals("Correct answer should be 'Test City'", testCity, match.getCorrectAnswer());
    }

    @Test
    public void testSetCorrectAnswer() {
        HashMap<String, Fact> newTestFacts = new HashMap<>();
        City newTestCity = new City("New Test City", newTestFacts);
        match.setCorrectAnswer(newTestCity);
        assertEquals("Correct answer should be updated to 'New Test City'", newTestCity, match.getCorrectAnswer());
    }

    @Test
    public void testGetUserAnswers() {
        assertEquals("User answers should match the provided list", testUserAnswers, match.getUserAnswers());
    }

    @Test
    public void testSetUserAnswers() {
        List<PlayerAnswer> newUserAnswers = new ArrayList<>();
        newUserAnswers.add(new PlayerAnswer("New Answer1"));
        newUserAnswers.add(new PlayerAnswer("New Answer2"));
        match.setUserAnswers(newUserAnswers);
        assertEquals("User answers should be updated", newUserAnswers, match.getUserAnswers());
    }

    @Test
    public void testToString() {
        String expectedString = "Match{Correct Answer =" + testCity + ", User's Answers =" + testUserAnswers + '}';
        assertEquals("toString should return the correct string representation", expectedString, match.toString());
    }
}