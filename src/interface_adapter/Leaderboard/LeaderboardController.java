package interface_adapter.Leaderboard;

import interface_adapter.ViewManagerModel;
import use_case.Leaderboard.LeaderboardInputBoundary;
import use_case.Leaderboard.LeaderboardInputData;

public class LeaderboardController {
    final LeaderboardInputBoundary leaderboardUseCaseInteractor;
    final ViewManagerModel viewManagerModel;
    public LeaderboardController(LeaderboardInputBoundary leaderboardUseCaseInteractor, ViewManagerModel viewManagerModel) {
        this.leaderboardUseCaseInteractor = leaderboardUseCaseInteractor;
        this.viewManagerModel = viewManagerModel;
    }


    public void execute() {
        LeaderboardInputData leaderboardInputData = new LeaderboardInputData();

        leaderboardUseCaseInteractor.execute(leaderboardInputData);
    }
    public void exit(String previousView){
        viewManagerModel.setActiveView(previousView);
        viewManagerModel.firePropertyChanged();
    }


}

