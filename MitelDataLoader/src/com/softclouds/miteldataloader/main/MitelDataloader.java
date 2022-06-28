package com.softclouds.miteldataloader.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.softclouds.miteldataloader.dataStructures.Configuration;
import com.softclouds.miteldataloader.dataStructures.models.contenthistory.ContentHistoryRecord;
import com.softclouds.miteldataloader.dataStructures.models.contentrecommendation.ContentRecommendationDetails;
import com.softclouds.miteldataloader.dataStructures.models.contentrecommendation.ContentRecommendationRecord;
import com.softclouds.miteldataloader.dataStructures.models.userinfo.ErrorDetails;
import com.softclouds.miteldataloader.dataStructures.models.userinfo.TransactionDetails;
import com.softclouds.miteldataloader.dataStructures.models.userinfo.UserInfoRecord;
import com.softclouds.miteldataloader.osvc.Cruder;
import com.softclouds.miteldataloader.utility.FileUtility;
import com.softclouds.miteldataloader.utility.PrintReportUtils;
import com.softclouds.miteldataloader.utility.Table;
import com.softclouds.miteldataloader.utility.TimingUtility;
import com.softclouds.miteldataloader.utility.Utilities;

public class MitelDataloader extends HttpServlet
{
	private static final long serialVersionUID	= 7793143495953222112L;

	final static Logger	logger	=	Logger.getLogger(MitelDataloader.class);

	private static final String	OLD_TIME_FILE="data" + File.separator + "oldTime.txt";

	private static Configuration configuration;
	private static String reportsPath;
	private static String lastProcessedTimestampPath;
	private static String folderDate;
	private static OSVCandKAWebServiceCaller caller;
	public static String CFG_FILE_NAME	=	"configuration.txt";
	private static JSONObject defaultUserObject = new JSONObject();
//	private static String currentTimezone;

	static
	{
//		System.out.println("Loading configuration file at\n" + CFG_FILE_NAME + "\ncwd = "+ FileUtility.getCurrentWorkingDirectory());
		try
		{
			configuration = readConfiguration();
		}
		catch (JSONException e)
		{
			Utilities.printStackTraceToLogs(MitelDataloader.class.getName(), "init()", e);
//			System.out.println(ExceptionUtils.getFullStackTrace(e));
//			throw new UnsupportedOperationException(
//					"Invalid JSON.  Check your configuration file at " + CFG_FILE_NAME + ", cwd = "
//							+ FileUtility.getCurrentWorkingDirectory());
		}
		
		try
		{
			caller = new OSVCandKAWebServiceCaller(configuration);
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(MitelDataloader.class.getName(), "init()", e);
		}
	}

	public static void main(String[] args) throws JSONException
	{
		File jarPath = new File(MitelDataloader.class.getProtectionDomain()
				.getCodeSource().getLocation().getPath());
		String propertiesPath = jarPath.getParentFile().getAbsolutePath();
		PropertyConfigurator.configure(propertiesPath + "/log.properties");
		
		readConfiguration();
		
		/*
		 * IDENTIFY CURRENT TIMEZONE
		 * EDT / EST
		 * IF EDT THEN ADD 6 HOURS TO FETCHED TIME FROM KA
		 * IF EST THEN ADD 7 HOURS TO FETCHED TIME FROM KA
		 */
//		TimeZone tz = TimeZone.getDefault();
//		currentTimezone = tz.getDisplayName(tz.inDaylightTime(new Date()), TimeZone.SHORT) ;
//		logger.info("main() :: Current Time Zone is :: > " + currentTimezone);
		
		MitelDataloader mtd = new MitelDataloader();
		mtd.dataLoad();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String result = dataLoad();

		PrintWriter out = response.getWriter();

		out.write(result);
		// test(request, response);

	}

	protected String dataLoad()
	{
		String ret = "";
		Timestamp lastTimeFromProps= null;
		Timestamp endTimeFromProps = null;
		Timestamp newLastProcessedTime = new Timestamp(new Date().getTime());
		DateTime lastProcessedTime = null; 
		DateTime endProcessedTime =  null;
		try
		{
			/*
			 * READ LAST PROCESSED TIMESTAMP 
			 */	
			if(null!=lastProcessedTimestampPath && !"".equals(lastProcessedTimestampPath))
			{
				lastTimeFromProps = Utilities.readTimestampValues(lastProcessedTimestampPath, "START_TIME");
				endTimeFromProps = Utilities.readTimestampValues(lastProcessedTimestampPath, "END_TIME");
			}
			
			// CONVERT TO DATE TIME
			if(null!=lastTimeFromProps)
			{
				lastProcessedTime = new DateTime(lastTimeFromProps.getTime());
				/*
				 * SUBTRACT 1 HOUR HERE, TO KEEP EVERYTHING IN SYNC, ELSE LAST MODIFIED
				 * RECORDS WITH IN TIME RANGE ARE GETTING SKIPPED
				 */
				long oneHourTime = (1*60*60)*1000;
				long actTime = lastProcessedTime.getMillis() - oneHourTime;
				lastProcessedTime = new DateTime(actTime);
			}
			if(null!=endTimeFromProps)
			{
				/*
				 * SUBTRACT 1 HOUR HERE, TO KEEP EVERYTHING IN SYNC, ELSE  MODIFIED
				 * RECORDS WITH IN TIME RANGE ARE GETTING SKIPPED
				 */
				endProcessedTime = new DateTime(endTimeFromProps.getTime());
				long oneHourTime = (1*60*60)*1000;
				long actTime = endProcessedTime.getMillis() - oneHourTime;
				endProcessedTime = new DateTime(actTime);
			}
			long time[] = new long[4];
			time[0] = TimingUtility.getAbsTime();

			// set folderDate as time[0] which will be used for generating reports.
			SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yyyy_hh-mm-ss");
			folderDate = sdf.format(new Date(time[0]));
			sdf = null;

			System.out.println("------------------------------- PROCEED FOR UPDATING RECOMMENDATIONS ----------------------");
			updateRecommendations(lastProcessedTime, endProcessedTime);
			time[1] = TimingUtility.getAbsTime();
			System.out.println("------------------------------- PROCEED FOR UPDATING USERS ----------------------");
			updateUsers();
			time[2] = TimingUtility.getAbsTime();
			System.out.println("------------------------------- PROCEED FOR UPDATING CONTENT HISTORY ----------------------");
			updateContentHistory(lastProcessedTime, endProcessedTime);
			time[3] = TimingUtility.getAbsTime();
			
			for (int i = 0; i < time.length - 1; i++)
			{
				long endTime = time[i + 1];
				long startTime = time[i];
				long runTime = endTime - startTime;

				String runTimeStr = TimingUtility.longTimeToTimeString(runTime);
				String msg = "Completed in " + runTimeStr;
				System.out.println(msg);
				ret += msg + "\n";
			}
			
			/*
			 * call Function to Write New Last ProcessedTimestamp.
			 */
			Utilities.writeLastProcessedTime(newLastProcessedTime, lastProcessedTimestampPath);
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(MitelDataloader.class.getName(), "dataLoad()", e);
		}
		return ret;
	}

	/**
	 * Reads the date from the OLD_TIME_FILE. If it doesn't exist, or there's an error, returns
	 * today at the start of day.
	 * 
	 * @return
	 */
	public static DateTime readDate()
	{
		if (!FileUtility.exists(OLD_TIME_FILE))
		{
			return DateTime.now().withTimeAtStartOfDay();
		}

		String oldTimeStr = FileUtility.read(OLD_TIME_FILE);

		try
		{
			DateTimeFormatter formatter =
					DateTimeFormat.forPattern("YYYY'-'MM'-'dd'T'HH:mm:ss.SSSZ").withOffsetParsed();
			DateTime ret = formatter.parseDateTime(oldTimeStr);
			return ret;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return DateTime.now().withTimeAtStartOfDay();
		}
	}

	protected void test(HttpServletRequest request, HttpServletResponse response)
			throws FileNotFoundException
	{

		// try
		// {
		// System.out.println("userInfo\n" + caller.getUserInfo("GKim"));
		// // System.out.println("call content\n" +
		// // caller.callKAWebservice("/content/docId/AL127/"));
		// }
		// catch (JSONException e1)
		// {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }

		// Cruder cruder = new Cruder(configuration);

		// cruder.create();

		// works!
		// cruder.read();
		// cruder.create();

		// Cruder cruder = new Cruder(configuration);
		// for (int i = 99; i <= 108; i++)
		// cruder.destroy(i, "recommendation");
		//

		// Cruder cruder = new Cruder(configuration);
		// cruder.destroyAll("recommendation");

		// try
		// {
		// JSONObject history = caller.callKAWebservice("/content/docId/HO1689/history");
		// System.out.println("----\nhistory\n" + history + "/history");
		// }
		// catch (JSONException e)
		// {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// Cruder cruder = new Cruder(configuration);
		// String result = cruder.queryObjects("SELECT KMS.user_info FROM KMS.user_info WHERE ID >
		// 3");
		//
		// System.out.println("result is\n" + result);

		// validateContentRecommendationStatuses();
	}

	public static Configuration readConfiguration() throws JSONException
	{
		File jarPath = new File(MitelDataloader.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		String filePath = jarPath.getParentFile().getAbsolutePath();
		if(!filePath.endsWith("\\"))
		{
			filePath= filePath+"\\";
		}
		filePath= filePath+CFG_FILE_NAME;
		
		JSONObject allConfigJson;
		String data = FileUtility.read(filePath);
		allConfigJson = new JSONObject(data);
		String useConnection = allConfigJson.getString("useConnection");
		logger.info("readConfiguration :: Using connection information from '" + useConnection + "' JSON Object.");
		reportsPath = allConfigJson.getString("reportsPath");
		lastProcessedTimestampPath = allConfigJson.getString("lastProcessedTimestampPath");
		defaultUserObject = new JSONObject();
		defaultUserObject.put("defaultUserName", allConfigJson.getString("defaultUserName"));
		defaultUserObject.put("defaultUserLoginId", allConfigJson.getString("defaultUserLoginId"));
		filePath  =null;
		
		JSONObject configJson = allConfigJson.getJSONObject(useConnection);
		return new Configuration(configJson);
	}

	private void updateContentHistory(DateTime lastProcessedTime, DateTime  endProcessedTime)
	{
		Vector<String> docIDs  = new Vector<String>();
		Vector<TransactionDetails> contentHistoryData = new Vector<TransactionDetails>();
		try
		{
			/*
			 * CALL FUNCTION TO IDENTIFY ALL DOCUMENT IDs
			 */
			docIDs= getAllDocIDs(lastProcessedTime , endProcessedTime);
			if(null!=docIDs && docIDs.size()>0)
			{
				logger.info("updateContentHistory :: Total Documents Found are :: >" + docIDs.size());
				Cruder cruder = new Cruder(configuration);
//				cruder.destroyAll("content_history");
				
				ContentHistoryRecord info = new ContentHistoryRecord();
				for(String documentId: docIDs)
				{
					/*
					 * GET ALL THE HISTORY DATA FOR DOCUMENT ID
					 */
					JSONObject history = null;
					try
					{
						history =caller.callKAWebservice("/content/docId/" + documentId + "/history");
					}
					catch(Exception e)
					{
						Utilities.printStackTraceToLogs(MitelDataloader.class.getName(), "updateContentHistory()", e);
					}
					if(null!=history)
					{
						/*
						 * EXTRACT ALL THE HISTORY DATA FOR THE DOCUMENT
						 */
						JSONArray items = history.getJSONArray("items");
						if(null!=items && items.length()>0)
						{
							for(int a=0;a<items.length();a++)
							{
								JSONObject itemObject = (JSONObject)items.get(a);
								TransactionDetails transDetails = info.extractContentHistoryData(itemObject, caller , cruder, defaultUserObject);
								if(null!=transDetails && null!=transDetails.getContentHistoryDetails() && null!=transDetails.getContentHistoryDetails().getRecordId())
								{
									// add to Vector
									contentHistoryData.add(transDetails);
								}
								transDetails = null;
								itemObject = null;
							}
						}
						items = null;
					}
					else
					{
						logger.info("updateContentHistory :: No History Data Found for Document Id {"+documentId+"}. Skip It.");
					}
					history = null;
				}
				
				if(null!=contentHistoryData && contentHistoryData.size()>0)
				{
					logger.info("updateContentHistory :: Total History Data Found for Processing are :: > " + contentHistoryData.size());
					
					for(TransactionDetails transData: contentHistoryData)
					{
						if(null!=transData.getContentHistoryDetails())
						{
							String recordId=transData.getContentHistoryDetails().getRecordId();
							/*
							 * NOW CHECK IF DATA FOR THE CONTENT HISTORY RECORD ID ALREADY EXISTS OR NOT
							 * IF EXISTS - THEN UPDATE THE RECORD
							 * ELSE INSERT THE RECORD
							 */
							// FETCH ID ON THE BASIS OF RECORD ID
							String getContentHistoryPayLoad = info.getContentHistoryPayload(recordId);
							if(null!=getContentHistoryPayLoad && !"".equals(getContentHistoryPayLoad))
							{
								transData.setPayloadBody(getContentHistoryPayLoad);
								transData.setOperationType("GET_CONTENTHISTORY");
								transData = cruder.fetch(transData);

								// EXTRACT ID from the Received SOAP RESPONSE
								String autoId="";
								if(null!=transData.getSoapEnvelopeResponse() && !"".equals(transData.getSoapEnvelopeResponse()))
								{
									try
									{
										Vector<Table> dataTable = cruder.getTablesFromXML(transData.getSoapEnvelopeResponse());
										if(null!=dataTable && dataTable.size()>0)
										{
											// check for the value at row_0
											Table data = dataTable.get(0);
											if(null!=data && data.size()>0)
											{
												Vector<String> idValue = data.getRow("row_0");
												if(null!=idValue && idValue.size()>0)
												{
													autoId = idValue.get(0).toString();
												}
												idValue = null;
											}
											data = null;
										}
										dataTable=  null;
									}
									catch(Exception e)
									{
										Utilities.printStackTraceToLogs(MitelDataloader.class.getName(), "updateContentHistory()", e);
									}
								}
								
								if(null!=autoId && !"".equals(autoId))
								{
									logger.info("updateContentHistory :: Auto Id fetched for {"+recordId+"} is :: > {"+autoId+"}. Proceed for Updating Existing ContentHistory. ");
									// set OperationType & Update Auto Id
									transData.setOperationType("UPDATE_CONTENTHISTORY");
									transData.getContentHistoryDetails().setAutoId(autoId);
									
									// getUpdateBodyPayLoad
									transData.setPayloadBody(info.getUpdatePayLoad(transData.getContentHistoryDetails()));
									transData = cruder.update(transData);
								}
								else
								{
									logger.info("updateContentHistory :: No Auto Id fetched for {"+recordId+"}. Proceed for Creating New ContentHistory. ");
									// set OperationType
									transData.setOperationType("CREATE_CONTENTHISTORY");
									// getCreateBodyPayLoad
									transData.setPayloadBody(info.getCreatePayload(transData.getContentHistoryDetails()));
									transData = cruder.create(transData);
								}
								autoId  =null;
							}
							getContentHistoryPayLoad = null;
							recordId =null;
						}
					}
				}
				else
				{
					logger.info("updateContentHistory :: No History Data Found for Processing.");
				}
			}
			else
			{
				logger.info("updateContentHistory :: No Document Ids Found for Processing Content History.");
			}
			docIDs = null;
			
			/*
			 * call function to print reports
			 */
			PrintReportUtils.printContentHistoryDataReport(reportsPath, folderDate, contentHistoryData);
			PrintReportUtils.printContentHistoryDataFailureReport(reportsPath, folderDate, contentHistoryData);
			
			contentHistoryData =  null;
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(MitelDataloader.class.getName(), "updateContentHistory()", e);
		}
	}

	/**
	 * Gets all document IDs.
	 * 
	 * @return
	 */
	private Vector<String> getAllDocIDs(DateTime lastProcessedTime, DateTime endProcessedTime)
	{
		DateTimeFormatter formatter = DateTimeFormat.forPattern("YYYY'-'MM'-'dd'T'HH:mm:ssZZ");
		Vector<String> ret = new Vector<String>();
		boolean hasMore = true;
		int limit = 1000;
		int offset = 0;
		while (hasMore)
		{
			JSONObject content = null;
			try
			{
				content = caller.callKAWebservice("/content?contentState=LATESTVALID&limit=" + limit + "&offset=" + offset);
			}
			catch(Exception e)
			{
				Utilities.printStackTraceToLogs(MitelDataloader.class.getName(), "getAllDocIDs()", e);
			}

			offset += limit;
			if(content==null)
				continue;
			try
			{
				hasMore = content.getBoolean("hasMore");
				JSONArray items = content.getJSONArray("items");
				if(null!=items && items.length()>0)
				{
					for (int i = 0; i < items.length(); i++)
					{
						JSONObject item = items.getJSONObject(i);
						if(null!=item.get("documentId"))
						{
							String id = item.getString("documentId");

							/*
							 * identify dateModified value from JSON
							 * 
							 * Now check, if LAST MODIFIED TIME AND END TIME IS DEFINED
							 * 	THEN CHECK IF DATE MODIFIED LIES IN BETWEEN LAST TIME AND END TIME
							 * 	ADD TO VECTOR
							 * ELSE IF LAST MODIFIED TIME IS NOT NULL AND END TIME IS NULL
							 * 	THEN CHECK IF DATE MODIFIED IS GREATER THEN OR EQUALS TO LAST TIME
							 * 	ADD TO VECTOR
							 * ELSE 
							 * 	ADD TO VECTOR
							 */
							DateTime dateModified = null;
							if(null!=item.get("dateModified"))
							{
								try
								{
									String value = String.valueOf(item.get("dateModified"));
									DateTime convertedDate = formatter.parseDateTime(value);
									if(null!=convertedDate)
									{
										/*
										 * SUBTRACT 1 HOURS FROM CONVERTED TIME.
										 */
										long timeValue = convertedDate.getMillis();
										long oneHourTime = (1*60*60)*1000;
										timeValue = timeValue - oneHourTime;
										dateModified = new DateTime(timeValue);
										/*
										String currentTimezone = "";
										if(value.endsWith("-0500"))
										{
											currentTimezone  = "EDT"; 
										}
										else if(value.endsWith("-0600"))
										{
											currentTimezone  = "EST";
										}
										long timeValue = convertedDate.getMillis();
										if(null!=currentTimezone && !"".equals(currentTimezone))
										{
											if(currentTimezone.trim().toLowerCase().equals("edt"))
											{
												// add 3 hours to fetched time
												long sixHoursTime=(3*60*60)*1000;
												timeValue= timeValue+ sixHoursTime;
											}
											else if(currentTimezone.trim().toLowerCase().equals("est"))
											{
												// add 4 hours to fetched time.
												long sevenHoursTime=(4*60*60)*1000;
												timeValue = timeValue + sevenHoursTime;
											}
										}
										*/
//										dateModified = new DateTime(timeValue);
//										currentTimezone = null;
									}
									convertedDate = null;
									value= null;
								}
								catch(Exception e)
								{
									Utilities.printStackTraceToLogs(MitelDataloader.class.getName(), "getAllDocIDs()", e);
								}
							}
							
							boolean addToList = false;
							if(null!=lastProcessedTime && null!=endProcessedTime && null!=dateModified)
							{
								if(dateModified.getMillis()>=lastProcessedTime.getMillis() && dateModified.getMillis()<=endProcessedTime.getMillis())
								{
									addToList = true;
								}
							}
							else if(null!=lastProcessedTime && null!=dateModified && null==endProcessedTime)
							{
								if(dateModified.getMillis()>=lastProcessedTime.getMillis())
								{
									addToList= true;
								}
							}
							else
							{
								addToList = true;
							}
							
							if(addToList==true)
							{
								if(null!=id && !"".equals(id))
								{
									ret.add(id);
								}
							}
							id = null;
							dateModified = null;
						}
						
					}
				}
			}
			catch (Exception e)
			{
				Utilities.printStackTraceToLogs(MitelDataloader.class.getName(), "getAllDocIDs()", e);
			}
		}
		return ret;
	}

	/**
	 * This method updates content recommendations in the custom OSvC table.
	 * 
	 */
	private void updateUsers()
	{
		long startTime = TimingUtility.getAbsTime();
		logger.info("updateUsers :: Starting User Data Processing at StartTime :: > " + new Date(startTime));
		System.out.println("Starting User Data Processing at StartTime :: > " + new Date(startTime));
		Vector<TransactionDetails> uirs = null;
		try
		{
			/*
			 * no need of deleting existing users information.
			 */
			// delete the current table
			Cruder cruder = new Cruder(configuration);
//			cruder.destroyAll("user_info");
			/*
			 * Invoke Web Service to Fetch all the User Accounts Information.
			 */
			JSONObject userInfoJSON = caller.callOSvCWebservice("/accounts/");
			if(null!=userInfoJSON )
			{
				/*
				 * PROCEED FOR ITERATING USER INFO JSON AND IDENTIFYING ALL THE USERS DATA
				 */
				uirs = UserInfoRecord.extractFromContent(userInfoJSON, caller);
				if(null!=uirs && uirs.size()>0)
				{
					UserInfoRecord info = new UserInfoRecord();
					for(TransactionDetails transactionData : uirs)
					{
						/*
						 * CHECK FOR USER, IF ALREADY EXISTS - THEN UPDATE 
						 * RECORD
						 * ELSE INSERT RECORD.
						 */
						if(null!=transactionData.getUserDetails())
						{
							String loginId = transactionData.getUserDetails().getLoginId();
							// FETCH ID ON THE BASIS OF LOGIN ID.
							String getUserPayLoad = info.getUserPayload(loginId);
							if(null!=getUserPayLoad && !"".equals(getUserPayLoad))
							{
								transactionData.setPayloadBody(getUserPayLoad);
								transactionData.setOperationType("GET_USER");
								transactionData = cruder.fetch(transactionData);

								// EXTRACT ID from the Received SOAP RESPONSE
								String autoId="";
								if(null!=transactionData.getSoapEnvelopeResponse() && !"".equals(transactionData.getSoapEnvelopeResponse()))
								{
									try
									{
										Vector<Table> dataTable = cruder.getTablesFromXML(transactionData.getSoapEnvelopeResponse());
										if(null!=dataTable && dataTable.size()>0)
										{
											// check for the value at row_0
											Table data = dataTable.get(0);
											if(null!=data && data.size()>0)
											{
												Vector<String> idValue = data.getRow("row_0");
												if(null!=idValue && idValue.size()>0)
												{
													autoId = idValue.get(0).toString();
												}
												idValue = null;
											}
											data = null;
										}
										dataTable=  null;
									}
									catch(Exception e)
									{
										Utilities.printStackTraceToLogs(MitelDataloader.class.getName(), "updateUsers", e);
									}
								}

								if(null!=autoId && !"".equals(autoId))
								{
									logger.info("updateUsers :: Auto Id fetched for {"+loginId+"} is :: > {"+autoId+"}. Proceed for Updating Existing User. ");
									// set OperationType & Update Auto Id
									transactionData.setOperationType("UPDATE_USER");
									transactionData.getUserDetails().setAutoId(autoId);

									// getUpdateBodyPayLoad
									transactionData.setPayloadBody(info.getUpdatePayLoad(transactionData.getUserDetails()));
									transactionData = cruder.update(transactionData);
								}
								else
								{
									logger.info("updateUsers :: No Auto Id fetched for {"+loginId+"}. Proceed for Creating New User. ");
									// set OperationType
									transactionData.setOperationType("CREATE_USER");
									// getCreateBodyPayLoad
									transactionData.setPayloadBody(info.getCreatePayload(transactionData.getUserDetails()));
									transactionData = cruder.create(transactionData);
								}
								autoId  =null;

							}
							loginId= null;
						}
					}
				}
				else
				{
					logger.info("updateUsers :: No User Account Information Extracted for Processing.");
				}
			}
		}
		catch (JSONException e)
		{
			Utilities.printStackTraceToLogs(MitelDataloader.class.getName(), "updateUsers()", e);
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(MitelDataloader.class.getName(), "updateUsers()", e);
		}
		
		/*
		 * call function to Print User Report.
		 */
		PrintReportUtils.printUserDataReport(reportsPath, folderDate, uirs);
		PrintReportUtils.printUserDataFailureReport(reportsPath, folderDate, uirs);
	
		// set userData to null
		uirs  =null;
		
		long endTime = TimingUtility.getAbsTime();
		logger.info("updateUsers :: Ending User Data Processing at EndTime :: > " + new Date(endTime));
		System.out.println("Ending User Data Processing at EndTime :: > " + new Date(endTime));
	}

	/**
	 * This method updates content recommendations in the custom OSvC table.
	 */
	private void updateRecommendations(DateTime lastProcessedTime, DateTime endProcessedTime)
	{
		Vector<String> contentRecommendationIDs = null;
		Vector<TransactionDetails> recommendationList = new Vector<TransactionDetails>();
		try
		{
			/*
			 * CALL FUNCTION TO GET ALL THE CONTENT RECOMMENDATION IDs.
			 */
			contentRecommendationIDs = getAllContentRecommendationIDs(lastProcessedTime, endProcessedTime);
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(MitelDataloader.class.getName(), "updateRecommendations()", e);
		}
		
		if(null!=contentRecommendationIDs && contentRecommendationIDs.size()>0)
		{
			logger.info("updateRecommendations :: Total Content Recommendations Found are :: >" + contentRecommendationIDs.size());
			
			Cruder cruder = new Cruder(configuration);
//			cruder.destroyAll("recommendation");
			
			ContentRecommendationRecord info = new ContentRecommendationRecord();
			for (String recordID : contentRecommendationIDs)
			{
				JSONObject json = null;
				TransactionDetails transactionDetails = new TransactionDetails();
				transactionDetails.setRecommendationDetails(new ContentRecommendationDetails());
				transactionDetails.getRecommendationDetails().setRecordId(recordID);
				try
				{
					// CALL KA WebService to fetch Data on the Basis of Record Id.
					json = caller.callKAWebservice("/contentRecommendations/" + recordID);
					if(null!=json)
					{
						/*
						 * ITERATE JSON DATA AND PREPARE DATA 
						 */
						transactionDetails = info.extractRecommdatationDataFromJSON(json, caller, transactionDetails, cruder, defaultUserObject);
						/*
						 * NOW CHECK IF DATA FOR THE RECOMMENDATION RECORD ID ALREADY EXISTS OR NOT
						 * IF EXISTS - THEN UPDATE THE RECORD
						 * ELSE INSERT THE RECORD
						 */
						// FETCH ID ON THE BASIS OF RECORD ID
						String getRecommendationPayLoad = info.getRecommendationPayload(transactionDetails.getRecommendationDetails().getRecordId());
						if(null!=getRecommendationPayLoad && !"".equals(getRecommendationPayLoad))
						{
							transactionDetails.setPayloadBody(getRecommendationPayLoad);
							transactionDetails.setOperationType("GET_RECOMMENDATION");
							transactionDetails = cruder.fetch(transactionDetails);

							// EXTRACT ID from the Received SOAP RESPONSE
							String autoId="";
							if(null!=transactionDetails.getSoapEnvelopeResponse() && !"".equals(transactionDetails.getSoapEnvelopeResponse()))
							{
								try
								{
									Vector<Table> dataTable = cruder.getTablesFromXML(transactionDetails.getSoapEnvelopeResponse());
									if(null!=dataTable && dataTable.size()>0)
									{
										// check for the value at row_0
										Table data = dataTable.get(0);
										if(null!=data && data.size()>0)
										{
											Vector<String> idValue = data.getRow("row_0");
											if(null!=idValue && idValue.size()>0)
											{
												autoId = idValue.get(0).toString();
											}
											idValue = null;
										}
										data = null;
									}
									dataTable=  null;
								}
								catch(Exception e){
									Utilities.printStackTraceToLogs(MitelDataloader.class.getName(), "updateRecommendations()", e);
								}
							}
							
							if(null!=autoId && !"".equals(autoId))
							{
								logger.info("updateRecommendations :: Auto Id fetched for {"+recordID+"} is :: > {"+autoId+"}. Proceed for Updating Existing Recommendation. ");
								// set OperationType & Update Auto Id
								transactionDetails.setOperationType("UPDATE_RECOMMENDATION");
								transactionDetails.getRecommendationDetails().setAutoId(autoId);
								
								// getUpdateBodyPayLoad
								transactionDetails.setPayloadBody(info.getUpdatePayLoad(transactionDetails.getRecommendationDetails()));
								transactionDetails = cruder.update(transactionDetails);
							}
							else
							{
								logger.info("updateRecommendations :: No Auto Id fetched for {"+recordID+"}. Proceed for Creating New Recommendation. ");
								// set OperationType
								transactionDetails.setOperationType("CREATE_RECOMMENDATION");
								// getCreateBodyPayLoad
								transactionDetails.setPayloadBody(info.getCreatePayload(transactionDetails.getRecommendationDetails()));
								transactionDetails = cruder.create(transactionDetails);
							}
							autoId  =null;
						}
					}
				}
				catch(Exception e)
				{
					Utilities.printStackTraceToLogs(MitelDataloader.class.getName(), "updateRecommendations()", e);
					/*
					 * ADD ERROR DETAILS. FAILED TO FETCH CONTENT RECOMMENDATION DATA FOR RECORD ID
					 */
					ErrorDetails errorDetails = new ErrorDetails();
					errorDetails.setErrorCode("");
					errorDetails.setErrorMessage("FAILED TO FETCH CONTENT RECOMMENDATION NODE FOR RECORD ID "+ recordID);
					if(null==transactionDetails.getErrorList() || transactionDetails.getErrorList().size()<=0)
					{
						transactionDetails.setErrorList(new ArrayList<ErrorDetails>());
					}
					transactionDetails.getErrorList().add(errorDetails);
					errorDetails = null;
				}
				json = null;
				recommendationList.add(transactionDetails);
				transactionDetails = null;
				recordID =null;
			}
		}
		else
		{
			logger.info("updateRecommendations :: No Content Recommendations Data Found.");
		}
		
		/*
		 * CALL FUNCTION TO PRINT RECOMMENDATION REPORTS
		 */
		PrintReportUtils.printRecommendationDataReport(reportsPath, folderDate, recommendationList);
		PrintReportUtils.printRecommendationDataFailureReport(reportsPath, folderDate, recommendationList);
		recommendationList=  null;
		contentRecommendationIDs = null;
	}

	private Vector<String> getAllContentRecommendationIDs(DateTime lastProcessedTime , DateTime endProcessedTime)
	{
		DateTimeFormatter formatter = DateTimeFormat.forPattern("YYYY'-'MM'-'dd'T'HH:mm:ssZZ");
		Vector<String> ret = new Vector<String>();
		boolean hasMore = true;
		int limit = 100;
		int offset = 0;
		while (hasMore)
		{
			JSONObject contentRecommendations = null;
			try
			{
				// note we can also use the mode=FULL& http parameter, but it omits certain results
				// with {links} type JSON objects. Don't know why exactly.
				contentRecommendations = caller.callKAWebservice(
						"/contentRecommendations?limit=" + limit + "&offset=" + offset);
			}
			catch (Exception e)
			{
				Utilities.printStackTraceToLogs(MitelDataloader.class.getName(), "getAllContentRecommendationIDs()", e);
			}

			offset += limit;
			if (contentRecommendations == null)
				continue;
			try
			{
				hasMore = contentRecommendations.getBoolean("hasMore");
				// get all recordIDs
				JSONArray items = contentRecommendations.getJSONArray("items");
				if(null!=items && items.length()>0)
				{
					for (int i = 0; i < items.length(); i++)
					{
						JSONObject item = items.getJSONObject(i);
						if(null!=item.get("recordId"))
						{
							String recordId = item.getString("recordId");
							/*
							 * identify dateModified value from JSON
							 * 
							 * Now check, if LAST MODIFIED TIME AND END TIME IS DEFINED
							 * 	THEN CHECK IF DATE MODIFIED LIES IN BETWEEN LAST TIME AND END TIME
							 * 	ADD TO VECTOR
							 * ELSE IF LAST MODIFIED TIME IS NOT NULL AND END TIME IS NULL
							 * 	THEN CHECK IF DATE MODIFIED IS GREATER THEN OR EQUALS TO LAST TIME
							 * 	ADD TO VECTOR
							 * ELSE 
							 * 	ADD TO VECTOR
							 */
							DateTime dateModified = null;
							if(null!=item.get("dateModified"))
							{
								try
								{
									String value = String.valueOf(item.get("dateModified"));
									DateTime convertedDate = formatter.parseDateTime(value);
									if(null!=convertedDate)
									{
										/*
										 * SUBTRACT 1 HOURS FROM CONVERTED TIME.
										 */
										long timeValue = convertedDate.getMillis();
										long oneHourTime = (1*60*60)*1000;
										timeValue = timeValue - oneHourTime;
										dateModified = new DateTime(timeValue);
										
										/*
										String currentTimezone = "";
										if(value.endsWith("-0500"))
										{
											currentTimezone  = "EDT"; 
										}
										else if(value.endsWith("-0600"))
										{
											currentTimezone  = "EST";
										}
										long timeValue = convertedDate.getMillis();
										if(null!=currentTimezone && !"".equals(currentTimezone))
										{
											if(currentTimezone.trim().toLowerCase().equals("edt"))
											{
												// add 3 hours to fetched time
												long sixHoursTime=(3*60*60)*1000;
												timeValue= timeValue+ sixHoursTime;
											}
											else if(currentTimezone.trim().toLowerCase().equals("est"))
											{
												// add 4 hours to fetched time.
												long sevenHoursTime=(4*60*60)*1000;
												timeValue = timeValue + sevenHoursTime;
											}
										}
										dateModified = new DateTime(timeValue);
										currentTimezone = null;
										*/
									}
									convertedDate = null;
									value= null;
								}
								catch(Exception e)
								{
									Utilities.printStackTraceToLogs(MitelDataloader.class.getName(), "getAllContentRecommendationIDs()", e);
								}
							}
							
							
							boolean addToList = false;
							if(null!=lastProcessedTime && null!=endProcessedTime && null!=dateModified)
							{
								if(dateModified.getMillis()>=lastProcessedTime.getMillis() && dateModified.getMillis()<=endProcessedTime.getMillis())
								{
									addToList = true;
								}
							}
							else if(null!=lastProcessedTime && null!=dateModified && null==endProcessedTime)
							{
								if(dateModified.getMillis()>=lastProcessedTime.getMillis())
								{
									addToList= true;
								}
							}
							else
							{
								addToList = true;
							}
							
							if(addToList==true)
							{
								if(null!=recordId && !"".equals(recordId))
								{
									ret.add(recordId);
								}
							}
							recordId = null;
							dateModified  =null;
						}
					}
				}
				items =null;
			}
			catch (JSONException e)
			{
				Utilities.printStackTraceToLogs(MitelDataloader.class.getName(), "getAllContentRecommendationIDs()", e);
			}
		}
		return ret;
	}
}