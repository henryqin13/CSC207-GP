package app;

import data_access.OpenAI;
import interface_adapter.*;
import interface_adapter.Game.*;
import use_case.DataAccessInterface;
import use_case.Game.GameInputBoundary;
import use_case.Game.GameInteractor;
import use_case.Game.GameOutputBoundary;
import use_case.Game.GameDataAccessInterface;
import use_case.GenerativeInterface;
import view.GameOverView;
import view.GameView;
import view.GuessView;
import view.HintView;
import javax.swing.JPanel;

import java.io.IOException;

public class GameUseCaseFactory {

    private GameUseCaseFactory() {}

    public static JPanel[] create(ViewManagerModel viewManagerModel, GameDataAccessInterface userDataAccessObject, GenerativeInterface client) throws IOException {
        GameViewModel gameViewModel = new GameViewModel();

        GameController gameController = createGameController(viewManagerModel, userDataAccessObject, client, gameViewModel);
        gameController.executeStart();

        GameView gameView = new GameView(gameViewModel, gameController);
        GuessView guessView = new GuessView(gameViewModel, gameController);
        HintView hintView = new HintView(gameViewModel, gameController);
        GameOverView gameOverView = new GameOverView(gameViewModel, gameController);

        // Create an array of JPanel and add the views to it
        JPanel[] views = { gameView, guessView, hintView, gameOverView };

        // Return the array
        return views;
    }


    private static GameController createGameController(ViewManagerModel viewManagerModel, GameDataAccessInterface userDataAccessObject, GenerativeInterface client, GameViewModel gameViewModel) throws IOException {
        // Create the GameOutputBoundary (Presenter)
        GameOutputBoundary gameOutputBoundary = new GamePresenter(viewManagerModel, gameViewModel);

        // Create the GameInputBoundary (Interactor)
        GameInputBoundary gameInteractor = new GameInteractor(userDataAccessObject, gameOutputBoundary, client);

        // Return the GameController
        return new GameController(gameInteractor);
    }
}