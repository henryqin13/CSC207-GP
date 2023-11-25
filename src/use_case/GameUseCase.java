package use_case;

import data_access.OpenAI;
import entity.City;

public class GameUseCase {
    private City currentCity;
    private OpenAI client;

    public GameUseCase(OpenAI client) {
        this.client = client;
    }

    public void startGame() {
        String city = client.getResponse("Give a random city");
        System.out.println(city);
        String facts = client.getResponse("Give a list of random facts about the city.");
        currentCity = new City(city, null);

    }

    public String getHint() {
        return client.getResponse("Give a small piece of information about " + currentCity.getName() + ", do not mention the name of the city in your response. do not use any special characters or formatters.");
    }

    public boolean guessCity(String guess) {
        return currentCity.getName().toLowerCase().contains(guess.toLowerCase());
    }
}
