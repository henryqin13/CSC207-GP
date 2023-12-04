package app;

import interface_adapter.Guest.GuestController;
import interface_adapter.Guest.GuestPresenter;
import interface_adapter.Guest.GuestViewModel;
import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_case.Guest.GuestInputBoundary;
import use_case.Guest.GuestInteractor;
import use_case.Guest.GuestOutputBoundary;
import use_case.Guest.GuestUserDataAccessInterface;
import view.GuestView;

import javax.swing.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GuestUseCaseFactoryTest {

    private ViewManagerModel viewManagerModel;
    private GuestViewModel guestViewModel;
    private GuestUserDataAccessInterface userDataAccessObject;
    private GuestView guestView;
    private GuestController guestController;

    @BeforeEach
    public void setUp() {
        viewManagerModel = mock(ViewManagerModel.class);
        guestViewModel = mock(GuestViewModel.class);
        userDataAccessObject = mock(GuestUserDataAccessInterface.class);
        guestView = mock(GuestView.class);
        guestController = mock(GuestController.class);
    }

    @Test
    public void testCreateGuestController() throws IOException {
        // This test ensures that a GuestController is created with the correct dependencies

        // Arrange
        GuestOutputBoundary guestOutputBoundary = new GuestPresenter(viewManagerModel, guestViewModel);
        GuestInputBoundary guestInteractor = new GuestInteractor(userDataAccessObject, guestOutputBoundary);

        // Act
        GuestView result = GuestUseCaseFactory.create(viewManagerModel, guestViewModel, userDataAccessObject);

        // Assert
        assertNotNull(result);
        // We cannot directly test the private method createGuestUseCase, but we can assert that the result is not null
    }
}