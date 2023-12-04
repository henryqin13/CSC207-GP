package use_caseInteractor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import use_case.Guest.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GuestInteractorTest {

    private GuestUserDataAccessInterface userDataAccessObject;
    private GuestOutputBoundary guestOutputBoundary;
    private GuestInteractor guestInteractor;

    @Before
    public void setUp() {
        // Mock the dependencies
        userDataAccessObject = mock(GuestUserDataAccessInterface.class);
        guestOutputBoundary = mock(GuestOutputBoundary.class);

        // Initialize the GuestInteractor with the mocked dependencies
        guestInteractor = new GuestInteractor(userDataAccessObject, guestOutputBoundary);
    }


    @Test
    public void testExecute() {
        // Arrange
        GuestInputData inputData = new GuestInputData();
        ArgumentCaptor<GuestOutputData> captor = ArgumentCaptor.forClass(GuestOutputData.class);

        // Act
        guestInteractor.execute(inputData);

        // Assert
        verify(guestOutputBoundary).prepareSuccessView(captor.capture());
        GuestOutputData outputData = captor.getValue();

        // Since there are no getters in GuestOutputData, we cannot directly assert its state.
        // We need to rely on the behavior of the GuestInteractor and the interactions with its dependencies.
        // If the GuestInteractor is supposed to change the state of the system or call other methods,
        // those interactions should be verified here.
    }
}
