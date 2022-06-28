/**
 * Copyright (c) 2015, Oracle Corporation and/or its affiliates. All rights reserved.
 *
 * The sample code in this document or accessed through this document is not certified or
 * supported by Oracle. It is intended for educational or testing purposes only. Use of this
 * sample code implies acceptance of the License Agreement
 * (http://www.oracle.com/technetwork/licenses/standard-license-152015.html).
 *
 * File: PaginationTag.java
 * Abstract: Tag to instantiate the Pagination widget 
 * Version: 1.0
 */

package com.ka.kcapp.widget.tag;

import java.net.URLEncoder;
import java.util.HashMap;
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

/**
 * Tag to instantiate the Pagination widget
 * 
 * @author Prashant
 */

public class PaginationTag extends BaseTag {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(PaginationTag.class);

	private String searchText = "";
	private String labelNext = labelsBundle.getString("PaginationTag.labelNext");
	private String labelPrevious = labelsBundle.getString("PaginationTag.labelPrevious");
	private String serverSideTemplate = "pagination";
	private String clientSideTemplate = "view";

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getLabelNext() {
		return labelNext;
	}

	public void setLabelNext(String labelNext) {
		this.labelNext = labelNext;
	}

	public String getLabelPrevious() {
		return labelPrevious;
	}

	public void setLabelPrevious(String labelPrevious) {
		this.labelPrevious = labelPrevious;
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

	public PaginationTag() {
		super();
	}

	public int doStartTag() throws JspException {
            try {
                JspWriter out = pageContext.getOut();
                HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
                HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();
                String searchList = (String) request.getAttribute("searchList");
                String searchText = this.getSearchText().equalsIgnoreCase("") ? request.getParameter("kw") : this.getSearchText();
                String widgetJsPath = getWidgetJsPath("pagination/pagination.js");
                String widgetId = getWidgetInstanceId("pagination");
                String commonHtml = getCommonResourceHtml();
                StringBuilder widgetHtml = new StringBuilder();
                int currentPage = 0, pageMore = 0;

                if (searchText != null && !searchText.equalsIgnoreCase("")) {
                    int transactionId = getDefaultNumber(1, Integer.MAX_VALUE);
                    String searchURL = getResourceBundle().getString("OKCS_SRCH_API_URL").concat("search/question?question=") + URLEncoder.encode(searchText, "UTF-8") + "&startOver=n";

                    JSONObject payload = new JSONObject();
                    payload.put("locale", getInterfaceLocale());
                    payload.put("session", "");
                    payload.put("transactionId", transactionId);
                    if (BaseTag.getResourceBundle().containsKey("SEARCH_RESULT_LOCALES"))
                        payload.put("resultLocales", BaseTag.getResourceBundle().getString("SEARCH_RESULT_LOCALES"));
                    if (logger.isDebugEnabled()){
                        logger.debug("----------PAGINATION TAG------------------");
                        logger.debug("Connecting to Search URL " + searchURL);
                        logger.debug("Transaction ID " + transactionId);
                        logger.debug("payload " + payload);
                        logger.debug("----------------------------");
                    }
                    if (searchList == null || searchList.equalsIgnoreCase("")) {
                        searchList = SearchMapper.getSearchResult(OKAPIUtility.callRESTAPI(request, response, searchURL, "POST", payload.toString()), null, 0, request.getParameter("caseID"));
                        request.setAttribute("searchList", searchList);
                    }
                    JSONObject searchResult = new JSONObject(searchList);
                    JSONArray results = new JSONArray(searchResult.getJSONObject("results").getString("results"));
                    if (results.length() > 0) {
                        currentPage = results.getJSONObject(0).getInt("pageNumber");
                        pageMore = results.getJSONObject(0).getInt("pageMore");
                    }
                }
                if (searchList != null && searchList.length() > 0) {
                        request.setAttribute("searchList", searchList);
                        JSONObject searchResult = new JSONObject(searchList);
                        JSONArray results = new JSONArray(searchResult.getJSONObject("results").getString("results"));
                        if (results.length() > 0) {
                                currentPage = results.getJSONObject(0).getInt("pageNumber");
                                pageMore = results.getJSONObject(0).getInt("pageMore");
                        }
                }

                Map<String, Object> input = new HashMap<String, Object>();
                input.put("widgetId", widgetId);
                input.put("labelNext", this.getLabelNext());
                input.put("labelPrevious", this.getLabelPrevious());
                input.put("pageMore", pageMore);
                input.put("currentPage", currentPage);
                widgetHtml = widgetHtml.append(getTemplateHtml(input, this.getServerSideTemplate()));

                if (!commonHtml.isEmpty())
                        widgetHtml = widgetHtml.append(commonHtml);

                if (!isFileAdded("pagination/pagination.js"))
                        widgetHtml = widgetHtml.append("<script src=\"" + widgetJsPath + "\"></script>");

                widgetHtml = widgetHtml
                    .append("<script>$(\"#")
                    .append(widgetId)
                    .append("\").pagination({\"pubSub\": ps, \"widgetId\":\"")
                    .append(widgetId)
                    .append("\", \"label_next\":\"")
                    .append(this.getLabelNext())
                    .append("\", \"label_previous\":\"")
                    .append(this.getLabelPrevious())
                    .append("\", \"client_side_template\":\"")
                    .append(clientSideTemplate)
                    .append("\"")
                    .append(", \"currentPage\":\"")
                    .append(currentPage)                    
                    .append("\"")
                    .append("}); </script>")
                ;
                logger.debug("Pagination Tag widget HTML " + widgetHtml);
                out.println(widgetHtml);

            } catch (Exception ex) {
                    logger.error("Error in PaginationTag.doStartTag: ", ex);
            }
            return SKIP_BODY;
	}

}
