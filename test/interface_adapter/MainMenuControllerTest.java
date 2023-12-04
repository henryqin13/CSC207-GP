package interface_adapter;

import interface_adapter.Guest.GuestViewModel;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.MainMenu.MainMenuController;
import interface_adapter.Signup.SignupViewModel;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MainMenuControllerTest {

    private MainMenuController mainMenuController;
    private ViewManagerModel viewManagerModelMock;
    private SignupViewModel signupViewModelMock;
    private LoginViewModel loginViewModelMock;
    private GuestViewModel guestViewModelMock;

    @Before
    public void setUp() {
        viewManagerModelMock = mock(ViewManagerModel.class);
        signupViewModelMock = mock(SignupViewModel.class);
        loginViewModelMock = mock(LoginViewModel.class);
        guestViewModelMock = mock(GuestViewModel.class);

        mainMenuController = new MainMenuController(
                viewManagerModelMock,
                signupViewModelMock,
                loginViewModelMock,
                guestViewModelMock
        );
    }

    @Test
    public void testSignup() {
        mainMenuController.signup();
        verify(signupViewModelMock).firePropertyChanged();
        verify(viewManagerModelMock).setActiveView("sign up");
        verify(viewManagerModelMock).firePropertyChanged();
    }

    @Test
    public void testLogin() {
        mainMenuController.login();
        verify(loginViewModelMock).firePropertyChanged();
        verify(viewManagerModelMock).setActiveView("log in");
        verify(viewManagerModelMock).firePropertyChanged();
    }

    @Test
    public void testGuest() {
        mainMenuController.guest();
        verify(signupViewModelMock).firePropertyChanged(); // This seems like a potential bug, should it be guestViewModel?
        verify(viewManagerModelMock).setActiveView("playing as guest");
        verify(viewManagerModelMock).firePropertyChanged();
    }
}