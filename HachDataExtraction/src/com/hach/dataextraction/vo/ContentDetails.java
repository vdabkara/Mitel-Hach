package com.hach.dataextraction.vo;

import java.util.List;

import org.json.JSONObject;

public class ContentDetails {
	
	private long autoId;
	private String channelRefKey=null;
	private String recordId=null;
	private String versionId=null;
	private String documentId=null;
	private String title=null;
	private String versionNumber=null;
	private String answerId=null;
	private String locale=null;
	private String resourcePath=null;
	
	private JSONObject contentObject=null;
	private String schemaData=null;
	
	
	private List<CategoryDetails> categoryList = null;
	private List<AttachmentDetails> attachmentsList=null;
	
	private String errorCodes=null;
	private String errorMessage=null;
	
	private List<UserGroupDetails> userGroupsList = null;
	private List<ViewDetails> viewsList = null;
	
	private String ownerName=null;
	private String lastModifierName=null;
	private String ownerEmail=null;
	private String lastModifierEmail=null;
	
	private String baseLocale=null;
	private String publishDate=null;
	private String createDate=null;
	private String lastModifiedDate=null;
	private String displayEndDate=null;
	
	private String metaDataXML = null;
	private String docArchived=null;
	
	private String sfRecordId=null;
	private String sfURLName=null;
	private String sfdocumentURL=null;
	private String sfArticleNumber=null;
	private String sfArticleId=null;
	private String sfChannelId=null;
	private String sfErrorMessage=null;
	private String sfProcessingStatus=null;
	private String sfRemarks=null;
	private String sfLocale=null;
	private String sfMasterIdentifier=null;
	private String allInnerLinksMapped = null;
	private String allCategoriesMapped = null;
	
	
	private String kaCatRefKey=null;
	private String sfCatName=null;
	private String sfCatRefKey=null;
	
	
	
	public String getKaCatRefKey() {
		return kaCatRefKey;
	}
	public void setKaCatRefKey(String kaCatRefKey) {
		this.kaCatRefKey = kaCatRefKey;
	}
	public String getSfCatName() {
		return sfCatName;
	}
	public void setSfCatName(String sfCatName) {
		this.sfCatName = sfCatName;
	}
	public String getSfCatRefKey() {
		return sfCatRefKey;
	}
	public void setSfCatRefKey(String sfCatRefKey) {
		this.sfCatRefKey = sfCatRefKey;
	}
	public String getSfRecordId() {
		return sfRecordId;
	}
	public void setSfRecordId(String sfRecordId) {
		this.sfRecordId = sfRecordId;
	}
	public String getSfURLName() {
		return sfURLName;
	}
	public void setSfURLName(String sfURLName) {
		this.sfURLName = sfURLName;
	}
	public String getSfdocumentURL() {
		return sfdocumentURL;
	}
	public void setSfdocumentURL(String sfdocumentURL) {
		this.sfdocumentURL = sfdocumentURL;
	}
	public String getSfArticleNumber() {
		return sfArticleNumber;
	}
	public void setSfArticleNumber(String sfArticleNumber) {
		this.sfArticleNumber = sfArticleNumber;
	}
	public String getSfArticleId() {
		return sfArticleId;
	}
	public void setSfArticleId(String sfArticleId) {
		this.sfArticleId = sfArticleId;
	}
	public String getSfChannelId() {
		return sfChannelId;
	}
	public void setSfChannelId(String sfChannelId) {
		this.sfChannelId = sfChannelId;
	}
	public String getSfErrorMessage() {
		return sfErrorMessage;
	}
	public void setSfErrorMessage(String sfErrorMessage) {
		this.sfErrorMessage = sfErrorMessage;
	}
	public String getSfProcessingStatus() {
		return sfProcessingStatus;
	}
	public void setSfProcessingStatus(String sfProcessingStatus) {
		this.sfProcessingStatus = sfProcessingStatus;
	}
	public String getSfRemarks() {
		return sfRemarks;
	}
	public void setSfRemarks(String sfRemarks) {
		this.sfRemarks = sfRemarks;
	}
	public String getSfLocale() {
		return sfLocale;
	}
	public void setSfLocale(String sfLocale) {
		this.sfLocale = sfLocale;
	}
	public String getSfMasterIdentifier() {
		return sfMasterIdentifier;
	}
	public void setSfMasterIdentifier(String sfMasterIdentifier) {
		this.sfMasterIdentifier = sfMasterIdentifier;
	}
	public String getAllInnerLinksMapped() {
		return allInnerLinksMapped;
	}
	public void setAllInnerLinksMapped(String allInnerLinksMapped) {
		this.allInnerLinksMapped = allInnerLinksMapped;
	}
	public String getAllCategoriesMapped() {
		return allCategoriesMapped;
	}
	public void setAllCategoriesMapped(String allCategoriesMapped) {
		this.allCategoriesMapped = allCategoriesMapped;
	}
	public String getDocArchived() {
		return docArchived;
	}
	public void setDocArchived(String docArchived) {
		this.docArchived = docArchived;
	}
	public String getMetaDataXML() {
		return metaDataXML;
	}
	public void setMetaDataXML(String metaDataXML) {
		this.metaDataXML = metaDataXML;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getLastModifierName() {
		return lastModifierName;
	}
	public void setLastModifierName(String lastModifierName) {
		this.lastModifierName = lastModifierName;
	}
	public String getOwnerEmail() {
		return ownerEmail;
	}
	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}
	public String getLastModifierEmail() {
		return lastModifierEmail;
	}
	public void setLastModifierEmail(String lastModifierEmail) {
		this.lastModifierEmail = lastModifierEmail;
	}
	public String getBaseLocale() {
		return baseLocale;
	}
	public void setBaseLocale(String baseLocale) {
		this.baseLocale = baseLocale;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public String getDisplayEndDate() {
		return displayEndDate;
	}
	public void setDisplayEndDate(String displayEndDate) {
		this.displayEndDate = displayEndDate;
	}
	public List<UserGroupDetails> getUserGroupsList() {
		return userGroupsList;
	}
	public void setUserGroupsList(List<UserGroupDetails> userGroupsList) {
		this.userGroupsList = userGroupsList;
	}
	public List<ViewDetails> getViewsList() {
		return viewsList;
	}
	public void setViewsList(List<ViewDetails> viewsList) {
		this.viewsList = viewsList;
	}
	public long getAutoId() {
		return autoId;
	}
	public void setAutoId(long autoId) {
		this.autoId = autoId;
	}
	public String getErrorCodes() {
		return errorCodes;
	}
	public void setErrorCodes(String errorCodes) {
		this.errorCodes = errorCodes;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
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
	public String getDocumentId() {
		return documentId;
	}
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getChannelRefKey() {
		return channelRefKey;
	}
	public void setChannelRefKey(String channelRefKey) {
		this.channelRefKey = channelRefKey;
	}
	public String getResourcePath() {
		return resourcePath;
	}
	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}
	public JSONObject getContentObject() {
		return contentObject;
	}
	public void setContentObject(JSONObject contentObject) {
		this.contentObject = contentObject;
	}
	public String getSchemaData() {
		return schemaData;
	}
	public void setSchemaData(String schemaData) {
		this.schemaData = schemaData;
	}
	public List<CategoryDetails> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<CategoryDetails> categoryList) {
		this.categoryList = categoryList;
	}
	public List<AttachmentDetails> getAttachmentsList() {
		return attachmentsList;
	}
	public void setAttachmentsList(List<AttachmentDetails> attachmentsList) {
		this.attachmentsList = attachmentsList;
	}
}
