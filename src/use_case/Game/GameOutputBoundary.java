package use_case.Game;

public interface GameOutputBoundary {

    void prepareSuccessView(GameOutputData user);

    void prepareFailView(String error);
}
