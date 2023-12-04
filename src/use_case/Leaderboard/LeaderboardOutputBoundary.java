package use_case.Leaderboard;

import use_case.Signup.SignupOutputData;

public interface LeaderboardOutputBoundary {
    void prepareSuccessView(LeaderboardOutputData leaderboard);

    void prepareFailView(String error);
}
