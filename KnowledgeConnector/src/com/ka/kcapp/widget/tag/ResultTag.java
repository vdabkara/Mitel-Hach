/**
 * Copyright (c) 2015, Oracle Corporation and/or its affiliates. All rights reserved.
 *
 * The sample code in this document or accessed through this document is not certified or
 * supported by Oracle. It is intended for educational or testing purposes only. Use of this
 * sample code implies acceptance of the License Agreement
 * (http://www.oracle.com/technetwork/licenses/standard-license-152015.html).
 *
 * File: ResultTag.java
 * Abstract: Tag to instantiate the Search Result widget
 * Version: 1.0
 */

package com.ka.kcapp.widget.tag;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ka.kcapp.util.SearchMapper;
import com.ka.kcapp.widget.apiUtility.OKAPIUtility;
import com.ka.kcapp.widget.common.JsonSequence;
import com.ka.kcapp.widget.common.LinkedItems;

/**
 * Tag to instantiate the Search Result widget
 * 
 * @author Prashant
 */

public class ResultTag extends BaseTag {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(ResultTag.class);

	private String searchText = "";
	private String target = "_self";
	private String truncateSize = "200";
	private boolean hideWhenNoResults = true;
	private String labelResults = labelsBundle.getString("ResultTag.labelResults");
	private String labelNoResults = labelsBundle.getString("ResultTag.labelNoResults");
	private String serverSideTemplate = "searchResult";
	private String clientSideTemplate = "view";
	private String incidentId = "";
	private String incidentDesc = "";
	private boolean showCaseLinks = false;
	private boolean showAddLink = false;
	private boolean showAddText = false;

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getIncidentId() {
		return incidentId;
	}

	public void setIncidentId(String incidentId) {
		this.incidentId = incidentId;
	}

	public String getIncidentDesc() {
		return incidentDesc;
	}

	public void setIncidentDesc(String incidentDesc) {
		this.incidentDesc = incidentDesc;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getTruncateSize() {
		return truncateSize;
	}

	public void setTruncateSize(String truncateSize) {
		this.truncateSize = truncateSize;
	}

	public boolean isHideWhenNoResults() {
		return hideWhenNoResults;
	}

	public void setHideWhenNoResults(boolean hideWhenNoResults) {
		this.hideWhenNoResults = hideWhenNoResults;
	}

	public String getLabelNoResults() {
		return labelNoResults;
	}

	public void setLabelNoResults(String labelNoResults) {
		this.labelNoResults = labelNoResults;
	}

	public String getLabelResults() {
		return labelResults;
	}

	public void setLabelResults(String labelResults) {
		this.labelResults = labelResults;
	}

	public String getServerSideTemplate() {
		return serverSideTemplate;
	}

	public void setServerSideTemplate(String serverSideTemplate) {
		this.serverSideTemplate = serverSideTemplate;
	}

	public String getClientSideTemplate() {
		return clientSideTemplate;
	}

	public void setClientSideTemplate(String clientSideTemplate) {
		this.clientSideTemplate = clientSideTemplate;
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

	public ResultTag() {
		super();
	}

	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();
			String searchText = this.getSearchText().equalsIgnoreCase("") ? request.getParameter("kw")
					: this.getSearchText();
			String searchList = (String) request.getAttribute("searchList");
			String widgetJsPath = getWidgetJsPath("searchResult/searchResult.js");
			String widgetId = getWidgetInstanceId("searchResult");
			String commonHtml = getCommonResourceHtml();
			StringBuilder widgetHtml = new StringBuilder();
			List<String> linkedAnswers = new ArrayList<String>();
			// added to fix linking issue
			List<String> linkedDocuments = new ArrayList<String>();

			if (request.getSession().getAttribute(OKAPIUtility.USER_TOKEN) != null && this.isShowCaseLinks()
					&& request.getParameter("caseID") != null && request.getParameter("caseID") != "") {
				this.setIncidentId(request.getParameter("caseID"));
				this.setIncidentDesc(request.getParameter("kw"));
				JSONArray linkedItems = new JSONArray(
						LinkedItems.getLinkedAnswersByIncidentID(this.getIncidentId(), request, response));
				logger.debug("ResultTag linked items = " + linkedItems.toString());
				if (linkedItems.length() != 0) {
					for (int i = 0; i < linkedItems.length(); i++) {
						linkedAnswers.add(linkedItems.getJSONObject(i).getJSONObject("document").getString("answerId"));
						linkedDocuments
								.add(linkedItems.getJSONObject(i).getJSONObject("document").getString("documentId"));
						logger.info("Linked Answers [DOC ID : ANSWER ID ] "
								+ linkedItems.getJSONObject(i).getJSONObject("document").getString("documentId") + ":"
								+ linkedItems.getJSONObject(i).getJSONObject("document").getString("answerId"));
					}
				}
			} else {
				this.setShowCaseLinks(false);
			}
			int transactionId = 1, priorTransactionId = 1;
			String searchSssion = "";
			JSONArray jsonArray = null;
			if (searchText != null && !searchText.equalsIgnoreCase("")) {
				transactionId = getDefaultNumber(1, Integer.MAX_VALUE);
				priorTransactionId = transactionId;
				String searchURL = getResourceBundle().getString("OKCS_SRCH_API_URL")
						.concat("search/question?question=") + URLEncoder.encode(searchText, "UTF-8") + "&startOver=n";
				if (logger.isDebugEnabled()) {
					logger.debug("----------------------------");
					logger.debug("Connecting to Search URL " + searchURL);
					logger.debug("Transaction ID " + transactionId);
					logger.debug("priorTransactionId ID " + priorTransactionId);
				}
				JSONObject payload = new JSONObject();
				payload.put("locale", getInterfaceLocale());
				payload.put("session", "");
				payload.put("transactionId", transactionId);
				payload.put("requestSource", "IM");
				if (BaseTag.getResourceBundle().containsKey("SEARCH_RESULT_LOCALES"))
					payload.put("resultLocales", BaseTag.getResourceBundle().getString("SEARCH_RESULT_LOCALES"));
				logger.debug("payload " + payload);
				logger.debug("----------------------------");
				if (searchList == null || searchList.equalsIgnoreCase("")) {
					searchList = SearchMapper.getSearchResult(
							OKAPIUtility.callRESTAPI(request, response, searchURL, "POST", payload.toString()), null,
							Integer.parseInt(truncateSize), this.getIncidentId());
					request.setAttribute("searchList", searchList);
				}
			}
			if (searchList != null && searchList.length() > 0) {
				JSONObject searchResult = new JSONObject(searchList);
				searchSssion = searchResult.getString("session");
				transactionId = searchResult.getInt("transactionId") + 1;
				priorTransactionId = searchResult.getInt("priorTransactionId");
				
				JSONArray resultList = new JSONArray(searchResult.getJSONObject("results").getString("results"));
				if (resultList.length() > 0) {
					jsonArray = resultList.getJSONObject(0).getJSONArray("resultItems");
					logger.debug("Search Results(ResultList) \n " + resultList.toString());
				}
			}
			logger.debug(">>>> incident id .1) " + this.getIncidentId());

			Map<String, Object> input = new HashMap<String, Object>();
			input.put("widgetId", widgetId);
			input.put("labelNoResults", this.getLabelNoResults());
			input.put("labelResults", this.getLabelResults());
			input.put("isHideWhenNoResults", this.isHideWhenNoResults());
			input.put("target", this.getTarget());
			input.put("searchText", searchText);
			input.put("resultList", jsonArray == null ? null : new JsonSequence(jsonArray));
			input.put("showCaseLinks", this.isShowCaseLinks());
			input.put("showAddLink", this.isShowAddLink());
			input.put("showAddText", this.isShowAddText());
			input.put("linkedAnswers", linkedAnswers);
			input.put("linkedDocuments", linkedDocuments);
			logger.debug(">>>> incident id .2) " + this.getIncidentId());
			widgetHtml = widgetHtml.append(getTemplateHtml(input, this.getServerSideTemplate()));

			if (!commonHtml.isEmpty())
				widgetHtml = widgetHtml.append(commonHtml);

			if (!isFileAdded("searchResult/searchResult.js"))
				widgetHtml = widgetHtml.append("<script src=\"" + widgetJsPath + "\"></script>");
			logger.debug(">>>> incident id 1) " + this.getIncidentId());

			widgetHtml = widgetHtml.append("<script>$(\"#").append(widgetId)
					.append("\").searchResult({ \"pubSub\": ps, \"searchSession\":\"").append(searchSssion)
					.append("\",\"transactionID\":").append(transactionId).append(",\"priorTransactionID\":")
					.append(priorTransactionId).append(",\"searchText\":\"")
					.append(searchText.replaceAll("\"", "\\\\\"")).append("\",\"showCaseLinks\":")
					.append(this.isShowCaseLinks()).append(",\"showAddLink\":").append(this.isShowAddLink())
					.append(",\"showAddText\":").append(this.isShowAddText()).append(",\"incidentID\":\"")
					.append(this.getIncidentId()).append("\",\"incidentDesc\":\"")
					.append(this.getIncidentDesc().replaceAll("\"", "\\\\\"")).append("\",\"linkedAnswers\":\"")
					.append(linkedAnswers.toString()).append("\",\"linkedDocuments\":\"")
					.append(linkedDocuments.toString()).append("\",\"widgetId\":\"").append(widgetId)
					.append("\",\"label_results\":\"").append(labelResults).append("\", \"hide_when_no_results\":")
					.append(hideWhenNoResults).append(", \"label_no_results\":\"")
					.append(labelNoResults + "\", \"target\":\"").append(target).append("\", \"truncate_size\":")
					.append(truncateSize).append(", \"client_side_template\":\"").append(clientSideTemplate)
					.append("\"}); </script>");
			logger.debug("Search Results/ResultList \n " + widgetHtml.toString());
			out.println(widgetHtml);
		} catch (Exception ex) {
			logger.error("Error in ResultTag.doStartTag: ", ex);
		}
		return SKIP_BODY;
	}
}