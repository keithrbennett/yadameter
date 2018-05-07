package biz.bbsinc.yadameter;
/**
 * 
 * @author Keith R. Bennett
 * Copyright 2005, Bennett Business Solutions, Inc.
 */
public class TimeDuration {

    private final static long SECONDS_PER_HOUR = 60 * 60;
    private final static long SECONDS_PER_MINUTE = 60;
    
    private int hours;
    private int minutes;
    private int seconds;
    
    
    public TimeDuration(long milliseconds) {
    	    setMillis(milliseconds);
    }
    
    /**
	 * @return Returns the hours.
	 */
	public int getHours() {
		return hours;
	}
	/**
	 * @param hours The hours to set.
	 */
	public void setHours(int hours) {
		this.hours = hours;
	}
	/**
	 * @return Returns the minutes.
	 */
	public int getMinutes() {
		return minutes;
	}
	/**
	 * @param minutes The minutes to set.
	 */
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	/**
	 * @return Returns the seconds.
	 */
	public int getSeconds() {
		return seconds;
	}
	/**
	 * @param seconds The seconds to set.
	 */
	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

    public void setHMS(int hours_, int minutes_, int seconds_) {
    	    hours = hours_;
        minutes = minutes_;
        seconds = seconds_;
    }
    
    public void setMillis(long millis) {
    	    long totalSeconds = (millis + 500) / 1000;
        
        setHours((int) (totalSeconds / SECONDS_PER_HOUR));
        totalSeconds -= (getHours() * SECONDS_PER_HOUR);
        
        setMinutes((int) (totalSeconds / SECONDS_PER_MINUTE));
        setSeconds((int) (totalSeconds % SECONDS_PER_MINUTE));
    }
    
    public long getMilliseconds() {
    	    return getMilliseconds(getHours(), getMinutes(), getSeconds());
    }
    
    public static long getMilliseconds(int hours, int minutes, int seconds) {
    	    return 1000L *
                (hours * SECONDS_PER_HOUR + minutes * SECONDS_PER_MINUTE + seconds);
    }
}
