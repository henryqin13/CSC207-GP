package interface_adapter.LoggedIn;

import interface_adapter.ViewManagerModel;
import view.SignupView;

public class LoggedInController {
    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;

    public LoggedInController(LoggedInViewModel loggedInViewModel, ViewManagerModel viewManagerModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void logout() {
        LoggedInState currentState = loggedInViewModel.getState();
        loggedInViewModel.firePropertyChanged();
        viewManagerModel.setActiveView("Login Example");
        viewManagerModel.firePropertyChanged();
        currentState.setUsername(null);
        loggedInViewModel.setState(currentState);
    }
}
