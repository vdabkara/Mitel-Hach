package com.softclouds.miteldataloader.dataStructures.models.userinfo;

public class UserDetails {

	private String autoId=null;
	private String loginId=null;
	private String fullName=null;
	private String emailId=null;
	private String reportingGroup=null;
	private String firstName = null;
	private String lastName = null;
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAutoId() {
		return autoId;
	}
	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getReportingGroup() {
		return reportingGroup;
	}
	public void setReportingGroup(String reportingGroup) {
		this.reportingGroup = reportingGroup;
	}
	
	
	
}
