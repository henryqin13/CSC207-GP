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

public class LeaderBoardView extends JFrame implements ActionListener, PropertyChangeListener {
    private final String viewName = "Leaderboard";
    private final LeaderboardViewModel leaderBoardViewModel;
    private final LeaderboardController leaderboardController;
    final JButton back;
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
        back = new JButton(GuestViewModel.BACK_BUTTON_NAME);
        back.setFont(new Font("Serif", Font.PLAIN, 25));
        back.setPreferredSize(new Dimension(125, 40));
        back.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        buttons.add(back);
        back.addActionListener(this);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.leaderBoardViewModel.initializeUI(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(back)){
            //Need to figure out how to pass in the previous view as an argument.
            //leaderboardController.exit()
        }

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
