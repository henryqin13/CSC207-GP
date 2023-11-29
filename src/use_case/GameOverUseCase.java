package use_case;

import entity.Match;
import entity.Player;
import entity.PlayerAnswer;
import interface_adapter.Presenter;

public class GameOverUseCase {

    private Match match; // the current match
    private Player player; // the current player
    private Presenter presenter; // the presenter to display the results

    public GameOverUseCase(Match match, Player player, Presenter presenter) {
        this.match = match;
        this.player = player;
        this.presenter = presenter;
    }

//    public void execute() {
//        // check if the player's last answer is correct
//        boolean isCorrect = match.getCorrectAnswer().getName().equals(player.getUserAnswers().get(player.getUserAnswers().size() - 1).getAnswer());
//        // update the player's score based on the difficulty level and the number of clues used
//        int score = player.getScore();
//        int difficulty = match.getCorrectAnswer().getFacts().get("difficulty").getValue();
//        int clues = player.getUserAnswers().size();
//        if (isCorrect) {
//            score += difficulty * 10 - clues * 2;
//        } else {
//            score -= difficulty * 5 + clues;
//        }
//        player.setScore(score);
//        // display the results to the user using the presenter
//        presenter.displayResults(match, player, isCorrect);
//  }

}
