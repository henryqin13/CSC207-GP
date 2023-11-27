package use_case.Game;

public class GameInputData {

    final private String guesses;
    final private String hints;

    public GameInputData(String guesses, String hints) {
        this.guesses = guesses;
        this.hints = hints;
    }

    public String getGuesses() {
        return guesses;
    }

    public String getHints() {
        return hints;
    }

}
