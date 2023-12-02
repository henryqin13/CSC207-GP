package use_caseInteractor;

import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_case.Signup.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SignupInteractorTest {

    private SignupInteractor signupInteractor;
    private SignupUserDataAccessInterface userDataAccess;
    private SignupOutputBoundary outputBoundary;
    private UserFactory userFactory;

    @BeforeEach
    void setUp() {
        userDataAccess = mock(SignupUserDataAccessInterface.class);
        outputBoundary = mock(SignupOutputBoundary.class);
        userFactory = mock(UserFactory.class);
        signupInteractor = new SignupInteractor(userDataAccess, outputBoundary, userFactory);
    }

    @Test
    void execute_withNewUser_shouldCreateUser() {
        // Arrange
        String username = "newUser";
        String password = "password";
        SignupInputData inputData = new SignupInputData(username, password, password);
        User mockUser = mock(User.class);
        when(mockUser.getName()).thenReturn(username); // Ensure the mock User returns the expected username
        when(userDataAccess.existsByName(username)).thenReturn(false);
        when(userFactory.create(username, password)).thenReturn(mockUser);

        // Act
        signupInteractor.execute(inputData);

        // Assert
        verify(userDataAccess).save(any(User.class));
        ArgumentCaptor<SignupOutputData> argument = ArgumentCaptor.forClass(SignupOutputData.class);
        verify(outputBoundary).prepareSuccessView(argument.capture());
        assertEquals(username, argument.getValue().getUsername());
    }

    @Test
    void execute_withExistingUser_shouldNotCreateUser() {
        // Arrange
        String username = "existingUser";
        String password = "password";
        SignupInputData inputData = new SignupInputData(username, password, password);
        when(userDataAccess.existsByName(username)).thenReturn(true);

        // Act
        signupInteractor.execute(inputData);

        // Assert
        verify(userDataAccess, never()).save(any(User.class));
        verify(outputBoundary).prepareFailView("User already exists.");
    }

    @Test
    void execute_withNonMatchingPasswords_shouldNotCreateUser() {
        // Arrange
        String username = "newUser";
        String password = "password";
        String repeatPassword = "differentPassword";
        SignupInputData inputData = new SignupInputData(username, password, repeatPassword);
        when(userDataAccess.existsByName(username)).thenReturn(false);

        // Act
        signupInteractor.execute(inputData);

        // Assert
        verify(userDataAccess, never()).save(any(User.class));
        verify(outputBoundary).prepareFailView("Passwords don't match.");
    }

    @Test
    void execute_withEmptyUsername_shouldNotCreateUser() {
        // Arrange
        String username = "";
        String password = "password";
        SignupInputData inputData = new SignupInputData(username, password, password);

        // Act
        signupInteractor.execute(inputData);

        // Assert
        verify(userDataAccess, never()).save(any(User.class));
        verify(outputBoundary).prepareFailView(anyString());
    }

    @Test
    void execute_withEmptyPassword_shouldNotCreateUser() {
        // Arrange
        String username = "newUser";
        String password = "";
        SignupInputData inputData = new SignupInputData(username, password, password);

        // Act
        signupInteractor.execute(inputData);

        // Assert
        verify(userDataAccess, never()).save(any(User.class));
        verify(outputBoundary).prepareFailView(anyString());
    }
}