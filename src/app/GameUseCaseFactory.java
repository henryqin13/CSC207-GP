package app;

import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.*;
import interface_adapter.Game.GameController;
import interface_adapter.Game.GamePresenter;
import interface_adapter.Game.GameViewModel;
import use_case.Game.GameInputBoundary;
import use_case.Game.GameInteractor;
import use_case.Game.GameOutputBoundary;
import use_case.Game.GameUserDataAccessInterface;

import javax.swing.*;
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

    private static GameController createGameUseCase(ViewManagerModel viewManagerModel) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
//        GameOutputBoundary signupOutputBoundary = new GamePresenter(viewManagerModel);

//        UserFactory userFactory = new CommonUserFactory();

//        GameOutputBoundary gameInteractor = new GameInteractor(signupOutputBoundary);

        return new GameController(null);
    }

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

    private static GameController createUserSignupUseCase(ViewManagerModel viewManagerModel, GameViewModel gameViewModel, GameUserDataAccessInterface userDataAccessObject) throws IOException {

        GameOutputBoundary gameOutputBoundary = new GamePresenter(viewManagerModel, gameViewModel);

        UserFactory userFactory = new CommonUserFactory();

        GameInputBoundary gameInteractor = new GameInteractor(
                userDataAccessObject, gameOutputBoundary, userFactory);

        return new GameController(gameInteractor);
    }
}
