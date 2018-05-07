package biz.bbsinc.yadameter;
import java.awt.Color;

public class State {
    
    public static final String OFF    = "Off";
    public static final String CYAN   = "Cyan";
    public static final String GREEN  = "Green";
    public static final String YELLOW = "Yellow";
    public static final String RED    = "Red";
    
    private String name;
    private ColorInfo colorInfo;
    private long startTime;
    private Color foregroundColor;
    
    public State(String name_, ColorInfo colorInfo_, long startTime_) {
        name      = name_;
        colorInfo = colorInfo_;
        startTime = startTime_;
    }
    
    public String getName() {
    	    return name;
    }
    
    public ColorInfo getColorInfo() {
    	    return colorInfo;
    }
    
    public Color getColor() {
        return getColorInfo().getColor();
    }
    
    public Color getForegroundColor() {
        return getColorInfo().getTextColor();
    }
    
    public String getColorDisplayName() {
        return getColorInfo().getDisplayName();
    }
    
    public long getStartTime() {
        return startTime;
    }
    
    public void setStartTime(long time) {
    	    startTime = time;
    }
    
    public String toString() {
        return "State: color name = " + getColorDisplayName()
                + ", startTime = " + getStartTime()
                + ", color = " + getColor()
                + ", foregoundColor = " + getForegroundColor();
    }
}

