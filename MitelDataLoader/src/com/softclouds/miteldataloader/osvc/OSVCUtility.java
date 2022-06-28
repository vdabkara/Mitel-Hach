package com.softclouds.miteldataloader.osvc;

import org.joda.time.DateTime;

public class OSVCUtility
{
	public static String formatDateTime(DateTime dateTime)
	{
		// " <q1:DateTimeValue>2013-11-01T21:45:35Z</q1:DateTimeValue>";
		// payload += " <q1:DateValue>2013-11-01</q1:DateValue>";

		String ret = dateTime.toString("YYYY-MM-DD");

		return ret;
		// String dateTime = "11/15/2013 08:00:00";
		// // Format for input
		// DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss");
		// // Parsing the date
		// DateTime jodatime = dtf.parseDateTime(dateTime);
		// // Format for output
		// DateTimeFormatter dtfOut = DateTimeFormat.forPattern("MM/dd/yyyy");

	}
}
