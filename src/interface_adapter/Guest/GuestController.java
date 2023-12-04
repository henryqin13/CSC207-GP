package interface_adapter.Guest;

import interface_adapter.ViewManagerModel;
import use_case.Guest.GuestInputData;
import use_case.Guest.GuestInputBoundary;

public class GuestController {

    final GuestInputBoundary guestUseCaseInteractor;
    final ViewManagerModel viewManagerModel;
    public GuestController(GuestInputBoundary guestUseCaseInteractor, ViewManagerModel viewManagerModel) {
        this.guestUseCaseInteractor = guestUseCaseInteractor;
        this.viewManagerModel = viewManagerModel;
    }


    public void execute() {
        GuestInputData guestInputData = new GuestInputData();

        guestUseCaseInteractor.execute(guestInputData);
    }
    public void exit(){
        viewManagerModel.setActiveView("the city game");
        viewManagerModel.firePropertyChanged();
    }

    public void startGame(){
        viewManagerModel.setActiveView("game");
        viewManagerModel.firePropertyChanged();
    }
}
