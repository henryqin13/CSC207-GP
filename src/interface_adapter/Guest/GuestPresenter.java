package interface_adapter.Guest;

import interface_adapter.ViewManagerModel;
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

        this.guestViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(guestViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

}
