package biz.bbsinc.yadameter;
import java.awt.Color;

/**
 * This is an immutable class containing a background and foregroud java.awt.Color
 * and a properties key string for its name.
 * 
 * @author Keith R. Bennett
 * Copyright 2005, Bennett Business Solutions, Inc.
 */
public class ColorInfo {
    
    private Color color;
    private Color textColor;
    private String colorNameKey;
    
    
    static public final ColorInfo BLUE =
        new ColorInfo(Color.BLUE, Color.WHITE, "Blue");
    
    static public final ColorInfo RED =
        new ColorInfo(Color.RED,  Color.BLACK, "Red");
    
    static public final ColorInfo CYAN =
        new ColorInfo(Color.CYAN, Color.BLACK, "Cyan");
    
    static public final ColorInfo GREEN =
        new ColorInfo(Color.GREEN, Color.BLACK, "Green");

    static public final ColorInfo YELLOW =
        new ColorInfo(Color.YELLOW, Color.BLACK, "Yellow");


    /**
     * Creates a ColorInfo object with the specified parameters.
     * 
     * @param color_ background color
     * @param textColor_ text (foreground) color
     * @param colorNameKey_ bundle key for the background color's name
     */
    public ColorInfo(Color color_, Color textColor_, String colorNameKey_) {
    	    color = color_;
        textColor = textColor_;
        colorNameKey = colorNameKey_;
    }

    
    /**
     * Get the human readable name for this color.
     * 
     * @return the human readable name for this color
     */
    public String getDisplayName() {
        // TODO get this from a bundle
    	    return colorNameKey;
    }
    
    
	/**
     * Returns the color used to display the elapsed time (this is the
     * foreground color).
     * 
	 * @return Returns the color.
	 */
	public Color getTextColor() {
		return textColor;
	}
	
    
    /**
     * Returns the color used to indicate the current state (this is the
     * background color).
     * 
     * @return the color used to indicate the current state (this is the
     * background color).
     */
    public Color getColor() {
        return color;
    }
    
    
    /**
     * Returns the string used as the bundle key for the color's name.
     * 
	 * @return the string used as the bundle key for the color's name.
	 */
	public String getColorNameKey() {
		return colorNameKey;
	}
}
