package biz.bbsinc.yadameter;
/**
 * 
 * @author Keith R. Bennett
 * Copyright 2005, Bennett Business Solutions, Inc.
 */
public class TimerUtilities {

    
    /**
     * Builds and returns the string used to display a time quantity 
     * in H:MM:SS format.
     * 
     * @param milliseconds time quantity to format, in milliseconds
     * @return formatted time string (e.g. "1:59:35")
     */
    public static String getTimeDisplayString(long milliseconds) {

        TimeDuration duration = new TimeDuration(milliseconds);
        String hoursString = String.valueOf(duration.getHours());
        
        String minutesString = String.valueOf(duration.getMinutes());
        if (minutesString.length() == 1)
            minutesString = "0" + minutesString;

        String secondsString = String.valueOf(duration.getSeconds());
        if (secondsString.length() == 1)
            secondsString = "0" + secondsString;
        
        String displayString = 
                hoursString + ":" + minutesString + ":" + secondsString;
        
        displayString = trimLeadingZeros(displayString);
        return displayString;
    }
    
    
    /**
     * Trims leading zeros from a String
     * 
     * @param s the string to trim
     * @return the string trimmed of zeros
     */
    private static String trimLeadingZeros(String s) {
        String returnString = s;
        final int minLength = 4; // "0:0X"

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((c != '0' && c != ':') || (s.length() - i <= minLength)) {
            	    returnString = s.substring(i);
                break;
            }
        }
        return returnString;
    }
}
