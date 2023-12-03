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
        System.out.println(data.getGuess());

        this.gameViewModel.setState(gameState);

        this.gameViewModel.firePropertyChanged(); // Notify observers about the state change
        if (data.getGuess()){
            this.viewManagerModel.setActiveView("game over");
            this.viewManagerModel.firePropertyChanged();
        } else {
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
    public void hintView(GameOutputData data, int hintDiff) {
        GameState gameState = gameViewModel.getState();
        gameState.setHint(data.getHint());
        int updatedScore = gameState.getScore() - hintDiff;
        System.out.println(updatedScore);
        if (updatedScore >= 0) {
            gameState.setScore(updatedScore);
        } else {
//            TODO: end game
        }

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
