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

    private final JTextArea hintLabel = new JTextArea();
    private final JButton getHintButton = new JButton("Get Another Hint");

    private final JComboBox<String> hintDifficultyComboBox = new JComboBox<>(new String[]{"1", "2", "3"});
    private final JButton makeGuessButton = new JButton("Make Guess");
    private final JLabel titleLabel = new JLabel("Hint Screen");

    private final JLabel score = new JLabel();

    private final JTextField hintInputField = new JTextField();

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
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // North panel for the title and score
        JPanel northPanel = new JPanel(new BorderLayout());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        northPanel.add(titleLabel, BorderLayout.CENTER); // Title in the center

        score.setText("" + gameViewModel.getState().getScore());
        score.setFont(new Font("Arial", Font.BOLD, 24));
        score.setHorizontalAlignment(SwingConstants.RIGHT);
        northPanel.add(score, BorderLayout.EAST); // Score in the top right
        add(northPanel, BorderLayout.NORTH);


        // Central panel for the hint difficulty, hint text, and buttons
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1.0; // Allow hint label to expand horizontally
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.anchor = GridBagConstraints.CENTER;

        // Hint label
        hintLabel.setFont(new Font("Arial", Font.ITALIC, 20));
        hintLabel.setAlignmentX(SwingConstants.CENTER);
        hintLabel.setLineWrap(true);
        hintLabel.setPreferredSize(new Dimension(700, 400));
        hintLabel.setWrapStyleWord(true);
        updateHintLabel(gameViewModel.getState().getHint());
        hintLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(hintLabel, gbc);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 50)));


        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.LINE_AXIS));
        contentPanel.add(Box.createHorizontalGlue());
        JPanel hintSubPanel = new JPanel();
        hintSubPanel.setLayout(new BoxLayout(hintSubPanel, BoxLayout.Y_AXIS));
        JPanel guessSubPanel = new JPanel();
        guessSubPanel.setLayout(new BoxLayout(guessSubPanel, BoxLayout.Y_AXIS));


        hintInputField.setPreferredSize((new Dimension(125, 40)));
        hintInputField.setMinimumSize(hintInputField.getPreferredSize());
        hintInputField.setMaximumSize(hintInputField.getPreferredSize());
        hintInputField.setToolTipText("keyword");


        hintDifficultyComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        getHintButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        hintDifficultyComboBox.setPreferredSize(new Dimension(75, 40));
        getHintButton.setPreferredSize(new Dimension(180, 40));
        makeGuessButton.setSize(new Dimension(125, 40));

        hintSubPanel.add(hintDifficultyComboBox);
        hintSubPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        hintSubPanel.add(hintInputField);
        hintSubPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        hintSubPanel.add(getHintButton);
        contentPanel.add(hintSubPanel);
        contentPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        guessSubPanel.add(makeGuessButton);
        contentPanel.add(guessSubPanel);
        contentPanel.add(Box.createHorizontalGlue());

        bottomPanel.add(contentPanel, BorderLayout.PAGE_START);

        int topPadding = 10;
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(topPadding, 0, 0, 0));
        centerPanel.add(bottomPanel, gbc);


        add(centerPanel, BorderLayout.CENTER);
    }

    private void updateHintLabel(String hint) {
        String wrappedText = "<html><body style='width: %1spx; text-align: center;'>%2s</body></html>";
        hintLabel.setText(String.format(wrappedText, this.getWidth() - 40, hint)); // Adjust width as needed
    }



    private void setupListeners() {
        getHintButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(getHintButton)) {
                    GameState currentState = gameViewModel.getState();
                    String selectedDifficulty = (String) hintDifficultyComboBox.getSelectedItem();
                    currentState.setHintDiff(selectedDifficulty);
                    gameViewModel.setState(currentState);
                    gameController.executeHint(selectedDifficulty, currentState.getCity(), hintInputField.getText());
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


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            updateHintDisplay(gameViewModel.getState().getHint());
            updateScore("" + gameViewModel.getState().getScore());
        }
    }

    @Override
    public void addNotify() {
        super.addNotify();
        updateHintLabel(gameViewModel.getState().getHint());
    }

    private void updateHintDisplay(String newHint) {
        hintLabel.setText(newHint);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private void updateScore(String feedback) {
        score.setText(feedback);
    }
}
