package view;

import interface_adapter.Leaderboard.LeaderboardController;
import interface_adapter.LoggedIn.LoggedInController;
import interface_adapter.LoggedIn.LoggedInState;
import interface_adapter.LoggedIn.LoggedInViewModel;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;
    private final LoggedInController loggedInController;
    private final LeaderboardController leaderboardController;
    private final ViewManagerModel viewManagerModel;

    JLabel username;

    final JButton logOut;
    final JButton playGame;
    final JButton leaderboard;

    public LoggedInView(LoggedInViewModel loggedInViewModel, LoggedInController loggedInController, LeaderboardController leaderboardController, ViewManagerModel viewManagerModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInController = loggedInController;
        this.leaderboardController = leaderboardController;
        this.viewManagerModel = viewManagerModel;
        this.viewManagerModel.addPropertyChangeListener(this);
        this.loggedInViewModel.addPropertyChangeListener(this);


        ImageIcon pic1 = new ImageIcon("pictures/toronto2.jpeg");
        Image oldSize = pic1.getImage();
        Image newSize = oldSize.getScaledInstance(900, 450, 1);
        ImageIcon pic = new ImageIcon(newSize);

        JLabel gamePic = new JLabel(pic);
        gamePic.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title = new JLabel(loggedInViewModel.TITLE_LABEL);
        title.setFont(new Font("Serif", Font.BOLD, 30));
        title.setPreferredSize(new Dimension(100, 200));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usernameInfo = new JLabel("You are logged in as: ");
        usernameInfo.setFont(new Font("Serif", Font.PLAIN, 25));
        usernameInfo.setPreferredSize(new Dimension(230, 200));
        username = new JLabel();
        username.setFont(new Font("Serif", Font.PLAIN, 25));
        JPanel welcomeMessage = new JPanel();
        welcomeMessage.add(usernameInfo);
        welcomeMessage.add(username);
        JPanel buttons = new JPanel();

        logOut = new JButton(loggedInViewModel.LOGOUT_BUTTON_LABEL);
        logOut.setFont(new Font("Serif", Font.PLAIN, 25));
        logOut.setPreferredSize(new Dimension(125, 40));
        buttons.add(logOut);
        logOut.addActionListener(this);
        logOut.addActionListener(e -> handleLogOut());

        playGame = new JButton("Play Game");
        playGame.setFont(new Font("Serif", Font.PLAIN, 25));
        playGame.setPreferredSize(new Dimension(150, 40));
        buttons.add(playGame);
        playGame.addActionListener(this);
        playGame.addActionListener(e -> handlePlayGame());

        leaderboard = new JButton("LeaderBoard");
        leaderboard.setFont(new Font("Serif", Font.PLAIN, 25));
        leaderboard.setPreferredSize(new Dimension(150, 40));
        buttons.add(leaderboard);
        leaderboard.addActionListener(this);
        leaderboard.addActionListener(e -> handleLeaderboard());


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(gamePic);
        this.add(title);
        this.add(welcomeMessage);
        this.add(buttons);
    }

    private void handleLogOut() {
        loggedInController.logout();
    }

    private void handlePlayGame() {
        loggedInController.playgame();
    }

    private void handleLeaderboard() {
        leaderboardController.execute();
        viewManagerModel.setActiveView("Leaderboard");
        viewManagerModel.firePropertyChanged();
    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(logOut)) {
            handleLogOut();
        } else if (evt.getSource().equals(playGame)) {
            handlePlayGame();
        } else if (evt.getSource().equals(leaderboard)) {
            handleLeaderboard();
        }
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            username.setText(loggedInViewModel.getState().getUsername());
        }
    }
}
