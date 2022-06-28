
/**
 * FieldType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.2  Built on : May 02, 2016 (05:56:08 BST)
 */

package com.mitel.ka.kcapp.services.sbm.sbmappservices72;

/**
 * FieldType bean class
 */
@SuppressWarnings({ "unchecked", "unused" })

public class FieldType implements org.apache.axis2.databinding.ADBBean {

	public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName("urn:sbmappservices72",
			"Field-Type", "");

	/**
	 * field for FieldType
	 */

	protected java.lang.String localFieldType;

	private static java.util.HashMap _table_ = new java.util.HashMap();

	// Constructor

	protected FieldType(java.lang.String value, boolean isRegisterValue) {
		localFieldType = value;
		if (isRegisterValue) {

			_table_.put(localFieldType, this);

		}

	}

	public static final java.lang.String _value1 = org.apache.axis2.databinding.utils.ConverterUtil
			.convertToString("FLDTYPE-UNKNOWN");

	public static final java.lang.String _value2 = org.apache.axis2.databinding.utils.ConverterUtil
			.convertToString("FLDTYPE-NUMERIC");

	public static final java.lang.String _value3 = org.apache.axis2.databinding.utils.ConverterUtil
			.convertToString("FLDTYPE-TEXT");

	public static final java.lang.String _value4 = org.apache.axis2.databinding.utils.ConverterUtil
			.convertToString("FLDTYPE-DATETIME");

	public static final java.lang.String _value5 = org.apache.axis2.databinding.utils.ConverterUtil
			.convertToString("FLDTYPE-SELECTION");

	public static final java.lang.String _value6 = org.apache.axis2.databinding.utils.ConverterUtil
			.convertToString("FLDTYPE-BINARY");

	public static final java.lang.String _value7 = org.apache.axis2.databinding.utils.ConverterUtil
			.convertToString("FLDTYPE-STATE");

	public static final java.lang.String _value8 = org.apache.axis2.databinding.utils.ConverterUtil
			.convertToString("FLDTYPE-USER");

	public static final java.lang.String _value9 = org.apache.axis2.databinding.utils.ConverterUtil
			.convertToString("FLDTYPE-PROJECT");

	public static final java.lang.String _value10 = org.apache.axis2.databinding.utils.ConverterUtil
			.convertToString("FLDTYPE-SUMMATION");

	public static final java.lang.String _value11 = org.apache.axis2.databinding.utils.ConverterUtil
			.convertToString("FLDTYPE-MULTIPLE-SELECTION");

	public static final java.lang.String _value12 = org.apache.axis2.databinding.utils.ConverterUtil
			.convertToString("FLDTYPE-CONTACT");

	public static final java.lang.String _value13 = org.apache.axis2.databinding.utils.ConverterUtil
			.convertToString("FLDTYPE-INCIDENT");

	public static final java.lang.String _value14 = org.apache.axis2.databinding.utils.ConverterUtil
			.convertToString("FLDTYPE-FOLDER");

	public static final java.lang.String _value15 = org.apache.axis2.databinding.utils.ConverterUtil
			.convertToString("FLDTYPE-RELATIONAL");

	public static final java.lang.String _value16 = org.apache.axis2.databinding.utils.ConverterUtil
			.convertToString("FLDTYPE-SUBRELATIONAL");

	public static final java.lang.String _value17 = org.apache.axis2.databinding.utils.ConverterUtil
			.convertToString("FLDTYPE-SYSTEM");

	public static final java.lang.String _value18 = org.apache.axis2.databinding.utils.ConverterUtil
			.convertToString("FLDTYPE-MULTIPLE-RELATIONAL");

	public static final java.lang.String _value19 = org.apache.axis2.databinding.utils.ConverterUtil
			.convertToString("FLDTYPE-MULTIPLE-GROUP");

	public static final java.lang.String _value20 = org.apache.axis2.databinding.utils.ConverterUtil
			.convertToString("FLDTYPE-MULTIPLE-USERGROUP");

	public static final FieldType value1 = new FieldType(_value1, true);

	public static final FieldType value2 = new FieldType(_value2, true);

	public static final FieldType value3 = new FieldType(_value3, true);

	public static final FieldType value4 = new FieldType(_value4, true);

	public static final FieldType value5 = new FieldType(_value5, true);

	public static final FieldType value6 = new FieldType(_value6, true);

	public static final FieldType value7 = new FieldType(_value7, true);

	public static final FieldType value8 = new FieldType(_value8, true);

	public static final FieldType value9 = new FieldType(_value9, true);

	public static final FieldType value10 = new FieldType(_value10, true);

	public static final FieldType value11 = new FieldType(_value11, true);

	public static final FieldType value12 = new FieldType(_value12, true);

	public static final FieldType value13 = new FieldType(_value13, true);

	public static final FieldType value14 = new FieldType(_value14, true);

	public static final FieldType value15 = new FieldType(_value15, true);

	public static final FieldType value16 = new FieldType(_value16, true);

	public static final FieldType value17 = new FieldType(_value17, true);

	public static final FieldType value18 = new FieldType(_value18, true);

	public static final FieldType value19 = new FieldType(_value19, true);

	public static final FieldType value20 = new FieldType(_value20, true);

	public java.lang.String getValue() {
		return localFieldType;
	}

	public boolean equals(java.lang.Object obj) {
		return (obj == this);
	}

	public int hashCode() {
		return toString().hashCode();
	}

	public java.lang.String toString() {

		return localFieldType.toString();

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

		// We can safely assume an element has only one type associated with it

		java.lang.String namespace = parentQName.getNamespaceURI();
		java.lang.String _localName = parentQName.getLocalPart();

		writeStartElement(null, namespace, _localName, xmlWriter);

		// add the type details if this is used in a simple type
		if (serializeType) {
			java.lang.String namespacePrefix = registerPrefix(xmlWriter, "urn:sbmappservices72");
			if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)) {
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type",
						namespacePrefix + ":Field-Type", xmlWriter);
			} else {
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "Field-Type", xmlWriter);
			}
		}

		if (localFieldType == null) {

			throw new org.apache.axis2.databinding.ADBException("Field-Type cannot be null !!");

		} else {

			xmlWriter.writeCharacters(localFieldType);

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

		public static FieldType fromValue(java.lang.String value) throws java.lang.IllegalArgumentException {
			FieldType enumeration = (FieldType)

			_table_.get(value);

			// handle unexpected enumeration values properly

			if (enumeration == null) {
				throw new java.lang.IllegalArgumentException();
			}
			return enumeration;

		}

		public static FieldType fromString(java.lang.String value, java.lang.String namespaceURI)
				throws java.lang.IllegalArgumentException {
			try {

				return fromValue(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(value));

			} catch (java.lang.Exception e) {
				throw new java.lang.IllegalArgumentException();
			}
		}

		public static FieldType fromString(javax.xml.stream.XMLStreamReader xmlStreamReader, java.lang.String content) {
			if (content.indexOf(":") > -1) {
				java.lang.String prefix = content.substring(0, content.indexOf(":"));
				java.lang.String namespaceUri = xmlStreamReader.getNamespaceContext().getNamespaceURI(prefix);
				return FieldType.Factory.fromString(content, namespaceUri);
			} else {
				return FieldType.Factory.fromString(content, "");
			}
		}

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
		public static FieldType parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
			FieldType object = null;
			// initialize a hash map to keep values
			java.util.Map attributeMap = new java.util.HashMap();
			java.util.List extraAttributeList = new java.util.ArrayList<org.apache.axiom.om.OMAttribute>();

			int event;
			javax.xml.namespace.QName currentQName = null;
			java.lang.String nillableValue = null;
			java.lang.String prefix = "";
			java.lang.String namespaceuri = "";
			try {

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				currentQName = reader.getName();

				// Note all attributes that were handled. Used to differ normal
				// attributes
				// from anyAttributes.
				java.util.Vector handledAttributes = new java.util.Vector();

				while (!reader.isEndElement()) {
					if (reader.isStartElement() || reader.hasText()) {

						nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
						if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
							throw new org.apache.axis2.databinding.ADBException(
									"The element: " + "Field-Type" + "  cannot be null");
						}

						java.lang.String content = reader.getElementText();

						if (content.indexOf(":") > 0) {
							// this seems to be a Qname so find the namespace
							// and send
							prefix = content.substring(0, content.indexOf(":"));
							namespaceuri = reader.getNamespaceURI(prefix);
							object = FieldType.Factory.fromString(content, namespaceuri);
						} else {
							// this seems to be not a qname send and empty
							// namespace incase of it is
							// check is done in fromString method
							object = FieldType.Factory.fromString(content, "");
						}

					} else {
						reader.next();
					}
				} // end of while loop

			} catch (javax.xml.stream.XMLStreamException e) {
				throw new java.lang.Exception(e);
			}

			return object;
		}

	}// end of factory class

}
