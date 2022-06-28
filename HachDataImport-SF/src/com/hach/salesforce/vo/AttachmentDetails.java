package com.hach.salesforce.vo;

public class AttachmentDetails {

	private String documentId=null;
	private String locale=null;
	private String attachmentName=null;
	private String sourcePath=null;
	private String attachmentType=null;
	private String kaResourcePath=null;
	
	private String errorMessage=null;
	private String channelRefKey=null;
	private String status=null;
	private long autoId;
	
	private String salesForceFieldName=null;
	
	private String size=null;
	
	private String cdnUrl = null;
	
	
	private String processingStatus=null;
	private String mappingStatus=null;
	
	private String targetUrl=null;
	
	public String getTargetUrl() {
		return targetUrl;
	}
	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}
	public String getProcessingStatus() {
		return processingStatus;
	}
	public void setProcessingStatus(String processingStatus) {
		this.processingStatus = processingStatus;
	}
	public String getMappingStatus() {
		return mappingStatus;
	}
	public void setMappingStatus(String mappingStatus) {
		this.mappingStatus = mappingStatus;
	}
	public String getCdnUrl() {
		return cdnUrl;
	}
	public void setCdnUrl(String cdnUrl) {
		this.cdnUrl = cdnUrl;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getSalesForceFieldName() {
		return salesForceFieldName;
	}
	public void setSalesForceFieldName(String salesForceFieldName) {
		this.salesForceFieldName = salesForceFieldName;
	}
	public long getAutoId() {
		return autoId;
	}
	public void setAutoId(long autoId) {
		this.autoId = autoId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getChannelRefKey() {
		return channelRefKey;
	}
	public void setChannelRefKey(String channelRefKey) {
		this.channelRefKey = channelRefKey;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getAttachmentName() {
		return attachmentName;
	}
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}
	public String getSourcePath() {
		return sourcePath;
	}
	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}
	public String getAttachmentType() {
		return attachmentType;
	}
	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}
	public String getKaResourcePath() {
		return kaResourcePath;
	}
	public void setKaResourcePath(String kaResourcePath) {
		this.kaResourcePath = kaResourcePath;
	}
	public String getDocumentId() {
		return documentId;
	}
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	
	
	
	
	
}
