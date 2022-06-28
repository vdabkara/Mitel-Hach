
/**
 * ReportInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.2  Built on : May 02, 2016 (05:56:08 BST)
 */

package com.mitel.ka.kcapp.services.sbm.sbmappservices72;

/**
 * ReportInfo bean class
 */
@SuppressWarnings({ "unchecked", "unused" })

public class ReportInfo implements org.apache.axis2.databinding.ADBBean {
	/*
	 * This type was generated from the piece of schema that had name =
	 * ReportInfo Namespace URI = urn:sbmappservices72 Namespace Prefix =
	 */

	/**
	 * field for Report
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.ReportIdentifier localReport;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localReportTracker = false;

	public boolean isReportSpecified() {
		return localReportTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.ReportIdentifier
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.ReportIdentifier getReport() {
		return localReport;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Report
	 */
	public void setReport(com.mitel.ka.kcapp.services.sbm.sbmappservices72.ReportIdentifier param) {
		localReportTracker = true;

		this.localReport = param;

	}

	/**
	 * field for ReportURL
	 */

	protected java.lang.String localReportURL;

	/**
	 * Auto generated getter method
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getReportURL() {
		return localReportURL;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            ReportURL
	 */
	public void setReportURL(java.lang.String param) {

		this.localReportURL = param;

	}

	/**
	 * field for ReportType
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.ReportType localReportType;

	/**
	 * Auto generated getter method
	 * 
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.ReportType
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.ReportType getReportType() {
		return localReportType;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            ReportType
	 */
	public void setReportType(com.mitel.ka.kcapp.services.sbm.sbmappservices72.ReportType param) {

		this.localReportType = param;

	}

	/**
	 * field for ReportCategory
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.ReportCategory localReportCategory;

	/**
	 * Auto generated getter method
	 * 
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.ReportCategory
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.ReportCategory getReportCategory() {
		return localReportCategory;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            ReportCategory
	 */
	public void setReportCategory(com.mitel.ka.kcapp.services.sbm.sbmappservices72.ReportCategory param) {

		this.localReportCategory = param;

	}

	/**
	 * field for ReportAccessLevel
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.ReportAccessLevel localReportAccessLevel;

	/**
	 * Auto generated getter method
	 * 
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.
	 *         ReportAccessLevel
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.ReportAccessLevel getReportAccessLevel() {
		return localReportAccessLevel;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            ReportAccessLevel
	 */
	public void setReportAccessLevel(com.mitel.ka.kcapp.services.sbm.sbmappservices72.ReportAccessLevel param) {

		this.localReportAccessLevel = param;

	}

	/**
	 * field for Solution
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.SolutionIdentifier localSolution;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localSolutionTracker = false;

	public boolean isSolutionSpecified() {
		return localSolutionTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.
	 *         SolutionIdentifier
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.SolutionIdentifier getSolution() {
		return localSolution;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Solution
	 */
	public void setSolution(com.mitel.ka.kcapp.services.sbm.sbmappservices72.SolutionIdentifier param) {
		localSolutionTracker = true;

		this.localSolution = param;

	}

	/**
	 * field for Table
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.TableIdentifier localTable;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localTableTracker = false;

	public boolean isTableSpecified() {
		return localTableTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.TableIdentifier
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.TableIdentifier getTable() {
		return localTable;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Table
	 */
	public void setTable(com.mitel.ka.kcapp.services.sbm.sbmappservices72.TableIdentifier param) {
		localTableTracker = true;

		this.localTable = param;

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
	 * field for CreatedBy
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserIdentifier localCreatedBy;

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

		this.localCreatedBy = param;

	}

	/**
	 * field for CreateDate
	 */

	protected java.util.Calendar localCreateDate;

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
		localModifiedDateTracker = param != null;

		this.localModifiedDate = param;

	}

	/**
	 * field for ExecDate
	 */

	protected java.util.Calendar localExecDate;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localExecDateTracker = false;

	public boolean isExecDateSpecified() {
		return localExecDateTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return java.util.Calendar
	 */
	public java.util.Calendar getExecDate() {
		return localExecDate;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            ExecDate
	 */
	public void setExecDate(java.util.Calendar param) {
		localExecDateTracker = param != null;

		this.localExecDate = param;

	}

	/**
	 * field for IsQueryAtRuntime
	 */

	protected boolean localIsQueryAtRuntime;

	/**
	 * Auto generated getter method
	 * 
	 * @return boolean
	 */
	public boolean getIsQueryAtRuntime() {
		return localIsQueryAtRuntime;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            IsQueryAtRuntime
	 */
	public void setIsQueryAtRuntime(boolean param) {

		this.localIsQueryAtRuntime = param;

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
						namespacePrefix + ":ReportInfo", xmlWriter);
			} else {
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "ReportInfo", xmlWriter);
			}

		}
		if (localReportTracker) {
			if (localReport == null) {

				writeStartElement(null, "urn:sbmappservices72", "report", xmlWriter);

				// write the nil attribute
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
				xmlWriter.writeEndElement();
			} else {
				localReport.serialize(new javax.xml.namespace.QName("urn:sbmappservices72", "report"), xmlWriter);
			}
		}
		namespace = "urn:sbmappservices72";
		writeStartElement(null, namespace, "reportURL", xmlWriter);

		if (localReportURL == null) {
			// write the nil attribute

			writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

		} else {

			xmlWriter.writeCharacters(localReportURL);

		}

		xmlWriter.writeEndElement();

		if (localReportType == null) {
			throw new org.apache.axis2.databinding.ADBException("reportType cannot be null!!");
		}
		localReportType.serialize(new javax.xml.namespace.QName("urn:sbmappservices72", "reportType"), xmlWriter);

		if (localReportCategory == null) {
			throw new org.apache.axis2.databinding.ADBException("reportCategory cannot be null!!");
		}
		localReportCategory.serialize(new javax.xml.namespace.QName("urn:sbmappservices72", "reportCategory"),
				xmlWriter);

		if (localReportAccessLevel == null) {
			throw new org.apache.axis2.databinding.ADBException("reportAccessLevel cannot be null!!");
		}
		localReportAccessLevel.serialize(new javax.xml.namespace.QName("urn:sbmappservices72", "reportAccessLevel"),
				xmlWriter);
		if (localSolutionTracker) {
			if (localSolution == null) {

				writeStartElement(null, "urn:sbmappservices72", "solution", xmlWriter);

				// write the nil attribute
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
				xmlWriter.writeEndElement();
			} else {
				localSolution.serialize(new javax.xml.namespace.QName("urn:sbmappservices72", "solution"), xmlWriter);
			}
		}
		if (localTableTracker) {
			if (localTable == null) {

				writeStartElement(null, "urn:sbmappservices72", "table", xmlWriter);

				// write the nil attribute
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
				xmlWriter.writeEndElement();
			} else {
				localTable.serialize(new javax.xml.namespace.QName("urn:sbmappservices72", "table"), xmlWriter);
			}
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
		if (localCreatedBy == null) {

			writeStartElement(null, "urn:sbmappservices72", "createdBy", xmlWriter);

			// write the nil attribute
			writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
			xmlWriter.writeEndElement();
		} else {
			localCreatedBy.serialize(new javax.xml.namespace.QName("urn:sbmappservices72", "createdBy"), xmlWriter);
		}

		namespace = "urn:sbmappservices72";
		writeStartElement(null, namespace, "createDate", xmlWriter);

		if (localCreateDate == null) {
			// write the nil attribute

			throw new org.apache.axis2.databinding.ADBException("createDate cannot be null!!");

		} else {

			xmlWriter
					.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCreateDate));

		}

		xmlWriter.writeEndElement();
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

				throw new org.apache.axis2.databinding.ADBException("modifiedDate cannot be null!!");

			} else {

				xmlWriter.writeCharacters(
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localModifiedDate));

			}

			xmlWriter.writeEndElement();
		}
		if (localExecDateTracker) {
			namespace = "urn:sbmappservices72";
			writeStartElement(null, namespace, "execDate", xmlWriter);

			if (localExecDate == null) {
				// write the nil attribute

				throw new org.apache.axis2.databinding.ADBException("execDate cannot be null!!");

			} else {

				xmlWriter.writeCharacters(
						org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localExecDate));

			}

			xmlWriter.writeEndElement();
		}
		namespace = "urn:sbmappservices72";
		writeStartElement(null, namespace, "isQueryAtRuntime", xmlWriter);

		if (false) {

			throw new org.apache.axis2.databinding.ADBException("isQueryAtRuntime cannot be null!!");

		} else {
			xmlWriter.writeCharacters(
					org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIsQueryAtRuntime));
		}

		xmlWriter.writeEndElement();
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
		public static ReportInfo parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
			ReportInfo object = new ReportInfo();

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

						if (!"ReportInfo".equals(type)) {
							// find namespace for the prefix
							java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
							return (ReportInfo) com.mitel.ka.kcapp.services.sbm.serenadiagnostics.ExtensionMapper
									.getTypeObject(nsUri, type, reader);
						}

					}

				}

				// Note all attributes that were handled. Used to differ normal
				// attributes
				// from anyAttributes.
				java.util.Vector handledAttributes = new java.util.Vector();

				reader.next();

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "report").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "report").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						object.setReport(null);
						reader.next();

						reader.next();

					} else {

						object.setReport(com.mitel.ka.kcapp.services.sbm.sbmappservices72.ReportIdentifier.Factory
								.parse(reader));

						reader.next();
					}
				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "reportURL").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "reportURL").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

						java.lang.String content = reader.getElementText();

						object.setReportURL(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

					} else {

						reader.getElementText(); // throw away text nodes if
													// any.
					}

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
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "reportType").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "reportType").equals(reader.getName())) {

					object.setReportType(
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.ReportType.Factory.parse(reader));

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
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "reportCategory")
								.equals(reader.getName())
						|| new javax.xml.namespace.QName("", "reportCategory").equals(reader.getName())) {

					object.setReportCategory(
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.ReportCategory.Factory.parse(reader));

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
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "reportAccessLevel")
								.equals(reader.getName())
						|| new javax.xml.namespace.QName("", "reportAccessLevel").equals(reader.getName())) {

					object.setReportAccessLevel(
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.ReportAccessLevel.Factory.parse(reader));

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
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "solution").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "solution").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						object.setSolution(null);
						reader.next();

						reader.next();

					} else {

						object.setSolution(com.mitel.ka.kcapp.services.sbm.sbmappservices72.SolutionIdentifier.Factory
								.parse(reader));

						reader.next();
					}
				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "table").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "table").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						object.setTable(null);
						reader.next();

						reader.next();

					} else {

						object.setTable(
								com.mitel.ka.kcapp.services.sbm.sbmappservices72.TableIdentifier.Factory.parse(reader));

						reader.next();
					}
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
					// 1 - A start element we are not expecting indicates an
					// invalid parameter was passed
					throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "createDate").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "createDate").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						throw new org.apache.axis2.databinding.ADBException(
								"The element: " + "createDate" + "  cannot be null");
					}

					java.lang.String content = reader.getElementText();

					object.setCreateDate(org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(content));

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
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						throw new org.apache.axis2.databinding.ADBException(
								"The element: " + "modifiedDate" + "  cannot be null");
					}

					java.lang.String content = reader.getElementText();

					object.setModifiedDate(org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(content));

					reader.next();

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "execDate").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "execDate").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						throw new org.apache.axis2.databinding.ADBException(
								"The element: " + "execDate" + "  cannot be null");
					}

					java.lang.String content = reader.getElementText();

					object.setExecDate(org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(content));

					reader.next();

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "isQueryAtRuntime")
								.equals(reader.getName())
						|| new javax.xml.namespace.QName("", "isQueryAtRuntime").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						throw new org.apache.axis2.databinding.ADBException(
								"The element: " + "isQueryAtRuntime" + "  cannot be null");
					}

					java.lang.String content = reader.getElementText();

					object.setIsQueryAtRuntime(
							org.apache.axis2.databinding.utils.ConverterUtil.convertToBoolean(content));

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
