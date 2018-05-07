package biz.bbsinc.yadameter;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JSplitPane;


/**
 * JPanel that contains the ControlPanel (for specifying times and otherwise 
 * controlling the timer) and the SignalPanel (for displaying the time).
 * 
 * @author Keith R. Bennett
 * Copyright 2004, Bennett Business Solutions, Inc.
 */
public class TimerPanel extends JPanel {
    
    private ControlPanel controlPanel;
    private SignalPanel signalPanel = new SignalPanel();
    private Context context;
    
    
    /**
     * Constructs the TimerPanel given the specified context.
     * 
     * @param context_ 
     */
    public TimerPanel(Context context_) {
        super(new BorderLayout());
        context = context_;
        controlPanel = new ControlPanel(context);
        JSplitPane splitPane = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT,
                controlPanel,
                signalPanel);
        add(splitPane, BorderLayout.CENTER);
    }
    
    public ControlPanel getControlPanel() {
        return controlPanel;
    }

	/**
	 * @return Returns the signalPanel.
	 */
	public SignalPanel getSignalPanel() {
		return signalPanel;
	}
    
    
    /**
     * Gets the amount of time, in milliseconds, at which the display will begin to
     * be in the green state.
     * 
     * @return the beginning of the green state, in milliseconds 
     */
    public long getGreenMsec() {
        return controlPanel.getGreenConfigPanel().getMilliseconds();
    }
    
    /**
     * Gets the amount of time, in milliseconds, at which the display will begin to
     * be in the yellow state.
     * 
     * @return the beginning of the yellow state, in milliseconds 
     */
    public long getYellowMsec() {
        return controlPanel.getYellowConfigPanel()
                .getMilliseconds();
    }

    /**
     * Gets the amount of time, in milliseconds, at which the display will begin to
     * be in the red state.
     * 
     * @return the beginning of the red state, in milliseconds 
     */
    public long getRedMsec() {
        return controlPanel.getRedConfigPanel().getMilliseconds();
    }
    
    
    /**
     * Gets the preferred size of the panel, currently 80% of the total screen
     * height by 80% of the total screen width.
     */
    public Dimension getPreferredSize() {
        Dimension screenSize
                = Toolkit.getDefaultToolkit().getScreenSize();
        int width  = screenSize.width  * 4 / 5;
        int height = screenSize.height * 4 / 5;
        return new Dimension(width, height);
    }
}
