package Adapter.View;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewModel {
    /* 
    public static final String TITLE_LABEL = "ID Search View";
    public static final String SEARCH_LABEL = "Enter player name";
    public static final String SEARCH_BUTTON_LABEL = "Get ID";
    public static final String BACK_BUTTON_LABEL = "Back";
    */
    private ViewState state = new ViewState();

    public ViewModel() {}

    public void setState(ViewState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public ViewState getState() {
        return state;
    }
}
