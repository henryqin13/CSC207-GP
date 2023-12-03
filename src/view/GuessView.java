package view;

import interface_adapter.Game.GameController;
import interface_adapter.Game.GameState;
import interface_adapter.Game.GameViewModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

public class GuessView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "guess";
    private final GameViewModel gameViewModel;
    private final GameController gameController;

    private final JTextField guessInputField = new JTextField(15);
    private final JButton submitGuessButton = new JButton("Submit Guess");
    private final JLabel feedbackLabel = new JLabel();

    private final JLabel score = new JLabel();

    public GuessView(GameViewModel gameViewModel, GameController gameController) {
        this.setName("guess");
        this.gameViewModel = gameViewModel;
        this.gameController = gameController;

        // Initialize the listeners for property changes and button actions
        this.gameViewModel.addPropertyChangeListener(this);
        setupLayout();
        styleSubmitButton(submitGuessButton);
        setupListeners();
    }

    private void setupLayout() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel northPanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Guess the City", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        northPanel.add(title, BorderLayout.CENTER);

        score.setText("" + gameViewModel.getState().getScore());
        score.setFont(new Font("Arial", Font.BOLD, 24));
        score.setHorizontalAlignment(SwingConstants.RIGHT);
        northPanel.add(score, BorderLayout.EAST);
        add(northPanel, BorderLayout.NORTH);

        // Set up the center panel with GridBagLayout
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally
        gbc.insets = new Insets(10, 0, 10, 0); // Consistent vertical padding

        Dimension fieldButtonSize = new Dimension(300, 40);

        guessInputField.setPreferredSize(fieldButtonSize);
        guessInputField.setMinimumSize(fieldButtonSize);
        guessInputField.setMaximumSize(fieldButtonSize);

        centerPanel.add(guessInputField, gbc);

        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)), gbc);

        submitGuessButton.setPreferredSize(fieldButtonSize);
        submitGuessButton.setMinimumSize(fieldButtonSize);
        submitGuessButton.setMaximumSize(fieldButtonSize);

        centerPanel.add(submitGuessButton, gbc);

        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)), gbc);
        feedbackLabel.setPreferredSize(fieldButtonSize);
        feedbackLabel.setMinimumSize(fieldButtonSize);
        feedbackLabel.setMaximumSize(fieldButtonSize);
        feedbackLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        feedbackLabel.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(feedbackLabel, gbc);
        add(centerPanel, BorderLayout.CENTER);
    }


    private void styleSubmitButton(JButton button) {
        button.setBackground(new Color(34, 139, 34)); // A shade of green
        button.setForeground(Color.WHITE); // Text color
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false); // Removes focus ring around text
        button.setBorder(BorderFactory.createRaisedBevelBorder()); // Gives a 3D effect
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Changes the cursor to a hand on hover

        // Optional: Add a hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 100, 0)); // Darker shade of green on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(34, 139, 34)); // Original shade of green
            }
        });
    }

    public void setupListeners() {
        submitGuessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(submitGuessButton)) {
                    String guess = guessInputField.getText();
                    if (!guess.isEmpty()) {
                        GameState currentState = gameViewModel.getState();
                        currentState.setGuess(guess);
                        gameViewModel.setState(currentState);
                        gameController.makeGuess(guess, currentState.getCity());
                    }
                }
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Check if the change is related to the guess result
        if ("state".equals(evt.getPropertyName())) {
            boolean isCorrect = gameViewModel.getState().isGuessCorrect();
            updateFeedbackDisplay(isCorrect ? "Correct guess!" : "");
            updateScore("" + gameViewModel.getState().getScore());
        }
    }

    private void updateFeedbackDisplay(String feedback) {
        feedbackLabel.setText(feedback);
    }

    private void updateScore(String feedback) {
        score.setText(feedback);
    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
