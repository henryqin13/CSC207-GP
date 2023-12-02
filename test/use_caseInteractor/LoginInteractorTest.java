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
}