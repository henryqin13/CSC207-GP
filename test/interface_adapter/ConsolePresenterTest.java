package interface_adapter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsolePresenterTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testDisplayMessage() {
        ConsolePresenter presenter = new ConsolePresenter();
        presenter.displayMessage("Test message");
        assertEquals("Test message\n", outContent.toString());
    }

    @Test
    public void testDisplayOptions() {
        ConsolePresenter presenter = new ConsolePresenter();
        presenter.displayOptions();
        String expectedOutput = "Do you want to play again or quit?\n" +
                "1. Play again\n" +
                "2. Quit\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testDisplayFeedbackScoreAbove80() {
        ConsolePresenter presenter = new ConsolePresenter();
        presenter.displayFeedback(85);
        assertEquals("You are awesome!\n", outContent.toString());
    }

    @Test
    public void testDisplayFeedbackScoreAbove50() {
        ConsolePresenter presenter = new ConsolePresenter();
        presenter.displayFeedback(75);
        assertEquals("You are good!\n", outContent.toString());
    }

    @Test
    public void testDisplayFeedbackScoreBelow50() {
        ConsolePresenter presenter = new ConsolePresenter();
        presenter.displayFeedback(45);
        assertEquals("You can do better!\n", outContent.toString());
    }
}