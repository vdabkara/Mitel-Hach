package com.ka.kcapp.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ka.kcapp.menuitems.CrmId;
import com.ka.kcapp.menuitems.TransactionType;
import com.ka.kcapp.osvc.services.CaseHistoryRecord;
import com.ka.kcapp.osvc.services.Cruder;
import com.ka.kcapp.osvc.services.UserInfoRecord;
import com.ka.kcapp.util.Utilities;
import com.ka.kcapp.vo.CaseHistoryDetails;
import com.ka.kcapp.vo.RecommendationDetails;
import com.ka.kcapp.widget.common.Content;
import com.ka.kcapp.widget.common.TemplateHandler;

/**
 * Servlet implementation class ContentRecommentController
 */
public class ContentRecommendController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(ContentRecommendController.class);
	private ResourceBundle	labelsBundle;
	private ResourceBundle	resourceBundle;
	private RecommendationDetails recommendationDetails;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContentRecommendController() {
        super();
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		logger.debug("doPost() ::  Processing the post request");
		StringBuilder action_response = new StringBuilder();
		JSONObject responseObject= null;
		resourceBundle = ResourceBundle.getBundle("resources.application");
		labelsBundle = ResourceBundle.getBundle("resources.LabelsBundle", new Locale("en"));
		try
		{
			readParametersFromRequest(request, response);
//			logger.info("doPost() :: Title :: >"+ recommendationDetails.getTitle());
//			logger.info("doPost() :: Description :: >"+ recommendationDetails.getDescription());
//			logger.info("doPost() :: CaseNumber :: >"+ recommendationDetails.getCaseNumber());
//			logger.info("doPost() :: Priority Type :: >"+ recommendationDetails.getPriorityType());
//			logger.info("doPost() :: Content Type :: >"+ recommendationDetails.getContentType());
			/*
			 * check if all the mandatory parameters are not null
			 * proceed for creating recommendation
			 */
			if(null!=recommendationDetails && null!=recommendationDetails.getTitle() && !"".equals(recommendationDetails.getTitle()) 
				&& null!=recommendationDetails.getDescription() && !"".equals(recommendationDetails.getDescription()) 
				&& null!=recommendationDetails.getPriorityType() && !"".equals(recommendationDetails.getPriorityType()) 
				&& null!=recommendationDetails.getContentType() && !"".equals(recommendationDetails.getContentType()))
			{
				String sourceLabel="";
				if(null!=request.getSession().getAttribute("source"))
				{
					sourceLabel= String.valueOf(request.getSession().getAttribute("source"));
					recommendationDetails.setCRM_ID(CrmId.valueOf(String.valueOf(request.getSession().getAttribute("source"))));
				}
				if(null!=request.getSession().getAttribute("userID"))
				{
					recommendationDetails.setUserId(String.valueOf(request.getSession().getAttribute("userID")));
				}
				/*
				 * Proceed for Recommend Content Creation.
				 * Identify Channel Node
				 */
				JSONObject contentTypeObject = null;
				if(null!=request.getSession().getAttribute("RC_CONTENT_TYPE_NODES"))
				{
					JSONArray contentTypes = (JSONArray)request.getSession().getAttribute("RC_CONTENT_TYPE_NODES");
					if(null!=contentTypes && contentTypes.length()>0)
					{
						for(int a=0;a<contentTypes.length();a++)
						{
							JSONObject jsonObj = (JSONObject)contentTypes.get(a);
							if(null!=jsonObj.get("recordId"))
							{
								if(recommendationDetails.getContentType().trim().toLowerCase().equals(String.valueOf(jsonObj.get("recordId")).trim().toLowerCase()))
								{
									contentTypeObject = new JSONObject();
									contentTypeObject.put("recordId", jsonObj.get("recordId"));
									contentTypeObject.put("name", jsonObj.get("name"));
									contentTypeObject.put("referenceKey", jsonObj.get("referenceKey"));
									contentTypeObject.put("links", jsonObj.get("links"));
									break;
								}
							}
							jsonObj = null;
						}
					}
					contentTypes = null;
				}
				
				if(null!=contentTypeObject)
				{
					/*
					 * CREATE XML FOR <ContentRecommendation>
					 */
					StringBuilder recXml = new StringBuilder();
					recXml.append("<ContentRecommendation>");
					// add Title
					recXml.append("<title><![CDATA["+recommendationDetails.getTitle()+"]]></title>");
					// add contentType
					recXml.append("<contentType>");
						// add recordId
						recXml.append("<recordId>"+ contentTypeObject.get("recordId")+"</recordId>");
						// add name
						recXml.append("<name>"+ contentTypeObject.get("name")+"</name>");
						// add referenceKey
						recXml.append("<referenceKey>"+ contentTypeObject.get("referenceKey")+"</referenceKey>");
						// add Links Node
						recXml.append("<links>");
						recXml.append("<rel>canonical</rel>");
						recXml.append("<method>GET</method>");
						recXml.append("<mediaType>application/json, application/xml</mediaType>");
						String hrefValue=resourceBundle.getString("OKCS_IM_API_URL")+"contentTypes/"+String.valueOf(contentTypeObject.get("recordId"));
						recXml.append("<href>"+hrefValue+"</href>");
						hrefValue =null;
						recXml.append("</links>");
					recXml.append("</contentType>");
					
					// add Case number
					recXml.append("<caseNumber><![CDATA["+recommendationDetails.getCaseNumber()+"]]></caseNumber>");
					// add Comments
					recXml.append("<comments><![CDATA["+recommendationDetails.getDescription()+"]]></comments>");
					// add Priority
					recXml.append("<priority><![CDATA["+recommendationDetails.getPriorityType()+"]]></priority>");
					recXml.append("</ContentRecommendation>");
					
//					logger.info("doPost() :: recommendationPayLoad :: > " + recXml.toString());
					
					/*
					 * invoke KA Rest service for Creating Recommendation
					 * if Success - Proceed for creating Case history Record Using Soap Web Service
					 */
					
					JSONObject createResponseObject = Content.createRecommendation(recXml.toString(), recommendationDetails, request, response);
					if(null!=createResponseObject)
					{
						if(null!=createResponseObject.get("success"))
						{
							if(createResponseObject.getBoolean("success")==true)
							{
								if(null!=createResponseObject.get("recordId"))
								{
									recommendationDetails.setRecommendationId(String.valueOf(createResponseObject.get("recordId")));
								}
								logger.info("doPost() :: Content Recommendation Created Successfully. Recommendation Id is :: >"+ createResponseObject.get("recordId"));
								
								/*
								 * call function to check whether user exists in user_info table or not.
								 */
								Cruder cruder = new Cruder();
								String accoundId="";
								String fullName= "";
								String userPayloadBody = "";
								UserInfoRecord userInfoRecrod = new UserInfoRecord();
								try
								{
									userPayloadBody = userInfoRecrod.getUserPayload(recommendationDetails.getUserId(), sourceLabel);
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
								
								/*
								 * proceed for saving the record in OSVC Table.
								 * CREATE PAYLOAD AND SAVE IN CASE_HISTORY TABLE.
								 */
								CaseHistoryDetails details = new CaseHistoryDetails();
								details.setCaseNumber(recommendationDetails.getCaseNumber());
								details.setAnswerId(recommendationDetails.getAnswerId());
								details.setDocumentId(recommendationDetails.getDocumentId());
								details.setCRM_ID(recommendationDetails.getCRM_ID());
								details.setTRANSACTION_TYPE(TransactionType.RECOMMEND_CONTENT);
								details.setKaRecordId(recommendationDetails.getRecommendationId());
								if(null!=accoundId && !"".equals(accoundId))
								{
									/*
									 *  USER LOCATED IN USER_INFO TABLE.
									 *  PUT USER ID AND KA RECID SAME AS RECEIVED IN PARAM
									 */
									details.setUserId(accoundId);
									details.setFullName(fullName);
									details.setCrmUserId(recommendationDetails.getUserId());
								}
								else
								{
									/*
									 * USER NOT LOCATED IN USER_INFO TABLE.
									 * PUT USER ID AS ANONYMOUS AND KA RECORD ID AS VALUE RECEIVED FROM PARAM
									 */
									details.setUserId(resourceBundle.getString("teamtrackDefaultUser"));
									details.setFullName(resourceBundle.getString("teamtrackDefaultUserName"));
									details.setCrmUserId(recommendationDetails.getUserId());
								}
								accoundId = null;
								fullName = null;
								
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
									Utilities.printStackTraceToLogs(ContentRecommendController.class.getName(), "doPost()", e);
								}
								
								if(null!=cruderResponse)
								{
									try
									{
										if(null!=cruderResponse.get("success"))
										{
											if(cruderResponse.getBoolean("success")==true)
											{
												logger.info("doPost() :: Case History Record for Recommendation Created Succesfully.");
												responseObject = new JSONObject();
												responseObject.put("success", true);
//												String message=labelsBundle.getString("RecommendContentTag.successMessage1")+ createResponseObject.get("recordId") + labelsBundle.getString("RecommendContentTag.successMessage2");
												String message=labelsBundle.getString("RecommendContentTag.successMessage");
												responseObject.put("message", message);
												message= null;
											}
											else
											{
												logger.info("doPost() :: Failed to Create Case History Record. Response from OSVC Web Service is FALSE.");
												responseObject = new JSONObject();
												responseObject.put("success", true);
//												String message=labelsBundle.getString("RecommendContentTag.successMessage1")+ createResponseObject.get("recordId") + labelsBundle.getString("RecommendContentTag.successMessage2");
												String message=labelsBundle.getString("RecommendContentTag.successMessage")+" "+labelsBundle.getString("RecommendContentTag.failedCaseHistoryMessage");
												responseObject.put("message", message);
												message= null;
											}
										}
										else
										{
											logger.info("doPost() :: Failed to Create Case History Record. In Response from OSVC Web Service SUCCESS Node not Found.");
											responseObject = new JSONObject();
											responseObject.put("success", true);
//											String message=labelsBundle.getString("RecommendContentTag.successMessage1")+ createResponseObject.get("recordId") + labelsBundle.getString("RecommendContentTag.successMessage2");
											String message=labelsBundle.getString("RecommendContentTag.successMessage")+" "+labelsBundle.getString("RecommendContentTag.failedCaseHistoryMessage");
											responseObject.put("message", message);
											message= null;
										}
									}
									catch(Exception e)
									{
										Utilities.printStackTraceToLogs(ContentRecommendController.class.getName(), "doPost()", e);
										logger.info("doPost() :: Failed to Create Case History Record. In Response from OSVC Web Service SUCCESS Node not Found.");
										responseObject = new JSONObject();
										responseObject.put("success", true);
//										String message=labelsBundle.getString("RecommendContentTag.successMessage1")+ createResponseObject.get("recordId") + labelsBundle.getString("RecommendContentTag.successMessage2");
										String message=labelsBundle.getString("RecommendContentTag.successMessage")+" "+labelsBundle.getString("RecommendContentTag.failedCaseHistoryMessage");
										responseObject.put("message", message);
										message= null;
									}
								}
								else
								{
									logger.info("doPost() :: Failed to Create Case History Record. Response from OSVC Web Service is NULL.");
									responseObject = new JSONObject();
									responseObject.put("success", true);
//									String message=labelsBundle.getString("RecommendContentTag.successMessage1")+ createResponseObject.get("recordId") + labelsBundle.getString("RecommendContentTag.successMessage2");
									String message=labelsBundle.getString("RecommendContentTag.successMessage")+" "+labelsBundle.getString("RecommendContentTag.failedCaseHistoryMessage");
									responseObject.put("message", message);
									message= null;
								}
								cruderResponse = null;
								cruder=  null;
								details= null;
								caseHisRec = null;
							}
							else
							{
								logger.info("doPost() :: Failed to Create Recommendation. Response from KA Service SUCCESS Key is FALSE.");
								// show Catched Error.
								responseObject = new JSONObject();
								responseObject.put("success", false);
								if(null!=createResponseObject.get("message"))
								{
									logger.info("doPost() :: Failed to Create Recommendation :: > " +createResponseObject.get("message"));
									responseObject.put("message", createResponseObject.get("message"));
								}
								else
								{
									// generic Error Message
									responseObject.put("message", labelsBundle.getString("RecommendContentTag.errorMessage"));
								}
							
							}
						}
						else
						{
							logger.info("doPost() :: Failed to Create Recommendation. Responsse from KA Service does not Contain SUCCESS key.");
							// throw Generic Error.
							responseObject = new JSONObject();
							responseObject.put("success", false);
							responseObject.put("message", labelsBundle.getString("RecommendContentTag.errorMessage"));
						
						}
					}
					else
					{
						logger.info("doPost() :: Failed to Create Recommendation. Responsse from KA Service is NULL.");
						// throw Generic Error.
						responseObject = new JSONObject();
						responseObject.put("success", false);
						responseObject.put("message", labelsBundle.getString("RecommendContentTag.errorMessage"));
					}
					recXml= null;
				}
				else
				{
					logger.info("doPost() :: Failed to Identify Content Type node for selected ContentType :: > " + recommendationDetails.getContentType());
					responseObject = new JSONObject();
					responseObject.put("success", false);
					responseObject.put("message", labelsBundle.getString("RecommendContentTag.errorMessage"));
				}
				contentTypeObject = null;
			}
			else
			{
				logger.info("doPost() :: Mandatory Fields are null.");
			}
		}
		catch (Exception ex)
		{
			Utilities.printStackTraceToLogs(ContentRecommendController.class.getName(), "doPost()", ex);
			try
			{
				responseObject = new JSONObject();
				responseObject.put("success", false);
				responseObject.put("message", labelsBundle.getString("RecommendContentTag.errorMessage"));
			}
			catch(Exception e)
			{
				Utilities.printStackTraceToLogs(ContentRecommendController.class.getName(), "doPost()", e);
			}
		}
		finally
		{
			// reset Form
			recommendationDetails = null;
		}
		
		if(null!=responseObject)
		{
			Map<String, Object> input = new HashMap<String, Object>();
			input.put("response", responseObject.toString());
			action_response.append(TemplateHandler.getTemplateHtml(input,
					"cc_CreateResponse.ftl", this.getServletContext()));
		}
		response.getWriter().write(action_response.toString());
	}

	private void readParametersFromRequest(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			recommendationDetails = new RecommendationDetails();
			/*
			 * Get Parameters from Request
			 */
			Enumeration<String> paramsData = request.getParameterNames();
			if(null!=paramsData)
			{
				while(paramsData.hasMoreElements())
				{
					String paramKey = paramsData.nextElement();
					if(null!=paramKey && !"".equals(paramKey))
					{
						String paramValue=null;
						String[] values = request.getParameterValues(paramKey);
						if(null!=values && values.length>0)
						{
							paramValue= values[0];
						}
						// set in Local Attributes
						if(null!=paramKey && !"".equals(paramKey))
						{
							if(paramKey.endsWith("title"))
							{
								recommendationDetails.setTitle(paramValue);
							}
							else if(paramKey.endsWith("description"))
							{
								recommendationDetails.setDescription(paramValue);
							}
							else if(paramKey.endsWith("caseNumber"))
							{
								recommendationDetails.setCaseNumber(paramValue);
							}
							else if(paramKey.endsWith("priorityType"))
							{
								recommendationDetails.setPriorityType(paramValue);
							}
							else if(paramKey.endsWith("contentType"))
							{
								recommendationDetails.setContentType(paramValue);
							}
						}
						paramValue=  null;
						values=  null;
					}
					paramKey = null;
				}
			}
			paramsData = null;
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(ContentRecommendController.class.getName(), "readParametersFromRequest()", e);
		}
	}
}