package use_case.Leaderboard;

import entity.Leaderboard;

public class LeaderboardInteractor implements LeaderboardInputBoundary {
    final LeaderboardDataAccessInterface leaderboardDataAccessObject;
    final LeaderboardOutputData leaderboardOutputData;
    public LeaderboardInteractor(LeaderboardDataAccessInterface leaderboardDataAccessObject, LeaderboardOutputData leaderboardOutputData){
        this.leaderboardDataAccessObject = leaderboardDataAccessObject;
        this.leaderboardOutputData = leaderboardOutputData;
    }
    @Override
    public void execute() {
        Leaderboard leaderboard = leaderboardDataAccessObject.createLeaderboard();
        leaderboardOutputData.setLeaderboard(leaderboard);

    }
}
