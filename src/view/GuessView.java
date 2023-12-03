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

    private final JButton exitButton = new JButton("Back");
    private final JLabel feedbackLabel = new JLabel();

    public GuessView(GameViewModel gameViewModel, GameController gameController) {
        this.setName("guess");
        this.gameViewModel = gameViewModel;
        this.gameController = gameController;

        // Initialize the listeners for property changes and button actions
        this.gameViewModel.addPropertyChangeListener(this);
        setupLayout();
        styleSubmitButton(submitGuessButton);
        styleExitButton(exitButton);
        setupListeners();
    }

    private void setupLayout() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title at the top
        JLabel title = new JLabel("Guess the City", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        // Center panel to hold input field and guess button
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Input field setup
        guessInputField.setPreferredSize(new Dimension(200, 24)); // Set the preferred size
        gbc.insets = new Insets(5, 0, 5, 0);
        centerPanel.add(guessInputField, gbc);

        // Guess button setup to match the input field size
        submitGuessButton.setPreferredSize(guessInputField.getPreferredSize());
        centerPanel.add(submitGuessButton, gbc);

        exitButton.setPreferredSize(guessInputField.getPreferredSize());
        centerPanel.add(exitButton, gbc);

        // Feedback label at the bottom
        feedbackLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        feedbackLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add center panel and feedback label to the main layout
        add(centerPanel, BorderLayout.CENTER);
        add(feedbackLabel, BorderLayout.SOUTH);
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

    private void styleExitButton(JButton button) {
        button.setBackground(new Color(255, 255, 255)); // A shade of green
        button.setForeground(Color.BLACK); // Text color
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false); // Removes focus ring around text
        button.setBorder(BorderFactory.createRaisedBevelBorder()); // Gives a 3D effect
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Changes the cursor to a hand on hover

        // Optional: Add a hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 80, 20));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(34, 120, 40));
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
                        gameController.makeGuess(guess, currentState.getCity())
                        ;
                    }

                }

            }

        });
        exitButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                if (evt.getSource().equals(exitButton)){
                   gameController.exit();
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
        }
    }

    private void updateFeedbackDisplay(String feedback) {
        feedbackLabel.setText(feedback);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
