package de.unimuenster.wfm.capitol.helper;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;

public class DateTools {
	
	/**
	 * Returns number of days between two date objects
	 * if date2 is more in the future than date1 then the result will be positive
	 * if date1 is more in the future than date2 then the result will be negative.
	 * @param date1 earlier
	 * @param date2 later
	 * @return
	 */
	public static int getDaysBetweenDates(Date date1, Date date2)
	{       
		return Days.daysBetween(new DateTime(date2), new DateTime(date1)).getDays() + 1;
	}
	
	
//	/**
//	 * Returns
//	 * @param dateString takes String of format YYYY-MM-DD and returns object of type java.util.Date
//	 * @return
//	 * @throws ParseException 
//	 */
//	public static Date convertStringToDate(String dateString) throws ParseException {
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		java.util.Date parsedDate = format.parse(dateString);
//		java.util.Date utilDate = new java.util.Date(parsedDate.getTime());
//
//		return utilDate;
//	}
	
}
