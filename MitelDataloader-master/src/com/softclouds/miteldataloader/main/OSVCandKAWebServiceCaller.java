package com.softclouds.miteldataloader.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.softclouds.miteldataloader.dataStructures.Configuration;
import com.softclouds.miteldataloader.utility.StringUtility;

public class OSVCandKAWebServiceCaller
{
	private Configuration	configuration;

	private String			authenticationToken				= null;
	private String			userToken						= null;

	private static boolean	USE_ANONYMOUS_AUTHENTICATION	= false;

	/**
	 * Constructor creates a new OSVCWebServiceCaller.
	 * 
	 * @param configuration
	 */
	public OSVCandKAWebServiceCaller(Configuration configuration)
	{
		this.configuration = configuration;
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
		// System.out.println("Calling KA webservice with subURL " + subURL);

		String url = configuration.get("apiURL") + subURL;

		// System.out.println("complete URL = " + url);

		final JSONObject kmAuthTokenObject = new JSONObject();

		String authenticationToken = getAuthenticationToken();
		String userToken = getUserToken(authenticationToken);

		if (USE_ANONYMOUS_AUTHENTICATION)
		{
			userToken = null;
		}

		kmAuthTokenObject.put("siteName", configuration.get("sitename"));
		kmAuthTokenObject.put("integrationUserToken", authenticationToken);
		kmAuthTokenObject.put("userToken", userToken);

		try
		{
			String charset = "UTF-8";

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
				String error = StringUtility.toString(errorStream);
				System.out.println("error = '" + error + "'");
			}

			// handle the response
			InputStream inputStream = connection.getInputStream();

			String response = StringUtility.toString(inputStream);
			
			// String response = StringUtility.toString(inputStream);
			 System.out.println("response = '" + response + "'");

			return new JSONObject(response);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return null;
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
		System.out.println("Calling OSvC webservice with subURL " + subURL);

		String url = configuration.get("OSvCapiURL") + subURL;

		// url = url.replace("https://", "https://" + configuration.get("accountUsername") + ":"
		// + configuration.get("accountPassword") + "@");

		System.out.println("complete URL = " + url);

		try
		{
			String charset = "UTF-8";

			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setDoOutput(true); // Triggers POST.

			connection.setRequestMethod("GET");
			connection.setRequestProperty("accept", "application/json");
			connection.setRequestProperty("content-type", "application/json");
			// connection.setRequestProperty("Authorization", "Basic bWl0ZWxkZXY6UjNkTWl0ZWw=");
			// get the auth encoding
			String baseString = configuration.get("accountUsername") + ":"
					+ configuration.get("accountPassword");
			byte[] encodedBytes = Base64.encodeBase64(baseString.getBytes());

			connection.setRequestProperty("Authorization", "Basic " + new String(encodedBytes));

			int responseCode = connection.getResponseCode();

			System.out.println("response code is " + responseCode);

			if (responseCode != 200)
			{
				InputStream errorStream = connection.getErrorStream();
				String error = StringUtility.toString(errorStream);
				System.out.println("error = '" + error + "'");

			}

			// handle the response
			InputStream inputStream = connection.getInputStream();

			String response = StringUtility.toString(inputStream);

			// String response = StringUtility.toString(inputStream);
			// System.out.println("response = '" + response + "'");

			return new JSONObject(response);
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

		kmAuthTokenObject.put("siteName", configuration.get("sitename"));
		kmAuthTokenObject.put("localeId", configuration.get("kaLocale"));

		final JSONObject payloadJSON = new JSONObject();

		payloadJSON.put("login", configuration.get("kaUsername"));
		payloadJSON.put("password", configuration.get("kaPassword"));
		payloadJSON.put("siteName", configuration.get("sitename"));

		try
		{
			String charset = "UTF-8";

			String url = configuration.get("authenticationURL");

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
						configuration.get("accountUsername"), configuration.get("accountPassword"),
						configuration.get("sitename"));

		final JSONObject kmAuthTokenObject = new JSONObject();

		kmAuthTokenObject.put("siteName", configuration.get("sitename"));
		kmAuthTokenObject.put("localeId", configuration.get("kaLocale"));
		kmAuthTokenObject.put("integrationUserToken", authenticationToken);

		try
		{
			String charset = "UTF-8";

			URLConnection connection =
					new URL(configuration.get("authorizationURL")).openConnection();
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
