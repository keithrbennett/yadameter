package biz.bbsinc.yadameter;
import javax.swing.Action;
import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;


public class StartStopAction extends AbstractAction {

    private Context context;
    
    public StartStopAction(Context context_) {
        super("Start");
        context = context_;
    }
    
    public void actionPerformed(ActionEvent e) {
        
        if (getTimerModel().isOn()) {            
            stop();
        } else {
            start();
        }
        putValue(Action.NAME, getName());
    }
    
    private void start() {
        ControlPanel controlPanel = context.getControlPanel();
        long greenTime  = controlPanel.getGreenConfigPanel().getMilliseconds();
        long yellowTime = controlPanel.getYellowConfigPanel().getMilliseconds();
        long redTime    = controlPanel.getRedConfigPanel().getMilliseconds();
        getTimerModel().setStateStartTimes(greenTime, yellowTime, redTime);
        getTimerModel().start();
    }
    
    
    private void stop() {
        getTimerModel().stop();
        
    }
    
    private String getName() {
        return getTimerModel().isOn() ? "Stop" : "Start";
    }

    private TimerModel getTimerModel() {
    	    return context.getTimerModel();
    }
}


