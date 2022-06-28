
/**
 * GetUsers.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.2  Built on : May 02, 2016 (05:56:08 BST)
 */

package com.mitel.ka.kcapp.services.sbm.sbmappservices72;

/**
 * GetUsers bean class
 */
@SuppressWarnings({ "unchecked", "unused" })

public class GetUsers implements org.apache.axis2.databinding.ADBBean {

	public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName("urn:sbmappservices72",
			"GetUsers", "");

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
	 * field for GetCurrentUser
	 */

	protected boolean localGetCurrentUser;

	/**
	 * Auto generated getter method
	 * 
	 * @return boolean
	 */
	public boolean getGetCurrentUser() {
		return localGetCurrentUser;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            GetCurrentUser
	 */
	public void setGetCurrentUser(boolean param) {

		this.localGetCurrentUser = param;

	}

	/**
	 * field for SearchByName
	 */

	protected java.lang.String localSearchByName;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localSearchByNameTracker = false;

	public boolean isSearchByNameSpecified() {
		return localSearchByNameTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getSearchByName() {
		return localSearchByName;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            SearchByName
	 */
	public void setSearchByName(java.lang.String param) {
		localSearchByNameTracker = true;

		this.localSearchByName = param;

	}

	/**
	 * field for User This was an Array!
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserIdentifier[] localUser;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localUserTracker = false;

	public boolean isUserSpecified() {
		return localUserTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserIdentifier[]
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserIdentifier[] getUser() {
		return localUser;
	}

	/**
	 * validate the array for User
	 */
	protected void validateUser(com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserIdentifier[] param) {

	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            User
	 */
	public void setUser(com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserIdentifier[] param) {

		validateUser(param);

		localUserTracker = param != null;

		this.localUser = param;
	}

	/**
	 * Auto generated add method for the array for convenience
	 * 
	 * @param param
	 *            com.mitel.ka.kcapp.services.sbm.sbmappservices72.
	 *            UserIdentifier
	 */
	public void addUser(com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserIdentifier param) {
		if (localUser == null) {
			localUser = new com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserIdentifier[] {};
		}

		// update the setting tracker
		localUserTracker = true;

		java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localUser);
		list.add(param);
		this.localUser = (com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserIdentifier[]) list
				.toArray(new com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserIdentifier[list.size()]);

	}

	/**
	 * field for Options
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserResponseOptions localOptions;

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
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.
	 *         UserResponseOptions
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserResponseOptions getOptions() {
		return localOptions;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Options
	 */
	public void setOptions(com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserResponseOptions param) {
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
						namespacePrefix + ":GetUsers", xmlWriter);
			} else {
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "GetUsers", xmlWriter);
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
		namespace = "urn:sbmappservices72";
		writeStartElement(null, namespace, "getCurrentUser", xmlWriter);

		if (false) {

			throw new org.apache.axis2.databinding.ADBException("getCurrentUser cannot be null!!");

		} else {
			xmlWriter.writeCharacters(
					org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localGetCurrentUser));
		}

		xmlWriter.writeEndElement();
		if (localSearchByNameTracker) {
			namespace = "urn:sbmappservices72";
			writeStartElement(null, namespace, "searchByName", xmlWriter);

			if (localSearchByName == null) {
				// write the nil attribute

				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);

			} else {

				xmlWriter.writeCharacters(localSearchByName);

			}

			xmlWriter.writeEndElement();
		}
		if (localUserTracker) {
			if (localUser != null) {
				for (int i = 0; i < localUser.length; i++) {
					if (localUser[i] != null) {
						localUser[i].serialize(new javax.xml.namespace.QName("urn:sbmappservices72", "user"),
								xmlWriter);
					} else {

						// we don't have to do any thing since minOccures is
						// zero

					}

				}
			} else {

				throw new org.apache.axis2.databinding.ADBException("user cannot be null!!");

			}
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
		public static GetUsers parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
			GetUsers object = new GetUsers();

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

						if (!"GetUsers".equals(type)) {
							// find namespace for the prefix
							java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
							return (GetUsers) com.mitel.ka.kcapp.services.sbm.serenadiagnostics.ExtensionMapper
									.getTypeObject(nsUri, type, reader);
						}

					}

				}

				// Note all attributes that were handled. Used to differ normal
				// attributes
				// from anyAttributes.
				java.util.Vector handledAttributes = new java.util.Vector();

				reader.next();

				java.util.ArrayList list4 = new java.util.ArrayList();

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
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "getCurrentUser")
								.equals(reader.getName())
						|| new javax.xml.namespace.QName("", "getCurrentUser").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						throw new org.apache.axis2.databinding.ADBException(
								"The element: " + "getCurrentUser" + "  cannot be null");
					}

					java.lang.String content = reader.getElementText();

					object.setGetCurrentUser(
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
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "searchByName")
								.equals(reader.getName())
						|| new javax.xml.namespace.QName("", "searchByName").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if (!"true".equals(nillableValue) && !"1".equals(nillableValue)) {

						java.lang.String content = reader.getElementText();

						object.setSearchByName(
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
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "user").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "user").equals(reader.getName())) {

					// Process the array and step past its final element's end.

					list4.add(com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserIdentifier.Factory.parse(reader));

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
							if (new javax.xml.namespace.QName("urn:sbmappservices72", "user")
									.equals(reader.getName())) {
								list4.add(com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserIdentifier.Factory
										.parse(reader));

							} else {
								loopDone4 = true;
							}
						}
					}
					// call the converter utility to convert and set the array

					object.setUser(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserIdentifier[]) org.apache.axis2.databinding.utils.ConverterUtil
									.convertToArray(
											com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserIdentifier.class,
											list4));

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

						object.setOptions(com.mitel.ka.kcapp.services.sbm.sbmappservices72.UserResponseOptions.Factory
								.parse(reader));

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
