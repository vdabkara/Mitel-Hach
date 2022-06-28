package com.hach.salesforce.utils.sfop;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hach.salesforce.utils.common.ApplicationProperties;
import com.hach.salesforce.utils.common.Utilities;



public class SalesForceAPICaller
{
	private Logger logger = Logger.getLogger(SalesForceAPICaller.class);

	private String			authenticationToken				= null;


	/**
	 * Calls the KA Webservice with the specified subURL to access different web service functions.
	 * If it fails, returns null.
	 * 
	 * https://docs.oracle.com/cloud/latest/servicecs_gs/CXSKA/
	 * 
	 * @return
	 * @throws JSONException
	 */
	public JSONObject callSFRestAPI(String subURL,String method, String payLoad) throws JSONException, FileNotFoundException
	{
		// System.out.println("Calling KA webservice with subURL " + subURL);
		JSONObject contentObj = new JSONObject();
		String url = ApplicationProperties.getProperty("rest.api.url") + subURL;
		logger.info("callSFRestAPI :: Complete URL :: >"+ url);
		// System.out.println("complete URL = " + url);

		String authenticationToken = getAuthenticationToken();
		logger.info("callSFRestAPI :: authenticationToken :: > "+ authenticationToken);

		try
		{
			if(method.equals(SalesForceConstants.METHOD_PATCH))
			{
				url = url+"?_HttpMethod=PATCH";
			}
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setDoOutput(true); // Triggers POST.
			if(method.equals(SalesForceConstants.METHOD_PATCH))
			{
				connection.setRequestMethod("POST");
				connection.setRequestProperty("X-HTTP-Method-Override", "PATCH");
			}
			else
			{
				connection.setRequestMethod(method);
			}
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("content-type", "application/json");
			connection.setRequestProperty("authorization", "Bearer "+authenticationToken);
			
			if(null!=payLoad && !"".equals(payLoad))
			{
				// set PayLoad
				connection.getOutputStream().write(payLoad.toString().getBytes("UTF-8"));
			}
			int responseCode = connection.getResponseCode();

			// System.out.println("response code is " + responseCode);
			logger.info("callSFRestAPI :: Response Code :: > "+ responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK || responseCode==HttpURLConnection.HTTP_CREATED || responseCode == HttpURLConnection.HTTP_NO_CONTENT)
			{
				// handle the response
				InputStream inputStream = connection.getInputStream();

				String response = Utilities.toString(inputStream);
				inputStream.close();
				inputStream = null;
				// String response = StringUtility.toString(inputStream);
				logger.info("callSFRestAPI :: response :: >"+ response);
				contentObj.put("CALL_STATUS", "SUCCESS");
				contentObj.put("ERROR_DATA", new JSONArray());
				if(responseCode!=HttpURLConnection.HTTP_NO_CONTENT)
				{
					contentObj.put("FINAL_DATA", new JSONObject(response));
				}
				else
				{
					contentObj.put("FINAL_DATA", new JSONObject());
				}
				contentObj.put("ERROR_MESSAGE", "");
			}
			else
			{
				InputStream errorStream = connection.getErrorStream();
				String error = Utilities.toString(errorStream);
				logger.info("callSFRestAPI :: error :: >"+ error);
				errorStream.close();
				errorStream = null;
				contentObj.put("CALL_STATUS", "FAILURE");
				contentObj.put("ERROR_DATA", new JSONArray(error));
				contentObj.put("FINAL_DATA", new JSONObject());
				contentObj.put("ERROR_MESSAGE", "");
				error = null;
			}
			authenticationToken = null;
			connection = null;
			url=null;
		}
		catch (Exception e)
		{
			Utilities.printStackTraceToLogs(SalesForceAPICaller.class.getName(), "callSFRestAPI()", e);
			contentObj.put("CALL_STATUS", "FAILURE");
			contentObj.put("ERROR_DATA", new JSONArray());
			contentObj.put("FINAL_DATA", new JSONObject());
			contentObj.put("ERROR_MESSAGE", "Failed to Invoke SF Rest API for URL :: >"+ url+". With Exception :: >"+ e.getMessage());
		}
		return contentObj;
	}

	/**
	 * 
	 * @param subURL
	 * @param method
	 * @param payLoad
	 * @return
	 * @throws JSONException
	 * @throws FileNotFoundException
	 */
	public JSONObject callSFRestAPIForOtherOperations(String apiURL, String method, String payLoad) throws JSONException, FileNotFoundException
	{
		// System.out.println("Calling KA webservice with subURL " + subURL);
		JSONObject contentObj = new JSONObject();
		String url = apiURL;
		logger.info("callSFRestAPIForOtherOperations :: Complete URL :: >"+ url);
		// System.out.println("complete URL = " + url);

		String authenticationToken = getAuthenticationToken();
		logger.info("callSFRestAPIForOtherOperations :: authenticationToken :: > "+ authenticationToken);

		try
		{
			if(method.equals(SalesForceConstants.METHOD_PATCH))
			{
				url = url+"?_HttpMethod=PATCH";
			}
			if(method.equals(SalesForceConstants.METHOD_DELETE))
			{
				url = url+"?_HttpMethod=DELETE";
			}
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setDoOutput(true); // Triggers POST.
			if(method.equals(SalesForceConstants.METHOD_PATCH) || (method.equals(SalesForceConstants.METHOD_DELETE)))
			{
				connection.setRequestProperty("X-HTTP-Method-Override", "PATCH");
				connection.setRequestMethod("POST");
			}
			else
			{
				connection.setRequestMethod(method);
			}
			
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("content-type", "application/json");
			connection.setRequestProperty("authorization", "Bearer "+authenticationToken);
			System.out.println(authenticationToken);
			if(null!=payLoad && !"".equals(payLoad))
			{
				// set PayLoad
				connection.getOutputStream().write(payLoad.toString().getBytes("UTF-8"));
			}
			System.out.println(url);
			int responseCode = connection.getResponseCode();

			// System.out.println("response code is " + responseCode);
			logger.info("callSFRestAPIForOtherOperations :: Response Code :: > "+ responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK || responseCode==HttpURLConnection.HTTP_CREATED || responseCode == HttpURLConnection.HTTP_NO_CONTENT)
			{
				// handle the response
				InputStream inputStream = connection.getInputStream();

				String response = Utilities.toString(inputStream);
				inputStream.close();
				inputStream = null;
				// String response = StringUtility.toString(inputStream);
				logger.info("callSFRestAPIForOtherOperations :: response :: >"+ response);
				contentObj.put("CALL_STATUS", "SUCCESS");
				contentObj.put("ERROR_DATA", new JSONArray());
				if(responseCode!=HttpURLConnection.HTTP_NO_CONTENT)
				{
					contentObj.put("FINAL_DATA", new JSONObject(response));
				}
				else
				{
					contentObj.put("FINAL_DATA", new JSONObject());
				}
				contentObj.put("ERROR_MESSAGE", "");
			}
			else
			{
				InputStream errorStream = connection.getErrorStream();
				String error = Utilities.toString(errorStream);
				logger.info("callSFRestAPIForOtherOperations :: error :: >"+ error);
				errorStream.close();
				errorStream = null;
				contentObj.put("CALL_STATUS", "FAILURE");
				contentObj.put("ERROR_DATA", new JSONArray(error));
				contentObj.put("FINAL_DATA", new JSONObject());
				contentObj.put("ERROR_MESSAGE", "");
				error = null;
			}
			authenticationToken = null;
			connection = null;
			url=null;
		}
		catch (Exception e)
		{
			Utilities.printStackTraceToLogs(SalesForceAPICaller.class.getName(), "callSFRestAPIForOtherOperations()", e);
			contentObj.put("CALL_STATUS", "FAILURE");
			contentObj.put("ERROR_DATA", new JSONArray());
			contentObj.put("FINAL_DATA", new JSONObject());
			contentObj.put("ERROR_MESSAGE", "Failed to Invoke SF Rest API for URL :: >"+ url+". With Exception :: >"+ e.getMessage());
		}
		return contentObj;
	}


	/**
	 * Gets the authentication token from KA.
	 * 
	 * @return
	 * @throws JSONException
	 */
	private String getAuthenticationToken() throws JSONException
	{
		if (this.authenticationToken != null)
			return this.authenticationToken;
		logger.info("Getting authentication token");
		
		
		StringBuilder payload=new StringBuilder();
		payload.append("grant_type=password&client_id=");
		payload.append(ApplicationProperties.getProperty("client_id"));
		payload.append("&client_secret=");
		payload.append(ApplicationProperties.getProperty("client_secret"));
		payload.append("&username=");
		payload.append(ApplicationProperties.getProperty("username"));
		payload.append("&password=");
		payload.append(ApplicationProperties.getProperty("password")+ApplicationProperties.getProperty("securityToken"));

		try
		{
			String charset = "UTF-8";

			String url = ApplicationProperties.getProperty("token.api.url");
			logger.info("Getting authentication token :: API URL :: >"+ url);
			// System.out.println("url = " + url);
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

			connection.setDoOutput(true); // Triggers POST.
			connection.setRequestProperty("Accept-Charset", charset);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			connection.setRequestProperty("accept", "application/json");

			// write the payload
			OutputStream output = connection.getOutputStream();
			output.write(payload.toString().getBytes(charset));

			int responseCode = connection.getResponseCode();
			// System.out.println("response code : " + responseCode);

			if (responseCode != 200)
			{
				logger.info("getAuthenticationToken :: response code : " + responseCode);
				InputStream errorStream = connection.getErrorStream();
				String error = Utilities.toString(errorStream);
				logger.info("getAuthenticationToken :: error :: >"+ error);
				error=null;
				errorStream.close();
				errorStream = null;
			}
			else
			{
				// read the response
				InputStream inputStream = connection.getInputStream();
				StringBuilder sb = new StringBuilder();
				while (inputStream.available() > 0)
					sb.append((char) inputStream.read());

				String response = sb.toString();
				JSONObject responseJSON = new JSONObject(response);
				String authenticationToken = responseJSON.getString("access_token");
				responseJSON = null;
				sb = null;
				inputStream.close();
				inputStream = null;
				this.authenticationToken = authenticationToken;
				authenticationToken = null;
			}

						
			output.close();
			output=  null;
			connection = null;
			url = null;
			charset=null;
			payload=  null;
			
			return authenticationToken;
		}
		catch (MalformedURLException e)
		{
			Utilities.printStackTraceToLogs(SalesForceAPICaller.class.getName(), "getAuthenticationToken()", e);
		}
		catch (IOException e)
		{
			Utilities.printStackTraceToLogs(SalesForceAPICaller.class.getName(), "getAuthenticationToken()", e);
		}

		return null;
	}
}