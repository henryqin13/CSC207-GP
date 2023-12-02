package use_case.Game;

import entity.City;

public class GameInputData {

    final private String guesses;
    final private String hints;

    final private City city;

    public GameInputData(String guesses, String hints, City city) {
        this.guesses = guesses;
        this.hints = hints;
        this.city = city;
    }

    public String getGuesses() {
        return guesses;
    }

    public String getHints() {
        return hints;
    }

    public City getCity() {
        return city;
    }

}
