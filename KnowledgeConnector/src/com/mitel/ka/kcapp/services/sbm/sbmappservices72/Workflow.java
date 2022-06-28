
/**
 * Workflow.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.2  Built on : May 02, 2016 (05:56:08 BST)
 */

package com.mitel.ka.kcapp.services.sbm.sbmappservices72;

/**
 * Workflow bean class
 */
@SuppressWarnings({ "unchecked", "unused" })

public class Workflow implements org.apache.axis2.databinding.ADBBean {
	/*
	 * This type was generated from the piece of schema that had name = Workflow
	 * Namespace URI = urn:sbmappservices72 Namespace Prefix =
	 */

	/**
	 * field for Id
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowIdentifier localId;

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
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.
	 *         WorkflowIdentifier
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowIdentifier getId() {
		return localId;
	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Id
	 */
	public void setId(com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowIdentifier param) {
		localIdTracker = true;

		this.localId = param;

	}

	/**
	 * field for State This was an Array!
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowState[] localState;

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
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowState[]
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowState[] getState() {
		return localState;
	}

	/**
	 * validate the array for State
	 */
	protected void validateState(com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowState[] param) {

	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            State
	 */
	public void setState(com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowState[] param) {

		validateState(param);

		localStateTracker = param != null;

		this.localState = param;
	}

	/**
	 * Auto generated add method for the array for convenience
	 * 
	 * @param param
	 *            com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowState
	 */
	public void addState(com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowState param) {
		if (localState == null) {
			localState = new com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowState[] {};
		}

		// update the setting tracker
		localStateTracker = true;

		java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localState);
		list.add(param);
		this.localState = (com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowState[]) list
				.toArray(new com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowState[list.size()]);

	}

	/**
	 * field for Transition This was an Array!
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowTransition[] localTransition;

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
	 *         WorkflowTransition[]
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowTransition[] getTransition() {
		return localTransition;
	}

	/**
	 * validate the array for Transition
	 */
	protected void validateTransition(com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowTransition[] param) {

	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Transition
	 */
	public void setTransition(com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowTransition[] param) {

		validateTransition(param);

		localTransitionTracker = param != null;

		this.localTransition = param;
	}

	/**
	 * Auto generated add method for the array for convenience
	 * 
	 * @param param
	 *            com.mitel.ka.kcapp.services.sbm.sbmappservices72.
	 *            WorkflowTransition
	 */
	public void addTransition(com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowTransition param) {
		if (localTransition == null) {
			localTransition = new com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowTransition[] {};
		}

		// update the setting tracker
		localTransitionTracker = true;

		java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localTransition);
		list.add(param);
		this.localTransition = (com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowTransition[]) list
				.toArray(new com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowTransition[list.size()]);

	}

	/**
	 * field for Comment This was an Array!
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowComment[] localComment;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localCommentTracker = false;

	public boolean isCommentSpecified() {
		return localCommentTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowComment[
	 *         ]
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowComment[] getComment() {
		return localComment;
	}

	/**
	 * validate the array for Comment
	 */
	protected void validateComment(com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowComment[] param) {

	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Comment
	 */
	public void setComment(com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowComment[] param) {

		validateComment(param);

		localCommentTracker = param != null;

		this.localComment = param;
	}

	/**
	 * Auto generated add method for the array for convenience
	 * 
	 * @param param
	 *            com.mitel.ka.kcapp.services.sbm.sbmappservices72.
	 *            WorkflowComment
	 */
	public void addComment(com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowComment param) {
		if (localComment == null) {
			localComment = new com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowComment[] {};
		}

		// update the setting tracker
		localCommentTracker = true;

		java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localComment);
		list.add(param);
		this.localComment = (com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowComment[]) list
				.toArray(new com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowComment[list.size()]);

	}

	/**
	 * field for Band This was an Array!
	 */

	protected com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowBand[] localBand;

	/*
	 * This tracker boolean wil be used to detect whether the user called the
	 * set method for this attribute. It will be used to determine whether to
	 * include this field in the serialized XML
	 */
	protected boolean localBandTracker = false;

	public boolean isBandSpecified() {
		return localBandTracker;
	}

	/**
	 * Auto generated getter method
	 * 
	 * @return com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowBand[]
	 */
	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowBand[] getBand() {
		return localBand;
	}

	/**
	 * validate the array for Band
	 */
	protected void validateBand(com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowBand[] param) {

	}

	/**
	 * Auto generated setter method
	 * 
	 * @param param
	 *            Band
	 */
	public void setBand(com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowBand[] param) {

		validateBand(param);

		localBandTracker = param != null;

		this.localBand = param;
	}

	/**
	 * Auto generated add method for the array for convenience
	 * 
	 * @param param
	 *            com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowBand
	 */
	public void addBand(com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowBand param) {
		if (localBand == null) {
			localBand = new com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowBand[] {};
		}

		// update the setting tracker
		localBandTracker = true;

		java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(localBand);
		list.add(param);
		this.localBand = (com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowBand[]) list
				.toArray(new com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowBand[list.size()]);

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
						namespacePrefix + ":Workflow", xmlWriter);
			} else {
				writeAttribute("xsi", "http://www.w3.org/2001/XMLSchema-instance", "type", "Workflow", xmlWriter);
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
		if (localStateTracker) {
			if (localState != null) {
				for (int i = 0; i < localState.length; i++) {
					if (localState[i] != null) {
						localState[i].serialize(new javax.xml.namespace.QName("urn:sbmappservices72", "state"),
								xmlWriter);
					} else {

						// we don't have to do any thing since minOccures is
						// zero

					}

				}
			} else {

				throw new org.apache.axis2.databinding.ADBException("state cannot be null!!");

			}
		}
		if (localTransitionTracker) {
			if (localTransition != null) {
				for (int i = 0; i < localTransition.length; i++) {
					if (localTransition[i] != null) {
						localTransition[i].serialize(
								new javax.xml.namespace.QName("urn:sbmappservices72", "transition"), xmlWriter);
					} else {

						// we don't have to do any thing since minOccures is
						// zero

					}

				}
			} else {

				throw new org.apache.axis2.databinding.ADBException("transition cannot be null!!");

			}
		}
		if (localCommentTracker) {
			if (localComment != null) {
				for (int i = 0; i < localComment.length; i++) {
					if (localComment[i] != null) {
						localComment[i].serialize(new javax.xml.namespace.QName("urn:sbmappservices72", "comment"),
								xmlWriter);
					} else {

						// we don't have to do any thing since minOccures is
						// zero

					}

				}
			} else {

				throw new org.apache.axis2.databinding.ADBException("comment cannot be null!!");

			}
		}
		if (localBandTracker) {
			if (localBand != null) {
				for (int i = 0; i < localBand.length; i++) {
					if (localBand[i] != null) {
						localBand[i].serialize(new javax.xml.namespace.QName("urn:sbmappservices72", "band"),
								xmlWriter);
					} else {

						// we don't have to do any thing since minOccures is
						// zero

					}

				}
			} else {

				throw new org.apache.axis2.databinding.ADBException("band cannot be null!!");

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
		public static Workflow parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
			Workflow object = new Workflow();

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

						if (!"Workflow".equals(type)) {
							// find namespace for the prefix
							java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
							return (Workflow) com.mitel.ka.kcapp.services.sbm.serenadiagnostics.ExtensionMapper
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

				java.util.ArrayList list3 = new java.util.ArrayList();

				java.util.ArrayList list4 = new java.util.ArrayList();

				java.util.ArrayList list5 = new java.util.ArrayList();

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

						object.setId(com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowIdentifier.Factory
								.parse(reader));

						reader.next();
					}
				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "state").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "state").equals(reader.getName())) {

					// Process the array and step past its final element's end.

					list2.add(com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowState.Factory.parse(reader));

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
							if (new javax.xml.namespace.QName("urn:sbmappservices72", "state")
									.equals(reader.getName())) {
								list2.add(com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowState.Factory
										.parse(reader));

							} else {
								loopDone2 = true;
							}
						}
					}
					// call the converter utility to convert and set the array

					object.setState(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowState[]) org.apache.axis2.databinding.utils.ConverterUtil
									.convertToArray(
											com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowState.class,
											list2));

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "transition").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "transition").equals(reader.getName())) {

					// Process the array and step past its final element's end.

					list3.add(
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowTransition.Factory.parse(reader));

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
							if (new javax.xml.namespace.QName("urn:sbmappservices72", "transition")
									.equals(reader.getName())) {
								list3.add(com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowTransition.Factory
										.parse(reader));

							} else {
								loopDone3 = true;
							}
						}
					}
					// call the converter utility to convert and set the array

					object.setTransition(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowTransition[]) org.apache.axis2.databinding.utils.ConverterUtil
									.convertToArray(
											com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowTransition.class,
											list3));

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "comment").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "comment").equals(reader.getName())) {

					// Process the array and step past its final element's end.

					list4.add(com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowComment.Factory.parse(reader));

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
							if (new javax.xml.namespace.QName("urn:sbmappservices72", "comment")
									.equals(reader.getName())) {
								list4.add(com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowComment.Factory
										.parse(reader));

							} else {
								loopDone4 = true;
							}
						}
					}
					// call the converter utility to convert and set the array

					object.setComment(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowComment[]) org.apache.axis2.databinding.utils.ConverterUtil
									.convertToArray(
											com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowComment.class,
											list4));

				} // End of if for expected property start element

				else {

				}

				while (!reader.isStartElement() && !reader.isEndElement())
					reader.next();

				if (reader.isStartElement()
						&& new javax.xml.namespace.QName("urn:sbmappservices72", "band").equals(reader.getName())
						|| new javax.xml.namespace.QName("", "band").equals(reader.getName())) {

					// Process the array and step past its final element's end.

					list5.add(com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowBand.Factory.parse(reader));

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
							if (new javax.xml.namespace.QName("urn:sbmappservices72", "band")
									.equals(reader.getName())) {
								list5.add(com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowBand.Factory
										.parse(reader));

							} else {
								loopDone5 = true;
							}
						}
					}
					// call the converter utility to convert and set the array

					object.setBand(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowBand[]) org.apache.axis2.databinding.utils.ConverterUtil
									.convertToArray(com.mitel.ka.kcapp.services.sbm.sbmappservices72.WorkflowBand.class,
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
