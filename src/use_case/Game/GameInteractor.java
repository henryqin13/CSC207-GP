package use_case.Game;

import data_access.OpenAI;
import entity.City;
import use_case.DataAccessInterface;
import use_case.GenerativeInterface;

public class GameInteractor implements GameInputBoundary{

    final GameDataAccessInterface gameDataAccessObject;
    final GameOutputBoundary gamePresenter;
    final GenerativeInterface client;

    public GameInteractor(GameDataAccessInterface gameDataAccessObject,
                          GameOutputBoundary gameOutputBoundary,
                          GenerativeInterface client) {
        this.gameDataAccessObject = gameDataAccessObject;
        this.gamePresenter = gameOutputBoundary;
        this.client = client;
    }

    @Override
    public void executeGame(GameInputData gameInputData) {
        String city = client.getResponse("Give a random city");
        System.out.println(city);
        String facts = client.getResponse("Give a list of random facts about the city.");
        gameDataAccessObject.saveCity(new City(city, null));

        GameOutputData signupOutputData = new GameOutputData("", false);
        gamePresenter.selectHintView(client, gameDataAccessObject);
    }
}
