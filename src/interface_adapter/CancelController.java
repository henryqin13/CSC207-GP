package interface_adapter;

import interface_adapter.Signup.SignupState;
import interface_adapter.Signup.SignupViewModel;


public class CancelController {
    private final SignupViewModel signupViewModel;
    private final ViewManagerModel viewManagerModel;

    public CancelController(SignupViewModel signupViewModel, ViewManagerModel viewManagerModel) {
        this.signupViewModel = signupViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void cancel() {
        SignupState currentState = signupViewModel.getState();
        currentState.setUsername(null);
        signupViewModel.firePropertyChanged();
        viewManagerModel.setActiveView("sign up");
        viewManagerModel.firePropertyChanged();
    }
}

