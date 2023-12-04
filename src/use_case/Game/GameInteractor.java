package use_case.Game;

import entity.City;
import interface_adapter.Game.GameState;
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
    public void executeGame() {
        String city = client.getResponse("Give a random city. GIVE ONLY THE NAME OF THE CITY.");

        GameOutputData gameOutputData = new GameOutputData("", false, new City(city, null));
        gamePresenter.gameStart(gameOutputData);
    }

    @Override
    public void executeHint(GameInputData gameInputData) {
        String prompt = "Give a small piece of information about " + gameInputData.getCity().getName() + ", DO NOT IN ANY CIRCUMSTANCE mention the name of the city. do not use any special characters, or slashes, or formatters. On a scale of 1-3 with 1 being the most vague or difficult hint and 3 being the easiest, this hint should be " + gameInputData.getHint().hintDiff;
        if (!gameInputData.getHint().keyword.isEmpty()) {
            prompt += " difficulty. Make it somewhat related to the keyword: " + gameInputData.getHint().keyword;
        }
        String hint = client.getResponse(prompt);
        System.out.println(hint);
        String cleanedString = hint.replaceAll("[^a-zA-Z0-9.,?!'\";:\\- ]", "");
        System.out.println(cleanedString);

        GameOutputData gameOutputData = new GameOutputData(cleanedString, false, gameInputData.getCity());
        gamePresenter.hintView(gameOutputData, Integer.parseInt(gameInputData.getHint().hintDiff));
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
        gameState = new GameState();
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

    @Override
    public void backToHint(){gamePresenter.backToHint();}

}
