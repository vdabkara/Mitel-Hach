package com.hach.dataextraction.vo;

import java.util.List;

public class CategoryDetails {
	
	private String documentId=null;
	private String locale=null;
	private String refKey=null;
	private String recordId=null;
	private String name=null;
	private String externalType=null;
	private String baseLocale=null;
	private String answerId=null;
	
	private String level1Name=null;
	private String level1RefKey=null;
	private String level2Name=null;
	private String level2RefKey=null;
	private String level3Name=null;
	private String level3RefKey=null;
	private String level4Name=null;
	private String level4RefKey=null;
	private String level5Name=null;
	private String level5RefKey=null;
	
	private List<CategoryDetails> childList = null;
	
	private String englishName=null;

	private String channelRefKey=null;
	
	private String sfCatName=null;
	private String sfCatRefKey=null;
	private String sfMappingId=null;
	private String sfProcessingStatus=null;
	private String sfErrorMessage=null;
	
	
	
	public String getAnswerId() {
		return answerId;
	}
	public void setAnswerId(String answerId) {
		this.answerId = answerId;
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
	public String getBaseLocale() {
		return baseLocale;
	}
	public void setBaseLocale(String baseLocale) {
		this.baseLocale = baseLocale;
	}
	public String getSfMappingId() {
		return sfMappingId;
	}
	public void setSfMappingId(String sfMappingId) {
		this.sfMappingId = sfMappingId;
	}
	public String getSfProcessingStatus() {
		return sfProcessingStatus;
	}
	public void setSfProcessingStatus(String sfProcessingStatus) {
		this.sfProcessingStatus = sfProcessingStatus;
	}
	public String getSfErrorMessage() {
		return sfErrorMessage;
	}
	public void setSfErrorMessage(String sfErrorMessage) {
		this.sfErrorMessage = sfErrorMessage;
	}
	public String getChannelRefKey() {
		return channelRefKey;
	}
	public void setChannelRefKey(String channelRefKey) {
		this.channelRefKey = channelRefKey;
	}
	public String getEnglishName() {
		return englishName;
	}
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	public List<CategoryDetails> getChildList() {
		return childList;
	}
	public void setChildList(List<CategoryDetails> childList) {
		this.childList = childList;
	}
	public String getLevel1Name() {
		return level1Name;
	}
	public void setLevel1Name(String level1Name) {
		this.level1Name = level1Name;
	}
	public String getLevel1RefKey() {
		return level1RefKey;
	}
	public void setLevel1RefKey(String level1RefKey) {
		this.level1RefKey = level1RefKey;
	}
	public String getLevel2Name() {
		return level2Name;
	}
	public void setLevel2Name(String level2Name) {
		this.level2Name = level2Name;
	}
	public String getLevel2RefKey() {
		return level2RefKey;
	}
	public void setLevel2RefKey(String level2RefKey) {
		this.level2RefKey = level2RefKey;
	}
	public String getLevel3Name() {
		return level3Name;
	}
	public void setLevel3Name(String level3Name) {
		this.level3Name = level3Name;
	}
	public String getLevel3RefKey() {
		return level3RefKey;
	}
	public void setLevel3RefKey(String level3RefKey) {
		this.level3RefKey = level3RefKey;
	}
	public String getLevel4Name() {
		return level4Name;
	}
	public void setLevel4Name(String level4Name) {
		this.level4Name = level4Name;
	}
	public String getLevel4RefKey() {
		return level4RefKey;
	}
	public void setLevel4RefKey(String level4RefKey) {
		this.level4RefKey = level4RefKey;
	}
	public String getLevel5Name() {
		return level5Name;
	}
	public void setLevel5Name(String level5Name) {
		this.level5Name = level5Name;
	}
	public String getLevel5RefKey() {
		return level5RefKey;
	}
	public void setLevel5RefKey(String level5RefKey) {
		this.level5RefKey = level5RefKey;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getRefKey() {
		return refKey;
	}
	public void setRefKey(String refKey) {
		this.refKey = refKey;
	}
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getExternalType() {
		return externalType;
	}
	public void setExternalType(String externalType) {
		this.externalType = externalType;
	}
	public String getDocumentId() {
		return documentId;
	}
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	
	

}
