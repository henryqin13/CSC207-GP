package view;

import interface_adapter.Guest.GuestViewModel;
import interface_adapter.Guest.GuestController;
import interface_adapter.Signup.SignupController;
import interface_adapter.Signup.SignupState;
import interface_adapter.Signup.SignupViewModel;
import interface_adapter.CancelController;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SignupView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "sign up";

    private final SignupViewModel signupViewModel;
    private final GuestViewModel guestViewModel;
    final JTextField usernameInputField = new JTextField(15);
    final JPasswordField passwordInputField = new JPasswordField(15);
    final JPasswordField repeatPasswordInputField = new JPasswordField(15);
    private final SignupController signupController;
    private final GuestController guestController;
    private final CancelController cancelController;

    final JButton signUp;
    final JButton cancel;

    public SignupView(SignupController controller, SignupViewModel signupViewModel, GuestController guestController, GuestViewModel guestViewModel, CancelController cancelController) {

        this.signupController = controller;
        this.signupViewModel = signupViewModel;
        this.guestController = guestController;
        this.guestViewModel = guestViewModel;
        this.cancelController = cancelController;
        signupViewModel.addPropertyChangeListener(this);

        ImageIcon pic1 = new ImageIcon("pictures/toronto.jpeg");
        Image oldSize = pic1.getImage();
        Image newSize = oldSize.getScaledInstance(900, 450, 1);
        ImageIcon pic = new ImageIcon(newSize);

        JLabel gamePic = new JLabel(pic);
        gamePic.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title = new JLabel(SignupViewModel.TITLE_LABEL);
        title.setPreferredSize(new Dimension(1300, 400));
        title.setFont(new Font("Serif", Font.BOLD, 30));
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

        usernameInputField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateFields();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateFields();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateFields();
            }

            private void updateFields() {
                String username = usernameInputField.getText();
                String password = passwordInputField.getText();
                String repeatPassword = repeatPasswordInputField.getText();

                SignupState currentState = signupViewModel.getState();
                currentState.setUsername(username);
                currentState.setPassword(password);
                currentState.setRepeatPassword(repeatPassword);
                signupViewModel.setState(currentState);
            }
        });

        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateFields();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateFields();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateFields();
            }

            private void updateFields() {
                String username = usernameInputField.getText();
                String password = passwordInputField.getText();
                String repeatPassword = repeatPasswordInputField.getText();

                SignupState currentState = signupViewModel.getState();
                currentState.setUsername(username);
                currentState.setPassword(password);
                currentState.setRepeatPassword(repeatPassword);
                signupViewModel.setState(currentState);
            }
        });

        repeatPasswordInputField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateFields();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateFields();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateFields();
            }

            private void updateFields() {
                String username = usernameInputField.getText();
                String password = passwordInputField.getText();
                String repeatPassword = repeatPasswordInputField.getText();

                SignupState currentState = signupViewModel.getState();
                currentState.setUsername(username);
                currentState.setPassword(password);
                currentState.setRepeatPassword(repeatPassword);
                signupViewModel.setState(currentState);
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(gamePic);
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
