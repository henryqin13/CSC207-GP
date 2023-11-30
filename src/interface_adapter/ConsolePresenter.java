package interface_adapter;

public class ConsolePresenter implements Presenter {
    // implement the methods for the presenter
    public void displayMessage(String message) {
        // print the message to the console
        System.out.println(message);
    }

    public void displayOptions() {
        // print the options to the console
        System.out.println("Do you want to play again or quit?");
        System.out.println("1. Play again");
        System.out.println("2. Quit");
    }

    public void displayFeedback(int score) {
        // print some feedback based on the score
        if (score >= 80) { //arbitrary
            System.out.println("You are awesome!");
        } else if (score >= 50) { //arbitrary
            System.out.println("You are good!");
        } else {
            System.out.println("You can do better!");
        }
    }
}