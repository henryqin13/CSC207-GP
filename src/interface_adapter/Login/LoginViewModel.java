package interface_adapter.Login;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginViewModel extends ViewModel {
    public final String TITLE = "log in";
    public final String USERNAME_FIELD = "Enter Username";
    public final String PASSWORD_FIELD = "Enter Password";

    public static final String LOGIN_BUTTON_NAME = "Log in";
    public static final String CANCEL_BUTTON_NAME = "Cancel";

    private LoginState state = new LoginState();
    public LoginViewModel(){
        super("log in");
    }
    public void setState(LoginState state) {
        this.state = state;
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public void firePropertyChanged(){
        support.firePropertyChange("state", null, this.state);
    }
    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }
    public LoginState getState(){
        return state;
    }
}
