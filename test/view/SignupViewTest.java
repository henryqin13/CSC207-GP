package view;

import app.SignupUseCaseFactory;
import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import interface_adapter.CancelController;
import interface_adapter.Game.GameViewModel;
import interface_adapter.Guest.GuestController;
import interface_adapter.Guest.GuestViewModel;
import interface_adapter.LoggedIn.LoggedInController;
import interface_adapter.LoggedIn.LoggedInViewModel;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.MainMenu.MainMenuController;
import interface_adapter.MainMenu.MainMenuViewModel;
import interface_adapter.Signup.SignupController;
import interface_adapter.Signup.SignupViewModel;
import interface_adapter.ViewManagerModel;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.swing.*;

import java.io.IOException;

import static org.assertj.swing.launcher.ApplicationLauncher.application;

public class SignupViewTest extends AssertJSwingJUnitTestCase {
    private FrameFixture window;
    private SignupViewModel signupViewModel;
    private SignupController signupController;
    private GuestViewModel guestViewModel;
    private GuestController guestController;
    private CancelController cancelController;

    @Override
    protected void onSetUp() {
        // Create instances of your controllers and view models here or mock them
        ViewManagerModel viewManagerModel = new ViewManagerModel();


        SignupViewModel signupViewModel = new SignupViewModel();

        JFrame frame = GuiActionRunner.execute(() -> {
            SignupView signupView = new SignupView(
                    signupController, signupViewModel, guestController, guestViewModel, cancelController
            );
            JFrame newFrame = new JFrame("Test Frame");
            newFrame.add(signupView);
            newFrame.pack();
            return newFrame;
        });

        window = new FrameFixture(robot(), frame);
        window.show(); // shows the frame to test
    }

    @Test
    public void test() {

        window.textBox("usernameInputField").enterText("takenUsername");

        window.textBox("passwordInputField").enterText("hi");

    }
}
