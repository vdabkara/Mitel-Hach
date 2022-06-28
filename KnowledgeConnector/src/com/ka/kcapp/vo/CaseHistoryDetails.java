package com.ka.kcapp.vo;

import com.ka.kcapp.menuitems.CrmId;
import com.ka.kcapp.menuitems.TransactionType;

public class CaseHistoryDetails {

	private String caseNumber=null;
	private CrmId CRM_ID = null;
	private TransactionType TRANSACTION_TYPE=null;
	private String userId=null;
	private String documentId=null;
	private String answerId = null;
	private String crmUserId=null;
	private String fullName = null;
	private String kaRecordId=null;
	
	
	
	public String getKaRecordId() {
		return kaRecordId;
	}
	public void setKaRecordId(String kaRecordId) {
		this.kaRecordId = kaRecordId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getCrmUserId() {
		return crmUserId;
	}
	public void setCrmUserId(String crmUserId) {
		this.crmUserId = crmUserId;
	}
	public String getDocumentId() {
		return documentId;
	}
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	public String getAnswerId() {
		return answerId;
	}
	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}
	public String getCaseNumber() {
		return caseNumber;
	}
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
	public CrmId getCRM_ID() {
		return CRM_ID;
	}
	public void setCRM_ID(CrmId cRM_ID) {
		CRM_ID = cRM_ID;
	}
	public TransactionType getTRANSACTION_TYPE() {
		return TRANSACTION_TYPE;
	}
	public void setTRANSACTION_TYPE(TransactionType tRANSACTION_TYPE) {
		TRANSACTION_TYPE = tRANSACTION_TYPE;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	
}
