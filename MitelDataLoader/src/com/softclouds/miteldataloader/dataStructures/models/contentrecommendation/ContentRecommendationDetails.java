package com.softclouds.miteldataloader.dataStructures.models.contentrecommendation;

import org.joda.time.DateTime;

import com.softclouds.miteldataloader.dataStructures.models.menuitems.Status;

public class ContentRecommendationDetails {

	private String autoId=null;
	private String title=null;
	private DateTime dateSubmitted=null;
	private String loginId=null;
	private String recordId=null;
	private String answerId=null;
	private String docId=null;
	private String statusLabel=null;
	private Status status=null;
	private String fullName = null;
	
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getAutoId() {
		return autoId;
	}
	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public DateTime getDateSubmitted() {
		return dateSubmitted;
	}
	public void setDateSubmitted(DateTime dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
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
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	public String getStatusLabel() {
		return statusLabel;
	}
	public void setStatusLabel(String statusLabel) {
		this.statusLabel = statusLabel;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
}