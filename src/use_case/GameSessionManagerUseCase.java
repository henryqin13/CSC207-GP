package use_case;

public class GameSessionManagerUseCase {
    private boolean gameInProgress;
    private int currentScore;
    private int numberOfAttempts;
    private final int maxAttempts;
    private int hintsAvailable;

    public GameSessionManagerUseCase() {
        gameInProgress = false;
        currentScore = 0;
        numberOfAttempts = 0;
        maxAttempts = 3; // have this for now, but can change later depending on how many we decide
        hintsAvailable = 2; // have this for now, but can change later depending on how many we decide
    }

    public void startGameSession() {
        if (!gameInProgress) {
            gameInProgress = true;
            numberOfAttempts = 0;
            currentScore = 0;
            System.out.println("Game session started.");
        } else {
            System.out.println("Game session is already in progress");
        }
    }

    public void pauseGameSession() {
        if (gameInProgress) {
            gameInProgress = false;
            System.out.println("Game session paused");
        } else {
            System.out.println("No active game session");
        }
    }

    public void resumeGameSession() {
        if (!gameInProgress) {
            gameInProgress = true;
            System.out.println("Game session resumed");
        } else {
            System.out.println("No active game session");
        }
    }

    public void endGameSession() {
        if (gameInProgress) {
            gameInProgress = false;
            System.out.println("Game session ended. Final Score: " + currentScore);
        } else {
            System.out.println("No active game session");
        }
    }

    public void increaseScore(int points) {
        currentScore += points;
    }

    public void usedHint() {
        if (hintsAvailable > 0) {
            hintsAvailable--;
            System.out.println("Hint used. Remaining Hints: " + hintsAvailable);
        } else {
            System.out.println("No hints available");
        }
    }

    public void increaseAttempts() {
        numberOfAttempts++;
        if (numberOfAttempts >= maxAttempts) {
            endGameSession();
        }
    }

    public void handleCorrectGuess() {
        if (gameInProgress) {
            System.out.println("Congratulations! You guessed correctly!");
        } else {
            System.out.println("No active game session");
        }
    }

    public boolean isGameInProgress() {
        return gameInProgress;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public int getNumberOfAttempts() {
        return numberOfAttempts;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public int getHintsAvailable() {
        return hintsAvailable;
    }

}