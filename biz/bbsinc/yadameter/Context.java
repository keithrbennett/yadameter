package biz.bbsinc.yadameter;
/**
 * Contains context information usable by any part of the system.
 *   
 * @author Keith R. Bennett
 * Copyright 2005, Bennett Business Solutions, Inc.
 */
public class Context {
	
    /**
     * This is the main frame used for the application.
     */
	private TimerFrame timerFrame;
	
    
    /**
     * Creates an instance with the specified parameter.
     * 
     * @param frame frame containing the timer
     */
	public Context(TimerFrame frame) {
		timerFrame = frame;
	}
	
	
    /**
     * Returns the frame containing the timer.
     * 
     * @return the frame containing the timer
     */
	public TimerFrame getTimerFrame() {
		return timerFrame;
	}
	
	
    /**
     * Returns the timer panel.
     * 
     * @return the timer panel
     */
	public TimerPanel getTimerPanel() {
		return getTimerFrame().getTimerPanel();
	}
	
    
    /**
     * Returns the timer's controller.
     * 
     * @return the timer's controller
     */
	public TimerController getTimerController() {
		return getTimerFrame().getTimerController();
	}
	
    
    /**
     * Returns the timer's model
     * 
     * @return the timer's model
     */
	public TimerModel getTimerModel() {
		return getTimerFrame().getTimerModel();
	}
	
	
    /**
     * Returns the timer panel's control panel (the panel used by the user to
     * specify the timer's behavior).
     * 
     * @return the timer panel's control panel
     */
	public ControlPanel getControlPanel() {
		return getTimerPanel().getControlPanel();
	}
	
	
    /**
     * Returns the panel used to signal time information (the colored rectangle
     * and optionally the time elapsed) to the user.
     * 
     * @return the panel used to signal time information to the user
     */
    public SignalPanel getSignalPanel() {
		return getTimerPanel().getSignalPanel();
	}
}
