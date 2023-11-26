package interface_adapter.Game;

import interface_adapter.ViewManagerModel;
import use_case.Game.GameOutputBoundary;
import use_case.Game.GameOutputData;

public class GamePresenter implements GameOutputBoundary {

    private final GameViewModel gameViewModel;
    private ViewManagerModel viewManagerModel;

    public GamePresenter(ViewManagerModel viewManagerModel,
                           GameViewModel gameViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.gameViewModel = gameViewModel;
    }
    @Override
    public void prepareSuccessView(GameOutputData user) {

    }

    @Override
    public void prepareFailView(String error) {

    }
}
