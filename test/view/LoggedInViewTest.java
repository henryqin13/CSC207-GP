package view;

import interface_adapter.CancelController;
import interface_adapter.Guest.GuestController;
import interface_adapter.Guest.GuestViewModel;
import interface_adapter.LoggedIn.LoggedInController;
import interface_adapter.LoggedIn.LoggedInViewModel;
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

public class LoggedInViewTest extends AssertJSwingJUnitTestCase {

    private FrameFixture window;
    private LoggedInController loggedInController;
    @Override
    protected void onSetUp() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();


        LoggedInViewModel loginViewModel = new LoggedInViewModel();

        JFrame frame = GuiActionRunner.execute(() -> {
            LoggedInView loginView = new LoggedInView(
                    loginViewModel, loggedInController
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
    public void test() {

        window.button("logOut").click();

        window.button("playGame").click();
    }
}
