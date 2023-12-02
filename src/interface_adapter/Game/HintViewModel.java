package interface_adapter.Game;

import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class HintViewModel extends ViewModel {

    // Labels and button texts for the hint view
    public static final String TITLE_LABEL = "Hint Screen";
    public static final String HINT_TEXT_LABEL = "Hint: ";
    public static final String GUESS_BUTTON_LABEL = "Guess";
    public static final String ANOTHER_HINT_BUTTON_LABEL = "Get Another Hint";

    // Game state related to hint
    private GameState state = new GameState();

    // Constructor
    public HintViewModel() {
        super("hint");
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

    // Get the current hint for display
    public String getCurrentHint() {
        return state.getHint();
    }
}
