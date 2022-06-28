package com.softclouds.miteldataloader.dataStructures.models.contenthistory;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Vector;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONException;
import org.json.JSONObject;

import com.softclouds.miteldataloader.dataStructures.cruder.DataType;
import com.softclouds.miteldataloader.dataStructures.models.casehistory.CaseHistoryRecord;
import com.softclouds.miteldataloader.dataStructures.models.menuitems.Locale;
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
 * From "Table Designs.xlsx" (content_history sheet) made by Grace Kim
 * 
 * Field Data Type Description
 * 
 * content_type Text KA content type of the document.
 * 
 * locale Menu Locale of the document.
 * 
 * version_num Integer Version number of the KA article. This column assumes that both major and
 * minor versions can appear in the column.
 * 
 * acct_id Menu OSvC staff account id by group.
 * 
 * operation_type Menu The types of operations performed in KA Authoring.
 * 
 * date_of_operation Date time The date and time related to the content operation in the record.
 * This should be specific to the doc id, and not to all the major versions of the doc id.
 * 
 * doc_id Text doc id of KA article, NOT the answer id.
 * 
 * ID Integer Unique record id.
 */

public class ContentHistoryRecord
{
	/**
	 * Constructor creates a new ContentHistoryRecord from a given 'item' JSONObject from the
	 * 'items' array from a content JSON history extraction.
	 * 
	 * @param item
	 * @param caller
	 * @throws JSONException
	 * @throws FileNotFoundException
	 */
	public TransactionDetails extractContentHistoryData(JSONObject item, OSVCandKAWebServiceCaller caller, Cruder cruder, JSONObject defaultUserObject)
	{
		TransactionDetails transDetails = null;
		try
		{
			if(null!=item.get("recordId"))
			{
				// initialize Transaction Details Object
				transDetails= new TransactionDetails();
				// Create new Object of Content History Details
				ContentHistoryDetails details = new ContentHistoryDetails();
				// RecordId
				details.setRecordId(String.valueOf(item.get("recordId")));
				
				/*
				 * PROCESS FOR FETCHING THE USER_ID OR ACCT_ID IS CHANGED - 7 MAY 2017
				 * NOW FETCH THE ACCT_ID FROM CASE_HISTORY TABLE ON THE BASIS OF HISTORY ID.
				 * IF FOUND - THEN SET THE ACCT_ID / FULL NAME OF THE IDENTIFIED VALUE
				 * ELSE - PASS THE VALUE OF userInformationId - FETCH ITS USER NAME AS WELL.
				 * 
				 * 
				 * AGAIN USER MAPPING PROCESS CHANGE - 17 MAY 2017
				 * SEARCH FOR ACCT_ID & FULL_NAME ON THE BASIS OF KA_REC_ID IN CASE_HISTORY
				 * 	IF FOUND - MAP IT
				 * 	IF NOT FO
				 * 
				 * 
				 */
				String userIdFromCaseHistory="";
				String userNameFromCaseHistory="";
				CaseHistoryRecord caseHistoryRecord = new CaseHistoryRecord();
				String getAccountDetailsOnKARecordIdPayLoad = caseHistoryRecord.getAccountDetailsOnKARecordId(details.getRecordId());
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
										details.setLoginId(userIdFromCaseHistory);
									}
									if(null!=userNameFromCaseHistory && !"".equals(userNameFromCaseHistory))
									{
										details.setFullName(userNameFromCaseHistory);
										details.setUserDisplayName(userNameFromCaseHistory);
									}
								}
								idValue = null;
							}
							data = null;
						}
						dataTable=  null;
					}
				}
				catch(Exception e){
					Utilities.printStackTraceToLogs(ContentHistoryDetails.class.getName(), "extractContentHistoryData()", e);
				}
				tDetails = null;
				getAccountDetailsOnKARecordIdPayLoad = null;
				caseHistoryRecord = null;
				
				/*
				 * Check is USER ID FROM CASE HISTORY IS NULL THEN FETCH FROM REQUESTED USER ID
				 */
				if(null==userIdFromCaseHistory || "".equals(userIdFromCaseHistory))
				{
					/*
					 * USER IDENTIFICATION PROCESS CHANGED - 25 JUNE 2017
					 * IF ENTRY NOT FOUND IN CASE HISTORY TABLE 
					 * CEHCK FOR THE USER ID ASSOCIATED WITH HISTORY NODE
					 * 	IF ANONYMOUS E.G. (USER USED FOR REMEDYSYS & TEAM TRACK APPLICATION)
					 * 	THEN DEFAULKT USER
					 * ELSE 
					 * 	IDENTIFY USER DETAILS FROM KA AND SET IN USER DETAILS
					 */
					boolean setDefaultUser = true;
					String userNameForWhichInformationIsTobeFetched="";
					if(null!=item.get("userName"))
					{
						userNameForWhichInformationIsTobeFetched = String.valueOf(item.get("userName"));
					}
					// getUserInfo
					if(null!=item.get("userInformationId"))
					{
						String userRecordId = String.valueOf(item.get("userInformationId"));
						JSONObject userInfoObject = null;
						try
						{
							userInfoObject = caller.getUserInfo(userRecordId);
						}
						catch(Exception e)
						{
							Utilities.printStackTraceToLogs(ContentHistoryDetails.class.getName(), "extractContentHistoryData()", e);
						}

						if(null!=userInfoObject)
						{
							if(null!=userInfoObject.get("login") && null!=defaultUserObject)
							{
								if(!String.valueOf(userInfoObject.get("login")).trim().toLowerCase().
										equals(defaultUserObject.getString("defaultUserLoginId").trim().toLowerCase()))
								{
									// USER NOT DEFAULT.
									setDefaultUser = false;
									details.setLoginId(String.valueOf(userInfoObject.get("login")));
									if(null!=userNameForWhichInformationIsTobeFetched && !"".equals(userNameForWhichInformationIsTobeFetched))
									{
										details.setUserDisplayName(userNameForWhichInformationIsTobeFetched);
										details.setFullName(userNameForWhichInformationIsTobeFetched);
									}
									else
									{
										if(null!=userInfoObject.get("name"))
										{
											details.setUserDisplayName(String.valueOf(userInfoObject.get("name")));
											details.setFullName(String.valueOf(userInfoObject.get("name")));
										}
									}
								}
							}
						}
						userInfoObject =null;
						userRecordId = null;
					}
					
					if(setDefaultUser==true)
					{
						// SET DEFAULT USER 
						try
						{
							if(null!=defaultUserObject)
							{
								details.setLoginId(defaultUserObject.getString("defaultUserLoginId"));
								details.setFullName(defaultUserObject.getString("defaultUserName"));
								details.setUserDisplayName(defaultUserObject.getString("defaultUserName"));
							}
						}
						catch(Exception e)
						{
							Utilities.printStackTraceToLogs(ContentHistoryRecord.class.getName(), "extractContentHistoryData()", e);
						}
					}
					
					userNameForWhichInformationIsTobeFetched = null;
				}

				if(null==details.getLoginId() || "".equals(details.getLoginId()))
				{
					// TRACK ERROR DETAILS - FAILED TO IDENTIFY ACCOUNT ID OF USER FOR RECORD ID
					ErrorDetails errorDetails = new ErrorDetails();
					errorDetails.setRemarks("FAILED TO IDENTIFY ACCOUNT ID OF USER .");
					errorDetails.setErrorMessage("FAILED TO IDENTIFY ACCOUNT ID OF USER FOR RECORD ID "+ details.getRecordId());
					if(null==transDetails.getErrorList() || transDetails.getErrorList().size()<=0)
					{
						transDetails.setErrorList(new ArrayList<ErrorDetails>());
					}
					transDetails.getErrorList().add(errorDetails);
					errorDetails=  null;
				}

				// set OperationType
				if(null!=item.get("action"))
				{
					details.setOperationType(String.valueOf(item.get("action")));
				}

				// set Date of Operation
				if(null!=item.get("dateModified"))
				{
					String dateOfOperationString = String.valueOf(item.get("dateModified"));
					DateTimeFormatter formatter = DateTimeFormat.forPattern("YYYY'-'MM'-'dd'T'HH:mm:ssZZ");
					DateTime dt = formatter.parseDateTime(dateOfOperationString);
					
					long val = dt.getMillis();
					/*
					 * SUBTRACT 1 HOUR FROM CONVERTED TIME
					 */
					long oneHourTime = (1*60*60)*1000;
					val = val - oneHourTime;
					dt = new DateTime(val);
					details.setDateOfOperation(dt);
					
					/*
					 * Convert DT to long value and if EDT - ADD 6 HOURS
					 * IF EST ADD 7 HOURS ELSE SET AS IT IS.
					 *
					String currentTimezone = "";
					if(dateOfOperationString.endsWith("-0500"))
					{
						currentTimezone  = "EDT"; 
					}
					else if(dateOfOperationString.endsWith("-0600"))
					{
						currentTimezone  = "EST";
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
					details.setDateOfOperation(dt);
					currentTimezone= null;
					*/
					dt = null;
					formatter = null;
					dateOfOperationString= null;
				}
				
				// Now Iterate Content Object
				if(null!=item.get("content"))
				{
					JSONObject contentObject= (JSONObject)item.get("content");
					
					String contentRecordId="";
					if(null!=contentObject.get("recordId"))
					{
						contentRecordId = String.valueOf(contentObject.get("recordId"));
					}
					
					// set Document Id
					if(null!=contentObject.get("documentId"))
					{
						details.setDocumentId((String)contentObject.get("documentId"));
					}
					// set answerId
					if(null!=contentObject.get("answerId"))
					{
						details.setAnswerId(String.valueOf(contentObject.get("answerId")));
					}
					
					if(null!=contentRecordId && !"".equals(contentRecordId))
					{
						// getContentType
						JSONObject wsContentTypeObject =null;
						try
						{
							wsContentTypeObject = caller.callKAWebservice("/content/" + contentRecordId+"?contentState=LATESTVALID");
						}
						catch(Exception e)
						{
							Utilities.printStackTraceToLogs(ContentHistoryRecord.class.getName(), "extractContentHistoryData()", e);
						}

						if(null!=wsContentTypeObject)
						{
							try
							{
								JSONObject contentTypeObject =(JSONObject) wsContentTypeObject.get("contentType");
								if(null!=contentTypeObject)
								{
									if(null!=contentTypeObject.get("referenceKey"))
									{
										details.setContentType(String.valueOf(contentTypeObject.get("referenceKey")));
									}
								}
								contentTypeObject = null;
							}
							catch(Exception e){}
						}
						wsContentTypeObject = null;
					}
					contentRecordId = null;
					
					if(null==details.getContentType() || "".equals(details.getContentType()))
					{
						// TRACK ERROR DETAILS - FAILED TO IDENTIFY CONTENT TYPE FOR RECORD ID
						ErrorDetails errorDetails = new ErrorDetails();
						errorDetails.setRemarks("FAILED TO IDENTIFY CONTENT TYPE.");
						errorDetails.setErrorMessage("FAILED TO IDENTIFY CONTENT TYPE FOR RECORD ID "+ details.getRecordId());
						if(null==transDetails.getErrorList() || transDetails.getErrorList().size()<=0)
						{
							transDetails.setErrorList(new ArrayList<ErrorDetails>());
						}
						transDetails.getErrorList().add(errorDetails);
						errorDetails=  null;
					}
					
					// getLocale
					if(null!=contentObject.get("locale"))
					{
						JSONObject localeObject = (JSONObject)contentObject.get("locale");
						if(null!=localeObject)
						{
							if(null!=localeObject.get("recordId"))
							{
								String locale= String.valueOf(localeObject.get("recordId"));
								details.setLocaleLabel(locale);
								details.setLocale(Locale.valueOf(locale.toUpperCase()));
								locale = null;
							}
						}
						localeObject=  null;
					}

					if(null==details.getLocale() || "".equals(details.getLocale()))
					{
						// TRACK ERROR DETAILS - FAILED TO IDENTIFY LOCALE FOR RECORD ID
						ErrorDetails errorDetails = new ErrorDetails();
						errorDetails.setRemarks("FAILED TO IDENTIFY LOCALE.");
						errorDetails.setErrorMessage("FAILED TO IDENTIFY LOCALE FOR RECORD ID "+ details.getRecordId());
						if(null==transDetails.getErrorList() || transDetails.getErrorList().size()<=0)
						{
							transDetails.setErrorList(new ArrayList<ErrorDetails>());
						}
						transDetails.getErrorList().add(errorDetails);
						errorDetails=  null;
					}
					contentObject = null;
				}
				
				transDetails.setContentHistoryDetails(new ContentHistoryDetails());
				// set details in tansactionDetails
				transDetails.setContentHistoryDetails(details);
				details=  null;
			}
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(ContentHistoryRecord.class.getName(), "extractContentHistoryData()", e);
		}
		return transDetails;
	}

	/**
	 * Gets a payload used for a create() call to Syed's webservice for custom table OSvC uploading.
	 * 
	 * @return
	 */
	public String getCreatePayload(ContentHistoryDetails details)
	{
		String payload = "";

		payload +=
				"<s:Body xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n";
		payload += "  <Create xmlns=\"urn:messages.ws.rightnow.com/v1_3\">\n";
		payload +=
				"    <RNObjects xmlns:q1=\"urn:generic.ws.rightnow.com/v1_3\" xsi:type=\"q1:GenericObject\">\n";
		payload += "      <q1:ObjectType>\n";
		payload += "        <q1:Namespace>KMS</q1:Namespace>\n";
		payload += "        <q1:TypeName>content_history</q1:TypeName>\n";
		payload += "      </q1:ObjectType>\n";
		
		// ADD Content Type
		if(null!=details.getContentType() && !"".equals(details.getContentType()))
		{
			payload +=	DataType.getPayloadSnippet(DataType.TEXT_VALUE, "content_type", details.getContentType());
		}
		else
		{
			payload +=	DataType.getPayloadSnippet(DataType.TEXT_VALUE, "content_type", "NULL");
		}
		
		if(null!=details.getLocaleLabel() && !"".equals(details.getLocaleLabel()))
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "article_locale",details.getLocaleLabel());
		}
		else
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "article_locale","NULL");
		}
		// Add Locale
//		payload += DataType.getPayloadSnippet(DataType.MENU_VALUE, "locale",(details.getLocale().ordinal() + 1) + "");

		// payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "version_num",
		// StringUtility.round(this.version, 1).replace('.', ' ') + "");

		// payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "version_num", "X");
		if(null!=details.getLoginId() && !"".equals(details.getLoginId()))
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "acct_id",details.getLoginId());
		}
		else
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "acct_id","NULL");
		}
		
		if(null!=details.getFullName() && !"".equals(details.getFullName()))
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "full_name",details.getFullName());
		}
		else
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "full_name","NULL");
		}
		
		if(null!=details.getOperationType() && !"".equals(details.getOperationType()))
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "operation_type", details.getOperationType());
		}
		else
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "operation_type", "NULL");
		}
		
		payload += DataType.getPayloadSnippet(DataType.DATE_TIME_VALUE, "date_of_operation",details.getDateOfOperation() + "");
		if(null!=details.getDocumentId() && !"".equals(details.getDocumentId()))
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "doc_id", details.getDocumentId());
		}
		else
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "doc_id","NULL");
		}
		
		if(null!=details.getRecordId() && !"".equals(details.getRecordId()))
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "ka_rec_id", details.getRecordId());
		}
		else
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "ka_rec_id", "NULL");
		}
		
		if(null!=details.getAnswerId() && !"".equals(details.getAnswerId()))
		{
			payload += DataType.getPayloadSnippet(DataType.INTEGER_VALUE, "a_id", details.getAnswerId());
		}
		

		payload += "    </RNObjects>\n";
		payload += "    <ProcessingOptions>\n";
		payload += "      <SuppressExternalEvents>false</SuppressExternalEvents>\n";
		payload += "      <SuppressRules>false</SuppressRules>\n";
		payload += "    </ProcessingOptions>\n";
		payload += "  </Create>\n";
		payload += "</s:Body>\n";

		return payload;
	}
	
	public String getContentHistoryPayload(String recordId)
	{
		String payload = "";
		String query="SELECT ID FROM KMS.content_history WHERE ka_rec_id='"+recordId+"';";
	
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
	
	public String getUpdatePayLoad(ContentHistoryDetails details)
	{
		String payload = "";

		payload +=
				"  <s:Body xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n";
		payload += "    <Update xmlns=\"urn:messages.ws.rightnow.com/v1_3\">\n";
		payload +=
				"      <RNObjects xmlns:q1=\"urn:generic.ws.rightnow.com/v1_3\" xsi:type=\"q1:GenericObject\">\n";
		payload += "<ID xmlns=\"urn:base.ws.rightnow.com/v1_3\" id=\""+details.getAutoId()+"\" />";
		payload += "        <q1:ObjectType>\n";
		payload += "          <q1:Namespace>KMS</q1:Namespace>\n";
		payload += "          <q1:TypeName>content_history</q1:TypeName>\n";
		payload += "        </q1:ObjectType>\n";

		// ADD Content Type
		if(null!=details.getContentType() && !"".equals(details.getContentType()))
		{
			payload +=	DataType.getPayloadSnippet(DataType.TEXT_VALUE, "content_type", details.getContentType());
		}
		else
		{
			payload +=	DataType.getPayloadSnippet(DataType.TEXT_VALUE, "content_type", "NULL");
		}

		// Add Locale
		if(null!=details.getLocaleLabel() && !"".equals(details.getLocaleLabel()))
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "article_locale",details.getLocaleLabel());
		}
		else
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "article_locale","NULL");
		}
//		payload += DataType.getPayloadSnippet(DataType.MENU_VALUE, "locale",(details.getLocale().ordinal() + 1) + "");

		// payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "version_num",
		// StringUtility.round(this.version, 1).replace('.', ' ') + "");

		// payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "version_num", "X");
		if(null!=details.getLoginId() && !"".equals(details.getLoginId()))
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "acct_id",details.getLoginId());
		}
		else
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "acct_id","NULL");
		}
		
		if(null!=details.getFullName() && !"".equals(details.getFullName()))
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "full_name",details.getFullName());
		}
		else
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "full_name","NULL");
		}

		if(null!=details.getOperationType() && !"".equals(details.getOperationType()))
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "operation_type", details.getOperationType());
		}
		else
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "operation_type", "NULL");
		}

		payload += DataType.getPayloadSnippet(DataType.DATE_TIME_VALUE, "date_of_operation",details.getDateOfOperation() + "");
		if(null!=details.getDocumentId() && !"".equals(details.getDocumentId()))
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "doc_id", details.getDocumentId());
		}
		else
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "doc_id","NULL");
		}

		if(null!=details.getRecordId() && !"".equals(details.getRecordId()))
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "ka_rec_id", details.getRecordId());
		}
		else
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "ka_rec_id", "NULL");
		}

		if(null!=details.getAnswerId() && !"".equals(details.getAnswerId()))
		{
			payload += DataType.getPayloadSnippet(DataType.INTEGER_VALUE, "a_id", details.getAnswerId());
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
