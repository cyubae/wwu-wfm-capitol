package de.unimuenster.wfm.capitol.contracting.helper;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateConverter {
	
	/**
	 * Returns
	 * @param dateString takes String of format YYYY-MM-DD and returns object of type java.sql.Date
	 * @return
	 * @throws ParseException 
	 */
	public static Date convertStringToDate(String dateString) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date parsedDate = format.parse(dateString);
		java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());

		return sqlDate;
	}

}
