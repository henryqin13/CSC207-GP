package view;

import interface_adapter.Game.GameController;
import interface_adapter.Game.GameViewModel;
import interface_adapter.Game.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameView extends JPanel implements ActionListener {

    public final String viewName = "guess";

    private final GameViewModel gameViewModel;
    private final GameController gameController;

    private final JLabel titleLabel = new JLabel(GameViewModel.TITLE_LABEL);
    private final JLabel hintLabel = new JLabel("Hint: ");
    private final JTextField guessInputField = new JTextField(15);
    private final JButton guessButton = new JButton(GameViewModel.GUESS_BUTTON_LABEL);
    private final JButton hintButton = new JButton(GameViewModel.HINT_BUTTON_LABEL);
    private final JComboBox<String> hintDifficultyComboBox = new JComboBox<>(new String[]{"1", "2", "3"});
    private final JLabel feedbackLabel = new JLabel();

    public GameView(GameViewModel gameViewModel, GameController gameController) {
        this.setName("game");
        this.gameViewModel = gameViewModel;
        this.gameController = gameController;

        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding around the window
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout with horizontal and vertical gaps

        setupComponents();
        setupListeners();
    }

    private void setupComponents() {
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Hint and Guess panel with GridBagLayout for more control
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0); // Top and bottom padding for components

        // Configure components
        hintDifficultyComboBox.setMaximumSize(hintDifficultyComboBox.getPreferredSize());
        guessInputField.setMaximumSize(guessInputField.getPreferredSize());

        // Hint components
        inputPanel.add(new JLabel(GameViewModel.HINT_DIFFICULTY_LABEL), gbc);
        inputPanel.add(hintDifficultyComboBox, gbc);
        hintButton.setPreferredSize(new Dimension(hintDifficultyComboBox.getPreferredSize().width, hintButton.getPreferredSize().height));
        inputPanel.add(hintButton, gbc);

        // Guess components
        inputPanel.add(new JLabel("Enter your guess:"), gbc);
        inputPanel.add(guessInputField, gbc);
        guessButton.setPreferredSize(new Dimension(guessInputField.getPreferredSize().width, guessButton.getPreferredSize().height));
        inputPanel.add(guessButton, gbc);

        add(inputPanel, BorderLayout.CENTER);

        feedbackLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(feedbackLabel, BorderLayout.SOUTH);
    }

    private JPanel createHintPanel() {
        JPanel hintPanel = new JPanel();
        hintPanel.setLayout(new BoxLayout(hintPanel, BoxLayout.Y_AXIS));
        hintPanel.add(new JLabel(GameViewModel.HINT_DIFFICULTY_LABEL));
        hintPanel.add(hintDifficultyComboBox);
        hintPanel.add(hintButton);
        hintPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return hintPanel;
    }

    private JPanel createGuessPanel() {
        JPanel guessPanel = new JPanel();
        guessPanel.setLayout(new BoxLayout(guessPanel, BoxLayout.Y_AXIS));
        guessPanel.add(new JLabel("Enter your guess:"));
        guessPanel.add(guessInputField);
        guessPanel.add(guessButton);
        guessPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return guessPanel;
    }

    private void setupListeners() {
        guessButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(guessButton)) {
                    GameState currentState = gameViewModel.getState();

                    // TODO: guess screen and set state

                    String guess = guessInputField.getText();
                    if (!guess.isEmpty()) {
                        currentState.setGuess(guess);
                        gameViewModel.setState(currentState);
                        gameController.executeGuess(currentState.getCity());
                    }
                }
            }
        });
        hintButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(hintButton)) {
                    GameState currentState = gameViewModel.getState();
                    String selectedDifficulty = (String) hintDifficultyComboBox.getSelectedItem();
                    // TODO: hint screem
                    currentState.setHintDiff(selectedDifficulty);
                    gameViewModel.setState(currentState);
                    gameController.executeHint(selectedDifficulty, currentState.getCity());
                }
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }


    // Method to update the view based on changes in the game state
//    public void updateView(GameState gameState) {
//        hintLabel.setText("Hint: " + gameState.getHint());
//        feedbackLabel.setText(gameState.isGuessCorrect() ? "Correct Guess!" : "Try again!");
//        guessInputField.setText("");
//    }
}
