package interface_adapter.MainMenu;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;

public class MainMenuViewModel extends ViewModel {
    public MainMenuViewModel(){
        super("the city game");
    }
    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}
