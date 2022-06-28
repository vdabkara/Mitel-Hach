package com.ka.kcapp.vo;

import com.ka.kcapp.menuitems.CrmId;
import com.ka.kcapp.menuitems.TransactionType;

public class RecommendationDetails {
	
	private String recommendationId=null;
	private String title=null;
	private String description=null;
	private String caseNumber=null;
	private String priorityType=null;
	private String contentType=null;
	private CrmId CRM_ID = null;
	private TransactionType TRANSACTION_TYPE=null;
	private String userId=null;
	
	private String documentId=null;
	private String answerId = null;
	
	public RecommendationDetails()
	{
		this.recommendationId="";
		this.title="";
		this.description="";
		this.caseNumber="";
		this.contentType="";
		this.priorityType="";
		this.CRM_ID=null;
		this.TRANSACTION_TYPE=TransactionType.RECOMMEND_CONTENT;
		this.userId="";
		this.documentId="";
		this.answerId="";
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


	public String getRecommendationId() {
		return recommendationId;
	}


	public void setRecommendationId(String recommendationId) {
		this.recommendationId = recommendationId;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCaseNumber() {
		return caseNumber;
	}
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
	public String getPriorityType() {
		return priorityType;
	}
	public void setPriorityType(String priorityType) {
		this.priorityType = priorityType;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
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
}