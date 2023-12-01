
package view;

import interface_adapter.Guest.GuestViewModel;
import interface_adapter.Login.LoginController;
import interface_adapter.Login.LoginState;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.Signup.SignupState;
import interface_adapter.Signup.SignupViewModel;
import interface_adapter.MainMenu.MainMenuViewModel;
import interface_adapter.MainMenu.MainMenuController;

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
    private final MainMenuViewModel mainMenuViewModel;

    private final LoginViewModel loginViewModel;
    private final SignupViewModel signUpViewModel;
    private final GuestViewModel guestViewModel;

    final JButton signUp;
    final JButton logIn;
    final JButton guest;
    private final MainMenuController mainMenuController;

    public MainMenuView(MainMenuViewModel mainMenuViewModel, MainMenuController mainMenucontroller,
                        SignupViewModel signUpViewModel, LoginViewModel loginViewModel, GuestViewModel guestViewModel) {

        this.mainMenuController = mainMenucontroller;
        this.mainMenuViewModel = mainMenuViewModel;
        this.signUpViewModel = signUpViewModel;
        this.loginViewModel = loginViewModel;
        this.guestViewModel = guestViewModel;
        this.mainMenuViewModel.addPropertyChangeListener(this);


        JLabel title = new JLabel("Sign up, Log in or join as a guest");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);


        JPanel buttons = new JPanel();
        signUp = new JButton(signUpViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(signUp);
        logIn = new JButton(loginViewModel.LOGIN_BUTTON_NAME);
        buttons.add(logIn);
        guest = new JButton(signUpViewModel.GUEST_BUTTON_LABEL);
        buttons.add(guest);

        signUp.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(signUp)) {
                            mainMenuController.signup();
                        }
                    }
                }
        );
        logIn.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logIn)) {
                            mainMenuController.login();
                        }
                    }
                });

        guest.addActionListener(
                new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource().equals(guest)) {
                mainMenuController.guest();
            }
        }});

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        this.add(title);
        this.add(buttons);
    }


    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
