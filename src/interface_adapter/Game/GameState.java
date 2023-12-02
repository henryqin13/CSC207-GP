package interface_adapter.Game;

public class GameState {

    private String hintDiff;
    private String hint;
    private String guess;
    private boolean guessCorrect;

    // Copy constructor
    public GameState(GameState copy) {
        if (copy != null) {
            this.hintDiff = copy.hintDiff;
            this.hint = copy.hint;
            this.guess = copy.guess;
            this.guessCorrect = copy.guessCorrect;
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
}
