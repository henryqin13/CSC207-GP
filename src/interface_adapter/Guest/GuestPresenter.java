package interface_adapter.Guest;

import interface_adapter.LoggedIn.LoggedInState;
import interface_adapter.LoggedIn.LoggedInViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.Signup.SignupState;
import use_case.Guest.GuestOutputBoundary;
import use_case.Guest.GuestOutputData;
import use_case.Guest.GuestOutputBoundary;
import use_case.Guest.GuestOutputData;

public class GuestPresenter implements GuestOutputBoundary {

    private final GuestViewModel guestViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private ViewManagerModel viewManagerModel;

    public GuestPresenter(ViewManagerModel viewManagerModel,
                          LoggedInViewModel loggedInViewModel,
                          GuestViewModel guestViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.guestViewModel = guestViewModel;
    }

    @Override
    public void prepareSuccessView(GuestOutputData response) {
        // On success, switch to the logged in view.

        LoggedInState loggedInState = loggedInViewModel.getState();
        this.loggedInViewModel.setState(loggedInState);
        this.loggedInViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

}
