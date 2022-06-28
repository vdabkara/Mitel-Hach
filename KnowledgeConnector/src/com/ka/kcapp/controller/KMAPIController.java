/**
 * Copyright (c) 2015, Oracle Corporation and/or its affiliates. All rights reserved.
 *
 * The sample code in this document or accessed through this document is not certified or
 * supported by Oracle. It is intended for educational or testing purposes only. Use of this
 * sample code implies acceptance of the License Agreement
 * (http://www.oracle.com/technetwork/licenses/standard-license-152015.html).
 *
 * File: KMAPIController.java
 * Abstract: Controller used to process all KM API related AJAX calls
 * Version: 1.0
 */

package com.ka.kcapp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.ka.kcapp.widget.common.LinkedItems;
import com.ka.kcapp.widget.common.Navigation;

/**
 * Controller used to process all IM AJAX calls
 * 
 * @author akshenoy
 */
public class KMAPIController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	final static Logger logger = Logger.getLogger(KMAPIController.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public KMAPIController() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		try {
			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = request.getReader();
			String line = null;
			while ((line = reader.readLine()) != null)
				buffer.append(line);
			JSONObject postData = new JSONObject(buffer.toString());
			if (logger.isDebugEnabled())
				logger.debug("Post data in KMAPIController.doPost: " + postData.toString());
			switch (postData.getString("action")) {
			case "addLink":
				out.write(LinkedItems.addLinkedDocument(request, response, postData));
				break;
			case "unlink":
				out.write(LinkedItems.removeLinkedDocument(request, response, postData));
				break;
			case "setNavigation":
				out.write(Navigation.setNavigationLocation(request, response, postData));
				break;
			// case "addText":
			// try{
			// IMArticleUtils articleUtils = new IMArticleUtils();
			// String responseString = LinkedItems.addLinkedDocument(request,
			// response, postData);
			// if (responseString != null && !"".equals(responseString)) {
			// JSONObject answerHtml = new JSONObject();
			// answerHtml.put("answerHtml",
			// articleUtils.getIMArticleHtml(request, response,
			// postData.getString("answerId")));
			// out.write(answerHtml.toString());
			// }
			// else {
			// throw new Exception("No response from API");
			// }
			// }catch(Exception e) {
			// out.write("{\"error\":\"" + e.getMessage() + "\"}");
			// }
			// break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to add submit content recommendations
	 * 
	 * @param request
	 *            Request object used to service recommend content request.
	 * @param response
	 *            Response object to capture the API response.
	 * @param postData
	 *            Post data containing information about recommendations.
	 * @return String API response to be sent back to page context.
	 * @throws Exception
	 */
	// protected void submitContentRecommendation(HttpServletRequest request,
	// HttpServletResponse response, JSONObject postData) throws Exception {
	// ResourceBundle resourceBundle =
	// ResourceBundle.getBundle("resources.application");
	// JSONObject input = this.getPostData(request, postData);
	// String urlStr = resourceBundle.getString("OKCS_IM_API_URL") +
	// "contentRecommendations";
	// OKAPIUtility.callRESTAPI(request, response, urlStr, "POST",
	// input.toString());
	// }

	/**
	 * Method to construct Post Data for the Link/Unlink API call.
	 * 
	 * @param request
	 *            Request object used to service link/unlink article request.
	 * @param answerID
	 *            Answer ID of the article being linked/unlinked.
	 * @param incidentID
	 *            Incident ID of the incident the article is being linked to or
	 *            unlinked from.
	 * @return String Post data to be sent to API.
	 * @throws Exception
	 */
	// protected JSONObject getPostData(HttpServletRequest request, JSONObject
	// filters) throws Exception {
	// JSONObject postData = new JSONObject();
	// if(filters.has("title")) {
	// if(filters.has("content")) {
	// String[] contentData = filters.getString("content").split("#");
	// postData.put("recordId", contentData[0]);
	// postData.put("answerId", contentData[1]);
	// postData.put("documentId", contentData[2]);
	// filters.put("content", postData);
	// }else {
	// String[] contentTypeData = filters.getString("contentType").split("-");
	// postData.put("recordId", contentTypeData[0]);
	// postData.put("referenceKey", contentTypeData[1]);
	// postData.put("name", contentTypeData[2]);
	// filters.put("contentType", postData);
	// }
	// System.out.print(filters.toString());
	// return filters;
	// }
	// else{
	// JSONObject incidentDetails =
	// (JSONObject)request.getSession().getAttribute(filters.getString("incidentId"));
	// incidentDetails.put("incidentId", filters.getString("incidentId"));
	// postData.put("incident", incidentDetails);
	// JSONObject document = new JSONObject();
	// document.put("title",filters.getString("answer_title"));
	// document.put("recordId",filters.getString("recordId"));
	// document.put("versionId",filters.getString("versionId"));
	// document.put("documentId",filters.getString("documentId"));
	// document.put("version",filters.getString("version"));
	// document.put("answerId",filters.getString("answerId"));
	// postData.put("documents",new JSONArray("["+document.toString()+"]"));
	// return postData;
	// }
	// }
}
