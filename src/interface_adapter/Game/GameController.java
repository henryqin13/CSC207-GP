package interface_adapter.Game;

import entity.City;
import entity.Hint;
import use_case.Game.GameInputBoundary;
import use_case.Game.GameInputData;

public class GameController {
    final GameInputBoundary gameUseCaseInteractor;
    public GameController(GameInputBoundary gameUseCaseInteractor) {
        this.gameUseCaseInteractor = gameUseCaseInteractor;
    }

    public void executeGuess(City city) {
        GameInputData data = new GameInputData("", null, city);
        gameUseCaseInteractor.executeGuess(data);
    }

    public void executeHint(String hintDiff, City city, String keyword) {
        GameInputData data = new GameInputData("", new Hint(hintDiff, keyword), city);
        gameUseCaseInteractor.executeHint(data);
    }

    public void executeStart() {
        gameUseCaseInteractor.executeGame();
    }

    public void makeGuess(String guess, City city) {
        GameInputData data = new GameInputData(guess, null, city);
        gameUseCaseInteractor.makeGuess(data);
    }

    public void exit(){
        gameUseCaseInteractor.exit();
        gameUseCaseInteractor.executeGame();
    }

    public void backToMain(){gameUseCaseInteractor.backToMain();}

    public void returnToMain (){
        gameUseCaseInteractor.returnToMain();
    }
}
