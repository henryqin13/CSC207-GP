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

public class HintView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "hint";
    private final GameViewModel gameViewModel;
    private final GameController gameController;

    private final JLabel hintLabel = new JLabel();
    private final JButton getHintButton = new JButton("Get Hint");

    private final JComboBox<String> hintDifficultyComboBox = new JComboBox<>(new String[]{"1", "2", "3"});
    private final JButton makeGuessButton = new JButton("Make Guess");
    private final JLabel titleLabel = new JLabel("Hint Screen");


    public HintView(GameViewModel gameViewModel, GameController gameController) {
        this.setName("hint");

        this.gameViewModel = gameViewModel;
        this.gameController = gameController;

        this.gameViewModel.addPropertyChangeListener(this);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setupComponents();
        setupListeners();
    }

    private void setupComponents() {
        // Title label centered horizontally
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER); // Ensure the label text is centered
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Use FlowLayout for auto centering
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        hintLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        // Use HTML to enable word wrap for the hint text
        updateHintLabel(gameViewModel.getState().getHint());

        // Create a panel with BorderLayout for the hint label
        JPanel hintPanel = new JPanel(new BorderLayout());
        hintPanel.add(hintLabel, BorderLayout.CENTER);
        hintPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding around the hint

        add(hintPanel, BorderLayout.CENTER); // Add the hint panel to the center of the main panel


        // Buttons and ComboBox at the bottom
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        hintDifficultyComboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, hintDifficultyComboBox.getPreferredSize().height));
        getHintButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, getHintButton.getPreferredSize().height));
        makeGuessButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, makeGuessButton.getPreferredSize().height));

        bottomPanel.add(hintDifficultyComboBox);
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        bottomPanel.add(getHintButton);
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        bottomPanel.add(makeGuessButton);

        // Aligning bottom panel components
        hintDifficultyComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        getHintButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        makeGuessButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void setupListeners() {
        getHintButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(getHintButton)) {
                    GameState currentState = gameViewModel.getState();
                    String selectedDifficulty = (String) hintDifficultyComboBox.getSelectedItem();
                    currentState.setHintDiff(selectedDifficulty);
                    gameViewModel.setState(currentState);
                    gameController.executeHint(selectedDifficulty, currentState.getCity());
                }
            }
        });
        makeGuessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(makeGuessButton)) {
                    GameState currentState = gameViewModel.getState();
                    gameController.executeGuess(currentState.getCity());
                }
            }
        });

    }

    public void updateHintLabel(String text) {
        String wrappedText = "<html><body style='width: " + this.getWidth() * 0.7 + "px;'><p>" + text + "</p></body></html>";
        hintLabel.setText(wrappedText);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            updateHintDisplay(gameViewModel.getState().getHint());
        }
    }

    // Override the 'addNotify' method to know when the label is actually added to a container
    @Override
    public void addNotify() {
        super.addNotify();
        // Call updateHintLabel here with the actual text to be displayed
        updateHintLabel("This city is the capital of Japan and is renowned for its beauty.");
    }

    private void updateHintDisplay(String newHint) {
        hintLabel.setText(newHint);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
