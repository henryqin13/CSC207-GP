package interface_adapter.Game;

import entity.City;
import use_case.Game.GameInputBoundary;
import use_case.Game.GameInputData;

public class GameController {
    final GameInputBoundary gameUseCaseInteractor;
    public GameController(GameInputBoundary gameUseCaseInteractor) {
        this.gameUseCaseInteractor = gameUseCaseInteractor;
    }

    public void executeGuess(City city) {
        GameInputData data = new GameInputData("", "", city);
        gameUseCaseInteractor.executeGuess(data);
    }

    public void executeHint(String hintDiff, City city) {
        GameInputData data = new GameInputData("", hintDiff, city);
        System.out.println("Hint " + city.getName());
        gameUseCaseInteractor.executeHint(data);
    }

    public void executeStart() {
        gameUseCaseInteractor.executeGame();
    }

    public void makeGuess(String guess, City city) {
        GameInputData data = new GameInputData(guess, "", city);
        gameUseCaseInteractor.makeGuess(data);
    }

    public void exit(){
        gameUseCaseInteractor.exit();
    }

    public void backToMain(){gameUseCaseInteractor.backToMain();}
}
