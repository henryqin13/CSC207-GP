package interface_adapter;

import entity.City;
import entity.Hint;
import interface_adapter.Game.GameController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import use_case.Game.GameInputBoundary;
import use_case.Game.GameInputData;
import use_case.Game.GameOutputData;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class GameControllerTest {

    private GameInputBoundary gameInputBoundaryMock;
    private GameController gameController;
    private City cityMock;
    private ArgumentCaptor<GameInputData> gameInputDataCaptor;

    @Before
    public void setUp() {
        gameInputBoundaryMock = mock(GameInputBoundary.class);
        gameController = new GameController(gameInputBoundaryMock);
        cityMock = mock(City.class);
        gameInputDataCaptor = ArgumentCaptor.forClass(GameInputData.class);
    }

    @Test
    public void testExecuteGuess() {
        gameController.executeGuess(cityMock);
        verify(gameInputBoundaryMock).executeGuess(gameInputDataCaptor.capture());
        GameInputData capturedData = gameInputDataCaptor.getValue();

        // Assuming there is a method to retrieve the guess from GameInputData
        // assertEquals("", capturedData.getGuess());
        assertEquals(cityMock, capturedData.getCity());
        // Assuming there is a method to retrieve the hint from GameInputData
        // assertEquals(null, capturedData.getHint());
    }

    @Test
    public void testExecuteStart() {
        gameController.executeStart();
        verify(gameInputBoundaryMock).executeGame();
    }

    @Test
    public void testMakeGuess() {
        String guess = "Toronto";
        gameController.makeGuess(guess, cityMock);
        verify(gameInputBoundaryMock).makeGuess(gameInputDataCaptor.capture());
        GameInputData capturedData = gameInputDataCaptor.getValue();

        // Assuming there is a method to retrieve the guess from GameInputData
        // assertEquals(guess, capturedData.getGuess());
        assertEquals(cityMock, capturedData.getCity());
        // Assuming there is a method to retrieve the hint from GameInputData
        // assertEquals(null, capturedData.getHint());
    }

    @Test
    public void testExit() {
        gameController.exit();
        verify(gameInputBoundaryMock).exit();
    }

    @Test
    public void testBackToMain() {
        gameController.backToMain();
        verify(gameInputBoundaryMock).backToMain();
    }

    @Test
    public void testReturnToMain() {
        gameController.returnToMain();
        verify(gameInputBoundaryMock).returnToMain();
    }

    @Test
    public void testBackToHint() {
        gameController.backToHint();
        verify(gameInputBoundaryMock).backToHint();
    }
}