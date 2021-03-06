package com.ka.kcapp.osvc.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.ka.kcapp.util.StringUtility;
import com.ka.kcapp.util.Utilities;


public class KAWebServiceCaller
{
	private Logger logger  = Logger.getLogger(KAWebServiceCaller.class);
	
	private ResourceBundle	resourceBundle;
	private String			authenticationToken				= null;
	private String			userToken						= null;

	private static boolean	USE_ANONYMOUS_AUTHENTICATION	= false;

	/**
	 * Constructor creates a new KAWebServiceCaller.
	 * 
	 * @param configuration
	 */
	public KAWebServiceCaller()
	{
		this.resourceBundle = ResourceBundle.getBundle("resources.application");
	}

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
		JSONObject responseObject = null;
		try
		{
			if(null!=subURL && !"".equals(subURL))
			{
				logger.info("callKAWebservice :: Calling KA webservice with subURL " + subURL);
				
				String url = resourceBundle.getString("apiURL") + subURL;
				logger.info("callKAWebservice :: Calling KA webservice with finalURL " + url);
				HttpURLConnection connection =null;
				try
				{
					// prepare KM AUTH TOKEN
					JSONObject kmAuthTokenObject = new JSONObject();
					String authenticationToken = getAuthenticationToken();
					String userToken = getUserToken(authenticationToken);
					if (USE_ANONYMOUS_AUTHENTICATION)
					{
						userToken = null;
					}
					kmAuthTokenObject.put("siteName", resourceBundle.getString("sitename"));
					kmAuthTokenObject.put("integrationUserToken", authenticationToken);
					kmAuthTokenObject.put("userToken", userToken);
					
					// invoke WS
					connection = (HttpURLConnection) new URL(url).openConnection();
					connection.setDoOutput(true); // Triggers POST.

					connection.setRequestMethod("GET");
					connection.setRequestProperty("Accept", "application/json");
					connection.setRequestProperty("content-type", "application/json");
					connection.setRequestProperty("kmauthtoken", kmAuthTokenObject.toString());
				}
				catch(Exception e)
				{
					Utilities.printStackTraceToLogs(KAWebServiceCaller.class.getName(), "callKAWebservice()", e);
				}
				
				if(null!=connection)
				{
					if(connection.getResponseCode()==HttpURLConnection.HTTP_OK)
					{
						logger.info("callKAWebservice :: WebService Invoked Successfully. Response Code :: > " + connection.getResponseCode());
						String response = StringUtility.toString(connection.getInputStream());
						if(null!=response && !"".equals(response))
						{
							// CONVERT TO JSON
							responseObject = new JSONObject(response);
						}
						response = null;
					}
					else
					{
						String error = StringUtility.toString(connection.getErrorStream());
						logger.info("callKAWebservice :: Failed to Invoke Web Service. Response Code :: > " + connection.getResponseCode());
						logger.info("callKAWebservice :: Failed to Invoke Web Service. Response Message :: > " + connection.getResponseMessage());
						logger.info("callKAWebservice :: Failed to Invoke Web Service. Error :: > " + error);
						error = null;
					}
					
					// disconnect connection
					connection.disconnect();
				}
			}
			else
			{
				logger.info("callKAWebservice :: SubUrl is passed as null. Cannot Invoke WebService.");
			}
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(KAWebServiceCaller.class.getName(), "callKAWebservice()", e);
		}
		return responseObject;
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
		System.out.println("Getting authentication token");

		final JSONObject kmAuthTokenObject = new JSONObject();

		kmAuthTokenObject.put("siteName", resourceBundle.getString("sitename"));
		kmAuthTokenObject.put("localeId", resourceBundle.getString("kaLocale"));

		final JSONObject payloadJSON = new JSONObject();

		payloadJSON.put("login", resourceBundle.getString("kaUsername"));
		payloadJSON.put("password", resourceBundle.getString("kaPassword"));
		payloadJSON.put("siteName", resourceBundle.getString("sitename"));

		try
		{
			String charset = "UTF-8";

			String url = resourceBundle.getString("authenticationURL");

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
				InputStream errorStream = connection.getErrorStream();
				String error = StringUtility.toString(errorStream);
				System.out.println("error = '" + error + "'");
			}

			// read the response
			InputStream inputStream = connection.getInputStream();

			StringBuilder sb = new StringBuilder();
			while (inputStream.available() > 0)
				sb.append((char) inputStream.read());

			String response = sb.toString();

			JSONObject responseJSON = new JSONObject(response);

			String authenticationToken = responseJSON.getString("authenticationToken");

			// System.out.println("authenticationToken = '" + authenticationToken + "'");

			this.authenticationToken = authenticationToken;
			return authenticationToken;
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
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
		System.out.println("Getting user token");
		String payload =
				String.format("userName=%s&password=%s&siteName=%s&userExternalType=ACCOUNT",
						resourceBundle.getString("accountUsername"), resourceBundle.getString("accountPassword"),
						resourceBundle.getString("sitename"));

		final JSONObject kmAuthTokenObject = new JSONObject();

		kmAuthTokenObject.put("siteName", resourceBundle.getString("sitename"));
		kmAuthTokenObject.put("localeId", resourceBundle.getString("kaLocale"));
		kmAuthTokenObject.put("integrationUserToken", authenticationToken);

		try
		{
			String charset = "UTF-8";

			URLConnection connection =
					new URL(resourceBundle.getString("authorizationURL")).openConnection();
			connection.setDoOutput(true); // Triggers POST.
			connection.setRequestProperty("Accept-Charset", charset);
			connection.setRequestProperty("Content-Type", "application/json;charset=" + charset);

			connection.setRequestProperty("accept", "application/json");

			connection.setRequestProperty("kmauthtoken", kmAuthTokenObject.toString());

			// Response response = client.target(kaConfigurationManager.getAuthorizationURL())
			// .request().header("accept", "application/json")
			// .header("kmauthtoken", kmAuthTokenObject.toString()).post(Entity.json(payload));

			OutputStream output = connection.getOutputStream();

			output.write(payload.toString().getBytes(charset));

			InputStream inputStream = connection.getInputStream();
			String response = IOUtils.toString(inputStream, charset);

			// System.out.println("response ut '" + response + "'");

			JSONObject responseJSON = new JSONObject(response);

			String authObjStr = responseJSON.getString("authenticationToken");

			JSONObject authObj = new JSONObject(authObjStr);

			String userToken = authObj.getString("userToken");

			// System.out.println("userToken = '" + userToken + "'");

			this.userToken = userToken;
			return userToken;

		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Gets the users json information.
	 * 
	 * @param userRecordId
	 * @return
	 * @throws JSONException
	 */
	public JSONObject getUserInfo(String userRecordId) throws JSONException, FileNotFoundException
	{
		return callKAWebservice("/users/" + userRecordId);
	}

	/**
	 * Gets document info by recordId.
	 * 
	 * @param recordId
	 * @return
	 * @throws JSONException
	 */
	public JSONObject getDocInfo(String recordId) throws JSONException, FileNotFoundException
	{
		return callKAWebservice("/content/" + recordId);
	}

	/**
	 * Gets document info by docId.
	 * 
	 * @param docId
	 * @return
	 * @throws FileNotFoundException
	 * @throws JSONException
	 */
	public JSONObject getDocInfoByDocId(String docId) throws FileNotFoundException, JSONException
	{
		return callKAWebservice("/content/docId/" + docId);
	}

}
