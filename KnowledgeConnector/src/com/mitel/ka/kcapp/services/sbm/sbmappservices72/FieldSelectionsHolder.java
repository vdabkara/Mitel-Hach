
/**
 * FieldSelectionsHolder.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.2  Built on : May 02, 2016 (05:56:08 BST)
 */

package com.mitel.ka.kcapp.services.sbm.sbmappservices72;

/**
 * FieldSelectionsHolder bean class
 */
@SuppressWarnings({ "unchecked", "unused" })

public class FieldSelectionsHolder implements org.apache.axis2.databinding.ADBBean {
	/*
	 * This type was generated from the piece of schema that had name =
	 * FieldSelectionsHolder Namespace URI = urn:sbmappservices72 Namespace
	 * Prefix =
	 */

	/**
	 * field for Field
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.FieldIdentifier localField;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localFieldTracker = false;

	public boolean isFieldSpecified() {
		return localFieldTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.FieldIdentifier
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.FieldIdentifier getField() {
		return localField;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Field
	 */
	public void setField(com.mitel.ka.kcapp.services.sbm.sbmappservices72.FieldIdentifier param) {
		localFieldTracker = true;

		this.localField = param;

	}

	/**
	 * field for Type
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.SelectionType localType;

	/**
	 * Auto generated getter method
	 * 
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.SelectionType
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.SelectionType getType() {
		return localType;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Type
	 */
	public void setType(com.mitel.ka.kcapp.services.sbm.sbmappservices72.SelectionType param) {

		this.localType = param;

	}

	/**
	 * field for Attribute This was an Array!
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.FieldAttribute[] localAttribute;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localAttributeTracker = false;

	public boolean isAttributeSpecified() {
		return localAttributeTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.FieldAttribute[]
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.FieldAttribute[] getAttribute() {
		return localAttribute;
	}

	/**
	 * validate the array for Attribute
	 */
	protected void validateAttribute(com.mitel.ka.kcapp.services.sbm.sbmappservices72.FieldAttribute[] param) {

	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Attribute
	 */
	public void setAttribute(com.mitel.ka.kcapp.services.sbm.sbmappservices72.FieldAttribute[] param) {

		validateAttribute(param);

		localAttributeTracker = param != null;

		this.localAttribute = param;
	}

	/**
	 * Auto generated add method for the array for convenience
	 * 
	 * @param param
	 *            com.mitel.ka.kcapp.services.sbm.sbmappservices72.
	 *            FieldAttribute
	 */
	public void addAttribute(com.mitel.ka.kcapp.services.sbm.sbmappservices72.FieldAttribute param) {
		if (localAttribute == null) {
			localAttribute = new com.mitel.ka.kcapp.services.sbm.sbmappservices72.FieldAttribute[] {};
		}

		// update the setting tracker
		localAttributeTracker = true;

		java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localAttribute);
		list.add(param);
		this.localAttribute = (com.mitel.ka.kcapp.services.sbm.sbmappservices72.FieldAttribute[]) list
				.toArray(new com.mitel.ka.kcapp.services.sbm.sbmappservices72.FieldAttribute[list.size()]);

	}

	/**
	 * field for Option This was an Array!
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.SelectionOption[] localOption;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localOptionTracker = false;

	public boolean isOptionSpecified() {
		return localOptionTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.SelectionOption[
	 *         ]
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.SelectionOption[] getOption() {
		return localOption;
	}

	/**
	 * validate the array for Option
	 */
	protected void validateOption(com.mitel.ka.kcapp.services.sbm.sbmappservices72.SelectionOption[] param) {

	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Option
	 */
	public void setOption(com.mitel.ka.kcapp.services.sbm.sbmappservices72.SelectionOption[] param) {

		validateOption(param);

		localOptionTracker = param != null;

		this.localOption = param;
	}

	/**
	 * Auto generated add method for the array for convenience
	 * 
	 * @param param
	 *            com.mitel.ka.kcapp.services.sbm.sbmappservices72.
	 *            SelectionOption
	 */
	public void addOption(com.mitel.ka.kcapp.services.sbm.sbmappservices72.SelectionOption param) {
		if (localOption == null) {
			localOption = new com.mitel.ka.kcapp.services.sbm.sbmappservices72.SelectionOption[] {};
		}

		// update the setting tracker
		localOptionTracker = true;

		java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localOption);
		list.add(param);
		this.localOption = (com.mitel.ka.kcapp.services.sbm.sbmappservices72.SelectionOption[]) list
				.toArray(new com.mitel.ka.kcapp.services.sbm.sbmappservices72.SelectionOption[list.size()]);

	}

	/**
	 * field for Status This was an Array!
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.Status[] localStatus;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localStatusTracker = false;

	public boolean isStatusSpecified() {
		return localStatusTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.Status[]
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.Status[] getStatus() {
		return localStatus;
	}

	/**
	 * validate the array for Status
	 */
	protected void validateStatus(com.mitel.ka.kcapp.services.sbm.sbmappservices72.Status[] param) {

	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Status
	 */
	public void setStatus(com.mitel.ka.kcapp.services.sbm.sbmappservices72.Status[] param) {

		validateStatus(param);

		localStatusTracker = param != null;

		this.localStatus = param;
	}

	/**
	 * Auto generated add method for the array for convenience
	 * 
	 * @param param
	 *            com.mitel.ka.kcapp.services.sbm.sbmappservices72.Status
	 */
	public void addStatus(com.mitel.ka.kcapp.services.sbm.sbmappservices72.Status param) {
		if (localStatus == null) {
			localStatus = new com.mitel.ka.kcapp.services.sbm.sbmappservices72.Status[] {};
		}

		// update the setting tracker
		localStatusTracker = true;

		java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localStatus);
		list.add(param);
		this.localStatus = (com.mitel.ka.kcapp.services.sbm.sbmappservices72.Status[]) list
				.toArray(new com.mitel.ka.kcapp.services.sbm.sbmappservices72.Status[list.size()]);

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
						namespacePrefix + ":FieldSelectionsHolder", xmlWriter);
			} else {
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "FieldSelectionsHolder",
						xmlWriter);
			}

		}
		if (localFieldTracker) {
			if (localField == null) {

				writeStartElement(null, "urn:sbmappservices72", "field", xmlWriter);

				// write the nil attribute
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
				xmlWriter.writeEndElement();
			} else {
				localField.serialize(new javax.xml.namespace.QName("urn:sbmappservices72", "field"), xmlWriter);
			}
		}
		if (localType == null) {
			throw new org.apache.axis2.databinding.ADBException("type cannot be null!!");
		}
		localType.serialize(new javax.xml.namespace.QName("urn:sbmappservices72", "type"), xmlWriter);
		if (localAttributeTracker) {
			if (localAttribute != null) {
				for (int i = 0; i < localAttribute.length; i++) {
					if (localAttribute[i] != null) {
						localAttribute[i].serialize(new javax.xml.namespace.QName("urn:sbmappservices72", "attribute"),
								xmlWriter);
					} else {

						// we don't have to do any thing since minOccures is
						// zero

					}

				}
			} else {

				throw new org.apache.axis2.databinding.ADBException("attribute cannot be null!!");

			}
		}
		if (localOptionTracker) {
			if (localOption != null) {
				for (int i = 0; i < localOption.length; i++) {
					if (localOption[i] != null) {
						localOption[i].serialize(new javax.xml.namespace.QName("urn:sbmappservices72", "option"),
								xmlWriter);
					} else {

						// we don't have to do any thing since minOccures is
						// zero

					}

				}
			} else {

				throw new org.apache.axis2.databinding.ADBException("option cannot be null!!");

			}
		}
		if (localStatusTracker) {
			if (localStatus != null) {
				for (int i = 0; i < localStatus.length; i++) {
					if (localStatus[i] != null) {
						localStatus[i].serialize(new javax.xml.namespace.QName("urn:sbmappservices72", "status"),
								xmlWriter);
					} else {

						// we don't have to do any thing since minOccures is
						// zero

					}

				}
			} else {

				throw new org.apache.axis2.databinding.ADBException("status cannot be null!!");

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
		public static FieldSelectionsHolder parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
			FieldSelectionsHolder object = new FieldSelectionsHolder();

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

						if (!"FieldSelectionsHolder".equals(type)) {
							// find namespace for the prefix
							java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
							return (FieldSelectionsHolder) com.mitel.ka.kcapp.services.sbm.serenadiagnostics.ExtensionMapper
									.getTypeObject(nsUri, type, reader);
						}

					}

				}

				// Note all attributes that were handled. Used to differ normal
				// attributes
				// from anyAttributes.
				java.util.Vector handledAttributes = new java.util.Vector();

				reader.next();

				java.util.ArrayList list3 = new java.util.ArrayList();

				java.util.ArrayList list4 = new java.util.ArrayList();

				java.util.ArrayList list5 = new java.util.ArrayList();

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "field").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "field").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						object.setField(null);
						reader.next();

						reader.next();

					} else {

						object.setField(
								com.mitel.ka.kcapp.services.sbm.sbmappservices72.FieldIdentifier.Factory.parse(reader));

						reader.next();
					}
				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "type").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "type").equals(reader.getName())) {

					object.setType(
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.SelectionType.Factory.parse(reader));

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
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "attribute").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "attribute").equals(reader.getName())) {

					// Process the array and step past its final element's end.

					list3.add(com.mitel.ka.kcapp.services.sbm.sbmappservices72.FieldAttribute.Factory.parse(reader));

					// loop until we find a start element that is not part of
					// this array
					boolean loopDone3 = false;
					while (!loopDone3) {
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
							loopDone3 = true;
						} else {
							if (new javax.xml.namespace.QName("urn:sbmappservices72", "attribute")
									.equals(reader.getName())) {
								list3.add(com.mitel.ka.kcapp.services.sbm.sbmappservices72.FieldAttribute.Factory
										.parse(reader));

							} else {
								loopDone3 = true;
							}
						}
					}
					// call the converter utility to convert and set the array

					object.setAttribute(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.FieldAttribute[]) org.apache.axis2.databinding.utils.ConverterUtil
									.convertToArray(
											com.mitel.ka.kcapp.services.sbm.sbmappservices72.FieldAttribute.class,
											list3));

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "option").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "option").equals(reader.getName())) {

					// Process the array and step past its final element's end.

					list4.add(com.mitel.ka.kcapp.services.sbm.sbmappservices72.SelectionOption.Factory.parse(reader));

					// loop until we find a start element that is not part of
					// this array
					boolean loopDone4 = false;
					while (!loopDone4) {
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
							loopDone4 = true;
						} else {
							if (new javax.xml.namespace.QName("urn:sbmappservices72", "option")
									.equals(reader.getName())) {
								list4.add(com.mitel.ka.kcapp.services.sbm.sbmappservices72.SelectionOption.Factory
										.parse(reader));

							} else {
								loopDone4 = true;
							}
						}
					}
					// call the converter utility to convert and set the array

					object.setOption(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.SelectionOption[]) org.apache.axis2.databinding.utils.ConverterUtil
									.convertToArray(
											com.mitel.ka.kcapp.services.sbm.sbmappservices72.SelectionOption.class,
											list4));

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "status").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "status").equals(reader.getName())) {

					// Process the array and step past its final element's end.

					list5.add(com.mitel.ka.kcapp.services.sbm.sbmappservices72.Status.Factory.parse(reader));

					// loop until we find a start element that is not part of
					// this array
					boolean loopDone5 = false;
					while (!loopDone5) {
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
							loopDone5 = true;
						} else {
							if (new javax.xml.namespace.QName("urn:sbmappservices72", "status")
									.equals(reader.getName())) {
								list5.add(
										com.mitel.ka.kcapp.services.sbm.sbmappservices72.Status.Factory.parse(reader));

							} else {
								loopDone5 = true;
							}
						}
					}
					// call the converter utility to convert and set the array

					object.setStatus(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.Status[]) org.apache.axis2.databinding.utils.ConverterUtil
									.convertToArray(com.mitel.ka.kcapp.services.sbm.sbmappservices72.Status.class,
											list5));

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
