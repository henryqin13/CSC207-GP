package interface_adapter;

import interface_adapter.Login.LoginController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.Login.LoginInputBoundary;
import use_case.Login.LoginInputData;

import static org.mockito.Mockito.*;

class LoginControllerTest {

    private LoginInputBoundary loginInputBoundaryMock;
    private LoginController loginController;

    @BeforeEach
    void setUp() {
        // Mock the LoginInputBoundary
        loginInputBoundaryMock = mock(LoginInputBoundary.class);
        // Instantiate the LoginController with the mocked LoginInputBoundary
        loginController = new LoginController(loginInputBoundaryMock);
    }

    @Test
    void testExecute() {
        // Given
        String username = "testUser";
        String password = "testPass";

        // When
        loginController.execute(username, password);

        // Then
        // Verify that the execute method of the mocked LoginInputBoundary is called with any instance of LoginInputData
        verify(loginInputBoundaryMock).execute(any(LoginInputData.class));
    }
}