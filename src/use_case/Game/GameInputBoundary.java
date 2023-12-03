package use_case.Game;

public interface GameInputBoundary {
    void executeGame();

    void executeHint(GameInputData gameInputData);

    void executeGuess(GameInputData gameInputData);

    void makeGuess(GameInputData gameInputData);

    void exit();

    void backToMain();
}
