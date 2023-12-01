package interface_adapter;

import interface_adapter.Login.LoginState;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.Signup.SignupState;
import interface_adapter.Signup.SignupViewModel;


public class CancelController {
    private final SignupViewModel signupViewModel;
    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;

    public CancelController(SignupViewModel signupViewModel, ViewManagerModel viewManagerModel) {
        this.signupViewModel = signupViewModel;
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = null;
    }

    public CancelController(LoginViewModel loginViewModel, ViewManagerModel viewManagerModel) {
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = null;
    }


    public void cancelSignUp() {
        SignupState currentState = signupViewModel.getState();
        currentState.setUsername(null);
        signupViewModel.firePropertyChanged();
        viewManagerModel.setActiveView("the city game");
        viewManagerModel.firePropertyChanged();
    }

    public void cancelLogIn() {
        LoginState currentState = loginViewModel.getState();
        currentState.setUsername(null);
        loginViewModel.firePropertyChanged();
        viewManagerModel.setActiveView("the city game");
        viewManagerModel.firePropertyChanged();
    }
}

