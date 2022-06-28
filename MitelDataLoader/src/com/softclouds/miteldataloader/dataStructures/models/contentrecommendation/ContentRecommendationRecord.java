package com.softclouds.miteldataloader.dataStructures.models.contentrecommendation;

import java.util.ArrayList;
import java.util.Vector;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONObject;

import com.softclouds.miteldataloader.dataStructures.cruder.DataType;
import com.softclouds.miteldataloader.dataStructures.models.casehistory.CaseHistoryRecord;
import com.softclouds.miteldataloader.dataStructures.models.menuitems.Status;
import com.softclouds.miteldataloader.dataStructures.models.userinfo.ErrorDetails;
import com.softclouds.miteldataloader.dataStructures.models.userinfo.TransactionDetails;
import com.softclouds.miteldataloader.main.OSVCandKAWebServiceCaller;
import com.softclouds.miteldataloader.osvc.Cruder;
import com.softclouds.miteldataloader.utility.Table;
import com.softclouds.miteldataloader.utility.Utilities;

/**
 * This class models the data that we input into OSvC for a content history record in the custom
 * OSvC table.
 * 
 * 
 * From "Table Designs.xlsx" (recommendation sheet) made by Grace Kim
 * 
 * Field Data Type Description
 * 
 * acct_id Menu OSvC staff account id by group.
 * 
 * ID Integer Unique record id.
 * 
 * status Menu Status of the content recommendation task in Authoring.
 * 
 * date_submitted Date Time Date when the recommendation was submitted by user.
 * 
 * title Text Title of the recommendation. This field is filled out on a web form that appears to
 * users when the Recommend Content button is pressed.
 * 
 */

public class ContentRecommendationRecord
{
	public TransactionDetails extractRecommdatationDataFromJSON(JSONObject item, OSVCandKAWebServiceCaller caller, TransactionDetails transDetails, Cruder cruder, JSONObject defaultUserObject)
	{
		try
		{
			if(null!=item)
			{
				// getUserRecordID
				String userRecordId="";
				String userName="";
				if(null!=item.get("requestedByUserId"))
				{
					userRecordId = String.valueOf(item.get("requestedByUserId"));
				}
				if(null!=item.get("requestedByUserName"))
				{
					userName= String.valueOf(item.get("requestedByUserName"));
				}
				
				/*
				 * PROCESS FOR FETCHING THE USER_ID OR ACCT_ID IS CHANGED - 7 MAY 2017
				 * NOW FETCH THE ACCT_ID FROM CASE_HISTORY TABLE ON THE BASIS OF RECOMMENDATION ID.
				 * IF FOUND - THEN SET THE ACCT_ID / FULL NAME OF THE IDENTIFIED VALUE
				 * ELSE - PASS THE VALUE OF REQUESTED BY USER ID - FETCH ITS USER NAME AS WELL.
				 * 
				 * 
				 * NEW CHANGE FOR USER MAPPING  -17 MAY 2017
				 * CHECK FOR ACCT_ID AND FUL_NAME IN CASE_HISTORY ON THE BASIS OF KA_REC_ID
				 * 	IF FOUND - MAP THEM
				 * 	IF NOT FOUND - USE DEFAULT USER
				 * 	
				 */
				String userIdFromCaseHistory="";
				String userNameFromCaseHistory="";
				CaseHistoryRecord caseHistoryRecord = new CaseHistoryRecord();
				String getAccountDetailsOnKARecordIdPayLoad = caseHistoryRecord.getAccountDetailsOnKARecordId(transDetails.getRecommendationDetails().getRecordId());
				TransactionDetails tDetails = new TransactionDetails();
				tDetails.setPayloadBody(getAccountDetailsOnKARecordIdPayLoad);
				tDetails.setOperationType("GET_CASE_HISTORY_DATA");
				try
				{
					tDetails = cruder.fetch(tDetails);
					// EXTRACT ACCT ID AND FULL NAME FROM SOAP RESPONSE
					if(null!=tDetails.getSoapEnvelopeResponse() && !"".equals(tDetails.getSoapEnvelopeResponse()))
					{
						Vector<Table> dataTable = cruder.getTablesFromXML(tDetails.getSoapEnvelopeResponse());
						if(null!=dataTable && dataTable.size()>0)
						{
							// check for the value at row_0
							Table data = dataTable.get(0);
							if(null!=data && data.size()>0)
							{
								Vector<String> idValue = data.getRow("row_0");
								if(null!=idValue && idValue.size()>0)
								{
									userIdFromCaseHistory = idValue.get(0).toString();
									userNameFromCaseHistory = idValue.get(1).toString();
									/*
									 * set in recommendationDetails
									 */
									if(null!=userIdFromCaseHistory && !"".equals(userIdFromCaseHistory))
									{
										transDetails.getRecommendationDetails().setLoginId(userIdFromCaseHistory);
									}
									if(null!=userNameFromCaseHistory && !"".equals(userNameFromCaseHistory))
									{
										transDetails.getRecommendationDetails().setFullName(userNameFromCaseHistory);
									}
								}
								idValue = null;
							}
							data = null;
						}
						dataTable=  null;
					}
				}
				catch(Exception e)
				{
					Utilities.printStackTraceToLogs(ContentRecommendationDetails.class.getName(), "extractRecommdatationDataFromJSON()", e);	
				}
				tDetails = null;
				getAccountDetailsOnKARecordIdPayLoad = null;
				caseHistoryRecord = null;
				
				/*
				 * Check is USER ID FROM CASE HISTORY IS NULL THEN FETCH FROM REQUESTED USER ID
				 */
				if(null==userIdFromCaseHistory || "".equals(userIdFromCaseHistory))
				{
					boolean setDefaultUser=true;
					/*
					 * NEW CHANGE 25 JJUNE 2017
					 * SINCE ENTRY NOT FOUND IN CASE HISTORY TABLE - 
					 * NOW CHECK HERE FOR THE USER INFO ON THE BASIS OF RECORD ID
					 * 	IF FETCHED USER LOGIN ID IS - anonymous (USED FOR REMEDY SYS & TEAM TRACK APP)
					 * 	THEN SET DEFAULT USER
					 * 	ELSE SET THE FETCHED LOGIN ID & USER NAME
					 */
					
					if(null!=userRecordId && !"".equals(userRecordId))
					{
						JSONObject userInfo = null;
						try
						{
							userInfo = caller.getUserInfo(userRecordId);
						}
						catch(Exception e)
						{
							userInfo= null;
							Utilities.printStackTraceToLogs(ContentRecommendationDetails.class.getName(), "extractRecommdatationDataFromJSON()", e);
						}

						if(null!=userInfo && null!=defaultUserObject)
						{
							if(!String.valueOf(userInfo.get("login")).trim().toLowerCase().
									equals(defaultUserObject.getString("defaultUserLoginId").trim().toLowerCase()))
							{
								// USER NOT DEFAULT 
								setDefaultUser=false;
								transDetails.getRecommendationDetails().setLoginId(String.valueOf(userInfo.get("login")));
								transDetails.getRecommendationDetails().setFullName(userName);
							}
						}
						userInfo = null;
					}
					
					if(setDefaultUser==true)
					{	
						// SET DEFAULT USER
						try
						{
							if(null!=defaultUserObject)
							{
								transDetails.getRecommendationDetails().setLoginId(defaultUserObject.getString("defaultUserLoginId"));
								transDetails.getRecommendationDetails().setFullName(defaultUserObject.getString("defaultUserName"));
							}
						}
						catch(Exception e)
						{
							Utilities.printStackTraceToLogs(ContentRecommendationDetails.class.getName(), "extractRecommdatationDataFromJSON()", e);
						}
					}
				}
				
				if(null==transDetails.getRecommendationDetails().getLoginId() || "".equals(transDetails.getRecommendationDetails().getLoginId()))
				{
					ErrorDetails errorDetails = new ErrorDetails();
					errorDetails.setErrorMessage("FAILED TO IDENTIFY ACCOUNT ID FOR THE RECOMMENDATION.");
					if(null==transDetails.getErrorList() || transDetails.getErrorList().size()<=0)
					{
						transDetails.setErrorList(new ArrayList<ErrorDetails>());
					}
					transDetails.getErrorList().add(errorDetails);
					errorDetails = null;
				}
				userIdFromCaseHistory = null;
				userNameFromCaseHistory = null;
//				userName = null;
//				userRecordId= null;
				
				// getStatus
				if(null!=item.get("status"))
				{
					String status = String.valueOf(item.get("status"));
					transDetails.getRecommendationDetails().setStatus(Status.valueOf(status));
					transDetails.getRecommendationDetails().setStatusLabel(status);
					status = null;
				}
				// getDateAdded
				if(null!=item.get("dateAdded"))
				{
					String dateString = String.valueOf(item.get("dateAdded"));
					DateTimeFormatter formatter = DateTimeFormat.forPattern("YYYY'-'MM'-'dd'T'HH:mm:ssZZ");
					DateTime dt = formatter.parseDateTime(dateString);
					
					long val = dt.getMillis();
					/*
					 * SUBTRACT 1 HOUR FROM CONVERTED TIME
					 */
					long oneHourTime = (1*60*60)*1000;
					val = val - oneHourTime;
					dt = new DateTime(val);
					transDetails.getRecommendationDetails().setDateSubmitted(dt);
					/*
					 * CHECK HERE, IF TIME ENDS WITH -0500 - THEN EDT
					 * ELSE IF TIME ENDS WITH -0600 - THEN EST
					 * 
					 * Convert DT to long value and if EDT - ADD 6 HOURS
					 * IF EST ADD 7 HOURS ELSE SET AS IT IS.
					 *
					String currentTimezone="";
					if(dateString.endsWith("-0500"))
					{
						currentTimezone = "EDT";
					}
					else if(dateString.endsWith("-0600"))
					{
						currentTimezone = "EST";
					}
					long val = dt.getMillis();
					if(null!=currentTimezone && !"".equals(currentTimezone))
					{
						if(currentTimezone.trim().toLowerCase().equals("edt"))
						{
							// add 3 hours
							long sixHoursTime=(3*60*60)*1000;
							val = val+sixHoursTime;
						}
						else if(currentTimezone.trim().toLowerCase().equals("est"))
						{	
							// add 4 hours
							long sevenHoursTime=(4*60*60)*1000;
							val = val+sevenHoursTime;
						}
					}
					dt = new DateTime(val);
					transDetails.getRecommendationDetails().setDateSubmitted(dt);
					currentTimezone = null;
					*/
					dt = null;
					dateString= null;
				}

				// setTitle
				if(null!=item.get("title"))
				{
					transDetails.getRecommendationDetails().setTitle(String.valueOf(item.get("title")));
				}
				/*
				 * CHECK IF CONTENT AVAILABLE WITH RECOMMENDATION
				 * IDENTIFY ANSWER ID AND DOCUMENT
				 */
				if (item.has("content"))
				{
					JSONObject contentObj = item.getJSONObject("content");
					if(null!=contentObj)
					{
						if(null!=contentObj.get("answerId"))
						{
							transDetails.getRecommendationDetails().setAnswerId(String.valueOf(contentObj.get("answerId")));
						}
						if(null!=contentObj.get("documentId"))
						{
							transDetails.getRecommendationDetails().setDocId(String.valueOf(contentObj.get("documentId")));
						}
					}
					contentObj = null;
				}
				
				userRecordId = null;
				userName = null;
			}
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(ContentRecommendationDetails.class.getName(), "extractRecommdatationDataFromJSON()", e);
		}
		return transDetails;
	}
	
	/**
	 * Gets a payload used for a create() call to Syed's webservice for custom table OSvC uploading.
	 * 
	 * @return
	 */
	public String getCreatePayload(ContentRecommendationDetails recommendationDetails)
	{
		String payload = "";

		payload +=
				"  <s:Body xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n";
		payload += "    <Create xmlns=\"urn:messages.ws.rightnow.com/v1_3\">\n";
		payload +=
				"      <RNObjects xmlns:q1=\"urn:generic.ws.rightnow.com/v1_3\" xsi:type=\"q1:GenericObject\">\n";
		payload += "        <q1:ObjectType>\n";
		payload += "          <q1:Namespace>KMS</q1:Namespace>\n";
		payload += "          <q1:TypeName>recommendation</q1:TypeName>\n";
		payload += "        </q1:ObjectType>\n";

		if(null!=recommendationDetails.getRecordId() && !"".equals(recommendationDetails.getRecordId()))
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "ka_rec_id", recommendationDetails.getRecordId());
		}
		else
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "ka_rec_id", "NULL");
		}
		
		if(null!=recommendationDetails.getLoginId() && !"".equals(recommendationDetails.getLoginId()))
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "acct_id", recommendationDetails.getLoginId());
		}
		else
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "acct_id", "NULL");
		}
		
		if(null!=recommendationDetails.getFullName() && !"".equals(recommendationDetails.getFullName()))
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "full_name", recommendationDetails.getFullName());
		}
		else
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "full_name", "NULL");
		}
		
		payload += DataType.getPayloadSnippet(DataType.MENU_VALUE, "status", (recommendationDetails.getStatus().ordinal() + 1) + "");
		payload += DataType.getPayloadSnippet(DataType.DATE_TIME_VALUE, "date_submitted",recommendationDetails.getDateSubmitted().toString()+"");
		
		if(null!=recommendationDetails.getAnswerId() && !"".equals(recommendationDetails.getAnswerId()))
		{
			payload += DataType.getPayloadSnippet(DataType.INTEGER_VALUE, "a_id", recommendationDetails.getAnswerId());
		}

		if(null!=recommendationDetails.getDocId() && !"".equals(recommendationDetails.getDocId()))
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "doc_id", recommendationDetails.getDocId());
		}
		else
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "doc_id", "NULL");
		}
		
		if(null!=recommendationDetails.getTitle() && !"".equals(recommendationDetails.getTitle()))
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "title", recommendationDetails.getTitle());
		}
		else
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "title", "NULL");
		}
		

		payload += "      </RNObjects>\n";
		payload += "      <ProcessingOptions>\n";
		payload += "        <SuppressExternalEvents>false</SuppressExternalEvents>\n";
		payload += "        <SuppressRules>false</SuppressRules>\n";
		payload += "      </ProcessingOptions>\n";
		payload += "    </Create>\n";
		payload += "  </s:Body>\n";

		return payload;
	}

	public String getRecommendationPayload(String recordId)
	{
		String payload = "";
		String query="SELECT ID FROM KMS.recommendation WHERE ka_rec_id='"+recordId+"';";
	
//		payload+="<soapenv:Body>";
//		payload+="<ns7:QueryObjects xmlns:ns7=\"urn:messages.ws.rightnow.com/v1_3\">";
//		payload+="<ns7:Query>"+query+"</ns7:Query>";
//		payload+="<ns7:ObjectTemplates xmlns:ns4=\"urn:objects.ws.rightnow.com/v1_3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns4:Contact\">";
//		payload+="<ns4:Notes />";
//		payload+="</ns7:ObjectTemplates>";
//		payload+="<ns7:PageSize>10000</ns7:PageSize>";
//		payload+="</ns7:QueryObjects>";
//		payload+="</soapenv:Body>";
		
		payload+="<soapenv:Body>\n";
		payload+="<ns7:QueryCSV xmlns:ns7=\"urn:messages.ws.rightnow.com/v1_3\">\n";
		payload+="    <ns7:Query>"+query+"</ns7:Query>\n";            
		payload+="    <ns7:PageSize>10000</ns7:PageSize>\n";
		payload+=" </ns7:QueryCSV>\n";
		payload+=" </soapenv:Body>\n";
		
		query=  null;
		return payload;
	}
	
	public String getUpdatePayLoad(ContentRecommendationDetails recommendationDetails)
	{
		String payload = "";

		payload +=
				"  <s:Body xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n";
		payload += "    <Update xmlns=\"urn:messages.ws.rightnow.com/v1_3\">\n";
		payload +=
				"      <RNObjects xmlns:q1=\"urn:generic.ws.rightnow.com/v1_3\" xsi:type=\"q1:GenericObject\">\n";
		payload += "<ID xmlns=\"urn:base.ws.rightnow.com/v1_3\" id=\""+recommendationDetails.getAutoId()+"\" />";
		payload += "        <q1:ObjectType>\n";
		payload += "          <q1:Namespace>KMS</q1:Namespace>\n";
		payload += "          <q1:TypeName>recommendation</q1:TypeName>\n";
		payload += "        </q1:ObjectType>\n";

		if(null!=recommendationDetails.getRecordId() && !"".equals(recommendationDetails.getRecordId()))
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "ka_rec_id", recommendationDetails.getRecordId());
		}
		else
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "ka_rec_id", "NULL");
		}
		
		if(null!=recommendationDetails.getLoginId() && !"".equals(recommendationDetails.getLoginId()))
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "acct_id", recommendationDetails.getLoginId());
		}
		else
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "acct_id", "NULL");
		}
		
		if(null!=recommendationDetails.getFullName() && !"".equals(recommendationDetails.getFullName()))
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "full_name", recommendationDetails.getFullName());
		}
		else
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "full_name", "NULL");
		}
		
		payload += DataType.getPayloadSnippet(DataType.MENU_VALUE, "status", (recommendationDetails.getStatus().ordinal() + 1) + "");
		payload += DataType.getPayloadSnippet(DataType.DATE_TIME_VALUE, "date_submitted",recommendationDetails.getDateSubmitted().toString()+"");
		
		if(null!=recommendationDetails.getAnswerId() && !"".equals(recommendationDetails.getAnswerId()))
		{
			payload += DataType.getPayloadSnippet(DataType.INTEGER_VALUE, "a_id", recommendationDetails.getAnswerId());
		}

		if(null!=recommendationDetails.getDocId() && !"".equals(recommendationDetails.getDocId()))
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "doc_id", recommendationDetails.getDocId());
		}
		else
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "doc_id", "NULL");
		}
		
		if(null!=recommendationDetails.getTitle() && !"".equals(recommendationDetails.getTitle()))
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "title", recommendationDetails.getTitle());
		}
		else
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "title", "NULL");
		}

		payload += "      </RNObjects>\n";
		payload += "      <ProcessingOptions>\n";
		payload += "        <SuppressExternalEvents>false</SuppressExternalEvents>\n";
		payload += "        <SuppressRules>false</SuppressRules>\n";
		payload += "      </ProcessingOptions>\n";
		payload += "    </Update>\n";
		payload += "  </s:Body>\n";

		return payload;
	}

}
