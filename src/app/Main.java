package app;

import data_access.APIClient;
import data_access.ConfigLoader;
import data_access.OpenAI;
import interface_adapter.ViewManagerModel;
import use_case.GameSessionManagerUseCase;
import use_case.GameUseCase;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.net.http.HttpResponse;
import java.util.Scanner;


public class Main {

    private static String apiKey; // Replace with your actual API key
    private static final String ENDPOINT = "https://api.openai.com/v1/chat/completions"; // Adjust as needed

    public static void main(String[] args) {

        JFrame application = new JFrame("Example running");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        JLabel label = new JLabel("Hello, World!");
        application.getContentPane().add(label);

//        ViewManagerModel viewManagerModel = new ViewManagerModel();
//        new ViewManager(views, cardLayout, viewManagerModel);

        ConfigLoader config = new ConfigLoader();
        apiKey = config.getApiKey();

//        GameSessionManagerUseCase game = new GameSessionManagerUseCase();


        OpenAI api = new OpenAI(ENDPOINT, apiKey);
        GameUseCase game = new GameUseCase(api);
        game.startGame();

        Scanner scanner = new Scanner(System.in);

        boolean hasGuessedCorrectly = false;
        int numberOfHintsGiven = 0;
        while (!hasGuessedCorrectly) {
            String hint = game.getHint();
            System.out.println("Hint #" + (numberOfHintsGiven + 1) + ": " + hint);

            System.out.print("Guess the city: ");
            String userGuess = scanner.nextLine();
            hasGuessedCorrectly = game.guessCity(userGuess);
            if (hasGuessedCorrectly) {
                System.out.println("Congratulations! You guessed the city correctly!");
            } else {
                System.out.println("That's not correct. Try again!");
                numberOfHintsGiven++;
                // if (numberOfHintsGiven >= MAX_HINTS) break;
            }
        }
        scanner.close();


//        GameView gameView = GameUseCaseFactory.create(viewManagerModel, GameModel, userDataAccessObject);
//        views.add(signupView, signupView.viewName);








//        define view manager and first active screen and set it as active
//        ViewManagerModel viewManagerModel = new ViewManagerModel();
//        new ViewManager(views, cardLayout, viewManagerModel);

//        viewManagerModel.setActiveView(label);
//        viewManagerModel.firePropertyChanged();
    }
}