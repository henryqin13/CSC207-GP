package view;

import interface_adapter.CancelController;
import interface_adapter.Guest.GuestController;
import interface_adapter.Guest.GuestViewModel;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.MainMenu.MainMenuController;
import interface_adapter.MainMenu.MainMenuViewModel;
import interface_adapter.Signup.SignupController;
import interface_adapter.Signup.SignupViewModel;
import interface_adapter.ViewManagerModel;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Test;

import javax.swing.*;

public class MainMenuViewTest extends AssertJSwingJUnitTestCase {
    private FrameFixture window;
    private MainMenuViewModel signupViewModel;
    private MainMenuController signupController;
    private GuestViewModel guestViewModel;
    private GuestController guestController;
    private CancelController cancelController;

    @Override
    protected void onSetUp() {
        // Create instances of your controllers and view models here or mock them
        ViewManagerModel viewManagerModel = new ViewManagerModel();


        MainMenuViewModel mainMenuViewModel = new MainMenuViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        LoginViewModel LoginViewModel = new LoginViewModel();



        JFrame frame = GuiActionRunner.execute(() -> {
            MainMenuView signupView = new MainMenuView(
                    mainMenuViewModel, signupController, signupViewModel, LoginViewModel, guestViewModel
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

        window.button("logIn").click();
        window.button("signUp").click();
        window.button("guest").click();

    }
}
