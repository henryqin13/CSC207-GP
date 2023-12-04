package interface_adapter;

import interface_adapter.Guest.GuestController;
import interface_adapter.Guest.GuestPresenter;
import interface_adapter.Guest.GuestState;
import interface_adapter.Guest.GuestViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_case.Guest.GuestInputBoundary;
import use_case.Guest.GuestOutputData;

import java.beans.PropertyChangeListener;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

class GuestControllerTest {

    private GuestController guestController;
    private GuestPresenter guestPresenter;
    private GuestViewModel guestViewModel;
    private ViewManagerModel viewManagerModel;
    private GuestInputBoundary guestInputBoundary;
    private PropertyChangeListener propertyChangeListener;

    @BeforeEach
    void setUp() {
        viewManagerModel = mock(ViewManagerModel.class);
        guestInputBoundary = mock(GuestInputBoundary.class);
        guestViewModel = new GuestViewModel();
        guestPresenter = new GuestPresenter(viewManagerModel, guestViewModel);
        guestController = new GuestController(guestInputBoundary, viewManagerModel);
        propertyChangeListener = mock(PropertyChangeListener.class);
        guestViewModel.addPropertyChangeListener(propertyChangeListener);
    }

    @Test
    void testExecute() {
        guestController.execute();
        verify(guestInputBoundary).execute(any());
    }

    @Test
    void testExit() {
        guestController.exit();
        verify(viewManagerModel).setActiveView("the city game");
        verify(viewManagerModel).firePropertyChanged();
    }

    @Test
    void testStartGame() {
        guestController.startGame();
        verify(viewManagerModel).setActiveView("game");
        verify(viewManagerModel).firePropertyChanged();
    }

    @Test
    void testPrepareSuccessView() {
        // Assuming "Guest" is the name of the guest and false indicates the use case did not fail
        GuestOutputData guestOutputData = new GuestOutputData("Guest", false);
        guestPresenter.prepareSuccessView(guestOutputData);

        ArgumentCaptor<GuestState> stateCaptor = ArgumentCaptor.forClass(GuestState.class);
        verify(propertyChangeListener).propertyChange(any());
        verify(viewManagerModel).setActiveView(anyString());
        verify(viewManagerModel).firePropertyChanged();

        // Capture the state that was set on the view model
        verify(guestViewModel).setState(stateCaptor.capture());
        GuestState capturedState = stateCaptor.getValue();

        // Assert that the state was set correctly
        // Replace getGuestName with the correct method to retrieve the guest name
        // If the guest name is stored in the guestUser field of GuestViewModel, use that
        assertEquals("Guest", guestViewModel.getGuestUser());
    }
}