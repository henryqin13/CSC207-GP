package use_case.Signup;

import entity.Player;
import entity.PlayerFactory;

import java.time.LocalDateTime;

public class SignupInteractor implements SignupInputBoundary {
    final SignupUserDataAccessInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter;
    final PlayerFactory playerFactory;

    public SignupInteractor(SignupUserDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            PlayerFactory playerFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.playerFactory = playerFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        if (signupInputData.getUsername() == null || signupInputData.getUsername().trim().isEmpty()) {
            userPresenter.prepareFailView("Username cannot be empty.");
        } else if (userDataAccessObject.existsByName(signupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists.");
        } else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        } else {
            LocalDateTime now = LocalDateTime.now();
            Player player = playerFactory.create(signupInputData.getUsername(), signupInputData.getPassword());
            if (player == null) {
                userPresenter.prepareFailView("User could not be created.");
            } else {
                userDataAccessObject.save(player);
                SignupOutputData signupOutputData = new SignupOutputData(player.getName(), now.toString(), false);
                userPresenter.prepareSuccessView(signupOutputData);
            }
        }
    }
}