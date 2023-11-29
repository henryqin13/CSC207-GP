package use_case.Signup;

import entity.*;

import java.util.ArrayList;
import java.util.List;


public class SignupInteractor implements SignupInputBoundary {

    final SignupUserDataAccessInterface userDataAccessObject;
    final SignupOutputBoundary signupPresenter;
    final UserFactory userFactory;

    public SignupInteractor(SignupUserDataAccessInterface userDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.signupPresenter = signupOutputBoundary;
        this.userFactory = new CommonUserFactory(); // use a factory to create different types of users
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        String username = signupInputData.getUsername();
        String password = signupInputData.getPassword();
        String repeatPassword = signupInputData.getRepeatPassword();

        if (userDataAccessObject.existsByName(username)) {
            signupPresenter.prepareFailView(username + ": Username already taken.");
        } else if (!password.equals(repeatPassword)) {
            signupPresenter.prepareFailView("Passwords do not match.");
        } else if (!isValidPassword(password)) {
            signupPresenter.prepareFailView("Password is not valid. It must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.");
        } else {
            User user = userFactory.create(username, password); // create a new user with the user factory
            List<Role> roles = new ArrayList<>(); // an empty list of roles
            Player player = new Player(user.getUsername(), user.getPassword(), roles); // create a new player with the user factory and no roles
            userDataAccessObject.save(player); // save the player to the data access object
            SignupOutputData signupOutputData = new SignupOutputData(player.getName(), "Signup successful", false); // create a signup output data with the player name and no errors
            signupPresenter.prepareSuccessView(signupOutputData); // prepare the success view with the signup output data
        }
    }


    // a helper method to check if a password is valid according to some criteria. ccic group can decide for security
    private boolean isValidPassword(String password) {
        return password.length() >= 8 && // at least 8 characters long
                password.matches(".*[A-Z].*") && // at least one uppercase letter
                password.matches(".*[a-z].*") && // at least one lowercase letter
                password.matches(".*\\d.*") && // at least one digit
                password.matches(".*[!@#$%^&*].*"); // at least one special character
    }
}
