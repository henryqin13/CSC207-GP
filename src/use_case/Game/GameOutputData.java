package use_case.Game;

public class GameOutputData {

    private final String hint;
    private boolean guess;

    public GameOutputData(String hint, boolean guess) {
        this.hint = hint;
        this.guess = guess;
    }

    public String getHint() {
        return hint;
    }

}
