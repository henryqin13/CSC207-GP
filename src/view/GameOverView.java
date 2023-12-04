package view;

import interface_adapter.Game.GameController;
import interface_adapter.Game.GameViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GameOverView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "game over";

    private final GameViewModel gameViewModel;
    private final GameController gameController;

    private final JLabel gameOverLabel = new JLabel(GameViewModel.GAME_OVER_LABEL);

    private final JLabel guessLabel = new JLabel(GameViewModel.WRONG_GUESS_COMMENT_LABEL);

    private final JButton startOverButton = new JButton(GameViewModel.START_OVER_LABEL);

    private final JButton backButton = new JButton(GameViewModel.BACK_LABEL);

    public GameOverView(GameViewModel gameViewModel, GameController gameController) {
        this.setName("game over");
        this.gameViewModel = gameViewModel;
        this.gameController = gameController;

        this.gameViewModel.addPropertyChangeListener(this);

        setupComponents();
        setupListeners();
    }


    private void setupComponents() {

        JPanel panels = new JPanel();

        gameOverLabel.setFont(new Font("Serif", Font.BOLD, 24));
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameOverLabel.setPreferredSize(new Dimension(800, 100));

        guessLabel.setFont(new Font("Serif", Font.BOLD, 24));
        guessLabel.setHorizontalAlignment(SwingConstants.CENTER);
        guessLabel.setPreferredSize(new Dimension(800, 100));
        panels.add(guessLabel);

        panels.setPreferredSize(new Dimension(800, 300));
        this.add(panels);

        startOverButton.setPreferredSize(new Dimension(800, 100));
        startOverButton.setFont(new Font("Serif", Font.BOLD, 26));
        startOverButton.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(startOverButton);

        backButton.setPreferredSize(new Dimension(800, 100));
        backButton.setFont(new Font("Serif", Font.BOLD, 26));
        backButton.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(backButton);

        startOverButton.setName("startOverButton");
        backButton.setName("backButton");
    }

    private void setupListeners() {
        startOverButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(startOverButton)) {
                    gameController.exit();
                }
            }
        });

        backButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                if (evt.getSource().equals(backButton)){
                    gameController.backToMain();
                }
            }

        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            boolean isCorrect = gameViewModel.getState().isGuessCorrect();
            if (isCorrect){
                guessLabel.setText(GameViewModel.CORRECT_GUESS_COMMENT_LABEL);
            }
            else{
                guessLabel.setText(GameViewModel.WRONG_GUESS_COMMENT_LABEL);
            }
        }
    }
}
