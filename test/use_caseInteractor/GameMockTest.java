package use_caseInteractor;

import entity.City;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import use_case.Game.*;
import use_case.GenerativeInterface;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GameMockTest {

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
        GameInputData inputData = new GameInputData("", "2", testCity);
        String hint = "This is a hint";
        int someIntValue = 0; // Replace with the appropriate int value expected by hintView
        when(client.getResponse(anyString())).thenReturn(hint);
        ArgumentCaptor<GameOutputData> captor = ArgumentCaptor.forClass(GameOutputData.class);

        // Act
        gameInteractor.executeHint(inputData);

        // Assert
        verify(gameOutputBoundary).hintView(captor.capture(), eq(someIntValue));
        assertEquals(hint, captor.getValue().getHint());
    }

    @Test
    public void testExecuteGuess() {
        // Arrange
        City testCity = new City("TestCity", null);
        GameInputData inputData = new GameInputData("", "", testCity);
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
        GameInputData inputData = new GameInputData("testcity", "", testCity);
        ArgumentCaptor<GameOutputData> captor = ArgumentCaptor.forClass(GameOutputData.class);

        // Act
        gameInteractor.makeGuess(inputData);

        // Assert
        verify(gameOutputBoundary).guess(captor.capture());
        assertTrue(captor.getValue().getGuess());
    }

    @Test
    public void testMakeGuessIncorrect() {
        // Arrange
        City testCity = new City("TestCity", null);
        GameInputData inputData = new GameInputData("wrongguess", "", testCity);
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
}
