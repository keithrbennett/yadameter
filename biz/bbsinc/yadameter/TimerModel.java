package biz.bbsinc.yadameter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;
    
/**
 * Model containing the timer's state information.
 * 
 * @author Keith R. Bennett
 * Copyright 2005, Bennett Business Solutions, Inc.
 */
public class TimerModel {
    	
    // To identify properties in PropertyChangeEvent's.
    public static final String SECONDS_ELAPSED = "SECONDS_ELAPSED";
    public static final String STATE           = "STATE";
    
    	public State offState      = new State(State.OFF, ColorInfo.BLUE, 0L);
    	public State cyanState     = new State(State.CYAN, ColorInfo.CYAN, 0L);
    	public State greenState    = new State(State.GREEN, ColorInfo.GREEN, 0L);
    	public State yellowState   = new State(State.YELLOW, ColorInfo.YELLOW, 0L);
    	public State redState      = new State(State.RED, ColorInfo.RED, 0L);
    	
    	private State state = offState;
        
    private long currentSecond   = -1L;
    	private long startTimeInMsec = -1L;
    	
    	private StateSequence stateSequence = new StateSequence();
    	
    	private Timer timer = new Timer(100, new TimerTickActionListener());
    	
    	private Context context;
        
    private PropertyChangeSupport propertyChangeSupport = 
            new PropertyChangeSupport(this);
    	
    
    /**
     * Creates a TimerModel with the specified context.
     * 
     * @param context_
     */
    	public TimerModel(Context context_) {
    		context = context_;
    	}
    	
    	//private boolean isTicking;
    	
    	// Need to keep this value in case clock is paused.
    	//private long timeElapsed;
    	
    /**
     * Returns the current state of the model (green, yellow, etc.)
     */
    	public State getState() {
    		return state;
    	}
    	

    /**
     * Sets the current state of the model (green, yellow, etc.)
     */
    private void setState(State newState) {
    		state = newState;
    	}
    	
    
    /**
     * Starts the timer.
     */
    	public void start() {
        currentSecond = -1;
        startTimeInMsec = System.currentTimeMillis();
        setState(cyanState);
        propertyChangeSupport.firePropertyChange(STATE, null, null);
        timer.start();
    	}
        

    /**
     * Sets the starting times, in milliseconds, 
     * for the green, yellow, and red states.
     * 
     * @param greenTime
     * @param yellowTime
     * @param redTime
     */
    protected void setStateStartTimes(long greenTime, long yellowTime, long redTime){
    	    greenState.setStartTime(greenTime);
        yellowState.setStartTime(yellowTime);
        redState.setStartTime(redTime);
    }
    	
    
    /**
     * Gets the starting time, in milliseconds.  This will be the value returned
     * by System.currentTimeMillis() when the timer is started.
     * 
     * @return start time, in milliseconds
     */
    	public long getStartTime() {
    		return startTimeInMsec;
    	}
    	
        
    /**
     * Stops the timer.
     */    
    	public void stop() {
        timer.stop();
    		setState(offState);
        propertyChangeSupport.firePropertyChange(STATE, null, null);
    	}

        
    /**
     * Gets the amount of time, in milliseconds, elapsed since the timer started.
     * 
     * @return time elapsed, in milliseconds
     */
    	public long getElapsedMsec() {
    		return System.currentTimeMillis() - startTimeInMsec;
    	}
    	
        
    /**
     * Returns whether or not the timer is currently on.
     * 
     * @return true if on, else false
     */    
    	public boolean isOn() {
    		return state != offState;
    	}
    	
        
    /**
     * Gets the current second (number of seconds elapsed).
     * 
     * @return number of seconds elapsed
     */
    public long getCurrentSecond() {
    	    return currentSecond;
    }

    
    /**
     * @param arg0
     */
    public void addPropertyChangeListener(PropertyChangeListener arg0) {
        propertyChangeSupport.addPropertyChangeListener(arg0);
    }


    /**
     * @param arg0
     * @param arg1
     */
    public void addPropertyChangeListener(String arg0,
            PropertyChangeListener arg1) {
        propertyChangeSupport.addPropertyChangeListener(arg0, arg1);
    }


    /**
     * @param arg0
     */
    public void removePropertyChangeListener(PropertyChangeListener arg0) {
        propertyChangeSupport.removePropertyChangeListener(arg0);
    }


    /**
     * @param arg0
     * @param arg1
     */
    public void removePropertyChangeListener(String arg0,
            PropertyChangeListener arg1) {
        propertyChangeSupport.removePropertyChangeListener(arg0, arg1);
    }

    
    private class StateSequence {
        
        private List sequenceList = new ArrayList();
                
        StateSequence() {
            sequenceList.add(offState);
            sequenceList.add(cyanState);
            sequenceList.add(greenState);
            sequenceList.add(yellowState);
            sequenceList.add(redState);
        }
                
        /**
         * Returns the next state in the sequence.
         * 
         * @return the next state in the list, or null if the
         * current state is invalid or the last state in the list
         */         
        State getNextState(State state) {
            int currIndex = sequenceList.indexOf(state);
            int nextIndex = currIndex + 1;
            boolean hasNext =
                    currIndex != -1 
                    &&
                    nextIndex < (sequenceList.size() -1);
            return hasNext
                    ? (State) sequenceList.get(currIndex + 1)
                    : (State) null;
        }
        
        
        public State calcCurrentState() {
            // If timer is stopped, return offState, else calculate based on
            // time elapsed.
            
                return isOn()
                    ? getState(getElapsedMsec())
                    : offState;
        }

        
        public State getState(long msecElapsed) {
            
            for (int i = sequenceList.size() - 1; i >= 0; i--) {
                State state = (State) sequenceList.get(i);
                if (msecElapsed >= state.getStartTime()) {
                    return state;
                 }
            }
            
            // should be unreachable
            return null;
        }

        
        private long getEndTime(State state) {
            State nextState = stateSequence.getNextState(state);
            return (nextState != null) 
                    ? nextState.getStartTime()
                    : Long.MAX_VALUE;
        }
    }

        
    	private class TimerTickActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (secondsHaveChanged()) {
				propertyChangeSupport.firePropertyChange(SECONDS_ELAPSED, null,
						null);
			}
			if (stateHasChanged()) {
				propertyChangeSupport.firePropertyChange(STATE, null, null);
			}
		}

        
		private boolean secondsHaveChanged() {
			long newSecond = getElapsedMsec() / 1000;
			boolean hasChanged = (newSecond > currentSecond);
			if (hasChanged) {
				currentSecond = newSecond;
			}
			return hasChanged;
		}


        private boolean stateHasChanged() {
			State newState = stateSequence.calcCurrentState();
			boolean hasChanged = (!newState.equals(state));
			state = newState;
			return hasChanged;
		}
	}
}
