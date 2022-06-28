package com.softclouds.miteldataloader.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.TreeSet;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.softclouds.miteldataloader.dataStructures.Configuration;
import com.softclouds.miteldataloader.dataStructures.models.contenthistory.ContentHistoryRecord;
import com.softclouds.miteldataloader.dataStructures.models.contentrecommendation.ContentRecommendationRecord;
import com.softclouds.miteldataloader.dataStructures.models.userinfo.UserInfoRecord;
import com.softclouds.miteldataloader.osvc.Cruder;
import com.softclouds.miteldataloader.utility.FileUtility;
import com.softclouds.miteldataloader.utility.TimingUtility;

public class MitelDataloader extends HttpServlet
{
	private static final long					serialVersionUID	= 7793143495953222112L;

	final static Logger							logger				=
			Logger.getLogger(MitelDataloader.class);

	private static final String					OLD_TIME_FILE		=
			"data" + File.separator + "oldTime.txt";

	private static Configuration				configuration;
	private static OSVCandKAWebServiceCaller	caller;
	public static String						CFG_FILE_NAME		=
			FileUtility.getPath("data", "mitelDataloader") + "configuration.txt";

	static
	{
		System.out.println("Loading configuration file at\n" + CFG_FILE_NAME + "\ncwd = "
				+ FileUtility.getCurrentWorkingDirectory());
		try
		{
			configuration = readConfiguration();
		}
		catch (JSONException e)
		{
			System.out.println(ExceptionUtils.getFullStackTrace(e));
			throw new UnsupportedOperationException(
					"Invalid JSON.  Check your configuration file at " + CFG_FILE_NAME + ", cwd = "
							+ FileUtility.getCurrentWorkingDirectory());
		}

		caller = new OSVCandKAWebServiceCaller(configuration);

		System.out.println("Initialization complete");
	}

	public static void main(String[] args)
	{
		System.out.println("Running dataloader from cmd line");
		MitelDataloader mtd = new MitelDataloader();

		mtd.dataLoad();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String result = dataLoad();

		PrintWriter out = response.getWriter();

		out.write(result);
		// test(request, response);

	}

	protected String dataLoad()
	{
		// read date of last run
		DateTime oldDate = readDate();
		System.out.println("old time was '" + oldDate + "'");

		System.out.println("Received request!");

		long time[] = new long[4];

		time[0] = TimingUtility.getAbsTime();

		 updateRecommendations();
		time[1] = TimingUtility.getAbsTime();
		System.out.println("------------------------------- PROCEED FOR UPDATING USERS ----------------------");
//		updateUsers();
		time[2] = TimingUtility.getAbsTime();
		
//		 updateContentHistory();
		time[3] = TimingUtility.getAbsTime();

		String ret = "";
		for (int i = 0; i < time.length - 1; i++)
		{
			long endTime = time[i + 1];
			long startTime = time[i];
			long runTime = endTime - startTime;

			String runTimeStr = TimingUtility.longTimeToTimeString(runTime);
			String msg = "Completed in " + runTimeStr;
			System.out.println(msg);
			ret += msg + "\n";
		}

		// write todays start of day
		DateTime newOldTime = DateTime.now();
		System.out.println("new old time is " + newOldTime);
		FileUtility.write(OLD_TIME_FILE, newOldTime.toString());

		return ret;
	}

	/**
	 * Reads the date from the OLD_TIME_FILE. If it doesn't exist, or there's an error, returns
	 * today at the start of day.
	 * 
	 * @return
	 */
	private static DateTime readDate()
	{
		if (!FileUtility.exists(OLD_TIME_FILE))
		{
			return DateTime.now().withTimeAtStartOfDay();
		}

		String oldTimeStr = FileUtility.read(OLD_TIME_FILE);

		try
		{
			DateTimeFormatter formatter =
					DateTimeFormat.forPattern("YYYY'-'MM'-'dd'T'HH:mm:ss.SSSZ").withOffsetParsed();
			DateTime ret = formatter.parseDateTime(oldTimeStr);
			return ret;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return DateTime.now().withTimeAtStartOfDay();
		}
	}

	protected void test(HttpServletRequest request, HttpServletResponse response)
			throws FileNotFoundException
	{

		// try
		// {
		// System.out.println("userInfo\n" + caller.getUserInfo("GKim"));
		// // System.out.println("call content\n" +
		// // caller.callKAWebservice("/content/docId/AL127/"));
		// }
		// catch (JSONException e1)
		// {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }

		// Cruder cruder = new Cruder(configuration);

		// cruder.create();

		// works!
		// cruder.read();
		// cruder.create();

		// Cruder cruder = new Cruder(configuration);
		// for (int i = 99; i <= 108; i++)
		// cruder.destroy(i, "recommendation");
		//

		// Cruder cruder = new Cruder(configuration);
		// cruder.destroyAll("recommendation");

		// try
		// {
		// JSONObject history = caller.callKAWebservice("/content/docId/HO1689/history");
		// System.out.println("----\nhistory\n" + history + "/history");
		// }
		// catch (JSONException e)
		// {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// Cruder cruder = new Cruder(configuration);
		// String result = cruder.queryObjects("SELECT KMS.user_info FROM KMS.user_info WHERE ID >
		// 3");
		//
		// System.out.println("result is\n" + result);

		// validateContentRecommendationStatuses();
	}

	public static Configuration readConfiguration() throws JSONException
	{
		JSONObject allConfigJson;
		System.out.println("Reading config file data");
		String fileName = "E:\\DEV\\MitelDataloader-master\\Dataloader_Package\\data\\mitelDataloader\\configuration.txt";
		String data = FileUtility.read(fileName);
		System.out.println("data is = '" + data + "'");
		System.out.println("converting to JSON");
		allConfigJson = new JSONObject(data);

		String useConnection = allConfigJson.getString("useConnection");
		System.out.println("Using connection information from '" + useConnection + "' JSON Object");

		JSONObject configJson = allConfigJson.getJSONObject(useConnection);
		return new Configuration(configJson);
	}

	private void validateContentRecommendationStatuses()
	{
		System.out.println("Validating content recommendation statuses");

		try
		{
			JSONObject contentRec = caller.callKAWebservice("/contentRecommendations?limit=100");

			System.out.println(contentRec.toString());

			JSONArray items = contentRec.getJSONArray("items");

			TreeSet<String> statusSet = new TreeSet<String>();
			for (int i = 0; i < items.length(); i++)
			{
				JSONObject item = items.getJSONObject(i);

				String status = item.getString("status");
				System.out.println("status[" + i + "] = '" + status + "'");
				statusSet.add(status);
			}

			System.out.println("statusSet = " + statusSet);
			System.out.println(statusSet.size() + " unique statuses");
			System.out.println(items.length() + " sampled recommendations");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void updateContentHistory()
	{
		System.out.println("------------------------------------------------------------");
		System.out.println("Updating Content History");

		Cruder cruder = new Cruder(configuration);

		// get all document IDs from content
		System.out.println("------------------------------------------------------------");
		System.out.println("Stage 1 : Getting all Doc IDs");
		Vector<String> docIDs = getAllDocIDs();

		System.out.println("------------------------------------------------------------");
		System.out.println("Stage 2 : Destroying all data");
		cruder.destroyAll("content_history");

		System.out.println("------------------------------------------------------------");
		System.out.println("Stage 3 : Creating all Content History");
		System.out.println(docIDs.size() + " documents found. Getting history...");

		int success = 0;
		int failure = 0;
		int total = 0;
		for (int i = 0; i < docIDs.size(); i++)
		{
			String docID = docIDs.get(i);

			System.out.println("creating " + i + " of " + docIDs.size() + " " + docID);

			try
			{
				createContentHistory(docID, cruder);
				success++;
			}
			catch (FileNotFoundException | JSONException e)
			{
				e.printStackTrace();
				failure++;
			}
			total++;
		}

		System.out.println("Done Updating Content History");
		System.out.println(success + " successful doc ids");
		System.out.println(failure + " failed doc ids");
		System.out.println(total + " total doc ids");
		System.out.println("------------------------------------------------------------");
	}

	/**
	 * Creates content history records in our report for the given document id.
	 * 
	 * @param docID
	 * @param cruder
	 * @throws JSONException
	 * @throws FileNotFoundException
	 */
	private void createContentHistory(String docID, Cruder cruder)
			throws FileNotFoundException, JSONException
	{
		JSONObject history = caller.callKAWebservice("/content/docId/" + docID + "/history");

		// System.out.println("-------------- history ------");
		// System.out.println(history);

		Vector<ContentHistoryRecord> chrs =
				ContentHistoryRecord.extractFromContent(history, caller);

		for (ContentHistoryRecord chr : chrs)
		{
			String payloadBody = chr.getCreatePayload();

			try
			{
				cruder.create(payloadBody);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * Gets all document IDs.
	 * 
	 * @return
	 */
	private Vector<String> getAllDocIDs()
	{
		Vector<String> ret = new Vector<String>();

		try
		{
			boolean hasMore = true;
			int limit = 1000;
			int offset = 0;

			long startTime = TimingUtility.getAbsTime();

			while (hasMore)
			{
				try
				{
					JSONObject content = caller
							.callKAWebservice("/content?limit=" + limit + "&offset=" + offset);
					offset += limit;

					hasMore = content.getBoolean("hasMore");

					// System.out.println("------------------------------");
					// System.out.println("content\n" + content);

					JSONArray items = content.getJSONArray("items");

					for (int i = 0; i < items.length(); i++)
					{
						JSONObject item = items.getJSONObject(i);

						String id = item.getString("documentId");
						ret.add(id);
					}

					long runTime = TimingUtility.getAbsTime() - startTime;
					double rate = 1000.0 * ret.size() / runTime;
					System.out.println("rate is " + rate + " doc/s");

					System.out.println(ret.size() + " items found so far.");
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}

			long runTime = TimingUtility.getAbsTime() - startTime;
			double rate = 1000.0 * ret.size() / runTime;
			System.out.println("rate is " + rate + " doc/s");

			System.out.println(ret.size() + " items found in total.");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return ret;
	}

	/**
	 * This method updates content recommendations in the custom OSvC table.
	 * 
	 */
	private void updateUsers()
	{
		long startTime = TimingUtility.getAbsTime();
		System.out.println("------------------------------------------------------------");
		System.out.println("Updating Users");
		try
		{
			// delete the current table
			System.out.println("Deleting current osvc table");
			Cruder cruder = new Cruder(configuration);
			
//			cruder.getData("user_info");
			
			
			
			
			
//			cruder.destroyAll("user_info");

			JSONObject userInfoJSON = caller.callOSvCWebservice("/accounts/");
			
			// System.out.println("content accounts\n" + userInfoJSON);

			// for each item, get the specific user information based on it's ID
			Vector<UserInfoRecord> uirs = UserInfoRecord.extractFromContent(userInfoJSON, caller);
			for (UserInfoRecord uir : uirs)
			{
				String payloadBody = uir.getCreatePayload();
				cruder.create(payloadBody);
			}

		}
		catch (JSONException e)
		{

		}

		System.out.println("Finished Updating Users");
		long endTime = TimingUtility.getAbsTime();

		long runTime = endTime - startTime;
		String runTimeStr = TimingUtility.longTimeToHoursMinutesAndSeconds(runTime);

		System.out.println("Completed in " + runTimeStr);
		System.out.println("------------------------------------------------------------");
	}

	/**
	 * This method updates content recommendations in the custom OSvC table.
	 */
	private void updateRecommendations()
	{
		System.out.println("------------------------------------------------------------");
		System.out.println("Updating Content Recommendations");

		// get the content record ids
		Vector<String> contentRecommendationIDs = getAllContentRecommendationIDs();
		
		System.out.println("crids = " + contentRecommendationIDs.size());
//		System.out.println("------------------------------------------------------------");
//		System.out.println("Stage 1 : Destroying all data");
		Cruder cruder = new Cruder(configuration);
//		cruder.destroyAll("recommendation");

//		for (String recordID : contentRecommendationIDs)
//		{
//			JSONObject json = null;
//			try
//			{
//				json = caller.callKAWebservice("/contentRecommendations/" + recordID);
//				System.out.println("--------------- con recon :: JSON :: > " + json);
//				ContentRecommendationRecord crr = new ContentRecommendationRecord(json, caller);
//
//				// create the record
////				String payloadBody = crr.getCreatePayload();
////				cruder.create(payloadBody);
//			}
//			catch (FileNotFoundException | JSONException e)
//			{
//				System.out.println("error parsing content recommendation\n" + json);
//				e.printStackTrace();
//			}
//		}

		// System.out.println("------------------------------------------------------------");
		// System.out.println("Stage 2 : Getting all Content Recommendations");
		//
		// boolean hasMore = true;
		// int limit = 100;
		// int offset = 0;
		//
		// int success = 0;
		// int failure = 0;
		// int total = 0;
		//
		// while (hasMore)
		// {
		// // System.out.println("--------------------------");
		// System.out.println("content rec batch offset " + offset);
		//
		// JSONObject contentRecommendations = null;
		// try
		// {
		// // note we can also use the mode=FULL& http parameter, but it omits certain results
		// // with {links} type JSON objects. Don't know why exactly.
		// contentRecommendations = caller.callKAWebservice(
		// "/contentRecommendations?mode=FULL&limit=" + limit + "&offset=" + offset);
		// }
		// catch (Exception e)
		// {
		// e.printStackTrace();
		// }
		//
		// offset += limit;
		//
		// // System.out.println("\n\n----- CRMD -----");
		// // System.out.println(contentRecommendations);
		// // System.out.println("----- CRMD -----\n\n");
		//
		// if (contentRecommendations == null)
		// {
		// failure += limit;
		// total += limit;
		// continue;
		// }
		//
		// Vector<ContentRecommendationRecord> crrs = null;
		//
		// try
		// {
		// hasMore = contentRecommendations.getBoolean("hasMore");
		//
		// crrs = ContentRecommendationRecord.extractFromContent(contentRecommendations,
		// caller);
		// }
		// catch (JSONException e)
		// {
		// e.printStackTrace();
		// }
		//
		// if (crrs == null)
		// {
		// failure += limit;
		// total += limit;
		// continue;
		// }
		//
		// for (ContentRecommendationRecord crr : crrs)
		// {
		// String payloadBody = crr.getCreatePayload();
		// try
		// {
		// cruder.create(payloadBody);
		// success++;
		// }
		// catch (Exception e)
		// {
		// e.printStackTrace();
		// failure++;
		// }
		// total++;
		// }
		// }

		System.out.println("Content Recommendation Update completed!");
		// System.out.println(success + " successful doc ids");
		// System.out.println(failure + " failed doc ids");
		// System.out.println(total + " total doc ids");
		System.out.println("------------------------------------------------------------");

	}

	private Vector<String> getAllContentRecommendationIDs1()
	{
		Vector<String> ret = new Vector<String>();
		Cruder cruder = new Cruder(configuration);
		JSONObject contentRecommendations = null;
		try
		{
			contentRecommendations = caller.callKAWebservice(
					"/contentRecommendations");
			System.out.println(contentRecommendations);
			JSONArray items = contentRecommendations.getJSONArray("items");
			System.out.println("------------------- items :: > " + items.length());
			for (int i = 0; i < items.length(); i++)
			{
				JSONObject item = items.getJSONObject(i);

				String recordId = item.getString("recordId");

				ret.add(recordId);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ret;
	}
	
	
	private Vector<String> getAllContentRecommendationIDs()
	{
		Vector<String> ret = new Vector<String>();
		Cruder cruder = new Cruder(configuration);

		boolean hasMore = true;
		int limit = 1000;
		int offset = 0;

		while (hasMore)
		{
			// System.out.println("--------------------------");
			System.out.println("GID content rec batch offset " + offset);

			JSONObject contentRecommendations = null;
			try
			{
				// note we can also use the mode=FULL& http parameter, but it omits certain results
				// with {links} type JSON objects. Don't know why exactly.
				contentRecommendations = caller.callKAWebservice(
						"/contentRecommendations?limit=" + limit + "&offset=" + offset);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

			offset += limit;

			// System.out.println("\n\n----- CRMD -----");
			// System.out.println(contentRecommendations);
			// System.out.println("----- CRMD -----\n\n");

			if (contentRecommendations == null)
				continue;

			Vector<ContentRecommendationRecord> crrs = null;

			try
			{
				hasMore = contentRecommendations.getBoolean("hasMore");
				// get all recordIDs
				JSONArray items = contentRecommendations.getJSONArray("items");
				for (int i = 0; i < items.length(); i++)
				{
					JSONObject item = items.getJSONObject(i);

					String recordId = item.getString("recordId");

					ret.add(recordId);
				}
			}
			catch (JSONException e)
			{
				e.printStackTrace();
			}
		}
		return ret;
	}

	private String cleanUpJSON(String output)
	{
		output = output.replaceAll("\\\\", "");
		output = output.replace("\"{", "{");
		output = output.replace("}\"", "}");
		output = output.replaceAll("<", "&lt");
		output = output.replaceAll(">", "&gt");

		return output;
	}

	// private JSONObject updateContent(String recordID, JSONObject payloadObject,
	// String authenticationToken, String userToken) throwsJSONException
	// {
	// String internetAddress = String.format("%s/%s",
	// "https://opn-softclouds02-na-ka-irs.rightnowdemo.com/km/api/latest/content",
	// recordID);
	//
	// String message = String.format("Updating content in KA at %s", internetAddress);
	// System.out.println(message);
	//
	// final JSONObject kmAuthTokenObject = new JSONObject();
	//
	// kmAuthTokenObject.put("siteName", "test");
	// kmAuthTokenObject.put("localeId", "");
	// kmAuthTokenObject.put("integrationUserToken", authenticationToken);
	// kmAuthTokenObject.put("userToken", userToken);
	//
	// // Data received from the web service
	// Response response = client.target(internetAddress).request()
	// .header("accept", "application/json").header("content-type", "application/json")
	// .header("kmauthtoken", kmAuthTokenObject.toString())
	// .put(Entity.json(payloadObject.toString()));
	//
	// // Send create content request to web service
	// String output = response.readEntity(String.class);
	//
	// response.close();
	//
	// return new JSONObject(output);
	// }
}
