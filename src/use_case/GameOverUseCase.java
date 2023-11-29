package use_case;
import entity.Match;
import entity.Player;
import entity.PlayerAnswer;
import interface_adapter.Presenter;

public class GameOver {

    private Match match; // the current match
    private Player player; // the current player
    private Presenter presenter; // the presenter to display the results

    public GameOver(Match match, Player player, Presenter presenter) {
        this.match = match;
        this.player = player;
        this.presenter = presenter;
    }