package com.softclouds.miteldataloader.dataStructures.models.casehistory;

import java.io.FileNotFoundException;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.json.JSONException;
import org.json.JSONObject;

import com.softclouds.miteldataloader.dataStructures.cruder.DataType;
import com.softclouds.miteldataloader.dataStructures.models.menuitems.CrmId;
import com.softclouds.miteldataloader.dataStructures.models.menuitems.TransactionType;
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

public class CaseHistoryRecord
{
	final static Logger		logger		= Logger.getLogger(CaseHistoryRecord.class);

	/**
	 * The date and time related to the content operation in the record. This should be specific to
	 * the doc id, and not to all the major versions of the doc id.
	 */
	// private DateTime dataloadDate;

	/**
	 * The transaction id of the link / unlink
	 */
	// private String transactionId;
	private TransactionType	transactionType;
	private DateTime		transactionDateTime;
	private CrmId			crmId;
	private String			accountId	= "";
	private String			answerId	= "";
	private String			docId		= "";

	// should this be a String in OSVC? We are getting it from 'incidentId', which has strings in
	// it.
	private String			caseNumber	= "";
	private int				linkUnlink	= 0;

	/**
	 * Constructor creates a new ContentHistoryRecord from a given 'item' JSONObject from the
	 * 'items' array from a content JSON history extraction.
	 * 
	 * {
	 * 
	 * "answerId": 3702,
	 * 
	 * "action": "addLink",
	 * 
	 * "description": "Hot desk agents cannot transfer if Record-a-call is enabled",
	 * 
	 * "incidentId": "RQST00000552040",
	 * 
	 * "title": "The Hot List for April, 2007",
	 * 
	 * "im_doc_id": "HT959"
	 * 
	 * }
	 * 
	 * @param item
	 * @param transactionType
	 * @param
	 * @param caller
	 * @throws JSONException
	 * @throws FileNotFoundException
	 */
	public CaseHistoryRecord(JSONObject item, TransactionType transactionType, CrmId crmId,
			String accountId, OSVCandKAWebServiceCaller caller)
			throws JSONException, FileNotFoundException
	{
		logger.debug("chr item\n" + item);

		// this.dataloadDate = DateTime.now();

		// transaction id is some random integer... what's this?!
		// this.transactionId = item.getString("recordId");
		this.transactionType = transactionType;
		this.transactionDateTime = DateTime.now();

		this.crmId = crmId;
		this.accountId = accountId;
		this.answerId = item.getInt("answerId") + "";
		this.docId = item.getString("im_doc_id");

		this.caseNumber = item.getString("incidentId");

		if (transactionType == TransactionType.LINK)
			this.linkUnlink = 1;
		else if (transactionType == TransactionType.UNLINK)
			this.linkUnlink = -1;
		else
			this.linkUnlink = 0;
	}

	public CaseHistoryRecord(TransactionType transactionType, CrmId crmId, String accountId,
			String answerId, String docId, String caseNumber, OSVCandKAWebServiceCaller caller)
			throws JSONException, FileNotFoundException
	{
		// this.dataloadDate = DateTime.now();

		// transaction id is some random integer... what's this?!
		// this.transactionId = item.getString("recordId");
		this.transactionType = transactionType;
		this.transactionDateTime = DateTime.now();

		this.crmId = crmId;
		this.accountId = accountId;
		this.answerId = answerId;
		this.docId = docId;

		JSONObject docInfo = caller.getDocInfoByDocId(this.docId);

		logger.debug("docInfo\n" + docInfo);
		this.caseNumber = caseNumber;

		if (transactionType == TransactionType.LINK)
			this.linkUnlink = 1;
		else if (transactionType == TransactionType.UNLINK)
			this.linkUnlink = -1;
		else
			this.linkUnlink = 0;
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
		payload += "        <q1:TypeName>case_history</q1:TypeName>\n";
		payload += "      </q1:ObjectType>\n";

		// private int transactionId;
		// private String transactionType;
		// private DateTime transactionDateTime;
		// private String crmId;
		// private String accountId;
		// private String answerId;
		// private String docId;
		// private String caseNumber;
		// private String linkUnlink;

		// payload += DataType.getPayloadSnippet(DataType.DATE_TIME_VALUE, "dateload_date",
		// this.dataloadDate + "");

		// payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "transaction_id",
		// this.transactionId);
		payload += DataType.getPayloadSnippet(DataType.DATE_TIME_VALUE, "transaction_date_time",
				this.transactionDateTime + "");

		payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "case_number", this.caseNumber);
		payload += DataType.getPayloadSnippet(DataType.MENU_VALUE, "crm_id",
				(this.crmId.ordinal() + 1) + "");
		payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "doc_id", this.docId);
		payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "acct_id", this.accountId);

		payload += DataType.getPayloadSnippet(DataType.MENU_VALUE, "transaction_type",
				(this.transactionType.ordinal() + 1) + "");

		// don't put 0 values, which were for 'recommend'
		if (this.linkUnlink != 0)
			payload += DataType.getPayloadSnippet(DataType.INTEGER_VALUE, "link_unlink_value",
					this.linkUnlink + "");
		payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "answer_id", this.answerId);

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
