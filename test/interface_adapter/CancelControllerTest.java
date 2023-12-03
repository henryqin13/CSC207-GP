package interface_adapter;

import interface_adapter.Login.LoginState;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.Signup.SignupState;
import interface_adapter.Signup.SignupViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

class CancelControllerTest {

    @Mock
    private SignupViewModel mockSignupViewModel;
    @Mock
    private LoginViewModel mockLoginViewModel;
    @Mock
    private ViewManagerModel mockViewManagerModel;

    private CancelController cancelControllerForSignup;
    private CancelController cancelControllerForLogin;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cancelControllerForSignup = new CancelController(mockSignupViewModel, mockViewManagerModel);
        cancelControllerForLogin = new CancelController(mockLoginViewModel, mockViewManagerModel);
    }

    @Test
    void testCancelSignUp() {
        SignupState mockSignupState = mock(SignupState.class);
        when(mockSignupViewModel.getState()).thenReturn(mockSignupState);

        cancelControllerForSignup.cancelSignUp();

        verify(mockSignupState).setUsername(null);
        verify(mockSignupViewModel).firePropertyChanged();
        verify(mockViewManagerModel).setActiveView("the city game");
        verify(mockViewManagerModel).firePropertyChanged();
    }

    @Test
    void testCancelLogIn() {
        LoginState mockLoginState = mock(LoginState.class);
        when(mockLoginViewModel.getState()).thenReturn(mockLoginState);

        cancelControllerForLogin.cancelLogIn();

        verify(mockLoginState).setUsername(null);
        verify(mockLoginViewModel).firePropertyChanged();
        verify(mockViewManagerModel).setActiveView("the city game");
        verify(mockViewManagerModel).firePropertyChanged();
    }
}