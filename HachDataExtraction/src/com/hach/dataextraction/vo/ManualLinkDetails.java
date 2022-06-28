package com.hach.dataextraction.vo;

public class ManualLinkDetails {

	private String documentId=null;
	private String locale=null;
	private String recordId=null;
	private String versionId=null;
	private String versionNumber=null;
	private String answerId=null;
	private String title=null;
	
	private ContentDetails contentDetails = null;

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getVersionId() {
		return versionId;
	}

	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}

	public String getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}

	public String getAnswerId() {
		return answerId;
	}

	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ContentDetails getContentDetails() {
		return contentDetails;
	}

	public void setContentDetails(ContentDetails contentDetails) {
		this.contentDetails = contentDetails;
	}
	
}
