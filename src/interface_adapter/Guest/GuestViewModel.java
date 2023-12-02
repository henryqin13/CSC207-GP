package interface_adapter.Guest;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GuestViewModel extends ViewModel {
    public final String TITLE_LABEL = "You're logged in as a guest";

    public static final String EXIT_BUTTON_NAME = "Exit";

    public String guestUser;
    private GuestState state = new GuestState();
    public GuestViewModel(){
        super("playing as guest");
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

    public String getGuestUser() {
        return guestUser;
    }

    public void setGuestUser(String guestUser) {
        this.guestUser = guestUser;
    }
}
