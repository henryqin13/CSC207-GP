package View;

import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;

import interface_adapter.Login.LoginViewModel;
import interface_adapter.Login.LoginController;
import interface_adapter.CancelController;
import use_case.Login.*;
import view.LoginView;


public class LoginViewTest {

    private LoginView loginView;
    private LoginInputBoundary loginInputBoundary;
    private LoginUserDataAccessInterface loginUserDataAccessInterface;
    private LoginOutputBoundary loginOutputBoundary;

    @BeforeEach
    public void setUp() {
        LoginViewModel loginViewModel = new LoginViewModel();
        LoginInputBoundary mockInteractor = null;
        LoginController loginController = new LoginController(mockInteractor);
        CancelController cancelController = new CancelController(loginViewModel, new ViewManagerModel());
        loginView = new LoginView(loginViewModel, loginController, cancelController);
    }

    @Test
    public void testRendering() {
        try {
            Field usernameInputField = LoginView.class.getDeclaredField("usernameInputField");
            usernameInputField.setAccessible(true);
            JTextField usernameField = (JTextField) usernameInputField.get(loginView);
            assertNotNull(usernameField);

            Field passwordInputField = LoginView.class.getDeclaredField("passwordInputField");
            passwordInputField.setAccessible(true);
            JPasswordField passwordField = (JPasswordField) passwordInputField.get(loginView);
            assertNotNull(passwordField);

            Field logInField = LoginView.class.getDeclaredField("logIn");
            logInField.setAccessible(true);
            JButton logInButton = (JButton) logInField.get(loginView);
            assertNotNull(logInButton);

            Field cancelField = LoginView.class.getDeclaredField("cancel");
            cancelField.setAccessible(true);
            JButton cancelButton = (JButton) cancelField.get(loginView);
            assertNotNull(cancelButton);

            Field viewModelField = LoginView.class.getDeclaredField("loginViewModel");
            viewModelField.setAccessible(true);
            LoginViewModel viewModel = (LoginViewModel) viewModelField.get(loginView);
            assertNotNull(viewModel);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Exception: " + e.getMessage());
        }
    }

//    @Test
//    public void testInputFieldsValidation() {
//        try {
//            Field usernameInputField = LoginView.class.getDeclaredField("usernameInputField");
//            usernameInputField.setAccessible(true);
//            JTextField usernameField = (JTextField) usernameInputField.get(loginView);
//
//            Field passwordInputField = LoginView.class.getDeclaredField("passwordInputField");
//            passwordInputField.setAccessible(true);
//            JPasswordField passwordField = (JPasswordField) passwordInputField.get(loginView);
//
//            // Test empty inputs
//            usernameField.setText("");
//            passwordField.setText("");
//            assertEquals("", loginView.loginViewModel.getState().getUsername());
//            assertEquals("", loginView.loginViewModel.getState().getPassword());
//
//            // Test valid inputs
//            usernameField.setText("validUsername");
//            passwordField.setText("validPassword");
//            assertEquals("validUsername", loginView.loginViewModel.getState().getUsername());
//            assertEquals("validPassword", loginView.loginViewModel.getState().getPassword());
//
//            // Test invalid inputs
//            usernameField.setText("!@#InvalidUser");
//            passwordField.setText("InvalidPass");
//            assertNotEquals("!@#InvalidUser", loginView.loginViewModel.getState().getUsername());
//            assertNotEquals("InvalidPass", loginView.loginViewModel.getState().getPassword());
//
//        } catch (NoSuchFieldException | IllegalAccessException e) {
//            fail("Exception: " + e.getMessage());
//        }
//    }

//    @Test
//    public void testLogInButtonAction() {
//        try {
//            Field usernameInputField = LoginView.class.getDeclaredField("usernameInputField");
//            usernameInputField.setAccessible(true);
//            JTextField usernameField = (JTextField) usernameInputField.get(loginView);
//            usernameField.setText("user");
//
//            Field passwordInputField = LoginView.class.getDeclaredField("passwordInputField");
//            passwordInputField.setAccessible(true);
//            JPasswordField passwordField = (JPasswordField) passwordInputField.get(loginView);
//            passwordField.setText("pass");
//
//            Field logInField = LoginView.class.getDeclaredField("logIn");
//            logInField.setAccessible(true);
//            JButton logInButton = (JButton) logInField.get(loginView);
//            logInButton.doClick();
//            // Add assertions for login action
//
//        } catch (NoSuchFieldException | IllegalAccessException e) {
//            fail("Exception: " + e.getMessage());
//        }
//    }

    @Test
    public void testCancelButtonAction() {
        try {
            Field usernameInputField = LoginView.class.getDeclaredField("usernameInputField");
            usernameInputField.setAccessible(true);
            JTextField usernameField = (JTextField) usernameInputField.get(loginView);
            usernameField.setText("user");

            Field passwordInputField = LoginView.class.getDeclaredField("passwordInputField");
            passwordInputField.setAccessible(true);
            JPasswordField passwordField = (JPasswordField) passwordInputField.get(loginView);
            passwordField.setText("pass");

            Field cancelField = LoginView.class.getDeclaredField("cancel");
            cancelField.setAccessible(true);
            JButton cancelButton = (JButton) cancelField.get(loginView);
            cancelButton.doClick();
            assertEquals("", usernameField.getText());
            assertEquals("", passwordField.getText());
            // Add more assertions for cancel action

        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Exception: " + e.getMessage());
        }
    }

    // Add more test methods using reflection to access private fields/methods
}