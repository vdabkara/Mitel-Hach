/**
 * Copyright (c) 2015, Oracle Corporation and/or its affiliates. All rights reserved.
 *
 * The sample code in this document or accessed through this document is not certified or
 * supported by Oracle. It is intended for educational or testing purposes only. Use of this
 * sample code implies acceptance of the License Agreement
 * (http://www.oracle.com/technetwork/licenses/standard-license-152015.html).
 *
 * File: OKAPIUtility.java
 * Abstract: API Utility Class to perform general API related functions.
 * Version: 1.0
 */

package com.ka.kcapp.widget.apiUtility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;

/**
 * API Utility Class to perform general API related functions.
 * 
 * @author Prashant Chaturvedi
 */
public class OKAPIUtility {

	final static Logger logger = Logger.getLogger(OKAPIUtility.class);

	public static final ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.application");
	public static final String GET = "GET";
	public static final String AUTH_TOKEN = "authToken";
	public static final String USER_TOKEN = "userToken";
	public static final String AUTH_END_POINT = "auth/integration/authorize";

	/**
	 * This method is used to get authToken which is required to call REST API.
	 * 
	 * @param request
	 *            HttpServletRequest Request object used to process
	 *            authentication requests
	 * @param response
	 *            HttpServletResponse Response object used to process
	 *            authentication response
	 * @return String authToken String containing authenticated API token
	 */
	public static String getKMAuthToken(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		String kmAuthToken = (String) session.getAttribute(AUTH_TOKEN);
		if (kmAuthToken == null) {
			String integrationTokenURL = resourceBundle.getString("OKCS_IM_API_URL").concat(AUTH_END_POINT);
			String authToken = "{\"siteName\":\"" + resourceBundle.getString("SITE_NAME") + "\", \"localeId\" : \""
					+ resourceBundle.getString("INTERFACE_LOCALE") + "\"}";
			session.setAttribute(AUTH_TOKEN, authToken);
			String payload = "{\"login\":\"" + resourceBundle.getString("OKCS_IU_USERNAME") + "\",\"password\":\""
					+ resourceBundle.getString("OKCS_IU_PASSWORD") + "\",\"siteName\":\""
					+ resourceBundle.getString("SITE_NAME") + "\"}";
			String integrationToken = callRESTAPI(request, response, integrationTokenURL, "POST", payload);
			try {
				JSONObject tokenJson = new JSONObject(integrationToken);
				integrationToken = tokenJson.get("authenticationToken").toString();
				if (session.getAttribute(USER_TOKEN) != null) {
					kmAuthToken = "{\"localeId\":\"" + resourceBundle.getString("INTERFACE_LOCALE")
							+ "\", \"interfaceId\" : \"" + resourceBundle.getString("INTERFACE_ID")
							+ "\", \"billableSessionId\" :\"" + resourceBundle.getString("BILLABLE_SESSION_ID")
							+ "\", \"knowledgeInteractionId\":\"" + resourceBundle.getString("BILLABLE_SESSION_ID")
							+ "\", \"appId\":\"" + resourceBundle.getString("APP_ID") + "\", \"siteName\":\""
							+ resourceBundle.getString("SITE_NAME") + "\", \"integrationUserToken\": \""
							+ integrationToken + "\",\"userToken\": \"" + session.getAttribute(USER_TOKEN) + "\"}";
				} else {
					kmAuthToken = "{\"localeId\":\"" + resourceBundle.getString("INTERFACE_LOCALE")
							+ "\", \"interfaceId\" : \"" + resourceBundle.getString("INTERFACE_ID")
							+ "\", \"billableSessionId\" :\"" + resourceBundle.getString("BILLABLE_SESSION_ID")
							+ "\", \"knowledgeInteractionId\":\"" + resourceBundle.getString("BILLABLE_SESSION_ID")
							+ "\", \"appId\":\"" + resourceBundle.getString("APP_ID") + "\", \"siteName\":\""
							+ resourceBundle.getString("SITE_NAME") + "\", \"integrationUserToken\": \""
							+ integrationToken + "\"}";
				}
			} catch (Exception ex) {
				logger.error("Error in OKAPIUtility.getKMAuthToken: ", ex);
				ex.printStackTrace();
			}
			session.setAttribute(AUTH_TOKEN, kmAuthToken);
			return kmAuthToken;
		}
		return kmAuthToken;
	}

	/**
	 * This method is used to call REST API.
	 * 
	 * @param request
	 *            HttpServletRequest Request object used to process API calls
	 * @param response
	 *            HttpServletResponse Response Object used to process API
	 *            response
	 * @param urlStr
	 *            REST API end point
	 * @param method
	 *            API request type. Possible values are GET and POST.
	 * @param input
	 *            Post JSON encoded form data to be sent as part of the request
	 * @return String API response
	 */
	public static String callRESTAPI(HttpServletRequest request, HttpServletResponse response, String urlStr,
			String method, String input) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("Calling the API method: URL " + urlStr);
				logger.debug("Calling the API method: method " + method);
				logger.debug("Calling the API method: input " + input);
			}
			boolean isTest = isTest(request);
			BufferedReader br;
			String authToken = (String) request.getSession(true).getAttribute(AUTH_TOKEN);
			StringBuilder res = new StringBuilder();
			if (!isTest) {
				authToken = OKAPIUtility.getKMAuthToken(request, response);
			} else {
				urlStr = getTestUrl(request, urlStr);
				authToken = null;
			}
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpResponse apiResponse = (method.equalsIgnoreCase("GET") ? httpGet(httpClient, urlStr, authToken)
					: httpPost(httpClient, urlStr, authToken, input));
			int statusCode = apiResponse.getStatusLine().getStatusCode();
			logger.debug("API Response status code  " + statusCode);
			if (statusCode == 200 || statusCode == 201) {
				br = new BufferedReader(
						new InputStreamReader(apiResponse.getEntity().getContent(), Charset.forName("UTF-8")));
				String line;
				while ((line = br.readLine()) != null)
					res.append(line);
				br.close();
			} else if (statusCode == 400) {
				JSONObject error_response = new JSONObject();
				error_response.append("error", EntityUtils.toString(apiResponse.getEntity(), "UTF-8"));
				res.append(error_response.toString());
			} else {
				JSONObject error_response = new JSONObject();
				error_response.append("error", "Unexpected Error:  Contact Admin with Error: API Call " + statusCode);
				res.append(error_response.toString());
			}
			logger.debug("--------------- API Response -----------------------");
			logger.debug(res.toString());
			logger.debug("-----------------------------------------------------");
			return res.toString();
		} catch (Exception ex) {
			logger.error("Error in OKAPIUtility.callRESTAPI: ", ex);
			return null;
		}
	}

	/**
	 * Method to call GET rest API
	 * 
	 * @param httpClient
	 *            HttpClient
	 * @param urlStr
	 *            actual API end-point
	 * @param authToken
	 *            Authentication token
	 * @return HttpResponse API response
	 */
	public static HttpResponse httpGet(HttpClient httpClient, String urlStr, String authToken)
			throws ClientProtocolException, IOException {
		try {
			HttpGet httpRequest = new HttpGet(urlStr);
			httpRequest.addHeader("Accept", "application/json, text/javascript, */*; q=0.01");
			httpRequest.addHeader("Accept-Charset", "UTF-8");
			httpRequest.addHeader("Connection", "keep-alive");
			httpRequest.addHeader("Content-Type", "application/json; charset=UTF-8");
			if (authToken != null)
				httpRequest.addHeader("kmauthtoken", authToken);
			return httpClient.execute(httpRequest);
		} catch (ConnectTimeoutException ex) {
			logger.error("The connection to " + urlStr + " timed out.");
			ex.printStackTrace();
			return null;
		} catch (Exception ex) {
			logger.error("The GET request to " + urlStr + " failed with exception: " + ex);
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * Method to call POST rest API
	 * 
	 * @param httpClient
	 *            HttpClient
	 * @param urlStr
	 *            actual API end-point
	 * @param authToken
	 *            Authentication token
	 * @param postData
	 *            Postdata
	 * @return HttpResponse API response
	 */
	public static HttpResponse httpPost(HttpClient httpClient, String urlStr, String authToken, String postData)
			throws ClientProtocolException, IOException {
		try {
			HttpPost httpRequest = new HttpPost(urlStr);
			httpRequest.addHeader("Accept", "application/json, text/javascript, */*; q=0.01");
			httpRequest.addHeader("Accept-Charset", "UTF-8");
			httpRequest.addHeader("Connection", "keep-alive");
			httpRequest.addHeader("Content-Type", "application/json; charset=UTF-8");
			if (authToken != null)
				httpRequest.addHeader("kmauthtoken", authToken);
			httpRequest.setEntity(new StringEntity(postData));
			return httpClient.execute(httpRequest);
		} catch (ConnectTimeoutException ex) {
			logger.error("The connection to " + urlStr + " timed out.");
			ex.printStackTrace();
			return null;
		} catch (Exception ex) {
			logger.error("The POST request to " + urlStr + " failed with exception: " + ex);
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * This method returns test API end-point for unitTest requests.
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param urlStr
	 *            actual API end-point
	 * @return String Test end-point for mock-data
	 */
	public static String getTestUrl(HttpServletRequest request, String urlStr) {
		String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
		if (urlStr.contains("search/question")) {
			String searchText = "";
			if (urlStr.contains("&startOver")) {
				searchText = "question/" + urlStr.substring(urlStr.indexOf("=") + 1, urlStr.indexOf("&startOver"));
			} else {
				searchText = "question/" + urlStr.substring(urlStr.indexOf("=") + 1);
			}
			urlStr = "/mockData/" + searchText;
		} else if (urlStr.contains("search/navigation")) {
			String facet = "navigation/"
					+ urlStr.substring(urlStr.indexOf("facet=") + "facet=".length(), urlStr.indexOf("&facetShowAll"));
			urlStr = "/mockData/" + facet;
		} else if (urlStr.contains("search/pagination")) {
			String pageDirection = urlStr.substring(urlStr.indexOf("&pageDirection=") + "&pageDirection=".length(),
					urlStr.indexOf("&pageNumber"));
			urlStr = "/mockData/pagination/" + pageDirection;
		} else if (urlStr.contains("search/answer")) {
			String searchAnswerId = urlStr.substring(urlStr.indexOf("answerId=") + "answerId=".length(),
					urlStr.indexOf("&priorTransactionId"));
			urlStr = "/mockData/highlight/" + searchAnswerId;
		} else if (urlStr.contains("content/answers")) {
			String answerId = urlStr.substring(urlStr.indexOf("content/answers/") + "content/answers/".length(),
					urlStr.indexOf("?"));
			urlStr = "/mockData/articles/" + answerId;
		} else if (urlStr.contains("contentTypes")) {
			if (urlStr.indexOf("referenceKey+in+('") > 0) {
				String contentType = urlStr.substring(
						urlStr.indexOf("referenceKey+in+('") + "referenceKey+in+('".length(), urlStr.indexOf("')"));
				urlStr = "/mockData/contentTypeSchema/" + contentType;
			} else {
				urlStr = "/mockData/contentTypes/contentTypes";
			}

		} else if (urlStr.contains("auth/authorize")) {
			urlStr = "/mockData/user/Test";
		} else if (urlStr.contains("incidents/link")) {
			urlStr = "/mockData/links/linkResponse";
		} else if (urlStr.contains("incidentLinks/")) {
			urlStr = "/mockData/links/linkedArticles";
		}
		return baseUrl.concat(urlStr);
	}

	/**
	 * This method returns true in case of unitTest requests.
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return boolean returns true in case of unitTest requests
	 */
	public static boolean isTest(HttpServletRequest request) {
		String currPageUrl = request.getHeader("referer");
		if (currPageUrl == null)
			currPageUrl = request.getRequestURL().toString();
		return currPageUrl.contains("/test/");
	}

	/**
	 * Method to authenticate a user
	 * 
	 * @param request
	 *            Request Object to process authentication
	 * @param response
	 *            Response Object to process authentication response
	 * @param postData
	 *            JSON string containing post data required for obtaining the
	 *            user token
	 * @return String Authentication Token String
	 */
	public static String authenticate(HttpServletRequest request, HttpServletResponse response, String postData) {
		try {
			boolean isTest = isTest(request);
			String urlStr = resourceBundle.getString("OKCS_IM_API_URL") + "auth/authorize";
			String integrationUserToken = "";
			if (!isTest) {
				JSONObject integrationToken = new JSONObject(OKAPIUtility.getKMAuthToken(request, response));
				integrationUserToken = integrationToken.getString("integrationUserToken");
			} else {
				urlStr = getTestUrl(request, urlStr);
				integrationUserToken = "ZGF5MDRfMTY1MDBfc3FsXzEzNGg6aW01bEliQlo0QUw1U3VZOW";
			}
			JSONObject kmauthtoken = new JSONObject();
			kmauthtoken.put("siteName", resourceBundle.getString("SITE_NAME"));
			kmauthtoken.put("integrationUserToken", integrationUserToken);
			String authToken = kmauthtoken.toString();

			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost httpRequest = new HttpPost(urlStr);
			httpRequest.addHeader("Accept", "application/json, text/javascript, */*; q=0.01");
			httpRequest.addHeader("Accept-Charset", "UTF-8");
			httpRequest.addHeader("Connection", "keep-alive");
			httpRequest.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			httpRequest.addHeader("kmauthtoken", authToken);
			httpRequest.setEntity(new StringEntity(postData));
			HttpResponse apiResponse = httpClient.execute(httpRequest);
			int statusCode = apiResponse.getStatusLine().getStatusCode();
			StringBuilder res = new StringBuilder();

			if (statusCode == 200 || statusCode == 201) {
				BufferedReader br = new BufferedReader(
						new InputStreamReader(apiResponse.getEntity().getContent(), Charset.forName("UTF-8")));
				String line;
				while ((line = br.readLine()) != null) {
					res.append(line);
				}
				br.close();
			} else {
				return null;
			}
			return res.toString();
		} catch (ConnectTimeoutException ex) {
			logger.error("The integration authentication request has timed out.");
			ex.printStackTrace();
			return null;
		} catch (Exception ex) {
			logger.error("The integration authentication request failed with exception: " + ex);
			ex.printStackTrace();
			return null;
		}
	}
}