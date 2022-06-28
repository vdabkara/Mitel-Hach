package com.hach.dataextraction.utils.osvc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.hach.dataextraction.utils.common.ApplicationProperties;
import com.hach.dataextraction.utils.common.Utilities;


public class OSVCandKAWebServiceCaller
{
	private Logger logger = Logger.getLogger(OSVCandKAWebServiceCaller.class);

	private String			authenticationToken				= null;
	private String			userToken						= null;

	private static boolean	USE_ANONYMOUS_AUTHENTICATION	= false;

	

	/**
	 * Calls the KA Webservice with the specified subURL to access different web service functions.
	 * If it fails, returns null.
	 * 
	 * https://docs.oracle.com/cloud/latest/servicecs_gs/CXSKA/
	 * 
	 * @return
	 * @throws JSONException
	 */
	public JSONObject callKAWebservice(String subURL) throws JSONException, FileNotFoundException
	{
		// System.out.println("Calling KA webservice with subURL " + subURL);
		JSONObject contentObj = new JSONObject();
		String url = ApplicationProperties.getProperty("apiURL") + subURL;
		logger.info("callKAWebservice :: Complete URL :: >"+ url);
		// System.out.println("complete URL = " + url);

		JSONObject kmAuthTokenObject = new JSONObject();

		String authenticationToken = getAuthenticationToken();
		logger.info("callKAWebservice :: authenticationToken :: > "+ authenticationToken);
		String userToken = getUserToken(authenticationToken);
		if (USE_ANONYMOUS_AUTHENTICATION)
		{
			userToken = null;
		}

		kmAuthTokenObject.put("siteName", ApplicationProperties.getProperty("contentsitename"));
		kmAuthTokenObject.put("integrationUserToken", authenticationToken);
//		kmAuthTokenObject.put("locale", "");
		kmAuthTokenObject.put("userToken", userToken);
		try
		{

			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setDoOutput(true); // Triggers POST.

			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("content-type", "application/json");
			connection.setRequestProperty("kmauthtoken", kmAuthTokenObject.toString());
			
			int responseCode = connection.getResponseCode();

			// System.out.println("response code is " + responseCode);

			if (responseCode != 200)
			{
				InputStream errorStream = connection.getErrorStream();
				String error = Utilities.toString(errorStream);
				logger.info("callKAWebservice :: error :: >"+ error);
				errorStream.close();
				errorStream = null;
				contentObj.put("CALL_STATUS", "FAILURE");
				contentObj.put("ERROR_DATA", new JSONObject(error));
				contentObj.put("FINAL_DATA", new JSONObject());
				contentObj.put("ERROR_MESSAGE", "");
				error = null;
			}
			else
			{
				// handle the response
				InputStream inputStream = connection.getInputStream();

				String response = Utilities.toString(inputStream);
				inputStream.close();
				inputStream = null;
				// String response = StringUtility.toString(inputStream);
				logger.info("callKAWebservice :: response :: >"+ response);
				contentObj.put("CALL_STATUS", "SUCCESS");
				contentObj.put("ERROR_DATA", new JSONObject());
				contentObj.put("FINAL_DATA", new JSONObject(response));
				contentObj.put("ERROR_MESSAGE", "");
			}
			authenticationToken = null;
			userToken=null;
			kmAuthTokenObject =null;
			connection = null;
			url=null;
		}
		catch (Exception e)
		{
			Utilities.printStackTraceToLogs(OSVCandKAWebServiceCaller.class.getName(), "callKAWebservice()", e);
			contentObj.put("CALL_STATUS", "FAILURE");
			contentObj.put("ERROR_DATA", new JSONObject());
			contentObj.put("FINAL_DATA", new JSONObject());
			contentObj.put("ERROR_MESSAGE", "Failed to Invoke KA WebService for URL :: >"+ url+". With Exception :: >"+ e.getMessage());
		}
		return contentObj;
	}

	public JSONObject callKAWebserviceForManualLinks(String subURL,String locale) throws JSONException, FileNotFoundException
	{
		// System.out.println("Calling KA webservice with subURL " + subURL);
		JSONObject contentObj = new JSONObject();
		String url = ApplicationProperties.getProperty("apiURL") + subURL;
		logger.info("callKAWebserviceForManualLinks :: Complete URL :: >"+ url);
		// System.out.println("complete URL = " + url);

		JSONObject kmAuthTokenObject = new JSONObject();

		String authenticationToken = getAuthenticationToken();
		logger.info("callKAWebserviceForManualLinks :: authenticationToken :: > "+ authenticationToken);
		String userToken = getUserToken(authenticationToken);
		if (USE_ANONYMOUS_AUTHENTICATION)
		{
			userToken = null;
		}

		kmAuthTokenObject.put("siteName", ApplicationProperties.getProperty("contentsitename"));
		kmAuthTokenObject.put("integrationUserToken", authenticationToken);
		kmAuthTokenObject.put("locale", locale);
		kmAuthTokenObject.put("userToken", userToken);
		try
		{

			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setDoOutput(true); // Triggers POST.

			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("content-type", "application/json");
			connection.setRequestProperty("kmauthtoken", kmAuthTokenObject.toString());
			
			int responseCode = connection.getResponseCode();

			// System.out.println("response code is " + responseCode);

			if (responseCode != 200)
			{
				InputStream errorStream = connection.getErrorStream();
				String error = Utilities.toString(errorStream);
				logger.info("callKAWebserviceForManualLinks :: error :: >"+ error);
				errorStream.close();
				errorStream = null;
				contentObj.put("CALL_STATUS", "FAILURE");
				contentObj.put("ERROR_DATA", new JSONObject(error));
				contentObj.put("FINAL_DATA", new JSONObject());
				contentObj.put("ERROR_MESSAGE", "");
				error = null;
			}
			else
			{
				// handle the response
				InputStream inputStream = connection.getInputStream();

				String response = Utilities.toString(inputStream);
				inputStream.close();
				inputStream = null;
				// String response = StringUtility.toString(inputStream);
				logger.info("callKAWebserviceForManualLinks :: response :: >"+ response);
				contentObj.put("CALL_STATUS", "SUCCESS");
				contentObj.put("ERROR_DATA", new JSONObject());
				contentObj.put("FINAL_DATA", new JSONObject(response));
				contentObj.put("ERROR_MESSAGE", "");
			}
			authenticationToken = null;
			userToken=null;
			kmAuthTokenObject =null;
			connection = null;
			url=null;
		}
		catch (Exception e)
		{
			Utilities.printStackTraceToLogs(OSVCandKAWebServiceCaller.class.getName(), "callKAWebserviceForManualLinks()", e);
			contentObj.put("CALL_STATUS", "FAILURE");
			contentObj.put("ERROR_DATA", new JSONObject());
			contentObj.put("FINAL_DATA", new JSONObject());
			contentObj.put("ERROR_MESSAGE", "Failed to Invoke KA WebService for URL :: >"+ url+". With Exception :: >"+ e.getMessage());
		}
		return contentObj;
	}

	
	/**
	 * Calls the KA Webservice with the specified subURL to access different web service functions.
	 * If it doesn't resolve, returns null.
	 * 
	 * https://docs.oracle.com/cloud/latest/servicecs_gs/CXSVC/
	 * 
	 * @return
	 * @throws JSONException
	 */
	public JSONObject callOSvCWebservice(String subURL) throws JSONException
	{
		String url = ApplicationProperties.getProperty("OSvCapiURL") + subURL;
		logger.info("callOSvCWebservice :: Complete URL :: >"+ url);
		try
		{
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setDoOutput(true); // Triggers POST.

			connection.setRequestMethod("GET");
			connection.setRequestProperty("accept", "application/json");
			connection.setRequestProperty("content-type", "application/json");
			// connection.setRequestProperty("Authorization", "Basic bWl0ZWxkZXY6UjNkTWl0ZWw=");
			// get the auth encoding
			String baseString = ApplicationProperties.getProperty("accountUsername") + ":"
					+ ApplicationProperties.getProperty("accountPassword");
			byte[] encodedBytes = Base64.encodeBase64(baseString.getBytes());

			connection.setRequestProperty("Authorization", "Basic " + new String(encodedBytes));

			int responseCode = connection.getResponseCode();
			logger.info("callOSvCWebservice :: Response Code :: >"+ responseCode);

			if (responseCode != 200)
			{
				InputStream errorStream = connection.getErrorStream();
				String error = Utilities.toString(errorStream);
				logger.info("callOSvCWebservice :: error :: >"+ error);
				error = null;
				errorStream.close();
				errorStream=null;

			}

			// handle the response
			InputStream inputStream = connection.getInputStream();

			String response = Utilities.toString(inputStream);
			
			inputStream.close();
			inputStream=null;
			baseString = null;
			connection=null;
			url=null;
			logger.info("callOSvCWebservice :: response :: >"+response);
			// String response = StringUtility.toString(inputStream);
			// System.out.println("response = '" + response + "'");

			return new JSONObject(response);
		}
		catch (MalformedURLException e)
		{
			Utilities.printStackTraceToLogs(OSVCandKAWebServiceCaller.class.getName(), "callOSvCWebservice()", e);
		}
		catch (IOException e)
		{
			Utilities.printStackTraceToLogs(OSVCandKAWebServiceCaller.class.getName(), "callOSvCWebservice()", e);
		}

		return null;
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

		JSONObject kmAuthTokenObject = new JSONObject();

		kmAuthTokenObject.put("siteName", ApplicationProperties.getProperty("sitename"));
		kmAuthTokenObject.put("localeId", ApplicationProperties.getProperty("kaLocale"));

		JSONObject payloadJSON = new JSONObject();

		payloadJSON.put("login", ApplicationProperties.getProperty("kaApiUsername"));
		payloadJSON.put("password", ApplicationProperties.getProperty("kaApiPassword"));
		payloadJSON.put("siteName", ApplicationProperties.getProperty("sitename"));

		try
		{
			String charset = "UTF-8";

			String url = ApplicationProperties.getProperty("authenticationURL");
			
			// System.out.println("url = " + url);
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

			connection.setDoOutput(true); // Triggers POST.
			connection.setRequestProperty("Accept-Charset", charset);
			connection.setRequestProperty("Content-Type", "application/json;charset=" + charset);

			connection.setRequestProperty("accept", "application/json");

			// Response response = client.target(kaConfigurationManager.getAuthenticationURL())
			// .request().header("accept", "application/json")
			// .header("content-type", "application/json")
			// .header("kmauthtoken", kmAuthTokenObject.toString())
			// .post(Entity.json(payloadJSON.toString()));

			// write the payload
			OutputStream output = connection.getOutputStream();
			output.write(payloadJSON.toString().getBytes(charset));

			int responseCode = connection.getResponseCode();
			// System.out.println("response code : " + responseCode);

			if (responseCode != 200)
			{
				System.out.println("response code : " + responseCode);
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
				String authenticationToken = responseJSON.getString("authenticationToken");
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
			kmAuthTokenObject =null;
			charset=null;
			payloadJSON = null;
			
			return authenticationToken;
		}
		catch (MalformedURLException e)
		{
			Utilities.printStackTraceToLogs(OSVCandKAWebServiceCaller.class.getName(), "getAuthenticationToken()", e);
		}
		catch (IOException e)
		{
			Utilities.printStackTraceToLogs(OSVCandKAWebServiceCaller.class.getName(), "getAuthenticationToken()", e);
		}

		return null;
	}

	/**
	 * Gets the user token from KA given the authentication token.
	 * 
	 * @param authenticationToken
	 * @return
	 * @throws JSONException
	 */
	private String getUserToken(String authenticationToken) throws JSONException
	{
		if (this.userToken != null)
			return this.userToken;
		logger.info("Getting user token");
		String payload =
				String.format("userName=%s&password=%s&siteName=%s&userExternalType=ACCOUNT",
						ApplicationProperties.getProperty("accountUsername"), ApplicationProperties.getProperty("accountPassword"),
						ApplicationProperties.getProperty("sitename"));
		JSONObject kmAuthTokenObject = new JSONObject();

		kmAuthTokenObject.put("siteName", ApplicationProperties.getProperty("sitename"));
		kmAuthTokenObject.put("localeId", ApplicationProperties.getProperty("kaLocale"));
		kmAuthTokenObject.put("integrationUserToken", authenticationToken);

		try
		{
			String charset = "UTF-8";
			HttpURLConnection connection = (HttpURLConnection) new URL(ApplicationProperties.getProperty("authorizationURL")).openConnection();
			connection.setDoOutput(true); // Triggers POST.
			connection.setRequestProperty("Accept-Charset", charset);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("accept", "application/json");
			connection.setRequestProperty("kmauthtoken", kmAuthTokenObject.toString());

			// Response response = client.target(kaConfigurationManager.getAuthorizationURL())
			// .request().header("accept", "application/json")
			// .header("kmauthtoken", kmAuthTokenObject.toString()).post(Entity.json(payload));

			OutputStream output = connection.getOutputStream();
			output.write(payload.toString().getBytes(charset));
			
			int responseCode = connection.getResponseCode();
			// System.out.println("response code : " + responseCode);

			if (responseCode != 200)
			{
				InputStream errorStream = connection.getErrorStream();
				String error = Utilities.toString(errorStream);
				logger.info("getUserToken :: error :: >"+ error);
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
				String authObjStr = responseJSON.getString("authenticationToken");
				JSONObject authObj = new JSONObject(authObjStr);
				String userToken = authObj.getString("userToken");
				logger.info("getUserToken :: User Token :: >" + userToken);
				this.userToken = userToken;
				
				userToken = null;
				authObj = null;
				authObjStr=null;
				response = null;
				inputStream.close();
				inputStream = null;
				responseJSON = null;
			}
			output.close();
			output= null;
			connection = null;
			charset = null;
			kmAuthTokenObject=null;
			payload =null;
			return userToken;
		}
		catch (MalformedURLException e)
		{
			Utilities.printStackTraceToLogs(OSVCandKAWebServiceCaller.class.getName(),"getUserToken()" , e);
		}
		catch (IOException e)
		{
			Utilities.printStackTraceToLogs(OSVCandKAWebServiceCaller.class.getName(),"getUserToken()" , e);
		}
		return null;
	}
}
