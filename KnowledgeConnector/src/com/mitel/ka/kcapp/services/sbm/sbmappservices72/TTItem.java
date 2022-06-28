
/**
 * TTItem.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.2  Built on : May 02, 2016 (05:56:08 BST)
 */

package com.mitel.ka.kcapp.services.sbm.sbmappservices72;

/**
 * TTItem bean class
 */
@SuppressWarnings({ "unchecked", "unused" })

public class TTItem implements org.apache.axis2.databinding.ADBBean {
	/*
	 * This type was generated from the piece of schema that had name = TTItem
	 * Namespace URI = urn:sbmappservices72 Namespace Prefix =
	 */

	/**
	 * field for Id
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.ItemIdentifier localId;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localIdTracker = false;

	public boolean isIdSpecified() {
		return localIdTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.ItemIdentifier
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.ItemIdentifier getId() {
		return localId;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Id
	 */
	public void setId(com.mitel.ka.kcapp.services.sbm.sbmappservices72.ItemIdentifier param) {
		localIdTracker = true;

		this.localId = param;

	}

	/**
	 * field for ItemType
	 */

	protected java.lang.String localItemType;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localItemTypeTracker = false;

	public boolean isItemTypeSpecified() {
		return localItemTypeTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getItemType() {
		return localItemType;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            ItemType
	 */
	public void setItemType(java.lang.String param) {
		localItemTypeTracker = true;

		this.localItemType = param;

	}

	/**
	 * field for Project
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.ProjectIdentifier localProject;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localProjectTracker = false;

	public boolean isProjectSpecified() {
		return localProjectTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.
	 *         ProjectIdentifier
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.ProjectIdentifier getProject() {
		return localProject;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Project
	 */
	public void setProject(com.mitel.ka.kcapp.services.sbm.sbmappservices72.ProjectIdentifier param) {
		localProjectTracker = true;

		this.localProject = param;

	}

	/**
	 * field for Title
	 */

	protected java.lang.String localTitle;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localTitleTracker = false;

	public boolean isTitleSpecified() {
		return localTitleTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getTitle() {
		return localTitle;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Title
	 */
	public void setTitle(java.lang.String param) {
		localTitleTracker = true;

		this.localTitle = param;

	}

	/**
	 * field for Description
	 */

	protected java.lang.String localDescription;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localDescriptionTracker = false;

	public boolean isDescriptionSpecified() {
		return localDescriptionTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getDescription() {
		return localDescription;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Description
	 */
	public void setDescription(java.lang.String param) {
		localDescriptionTracker = true;

		this.localDescription = param;

	}

	/**
	 * field for CreatedBy
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserIdentifier localCreatedBy;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localCreatedByTracker = false;

	public boolean isCreatedBySpecified() {
		return localCreatedByTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserIdentifier
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserIdentifier getCreatedBy() {
		return localCreatedBy;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            CreatedBy
	 */
	public void setCreatedBy(com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserIdentifier param) {
		localCreatedByTracker = true;

		this.localCreatedBy = param;

	}

	/**
	 * field for CreateDate
	 */

	protected java.util.Calendar localCreateDate;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localCreateDateTracker = false;

	public boolean isCreateDateSpecified() {
		return localCreateDateTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return java.util.Calendar
	 */
	public java.util.Calendar getCreateDate() {
		return localCreateDate;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            CreateDate
	 */
	public void setCreateDate(java.util.Calendar param) {
		localCreateDateTracker = true;

		this.localCreateDate = param;

	}

	/**
	 * field for ModifiedBy
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserIdentifier localModifiedBy;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localModifiedByTracker = false;

	public boolean isModifiedBySpecified() {
		return localModifiedByTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserIdentifier
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserIdentifier getModifiedBy() {
		return localModifiedBy;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            ModifiedBy
	 */
	public void setModifiedBy(com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserIdentifier param) {
		localModifiedByTracker = true;

		this.localModifiedBy = param;

	}

	/**
	 * field for ModifiedDate
	 */

	protected java.util.Calendar localModifiedDate;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localModifiedDateTracker = false;

	public boolean isModifiedDateSpecified() {
		return localModifiedDateTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return java.util.Calendar
	 */
	public java.util.Calendar getModifiedDate() {
		return localModifiedDate;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            ModifiedDate
	 */
	public void setModifiedDate(java.util.Calendar param) {
		localModifiedDateTracker = true;

		this.localModifiedDate = param;

	}

	/**
	 * field for ActiveInactive
	 */

	protected java.lang.String localActiveInactive;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localActiveInactiveTracker = false;

	public boolean isActiveInactiveSpecified() {
		return localActiveInactiveTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getActiveInactive() {
		return localActiveInactive;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            ActiveInactive
	 */
	public void setActiveInactive(java.lang.String param) {
		localActiveInactiveTracker = true;

		this.localActiveInactive = param;

	}

	/**
	 * field for State
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.StateIdentifier localState;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localStateTracker = false;

	public boolean isStateSpecified() {
		return localStateTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.StateIdentifier
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.StateIdentifier getState() {
		return localState;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            State
	 */
	public void setState(com.mitel.ka.kcapp.services.sbm.sbmappservices72.StateIdentifier param) {
		localStateTracker = true;

		this.localState = param;

	}

	/**
	 * field for Owner
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserIdentifier localOwner;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localOwnerTracker = false;

	public boolean isOwnerSpecified() {
		return localOwnerTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserIdentifier
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserIdentifier getOwner() {
		return localOwner;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Owner
	 */
	public void setOwner(com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserIdentifier param) {
		localOwnerTracker = true;

		this.localOwner = param;

	}

	/**
	 * field for Url
	 */

	protected java.lang.String localUrl;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localUrlTracker = false;

	public boolean isUrlSpecified() {
		return localUrlTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getUrl() {
		return localUrl;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Url
	 */
	public void setUrl(java.lang.String param) {
		localUrlTracker = true;

		this.localUrl = param;

	}

	/**
	 * field for ExtendedField This was an Array!
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.NameValue[] localExtendedField;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localExtendedFieldTracker = false;

	public boolean isExtendedFieldSpecified() {
		return localExtendedFieldTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.NameValue[]
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.NameValue[] getExtendedField() {
		return localExtendedField;
	}

	/**
	 * validate the array for ExtendedField
	 */
	protected void validateExtendedField(com.mitel.ka.kcapp.services.sbm.sbmappservices72.NameValue[] param) {

	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            ExtendedField
	 */
	public void setExtendedField(com.mitel.ka.kcapp.services.sbm.sbmappservices72.NameValue[] param) {

		validateExtendedField(param);

		localExtendedFieldTracker = param != null;

		this.localExtendedField = param;
	}

	/**
	 * Auto generated add method for the array for convenience
	 * 
	 * @param param
	 *            com.mitel.ka.kcapp.services.sbm.sbmappservices72.NameValue
	 */
	public void addExtendedField(com.mitel.ka.kcapp.services.sbm.sbmappservices72.NameValue param) {
		if (localExtendedField == null) {
			localExtendedField = new com.mitel.ka.kcapp.services.sbm.sbmappservices72.NameValue[] {};
		}

		// update the setting tracker
		localExtendedFieldTracker = true;

		java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localExtendedField);
		list.add(param);
		this.localExtendedField = (com.mitel.ka.kcapp.services.sbm.sbmappservices72.NameValue[]) list
				.toArray(new com.mitel.ka.kcapp.services.sbm.sbmappservices72.NameValue[list.size()]);

	}

	/**
	 * field for Note This was an Array!
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.Note[] localNote;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localNoteTracker = false;

	public boolean isNoteSpecified() {
		return localNoteTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.Note[]
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.Note[] getNote() {
		return localNote;
	}

	/**
	 * validate the array for Note
	 */
	protected void validateNote(com.mitel.ka.kcapp.services.sbm.sbmappservices72.Note[] param) {

	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Note
	 */
	public void setNote(com.mitel.ka.kcapp.services.sbm.sbmappservices72.Note[] param) {

		validateNote(param);

		localNoteTracker = param != null;

		this.localNote = param;
	}

	/**
	 * Auto generated add method for the array for convenience
	 * 
	 * @param param
	 *            com.mitel.ka.kcapp.services.sbm.sbmappservices72.Note
	 */
	public void addNote(com.mitel.ka.kcapp.services.sbm.sbmappservices72.Note param) {
		if (localNote == null) {
			localNote = new com.mitel.ka.kcapp.services.sbm.sbmappservices72.Note[] {};
		}

		// update the setting tracker
		localNoteTracker = true;

		java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localNote);
		list.add(param);
		this.localNote = (com.mitel.ka.kcapp.services.sbm.sbmappservices72.Note[]) list
				.toArray(new com.mitel.ka.kcapp.services.sbm.sbmappservices72.Note[list.size()]);

	}

	/**
	 * field for ItemLink This was an Array!
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.ItemLink[] localItemLink;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localItemLinkTracker = false;

	public boolean isItemLinkSpecified() {
		return localItemLinkTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.ItemLink[]
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.ItemLink[] getItemLink() {
		return localItemLink;
	}

	/**
	 * validate the array for ItemLink
	 */
	protected void validateItemLink(com.mitel.ka.kcapp.services.sbm.sbmappservices72.ItemLink[] param) {

	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            ItemLink
	 */
	public void setItemLink(com.mitel.ka.kcapp.services.sbm.sbmappservices72.ItemLink[] param) {

		validateItemLink(param);

		localItemLinkTracker = param != null;

		this.localItemLink = param;
	}

	/**
	 * Auto generated add method for the array for convenience
	 * 
	 * @param param
	 *            com.mitel.ka.kcapp.services.sbm.sbmappservices72.ItemLink
	 */
	public void addItemLink(com.mitel.ka.kcapp.services.sbm.sbmappservices72.ItemLink param) {
		if (localItemLink == null) {
			localItemLink = new com.mitel.ka.kcapp.services.sbm.sbmappservices72.ItemLink[] {};
		}

		// update the setting tracker
		localItemLinkTracker = true;

		java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localItemLink);
		list.add(param);
		this.localItemLink = (com.mitel.ka.kcapp.services.sbm.sbmappservices72.ItemLink[]) list
				.toArray(new com.mitel.ka.kcapp.services.sbm.sbmappservices72.ItemLink[list.size()]);

	}

	/**
	 * field for UrlAttachment This was an Array!
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.URLAttachment[] localUrlAttachment;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localUrlAttachmentTracker = false;

	public boolean isUrlAttachmentSpecified() {
		return localUrlAttachmentTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.URLAttachment[]
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.URLAttachment[] getUrlAttachment() {
		return localUrlAttachment;
	}

	/**
	 * validate the array for UrlAttachment
	 */
	protected void validateUrlAttachment(com.mitel.ka.kcapp.services.sbm.sbmappservices72.URLAttachment[] param) {

	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            UrlAttachment
	 */
	public void setUrlAttachment(com.mitel.ka.kcapp.services.sbm.sbmappservices72.URLAttachment[] param) {

		validateUrlAttachment(param);

		localUrlAttachmentTracker = param != null;

		this.localUrlAttachment = param;
	}

	/**
	 * Auto generated add method for the array for convenience
	 * 
	 * @param param
	 *            com.mitel.ka.kcapp.services.sbm.sbmappservices72.URLAttachment
	 */
	public void addUrlAttachment(com.mitel.ka.kcapp.services.sbm.sbmappservices72.URLAttachment param) {
		if (localUrlAttachment == null) {
			localUrlAttachment = new com.mitel.ka.kcapp.services.sbm.sbmappservices72.URLAttachment[] {};
		}

		// update the setting tracker
		localUrlAttachmentTracker = true;

		java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localUrlAttachment);
		list.add(param);
		this.localUrlAttachment = (com.mitel.ka.kcapp.services.sbm.sbmappservices72.URLAttachment[]) list
				.toArray(new com.mitel.ka.kcapp.services.sbm.sbmappservices72.URLAttachment[list.size()]);

	}

	/**
	 * field for FileAttachment This was an Array!
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.FileAttachment[] localFileAttachment;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localFileAttachmentTracker = false;

	public boolean isFileAttachmentSpecified() {
		return localFileAttachmentTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.FileAttachment[]
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.FileAttachment[] getFileAttachment() {
		return localFileAttachment;
	}

	/**
	 * validate the array for FileAttachment
	 */
	protected void validateFileAttachment(com.mitel.ka.kcapp.services.sbm.sbmappservices72.FileAttachment[] param) {

	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            FileAttachment
	 */
	public void setFileAttachment(com.mitel.ka.kcapp.services.sbm.sbmappservices72.FileAttachment[] param) {

		validateFileAttachment(param);

		localFileAttachmentTracker = param != null;

		this.localFileAttachment = param;
	}

	/**
	 * Auto generated add method for the array for convenience
	 * 
	 * @param param
	 *            com.mitel.ka.kcapp.services.sbm.sbmappservices72.
	 *            FileAttachment
	 */
	public void addFileAttachment(com.mitel.ka.kcapp.services.sbm.sbmappservices72.FileAttachment param) {
		if (localFileAttachment == null) {
			localFileAttachment = new com.mitel.ka.kcapp.services.sbm.sbmappservices72.FileAttachment[] {};
		}

		// update the setting tracker
		localFileAttachmentTracker = true;

		java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localFileAttachment);
		list.add(param);
		this.localFileAttachment = (com.mitel.ka.kcapp.services.sbm.sbmappservices72.FileAttachment[]) list
				.toArray(new com.mitel.ka.kcapp.services.sbm.sbmappservices72.FileAttachment[list.size()]);

	}

	/**
	 * field for Subtasks
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.SubTasks localSubtasks;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localSubtasksTracker = false;

	public boolean isSubtasksSpecified() {
		return localSubtasksTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.SubTasks
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.SubTasks getSubtasks() {
		return localSubtasks;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Subtasks
	 */
	public void setSubtasks(com.mitel.ka.kcapp.services.sbm.sbmappservices72.SubTasks param) {
		localSubtasksTracker = true;

		this.localSubtasks = param;

	}

	/**
	 * field for ExtendedData
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.ExtendedData localExtendedData;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localExtendedDataTracker = false;

	public boolean isExtendedDataSpecified() {
		return localExtendedDataTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.ExtendedData
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.ExtendedData getExtendedData() {
		return localExtendedData;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            ExtendedData
	 */
	public void setExtendedData(com.mitel.ka.kcapp.services.sbm.sbmappservices72.ExtendedData param) {
		localExtendedDataTracker = true;

		this.localExtendedData = param;

	}

	/**
	 * field for ExtraElement This was an Array!
	 */

	protected org.apache.axiom.om.OMElement[] localExtraElement;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localExtraElementTracker = false;

	public boolean isExtraElementSpecified() {
		return localExtraElementTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return org.apache.axiom.om.OMElement[]
	 */
	public org.apache.axiom.om.OMElement[] getExtraElement() {
		return localExtraElement;
	}

	/**
	 * validate the array for ExtraElement
	 */
	protected void validateExtraElement(org.apache.axiom.om.OMElement[] param) {

	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            ExtraElement
	 */
	public void setExtraElement(org.apache.axiom.om.OMElement[] param) {

		validateExtraElement(param);

		localExtraElementTracker = param != null;

		this.localExtraElement = param;
	}

	/**
	 * Auto generated add method for the array for convenience
	 * 
	 * @param param
	 *            org.apache.axiom.om.OMElement
	 */
	public void addExtraElement(org.apache.axiom.om.OMElement param) {
		if (localExtraElement == null) {
			localExtraElement = new org.apache.axiom.om.OMElement[] {};
		}

		// update the setting tracker
		localExtraElementTracker = true;

		java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localExtraElement);
		list.add(param);
		this.localExtraElement = (org.apache.axiom.om.OMElement[]) list
				.toArray(new org.apache.axiom.om.OMElement[list.size()]);

	}

	/**
	 *
	 * @param parentQName
	 * @param factory
	 * @return org.apache.axiom.om.OMElement
	 */
	public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName,
			final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

		return factory.createOMElement(new org.apache.axis2.databinding.ADBDataSource(this, parentQName));

	}

	public void serialize(final javax.xml.namespace.QName parentQName, javax.xml.stream.XMLStreamWriter xmlWriter)
			throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {
		serialize(parentQName, xmlWriter, false);
	}

	public void serialize(final javax.xml.namespace.QName parentQName, javax.xml.stream.XMLStreamWriter xmlWriter,
			boolean serializeType)
			throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException {

		java.lang.String prefix = null;
		java.lang.String namespace = null;

		prefix = parentQName.getPrefix();
		namespace = parentQName.getNamespaceURI();
		writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);

		if (serializeType) {

			java.lang.String namespacePrefix = registerPrefix(xmlWriter, "urn:sbmappservices72");
			if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", namespacePrefix + ":TTItem",
						xmlWriter);
			} else {
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "TTItem", xmlWriter);
			}

		}
		if (localIdTracker) {
			if (localId == null) {

				writeStartElement(null, "urn:sbmappservices72", "id", xmlWriter);

				// write the nil attribute
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
				xmlWriter.writeEndElement();
			} else {
				localId.serialize(new javax.xml.namespace.QName("urn:sbmappservices72", "id"), xmlWriter);
			}
		}
		if (localItemTypeTracker) {
			namespace = "urn:sbmappservices72";
			writeStartElement(null, namespace, "itemType", xmlWriter);

			if (localItemType == null) {
				// write the nil attribute

				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

			} else {

				xmlWriter.writeCharacters(localItemType);

			}

			xmlWriter.writeEndElement();
		}
		if (localProjectTracker) {
			if (localProject == null) {

				writeStartElement(null, "urn:sbmappservices72", "project", xmlWriter);

				// write the nil attribute
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
				xmlWriter.writeEndElement();
			} else {
				localProject.serialize(new javax.xml.namespace.QName("urn:sbmappservices72", "project"), xmlWriter);
			}
		}
		if (localTitleTracker) {
			namespace = "urn:sbmappservices72";
			writeStartElement(null, namespace, "title", xmlWriter);

			if (localTitle == null) {
				// write the nil attribute

				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

			} else {

				xmlWriter.writeCharacters(localTitle);

			}

			xmlWriter.writeEndElement();
		}
		if (localDescriptionTracker) {
			namespace = "urn:sbmappservices72";
			writeStartElement(null, namespace, "description", xmlWriter);

			if (localDescription == null) {
				// write the nil attribute

				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

			} else {

				xmlWriter.writeCharacters(localDescription);

			}

			xmlWriter.writeEndElement();
		}
		if (localCreatedByTracker) {
			if (localCreatedBy == null) {

				writeStartElement(null, "urn:sbmappservices72", "createdBy", xmlWriter);

				// write the nil attribute
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
				xmlWriter.writeEndElement();
			} else {
				localCreatedBy.serialize(new javax.xml.namespace.QName("urn:sbmappservices72", "createdBy"), xmlWriter);
			}
		}
		if (localCreateDateTracker) {
			namespace = "urn:sbmappservices72";
			writeStartElement(null, namespace, "createDate", xmlWriter);

			if (localCreateDate == null) {
				// write the nil attribute

				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

			} else {

				xmlWriter.writeCharacters(
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCreateDate));

			}

			xmlWriter.writeEndElement();
		}
		if (localModifiedByTracker) {
			if (localModifiedBy == null) {

				writeStartElement(null, "urn:sbmappservices72", "modifiedBy", xmlWriter);

				// write the nil attribute
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
				xmlWriter.writeEndElement();
			} else {
				localModifiedBy.serialize(new javax.xml.namespace.QName("urn:sbmappservices72", "modifiedBy"),
						xmlWriter);
			}
		}
		if (localModifiedDateTracker) {
			namespace = "urn:sbmappservices72";
			writeStartElement(null, namespace, "modifiedDate", xmlWriter);

			if (localModifiedDate == null) {
				// write the nil attribute

				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

			} else {

				xmlWriter.writeCharacters(
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localModifiedDate));

			}

			xmlWriter.writeEndElement();
		}
		if (localActiveInactiveTracker) {
			namespace = "urn:sbmappservices72";
			writeStartElement(null, namespace, "activeInactive", xmlWriter);

			if (localActiveInactive == null) {
				// write the nil attribute

				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

			} else {

				xmlWriter.writeCharacters(localActiveInactive);

			}

			xmlWriter.writeEndElement();
		}
		if (localStateTracker) {
			if (localState == null) {

				writeStartElement(null, "urn:sbmappservices72", "state", xmlWriter);

				// write the nil attribute
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
				xmlWriter.writeEndElement();
			} else {
				localState.serialize(new javax.xml.namespace.QName("urn:sbmappservices72", "state"), xmlWriter);
			}
		}
		if (localOwnerTracker) {
			if (localOwner == null) {

				writeStartElement(null, "urn:sbmappservices72", "owner", xmlWriter);

				// write the nil attribute
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
				xmlWriter.writeEndElement();
			} else {
				localOwner.serialize(new javax.xml.namespace.QName("urn:sbmappservices72", "owner"), xmlWriter);
			}
		}
		if (localUrlTracker) {
			namespace = "urn:sbmappservices72";
			writeStartElement(null, namespace, "url", xmlWriter);

			if (localUrl == null) {
				// write the nil attribute

				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

			} else {

				xmlWriter.writeCharacters(localUrl);

			}

			xmlWriter.writeEndElement();
		}
		if (localExtendedFieldTracker) {
			if (localExtendedField != null) {
				for (int i = 0; i < localExtendedField.length; i++) {
					if (localExtendedField[i] != null) {
						localExtendedField[i].serialize(
								new javax.xml.namespace.QName("urn:sbmappservices72", "extendedField"), xmlWriter);
					} else {

						// we don't have to do any thing since minOccures is
						// zero

					}

				}
			} else {

				throw new org.apache.axis2.databinding.ADBException("extendedField cannot be null!!");

			}
		}
		if (localNoteTracker) {
			if (localNote != null) {
				for (int i = 0; i < localNote.length; i++) {
					if (localNote[i] != null) {
						localNote[i].serialize(new javax.xml.namespace.QName("urn:sbmappservices72", "note"),
								xmlWriter);
					} else {

						// we don't have to do any thing since minOccures is
						// zero

					}

				}
			} else {

				throw new org.apache.axis2.databinding.ADBException("note cannot be null!!");

			}
		}
		if (localItemLinkTracker) {
			if (localItemLink != null) {
				for (int i = 0; i < localItemLink.length; i++) {
					if (localItemLink[i] != null) {
						localItemLink[i].serialize(new javax.xml.namespace.QName("urn:sbmappservices72", "itemLink"),
								xmlWriter);
					} else {

						// we don't have to do any thing since minOccures is
						// zero

					}

				}
			} else {

				throw new org.apache.axis2.databinding.ADBException("itemLink cannot be null!!");

			}
		}
		if (localUrlAttachmentTracker) {
			if (localUrlAttachment != null) {
				for (int i = 0; i < localUrlAttachment.length; i++) {
					if (localUrlAttachment[i] != null) {
						localUrlAttachment[i].serialize(
								new javax.xml.namespace.QName("urn:sbmappservices72", "urlAttachment"), xmlWriter);
					} else {

						// we don't have to do any thing since minOccures is
						// zero

					}

				}
			} else {

				throw new org.apache.axis2.databinding.ADBException("urlAttachment cannot be null!!");

			}
		}
		if (localFileAttachmentTracker) {
			if (localFileAttachment != null) {
				for (int i = 0; i < localFileAttachment.length; i++) {
					if (localFileAttachment[i] != null) {
						localFileAttachment[i].serialize(
								new javax.xml.namespace.QName("urn:sbmappservices72", "fileAttachment"), xmlWriter);
					} else {

						// we don't have to do any thing since minOccures is
						// zero

					}

				}
			} else {

				throw new org.apache.axis2.databinding.ADBException("fileAttachment cannot be null!!");

			}
		}
		if (localSubtasksTracker) {
			if (localSubtasks == null) {

				writeStartElement(null, "urn:sbmappservices72", "subtasks", xmlWriter);

				// write the nil attribute
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
				xmlWriter.writeEndElement();
			} else {
				localSubtasks.serialize(new javax.xml.namespace.QName("urn:sbmappservices72", "subtasks"), xmlWriter);
			}
		}
		if (localExtendedDataTracker) {
			if (localExtendedData == null) {

				writeStartElement(null, "urn:sbmappservices72", "extendedData", xmlWriter);

				// write the nil attribute
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
				xmlWriter.writeEndElement();
			} else {
				localExtendedData.serialize(new javax.xml.namespace.QName("urn:sbmappservices72", "extendedData"),
						xmlWriter);
			}
		}
		if (localExtraElementTracker) {

			if (localExtraElement != null) {
				for (int i = 0; i < localExtraElement.length; i++) {
					if (localExtraElement[i] != null) {
						localExtraElement[i].serialize(xmlWriter);
					} else {

						// we have to do nothing since minOccures zero

					}
				}
			} else {
				throw new org.apache.axis2.databinding.ADBException("extraElement cannot be null!!");
			}
		}
		xmlWriter.writeEndElement();

	}

	private static java.lang.String generatePrefix(java.lang.String namespace) {
		if (namespace.equals("urn:sbmappservices72")) {
			return "";
		}
		return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
	}

	/**
	 * Utility method to write an element start tag.
	 */
	private void writeStartElement(java.lang.String prefix, java.lang.String namespace, java.lang.String localPart,
			javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
		java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
		if (writerPrefix != null) {
			xmlWriter.writeStartElement(writerPrefix, localPart, namespace);
		} else {
			if (namespace.length() == 0) {
				prefix = "";
			} else if (prefix == null) {
				prefix = generatePrefix(namespace);
			}

			xmlWriter.writeStartElement(prefix, localPart, namespace);
			xmlWriter.writeNamespace(prefix, namespace);
			xmlWriter.setPrefix(prefix, namespace);
		}
	}

	/**
	 * Util method to write an attribute with the ns prefix
	 */
	private void writeAttribute(java.lang.String prefix, java.lang.String namespace, java.lang.String attName,
			java.lang.String attValue, javax.xml.stream.XMLStreamWriter xmlWriter)
			throws javax.xml.stream.XMLStreamException {
		java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
		if (writerPrefix != null) {
			xmlWriter.writeAttribute(writerPrefix, namespace, attName, attValue);
		} else {
			xmlWriter.writeNamespace(prefix, namespace);
			xmlWriter.setPrefix(prefix, namespace);
			xmlWriter.writeAttribute(prefix, namespace, attName, attValue);
		}
	}

	/**
	 * Util method to write an attribute without the ns prefix
	 */
	private void writeAttribute(java.lang.String namespace, java.lang.String attName, java.lang.String attValue,
			javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
		if (namespace.equals("")) {
			xmlWriter.writeAttribute(attName, attValue);
		} else {
			xmlWriter.writeAttribute(registerPrefix(xmlWriter, namespace), namespace, attName, attValue);
		}
	}

	/**
	 * Util method to write an attribute without the ns prefix
	 */
	private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
			javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
			throws javax.xml.stream.XMLStreamException {

		java.lang.String attributeNamespace = qname.getNamespaceURI();
		java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
		if (attributePrefix == null) {
			attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
		}
		java.lang.String attributeValue;
		if (attributePrefix.trim().length() > 0) {
			attributeValue = attributePrefix + ":" + qname.getLocalPart();
		} else {
			attributeValue = qname.getLocalPart();
		}

		if (namespace.equals("")) {
			xmlWriter.writeAttribute(attName, attributeValue);
		} else {
			registerPrefix(xmlWriter, namespace);
			xmlWriter.writeAttribute(attributePrefix, namespace, attName, attributeValue);
		}
	}

	/**
	 * method to handle Qnames
	 */

	private void writeQName(javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter)
			throws javax.xml.stream.XMLStreamException {
		java.lang.String namespaceURI = qname.getNamespaceURI();
		if (namespaceURI != null) {
			java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
			if (prefix == null) {
				prefix = generatePrefix(namespaceURI);
				xmlWriter.writeNamespace(prefix, namespaceURI);
				xmlWriter.setPrefix(prefix, namespaceURI);
			}

			if (prefix.trim().length() > 0) {
				xmlWriter.writeCharacters(
						prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			} else {
				// i.e this is the default namespace
				xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
			}

		} else {
			xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
		}
	}

	private void writeQNames(javax.xml.namespace.QName[] qnames, javax.xml.stream.XMLStreamWriter xmlWriter)
			throws javax.xml.stream.XMLStreamException {

		if (qnames != null) {
			// we have to store this data until last moment since it is not
			// possible to write any
			// namespace data after writing the charactor data
			java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
			java.lang.String namespaceURI = null;
			java.lang.String prefix = null;

			for (int i = 0; i < qnames.length; i++) {
				if (i > 0) {
					stringToWrite.append(" ");
				}
				namespaceURI = qnames[i].getNamespaceURI();
				if (namespaceURI != null) {
					prefix = xmlWriter.getPrefix(namespaceURI);
					if ((prefix == null) || (prefix.length() == 0)) {
						prefix = generatePrefix(namespaceURI);
						xmlWriter.writeNamespace(prefix, namespaceURI);
						xmlWriter.setPrefix(prefix, namespaceURI);
					}

					if (prefix.trim().length() > 0) {
						stringToWrite.append(prefix).append(":")
								.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
					} else {
						stringToWrite
								.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
					}
				} else {
					stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
				}
			}
			xmlWriter.writeCharacters(stringToWrite.toString());
		}

	}

	/**
	 * Register a namespace prefix
	 */
	private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace)
			throws javax.xml.stream.XMLStreamException {
		java.lang.String prefix = xmlWriter.getPrefix(namespace);
		if (prefix == null) {
			prefix = generatePrefix(namespace);
			javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
			while (true) {
				java.lang.String uri = nsContext.getNamespaceURI(prefix);
				if (uri == null || uri.length() == 0) {
					break;
				}
				prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
			}
			xmlWriter.writeNamespace(prefix, namespace);
			xmlWriter.setPrefix(prefix, namespace);
		}
		return prefix;
	}

	/**
	 * Factory class that keeps the parse method
	 */
	public static class Factory {
		private static org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(Factory.class);

		/**
		 * static method to create the object Precondition: If this object is an
		 * element, the current or next start element starts this object and any
		 * intervening reader events are ignorable If this object is not an
		 * element, it is a complex type and the reader is at the event just
		 * after the outer start element Postcondition: If this object is an
		 * element, the reader is positioned at its end element If this object
		 * is a complex type, the reader is positioned at the end element of its
		 * outer element
		 */
		public static TTItem parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
			TTItem object = new TTItem();

			int event;
			javax.xml.namespace.QName currentQName = null;
			java.lang.String nillableValue = null;
			java.lang.String prefix = "";
			java.lang.String namespaceuri = "";
			try {

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				currentQName = reader.getName();

				if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type") != null) {
					java.lang.String fullTypeName = reader
							.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "type");
					if (fullTypeName != null) {
						java.lang.String nsPrefix = null;
						if (fullTypeName.indexOf(":") > -1) {
							nsPrefix = fullTypeName.substring(0, fullTypeName.indexOf(":"));
						}
						nsPrefix = nsPrefix == null ? "" : nsPrefix;

						java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":") + 1);

						if (!"TTItem".equals(type)) {
							// find namespace for the prefix
							java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
							return (TTItem) com.mitel.ka.kcapp.services.sbm.serenadiagnostics.ExtensionMapper
									.getTypeObject(nsUri, type, reader);
						}

					}

				}

				// Note all attributes that were handled. Used to differ normal
				// attributes
				// from anyAttributes.
				java.util.Vector handledAttributes = new java.util.Vector();

				reader.next();

				java.util.ArrayList list14 = new java.util.ArrayList();

				java.util.ArrayList list15 = new java.util.ArrayList();

				java.util.ArrayList list16 = new java.util.ArrayList();

				java.util.ArrayList list17 = new java.util.ArrayList();

				java.util.ArrayList list18 = new java.util.ArrayList();

				java.util.ArrayList list21 = new java.util.ArrayList();

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "id").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "id").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						object.setId(null);
						reader.next();

						reader.next();

					} else {

						object.setId(
								com.mitel.ka.kcapp.services.sbm.sbmappservices72.ItemIdentifier.Factory.parse(reader));

						reader.next();
					}
				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "itemType").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "itemType").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

						java.lang.String content = reader.getElementText();

						object.setItemType(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

					} else {

						reader.getElementText(); // throw away text nodes if
													// any.
					}

					reader.next();

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "project").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "project").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						object.setProject(null);
						reader.next();

						reader.next();

					} else {

						object.setProject(com.mitel.ka.kcapp.services.sbm.sbmappservices72.ProjectIdentifier.Factory
								.parse(reader));

						reader.next();
					}
				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "title").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "title").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

						java.lang.String content = reader.getElementText();

						object.setTitle(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

					} else {

						reader.getElementText(); // throw away text nodes if
													// any.
					}

					reader.next();

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "description").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "description").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

						java.lang.String content = reader.getElementText();

						object.setDescription(
								org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

					} else {

						reader.getElementText(); // throw away text nodes if
													// any.
					}

					reader.next();

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "createdBy").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "createdBy").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						object.setCreatedBy(null);
						reader.next();

						reader.next();

					} else {

						object.setCreatedBy(
								com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserIdentifier.Factory.parse(reader));

						reader.next();
					}
				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "createDate").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "createDate").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

						java.lang.String content = reader.getElementText();

						object.setCreateDate(
								org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(content));

					} else {

						reader.getElementText(); // throw away text nodes if
													// any.
					}

					reader.next();

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "modifiedBy").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "modifiedBy").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						object.setModifiedBy(null);
						reader.next();

						reader.next();

					} else {

						object.setModifiedBy(
								com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserIdentifier.Factory.parse(reader));

						reader.next();
					}
				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "modifiedDate")
								.equals(reader.getName())
						|| new javax.xml.namespace.QName("", "modifiedDate").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

						java.lang.String content = reader.getElementText();

						object.setModifiedDate(
								org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(content));

					} else {

						reader.getElementText(); // throw away text nodes if
													// any.
					}

					reader.next();

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "activeInactive")
								.equals(reader.getName())
						|| new javax.xml.namespace.QName("", "activeInactive").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

						java.lang.String content = reader.getElementText();

						object.setActiveInactive(
								org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

					} else {

						reader.getElementText(); // throw away text nodes if
													// any.
					}

					reader.next();

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "state").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "state").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						object.setState(null);
						reader.next();

						reader.next();

					} else {

						object.setState(
								com.mitel.ka.kcapp.services.sbm.sbmappservices72.StateIdentifier.Factory.parse(reader));

						reader.next();
					}
				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "owner").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "owner").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						object.setOwner(null);
						reader.next();

						reader.next();

					} else {

						object.setOwner(
								com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserIdentifier.Factory.parse(reader));

						reader.next();
					}
				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "url").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "url").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

						java.lang.String content = reader.getElementText();

						object.setUrl(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

					} else {

						reader.getElementText(); // throw away text nodes if
													// any.
					}

					reader.next();

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "extendedField")
								.equals(reader.getName())
						|| new javax.xml.namespace.QName("", "extendedField").equals(reader.getName())) {

					// Process the array and step past its final element's end.

					list14.add(com.mitel.ka.kcapp.services.sbm.sbmappservices72.NameValue.Factory.parse(reader));

					// loop until we find a start element that is not part of
					// this array
					boolean loopDone14 = false;
					while (!loopDone14) {
						// We should be at the end element, but make sure
						while (!reader.isEndElement())
							reader.next();
						// Step out of this element
						reader.next();
						// Step to next element event.
						while (!reader.isStartElement() && !reader.isEndElement())
							reader.next();
						if (reader.isEndElement()) {
							// two continuous end elements means we are exiting
							// the xml structure
							loopDone14 = true;
						} else {
							if (new javax.xml.namespace.QName("urn:sbmappservices72", "extendedField")
									.equals(reader.getName())) {
								list14.add(com.mitel.ka.kcapp.services.sbm.sbmappservices72.NameValue.Factory
										.parse(reader));

							} else {
								loopDone14 = true;
							}
						}
					}
					// call the converter utility to convert and set the array

					object.setExtendedField(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.NameValue[]) org.apache.axis2.databinding.utils.ConverterUtil
									.convertToArray(com.mitel.ka.kcapp.services.sbm.sbmappservices72.NameValue.class,
											list14));

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "note").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "note").equals(reader.getName())) {

					// Process the array and step past its final element's end.

					list15.add(com.mitel.ka.kcapp.services.sbm.sbmappservices72.Note.Factory.parse(reader));

					// loop until we find a start element that is not part of
					// this array
					boolean loopDone15 = false;
					while (!loopDone15) {
						// We should be at the end element, but make sure
						while (!reader.isEndElement())
							reader.next();
						// Step out of this element
						reader.next();
						// Step to next element event.
						while (!reader.isStartElement() && !reader.isEndElement())
							reader.next();
						if (reader.isEndElement()) {
							// two continuous end elements means we are exiting
							// the xml structure
							loopDone15 = true;
						} else {
							if (new javax.xml.namespace.QName("urn:sbmappservices72", "note")
									.equals(reader.getName())) {
								list15.add(com.mitel.ka.kcapp.services.sbm.sbmappservices72.Note.Factory.parse(reader));

							} else {
								loopDone15 = true;
							}
						}
					}
					// call the converter utility to convert and set the array

					object.setNote(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.Note[]) org.apache.axis2.databinding.utils.ConverterUtil
									.convertToArray(com.mitel.ka.kcapp.services.sbm.sbmappservices72.Note.class,
											list15));

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "itemLink").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "itemLink").equals(reader.getName())) {

					// Process the array and step past its final element's end.

					list16.add(com.mitel.ka.kcapp.services.sbm.sbmappservices72.ItemLink.Factory.parse(reader));

					// loop until we find a start element that is not part of
					// this array
					boolean loopDone16 = false;
					while (!loopDone16) {
						// We should be at the end element, but make sure
						while (!reader.isEndElement())
							reader.next();
						// Step out of this element
						reader.next();
						// Step to next element event.
						while (!reader.isStartElement() && !reader.isEndElement())
							reader.next();
						if (reader.isEndElement()) {
							// two continuous end elements means we are exiting
							// the xml structure
							loopDone16 = true;
						} else {
							if (new javax.xml.namespace.QName("urn:sbmappservices72", "itemLink")
									.equals(reader.getName())) {
								list16.add(com.mitel.ka.kcapp.services.sbm.sbmappservices72.ItemLink.Factory
										.parse(reader));

							} else {
								loopDone16 = true;
							}
						}
					}
					// call the converter utility to convert and set the array

					object.setItemLink(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.ItemLink[]) org.apache.axis2.databinding.utils.ConverterUtil
									.convertToArray(com.mitel.ka.kcapp.services.sbm.sbmappservices72.ItemLink.class,
											list16));

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "urlAttachment")
								.equals(reader.getName())
						|| new javax.xml.namespace.QName("", "urlAttachment").equals(reader.getName())) {

					// Process the array and step past its final element's end.

					list17.add(com.mitel.ka.kcapp.services.sbm.sbmappservices72.URLAttachment.Factory.parse(reader));

					// loop until we find a start element that is not part of
					// this array
					boolean loopDone17 = false;
					while (!loopDone17) {
						// We should be at the end element, but make sure
						while (!reader.isEndElement())
							reader.next();
						// Step out of this element
						reader.next();
						// Step to next element event.
						while (!reader.isStartElement() && !reader.isEndElement())
							reader.next();
						if (reader.isEndElement()) {
							// two continuous end elements means we are exiting
							// the xml structure
							loopDone17 = true;
						} else {
							if (new javax.xml.namespace.QName("urn:sbmappservices72", "urlAttachment")
									.equals(reader.getName())) {
								list17.add(com.mitel.ka.kcapp.services.sbm.sbmappservices72.URLAttachment.Factory
										.parse(reader));

							} else {
								loopDone17 = true;
							}
						}
					}
					// call the converter utility to convert and set the array

					object.setUrlAttachment(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.URLAttachment[]) org.apache.axis2.databinding.utils.ConverterUtil
									.convertToArray(
											com.mitel.ka.kcapp.services.sbm.sbmappservices72.URLAttachment.class,
											list17));

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "fileAttachment")
								.equals(reader.getName())
						|| new javax.xml.namespace.QName("", "fileAttachment").equals(reader.getName())) {

					// Process the array and step past its final element's end.

					list18.add(com.mitel.ka.kcapp.services.sbm.sbmappservices72.FileAttachment.Factory.parse(reader));

					// loop until we find a start element that is not part of
					// this array
					boolean loopDone18 = false;
					while (!loopDone18) {
						// We should be at the end element, but make sure
						while (!reader.isEndElement())
							reader.next();
						// Step out of this element
						reader.next();
						// Step to next element event.
						while (!reader.isStartElement() && !reader.isEndElement())
							reader.next();
						if (reader.isEndElement()) {
							// two continuous end elements means we are exiting
							// the xml structure
							loopDone18 = true;
						} else {
							if (new javax.xml.namespace.QName("urn:sbmappservices72", "fileAttachment")
									.equals(reader.getName())) {
								list18.add(com.mitel.ka.kcapp.services.sbm.sbmappservices72.FileAttachment.Factory
										.parse(reader));

							} else {
								loopDone18 = true;
							}
						}
					}
					// call the converter utility to convert and set the array

					object.setFileAttachment(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.FileAttachment[]) org.apache.axis2.databinding.utils.ConverterUtil
									.convertToArray(
											com.mitel.ka.kcapp.services.sbm.sbmappservices72.FileAttachment.class,
											list18));

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "subtasks").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "subtasks").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						object.setSubtasks(null);
						reader.next();

						reader.next();

					} else {

						object.setSubtasks(
								com.mitel.ka.kcapp.services.sbm.sbmappservices72.SubTasks.Factory.parse(reader));

						reader.next();
					}
				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "extendedData")
								.equals(reader.getName())
						|| new javax.xml.namespace.QName("", "extendedData").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						object.setExtendedData(null);
						reader.next();

						reader.next();

					} else {

						object.setExtendedData(
								com.mitel.ka.kcapp.services.sbm.sbmappservices72.ExtendedData.Factory.parse(reader));

						reader.next();
					}
				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()) {

					// Process the array and step past its final element's end.

					boolean loopDone21 = false;

					while (!loopDone21) {
						event = reader.getEventType();
						if (javax.xml.stream.XMLStreamConstants.START_ELEMENT == event) {

							// We need to wrap the reader so that it produces a
							// fake START_DOCUEMENT event
							org.apache.axis2.databinding.utils.NamedStaxOMBuilder builder21 = new org.apache.axis2.databinding.utils.NamedStaxOMBuilder(
									new org.apache.axis2.util.StreamWrapper(reader), reader.getName());

							list21.add(builder21.getOMElement());
							reader.next();
							if (reader.isEndElement()) {
								// we have two countinuos end elements
								loopDone21 = true;
							}

						} else if (javax.xml.stream.XMLStreamConstants.END_ELEMENT == event) {
							loopDone21 = true;
						} else {
							reader.next();
						}

					}

					object.setExtraElement(
							(org.apache.axiom.om.OMElement[]) org.apache.axis2.databinding.utils.ConverterUtil
									.convertToArray(org.apache.axiom.om.OMElement.class, list21));

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement())
					// 2 - A start element we are not expecting indicates a
					// trailing invalid property

					throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());

			} catch (javax.xml.stream.XMLStreamException e) {
				throw new java.lang.Exception(e);
			}

			return object;
		}

	}// end of factory class

}
