package biz.bbsinc.yadameter;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;


/**
 * This is the panel where the user specifies the time periods
 * for the various color stages.  It contains three ColorConfigPanels,
 * one for each of the colors to display after the initial state
 * expires.
 * 
 * @author Keith R. Bennett
 * Copyright 2005, Bennett Business Solutions, Inc.
 */
public class ControlPanel extends JPanel {
    
    private ColorConfigPanel greenConfigPanel 
            = new ColorConfigPanel(ColorInfo.GREEN, "Green", 0, 3);
    private ColorConfigPanel yellowConfigPanel
            = new ColorConfigPanel(ColorInfo.YELLOW, "Yellow", 0, 6);
    private ColorConfigPanel redConfigPanel
            = new ColorConfigPanel(ColorInfo.RED, "Red", 0, 9);
    
    private Context context;
    
    private JButton startStopButton;
    
    
    /**
     * Creates a control panel with the specified parameter.
     * 
     * @param context_ context object
     */
    public ControlPanel(Context context_) {
        super(new BorderLayout());
        context = context_;
        add(createTopPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
    }
    
    
    private JPanel createTopPanel() {
    	    JPanel panel = new JPanel(new BorderLayout());
        panel.add(createPresetPanel(), BorderLayout.WEST);
        panel.add(createColorConfigPanel(), BorderLayout.EAST);
        return panel;
    }
    
    
    private JPanel createColorConfigPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(greenConfigPanel);
        panel.add(yellowConfigPanel);
        panel.add(redConfigPanel);
        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        startStopButton = new JButton(new StartStopAction(context));
        panel.add(startStopButton);
        return panel;
    }

    
    /**
     * Returns the panel containing input components used to specify the green
     * state's information.
     * 
     * @return the green state's config panel
     */
    public ColorConfigPanel getGreenConfigPanel() {
        return greenConfigPanel;
    }
    

    /**
     * Returns the panel containing input components used to specify the yellow
     * state's information.
     * 
     * @return the yellow state's config panel
     */
    public ColorConfigPanel getYellowConfigPanel() {
        return yellowConfigPanel;
    }
    
    
    /**
     * Returns the panel containing input components used to specify the red
     * state's information.
     * 
     * @return the red state's config panel
     */
    public ColorConfigPanel getRedConfigPanel() {
        return redConfigPanel;
    }
    
    
    /**
     * Returns the button used to start and stop the timer.
     * 
     * @return the button used to start and stop the timer
     */
    public JButton getStartStopButton() {
        return startStopButton;
    }
    
    
    protected JPanel createPresetPanel() {
        TimerController controller = context.getTimerController();
        
        PresetAction [] actions = {
                new PresetAction("Test", controller, 
                        TimeDuration.getMilliseconds(0, 0, 3),
                        TimeDuration.getMilliseconds(0, 0, 6),
                        TimeDuration.getMilliseconds(0, 0, 9)
                ),
                new PresetAction("Speech", controller, 
                        TimeDuration.getMilliseconds(0, 5, 0),
                        TimeDuration.getMilliseconds(0, 6, 0),
                        TimeDuration.getMilliseconds(0, 7, 0)
                ),
                new PresetAction("Table Topics", controller, 
                        TimeDuration.getMilliseconds(0, 1, 0),
                        TimeDuration.getMilliseconds(0, 1, 30),
                        TimeDuration.getMilliseconds(0, 2, 0)
                ),
                new PresetAction("Ice Breaker", controller, 
                        TimeDuration.getMilliseconds(0, 3, 0),
                        TimeDuration.getMilliseconds(0, 4, 0),
                        TimeDuration.getMilliseconds(0, 5, 0)
                ),
                new PresetAction("Evaluation", controller, 
                        TimeDuration.getMilliseconds(0, 2, 0),
                        TimeDuration.getMilliseconds(0, 2, 30),
                        TimeDuration.getMilliseconds(0, 3, 0)
                ),
        };
        
        JPanel panel = new JPanel(new GridLayout(3, 0));
        for (int i = 0; i < actions.length; i++) {
            panel.add(new JButton(actions[i]));
        }
        
        return panel;
    }
}
