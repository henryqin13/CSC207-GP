package src.entity;

public class PlayerAnswer {
    private String answer;

    public PlayerAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String toString () {
        return "PlayerAnswer{" + "answer='" + answer + '\'' + '}';
    }
}


