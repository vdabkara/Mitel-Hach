package com.softclouds.miteldataloader.dataStructures.models.userinfo;

public class ErrorDetails {

	private String errorCode=null;
	private String errorMessage=null;
	private String soapEnvelopeErrorResponse=null;
	private String remarks=null;
	
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getSoapEnvelopeErrorResponse() {
		return soapEnvelopeErrorResponse;
	}
	public void setSoapEnvelopeErrorResponse(String soapEnvelopeErrorResponse) {
		this.soapEnvelopeErrorResponse = soapEnvelopeErrorResponse;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
