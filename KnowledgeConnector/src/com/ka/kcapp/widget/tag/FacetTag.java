/**
 * Copyright (c) 2015, Oracle Corporation and/or its affiliates. All rights reserved.
 *
 * The sample code in this document or accessed through this document is not certified or
 * supported by Oracle. It is intended for educational or testing purposes only. Use of this
 * sample code implies acceptance of the License Agreement
 * (http://www.oracle.com/technetwork/licenses/standard-license-152015.html).
 *
 * File: FacetTag.java
 * Abstract: Class to instantiate the Facet widget
 * Version: 1.0
 */

package com.ka.kcapp.widget.tag;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ka.kcapp.controller.SearchAPIController;
import com.ka.kcapp.util.SearchMapper;
import com.ka.kcapp.widget.apiUtility.OKAPIUtility;
import com.ka.kcapp.widget.common.JsonSequence;

/**
 * Class to instantiate the Facet widget
 * 
 * @author Prashant
 */

public class FacetTag extends BaseTag {

	private static final long serialVersionUID = -8133350043826229973L;

	final static Logger logger = Logger.getLogger(FacetTag.class);

	public FacetTag() {
		super();
	}

	private String maxSubFacetSize = "5";
	private String titleLabel = labelsBundle.getString("FacetTag.titleLabel");
	private String clearFilterLabel = labelsBundle.getString("FacetTag.clearFilterLabel");
	private String clearFilterScreenReaderLabel = labelsBundle.getString("FacetTag.clearFilterScreenReaderLabel");
	private String moreFilterLabel = labelsBundle.getString("FacetTag.moreFilterLabel");
	private String moreScreenReaderLabel = labelsBundle.getString("FacetTag.moreScreenReaderLabel");
	private String activeFilterScreenreaderLabel = labelsBundle.getString("FacetTag.activeFilterScreenreaderLabel");
	private String searchText = "";
	private String serverSideTemplate = "facet";
	private String clientSideTemplate = "facetLink";

	public String getMaxSubFacetSize() {
		return maxSubFacetSize;
	}

	public void setMaxSubFacetSize(String maxSubFacetSize) {
		this.maxSubFacetSize = maxSubFacetSize;
	}

	public String getTitleLabel() {
		return titleLabel;
	}

	public void setTitleLabel(String titleLabel) {
		this.titleLabel = titleLabel;
	}

	public String getClearFilterLabel() {
		return clearFilterLabel;
	}

	public void setClearFilterLabel(String clearFilterLabel) {
		this.clearFilterLabel = clearFilterLabel;
	}

	public String getMoreFilterLabel() {
		return moreFilterLabel;
	}

	public void setMoreFilterLabel(String moreFilterLabel) {
		this.moreFilterLabel = moreFilterLabel;
	}

	public String getClearFilterScreenReaderLabel() {
		return clearFilterScreenReaderLabel;
	}

	public void setClearFilterScreenReaderLabel(String clearFilterScreenReaderLabel) {
		this.clearFilterScreenReaderLabel = clearFilterScreenReaderLabel;
	}

	public String getMoreScreenReaderLabel() {
		return moreScreenReaderLabel;
	}

	public void setMoreScreenReaderLabel(String moreScreenReaderLabel) {
		this.moreScreenReaderLabel = moreScreenReaderLabel;
	}

	public String getActiveFilterScreenreaderLabel() {
		return activeFilterScreenreaderLabel;
	}

	public void setActiveFilterScreenreaderLabel(String activeFilterScreenreaderLabel) {
		this.activeFilterScreenreaderLabel = activeFilterScreenreaderLabel;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
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

	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();
			HttpSession session = request.getSession(true);

			String searchList = (String) request.getAttribute("searchList");
			String searchText = this.getSearchText().equalsIgnoreCase("") ? request.getParameter("kw")
					: this.getSearchText();
			String searchId = request.getParameter("s_id");
			String widgetJsPath = getWidgetJsPath("facet/facet.js");
			String widgetId = getWidgetInstanceId("facet");
			String commonHtml = getCommonResourceHtml();
			StringBuilder widgetHtml = new StringBuilder();

			JSONArray jsonArray = null;
			String widgetClass = "Content";
			if (logger.isDebugEnabled())
				logger.debug("FacetTag: 1");
			// resetting session
			JSONObject searchFilters = null;
			if (session.getAttribute("search_filters_saved") != null) {
				searchFilters = (JSONObject) session.getAttribute("search_filters_saved");
				if (logger.isDebugEnabled())
					logger.debug("facettag testing " + searchFilters.getString("incidentID") + " = "
							+ request.getParameter("caseID"));
				if (logger.isDebugEnabled())
					logger.debug("facettag testing " + URLDecoder.decode(searchFilters.getString("question"), "UTF-8")
							+ " = " + request.getParameter("kw"));
				if (request.getParameter("caseID") != null
						&& (!searchFilters.getString("incidentID").equals(request.getParameter("caseID"))
								|| !URLDecoder.decode(searchFilters.getString("question"), "UTF-8")
										.equals(request.getParameter("kw"))))
					session.removeAttribute("search_filters_saved");
			}

			if (session.getAttribute("search_filters_saved") != null) {
				if (logger.isDebugEnabled())
					logger.debug("FacetTag: 2");
				searchFilters = (JSONObject) session.getAttribute("search_filters_saved");
				String searchURL = "";
				searchURL = SearchMapper.getRequestUrl(getResourceBundle().getString("OKCS_SRCH_API_URL"),
						searchFilters);
				if (logger.isDebugEnabled())
					logger.debug("FacetTag searchURL = " + searchURL);
				JSONObject postData = SearchAPIController.getPostData(searchFilters, session);
				if (BaseTag.getResourceBundle().containsKey("SEARCH_RESULT_LOCALES"))
					postData.put("resultLocales", BaseTag.getResourceBundle().getString("SEARCH_RESULT_LOCALES"));
				String result = OKAPIUtility.callRESTAPI(request, response, searchURL, "POST", postData.toString());
				searchList = SearchMapper.getSearchResult(result, searchId, 0, request.getParameter("caseID"));
				request.setAttribute("searchList", searchList);
				JSONObject searchListJson = new JSONObject(searchList);
				jsonArray = searchListJson.getJSONObject("results").getJSONArray("facets");
				if (jsonArray != null && jsonArray.length() == 0)
					widgetClass += " Hidden";
				// session.removeAttribute("search_filters_saved");

			} else if (searchId != null) {
				if (logger.isDebugEnabled())
					logger.debug("FacetTag: 3");
				searchFilters = new JSONObject((String) session.getAttribute(searchId));
				String searchURL = getResourceBundle().getString("OKCS_SRCH_API_URL")
						.concat("search/pagination?purposeName=ANSWER&pageDirection=current").concat("&pageNumber=")
						.concat(searchFilters.getString("page")).concat("&priorTransactionId=")
						.concat(searchFilters.getString("transactionId"));
				if (logger.isDebugEnabled())
					logger.debug("FacetTag searchURL = " + searchURL);
				JSONObject payload = new JSONObject();
				payload.put("session", searchFilters.getString("apiSession"));
				payload.put("transactionId", searchFilters.getString("transactionId"));
				payload.put("requestSource", "IM");
				payload.put("uiMode", "paging");
				if (BaseTag.getResourceBundle().containsKey("SEARCH_RESULT_LOCALES"))
					payload.put("resultLocales", BaseTag.getResourceBundle().getString("SEARCH_RESULT_LOCALES"));
				String result = OKAPIUtility.callRESTAPI(request, response, searchURL, "POST", payload.toString());
				searchList = SearchMapper.getSearchResult(result, searchId, 0, request.getParameter("caseID"));
				request.setAttribute("searchList", searchList);

				JSONObject searchListJson = new JSONObject(searchList);
				jsonArray = searchListJson.getJSONObject("results").getJSONArray("facets");
				if (jsonArray != null && jsonArray.length() == 0)
					widgetClass += " Hidden";

			} else if (searchText != null && !searchText.equalsIgnoreCase("")) {
				if (logger.isDebugEnabled())
					logger.debug("FacetTag: 4");
				int transactionId = getDefaultNumber(1, Integer.MAX_VALUE);
				String searchURL = getResourceBundle().getString("OKCS_SRCH_API_URL")
						.concat("search/question?question=") + URLEncoder.encode(searchText, "UTF-8") + "&startOver=n";
				JSONObject payload = new JSONObject();
				payload.put("locale", getInterfaceLocale());
				payload.put("session", "");
				payload.put("transactionId", transactionId);
				if (BaseTag.getResourceBundle().containsKey("SEARCH_RESULT_LOCALES"))
					payload.put("resultLocales", BaseTag.getResourceBundle().getString("SEARCH_RESULT_LOCALES"));
				if (searchList == null || searchList.equalsIgnoreCase("")) {
					searchList = SearchMapper.getSearchResult(
							OKAPIUtility.callRESTAPI(request, response, searchURL, "POST", payload.toString()), null, 0,
							request.getParameter("caseID"));
					request.setAttribute("searchList", searchList);
				}
				JSONObject searchListJson = new JSONObject(searchList);
				jsonArray = searchListJson.getJSONObject("results").getJSONArray("facets");
				if (jsonArray != null && jsonArray.length() == 0)
					widgetClass += " Hidden";
			} else {
				if (logger.isDebugEnabled())
					logger.debug("FacetTag: 5");
				widgetClass += " Hidden";
			}

			Map<String, Object> input = new HashMap<String, Object>();
			input.put("widgetId", widgetId);
			input.put("widgetClass", widgetClass);
			input.put("titleLabel", titleLabel);
			input.put("clearFilterScreenReaderLabel", clearFilterScreenReaderLabel);
			input.put("clearFilterLabel", clearFilterLabel);
			input.put("moreFilterLabel", moreFilterLabel);
			input.put("moreScreenReaderLabel", moreScreenReaderLabel);
			input.put("facetList", jsonArray == null ? null : new JsonSequence(jsonArray));
			input.put("maxSubFacetSize", maxSubFacetSize);
			widgetHtml = widgetHtml.append(getTemplateHtml(input, this.getServerSideTemplate()));

			if (!commonHtml.isEmpty())
				widgetHtml = widgetHtml.append(commonHtml);

			if (!isFileAdded("facet/facet.js"))
				widgetHtml = widgetHtml.append("<script src=\"" + widgetJsPath + "\"></script>");

			String facets = jsonArray == null ? "{}" : jsonArray.toString();
			widgetHtml = widgetHtml.append("<script>$(\"#" + widgetId + "\").facet({ \"pubSub\": ps,\"widgetId\":\""
					+ widgetId + "\",\"label_filter\":\"" + titleLabel + "\",\"label_clear_screenreader\":\""
					+ clearFilterScreenReaderLabel + "\",\"label_clear\":\"" + clearFilterLabel
					+ "\",\"label_active_filter_screenreader\":\"" + activeFilterScreenreaderLabel
					+ "\",\"label_more\":\"" + moreFilterLabel + "\",\"label_more_screenReader\":\""
					+ moreScreenReaderLabel + "\",\"max_sub_facet_size\":" + maxSubFacetSize
					+ ", \"client_side_template\":\"" + clientSideTemplate + "\",\"facets\":\'" + facets
					+ "\'}); </script>");
			out.println(widgetHtml);
		} catch (Exception ex) {
			logger.error("Error in FacetTag.doStartTag: ", ex);
		}
		return SKIP_BODY;
	}
}