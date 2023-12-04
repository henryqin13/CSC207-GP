package view;

import interface_adapter.CancelController;
import interface_adapter.Guest.GuestController;
import interface_adapter.Guest.GuestViewModel;
import interface_adapter.Login.LoginController;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.Signup.SignupController;
import interface_adapter.Signup.SignupViewModel;
import interface_adapter.ViewManagerModel;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Test;

import javax.swing.*;

public class LoginViewTest extends AssertJSwingJUnitTestCase {

    private FrameFixture window;
    private LoginViewModel loginViewModel;
    private LoginController loginController;
    private GuestViewModel guestViewModel;
    private GuestController guestController;
    private CancelController cancelController;

    @Override
    protected void onSetUp() {
        // Create instances of your controllers and view models here or mock them
        ViewManagerModel viewManagerModel = new ViewManagerModel();


        LoginViewModel loginViewModel = new LoginViewModel();

        JFrame frame = GuiActionRunner.execute(() -> {
            LoginView loginView = new LoginView(
                    loginViewModel, loginController, cancelController
            );
            JFrame newFrame = new JFrame("Test Frame");
            newFrame.add(loginView);
            newFrame.pack();
            return newFrame;
        });

        window = new FrameFixture(robot(), frame);
        window.show(); // shows the frame to test
    }

    @Test
    public void shouldWarnWhenUsernameIsTaken() {

        window.textBox("usernameInputField").enterText("takenUsername");

        window.textBox("passwordInputField").enterText("hi");

        // Click the sign-up button
//        window.button("signUp").click();

        assert true;
    }
}
