package interface_adapter.Guest;

import use_case.Guest.GuestInputData;
import use_case.Guest.GuestInputBoundary;
import use_case.Signup.SignupInputBoundary;
import use_case.Signup.SignupInputData;

public class GuestController {

    final GuestInputBoundary guestUseCaseInteractor;
    public GuestController(GuestInputBoundary guestUseCaseInteractor) {
        this.guestUseCaseInteractor = guestUseCaseInteractor;
    }


    public void execute() {
        GuestInputData guestInputData = new GuestInputData();

        guestUseCaseInteractor.execute(guestInputData);
    }
}
