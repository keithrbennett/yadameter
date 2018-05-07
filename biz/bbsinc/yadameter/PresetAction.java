package biz.bbsinc.yadameter;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 * This class encapsulates a preset (as in radio preset) time interval.
 * These presets will be available to the user via a button so that there
 * is no need to explicitly specify the minutes and seconds.
 * 
 * @author Keith R. Bennett
 * Copyright 2004, Bennett Business Solutions, Inc.
 */
public class PresetAction extends AbstractAction {

    private String prefix;
    private TimerController controller;
    private long greenMsec;
    private long yellowMsec;
    private long redMsec;
    
    
    /**
     * Creates an instance with the specified parameters.
     * 
     * @param prefix_ name of the preset (e.g. "Table Topics", which is 1.0,
     * 1.5, and 2.0 minutes)
     * @param controller_
     * @param greenMsec_ time to start green
     * @param yellowMsec_ time to start yellow
     * @param redMsec_ time to start red
     */
    public PresetAction(String prefix_, TimerController controller_, long greenMsec_,
            long yellowMsec_, long redMsec_) {
    	    prefix     = prefix_;
        controller = controller_;
        greenMsec  = greenMsec_;
        yellowMsec = yellowMsec_;
        redMsec    = redMsec_;
        putValue(Action.NAME, getName());
    }
    
    
    /**
     * Sets the timer with the color settings from this instance.
     */
    public void actionPerformed(ActionEvent e) {
       controller.setTimes(greenMsec, yellowMsec, redMsec);
    }
    
    
    /**
     * Gets the display string for this action, including the human readable
     * caption and the times (e.g. "Table Topics: 1:00, 1:30, 2:00).
     * 
     * @return the display string described above
     */
    private String getName() {
    	    String name = TimerUtilities.getTimeDisplayString(greenMsec)
            + ", "
            + TimerUtilities.getTimeDisplayString(yellowMsec)
            + ", " 
            + TimerUtilities.getTimeDisplayString(redMsec);
        if (prefix != null && prefix.length() > 0) {
        	    name = prefix + ": " + name;
        }
        return name;
    }
}
