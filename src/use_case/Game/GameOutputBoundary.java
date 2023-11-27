package use_case.Game;

public interface GameOutputBoundary {

    void guessView(GameOutputData guess);

    void hintView(String hint);

    void selectHintView();
}
