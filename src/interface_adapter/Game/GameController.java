package interface_adapter.Game;

import use_case.Game.GameInputBoundary;
import use_case.Game.GameInputData;

public class GameController {
    final GameInputBoundary gameUseCaseInteractor;
    public GameController(GameInputBoundary gameUseCaseInteractor) {
        this.gameUseCaseInteractor = gameUseCaseInteractor;
    }

    public void executeGame() {
        GameInputData data = new GameInputData("", "");
        gameUseCaseInteractor.executeGame(data);
    }
}
