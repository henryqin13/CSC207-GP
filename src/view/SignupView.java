package view;

import interface_adapter.Guest.GuestViewModel;
import interface_adapter.Guest.GuestController;
import interface_adapter.Signup.SignupController;
import interface_adapter.Signup.SignupState;
import interface_adapter.Signup.SignupViewModel;
import interface_adapter.CancelController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SignupView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "sign up";

    private final SignupViewModel signupViewModel;
    private final GuestViewModel guestViewModel;
    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);
    private final SignupController signupController;
    private final GuestController guestController;
    private final CancelController cancelController;

    private final JButton signUp;
    private final JButton cancel;

    // TODO Note: this is the new JButton for guesting the users file

    public SignupView(SignupController controller, SignupViewModel signupViewModel, GuestController guestController, GuestViewModel guestViewModel, CancelController cancelController) {

        this.signupController = controller;
        this.signupViewModel = signupViewModel;
        this.guestController = guestController;
        this.guestViewModel = guestViewModel;
        this.cancelController = cancelController;
        signupViewModel.addPropertyChangeListener(this);

        ImageIcon pic1 = new ImageIcon("pictures/toronto2.jpeg");
        Image oldSize = pic1.getImage();
        Image newSize = oldSize.getScaledInstance(1300, 550, 1);
        ImageIcon pic = new ImageIcon(newSize);

        JLabel gamePic = new JLabel(pic);
        gamePic.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title = new JLabel(SignupViewModel.TITLE_LABEL);
        title.setPreferredSize(new Dimension(1300, 400));
        title.setFont(new Font("Serif", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usernameLabel = new JLabel(SignupViewModel.USERNAME_LABEL);
        usernameLabel.setPreferredSize(new Dimension(250,50));
        usernameLabel.setFont(new Font("Serif", Font.BOLD, 18));

        JLabel passwordLabel = new JLabel(SignupViewModel.PASSWORD_LABEL);
        passwordLabel.setPreferredSize(new Dimension(250,50));
        passwordLabel.setFont(new Font("Serif", Font.BOLD, 18));

        JLabel repeatPasswordLabel = new JLabel(SignupViewModel.REPEAT_PASSWORD_LABEL);
        repeatPasswordLabel.setPreferredSize(new Dimension(250,50));
        repeatPasswordLabel.setFont(new Font("Serif", Font.BOLD, 18));

        LabelTextPanel usernameInfo = new LabelTextPanel(usernameLabel, usernameInputField);
        usernameInfo.setPreferredSize(new Dimension(600, 150));


        LabelTextPanel passwordInfo = new LabelTextPanel(passwordLabel, passwordInputField);
        passwordInfo.setPreferredSize(new Dimension(600, 150));


        LabelTextPanel repeatPasswordInfo = new LabelTextPanel(repeatPasswordLabel, repeatPasswordInputField);
        repeatPasswordInfo.setPreferredSize(new Dimension(600, 150));

        JPanel buttons = new JPanel();
        signUp = new JButton(SignupViewModel.SIGNUP_BUTTON_LABEL);
        signUp.setFont(new Font("Serif", Font.PLAIN, 25));
        signUp.setPreferredSize(new Dimension(125, 40));
        buttons.add(signUp);
        cancel = new JButton(SignupViewModel.CANCEL_BUTTON_LABEL);
        cancel.setFont(new Font("Serif", Font.PLAIN, 25));
        cancel.setPreferredSize(new Dimension(125, 40));
        buttons.add(cancel);

        // TODO Note: the following line instantiates the "guest" button; it uses
        //      a guest_BUTTON_LABEL constant which is defined in the SignupViewModel class.
        //      You need to add this "guest" button to the "buttons" panel.

        signUp.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(signUp)) {
                            SignupState currentState = signupViewModel.getState();

                            signupController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword(),
                                    currentState.getRepeatPassword()
                            );
                        }
                    }
                }
        );


        cancel.addActionListener(this);
        cancel.addActionListener(e -> handleCancel());

        // This makes a new KeyListener implementing class, instantiates it, and
        // makes it listen to keystrokes in the usernameInputField.
        //
        // Notice how it has access to instance variables in the enclosing class!
        usernameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        String text = usernameInputField.getText() + e.getKeyChar();
                        currentState.setUsername(text);
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if(e.getKeyCode() == KeyEvent.VK_DELETE){
                            SignupState currentState = signupViewModel.getState();
                            String oldUsername = usernameInputField.getText();
                            String newUsername = oldUsername.substring(0, oldUsername.length() - 1);
                            currentState.setUsername(newUsername);
                            signupViewModel.setState(currentState);
                        }


                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        passwordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if(e.getKeyCode() == KeyEvent.VK_DELETE){
                            SignupState currentState = signupViewModel.getState();
                            String oldPassword = passwordInputField.getText();
                            String newPassword = oldPassword.substring(0, oldPassword.length()- 1);
                            currentState.setPassword(newPassword);
                            signupViewModel.setState(currentState);}
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        repeatPasswordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        currentState.setRepeatPassword(repeatPasswordInputField.getText() + e.getKeyChar());
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if(e.getKeyCode() == KeyEvent.VK_DELETE){
                            SignupState currentState = signupViewModel.getState();
                            String oldRepeatedPassword = repeatPasswordInputField.getText();
                            String newRepeatedPassword = repeatPasswordInputField.getText().substring(0,
                                    oldRepeatedPassword.length() - 1);
                            currentState.setRepeatPassword(newRepeatedPassword);
                            signupViewModel.setState(currentState);
                        }
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                        }


                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(repeatPasswordInfo);
        this.add(buttons);
    }

    private void handleCancel() {
        cancelController.cancelSignUp();
    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(cancel)) {
            handleCancel();
            usernameInputField.setText("");
            passwordInputField.setText("");
            repeatPasswordInputField.setText("");
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SignupState state = (SignupState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
    }
}
