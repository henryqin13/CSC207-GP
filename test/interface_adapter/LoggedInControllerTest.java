package interface_adapter;

import interface_adapter.LoggedIn.LoggedInController;
import interface_adapter.LoggedIn.LoggedInState;
import interface_adapter.LoggedIn.LoggedInViewModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.beans.PropertyChangeListener;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class LoggedInControllerTest {

    private LoggedInController controller;
    private LoggedInViewModel viewModel;
    private ViewManagerModel viewManagerModel;
    private PropertyChangeListener mockListener;

    @Before
    public void setUp() {
        viewModel = new LoggedInViewModel();
        viewManagerModel = mock(ViewManagerModel.class);
        controller = new LoggedInController(viewModel, viewManagerModel);
        mockListener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(mockListener);
    }

    @Test
    public void testLogout() {
        // Set initial state
        LoggedInState initialState = new LoggedInState();
        initialState.setUsername("testUser");
        viewModel.setState(initialState);

        // Perform logout action
        controller.logout();

        // Capture the state change
        ArgumentCaptor<LoggedInState> stateCaptor = ArgumentCaptor.forClass(LoggedInState.class);
        verify(mockListener).propertyChange(any());
        assertEquals(null, viewModel.getState().getUsername());

        // Verify view change to "the city game"
        verify(viewManagerModel).setActiveView("the city game");
        verify(viewManagerModel).firePropertyChanged();
    }

    @Test
    public void testPlayGame() {
        // Set initial state
        LoggedInState initialState = new LoggedInState();
        initialState.setUsername("testUser");
        viewModel.setState(initialState);

        // Perform play game action
        controller.playgame();

        // Capture the state change
        ArgumentCaptor<LoggedInState> stateCaptor = ArgumentCaptor.forClass(LoggedInState.class);
        verify(mockListener).propertyChange(any());
        assertEquals("testUser", viewModel.getState().getUsername());

        // Verify view change to "game"
        verify(viewManagerModel).setActiveView("game");
        verify(viewManagerModel).firePropertyChanged();
    }
}