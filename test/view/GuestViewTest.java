package view;

import interface_adapter.Game.GameController;
import interface_adapter.Game.GameViewModel;
import interface_adapter.Guest.GuestController;
import interface_adapter.Guest.GuestViewModel;
import interface_adapter.MainMenu.MainMenuViewModel;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Test;

import javax.swing.*;

public class GuestViewTest extends AssertJSwingJUnitTestCase {
    private FrameFixture window;
    private MainMenuViewModel signupViewModel;
    private GuestController guestController;


    @Override
    protected void onSetUp() {
        GuestViewModel guestViewModel = new GuestViewModel();



        JFrame frame = GuiActionRunner.execute(() -> {
            GuestView guessView = new GuestView(
                    guestViewModel, guestController
            );
            JFrame newFrame = new JFrame("Test Frame");
            newFrame.add(guessView);
            newFrame.pack();
            return newFrame;
        });

        window = new FrameFixture(robot(), frame);
        window.show(); // shows the frame to test
    }

    @Test
    public void test() {

        window.button("exit").click();
        window.button("startGame").click();

    }
}
