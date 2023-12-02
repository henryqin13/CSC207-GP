package use_case.Game;

import entity.City;

public class GameOutputData {

    private final String hint;
    private boolean guess;

    private City city;

    public GameOutputData(String hint, boolean guess, City city) {
        this.hint = hint;
        this.guess = guess;
        this.city = city;
    }

    public String getHint() {
        return hint;
    }

    public boolean getGuess() {return guess;}

    public City getCity() {return city;}
}
