package view;

import interface_adapter.Game.GameController;
import interface_adapter.Game.GameViewModel;
import interface_adapter.Game.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GameView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "guess";

    private final GameViewModel gameViewModel;
    private final GameController gameController;

    private final JLabel titleLabel = new JLabel(GameViewModel.TITLE_LABEL);
    private final JLabel hintLabel = new JLabel("Hint: ");
    private final JButton guessButton = new JButton(GameViewModel.GUESS_BUTTON_LABEL);
    private final JButton hintButton = new JButton(GameViewModel.HINT_BUTTON_LABEL);
    private final JButton returnButton = new JButton("Return to Main Menu");
    private final JComboBox<String> hintDifficultyComboBox = new JComboBox<>(new String[]{"1", "2", "3"});
    private final JLabel feedbackLabel = new JLabel();
    private final JLabel score = new JLabel();

    private final JTextField hintInputField = new JTextField();

    public GameView(GameViewModel gameViewModel, GameController gameController) {
        this.setName("game");
        this.gameViewModel = gameViewModel;
        this.gameController = gameController;

        this.gameViewModel.addPropertyChangeListener(this);

        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding around the window
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout with horizontal and vertical gaps

        setupComponents();
        setupListeners();
    }

    private void setupComponents() {

        // North panel that uses BorderLayout to place title and score
        JPanel northPanel = new JPanel(new BorderLayout());
        titleLabel.setFont(new Font("Serif", Font.BOLD, 72));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setPreferredSize(new Dimension(900, 300));
        northPanel.add(titleLabel, BorderLayout.CENTER); // Title in the center

        score.setText("" + gameViewModel.getState().getScore());
        score.setFont(new Font("Arial", Font.BOLD, 24));
        score.setHorizontalAlignment(SwingConstants.RIGHT);
        northPanel.add(score, BorderLayout.EAST); // Score in the top right

        add(northPanel, BorderLayout.NORTH); // Add the entire northPanel to the NORTH region of the BorderLayout

        Dimension preferredSize = new Dimension(300, 40);

        hintDifficultyComboBox.setPreferredSize(preferredSize);
        hintDifficultyComboBox.setMaximumSize(hintDifficultyComboBox.getPreferredSize());
        hintDifficultyComboBox.setMinimumSize(hintDifficultyComboBox.getPreferredSize());

        hintInputField.setPreferredSize(preferredSize);
        hintInputField.setMinimumSize(hintInputField.getPreferredSize());
        hintInputField.setMaximumSize(hintInputField.getPreferredSize());
        hintInputField.setToolTipText("keyword");

        hintButton.setPreferredSize(preferredSize);
        hintButton.setMaximumSize(hintButton.getPreferredSize());
        hintButton.setMinimumSize(hintButton.getPreferredSize());

        guessButton.setPreferredSize(preferredSize);
        guessButton.setMaximumSize(guessButton.getPreferredSize());
        guessButton.setMinimumSize(guessButton.getPreferredSize());

        returnButton.setPreferredSize(preferredSize);
        returnButton.setMaximumSize(guessButton.getPreferredSize());
        returnButton.setMinimumSize(guessButton.getPreferredSize());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0); // Top and bottom padding for components

        JPanel inputPanel = new JPanel(new GridBagLayout());

        inputPanel.add(new JLabel(GameViewModel.HINT_DIFFICULTY_LABEL), gbc);
        inputPanel.add(hintDifficultyComboBox, gbc);
        inputPanel.add(hintInputField, gbc);
        inputPanel.add(hintButton, gbc);
        inputPanel.add(new JLabel("Enter your guess:"), gbc);
        inputPanel.add(guessButton, gbc);
        inputPanel.add(returnButton);
        add(inputPanel, BorderLayout.CENTER);

        feedbackLabel.setPreferredSize(new Dimension(getWidth(), 40)); // Assuming you want the feedback label to match the height of the other components
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
        guessPanel.add(guessButton);
        guessPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return guessPanel;
    }

    private JPanel createReturnPanel() {
        JPanel returnPanel = new JPanel();
        returnPanel.setLayout(new BoxLayout(returnPanel, BoxLayout.Y_AXIS));
        returnPanel.add(new JLabel("Enter your Guess"));
        returnPanel.add(returnButton);
        returnPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return returnPanel;
    }

    private void setupListeners() {
        guessButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(guessButton)) {
                    GameState currentState = gameViewModel.getState();
                    gameViewModel.setState(currentState);
                    gameController.executeGuess(currentState.getCity());
                }
            }
        });
        hintButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(hintButton)) {
                    GameState currentState = gameViewModel.getState();
                    String selectedDifficulty = (String) hintDifficultyComboBox.getSelectedItem();
                    currentState.setHintDiff(selectedDifficulty);
                    gameViewModel.setState(currentState);
                    gameController.executeHint(selectedDifficulty, currentState.getCity(), hintInputField.getText());
                }
            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(returnButton)) {
                    gameController.returnToMain();
                }
            }
        });

    }

    private void updateScore(String feedback) {
        score.setText(feedback);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            updateScore("" + gameViewModel.getState().getScore());
        }
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
