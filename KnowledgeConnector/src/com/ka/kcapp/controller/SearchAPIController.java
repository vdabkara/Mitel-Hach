/**
 * Copyright (c) 2015, Oracle Corporation and/or its affiliates. All rights reserved.
 *
 * The sample code in this document or accessed through this document is not certified or
 * supported by Oracle. It is intended for educational or testing purposes only. Use of this
 * sample code implies acceptance of the License Agreement
 * (http://www.oracle.com/technetwork/licenses/standard-license-152015.html).
 *
 * File: SearchAPIController.java
 * Abstract: Controller used to process all search API related AJAX calls
 * Version: 1.0
 */

package com.ka.kcapp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.ka.kcapp.util.SearchMapper;
import com.ka.kcapp.widget.apiUtility.OKAPIUtility;
import com.ka.kcapp.widget.tag.BaseTag;

/**
 * Controller used to process all Search AJAX calls
 */
public class SearchAPIController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	final static Logger logger = Logger.getLogger(SearchAPIController.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchAPIController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(
            HttpServletRequest request, HttpServletResponse response
        ) throws ServletException, IOException {

            ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.application");
            response.setContentType("application/json;");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            HttpSession session = request.getSession(true);
            String apiResponse = null;
            String searchBaseUrl = resourceBundle.getString("OKCS_SRCH_API_URL");
            boolean use_clientside_presentation = resourceBundle.containsKey("USE_CLIENTSIDE_PRESENTATION")? Boolean.getBoolean(resourceBundle.getString("OKCS_SRCH_API_URL")) :true;
            if (logger.isDebugEnabled()) logger.debug("USE_CLIENTSIDE_PRESENTATION: " + resourceBundle.getString("USE_CLIENTSIDE_PRESENTATION"));
            String apiUrl = "";
            try {
                StringBuilder buffer = new StringBuilder();
                BufferedReader reader = request.getReader();
                String line = null;
                while ((line = reader.readLine()) != null) buffer.append(line);
                JSONObject searchFilters = new JSONObject(buffer.toString());
                if (logger.isDebugEnabled()) logger.debug("Post data in SearchAPIController.doPost: " + searchFilters.toString());
                if (!use_clientside_presentation){
                   session.setAttribute("search_filters_saved", searchFilters);
                   searchFilters.append("search_filters_saved", true);
                   apiResponse = searchFilters.toString();
                }else{
                    JSONObject postData = getPostData(searchFilters, session);
                    apiUrl = getRequestUrl(searchBaseUrl, searchFilters);
                    if (logger.isDebugEnabled()) logger.debug("API Url in SearchAPIController.doPost : " + apiUrl);
                    if (!apiUrl.isEmpty()) {
                        if (BaseTag.getResourceBundle().containsKey("SEARCH_RESULT_LOCALES"))
                            postData.put("resultLocales", BaseTag.getResourceBundle().getString("SEARCH_RESULT_LOCALES"));
                        String responseString = OKAPIUtility.callRESTAPI(request, response, apiUrl, "POST", postData.toString());
                        if (!responseString.isEmpty()) {
                            JSONObject resultList = new JSONObject(responseString);
                            String searchId = searchFilters.getString("searchId");
                            resultList.put("searchId", searchId);
                            responseString = resultList.toString();
                            if (logger.isDebugEnabled()) logger.debug("Search Response JSON = \n" + responseString);
                            if (resultList != null) {
                                searchFilters.put("apiSession",
                                resultList.getString("session").replaceAll("\\r\\n", "\\\\r\\\\n"));
                                session.setAttribute(searchId, searchFilters.toString());
                            }
                            apiResponse = SearchMapper.getSearchResult(responseString, searchFilters.getString("searchId"),
                            Integer.parseInt(searchFilters.getString("truncateSize")),
                            searchFilters.getString("incidentID"));
                        }
                    }
                }
            } catch (JSONException ex) {
                logger.error("Error in SearchAPIController.doPost: ", ex);
            }
            out.write(apiResponse);
        }

	/**
	 * Method to construct the REST API request URL
	 * 
	 * @param searchBaseUrl
	 *            The base URL for all search requests.
	 * @param searchFilters
	 *            Set of parameters used to filter search results.
	 * @return String REST API request URL
	 * @throws JSONException
	 */
        private String getRequestUrl(String searchBaseUrl, JSONObject searchFilters) throws JSONException {
            String apiUrl = "";
            if (searchFilters.has("type")) {
                String requestType = searchFilters.getString("type");
                if (requestType.equalsIgnoreCase("search")) {
                    apiUrl = searchBaseUrl.concat("search/question?question=").concat(searchFilters.getString("question"));
                } else if (requestType.equalsIgnoreCase("facet")) {
                    apiUrl = 
                        searchBaseUrl
                        .concat("search/navigation?priorTransactionId=")
                        .concat(searchFilters.getString("priorTransactionID"))
                        .concat("&facet=")
                        .concat(searchFilters.getString("facet"))
                        .concat("&facetShowAll=false")
                    ;
                } else if (requestType.equalsIgnoreCase("page")) {
                    apiUrl = 
                        searchBaseUrl
                        .concat("search/pagination?purposeName=ANSWER&pageDirection=")
                        .concat(searchFilters.getString("direction"))
                        .concat("&pageNumber=")
                        .concat(searchFilters.getString("page"))
                        .concat("&priorTransactionId=")
                        .concat(searchFilters.getString("priorTransactionID"))
                    ;
                }
            }
            return apiUrl;
        }

	/**
	 * Method to construct the post parameters for the request
	 * 
	 * @param filters
	 *            Set of Parameters used to filter search results
	 * @param session
	 *            Httpsession
	 * @return JSONObject JSON Object containing the key value pairs of the post
	 *         data
	 * @throws JSONException
	 */
	public static JSONObject getPostData(JSONObject filters, HttpSession session) throws JSONException {
		ResourceBundle resourceBundle = OKAPIUtility.resourceBundle;
		JSONObject postData = new JSONObject();
		postData.put("session", filters.getString("session"));
		postData.put("transactionId", filters.getString("transactionId"));
		String locale = session.getAttribute("userLocale") == null ? resourceBundle.getString("INTERFACE_LOCALE")
				: (String) session.getAttribute("userLocale");
		postData.put("locale", locale);
		postData.put("requestSource", "IM");
		if (filters.getString("type").equals("page")) {
			postData.put("uiMode", "paging");
		}
		filters.put("searchState", postData);
		String searchId = "" + new Date().getTime();
		filters.put("searchId", searchId);
		session.setAttribute(searchId, filters.toString());
		return postData;
	}
}