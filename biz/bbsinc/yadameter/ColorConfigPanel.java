package biz.bbsinc.yadameter;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * This is the panel where the user specifies the duration of
 * the state, in minutes and seconds.
 * 
 * @author Keith R. Bennett
 * Copyright 2005, Bennett Business Solutions, Inc.
 */
public class ColorConfigPanel extends JPanel {
    
    private ColorInfo colorInfo;
    private String colorName;
    private JLabel startLabel = new MinWidthJLabel(120);
    private JLabel minLabel = new MinWidthJLabel(40);
    private MinWidthJLabel secLabel = new MinWidthJLabel(40);
    
    private JComboBox minComboBox = new JComboBox();
    private JComboBox secComboBox = new JComboBox();
    
    
    /**
     * Creates an instance with the specified values.
     * 
     * @param colorInfo_ information regarding the initial default color scheme
     * @param colorName_ name of initial default color scheme
     * @param initialMinutes initial setting of minutes
     * @param initialSeconds initial setting of minutes
     */
    public ColorConfigPanel(ColorInfo colorInfo_, String colorName_,
            int initialMinutes, int initialSeconds) {
        super();
        colorInfo = colorInfo_;
        colorName = colorName_;
        init(initialMinutes, initialSeconds);
    }
    
    
    /**
     * Initialize the panel.
     * 
     * @param initialMinutes the initial value of the minutes input component
     * @param initialSeconds the initial value of the seconds input component
     */
    private void init(int initialMinutes, int initialSeconds) {
        
        minComboBox.setModel(createComboBoxModel(0, 121, 1));
        secComboBox.setModel(createComboBoxModel(0, 60, 1));
        
        startLabel.setText("Start " + colorName + " at: ");
        startLabel.setForeground(colorInfo.getTextColor());
        startLabel.setBackground(colorInfo.getColor());
        add(startLabel);      
        
        minLabel.setText("min");
        secLabel.setText("sec");
        
        Component strut = Box.createHorizontalStrut(5);
        
        add(strut);        
        add(minComboBox);
        add(minLabel);
        
        add(strut);        
        add(secComboBox);
        add(secLabel);
        
        minLabel.setLabelFor(minComboBox);
        secLabel.setLabelFor(secComboBox);
        
        minComboBox.setSelectedItem(new Integer(initialMinutes));
        secComboBox.setSelectedItem(new Integer(initialSeconds));
    }

    
    /**
     * Creates the combo box model with the specified values.
     * 
     * @param startNum number to start with, probably zero
     * @param endNum number to end at (e.g. 59 for minutes/seconds)
     * @param interval interval between combo box items (probably 1)
     * @return the newly created combo box model
     */
    private ComboBoxModel createComboBoxModel(
            int startNum, int endNum, int interval) {

        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for (int i = startNum; i < endNum; i += interval) {
            model.addElement(new Integer(i));
        }
        return model;       
    }
       
    
    /**
     * Converts the minutes and seconds specified by the user to milliseconds.
     * 
     * @return the number of milliseconds specified by the user
     */
    public long getMilliseconds() {
        int minutes = ((Integer) minComboBox.getSelectedItem())
                .intValue();
        int seconds = ((Integer) secComboBox.getSelectedItem())
                .intValue();
        long msec = 1000 * ((minutes * 60) + seconds);
        return msec;
    }
    
    
    /**
     * Sets the minutes and seconds input components to the values appropriate
     * for the specified number of milliseconds (e.g. 90000 msec --> 1 min, 30 sec).
     * 
     * @param msec number of milliseconds to convert to minutes/seconds
     */
    public void setMilliseconds(long msec) {
    	    // Round to nearest second
        long totalSeconds = (msec + 500) / 1000;
        long minutes = totalSeconds / 60;
        long seconds = totalSeconds % 60;
        
        minComboBox.setSelectedItem(new Integer((int) minutes));
        secComboBox.setSelectedItem(new Integer((int) seconds));
    }
}

