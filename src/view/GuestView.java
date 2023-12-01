package view;

import interface_adapter.Guest.GuestController;
import interface_adapter.Guest.GuestViewModel;
import interface_adapter.Guest.GuestState;

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


    public GuestView(GuestViewModel guestViewModel, GuestController guestController) {
        this.guestViewModel = guestViewModel;
        this.guestController = guestController;
        this.guestViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(guestViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usernameInfo = new JLabel("Currently logged in as guest");
        username = new JLabel();

        JPanel buttons = new JPanel();
        exit = new JButton(GuestViewModel.EXIT_BUTTON_NAME);
        buttons.add(exit);

        exit.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

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
    }


    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt){};
}
