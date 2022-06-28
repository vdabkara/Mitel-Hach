
/**
 * UserInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.2  Built on : May 02, 2016 (05:56:08 BST)
 */

package com.mitel.ka.kcapp.services.sbm.sbmappservices72;

/**
 * UserInfo bean class
 */
@SuppressWarnings({ "unchecked", "unused" })

public class UserInfo implements org.apache.axis2.databinding.ADBBean {
	/*
	 * This type was generated from the piece of schema that had name = UserInfo
	 * Namespace URI = urn:sbmappservices72 Namespace Prefix =
	 */

	/**
	 * field for Id
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserIdentifier localId;

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
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserIdentifier
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserIdentifier getId() {
		return localId;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Id
	 */
	public void setId(com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserIdentifier param) {
		localIdTracker = true;

		this.localId = param;

	}

	/**
	 * field for AccessType
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.AccessType localAccessType;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localAccessTypeTracker = false;

	public boolean isAccessTypeSpecified() {
		return localAccessTypeTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.AccessType
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.AccessType getAccessType() {
		return localAccessType;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            AccessType
	 */
	public void setAccessType(com.mitel.ka.kcapp.services.sbm.sbmappservices72.AccessType param) {
		localAccessTypeTracker = param != null;

		this.localAccessType = param;

	}

	/**
	 * field for Email
	 */

	protected java.lang.String localEmail;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localEmailTracker = false;

	public boolean isEmailSpecified() {
		return localEmailTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getEmail() {
		return localEmail;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Email
	 */
	public void setEmail(java.lang.String param) {
		localEmailTracker = true;

		this.localEmail = param;

	}

	/**
	 * field for EmailCC
	 */

	protected java.lang.String localEmailCC;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localEmailCCTracker = false;

	public boolean isEmailCCSpecified() {
		return localEmailCCTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getEmailCC() {
		return localEmailCC;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            EmailCC
	 */
	public void setEmailCC(java.lang.String param) {
		localEmailCCTracker = true;

		this.localEmailCC = param;

	}

	/**
	 * field for Timezone
	 */

	protected java.lang.String localTimezone;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localTimezoneTracker = false;

	public boolean isTimezoneSpecified() {
		return localTimezoneTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getTimezone() {
		return localTimezone;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Timezone
	 */
	public void setTimezone(java.lang.String param) {
		localTimezoneTracker = true;

		this.localTimezone = param;

	}

	/**
	 * field for OffsetFromGMT
	 */

	protected java.math.BigInteger localOffsetFromGMT;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localOffsetFromGMTTracker = false;

	public boolean isOffsetFromGMTSpecified() {
		return localOffsetFromGMTTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return java.math.BigInteger
	 */
	public java.math.BigInteger getOffsetFromGMT() {
		return localOffsetFromGMT;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            OffsetFromGMT
	 */
	public void setOffsetFromGMT(java.math.BigInteger param) {
		localOffsetFromGMTTracker = param != null;

		this.localOffsetFromGMT = param;

	}

	/**
	 * field for DstSavings
	 */

	protected java.math.BigInteger localDstSavings;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localDstSavingsTracker = false;

	public boolean isDstSavingsSpecified() {
		return localDstSavingsTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return java.math.BigInteger
	 */
	public java.math.BigInteger getDstSavings() {
		return localDstSavings;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            DstSavings
	 */
	public void setDstSavings(java.math.BigInteger param) {
		localDstSavingsTracker = param != null;

		this.localDstSavings = param;

	}

	/**
	 * field for DatePreference
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.DatePreference localDatePreference;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localDatePreferenceTracker = false;

	public boolean isDatePreferenceSpecified() {
		return localDatePreferenceTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.DatePreference
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.DatePreference getDatePreference() {
		return localDatePreference;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            DatePreference
	 */
	public void setDatePreference(com.mitel.ka.kcapp.services.sbm.sbmappservices72.DatePreference param) {
		localDatePreferenceTracker = param != null;

		this.localDatePreference = param;

	}

	/**
	 * field for TimePreference
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.TimePreference localTimePreference;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localTimePreferenceTracker = false;

	public boolean isTimePreferenceSpecified() {
		return localTimePreferenceTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.TimePreference
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.TimePreference getTimePreference() {
		return localTimePreference;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            TimePreference
	 */
	public void setTimePreference(com.mitel.ka.kcapp.services.sbm.sbmappservices72.TimePreference param) {
		localTimePreferenceTracker = param != null;

		this.localTimePreference = param;

	}

	/**
	 * field for NamespaceName
	 */

	protected java.lang.String localNamespaceName;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localNamespaceNameTracker = false;

	public boolean isNamespaceNameSpecified() {
		return localNamespaceNameTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getNamespaceName() {
		return localNamespaceName;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            NamespaceName
	 */
	public void setNamespaceName(java.lang.String param) {
		localNamespaceNameTracker = true;

		this.localNamespaceName = param;

	}

	/**
	 * field for PhoneNumber
	 */

	protected java.lang.String localPhoneNumber;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localPhoneNumberTracker = false;

	public boolean isPhoneNumberSpecified() {
		return localPhoneNumberTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPhoneNumber() {
		return localPhoneNumber;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            PhoneNumber
	 */
	public void setPhoneNumber(java.lang.String param) {
		localPhoneNumberTracker = true;

		this.localPhoneNumber = param;

	}

	/**
	 * field for Locale
	 */

	protected java.lang.String localLocale;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localLocaleTracker = false;

	public boolean isLocaleSpecified() {
		return localLocaleTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getLocale() {
		return localLocale;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Locale
	 */
	public void setLocale(java.lang.String param) {
		localLocaleTracker = true;

		this.localLocale = param;

	}

	/**
	 * field for IsDeleted
	 */

	protected boolean localIsDeleted;

	/**
	 * Auto generated getter method
	 * 
	 * @return boolean
	 */
	public boolean getIsDeleted() {
		return localIsDeleted;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            IsDeleted
	 */
	public void setIsDeleted(boolean param) {

		this.localIsDeleted = param;

	}

	/**
	 * field for Contact
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.ContactIdentifier localContact;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localContactTracker = false;

	public boolean isContactSpecified() {
		return localContactTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.
	 *         ContactIdentifier
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.ContactIdentifier getContact() {
		return localContact;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Contact
	 */
	public void setContact(com.mitel.ka.kcapp.services.sbm.sbmappservices72.ContactIdentifier param) {
		localContactTracker = true;

		this.localContact = param;

	}

	/**
	 * field for MaxNotes
	 */

	protected java.math.BigInteger localMaxNotes;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localMaxNotesTracker = false;

	public boolean isMaxNotesSpecified() {
		return localMaxNotesTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return java.math.BigInteger
	 */
	public java.math.BigInteger getMaxNotes() {
		return localMaxNotes;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            MaxNotes
	 */
	public void setMaxNotes(java.math.BigInteger param) {
		localMaxNotesTracker = param != null;

		this.localMaxNotes = param;

	}

	/**
	 * field for MaxChangeHistory
	 */

	protected java.math.BigInteger localMaxChangeHistory;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localMaxChangeHistoryTracker = false;

	public boolean isMaxChangeHistorySpecified() {
		return localMaxChangeHistoryTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return java.math.BigInteger
	 */
	public java.math.BigInteger getMaxChangeHistory() {
		return localMaxChangeHistory;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            MaxChangeHistory
	 */
	public void setMaxChangeHistory(java.math.BigInteger param) {
		localMaxChangeHistoryTracker = param != null;

		this.localMaxChangeHistory = param;

	}

	/**
	 * field for MaxItemsPerPage
	 */

	protected java.math.BigInteger localMaxItemsPerPage;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localMaxItemsPerPageTracker = false;

	public boolean isMaxItemsPerPageSpecified() {
		return localMaxItemsPerPageTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return java.math.BigInteger
	 */
	public java.math.BigInteger getMaxItemsPerPage() {
		return localMaxItemsPerPage;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            MaxItemsPerPage
	 */
	public void setMaxItemsPerPage(java.math.BigInteger param) {
		localMaxItemsPerPageTracker = param != null;

		this.localMaxItemsPerPage = param;

	}

	/**
	 * field for FieldsMask
	 */

	protected java.math.BigInteger localFieldsMask;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localFieldsMaskTracker = false;

	public boolean isFieldsMaskSpecified() {
		return localFieldsMaskTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return java.math.BigInteger
	 */
	public java.math.BigInteger getFieldsMask() {
		return localFieldsMask;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            FieldsMask
	 */
	public void setFieldsMask(java.math.BigInteger param) {
		localFieldsMaskTracker = param != null;

		this.localFieldsMask = param;

	}

	/**
	 * field for NotesMask
	 */

	protected java.math.BigInteger localNotesMask;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localNotesMaskTracker = false;

	public boolean isNotesMaskSpecified() {
		return localNotesMaskTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return java.math.BigInteger
	 */
	public java.math.BigInteger getNotesMask() {
		return localNotesMask;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            NotesMask
	 */
	public void setNotesMask(java.math.BigInteger param) {
		localNotesMaskTracker = param != null;

		this.localNotesMask = param;

	}

	/**
	 * field for ChangeHistoryMask
	 */

	protected java.math.BigInteger localChangeHistoryMask;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localChangeHistoryMaskTracker = false;

	public boolean isChangeHistoryMaskSpecified() {
		return localChangeHistoryMaskTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return java.math.BigInteger
	 */
	public java.math.BigInteger getChangeHistoryMask() {
		return localChangeHistoryMask;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            ChangeHistoryMask
	 */
	public void setChangeHistoryMask(java.math.BigInteger param) {
		localChangeHistoryMaskTracker = param != null;

		this.localChangeHistoryMask = param;

	}

	/**
	 * field for BrowserMask
	 */

	protected java.math.BigInteger localBrowserMask;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localBrowserMaskTracker = false;

	public boolean isBrowserMaskSpecified() {
		return localBrowserMaskTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return java.math.BigInteger
	 */
	public java.math.BigInteger getBrowserMask() {
		return localBrowserMask;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            BrowserMask
	 */
	public void setBrowserMask(java.math.BigInteger param) {
		localBrowserMaskTracker = param != null;

		this.localBrowserMask = param;

	}

	/**
	 * field for Group This was an Array!
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.GroupIdentifier[] localGroup;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localGroupTracker = false;

	public boolean isGroupSpecified() {
		return localGroupTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.GroupIdentifier[
	 *         ]
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.GroupIdentifier[] getGroup() {
		return localGroup;
	}

	/**
	 * validate the array for Group
	 */
	protected void validateGroup(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GroupIdentifier[] param) {

	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Group
	 */
	public void setGroup(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GroupIdentifier[] param) {

		validateGroup(param);

		localGroupTracker = param != null;

		this.localGroup = param;
	}

	/**
	 * Auto generated add method for the array for convenience
	 * 
	 * @param param
	 *            com.mitel.ka.kcapp.services.sbm.sbmappservices72.
	 *            GroupIdentifier
	 */
	public void addGroup(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GroupIdentifier param) {
		if (localGroup == null) {
			localGroup = new com.mitel.ka.kcapp.services.sbm.sbmappservices72.GroupIdentifier[] {};
		}

		// update the setting tracker
		localGroupTracker = true;

		java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localGroup);
		list.add(param);
		this.localGroup = (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GroupIdentifier[]) list
				.toArray(new com.mitel.ka.kcapp.services.sbm.sbmappservices72.GroupIdentifier[list.size()]);

	}

	/**
	 * field for PreferredSolution
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.SolutionIdentifier localPreferredSolution;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localPreferredSolutionTracker = false;

	public boolean isPreferredSolutionSpecified() {
		return localPreferredSolutionTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.
	 *         SolutionIdentifier
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.SolutionIdentifier getPreferredSolution() {
		return localPreferredSolution;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            PreferredSolution
	 */
	public void setPreferredSolution(com.mitel.ka.kcapp.services.sbm.sbmappservices72.SolutionIdentifier param) {
		localPreferredSolutionTracker = true;

		this.localPreferredSolution = param;

	}

	/**
	 * field for SolutionData This was an Array!
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserSolutionData[] localSolutionData;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localSolutionDataTracker = false;

	public boolean isSolutionDataSpecified() {
		return localSolutionDataTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserSolutionData
	 *         []
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserSolutionData[] getSolutionData() {
		return localSolutionData;
	}

	/**
	 * validate the array for SolutionData
	 */
	protected void validateSolutionData(com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserSolutionData[] param) {

	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            SolutionData
	 */
	public void setSolutionData(com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserSolutionData[] param) {

		validateSolutionData(param);

		localSolutionDataTracker = param != null;

		this.localSolutionData = param;
	}

	/**
	 * Auto generated add method for the array for convenience
	 * 
	 * @param param
	 *            com.mitel.ka.kcapp.services.sbm.sbmappservices72.
	 *            UserSolutionData
	 */
	public void addSolutionData(com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserSolutionData param) {
		if (localSolutionData == null) {
			localSolutionData = new com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserSolutionData[] {};
		}

		// update the setting tracker
		localSolutionDataTracker = true;

		java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localSolutionData);
		list.add(param);
		this.localSolutionData = (com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserSolutionData[]) list
				.toArray(new com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserSolutionData[list.size()]);

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
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
						namespacePrefix + ":UserInfo", xmlWriter);
			} else {
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "UserInfo", xmlWriter);
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
		if (localAccessTypeTracker) {
			if (localAccessType == null) {
				throw new org.apache.axis2.databinding.ADBException("accessType cannot be null!!");
			}
			localAccessType.serialize(new javax.xml.namespace.QName("urn:sbmappservices72", "accessType"), xmlWriter);
		}
		if (localEmailTracker) {
			namespace = "urn:sbmappservices72";
			writeStartElement(null, namespace, "email", xmlWriter);

			if (localEmail == null) {
				// write the nil attribute

				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

			} else {

				xmlWriter.writeCharacters(localEmail);

			}

			xmlWriter.writeEndElement();
		}
		if (localEmailCCTracker) {
			namespace = "urn:sbmappservices72";
			writeStartElement(null, namespace, "emailCC", xmlWriter);

			if (localEmailCC == null) {
				// write the nil attribute

				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

			} else {

				xmlWriter.writeCharacters(localEmailCC);

			}

			xmlWriter.writeEndElement();
		}
		if (localTimezoneTracker) {
			namespace = "urn:sbmappservices72";
			writeStartElement(null, namespace, "timezone", xmlWriter);

			if (localTimezone == null) {
				// write the nil attribute

				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

			} else {

				xmlWriter.writeCharacters(localTimezone);

			}

			xmlWriter.writeEndElement();
		}
		if (localOffsetFromGMTTracker) {
			namespace = "urn:sbmappservices72";
			writeStartElement(null, namespace, "offsetFromGMT", xmlWriter);

			if (localOffsetFromGMT == null) {
				// write the nil attribute

				throw new org.apache.axis2.databinding.ADBException("offsetFromGMT cannot be null!!");

			} else {

				xmlWriter.writeCharacters(
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localOffsetFromGMT));

			}

			xmlWriter.writeEndElement();
		}
		if (localDstSavingsTracker) {
			namespace = "urn:sbmappservices72";
			writeStartElement(null, namespace, "dstSavings", xmlWriter);

			if (localDstSavings == null) {
				// write the nil attribute

				throw new org.apache.axis2.databinding.ADBException("dstSavings cannot be null!!");

			} else {

				xmlWriter.writeCharacters(
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDstSavings));

			}

			xmlWriter.writeEndElement();
		}
		if (localDatePreferenceTracker) {
			if (localDatePreference == null) {
				throw new org.apache.axis2.databinding.ADBException("datePreference cannot be null!!");
			}
			localDatePreference.serialize(new javax.xml.namespace.QName("urn:sbmappservices72", "datePreference"),
					xmlWriter);
		}
		if (localTimePreferenceTracker) {
			if (localTimePreference == null) {
				throw new org.apache.axis2.databinding.ADBException("timePreference cannot be null!!");
			}
			localTimePreference.serialize(new javax.xml.namespace.QName("urn:sbmappservices72", "timePreference"),
					xmlWriter);
		}
		if (localNamespaceNameTracker) {
			namespace = "urn:sbmappservices72";
			writeStartElement(null, namespace, "namespaceName", xmlWriter);

			if (localNamespaceName == null) {
				// write the nil attribute

				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

			} else {

				xmlWriter.writeCharacters(localNamespaceName);

			}

			xmlWriter.writeEndElement();
		}
		if (localPhoneNumberTracker) {
			namespace = "urn:sbmappservices72";
			writeStartElement(null, namespace, "phoneNumber", xmlWriter);

			if (localPhoneNumber == null) {
				// write the nil attribute

				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

			} else {

				xmlWriter.writeCharacters(localPhoneNumber);

			}

			xmlWriter.writeEndElement();
		}
		if (localLocaleTracker) {
			namespace = "urn:sbmappservices72";
			writeStartElement(null, namespace, "locale", xmlWriter);

			if (localLocale == null) {
				// write the nil attribute

				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

			} else {

				xmlWriter.writeCharacters(localLocale);

			}

			xmlWriter.writeEndElement();
		}
		namespace = "urn:sbmappservices72";
		writeStartElement(null, namespace, "isDeleted", xmlWriter);

		if (false) {

			throw new org.apache.axis2.databinding.ADBException("isDeleted cannot be null!!");

		} else {
			xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIsDeleted));
		}

		xmlWriter.writeEndElement();
		if (localContactTracker) {
			if (localContact == null) {

				writeStartElement(null, "urn:sbmappservices72", "contact", xmlWriter);

				// write the nil attribute
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
				xmlWriter.writeEndElement();
			} else {
				localContact.serialize(new javax.xml.namespace.QName("urn:sbmappservices72", "contact"), xmlWriter);
			}
		}
		if (localMaxNotesTracker) {
			namespace = "urn:sbmappservices72";
			writeStartElement(null, namespace, "maxNotes", xmlWriter);

			if (localMaxNotes == null) {
				// write the nil attribute

				throw new org.apache.axis2.databinding.ADBException("maxNotes cannot be null!!");

			} else {

				xmlWriter.writeCharacters(
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMaxNotes));

			}

			xmlWriter.writeEndElement();
		}
		if (localMaxChangeHistoryTracker) {
			namespace = "urn:sbmappservices72";
			writeStartElement(null, namespace, "maxChangeHistory", xmlWriter);

			if (localMaxChangeHistory == null) {
				// write the nil attribute

				throw new org.apache.axis2.databinding.ADBException("maxChangeHistory cannot be null!!");

			} else {

				xmlWriter.writeCharacters(
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMaxChangeHistory));

			}

			xmlWriter.writeEndElement();
		}
		if (localMaxItemsPerPageTracker) {
			namespace = "urn:sbmappservices72";
			writeStartElement(null, namespace, "maxItemsPerPage", xmlWriter);

			if (localMaxItemsPerPage == null) {
				// write the nil attribute

				throw new org.apache.axis2.databinding.ADBException("maxItemsPerPage cannot be null!!");

			} else {

				xmlWriter.writeCharacters(
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMaxItemsPerPage));

			}

			xmlWriter.writeEndElement();
		}
		if (localFieldsMaskTracker) {
			namespace = "urn:sbmappservices72";
			writeStartElement(null, namespace, "fieldsMask", xmlWriter);

			if (localFieldsMask == null) {
				// write the nil attribute

				throw new org.apache.axis2.databinding.ADBException("fieldsMask cannot be null!!");

			} else {

				xmlWriter.writeCharacters(
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localFieldsMask));

			}

			xmlWriter.writeEndElement();
		}
		if (localNotesMaskTracker) {
			namespace = "urn:sbmappservices72";
			writeStartElement(null, namespace, "notesMask", xmlWriter);

			if (localNotesMask == null) {
				// write the nil attribute

				throw new org.apache.axis2.databinding.ADBException("notesMask cannot be null!!");

			} else {

				xmlWriter.writeCharacters(
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNotesMask));

			}

			xmlWriter.writeEndElement();
		}
		if (localChangeHistoryMaskTracker) {
			namespace = "urn:sbmappservices72";
			writeStartElement(null, namespace, "changeHistoryMask", xmlWriter);

			if (localChangeHistoryMask == null) {
				// write the nil attribute

				throw new org.apache.axis2.databinding.ADBException("changeHistoryMask cannot be null!!");

			} else {

				xmlWriter.writeCharacters(
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localChangeHistoryMask));

			}

			xmlWriter.writeEndElement();
		}
		if (localBrowserMaskTracker) {
			namespace = "urn:sbmappservices72";
			writeStartElement(null, namespace, "browserMask", xmlWriter);

			if (localBrowserMask == null) {
				// write the nil attribute

				throw new org.apache.axis2.databinding.ADBException("browserMask cannot be null!!");

			} else {

				xmlWriter.writeCharacters(
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBrowserMask));

			}

			xmlWriter.writeEndElement();
		}
		if (localGroupTracker) {
			if (localGroup != null) {
				for (int i = 0; i < localGroup.length; i++) {
					if (localGroup[i] != null) {
						localGroup[i].serialize(new javax.xml.namespace.QName("urn:sbmappservices72", "group"),
								xmlWriter);
					} else {

						// we don't have to do any thing since minOccures is
						// zero

					}

				}
			} else {

				throw new org.apache.axis2.databinding.ADBException("group cannot be null!!");

			}
		}
		if (localPreferredSolutionTracker) {
			if (localPreferredSolution == null) {

				writeStartElement(null, "urn:sbmappservices72", "preferredSolution", xmlWriter);

				// write the nil attribute
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
				xmlWriter.writeEndElement();
			} else {
				localPreferredSolution.serialize(
						new javax.xml.namespace.QName("urn:sbmappservices72", "preferredSolution"), xmlWriter);
			}
		}
		if (localSolutionDataTracker) {
			if (localSolutionData != null) {
				for (int i = 0; i < localSolutionData.length; i++) {
					if (localSolutionData[i] != null) {
						localSolutionData[i].serialize(
								new javax.xml.namespace.QName("urn:sbmappservices72", "solutionData"), xmlWriter);
					} else {

						// we don't have to do any thing since minOccures is
						// zero

					}

				}
			} else {

				throw new org.apache.axis2.databinding.ADBException("solutionData cannot be null!!");

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
		public static UserInfo parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
			UserInfo object = new UserInfo();

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

						if (!"UserInfo".equals(type)) {
							// find namespace for the prefix
							java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
							return (UserInfo) com.mitel.ka.kcapp.services.sbm.serenadiagnostics.ExtensionMapper
									.getTypeObject(nsUri, type, reader);
						}

					}

				}

				// Note all attributes that were handled. Used to differ normal
				// attributes
				// from anyAttributes.
				java.util.Vector handledAttributes = new java.util.Vector();

				reader.next();

				java.util.ArrayList list22 = new java.util.ArrayList();

				java.util.ArrayList list24 = new java.util.ArrayList();

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
								com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserIdentifier.Factory.parse(reader));

						reader.next();
					}
				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "accessType").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "accessType").equals(reader.getName())) {

					object.setAccessType(
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.AccessType.Factory.parse(reader));

					reader.next();

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "email").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "email").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

						java.lang.String content = reader.getElementText();

						object.setEmail(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

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
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "emailCC").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "emailCC").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

						java.lang.String content = reader.getElementText();

						object.setEmailCC(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

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
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "timezone").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "timezone").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

						java.lang.String content = reader.getElementText();

						object.setTimezone(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

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
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "offsetFromGMT")
								.equals(reader.getName())
						|| new javax.xml.namespace.QName("", "offsetFromGMT").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						throw new org.apache.axis2.databinding.ADBException(
								"The element: " + "offsetFromGMT" + "  cannot be null");
					}

					java.lang.String content = reader.getElementText();

					object.setOffsetFromGMT(org.apache.axis2.databinding.utils.ConverterUtil.convertToInteger(content));

					reader.next();

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "dstSavings").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "dstSavings").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						throw new org.apache.axis2.databinding.ADBException(
								"The element: " + "dstSavings" + "  cannot be null");
					}

					java.lang.String content = reader.getElementText();

					object.setDstSavings(org.apache.axis2.databinding.utils.ConverterUtil.convertToInteger(content));

					reader.next();

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "datePreference")
								.equals(reader.getName())
						|| new javax.xml.namespace.QName("", "datePreference").equals(reader.getName())) {

					object.setDatePreference(
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.DatePreference.Factory.parse(reader));

					reader.next();

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "timePreference")
								.equals(reader.getName())
						|| new javax.xml.namespace.QName("", "timePreference").equals(reader.getName())) {

					object.setTimePreference(
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.TimePreference.Factory.parse(reader));

					reader.next();

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "namespaceName")
								.equals(reader.getName())
						|| new javax.xml.namespace.QName("", "namespaceName").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

						java.lang.String content = reader.getElementText();

						object.setNamespaceName(
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
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "phoneNumber").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "phoneNumber").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

						java.lang.String content = reader.getElementText();

						object.setPhoneNumber(
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
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "locale").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "locale").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

						java.lang.String content = reader.getElementText();

						object.setLocale(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

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
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "isDeleted").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "isDeleted").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						throw new org.apache.axis2.databinding.ADBException(
								"The element: " + "isDeleted" + "  cannot be null");
					}

					java.lang.String content = reader.getElementText();

					object.setIsDeleted(org.apache.axis2.databinding.utils.ConverterUtil.convertToBoolean(content));

					reader.next();

				} // End of if for expected property start element

				else {
					// 1 - A start element we are not expecting indicates an
					// invalid parameter was passed
					throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "contact").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "contact").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						object.setContact(null);
						reader.next();

						reader.next();

					} else {

						object.setContact(com.mitel.ka.kcapp.services.sbm.sbmappservices72.ContactIdentifier.Factory
								.parse(reader));

						reader.next();
					}
				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "maxNotes").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "maxNotes").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						throw new org.apache.axis2.databinding.ADBException(
								"The element: " + "maxNotes" + "  cannot be null");
					}

					java.lang.String content = reader.getElementText();

					object.setMaxNotes(org.apache.axis2.databinding.utils.ConverterUtil.convertToInteger(content));

					reader.next();

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "maxChangeHistory")
								.equals(reader.getName())
						|| new javax.xml.namespace.QName("", "maxChangeHistory").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						throw new org.apache.axis2.databinding.ADBException(
								"The element: " + "maxChangeHistory" + "  cannot be null");
					}

					java.lang.String content = reader.getElementText();

					object.setMaxChangeHistory(
							org.apache.axis2.databinding.utils.ConverterUtil.convertToInteger(content));

					reader.next();

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "maxItemsPerPage")
								.equals(reader.getName())
						|| new javax.xml.namespace.QName("", "maxItemsPerPage").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						throw new org.apache.axis2.databinding.ADBException(
								"The element: " + "maxItemsPerPage" + "  cannot be null");
					}

					java.lang.String content = reader.getElementText();

					object.setMaxItemsPerPage(
							org.apache.axis2.databinding.utils.ConverterUtil.convertToInteger(content));

					reader.next();

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "fieldsMask").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "fieldsMask").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						throw new org.apache.axis2.databinding.ADBException(
								"The element: " + "fieldsMask" + "  cannot be null");
					}

					java.lang.String content = reader.getElementText();

					object.setFieldsMask(org.apache.axis2.databinding.utils.ConverterUtil.convertToInteger(content));

					reader.next();

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "notesMask").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "notesMask").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						throw new org.apache.axis2.databinding.ADBException(
								"The element: " + "notesMask" + "  cannot be null");
					}

					java.lang.String content = reader.getElementText();

					object.setNotesMask(org.apache.axis2.databinding.utils.ConverterUtil.convertToInteger(content));

					reader.next();

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "changeHistoryMask")
								.equals(reader.getName())
						|| new javax.xml.namespace.QName("", "changeHistoryMask").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						throw new org.apache.axis2.databinding.ADBException(
								"The element: " + "changeHistoryMask" + "  cannot be null");
					}

					java.lang.String content = reader.getElementText();

					object.setChangeHistoryMask(
							org.apache.axis2.databinding.utils.ConverterUtil.convertToInteger(content));

					reader.next();

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "browserMask").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "browserMask").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						throw new org.apache.axis2.databinding.ADBException(
								"The element: " + "browserMask" + "  cannot be null");
					}

					java.lang.String content = reader.getElementText();

					object.setBrowserMask(org.apache.axis2.databinding.utils.ConverterUtil.convertToInteger(content));

					reader.next();

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "group").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "group").equals(reader.getName())) {

					// Process the array and step past its final element's end.

					list22.add(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GroupIdentifier.Factory.parse(reader));

					// loop until we find a start element that is not part of
					// this array
					boolean loopDone22 = false;
					while (!loopDone22) {
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
							loopDone22 = true;
						} else {
							if (new javax.xml.namespace.QName("urn:sbmappservices72", "group")
									.equals(reader.getName())) {
								list22.add(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GroupIdentifier.Factory
										.parse(reader));

							} else {
								loopDone22 = true;
							}
						}
					}
					// call the converter utility to convert and set the array

					object.setGroup(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GroupIdentifier[]) org.apache.axis2.databinding.utils.ConverterUtil
									.convertToArray(
											com.mitel.ka.kcapp.services.sbm.sbmappservices72.GroupIdentifier.class,
											list22));

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "preferredSolution")
								.equals(reader.getName())
						|| new javax.xml.namespace.QName("", "preferredSolution").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						object.setPreferredSolution(null);
						reader.next();

						reader.next();

					} else {

						object.setPreferredSolution(
								com.mitel.ka.kcapp.services.sbm.sbmappservices72.SolutionIdentifier.Factory
										.parse(reader));

						reader.next();
					}
				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "solutionData")
								.equals(reader.getName())
						|| new javax.xml.namespace.QName("", "solutionData").equals(reader.getName())) {

					// Process the array and step past its final element's end.

					list24.add(com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserSolutionData.Factory.parse(reader));

					// loop until we find a start element that is not part of
					// this array
					boolean loopDone24 = false;
					while (!loopDone24) {
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
							loopDone24 = true;
						} else {
							if (new javax.xml.namespace.QName("urn:sbmappservices72", "solutionData")
									.equals(reader.getName())) {
								list24.add(com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserSolutionData.Factory
										.parse(reader));

							} else {
								loopDone24 = true;
							}
						}
					}
					// call the converter utility to convert and set the array

					object.setSolutionData(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserSolutionData[]) org.apache.axis2.databinding.utils.ConverterUtil
									.convertToArray(
											com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserSolutionData.class,
											list24));

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
