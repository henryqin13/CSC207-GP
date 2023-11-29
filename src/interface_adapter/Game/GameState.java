package interface_adapter.Game;

public class GameState {

    private String hintDiff;

    private String hint;

    private String guess;

    private boolean guessCorrect;

    public GameState(GameState copy) {
    }

    public GameState() {

    }

    public String getHintDiff() {
        return hintDiff;
    }

    public String getHint() {
        return hint;
    }

    public String getGuess() {
        return guess;
    }

    public boolean getGuessCorrect() {
        return guessCorrect;
    }


    public void setHintDiff(String hintDiff) {
        this.hintDiff = hintDiff;
    }

}
