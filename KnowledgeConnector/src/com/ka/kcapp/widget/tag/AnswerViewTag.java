/**
 * Copyright (c) 2015, Oracle Corporation and/or its affiliates. All rights reserved.
 *
 * The sample code in this document or accessed through this document is not certified or
 * supported by Oracle. It is intended for educational or testing purposes only. Use of this
 * sample code implies acceptance of the License Agreement
 * (http://www.oracle.com/technetwork/licenses/standard-license-152015.html).
 *
 * File: AnswerViewTag.java
 * Abstract: Tag used to retrieve Answer Details
 * Version: 1.0
 */

package com.ka.kcapp.widget.tag;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ka.kcapp.util.IMArticleUtils;
import com.ka.kcapp.widget.apiUtility.OKAPIUtility;
import com.ka.kcapp.widget.common.JsonSequence;
import com.ka.kcapp.widget.common.LinkedItems;

/**
 * Tag used to retrieve Answer Details
 * 
 * @author Akshay Shenoy
 */
public class AnswerViewTag extends BaseTag {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(AnswerViewTag.class);
	private IMArticleUtils articleUtils = new IMArticleUtils();

	private String label_unlink_confirm = labelsBundle.getString("LINKING.unlink_cofirm");

	/**
	 * Constructor for AnswerViewTag
	 */
	public AnswerViewTag() {
		super();
	}

	/**
	 * IM Answer ID
	 */
	private String answerId;
	/**
	 * Search Answer ID
	 */
	private String searchAnswerId;
	/**
	 * Document ID
	 */
	private String documentId;
	/**
	 * File Type requested.
	 */
	private String fileType;
	/**
	 * File Name
	 */
	private String fileName;
	/**
	 * Server Side Template
	 */

	private String serverSideTemplate;
	private boolean answerLinkStatus = false;
	private boolean showCaseLinks = false;
	private boolean showAddLink = false;
	private boolean showAddText = false;
	private String documentIdLabel = labelsBundle.getString("AnswerView.documentIdLabel");
	private String statusLabel = labelsBundle.getString("AnswerView.statusLabel");
	private String versionLabel = labelsBundle.getString("AnswerView.versionLabel");
	private String publishedDateLabel = labelsBundle.getString("AnswerView.publishedDateLabel");

	public String getStatusLabel() {
		return statusLabel;
	}

	public void setStatusLabel(String statusLabel) {
		this.statusLabel = statusLabel;
	}

	public String getVersionLabel() {
		return versionLabel;
	}

	public void setVersionLabel(String versionLabel) {
		this.versionLabel = versionLabel;
	}

	public String getPublishedDateLabel() {
		return publishedDateLabel;
	}

	public void setPublishedDateLabel(String publishedDateLabel) {
		this.publishedDateLabel = publishedDateLabel;
	}

	public String getDocumentIdLabel() {
		return documentIdLabel;
	}

	public void setDocumentIdLabel(String documentIdLabel) {
		this.documentIdLabel = documentIdLabel;
	}

	public String getAnswerId() {
		return answerId;
	}

	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	/**
	 * @return the searchAnswerId
	 */
	public String getSearchAnswerId() {
		return searchAnswerId;
	}

	/**
	 * @param searchAnswerId
	 *            the searchAnswerId to set
	 */
	public void setSearchAnswerId(String searchAnswerId) {
		this.searchAnswerId = searchAnswerId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public boolean getAnswerLinkStatus() {
		return answerLinkStatus;
	}

	public void setAnswerLinkStatus(boolean answerLinkStatus) {
		this.answerLinkStatus = answerLinkStatus;
	}

	public boolean isShowCaseLinks() {
		return showCaseLinks;
	}

	public void setShowCaseLinks(boolean showCaseLinks) {
		this.showCaseLinks = showCaseLinks;
	}

	public boolean isShowAddLink() {
		return showAddLink;
	}

	public void setShowAddLink(boolean showAddLink) {
		this.showAddLink = showAddLink;
	}

	public boolean isShowAddText() {
		return showAddText;
	}

	public void setShowAddText(boolean showAddText) {
		this.showAddText = showAddText;
	}

	/**
	 * @return the serverSideTemplate
	 */
	public String getServerSideTemplate() {
		if (serverSideTemplate == null) {
			serverSideTemplate = "answerView";
		}
		return serverSideTemplate;
	}

	/**
	 * @param serverSideTemplate
	 *            the serverSideTemplate to set
	 */
	public void setServerSideTemplate(String serverSideTemplate) {
		this.serverSideTemplate = serverSideTemplate;
	}

	/**
	 * Method to retrieve highlighted content for the Answer being viewed
	 * 
	 * @param request
	 *            Request Object to process the Rest Api Call
	 * @param response
	 *            Response Object to process the Rest Api Response
	 * @param transactionId
	 *            Transaction ID for the highlight request
	 * @param session
	 *            Search Session for the highlight request
	 * @return String JSON Response containing highlight details
	 */
	public String getHighlightData(HttpServletRequest request, HttpServletResponse response, int transactionId,
			String session) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.application");
		String searchURL = resourceBundle.getString("OKCS_SRCH_API_URL").concat("search/answer?answerId=")
				.concat(this.getSearchAnswerId()).concat("&priorTransactionId=").concat(transactionId + "")
				.concat("&highlightFlag=true&trackClickFlag=true");

		String postData = "{\"requestSource\":\"IM\",\"locale\":\"en_US\",\"resultLocales\":\"en_US\",\"session\":\""
				.concat(session).concat("\",\"transactionId\":\"").concat(transactionId + "").concat("\"}");
		return OKAPIUtility.callRESTAPI(request, response, searchURL, "POST", postData);
	}

	/**
	 * Method to retrieve Article Details from InfoManager
	 * 
	 * @param request
	 *            Request Object to process the Rest Api Call
	 * @param response
	 *            Response Object to process the Rest Api Response
	 * @return String JSON Response containing Article details
	 */
	public String getIMArticleDetail(HttpServletRequest request, HttpServletResponse response) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.application");
		String imURL = resourceBundle.getString("OKCS_IM_API_URL").concat("content/answers/") + this.answerId
				+ "?q=contentState+eq+%22LATESTVALID%22";
		return OKAPIUtility.callRESTAPI(request, response, imURL, "GET", "");
	}

	/**
	 * Method to get the Answer Schema in the page context for IM Articles
	 * 
	 * @param request
	 *            Request Object to process the Rest Api Call
	 * @param response
	 *            Response Object to process the Rest Api Response
	 * @param answerJSON
	 *            JSON containing the Article details
	 * @param input
	 *            Hash Map containing name-Object mappings of Article Schema
	 *            Attributes. At the end of this method, this Hash Map will have
	 *            all the details necessary to render the Article on the
	 *            AnswerView Page
	 * @throws JSONException
	 * @throws ParseException
	 */
	public void getAnswerSchemaData(HttpServletRequest request, HttpServletResponse response, JSONObject answerJSON,
			Map<String, Object> input) throws JSONException, ParseException {
		String status = answerJSON.getBoolean("published") == true ? "Published" : "UnPublished";
		String publishedDate = articleUtils.processIMDate(answerJSON.getString("publishDate"));
		input.put("documentIdLabel", this.getDocumentIdLabel());
		input.put("statusLabel", this.getStatusLabel());
		input.put("versionLabel", this.getVersionLabel());
		input.put("publishedDateLabel", this.getPublishedDateLabel());
		input.put("title", answerJSON.getString("title"));
		input.put("documentId", answerJSON.getString("documentId"));
		input.put("status", status);
		input.put("version", answerJSON.getString("version"));
		input.put("publishedDate", publishedDate);
		JSONObject contentType = new JSONObject(answerJSON.getString("contentType"));
		request.setAttribute("answerJSON", answerJSON.toString());
		LinkedHashMap<String, JSONObject> schemaAttributesMap = articleUtils.getSchemaDetails(request, response,
				contentType.getString("referenceKey"));
		articleUtils.setAnswerId(this.getAnswerId());
		input.put("SchemaAttributeList", articleUtils.getSchemaHtml(schemaAttributesMap, answerJSON.getString("xml"),
				answerJSON.getString("resourcePath")));
	}

	public int doStartTag() throws JspException {
		try {
			logger.info("Answer view - doStartTag called..");
			JspWriter out = pageContext.getOut();
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();
			String widgetJsPath = getWidgetJsPath("answerView/answerView.js");
			String widgetId = getWidgetInstanceId("answerView");
			String searchId = request.getParameter("s_id");
			HttpSession session = request.getSession(true);
			String transId = null;
			String apiSession = "";
			JSONArray linkedItems = null;
			String incidentID = request.getParameter("incidentID");
			if (searchId != null) {
				JSONObject searchFilters = new JSONObject((String) session.getAttribute(searchId));
				transId = searchFilters.getString("transactionId");
				apiSession = searchFilters.getString("apiSession");
			}
			int transctionId = 1;
			if (transId != null) {
				transctionId = Integer.parseInt(transId);
			}

			this.setSearchAnswerId(request.getParameter("searchAnswerId"));
			this.setAnswerId(request.getParameter("answerId"));
			this.setFileType(request.getParameter("fileType"));
			this.setAnswerLinkStatus(false);
			if (request.getSession().getAttribute(OKAPIUtility.USER_TOKEN) != null) {
				if (this.isShowCaseLinks() && (!"".equals(incidentID) && incidentID != null)
						&& this.getAnswerId() != null) {
					if (request.getSession().getAttribute(OKAPIUtility.USER_TOKEN) != null && this.isShowAddLink()
							&& request.getParameter("incidentID") != null && request.getParameter("incidentID") != "") {
						linkedItems = new JSONArray(LinkedItems
								.getLinkedAnswersByIncidentID(request.getParameter("incidentID"), request, response));
						/*
						 * if (linkedItems.length() != 0) { for (int i = 0; i <
						 * linkedItems.length(); i++) { if
						 * (logger.isDebugEnabled()) logger.debug(" link item "
						 * + this.getAnswerId() + " = " + linkedItems
						 * .getJSONObject(i).getJSONObject("document").getString
						 * ("answerId")); logger.info(
						 * " linked item : documentId " + this.getAnswerId() +
						 * " = " + linkedItems
						 * .getJSONObject(i).getJSONObject("document").getString
						 * ("documentId")); if (this.getAnswerId().equals(
						 * linkedItems.getJSONObject(i).getJSONObject("document"
						 * ).getString("answerId"))) {
						 * this.setAnswerLinkStatus(true); } } }
						 */
					}

				} else {
					this.setShowCaseLinks(false);
					this.setAnswerLinkStatus(false);
				}
			} else {
				this.setShowCaseLinks(false);
				this.setAnswerLinkStatus(false);
			}
			String url = request.getParameter("url");
			String answerDetail = "";
			Map<String, Object> input = new HashMap<String, Object>();
			Map<String, Object> error = new HashMap<String, Object>();

			if (this.getAnswerId() != null) {
				// open IM Article
				logger.info("Fetching the IM Article");
				if (request.getAttribute("answerJSON") != null) {
					try {
						getAnswerSchemaData(request, response,
								new JSONObject((String) request.getAttribute("answerJSON")), input);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				} else {
					try {
						answerDetail = getHighlightData(request, response, transctionId, apiSession);
						JSONObject jsonAnswerDetail = new JSONObject(answerDetail);
						JSONObject httpPassThrough = new JSONObject();
						if(null!=jsonAnswerDetail)
						{
							if(null!=jsonAnswerDetail.get("HttpPassThrough"))
							{
								httpPassThrough = new JSONObject(jsonAnswerDetail.getString("HttpPassThrough"));
							}
						}
						getAnswerSchemaData(request, response, httpPassThrough, input);
					} catch (Exception e) {
						try {
							logger.warn(
									"Highlighted content could not be fetched for the answer requested. Trying to fetch content/article without highlighting,"
											+ e.getMessage());
							answerDetail = getIMArticleDetail(request, response);
							JSONObject jsonAnswerDetail = new JSONObject(answerDetail);
							getAnswerSchemaData(request, response, jsonAnswerDetail, input);
							// check if this document id is already linked
							logger.info("Checking if this document is already linked");
							if (null!=linkedItems && linkedItems.length() > 0) {
								for (int i = 0; i < linkedItems.length(); i++) {
									logger.info(" linked item : documentId " + linkedItems.getJSONObject(i)
											.getJSONObject("document").getString("documentId"));
									if (jsonAnswerDetail.getString("documentId").equals(linkedItems.getJSONObject(i)
											.getJSONObject("document").getString("documentId"))) {
										logger.info("This document[" + jsonAnswerDetail.getString("documentId")
												+ "] is already linked, setting status to TRUE");
										this.setAnswerLinkStatus(true);
									}
								}
							}

						} catch (Exception ex) {
							logger.error(
									"You no longer have permission to view the article or the article has been removed",
									e);
							error.put("error", labelsBundle.getString("AnswerView.AnswerUnavailableTitle"));
							error.put("message", labelsBundle.getString("AnswerView.AnswerUnavailableMessage"));
						}
					}
				}
				input.put("hideHeaderHtml", "true");
			} else if ("true".equals(request.getParameter("viewAttachment"))) {
				logger.info("ViewAttachment block - Getting the resource url");
				// Open highlighted IM Attachment
				input.put("hideHeaderHtml", "false");
				String[] attachmentDetails = url.split("/");
				this.setAnswerId(attachmentDetails[0]);
				this.setFileName(attachmentDetails[1]);
				input.put("title", this.getFileName());
				String resourcePath = "";
				String highlightString = "";

				try {
					JSONObject jsonHighlightDetail = new JSONObject(getHighlightData(request, response, transctionId, apiSession));
					String[] highlightData = new String[0];
					JSONObject urlData = new JSONObject();
					if(null!=jsonHighlightDetail)
					{
						if(null!=jsonHighlightDetail.get("url"))
						{
							highlightData = jsonHighlightDetail.getString("url").split("#");
						}
						if(null!=jsonHighlightDetail.get("HttpPassThrough"))
						{
							urlData = new JSONObject(jsonHighlightDetail.getString("HttpPassThrough"));
						}
					}
					
					String[] articleData = new String[0];
					if(null!=highlightData)
					{
						if(highlightData.length>0)
						{
							articleData = highlightData[0].split(":");
						}
						if(highlightData.length>2)
						{
							highlightString = "#" + highlightData[2];
						}
					}
					if(null!=articleData && articleData.length>6)
					{
						this.setAnswerId(articleData[6]);
					}
					if(null!=urlData)
					{
						if(null!=urlData.get("resourcePath"))
						{
							resourcePath = urlData.getString("resourcePath");
						}
					}
					input.put("isHighlighted", "true");
					input.put("highlightString", highlightString);
				} catch (Exception e) {
					logger.warn(
							"Highlighted content could not be fetched for the answer(attachment) requested. Trying to fetch content without highlighting : ",
							e.fillInStackTrace());
					input.put("viewableAttachment", "true");
					try {
						answerDetail = getIMArticleDetail(request, response);
						JSONObject jsonAnswerDetail  = new JSONObject();
						if(null!=answerDetail)
						{
							jsonAnswerDetail  = new JSONObject(answerDetail);
						}
						if(null!=jsonAnswerDetail)
						{
							if(null!=jsonAnswerDetail.get("resourcePath"))
							{
								resourcePath = jsonAnswerDetail.getString("resourcePath");
							}
						}
						logger.info("Resource Path for file attachment " + resourcePath);
					} catch (Exception ex) {
						logger.error(
								"You no longer have permission to view the attachment or the attachment is not available",
								e);
						error.put("error", labelsBundle.getString("AnswerView.FileDownloadErrorTitle"));
						error.put("message", labelsBundle.getString("AnswerView.FileDownloadErrorMessage"));
					}
				}
				if (!error.containsKey("error")) {
					logger.info("File Type ..............." + fileType);
					if ("PDF".equals(fileType) || "TEXT".equals(fileType) || "TXT".equals(fileType)
							|| "MS-WORD".equals(fileType) || "DOC".equals(fileType) || "XLS".equals(fileType)
							|| "MS-EXCEL".equals(fileType) || "MS-POWERPOINT".equals(fileType)
							|| "HTML".equals(fileType) || "XML".equals(fileType) || "ZIP".equals(fileType)) {
						input.put("fileType", fileType);
						input.put("resourceUrl", resourcePath + this.getFileName());
					} else {
						response.sendRedirect(resourcePath + this.getFileName());
					}
					logger.debug("Setting input with the attachment url " + request.getRequestURL().toString()
							+ "?getFile=true&fileType=" + fileType + "&url=" + URLEncoder.encode(url, "UTF-8"));
					this.setShowAddLink(false);
					input.put("attachmentUrl", request.getRequestURL().toString() + "?getFile=true&fileType=" + fileType
							+ "&url=" + URLEncoder.encode(url, "UTF-8"));

				}
			} else if ("true".equals(request.getParameter("getFile"))) {

				logger.info("getFile block - Streaming the resource file");
				// Open IM Attachment as a sharable link(without highlighting)
				input.put("hideHeaderHtml", "true");
				String[] attachmentDetails = url.split("/");
				this.setAnswerId(attachmentDetails[0]);
				this.setFileName(attachmentDetails[1]);
				input.put("title", this.getFileName());
				logger.info("Attachment File name =  " + this.getFileName());
				answerDetail = getIMArticleDetail(request, response);
				JSONObject jsonAnswerDetail = new JSONObject(answerDetail);
				// workaround for encoding issue
				fileName = URLDecoder.decode(fileName, "UTF-8");
				logger.info("Attachment File name(after decode) =  " + fileName);
				/*
				 * + character in file name replaced with %20. As this seems to
				 * cause file downloading issues
				 */
				String resourcePath = jsonAnswerDetail.getString("resourcePath")
						+ URLEncoder.encode(fileName, "UTF-8").replace("+", "%20");
				logger.info("resourcePath =  " + resourcePath);
				HttpGet httpGet = new HttpGet(resourcePath);

				// Set auth token in the header for file fetching
				httpGet.addHeader("Accept", "application/json, text/javascript, */*; q=0.01");
				httpGet.addHeader("Accept-Charset", "UTF-8");
				httpGet.addHeader("Connection", "keep-alive");
				String authToken = (String) request.getSession(true).getAttribute("authToken");
				httpGet.addHeader("kmauthtoken", authToken);

				HttpClient httpClient = HttpClientBuilder.create().build();
				HttpResponse fileResponse = httpClient.execute(httpGet);
				HttpEntity entity = fileResponse.getEntity();
				logger.info("File Get Response status code " + fileResponse.getStatusLine().getStatusCode());
				if (fileResponse != null && fileResponse.getStatusLine().getStatusCode() != 404 && entity != null) {
					long len = entity.getContentLength();
					InputStream inputStream = entity.getContent();
					response.setCharacterEncoding("UTF-8");
					response.setContentLength((int) len);
					response.setHeader("Content-Disposition", "inline;filename=\"" + fileName + "\"");
					if ("TEXT".equals(fileType) || "TXT".equals(fileType)) {
						response.setContentType("text/plain");
					} else if ("PDF".equals(fileType)) {
						response.setContentType("application/pdf");
					} else if ("MS-WORD".equals(fileType) || "DOC".equals(fileType)) {
						response.setContentType("application/msword");
					} else if ("XLS".equals(fileType) || "MS-EXCEL".equals(fileType)) {
						response.setContentType("application/vnd.ms-excel");
					} else if ("MS-POWERPOINT".equals(fileType)) {
						response.setContentType("application/ppt");
					} else if ("HTML".equals(fileType)) {
						response.setContentType("application/html");
					} else if ("XML".equals(fileType)) {
						response.setContentType("application/xml");
					} else if ("ZIP".equals(fileType)) {
						response.setContentType("application/zip");
					}
					logger.info("Reading the input stream for file");
					OutputStream outputStream = response.getOutputStream();
					byte[] byteBuffer = new byte[4096];
					int length;
					while ((length = inputStream.read(byteBuffer)) > 0) {
						outputStream.write(byteBuffer, 0, length);
					}
					inputStream.close();
					outputStream.flush();
					outputStream.close();
					logger.info("Reading the input stream for file - Complete");
				} else {
					// flag it as a error
					logger.error("File Get Response :" + EntityUtils.toString(entity, "UTF-8"));
					error.put("error", labelsBundle.getString("AnswerView.FileDownloadErrorTitle"));
					error.put("message", labelsBundle.getString("AnswerView.FileDownloadErrorMessage"));
				}
				return SKIP_BODY;
			} else {
				logger.info("Default block...");
				// open external attachments and URLs
				input.put("hideHeaderHtml", "false");
				input.put("title", "External Content");
				answerDetail = this.getHighlightData(request, response, transctionId, apiSession);
				String externalHTML = "";
				String resourceUrl = "";
				try {
					JSONObject jsonAnswerDetail = new JSONObject();
					if(null!=answerDetail)
					{
						jsonAnswerDetail = new JSONObject(answerDetail);
					}
					String HttpPassThrough = "";
					String urlInJson = "";
					if(null!=jsonAnswerDetail)
					{
						try
						{
							if(null!=jsonAnswerDetail.get("HttpPassThrough"))
							{
								HttpPassThrough  = jsonAnswerDetail.getString("HttpPassThrough");
							}
						}catch(Exception e){/* Do Nothing for thi exception.*/}
						try
						{
							if(null!=jsonAnswerDetail.get("url"))
							{
								urlInJson = jsonAnswerDetail.getString("url");
							}
						}catch(Exception e){/* Do Nothing for thi exception.*/}
					}
					if ("HTML".equals(fileType)) {
						externalHTML = HttpPassThrough;
						input.put("fileType", "HighlightedHTML");
					} else if ("PDF".equals(fileType) || "TEXT".equals(fileType) || "TXT".equals(fileType)) {
						String highlightString = urlInJson.replace(url, "");
						input.put("highlightString", highlightString);
						input.put("fileType", fileType);
					} else {
						if(null!=urlInJson && !"".equals(urlInJson))
						{
							response.sendRedirect(urlInJson);
						}
					}
					resourceUrl = url;
					input.put("isHighlighted", "true");
				} catch (Exception e) {
					logger.error(
							"Highlighted content could not be fetched for the answer requested. Trying to fetch content without highlighting",
							e);
					input.put("viewableAttachment", "true");
					if ("HTML".equals(fileType) || "PDF".equals(fileType) || "TEXT".equals(fileType)) {
						input.put("fileType", fileType);
						resourceUrl = url;
					} else {
						response.sendRedirect(resourceUrl = url);
					}
				}
				input.put("httpPassThrough", externalHTML);
				input.put("resourceUrl", resourceUrl);
				input.put("attachmentUrl", resourceUrl);
			}
			logger.debug("Setting in the input values for display");
			logger.info("is Answer/Document linked?" + this.getAnswerLinkStatus());
			input.put("answerLinkStatus", this.getAnswerLinkStatus());
			input.put("showCaseLinks", this.isShowCaseLinks());
			input.put("showAddLink", this.isShowAddLink());
			input.put("showAddText", this.isShowAddText());
			input.put("answerID", this.getAnswerId());
			input.put("widgetID", widgetId);
			
			
			/*
			 * Recommend Change Properties
			 */
			input.put("recommendChangeLabel", labelsBundle.getString("AnswerView.RecommendChangeLabel"));
			input.put("submitButtonLabel", labelsBundle.getString("AnswerView.submitButtonLabel"));
			input.put("cancelButtonLabel", labelsBundle.getString("AnswerView.cancelButtonLabel"));
			input.put("submittingMessageLabel", labelsBundle.getString("AnswerView.submittingMessageLabel"));
			input.put("titleLabel", labelsBundle.getString("AnswerView.titleLabel"));
			input.put("detailsLabel", labelsBundle.getString("AnswerView.detailsLabel"));
			input.put("caseNumberLabel", labelsBundle.getString("AnswerView.caseNumberLabel"));
			input.put("priorityLabel", labelsBundle.getString("AnswerView.priorityLabel"));
			input.put("selectPriorityLabel", labelsBundle.getString("AnswerView.selectPriorityLabel"));
			input.put("answerId", request.getParameter("answerId"));
			
			String messageID = getWidgetInstanceId("messageDiv");
			
			String caseNumber="";
			if(null!=request.getSession().getAttribute("caseID"))
			{
				caseNumber = (String)request.getSession().getAttribute("caseID");
			}
			
			// create priorotyTypesData
			JSONArray priorityTypes=  new JSONArray();
			JSONObject obj = new JSONObject();
			obj.put("label", "Low");
			obj.put("value", "LOW");
			priorityTypes.put(obj);
			obj = new JSONObject();
			obj.put("label", "Medium");
			obj.put("value", "MEDIUM");
			priorityTypes.put(obj);
			obj = new JSONObject();
			obj.put("label", "High");
			obj.put("value", "HIGH");
			priorityTypes.put(obj);
			obj = null;
			
			JsonSequence js = new JsonSequence(priorityTypes);
			input.put("priorityTypes", js);
			input.put("messageID", messageID);
			input.put("caseNumber",caseNumber);
			input.put("caseIdFromParam", caseNumber);
			
			String userAgent = request.getHeader("User-Agent");
			logger.debug("userAgent = " + userAgent);
			if (userAgent.indexOf("MSIE") != -1 && "PDF".equals(fileType)) {
				input.put("showInfo", "true");
			}
			if (!input.containsKey("isHighlighted"))
				input.put("isHighlighted", "false");
			if (!input.containsKey("viewableAttachment"))
				input.put("viewableAttachment", "false");

			StringBuffer answerHTML = new StringBuffer("");
			/*
			 * JS Files for recommend change
			 */
			String collapseWidgetJsPath = getWidgetJsPath("collapse/collapse.js");
			if (!isFileAdded("message/message.js"))
				answerHTML = answerHTML.append("<script src=\"" + getWidgetJsPath("messages/message.js") + "\"></script>");
			if (!isFileAdded("collapse/collapse.js"))
				answerHTML = answerHTML.append("<script src=\"" + collapseWidgetJsPath + "\"></script>");
			/* ------------------ ends here ---------------- */
			String templateHtml = "";
			if (error.containsKey("message")) {
				templateHtml = getTemplateHtml(error, "answerError");
			} else {
				templateHtml = getTemplateHtml(input, this.getServerSideTemplate());
			}

			String resourceHtml = getCommonResourceHtml();
			String externalResourceHtml = getExternalResourceHtml();
			if (resourceHtml != null && this.getAnswerId() != null) {
				answerHTML = answerHTML.append(resourceHtml);
			}
			if (externalResourceHtml != null && this.getAnswerId() == null) {
				answerHTML = answerHTML.append(externalResourceHtml);
			}
			// print the input map
			logger.debug("---------------------- Input values----------------------");
			Iterator<Entry<String, Object>> it = input.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<?, ?> object = it.next();
				logger.debug("Key/value =  " + object.getKey() + ":" + object.getValue());
			}
			logger.debug("---------------------- Input values----------------------");
			answerHTML.append(templateHtml);
			
			
			if (!isFileAdded("answerView/answerView.js"))
				answerHTML = answerHTML.append("<script src=\"" + widgetJsPath + "\"></script>");
			answerHTML = answerHTML.append("<script>$(\"#").append(widgetId)
					.append("\").answerView({ \"pubSub\": ps, \"incidentID\":\"").append(incidentID)
					.append("\",\"answerLinkStatus\":\"").append(this.getAnswerLinkStatus())
					.append("\",\"showCaseLinks\":").append(this.isShowCaseLinks()).append(",\"showAddLink\":")
					.append(this.isShowAddLink()).append(",\"showAddText\":").append(this.isShowAddText())
					.append(",\"label_confirm_remove\":").append("\"").append(this.label_unlink_confirm).append("\"")
					.append(",\"widgetId\":\"").append(widgetId).append("\"}); </script>");


			
			logger.debug("answerHTML = " + answerHTML);
			out.write(answerHTML.toString());
		} catch (JSONException ex) {
			logger.error("JSONException in AnswerViewTag.doStartTag: ", ex);
		} catch (IOException ex) {
			logger.error("IOException in AnswerViewTag.doStartTag: ", ex);
		}
		return SKIP_BODY;
	}

}
