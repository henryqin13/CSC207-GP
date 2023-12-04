package app;

import entity.PlayerFactory;
import interface_adapter.Guest.GuestController;
import interface_adapter.Guest.GuestPresenter;
import interface_adapter.Guest.GuestViewModel;
import interface_adapter.ViewManagerModel;
import use_case.Guest.GuestInputBoundary;
import use_case.Guest.GuestInteractor;
import use_case.Guest.GuestOutputBoundary;
import use_case.Guest.GuestUserDataAccessInterface;
import view.GuestView;

import javax.swing.*;
import java.io.IOException;

public class GuestUseCaseFactory {
    public GuestUseCaseFactory(){}
    public static GuestView create(
            ViewManagerModel viewManagerModel,
            GuestViewModel guestViewModel,
            GuestUserDataAccessInterface userDataAccessObject) {

        try {
            GuestController guestController = createGuestUseCase(viewManagerModel, guestViewModel, userDataAccessObject);
            return new GuestView(guestViewModel, guestController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static GuestController createGuestUseCase(
            ViewManagerModel viewManagerModel,
            GuestViewModel guestViewModel,
            GuestUserDataAccessInterface userDataAccessObject)throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        GuestOutputBoundary guestOutputBoundary = new GuestPresenter(viewManagerModel, guestViewModel);

        GuestInputBoundary guestInteractor = new GuestInteractor(userDataAccessObject, guestOutputBoundary);

        return new GuestController(guestInteractor, viewManagerModel);
    }
}
