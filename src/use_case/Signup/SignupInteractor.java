package use_case.Signup;

import entity.User;
import entity.UserFactory;

import java.time.LocalDateTime;

public class SignupInteractor implements SignupInputBoundary {
    final SignupUserDataAccessInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter;
    final UserFactory userFactory;

    public SignupInteractor(SignupUserDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
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
            User user = userFactory.create(signupInputData.getUsername(), signupInputData.getPassword());
            if (user == null) {
                userPresenter.prepareFailView("User could not be created.");
            } else {
                userDataAccessObject.save(user);
                SignupOutputData signupOutputData = new SignupOutputData(user.getName(), now.toString(), false);
                userPresenter.prepareSuccessView(signupOutputData);
            }
        }
    }
}