
/**
 * WorkflowBand.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.2  Built on : May 02, 2016 (05:56:08 BST)
 */

package com.mitel.ka.kcapp.services.sbm.sbmappservices72;

/**
 * WorkflowBand bean class
 */
@SuppressWarnings({ "unchecked", "unused" })

public class WorkflowBand implements org.apache.axis2.databinding.ADBBean {
	/*
	 * This type was generated from the piece of schema that had name =
	 * WorkflowBand Namespace URI = urn:sbmappservices72 Namespace Prefix =
	 */

	/**
	 * field for Id
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.Identifier localId;

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
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.Identifier
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.Identifier getId() {
		return localId;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Id
	 */
	public void setId(com.mitel.ka.kcapp.services.sbm.sbmappservices72.Identifier param) {
		localIdTracker = true;

		this.localId = param;

	}

	/**
	 * field for Origin
	 */

	protected java.math.BigInteger localOrigin;

	/**
	 * Auto generated getter method
	 * 
	 * @return java.math.BigInteger
	 */
	public java.math.BigInteger getOrigin() {
		return localOrigin;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Origin
	 */
	public void setOrigin(java.math.BigInteger param) {

		this.localOrigin = param;

	}

	/**
	 * field for Orientation
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.BandOrientation localOrientation;

	/**
	 * Auto generated getter method
	 * 
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.BandOrientation
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.BandOrientation getOrientation() {
		return localOrientation;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Orientation
	 */
	public void setOrientation(com.mitel.ka.kcapp.services.sbm.sbmappservices72.BandOrientation param) {

		this.localOrientation = param;

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
	 * field for Size
	 */

	protected java.math.BigInteger localSize;

	/**
	 * Auto generated getter method
	 * 
	 * @return java.math.BigInteger
	 */
	public java.math.BigInteger getSize() {
		return localSize;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Size
	 */
	public void setSize(java.math.BigInteger param) {

		this.localSize = param;

	}

	/**
	 * field for StyleClass
	 */

	protected java.lang.String localStyleClass;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localStyleClassTracker = false;

	public boolean isStyleClassSpecified() {
		return localStyleClassTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getStyleClass() {
		return localStyleClass;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            StyleClass
	 */
	public void setStyleClass(java.lang.String param) {
		localStyleClassTracker = true;

		this.localStyleClass = param;

	}

	/**
	 * field for StyleOverride
	 */

	protected java.lang.String localStyleOverride;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localStyleOverrideTracker = false;

	public boolean isStyleOverrideSpecified() {
		return localStyleOverrideTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getStyleOverride() {
		return localStyleOverride;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            StyleOverride
	 */
	public void setStyleOverride(java.lang.String param) {
		localStyleOverrideTracker = true;

		this.localStyleOverride = param;

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
						namespacePrefix + ":WorkflowBand", xmlWriter);
			} else {
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "WorkflowBand", xmlWriter);
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
		namespace = "urn:sbmappservices72";
		writeStartElement(null, namespace, "origin", xmlWriter);

		if (localOrigin == null) {
			// write the nil attribute

			throw new org.apache.axis2.databinding.ADBException("origin cannot be null!!");

		} else {

			xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localOrigin));

		}

		xmlWriter.writeEndElement();

		if (localOrientation == null) {
			throw new org.apache.axis2.databinding.ADBException("orientation cannot be null!!");
		}
		localOrientation.serialize(new javax.xml.namespace.QName("urn:sbmappservices72", "orientation"), xmlWriter);
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
		namespace = "urn:sbmappservices72";
		writeStartElement(null, namespace, "size", xmlWriter);

		if (localSize == null) {
			// write the nil attribute

			throw new org.apache.axis2.databinding.ADBException("size cannot be null!!");

		} else {

			xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSize));

		}

		xmlWriter.writeEndElement();
		if (localStyleClassTracker) {
			namespace = "urn:sbmappservices72";
			writeStartElement(null, namespace, "styleClass", xmlWriter);

			if (localStyleClass == null) {
				// write the nil attribute

				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

			} else {

				xmlWriter.writeCharacters(localStyleClass);

			}

			xmlWriter.writeEndElement();
		}
		if (localStyleOverrideTracker) {
			namespace = "urn:sbmappservices72";
			writeStartElement(null, namespace, "styleOverride", xmlWriter);

			if (localStyleOverride == null) {
				// write the nil attribute

				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

			} else {

				xmlWriter.writeCharacters(localStyleOverride);

			}

			xmlWriter.writeEndElement();
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
		public static WorkflowBand parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
			WorkflowBand object = new WorkflowBand();

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

						if (!"WorkflowBand".equals(type)) {
							// find namespace for the prefix
							java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
							return (WorkflowBand) com.mitel.ka.kcapp.services.sbm.serenadiagnostics.ExtensionMapper
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
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "id").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "id").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						object.setId(null);
						reader.next();

						reader.next();

					} else {

						object.setId(com.mitel.ka.kcapp.services.sbm.sbmappservices72.Identifier.Factory.parse(reader));

						reader.next();
					}
				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "origin").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "origin").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						throw new org.apache.axis2.databinding.ADBException(
								"The element: " + "origin" + "  cannot be null");
					}

					java.lang.String content = reader.getElementText();

					object.setOrigin(org.apache.axis2.databinding.utils.ConverterUtil.convertToInteger(content));

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
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "orientation").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "orientation").equals(reader.getName())) {

					object.setOrientation(
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.BandOrientation.Factory.parse(reader));

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
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "size").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "size").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						throw new org.apache.axis2.databinding.ADBException(
								"The element: " + "size" + "  cannot be null");
					}

					java.lang.String content = reader.getElementText();

					object.setSize(org.apache.axis2.databinding.utils.ConverterUtil.convertToInteger(content));

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
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "styleClass").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "styleClass").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

						java.lang.String content = reader.getElementText();

						object.setStyleClass(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));

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
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "styleOverride")
								.equals(reader.getName())
						|| new javax.xml.namespace.QName("", "styleOverride").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

						java.lang.String content = reader.getElementText();

						object.setStyleOverride(
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
