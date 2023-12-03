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
}
