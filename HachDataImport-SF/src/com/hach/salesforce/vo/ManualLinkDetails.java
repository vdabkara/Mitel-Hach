package com.hach.salesforce.vo;

public class ManualLinkDetails {

	
	private long autoId;
	private String documentId=null;
	private String locale=null;
	private String sfMappingStatus=null;
	private String sfMappedDocUrl=null;
	private String sfMappingError=null;
	private String sfArticleNumber=null;
	private String title=null;
	private String sfRecordId=null;
	
	
	
	public String getSfRecordId() {
		return sfRecordId;
	}
	public void setSfRecordId(String sfRecordId) {
		this.sfRecordId = sfRecordId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSfArticleNumber() {
		return sfArticleNumber;
	}
	public void setSfArticleNumber(String sfArticleNumber) {
		this.sfArticleNumber = sfArticleNumber;
	}
	public long getAutoId() {
		return autoId;
	}
	public void setAutoId(long autoId) {
		this.autoId = autoId;
	}
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
	public String getSfMappingStatus() {
		return sfMappingStatus;
	}
	public void setSfMappingStatus(String sfMappingStatus) {
		this.sfMappingStatus = sfMappingStatus;
	}
	public String getSfMappedDocUrl() {
		return sfMappedDocUrl;
	}
	public void setSfMappedDocUrl(String sfMappedDocUrl) {
		this.sfMappedDocUrl = sfMappedDocUrl;
	}
	public String getSfMappingError() {
		return sfMappingError;
	}
	public void setSfMappingError(String sfMappingError) {
		this.sfMappingError = sfMappingError;
	}
	
}
