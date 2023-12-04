package use_case.Game;


public interface GameOutputBoundary {

    void guess(GameOutputData data);

    void guessView(GameOutputData data);

    void hintView(GameOutputData data, int hint);

    void gameStart(GameOutputData data);

    void exit();

    void backToMain();

    void returnToMain();

    void backToHint();
}
