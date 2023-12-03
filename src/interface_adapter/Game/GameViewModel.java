package interface_adapter.Game;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GameViewModel extends ViewModel {

    // Labels and button texts for the game view
    public static final String TITLE_LABEL = "City Guessing Game";
    public static final String GUESS_BUTTON_LABEL = "Guess";
    public static final String HINT_BUTTON_LABEL = "Get Hint";
    public static final String HINT_DIFFICULTY_LABEL = "Hint Difficulty (1-3)";

    public static final String GAME_OVER_LABEL = "This round is over!";

    public static final String START_OVER_LABEL = "Another Game";

    public static final String CORRECT_GUESS_COMMENT_LABEL = ("Congratulations! You're so knowledgeable! " +
            "Do you want another round?");

    public static final String WRONG_GUESS_COMMENT_LABEl = ("Oh no...... what a pity! You were so close!" +
            " Maybe try for another around?");

    public static final String BACK_LABEL = "Back to Main Menu";

    // Game state
    private GameState state = new GameState();

    // Constructor
    public GameViewModel() {
        super("game");
    }

    // Setter for game state
    public void setState(GameState state) {
        this.state = state;
    }

    // Property change support for notifying observers of state changes
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    // Getter for game state
    public GameState getState() {
        return state;
    }
}
