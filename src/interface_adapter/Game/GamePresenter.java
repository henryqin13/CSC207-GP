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
        if (!data.getGuess()) {
            gameState.setScore(gameState.getScore() - 1);
        }
        this.gameViewModel.setState(gameState);
        this.gameViewModel.firePropertyChanged();
        if (gameState.isGuessCorrect() || gameState.getScore() < 0){
            this.viewManagerModel.setActiveView("game over");
        } else {
            this.viewManagerModel.setActiveView("game");
        }
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void guessView(GameOutputData data) {

        this.viewManagerModel.setActiveView("guess");
        this.viewManagerModel.firePropertyChanged();

    }

    @Override
    public void hintView(GameOutputData data, int hintDiff) {
        GameState gameState = gameViewModel.getState();
        gameState.setHint(data.getHint());
        int updatedScore = gameState.getScore() - hintDiff;
        gameState.setScore(updatedScore);

        this.gameViewModel.setState(gameState);
        this.gameViewModel.firePropertyChanged();
        if (gameState.getScore() > 0) {
            this.viewManagerModel.setActiveView("hint");
        } else {
            this.viewManagerModel.setActiveView("game over");
        }
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void gameStart(GameOutputData data) {

        GameState gameState = gameViewModel.getState();
        gameState.setCity(data.getCity());

        this.gameViewModel.setState(gameState);
        this.gameViewModel.firePropertyChanged();
    }

    @Override
    public void exit(){
        this.viewManagerModel.setActiveView("game");
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void backToMain(){ //from after guess screen
        this.viewManagerModel.setActiveView("the city game");
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void returnToMain(){ //from the main game screen
        this.viewManagerModel.setActiveView("the city game");
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void backToHint(){
        this.viewManagerModel.setActiveView("hint");
        this.viewManagerModel.firePropertyChanged();
    }
}
