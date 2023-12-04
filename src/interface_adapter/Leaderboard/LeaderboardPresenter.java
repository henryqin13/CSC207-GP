package interface_adapter.Leaderboard;

import interface_adapter.ViewManagerModel;
import use_case.Leaderboard.LeaderboardOutputBoundary;
import use_case.Leaderboard.LeaderboardOutputData;
import use_case.Signup.SignupOutputData;

public class LeaderboardPresenter implements LeaderboardOutputBoundary {
    private final LeaderboardViewModel leaderboardViewModel;
    private ViewManagerModel viewManagerModel;

    private final LeaderboardOutputData leaderboardOutputData;
    public LeaderboardPresenter(LeaderboardViewModel leaderboardViewModel, ViewManagerModel viewManagerModel, LeaderboardOutputData leaderboardOutputData){
        this.leaderboardViewModel = leaderboardViewModel;
        this.viewManagerModel = viewManagerModel;
        this.leaderboardOutputData = leaderboardOutputData;
    }

    public void prepareSuccessView(LeaderboardOutputData leaderboard) {
        LeaderboardState leaderboardState = leaderboardViewModel.getState();
        leaderboardState.setLeaderboard(leaderboard.getLeaderboard());
        this.leaderboardViewModel.setState(leaderboardState);
        this.leaderboardViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(leaderboardViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {

    }
}
