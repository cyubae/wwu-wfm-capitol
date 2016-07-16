package de.unimuenster.wfm.capitol.settlement.helper;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTools {
	
	/**
	 * 
	 * @param startDate
	 * @param endDate
	 * @param testDate
	 * @return true if testDate is within period [startDate; endDate]
	 */
	public static boolean dateIsWithinRange(Date startDate, Date endDate, Date testDate) {
		startDate.setHours(0);
		startDate.setMinutes(0);
		startDate.setSeconds(0);
		
		endDate.setHours(23);
		endDate.setMinutes(59);
		endDate.setSeconds(59);		
		
		return testDate.getTime() >= startDate.getTime() &&
	             testDate.getTime() <= endDate.getTime();
	 }

}
