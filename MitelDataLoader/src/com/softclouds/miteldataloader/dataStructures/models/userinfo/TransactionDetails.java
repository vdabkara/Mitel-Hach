package com.softclouds.miteldataloader.dataStructures.models.userinfo;

import java.util.ArrayList;

import com.softclouds.miteldataloader.dataStructures.models.contenthistory.ContentHistoryDetails;
import com.softclouds.miteldataloader.dataStructures.models.contentrecommendation.ContentRecommendationDetails;

public class TransactionDetails {

	private String payloadBody=null;
	private String payloadHeaader=null;
	private String soapEnvelopeRequest=null;
	private String soapEnvelopeResponse=null;
	
	private String endpointAddress=null;
	private UserDetails userDetails=null;
	
	private String operationType=null;
	private String processingStatus=null;
	
	private boolean hasError = false;
	private ArrayList<ErrorDetails> errorList = new ArrayList<ErrorDetails>();
	
	private ContentRecommendationDetails recommendationDetails = null;
	
	private ContentHistoryDetails contentHistoryDetails = null;
	
	public ContentHistoryDetails getContentHistoryDetails() {
		return contentHistoryDetails;
	}
	public void setContentHistoryDetails(ContentHistoryDetails contentHistoryDetails) {
		this.contentHistoryDetails = contentHistoryDetails;
	}
	public ContentRecommendationDetails getRecommendationDetails() {
		return recommendationDetails;
	}
	public void setRecommendationDetails(
			ContentRecommendationDetails recommendationDetails) {
		this.recommendationDetails = recommendationDetails;
	}
	public ArrayList<ErrorDetails> getErrorList() {
		return errorList;
	}
	public void setErrorList(ArrayList<ErrorDetails> errorList) {
		this.errorList = errorList;
	}
	public String getSoapEnvelopeRequest() {
		return soapEnvelopeRequest;
	}
	public void setSoapEnvelopeRequest(String soapEnvelopeRequest) {
		this.soapEnvelopeRequest = soapEnvelopeRequest;
	}
	public String getSoapEnvelopeResponse() {
		return soapEnvelopeResponse;
	}
	public void setSoapEnvelopeResponse(String soapEnvelopeResponse) {
		this.soapEnvelopeResponse = soapEnvelopeResponse;
	}
	public boolean isHasError() {
		return hasError;
	}
	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public String getProcessingStatus() {
		return processingStatus;
	}
	public void setProcessingStatus(String processingStatus) {
		this.processingStatus = processingStatus;
	}
	public String getPayloadBody() {
		return payloadBody;
	}
	public void setPayloadBody(String payloadBody) {
		this.payloadBody = payloadBody;
	}
	public String getPayloadHeaader() {
		return payloadHeaader;
	}
	public void setPayloadHeaader(String payloadHeaader) {
		this.payloadHeaader = payloadHeaader;
	}
	public String getEndpointAddress() {
		return endpointAddress;
	}
	public void setEndpointAddress(String endpointAddress) {
		this.endpointAddress = endpointAddress;
	}
	public UserDetails getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}
}