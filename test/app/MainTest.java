//package app;
//
//import org.assertj.swing.edt.GuiActionRunner;
//import org.assertj.swing.fixture.FrameFixture;
//import org.assertj.swing.fixture.JButtonFixture;
//import org.assertj.swing.fixture.JPanelFixture;
//import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
//import org.junit.Test;
//
//import javax.swing.*;
//import java.awt.*;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.fail;
//
//public class MainTest extends AssertJSwingJUnitTestCase {
//    private FrameFixture window;
//
//    @Override
//    protected void onSetUp() {
//        // Execute the Main class in the GUI thread.
//        GuiActionRunner.execute(() -> {
//            try {
//                Main.main(new String[]{});
//            } catch (NullPointerException e) {
//                if (e.getMessage().contains("config.properties")) {
//                    fail("config.properties file not found. Ensure it is in the correct location.");
//                } else {
//                    throw e; // rethrow if it's not the known issue
//                }
//            } catch (Exception e) {
//                fail("Failed to start the Main application: " + e.getMessage());
//            }
//        });
//        // Find the visible window to interact with it.
//        window = new FrameFixture(robot(), findActiveFrame());
//        window.show(); // shows the frame to test
//    }
//
//    private JFrame findActiveFrame() {
//        for (Frame frame : JFrame.getFrames()) {
//            if (frame.isVisible()) {
//                return (JFrame) frame;
//            }
//        }
//        throw new IllegalStateException("The application frame is not visible");
//    }
//
//    @Test
//    public void testApplicationWindowCreated() {
//        window.requireVisible();
//        window.requireTitle("our game demo");
//    }
//
//    @Test
//    public void testViewsAddedToMainPanel() {
//        JPanelFixture viewsPanel = window.panel("views");
//        viewsPanel.requireVisible();
//
//        // Retrieve the underlying JPanel to check the component count.
//        JPanel panel = (JPanel) viewsPanel.target();
//        assertEquals("Expected number of components in the views panel is not correct.", 6, panel.getComponentCount());
//    }
//
//    @Test
//    public void testMainMenuViewPresent() {
//        JPanelFixture mainMenuView = window.panel("MainMenuView");
//        mainMenuView.requireVisible();
//    }
//
//    // Add more tests for other views if necessary.
//    @Test
//    public void testLoginButtonOpensLoginView() {
//        JButtonFixture loginButton = window.button("loginButton");
//        loginButton.requireVisible();
//        loginButton.click();
//
//        JPanelFixture loginView = window.panel("LoginView");
//        loginView.requireVisible();
//    }
//
//    @Test
//    public void testSignupButtonOpensSignupView() {
//        JButtonFixture signupButton = window.button("signupButton");
//        signupButton.requireVisible();
//        signupButton.click();
//
//        JPanelFixture signupView = window.panel("SignupView");
//        signupView.requireVisible();
//    }
//
//    @Test
//    public void testGuestButtonOpensGuestView() {
//        JButtonFixture guestButton = window.button("guestButton");
//        guestButton.requireVisible();
//        guestButton.click();
//
//        JPanelFixture guestView = window.panel("GuestView");
//        guestView.requireVisible();
//    }
//
//    @Test
//    public void testExitButtonClosesApplication() {
//        JButtonFixture exitButton = window.button("exitButton");
//        exitButton.requireVisible();
//        exitButton.click();
//
//        // Ensure the application window is closed
//        window.requireNotVisible();
//    }
//
//    @Override
//    protected void onTearDown() {
//        // Check if the window is not null before cleaning up
//        if (window != null) {
//            window.cleanUp();
//        }
//    }
//}