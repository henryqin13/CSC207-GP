package interface_adapter.Game;

import use_case.Game.GameDataAccessInterface;
import use_case.Game.GameOutputBoundary;
import use_case.Game.GameOutputData;
import interface_adapter.ViewManagerModel;
import use_case.GenerativeInterface;

import java.util.Arrays;

public class GamePresenter implements GameOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final GameViewModel gameViewModel;


    public GamePresenter(ViewManagerModel viewManagerModel,
                         GameViewModel gameViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.gameViewModel = gameViewModel;
    }

    @Override
    public void guess(GameOutputData data) {
        GameState gameState = gameViewModel.getState();
        gameState.setGuessCorrect(data.getGuess());
        System.out.println(data.getGuess());

        this.gameViewModel.setState(gameState);
        this.gameViewModel.firePropertyChanged(); // Notify observers about the state change

        if (!data.getGuess()) {
            this.viewManagerModel.setActiveView("game");
            this.viewManagerModel.firePropertyChanged();
        }

    }

    @Override
    public void guessView(GameOutputData data) {

        this.viewManagerModel.setActiveView("guess");
        this.viewManagerModel.firePropertyChanged();

    }

    @Override
    public void hintView(GameOutputData data) {
        GameState gameState = gameViewModel.getState();
        gameState.setHint(data.getHint());

        this.gameViewModel.setState(gameState);
        this.gameViewModel.firePropertyChanged();

        // Change to the hint view
        this.viewManagerModel.setActiveView("hint");
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void gameStart(GameOutputData data) {

        GameState gameState = gameViewModel.getState();
        gameState.setCity(data.getCity());

        this.gameViewModel.setState(gameState);
        this.gameViewModel.firePropertyChanged();
    }
}
