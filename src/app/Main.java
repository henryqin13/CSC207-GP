package app;

import data_access.*;
import entity.CommonUserFactory;
import interface_adapter.Game.GameController;
import interface_adapter.Game.GameViewModel;
import interface_adapter.Game.GuessViewModel;
import interface_adapter.Game.HintViewModel;
import interface_adapter.Guest.GuestViewModel;
import interface_adapter.LoggedIn.LoggedInController;
import interface_adapter.LoggedIn.LoggedInViewModel;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.MainMenu.MainMenuController;
import interface_adapter.MainMenu.MainMenuViewModel;
import interface_adapter.Signup.SignupViewModel;
import interface_adapter.ViewManagerModel;
import use_case.Game.GameDataAccessInterface;
import use_case.GameSessionManagerUseCase;
import use_case.GameUseCase;
import use_case.Guest.GuestUserDataAccessInterface;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Scanner;


public class Main {

    private static String apiKey; // Replace with your actual API key
    private static final String ENDPOINT = "https://api.openai.com/v1/chat/completions"; // Adjust as needed

    public static void main(String[] args) throws IOException {
        JFrame application = new JFrame("Our Game Demo");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // Initialize ViewManager and ViewModels
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

//        // Initialize other ViewModels (for login, signup, etc.)
//        LoginViewModel loginViewModel = new LoginViewModel();
//        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
//        SignupViewModel signupViewModel = new SignupViewModel();
//        GuestViewModel guestViewModel = new GuestViewModel();
//        MainMenuViewModel mainMenuViewModel = new MainMenuViewModel();


        GameViewModel gameViewModel = new GameViewModel();

//        LoggedInController loggedInController = new LoggedInController(loggedInViewModel, viewManagerModel);
//        MainMenuController mainMenuController = new MainMenuController(viewManagerModel, signupViewModel, loginViewModel,
//                guestViewModel);
//        FileUserDataAccessObject userDataAccessObject;
//        try {
//            userDataAccessObject = new FileUserDataAccessObject("./users.csv", new CommonUserFactory());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        // Load configuration and create API client
        ConfigLoader config = new ConfigLoader();
        String apiKey = config.getApiKey();
        OpenAI api = new OpenAI(ENDPOINT, apiKey); // Assuming OpenAI constructor takes apiKey

//        // Data access object for game
        GameDataAccessInterface data = new GameData();
//
//        //Not yet set up
//        GuestUserDataAccessInterface guestUserDataAccessInterface;
//
//        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject, userDataAccessObject, loggedInViewModel);
//        views.add(signupView, signupView.viewName);
//
//        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject);
//        views.add(loginView, loginView.viewName);
//
//        LoggedInView loggedInView = new LoggedInView(loggedInViewModel, loggedInController);
//        views.add(loggedInView, loggedInView.viewName);
//
//        GuestView guestView = GuestUseCaseFactory.create(viewManagerModel,guestViewModel,userDataAccessObject);
//        views.add(guestView, guestView.viewName);
//
//        MainMenuView mainMenuView = new MainMenuView(mainMenuViewModel, mainMenuController, signupViewModel,
//                loginViewModel, guestViewModel);
//        views.add(mainMenuView, mainMenuView.gameName);

//        GameView gameController = GameUseCaseFactory.create(viewManagerModel, data, api);

        JPanel[] gameViews = GameUseCaseFactory.create(viewManagerModel, data, api);
        for (JPanel gameView : gameViews) {
            views.add(gameView, gameView.getName());
            System.out.println(gameView.getName());
        }


        viewManagerModel.setActiveView("game");
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setSize(400, 400);
        application.setVisible(true);
    }
}