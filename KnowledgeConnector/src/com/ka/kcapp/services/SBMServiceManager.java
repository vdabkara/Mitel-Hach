/**
 * 
 */
package com.ka.kcapp.services;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axis2.AxisFault;
import org.apache.axis2.databinding.ADBException;
import org.apache.commons.lang3.StringUtils;
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
import com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault;
import com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72;
import com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72Stub;
import com.mitel.ka.kcapp.services.sbm.sbmappservices72.AttachmentAccessType;
import com.mitel.ka.kcapp.services.sbm.sbmappservices72.Auth;
import com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteAttachment;
import com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteAttachmentResponse;
import com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItem;
import com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItemResponse;
import com.mitel.ka.kcapp.services.sbm.sbmappservices72.ItemIdentifier;
import com.mitel.ka.kcapp.services.sbm.sbmappservices72.Logout;
import com.mitel.ka.kcapp.services.sbm.sbmappservices72.TTItem;
import com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionIdentifier;
import com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionItem;
import com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionItemResponse;
import com.mitel.ka.kcapp.services.sbm.sbmappservices72.URLAttachment;

/**
 * SBM - Service Manager class for incident link/unlink functionality. This
 * class utilizes the SOAP end point and invokes the corresponding methods to
 * link or unlink an answer from the incident
 * 
 * @author SMarimuthu
 */
public class SBMServiceManager {
	// logger
	private final static Logger logger = Logger.getLogger(SBMServiceManager.class);
	private static ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.application");
	private static ResourceBundle	labelsBundle = ResourceBundle.getBundle("resources.LabelsBundle", new Locale("en"));
	private static Sbmappservices72 serviceStub;
        private static String SBM_SERVICE_TRAN_INT_NAME = "";
        private static String ANSWERID_URL="";
	/**
	 * initialize the stub
	 */
	static {
		try {
			serviceStub = new Sbmappservices72Stub(resourceBundle.getString("SBM_SERVICE_ENDPOINT"));
		    SBM_SERVICE_TRAN_INT_NAME = resourceBundle.getString("SBM_SERVICE_TRAN_INT_NAME");
		    ANSWERID_URL=resourceBundle.getString("ANSWERID_URL");
		} catch (AxisFault e) {
			logger.error("AxisFault: Exception while creating the service stub " + e.fillInStackTrace());
		}
	}

	/**
	 * 
	 * This method calls the linking API (TransitionItem method) passing in the
	 * selected answer data ( Title, URL). Based on the API response,an
	 * appropriate status is passed back to the calling code.
	 * 
	 * @param postData
	 *            - Selected Answer data in JSON format
	 * @return json_response - response string based on the service call output
	 * @throws AEWebservicesFaultFault
	 */
	public static String addIncidentLink(JSONObject postData, Object userObj) {
		String json_response = null;
		try {
			logger.info("addIncidentLink method called for incident id " + postData.toString());
			// setup the Auth credentials for SOAP connection
			Auth userCredentials = new Auth();
			userCredentials.setHostname(resourceBundle.getString("SBM_SERVICE_HOST"));
			userCredentials.setUserId(resourceBundle.getString("SBM_SERVICE_USER_ID"));
			userCredentials.setPassword(resourceBundle.getString("SBM_SERVICE_USER_PASSWORD"));

			logger.info("Setting up the payload for linking");
			// call TransitionItem for linking
			TransitionItem transitionItem = new TransitionItem();
			// set auth creds
			transitionItem.setAuth(userCredentials);
			transitionItem.setBreakLock(true);

			// set TTItem (with the link reference as <urlAttachment>
			TTItem linkItem = new TTItem();

			// ItemIdentifier object that is being updated
			ItemIdentifier linkItemIdentifier = new ItemIdentifier();
			// get the internal item id
			String tableItemID = StringUtils.substringBetween(postData.getString("incidentId"), "(", ")");
			linkItemIdentifier.setTableIdItemId(tableItemID);

			// answer link as attachment
			URLAttachment link = new URLAttachment();
			link.setName(postData.getString("im_doc_id") + ":" + postData.getString("title").trim());
//			link.setUrl(ANSWERID_URL + postData.getString("answerId") + "&incidentID=" + postData.getString("incidentId").replaceAll(" ", "+"));
                        link.setUrl("#");
			link.setAccessType(AttachmentAccessType.value1);
			linkItem.setId(linkItemIdentifier);
			linkItem.addUrlAttachment(link);
			transitionItem.setItem(linkItem);
                        TransitionIdentifier ti = new TransitionIdentifier();
                        ti.setInternalName(SBM_SERVICE_TRAN_INT_NAME);
                        transitionItem.setTransition(ti);
			logger.debug("Payload setup complete for linking");
			// execute the call
			logger.info("executing the API call for transition and link update");
			TransitionItemResponse response = serviceStub.transitionItem(transitionItem);
			// process the response and confirm linking
			boolean isAnswerLinked = false;
			for (URLAttachment linkedItem : response.get_return().getItem().getUrlAttachment()) {
                            
//                            logger.debug(
//                                "Link Name:URL:Id " + 
//                                linkedItem.getName() + 
//                                ":" + 
//                                linkedItem.getUrl() + 
//                                ":" + 
//                                linkedItem.getId()
//                            );
                            String linkedAnswerId = linkedItem.getName().split(":")[0];

			    if (logger.isDebugEnabled()) 
			        logger.debug("SBMServiceManager response = "+linkedAnswerId+ " = "+postData.getString("im_doc_id"));


                            if (linkedAnswerId.equals(postData.getString("im_doc_id"))) {
                                logger.debug("Linked Item Answer Id " + linkedAnswerId);
                                isAnswerLinked = true;
                                break;
                            }
			}
			if (isAnswerLinked) {
				logger.info("API call executed successfully, Transition complete with link update for incidentId " + postData.getString("incidentId"));
				// logout the user and release the connection
				Logout userLogout = new Logout();
				userLogout.setAuth(userCredentials);
				serviceStub.logout(userLogout);
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
			}

		} catch (RemoteException e) {
			logger.error("RemoteException: Exception while calling the service " + e.fillInStackTrace());
			// json_response = "{\"error\":\" Error Occurred during link contact
			// Admin with Error Code LinkItem-6200 \"}";
			// json_response = "{\"error\":\"true\", \"message\":\" Error
			// Occurred link unlink contact Admin with Error Code LinkItem-6200
			// \"}";
			json_response = "{\"message\":\"" + e.getMessage().trim() + "\",\"error\":\"true\"}";
		} catch (AEWebservicesFaultFault e) {
			logger.error("AEWebservicesFaultFault: Exception while calling the service " + e.fillInStackTrace());
			// json_response = "{\"error\":\" Error Occurred during link contact
			// Admin with Error Code LinkItem-6210 \"}";
			json_response = "{\"message\":\"" + e.getMessage().trim() + "\",\"error\":\"true\"}";
		} catch (JSONException e) {
			logger.error("JSONException: Exception while parsing the input data " + e.fillInStackTrace());
			// json_response = "{\"error\":\" Error Occurred during link contact
			// Admin with Error Code LinkItem-6220 \"}";
			json_response = "{\"message\":\"" + e.getMessage().trim() + "\",\"error\":\"true\"}";
		}
		if (json_response == null)
			json_response = "{\"error\":\" Application Error during link contact Admin with Error Code LinkItem-6230 \"}";
		return json_response;
	}

	/**
	 * 
	 * This method calls the unlinking API (DeleteAttachment method) passing in
	 * the answer/attachment data id. Based on the API response,an appropriate
	 * status is passed back to the calling code.
	 * 
	 * @param postData
	 *            - Selected Answer data in JSON format
	 * @return json_response - response string based on the service call output
	 */

        public static String removeIncidentLink(JSONObject postData, Object userObj) {
            String json_response = null;
            try {
                logger.info("removeIncidentLink method called for incident id " + postData.getString("incidentId"));
                // setup the Auth credentials for SOAP connection
                Auth userCredentials = new Auth();
                userCredentials.setHostname(resourceBundle.getString("SBM_SERVICE_HOST"));
                userCredentials.setUserId(resourceBundle.getString("SBM_SERVICE_USER_ID"));
                userCredentials.setPassword(resourceBundle.getString("SBM_SERVICE_USER_PASSWORD"));
                
                String linkedItemId = getLinkedItemId(postData, userCredentials);
                if (linkedItemId != null && !linkedItemId.isEmpty()) {
                    logger.debug("Setting up the payload for unlinking, item id " + linkedItemId);
                    // call DeleteAttachment for unlinking
                    DeleteAttachment unlinkItem = new DeleteAttachment();
                    // set auth creds
                    unlinkItem.setAuth(userCredentials);
                    // set the link(attachment id) for delete
                    unlinkItem.setAttachmentID(new BigInteger(linkedItemId));
                    logger.debug("Payload setup complete for unlinking");
                    // execute the call
                    logger.info("executing the API call for unlink update");
                    DeleteAttachmentResponse unlinkResponse = serviceStub.deleteAttachment(unlinkItem);
                    OMElement output = unlinkResponse.getOMElement(DeleteAttachmentResponse.MY_QNAME,
                    OMAbstractFactory.getOMFactory());
                    logger.info("API call executed successfully, Transition complete with unlink update for incidentId " + output.getText());
                    // logout the user and release the connection
                    Logout userLogout = new Logout();
                    userLogout.setAuth(userCredentials);
                    serviceStub.logout(userLogout);
                    json_response = "{\"success\":\"true\", \"message\":\" Document Unlinked \"}";
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
    					json_response = "{\"success\":\"true\", \"message\":\" Document Unlinked. \"}";
    				}
                }            
            } catch (RemoteException e) {
                logger.error("RemoteException: Exception while calling the service " + e.fillInStackTrace());
                // json_response = "{\"error\":\"true\", \"message\":\" Error
                // Occurred during unlink contact Admin with Error Code
                // LinkItem-6500 \"}";
                json_response = "{\"message\":\"" + e.getMessage().trim() + "\",\"error\":\"true\"}";
            } catch (AEWebservicesFaultFault e) {
                logger.error("AEWebservicesFaultFault: Exception while calling the service " + e.fillInStackTrace());
                // json_response = "{\"error\":\"true\", \"message\":\" Error
                // Occurred during unlink contact Admin with Error Code
                // LinkItem-6600 \"}";
                json_response = "{\"message\":\"" + e.getMessage().trim() + "\",\"error\":\"true\"}";
            } catch (JSONException e) {
                logger.error("JSONException: Exception while parsing the input data " + e.fillInStackTrace());
                json_response = "{\"error\":\"true\", \"message\":\" Error Occurred during unlink contact Admin with Error Code LinkItem-6700 \"}";
            } catch (ADBException e) {
                logger.error("ADBException: Exception while parsing the API response output " + e.fillInStackTrace());
                // json_response = "{\"error\":\"true\", \"message\":\" Error
                // Occurred during unlink contact Admin with Error Code
                // LinkItem-6800 \"}";
                json_response = "{\"message\":\"" + e.getMessage().trim() + "\",\"error\":\"true\"}";            
            }
            if (json_response == null) json_response = "{\"error\":\"true\", \"message\":\" Error Occurred during unlink contact Admin with Error Code LinkItem-6900 \"}";
            logger.info("Returning the JSON response " + json_response);
            return json_response;
        }

	/**
	 * 
	 * This method gets the internal id of the linked item. It is used before
	 * the unlink call to get the item id that is being unlinked.
	 * 
	 * @param postData
	 *            - Selected Answer data in JSON format
	 * @userCredentials - user authentication data for SOAP call
	 * @return linkedItemId - internal item id for the linked item(attachment)
	 * @throws AEWebservicesFaultFault
	 */
        private static String getLinkedItemId(JSONObject postData, Auth userCredentials) throws AEWebservicesFaultFault {        
            try {
                logger.info("Getting the linked Item ID for incident " + postData.getString("incidentId"));
                // setup the Auth credentials for SOAP connection
                GetItem item = new GetItem();
                item.setAuth(userCredentials);
                logger.debug("Setting the item identifier");
                ItemIdentifier itemIdentifier = new ItemIdentifier();
                // get the internal item id
                String tableItemID = StringUtils.substringBetween(postData.getString("incidentId"), "(", ")");
                itemIdentifier.setTableIdItemId(tableItemID);
                item.setItemId(itemIdentifier);
                
                logger.debug("Executing the call for GetItem");
                GetItemResponse response = serviceStub.getItem(item);
                // get the linked item id
                String linkedItemId = null;
                for (URLAttachment linkedItem : response.get_return().getItem().getUrlAttachment()) {
                    String linkedAnswerId = linkedItem.getName().split(":")[0];
                    logger.debug("Link Name:" + linkedAnswerId + ":" + postData.getString("im_doc_id") + ":" + linkedAnswerId.equals(postData.getString("im_doc_id")));
                    //if (linkedAnswerId.equals(postData.getString("documentid"))) {
                    if (linkedAnswerId.equals(postData.getString("im_doc_id"))) {
                        logger.debug("Linked Item Answer Id " + linkedAnswerId);
                        linkedItemId = linkedItem.getId().toString();
                        break;
                    }
                }
                return linkedItemId;
            } catch (RemoteException e) {
                logger.error("RemoteException: Exception while calling the service " + e.fillInStackTrace());
                throw new AEWebservicesFaultFault(e.getMessage(), e);
            } catch (AEWebservicesFaultFault e) {
                logger.error("AEWebservicesFaultFault: Exception while calling the service " + e.fillInStackTrace());
                throw new AEWebservicesFaultFault(e.getMessage(), e);
            } catch (JSONException e) {
                logger.error("JSONException: Exception while parsing the input data " + e.fillInStackTrace());
                throw new AEWebservicesFaultFault(e.getMessage(), e);
            }
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
    			details.setCRM_ID(CrmId.TEAMTRACK);
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
    				Utilities.printStackTraceToLogs(SBMServiceManager.class.getName(), "createCaseHsitoryRecrod()", e);
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
    			accoundId = null;
    			fullName = null;
    		}
    		catch(Exception e)
    		{
    			Utilities.printStackTraceToLogs(SBMServiceManager.class.getName(), "createCaseHsitoryRecrod()", e);
    		}
    		return status;
    	}
    	
} // end class
