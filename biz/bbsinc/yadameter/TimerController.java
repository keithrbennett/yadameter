package biz.bbsinc.yadameter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * 
 * @author Keith R. Bennett
 * Copyright 2005, Bennett Business Solutions, Inc.
 */
public class TimerController implements PropertyChangeListener {
    
    private Context context;
    
    
    public TimerController(Context context_) {
    	    context = context_;
        context.getTimerModel().addPropertyChangeListener(this);
    }
    
    
    public void setDisplayTime(long milliseconds) {
        getSignalPanel().setTimeDisplayed(milliseconds);
    }
    
    
    private TimerPanel getTimerPanel() {
    	    return context.getTimerPanel();
    }
    
    
    private SignalPanel getSignalPanel() {
    	    return getTimerPanel().getSignalPanel();
    }
    
    public void propertyChange(PropertyChangeEvent e) {
        String propertyName = e.getPropertyName();
        TimerModel model = context.getTimerModel();
        SignalPanel signalPanel = context.getSignalPanel();
        
    	    if (propertyName == TimerModel.STATE || propertyName == null) {
    	    	    State newState = model.getState();
	    	    signalPanel.setBackgroundColor(newState.getColor());
	    	    signalPanel.setForegroundColor(newState.getForegroundColor());
            if (newState.getName().equals(State.CYAN)) {
            	    signalPanel.setTimeDisplayed(0L);
            }
        }
        if (propertyName == TimerModel.SECONDS_ELAPSED || propertyName == null) {
            if (model.isOn()) {
            	    signalPanel.setTimeDisplayed(model.getElapsedMsec());
            }
        }
    }

    
    public void setTimes(long greenMsec, long yellowMsec, long redMsec) {
    	    ControlPanel controlPanel = context.getControlPanel();
        controlPanel.getGreenConfigPanel().setMilliseconds(greenMsec);
        controlPanel.getYellowConfigPanel().setMilliseconds(yellowMsec);
        controlPanel.getRedConfigPanel().setMilliseconds(redMsec);
    }
    
}
