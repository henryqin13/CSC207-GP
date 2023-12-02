package use_case.Game;

import interface_adapter.Game.GamePresenter;
import interface_adapter.Game.GameViewModel;
import interface_adapter.ViewManagerModel;
import use_case.GenerativeInterface;

public class GameOverUseCase {

    private final GamePresenter gamePresenter;
    private final GameViewModel gameViewModel;
    private final ViewManagerModel viewManagerModel;
    private final GameDataAccessInterface gameDataAccessObject;
    private final GenerativeInterface client;

    private boolean gameSessionEnded;
    private int currentScore;

    public GameOverUseCase(GamePresenter gamePresenter, GameViewModel gameViewModel, ViewManagerModel viewManagerModel, GameDataAccessInterface gameDataAccessObject, GenerativeInterface client) {
        this.gamePresenter = gamePresenter;
        this.gameViewModel = gameViewModel;
        this.viewManagerModel = viewManagerModel;
        this.gameDataAccessObject = gameDataAccessObject;
        this.client = client;
    }

    // This method is the main logic for the GameOverUseCase
    public void gameOverView() {
        if (!gameSessionEnded) {
            // End the game session (if it's not already ended)
            gameSessionEnded = true;

            // Get the final score
            currentScore = calculateFinalScore();

            // Get the name of the city that was the correct answer
            String city = gameDataAccessObject.getCity().getName();

            // Generate a personalized message for the user based on their score and performance
            String message = client.getResponse("Create a message for the user that congratulates them or encourages them to try again based on their score and performance. The message should mention the city name and the score. The message should be positive, polite, and empathetic. The message should be less than 100 characters.");

            // Display the message, the city name, and the score to the user
//            gamePresenter.showMessage(message);
//            gamePresenter.showCity(city);
//            gamePresenter.showScore(currentScore);
//notimplemented

            // Switch to the main menu view
            viewManagerModel.setActiveView("main menu");
            viewManagerModel.firePropertyChanged();
        }
    }

    private int calculateFinalScore() {
        // Implement your logic for calculating the final score here
        // For example, you might sum up scores from different aspects of the game
        // Replace this with your actual scoring logic
        return 0;
    }
}
