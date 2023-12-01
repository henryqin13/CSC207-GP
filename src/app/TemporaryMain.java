package app;

import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import interface_adapter.Guest.GuestController;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.Guest.GuestViewModel;
import interface_adapter.LoggedIn.LoggedInViewModel;
import interface_adapter.Signup.SignupViewModel;
import interface_adapter.ViewManagerModel;
import view.LoggedInView;
import view.LoginView;
import view.SignupView;
import view.ViewManager;
import view.GuestView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TemporaryMain {
    private static GuestController guestController;

    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("Login Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        LoginViewModel loginViewModel = new LoginViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        GuestViewModel guestViewModel = new GuestViewModel();

        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./users.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject, userDataAccessObject, loggedInViewModel);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject);
        views.add(loginView, loginView.viewName);

        LoggedInView loggedInView = new LoggedInView(loggedInViewModel);
        views.add(loggedInView, loggedInView.viewName);

        GuestView guestView = new GuestView(guestViewModel);
        views.add(guestView, guestView.viewName);

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}