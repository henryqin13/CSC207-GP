package interface_adapter;

import entity.User;
import entity.UserFactory;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.Signup.SignupController;
import interface_adapter.Signup.SignupPresenter;
import interface_adapter.Signup.SignupState;
import interface_adapter.Signup.SignupViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_case.Signup.SignupInputBoundary;
import use_case.Signup.SignupInputData;
import use_case.Signup.SignupInteractor;
import use_case.Signup.SignupUserDataAccessInterface;

import static org.mockito.Mockito.*;

class SignupControllerTest {

    private SignupController signupController;
    private SignupInputBoundary signupInputBoundary;
    private SignupPresenter signupPresenter;
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;
    private SignupUserDataAccessInterface userDataAccessInterface;
    private UserFactory userFactory;

    @BeforeEach
    void setUp() {
        userDataAccessInterface = mock(SignupUserDataAccessInterface.class);
        userFactory = mock(UserFactory.class);
        signupViewModel = new SignupViewModel();
        loginViewModel = mock(LoginViewModel.class);
        viewManagerModel = mock(ViewManagerModel.class);
        signupPresenter = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);
        signupInputBoundary = new SignupInteractor(userDataAccessInterface, signupPresenter, userFactory);
        signupController = new SignupController(signupInputBoundary);
    }

    @Test
    void testSignupSuccess() {
        SignupInputBoundary mockInputBoundary = Mockito.mock(SignupInputBoundary.class);

        SignupController signupController = new SignupController(mockInputBoundary);

        String username = "testuser";
        String password1 = "password";
        String password2 = "password";

        signupController.execute(username, password1, password2);

        Mockito.verify(mockInputBoundary).execute(Mockito.any());

    }

    @Test
    void testSignupFailureDueToExistingUser() {
        // Arrange
        String username = "existingUser";
        String password = "password";
        String repeatPassword = "password";
        when(userDataAccessInterface.existsByName(username)).thenReturn(true);

        // Act
        signupController.execute(username, password, repeatPassword);

        // Assert
        verify(userDataAccessInterface, never()).save(any());
        SignupState state = signupViewModel.getState();
        assert state.getUsernameError().equals("User already exists.");
    }

    @Test
    void testSignupFailureDueToPasswordMismatch() {
        // Arrange
        String username = "newUser";
        String password = "password";
        String repeatPassword = "differentPassword";
        when(userDataAccessInterface.existsByName(username)).thenReturn(false);

        // Act
        signupController.execute(username, password, repeatPassword);

        // Assert
        verify(userDataAccessInterface, never()).save(any());
        SignupState state = signupViewModel.getState();
        assert state.getUsernameError().equals("Passwords don't match.");
    }

    @Test
    void testSignupFailureDueToEmptyUsername() {
        // Arrange
        String username = "";
        String password = "password";
        String repeatPassword = "password";

        // Act
        signupController.execute(username, password, repeatPassword);

        // Assert
        verify(userDataAccessInterface, never()).save(any());
        SignupState state = signupViewModel.getState();
        assert state.getUsernameError().equals("Username cannot be empty.");
    }
}