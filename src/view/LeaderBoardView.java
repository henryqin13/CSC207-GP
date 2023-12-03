package view;

import interface_adapter.Guest.GuestViewModel;
import interface_adapter.Leaderboard.LeaderboardController;
import interface_adapter.Leaderboard.LeaderboardViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LeaderBoardView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "Leaderboard";
    private final LeaderboardViewModel leaderBoardViewModel;
    private final LeaderboardController leaderboardController;
    final JButton exit;
    public LeaderBoardView(LeaderboardViewModel leaderBoardViewModel, LeaderboardController leaderboardController){
        this.leaderBoardViewModel = leaderBoardViewModel;
        this.leaderboardController = leaderboardController;
        this.leaderBoardViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(leaderBoardViewModel.TITLE_LABEL);
        title.setFont(new Font("Serif", Font.BOLD, 30));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel buttons = new JPanel();
        buttons.setPreferredSize(new Dimension(500, 400));
        buttons.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        exit = new JButton(GuestViewModel.EXIT_BUTTON_NAME);
        exit.setFont(new Font("Serif", Font.PLAIN, 25));
        exit.setPreferredSize(new Dimension(125, 40));
        exit.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        buttons.add(exit);
        exit.addActionListener(this);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(exit)){
            //Need to figure out how to pass in the previous view as an argument.
            //leaderboardController.exit()
        }

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
