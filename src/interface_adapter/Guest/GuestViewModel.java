package interface_adapter.Guest;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GuestViewModel extends ViewModel {
    public final String TITLE_LABEL = "You're logged in as a guest";

    public static final String BACK_BUTTON_NAME = "Back";
    public static final String GAME_BUTTON_NAME = "Start Game";
    public GuestViewModel(){
        super("playing as guest");
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }

    @Override
    public void firePropertyChanged(){
    }
}
