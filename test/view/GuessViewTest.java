package view;

import interface_adapter.Game.GameController;
import interface_adapter.Game.GameViewModel;
import interface_adapter.MainMenu.MainMenuViewModel;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Test;

import javax.swing.*;

public class GuessViewTest extends AssertJSwingJUnitTestCase {
    private FrameFixture window;
    private MainMenuViewModel signupViewModel;
    private GameController gameController;


    @Override
    protected void onSetUp() {
        GameViewModel gameViewModel = new GameViewModel();



        JFrame frame = GuiActionRunner.execute(() -> {
            GuessView guessView = new GuessView(
                    gameViewModel, gameController
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

        window.textBox("guessInputField").enterText("hi");
        window.button("submitGuessButton").click();

    }
}
