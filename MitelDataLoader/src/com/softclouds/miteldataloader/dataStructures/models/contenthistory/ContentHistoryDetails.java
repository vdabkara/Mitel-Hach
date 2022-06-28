package com.softclouds.miteldataloader.dataStructures.models.contenthistory;

import org.joda.time.DateTime;

import com.softclouds.miteldataloader.dataStructures.models.menuitems.Locale;

public class ContentHistoryDetails {

	private String autoId =null;
	private String recordId=null;
	private String answerId=null;
	private String documentId=null;
	private DateTime dateOfOperation=null;
	private String contentType=null;
	private String loginId=null;
	private Locale locale=null;
	private String localeLabel=null;
	private String operationType=null;
	private String userDisplayName=null;
	private String fullName = null;
	
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getUserDisplayName() {
		return userDisplayName;
	}
	public void setUserDisplayName(String userDisplayName) {
		this.userDisplayName = userDisplayName;
	}
	public String getAutoId() {
		return autoId;
	}
	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public String getAnswerId() {
		return answerId;
	}
	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}
	public String getDocumentId() {
		return documentId;
	}
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	public DateTime getDateOfOperation() {
		return dateOfOperation;
	}
	public void setDateOfOperation(DateTime dateOfOperation) {
		this.dateOfOperation = dateOfOperation;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	public String getLocaleLabel() {
		return localeLabel;
	}
	public void setLocaleLabel(String localeLabel) {
		this.localeLabel = localeLabel;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
}
