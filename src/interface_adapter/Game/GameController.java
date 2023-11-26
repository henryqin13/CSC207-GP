package interface_adapter.Game;

import use_case.Game.GameInputBoundary;
import use_case.Game.GameInputData;

public class GameController {
    final GameInputBoundary userSignupUseCaseInteractor;
    public GameController(GameInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    public void execute(String username, String password1, String password2) {
        GameInputData signupInputData = new GameInputData(
                username, password1, password2);

        userSignupUseCaseInteractor.execute(signupInputData);
    }
}
