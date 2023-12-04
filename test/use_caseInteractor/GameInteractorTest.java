package use_caseInteractor;

import entity.City;
import entity.Hint;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import use_case.Game.*;
import use_case.GenerativeInterface;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GameInteractorTest {

    private GameDataAccessInterface gameDataAccessObject;
    private GameOutputBoundary gameOutputBoundary;
    private GenerativeInterface client;
    private GameInteractor gameInteractor;

    @Before
    public void setUp() {
        gameDataAccessObject = mock(GameDataAccessInterface.class);
        gameOutputBoundary = mock(GameOutputBoundary.class);
        client = mock(GenerativeInterface.class);
        gameInteractor = new GameInteractor(gameDataAccessObject, gameOutputBoundary, client);
    }

    @Test
    public void testExecuteGame() {
        // Arrange
        String cityName = "TestCity";
        when(client.getResponse(anyString())).thenReturn(cityName);
        ArgumentCaptor<GameOutputData> captor = ArgumentCaptor.forClass(GameOutputData.class);

        // Act
        gameInteractor.executeGame();

        // Assert
        verify(gameOutputBoundary).gameStart(captor.capture());
        assertEquals(cityName, captor.getValue().getCity().getName());
    }

    @Test
    public void testExecuteHint() {

        // Arrange
        City testCity = new City("TestCity", null);
        // Assuming the difficulty level is a String where '1' corresponds to 'easy'
        Hint testHint = new Hint("1", "landmark"); // Changed 1 to "1"
        GameInputData inputData = new GameInputData("", testHint, testCity);
        String hintResponse = "This is a hint";
        when(client.getResponse(anyString())).thenReturn(hintResponse);
        ArgumentCaptor<GameOutputData> captor = ArgumentCaptor.forClass(GameOutputData.class);
        ArgumentCaptor<Integer> intCaptor = ArgumentCaptor.forClass(Integer.class);

        // Act
        gameInteractor.executeHint(inputData);

        // Assert
        verify(gameOutputBoundary).hintView(captor.capture(), intCaptor.capture());
        assertEquals(hintResponse, captor.getValue().getHint());
        // Assuming the difficulty level '1' corresponds to the integer 1
        assertEquals(Integer.valueOf(1), intCaptor.getValue());
    }

    @Test
    public void testExecuteGuess() {
        // Arrange
        City testCity = new City("TestCity", null);
        GameInputData inputData = new GameInputData("", null, testCity); // Pass null for the hint
        ArgumentCaptor<GameOutputData> captor = ArgumentCaptor.forClass(GameOutputData.class);

        // Act
        gameInteractor.executeGuess(inputData);

        // Assert
        verify(gameOutputBoundary).guessView(captor.capture());
        assertEquals(testCity, captor.getValue().getCity());
    }

    @Test
    public void testMakeGuessCorrect() {

        // Arrange
        City testCity = new City("TestCity", null);
        GameInputData inputData = new GameInputData("testcity", null, testCity); // Pass null for the Hint
        ArgumentCaptor<GameOutputData> captor = ArgumentCaptor.forClass(GameOutputData.class);

        // Act
        gameInteractor.makeGuess(inputData);

        // Assert
        verify(gameOutputBoundary).guess(captor.capture());
        assertTrue(captor.getValue().getGuess());
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
        public void hintView(GameOutputData data, int hint) {
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

        @Override
        public void returnToMain() {

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
=======
        // Arrange
        City testCity = new City("TestCity", null);
        GameInputData inputData = new GameInputData("wrongguess", null, testCity); // Pass null for the Hint
        ArgumentCaptor<GameOutputData> captor = ArgumentCaptor.forClass(GameOutputData.class);

        // Act
        gameInteractor.makeGuess(inputData);

        // Assert
        verify(gameOutputBoundary).guess(captor.capture());
        assertFalse(captor.getValue().getGuess());
    }

    @Test
    public void testExit() {
        // Act
        gameInteractor.exit();

        // Assert
        verify(gameOutputBoundary).exit();
    }

    @Test
    public void testBackToMain() {
        // Act
        gameInteractor.backToMain();

        // Assert
        verify(gameOutputBoundary).backToMain();
    }

    @Test
    public void returnToMain() {
        // Act
        gameInteractor.returnToMain();

        // Assert
        verify(gameOutputBoundary).returnToMain();
    }
}


