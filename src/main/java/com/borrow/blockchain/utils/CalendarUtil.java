package com.borrow.blockchain.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CalendarUtil {

	public static final String DATE_FORMAT_YYYYMMDD_HHMMSSSSS = "yyyyMMdd HH:mm:ss:SSS";
	public static final String DATE_FORMAT_YYYYMMDD_HHMMSS = "yyyyMMdd HH:mm:ss";
	public static final String DATE_FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";
	public static final String DATE_FORMAT_YYMMDD = "yyMMdd";
	public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
	public static final String DATE_FORMAT_YYYY_MM_DD_HHMMSSSSS = "yyyy-MM-dd HH:mm:ss:SSS";
	public static final String DATE_FORMAT_DDMMYYYY_HHmmssSS = "dd/MM/yyyy HH:mm:ss:SS";
	public static final String DATE_FORMAT_DD_MM_YYYY_HHmmss = "dd-MM-yyyy HH:mm:ss";
	public static final String DATE_FORMAT_DD_MM_YYYY_HHmm = "dd-MM-yyyy HH:mm";
	public static final String DATE_FORMAT_MMDD = "MMdd";
	public static final String TIME_FORMAT_HHMMSS = "HHmmss";
	public static final String TIME_FORMAT_HHMMSSSSSS = "HHmmsssss";
	public static final String DATE_FORMAT_DDMMYYYY_HHmmss = "dd/MM/yyyy HH:mm:ss";
	public static final String TIME_FORMAT_HHMMS = "HHmms";
	public static final String BS_DATE_TIME_FORMAT_YYYY_MM_DDTHHmmssSSS = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
	public static final String MVISA_DATE_TIME_FORMAT_YYYY_MM_DDTHHmmss = "yyyy-MM-dd'T'HH:mm:ss";
	public static final String DATE_FORMAT_DD_MM_YYYY = "dd-MM-yyyy";
	public static final String DATE_FORMAT_YY_MM_DD_mmSS = "yyMMddmmss";
	public static final String DATE_FORMAT_DDMMYYYY_HHMMSSSSS = "ddMMyyyy HH:mm:ss";
	public static final String DATE_FORMAT_DDMMYYYY = "ddMMyyyy";
	public static final String TIME_FORMAT_HH_MM_SSSSS = "HH:mm:ss";



	public static Calendar convertStrToCalendar(String strDate, String format) throws ParseException {
		Calendar cal = Calendar.getInstance(Locale.US);
		Date date = convertStrToDate(strDate, format);
		cal.setTime(date);
		return cal;
	}

	public static Date convertStrToDate(String strDate, String format) throws ParseException {

		if (CommonUtil.checkStringIsNull(strDate))
			return null;

		format = getDefaultFormat(format);
		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
		try {
			Date date = sdf.parse(strDate);
			return date;
		} catch (ParseException e) {
			throw e;
		}
	}

	private static String getDefaultFormat(String format) {
		if (CommonUtil.checkStringIsNull(format))
			format = DATE_FORMAT_YYYYMMDD_HHMMSSSSS;
		return format;
	}

	public static String convertCalToString(Calendar cal, String format) {
		if (cal == null)
			return null;

		format = getDefaultFormat(format);

		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
		return sdf.format(cal.getTime());
	}

	public static String convertDateToString(Date date, String format) {
		if (date == null)
			return null;

		format = getDefaultFormat(format);

		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
		return sdf.format(date);
	}

	public static Calendar getCurrentCalWithOutTime() {
		Calendar cal = Calendar.getInstance(Locale.US);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.AM_PM, Calendar.AM);
		return cal;
	}

	public static String getCurrentYear() {
		Calendar cal = Calendar.getInstance(Locale.US);
		return String.valueOf(cal.get(Calendar.YEAR));
	}
}
