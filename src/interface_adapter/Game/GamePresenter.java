package interface_adapter.Game;

import interface_adapter.ViewManagerModel;
import use_case.Game.GameOutputBoundary;
import use_case.Game.GameOutputData;

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
    public void guessView(GameOutputData guess) {

    }

    @Override
    public void hintView(String hint) {

    }

    @Override
    public void selectHintView() {
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
    }
}
