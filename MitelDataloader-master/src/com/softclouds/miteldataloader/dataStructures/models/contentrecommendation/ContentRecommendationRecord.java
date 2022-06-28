package com.softclouds.miteldataloader.dataStructures.models.contentrecommendation;

import java.io.FileNotFoundException;
import java.util.Vector;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.softclouds.miteldataloader.dataStructures.cruder.DataType;
import com.softclouds.miteldataloader.dataStructures.models.menuitems.Status;
import com.softclouds.miteldataloader.main.OSVCandKAWebServiceCaller;

/**
 * This class models the data that we input into OSvC for a content history record in the custom
 * OSvC table.
 * 
 * 
 * From "Table Designs.xlsx" (recommendation sheet) made by Grace Kim
 * 
 * Field Data Type Description
 * 
 * acct_id Menu OSvC staff account id by group.
 * 
 * ID Integer Unique record id.
 * 
 * status Menu Status of the content recommendation task in Authoring.
 * 
 * date_submitted Date Time Date when the recommendation was submitted by user.
 * 
 * title Text Title of the recommendation. This field is filled out on a web form that appears to
 * users when the Recommend Content button is pressed.
 * 
 */

public class ContentRecommendationRecord
{
	/**
	 * OSvC staff account id by group.
	 */
	private String		accountID;

	/**
	 * Unique record id.
	 */
	private String		recordID;

	/**
	 * Status of the content recommendation task in Authoring.
	 */
	private Status		status;

	/**
	 * Date when the recommendation was submitted by user.
	 */
	private DateTime	dateSubmitted;

	/**
	 * Title of the recommendation. This field is filled out on a web form that appears to users
	 * when the Recommend Content button is pressed.
	 */
	private String		title;

	/**
	 * Answer id of the recommendation
	 */
	private int			answerId;
	/**
	 * Doc id of the recommendation
	 */
	private String		docId;

	/**
	 * Constructor creates a new ContentHistoryRecord from a given 'item' JSONObject from the
	 * 'items' array from a content JSON history extraction.
	 * 
	 * @param item
	 * @param caller
	 * @throws JSONException
	 * @throws FileNotFoundException
	 */
	public ContentRecommendationRecord(JSONObject item, OSVCandKAWebServiceCaller caller)
			throws JSONException, FileNotFoundException, NullPointerException
	{
		// System.out.println("--- content rec initialization");
		// System.out.println("json\n" + item);
		if (item.has("recordId"))
			this.recordID = item.getString("recordId");
		else
			this.recordID = null;

		// account ID
		String userRecordId = item.getString("requestedByUserId");

		JSONObject userInfo = caller.getUserInfo(userRecordId);

		// System.out.println("user info for record ID '" + userRecordId);
		 System.out.println(userInfo);
		 System.out.println("/user info");

		this.accountID = userInfo.getString("login");

		String statusStr = item.getString("status");
		this.status = Status.valueOf(statusStr);

		String dateSubmittedStr = item.getString("dateAdded");

		// "dateAdded": "2016-08-30T10:32:58-0500",
		DateTimeFormatter formatter = DateTimeFormat.forPattern("YYYY'-'MM'-'dd'T'HH:mm:ssZZ");
		this.dateSubmitted = formatter.parseDateTime(dateSubmittedStr);
		// System.out.println(this.title + "\t" + this.status + "\t" + dateSubmittedStr + "\t"
		// + this.dateSubmitted);

		if (item.has("content"))
		{
			this.answerId = item.getJSONObject("content").getInt("answerId");
			this.docId = item.getJSONObject("content").getString("documentId");
		}
		else
		{
			this.answerId = 0;
			this.docId = null;
		}

		this.title = item.getString("title");

		// doc id
		// JSONObject docInfo = caller.getDocInfo(recordID);
		//
		// System.out.println("-----------------------------------------");
		// System.out.println("-----------------------------------------");
		// System.out.println("docinfo is \n" + docInfo);
		// System.out.println("-----------------------------------------");
		// System.out.println("-----------------------------------------");
		// System.out.println("-----------------------------------------");
		// this.docId = docInfo.getString("docID");
	}

	/**
	 * This scans through a
	 * 
	 * @param content
	 * @param caller
	 * @return
	 * @throws JSONException
	 */
	public static Vector<ContentRecommendationRecord> extractFromContent(JSONObject content,
			OSVCandKAWebServiceCaller caller) throws JSONException
	{
		// System.out.println("--------");
		// System.out.println("Extracting Content Recommendation Records from Content
		// Recommendations");

		Vector<ContentRecommendationRecord> ret = new Vector<ContentRecommendationRecord>();

		JSONArray items = content.getJSONArray("items");
		int len = items.length();
		// System.out.println(len + " items");

		int success = 0, failure = 0;

		for (int i = 0; i < len; i++)
		{
			JSONObject item = items.getJSONObject(i);

			// System.out.println("--- item " + i);
			// System.out.println("json\n" + item);

			try
			{
				ContentRecommendationRecord chr = new ContentRecommendationRecord(item, caller);
				ret.add(chr);
				success++;
			}
			catch (Exception e)
			{
				failure++;
				e.printStackTrace();
				System.out.println(
						"WARNING: The following JSON did not have the all fields for a Content Recommendation Record, so we're skipping it");
				System.out.println("json\n" + item);
			}
		}

		// System.out.println(success + " successful extractions");
		// System.out.println(failure + " failed extractions");
		// System.out.println(len + " total records found from webservice call");

		// System.out
		// .println("Done extracting " + items.length() + " Content Recommendation Records!");
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
		payload += "          <q1:TypeName>recommendation</q1:TypeName>\n";
		payload += "        </q1:ObjectType>\n";

		payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "ka_rec_id", this.recordID);
		payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "acct_id", this.accountID);
		// payload += DataType.getPayloadSnippet(DataType.INTEGER_VALUE, "reccord_id",
		// this.recordID);
		payload += DataType.getPayloadSnippet(DataType.MENU_VALUE, "status",
				(this.status.ordinal() + 1) + "");
		payload += DataType.getPayloadSnippet(DataType.DATE_TIME_VALUE, "date_submitted",
				this.dateSubmitted.toString() + "");

		// these may not have been initialized because we have a content recommendation which isn't
		// associated to a document
		// if that's the case, don't add this to the store-data payload for these fields

		// if (this.answerId != -1)
		payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "answer_id", this.answerId + "");

		// if (this.docId != null)
		payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "doc_id", this.docId);

		payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "title", this.title);

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
