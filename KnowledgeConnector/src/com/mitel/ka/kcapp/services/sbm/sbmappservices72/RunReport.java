
/**
 * RunReport.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.2  Built on : May 02, 2016 (05:56:08 BST)
 */

package com.mitel.ka.kcapp.services.sbm.sbmappservices72;

/**
 * RunReport bean class
 */
@SuppressWarnings({ "unchecked", "unused" })

public class RunReport implements org.apache.axis2.databinding.ADBBean {

	public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName("urn:sbmappservices72",
			"RunReport", "");

	/**
	 * field for Auth
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.Auth localAuth;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localAuthTracker = false;

	public boolean isAuthSpecified() {
		return localAuthTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.Auth
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.Auth getAuth() {
		return localAuth;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Auth
	 */
	public void setAuth(com.mitel.ka.kcapp.services.sbm.sbmappservices72.Auth param) {
		localAuthTracker = true;

		this.localAuth = param;

	}

	/**
	 * field for QueryRange
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.QueryRange localQueryRange;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localQueryRangeTracker = false;

	public boolean isQueryRangeSpecified() {
		return localQueryRangeTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.QueryRange
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.QueryRange getQueryRange() {
		return localQueryRange;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            QueryRange
	 */
	public void setQueryRange(com.mitel.ka.kcapp.services.sbm.sbmappservices72.QueryRange param) {
		localQueryRangeTracker = true;

		this.localQueryRange = param;

	}

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
	 * field for ReportCategory
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.ReportCategory localReportCategory;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localReportCategoryTracker = false;

	public boolean isReportCategorySpecified() {
		return localReportCategoryTracker;
	}

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
		localReportCategoryTracker = param != null;

		this.localReportCategory = param;

	}

	/**
	 * field for ReportAccessLevel
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.ReportAccessLevel localReportAccessLevel;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localReportAccessLevelTracker = false;

	public boolean isReportAccessLevelSpecified() {
		return localReportAccessLevelTracker;
	}

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
		localReportAccessLevelTracker = param != null;

		this.localReportAccessLevel = param;

	}

	/**
	 * field for Options
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.Options localOptions;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localOptionsTracker = false;

	public boolean isOptionsSpecified() {
		return localOptionsTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.Options
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.Options getOptions() {
		return localOptions;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Options
	 */
	public void setOptions(com.mitel.ka.kcapp.services.sbm.sbmappservices72.Options param) {
		localOptionsTracker = true;

		this.localOptions = param;

	}

	/**
	 *
	 * @param parentQName
	 * @param factory
	 * @return org.apache.axiom.om.OMElement
	 */
	public org.apache.axiom.om.OMElement getOMElement(final javax.xml.namespace.QName parentQName,
			final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException {

		return factory.createOMElement(new org.apache.axis2.databinding.ADBDataSource(this, MY_QNAME));

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
						namespacePrefix + ":RunReport", xmlWriter);
			} else {
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "RunReport", xmlWriter);
			}

		}
		if (localAuthTracker) {
			if (localAuth == null) {

				writeStartElement(null, "urn:sbmappservices72", "auth", xmlWriter);

				// write the nil attribute
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
				xmlWriter.writeEndElement();
			} else {
				localAuth.serialize(new javax.xml.namespace.QName("urn:sbmappservices72", "auth"), xmlWriter);
			}
		}
		if (localQueryRangeTracker) {
			if (localQueryRange == null) {

				writeStartElement(null, "urn:sbmappservices72", "queryRange", xmlWriter);

				// write the nil attribute
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
				xmlWriter.writeEndElement();
			} else {
				localQueryRange.serialize(new javax.xml.namespace.QName("urn:sbmappservices72", "queryRange"),
						xmlWriter);
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
		if (localReportCategoryTracker) {
			if (localReportCategory == null) {
				throw new org.apache.axis2.databinding.ADBException("reportCategory cannot be null!!");
			}
			localReportCategory.serialize(new javax.xml.namespace.QName("urn:sbmappservices72", "reportCategory"),
					xmlWriter);
		}
		if (localReportAccessLevelTracker) {
			if (localReportAccessLevel == null) {
				throw new org.apache.axis2.databinding.ADBException("reportAccessLevel cannot be null!!");
			}
			localReportAccessLevel.serialize(new javax.xml.namespace.QName("urn:sbmappservices72", "reportAccessLevel"),
					xmlWriter);
		}
		if (localOptionsTracker) {
			if (localOptions == null) {

				writeStartElement(null, "urn:sbmappservices72", "options", xmlWriter);

				// write the nil attribute
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
				xmlWriter.writeEndElement();
			} else {
				localOptions.serialize(new javax.xml.namespace.QName("urn:sbmappservices72", "options"), xmlWriter);
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
		public static RunReport parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
			RunReport object = new RunReport();

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

						if (!"RunReport".equals(type)) {
							// find namespace for the prefix
							java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
							return (RunReport) com.mitel.ka.kcapp.services.sbm.serenadiagnostics.ExtensionMapper
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
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "auth").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "auth").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						object.setAuth(null);
						reader.next();

						reader.next();

					} else {

						object.setAuth(com.mitel.ka.kcapp.services.sbm.sbmappservices72.Auth.Factory.parse(reader));

						reader.next();
					}
				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "queryRange").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "queryRange").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						object.setQueryRange(null);
						reader.next();

						reader.next();

					} else {

						object.setQueryRange(
								com.mitel.ka.kcapp.services.sbm.sbmappservices72.QueryRange.Factory.parse(reader));

						reader.next();
					}
				} // End of if for expected property start element

				else {

				}

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
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "reportCategory")
								.equals(reader.getName())
						|| new javax.xml.namespace.QName("", "reportCategory").equals(reader.getName())) {

					object.setReportCategory(
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.ReportCategory.Factory.parse(reader));

					reader.next();

				} // End of if for expected property start element

				else {

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

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "options").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "options").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						object.setOptions(null);
						reader.next();

						reader.next();

					} else {

						object.setOptions(
								com.mitel.ka.kcapp.services.sbm.sbmappservices72.Options.Factory.parse(reader));

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
