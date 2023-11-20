package src.entity;
import java.util.List;

public class Match {
    private City correctAnswer;
    private List<PlayerAnswer> userAnswers;

    public Match(City correctAnswer, List<PlayerAnswer> userAnswers) {
        this.correctAnswer = correctAnswer;
        this.userAnswers = userAnswers;
    }

    public City getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(City correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<PlayerAnswer> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(List<PlayerAnswer> userAnswers) {
        this.userAnswers = userAnswers;
    }

    public String toString() {
        return "Match{" + "Correct Answer =" + correctAnswer + ", User's Answers =" + userAnswers + '}';
    }
}


