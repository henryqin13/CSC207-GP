package view;

import interface_adapter.Guest.GuestViewModel;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.Signup.SignupViewModel;
import interface_adapter.MainMenu.MainMenuViewModel;
import interface_adapter.MainMenu.MainMenuController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        ImageIcon pic1 = new ImageIcon("pictures/toronto.jpeg");
        Image oldSize = pic1.getImage();
        Image newSize = oldSize.getScaledInstance(900, 450, 1);
        ImageIcon pic = new ImageIcon(newSize);

        JLabel gamePic = new JLabel(pic);
        gamePic.setAlignmentX(Component.CENTER_ALIGNMENT);


        JLabel gameName = new JLabel("CityGuesser");
        gameName.setPreferredSize(new Dimension(900, 400));
        gameName.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameName.setFont(new Font("Serif", Font.BOLD, 50));

        JLabel title = new JLabel("Sign up, Log in or join as a guest.");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setPreferredSize(new Dimension(900, 100));
        title.setFont(new Font("Serif", Font.PLAIN, 20));

        JPanel buttons = new JPanel();
        signUp = new JButton(signUpViewModel.SIGNUP_BUTTON_LABEL);
        signUp.setFont(new Font("Serif", Font.PLAIN, 25));
        signUp.setPreferredSize(new Dimension(125, 40));
        buttons.add(signUp);
        logIn = new JButton(loginViewModel.LOGIN_BUTTON_NAME);
        logIn.setFont(new Font("Serif", Font.PLAIN, 25));
        logIn.setPreferredSize(new Dimension(125, 40));
        buttons.add(logIn);
        guest = new JButton(signUpViewModel.GUEST_BUTTON_LABEL);
        guest.setFont(new Font("Serif", Font.PLAIN, 25));
        guest.setPreferredSize(new Dimension(125, 40));
        buttons.add(guest);
        buttons.setPreferredSize(new Dimension(900, 300));

        signUp.setName("signUp");
        logIn.setName("logIn");
        guest.setName("guest");


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

        this.add(gamePic);
        this.add(gameName);
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
