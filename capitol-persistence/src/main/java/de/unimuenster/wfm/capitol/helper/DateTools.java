package de.unimuenster.wfm.capitol.helper;

import java.sql.Date;

public class DateTools {
	
	/**
	 * Returns number of days between two date objects
	 * if date2 is more in the future than date1 then the result will be positive
	 * if date1 is more in the future than date2 then the result will be negative.
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getDaysBetweenDates(Date date1, Date date2)
	{       
	    return (int)((date2.getTime() - date1.getTime()) / (1000*60*60*24l));
	}
}
