package use_case;

import java.util.List;

public class PlayInquiryModeUseCase {
    private CityRepository cityRepository;

    public PlayInquiryModeUseCase(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public String execute(Player player, String difficultyLevel, List<String> categories) {
        // Get a random city from the repository
        City city = cityRepository.getRandomCity();

        // Create a new game with the selected city, difficulty level, and categories
        Game game = new Game(city, difficultyLevel, categories);

        // While the game is not over, continue to play
        while (!game.isOver()) {
            // Get the next question for the player
            String question = game.getNextQuestion();

            // Ask the player the question and get their answer
            String answer = player.ask(question);

            // Check the player's answer and update the game state
            game.checkAnswer(answer);
        }

        // Return the final score
        return "Your final score is: " + game.getScore();
    }
}
