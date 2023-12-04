package app;

import interface_adapter.Game.GameController;
import interface_adapter.Game.GamePresenter;
import interface_adapter.Game.GameViewModel;
import interface_adapter.Leaderboard.LeaderboardController;
import interface_adapter.Leaderboard.LeaderboardPresenter;
import interface_adapter.Leaderboard.LeaderboardViewModel;
import interface_adapter.ViewManagerModel;
import use_case.Game.GameDataAccessInterface;
import use_case.Game.GameInputBoundary;
import use_case.Game.GameInteractor;
import use_case.Game.GameOutputBoundary;
import use_case.GenerativeInterface;
import use_case.Leaderboard.*;
import view.LeaderBoardView;

import java.io.IOException;

public class LeaderboardUseCaseFactory {
    private LeaderboardUseCaseFactory(){}

    public static LeaderboardController createController(ViewManagerModel viewManagerModel, LeaderboardDataAccessInterface userDataAccessObject, LeaderboardViewModel leaderboardViewModel) throws IOException {

        LeaderboardInputBoundary gameInteractor = new LeaderboardInteractor(userDataAccessObject);
        LeaderboardOutputBoundary OutputBoundary = new LeaderboardPresenter(leaderboardViewModel,viewManagerModel, gameInteractor.getLeaderboardOutputData());


        return new LeaderboardController(gameInteractor);
    }
    public static LeaderBoardView createView(LeaderboardViewModel leaderboardViewModel, LeaderboardController leaderboardController){
        return new LeaderBoardView(leaderboardViewModel);
    }

}
