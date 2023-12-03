package use_case.Leaderboard;

import use_case.Signup.SignupOutputData;

public interface LeaderboardOutputBoundary {
    void prepareSuccessView(SignupOutputData user);

    void prepareFailView(String error);
}
