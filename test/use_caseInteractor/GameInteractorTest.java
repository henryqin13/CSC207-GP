package use_caseInteractor;

import entity.City;
import entity.User;
import org.junit.Before;
import org.junit.Test;
import use_case.Game.*;
import use_case.GenerativeInterface;

import static org.junit.Assert.*;

public class GameInteractorTest {

    private GameInteractor gameInteractor;
    private StubGameDataAccessInterface gameDataAccessObject;
    private StubGameOutputBoundary gameOutputBoundary;
    private StubGenerativeInterface client;

    @Before
    public void setUp() {
        gameDataAccessObject = new StubGameDataAccessInterface();
        gameOutputBoundary = new StubGameOutputBoundary();
        client = new StubGenerativeInterface();
        gameInteractor = new GameInteractor(gameDataAccessObject, gameOutputBoundary, client);
    }

    @Test
    public void testExecuteGame() {
        gameInteractor.executeGame();
        assertTrue(gameOutputBoundary.isGameStarted());
    }

    @Test
    public void testExecuteHint() {
        GameInputData inputData = new GameInputData("", "2", new City("Toronto", null));
        gameInteractor.executeHint(inputData);
        assertTrue(gameOutputBoundary.isHintDisplayed());
    }

    @Test
    public void testExecuteGuess() {
        GameInputData inputData = new GameInputData("", "", new City("Toronto", null));
        gameInteractor.executeGuess(inputData);
        assertTrue(gameOutputBoundary.isGuessViewDisplayed());
    }

    @Test
    public void testMakeGuessCorrect() {
        GameInputData inputData = new GameInputData("Toronto", "", new City("Toronto", null));
        gameInteractor.makeGuess(inputData);
        assertTrue(gameOutputBoundary.isGuessCorrect());
    }

    @Test
    public void testMakeGuessIncorrect() {
        GameInputData inputData = new GameInputData("Montreal", "", new City("Toronto", null));
        gameInteractor.makeGuess(inputData);
        assertFalse(gameOutputBoundary.isGuessCorrect());
    }

    // Stub classes to replace actual implementations
    public class StubGameDataAccessInterface implements GameDataAccessInterface {

        @Override
        public City getCity() {
            // Return a dummy City object or mock behavior
            return new City("Toronto", null);
        }

        @Override
        public void save(User user) {

        }

        @Override
        public void saveCity(City city) {
            // Implement the saveCity method, e.g., do nothing for a stub
        }
    }



    private static class StubGameOutputBoundary implements GameOutputBoundary {
        private boolean gameStarted = false;
        private boolean hintDisplayed = false;
        private boolean guessViewDisplayed = false;
        private boolean guessCorrect = false;

        @Override
        public void guess(GameOutputData data) {
            guessCorrect = data.getGuess();
        }

        @Override
        public void guessView(GameOutputData data) {
            guessViewDisplayed = true;
        }

        @Override
        public void hintView(GameOutputData data) {
            hintDisplayed = true;
        }

        @Override
        public void gameStart(GameOutputData data) {
            gameStarted = true;
        }

        @Override
        public void exit() {

        }

        @Override
        public void backToMain() {

        }

        public boolean isGameStarted() {
            return gameStarted;
        }

        public boolean isHintDisplayed() {
            return hintDisplayed;
        }

        public boolean isGuessViewDisplayed() {
            return guessViewDisplayed;
        }

        public boolean isGuessCorrect() {
            return guessCorrect;
        }
    }

    private static class StubGenerativeInterface implements GenerativeInterface {
        @Override
        public String getResponse(String prompt) {
            if (prompt.contains("Give a random city")) {
                return "Toronto";
            } else if (prompt.contains("small piece of information")) {
                return "It's located in Ontario, Canada.";
            } else {
                return "Congratulations! Your score is 10.";
            }
        }
    }
}