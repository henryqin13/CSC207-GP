package view;

import interface_adapter.Game.GameController;
import interface_adapter.Game.GameViewModel;
import interface_adapter.Game.GameState;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameOverView extends JPanel implements ActionListener{
    public final String viewName = "game over";

    private final GameViewModel gameViewModel;
    private final GameController gameController;

    private final JLabel gameOverLabel = new JLabel(GameViewModel.GAME_OVER_LABEL);

    private final JLabel correctGuessLabel = new JLabel(GameViewModel.CORRECT_GUESS_COMMENT_LABEL);

    private final JLabel wrongGuessLabel = new JLabel(GameViewModel.WRONG_GUESS_COMMENT_LABEl);
    private final JButton startOverButton = new JButton(GameViewModel.START_OVER_LABEL);

    private final JButton backButton = new JButton(GameViewModel.BACK_LABEL);

    public GameOverView(GameViewModel gameViewModel, GameController gameController) {
        this.setName("game over");
        this.gameViewModel = gameViewModel;
        this.gameController = gameController;


        setupComponents();
        setupListeners();
    }


    private void setupComponents() {

        JPanel panels = new JPanel();


        gameOverLabel.setFont(new Font("Serif", Font.BOLD, 24));
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameOverLabel.setPreferredSize(new Dimension(800, 100));


        correctGuessLabel.setFont(new Font("Serif", Font.BOLD, 24));
        correctGuessLabel.setHorizontalAlignment(SwingConstants.CENTER);
        correctGuessLabel.setPreferredSize(new Dimension(800, 100));

        panels.add(gameOverLabel);
        panels.add(correctGuessLabel);
        panels.setPreferredSize(new Dimension(800, 500));
        this.add(panels);

//        wrongGuessLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        wrongGuessLabel.setFont(new Font("Serif", Font.BOLD, 24));
//        add(wrongGuessLabel, BorderLayout.CENTER);


        startOverButton.setPreferredSize(new Dimension(800, 100));
        startOverButton.setFont(new Font("Serif", Font.BOLD, 26));
        startOverButton.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(startOverButton);

        backButton.setPreferredSize(new Dimension(800, 100));
        backButton.setFont(new Font("Serif", Font.BOLD, 26));
        backButton.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(backButton);
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

}
