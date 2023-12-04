package interface_adapter.Leaderboard;

import entity.Leaderboard;
import interface_adapter.ViewManagerModel;
import use_case.Leaderboard.LeaderboardInputBoundary;
import use_case.Leaderboard.LeaderboardInputData;

public class LeaderboardController {
    final LeaderboardInputBoundary leaderboardUseCaseInteractor;

    public LeaderboardController(LeaderboardInputBoundary leaderboardUseCaseInteractor) {
        this.leaderboardUseCaseInteractor = leaderboardUseCaseInteractor;
    }

    public void execute() {
        leaderboardUseCaseInteractor.execute();
    }
}
