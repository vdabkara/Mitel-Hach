
/**
 * Transition.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.2  Built on : May 02, 2016 (05:56:08 BST)
 */

package com.mitel.ka.kcapp.services.sbm.sbmappservices72;

/**
 * Transition bean class
 */
@SuppressWarnings({ "unchecked", "unused" })

public class Transition implements org.apache.axis2.databinding.ADBBean {
	/*
	 * This type was generated from the piece of schema that had name =
	 * Transition Namespace URI = urn:sbmappservices72 Namespace Prefix =
	 */

	/**
	 * field for Transition
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionIdentifier localTransition;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localTransitionTracker = false;

	public boolean isTransitionSpecified() {
		return localTransitionTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.
	 *         TransitionIdentifier
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionIdentifier getTransition() {
		return localTransition;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Transition
	 */
	public void setTransition(com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionIdentifier param) {
		localTransitionTracker = true;

		this.localTransition = param;

	}

	/**
	 * field for FromState
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.StateIdentifier localFromState;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localFromStateTracker = false;

	public boolean isFromStateSpecified() {
		return localFromStateTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.StateIdentifier
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.StateIdentifier getFromState() {
		return localFromState;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            FromState
	 */
	public void setFromState(com.mitel.ka.kcapp.services.sbm.sbmappservices72.StateIdentifier param) {
		localFromStateTracker = true;

		this.localFromState = param;

	}

	/**
	 * field for ToState
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.StateIdentifier localToState;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localToStateTracker = false;

	public boolean isToStateSpecified() {
		return localToStateTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.StateIdentifier
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.StateIdentifier getToState() {
		return localToState;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            ToState
	 */
	public void setToState(com.mitel.ka.kcapp.services.sbm.sbmappservices72.StateIdentifier param) {
		localToStateTracker = true;

		this.localToState = param;

	}

	/**
	 * field for Type
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionType localType;

	/**
	 * Auto generated getter method
	 * 
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionType
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionType getType() {
		return localType;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Type
	 */
	public void setType(com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionType param) {

		this.localType = param;

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
	 * field for TransitionAttribute This was an Array!
	 */

	protected java.lang.String[] localTransitionAttribute;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localTransitionAttributeTracker = false;

	public boolean isTransitionAttributeSpecified() {
		return localTransitionAttributeTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return java.lang.String[]
	 */
	public java.lang.String[] getTransitionAttribute() {
		return localTransitionAttribute;
	}

	/**
	 * validate the array for TransitionAttribute
	 */
	protected void validateTransitionAttribute(java.lang.String[] param) {

	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            TransitionAttribute
	 */
	public void setTransitionAttribute(java.lang.String[] param) {

		validateTransitionAttribute(param);

		localTransitionAttributeTracker = param != null;

		this.localTransitionAttribute = param;
	}

	/**
	 * Auto generated add method for the array for convenience
	 * 
	 * @param param
	 *            java.lang.String
	 */
	public void addTransitionAttribute(java.lang.String param) {
		if (localTransitionAttribute == null) {
			localTransitionAttribute = new java.lang.String[] {};
		}

		// update the setting tracker
		localTransitionAttributeTracker = true;

		java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localTransitionAttribute);
		list.add(param);
		this.localTransitionAttribute = (java.lang.String[]) list.toArray(new java.lang.String[list.size()]);

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
						namespacePrefix + ":Transition", xmlWriter);
			} else {
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "Transition", xmlWriter);
			}

		}
		if (localTransitionTracker) {
			if (localTransition == null) {

				writeStartElement(null, "urn:sbmappservices72", "transition", xmlWriter);

				// write the nil attribute
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
				xmlWriter.writeEndElement();
			} else {
				localTransition.serialize(new javax.xml.namespace.QName("urn:sbmappservices72", "transition"),
						xmlWriter);
			}
		}
		if (localFromStateTracker) {
			if (localFromState == null) {

				writeStartElement(null, "urn:sbmappservices72", "fromState", xmlWriter);

				// write the nil attribute
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
				xmlWriter.writeEndElement();
			} else {
				localFromState.serialize(new javax.xml.namespace.QName("urn:sbmappservices72", "fromState"), xmlWriter);
			}
		}
		if (localToStateTracker) {
			if (localToState == null) {

				writeStartElement(null, "urn:sbmappservices72", "toState", xmlWriter);

				// write the nil attribute
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "nil", "1", xmlWriter);
				xmlWriter.writeEndElement();
			} else {
				localToState.serialize(new javax.xml.namespace.QName("urn:sbmappservices72", "toState"), xmlWriter);
			}
		}
		if (localType == null) {
			throw new org.apache.axis2.databinding.ADBException("type cannot be null!!");
		}
		localType.serialize(new javax.xml.namespace.QName("urn:sbmappservices72", "type"), xmlWriter);
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
		if (localTransitionAttributeTracker) {
			if (localTransitionAttribute != null) {
				namespace = "urn:sbmappservices72";
				for (int i = 0; i < localTransitionAttribute.length; i++) {

					if (localTransitionAttribute[i] != null) {

						writeStartElement(null, namespace, "transitionAttribute", xmlWriter);

						xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil
								.convertToString(localTransitionAttribute[i]));

						xmlWriter.writeEndElement();

					} else {

						// we have to do nothing since minOccurs is zero

					}

				}
			} else {

				throw new org.apache.axis2.databinding.ADBException("transitionAttribute cannot be null!!");

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
		public static Transition parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
			Transition object = new Transition();

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

						if (!"Transition".equals(type)) {
							// find namespace for the prefix
							java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
							return (Transition) com.mitel.ka.kcapp.services.sbm.serenadiagnostics.ExtensionMapper
									.getTypeObject(nsUri, type, reader);
						}

					}

				}

				// Note all attributes that were handled. Used to differ normal
				// attributes
				// from anyAttributes.
				java.util.Vector handledAttributes = new java.util.Vector();

				reader.next();

				java.util.ArrayList list6 = new java.util.ArrayList();

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "transition").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "transition").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						object.setTransition(null);
						reader.next();

						reader.next();

					} else {

						object.setTransition(
								com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionIdentifier.Factory
										.parse(reader));

						reader.next();
					}
				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "fromState").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "fromState").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						object.setFromState(null);
						reader.next();

						reader.next();

					} else {

						object.setFromState(
								com.mitel.ka.kcapp.services.sbm.sbmappservices72.StateIdentifier.Factory.parse(reader));

						reader.next();
					}
				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "toState").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "toState").equals(reader.getName())) {

					nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance", "nil");
					if ("true".equals(nillableValue) || "1".equals(nillableValue)) {
						object.setToState(null);
						reader.next();

						reader.next();

					} else {

						object.setToState(
								com.mitel.ka.kcapp.services.sbm.sbmappservices72.StateIdentifier.Factory.parse(reader));

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
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionType.Factory.parse(reader));

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
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "transitionAttribute")
								.equals(reader.getName())
						|| new javax.xml.namespace.QName("", "transitionAttribute").equals(reader.getName())) {

					// Process the array and step past its final element's end.

					list6.add(reader.getElementText());

					// loop until we find a start element that is not part of
					// this array
					boolean loopDone6 = false;
					while (!loopDone6) {
						// Ensure we are at the EndElement
						while (!reader.isEndElement()) {
							reader.next();
						}
						// Step out of this element
						reader.next();
						// Step to next element event.
						while (!reader.isStartElement() && !reader.isEndElement())
							reader.next();
						if (reader.isEndElement()) {
							// two continuous end elements means we are exiting
							// the xml structure
							loopDone6 = true;
						} else {
							if (new javax.xml.namespace.QName("urn:sbmappservices72", "transitionAttribute")
									.equals(reader.getName())) {
								list6.add(reader.getElementText());

							} else {
								loopDone6 = true;
							}
						}
					}
					// call the converter utility to convert and set the array

					object.setTransitionAttribute(
							(java.lang.String[]) list6.toArray(new java.lang.String[list6.size()]));

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
