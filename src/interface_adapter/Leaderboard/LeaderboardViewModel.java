package interface_adapter.Leaderboard;
import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LeaderboardViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Leaderboard";
    public static final String EXIT_BUTTON_LABEL = "Exit";
    private LeaderboardState state = new LeaderboardState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public LeaderboardViewModel(){super("Leaderboard");}
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public LeaderboardState getState(){return this.state;}
}
