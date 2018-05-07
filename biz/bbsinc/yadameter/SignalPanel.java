package biz.bbsinc.yadameter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * This is the panel that expresses the signal (solid colored rectangle)
 * and the elapsed time to the speaker.
 *
 * @author Keith R. Bennett
 * Copyright 2005, Bennett Business Solutions, Inc.
 */
public class SignalPanel extends JPanel {

    // Font size for the elapsed time label
    private static int DEFAULT_FONT_SIZE = 250;
    private static int MIN_FONT_SIZE     =   4;
    private static int MAX_FONT_SIZE     = 400;
    
    private JLabel clockLabel = new JLabel("", SwingConstants.CENTER);
    private JSlider numberFontSizeSlider;
    private int numberFontSize = DEFAULT_FONT_SIZE;
    private JLabel statusLabel = new JLabel("",  JLabel.RIGHT);
    
    public SignalPanel() {
        super(new BorderLayout());
        setBackgroundColor(Color.BLUE);
        setForegroundColor(Color.BLACK);
        init();
    }
    
    private void init() {
    	    // Base the new larger font on the current font of the label.
        Font newFont = clockLabel.getFont().deriveFont((float) numberFontSize);
        clockLabel.setFont(newFont);

        // Let the color of the panel show in the background of the label.
        clockLabel.setOpaque(false);
        add(clockLabel, BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);
    }
    
    
    private JPanel createBottomPanel() {
        
    	    JPanel panel = new JPanel(new BorderLayout());
    	    panel.add(createSliderPanel(), BorderLayout.WEST);
        return panel;
    }
    
    
    private JPanel createSliderPanel() {
    	    numberFontSizeSlider = 
                new JSlider(MIN_FONT_SIZE, MAX_FONT_SIZE, numberFontSize);
        JLabel captionLabel = new JLabel("Number Font Size: ");
        numberFontSizeSlider.addChangeListener(new NumberFontSizeChangeListener());
        JPanel panel = new JPanel();
        panel.add(captionLabel);
        panel.add(numberFontSizeSlider);
        return panel;
    }
    
    public Dimension getPreferredSize() {
    	    // Arbitrary size; may need to change this or make it configurable based
        // on user feedback.  Even better would be to save the size of the window
        // in a properties file on exit so that if the user resized it, it would
        // assume that size on next startup.
        return new Dimension(300, 300);
    }

    public void setBackgroundColor(Color color) {
        setBackground(color);
    }
    
    
    public void setForegroundColor(Color color) {
        clockLabel.setForeground(color);
    }
    
    
    public void setNumberFontSize(int newFontSize) {
        Font newFont = clockLabel.getFont().deriveFont((float) newFontSize);
        clockLabel.setFont(newFont);
    }

    
    /**
     * Sets the time displayed in the label in 0:00:00 format.
     * @param milliseconds the number of milliseconds
     */
    public void setTimeDisplayed(long milliseconds) {
        clockLabel.setText(TimerUtilities.getTimeDisplayString(milliseconds));
    }
    
    
    public void setStatusText(String s) {
    	    statusLabel.setText(s);
    }

    
    private class NumberFontSizeChangeListener implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            setNumberFontSize(numberFontSizeSlider.getValue());
        }
    }

}
