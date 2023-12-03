package use_case.Game;

import entity.City;
import interface_adapter.Game.GameState;
import use_case.GenerativeInterface;

public class GameInteractor implements GameInputBoundary{

    final GameDataAccessInterface gameDataAccessObject;
    final GameOutputBoundary gamePresenter;
    final GenerativeInterface client;
    private GameState gameState;

    public GameInteractor(GameDataAccessInterface gameDataAccessObject,
                          GameOutputBoundary gameOutputBoundary,
                          GenerativeInterface client) {
        this.gameDataAccessObject = gameDataAccessObject;
        this.gamePresenter = gameOutputBoundary;
        this.client = client;
    }

    @Override
    public void executeGame() {
        String city = client.getResponse("Give a random city");
        System.out.println(city);
        gameState = new GameState();

        GameOutputData gameOutputData = new GameOutputData("", false, new City(city, null));
        gamePresenter.gameStart(gameOutputData);
    }

    @Override
    public void executeHint(GameInputData gameInputData) {
        String hint = client.getResponse("Give a small piece of information about " + gameInputData.getCity().getName() + ", do not mention the name of the city in your response. do not use any special characters or formatters. On a scale of 1-3 with 3 being the most vague or difficult hint and 1 being the easiest, this hint should be " + gameInputData.getHints() + " difficulty.");
        System.out.println(hint);

        GameOutputData gameOutputData = new GameOutputData(hint, false, gameInputData.getCity());
        gamePresenter.hintView(gameOutputData, Integer.parseInt(gameInputData.getHints()));
    }

    @Override
    public void executeGuess(GameInputData gameInputData) {
        GameOutputData gameOutputData = new GameOutputData("", false, gameInputData.getCity());

        gamePresenter.guessView(gameOutputData);
    }

    @Override
    public void makeGuess(GameInputData gameInputData) {
        boolean correct = gameInputData.getCity().getName().toLowerCase().contains(gameInputData.getGuesses().toLowerCase());

        GameOutputData gameOutputData = new GameOutputData("", correct, gameInputData.getCity());
        gamePresenter.guess(gameOutputData);
    }

    @Override
    public void exit(){
        gamePresenter.exit();
    }

    @Override
    public void backToMain(){
        gamePresenter.backToMain();
    }

    @Override
    public void returnToMain(){
        gamePresenter.returnToMain();
    }

}
