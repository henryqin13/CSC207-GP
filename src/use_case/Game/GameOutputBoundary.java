package use_case.Game;

import data_access.OpenAI;

public interface GameOutputBoundary {

    void guessView(OpenAI client, GameDataAccessInterface data);

    void hintView(OpenAI client, String hint, GameDataAccessInterface data);

    void selectHintView(OpenAI client, GameDataAccessInterface data);
}
