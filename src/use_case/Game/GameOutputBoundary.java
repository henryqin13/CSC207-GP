package use_case.Game;

import data_access.OpenAI;
import use_case.DataAccessInterface;
import use_case.GenerativeInterface;

public interface GameOutputBoundary {

    void guessView(GenerativeInterface client, GameDataAccessInterface data);

    void hintView(GenerativeInterface client, String hint, GameDataAccessInterface data);

    void selectHintView(GenerativeInterface client, GameDataAccessInterface data);
}
