package interface_adapter.Game;

import data_access.OpenAI;
import interface_adapter.ViewManagerModel;
import use_case.DataAccessInterface;
import use_case.Game.GameDataAccessInterface;
import use_case.Game.GameOutputBoundary;
import use_case.GenerativeInterface;

import javax.xml.crypto.Data;
import java.util.Arrays;
import java.util.Scanner;

public class GamePresenter implements GameOutputBoundary {

    private final GameViewModel gameViewModel;
    private ViewManagerModel viewManagerModel;

    public GamePresenter(ViewManagerModel viewManagerModel,
                           GameViewModel gameViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.gameViewModel = gameViewModel;
    }
    @Override
    public void guessView(GenerativeInterface client, GameDataAccessInterface data) {
        System.out.println("Please make a guess:");
        Scanner scanner = new Scanner(System.in);
        boolean correct = data.getCity().getName().toLowerCase().contains(scanner.nextLine().toLowerCase());

        if (correct) {
            System.out.println("Your guess was correct, congratulations!");
        } else {
            System.out.println("That was incorrect, would you like another hint or guess? [g/h]");
            String result = guessOrHint();
            if (result.equals("h")) {
                selectHintView(client, data);
            } else {
                guessView(client, data);
            }
        }
    }

    @Override
    public void hintView(GenerativeInterface client, String hint, GameDataAccessInterface data) {
        System.out.println(hint);
        System.out.println("Would you like to guess or receive another hint? [g/h]");
        String result = guessOrHint();
        if (result.equals("g")) {
            guessView(client, data);
        } else  {
            selectHintView(client, data);
        }
    }

    private String guessOrHint() {
        boolean success = false;
        String result = "";
        while (!success) {
            Scanner scan = new Scanner(System.in);
            String[] hints = {"h", "g"};
            result = scan.next();
            if (Arrays.asList(hints).contains(result)) {
                success = true;
            }
        }
        return result;
    }

    @Override
    public void selectHintView(GenerativeInterface client, GameDataAccessInterface data) {
        boolean success = false;
        String hintDiff = "";
        while (!success) {
            System.out.println("Input a number from one to three to select a difficulty of hint:");
            Scanner scan = new Scanner(System.in);

            String[] hints = {"1", "2", "3"};
            hintDiff = scan.next();
            if (Arrays.asList(hints).contains(hintDiff)) {
                success = true;
            }
        }

        GameState state = new GameState();
        state.setHintDiff(hintDiff);
        hintView(client, client.getResponse("Give a small piece of information about " + data.getCity().getName() + ", do not mention the name of the city in your response. do not use any special characters or formatters. On a scale of 1-3 with 3 being the most vague or difficult hint and 1 being the easiest, this hint should be " + hintDiff + " difficulty."), data);
    }
}
