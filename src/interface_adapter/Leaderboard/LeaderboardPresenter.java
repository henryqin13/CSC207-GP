package interface_adapter.Leaderboard;

import interface_adapter.ViewManagerModel;
import use_case.Leaderboard.LeaderboardOutputBoundary;
import use_case.Signup.SignupOutputData;

public class LeaderboardPresenter implements LeaderboardOutputBoundary {
    private final LeaderboardViewModel leaderboardViewModel;
    private ViewManagerModel viewManagerModel;
    public LeaderboardPresenter(LeaderboardViewModel leaderboardViewModel, ViewManagerModel viewManagerModel){
        this.leaderboardViewModel = leaderboardViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    @Override
    public void prepareSuccessView(SignupOutputData user) {
        LeaderboardState leaderboardState = leaderboardViewModel.getState();
        leaderboardState.

    }

    @Override
    public void prepareFailView(String error) {

    }
}
