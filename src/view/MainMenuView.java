package view;

import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainMenuView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String gameName = "the city game";
    private final mainViewModel mainViewModel;

    final JButton signUp;
    final JButton logIn;
    final JButton guest;
    private final mainMenuController loginController;

    public MainMenuView(mainMenuViewModel loginViewModel, mainMenuController controller) {

        this.mainMenuController = controller;
        this.mainMenuViewModel = loginViewModel;
        this.mainMenuViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Sign up, Log in or join as a guest");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);


        JPanel buttons = new JPanel();
        logIn = new JButton(loginViewModel.LOGIN_BUTTON_LABEL);
        buttons.add(logIn);
        cancel = new JButton(loginViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        signUp = new JButton(signUpViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(signUp);
        guest = new JButton(guestViewModel.GUEST_BUTTON_LABEL);
        buttons.add(guest);

        cancel.addActionListener(this);
        });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        this.add(title);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }



}