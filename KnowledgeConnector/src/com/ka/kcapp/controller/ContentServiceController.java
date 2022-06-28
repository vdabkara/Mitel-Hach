package com.ka.kcapp.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ka.kcapp.menuitems.CrmId;
import com.ka.kcapp.menuitems.TransactionType;
import com.ka.kcapp.osvc.services.CaseHistoryRecord;
import com.ka.kcapp.osvc.services.Cruder;
import com.ka.kcapp.osvc.services.KAWebServiceCaller;
import com.ka.kcapp.osvc.services.UserInfoRecord;
import com.ka.kcapp.util.Utilities;
import com.ka.kcapp.vo.CaseHistoryDetails;
import com.ka.kcapp.widget.common.Content;
import com.ka.kcapp.widget.common.TemplateHandler;

public class ContentServiceController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ResourceBundle	resourceBundle;
	private ResourceBundle	labelsBundle;
	public ContentServiceController() {
		super();
	}

	private final static Logger logger = Logger.getLogger(ContentServiceController.class);

	/*
	 * 
	 * Post method to handle the contribute content request (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException {
		logger.debug("ContentServiceController - Processing the post request");
		StringBuilder action_response = new StringBuilder();
		resourceBundle = ResourceBundle.getBundle("resources.application");
		labelsBundle = ResourceBundle.getBundle("resources.LabelsBundle", new Locale("en"));
		List<FileItem> items = null;
		List<File> uploadFiles = null;
		try {
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			logger.info("ContentServiceController - is this multipart request " + isMultipart);
			if (isMultipart) {
				logger.debug("getting the file items");
				items = Content.getPostFileItemsFromRequest(request);
				logger.debug("ContentServiceController - getting content xml  ");
				String body_xml = Content.createContentXMLWithFiles(items, true, request, response);
				logger.debug("ContentServiceController - content xml  \n" + body_xml);
				uploadFiles = Content.createFileList(items);
				JSONObject json_response = Content.createContentWithFiles(request, response, body_xml, uploadFiles);
				
				String documentId="";
				String answerId="";
				boolean success = false;
				try
				{
					if(null!=json_response)
					{
						if(null!=json_response.get("documentId"))
						{
							documentId = String.valueOf(json_response.get("documentId"));
						}
						if(null!=json_response.get("answerId"))
						{
							answerId = String.valueOf(json_response.get("answerId"));
						}
						if(null!=json_response.get("success"))
						{
							success = json_response.getBoolean("success");
						}
					}
				}
				catch(Exception e)
				{/* do nothing for this exception  */}
				if(success==true)
				{
					/*
					 * CHECK HERE, IF DOCUMENT ID IS NOT NULL
					 * FETCH THE HISTORY RECORD ID FOR THE DOCUMENT AND SET AS KA_REC_ID
					 */
					String historyRecordId="";
					if(null!=documentId && !"".equals(documentId))
					{
						KAWebServiceCaller caller = new KAWebServiceCaller();
						try
						{
							JSONObject history = null;
							try
							{
								history =caller.callKAWebservice("/content/docId/" + documentId + "/history");
							}
							catch(Exception e)
							{
								Utilities.printStackTraceToLogs(ContentServiceController.class.getName(), "doPost()", e);
							}
							if(null!=history)
							{
								/*
								 * EXTRACT ALL THE HISTORY DATA FOR THE DOCUMENT
								 */
								JSONArray historyItems = history.getJSONArray("items");
								if(null!=historyItems && historyItems.length()>0)
								{
									JSONObject itemObject = (JSONObject)historyItems.get(0);
									if(null!=itemObject.get("recordId"))
									{
										// IDENTIFY HISTORY RECORD ID
										historyRecordId = String.valueOf(itemObject.get("recordId"));
									}
									itemObject = null;
								}
								historyItems = null;
							}
							history = null;
							caller = null;
						}
						catch(Exception e)
						{
							Utilities.printStackTraceToLogs(ContentServiceController.class.getName(), "doPost()", e);
						}
					}
					
					/*
					 * PROCEED FOR SAVING CASE HISTORY RECORD
					 */
					CaseHistoryDetails details = new CaseHistoryDetails();
					String sourceLabel="";
					String userId="";
					if(null!=request.getSession().getAttribute("source"))
					{
						sourceLabel= String.valueOf(request.getSession().getAttribute("source"));
						details.setCRM_ID(CrmId.valueOf(String.valueOf(request.getSession().getAttribute("source"))));
					}
					if(null!=request.getSession().getAttribute("userID"))
					{
						userId = String.valueOf(request.getSession().getAttribute("userID"));
					}
					if(null!=request.getSession().getAttribute("caseID"))
					{
						details.setCaseNumber(String.valueOf(request.getSession().getAttribute("caseID")));
					}
					
					/*
					 * call function to check whether user exists in user_info table or not.
					 */
					Cruder cruder = new Cruder();
					String accoundId="";
					String fullName = "";
					String userPayloadBody = "";
					UserInfoRecord userInfoRecrod = new UserInfoRecord();
					try
					{
						userPayloadBody = userInfoRecrod.getUserPayload(userId, sourceLabel);
						JSONObject userTrackObject = cruder.fetchUserAccountId(userPayloadBody);
						if(null!=userTrackObject)
						{
							if(null!=userTrackObject.get("accountId"))
							{
								accoundId  =String.valueOf(userTrackObject.get("accountId"));
							}
							if(null!=userTrackObject.get("fullName"))
	    					{
	    						fullName  =String.valueOf(userTrackObject.get("fullName"));
	    					}
						}
					}
					catch(Exception e)
					{/* Do Nothing for this exception.*/}
					userPayloadBody= null;
					userInfoRecrod=  null;
					
					details.setKaRecordId(historyRecordId);
					details.setAnswerId(answerId);
					details.setDocumentId(documentId);
					details.setTRANSACTION_TYPE(TransactionType.AUTHOR_A_NEW_CONTENT);
					if(null!=accoundId && !"".equals(accoundId))
					{
						/*
						 *  USER LOCATED IN USER_INFO TABLE.
						 *  PUT USER ID AND KA RECID SAME AS RECEIVED IN PARAM
						 */
						details.setUserId(accoundId);
						details.setFullName(fullName);
						details.setCrmUserId(userId);
					}
					else
					{
						/*
						 * USER NOT LOCATED IN USER_INFO TABLE.
						 * PUT USER ID AS ANONYMOUS AND KA RECORD ID AS VALUE RECEIVED FROM PARAM
						 */
						details.setUserId(resourceBundle.getString("teamtrackDefaultUser"));
						details.setFullName(resourceBundle.getString("teamtrackDefaultUserName"));
						details.setCrmUserId(userId);
					}
					accoundId=  null;
					fullName= null;
					/*
					 * Create BODY PAYLOAD
					 */
					CaseHistoryRecord caseHisRec = new CaseHistoryRecord();
					String payloadBody = "";
					/*
					 * PROCEED FOR CASE HISTORY CREATION
					 */
					JSONObject cruderResponse  = null;
					try
					{
						payloadBody = caseHisRec.getCreatePayload(details);
						cruderResponse =  cruder.create(payloadBody);
					}
					catch(Exception e)
					{
						Utilities.printStackTraceToLogs(ContentServiceController.class.getName(), "doPost()", e);
					}
					payloadBody = null;
					caseHisRec = null;
					details = null;
					cruder = null;
					
					boolean cruderSuccess = false;
					try
					{
						if(null!=cruderResponse)
						{
							if(null!=cruderResponse.get("success"))
							{
								if(cruderResponse.getBoolean("success")==true)
								{
									logger.info("doPost() :: Case History Record for Author a New Article Created Succesfully.");
									cruderSuccess = true;
								}
							}
						}
					}
					catch(Exception e){/* DO NOTHING WITH THIS EXCEPTION.*/
						logger.info("doPost() :: Failed to Create Case History Record. In Response from OSVC Web Service SUCCESS Node not Found.");
					}
					if(cruderSuccess ==false)
					{
						logger.info("doPost() :: Failed to Create Case History Record.");
						try
						{
							if(null!=json_response.get("message"))
							{
								String message = String.valueOf(json_response.get("message"));
								message=  message+" "+labelsBundle.getString("ContributeContentTag.failedCaseHistoryMessage");
								json_response.put("message", message);
								message = null;
							}
						}
						catch(Exception e){/* DO NOTHING WITH THIS EXCEPTION.*/}
					}
					historyRecordId = null;
				}
				Map<String, Object> input = new HashMap<String, Object>();
				input.put("response", json_response.toString());
				action_response.append(
						TemplateHandler.getTemplateHtml(input, "cc_CreateResponse.ftl", this.getServletContext()));
			}
			logger.debug("ContentServiceController - Processed the post request");
		} catch (Exception ex) {
			logger.error("ContentServiceController error ", ex);
		} finally {
			Content.cleanUploadedItems(items, uploadFiles);
		}
		response.getWriter().write(action_response.toString());

	}

}
