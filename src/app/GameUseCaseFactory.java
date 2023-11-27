package app;

import data_access.OpenAI;
import interface_adapter.*;
import interface_adapter.Game.GameController;
import interface_adapter.Game.GamePresenter;
import interface_adapter.Game.GameViewModel;
import use_case.Game.GameInputBoundary;
import use_case.Game.GameInteractor;
import use_case.Game.GameOutputBoundary;
import use_case.Game.GameDataAccessInterface;

import java.io.IOException;

public class GameUseCaseFactory {

    /** Prevent instantiation. */
    private GameUseCaseFactory() {}

//    public static SignupView create(
//            ViewManagerModel viewManagerModel,
//            LoginViewModel loginViewModel,
//            SignupViewModel signupViewModel,
//            SignupUserDataAccessInterface userDataAccessObject,
//            ClearController clearController) {
//
//        try {
//            SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel, userDataAccessObject);
//            return new SignupView(signupController, clearController, signupViewModel); // Add clearController here
//        } catch (IOException e) {
//            JOptionPane.showMessageDialog(null, "Could not open user data file.");
//        }
//
//        return null;
//    }

//    public static GameView create(
//            ViewManagerModel viewManagerModel,
//            GameViewModel signupViewModel,
//            GameDataAccessInterface userDataAccessObject) {
//
//        try {
//            GameController gameController = ;
//            return new GameView();
//        } catch (IOException e) {
//            JOptionPane.showMessageDialog(null, "Could not open user data file.");
//        }
//
//        return null;
//    }

    public static GameController createGameUseCase(ViewManagerModel viewManagerModel, GameDataAccessInterface userDataAccessObject, OpenAI client) throws IOException {

        GameOutputBoundary gameOutputBoundary = new GamePresenter(viewManagerModel, null);



        GameInputBoundary gameInteractor = new GameInteractor(
                userDataAccessObject, gameOutputBoundary, client);

        return new GameController(gameInteractor);
    }
}
