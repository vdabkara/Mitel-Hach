package com.softclouds.miteldataloader.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.poi.ss.format.CellNumberFormatter;
import org.apache.poi.ss.usermodel.Cell;

import com.softclouds.miteldataloader.dataStructures.models.userinfo.ErrorDetails;
import com.softclouds.miteldataloader.dataStructures.models.userinfo.TransactionDetails;

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
	 * Function will help to get the CDATA value of an Element in the XML
	 * Document
	 * 
	 * @param e
	 * @return
	 */

	public static String getStringFromXML(File f) throws IOException {
		StringBuffer xmlData = new StringBuffer();
		try {
			BufferedReader input = new BufferedReader(new FileReader(f));
			try {
				String line = null;
				while ((line = input.readLine()) != null) {
					xmlData.append(line);
					xmlData.append(System.getProperty("line.separator"));
				}
			} finally {
				input.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			logger.error("getStringFromXML :: Cannot Read the Input FileL :: "
					+ f.getName());
			throw ex;
		}
		String articleXML = xmlData.toString();
		return articleXML;
	}


	/**
	 * Function will identify the Cell Value Type and accordingly will read
	 * their values and will return
	 * 
	 * @param cell
	 * @return
	 */
	public static Object readCellValue(Cell cell) {
		Object cellValue = null;
		try {
			if (null != cell) {
				/*
				 * check for Cell Type and format accordingly
				 */
				if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					cellValue = cell.getNumericCellValue();

					/*
					 * Check here, if callValue is 0; THEN return "" as object
					 * else, check for decimal value in cell if decimal value is
					 * .0 or .00, then remove it if more than 0, then pass the
					 * value as it is
					 */

					String val = String.valueOf(cellValue);
					if (null != val && !"".equals(val)) {
						/*
						 * check if val has any value in point to decimal and
						 * that value is 0 then remove the decimal Value
						 */
						if (val.lastIndexOf(".") != -1) {
							String decimal = val.substring(
									val.lastIndexOf(".") + 1, val.length());
							if (null != decimal && !"".equals(decimal)) {
								Double dec = new Double(decimal).doubleValue();
								if (dec == 0) {
									// remove decimal from the val and value
									// after decimal
									val = val
											.substring(0, val.lastIndexOf("."));
								} else {
									// GET THE NUMERIC VALUE IN THE FORMAT
									// ########################## AND PASS IT
									CellNumberFormatter cn = new CellNumberFormatter(
											"################################");
									val = cn.format(cell.getNumericCellValue());
									cn = null;
								}
								// set dec to null
								dec = null;
							}
							// set decimal to null
							decimal = null;
						}
					}

					// set cellValue as val
					cellValue = val;
					// set val to null
					val = null;
				} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
					cellValue = cell.getCellFormula();
					if (null != cellValue) {
						// append a = with this formula
						cellValue = "=" + cellValue;
					}
				} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
					cellValue = cell.getStringCellValue();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// set cellValue to null
			cellValue = null;
		}
		return cellValue;
	}

	public static TransactionDetails capturreErrorDetails(TransactionDetails transDetails, Exception e) 
	{
		Writer writer = new StringWriter();
		PrintWriter print = new PrintWriter(writer);
		e.printStackTrace(print);
		String errorCode = e.getMessage();
		String errorMessage = writer.toString();
		
		if(null==transDetails)
		{
			transDetails =  new TransactionDetails();
		}
		
		// set hasError to true
		transDetails.setHasError(true);
		ErrorDetails errorDetails = new ErrorDetails();
		// set errorCode
		errorDetails.setErrorCode(errorCode);
		// set errorMessage
		errorDetails.setErrorMessage(errorMessage);
		if(null==transDetails.getErrorList() || transDetails.getErrorList().size()<=0)
		{
			transDetails.setErrorList(new ArrayList<ErrorDetails>());
		}
		transDetails.getErrorList().add(errorDetails);		
		errorDetails=null;
		
		if(null!=writer)
		{
			try
			{
				writer.close();
			}
			catch(Exception e1)
			{
				printStackTraceToLogs(Utilities.class.getName(), "capturreErrorDetails", e1);
			}
		}
		// set errorCode to null
		errorCode = null;
		// set errorMessage to null
		errorMessage = null;
		// set writer & print to null
		writer = null;
		// set print to null
		print = null;
		return transDetails;
	}

	public static Timestamp readTimestampValues(String lastProcessedTimeStampPath,String keyName) throws IOException
	{
		Timestamp timestampValue=null;
		try
		{
			String templatePath = lastProcessedTimeStampPath;
			File file = new File(templatePath);
			if(file.isFile() && file.exists())
			{
				Properties props = new Properties();
				props.load(new FileInputStream(file));
				String value = props.getProperty(keyName);
				if(null!=value && !"".equals(value))
				{
					value = value.trim();
					logger.info("readTimestampValues :: {"+keyName+"} Value is :: > " + value);
					/*
					 * CONVERT THIS TEXT TO TIMESTAMP
					 */
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
					Date convDate = sdf.parse(value);
					if(null!=convDate)
					{
						timestampValue = new Timestamp(convDate.getTime());
					}
					
					sdf =  null;
					value= null;
					props= null;
				}
				else
				{
					logger.info("readTimestampValues :: No Time is Defined for {"+keyName+"}. Fetch all the Modified Data.");
				}
				file = null;
			}
			else
			{
				logger.info("readTimestampValues :: No Time is Defined for {"+keyName+"}. Fetch all the Modified Data.");
			}
		
			file=null;
			templatePath = null;
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(Utilities.class.getName(), "readTimestampValues()", e);
		}
		return timestampValue;
	}

	public static void writeLastProcessedTime(Timestamp lastProcessedTime, String lastProcessedTimeStampPath) throws IOException
	{
		try
		{
			StringBuilder str = new StringBuilder();
			str.append("###############################################\n");
			str.append("# ----- ACCEPTED TIME FORAMT FOR DATES -------\n");
			str.append("# Format - yyyy-MM-dd HH:mm:ss a\n");
			str.append("# Sample Date - 2017-05-04 07:02:23 AM\n");
			str.append("###############################################\n");
			
			if(null!=lastProcessedTime)
			{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
				String fileText = sdf.format(lastProcessedTime);
				// add startTime.
				str.append("START_TIME="+fileText+"\n");
				logger.info("writeLastProcessedTime :: Last Processed Time to be Set is :: > " + fileText);
				fileText=  null;
//				sdf = null;
			}
			else
			{
				str.append("START_TIME=\n");
			}
			// add Blank End Time
			str.append("END_TIME=\n");
			
			String templatePath = lastProcessedTimeStampPath;
			if(null!=templatePath && !"".equals(templatePath))
			{
				templatePath = templatePath.replace("/", "\\");
				logger.info("writeLastProcessedTime :: Updating Last Processed File at path :: > " + templatePath);
				File outPutFile = new File(templatePath);
				outPutFile.createNewFile();
				
				FileOutputStream fos = new FileOutputStream(outPutFile);
				fos.write(str.toString().getBytes());
				fos.close();
				fos.flush();
				fos = null;
			}
			else
			{
				logger.info("writeLastProcessedTime :: Processing File Path is null. Cannot update Last Modified Time.");
			}
			templatePath = null;
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(Utilities.class.getName(), "writeLastProcessedTime()", e);
		}
	}

}
