package view;

import interface_adapter.Guest.GuestController;
import interface_adapter.Guest.GuestViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GuestView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "playing as guest";
    private final GuestViewModel guestViewModel;
    private final GuestController guestController;

    JLabel username;

    final JButton exit;

    final JButton startGame;


    public GuestView(GuestViewModel guestViewModel, GuestController guestController) {
        this.guestViewModel = guestViewModel;
        this.guestController = guestController;
        this.guestViewModel.addPropertyChangeListener(this);

        ImageIcon pic1 = new ImageIcon("pictures/toronto.jpeg");
        Image oldSize = pic1.getImage();
        Image newSize = oldSize.getScaledInstance(900, 450, 1);
        ImageIcon pic = new ImageIcon(newSize);

        JLabel gamePic = new JLabel(pic);
        gamePic.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title = new JLabel(guestViewModel.TITLE_LABEL);
        title.setFont(new Font("Serif", Font.BOLD, 30));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usernameInfo = new JLabel("Currently logged in as guest");
        usernameInfo.setFont(new Font("Serif", Font.BOLD, 30));
        usernameInfo.setPreferredSize(new Dimension(500,400));
        username = new JLabel();

        JPanel buttons = new JPanel();
        buttons.setPreferredSize(new Dimension(500, 400));
        buttons.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        exit = new JButton(GuestViewModel.EXIT_BUTTON_NAME);
        exit.setFont(new Font("Serif", Font.PLAIN, 25));
        exit.setPreferredSize(new Dimension(125, 40));
        exit.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        buttons.add(exit);

        startGame = new JButton(GuestViewModel.GAME_BUTTON_NAME);
        startGame.setFont(new Font("Serif", Font.PLAIN, 25));
        startGame.setPreferredSize(new Dimension(150, 40));
        startGame.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        buttons.add(startGame);

        exit.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(gamePic);
        this.add(title);
        this.add(buttons);
        exit.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(exit)){
                            guestController.exit();
                        }
                    }
                }
        );
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(startGame)){
                    guestController.startGame();
                }
            }
        });
    }


    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt){};
}
