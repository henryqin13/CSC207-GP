package interface_adapter.Game;

import entity.City;

public class GameState {

    private String hintDiff;
    private String hint;
    private String guess;
    private boolean guessCorrect;

    private City city;

    private int score = 10;

    // Copy constructor
    public GameState(GameState copy) {
        if (copy != null) {
            this.hintDiff = copy.hintDiff;
            this.hint = copy.hint;
            this.guess = copy.guess;
            this.guessCorrect = copy.guessCorrect;
            this.city = copy.city;
            this.score = copy.score;
        }
    }

    // Default constructor
    public GameState() {
    }

    // Getters
    public String getHintDiff() {
        return hintDiff;
    }

    public String getHint() {
        return hint;
    }

    public String getGuess() {
        return guess;
    }

    public boolean isGuessCorrect() {
        return guessCorrect;
    }

    public City getCity() {return city;}

    public int getScore() {return score;}

    // Setters
    public void setHintDiff(String hintDiff) {
        this.hintDiff = hintDiff;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public void setGuessCorrect(boolean guessCorrect) {
        this.guessCorrect = guessCorrect;
    }

    public void setCity(City city) {this.city = city;}

    public void setScore(int score) {
        if (score >= 0) {
            this.score = score;
        }
    }
}
