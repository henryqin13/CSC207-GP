package use_case.Leaderboard;

import entity.Leaderboard;

public class LeaderboardInteractor implements LeaderboardInputBoundary {
    final LeaderboardDataAccessInterface leaderboardDataAccessObject;
    final LeaderboardOutputData leaderboardOutputData;
    public LeaderboardInteractor(LeaderboardDataAccessInterface leaderboardDataAccessObject){
        this.leaderboardDataAccessObject = leaderboardDataAccessObject;
        this.leaderboardOutputData = new LeaderboardOutputData();
    }
    @Override
    public void execute() {
        Leaderboard leaderboard = leaderboardDataAccessObject.createLeaderboard();
        leaderboardOutputData.setLeaderboard(leaderboard);

    }
    public LeaderboardOutputData getLeaderboardOutputData(){return this.leaderboardOutputData;}
}

