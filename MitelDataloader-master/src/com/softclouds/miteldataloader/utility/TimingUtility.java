package com.softclouds.miteldataloader.utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Class for timing-related utility functions.
 * 
 * @author Ray
 *
 */
public class TimingUtility
{
	public static long startTime = TimingUtility.getAbsTime();

	/**
	 * Returns the absolute time in milliseconds (used in TimingEvent, and as general purpose).
	 */
	public static long getAbsTime()
	{
		return (new Date().getTime());
	}

	/**
	 * Gives us the time of day
	 */
	public static String timeOfDay()
	{
		return longTimeToDateString(getAbsTime() % (24 * 60 * 60 * 1000));
	}

	/**
	 * Gets the run time since the program started in milliseconds.
	 */
	public long getRunTime()
	{
		return getAbsTime() - startTime;
	}

	/**
	 * Returns a string representation of the run time.
	 * 
	 * @return
	 */
	public String getRunTimeString()
	{
		return longTimeToTimeString(getRunTime());
	}

	/**
	 * Converts a long time in milliseconds to a string of the format
	 * 
	 * XXh YYm ZZm
	 * 
	 * i.e. "3h 12m 23s"
	 * 
	 * @param time
	 * @return
	 */
	public static String longTimeToHoursMinutesAndSeconds(long time)
	{
		int totalSeconds = (int) (time / 1000);

		int hours = totalSeconds / (60 * 60);
		int minutes = (totalSeconds % (60 * 60)) / 60;
		int seconds = totalSeconds % 60;

		return hours + "h " + minutes + "m " + seconds + "s";
	}

	/**
	 * Converts a time in milliseconds to a string of the format. Suitable for file names, and
	 * sortable.
	 * 
	 * "yyyy_MM_dd__hh_mm_ss"
	 */
	public static String longTimeToNumericDateStringWithUnderscores(long time)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);
		Date date = (Date) calendar.getTime();

		// System.out.println("time = " + time);
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");// dd/MM/yyyy
		// Date now = new Date();
		String strDate = sdfDate.format(date);
		return strDate;
	}

	/**
	 * Converts a time in milliseconds to a string of the format
	 * 
	 * "yyyy.MM.dd hh:mm:ss"
	 */
	public static String longTimeToNumericDateStringCleanFormat(long time)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);
		Date date = (Date) calendar.getTime();

		// System.out.println("time = " + time);
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");// dd/MM/yyyy
		// Date now = new Date();
		String strDate = sdfDate.format(date);
		return strDate;
	}

	/**
	 * Converts a time in milliseconds to a string of the format EEE, dd MMM yyyy HH:mm:ss z
	 */
	public static String longTimeToDateString(long time)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);
		Date date = (Date) calendar.getTime();

		// System.out.println("time = " + time);
		SimpleDateFormat sdfDate = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");// dd/MM/yyyy
		// Date now = new Date();
		String strDate = sdfDate.format(date);
		return strDate;
	}

	/**
	 * Converts a long time to a time of the format XXd XXh XXm XXs XXms
	 * 
	 * @param time
	 * @return
	 */
	public static String longTimeToTimeString(long time)
	{
		// long years = (time) / (365 * 24 * 60 * 60 * 1000);
		// long days = (time % (365 * 24 * 60 * 60 * 1000)) / (24 * 60 * 60 * 1000);
		long days = (time) / (24 * 60 * 60 * 1000);
		long hours = (time % (24 * 60 * 60 * 1000)) / (60 * 60 * 1000);
		long minutes = (time % (60 * 60 * 1000)) / (60 * 1000);
		long seconds = (time % (60 * 1000)) / (1000);
		long milliseconds = (time % (1000)) / (1);

		StringBuilder r = new StringBuilder("");

		// //r.append (ioString.padZeroes (years, 4) + "y ");
		// r.append (ioString.padZeroes (days, 2) + "d ");
		// r.append (ioString.padZeroes (hours, 2) + "h ");
		// r.append (ioString.padZeroes (minutes, 2) + "m ");
		// r.append (ioString.padZeroes (seconds, 2) + "s ");
		// //r.append (ioString.padZeroes (milliseconds, 3) + "ms ");

		boolean fnz = false;

		if (days > 0)
			fnz = true;
		if (days > 0 || fnz)
			r.append(StringUtility.padZeroes(days, 2) + "d ");

		if (hours > 0)
			fnz = true;
		if (hours > 0 || fnz)
			r.append(StringUtility.padZeroes(hours, 2) + "h ");

		if (minutes > 0)
			fnz = true;
		if (minutes > 0 || fnz)
			r.append(StringUtility.padZeroes(minutes, 2) + "m ");

		if (seconds > 0)
			fnz = true;
		if (seconds > 0 || fnz)
			r.append(StringUtility.padZeroes(seconds, 2) + "s ");

		if (milliseconds > 0)
			fnz = true;
		if (milliseconds > 0 || fnz)
			r.append(StringUtility.padZeroes(milliseconds, 2) + "ms");

		return (r.toString());
	}

	/**
	 * Converts a long time in milliseconds to a time of the format XXd XXh XXm XXs XXms. Uses rough
	 * estimates.
	 * 
	 * @param time
	 * @return
	 */
	public static String roughLongTimeToTimeString(long time)
	{
		// long years = (time) / (365 * 24 * 60 * 60 * 1000);
		// long days = (time % (365 * 24 * 60 * 60 * 1000)) / (24 * 60 * 60 * 1000);
		long days = (time) / (24 * 60 * 60 * 1000);
		long hours = (time % (24 * 60 * 60 * 1000)) / (60 * 60 * 1000);
		long minutes = (time % (60 * 60 * 1000)) / (60 * 1000);
		long seconds = (time % (60 * 1000)) / (1000);
		long milliseconds = (time % (1000)) / (1);

		if (time < 10 * 1000)
			return seconds + "." + (int) (milliseconds / 10) + "s";
		else if (time < 60 * 1000)
			return seconds + "." + (int) (milliseconds / 100) + "s";
		else if (time < 60 * 60 * 1000)
			return minutes + "m " + seconds + "s";
		else if (time < 24 * 60 * 60 * 1000)
			return hours + "h " + minutes + "m";
		else
			return days + "d " + hours + "h";
	}

	/**
	 * Returns the hour of the day. Uses 24-hour military time.
	 * 
	 * @return
	 */
	public static int getHourOfDay()
	{
		int ret = (int) (getAbsTime() % (24 * 60 * 60 * 1000)) / (60 * 60 * 1000);
		System.out.println("hour of day is " + ret);
		return ret;
	}

	/**
	 * Returns a string representation of the absolute time.
	 * 
	 * @return
	 */
	public static String getAbsTimeString()
	{
		return longTimeToTimeString(getAbsTime());
	}

	/**
	 * Returns a string representation of the absolute time in a date-like format. A string of the
	 * format EEE, dd MMM yyyy HH:mm:ss z
	 * 
	 * @return
	 */
	public static String getAbsDateString()
	{
		return longTimeToDateString(getAbsTime());
	}

	/**
	 * Gets a numerical representation absolute date string with underscores between so that it is
	 * well-formed, and can be sorted.
	 * 
	 * YYYY_MM_DD_HH_MM_SS
	 * 
	 * @return
	 */
	public static String getNumericAbsDateString()
	{
		return longTimeToNumericDateStringWithUnderscores(getAbsTime());
	}

	/**
	 * Returns the hour and minute of the day.
	 */
	public static String getHourAndMinuteAndSecondOfDayString()
	{
		long absTime = getAbsTime();
		long modTime = (absTime / 1000) % (60 * 60 * 24);

		long hour = modTime / (60 * 60);
		long minute = (modTime % (60 * 60)) / 60;
		long second = modTime % 60;

		String minuteStr = StringUtility.padZeroes(minute, 2);
		String secondStr = StringUtility.padZeroes(second, 2);

		if (hour >= 12)
			return (hour % 12) + ":" + minuteStr + ":" + secondStr + "p";
		else
			return hour + ":" + minuteStr + ":" + secondStr + "a";
	}

}
