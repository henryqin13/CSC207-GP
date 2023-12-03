package use_case.Game;

import entity.City;
import entity.Hint;

public class GameInputData {

    final private String guesses;
    final private Hint hint;

    final private City city;

    public GameInputData(String guesses, Hint hint, City city) {
        this.guesses = guesses;
        this.hint = hint;
        this.city = city;
    }

    public String getGuesses() {
        return guesses;
    }

    public Hint getHint() {
        return hint;
    }

    public City getCity() {
        return city;
    }

}
