package interface_adapter.Guest;

import interface_adapter.Guest.GuestState;
import interface_adapter.Guest.GuestViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.Signup.SignupState;
import use_case.Guest.GuestOutputBoundary;
import use_case.Guest.GuestOutputData;

public class GuestPresenter implements GuestOutputBoundary {

    private final GuestViewModel guestViewModel;

    private ViewManagerModel viewManagerModel;

    public GuestPresenter(ViewManagerModel viewManagerModel,
                          GuestViewModel guestViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.guestViewModel = guestViewModel;
    }

    @Override
    public void prepareSuccessView(GuestOutputData response) {
        // On success, switch to the logged in view.

        GuestState guestState = guestViewModel.getState();
        this.guestViewModel.setState(guestState);
        this.guestViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(guestViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

}
