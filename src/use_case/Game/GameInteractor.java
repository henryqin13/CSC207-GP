package use_case.Game;

import entity.UserFactory;

import java.time.LocalDateTime;

public class GameInteractor implements GameInputBoundary{

    final GameUserDataAccessInterface userDataAccessObject;
    final GameOutputBoundary userPresenter;
    final UserFactory userFactory;

    public GameInteractor(GameUserDataAccessInterface signupDataAccessInterface,
                            GameOutputBoundary signupOutputBoundary,
                            UserFactory userFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(GameInputData gameInputData) {

    }
}
