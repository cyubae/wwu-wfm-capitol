package de.unimuenster.wfm.capitol.helper;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DateTools {
	
	private static Logger LOGGER = Logger.getLogger(DateTools.class.getName());
	

	/**
	 * Returns number of days between two date objects
	 * if date2 is more in the future than date1 then the result will be positive
	 * if date1 is more in the future than date2 then the result will be negative.
	 * @param dateStart earlier
	 * @param dateEnd later
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static int getDaysBetweenDates(Date dateStart, Date dateEnd) {
	  long diff = -1;
	  try {
		  dateStart.setHours(0);
		  dateStart.setMinutes(0);
		  dateStart.setSeconds(0);
		  
		  dateEnd.setHours(0);
		  dateEnd.setMinutes(0);
		  dateEnd.setSeconds(0);
		  
	    //time is always 00:00:00 so rounding should help to ignore the missing hour when going from winter to summer time as well as the extra hour in the other direction
	    diff = Math.round((dateEnd.getTime() - dateStart.getTime()) / (double) 86400000);
	  } catch (Exception e) {
		  LOGGER.log(Level.SEVERE, "Failed to calculate days between days (contract duration)");
	  }
	  return (int) diff;
	}
	
}
