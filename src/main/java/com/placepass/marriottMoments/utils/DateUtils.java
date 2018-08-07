package com.placepass.marriottMoments.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {
	public static String getFormatedDate(String formatType, Calendar cal) {
		DateFormat sdf = new SimpleDateFormat(formatType);
		String fromatedDateone = sdf.format(cal.getTime());
		return fromatedDateone;
	}

	public static Calendar getMonth(int monthNumber) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, monthNumber);
		return cal;
	}

	public static Calendar getDate(int dateRange) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, dateRange);
		return cal;
	}

	public static Calendar getDateFromCurrentDate(int yearRange, int monthRange, int dateRange) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, dateRange);
		cal.add(Calendar.MONTH, monthRange);
		cal.add(Calendar.YEAR, yearRange);
		return cal;
	}

	public static Calendar getDateFromGivenDate(int yearRange, int monthRange, int dateRange, Calendar cal) {
		// Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, dateRange);
		cal.add(Calendar.MONTH, monthRange);
		cal.add(Calendar.YEAR, yearRange);
		return cal;
	}

}
