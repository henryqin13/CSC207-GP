package interface_adapter.Leaderboard;
import entity.Player;
import interface_adapter.Login.LoginState;
import interface_adapter.ViewModel;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LeaderboardViewModel extends ViewModel {
    public final String TITLE_LABEL = "Leaderboard";
    public static final String EXIT_BUTTON_LABEL = "Exit";
    private LeaderboardState state = new LeaderboardState();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public LeaderboardViewModel() {
        super("Leaderboard");
    }

    public void setState(LeaderboardState state) {
        this.state = state;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public LeaderboardState getState() {
        return this.state;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void initializeUI(JPanel panel) {
        int size = this.state.getLeaderboard().getPlayers().size();
        String[] columnNames = {"Username", "Score"};
        Object[][] list = new Object[size][2];
        for(int i = 0; i<size; i++){
            list[i][0] = this.state.getLeaderboard().getPlayers().get(i).getName();
            list[i][1] = this.state.getLeaderboard().getPlayers().get(i).getScore();

        }
        JTable table = new JTable(list, columnNames);
        panel.add(new JScrollPane(table));
    }
}
