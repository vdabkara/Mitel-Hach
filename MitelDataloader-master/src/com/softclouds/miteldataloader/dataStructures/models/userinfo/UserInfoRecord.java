package com.softclouds.miteldataloader.dataStructures.models.userinfo;

import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.softclouds.miteldataloader.dataStructures.cruder.DataType;
import com.softclouds.miteldataloader.main.OSVCandKAWebServiceCaller;
import com.softclouds.miteldataloader.utility.FileUtility;
import com.softclouds.miteldataloader.utility.TimingUtility;

/**
 * This class models the data that we input into OSvC for a user info record in the custom OSvC
 * table.
 * 
 * 
 * From "Table Designs.xlsx" (recommendation sheet) made by Grace Kim
 * 
 * acct_id Text KA console user ID. Called ka_console_user_id in reports.
 * 
 * ID Integer Unique record id.
 * 
 * last_name Text Last name of staff member.
 * 
 * first_name Text First name of staff member.
 * 
 * full_name Text Full name of staff member.
 * 
 * CreatedTime DateTime Date/Time when the record was created in this table. This value is
 * auto-generated by the system when the record is created.
 * 
 * reporting_group Text Group values from OSvC Staff Account.
 * 
 */

public class UserInfoRecord
{
	/**
	 * acct_id Text KA console user ID. Called ka_console_user_id in reports.
	 */
	private String	accountID;

	/**
	 * ka_rec_id Text The KA record id for the record that was fetched from KA.
	 */
	private String	recordID;

	/**
	 * last_name Text Last name of staff member.
	 */
	private String	lastName;

	/**
	 * first_name Text First name of staff member.
	 */
	private String	firstName;

	/**
	 * full_name Text Full name of staff member.
	 */
	private String	fullName;

	/**
	 * email_address Text The user's email address
	 */
	private String	emailAddress;

	/**
	 * CreatedTime DateTime Date/Time when the record was created in this table. This value is
	 * auto-generated by the system when the record is created.
	 */
	// private DateTime createdTime;

	/**
	 * reporting_group_description
	 */
	private String	reportingGroupDescription;

	/**
	 * reporting_group Text Group values from OSvC Staff Account.
	 */
	private String	reportingGroup;

	/**
	 * Constructor creates a new ContentHistoryRecord from a given 'item' JSONObject from the
	 * 'items' array from a content JSON history extraction.
	 * 
	 * @param item
	 * @param caller
	 * @throws JSONException
	 */
	public UserInfoRecord(JSONObject item, OSVCandKAWebServiceCaller caller) throws JSONException
	{

		System.out.println("--- user info initialization");
		System.out.println("json\n" + item);

		this.accountID = item.getString("login");

		this.recordID = item.getInt("id") + "";

		this.lastName = item.getJSONObject("name").getString("last");
		this.firstName = item.getJSONObject("name").getString("first");

		this.fullName = firstName + " " + lastName;

		JSONArray emailLinks = item.getJSONObject("emails").getJSONArray("links");

		if (emailLinks.length() > 0)
		{
			String firstEmailLink = ((JSONObject) emailLinks.get(0)).getString("href");

			int index = firstEmailLink.indexOf("/accounts");

			String subLink = firstEmailLink.substring(index);

			System.out.println("sublink '" + subLink + "'");

			JSONObject linkInfo = caller.callOSvCWebservice(subLink + "/0");

			System.out.println("linkInfo\n" + linkInfo);

			if (linkInfo != null)
				this.emailAddress = linkInfo.getString("address");
			else
				this.emailAddress = null;
		}
		else
		{
			this.emailAddress = null;
		}

		// String dateSubmittedString = item.getString("dateModified");
		// // 2017-02-21T15:37:29-0600
		// DateTimeFormatter formatter = DateTimeFormat.forPattern("YYYY'-'MM'-'dd'T'HH:mm:ssZZ");
		// this.createdTime = formatter.parseDateTime(dateSubmittedString);

		// this.createdTime = DateTime.now();

		// this is optional, it may be null and a user may not be assigned to a staff group
		try
		{
			this.reportingGroup = item.getJSONObject("staffGroup").getString("lookupName");
		}
		catch (JSONException e)
		{
			this.reportingGroup = null;
		}
	}

	/**
	 * This scans through a
	 * 
	 * @param content
	 * @param caller
	 * @return
	 * @throws JSONException
	 */
	public static Vector<UserInfoRecord> extractFromContent(JSONObject content,
			OSVCandKAWebServiceCaller caller) throws JSONException
	{
		System.out.println("--------");
		System.out.println("Extracting User Info Records from User Infos");
		Vector<UserInfoRecord> ret = new Vector<UserInfoRecord>();

		JSONArray items = content.getJSONArray("items");
		int len = items.length();
		System.out.println(len + " items");

		int success = 0, failure = 0;

		Vector<String> userIdsNotInKA = new Vector<String>();

		for (int i = 0; i < len; i++)
		// for (int i = 0; i < 1; i++)
		{
			JSONObject item = items.getJSONObject(i);

			System.out.println("--- item " + i + " of " + len);
			// System.out.println("json\n" + item);
			if(item.getInt("id")==21758)
			{
				try
				{
					// get the detailed information based on the ID.
					int id = item.getInt("id");
					System.out.println("id ::>"+ id);
					JSONObject userInfoJSON = caller.callOSvCWebservice("/accounts/" + id + "/");
					System.out.println("userInfoJSON > "+ userInfoJSON);
					String loginId = userInfoJSON.getString("login");

					// try to get it from KA too to cross-validate
					UserInfoRecord chr = new UserInfoRecord(userInfoJSON, caller);

					try
					{
						JSONObject userInfoKA =
								caller.callKAWebservice("/users/" + loginId.toLowerCase() + "/");
						ret.add(chr);
					}
					catch (Exception e)
					{
						userIdsNotInKA.add(loginId);
						// chr.recordID = "[not in KA]" + chr.recordID;
						// if the user didn't exist in KA, modify the user info record.
					}

					success++;
				}
				catch (JSONException e)
				{
					failure++;
					e.printStackTrace();
					System.out.println(
							"WARNING: The following JSON did not have the all fields for a Content Recommendation Record, so we're skipping it");
					System.out.println("json\n" + item);
				}
			}
		}

		userIdsNotInKA.add(0, userIdsNotInKA.size() + " users are not in KA");

		String successStr = success + " successful extractions";
		String failureStr = failure + " failed extractions";
		String totalStr = len + " total records found from OSvC webservice call";
		System.out.println(successStr);
		System.out.println(failureStr);
		System.out.println(totalStr);

		userIdsNotInKA.add(1, successStr);
		userIdsNotInKA.add(2, failureStr);
		userIdsNotInKA.add(3, totalStr);

		String timeStamp = TimingUtility.getAbsTimeString().replace(' ', '_');
		String failFileName = FileUtility.getPath("data", "mitelDataloader", "failed_users")
				+ "failed_users_" + timeStamp + ".txt";

		System.out.println("List of users which has failed is located here " + failFileName
				+ "\ncwd = " + FileUtility.getCurrentWorkingDirectory());

		FileUtility.write(failFileName, userIdsNotInKA);

		System.out.println("Done extracting " + items.length() + " User Info Records!");
		return ret;
	}

	/**
	 * Gets a payload used for a create() call to Syed's webservice for custom table OSvC uploading.
	 * 
	 * @return
	 */
	public String getCreatePayload()
	{
		String payload = "";

		payload +=
				"  <s:Body xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n";
		payload += "    <Create xmlns=\"urn:messages.ws.rightnow.com/v1_3\">\n";
		payload +=
				"      <RNObjects xmlns:q1=\"urn:generic.ws.rightnow.com/v1_3\" xsi:type=\"q1:GenericObject\">\n";
		payload += "        <q1:ObjectType>\n";
		payload += "          <q1:Namespace>KMS</q1:Namespace>\n";
		payload += "          <q1:TypeName>user_info</q1:TypeName>\n";
		payload += "        </q1:ObjectType>\n";

		payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "acct_id",
				this.accountID.toLowerCase());
		payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "ka_rec_id", this.recordID);
		payload +=
				DataType.getPayloadSnippet(DataType.TEXT_VALUE, "email_address", this.emailAddress);
		payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "last_name", this.lastName);
		payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "first_name", this.firstName);
		payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "full_name", this.fullName);
		// payload += DataType.getPayloadSnippet(DataType.DATE_TIME_VALUE, "CreatedTime",
		// this.createdTime.toString() + "");

		if (this.reportingGroup != null)
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "reporting_group",
					this.reportingGroup + "");

		// TODO: reporting group description

		payload += "      </RNObjects>\n";
		payload += "      <ProcessingOptions>\n";
		payload += "        <SuppressExternalEvents>false</SuppressExternalEvents>\n";
		payload += "        <SuppressRules>false</SuppressRules>\n";
		payload += "      </ProcessingOptions>\n";
		payload += "    </Create>\n";
		payload += "  </s:Body>\n";

		return payload;
	}
}