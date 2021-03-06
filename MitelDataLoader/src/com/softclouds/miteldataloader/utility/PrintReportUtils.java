package com.softclouds.miteldataloader.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.softclouds.miteldataloader.dataStructures.models.contenthistory.ContentHistoryDetails;
import com.softclouds.miteldataloader.dataStructures.models.contentrecommendation.ContentRecommendationDetails;
import com.softclouds.miteldataloader.dataStructures.models.userinfo.ErrorDetails;
import com.softclouds.miteldataloader.dataStructures.models.userinfo.TransactionDetails;
import com.softclouds.miteldataloader.dataStructures.models.userinfo.UserDetails;

public class PrintReportUtils {

	private static Logger logger = Logger.getLogger(PrintReportUtils.class);
	
	public static void printUserDataReport(String reportsPath, String folderDate, Vector<TransactionDetails> userDataList) 
	{
		if (logger.isInfoEnabled())
			logger.info("printUserDataReport :: Method Starts.");
		try {
			if (null != userDataList && userDataList.size()>0) 
			{
				String path = reportsPath;
				/*
				 * Now check inside the reports directory does a directory exists
				 * for wslId
				 */
				File reportDir = new File(path);
				if (!reportDir.exists() || !reportDir.isDirectory()) {
					// create new directory
					reportDir.mkdir();
				}
				reportDir = null;
				/*
				 * Now check, whether the schedule code directory exists or not
				 */
				path = path + "/" + folderDate;
				File folderDir = new File(path);
				if (!folderDir.exists() || !folderDir.isDirectory()) {
					folderDir.mkdir();
				}
				folderDir = null;

				/*
				 * iterate Map and write the Excel File for each Index
				 */
				String fName = "/"+ "USERDATA_REPORT.xlsx";
				File myFile = new File(path + fName);
				fName = null;
				// Create the workbook instance for XLSX file, KEEP 100 ROWS IN MEMMORY AND RET ON DISK
				SXSSFWorkbook myWorkBook = new SXSSFWorkbook(100);

				// Create a new sheet
				Sheet mySheet = myWorkBook.createSheet("Details");
				/*
				 * Add Header Row
				 */
				Row headerRow = mySheet.createRow(0);
				Cell autoGeneratedIdCell = headerRow.createCell(0);
				autoGeneratedIdCell.setCellValue("AUTO ID");
				Cell accountIdCell = headerRow.createCell(1);
				accountIdCell.setCellValue("ACCOUNT ID");
				Cell fullNameCell = headerRow.createCell(2);
				fullNameCell.setCellValue("FULL NAME");
				Cell firstNameCell=headerRow.createCell(3);
				firstNameCell.setCellValue("FIRST NAME");
				Cell lastNameCell = headerRow.createCell(4);
				lastNameCell.setCellValue("LAST NAME");
				Cell emailIdCell = headerRow.createCell(5);
				emailIdCell.setCellValue("EMAIL ID");
				Cell reportingGroupCell = headerRow.createCell(6);
				reportingGroupCell.setCellValue("REPORTING GROUP");
				Cell operationTypeCell = headerRow.createCell(7);
				operationTypeCell.setCellValue("OPERATION TYPE");
				Cell processingStatusCell = headerRow.createCell(8);
				processingStatusCell.setCellValue("PROCESSING STATUS");
				
				int rowCount = 0;
				for (TransactionDetails transDetails : userDataList) 
				{
					UserDetails details = transDetails.getUserDetails();
					if(null!=details)
					{
						// increment rowCount by 1
						rowCount++;
						// Create a new Row
						Row row = mySheet.createRow(rowCount);

						Cell cell201 = row.createCell(0);
						cell201.setCellValue("");
						if(null!=details.getAutoId() && !"".equals(details.getAutoId()))
						{
							cell201.setCellValue(details.getAutoId());
						}
						
						Cell cell0 = row.createCell(1);
						cell0.setCellValue("");
						if(null!=details.getLoginId() && !"".equals(details.getLoginId()))
						{
							cell0.setCellValue(details.getLoginId());
						}

						Cell cell1 = row.createCell(2);
						cell1.setCellValue("");
						if(null!=details.getFullName() && !"".equals(details.getFullName()))
						{
							cell1.setCellValue(details.getFullName());
						}

						Cell cell2 = row.createCell(3);
						cell2.setCellValue("");
						if(null!=details.getFirstName() && !"".equals(details.getFirstName()))
						{
							cell2.setCellValue(details.getFirstName());
						}

						Cell cell3 = row.createCell(4);
						cell3.setCellValue("");
						if(null!=details.getLastName() && !"".equals(details.getLastName()))
						{
							cell3.setCellValue(details.getLastName());
						}

						Cell cell4 = row.createCell(5);
						if (null != details.getEmailId() && !"".equals(details.getEmailId())) 
						{
							cell4.setCellValue(details.getEmailId());
						} 

						Cell cell5 = row.createCell(6);
						if (null != details.getReportingGroup() && !"".equals(details.getReportingGroup())) 
						{
							cell5.setCellValue(details.getReportingGroup());
						} 

						Cell cell6 = row.createCell(7);
						if(null!=details.getAutoId() && !"".equals(details.getAutoId()))
						{
							cell6.setCellValue("UPDATE_USER");
						}
						else
						{
							cell6.setCellValue("CREATE_USER");
						}

						Cell cell7 = row.createCell(8);
						/*
						 * INSTEAD OF ERROR SET IF ERROR LIST IS NOT NULL
						 */
//						if (transDetails.isHasError()==true) 
						if(null!=transDetails.getErrorList() && transDetails.getErrorList().size()>0)
						{
							cell7.setCellValue("FAILURE");
						} 
						else
						{
							cell7.setCellValue("SUCCESS");
						}

						cell201 =null;
						cell7 = null;
						cell6 = null;
						cell5 = null;
						cell4 = null;
						cell3 = null;
						cell2 = null;
						cell1 = null;
						cell0 = null;

						row = null;
						details=  null;
					}
				}

				headerRow = null;
				accountIdCell= null;
				fullNameCell=null;
				lastNameCell=null;
				firstNameCell= null;
				emailIdCell=null;
				reportingGroupCell = null;
				operationTypeCell=  null;
				processingStatusCell= null;
				autoGeneratedIdCell = null;

				FileOutputStream os = new FileOutputStream(myFile);
				myWorkBook.write(os);
				logger.info("Writing on USER DATA REPORT XLSX file Finished ...");
				System.out.println("Writing on USER DATA REPORT XLSX file Finished ...");
				os.flush();
				os.close();

				// set mySheet to null
				mySheet = null;
				// set myWorkBook to null
				myWorkBook = null;
				// set path to null
				path = null;
				// set myFile to null
				myFile = null;
			}
		} 
		catch (Exception e) 
		{
			Utilities.printStackTraceToLogs(PrintReportUtils.class.getName(), "printUserDataReport()", e);
		}
		if (logger.isInfoEnabled())
			logger.info("printUserDataReport :: Method Ends.");
	}

	public static void printUserDataFailureReport(String reportsPath, String folderDate, Vector<TransactionDetails> userDataList) 
	{
		if (logger.isInfoEnabled())
			logger.info("printUserDataFailureReport :: Method Starts.");
		try {
			if (null != userDataList && userDataList.size()>0) 
			{
				String path = reportsPath;
				/*
				 * Now check inside the reports directory does a directory exists
				 * for wslId
				 */
				File reportDir = new File(path);
				if (!reportDir.exists() || !reportDir.isDirectory()) {
					// create new directory
					reportDir.mkdir();
				}
				reportDir = null;
				/*
				 * Now check, whether the schedule code directory exists or not
				 */
				path = path + "/" + folderDate;
				File folderDir = new File(path);
				if (!folderDir.exists() || !folderDir.isDirectory()) {
					folderDir.mkdir();
				}
				folderDir = null;

				/*
				 * iterate Map and write the Excel File for each Index
				 */
				String fName = "/"+ "USERDATA_FAILURE_REPORT.xlsx";
				File myFile = new File(path + fName);
				fName = null;
				// Create the workbook instance for XLSX file, KEEP 100 ROWS IN MEMMORY AND RET ON DISK
				SXSSFWorkbook myWorkBook = new SXSSFWorkbook(100);

				// Create a new sheet
				Sheet mySheet = myWorkBook.createSheet("Failure Details");
				/*
				 * Add Header Row
				 */
				Row headerRow = mySheet.createRow(0);
				Cell autoGeneratedIdCell = headerRow.createCell(0);
				autoGeneratedIdCell.setCellValue("AUTO ID");
				Cell accountIdCell = headerRow.createCell(1);
				accountIdCell.setCellValue("ACCOUNT ID");
				Cell fullNameCell = headerRow.createCell(2);
				fullNameCell.setCellValue("FULL NAME");
				Cell operationTypeCell = headerRow.createCell(3);
				operationTypeCell.setCellValue("OPERATION TYPE");
				Cell errorCodeCell=headerRow.createCell(4);
				errorCodeCell.setCellValue("ERROR CODE");
				Cell errorMessageCell = headerRow.createCell(5);
				errorMessageCell.setCellValue("ERROR MESSAGE");
				Cell soapErrorMessageCell = headerRow.createCell(6);
				soapErrorMessageCell.setCellValue("SOAP ERROR RESPONSE");
				
				
				int rowCount = 0;
				for (TransactionDetails transDetails : userDataList) 
				{
					UserDetails details = new UserDetails();
					if(null!=transDetails.getUserDetails())
					{
						details  =transDetails.getUserDetails();
					}
					
					if(null!=transDetails.getErrorList() && transDetails.getErrorList().size()>0)
					{
						for(ErrorDetails errorDetails : transDetails.getErrorList())
						{
							// increment rowCount by 1
							rowCount++;
							// Create a new Row
							Row row = mySheet.createRow(rowCount);

							Cell cell201 = row.createCell(0);
							cell201.setCellValue("");
							if(null!=details.getAutoId() && !"".equals(details.getAutoId()))
							{
								cell201.setCellValue(details.getAutoId());
							}

							Cell cell0 = row.createCell(1);
							cell0.setCellValue("");
							if(null!=details.getLoginId() && !"".equals(details.getLoginId()))
							{
								cell0.setCellValue(details.getLoginId());
							}

							Cell cell1 = row.createCell(2);
							cell1.setCellValue("");
							if(null!=details.getFullName() && !"".equals(details.getFullName()))
							{
								cell1.setCellValue(details.getFullName());
							}

							Cell cell2 = row.createCell(3);
							cell2.setCellValue("");
							if(null!=transDetails.getOperationType() && !"".equals(transDetails.getOperationType()))
							{
								cell2.setCellValue(transDetails.getOperationType());
							}

							Cell cell3 = row.createCell(4);
							cell3.setCellValue("");
							if(null!=errorDetails.getErrorCode() && !"".equals(errorDetails.getErrorCode()))
							{
								cell3.setCellValue(errorDetails.getErrorCode());
							}

							Cell cell4 = row.createCell(5);
							if (null != errorDetails.getErrorMessage() && !"".equals(errorDetails.getErrorMessage())) 
							{
								cell4.setCellValue(errorDetails.getErrorMessage());
							} 

							Cell cell5 = row.createCell(6);
							if (null != errorDetails.getSoapEnvelopeErrorResponse() && !"".equals(errorDetails.getSoapEnvelopeErrorResponse())) 
							{
								cell5.setCellValue(errorDetails.getSoapEnvelopeErrorResponse());
							} 
							
							cell201 = null;
							cell5 = null;
							cell4 = null;
							cell3 = null;
							cell2 = null;
							cell1 = null;
							cell0 = null;

							row = null;
							errorDetails=  null;
						}
						details=  null;
					}
				}

				headerRow = null;
				accountIdCell= null;
				fullNameCell=null;
				operationTypeCell = null;
				autoGeneratedIdCell =null;
				errorCodeCell= null;
				errorMessageCell =null;
				soapErrorMessageCell = null;

				FileOutputStream os = new FileOutputStream(myFile);
				myWorkBook.write(os);
				logger.info("Writing on USER DATA FAILURE REPORT XLSX file Finished ...");
				System.out.println("Writing on USER DATA FAILURE REPORT XLSX file Finished ...");
				os.flush();
				os.close();

				// set mySheet to null
				mySheet = null;
				// set myWorkBook to null
				myWorkBook = null;
				// set path to null
				path = null;
				// set myFile to null
				myFile = null;
			}
		} 
		catch (Exception e) 
		{
			Utilities.printStackTraceToLogs(PrintReportUtils.class.getName(), "printUserDataFailureReport()", e);
		}
		if (logger.isInfoEnabled())
			logger.info("printUserDataFailureReport :: Method Ends.");
	}

	public static void printRecommendationDataReport(String reportsPath, String folderDate, Vector<TransactionDetails> recommendationsDataList) 
	{
		if (logger.isInfoEnabled())
			logger.info("printRecommendationDataReport :: Method Starts.");
		try {
			if (null != recommendationsDataList && recommendationsDataList.size()>0) 
			{
				String path = reportsPath;
				/*
				 * Now check inside the reports directory does a directory exists
				 * for wslId
				 */
				File reportDir = new File(path);
				if (!reportDir.exists() || !reportDir.isDirectory()) {
					// create new directory
					reportDir.mkdir();
				}
				reportDir = null;
				/*
				 * Now check, whether the schedule code directory exists or not
				 */
				path = path + "/" + folderDate;
				File folderDir = new File(path);
				if (!folderDir.exists() || !folderDir.isDirectory()) {
					folderDir.mkdir();
				}
				folderDir = null;

				/*
				 * iterate Map and write the Excel File for each Index
				 */
				String fName = "/"+ "RECOMMENDATIONDATA_REPORT.xlsx";
				File myFile = new File(path + fName);
				fName = null;
				// Create the workbook instance for XLSX file, KEEP 100 ROWS IN MEMMORY AND RET ON DISK
				SXSSFWorkbook myWorkBook = new SXSSFWorkbook(100);

				// Create a new sheet
				Sheet mySheet = myWorkBook.createSheet("Details");
				/*
				 * Add Header Row
				 */
				Row headerRow = mySheet.createRow(0);
				Cell autoGeneratedIdCell = headerRow.createCell(0);
				autoGeneratedIdCell.setCellValue("AUTO ID");
				Cell accountIdCell = headerRow.createCell(1);
				accountIdCell.setCellValue("TITLE");
				Cell fullNameCell = headerRow.createCell(2);
				fullNameCell.setCellValue("RECORD ID");
				Cell firstNameCell=headerRow.createCell(3);
				firstNameCell.setCellValue("ACCOUNT ID");
				Cell lastNameCell = headerRow.createCell(4);
				lastNameCell.setCellValue("STATUS");
				Cell emailIdCell = headerRow.createCell(5);
				emailIdCell.setCellValue("ANSWER ID");
				Cell reportingGroupCell = headerRow.createCell(6);
				reportingGroupCell.setCellValue("DOC ID");
				Cell dateSubmittedCell = headerRow.createCell(7);
				dateSubmittedCell.setCellValue("DATE SUBMITTED");
				Cell operationTypeCell = headerRow.createCell(8);
				operationTypeCell.setCellValue("OPERATION TYPE");
				Cell processingStatusCell = headerRow.createCell(9);
				processingStatusCell.setCellValue("PROCESSING STATUS");
				
				int rowCount = 0;
				for (TransactionDetails transDetails : recommendationsDataList) 
				{
					ContentRecommendationDetails details = transDetails.getRecommendationDetails();
					if(null!=details)
					{
						// increment rowCount by 1
						rowCount++;
						// Create a new Row
						Row row = mySheet.createRow(rowCount);

						Cell cell201 = row.createCell(0);
						cell201.setCellValue("");
						if(null!=details.getAutoId() && !"".equals(details.getAutoId()))
						{
							cell201.setCellValue(details.getAutoId());
						}
						
						Cell cell0 = row.createCell(1);
						cell0.setCellValue("");
						if(null!=details.getTitle() && !"".equals(details.getTitle()))
						{
							cell0.setCellValue(details.getTitle());
						}

						Cell cell1 = row.createCell(2);
						cell1.setCellValue("");
						if(null!=details.getRecordId() && !"".equals(details.getRecordId()))
						{
							cell1.setCellValue(details.getRecordId());
						}

						Cell cell2 = row.createCell(3);
						cell2.setCellValue("");
						if(null!=details.getLoginId() && !"".equals(details.getLoginId()))
						{
							cell2.setCellValue(details.getLoginId());
						}

						Cell cell3 = row.createCell(4);
						cell3.setCellValue("");
						if(null!=details.getStatusLabel() && !"".equals(details.getStatusLabel()))
						{
							cell3.setCellValue(details.getStatusLabel());
						}

						Cell cell4 = row.createCell(5);
						if (null != details.getAnswerId() && !"".equals(details.getAnswerId())) 
						{
							cell4.setCellValue(details.getAnswerId());
						} 

						Cell cell5 = row.createCell(6);
						if (null != details.getDocId() && !"".equals(details.getDocId())) 
						{
							cell5.setCellValue(details.getDocId());
						} 

						Cell cell19 = row.createCell(7);
						cell19.setCellValue("");
						if(null!=details.getDateSubmitted())
						{
							cell19.setCellValue(details.getDateSubmitted().toString());
						}
						
						Cell cell6 = row.createCell(8);
						if(null!=details.getAutoId() && !"".equals(details.getAutoId()))
						{
							cell6.setCellValue("UPDATE_RECOMMENDATION");
						}
						else
						{
							cell6.setCellValue("CREATE_RECOMMENDATION");
						}

						Cell cell7 = row.createCell(9);
						/*
						 * INSTEAD OF ERROR SET IF ERROR LIST IS NOT NULL
						 */
//						if (transDetails.isHasError()==true)
						if(null!=transDetails.getErrorList() && transDetails.getErrorList().size()>0)
						{
							cell7.setCellValue("FAILURE");
						} 
						else
						{
							cell7.setCellValue("SUCCESS");
						}

						cell19 =null;
						cell201 =null;
						cell7 = null;
						cell6 = null;
						cell5 = null;
						cell4 = null;
						cell3 = null;
						cell2 = null;
						cell1 = null;
						cell0 = null;

						row = null;
						details=  null;
					}
				}

				headerRow = null;
				accountIdCell= null;
				fullNameCell=null;
				lastNameCell=null;
				firstNameCell= null;
				emailIdCell=null;
				reportingGroupCell = null;
				operationTypeCell=  null;
				processingStatusCell= null;
				autoGeneratedIdCell = null;
				dateSubmittedCell = null;

				FileOutputStream os = new FileOutputStream(myFile);
				myWorkBook.write(os);
				logger.info("Writing on RECOMMENDATIONS DATA REPORT XLSX file Finished ...");
				System.out.println("Writing on RECOMMENDATIONS DATA REPORT XLSX file Finished ...");
				os.flush();
				os.close();

				// set mySheet to null
				mySheet = null;
				// set myWorkBook to null
				myWorkBook = null;
				// set path to null
				path = null;
				// set myFile to null
				myFile = null;
			}
		} 
		catch (Exception e) 
		{
			Utilities.printStackTraceToLogs(PrintReportUtils.class.getName(), "printRecommendationDataReport()", e);
		}
		if (logger.isInfoEnabled())
			logger.info("printRecommendationDataReport :: Method Ends.");
	}

	public static void printRecommendationDataFailureReport(String reportsPath, String folderDate, Vector<TransactionDetails> recommendationsDataList) 
	{
		if (logger.isInfoEnabled())
			logger.info("printRecommendationDataFailureReport :: Method Starts.");
		try {
			if (null != recommendationsDataList && recommendationsDataList.size()>0) 
			{
				String path = reportsPath;
				/*
				 * Now check inside the reports directory does a directory exists
				 * for wslId
				 */
				File reportDir = new File(path);
				if (!reportDir.exists() || !reportDir.isDirectory()) {
					// create new directory
					reportDir.mkdir();
				}
				reportDir = null;
				/*
				 * Now check, whether the schedule code directory exists or not
				 */
				path = path + "/" + folderDate;
				File folderDir = new File(path);
				if (!folderDir.exists() || !folderDir.isDirectory()) {
					folderDir.mkdir();
				}
				folderDir = null;

				/*
				 * iterate Map and write the Excel File for each Index
				 */
				String fName = "/"+ "RECOMMENDATIONDATA_FAILURE_REPORT.xlsx";
				File myFile = new File(path + fName);
				fName = null;
				// Create the workbook instance for XLSX file, KEEP 100 ROWS IN MEMMORY AND RET ON DISK
				SXSSFWorkbook myWorkBook = new SXSSFWorkbook(100);

				// Create a new sheet
				Sheet mySheet = myWorkBook.createSheet("Failure Details");
				/*
				 * Add Header Row
				 */
				Row headerRow = mySheet.createRow(0);
				Cell autoGeneratedIdCell = headerRow.createCell(0);
				autoGeneratedIdCell.setCellValue("AUTO ID");
				Cell accountIdCell = headerRow.createCell(1);
				accountIdCell.setCellValue("RECORD ID");
				Cell fullNameCell = headerRow.createCell(2);
				fullNameCell.setCellValue("ACCOUNT ID");
				Cell operationTypeCell = headerRow.createCell(3);
				operationTypeCell.setCellValue("OPERATION TYPE");
				Cell errorCodeCell=headerRow.createCell(4);
				errorCodeCell.setCellValue("ERROR CODE");
				Cell errorMessageCell = headerRow.createCell(5);
				errorMessageCell.setCellValue("ERROR MESSAGE");
				Cell soapErrorMessageCell = headerRow.createCell(6);
				soapErrorMessageCell.setCellValue("SOAP ERROR RESPONSE");
				
				
				int rowCount = 0;
				for (TransactionDetails transDetails : recommendationsDataList) 
				{
					ContentRecommendationDetails details = new ContentRecommendationDetails();
					if(null!=transDetails.getRecommendationDetails())
					{
						details  =transDetails.getRecommendationDetails();
					}
					
					if(null!=transDetails.getErrorList() && transDetails.getErrorList().size()>0)
					{
						for(ErrorDetails errorDetails : transDetails.getErrorList())
						{
							// increment rowCount by 1
							rowCount++;
							// Create a new Row
							Row row = mySheet.createRow(rowCount);

							Cell cell201 = row.createCell(0);
							cell201.setCellValue("");
							if(null!=details.getAutoId() && !"".equals(details.getAutoId()))
							{
								cell201.setCellValue(details.getAutoId());
							}

							Cell cell0 = row.createCell(1);
							cell0.setCellValue("");
							if(null!=details.getRecordId() && !"".equals(details.getRecordId()))
							{
								cell0.setCellValue(details.getRecordId());
							}

							Cell cell1 = row.createCell(2);
							cell1.setCellValue("");
							if(null!=details.getLoginId() && !"".equals(details.getLoginId()))
							{
								cell1.setCellValue(details.getLoginId());
							}

							Cell cell2 = row.createCell(3);
							cell2.setCellValue("");
							if(null!=transDetails.getOperationType() && !"".equals(transDetails.getOperationType()))
							{
								cell2.setCellValue(transDetails.getOperationType());
							}

							Cell cell3 = row.createCell(4);
							cell3.setCellValue("");
							if(null!=errorDetails.getErrorCode() && !"".equals(errorDetails.getErrorCode()))
							{
								cell3.setCellValue(errorDetails.getErrorCode());
							}

							Cell cell4 = row.createCell(5);
							if (null != errorDetails.getErrorMessage() && !"".equals(errorDetails.getErrorMessage())) 
							{
								cell4.setCellValue(errorDetails.getErrorMessage());
							} 

							Cell cell5 = row.createCell(6);
							if (null != errorDetails.getSoapEnvelopeErrorResponse() && !"".equals(errorDetails.getSoapEnvelopeErrorResponse())) 
							{
								cell5.setCellValue(errorDetails.getSoapEnvelopeErrorResponse());
							} 
							
							cell201 = null;
							cell5 = null;
							cell4 = null;
							cell3 = null;
							cell2 = null;
							cell1 = null;
							cell0 = null;

							row = null;
							errorDetails=  null;
						}
						details=  null;
					}
				}

				headerRow = null;
				accountIdCell= null;
				fullNameCell=null;
				operationTypeCell = null;
				autoGeneratedIdCell =null;
				errorCodeCell= null;
				errorMessageCell =null;
				soapErrorMessageCell = null;

				FileOutputStream os = new FileOutputStream(myFile);
				myWorkBook.write(os);
				logger.info("Writing on RECOMMENDATION DATA FAILURE REPORT XLSX file Finished ...");
				System.out.println("Writing on RECOMMENDATION DATA FAILURE REPORT XLSX file Finished ...");
				os.flush();
				os.close();

				// set mySheet to null
				mySheet = null;
				// set myWorkBook to null
				myWorkBook = null;
				// set path to null
				path = null;
				// set myFile to null
				myFile = null;
			}
		} 
		catch (Exception e) 
		{
			Utilities.printStackTraceToLogs(PrintReportUtils.class.getName(), "printRecommendationDataFailureReport()", e);
		}
		if (logger.isInfoEnabled())
			logger.info("printRecommendationDataFailureReport :: Method Ends.");
	}

	public static void printContentHistoryDataReport(String reportsPath, String folderDate, Vector<TransactionDetails> historyDetailsList) 
	{
		if (logger.isInfoEnabled())
			logger.info("printContentHistoryDataReport :: Method Starts.");
		try {
			if (null != historyDetailsList && historyDetailsList.size()>0) 
			{
				String path = reportsPath;
				/*
				 * Now check inside the reports directory does a directory exists
				 * for wslId
				 */
				File reportDir = new File(path);
				if (!reportDir.exists() || !reportDir.isDirectory()) {
					// create new directory
					reportDir.mkdir();
				}
				reportDir = null;
				/*
				 * Now check, whether the schedule code directory exists or not
				 */
				path = path + "/" + folderDate;
				File folderDir = new File(path);
				if (!folderDir.exists() || !folderDir.isDirectory()) {
					folderDir.mkdir();
				}
				folderDir = null;

				/*
				 * iterate Map and write the Excel File for each Index
				 */
				String fName = "/"+ "CONTENTHISTORYDATA_REPORT.xlsx";
				File myFile = new File(path + fName);
				fName = null;
				// Create the workbook instance for XLSX file, KEEP 100 ROWS IN MEMMORY AND RET ON DISK
				SXSSFWorkbook myWorkBook = new SXSSFWorkbook(100);

				// Create a new sheet
				Sheet mySheet = myWorkBook.createSheet("Details");
				/*
				 * Add Header Row
				 */
				Row headerRow = mySheet.createRow(0);
				Cell autoGeneratedIdCell = headerRow.createCell(0);
				autoGeneratedIdCell.setCellValue("AUTO ID");
				Cell accountIdCell = headerRow.createCell(1);
				accountIdCell.setCellValue("RECORD ID");
				Cell fullNameCell = headerRow.createCell(2);
				fullNameCell.setCellValue("ACCOUNT ID");
				Cell displayNameCell = headerRow.createCell(3);
				displayNameCell.setCellValue("USER NAME");
				Cell firstNameCell=headerRow.createCell(4);
				firstNameCell.setCellValue("DOCUMENT ID");
				Cell lastNameCell = headerRow.createCell(5);
				lastNameCell.setCellValue("ANSWER ID");
				Cell emailIdCell = headerRow.createCell(6);
				emailIdCell.setCellValue("CONTENT TYPE");
				Cell reportingGroupCell = headerRow.createCell(7);
				reportingGroupCell.setCellValue("LOCALE");
				Cell dateSubmittedCell = headerRow.createCell(8);
				dateSubmittedCell.setCellValue("DATE OF OPERATION");
				
				Cell operationTypeCell = headerRow.createCell(9);
				operationTypeCell.setCellValue("OPERATION TYPE");
				Cell processingStatusCell = headerRow.createCell(10);
				processingStatusCell.setCellValue("PROCESSING STATUS");
				
				int rowCount = 0;
				for (TransactionDetails transDetails : historyDetailsList) 
				{
					ContentHistoryDetails details = transDetails.getContentHistoryDetails();
					if(null!=details)
					{
						// increment rowCount by 1
						rowCount++;
						// Create a new Row
						Row row = mySheet.createRow(rowCount);

						Cell cell201 = row.createCell(0);
						cell201.setCellValue("");
						if(null!=details.getAutoId() && !"".equals(details.getAutoId()))
						{
							cell201.setCellValue(details.getAutoId());
						}
						
						Cell cell0 = row.createCell(1);
						cell0.setCellValue("");
						if(null!=details.getRecordId() && !"".equals(details.getRecordId()))
						{
							cell0.setCellValue(details.getRecordId());
						}

						Cell cell1 = row.createCell(2);
						cell1.setCellValue("");
						if(null!=details.getLoginId() && !"".equals(details.getLoginId()))
						{
							cell1.setCellValue(details.getLoginId());
						}
						
						Cell cell23 = row.createCell(3);
						cell23.setCellValue("");
						if(null!=details.getUserDisplayName() && !"".equals(details.getUserDisplayName()))
						{
							cell23.setCellValue(details.getUserDisplayName());
						}
						
						Cell cell2 = row.createCell(4);
						cell2.setCellValue("");
						if(null!=details.getDocumentId() && !"".equals(details.getDocumentId()))
						{
							cell2.setCellValue(details.getDocumentId());
						}

						Cell cell3 = row.createCell(5);
						cell3.setCellValue("");
						if(null!=details.getAnswerId() && !"".equals(details.getAnswerId()))
						{
							cell3.setCellValue(details.getAnswerId());
						}

						Cell cell4 = row.createCell(6);
						if (null != details.getContentType() && !"".equals(details.getContentType())) 
						{
							cell4.setCellValue(details.getContentType());
						} 

						Cell cell5 = row.createCell(7);
						if (null != details.getLocaleLabel() && !"".equals(details.getLocaleLabel())) 
						{
							cell5.setCellValue(details.getLocaleLabel());
						} 

						Cell cell19 = row.createCell(8);
						cell19.setCellValue("");
						if(null!=details.getDateOfOperation())
						{
							cell19.setCellValue(details.getDateOfOperation().toString());
						}
						
						Cell cell6 = row.createCell(9);
						if(null!=details.getAutoId() && !"".equals(details.getAutoId()))
						{
							cell6.setCellValue("UPDATE_CONTENTHISTORY");
						}
						else
						{
							cell6.setCellValue("CREATE_CONTENTHISTORY");
						}

						Cell cell7 = row.createCell(10);
						/*
						 * INSTEAD OF ERROR SET IF ERROR LIST IS NOT NULL
						 */
//						if (transDetails.isHasError()==true)
						if(null!=transDetails.getErrorList() && transDetails.getErrorList().size()>0)
						{
							cell7.setCellValue("FAILURE");
						} 
						else
						{
							cell7.setCellValue("SUCCESS");
						}

						cell23 = null;
						cell19 =null;
						cell201 =null;
						cell7 = null;
						cell6 = null;
						cell5 = null;
						cell4 = null;
						cell3 = null;
						cell2 = null;
						cell1 = null;
						cell0 = null;

						row = null;
						details=  null;
					}
				}

				headerRow = null;
				accountIdCell= null;
				fullNameCell=null;
				lastNameCell=null;
				firstNameCell= null;
				emailIdCell=null;
				reportingGroupCell = null;
				operationTypeCell=  null;
				processingStatusCell= null;
				autoGeneratedIdCell = null;
				dateSubmittedCell = null;
				displayNameCell=  null;

				FileOutputStream os = new FileOutputStream(myFile);
				myWorkBook.write(os);
				logger.info("Writing on CONTENTHISTORY DATA REPORT XLSX file Finished ...");
				System.out.println("Writing on CONTENTHISTORY DATA REPORT XLSX file Finished ...");
				os.flush();
				os.close();

				// set mySheet to null
				mySheet = null;
				// set myWorkBook to null
				myWorkBook = null;
				// set path to null
				path = null;
				// set myFile to null
				myFile = null;
			}
		} 
		catch (Exception e) 
		{
			Utilities.printStackTraceToLogs(PrintReportUtils.class.getName(), "printContentHistoryDataReport()", e);
		}
		if (logger.isInfoEnabled())
			logger.info("printContentHistoryDataReport :: Method Ends.");
	}

	public static void printContentHistoryDataFailureReport(String reportsPath, String folderDate, Vector<TransactionDetails> historyDetailsList) 
	{
		if (logger.isInfoEnabled())
			logger.info("printContentHistoryDataFailureReport :: Method Starts.");
		try {
			if (null != historyDetailsList && historyDetailsList.size()>0) 
			{
				String path = reportsPath;
				/*
				 * Now check inside the reports directory does a directory exists
				 * for wslId
				 */
				File reportDir = new File(path);
				if (!reportDir.exists() || !reportDir.isDirectory()) {
					// create new directory
					reportDir.mkdir();
				}
				reportDir = null;
				/*
				 * Now check, whether the schedule code directory exists or not
				 */
				path = path + "/" + folderDate;
				File folderDir = new File(path);
				if (!folderDir.exists() || !folderDir.isDirectory()) {
					folderDir.mkdir();
				}
				folderDir = null;

				/*
				 * iterate Map and write the Excel File for each Index
				 */
				String fName = "/"+ "CONTENTHISTORYDATA_FAILURE_REPORT.xlsx";
				File myFile = new File(path + fName);
				fName = null;
				// Create the workbook instance for XLSX file, KEEP 100 ROWS IN MEMMORY AND RET ON DISK
				SXSSFWorkbook myWorkBook = new SXSSFWorkbook(100);

				// Create a new sheet
				Sheet mySheet = myWorkBook.createSheet("Failure Details");
				/*
				 * Add Header Row
				 */
				Row headerRow = mySheet.createRow(0);
				Cell autoGeneratedIdCell = headerRow.createCell(0);
				autoGeneratedIdCell.setCellValue("AUTO ID");
				Cell accountIdCell = headerRow.createCell(1);
				accountIdCell.setCellValue("RECORD ID");
				Cell displayNameCell = headerRow.createCell(2);
				displayNameCell.setCellValue("USER NAME");
				Cell fullNameCell = headerRow.createCell(3);
				fullNameCell.setCellValue("DOCUMENT ID");
				Cell operationTypeCell = headerRow.createCell(4);
				operationTypeCell.setCellValue("OPERATION TYPE");
				Cell errorCodeCell=headerRow.createCell(5);
				errorCodeCell.setCellValue("ERROR CODE");
				Cell remarksCell = headerRow.createCell(6);
				remarksCell.setCellValue("REMARKS");
				Cell errorMessageCell = headerRow.createCell(7);
				errorMessageCell.setCellValue("ERROR MESSAGE");
				Cell soapErrorMessageCell = headerRow.createCell(8);
				soapErrorMessageCell.setCellValue("SOAP ERROR RESPONSE");
				
				
				int rowCount = 0;
				for (TransactionDetails transDetails : historyDetailsList) 
				{
					ContentHistoryDetails details = new ContentHistoryDetails();
					if(null!=transDetails.getContentHistoryDetails())
					{
						details  =transDetails.getContentHistoryDetails();
					}
					
					if(null!=transDetails.getErrorList() && transDetails.getErrorList().size()>0)
					{
						for(ErrorDetails errorDetails : transDetails.getErrorList())
						{
							// increment rowCount by 1
							rowCount++;
							// Create a new Row
							Row row = mySheet.createRow(rowCount);

							Cell cell201 = row.createCell(0);
							cell201.setCellValue("");
							if(null!=details.getAutoId() && !"".equals(details.getAutoId()))
							{
								cell201.setCellValue(details.getAutoId());
							}

							Cell cell0 = row.createCell(1);
							cell0.setCellValue("");
							if(null!=details.getRecordId() && !"".equals(details.getRecordId()))
							{
								cell0.setCellValue(details.getRecordId());
							}
							
							Cell cell110 = row.createCell(2);
							cell110.setCellValue("");
							if(null!=details.getUserDisplayName() && !"".equals(details.getUserDisplayName()))
							{
								cell110.setCellValue(details.getUserDisplayName());
							}

							Cell cell1 = row.createCell(3);
							cell1.setCellValue("");
							if(null!=details.getDocumentId() && !"".equals(details.getDocumentId()))
							{
								cell1.setCellValue(details.getDocumentId());
							}

							Cell cell2 = row.createCell(4);
							cell2.setCellValue("");
							if(null!=transDetails.getOperationType() && !"".equals(transDetails.getOperationType()))
							{
								cell2.setCellValue(transDetails.getOperationType());
							}

							Cell cell3 = row.createCell(5);
							cell3.setCellValue("");
							if(null!=errorDetails.getErrorCode() && !"".equals(errorDetails.getErrorCode()))
							{
								cell3.setCellValue(errorDetails.getErrorCode());
							}
							
							Cell cell21 = row.createCell(6);
							cell21.setCellValue("");
							if(null!=errorDetails.getRemarks() && !"".equals(errorDetails.getRemarks()))
							{
								cell21.setCellValue(errorDetails.getRemarks());
							}
							
							Cell cell4 = row.createCell(7);
							if (null != errorDetails.getErrorMessage() && !"".equals(errorDetails.getErrorMessage())) 
							{
								cell4.setCellValue(errorDetails.getErrorMessage());
							} 

							Cell cell5 = row.createCell(8);
							if (null != errorDetails.getSoapEnvelopeErrorResponse() && !"".equals(errorDetails.getSoapEnvelopeErrorResponse())) 
							{
								cell5.setCellValue(errorDetails.getSoapEnvelopeErrorResponse());
							} 
							
							cell21 = null;
							cell110 = null;
							cell201 = null;
							cell5 = null;
							cell4 = null;
							cell3 = null;
							cell2 = null;
							cell1 = null;
							cell0 = null;

							row = null;
							errorDetails=  null;
						}
						details=  null;
					}
				}

				headerRow = null;
				accountIdCell= null;
				fullNameCell=null;
				operationTypeCell = null;
				autoGeneratedIdCell =null;
				errorCodeCell= null;
				errorMessageCell =null;
				soapErrorMessageCell = null;
				displayNameCell=null;
				remarksCell= null;

				FileOutputStream os = new FileOutputStream(myFile);
				myWorkBook.write(os);
				logger.info("Writing on CONTENTHISTORY DATA FAILURE REPORT XLSX file Finished ...");
				System.out.println("Writing on CONTENTHISTORY DATA FAILURE REPORT XLSX file Finished ...");
				os.flush();
				os.close();

				// set mySheet to null
				mySheet = null;
				// set myWorkBook to null
				myWorkBook = null;
				// set path to null
				path = null;
				// set myFile to null
				myFile = null;
			}
		} 
		catch (Exception e) 
		{
			Utilities.printStackTraceToLogs(PrintReportUtils.class.getName(), "printContentHistoryDataFailureReport", e);
		}
		if (logger.isInfoEnabled())
			logger.info("printContentHistoryDataFailureReport :: Method Ends.");
	}

}