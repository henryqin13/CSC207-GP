package use_case.Guest;

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
