package com.ka.kcapp.osvc.services;


import org.joda.time.DateTime;

import com.ka.kcapp.datatypes.DataType;
import com.ka.kcapp.menuitems.CrmId;
import com.ka.kcapp.menuitems.TransactionType;
import com.ka.kcapp.vo.CaseHistoryDetails;
public class CaseHistoryRecord {
	
	/**
	 * Gets a payload used for a create() call to Syed's webservice for custom table OSvC uploading.
	 * 
	 * @return
	 */
	public String getCreatePayload(CaseHistoryDetails details)
	{
		DateTime transactionDateTime = new DateTime();
		/*
		 * Sub tract 1 hour from transactionDateTime to keep in sync with recommendation & content history data 
		 */
		long oneHourTime = (1*60*60)*1000;
		long timeValue = transactionDateTime.getMillis() - oneHourTime;
		transactionDateTime = new DateTime(timeValue);
		
		
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
		
		payload += DataType.getPayloadSnippet(DataType.DATE_TIME_VALUE, "transaction_date_time",
				transactionDateTime + "");

		if(null!=details.getCaseNumber() && !"".equals(details.getCaseNumber()))
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "case_number", details.getCaseNumber());	
		}
		else
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "case_number", "NULL");
		}
		
		if(null!=details.getCRM_ID())
		{
			payload += DataType.getPayloadSnippet(DataType.MENU_VALUE, "crm_id",
					(details.getCRM_ID().ordinal()) + "");
		}
		else
		{
			payload += DataType.getPayloadSnippet(DataType.MENU_VALUE, "crm_id",
					(CrmId.NONE.ordinal()) + "");
		}
		
		if(null!=details.getDocumentId() && !"".equals(details.getDocumentId()))
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "doc_id", details.getDocumentId());
		}
		else
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "doc_id", "NULL");
		}
		
		if(null!=details.getUserId() && !"".equals(details.getUserId()))
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "acct_id", details.getUserId());
		}
		else
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "acct_id", "NULL");
		}

		if(null!=details.getFullName() && !"".equals(details.getFullName()))
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "full_name", details.getFullName());
		}
		else
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "full_name", "NULL");
		}
		
		if(null!=details.getKaRecordId() && !"".equals(details.getKaRecordId()))
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "ka_rec_id", details.getKaRecordId());
		}
		else
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "ka_rec_id", "NULL");
		}
		
		if(null!=details.getTRANSACTION_TYPE())
		{
			payload += DataType.getPayloadSnippet(DataType.MENU_VALUE, "transaction_type",
					(details.getTRANSACTION_TYPE().ordinal()) + "");
		}
		else
		{
			payload += DataType.getPayloadSnippet(DataType.MENU_VALUE, "transaction_type",
					(TransactionType.NONE.ordinal()) + "");
		}
		

		if(null!=details.getAnswerId() && !"".equals(details.getAnswerId()))
		{
			payload += DataType.getPayloadSnippet(DataType.INTEGER_VALUE, "a_id", details.getAnswerId());
		}
		
		
		if(null!=details.getCrmUserId() && !"".equals(details.getCrmUserId()))
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "crm_user_id", details.getCrmUserId());
		}
		else
		{
			payload += DataType.getPayloadSnippet(DataType.TEXT_VALUE, "crm_user_id", "NULL");
		}

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
