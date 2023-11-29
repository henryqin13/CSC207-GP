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
    public void execute() {
        // check if the player's last answer is correct
        PlayerAnswer lastAnswer = match.getUserAnswers().get(match.getUserAnswers().size() - 1);
        if (lastAnswer.getAnswer().equalsIgnoreCase(match.getCorrectAnswer().getName())) {
            // if correct, update the player's score and show a congratulatory message
            player.setScore(player.getScore() + 10); // added arbitrary # points for a correct answer (group can decide later)
            presenter.displayMessage("You got it right! The city is " + match.getCorrectAnswer().getName() + ".");
        } else {
            // if incorrect, show a sorry message and reveal the correct answer
            presenter.displayMessage("Sorry, that's not correct. The city is " + match.getCorrectAnswer().getName() + ".");
        }
        // show the player's final score and some feedback
        presenter.displayMessage("Your final score is " + player.getScore() + ".");
        presenter.displayFeedback(player.getScore());
        // ask the player if they want to play again or quit
        presenter.displayOptions();
    }
}

//a presenter needs to be implemented