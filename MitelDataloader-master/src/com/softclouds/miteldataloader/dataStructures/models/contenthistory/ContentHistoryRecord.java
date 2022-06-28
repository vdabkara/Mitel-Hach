package com.softclouds.miteldataloader.dataStructures.models.contenthistory;

import java.io.FileNotFoundException;
import java.util.Vector;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.softclouds.miteldataloader.dataStructures.cruder.DataType;
import com.softclouds.miteldataloader.dataStructures.models.menuitems.Locale;
import com.softclouds.miteldataloader.main.OSVCandKAWebServiceCaller;

/**
 * This class models the data that we input into OSvC for a content history record in the custom
 * OSvC table.
 * 
 * 
 * From "Table Designs.xlsx" (content_history sheet) made by Grace Kim
 * 
 * Field Data Type Description
 * 
 * content_type Text KA content type of the document.
 * 
 * locale Menu Locale of the document.
 * 
 * version_num Integer Version number of the KA article. This column assumes that both major and
 * minor versions can appear in the column.
 * 
 * acct_id Menu OSvC staff account id by group.
 * 
 * operation_type Menu The types of operations performed in KA Authoring.
 * 
 * date_of_operation Date time The date and time related to the content operation in the record.
 * This should be specific to the doc id, and not to all the major versions of the doc id.
 * 
 * doc_id Text doc id of KA article, NOT the answer id.
 * 
 * ID Integer Unique record id.
 */

public class ContentHistoryRecord
{
	/**
	 * KA content type of the document.
	 */
	private String		contentType;

	/**
	 * Locale of the document.
	 */
	private Locale		locale;

	/**
	 * Version number of the KA article. This column assumes that both major and minor versions can
	 * appear in the column.
	 */
	private double		version;

	/**
	 * OSvC staff account id by group.
	 */
	private String		accountID;

	/**
	 * The types of operations performed in KA Authoring.
	 */
	private String		operationType;

	/**
	 * The date and time related to the content operation in the record. This should be specific to
	 * the doc id, and not to all the major versions of the doc id.
	 */
	private DateTime	dateOfOperation;

	/**
	 * doc id of the KA article, NOT the answer id
	 */
	private String		docID;

	/**
	 * Unique record id.
	 */
	private String		recordId;

	/**
	 * Constructor creates a new ContentHistoryRecord from a given 'item' JSONObject from the
	 * 'items' array from a content JSON history extraction.
	 * 
	 * @param item
	 * @param caller
	 * @throws JSONException
	 * @throws FileNotFoundException
	 */
	public ContentHistoryRecord(JSONObject item, OSVCandKAWebServiceCaller caller)
			throws JSONException, FileNotFoundException
	{
		// System.out.println("chr item\n" + item);

		JSONObject content = item.getJSONObject("content");

		this.recordId = content.getString("recordId");

		// get the content type from the actual content itself
		JSONObject wsContent = caller.callKAWebservice("/content/" + this.recordId);
		this.contentType = wsContent.getJSONObject("contentType").getString("referenceKey");

		String localeStr = content.getJSONObject("locale").getString("recordId").toUpperCase();
		this.locale = Locale.valueOf(localeStr);

		String versionStr = content.getString("version");
		this.version = Double.parseDouble(versionStr);

		// some temp values for these three
		String userRecordId = item.getString("userInformationId");

		try
		{
			JSONObject userInfo = caller.getUserInfo(userRecordId);

			// System.out.println("user info for record ID '" + userRecordId);
			// System.out.println(userInfo);
			// System.out.println("/user info");
			this.accountID = userInfo.getString("login");
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			throw new UnsupportedOperationException(
					"Could not get account info for content history record, account is '"
							+ accountID + "'");
		}

		this.operationType = item.getString("action");

		String dateOfOperationString = item.getString("dateModified");
		DateTimeFormatter formatter = DateTimeFormat.forPattern("YYYY'-'MM'-'dd'T'HH:mm:ssZZ");
		this.dateOfOperation = formatter.parseDateTime(dateOfOperationString);

		// document id
		this.docID = item.getJSONObject("content").getString("documentId");
		this.recordId = item.getString("recordId");
	}

	/**
	 * This scans through a
	 * 
	 * @param content
	 * @param caller
	 * @return
	 * @throws JSONException
	 */
	public static Vector<ContentHistoryRecord> extractFromContent(JSONObject content,
			OSVCandKAWebServiceCaller caller)
	{
		Vector<ContentHistoryRecord> ret = new Vector<ContentHistoryRecord>();

		try
		{
			JSONArray items = content.getJSONArray("items");
			System.out.println(items.length() + " items found.");

			for (int i = 0; i < items.length(); i++)
			{
				try
				{
					JSONObject item = items.getJSONObject(i);
					ContentHistoryRecord chr = new ContentHistoryRecord(item, caller);
					ret.add(chr);
					System.out.println((i + 1) + " of " + items.length() + " completed.");
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			System.out.println("Done extracting content history records");
			return ret;

		}
		catch (JSONException e)
		{
			e.printStackTrace();
			return ret;
		}
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
				"<s:Body xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n";
		payload += "  <Create xmlns=\"urn:messages.ws.rightnow.com/v1_3\">\n";
		payload +=
				"    <RNObjects xmlns:q1=\"urn:generic.ws.rightnow.com/v1_3\" xsi:type=\"q1:GenericObject\">\n";
		payload += "      <q1:ObjectType>\n";
		payload += "        <q1:Namespace>KMS</q1:Namespace>\n";
		payload += "        <q1:TypeName>content_history</q1:TypeName>\n";
		payload += "      </q1:ObjectType>\n";

		payload +=
				DataType.getPayloadSnippet(DataType.TEXT_VALUE, "content_type", this.contentType);
		payload += DataType.getPayloadSnippet(DataType.MENU_VALUE, "locale",
				(this.locale.ordinal() + 1) + "");

		// payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "version_num",
		// StringUtility.round(this.version, 1).replace('.', ' ') + "");

		// payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "version_num", "X");

		payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "acct_id",
				this.accountID.toString() + "");
		payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "operation_type",
				this.operationType);
		payload += DataType.getPayloadSnippet(DataType.DATE_TIME_VALUE, "date_of_operation",
				this.dateOfOperation + "");
		payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "doc_id", this.docID + "");
		payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "ka_rec_id", this.recordId + "");

		payload += "    </RNObjects>\n";
		payload += "    <ProcessingOptions>\n";
		payload += "      <SuppressExternalEvents>false</SuppressExternalEvents>\n";
		payload += "      <SuppressRules>false</SuppressRules>\n";
		payload += "    </ProcessingOptions>\n";
		payload += "  </Create>\n";
		payload += "</s:Body>\n";

		return payload;
	}
}
