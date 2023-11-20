package app;

import data_access.APIClient;
import data_access.ConfigLoader;

import javax.swing.*;
import java.awt.*;
import java.net.http.HttpResponse;

public class Main {

    private static String apiKey; // Replace with your actual API key
    private static final String ENDPOINT = "https://api.openai.com/v1/engines/davinci/completions"; // Adjust as needed

    public static void main(String[] args) {

        JFrame application = new JFrame("Example running");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        JLabel label = new JLabel("Hello, World!");
        application.getContentPane().add(label);

        ConfigLoader config = new ConfigLoader();
        apiKey = config.getApiKey();

        APIClient api = new APIClient(ENDPOINT, apiKey);
        String requestBody = "{"
                + "\"prompt\": \"Translate the following English text to French: 'Hello, world!'\","
                + "\"max_tokens\": 60"
                + "}";

        HttpResponse<String> response = api.getData(requestBody);
        System.out.println(response.body());
        System.out.println(response.statusCode());








//        define view manager and first active screen and set it as active
//        ViewManagerModel viewManagerModel = new ViewManagerModel();
//        new ViewManager(views, cardLayout, viewManagerModel);

//        viewManagerModel.setActiveView(label);
//        viewManagerModel.firePropertyChanged();



        application.pack();
        application.setVisible(true);
    }
}