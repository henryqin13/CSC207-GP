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

        JLabel title = new JLabel(SignupViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.USERNAME_LABEL), usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.PASSWORD_LABEL), passwordInputField);
        LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.REPEAT_PASSWORD_LABEL), repeatPasswordInputField);

        JPanel buttons = new JPanel();
        signUp = new JButton(SignupViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(signUp);
        cancel = new JButton(SignupViewModel.CANCEL_BUTTON_LABEL);
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
//        usernameInputField.addKeyListener(
//                new KeyListener() {
//                    @Override
//                    public void keyTyped(KeyEvent e) {
//                        SignupState currentState = signupViewModel.getState();
//                        String text = usernameInputField.getText() + e.getKeyChar();
//                        currentState.setUsername(text);
//                        signupViewModel.setState(currentState);
//                    }
//
//                    @Override
//                    public void keyPressed(KeyEvent e) {
//                        if (e.getKeyCode() == KeyEvent.VK_DELETE) {
//                            SignupState currentState = signupViewModel.getState();
//                            String oldUsername = usernameInputField.getText();
//                            String newUsername = oldUsername.substring(0, oldUsername.length() - 1);
//                            currentState.setUsername(newUsername);
//                            signupViewModel.setState(currentState);
//                        }
//
//
//                    }
//
//                    @Override
//                    public void keyReleased(KeyEvent e) {
//                    }
//                });
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

//        passwordInputField.addKeyListener(
//                new KeyListener() {
//                    @Override
//                    public void keyTyped(KeyEvent e) {
//                        SignupState currentState = signupViewModel.getState();
//                        currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
//                        signupViewModel.setState(currentState);
//                    }
//
//                    @Override
//                    public void keyPressed(KeyEvent e) {
//                        if (e.getKeyCode() == KeyEvent.VK_DELETE) {
//                            handleDeleteKey(passwordInputField, signupViewModel, true);
//                        }
//                    }
//
//                    @Override
//                    public void keyReleased(KeyEvent e) {
//
//                    }
//                }
//        );

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

//        repeatPasswordInputField.addKeyListener(
//                new KeyListener() {
//                    @Override
//                    public void keyTyped(KeyEvent e) {
//                        SignupState currentState = signupViewModel.getState();
//                        currentState.setRepeatPassword(repeatPasswordInputField.getText() + e.getKeyChar());
//                        signupViewModel.setState(currentState);
//                    }
//
//                    @Override
//                    public void keyPressed(KeyEvent e) {
//                        if (e.getKeyCode() == KeyEvent.VK_DELETE) {
//                            handleDeleteKey(repeatPasswordInputField, signupViewModel, false);
//                        }
//                    }
//
//                    @Override
//                    public void keyReleased(KeyEvent e) {
//                    }
//
//
//                }
//        );

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

        this.add(title);
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(repeatPasswordInfo);
        this.add(buttons);
    }

    private void handleDeleteKey(JTextField textField, SignupViewModel signupViewModel, boolean isPassword) {
        SignupState currentState = signupViewModel.getState();
        String oldText = textField.getText();

        if (!oldText.isEmpty()) {
            String newText = oldText.substring(0, oldText.length() - 1);

            if (isPassword) {
                currentState.setPassword(newText);
            } else {
                currentState.setRepeatPassword(newText);
            }

            signupViewModel.setState(currentState);
        }
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
