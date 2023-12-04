package interface_adapter.Leaderboard;

import entity.Leaderboard;

public class LeaderboardState {

    private Leaderboard leaderboard;
    public LeaderboardState(LeaderboardState copy){this.leaderboard = copy.leaderboard;}
    public LeaderboardState(){}
    public Leaderboard getLeaderboard(){return this.leaderboard;}
    public void setLeaderboard(Leaderboard leaderboard){this.leaderboard = leaderboard;}


}
