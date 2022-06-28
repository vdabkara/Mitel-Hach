package com.ka.kcapp.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;


public class Utilities {

	static Logger logger = Logger.getLogger(Utilities.class);

	public static void printStackTraceToLogs(String className,
			String methodName, Exception e) {
		try {
			Writer writer = new StringWriter();
			PrintWriter print = new PrintWriter(writer);
			e.printStackTrace(print);

			logger.info(className + "::" + methodName + ":: Error :: > "
					+ e.getMessage());
			logger.info(className + "::" + methodName + ":: Error :: > "
					+ writer.toString());
			// String errorCode = e.getMessage();
			// String errorMessage = writer.toString();

			print = null;
			writer = null;
		} catch (Exception f) {
			f.printStackTrace();
		}
	}
	
	/**
	 * Reads all you can from an input stream.
	 * 
	 * @param inputStream
	 * @return
	 */
	public static String convertInputStreamToString(InputStream inputStream)
	{
		StringWriter writer = new StringWriter();
		try
		{
			if(null!=inputStream)
			{
				IOUtils.copy(inputStream, writer, "UTF-8");
				String response = writer.toString();
				return response;
			}
		}
		catch (IOException e)
		{
			printStackTraceToLogs(Utilities.class.getName(), "convertInputStreamToString()", e);
		}
		return null;
	}
	
}