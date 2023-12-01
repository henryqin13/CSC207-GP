package app;

import interface_adapter.Guest.GuestController;
import interface_adapter.Guest.GuestPresenter;
import interface_adapter.Guest.GuestViewModel;
import interface_adapter.LoggedIn.LoggedInViewModel;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.Signup.SignupController;
import interface_adapter.Signup.SignupPresenter;
import interface_adapter.Signup.SignupViewModel;
import use_case.Guest.GuestInputBoundary;
import use_case.Guest.GuestInteractor;
import use_case.Guest.GuestOutputBoundary;
import use_case.Guest.GuestUserDataAccessInterface;
import use_case.Login.LoginInputBoundary;
import use_case.Login.LoginInteractor;
import use_case.Login.LoginUserDataAccessInterface;
import use_case.Signup.SignupUserDataAccessInterface;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.*;
import use_case.Signup.SignupInputBoundary;
import use_case.Signup.SignupInteractor;
import use_case.Signup.SignupOutputBoundary;
import view.SignupView;
import view.GuestView;

import javax.swing.*;
import java.io.IOException;

public class SignupUseCaseFactory {
    private static GuestViewModel guestViewModel = new GuestViewModel();


    /** Prevent instantiation. */
    private SignupUseCaseFactory() {}

    public static SignupView create(
            ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignupViewModel signupViewModel,
            SignupUserDataAccessInterface signupUserDataAccessObject, GuestUserDataAccessInterface guestUserDataAccessObject,
            LoggedInViewModel loggedInViewModel) {

        try {
            SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel, signupUserDataAccessObject);
            GuestController guestController = createUserGuestUseCase(viewManagerModel, guestViewModel, guestUserDataAccessObject);
            return new SignupView(signupController, signupViewModel,guestController, guestViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static SignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel, SignupUserDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);

        UserFactory userFactory = new CommonUserFactory();

        SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        return new SignupController(userSignupInteractor);
    }

    private static GuestController createUserGuestUseCase(ViewManagerModel viewManagerModel,
                                                          GuestViewModel guestViewModel,
                                                          GuestUserDataAccessInterface userDataAccessObject) {
        GuestOutputBoundary guestOutputBoundary = new GuestPresenter(viewManagerModel, guestViewModel);
        GuestInputBoundary guestInteractor = new GuestInteractor(
                userDataAccessObject, guestOutputBoundary);
        return new GuestController(guestInteractor);
    }
}
