package interface_adapter.MainMenu;

import interface_adapter.Guest.GuestViewModel;
import interface_adapter.Guest.GuestController;
import interface_adapter.LoggedIn.LoggedInState;
import interface_adapter.Signup.SignupViewModel;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.Login.LoginViewModel;
import use_case.Signup.SignupInputData;
import interface_adapter.ViewManagerModel;

public class MainMenuController {
    private final ViewManagerModel viewManagerModel;
    private final SignupViewModel signupViewModel;

    private final LoginViewModel loginViewModel;

    private final GuestViewModel guestViewModel;

    public MainMenuController(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel,
                              LoginViewModel loginViewModel, GuestViewModel guestViewModel){
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
        this.guestViewModel = guestViewModel;
    }
    public void signup() {

        signupViewModel.firePropertyChanged();
        viewManagerModel.setActiveView("sign up");
        viewManagerModel.firePropertyChanged();
    }
    public void login() {

        loginViewModel.firePropertyChanged();
        viewManagerModel.setActiveView("log in");
        viewManagerModel.firePropertyChanged();
    }

    public void guest() {

        signupViewModel.firePropertyChanged();
        viewManagerModel.setActiveView("playing as guest");
        viewManagerModel.firePropertyChanged();
    }
}
