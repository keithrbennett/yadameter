package biz.bbsinc.yadameter;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;

/**
 * The Yadameter application provides a timer conforming to the
 * requirements of Toastmaster (http://www.toastmasters.org) speech timing.
 * There are four possible states during a speech.  
 * 
 * Traditionally, colored cards are used to signal the state to the speaker.
 * At the beginning of the speech, no card is displayed.  Then, at some point, the
 * green card is displayed, then the yellow card, then the red card.
 * 
 * The user specifies the length of these three periods.  As per Toastmasters custom,
 * the durations are specified by the start time of the state, relative 
 * to the beginning of the speech.
 * 
 * For example, let's say the states should be as follows:
 * 
 * 0     1     2     3     4     5     6     7     8     9     10
 * |-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|---->
 * |----- no color --------|-- green --------|- yellow --| -- red -->
 * 
 * Then the states would be specified as follows:
 * 
 * Green:   4:00
 * Yellow:  7:00
 * Red:     9:00
 * 
 * Although traditionally the pre-green period is represented by the absence of any
 * cues, this program displays cyan during this time.
 * 
 * @author Keith R. Bennett
 * Copyright 2005, Bennett Business Solutions, Inc.
 */


//TODO: I18N
//TODO: About dialog
//TODO: Move classes to biz.bbsinc
//TODO: Applet'ize
//TODO: Recent Times Panel
//TODO: Disable all buttons & combo boxes while timer is running


public class Main {

    
    private static TimerFrame timerFrame;

    
    public static void main(String [] args) {
        TimerFrame frame = getTimerFrame();
        frame.setLocation(getCenteredLocation(frame));
        frame.setVisible(true);
    }
    
    
    /**
     * Returns the TimerFrame, creating it if necessary.
     * 
     * @return the frame containing the timer
     */
    static TimerFrame getTimerFrame() {
        if (timerFrame == null) {
            timerFrame = new TimerFrame();      
        }
        return timerFrame;
    }
    
    /**
     * Gets the point at which the window (or, more precisely, the window's upper left corner
     * should be positioned so that it will be centered on the screen.
     * 
     * @param window the window to center on the screen
     * @return point at which to position the window
     */
    private static Point getCenteredLocation(Window window) {
        Dimension screenSize
                = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = window.getPreferredSize();
        
        int newX = (screenSize.width  - windowSize.width) / 2;
        int newY = (screenSize.height - windowSize.height) / 2;
    
        return new Point(newX, newY);
    }
}
