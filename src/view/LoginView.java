package view;

import interface_adapter.Login.LoginController;
import interface_adapter.Login.LoginState;
import interface_adapter.Login.LoginViewModel;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import interface_adapter.CancelController;

public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {


    private final LoginViewModel loginViewModel;
    public final String viewName = "log in";
    final JTextField usernameInputField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();

    final JPasswordField passwordInputField = new JPasswordField(15);
    private final JLabel passwordErrorField = new JLabel();

    final JButton logIn;
    final JButton cancel;
    private final LoginController loginController;
    private final CancelController cancelController;

    public LoginView(LoginViewModel loginViewModel, LoginController controller, CancelController cancelController) {

        this.loginController = controller;
        this.loginViewModel = loginViewModel;
        this.loginViewModel.addPropertyChangeListener(this);
        this.cancelController = cancelController;

        ImageIcon pic1 = new ImageIcon("pictures/toronto.jpeg");
        Image oldSize = pic1.getImage();
        Image newSize = oldSize.getScaledInstance(900, 450, 1);
        ImageIcon pic = new ImageIcon(newSize);

        JLabel gamePic = new JLabel(pic);
        gamePic.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title = new JLabel("Please enter your username and password");
        title.setPreferredSize(new Dimension(1300, 150));
        title.setFont(new Font("Serif", Font.BOLD, 30));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usernameLabel = new JLabel(loginViewModel.USERNAME_FIELD);
        usernameLabel.setPreferredSize(new Dimension(140,50));
        usernameLabel.setFont(new Font("Serif", Font.BOLD, 18));

        JLabel passwordLabel = new JLabel(loginViewModel.PASSWORD_FIELD);
        passwordLabel.setPreferredSize(new Dimension(140,50));
        passwordLabel.setFont(new Font("Serif", Font.BOLD, 18));

        LabelTextPanel usernameInfo = new LabelTextPanel(usernameLabel, usernameInputField);
        usernameInfo.setPreferredSize(new Dimension(600, 50));

        LabelTextPanel passwordInfo = new LabelTextPanel(passwordLabel, passwordInputField);
        passwordInfo.setPreferredSize(new Dimension(600, 50));

        JPanel buttons = new JPanel();
        logIn = new JButton(loginViewModel.LOGIN_BUTTON_NAME);
        logIn.setFont(new Font("Serif", Font.PLAIN, 25));
        logIn.setPreferredSize(new Dimension(125, 40));
        buttons.add(logIn);

        cancel = new JButton(loginViewModel.CANCEL_BUTTON_NAME);
        cancel.setFont(new Font("Serif", Font.PLAIN, 25));
        cancel.setPreferredSize(new Dimension(125, 40));
        buttons.add(cancel);

        logIn.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logIn)) {
                            LoginState currentState = loginViewModel.getState();

                            loginController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword()
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

                LoginState currentState = loginViewModel.getState();
                currentState.setUsername(username);
                currentState.setPassword(password);
                loginViewModel.setState(currentState);
            }
        });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

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

                LoginState currentState = loginViewModel.getState();
                currentState.setUsername(username);
                currentState.setPassword(password);
                loginViewModel.setState(currentState);
            }
        });

        this.add(gamePic);
        this.add(title);
        this.add(usernameInfo);
        this.add(usernameErrorField);
        this.add(passwordInfo);
        this.add(passwordErrorField);
        this.add(buttons);
    }

    private void handleCancel() {
        cancelController.cancelLogIn();
    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(cancel)) {
            handleCancel();
            usernameInputField.setText("");
            passwordInputField.setText("");
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoginState state = (LoginState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(LoginState state) {
        usernameInputField.setText(state.getUsername());
    }
}

