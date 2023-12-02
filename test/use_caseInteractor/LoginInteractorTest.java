package use_caseInteractor;

import entity.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import use_case.Login.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class LoginInteractorTest {

    private LoginUserDataAccessInterface userDataAccessObject;
    private LoginOutputBoundary loginPresenter;
    private LoginInteractor loginInteractor;

    @Before
    public void setUp() {
        userDataAccessObject = mock(LoginUserDataAccessInterface.class);
        loginPresenter = mock(LoginOutputBoundary.class);
        loginInteractor = new LoginInteractor(userDataAccessObject, loginPresenter);
    }

    @Test
    public void testSuccessfulLogin() {
        // Arrange
        String username = "testUser";
        String password = "testPass";
        User mockUser = mock(User.class);
        when(mockUser.getName()).thenReturn(username);
        when(mockUser.getPassword()).thenReturn(password);
        when(userDataAccessObject.existsByName(username)).thenReturn(true);
        when(userDataAccessObject.get(username)).thenReturn(mockUser);

        LoginInputData inputData = new LoginInputData(username, password);

        // Act
        loginInteractor.execute(inputData);

        // Assert
        ArgumentCaptor<LoginOutputData> argument = ArgumentCaptor.forClass(LoginOutputData.class);
        verify(loginPresenter).prepareSuccessView(argument.capture());
        assertEquals(username, argument.getValue().getUsername());
    }

    @Test
    public void testLoginWithIncorrectPassword() {
        // Arrange
        String username = "testUser";
        String correctPassword = "correctPass";
        String wrongPassword = "wrongPass";
        User mockUser = new User() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getPassword() {
                return null;
            }
        };
        when(userDataAccessObject.existsByName(username)).thenReturn(true);
        when(userDataAccessObject.get(username)).thenReturn(mockUser);

        LoginInputData inputData = new LoginInputData(username, wrongPassword);

        // Act
        loginInteractor.execute(inputData);

        // Assert
        verify(loginPresenter).prepareFailView("Incorrect password for " + username + ".");
    }

}