package app;

import data_access.APIClient;
import data_access.ConfigLoader;
import data_access.OpenAI;
import interface_adapter.ViewManagerModel;
import use_case.GameSessionManagerUseCase;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.net.http.HttpResponse;

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


            String prompt = "give a random city";
            String response = api.getResponse(prompt);
            if (response != null) {
                System.out.println("Response: " + response);
                // Parse the JSON to extract the response text
            }









//        define view manager and first active screen and set it as active
//        ViewManagerModel viewManagerModel = new ViewManagerModel();
//        new ViewManager(views, cardLayout, viewManagerModel);

//        viewManagerModel.setActiveView(label);
//        viewManagerModel.firePropertyChanged();



        application.pack();
        application.setVisible(true);
    }
}