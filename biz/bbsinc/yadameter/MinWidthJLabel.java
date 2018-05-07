package biz.bbsinc.yadameter;
import java.awt.Dimension;

import javax.swing.JLabel;


/**
 * A JLabel that enforces a minimum width; useful for horizontally aligning
 * labels placed in a vertical column.
 * 
 * @author Keith R. Bennett
 * Copyright 2005, Bennett Business Solutions, Inc.
 */
public class MinWidthJLabel extends JLabel {


    private int minWidthInPixels;
    
    
    /**
     * Creates an instance with the specified minimum width
     * 
     * @param minWidthInPixels minimum label width, in pixels
     */
    public MinWidthJLabel(int minWidthInPixels) {
        this.minWidthInPixels = minWidthInPixels;
    }
    
    
    /**
     * Returns the preferred size of this label.  This method is called by the
     * layout managers to determine size and placement of the component.  The whole
     * purpose of this class is to override this method, which will otherwise
     * take into consideration the label's text's width.
     */
    public Dimension getPreferredSize() {
        int width = Math.max
                (minWidthInPixels, super.getPreferredSize().width);
        int height = super.getPreferredSize().height;
        return new Dimension(width, height);
    }
}

