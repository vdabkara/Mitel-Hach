/**
 * 
 */
package com.ka.kcapp.services;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * @author sarmarim
 *
 */
public class RemedyTester {
	private final String USER_AGENT = "Mozilla/5.0";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RemedyTester testClass = new RemedyTester();
		try {
			System.out.println("Making a post call to the service url");
			// testClass.sendPost();
			testClass.httpPost();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// HTTP POST request
	private void sendPost() throws Exception {
		String url = "https://caottmtierdev1:15000/Remedy-OKA_Adapter/LinkRemedy";
		URL obj = new URL(url);
		// HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		// add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		String urlParameters = "solution_url=http://mitel.com&answer_id=5910&incident_number=INC5045&solution_title=This is a test";
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println("Service Response" + response.toString());

	}

	private void httpPost() throws ClientProtocolException, IOException {
		try {
			HttpClient httpClient = HttpClientBuilder.create().build();
			// String postUrl =
			// "https://caottmtierdev1.mitel.com:15000/Remedy-OKA_Adapter/LinkRemedy";
			String postUrl = "https://mitelsupport.mitel.com/Remedy-OKA_Adapter/LinkRemedy";
			// String postUrl =
			// "https://mitelsupport.mitel.com/Remedy-OKA_Adapter/LinkRemedy?solution_url=http://mitel.com&answer_id=5910&incident_number=RQST00000600979&solution_title=This%20is%20a%20test";
			String postParams = "solution_url=http://ka.mitel.com&answer_id=1001&incident_number=RQST00000600979&solution_title=1001:This%20is%20a%20test";
			System.out.println("Connecting to url  " + postUrl);
			HttpPost httpPost = new HttpPost(postUrl);

			httpPost.addHeader("Accept-Charset", "UTF-8");
			httpPost.addHeader("Connection", "keep-alive");
			httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
			httpPost.setEntity(new StringEntity(postParams));
			HttpResponse response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			System.out.println("Response code " + statusCode);
			StringBuilder responseString = new StringBuilder();

			BufferedReader br = new BufferedReader(
					new InputStreamReader(response.getEntity().getContent(), Charset.forName("UTF-8")));
			String line;
			while ((line = br.readLine()) != null) {
				responseString.append(line);
			}
			br.close();
			System.out.println("Response String  " + responseString.toString());
		} catch (ConnectTimeoutException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
