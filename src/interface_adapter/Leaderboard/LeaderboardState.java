package interface_adapter.Leaderboard;

import entity.Leaderboard;

public class LeaderboardState {

    private Leaderboard leaderboard;
    public LeaderboardState(){
        this.leaderboard = new Leaderboard();
    }
    public Leaderboard getLeaderboard(){return this.leaderboard;}
    public void setLeaderboard(Leaderboard leaderboard){this.leaderboard = leaderboard;}


}
