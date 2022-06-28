
/**
 * DeleteItems.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.2  Built on : May 02, 2016 (05:56:08 BST)
 */

package com.mitel.ka.kcapp.services.sbm.sbmappservices72;

/**
 * DeleteItems bean class
 */
@SuppressWarnings({ "unchecked", "unused" })

public class DeleteItems implements org.apache.axis2.databinding.ADBBean {

	public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName("urn:sbmappservices72",
			"DeleteItems", "");

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
	 * field for ItemId This was an Array!
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.ItemIdentifier[] localItemId;

	/**
	 * Auto generated getter method
	 * 
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.ItemIdentifier[]
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.ItemIdentifier[] getItemId() {
		return localItemId;
	}

	/**
	 * validate the array for ItemId
	 */
	protected void validateItemId(com.mitel.ka.kcapp.services.sbm.sbmappservices72.ItemIdentifier[] param) {

		if ((param != null) && (param.length < 1)) {
			throw new java.lang.RuntimeException("Input values do not follow defined XSD restrictions");
		}

	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            ItemId
	 */
	public void setItemId(com.mitel.ka.kcapp.services.sbm.sbmappservices72.ItemIdentifier[] param) {

		validateItemId(param);

		this.localItemId = param;
	}

	/**
	 * Auto generated add method for the array for convenience
	 * 
	 * @param param
	 *            com.mitel.ka.kcapp.services.sbm.sbmappservices72.
	 *            ItemIdentifier
	 */
	public void addItemId(com.mitel.ka.kcapp.services.sbm.sbmappservices72.ItemIdentifier param) {
		if (localItemId == null) {
			localItemId = new com.mitel.ka.kcapp.services.sbm.sbmappservices72.ItemIdentifier[] {};
		}

		java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localItemId);
		list.add(param);
		this.localItemId = (com.mitel.ka.kcapp.services.sbm.sbmappservices72.ItemIdentifier[]) list
				.toArray(new com.mitel.ka.kcapp.services.sbm.sbmappservices72.ItemIdentifier[list.size()]);

	}

	/**
	 * field for Options
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.MultipleOptions localOptions;

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
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.MultipleOptions
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.MultipleOptions getOptions() {
		return localOptions;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Options
	 */
	public void setOptions(com.mitel.ka.kcapp.services.sbm.sbmappservices72.MultipleOptions param) {
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
						namespacePrefix + ":DeleteItems", xmlWriter);
			} else {
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "DeleteItems", xmlWriter);
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
		if (localItemId != null) {
			for (int i = 0; i < localItemId.length; i++) {
				if (localItemId[i] != null) {
					localItemId[i].serialize(new javax.xml.namespace.QName("urn:sbmappservices72", "itemId"),
							xmlWriter);
				} else {

					throw new org.apache.axis2.databinding.ADBException("itemId cannot be null!!");

				}

			}
		} else {

			throw new org.apache.axis2.databinding.ADBException("itemId cannot be null!!");

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
		public static DeleteItems parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
			DeleteItems object = new DeleteItems();

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

						if (!"DeleteItems".equals(type)) {
							// find namespace for the prefix
							java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
							return (DeleteItems) com.mitel.ka.kcapp.services.sbm.serenadiagnostics.ExtensionMapper
									.getTypeObject(nsUri, type, reader);
						}

					}

				}

				// Note all attributes that were handled. Used to differ normal
				// attributes
				// from anyAttributes.
				java.util.Vector handledAttributes = new java.util.Vector();

				reader.next();

				java.util.ArrayList list2 = new java.util.ArrayList();

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
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "itemId").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "itemId").equals(reader.getName())) {

					// Process the array and step past its final element's end.

					list2.add(com.mitel.ka.kcapp.services.sbm.sbmappservices72.ItemIdentifier.Factory.parse(reader));

					// loop until we find a start element that is not part of
					// this array
					boolean loopDone2 = false;
					while (!loopDone2) {
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
							loopDone2 = true;
						} else {
							if (new javax.xml.namespace.QName("urn:sbmappservices72", "itemId")
									.equals(reader.getName())) {
								list2.add(com.mitel.ka.kcapp.services.sbm.sbmappservices72.ItemIdentifier.Factory
										.parse(reader));

							} else {
								loopDone2 = true;
							}
						}
					}
					// call the converter utility to convert and set the array

					object.setItemId(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.ItemIdentifier[]) org.apache.axis2.databinding.utils.ConverterUtil
									.convertToArray(
											com.mitel.ka.kcapp.services.sbm.sbmappservices72.ItemIdentifier.class,
											list2));

				} // End of if for expected property start element

				else {
					// 1 - A start element we are not expecting indicates an
					// invalid parameter was passed
					throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
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
								com.mitel.ka.kcapp.services.sbm.sbmappservices72.MultipleOptions.Factory.parse(reader));

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
