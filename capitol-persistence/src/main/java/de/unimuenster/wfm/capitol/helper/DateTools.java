package de.unimuenster.wfm.capitol.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
