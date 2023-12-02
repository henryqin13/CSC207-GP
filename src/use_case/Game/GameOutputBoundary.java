package use_case.Game;

import use_case.GenerativeInterface;

public interface GameOutputBoundary {

    void guess(GameOutputData data);

    void guessView(GameOutputData data);

    void hintView(GameOutputData data);

    void gameStart(GameOutputData data);
}
