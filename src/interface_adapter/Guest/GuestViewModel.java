package interface_adapter.Guest;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GuestViewModel extends ViewModel {
    public final String TITLE = "Guest";

    public static final String GUEST_BUTTON_NAME = "Guest";

    public static final String EXIT_BUTTON_NAME = "Exit";

    private GuestState state = new GuestState();
    public GuestViewModel(){
        super("Log in");
    }
    public void setState(GuestState state) {
        this.state = state;
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public void firePropertyChanged(){
        support.firePropertyChange("state", null, this.state);
    }
    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }
    public GuestState getState(){
        return state;
    }
}
