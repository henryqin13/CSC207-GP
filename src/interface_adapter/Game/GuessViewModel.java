package interface_adapter.Game;

import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GuessViewModel extends ViewModel {

    // Labels and button texts for the guess view
    public static final String TITLE_LABEL = "Guess the City";
    public static final String INSTRUCTION_LABEL = "Enter your guess:";
    public static final String SUBMIT_BUTTON_LABEL = "Submit Guess";

    // Game state related to the user's guess
    private GameState state = new GameState();

    // Constructor
    public GuessViewModel() {
        super("guess");
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

    // Method to handle user's guess
    public void submitGuess(String guess) {
        // Here you can implement the logic to handle the user's guess.
        // It could involve updating the GameState, validating the guess, etc.
        state.setGuess(guess);
        // Notify that a guess has been made
        firePropertyChanged();
    }
}
