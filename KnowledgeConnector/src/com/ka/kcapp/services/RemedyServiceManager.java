
package com.ka.kcapp.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.ka.kcapp.menuitems.CrmId;
import com.ka.kcapp.menuitems.TransactionType;
import com.ka.kcapp.osvc.services.CaseHistoryRecord;
import com.ka.kcapp.osvc.services.Cruder;
import com.ka.kcapp.osvc.services.UserInfoRecord;
import com.ka.kcapp.util.Utilities;
import com.ka.kcapp.vo.CaseHistoryDetails;

/**
 * Remedy - Service Manager class for incident link/unlink functionality. This
 * class utilizes the Remedy end point and invokes the corresponding methods to
 * link or unlink an answer from the incident
 * 
 * @author SMarimuthu
 */
public class RemedyServiceManager {
	// logger
	private final static Logger logger = Logger.getLogger(RemedyServiceManager.class);
	private static ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.application");
	private static ResourceBundle	labelsBundle = ResourceBundle.getBundle("resources.LabelsBundle", new Locale("en"));
	private static HttpClient httpClient;
        private static String ANSWERID_URL="";

	/**
	 * initialize the client
	 */
	static {
            httpClient = HttpClientBuilder.create().build();
	    ANSWERID_URL=resourceBundle.getString("ANSWERID_URL");
	}

	/**
	 * 
	 * This method calls the linking API passing in the selected answer data (
	 * Title, URL). Based on the API response,an appropriate status is passed
	 * back to the calling code.
	 * 
	 * @param postData
	 *            - Selected Answer data in JSON format
	 * @return json_response - response string based on the service call output
	 */

	public static String addIncidentLink(JSONObject postData, Object userObj) {
		String json_response = null;
		try {
			logger.info("addIncidentLink method called for incident id " + postData.getString("incidentId"));
			String postParameters =
                                  "solution_url="+ ANSWERID_URL + postData.getString("answerId")
                                + "&incidentID=" + postData.getString("incidentId")
                                + "&incident_number=" + postData.getString("incidentId") 
                                + "&solution_title=" + postData.getString("title").trim() 
                                + "&answer_id=" + postData.getString("im_doc_id") ;
			logger.info("Post parameters " + postParameters);
			String response = httpPost(resourceBundle.getString("REMEDY_SERVICE_LINK_ENDPOINT"), postParameters);
			logger.info("Remedy Service response : " + response);
			if (response != null && !response.isEmpty() && response.contains("successfully")) {
				/*
				 * CREATE A CASE HISTORY RECORD HERE
				 */
				boolean status = createCaseHsitoryRecrod("LINK", userObj, postData);
				if(status==false)
				{
					String errorMesage = labelsBundle.getString("LINKING.failedCaseHistoryMessage");
					json_response = "{\"message\":\" Document Linked. "+errorMesage+" \"}";
					errorMesage = null;
				}
				else
				{
					json_response = "{\"message\":\" Document Linked. \"}";
				}
			} else
				// json_response = "{\"error\":\"" + response.toString() + "
				// \"}";
				json_response = "{\"message\":\"" + response.toString() + "\",\"error\":\"true\"}";
		} catch (JSONException e) {
			logger.error("JSONException: Error while parsing the post data " + e.fillInStackTrace());
			// json_response = "{\"error\":\" Error Occurred during link contact
			// Admin with Error Code LinkItem-6220 \"}";
			json_response = "{\"message\":\"" + e.getMessage().trim() + "\",\"error\":\"true\"}";
		}
		logger.info("Returning, service response " + json_response);
		return json_response;
	}

	/**
	 * 
	 * This method calls the unlinking API passing in the answer id. Based on
	 * the API response,an appropriate status is passed back to the calling
	 * code.
	 * 
	 * @param postData
	 *            - Selected Answer data in JSON format
	 * @return json_response - response string based on the service call output
	 */
	public static String removeIncidentLink(JSONObject postData, Object userObj) {
		String json_response = null;
		try {
			logger.info("removeIncidentLink method called for incident id " + postData.getString("incidentId"));
			String postParameters = 
                            "incident_number=" + postData.getString("incidentId") 
//                            + "&answer_id=" + postData.getString("answerId");
        		    + "&answer_id=" + postData.getString("documentid");//+"/"+postData.getString("answerId");
			logger.info("Post parameters " + postParameters);
			String response = httpPost(resourceBundle.getString("REMEDY_SERVICE_UNLINK_ENDPOINT"), postParameters);
			logger.info("Remedy Service response : " + response);
			if (response != null & !response.isEmpty() && response.contains("successfully")) {
				
				/*
				 * CREATE A CASE HISTORY RECORD HERE
				 */
				boolean status = createCaseHsitoryRecrod("UNLINK", userObj, postData);
				if(status==false)
				{
					String errorMesage = labelsBundle.getString("LINKING.failedCaseHistoryMessage");
					json_response = "{\"success\":\"true\", \"message\":\"Document Unlinked. "+errorMesage
					+ "\"}";
					errorMesage = null;
				}
				else
				{
					json_response = "{\"success\":\"true\", \"message\":\"Document Unlinked. \"}";
				}
				
			} else
				// json_response = "{\"error\":\"true\", \"message\":\"(" +
				// response.toString() + " )\"}";
				json_response = "{\"message\":\"" + response.toString() + "\",\"error\":\"true\"}";

		} catch (JSONException e) {
			logger.error("JSONException: Error while parsing the post data " + e.fillInStackTrace());
			// json_response = "{\"error\":\"true\", \"message\":\" Error
			// Occurred during unlink contact Admin with Error Code
			// LinkItem-6500 \"}";
			json_response = "{\"message\":\"" + e.getMessage().trim() + "\",\"error\":\"true\"}";
		}
		logger.info("Returning, service response " + json_response);
		return json_response;

	}

	/**
	 * 
	 * HttpPost method to invoke the remedy Service url methods
	 * 
	 * @param postUrl
	 *            - post url for the operation(link/unlink)
	 * @param postParameters
	 *            - url paramters for the operation
	 */
	private static String httpPost(String postUrl, String postParameters) {
		logger.info("Connecting to url  " + postUrl);
		StringBuilder responseString = new StringBuilder();

		try {
			HttpPost httpPost = new HttpPost(postUrl);
			httpPost.addHeader("Accept-Charset", "UTF-8");
			httpPost.addHeader("Connection", "keep-alive");
			httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
			httpPost.setEntity(new StringEntity(postParameters));
			HttpResponse response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			logger.info("Response code " + statusCode);

			BufferedReader br = new BufferedReader(
					new InputStreamReader(response.getEntity().getContent(), Charset.forName("UTF-8")));
			String line;
			while ((line = br.readLine()) != null) {
				responseString.append(line);
			}
			br.close();
			logger.info("Response String " + responseString.toString());
		} catch (ConnectTimeoutException e) {
			logger.error("ConnectTimeoutException: Error while connecting to the service url " + e.fillInStackTrace());
		} catch (UnsupportedEncodingException e) {
			logger.error("UnsupportedEncodingException: Error while setting the post entities " + e.fillInStackTrace());
		} catch (ClientProtocolException e) {
			logger.error("ClientProtocolException: Error while executing the post call " + e.fillInStackTrace());
		} catch (IOException e) {
			logger.error("IOException: Error while executing the closing the response readers " + e.fillInStackTrace());
		}
		return responseString.toString();
	}

	/**
	 * Function will perform Create Case History Operation.
	 * @param caseType
	 * @param userObj
	 * @param postData
	 * @return
	 */
	private static boolean createCaseHsitoryRecrod(String caseType, Object userObj, JSONObject postData)
	{
		boolean status = false;
		try
		{
			String documentId="";
			String answerId="";
			String caseId = "";
			
			try
			{
				if(null!=postData)
				{
					if(null!=postData.get("incidentId"))
					{
						caseId = String.valueOf(postData.get("incidentId"));
					}
					if(null!=postData.get("im_doc_id"))
					{
						documentId = String.valueOf(postData.get("im_doc_id"));
					}
					if(null!=postData.get("answerId"))
					{
						answerId = String.valueOf(postData.get("answerId"));
					}
				}
			}
			catch(Exception e)
			{/* Do Nothing for this Exception */}
			/*
			 * PROCEED FOR SAVING CASE HISTORY RECORD
			 */
			CaseHistoryDetails details = new CaseHistoryDetails();
			details.setCRM_ID(CrmId.REMEDY);
			String sourceLabel="REMEDY";
			String userId="";
			if(null!=userObj)
			{
				userId = String.valueOf(userObj);
			}
			
			/*
			 * call function to check whether user exists in user_info table or not.
			 */
			Cruder cruder = new Cruder();
			String accoundId="";
			String fullName="";
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
			details.setCaseNumber(caseId);
			details.setAnswerId(answerId);
			details.setDocumentId(documentId);
			if(caseType.equals("LINK"))
			{
				details.setTRANSACTION_TYPE(TransactionType.LINK);
			}
			else if(caseType.equals("UNLINK"))
			{
				details.setTRANSACTION_TYPE(TransactionType.UNLINK);
			}
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
				Utilities.printStackTraceToLogs(RemedyServiceManager.class.getName(), "createCaseHsitoryRecrod()", e);
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
							logger.info("createCaseHsitoryRecrod() :: Case History Record for {"+caseType+"} Document Created Succesfully.");
							status = true;
							cruderSuccess = true;
						}
					}
				}
			}
			catch(Exception e){/* DO NOTHING WITH THIS EXCEPTION.*/
				logger.info("createCaseHsitoryRecrod() :: Failed to Create Case History Record. In Response from OSVC Web Service SUCCESS Node not Found.");
			}
			if(cruderSuccess ==false)
			{
				logger.info("createCaseHsitoryRecrod() :: Failed to Create Case History Record.");
			}
			
			caseId = null;
			documentId = null;
			answerId=  null;
			accoundId=  null;
			fullName=  null;
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(RemedyServiceManager.class.getName(), "createCaseHsitoryRecrod()", e);
		}
		return status;
	}
	
} // end class
