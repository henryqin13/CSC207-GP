package use_case.Guest;

import entity.User;
import interface_adapter.Guest.GuestPresenter;
import use_case.Login.LoginOutputData;

public class GuestInteractor implements GuestInputBoundary {
    final GuestUserDataAccessInterface userDataAccessObject;
    final GuestOutputBoundary guestPresenter;

    public GuestInteractor(GuestUserDataAccessInterface userDataAccessInterface,
                           GuestOutputBoundary guestOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.guestPresenter = guestOutputBoundary;
    }

    @Override
    public void execute(GuestInputData guestInputData) {

        GuestOutputData guestOutputData = new GuestOutputData("Guest", false);
        guestPresenter.prepareSuccessView(guestOutputData);
    }


}
