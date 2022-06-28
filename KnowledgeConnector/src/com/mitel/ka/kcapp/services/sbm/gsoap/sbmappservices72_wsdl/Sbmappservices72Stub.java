
/**
 * Sbmappservices72Stub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.2  Built on : May 02, 2016 (05:55:18 BST)
 */
package com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl;

/*
*  Sbmappservices72Stub java implementation
*/

public class Sbmappservices72Stub extends org.apache.axis2.client.Stub implements Sbmappservices72 {
	protected org.apache.axis2.description.AxisOperation[] _operations;

	// hashmaps to keep the fault mapping
	private java.util.HashMap faultExceptionNameMap = new java.util.HashMap();
	private java.util.HashMap faultExceptionClassNameMap = new java.util.HashMap();
	private java.util.HashMap faultMessageMap = new java.util.HashMap();

	private static int counter = 0;

	private static synchronized java.lang.String getUniqueSuffix() {
		// reset the counter if it is greater than 99999
		if (counter > 99999) {
			counter = 0;
		}
		counter = counter + 1;
		return java.lang.Long.toString(java.lang.System.currentTimeMillis()) + "_" + counter;
	}

	private void populateAxisService() throws org.apache.axis2.AxisFault {

		// creating the Service with a unique name
		_service = new org.apache.axis2.description.AxisService("Sbmappservices72" + getUniqueSuffix());
		addAnonymousOperations();

		// creating the operations
		org.apache.axis2.description.AxisOperation __operation;

		_operations = new org.apache.axis2.description.AxisOperation[34];

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(
				new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl", "getWorkflows"));
		_service.addOperation(__operation);

		_operations[0] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
				"updateFileAttachment"));
		_service.addOperation(__operation);

		_operations[1] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation
				.setName(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl", "getUsers"));
		_service.addOperation(__operation);

		_operations[2] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(
				new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl", "getItemsByQuery"));
		_service.addOperation(__operation);

		_operations[3] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(
				new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl", "getNoteLoggerInfo"));
		_service.addOperation(__operation);

		_operations[4] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(
				new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl", "deleteItems"));
		_service.addOperation(__operation);

		_operations[5] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(
				new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl", "deleteItemsByQuery"));
		_service.addOperation(__operation);

		_operations[6] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(
				new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl", "getFileAttachment"));
		_service.addOperation(__operation);

		_operations[7] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
				"getAvailableTransitions"));
		_service.addOperation(__operation);

		_operations[8] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation
				.setName(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl", "getTables"));
		_service.addOperation(__operation);

		_operations[9] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(
				new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl", "linkSubtask"));
		_service.addOperation(__operation);

		_operations[10] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
				"getStateChangeHistory"));
		_service.addOperation(__operation);

		_operations[11] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(
				new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl", "transitionItem"));
		_service.addOperation(__operation);

		_operations[12] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(
				new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl", "getSubmitProjects"));
		_service.addOperation(__operation);

		_operations[13] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(
				new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl", "getSolutions"));
		_service.addOperation(__operation);

		_operations[14] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(
				new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl", "transitionItems"));
		_service.addOperation(__operation);

		_operations[15] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(
				new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl", "getReports"));
		_service.addOperation(__operation);

		_operations[16] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation
				.setName(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl", "runReport"));
		_service.addOperation(__operation);

		_operations[17] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(
				new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl", "getFieldSelections"));
		_service.addOperation(__operation);

		_operations[18] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(
				new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl", "runReportXml"));
		_service.addOperation(__operation);

		_operations[19] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(
				new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl", "createPrimaryItems"));
		_service.addOperation(__operation);

		_operations[20] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
				"createNoteAttachment"));
		_service.addOperation(__operation);

		_operations[21] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(
				new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl", "getApplications"));
		_service.addOperation(__operation);

		_operations[22] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(
				new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl", "deleteAttachment"));
		_service.addOperation(__operation);

		_operations[23] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(
				new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl", "isUserValid"));
		_service.addOperation(__operation);

		_operations[24] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
				"createFileAttachment"));
		_service.addOperation(__operation);

		_operations[25] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(
				new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl", "createAuxItem"));
		_service.addOperation(__operation);

		_operations[26] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation
				.setName(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl", "getItems"));
		_service.addOperation(__operation);

		_operations[27] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(
				new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl", "createPrimaryItem"));
		_service.addOperation(__operation);

		_operations[28] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation
				.setName(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl", "getItem"));
		_service.addOperation(__operation);

		_operations[29] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
				"getAvailableSubmitTransitions"));
		_service.addOperation(__operation);

		_operations[30] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl", "logout"));
		_service.addOperation(__operation);

		_operations[31] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(
				new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl", "createAuxItems"));
		_service.addOperation(__operation);

		_operations[32] = __operation;

		__operation = new org.apache.axis2.description.OutInAxisOperation();

		__operation.setName(
				new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl", "getVersion"));
		_service.addOperation(__operation);

		_operations[33] = __operation;

	}

	// populates the faults
	private void populateFaults() {

		faultExceptionNameMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetWorkflows"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultExceptionClassNameMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetWorkflows"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultMessageMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetWorkflows"),
				"com.mitel.ka.kcapp.services.sbm.sbmappservices72.AEWebservicesFault");

		faultExceptionNameMap.put(new org.apache.axis2.client.FaultMapKey(
				new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "UpdateFileAttachment"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultExceptionClassNameMap.put(new org.apache.axis2.client.FaultMapKey(
				new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "UpdateFileAttachment"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultMessageMap.put(new org.apache.axis2.client.FaultMapKey(
				new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "UpdateFileAttachment"),
				"com.mitel.ka.kcapp.services.sbm.sbmappservices72.AEWebservicesFault");

		faultExceptionNameMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetUsers"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultExceptionClassNameMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetUsers"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultMessageMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetUsers"),
				"com.mitel.ka.kcapp.services.sbm.sbmappservices72.AEWebservicesFault");

		faultExceptionNameMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetItemsByQuery"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultExceptionClassNameMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetItemsByQuery"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultMessageMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetItemsByQuery"),
				"com.mitel.ka.kcapp.services.sbm.sbmappservices72.AEWebservicesFault");

		faultExceptionNameMap.put(new org.apache.axis2.client.FaultMapKey(
				new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetNoteLoggerInfo"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultExceptionClassNameMap.put(new org.apache.axis2.client.FaultMapKey(
				new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetNoteLoggerInfo"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultMessageMap.put(new org.apache.axis2.client.FaultMapKey(
				new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetNoteLoggerInfo"),
				"com.mitel.ka.kcapp.services.sbm.sbmappservices72.AEWebservicesFault");

		faultExceptionNameMap.put(new org.apache.axis2.client.FaultMapKey(
				new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "DeleteItemsByQuery"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultExceptionClassNameMap.put(new org.apache.axis2.client.FaultMapKey(
				new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "DeleteItemsByQuery"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultMessageMap.put(new org.apache.axis2.client.FaultMapKey(
				new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "DeleteItemsByQuery"),
				"com.mitel.ka.kcapp.services.sbm.sbmappservices72.AEWebservicesFault");

		faultExceptionNameMap.put(new org.apache.axis2.client.FaultMapKey(
				new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetFileAttachment"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultExceptionClassNameMap.put(new org.apache.axis2.client.FaultMapKey(
				new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetFileAttachment"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultMessageMap.put(new org.apache.axis2.client.FaultMapKey(
				new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetFileAttachment"),
				"com.mitel.ka.kcapp.services.sbm.sbmappservices72.AEWebservicesFault");

		faultExceptionNameMap.put(new org.apache.axis2.client.FaultMapKey(
				new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetAvailableTransitions"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultExceptionClassNameMap.put(new org.apache.axis2.client.FaultMapKey(
				new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetAvailableTransitions"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultMessageMap.put(new org.apache.axis2.client.FaultMapKey(
				new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetAvailableTransitions"),
				"com.mitel.ka.kcapp.services.sbm.sbmappservices72.AEWebservicesFault");

		faultExceptionNameMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetTables"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultExceptionClassNameMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetTables"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultMessageMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetTables"),
				"com.mitel.ka.kcapp.services.sbm.sbmappservices72.AEWebservicesFault");

		faultExceptionNameMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "LinkSubtask"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultExceptionClassNameMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "LinkSubtask"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultMessageMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "LinkSubtask"),
				"com.mitel.ka.kcapp.services.sbm.sbmappservices72.AEWebservicesFault");

		faultExceptionNameMap.put(new org.apache.axis2.client.FaultMapKey(
				new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetStateChangeHistory"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultExceptionClassNameMap.put(new org.apache.axis2.client.FaultMapKey(
				new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetStateChangeHistory"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultMessageMap.put(new org.apache.axis2.client.FaultMapKey(
				new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetStateChangeHistory"),
				"com.mitel.ka.kcapp.services.sbm.sbmappservices72.AEWebservicesFault");

		faultExceptionNameMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "TransitionItem"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultExceptionClassNameMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "TransitionItem"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultMessageMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "TransitionItem"),
				"com.mitel.ka.kcapp.services.sbm.sbmappservices72.AEWebservicesFault");

		faultExceptionNameMap.put(new org.apache.axis2.client.FaultMapKey(
				new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetSubmitProjects"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultExceptionClassNameMap.put(new org.apache.axis2.client.FaultMapKey(
				new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetSubmitProjects"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultMessageMap.put(new org.apache.axis2.client.FaultMapKey(
				new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetSubmitProjects"),
				"com.mitel.ka.kcapp.services.sbm.sbmappservices72.AEWebservicesFault");

		faultExceptionNameMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetSolutions"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultExceptionClassNameMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetSolutions"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultMessageMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetSolutions"),
				"com.mitel.ka.kcapp.services.sbm.sbmappservices72.AEWebservicesFault");

		faultExceptionNameMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "TransitionItems"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultExceptionClassNameMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "TransitionItems"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultMessageMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "TransitionItems"),
				"com.mitel.ka.kcapp.services.sbm.sbmappservices72.AEWebservicesFault");

		faultExceptionNameMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetReports"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultExceptionClassNameMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetReports"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultMessageMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetReports"),
				"com.mitel.ka.kcapp.services.sbm.sbmappservices72.AEWebservicesFault");

		faultExceptionNameMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "RunReport"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultExceptionClassNameMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "RunReport"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultMessageMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "RunReport"),
				"com.mitel.ka.kcapp.services.sbm.sbmappservices72.AEWebservicesFault");

		faultExceptionNameMap.put(new org.apache.axis2.client.FaultMapKey(
				new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetFieldSelections"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultExceptionClassNameMap.put(new org.apache.axis2.client.FaultMapKey(
				new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetFieldSelections"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultMessageMap.put(new org.apache.axis2.client.FaultMapKey(
				new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetFieldSelections"),
				"com.mitel.ka.kcapp.services.sbm.sbmappservices72.AEWebservicesFault");

		faultExceptionNameMap.put(new org.apache.axis2.client.FaultMapKey(
				new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "CreatePrimaryItems"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultExceptionClassNameMap.put(new org.apache.axis2.client.FaultMapKey(
				new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "CreatePrimaryItems"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultMessageMap.put(new org.apache.axis2.client.FaultMapKey(
				new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "CreatePrimaryItems"),
				"com.mitel.ka.kcapp.services.sbm.sbmappservices72.AEWebservicesFault");

		faultExceptionNameMap.put(new org.apache.axis2.client.FaultMapKey(
				new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "CreateNoteAttachment"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultExceptionClassNameMap.put(new org.apache.axis2.client.FaultMapKey(
				new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "CreateNoteAttachment"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultMessageMap.put(new org.apache.axis2.client.FaultMapKey(
				new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "CreateNoteAttachment"),
				"com.mitel.ka.kcapp.services.sbm.sbmappservices72.AEWebservicesFault");

		faultExceptionNameMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetApplications"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultExceptionClassNameMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetApplications"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultMessageMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetApplications"),
				"com.mitel.ka.kcapp.services.sbm.sbmappservices72.AEWebservicesFault");

		faultExceptionNameMap.put(new org.apache.axis2.client.FaultMapKey(
				new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "DeleteAttachment"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultExceptionClassNameMap.put(new org.apache.axis2.client.FaultMapKey(
				new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "DeleteAttachment"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultMessageMap.put(new org.apache.axis2.client.FaultMapKey(
				new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "DeleteAttachment"),
				"com.mitel.ka.kcapp.services.sbm.sbmappservices72.AEWebservicesFault");

		faultExceptionNameMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "IsUserValid"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultExceptionClassNameMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "IsUserValid"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultMessageMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "IsUserValid"),
				"com.mitel.ka.kcapp.services.sbm.sbmappservices72.AEWebservicesFault");

		faultExceptionNameMap.put(new org.apache.axis2.client.FaultMapKey(
				new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "CreateFileAttachment"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultExceptionClassNameMap.put(new org.apache.axis2.client.FaultMapKey(
				new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "CreateFileAttachment"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultMessageMap.put(new org.apache.axis2.client.FaultMapKey(
				new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "CreateFileAttachment"),
				"com.mitel.ka.kcapp.services.sbm.sbmappservices72.AEWebservicesFault");

		faultExceptionNameMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "CreateAuxItem"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultExceptionClassNameMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "CreateAuxItem"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultMessageMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "CreateAuxItem"),
				"com.mitel.ka.kcapp.services.sbm.sbmappservices72.AEWebservicesFault");

		faultExceptionNameMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetItems"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultExceptionClassNameMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetItems"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultMessageMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetItems"),
				"com.mitel.ka.kcapp.services.sbm.sbmappservices72.AEWebservicesFault");

		faultExceptionNameMap.put(new org.apache.axis2.client.FaultMapKey(
				new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "CreatePrimaryItem"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultExceptionClassNameMap.put(new org.apache.axis2.client.FaultMapKey(
				new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "CreatePrimaryItem"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultMessageMap.put(new org.apache.axis2.client.FaultMapKey(
				new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "CreatePrimaryItem"),
				"com.mitel.ka.kcapp.services.sbm.sbmappservices72.AEWebservicesFault");

		faultExceptionNameMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetItem"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultExceptionClassNameMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetItem"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultMessageMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetItem"),
				"com.mitel.ka.kcapp.services.sbm.sbmappservices72.AEWebservicesFault");

		faultExceptionNameMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"),
						"GetAvailableSubmitTransitions"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultExceptionClassNameMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"),
						"GetAvailableSubmitTransitions"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultMessageMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"),
						"GetAvailableSubmitTransitions"),
				"com.mitel.ka.kcapp.services.sbm.sbmappservices72.AEWebservicesFault");

		faultExceptionNameMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "Logout"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultExceptionClassNameMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "Logout"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultMessageMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "Logout"),
				"com.mitel.ka.kcapp.services.sbm.sbmappservices72.AEWebservicesFault");

		faultExceptionNameMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "CreateAuxItems"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultExceptionClassNameMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "CreateAuxItems"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultMessageMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "CreateAuxItems"),
				"com.mitel.ka.kcapp.services.sbm.sbmappservices72.AEWebservicesFault");

		faultExceptionNameMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetVersion"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultExceptionClassNameMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetVersion"),
				"localhost.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault");
		faultMessageMap.put(
				new org.apache.axis2.client.FaultMapKey(
						new javax.xml.namespace.QName("urn:sbmappservices72", "AEWebservicesFault"), "GetVersion"),
				"com.mitel.ka.kcapp.services.sbm.sbmappservices72.AEWebservicesFault");

	}

	/**
	 * Constructor that takes in a configContext
	 */

	public Sbmappservices72Stub(org.apache.axis2.context.ConfigurationContext configurationContext,
			java.lang.String targetEndpoint) throws org.apache.axis2.AxisFault {
		this(configurationContext, targetEndpoint, false);
	}

	/**
	 * Constructor that takes in a configContext and useseperate listner
	 */
	public Sbmappservices72Stub(org.apache.axis2.context.ConfigurationContext configurationContext,
			java.lang.String targetEndpoint, boolean useSeparateListener) throws org.apache.axis2.AxisFault {
		// To populate AxisService
		populateAxisService();
		populateFaults();

		_serviceClient = new org.apache.axis2.client.ServiceClient(configurationContext, _service);

		_serviceClient.getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(targetEndpoint));
		_serviceClient.getOptions().setUseSeparateListener(useSeparateListener);

	}

	/**
	 * Default Constructor
	 */
	public Sbmappservices72Stub(org.apache.axis2.context.ConfigurationContext configurationContext)
			throws org.apache.axis2.AxisFault {

		this(configurationContext, "http://localhost:80/gsoap/gsoap_ssl.dll?sbmappservices72");

	}

	/**
	 * Default Constructor
	 */
	public Sbmappservices72Stub() throws org.apache.axis2.AxisFault {

		this("http://localhost:80/gsoap/gsoap_ssl.dll?sbmappservices72");

	}

	/**
	 * Constructor taking the target endpoint
	 */
	public Sbmappservices72Stub(java.lang.String targetEndpoint) throws org.apache.axis2.AxisFault {
		this(null, targetEndpoint);
	}

	/**
	 * Auto generated method signature Returns project workflows graphical data.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#getWorkflows
	 * @param getWorkflows68
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetWorkflowsResponse getWorkflows(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetWorkflows getWorkflows68)

			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault {
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[0].getName());
			_operationClient.getOptions().setAction(
					"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/GetWorkflowsRequest");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

			addPropertyToOperationClient(_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), getWorkflows68,
					optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
							"getWorkflows")),
					new javax.xml.namespace.QName("urn:sbmappservices72", "GetWorkflows"));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

			java.lang.Object object = fromOM(_returnEnv.getBody().getFirstElement(),
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetWorkflowsResponse.class);

			return (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetWorkflowsResponse) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap
						.containsKey(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetWorkflows"))) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetWorkflows"));
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.reflect.Constructor constructor = exceptionClass
								.getConstructor(java.lang.String.class);
						java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetWorkflows"));
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt, messageClass);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
							throw (com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex;
						}

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			if (_messageContext.getTransportOut() != null) {
				_messageContext.getTransportOut().getSender().cleanup(_messageContext);
			}
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations Returns
	 * project workflows graphical data.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#startgetWorkflows
	 * @param getWorkflows68
	 * 
	 */
	public void startgetWorkflows(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetWorkflows getWorkflows68,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException {

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient
				.createClient(_operations[0].getName());
		_operationClient.getOptions().setAction(
				"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/GetWorkflowsRequest");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(_operationClient,
				org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), getWorkflows68, optimizeContent(
				new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl", "getWorkflows")),
				new javax.xml.namespace.QName("urn:sbmappservices72", "GetWorkflows"));

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
			public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
				try {
					org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

					java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetWorkflowsResponse.class);
					callback.receiveResultgetWorkflows(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetWorkflowsResponse) object);

				} catch (org.apache.axis2.AxisFault e) {
					callback.receiveErrorgetWorkflows(e);
				}
			}

			public void onError(java.lang.Exception error) {
				if (error instanceof org.apache.axis2.AxisFault) {
					org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
					org.apache.axiom.om.OMElement faultElt = f.getDetail();
					if (faultElt != null) {
						if (faultExceptionNameMap.containsKey(
								new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetWorkflows"))) {
							// make the fault by reflection
							try {
								java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap.get(
										new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetWorkflows"));
								java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
								java.lang.reflect.Constructor constructor = exceptionClass
										.getConstructor(java.lang.String.class);
								java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
								// message class
								java.lang.String messageClassName = (java.lang.String) faultMessageMap.get(
										new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetWorkflows"));
								java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
								java.lang.Object messageObject = fromOM(faultElt, messageClass);
								java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
										new java.lang.Class[] { messageClass });
								m.invoke(ex, new java.lang.Object[] { messageObject });

								if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
									callback.receiveErrorgetWorkflows(
											(com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex);
									return;
								}

								callback.receiveErrorgetWorkflows(new java.rmi.RemoteException(ex.getMessage(), ex));
							} catch (java.lang.ClassCastException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetWorkflows(f);
							} catch (java.lang.ClassNotFoundException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetWorkflows(f);
							} catch (java.lang.NoSuchMethodException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetWorkflows(f);
							} catch (java.lang.reflect.InvocationTargetException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetWorkflows(f);
							} catch (java.lang.IllegalAccessException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetWorkflows(f);
							} catch (java.lang.InstantiationException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetWorkflows(f);
							} catch (org.apache.axis2.AxisFault e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetWorkflows(f);
							}
						} else {
							callback.receiveErrorgetWorkflows(f);
						}
					} else {
						callback.receiveErrorgetWorkflows(f);
					}
				} else {
					callback.receiveErrorgetWorkflows(error);
				}
			}

			public void onFault(org.apache.axis2.context.MessageContext faultContext) {
				org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
						.getInboundFaultFromMessageContext(faultContext);
				onError(fault);
			}

			public void onComplete() {
				try {
					_messageContext.getTransportOut().getSender().cleanup(_messageContext);
				} catch (org.apache.axis2.AxisFault axisFault) {
					callback.receiveErrorgetWorkflows(axisFault);
				}
			}
		});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[0].getMessageReceiver() == null && _operationClient.getOptions().isUseSeparateListener()) {
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[0].setMessageReceiver(_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	/**
	 * Auto generated method signature Updates an existing attachment, given
	 * item id, and the file attachment contents.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#updateFileAttachment
	 * @param updateFileAttachment70
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.UpdateFileAttachmentResponse updateFileAttachment(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.UpdateFileAttachment updateFileAttachment70)

			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault {
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[1].getName());
			_operationClient.getOptions().setAction(
					"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/UpdateFileAttachmentRequest");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

			addPropertyToOperationClient(_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), updateFileAttachment70,
					optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
							"updateFileAttachment")),
					new javax.xml.namespace.QName("urn:sbmappservices72", "UpdateFileAttachment"));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

			java.lang.Object object = fromOM(_returnEnv.getBody().getFirstElement(),
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.UpdateFileAttachmentResponse.class);

			return (com.mitel.ka.kcapp.services.sbm.sbmappservices72.UpdateFileAttachmentResponse) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap.containsKey(
						new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "UpdateFileAttachment"))) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap.get(
								new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "UpdateFileAttachment"));
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.reflect.Constructor constructor = exceptionClass
								.getConstructor(java.lang.String.class);
						java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap.get(
								new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "UpdateFileAttachment"));
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt, messageClass);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
							throw (com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex;
						}

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			if (_messageContext.getTransportOut() != null) {
				_messageContext.getTransportOut().getSender().cleanup(_messageContext);
			}
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations Updates an
	 * existing attachment, given item id, and the file attachment contents.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#startupdateFileAttachment
	 * @param updateFileAttachment70
	 * 
	 */
	public void startupdateFileAttachment(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.UpdateFileAttachment updateFileAttachment70,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException {

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient
				.createClient(_operations[1].getName());
		_operationClient.getOptions().setAction(
				"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/UpdateFileAttachmentRequest");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(_operationClient,
				org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), updateFileAttachment70,
				optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
						"updateFileAttachment")),
				new javax.xml.namespace.QName("urn:sbmappservices72", "UpdateFileAttachment"));

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
			public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
				try {
					org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

					java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.UpdateFileAttachmentResponse.class);
					callback.receiveResultupdateFileAttachment(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.UpdateFileAttachmentResponse) object);

				} catch (org.apache.axis2.AxisFault e) {
					callback.receiveErrorupdateFileAttachment(e);
				}
			}

			public void onError(java.lang.Exception error) {
				if (error instanceof org.apache.axis2.AxisFault) {
					org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
					org.apache.axiom.om.OMElement faultElt = f.getDetail();
					if (faultElt != null) {
						if (faultExceptionNameMap.containsKey(
								new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "UpdateFileAttachment"))) {
							// make the fault by reflection
							try {
								java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
												"UpdateFileAttachment"));
								java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
								java.lang.reflect.Constructor constructor = exceptionClass
										.getConstructor(java.lang.String.class);
								java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
								// message class
								java.lang.String messageClassName = (java.lang.String) faultMessageMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
												"UpdateFileAttachment"));
								java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
								java.lang.Object messageObject = fromOM(faultElt, messageClass);
								java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
										new java.lang.Class[] { messageClass });
								m.invoke(ex, new java.lang.Object[] { messageObject });

								if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
									callback.receiveErrorupdateFileAttachment(
											(com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex);
									return;
								}

								callback.receiveErrorupdateFileAttachment(
										new java.rmi.RemoteException(ex.getMessage(), ex));
							} catch (java.lang.ClassCastException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorupdateFileAttachment(f);
							} catch (java.lang.ClassNotFoundException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorupdateFileAttachment(f);
							} catch (java.lang.NoSuchMethodException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorupdateFileAttachment(f);
							} catch (java.lang.reflect.InvocationTargetException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorupdateFileAttachment(f);
							} catch (java.lang.IllegalAccessException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorupdateFileAttachment(f);
							} catch (java.lang.InstantiationException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorupdateFileAttachment(f);
							} catch (org.apache.axis2.AxisFault e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorupdateFileAttachment(f);
							}
						} else {
							callback.receiveErrorupdateFileAttachment(f);
						}
					} else {
						callback.receiveErrorupdateFileAttachment(f);
					}
				} else {
					callback.receiveErrorupdateFileAttachment(error);
				}
			}

			public void onFault(org.apache.axis2.context.MessageContext faultContext) {
				org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
						.getInboundFaultFromMessageContext(faultContext);
				onError(fault);
			}

			public void onComplete() {
				try {
					_messageContext.getTransportOut().getSender().cleanup(_messageContext);
				} catch (org.apache.axis2.AxisFault axisFault) {
					callback.receiveErrorupdateFileAttachment(axisFault);
				}
			}
		});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[1].getMessageReceiver() == null && _operationClient.getOptions().isUseSeparateListener()) {
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[1].setMessageReceiver(_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	/**
	 * Auto generated method signature Returns user information one or more
	 * users. If getCurrentUser is true, then information about the current user
	 * is returned.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#getUsers
	 * @param getUsers72
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetUsersResponse getUsers(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetUsers getUsers72)

			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault {
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[2].getName());
			_operationClient.getOptions().setAction(
					"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/GetUsersRequest");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

			addPropertyToOperationClient(_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), getUsers72, optimizeContent(
					new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl", "getUsers")),
					new javax.xml.namespace.QName("urn:sbmappservices72", "GetUsers"));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

			java.lang.Object object = fromOM(_returnEnv.getBody().getFirstElement(),
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetUsersResponse.class);

			return (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetUsersResponse) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap
						.containsKey(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetUsers"))) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetUsers"));
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.reflect.Constructor constructor = exceptionClass
								.getConstructor(java.lang.String.class);
						java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetUsers"));
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt, messageClass);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
							throw (com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex;
						}

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			if (_messageContext.getTransportOut() != null) {
				_messageContext.getTransportOut().getSender().cleanup(_messageContext);
			}
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations Returns user
	 * information one or more users. If getCurrentUser is true, then
	 * information about the current user is returned.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#startgetUsers
	 * @param getUsers72
	 * 
	 */
	public void startgetUsers(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetUsers getUsers72,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException {

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient
				.createClient(_operations[2].getName());
		_operationClient.getOptions()
				.setAction("http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/GetUsersRequest");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(_operationClient,
				org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), getUsers72,
				optimizeContent(
						new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl", "getUsers")),
				new javax.xml.namespace.QName("urn:sbmappservices72", "GetUsers"));

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
			public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
				try {
					org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

					java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetUsersResponse.class);
					callback.receiveResultgetUsers(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetUsersResponse) object);

				} catch (org.apache.axis2.AxisFault e) {
					callback.receiveErrorgetUsers(e);
				}
			}

			public void onError(java.lang.Exception error) {
				if (error instanceof org.apache.axis2.AxisFault) {
					org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
					org.apache.axiom.om.OMElement faultElt = f.getDetail();
					if (faultElt != null) {
						if (faultExceptionNameMap.containsKey(
								new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetUsers"))) {
							// make the fault by reflection
							try {
								java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetUsers"));
								java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
								java.lang.reflect.Constructor constructor = exceptionClass
										.getConstructor(java.lang.String.class);
								java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
								// message class
								java.lang.String messageClassName = (java.lang.String) faultMessageMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetUsers"));
								java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
								java.lang.Object messageObject = fromOM(faultElt, messageClass);
								java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
										new java.lang.Class[] { messageClass });
								m.invoke(ex, new java.lang.Object[] { messageObject });

								if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
									callback.receiveErrorgetUsers(
											(com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex);
									return;
								}

								callback.receiveErrorgetUsers(new java.rmi.RemoteException(ex.getMessage(), ex));
							} catch (java.lang.ClassCastException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetUsers(f);
							} catch (java.lang.ClassNotFoundException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetUsers(f);
							} catch (java.lang.NoSuchMethodException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetUsers(f);
							} catch (java.lang.reflect.InvocationTargetException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetUsers(f);
							} catch (java.lang.IllegalAccessException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetUsers(f);
							} catch (java.lang.InstantiationException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetUsers(f);
							} catch (org.apache.axis2.AxisFault e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetUsers(f);
							}
						} else {
							callback.receiveErrorgetUsers(f);
						}
					} else {
						callback.receiveErrorgetUsers(f);
					}
				} else {
					callback.receiveErrorgetUsers(error);
				}
			}

			public void onFault(org.apache.axis2.context.MessageContext faultContext) {
				org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
						.getInboundFaultFromMessageContext(faultContext);
				onError(fault);
			}

			public void onComplete() {
				try {
					_messageContext.getTransportOut().getSender().cleanup(_messageContext);
				} catch (org.apache.axis2.AxisFault axisFault) {
					callback.receiveErrorgetUsers(axisFault);
				}
			}
		});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[2].getMessageReceiver() == null && _operationClient.getOptions().isUseSeparateListener()) {
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[2].setMessageReceiver(_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	/**
	 * Auto generated method signature Gets a list of existing items, given a
	 * table id, a query where clause, an order by clause (optional) and a
	 * maximum return list size.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#getItemsByQuery
	 * @param getItemsByQuery74
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItemsByQueryResponse getItemsByQuery(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItemsByQuery getItemsByQuery74)

			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault {
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[3].getName());
			_operationClient.getOptions().setAction(
					"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/GetItemsByQueryRequest");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

			addPropertyToOperationClient(_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), getItemsByQuery74,
					optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
							"getItemsByQuery")),
					new javax.xml.namespace.QName("urn:sbmappservices72", "GetItemsByQuery"));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

			java.lang.Object object = fromOM(_returnEnv.getBody().getFirstElement(),
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItemsByQueryResponse.class);

			return (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItemsByQueryResponse) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap
						.containsKey(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetItemsByQuery"))) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetItemsByQuery"));
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.reflect.Constructor constructor = exceptionClass
								.getConstructor(java.lang.String.class);
						java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetItemsByQuery"));
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt, messageClass);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
							throw (com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex;
						}

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			if (_messageContext.getTransportOut() != null) {
				_messageContext.getTransportOut().getSender().cleanup(_messageContext);
			}
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations Gets a list
	 * of existing items, given a table id, a query where clause, an order by
	 * clause (optional) and a maximum return list size.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#startgetItemsByQuery
	 * @param getItemsByQuery74
	 * 
	 */
	public void startgetItemsByQuery(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItemsByQuery getItemsByQuery74,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException {

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient
				.createClient(_operations[3].getName());
		_operationClient.getOptions().setAction(
				"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/GetItemsByQueryRequest");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(_operationClient,
				org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), getItemsByQuery74,
				optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
						"getItemsByQuery")),
				new javax.xml.namespace.QName("urn:sbmappservices72", "GetItemsByQuery"));

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
			public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
				try {
					org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

					java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItemsByQueryResponse.class);
					callback.receiveResultgetItemsByQuery(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItemsByQueryResponse) object);

				} catch (org.apache.axis2.AxisFault e) {
					callback.receiveErrorgetItemsByQuery(e);
				}
			}

			public void onError(java.lang.Exception error) {
				if (error instanceof org.apache.axis2.AxisFault) {
					org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
					org.apache.axiom.om.OMElement faultElt = f.getDetail();
					if (faultElt != null) {
						if (faultExceptionNameMap.containsKey(
								new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetItemsByQuery"))) {
							// make the fault by reflection
							try {
								java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
												"GetItemsByQuery"));
								java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
								java.lang.reflect.Constructor constructor = exceptionClass
										.getConstructor(java.lang.String.class);
								java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
								// message class
								java.lang.String messageClassName = (java.lang.String) faultMessageMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
												"GetItemsByQuery"));
								java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
								java.lang.Object messageObject = fromOM(faultElt, messageClass);
								java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
										new java.lang.Class[] { messageClass });
								m.invoke(ex, new java.lang.Object[] { messageObject });

								if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
									callback.receiveErrorgetItemsByQuery(
											(com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex);
									return;
								}

								callback.receiveErrorgetItemsByQuery(new java.rmi.RemoteException(ex.getMessage(), ex));
							} catch (java.lang.ClassCastException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetItemsByQuery(f);
							} catch (java.lang.ClassNotFoundException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetItemsByQuery(f);
							} catch (java.lang.NoSuchMethodException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetItemsByQuery(f);
							} catch (java.lang.reflect.InvocationTargetException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetItemsByQuery(f);
							} catch (java.lang.IllegalAccessException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetItemsByQuery(f);
							} catch (java.lang.InstantiationException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetItemsByQuery(f);
							} catch (org.apache.axis2.AxisFault e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetItemsByQuery(f);
							}
						} else {
							callback.receiveErrorgetItemsByQuery(f);
						}
					} else {
						callback.receiveErrorgetItemsByQuery(f);
					}
				} else {
					callback.receiveErrorgetItemsByQuery(error);
				}
			}

			public void onFault(org.apache.axis2.context.MessageContext faultContext) {
				org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
						.getInboundFaultFromMessageContext(faultContext);
				onError(fault);
			}

			public void onComplete() {
				try {
					_messageContext.getTransportOut().getSender().cleanup(_messageContext);
				} catch (org.apache.axis2.AxisFault axisFault) {
					callback.receiveErrorgetItemsByQuery(axisFault);
				}
			}
		});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[3].getMessageReceiver() == null && _operationClient.getOptions().isUseSeparateListener()) {
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[3].setMessageReceiver(_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	/**
	 * Auto generated method signature Returns note logger information.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#getNoteLoggerInfo
	 * @param getNoteLoggerInfo76
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetNoteLoggerInfoResponse getNoteLoggerInfo(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetNoteLoggerInfo getNoteLoggerInfo76)

			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault {
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[4].getName());
			_operationClient.getOptions().setAction(
					"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/GetNoteLoggerInfoRequest");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

			addPropertyToOperationClient(_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), getNoteLoggerInfo76,
					optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
							"getNoteLoggerInfo")),
					new javax.xml.namespace.QName("urn:sbmappservices72", "GetNoteLoggerInfo"));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

			java.lang.Object object = fromOM(_returnEnv.getBody().getFirstElement(),
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetNoteLoggerInfoResponse.class);

			return (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetNoteLoggerInfoResponse) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap.containsKey(
						new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetNoteLoggerInfo"))) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetNoteLoggerInfo"));
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.reflect.Constructor constructor = exceptionClass
								.getConstructor(java.lang.String.class);
						java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetNoteLoggerInfo"));
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt, messageClass);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
							throw (com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex;
						}

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			if (_messageContext.getTransportOut() != null) {
				_messageContext.getTransportOut().getSender().cleanup(_messageContext);
			}
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations Returns note
	 * logger information.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#startgetNoteLoggerInfo
	 * @param getNoteLoggerInfo76
	 * 
	 */
	public void startgetNoteLoggerInfo(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetNoteLoggerInfo getNoteLoggerInfo76,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException {

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient
				.createClient(_operations[4].getName());
		_operationClient.getOptions().setAction(
				"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/GetNoteLoggerInfoRequest");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(_operationClient,
				org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), getNoteLoggerInfo76,
				optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
						"getNoteLoggerInfo")),
				new javax.xml.namespace.QName("urn:sbmappservices72", "GetNoteLoggerInfo"));

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
			public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
				try {
					org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

					java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetNoteLoggerInfoResponse.class);
					callback.receiveResultgetNoteLoggerInfo(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetNoteLoggerInfoResponse) object);

				} catch (org.apache.axis2.AxisFault e) {
					callback.receiveErrorgetNoteLoggerInfo(e);
				}
			}

			public void onError(java.lang.Exception error) {
				if (error instanceof org.apache.axis2.AxisFault) {
					org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
					org.apache.axiom.om.OMElement faultElt = f.getDetail();
					if (faultElt != null) {
						if (faultExceptionNameMap.containsKey(
								new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetNoteLoggerInfo"))) {
							// make the fault by reflection
							try {
								java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
												"GetNoteLoggerInfo"));
								java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
								java.lang.reflect.Constructor constructor = exceptionClass
										.getConstructor(java.lang.String.class);
								java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
								// message class
								java.lang.String messageClassName = (java.lang.String) faultMessageMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
												"GetNoteLoggerInfo"));
								java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
								java.lang.Object messageObject = fromOM(faultElt, messageClass);
								java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
										new java.lang.Class[] { messageClass });
								m.invoke(ex, new java.lang.Object[] { messageObject });

								if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
									callback.receiveErrorgetNoteLoggerInfo(
											(com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex);
									return;
								}

								callback.receiveErrorgetNoteLoggerInfo(
										new java.rmi.RemoteException(ex.getMessage(), ex));
							} catch (java.lang.ClassCastException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetNoteLoggerInfo(f);
							} catch (java.lang.ClassNotFoundException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetNoteLoggerInfo(f);
							} catch (java.lang.NoSuchMethodException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetNoteLoggerInfo(f);
							} catch (java.lang.reflect.InvocationTargetException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetNoteLoggerInfo(f);
							} catch (java.lang.IllegalAccessException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetNoteLoggerInfo(f);
							} catch (java.lang.InstantiationException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetNoteLoggerInfo(f);
							} catch (org.apache.axis2.AxisFault e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetNoteLoggerInfo(f);
							}
						} else {
							callback.receiveErrorgetNoteLoggerInfo(f);
						}
					} else {
						callback.receiveErrorgetNoteLoggerInfo(f);
					}
				} else {
					callback.receiveErrorgetNoteLoggerInfo(error);
				}
			}

			public void onFault(org.apache.axis2.context.MessageContext faultContext) {
				org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
						.getInboundFaultFromMessageContext(faultContext);
				onError(fault);
			}

			public void onComplete() {
				try {
					_messageContext.getTransportOut().getSender().cleanup(_messageContext);
				} catch (org.apache.axis2.AxisFault axisFault) {
					callback.receiveErrorgetNoteLoggerInfo(axisFault);
				}
			}
		});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[4].getMessageReceiver() == null && _operationClient.getOptions().isUseSeparateListener()) {
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[4].setMessageReceiver(_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	/**
	 * Auto generated method signature Service definition of function
	 * ae__DeleteItems
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#deleteItems
	 * @param deleteItems78
	 * 
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteItemsResponse deleteItems(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteItems deleteItems78)

			throws java.rmi.RemoteException

	{
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[5].getName());
			_operationClient.getOptions().setAction(
					"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/DeleteItemsRequest");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

			addPropertyToOperationClient(_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), deleteItems78,
					optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
							"deleteItems")),
					new javax.xml.namespace.QName("urn:sbmappservices72", "DeleteItems"));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

			java.lang.Object object = fromOM(_returnEnv.getBody().getFirstElement(),
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteItemsResponse.class);

			return (com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteItemsResponse) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap
						.containsKey(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "DeleteItems"))) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "DeleteItems"));
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.reflect.Constructor constructor = exceptionClass
								.getConstructor(java.lang.String.class);
						java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "DeleteItems"));
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt, messageClass);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			if (_messageContext.getTransportOut() != null) {
				_messageContext.getTransportOut().getSender().cleanup(_messageContext);
			}
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations Service
	 * definition of function ae__DeleteItems
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#startdeleteItems
	 * @param deleteItems78
	 * 
	 */
	public void startdeleteItems(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteItems deleteItems78,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException {

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient
				.createClient(_operations[5].getName());
		_operationClient.getOptions().setAction(
				"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/DeleteItemsRequest");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(_operationClient,
				org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), deleteItems78, optimizeContent(
				new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl", "deleteItems")),
				new javax.xml.namespace.QName("urn:sbmappservices72", "DeleteItems"));

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
			public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
				try {
					org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

					java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteItemsResponse.class);
					callback.receiveResultdeleteItems(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteItemsResponse) object);

				} catch (org.apache.axis2.AxisFault e) {
					callback.receiveErrordeleteItems(e);
				}
			}

			public void onError(java.lang.Exception error) {
				if (error instanceof org.apache.axis2.AxisFault) {
					org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
					org.apache.axiom.om.OMElement faultElt = f.getDetail();
					if (faultElt != null) {
						if (faultExceptionNameMap.containsKey(
								new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "DeleteItems"))) {
							// make the fault by reflection
							try {
								java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap.get(
										new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "DeleteItems"));
								java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
								java.lang.reflect.Constructor constructor = exceptionClass
										.getConstructor(java.lang.String.class);
								java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
								// message class
								java.lang.String messageClassName = (java.lang.String) faultMessageMap.get(
										new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "DeleteItems"));
								java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
								java.lang.Object messageObject = fromOM(faultElt, messageClass);
								java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
										new java.lang.Class[] { messageClass });
								m.invoke(ex, new java.lang.Object[] { messageObject });

								callback.receiveErrordeleteItems(new java.rmi.RemoteException(ex.getMessage(), ex));
							} catch (java.lang.ClassCastException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrordeleteItems(f);
							} catch (java.lang.ClassNotFoundException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrordeleteItems(f);
							} catch (java.lang.NoSuchMethodException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrordeleteItems(f);
							} catch (java.lang.reflect.InvocationTargetException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrordeleteItems(f);
							} catch (java.lang.IllegalAccessException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrordeleteItems(f);
							} catch (java.lang.InstantiationException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrordeleteItems(f);
							} catch (org.apache.axis2.AxisFault e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrordeleteItems(f);
							}
						} else {
							callback.receiveErrordeleteItems(f);
						}
					} else {
						callback.receiveErrordeleteItems(f);
					}
				} else {
					callback.receiveErrordeleteItems(error);
				}
			}

			public void onFault(org.apache.axis2.context.MessageContext faultContext) {
				org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
						.getInboundFaultFromMessageContext(faultContext);
				onError(fault);
			}

			public void onComplete() {
				try {
					_messageContext.getTransportOut().getSender().cleanup(_messageContext);
				} catch (org.apache.axis2.AxisFault axisFault) {
					callback.receiveErrordeleteItems(axisFault);
				}
			}
		});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[5].getMessageReceiver() == null && _operationClient.getOptions().isUseSeparateListener()) {
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[5].setMessageReceiver(_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	/**
	 * Auto generated method signature Deletes one or more items, given a table
	 * id and a query where clause.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#deleteItemsByQuery
	 * @param deleteItemsByQuery80
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteItemsByQueryResponse deleteItemsByQuery(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteItemsByQuery deleteItemsByQuery80)

			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault {
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[6].getName());
			_operationClient.getOptions().setAction(
					"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/DeleteItemsByQueryRequest");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

			addPropertyToOperationClient(_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), deleteItemsByQuery80,
					optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
							"deleteItemsByQuery")),
					new javax.xml.namespace.QName("urn:sbmappservices72", "DeleteItemsByQuery"));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

			java.lang.Object object = fromOM(_returnEnv.getBody().getFirstElement(),
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteItemsByQueryResponse.class);

			return (com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteItemsByQueryResponse) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap.containsKey(
						new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "DeleteItemsByQuery"))) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap.get(
								new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "DeleteItemsByQuery"));
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.reflect.Constructor constructor = exceptionClass
								.getConstructor(java.lang.String.class);
						java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap.get(
								new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "DeleteItemsByQuery"));
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt, messageClass);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
							throw (com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex;
						}

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			if (_messageContext.getTransportOut() != null) {
				_messageContext.getTransportOut().getSender().cleanup(_messageContext);
			}
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations Deletes one
	 * or more items, given a table id and a query where clause.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#startdeleteItemsByQuery
	 * @param deleteItemsByQuery80
	 * 
	 */
	public void startdeleteItemsByQuery(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteItemsByQuery deleteItemsByQuery80,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException {

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient
				.createClient(_operations[6].getName());
		_operationClient.getOptions().setAction(
				"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/DeleteItemsByQueryRequest");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(_operationClient,
				org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), deleteItemsByQuery80,
				optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
						"deleteItemsByQuery")),
				new javax.xml.namespace.QName("urn:sbmappservices72", "DeleteItemsByQuery"));

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
			public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
				try {
					org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

					java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteItemsByQueryResponse.class);
					callback.receiveResultdeleteItemsByQuery(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteItemsByQueryResponse) object);

				} catch (org.apache.axis2.AxisFault e) {
					callback.receiveErrordeleteItemsByQuery(e);
				}
			}

			public void onError(java.lang.Exception error) {
				if (error instanceof org.apache.axis2.AxisFault) {
					org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
					org.apache.axiom.om.OMElement faultElt = f.getDetail();
					if (faultElt != null) {
						if (faultExceptionNameMap.containsKey(
								new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "DeleteItemsByQuery"))) {
							// make the fault by reflection
							try {
								java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
												"DeleteItemsByQuery"));
								java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
								java.lang.reflect.Constructor constructor = exceptionClass
										.getConstructor(java.lang.String.class);
								java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
								// message class
								java.lang.String messageClassName = (java.lang.String) faultMessageMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
												"DeleteItemsByQuery"));
								java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
								java.lang.Object messageObject = fromOM(faultElt, messageClass);
								java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
										new java.lang.Class[] { messageClass });
								m.invoke(ex, new java.lang.Object[] { messageObject });

								if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
									callback.receiveErrordeleteItemsByQuery(
											(com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex);
									return;
								}

								callback.receiveErrordeleteItemsByQuery(
										new java.rmi.RemoteException(ex.getMessage(), ex));
							} catch (java.lang.ClassCastException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrordeleteItemsByQuery(f);
							} catch (java.lang.ClassNotFoundException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrordeleteItemsByQuery(f);
							} catch (java.lang.NoSuchMethodException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrordeleteItemsByQuery(f);
							} catch (java.lang.reflect.InvocationTargetException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrordeleteItemsByQuery(f);
							} catch (java.lang.IllegalAccessException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrordeleteItemsByQuery(f);
							} catch (java.lang.InstantiationException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrordeleteItemsByQuery(f);
							} catch (org.apache.axis2.AxisFault e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrordeleteItemsByQuery(f);
							}
						} else {
							callback.receiveErrordeleteItemsByQuery(f);
						}
					} else {
						callback.receiveErrordeleteItemsByQuery(f);
					}
				} else {
					callback.receiveErrordeleteItemsByQuery(error);
				}
			}

			public void onFault(org.apache.axis2.context.MessageContext faultContext) {
				org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
						.getInboundFaultFromMessageContext(faultContext);
				onError(fault);
			}

			public void onComplete() {
				try {
					_messageContext.getTransportOut().getSender().cleanup(_messageContext);
				} catch (org.apache.axis2.AxisFault axisFault) {
					callback.receiveErrordeleteItemsByQuery(axisFault);
				}
			}
		});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[6].getMessageReceiver() == null && _operationClient.getOptions().isUseSeparateListener()) {
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[6].setMessageReceiver(_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	/**
	 * Auto generated method signature Gets an existing file attachment, given
	 * an item id and attachment id.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#getFileAttachment
	 * @param getFileAttachment82
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetFileAttachmentResponse getFileAttachment(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetFileAttachment getFileAttachment82)

			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault {
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[7].getName());
			_operationClient.getOptions().setAction(
					"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/GetFileAttachmentRequest");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

			addPropertyToOperationClient(_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), getFileAttachment82,
					optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
							"getFileAttachment")),
					new javax.xml.namespace.QName("urn:sbmappservices72", "GetFileAttachment"));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

			java.lang.Object object = fromOM(_returnEnv.getBody().getFirstElement(),
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetFileAttachmentResponse.class);

			return (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetFileAttachmentResponse) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap.containsKey(
						new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetFileAttachment"))) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetFileAttachment"));
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.reflect.Constructor constructor = exceptionClass
								.getConstructor(java.lang.String.class);
						java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetFileAttachment"));
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt, messageClass);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
							throw (com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex;
						}

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			if (_messageContext.getTransportOut() != null) {
				_messageContext.getTransportOut().getSender().cleanup(_messageContext);
			}
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations Gets an
	 * existing file attachment, given an item id and attachment id.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#startgetFileAttachment
	 * @param getFileAttachment82
	 * 
	 */
	public void startgetFileAttachment(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetFileAttachment getFileAttachment82,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException {

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient
				.createClient(_operations[7].getName());
		_operationClient.getOptions().setAction(
				"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/GetFileAttachmentRequest");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(_operationClient,
				org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), getFileAttachment82,
				optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
						"getFileAttachment")),
				new javax.xml.namespace.QName("urn:sbmappservices72", "GetFileAttachment"));

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
			public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
				try {
					org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

					java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetFileAttachmentResponse.class);
					callback.receiveResultgetFileAttachment(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetFileAttachmentResponse) object);

				} catch (org.apache.axis2.AxisFault e) {
					callback.receiveErrorgetFileAttachment(e);
				}
			}

			public void onError(java.lang.Exception error) {
				if (error instanceof org.apache.axis2.AxisFault) {
					org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
					org.apache.axiom.om.OMElement faultElt = f.getDetail();
					if (faultElt != null) {
						if (faultExceptionNameMap.containsKey(
								new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetFileAttachment"))) {
							// make the fault by reflection
							try {
								java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
												"GetFileAttachment"));
								java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
								java.lang.reflect.Constructor constructor = exceptionClass
										.getConstructor(java.lang.String.class);
								java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
								// message class
								java.lang.String messageClassName = (java.lang.String) faultMessageMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
												"GetFileAttachment"));
								java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
								java.lang.Object messageObject = fromOM(faultElt, messageClass);
								java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
										new java.lang.Class[] { messageClass });
								m.invoke(ex, new java.lang.Object[] { messageObject });

								if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
									callback.receiveErrorgetFileAttachment(
											(com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex);
									return;
								}

								callback.receiveErrorgetFileAttachment(
										new java.rmi.RemoteException(ex.getMessage(), ex));
							} catch (java.lang.ClassCastException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetFileAttachment(f);
							} catch (java.lang.ClassNotFoundException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetFileAttachment(f);
							} catch (java.lang.NoSuchMethodException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetFileAttachment(f);
							} catch (java.lang.reflect.InvocationTargetException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetFileAttachment(f);
							} catch (java.lang.IllegalAccessException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetFileAttachment(f);
							} catch (java.lang.InstantiationException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetFileAttachment(f);
							} catch (org.apache.axis2.AxisFault e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetFileAttachment(f);
							}
						} else {
							callback.receiveErrorgetFileAttachment(f);
						}
					} else {
						callback.receiveErrorgetFileAttachment(f);
					}
				} else {
					callback.receiveErrorgetFileAttachment(error);
				}
			}

			public void onFault(org.apache.axis2.context.MessageContext faultContext) {
				org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
						.getInboundFaultFromMessageContext(faultContext);
				onError(fault);
			}

			public void onComplete() {
				try {
					_messageContext.getTransportOut().getSender().cleanup(_messageContext);
				} catch (org.apache.axis2.AxisFault axisFault) {
					callback.receiveErrorgetFileAttachment(axisFault);
				}
			}
		});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[7].getMessageReceiver() == null && _operationClient.getOptions().isUseSeparateListener()) {
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[7].setMessageReceiver(_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	/**
	 * Auto generated method signature Return available transitions, given an
	 * item id and attribute name (may be null or empty).
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#getAvailableTransitions
	 * @param getAvailableTransitions84
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetAvailableTransitionsResponse getAvailableTransitions(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetAvailableTransitions getAvailableTransitions84)

			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault {
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[8].getName());
			_operationClient.getOptions().setAction(
					"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/GetAvailableTransitionsRequest");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

			addPropertyToOperationClient(_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), getAvailableTransitions84,
					optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
							"getAvailableTransitions")),
					new javax.xml.namespace.QName("urn:sbmappservices72", "GetAvailableTransitions"));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

			java.lang.Object object = fromOM(_returnEnv.getBody().getFirstElement(),
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetAvailableTransitionsResponse.class);

			return (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetAvailableTransitionsResponse) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap.containsKey(
						new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetAvailableTransitions"))) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
										"GetAvailableTransitions"));
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.reflect.Constructor constructor = exceptionClass
								.getConstructor(java.lang.String.class);
						java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
										"GetAvailableTransitions"));
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt, messageClass);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
							throw (com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex;
						}

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			if (_messageContext.getTransportOut() != null) {
				_messageContext.getTransportOut().getSender().cleanup(_messageContext);
			}
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations Return
	 * available transitions, given an item id and attribute name (may be null
	 * or empty).
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#startgetAvailableTransitions
	 * @param getAvailableTransitions84
	 * 
	 */
	public void startgetAvailableTransitions(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetAvailableTransitions getAvailableTransitions84,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException {

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient
				.createClient(_operations[8].getName());
		_operationClient.getOptions().setAction(
				"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/GetAvailableTransitionsRequest");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(_operationClient,
				org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), getAvailableTransitions84,
				optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
						"getAvailableTransitions")),
				new javax.xml.namespace.QName("urn:sbmappservices72", "GetAvailableTransitions"));

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
			public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
				try {
					org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

					java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetAvailableTransitionsResponse.class);
					callback.receiveResultgetAvailableTransitions(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetAvailableTransitionsResponse) object);

				} catch (org.apache.axis2.AxisFault e) {
					callback.receiveErrorgetAvailableTransitions(e);
				}
			}

			public void onError(java.lang.Exception error) {
				if (error instanceof org.apache.axis2.AxisFault) {
					org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
					org.apache.axiom.om.OMElement faultElt = f.getDetail();
					if (faultElt != null) {
						if (faultExceptionNameMap.containsKey(new org.apache.axis2.client.FaultMapKey(
								faultElt.getQName(), "GetAvailableTransitions"))) {
							// make the fault by reflection
							try {
								java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
												"GetAvailableTransitions"));
								java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
								java.lang.reflect.Constructor constructor = exceptionClass
										.getConstructor(java.lang.String.class);
								java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
								// message class
								java.lang.String messageClassName = (java.lang.String) faultMessageMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
												"GetAvailableTransitions"));
								java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
								java.lang.Object messageObject = fromOM(faultElt, messageClass);
								java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
										new java.lang.Class[] { messageClass });
								m.invoke(ex, new java.lang.Object[] { messageObject });

								if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
									callback.receiveErrorgetAvailableTransitions(
											(com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex);
									return;
								}

								callback.receiveErrorgetAvailableTransitions(
										new java.rmi.RemoteException(ex.getMessage(), ex));
							} catch (java.lang.ClassCastException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetAvailableTransitions(f);
							} catch (java.lang.ClassNotFoundException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetAvailableTransitions(f);
							} catch (java.lang.NoSuchMethodException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetAvailableTransitions(f);
							} catch (java.lang.reflect.InvocationTargetException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetAvailableTransitions(f);
							} catch (java.lang.IllegalAccessException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetAvailableTransitions(f);
							} catch (java.lang.InstantiationException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetAvailableTransitions(f);
							} catch (org.apache.axis2.AxisFault e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetAvailableTransitions(f);
							}
						} else {
							callback.receiveErrorgetAvailableTransitions(f);
						}
					} else {
						callback.receiveErrorgetAvailableTransitions(f);
					}
				} else {
					callback.receiveErrorgetAvailableTransitions(error);
				}
			}

			public void onFault(org.apache.axis2.context.MessageContext faultContext) {
				org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
						.getInboundFaultFromMessageContext(faultContext);
				onError(fault);
			}

			public void onComplete() {
				try {
					_messageContext.getTransportOut().getSender().cleanup(_messageContext);
				} catch (org.apache.axis2.AxisFault axisFault) {
					callback.receiveErrorgetAvailableTransitions(axisFault);
				}
			}
		});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[8].getMessageReceiver() == null && _operationClient.getOptions().isUseSeparateListener()) {
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[8].setMessageReceiver(_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	/**
	 * Auto generated method signature Gets the list of available tables,
	 * optionally filtered by solution and/or table type.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#getTables
	 * @param getTables86
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetTablesResponse getTables(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetTables getTables86)

			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault {
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[9].getName());
			_operationClient.getOptions().setAction(
					"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/GetTablesRequest");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

			addPropertyToOperationClient(_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), getTables86,
					optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
							"getTables")),
					new javax.xml.namespace.QName("urn:sbmappservices72", "GetTables"));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

			java.lang.Object object = fromOM(_returnEnv.getBody().getFirstElement(),
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetTablesResponse.class);

			return (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetTablesResponse) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap
						.containsKey(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetTables"))) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetTables"));
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.reflect.Constructor constructor = exceptionClass
								.getConstructor(java.lang.String.class);
						java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetTables"));
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt, messageClass);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
							throw (com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex;
						}

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			if (_messageContext.getTransportOut() != null) {
				_messageContext.getTransportOut().getSender().cleanup(_messageContext);
			}
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations Gets the
	 * list of available tables, optionally filtered by solution and/or table
	 * type.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#startgetTables
	 * @param getTables86
	 * 
	 */
	public void startgetTables(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetTables getTables86,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException {

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient
				.createClient(_operations[9].getName());
		_operationClient.getOptions()
				.setAction("http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/GetTablesRequest");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(_operationClient,
				org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), getTables86,
				optimizeContent(
						new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl", "getTables")),
				new javax.xml.namespace.QName("urn:sbmappservices72", "GetTables"));

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
			public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
				try {
					org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

					java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetTablesResponse.class);
					callback.receiveResultgetTables(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetTablesResponse) object);

				} catch (org.apache.axis2.AxisFault e) {
					callback.receiveErrorgetTables(e);
				}
			}

			public void onError(java.lang.Exception error) {
				if (error instanceof org.apache.axis2.AxisFault) {
					org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
					org.apache.axiom.om.OMElement faultElt = f.getDetail();
					if (faultElt != null) {
						if (faultExceptionNameMap.containsKey(
								new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetTables"))) {
							// make the fault by reflection
							try {
								java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetTables"));
								java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
								java.lang.reflect.Constructor constructor = exceptionClass
										.getConstructor(java.lang.String.class);
								java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
								// message class
								java.lang.String messageClassName = (java.lang.String) faultMessageMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetTables"));
								java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
								java.lang.Object messageObject = fromOM(faultElt, messageClass);
								java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
										new java.lang.Class[] { messageClass });
								m.invoke(ex, new java.lang.Object[] { messageObject });

								if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
									callback.receiveErrorgetTables(
											(com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex);
									return;
								}

								callback.receiveErrorgetTables(new java.rmi.RemoteException(ex.getMessage(), ex));
							} catch (java.lang.ClassCastException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetTables(f);
							} catch (java.lang.ClassNotFoundException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetTables(f);
							} catch (java.lang.NoSuchMethodException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetTables(f);
							} catch (java.lang.reflect.InvocationTargetException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetTables(f);
							} catch (java.lang.IllegalAccessException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetTables(f);
							} catch (java.lang.InstantiationException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetTables(f);
							} catch (org.apache.axis2.AxisFault e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetTables(f);
							}
						} else {
							callback.receiveErrorgetTables(f);
						}
					} else {
						callback.receiveErrorgetTables(f);
					}
				} else {
					callback.receiveErrorgetTables(error);
				}
			}

			public void onFault(org.apache.axis2.context.MessageContext faultContext) {
				org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
						.getInboundFaultFromMessageContext(faultContext);
				onError(fault);
			}

			public void onComplete() {
				try {
					_messageContext.getTransportOut().getSender().cleanup(_messageContext);
				} catch (org.apache.axis2.AxisFault axisFault) {
					callback.receiveErrorgetTables(axisFault);
				}
			}
		});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[9].getMessageReceiver() == null && _operationClient.getOptions().isUseSeparateListener()) {
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[9].setMessageReceiver(_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	/**
	 * Auto generated method signature Links one item to another as a sub-task.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#linkSubtask
	 * @param linkSubtask88
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.LinkSubtaskResponse linkSubtask(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.LinkSubtask linkSubtask88)

			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault {
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[10].getName());
			_operationClient.getOptions().setAction(
					"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/LinkSubtaskRequest");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

			addPropertyToOperationClient(_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), linkSubtask88,
					optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
							"linkSubtask")),
					new javax.xml.namespace.QName("urn:sbmappservices72", "LinkSubtask"));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

			java.lang.Object object = fromOM(_returnEnv.getBody().getFirstElement(),
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.LinkSubtaskResponse.class);

			return (com.mitel.ka.kcapp.services.sbm.sbmappservices72.LinkSubtaskResponse) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap
						.containsKey(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "LinkSubtask"))) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "LinkSubtask"));
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.reflect.Constructor constructor = exceptionClass
								.getConstructor(java.lang.String.class);
						java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "LinkSubtask"));
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt, messageClass);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
							throw (com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex;
						}

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			if (_messageContext.getTransportOut() != null) {
				_messageContext.getTransportOut().getSender().cleanup(_messageContext);
			}
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations Links one
	 * item to another as a sub-task.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#startlinkSubtask
	 * @param linkSubtask88
	 * 
	 */
	public void startlinkSubtask(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.LinkSubtask linkSubtask88,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException {

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient
				.createClient(_operations[10].getName());
		_operationClient.getOptions().setAction(
				"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/LinkSubtaskRequest");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(_operationClient,
				org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), linkSubtask88, optimizeContent(
				new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl", "linkSubtask")),
				new javax.xml.namespace.QName("urn:sbmappservices72", "LinkSubtask"));

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
			public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
				try {
					org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

					java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.LinkSubtaskResponse.class);
					callback.receiveResultlinkSubtask(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.LinkSubtaskResponse) object);

				} catch (org.apache.axis2.AxisFault e) {
					callback.receiveErrorlinkSubtask(e);
				}
			}

			public void onError(java.lang.Exception error) {
				if (error instanceof org.apache.axis2.AxisFault) {
					org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
					org.apache.axiom.om.OMElement faultElt = f.getDetail();
					if (faultElt != null) {
						if (faultExceptionNameMap.containsKey(
								new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "LinkSubtask"))) {
							// make the fault by reflection
							try {
								java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap.get(
										new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "LinkSubtask"));
								java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
								java.lang.reflect.Constructor constructor = exceptionClass
										.getConstructor(java.lang.String.class);
								java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
								// message class
								java.lang.String messageClassName = (java.lang.String) faultMessageMap.get(
										new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "LinkSubtask"));
								java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
								java.lang.Object messageObject = fromOM(faultElt, messageClass);
								java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
										new java.lang.Class[] { messageClass });
								m.invoke(ex, new java.lang.Object[] { messageObject });

								if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
									callback.receiveErrorlinkSubtask(
											(com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex);
									return;
								}

								callback.receiveErrorlinkSubtask(new java.rmi.RemoteException(ex.getMessage(), ex));
							} catch (java.lang.ClassCastException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorlinkSubtask(f);
							} catch (java.lang.ClassNotFoundException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorlinkSubtask(f);
							} catch (java.lang.NoSuchMethodException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorlinkSubtask(f);
							} catch (java.lang.reflect.InvocationTargetException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorlinkSubtask(f);
							} catch (java.lang.IllegalAccessException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorlinkSubtask(f);
							} catch (java.lang.InstantiationException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorlinkSubtask(f);
							} catch (org.apache.axis2.AxisFault e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorlinkSubtask(f);
							}
						} else {
							callback.receiveErrorlinkSubtask(f);
						}
					} else {
						callback.receiveErrorlinkSubtask(f);
					}
				} else {
					callback.receiveErrorlinkSubtask(error);
				}
			}

			public void onFault(org.apache.axis2.context.MessageContext faultContext) {
				org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
						.getInboundFaultFromMessageContext(faultContext);
				onError(fault);
			}

			public void onComplete() {
				try {
					_messageContext.getTransportOut().getSender().cleanup(_messageContext);
				} catch (org.apache.axis2.AxisFault axisFault) {
					callback.receiveErrorlinkSubtask(axisFault);
				}
			}
		});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[10].getMessageReceiver() == null && _operationClient.getOptions().isUseSeparateListener()) {
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[10].setMessageReceiver(_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	/**
	 * Auto generated method signature Gets the state change history of an
	 * existing item, given a table id and internal item id.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#getStateChangeHistory
	 * @param getStateChangeHistory90
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetStateChangeHistoryResponse getStateChangeHistory(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetStateChangeHistory getStateChangeHistory90)

			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault {
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[11].getName());
			_operationClient.getOptions().setAction(
					"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/GetStateChangeHistoryRequest");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

			addPropertyToOperationClient(_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), getStateChangeHistory90,
					optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
							"getStateChangeHistory")),
					new javax.xml.namespace.QName("urn:sbmappservices72", "GetStateChangeHistory"));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

			java.lang.Object object = fromOM(_returnEnv.getBody().getFirstElement(),
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetStateChangeHistoryResponse.class);

			return (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetStateChangeHistoryResponse) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap.containsKey(
						new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetStateChangeHistory"))) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap.get(
								new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetStateChangeHistory"));
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.reflect.Constructor constructor = exceptionClass
								.getConstructor(java.lang.String.class);
						java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap.get(
								new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetStateChangeHistory"));
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt, messageClass);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
							throw (com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex;
						}

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			if (_messageContext.getTransportOut() != null) {
				_messageContext.getTransportOut().getSender().cleanup(_messageContext);
			}
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations Gets the
	 * state change history of an existing item, given a table id and internal
	 * item id.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#startgetStateChangeHistory
	 * @param getStateChangeHistory90
	 * 
	 */
	public void startgetStateChangeHistory(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetStateChangeHistory getStateChangeHistory90,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException {

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient
				.createClient(_operations[11].getName());
		_operationClient.getOptions().setAction(
				"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/GetStateChangeHistoryRequest");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(_operationClient,
				org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), getStateChangeHistory90,
				optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
						"getStateChangeHistory")),
				new javax.xml.namespace.QName("urn:sbmappservices72", "GetStateChangeHistory"));

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
			public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
				try {
					org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

					java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetStateChangeHistoryResponse.class);
					callback.receiveResultgetStateChangeHistory(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetStateChangeHistoryResponse) object);

				} catch (org.apache.axis2.AxisFault e) {
					callback.receiveErrorgetStateChangeHistory(e);
				}
			}

			public void onError(java.lang.Exception error) {
				if (error instanceof org.apache.axis2.AxisFault) {
					org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
					org.apache.axiom.om.OMElement faultElt = f.getDetail();
					if (faultElt != null) {
						if (faultExceptionNameMap.containsKey(new org.apache.axis2.client.FaultMapKey(
								faultElt.getQName(), "GetStateChangeHistory"))) {
							// make the fault by reflection
							try {
								java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
												"GetStateChangeHistory"));
								java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
								java.lang.reflect.Constructor constructor = exceptionClass
										.getConstructor(java.lang.String.class);
								java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
								// message class
								java.lang.String messageClassName = (java.lang.String) faultMessageMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
												"GetStateChangeHistory"));
								java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
								java.lang.Object messageObject = fromOM(faultElt, messageClass);
								java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
										new java.lang.Class[] { messageClass });
								m.invoke(ex, new java.lang.Object[] { messageObject });

								if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
									callback.receiveErrorgetStateChangeHistory(
											(com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex);
									return;
								}

								callback.receiveErrorgetStateChangeHistory(
										new java.rmi.RemoteException(ex.getMessage(), ex));
							} catch (java.lang.ClassCastException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetStateChangeHistory(f);
							} catch (java.lang.ClassNotFoundException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetStateChangeHistory(f);
							} catch (java.lang.NoSuchMethodException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetStateChangeHistory(f);
							} catch (java.lang.reflect.InvocationTargetException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetStateChangeHistory(f);
							} catch (java.lang.IllegalAccessException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetStateChangeHistory(f);
							} catch (java.lang.InstantiationException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetStateChangeHistory(f);
							} catch (org.apache.axis2.AxisFault e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetStateChangeHistory(f);
							}
						} else {
							callback.receiveErrorgetStateChangeHistory(f);
						}
					} else {
						callback.receiveErrorgetStateChangeHistory(f);
					}
				} else {
					callback.receiveErrorgetStateChangeHistory(error);
				}
			}

			public void onFault(org.apache.axis2.context.MessageContext faultContext) {
				org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
						.getInboundFaultFromMessageContext(faultContext);
				onError(fault);
			}

			public void onComplete() {
				try {
					_messageContext.getTransportOut().getSender().cleanup(_messageContext);
				} catch (org.apache.axis2.AxisFault axisFault) {
					callback.receiveErrorgetStateChangeHistory(axisFault);
				}
			}
		});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[11].getMessageReceiver() == null && _operationClient.getOptions().isUseSeparateListener()) {
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[11].setMessageReceiver(_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	/**
	 * Auto generated method signature Transitions existing item, given the the
	 * item id, plus any data to update, and transition id to use a non-default
	 * transition.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#transitionItem
	 * @param transitionItem92
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionItemResponse transitionItem(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionItem transitionItem92)

			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault {
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[12].getName());
			_operationClient.getOptions().setAction(
					"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/TransitionItemRequest");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

			addPropertyToOperationClient(_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), transitionItem92,
					optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
							"transitionItem")),
					new javax.xml.namespace.QName("urn:sbmappservices72", "TransitionItem"));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

			java.lang.Object object = fromOM(_returnEnv.getBody().getFirstElement(),
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionItemResponse.class);

			return (com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionItemResponse) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap
						.containsKey(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "TransitionItem"))) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "TransitionItem"));
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.reflect.Constructor constructor = exceptionClass
								.getConstructor(java.lang.String.class);
						java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "TransitionItem"));
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt, messageClass);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
							throw (com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex;
						}

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			if (_messageContext.getTransportOut() != null) {
				_messageContext.getTransportOut().getSender().cleanup(_messageContext);
			}
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations Transitions
	 * existing item, given the the item id, plus any data to update, and
	 * transition id to use a non-default transition.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#starttransitionItem
	 * @param transitionItem92
	 * 
	 */
	public void starttransitionItem(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionItem transitionItem92,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException {

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient
				.createClient(_operations[12].getName());
		_operationClient.getOptions().setAction(
				"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/TransitionItemRequest");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(_operationClient,
				org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), transitionItem92,
				optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
						"transitionItem")),
				new javax.xml.namespace.QName("urn:sbmappservices72", "TransitionItem"));

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
			public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
				try {
					org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

					java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionItemResponse.class);
					callback.receiveResulttransitionItem(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionItemResponse) object);

				} catch (org.apache.axis2.AxisFault e) {
					callback.receiveErrortransitionItem(e);
				}
			}

			public void onError(java.lang.Exception error) {
				if (error instanceof org.apache.axis2.AxisFault) {
					org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
					org.apache.axiom.om.OMElement faultElt = f.getDetail();
					if (faultElt != null) {
						if (faultExceptionNameMap.containsKey(
								new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "TransitionItem"))) {
							// make the fault by reflection
							try {
								java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap.get(
										new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "TransitionItem"));
								java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
								java.lang.reflect.Constructor constructor = exceptionClass
										.getConstructor(java.lang.String.class);
								java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
								// message class
								java.lang.String messageClassName = (java.lang.String) faultMessageMap.get(
										new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "TransitionItem"));
								java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
								java.lang.Object messageObject = fromOM(faultElt, messageClass);
								java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
										new java.lang.Class[] { messageClass });
								m.invoke(ex, new java.lang.Object[] { messageObject });

								if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
									callback.receiveErrortransitionItem(
											(com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex);
									return;
								}

								callback.receiveErrortransitionItem(new java.rmi.RemoteException(ex.getMessage(), ex));
							} catch (java.lang.ClassCastException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrortransitionItem(f);
							} catch (java.lang.ClassNotFoundException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrortransitionItem(f);
							} catch (java.lang.NoSuchMethodException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrortransitionItem(f);
							} catch (java.lang.reflect.InvocationTargetException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrortransitionItem(f);
							} catch (java.lang.IllegalAccessException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrortransitionItem(f);
							} catch (java.lang.InstantiationException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrortransitionItem(f);
							} catch (org.apache.axis2.AxisFault e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrortransitionItem(f);
							}
						} else {
							callback.receiveErrortransitionItem(f);
						}
					} else {
						callback.receiveErrortransitionItem(f);
					}
				} else {
					callback.receiveErrortransitionItem(error);
				}
			}

			public void onFault(org.apache.axis2.context.MessageContext faultContext) {
				org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
						.getInboundFaultFromMessageContext(faultContext);
				onError(fault);
			}

			public void onComplete() {
				try {
					_messageContext.getTransportOut().getSender().cleanup(_messageContext);
				} catch (org.apache.axis2.AxisFault axisFault) {
					callback.receiveErrortransitionItem(axisFault);
				}
			}
		});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[12].getMessageReceiver() == null && _operationClient.getOptions().isUseSeparateListener()) {
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[12].setMessageReceiver(_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	/**
	 * Auto generated method signature Gets the list of available projects
	 * available for submitting new items, optionally filtered by table id.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#getSubmitProjects
	 * @param getSubmitProjects94
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetSubmitProjectsResponse getSubmitProjects(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetSubmitProjects getSubmitProjects94)

			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault {
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[13].getName());
			_operationClient.getOptions().setAction(
					"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/GetSubmitProjectsRequest");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

			addPropertyToOperationClient(_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), getSubmitProjects94,
					optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
							"getSubmitProjects")),
					new javax.xml.namespace.QName("urn:sbmappservices72", "GetSubmitProjects"));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

			java.lang.Object object = fromOM(_returnEnv.getBody().getFirstElement(),
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetSubmitProjectsResponse.class);

			return (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetSubmitProjectsResponse) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap.containsKey(
						new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetSubmitProjects"))) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetSubmitProjects"));
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.reflect.Constructor constructor = exceptionClass
								.getConstructor(java.lang.String.class);
						java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetSubmitProjects"));
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt, messageClass);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
							throw (com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex;
						}

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			if (_messageContext.getTransportOut() != null) {
				_messageContext.getTransportOut().getSender().cleanup(_messageContext);
			}
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations Gets the
	 * list of available projects available for submitting new items, optionally
	 * filtered by table id.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#startgetSubmitProjects
	 * @param getSubmitProjects94
	 * 
	 */
	public void startgetSubmitProjects(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetSubmitProjects getSubmitProjects94,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException {

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient
				.createClient(_operations[13].getName());
		_operationClient.getOptions().setAction(
				"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/GetSubmitProjectsRequest");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(_operationClient,
				org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), getSubmitProjects94,
				optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
						"getSubmitProjects")),
				new javax.xml.namespace.QName("urn:sbmappservices72", "GetSubmitProjects"));

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
			public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
				try {
					org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

					java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetSubmitProjectsResponse.class);
					callback.receiveResultgetSubmitProjects(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetSubmitProjectsResponse) object);

				} catch (org.apache.axis2.AxisFault e) {
					callback.receiveErrorgetSubmitProjects(e);
				}
			}

			public void onError(java.lang.Exception error) {
				if (error instanceof org.apache.axis2.AxisFault) {
					org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
					org.apache.axiom.om.OMElement faultElt = f.getDetail();
					if (faultElt != null) {
						if (faultExceptionNameMap.containsKey(
								new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetSubmitProjects"))) {
							// make the fault by reflection
							try {
								java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
												"GetSubmitProjects"));
								java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
								java.lang.reflect.Constructor constructor = exceptionClass
										.getConstructor(java.lang.String.class);
								java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
								// message class
								java.lang.String messageClassName = (java.lang.String) faultMessageMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
												"GetSubmitProjects"));
								java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
								java.lang.Object messageObject = fromOM(faultElt, messageClass);
								java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
										new java.lang.Class[] { messageClass });
								m.invoke(ex, new java.lang.Object[] { messageObject });

								if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
									callback.receiveErrorgetSubmitProjects(
											(com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex);
									return;
								}

								callback.receiveErrorgetSubmitProjects(
										new java.rmi.RemoteException(ex.getMessage(), ex));
							} catch (java.lang.ClassCastException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetSubmitProjects(f);
							} catch (java.lang.ClassNotFoundException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetSubmitProjects(f);
							} catch (java.lang.NoSuchMethodException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetSubmitProjects(f);
							} catch (java.lang.reflect.InvocationTargetException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetSubmitProjects(f);
							} catch (java.lang.IllegalAccessException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetSubmitProjects(f);
							} catch (java.lang.InstantiationException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetSubmitProjects(f);
							} catch (org.apache.axis2.AxisFault e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetSubmitProjects(f);
							}
						} else {
							callback.receiveErrorgetSubmitProjects(f);
						}
					} else {
						callback.receiveErrorgetSubmitProjects(f);
					}
				} else {
					callback.receiveErrorgetSubmitProjects(error);
				}
			}

			public void onFault(org.apache.axis2.context.MessageContext faultContext) {
				org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
						.getInboundFaultFromMessageContext(faultContext);
				onError(fault);
			}

			public void onComplete() {
				try {
					_messageContext.getTransportOut().getSender().cleanup(_messageContext);
				} catch (org.apache.axis2.AxisFault axisFault) {
					callback.receiveErrorgetSubmitProjects(axisFault);
				}
			}
		});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[13].getMessageReceiver() == null && _operationClient.getOptions().isUseSeparateListener()) {
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[13].setMessageReceiver(_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	/**
	 * Auto generated method signature Gets the list of available solutions.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#getSolutions
	 * @param getSolutions96
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetSolutionsResponse getSolutions(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetSolutions getSolutions96)

			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault {
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[14].getName());
			_operationClient.getOptions().setAction(
					"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/GetSolutionsRequest");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

			addPropertyToOperationClient(_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), getSolutions96,
					optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
							"getSolutions")),
					new javax.xml.namespace.QName("urn:sbmappservices72", "GetSolutions"));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

			java.lang.Object object = fromOM(_returnEnv.getBody().getFirstElement(),
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetSolutionsResponse.class);

			return (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetSolutionsResponse) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap
						.containsKey(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetSolutions"))) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetSolutions"));
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.reflect.Constructor constructor = exceptionClass
								.getConstructor(java.lang.String.class);
						java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetSolutions"));
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt, messageClass);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
							throw (com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex;
						}

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			if (_messageContext.getTransportOut() != null) {
				_messageContext.getTransportOut().getSender().cleanup(_messageContext);
			}
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations Gets the
	 * list of available solutions.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#startgetSolutions
	 * @param getSolutions96
	 * 
	 */
	public void startgetSolutions(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetSolutions getSolutions96,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException {

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient
				.createClient(_operations[14].getName());
		_operationClient.getOptions().setAction(
				"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/GetSolutionsRequest");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(_operationClient,
				org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), getSolutions96, optimizeContent(
				new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl", "getSolutions")),
				new javax.xml.namespace.QName("urn:sbmappservices72", "GetSolutions"));

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
			public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
				try {
					org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

					java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetSolutionsResponse.class);
					callback.receiveResultgetSolutions(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetSolutionsResponse) object);

				} catch (org.apache.axis2.AxisFault e) {
					callback.receiveErrorgetSolutions(e);
				}
			}

			public void onError(java.lang.Exception error) {
				if (error instanceof org.apache.axis2.AxisFault) {
					org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
					org.apache.axiom.om.OMElement faultElt = f.getDetail();
					if (faultElt != null) {
						if (faultExceptionNameMap.containsKey(
								new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetSolutions"))) {
							// make the fault by reflection
							try {
								java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap.get(
										new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetSolutions"));
								java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
								java.lang.reflect.Constructor constructor = exceptionClass
										.getConstructor(java.lang.String.class);
								java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
								// message class
								java.lang.String messageClassName = (java.lang.String) faultMessageMap.get(
										new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetSolutions"));
								java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
								java.lang.Object messageObject = fromOM(faultElt, messageClass);
								java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
										new java.lang.Class[] { messageClass });
								m.invoke(ex, new java.lang.Object[] { messageObject });

								if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
									callback.receiveErrorgetSolutions(
											(com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex);
									return;
								}

								callback.receiveErrorgetSolutions(new java.rmi.RemoteException(ex.getMessage(), ex));
							} catch (java.lang.ClassCastException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetSolutions(f);
							} catch (java.lang.ClassNotFoundException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetSolutions(f);
							} catch (java.lang.NoSuchMethodException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetSolutions(f);
							} catch (java.lang.reflect.InvocationTargetException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetSolutions(f);
							} catch (java.lang.IllegalAccessException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetSolutions(f);
							} catch (java.lang.InstantiationException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetSolutions(f);
							} catch (org.apache.axis2.AxisFault e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetSolutions(f);
							}
						} else {
							callback.receiveErrorgetSolutions(f);
						}
					} else {
						callback.receiveErrorgetSolutions(f);
					}
				} else {
					callback.receiveErrorgetSolutions(error);
				}
			}

			public void onFault(org.apache.axis2.context.MessageContext faultContext) {
				org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
						.getInboundFaultFromMessageContext(faultContext);
				onError(fault);
			}

			public void onComplete() {
				try {
					_messageContext.getTransportOut().getSender().cleanup(_messageContext);
				} catch (org.apache.axis2.AxisFault axisFault) {
					callback.receiveErrorgetSolutions(axisFault);
				}
			}
		});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[14].getMessageReceiver() == null && _operationClient.getOptions().isUseSeparateListener()) {
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[14].setMessageReceiver(_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	/**
	 * Auto generated method signature Transitions existing items, given the
	 * item ids, plus any data to update, and transition id to use a non-default
	 * transition.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#transitionItems
	 * @param transitionItems98
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionItemsResponse transitionItems(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionItems transitionItems98)

			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault {
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[15].getName());
			_operationClient.getOptions().setAction(
					"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/TransitionItemsRequest");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

			addPropertyToOperationClient(_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), transitionItems98,
					optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
							"transitionItems")),
					new javax.xml.namespace.QName("urn:sbmappservices72", "TransitionItems"));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

			java.lang.Object object = fromOM(_returnEnv.getBody().getFirstElement(),
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionItemsResponse.class);

			return (com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionItemsResponse) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap
						.containsKey(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "TransitionItems"))) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "TransitionItems"));
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.reflect.Constructor constructor = exceptionClass
								.getConstructor(java.lang.String.class);
						java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "TransitionItems"));
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt, messageClass);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
							throw (com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex;
						}

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			if (_messageContext.getTransportOut() != null) {
				_messageContext.getTransportOut().getSender().cleanup(_messageContext);
			}
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations Transitions
	 * existing items, given the item ids, plus any data to update, and
	 * transition id to use a non-default transition.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#starttransitionItems
	 * @param transitionItems98
	 * 
	 */
	public void starttransitionItems(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionItems transitionItems98,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException {

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient
				.createClient(_operations[15].getName());
		_operationClient.getOptions().setAction(
				"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/TransitionItemsRequest");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(_operationClient,
				org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), transitionItems98,
				optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
						"transitionItems")),
				new javax.xml.namespace.QName("urn:sbmappservices72", "TransitionItems"));

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
			public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
				try {
					org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

					java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionItemsResponse.class);
					callback.receiveResulttransitionItems(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionItemsResponse) object);

				} catch (org.apache.axis2.AxisFault e) {
					callback.receiveErrortransitionItems(e);
				}
			}

			public void onError(java.lang.Exception error) {
				if (error instanceof org.apache.axis2.AxisFault) {
					org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
					org.apache.axiom.om.OMElement faultElt = f.getDetail();
					if (faultElt != null) {
						if (faultExceptionNameMap.containsKey(
								new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "TransitionItems"))) {
							// make the fault by reflection
							try {
								java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
												"TransitionItems"));
								java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
								java.lang.reflect.Constructor constructor = exceptionClass
										.getConstructor(java.lang.String.class);
								java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
								// message class
								java.lang.String messageClassName = (java.lang.String) faultMessageMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
												"TransitionItems"));
								java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
								java.lang.Object messageObject = fromOM(faultElt, messageClass);
								java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
										new java.lang.Class[] { messageClass });
								m.invoke(ex, new java.lang.Object[] { messageObject });

								if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
									callback.receiveErrortransitionItems(
											(com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex);
									return;
								}

								callback.receiveErrortransitionItems(new java.rmi.RemoteException(ex.getMessage(), ex));
							} catch (java.lang.ClassCastException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrortransitionItems(f);
							} catch (java.lang.ClassNotFoundException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrortransitionItems(f);
							} catch (java.lang.NoSuchMethodException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrortransitionItems(f);
							} catch (java.lang.reflect.InvocationTargetException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrortransitionItems(f);
							} catch (java.lang.IllegalAccessException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrortransitionItems(f);
							} catch (java.lang.InstantiationException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrortransitionItems(f);
							} catch (org.apache.axis2.AxisFault e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrortransitionItems(f);
							}
						} else {
							callback.receiveErrortransitionItems(f);
						}
					} else {
						callback.receiveErrortransitionItems(f);
					}
				} else {
					callback.receiveErrortransitionItems(error);
				}
			}

			public void onFault(org.apache.axis2.context.MessageContext faultContext) {
				org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
						.getInboundFaultFromMessageContext(faultContext);
				onError(fault);
			}

			public void onComplete() {
				try {
					_messageContext.getTransportOut().getSender().cleanup(_messageContext);
				} catch (org.apache.axis2.AxisFault axisFault) {
					callback.receiveErrortransitionItems(axisFault);
				}
			}
		});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[15].getMessageReceiver() == null && _operationClient.getOptions().isUseSeparateListener()) {
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[15].setMessageReceiver(_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	/**
	 * Auto generated method signature Gets a filtered list of reports.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#getReports
	 * @param getReports100
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetReportsResponse getReports(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetReports getReports100)

			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault {
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[16].getName());
			_operationClient.getOptions().setAction(
					"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/GetReportsRequest");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

			addPropertyToOperationClient(_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), getReports100,
					optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
							"getReports")),
					new javax.xml.namespace.QName("urn:sbmappservices72", "GetReports"));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

			java.lang.Object object = fromOM(_returnEnv.getBody().getFirstElement(),
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetReportsResponse.class);

			return (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetReportsResponse) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap
						.containsKey(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetReports"))) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetReports"));
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.reflect.Constructor constructor = exceptionClass
								.getConstructor(java.lang.String.class);
						java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetReports"));
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt, messageClass);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
							throw (com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex;
						}

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			if (_messageContext.getTransportOut() != null) {
				_messageContext.getTransportOut().getSender().cleanup(_messageContext);
			}
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations Gets a
	 * filtered list of reports.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#startgetReports
	 * @param getReports100
	 * 
	 */
	public void startgetReports(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetReports getReports100,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException {

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient
				.createClient(_operations[16].getName());
		_operationClient.getOptions().setAction(
				"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/GetReportsRequest");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(_operationClient,
				org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), getReports100,
				optimizeContent(
						new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl", "getReports")),
				new javax.xml.namespace.QName("urn:sbmappservices72", "GetReports"));

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
			public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
				try {
					org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

					java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetReportsResponse.class);
					callback.receiveResultgetReports(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetReportsResponse) object);

				} catch (org.apache.axis2.AxisFault e) {
					callback.receiveErrorgetReports(e);
				}
			}

			public void onError(java.lang.Exception error) {
				if (error instanceof org.apache.axis2.AxisFault) {
					org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
					org.apache.axiom.om.OMElement faultElt = f.getDetail();
					if (faultElt != null) {
						if (faultExceptionNameMap.containsKey(
								new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetReports"))) {
							// make the fault by reflection
							try {
								java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap.get(
										new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetReports"));
								java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
								java.lang.reflect.Constructor constructor = exceptionClass
										.getConstructor(java.lang.String.class);
								java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
								// message class
								java.lang.String messageClassName = (java.lang.String) faultMessageMap.get(
										new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetReports"));
								java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
								java.lang.Object messageObject = fromOM(faultElt, messageClass);
								java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
										new java.lang.Class[] { messageClass });
								m.invoke(ex, new java.lang.Object[] { messageObject });

								if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
									callback.receiveErrorgetReports(
											(com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex);
									return;
								}

								callback.receiveErrorgetReports(new java.rmi.RemoteException(ex.getMessage(), ex));
							} catch (java.lang.ClassCastException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetReports(f);
							} catch (java.lang.ClassNotFoundException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetReports(f);
							} catch (java.lang.NoSuchMethodException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetReports(f);
							} catch (java.lang.reflect.InvocationTargetException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetReports(f);
							} catch (java.lang.IllegalAccessException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetReports(f);
							} catch (java.lang.InstantiationException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetReports(f);
							} catch (org.apache.axis2.AxisFault e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetReports(f);
							}
						} else {
							callback.receiveErrorgetReports(f);
						}
					} else {
						callback.receiveErrorgetReports(f);
					}
				} else {
					callback.receiveErrorgetReports(error);
				}
			}

			public void onFault(org.apache.axis2.context.MessageContext faultContext) {
				org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
						.getInboundFaultFromMessageContext(faultContext);
				onError(fault);
			}

			public void onComplete() {
				try {
					_messageContext.getTransportOut().getSender().cleanup(_messageContext);
				} catch (org.apache.axis2.AxisFault axisFault) {
					callback.receiveErrorgetReports(axisFault);
				}
			}
		});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[16].getMessageReceiver() == null && _operationClient.getOptions().isUseSeparateListener()) {
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[16].setMessageReceiver(_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	/**
	 * Auto generated method signature Runs a specified report.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#runReport
	 * @param runReport102
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.RunReportResponse runReport(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.RunReport runReport102)

			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault {
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[17].getName());
			_operationClient.getOptions().setAction(
					"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/RunReportRequest");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

			addPropertyToOperationClient(_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), runReport102,
					optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
							"runReport")),
					new javax.xml.namespace.QName("urn:sbmappservices72", "RunReport"));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

			java.lang.Object object = fromOM(_returnEnv.getBody().getFirstElement(),
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.RunReportResponse.class);

			return (com.mitel.ka.kcapp.services.sbm.sbmappservices72.RunReportResponse) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap
						.containsKey(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "RunReport"))) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "RunReport"));
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.reflect.Constructor constructor = exceptionClass
								.getConstructor(java.lang.String.class);
						java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "RunReport"));
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt, messageClass);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
							throw (com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex;
						}

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			if (_messageContext.getTransportOut() != null) {
				_messageContext.getTransportOut().getSender().cleanup(_messageContext);
			}
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations Runs a
	 * specified report.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#startrunReport
	 * @param runReport102
	 * 
	 */
	public void startrunReport(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.RunReport runReport102,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException {

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient
				.createClient(_operations[17].getName());
		_operationClient.getOptions()
				.setAction("http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/RunReportRequest");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(_operationClient,
				org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), runReport102,
				optimizeContent(
						new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl", "runReport")),
				new javax.xml.namespace.QName("urn:sbmappservices72", "RunReport"));

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
			public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
				try {
					org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

					java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.RunReportResponse.class);
					callback.receiveResultrunReport(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.RunReportResponse) object);

				} catch (org.apache.axis2.AxisFault e) {
					callback.receiveErrorrunReport(e);
				}
			}

			public void onError(java.lang.Exception error) {
				if (error instanceof org.apache.axis2.AxisFault) {
					org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
					org.apache.axiom.om.OMElement faultElt = f.getDetail();
					if (faultElt != null) {
						if (faultExceptionNameMap.containsKey(
								new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "RunReport"))) {
							// make the fault by reflection
							try {
								java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "RunReport"));
								java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
								java.lang.reflect.Constructor constructor = exceptionClass
										.getConstructor(java.lang.String.class);
								java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
								// message class
								java.lang.String messageClassName = (java.lang.String) faultMessageMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "RunReport"));
								java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
								java.lang.Object messageObject = fromOM(faultElt, messageClass);
								java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
										new java.lang.Class[] { messageClass });
								m.invoke(ex, new java.lang.Object[] { messageObject });

								if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
									callback.receiveErrorrunReport(
											(com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex);
									return;
								}

								callback.receiveErrorrunReport(new java.rmi.RemoteException(ex.getMessage(), ex));
							} catch (java.lang.ClassCastException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorrunReport(f);
							} catch (java.lang.ClassNotFoundException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorrunReport(f);
							} catch (java.lang.NoSuchMethodException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorrunReport(f);
							} catch (java.lang.reflect.InvocationTargetException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorrunReport(f);
							} catch (java.lang.IllegalAccessException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorrunReport(f);
							} catch (java.lang.InstantiationException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorrunReport(f);
							} catch (org.apache.axis2.AxisFault e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorrunReport(f);
							}
						} else {
							callback.receiveErrorrunReport(f);
						}
					} else {
						callback.receiveErrorrunReport(f);
					}
				} else {
					callback.receiveErrorrunReport(error);
				}
			}

			public void onFault(org.apache.axis2.context.MessageContext faultContext) {
				org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
						.getInboundFaultFromMessageContext(faultContext);
				onError(fault);
			}

			public void onComplete() {
				try {
					_messageContext.getTransportOut().getSender().cleanup(_messageContext);
				} catch (org.apache.axis2.AxisFault axisFault) {
					callback.receiveErrorrunReport(axisFault);
				}
			}
		});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[17].getMessageReceiver() == null && _operationClient.getOptions().isUseSeparateListener()) {
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[17].setMessageReceiver(_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	/**
	 * Auto generated method signature Returns full list of possible selections
	 * for single- or multi-selection field.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#getFieldSelections
	 * @param getFieldSelections104
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetFieldSelectionsResponse getFieldSelections(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetFieldSelections getFieldSelections104)

			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault {
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[18].getName());
			_operationClient.getOptions().setAction(
					"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/GetFieldSelectionsRequest");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

			addPropertyToOperationClient(_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), getFieldSelections104,
					optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
							"getFieldSelections")),
					new javax.xml.namespace.QName("urn:sbmappservices72", "GetFieldSelections"));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

			java.lang.Object object = fromOM(_returnEnv.getBody().getFirstElement(),
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetFieldSelectionsResponse.class);

			return (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetFieldSelectionsResponse) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap.containsKey(
						new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetFieldSelections"))) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap.get(
								new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetFieldSelections"));
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.reflect.Constructor constructor = exceptionClass
								.getConstructor(java.lang.String.class);
						java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap.get(
								new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetFieldSelections"));
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt, messageClass);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
							throw (com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex;
						}

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			if (_messageContext.getTransportOut() != null) {
				_messageContext.getTransportOut().getSender().cleanup(_messageContext);
			}
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations Returns full
	 * list of possible selections for single- or multi-selection field.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#startgetFieldSelections
	 * @param getFieldSelections104
	 * 
	 */
	public void startgetFieldSelections(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetFieldSelections getFieldSelections104,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException {

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient
				.createClient(_operations[18].getName());
		_operationClient.getOptions().setAction(
				"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/GetFieldSelectionsRequest");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(_operationClient,
				org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), getFieldSelections104,
				optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
						"getFieldSelections")),
				new javax.xml.namespace.QName("urn:sbmappservices72", "GetFieldSelections"));

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
			public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
				try {
					org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

					java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetFieldSelectionsResponse.class);
					callback.receiveResultgetFieldSelections(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetFieldSelectionsResponse) object);

				} catch (org.apache.axis2.AxisFault e) {
					callback.receiveErrorgetFieldSelections(e);
				}
			}

			public void onError(java.lang.Exception error) {
				if (error instanceof org.apache.axis2.AxisFault) {
					org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
					org.apache.axiom.om.OMElement faultElt = f.getDetail();
					if (faultElt != null) {
						if (faultExceptionNameMap.containsKey(
								new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetFieldSelections"))) {
							// make the fault by reflection
							try {
								java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
												"GetFieldSelections"));
								java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
								java.lang.reflect.Constructor constructor = exceptionClass
										.getConstructor(java.lang.String.class);
								java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
								// message class
								java.lang.String messageClassName = (java.lang.String) faultMessageMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
												"GetFieldSelections"));
								java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
								java.lang.Object messageObject = fromOM(faultElt, messageClass);
								java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
										new java.lang.Class[] { messageClass });
								m.invoke(ex, new java.lang.Object[] { messageObject });

								if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
									callback.receiveErrorgetFieldSelections(
											(com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex);
									return;
								}

								callback.receiveErrorgetFieldSelections(
										new java.rmi.RemoteException(ex.getMessage(), ex));
							} catch (java.lang.ClassCastException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetFieldSelections(f);
							} catch (java.lang.ClassNotFoundException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetFieldSelections(f);
							} catch (java.lang.NoSuchMethodException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetFieldSelections(f);
							} catch (java.lang.reflect.InvocationTargetException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetFieldSelections(f);
							} catch (java.lang.IllegalAccessException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetFieldSelections(f);
							} catch (java.lang.InstantiationException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetFieldSelections(f);
							} catch (org.apache.axis2.AxisFault e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetFieldSelections(f);
							}
						} else {
							callback.receiveErrorgetFieldSelections(f);
						}
					} else {
						callback.receiveErrorgetFieldSelections(f);
					}
				} else {
					callback.receiveErrorgetFieldSelections(error);
				}
			}

			public void onFault(org.apache.axis2.context.MessageContext faultContext) {
				org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
						.getInboundFaultFromMessageContext(faultContext);
				onError(fault);
			}

			public void onComplete() {
				try {
					_messageContext.getTransportOut().getSender().cleanup(_messageContext);
				} catch (org.apache.axis2.AxisFault axisFault) {
					callback.receiveErrorgetFieldSelections(axisFault);
				}
			}
		});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[18].getMessageReceiver() == null && _operationClient.getOptions().isUseSeparateListener()) {
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[18].setMessageReceiver(_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	/**
	 * Auto generated method signature Service definition of function
	 * ae__RunReportXml
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#runReportXml
	 * @param runReportXml106
	 * 
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.RunReportXmlResponse runReportXml(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.RunReportXml runReportXml106)

			throws java.rmi.RemoteException

	{
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[19].getName());
			_operationClient.getOptions().setAction(
					"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/RunReportXmlRequest");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

			addPropertyToOperationClient(_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), runReportXml106,
					optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
							"runReportXml")),
					new javax.xml.namespace.QName("urn:sbmappservices72", "RunReportXml"));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

			java.lang.Object object = fromOM(_returnEnv.getBody().getFirstElement(),
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.RunReportXmlResponse.class);

			return (com.mitel.ka.kcapp.services.sbm.sbmappservices72.RunReportXmlResponse) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap
						.containsKey(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "RunReportXml"))) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "RunReportXml"));
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.reflect.Constructor constructor = exceptionClass
								.getConstructor(java.lang.String.class);
						java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "RunReportXml"));
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt, messageClass);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			if (_messageContext.getTransportOut() != null) {
				_messageContext.getTransportOut().getSender().cleanup(_messageContext);
			}
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations Service
	 * definition of function ae__RunReportXml
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#startrunReportXml
	 * @param runReportXml106
	 * 
	 */
	public void startrunReportXml(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.RunReportXml runReportXml106,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException {

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient
				.createClient(_operations[19].getName());
		_operationClient.getOptions().setAction(
				"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/RunReportXmlRequest");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(_operationClient,
				org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), runReportXml106,
				optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
						"runReportXml")),
				new javax.xml.namespace.QName("urn:sbmappservices72", "RunReportXml"));

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
			public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
				try {
					org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

					java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.RunReportXmlResponse.class);
					callback.receiveResultrunReportXml(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.RunReportXmlResponse) object);

				} catch (org.apache.axis2.AxisFault e) {
					callback.receiveErrorrunReportXml(e);
				}
			}

			public void onError(java.lang.Exception error) {
				if (error instanceof org.apache.axis2.AxisFault) {
					org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
					org.apache.axiom.om.OMElement faultElt = f.getDetail();
					if (faultElt != null) {
						if (faultExceptionNameMap.containsKey(
								new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "RunReportXml"))) {
							// make the fault by reflection
							try {
								java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap.get(
										new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "RunReportXml"));
								java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
								java.lang.reflect.Constructor constructor = exceptionClass
										.getConstructor(java.lang.String.class);
								java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
								// message class
								java.lang.String messageClassName = (java.lang.String) faultMessageMap.get(
										new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "RunReportXml"));
								java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
								java.lang.Object messageObject = fromOM(faultElt, messageClass);
								java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
										new java.lang.Class[] { messageClass });
								m.invoke(ex, new java.lang.Object[] { messageObject });

								callback.receiveErrorrunReportXml(new java.rmi.RemoteException(ex.getMessage(), ex));
							} catch (java.lang.ClassCastException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorrunReportXml(f);
							} catch (java.lang.ClassNotFoundException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorrunReportXml(f);
							} catch (java.lang.NoSuchMethodException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorrunReportXml(f);
							} catch (java.lang.reflect.InvocationTargetException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorrunReportXml(f);
							} catch (java.lang.IllegalAccessException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorrunReportXml(f);
							} catch (java.lang.InstantiationException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorrunReportXml(f);
							} catch (org.apache.axis2.AxisFault e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorrunReportXml(f);
							}
						} else {
							callback.receiveErrorrunReportXml(f);
						}
					} else {
						callback.receiveErrorrunReportXml(f);
					}
				} else {
					callback.receiveErrorrunReportXml(error);
				}
			}

			public void onFault(org.apache.axis2.context.MessageContext faultContext) {
				org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
						.getInboundFaultFromMessageContext(faultContext);
				onError(fault);
			}

			public void onComplete() {
				try {
					_messageContext.getTransportOut().getSender().cleanup(_messageContext);
				} catch (org.apache.axis2.AxisFault axisFault) {
					callback.receiveErrorrunReportXml(axisFault);
				}
			}
		});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[19].getMessageReceiver() == null && _operationClient.getOptions().isUseSeparateListener()) {
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[19].setMessageReceiver(_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	/**
	 * Auto generated method signature Creates new item(s), given a project and
	 * item data. Creates the item(s) as sub-items if a parent item is
	 * specified.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#createPrimaryItems
	 * @param createPrimaryItems108
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreatePrimaryItemsResponse createPrimaryItems(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreatePrimaryItems createPrimaryItems108)

			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault {
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[20].getName());
			_operationClient.getOptions().setAction(
					"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/CreatePrimaryItemsRequest");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

			addPropertyToOperationClient(_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), createPrimaryItems108,
					optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
							"createPrimaryItems")),
					new javax.xml.namespace.QName("urn:sbmappservices72", "CreatePrimaryItems"));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

			java.lang.Object object = fromOM(_returnEnv.getBody().getFirstElement(),
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreatePrimaryItemsResponse.class);

			return (com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreatePrimaryItemsResponse) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap.containsKey(
						new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "CreatePrimaryItems"))) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap.get(
								new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "CreatePrimaryItems"));
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.reflect.Constructor constructor = exceptionClass
								.getConstructor(java.lang.String.class);
						java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap.get(
								new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "CreatePrimaryItems"));
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt, messageClass);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
							throw (com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex;
						}

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			if (_messageContext.getTransportOut() != null) {
				_messageContext.getTransportOut().getSender().cleanup(_messageContext);
			}
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations Creates new
	 * item(s), given a project and item data. Creates the item(s) as sub-items
	 * if a parent item is specified.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#startcreatePrimaryItems
	 * @param createPrimaryItems108
	 * 
	 */
	public void startcreatePrimaryItems(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreatePrimaryItems createPrimaryItems108,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException {

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient
				.createClient(_operations[20].getName());
		_operationClient.getOptions().setAction(
				"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/CreatePrimaryItemsRequest");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(_operationClient,
				org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), createPrimaryItems108,
				optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
						"createPrimaryItems")),
				new javax.xml.namespace.QName("urn:sbmappservices72", "CreatePrimaryItems"));

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
			public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
				try {
					org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

					java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreatePrimaryItemsResponse.class);
					callback.receiveResultcreatePrimaryItems(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreatePrimaryItemsResponse) object);

				} catch (org.apache.axis2.AxisFault e) {
					callback.receiveErrorcreatePrimaryItems(e);
				}
			}

			public void onError(java.lang.Exception error) {
				if (error instanceof org.apache.axis2.AxisFault) {
					org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
					org.apache.axiom.om.OMElement faultElt = f.getDetail();
					if (faultElt != null) {
						if (faultExceptionNameMap.containsKey(
								new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "CreatePrimaryItems"))) {
							// make the fault by reflection
							try {
								java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
												"CreatePrimaryItems"));
								java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
								java.lang.reflect.Constructor constructor = exceptionClass
										.getConstructor(java.lang.String.class);
								java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
								// message class
								java.lang.String messageClassName = (java.lang.String) faultMessageMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
												"CreatePrimaryItems"));
								java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
								java.lang.Object messageObject = fromOM(faultElt, messageClass);
								java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
										new java.lang.Class[] { messageClass });
								m.invoke(ex, new java.lang.Object[] { messageObject });

								if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
									callback.receiveErrorcreatePrimaryItems(
											(com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex);
									return;
								}

								callback.receiveErrorcreatePrimaryItems(
										new java.rmi.RemoteException(ex.getMessage(), ex));
							} catch (java.lang.ClassCastException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorcreatePrimaryItems(f);
							} catch (java.lang.ClassNotFoundException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorcreatePrimaryItems(f);
							} catch (java.lang.NoSuchMethodException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorcreatePrimaryItems(f);
							} catch (java.lang.reflect.InvocationTargetException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorcreatePrimaryItems(f);
							} catch (java.lang.IllegalAccessException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorcreatePrimaryItems(f);
							} catch (java.lang.InstantiationException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorcreatePrimaryItems(f);
							} catch (org.apache.axis2.AxisFault e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorcreatePrimaryItems(f);
							}
						} else {
							callback.receiveErrorcreatePrimaryItems(f);
						}
					} else {
						callback.receiveErrorcreatePrimaryItems(f);
					}
				} else {
					callback.receiveErrorcreatePrimaryItems(error);
				}
			}

			public void onFault(org.apache.axis2.context.MessageContext faultContext) {
				org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
						.getInboundFaultFromMessageContext(faultContext);
				onError(fault);
			}

			public void onComplete() {
				try {
					_messageContext.getTransportOut().getSender().cleanup(_messageContext);
				} catch (org.apache.axis2.AxisFault axisFault) {
					callback.receiveErrorcreatePrimaryItems(axisFault);
				}
			}
		});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[20].getMessageReceiver() == null && _operationClient.getOptions().isUseSeparateListener()) {
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[20].setMessageReceiver(_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	/**
	 * Auto generated method signature attaches note.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#createNoteAttachment
	 * @param createNoteAttachment110
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateNoteAttachmentResponse createNoteAttachment(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateNoteAttachment createNoteAttachment110)

			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault {
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[21].getName());
			_operationClient.getOptions().setAction(
					"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/CreateNoteAttachmentRequest");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

			addPropertyToOperationClient(_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), createNoteAttachment110,
					optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
							"createNoteAttachment")),
					new javax.xml.namespace.QName("urn:sbmappservices72", "CreateNoteAttachment"));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

			java.lang.Object object = fromOM(_returnEnv.getBody().getFirstElement(),
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateNoteAttachmentResponse.class);

			return (com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateNoteAttachmentResponse) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap.containsKey(
						new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "CreateNoteAttachment"))) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap.get(
								new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "CreateNoteAttachment"));
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.reflect.Constructor constructor = exceptionClass
								.getConstructor(java.lang.String.class);
						java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap.get(
								new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "CreateNoteAttachment"));
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt, messageClass);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
							throw (com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex;
						}

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			if (_messageContext.getTransportOut() != null) {
				_messageContext.getTransportOut().getSender().cleanup(_messageContext);
			}
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations attaches
	 * note.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#startcreateNoteAttachment
	 * @param createNoteAttachment110
	 * 
	 */
	public void startcreateNoteAttachment(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateNoteAttachment createNoteAttachment110,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException {

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient
				.createClient(_operations[21].getName());
		_operationClient.getOptions().setAction(
				"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/CreateNoteAttachmentRequest");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(_operationClient,
				org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), createNoteAttachment110,
				optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
						"createNoteAttachment")),
				new javax.xml.namespace.QName("urn:sbmappservices72", "CreateNoteAttachment"));

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
			public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
				try {
					org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

					java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateNoteAttachmentResponse.class);
					callback.receiveResultcreateNoteAttachment(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateNoteAttachmentResponse) object);

				} catch (org.apache.axis2.AxisFault e) {
					callback.receiveErrorcreateNoteAttachment(e);
				}
			}

			public void onError(java.lang.Exception error) {
				if (error instanceof org.apache.axis2.AxisFault) {
					org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
					org.apache.axiom.om.OMElement faultElt = f.getDetail();
					if (faultElt != null) {
						if (faultExceptionNameMap.containsKey(
								new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "CreateNoteAttachment"))) {
							// make the fault by reflection
							try {
								java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
												"CreateNoteAttachment"));
								java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
								java.lang.reflect.Constructor constructor = exceptionClass
										.getConstructor(java.lang.String.class);
								java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
								// message class
								java.lang.String messageClassName = (java.lang.String) faultMessageMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
												"CreateNoteAttachment"));
								java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
								java.lang.Object messageObject = fromOM(faultElt, messageClass);
								java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
										new java.lang.Class[] { messageClass });
								m.invoke(ex, new java.lang.Object[] { messageObject });

								if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
									callback.receiveErrorcreateNoteAttachment(
											(com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex);
									return;
								}

								callback.receiveErrorcreateNoteAttachment(
										new java.rmi.RemoteException(ex.getMessage(), ex));
							} catch (java.lang.ClassCastException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorcreateNoteAttachment(f);
							} catch (java.lang.ClassNotFoundException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorcreateNoteAttachment(f);
							} catch (java.lang.NoSuchMethodException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorcreateNoteAttachment(f);
							} catch (java.lang.reflect.InvocationTargetException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorcreateNoteAttachment(f);
							} catch (java.lang.IllegalAccessException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorcreateNoteAttachment(f);
							} catch (java.lang.InstantiationException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorcreateNoteAttachment(f);
							} catch (org.apache.axis2.AxisFault e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorcreateNoteAttachment(f);
							}
						} else {
							callback.receiveErrorcreateNoteAttachment(f);
						}
					} else {
						callback.receiveErrorcreateNoteAttachment(f);
					}
				} else {
					callback.receiveErrorcreateNoteAttachment(error);
				}
			}

			public void onFault(org.apache.axis2.context.MessageContext faultContext) {
				org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
						.getInboundFaultFromMessageContext(faultContext);
				onError(fault);
			}

			public void onComplete() {
				try {
					_messageContext.getTransportOut().getSender().cleanup(_messageContext);
				} catch (org.apache.axis2.AxisFault axisFault) {
					callback.receiveErrorcreateNoteAttachment(axisFault);
				}
			}
		});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[21].getMessageReceiver() == null && _operationClient.getOptions().isUseSeparateListener()) {
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[21].setMessageReceiver(_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	/**
	 * Auto generated method signature Gets the list of available applications.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#getApplications
	 * @param getApplications112
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetApplicationsResponse getApplications(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetApplications getApplications112)

			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault {
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[22].getName());
			_operationClient.getOptions().setAction(
					"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/GetApplicationsRequest");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

			addPropertyToOperationClient(_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), getApplications112,
					optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
							"getApplications")),
					new javax.xml.namespace.QName("urn:sbmappservices72", "GetApplications"));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

			java.lang.Object object = fromOM(_returnEnv.getBody().getFirstElement(),
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetApplicationsResponse.class);

			return (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetApplicationsResponse) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap
						.containsKey(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetApplications"))) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetApplications"));
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.reflect.Constructor constructor = exceptionClass
								.getConstructor(java.lang.String.class);
						java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetApplications"));
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt, messageClass);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
							throw (com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex;
						}

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			if (_messageContext.getTransportOut() != null) {
				_messageContext.getTransportOut().getSender().cleanup(_messageContext);
			}
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations Gets the
	 * list of available applications.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#startgetApplications
	 * @param getApplications112
	 * 
	 */
	public void startgetApplications(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetApplications getApplications112,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException {

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient
				.createClient(_operations[22].getName());
		_operationClient.getOptions().setAction(
				"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/GetApplicationsRequest");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(_operationClient,
				org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), getApplications112,
				optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
						"getApplications")),
				new javax.xml.namespace.QName("urn:sbmappservices72", "GetApplications"));

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
			public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
				try {
					org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

					java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetApplicationsResponse.class);
					callback.receiveResultgetApplications(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetApplicationsResponse) object);

				} catch (org.apache.axis2.AxisFault e) {
					callback.receiveErrorgetApplications(e);
				}
			}

			public void onError(java.lang.Exception error) {
				if (error instanceof org.apache.axis2.AxisFault) {
					org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
					org.apache.axiom.om.OMElement faultElt = f.getDetail();
					if (faultElt != null) {
						if (faultExceptionNameMap.containsKey(
								new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetApplications"))) {
							// make the fault by reflection
							try {
								java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
												"GetApplications"));
								java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
								java.lang.reflect.Constructor constructor = exceptionClass
										.getConstructor(java.lang.String.class);
								java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
								// message class
								java.lang.String messageClassName = (java.lang.String) faultMessageMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
												"GetApplications"));
								java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
								java.lang.Object messageObject = fromOM(faultElt, messageClass);
								java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
										new java.lang.Class[] { messageClass });
								m.invoke(ex, new java.lang.Object[] { messageObject });

								if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
									callback.receiveErrorgetApplications(
											(com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex);
									return;
								}

								callback.receiveErrorgetApplications(new java.rmi.RemoteException(ex.getMessage(), ex));
							} catch (java.lang.ClassCastException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetApplications(f);
							} catch (java.lang.ClassNotFoundException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetApplications(f);
							} catch (java.lang.NoSuchMethodException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetApplications(f);
							} catch (java.lang.reflect.InvocationTargetException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetApplications(f);
							} catch (java.lang.IllegalAccessException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetApplications(f);
							} catch (java.lang.InstantiationException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetApplications(f);
							} catch (org.apache.axis2.AxisFault e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetApplications(f);
							}
						} else {
							callback.receiveErrorgetApplications(f);
						}
					} else {
						callback.receiveErrorgetApplications(f);
					}
				} else {
					callback.receiveErrorgetApplications(error);
				}
			}

			public void onFault(org.apache.axis2.context.MessageContext faultContext) {
				org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
						.getInboundFaultFromMessageContext(faultContext);
				onError(fault);
			}

			public void onComplete() {
				try {
					_messageContext.getTransportOut().getSender().cleanup(_messageContext);
				} catch (org.apache.axis2.AxisFault axisFault) {
					callback.receiveErrorgetApplications(axisFault);
				}
			}
		});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[22].getMessageReceiver() == null && _operationClient.getOptions().isUseSeparateListener()) {
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[22].setMessageReceiver(_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	/**
	 * Auto generated method signature Deletes an existing attachment, which may
	 * be a note, item link, URL attachment or file attachment, given an
	 * attachment id.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#deleteAttachment
	 * @param deleteAttachment114
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteAttachmentResponse deleteAttachment(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteAttachment deleteAttachment114)

			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault {
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[23].getName());
			_operationClient.getOptions().setAction(
					"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/DeleteAttachmentRequest");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

			addPropertyToOperationClient(_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), deleteAttachment114,
					optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
							"deleteAttachment")),
					new javax.xml.namespace.QName("urn:sbmappservices72", "DeleteAttachment"));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

			java.lang.Object object = fromOM(_returnEnv.getBody().getFirstElement(),
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteAttachmentResponse.class);

			return (com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteAttachmentResponse) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap.containsKey(
						new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "DeleteAttachment"))) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "DeleteAttachment"));
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.reflect.Constructor constructor = exceptionClass
								.getConstructor(java.lang.String.class);
						java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "DeleteAttachment"));
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt, messageClass);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
							throw (com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex;
						}

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			if (_messageContext.getTransportOut() != null) {
				_messageContext.getTransportOut().getSender().cleanup(_messageContext);
			}
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations Deletes an
	 * existing attachment, which may be a note, item link, URL attachment or
	 * file attachment, given an attachment id.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#startdeleteAttachment
	 * @param deleteAttachment114
	 * 
	 */
	public void startdeleteAttachment(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteAttachment deleteAttachment114,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException {

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient
				.createClient(_operations[23].getName());
		_operationClient.getOptions().setAction(
				"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/DeleteAttachmentRequest");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(_operationClient,
				org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), deleteAttachment114,
				optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
						"deleteAttachment")),
				new javax.xml.namespace.QName("urn:sbmappservices72", "DeleteAttachment"));

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
			public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
				try {
					org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

					java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteAttachmentResponse.class);
					callback.receiveResultdeleteAttachment(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteAttachmentResponse) object);

				} catch (org.apache.axis2.AxisFault e) {
					callback.receiveErrordeleteAttachment(e);
				}
			}

			public void onError(java.lang.Exception error) {
				if (error instanceof org.apache.axis2.AxisFault) {
					org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
					org.apache.axiom.om.OMElement faultElt = f.getDetail();
					if (faultElt != null) {
						if (faultExceptionNameMap.containsKey(
								new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "DeleteAttachment"))) {
							// make the fault by reflection
							try {
								java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
												"DeleteAttachment"));
								java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
								java.lang.reflect.Constructor constructor = exceptionClass
										.getConstructor(java.lang.String.class);
								java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
								// message class
								java.lang.String messageClassName = (java.lang.String) faultMessageMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
												"DeleteAttachment"));
								java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
								java.lang.Object messageObject = fromOM(faultElt, messageClass);
								java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
										new java.lang.Class[] { messageClass });
								m.invoke(ex, new java.lang.Object[] { messageObject });

								if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
									callback.receiveErrordeleteAttachment(
											(com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex);
									return;
								}

								callback.receiveErrordeleteAttachment(
										new java.rmi.RemoteException(ex.getMessage(), ex));
							} catch (java.lang.ClassCastException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrordeleteAttachment(f);
							} catch (java.lang.ClassNotFoundException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrordeleteAttachment(f);
							} catch (java.lang.NoSuchMethodException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrordeleteAttachment(f);
							} catch (java.lang.reflect.InvocationTargetException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrordeleteAttachment(f);
							} catch (java.lang.IllegalAccessException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrordeleteAttachment(f);
							} catch (java.lang.InstantiationException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrordeleteAttachment(f);
							} catch (org.apache.axis2.AxisFault e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrordeleteAttachment(f);
							}
						} else {
							callback.receiveErrordeleteAttachment(f);
						}
					} else {
						callback.receiveErrordeleteAttachment(f);
					}
				} else {
					callback.receiveErrordeleteAttachment(error);
				}
			}

			public void onFault(org.apache.axis2.context.MessageContext faultContext) {
				org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
						.getInboundFaultFromMessageContext(faultContext);
				onError(fault);
			}

			public void onComplete() {
				try {
					_messageContext.getTransportOut().getSender().cleanup(_messageContext);
				} catch (org.apache.axis2.AxisFault axisFault) {
					callback.receiveErrordeleteAttachment(axisFault);
				}
			}
		});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[23].getMessageReceiver() == null && _operationClient.getOptions().isUseSeparateListener()) {
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[23].setMessageReceiver(_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	/**
	 * Auto generated method signature Determine if the specified user is valid.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#isUserValid
	 * @param isUserValid116
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.IsUserValidResponse isUserValid(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.IsUserValid isUserValid116)

			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault {
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[24].getName());
			_operationClient.getOptions().setAction(
					"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/IsUserValidRequest");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

			addPropertyToOperationClient(_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), isUserValid116,
					optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
							"isUserValid")),
					new javax.xml.namespace.QName("urn:sbmappservices72", "IsUserValid"));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

			java.lang.Object object = fromOM(_returnEnv.getBody().getFirstElement(),
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.IsUserValidResponse.class);

			return (com.mitel.ka.kcapp.services.sbm.sbmappservices72.IsUserValidResponse) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap
						.containsKey(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "IsUserValid"))) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "IsUserValid"));
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.reflect.Constructor constructor = exceptionClass
								.getConstructor(java.lang.String.class);
						java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "IsUserValid"));
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt, messageClass);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
							throw (com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex;
						}

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			if (_messageContext.getTransportOut() != null) {
				_messageContext.getTransportOut().getSender().cleanup(_messageContext);
			}
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations Determine if
	 * the specified user is valid.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#startisUserValid
	 * @param isUserValid116
	 * 
	 */
	public void startisUserValid(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.IsUserValid isUserValid116,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException {

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient
				.createClient(_operations[24].getName());
		_operationClient.getOptions().setAction(
				"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/IsUserValidRequest");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(_operationClient,
				org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), isUserValid116, optimizeContent(
				new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl", "isUserValid")),
				new javax.xml.namespace.QName("urn:sbmappservices72", "IsUserValid"));

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
			public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
				try {
					org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

					java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.IsUserValidResponse.class);
					callback.receiveResultisUserValid(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.IsUserValidResponse) object);

				} catch (org.apache.axis2.AxisFault e) {
					callback.receiveErrorisUserValid(e);
				}
			}

			public void onError(java.lang.Exception error) {
				if (error instanceof org.apache.axis2.AxisFault) {
					org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
					org.apache.axiom.om.OMElement faultElt = f.getDetail();
					if (faultElt != null) {
						if (faultExceptionNameMap.containsKey(
								new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "IsUserValid"))) {
							// make the fault by reflection
							try {
								java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap.get(
										new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "IsUserValid"));
								java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
								java.lang.reflect.Constructor constructor = exceptionClass
										.getConstructor(java.lang.String.class);
								java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
								// message class
								java.lang.String messageClassName = (java.lang.String) faultMessageMap.get(
										new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "IsUserValid"));
								java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
								java.lang.Object messageObject = fromOM(faultElt, messageClass);
								java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
										new java.lang.Class[] { messageClass });
								m.invoke(ex, new java.lang.Object[] { messageObject });

								if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
									callback.receiveErrorisUserValid(
											(com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex);
									return;
								}

								callback.receiveErrorisUserValid(new java.rmi.RemoteException(ex.getMessage(), ex));
							} catch (java.lang.ClassCastException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorisUserValid(f);
							} catch (java.lang.ClassNotFoundException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorisUserValid(f);
							} catch (java.lang.NoSuchMethodException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorisUserValid(f);
							} catch (java.lang.reflect.InvocationTargetException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorisUserValid(f);
							} catch (java.lang.IllegalAccessException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorisUserValid(f);
							} catch (java.lang.InstantiationException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorisUserValid(f);
							} catch (org.apache.axis2.AxisFault e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorisUserValid(f);
							}
						} else {
							callback.receiveErrorisUserValid(f);
						}
					} else {
						callback.receiveErrorisUserValid(f);
					}
				} else {
					callback.receiveErrorisUserValid(error);
				}
			}

			public void onFault(org.apache.axis2.context.MessageContext faultContext) {
				org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
						.getInboundFaultFromMessageContext(faultContext);
				onError(fault);
			}

			public void onComplete() {
				try {
					_messageContext.getTransportOut().getSender().cleanup(_messageContext);
				} catch (org.apache.axis2.AxisFault axisFault) {
					callback.receiveErrorisUserValid(axisFault);
				}
			}
		});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[24].getMessageReceiver() == null && _operationClient.getOptions().isUseSeparateListener()) {
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[24].setMessageReceiver(_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	/**
	 * Auto generated method signature Creates a new attachment, given item id
	 * of the item to which it is to be attached, and the file attachment
	 * contents.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#createFileAttachment
	 * @param createFileAttachment118
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateFileAttachmentResponse createFileAttachment(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateFileAttachment createFileAttachment118)

			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault {
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[25].getName());
			_operationClient.getOptions().setAction(
					"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/CreateFileAttachmentRequest");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

			addPropertyToOperationClient(_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), createFileAttachment118,
					optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
							"createFileAttachment")),
					new javax.xml.namespace.QName("urn:sbmappservices72", "CreateFileAttachment"));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

			java.lang.Object object = fromOM(_returnEnv.getBody().getFirstElement(),
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateFileAttachmentResponse.class);

			return (com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateFileAttachmentResponse) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap.containsKey(
						new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "CreateFileAttachment"))) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap.get(
								new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "CreateFileAttachment"));
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.reflect.Constructor constructor = exceptionClass
								.getConstructor(java.lang.String.class);
						java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap.get(
								new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "CreateFileAttachment"));
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt, messageClass);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
							throw (com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex;
						}

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			if (_messageContext.getTransportOut() != null) {
				_messageContext.getTransportOut().getSender().cleanup(_messageContext);
			}
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations Creates a
	 * new attachment, given item id of the item to which it is to be attached,
	 * and the file attachment contents.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#startcreateFileAttachment
	 * @param createFileAttachment118
	 * 
	 */
	public void startcreateFileAttachment(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateFileAttachment createFileAttachment118,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException {

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient
				.createClient(_operations[25].getName());
		_operationClient.getOptions().setAction(
				"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/CreateFileAttachmentRequest");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(_operationClient,
				org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), createFileAttachment118,
				optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
						"createFileAttachment")),
				new javax.xml.namespace.QName("urn:sbmappservices72", "CreateFileAttachment"));

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
			public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
				try {
					org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

					java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateFileAttachmentResponse.class);
					callback.receiveResultcreateFileAttachment(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateFileAttachmentResponse) object);

				} catch (org.apache.axis2.AxisFault e) {
					callback.receiveErrorcreateFileAttachment(e);
				}
			}

			public void onError(java.lang.Exception error) {
				if (error instanceof org.apache.axis2.AxisFault) {
					org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
					org.apache.axiom.om.OMElement faultElt = f.getDetail();
					if (faultElt != null) {
						if (faultExceptionNameMap.containsKey(
								new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "CreateFileAttachment"))) {
							// make the fault by reflection
							try {
								java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
												"CreateFileAttachment"));
								java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
								java.lang.reflect.Constructor constructor = exceptionClass
										.getConstructor(java.lang.String.class);
								java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
								// message class
								java.lang.String messageClassName = (java.lang.String) faultMessageMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
												"CreateFileAttachment"));
								java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
								java.lang.Object messageObject = fromOM(faultElt, messageClass);
								java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
										new java.lang.Class[] { messageClass });
								m.invoke(ex, new java.lang.Object[] { messageObject });

								if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
									callback.receiveErrorcreateFileAttachment(
											(com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex);
									return;
								}

								callback.receiveErrorcreateFileAttachment(
										new java.rmi.RemoteException(ex.getMessage(), ex));
							} catch (java.lang.ClassCastException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorcreateFileAttachment(f);
							} catch (java.lang.ClassNotFoundException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorcreateFileAttachment(f);
							} catch (java.lang.NoSuchMethodException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorcreateFileAttachment(f);
							} catch (java.lang.reflect.InvocationTargetException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorcreateFileAttachment(f);
							} catch (java.lang.IllegalAccessException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorcreateFileAttachment(f);
							} catch (java.lang.InstantiationException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorcreateFileAttachment(f);
							} catch (org.apache.axis2.AxisFault e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorcreateFileAttachment(f);
							}
						} else {
							callback.receiveErrorcreateFileAttachment(f);
						}
					} else {
						callback.receiveErrorcreateFileAttachment(f);
					}
				} else {
					callback.receiveErrorcreateFileAttachment(error);
				}
			}

			public void onFault(org.apache.axis2.context.MessageContext faultContext) {
				org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
						.getInboundFaultFromMessageContext(faultContext);
				onError(fault);
			}

			public void onComplete() {
				try {
					_messageContext.getTransportOut().getSender().cleanup(_messageContext);
				} catch (org.apache.axis2.AxisFault axisFault) {
					callback.receiveErrorcreateFileAttachment(axisFault);
				}
			}
		});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[25].getMessageReceiver() == null && _operationClient.getOptions().isUseSeparateListener()) {
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[25].setMessageReceiver(_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	/**
	 * Auto generated method signature Creates a new aux item, given a table id
	 * and item data.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#createAuxItem
	 * @param createAuxItem120
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateAuxItemResponse createAuxItem(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateAuxItem createAuxItem120)

			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault {
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[26].getName());
			_operationClient.getOptions().setAction(
					"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/CreateAuxItemRequest");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

			addPropertyToOperationClient(_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), createAuxItem120,
					optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
							"createAuxItem")),
					new javax.xml.namespace.QName("urn:sbmappservices72", "CreateAuxItem"));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

			java.lang.Object object = fromOM(_returnEnv.getBody().getFirstElement(),
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateAuxItemResponse.class);

			return (com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateAuxItemResponse) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap
						.containsKey(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "CreateAuxItem"))) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "CreateAuxItem"));
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.reflect.Constructor constructor = exceptionClass
								.getConstructor(java.lang.String.class);
						java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "CreateAuxItem"));
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt, messageClass);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
							throw (com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex;
						}

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			if (_messageContext.getTransportOut() != null) {
				_messageContext.getTransportOut().getSender().cleanup(_messageContext);
			}
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations Creates a
	 * new aux item, given a table id and item data.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#startcreateAuxItem
	 * @param createAuxItem120
	 * 
	 */
	public void startcreateAuxItem(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateAuxItem createAuxItem120,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException {

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient
				.createClient(_operations[26].getName());
		_operationClient.getOptions().setAction(
				"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/CreateAuxItemRequest");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(_operationClient,
				org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), createAuxItem120,
				optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
						"createAuxItem")),
				new javax.xml.namespace.QName("urn:sbmappservices72", "CreateAuxItem"));

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
			public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
				try {
					org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

					java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateAuxItemResponse.class);
					callback.receiveResultcreateAuxItem(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateAuxItemResponse) object);

				} catch (org.apache.axis2.AxisFault e) {
					callback.receiveErrorcreateAuxItem(e);
				}
			}

			public void onError(java.lang.Exception error) {
				if (error instanceof org.apache.axis2.AxisFault) {
					org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
					org.apache.axiom.om.OMElement faultElt = f.getDetail();
					if (faultElt != null) {
						if (faultExceptionNameMap.containsKey(
								new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "CreateAuxItem"))) {
							// make the fault by reflection
							try {
								java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap.get(
										new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "CreateAuxItem"));
								java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
								java.lang.reflect.Constructor constructor = exceptionClass
										.getConstructor(java.lang.String.class);
								java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
								// message class
								java.lang.String messageClassName = (java.lang.String) faultMessageMap.get(
										new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "CreateAuxItem"));
								java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
								java.lang.Object messageObject = fromOM(faultElt, messageClass);
								java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
										new java.lang.Class[] { messageClass });
								m.invoke(ex, new java.lang.Object[] { messageObject });

								if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
									callback.receiveErrorcreateAuxItem(
											(com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex);
									return;
								}

								callback.receiveErrorcreateAuxItem(new java.rmi.RemoteException(ex.getMessage(), ex));
							} catch (java.lang.ClassCastException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorcreateAuxItem(f);
							} catch (java.lang.ClassNotFoundException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorcreateAuxItem(f);
							} catch (java.lang.NoSuchMethodException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorcreateAuxItem(f);
							} catch (java.lang.reflect.InvocationTargetException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorcreateAuxItem(f);
							} catch (java.lang.IllegalAccessException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorcreateAuxItem(f);
							} catch (java.lang.InstantiationException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorcreateAuxItem(f);
							} catch (org.apache.axis2.AxisFault e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorcreateAuxItem(f);
							}
						} else {
							callback.receiveErrorcreateAuxItem(f);
						}
					} else {
						callback.receiveErrorcreateAuxItem(f);
					}
				} else {
					callback.receiveErrorcreateAuxItem(error);
				}
			}

			public void onFault(org.apache.axis2.context.MessageContext faultContext) {
				org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
						.getInboundFaultFromMessageContext(faultContext);
				onError(fault);
			}

			public void onComplete() {
				try {
					_messageContext.getTransportOut().getSender().cleanup(_messageContext);
				} catch (org.apache.axis2.AxisFault axisFault) {
					callback.receiveErrorcreateAuxItem(axisFault);
				}
			}
		});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[26].getMessageReceiver() == null && _operationClient.getOptions().isUseSeparateListener()) {
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[26].setMessageReceiver(_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	/**
	 * Auto generated method signature Gets items, given a list of item ids.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#getItems
	 * @param getItems122
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItemsResponse getItems(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItems getItems122)

			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault {
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[27].getName());
			_operationClient.getOptions().setAction(
					"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/GetItemsRequest");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

			addPropertyToOperationClient(_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), getItems122,
					optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
							"getItems")),
					new javax.xml.namespace.QName("urn:sbmappservices72", "GetItems"));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

			java.lang.Object object = fromOM(_returnEnv.getBody().getFirstElement(),
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItemsResponse.class);

			return (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItemsResponse) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap
						.containsKey(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetItems"))) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetItems"));
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.reflect.Constructor constructor = exceptionClass
								.getConstructor(java.lang.String.class);
						java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetItems"));
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt, messageClass);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
							throw (com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex;
						}

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			if (_messageContext.getTransportOut() != null) {
				_messageContext.getTransportOut().getSender().cleanup(_messageContext);
			}
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations Gets items,
	 * given a list of item ids.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#startgetItems
	 * @param getItems122
	 * 
	 */
	public void startgetItems(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItems getItems122,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException {

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient
				.createClient(_operations[27].getName());
		_operationClient.getOptions()
				.setAction("http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/GetItemsRequest");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(_operationClient,
				org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), getItems122,
				optimizeContent(
						new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl", "getItems")),
				new javax.xml.namespace.QName("urn:sbmappservices72", "GetItems"));

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
			public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
				try {
					org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

					java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItemsResponse.class);
					callback.receiveResultgetItems(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItemsResponse) object);

				} catch (org.apache.axis2.AxisFault e) {
					callback.receiveErrorgetItems(e);
				}
			}

			public void onError(java.lang.Exception error) {
				if (error instanceof org.apache.axis2.AxisFault) {
					org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
					org.apache.axiom.om.OMElement faultElt = f.getDetail();
					if (faultElt != null) {
						if (faultExceptionNameMap.containsKey(
								new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetItems"))) {
							// make the fault by reflection
							try {
								java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetItems"));
								java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
								java.lang.reflect.Constructor constructor = exceptionClass
										.getConstructor(java.lang.String.class);
								java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
								// message class
								java.lang.String messageClassName = (java.lang.String) faultMessageMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetItems"));
								java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
								java.lang.Object messageObject = fromOM(faultElt, messageClass);
								java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
										new java.lang.Class[] { messageClass });
								m.invoke(ex, new java.lang.Object[] { messageObject });

								if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
									callback.receiveErrorgetItems(
											(com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex);
									return;
								}

								callback.receiveErrorgetItems(new java.rmi.RemoteException(ex.getMessage(), ex));
							} catch (java.lang.ClassCastException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetItems(f);
							} catch (java.lang.ClassNotFoundException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetItems(f);
							} catch (java.lang.NoSuchMethodException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetItems(f);
							} catch (java.lang.reflect.InvocationTargetException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetItems(f);
							} catch (java.lang.IllegalAccessException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetItems(f);
							} catch (java.lang.InstantiationException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetItems(f);
							} catch (org.apache.axis2.AxisFault e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetItems(f);
							}
						} else {
							callback.receiveErrorgetItems(f);
						}
					} else {
						callback.receiveErrorgetItems(f);
					}
				} else {
					callback.receiveErrorgetItems(error);
				}
			}

			public void onFault(org.apache.axis2.context.MessageContext faultContext) {
				org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
						.getInboundFaultFromMessageContext(faultContext);
				onError(fault);
			}

			public void onComplete() {
				try {
					_messageContext.getTransportOut().getSender().cleanup(_messageContext);
				} catch (org.apache.axis2.AxisFault axisFault) {
					callback.receiveErrorgetItems(axisFault);
				}
			}
		});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[27].getMessageReceiver() == null && _operationClient.getOptions().isUseSeparateListener()) {
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[27].setMessageReceiver(_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	/**
	 * Auto generated method signature Creates a new item, given a project and
	 * item data. Creates the item as a sub-item if a parent item is specified.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#createPrimaryItem
	 * @param createPrimaryItem124
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreatePrimaryItemResponse createPrimaryItem(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreatePrimaryItem createPrimaryItem124)

			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault {
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[28].getName());
			_operationClient.getOptions().setAction(
					"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/CreatePrimaryItemRequest");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

			addPropertyToOperationClient(_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), createPrimaryItem124,
					optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
							"createPrimaryItem")),
					new javax.xml.namespace.QName("urn:sbmappservices72", "CreatePrimaryItem"));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

			java.lang.Object object = fromOM(_returnEnv.getBody().getFirstElement(),
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreatePrimaryItemResponse.class);

			return (com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreatePrimaryItemResponse) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap.containsKey(
						new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "CreatePrimaryItem"))) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "CreatePrimaryItem"));
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.reflect.Constructor constructor = exceptionClass
								.getConstructor(java.lang.String.class);
						java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "CreatePrimaryItem"));
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt, messageClass);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
							throw (com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex;
						}

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			if (_messageContext.getTransportOut() != null) {
				_messageContext.getTransportOut().getSender().cleanup(_messageContext);
			}
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations Creates a
	 * new item, given a project and item data. Creates the item as a sub-item
	 * if a parent item is specified.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#startcreatePrimaryItem
	 * @param createPrimaryItem124
	 * 
	 */
	public void startcreatePrimaryItem(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreatePrimaryItem createPrimaryItem124,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException {

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient
				.createClient(_operations[28].getName());
		_operationClient.getOptions().setAction(
				"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/CreatePrimaryItemRequest");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(_operationClient,
				org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), createPrimaryItem124,
				optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
						"createPrimaryItem")),
				new javax.xml.namespace.QName("urn:sbmappservices72", "CreatePrimaryItem"));

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
			public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
				try {
					org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

					java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreatePrimaryItemResponse.class);
					callback.receiveResultcreatePrimaryItem(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreatePrimaryItemResponse) object);

				} catch (org.apache.axis2.AxisFault e) {
					callback.receiveErrorcreatePrimaryItem(e);
				}
			}

			public void onError(java.lang.Exception error) {
				if (error instanceof org.apache.axis2.AxisFault) {
					org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
					org.apache.axiom.om.OMElement faultElt = f.getDetail();
					if (faultElt != null) {
						if (faultExceptionNameMap.containsKey(
								new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "CreatePrimaryItem"))) {
							// make the fault by reflection
							try {
								java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
												"CreatePrimaryItem"));
								java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
								java.lang.reflect.Constructor constructor = exceptionClass
										.getConstructor(java.lang.String.class);
								java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
								// message class
								java.lang.String messageClassName = (java.lang.String) faultMessageMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
												"CreatePrimaryItem"));
								java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
								java.lang.Object messageObject = fromOM(faultElt, messageClass);
								java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
										new java.lang.Class[] { messageClass });
								m.invoke(ex, new java.lang.Object[] { messageObject });

								if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
									callback.receiveErrorcreatePrimaryItem(
											(com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex);
									return;
								}

								callback.receiveErrorcreatePrimaryItem(
										new java.rmi.RemoteException(ex.getMessage(), ex));
							} catch (java.lang.ClassCastException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorcreatePrimaryItem(f);
							} catch (java.lang.ClassNotFoundException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorcreatePrimaryItem(f);
							} catch (java.lang.NoSuchMethodException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorcreatePrimaryItem(f);
							} catch (java.lang.reflect.InvocationTargetException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorcreatePrimaryItem(f);
							} catch (java.lang.IllegalAccessException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorcreatePrimaryItem(f);
							} catch (java.lang.InstantiationException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorcreatePrimaryItem(f);
							} catch (org.apache.axis2.AxisFault e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorcreatePrimaryItem(f);
							}
						} else {
							callback.receiveErrorcreatePrimaryItem(f);
						}
					} else {
						callback.receiveErrorcreatePrimaryItem(f);
					}
				} else {
					callback.receiveErrorcreatePrimaryItem(error);
				}
			}

			public void onFault(org.apache.axis2.context.MessageContext faultContext) {
				org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
						.getInboundFaultFromMessageContext(faultContext);
				onError(fault);
			}

			public void onComplete() {
				try {
					_messageContext.getTransportOut().getSender().cleanup(_messageContext);
				} catch (org.apache.axis2.AxisFault axisFault) {
					callback.receiveErrorcreatePrimaryItem(axisFault);
				}
			}
		});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[28].getMessageReceiver() == null && _operationClient.getOptions().isUseSeparateListener()) {
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[28].setMessageReceiver(_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	/**
	 * Auto generated method signature Gets item, given an item id.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#getItem
	 * @param getItem126
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItemResponse getItem(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItem getItem126)

			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault {
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[29].getName());
			_operationClient.getOptions().setAction(
					"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/GetItemRequest");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

			addPropertyToOperationClient(_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), getItem126, optimizeContent(
					new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl", "getItem")),
					new javax.xml.namespace.QName("urn:sbmappservices72", "GetItem"));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

			java.lang.Object object = fromOM(_returnEnv.getBody().getFirstElement(),
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItemResponse.class);

			return (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItemResponse) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap
						.containsKey(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetItem"))) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetItem"));
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.reflect.Constructor constructor = exceptionClass
								.getConstructor(java.lang.String.class);
						java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetItem"));
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt, messageClass);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
							throw (com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex;
						}

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			if (_messageContext.getTransportOut() != null) {
				_messageContext.getTransportOut().getSender().cleanup(_messageContext);
			}
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations Gets item,
	 * given an item id.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#startgetItem
	 * @param getItem126
	 * 
	 */
	public void startgetItem(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItem getItem126,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException {

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient
				.createClient(_operations[29].getName());
		_operationClient.getOptions()
				.setAction("http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/GetItemRequest");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(_operationClient,
				org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), getItem126,
				optimizeContent(
						new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl", "getItem")),
				new javax.xml.namespace.QName("urn:sbmappservices72", "GetItem"));

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
			public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
				try {
					org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

					java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItemResponse.class);
					callback.receiveResultgetItem(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItemResponse) object);

				} catch (org.apache.axis2.AxisFault e) {
					callback.receiveErrorgetItem(e);
				}
			}

			public void onError(java.lang.Exception error) {
				if (error instanceof org.apache.axis2.AxisFault) {
					org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
					org.apache.axiom.om.OMElement faultElt = f.getDetail();
					if (faultElt != null) {
						if (faultExceptionNameMap
								.containsKey(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetItem"))) {
							// make the fault by reflection
							try {
								java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetItem"));
								java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
								java.lang.reflect.Constructor constructor = exceptionClass
										.getConstructor(java.lang.String.class);
								java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
								// message class
								java.lang.String messageClassName = (java.lang.String) faultMessageMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetItem"));
								java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
								java.lang.Object messageObject = fromOM(faultElt, messageClass);
								java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
										new java.lang.Class[] { messageClass });
								m.invoke(ex, new java.lang.Object[] { messageObject });

								if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
									callback.receiveErrorgetItem(
											(com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex);
									return;
								}

								callback.receiveErrorgetItem(new java.rmi.RemoteException(ex.getMessage(), ex));
							} catch (java.lang.ClassCastException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetItem(f);
							} catch (java.lang.ClassNotFoundException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetItem(f);
							} catch (java.lang.NoSuchMethodException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetItem(f);
							} catch (java.lang.reflect.InvocationTargetException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetItem(f);
							} catch (java.lang.IllegalAccessException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetItem(f);
							} catch (java.lang.InstantiationException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetItem(f);
							} catch (org.apache.axis2.AxisFault e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetItem(f);
							}
						} else {
							callback.receiveErrorgetItem(f);
						}
					} else {
						callback.receiveErrorgetItem(f);
					}
				} else {
					callback.receiveErrorgetItem(error);
				}
			}

			public void onFault(org.apache.axis2.context.MessageContext faultContext) {
				org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
						.getInboundFaultFromMessageContext(faultContext);
				onError(fault);
			}

			public void onComplete() {
				try {
					_messageContext.getTransportOut().getSender().cleanup(_messageContext);
				} catch (org.apache.axis2.AxisFault axisFault) {
					callback.receiveErrorgetItem(axisFault);
				}
			}
		});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[29].getMessageReceiver() == null && _operationClient.getOptions().isUseSeparateListener()) {
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[29].setMessageReceiver(_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	/**
	 * Auto generated method signature Return available Submit transitions,
	 * given an item id and attribute name (may be null or empty).
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#getAvailableSubmitTransitions
	 * @param getAvailableSubmitTransitions128
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetAvailableSubmitTransitionsResponse getAvailableSubmitTransitions(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetAvailableSubmitTransitions getAvailableSubmitTransitions128)

			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault {
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[30].getName());
			_operationClient.getOptions().setAction(
					"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/GetAvailableSubmitTransitionsRequest");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

			addPropertyToOperationClient(_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
					getAvailableSubmitTransitions128,
					optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
							"getAvailableSubmitTransitions")),
					new javax.xml.namespace.QName("urn:sbmappservices72", "GetAvailableSubmitTransitions"));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

			java.lang.Object object = fromOM(_returnEnv.getBody().getFirstElement(),
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetAvailableSubmitTransitionsResponse.class);

			return (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetAvailableSubmitTransitionsResponse) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap.containsKey(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
						"GetAvailableSubmitTransitions"))) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
										"GetAvailableSubmitTransitions"));
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.reflect.Constructor constructor = exceptionClass
								.getConstructor(java.lang.String.class);
						java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
										"GetAvailableSubmitTransitions"));
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt, messageClass);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
							throw (com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex;
						}

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			if (_messageContext.getTransportOut() != null) {
				_messageContext.getTransportOut().getSender().cleanup(_messageContext);
			}
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations Return
	 * available Submit transitions, given an item id and attribute name (may be
	 * null or empty).
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#startgetAvailableSubmitTransitions
	 * @param getAvailableSubmitTransitions128
	 * 
	 */
	public void startgetAvailableSubmitTransitions(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetAvailableSubmitTransitions getAvailableSubmitTransitions128,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException {

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient
				.createClient(_operations[30].getName());
		_operationClient.getOptions().setAction(
				"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/GetAvailableSubmitTransitionsRequest");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(_operationClient,
				org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
				getAvailableSubmitTransitions128,
				optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
						"getAvailableSubmitTransitions")),
				new javax.xml.namespace.QName("urn:sbmappservices72", "GetAvailableSubmitTransitions"));

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
			public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
				try {
					org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

					java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetAvailableSubmitTransitionsResponse.class);
					callback.receiveResultgetAvailableSubmitTransitions(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetAvailableSubmitTransitionsResponse) object);

				} catch (org.apache.axis2.AxisFault e) {
					callback.receiveErrorgetAvailableSubmitTransitions(e);
				}
			}

			public void onError(java.lang.Exception error) {
				if (error instanceof org.apache.axis2.AxisFault) {
					org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
					org.apache.axiom.om.OMElement faultElt = f.getDetail();
					if (faultElt != null) {
						if (faultExceptionNameMap.containsKey(new org.apache.axis2.client.FaultMapKey(
								faultElt.getQName(), "GetAvailableSubmitTransitions"))) {
							// make the fault by reflection
							try {
								java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
												"GetAvailableSubmitTransitions"));
								java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
								java.lang.reflect.Constructor constructor = exceptionClass
										.getConstructor(java.lang.String.class);
								java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
								// message class
								java.lang.String messageClassName = (java.lang.String) faultMessageMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
												"GetAvailableSubmitTransitions"));
								java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
								java.lang.Object messageObject = fromOM(faultElt, messageClass);
								java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
										new java.lang.Class[] { messageClass });
								m.invoke(ex, new java.lang.Object[] { messageObject });

								if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
									callback.receiveErrorgetAvailableSubmitTransitions(
											(com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex);
									return;
								}

								callback.receiveErrorgetAvailableSubmitTransitions(
										new java.rmi.RemoteException(ex.getMessage(), ex));
							} catch (java.lang.ClassCastException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetAvailableSubmitTransitions(f);
							} catch (java.lang.ClassNotFoundException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetAvailableSubmitTransitions(f);
							} catch (java.lang.NoSuchMethodException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetAvailableSubmitTransitions(f);
							} catch (java.lang.reflect.InvocationTargetException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetAvailableSubmitTransitions(f);
							} catch (java.lang.IllegalAccessException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetAvailableSubmitTransitions(f);
							} catch (java.lang.InstantiationException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetAvailableSubmitTransitions(f);
							} catch (org.apache.axis2.AxisFault e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetAvailableSubmitTransitions(f);
							}
						} else {
							callback.receiveErrorgetAvailableSubmitTransitions(f);
						}
					} else {
						callback.receiveErrorgetAvailableSubmitTransitions(f);
					}
				} else {
					callback.receiveErrorgetAvailableSubmitTransitions(error);
				}
			}

			public void onFault(org.apache.axis2.context.MessageContext faultContext) {
				org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
						.getInboundFaultFromMessageContext(faultContext);
				onError(fault);
			}

			public void onComplete() {
				try {
					_messageContext.getTransportOut().getSender().cleanup(_messageContext);
				} catch (org.apache.axis2.AxisFault axisFault) {
					callback.receiveErrorgetAvailableSubmitTransitions(axisFault);
				}
			}
		});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[30].getMessageReceiver() == null && _operationClient.getOptions().isUseSeparateListener()) {
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[30].setMessageReceiver(_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	/**
	 * Auto generated method signature Logs out the current active session,
	 * releasing license.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#logout
	 * @param logout130
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.LogoutResponse logout(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.Logout logout130)

			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault {
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[31].getName());
			_operationClient.getOptions().setAction(
					"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/LogoutRequest");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

			addPropertyToOperationClient(_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), logout130,
					optimizeContent(
							new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl", "logout")),
					new javax.xml.namespace.QName("urn:sbmappservices72", "Logout"));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

			java.lang.Object object = fromOM(_returnEnv.getBody().getFirstElement(),
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.LogoutResponse.class);

			return (com.mitel.ka.kcapp.services.sbm.sbmappservices72.LogoutResponse) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap
						.containsKey(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "Logout"))) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "Logout"));
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.reflect.Constructor constructor = exceptionClass
								.getConstructor(java.lang.String.class);
						java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "Logout"));
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt, messageClass);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
							throw (com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex;
						}

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			if (_messageContext.getTransportOut() != null) {
				_messageContext.getTransportOut().getSender().cleanup(_messageContext);
			}
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations Logs out the
	 * current active session, releasing license.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#startlogout
	 * @param logout130
	 * 
	 */
	public void startlogout(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.Logout logout130,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException {

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient
				.createClient(_operations[31].getName());
		_operationClient.getOptions()
				.setAction("http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/LogoutRequest");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(_operationClient,
				org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), logout130,
				optimizeContent(
						new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl", "logout")),
				new javax.xml.namespace.QName("urn:sbmappservices72", "Logout"));

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
			public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
				try {
					org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

					java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.LogoutResponse.class);
					callback.receiveResultlogout(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.LogoutResponse) object);

				} catch (org.apache.axis2.AxisFault e) {
					callback.receiveErrorlogout(e);
				}
			}

			public void onError(java.lang.Exception error) {
				if (error instanceof org.apache.axis2.AxisFault) {
					org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
					org.apache.axiom.om.OMElement faultElt = f.getDetail();
					if (faultElt != null) {
						if (faultExceptionNameMap
								.containsKey(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "Logout"))) {
							// make the fault by reflection
							try {
								java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "Logout"));
								java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
								java.lang.reflect.Constructor constructor = exceptionClass
										.getConstructor(java.lang.String.class);
								java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
								// message class
								java.lang.String messageClassName = (java.lang.String) faultMessageMap
										.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "Logout"));
								java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
								java.lang.Object messageObject = fromOM(faultElt, messageClass);
								java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
										new java.lang.Class[] { messageClass });
								m.invoke(ex, new java.lang.Object[] { messageObject });

								if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
									callback.receiveErrorlogout(
											(com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex);
									return;
								}

								callback.receiveErrorlogout(new java.rmi.RemoteException(ex.getMessage(), ex));
							} catch (java.lang.ClassCastException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorlogout(f);
							} catch (java.lang.ClassNotFoundException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorlogout(f);
							} catch (java.lang.NoSuchMethodException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorlogout(f);
							} catch (java.lang.reflect.InvocationTargetException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorlogout(f);
							} catch (java.lang.IllegalAccessException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorlogout(f);
							} catch (java.lang.InstantiationException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorlogout(f);
							} catch (org.apache.axis2.AxisFault e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorlogout(f);
							}
						} else {
							callback.receiveErrorlogout(f);
						}
					} else {
						callback.receiveErrorlogout(f);
					}
				} else {
					callback.receiveErrorlogout(error);
				}
			}

			public void onFault(org.apache.axis2.context.MessageContext faultContext) {
				org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
						.getInboundFaultFromMessageContext(faultContext);
				onError(fault);
			}

			public void onComplete() {
				try {
					_messageContext.getTransportOut().getSender().cleanup(_messageContext);
				} catch (org.apache.axis2.AxisFault axisFault) {
					callback.receiveErrorlogout(axisFault);
				}
			}
		});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[31].getMessageReceiver() == null && _operationClient.getOptions().isUseSeparateListener()) {
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[31].setMessageReceiver(_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	/**
	 * Auto generated method signature Service definition of function
	 * ae__CreateAuxItems
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#createAuxItems
	 * @param createAuxItems132
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateAuxItemsResponse createAuxItems(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateAuxItems createAuxItems132)

			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault {
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[32].getName());
			_operationClient.getOptions().setAction(
					"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/CreateAuxItemsRequest");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

			addPropertyToOperationClient(_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), createAuxItems132,
					optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
							"createAuxItems")),
					new javax.xml.namespace.QName("urn:sbmappservices72", "CreateAuxItems"));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

			java.lang.Object object = fromOM(_returnEnv.getBody().getFirstElement(),
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateAuxItemsResponse.class);

			return (com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateAuxItemsResponse) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap
						.containsKey(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "CreateAuxItems"))) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "CreateAuxItems"));
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.reflect.Constructor constructor = exceptionClass
								.getConstructor(java.lang.String.class);
						java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "CreateAuxItems"));
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt, messageClass);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
							throw (com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex;
						}

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			if (_messageContext.getTransportOut() != null) {
				_messageContext.getTransportOut().getSender().cleanup(_messageContext);
			}
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations Service
	 * definition of function ae__CreateAuxItems
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#startcreateAuxItems
	 * @param createAuxItems132
	 * 
	 */
	public void startcreateAuxItems(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateAuxItems createAuxItems132,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException {

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient
				.createClient(_operations[32].getName());
		_operationClient.getOptions().setAction(
				"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/CreateAuxItemsRequest");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(_operationClient,
				org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), createAuxItems132,
				optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
						"createAuxItems")),
				new javax.xml.namespace.QName("urn:sbmappservices72", "CreateAuxItems"));

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
			public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
				try {
					org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

					java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateAuxItemsResponse.class);
					callback.receiveResultcreateAuxItems(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateAuxItemsResponse) object);

				} catch (org.apache.axis2.AxisFault e) {
					callback.receiveErrorcreateAuxItems(e);
				}
			}

			public void onError(java.lang.Exception error) {
				if (error instanceof org.apache.axis2.AxisFault) {
					org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
					org.apache.axiom.om.OMElement faultElt = f.getDetail();
					if (faultElt != null) {
						if (faultExceptionNameMap.containsKey(
								new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "CreateAuxItems"))) {
							// make the fault by reflection
							try {
								java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap.get(
										new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "CreateAuxItems"));
								java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
								java.lang.reflect.Constructor constructor = exceptionClass
										.getConstructor(java.lang.String.class);
								java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
								// message class
								java.lang.String messageClassName = (java.lang.String) faultMessageMap.get(
										new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "CreateAuxItems"));
								java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
								java.lang.Object messageObject = fromOM(faultElt, messageClass);
								java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
										new java.lang.Class[] { messageClass });
								m.invoke(ex, new java.lang.Object[] { messageObject });

								if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
									callback.receiveErrorcreateAuxItems(
											(com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex);
									return;
								}

								callback.receiveErrorcreateAuxItems(new java.rmi.RemoteException(ex.getMessage(), ex));
							} catch (java.lang.ClassCastException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorcreateAuxItems(f);
							} catch (java.lang.ClassNotFoundException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorcreateAuxItems(f);
							} catch (java.lang.NoSuchMethodException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorcreateAuxItems(f);
							} catch (java.lang.reflect.InvocationTargetException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorcreateAuxItems(f);
							} catch (java.lang.IllegalAccessException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorcreateAuxItems(f);
							} catch (java.lang.InstantiationException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorcreateAuxItems(f);
							} catch (org.apache.axis2.AxisFault e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorcreateAuxItems(f);
							}
						} else {
							callback.receiveErrorcreateAuxItems(f);
						}
					} else {
						callback.receiveErrorcreateAuxItems(f);
					}
				} else {
					callback.receiveErrorcreateAuxItems(error);
				}
			}

			public void onFault(org.apache.axis2.context.MessageContext faultContext) {
				org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
						.getInboundFaultFromMessageContext(faultContext);
				onError(fault);
			}

			public void onComplete() {
				try {
					_messageContext.getTransportOut().getSender().cleanup(_messageContext);
				} catch (org.apache.axis2.AxisFault axisFault) {
					callback.receiveErrorcreateAuxItems(axisFault);
				}
			}
		});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[32].getMessageReceiver() == null && _operationClient.getOptions().isUseSeparateListener()) {
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[32].setMessageReceiver(_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	/**
	 * Auto generated method signature Gets the server version.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#getVersion
	 * @param getVersion134
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetVersionResponse getVersion(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetVersion getVersion134)

			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault {
		org.apache.axis2.context.MessageContext _messageContext = null;
		try {
			org.apache.axis2.client.OperationClient _operationClient = _serviceClient
					.createClient(_operations[33].getName());
			_operationClient.getOptions().setAction(
					"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/GetVersionRequest");
			_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

			addPropertyToOperationClient(_operationClient,
					org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

			// create a message context
			_messageContext = new org.apache.axis2.context.MessageContext();

			// create SOAP envelope with that payload
			org.apache.axiom.soap.SOAPEnvelope env = null;

			env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), getVersion134,
					optimizeContent(new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl",
							"getVersion")),
					new javax.xml.namespace.QName("urn:sbmappservices72", "GetVersion"));

			// adding SOAP soap_headers
			_serviceClient.addHeadersToEnvelope(env);
			// set the message context with that soap envelope
			_messageContext.setEnvelope(env);

			// add the message contxt to the operation client
			_operationClient.addMessageContext(_messageContext);

			// execute the operation client
			_operationClient.execute(true);

			org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
					.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
			org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

			java.lang.Object object = fromOM(_returnEnv.getBody().getFirstElement(),
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetVersionResponse.class);

			return (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetVersionResponse) object;

		} catch (org.apache.axis2.AxisFault f) {

			org.apache.axiom.om.OMElement faultElt = f.getDetail();
			if (faultElt != null) {
				if (faultExceptionNameMap
						.containsKey(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetVersion"))) {
					// make the fault by reflection
					try {
						java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetVersion"));
						java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
						java.lang.reflect.Constructor constructor = exceptionClass
								.getConstructor(java.lang.String.class);
						java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
						// message class
						java.lang.String messageClassName = (java.lang.String) faultMessageMap
								.get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetVersion"));
						java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
						java.lang.Object messageObject = fromOM(faultElt, messageClass);
						java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
								new java.lang.Class[] { messageClass });
						m.invoke(ex, new java.lang.Object[] { messageObject });

						if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
							throw (com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex;
						}

						throw new java.rmi.RemoteException(ex.getMessage(), ex);
					} catch (java.lang.ClassCastException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.ClassNotFoundException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.NoSuchMethodException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.reflect.InvocationTargetException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.IllegalAccessException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					} catch (java.lang.InstantiationException e) {
						// we cannot intantiate the class - throw the original
						// Axis fault
						throw f;
					}
				} else {
					throw f;
				}
			} else {
				throw f;
			}
		} finally {
			if (_messageContext.getTransportOut() != null) {
				_messageContext.getTransportOut().getSender().cleanup(_messageContext);
			}
		}
	}

	/**
	 * Auto generated method signature for Asynchronous Invocations Gets the
	 * server version.
	 * 
	 * @see com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72#startgetVersion
	 * @param getVersion134
	 * 
	 */
	public void startgetVersion(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetVersion getVersion134,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException {

		org.apache.axis2.client.OperationClient _operationClient = _serviceClient
				.createClient(_operations[33].getName());
		_operationClient.getOptions().setAction(
				"http://localhost:80/gsoap/sbmappservices72.wsdl/sbmappservices72PortType/GetVersionRequest");
		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

		addPropertyToOperationClient(_operationClient,
				org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

		// create SOAP envelope with that payload
		org.apache.axiom.soap.SOAPEnvelope env = null;
		final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

		// Style is Doc.

		env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), getVersion134,
				optimizeContent(
						new javax.xml.namespace.QName("http://localhost:80/gsoap/sbmappservices72.wsdl", "getVersion")),
				new javax.xml.namespace.QName("urn:sbmappservices72", "GetVersion"));

		// adding SOAP soap_headers
		_serviceClient.addHeadersToEnvelope(env);
		// create message context with that soap envelope
		_messageContext.setEnvelope(env);

		// add the message context to the operation client
		_operationClient.addMessageContext(_messageContext);

		_operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
			public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
				try {
					org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

					java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
							com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetVersionResponse.class);
					callback.receiveResultgetVersion(
							(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetVersionResponse) object);

				} catch (org.apache.axis2.AxisFault e) {
					callback.receiveErrorgetVersion(e);
				}
			}

			public void onError(java.lang.Exception error) {
				if (error instanceof org.apache.axis2.AxisFault) {
					org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
					org.apache.axiom.om.OMElement faultElt = f.getDetail();
					if (faultElt != null) {
						if (faultExceptionNameMap.containsKey(
								new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetVersion"))) {
							// make the fault by reflection
							try {
								java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap.get(
										new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetVersion"));
								java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
								java.lang.reflect.Constructor constructor = exceptionClass
										.getConstructor(java.lang.String.class);
								java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());
								// message class
								java.lang.String messageClassName = (java.lang.String) faultMessageMap.get(
										new org.apache.axis2.client.FaultMapKey(faultElt.getQName(), "GetVersion"));
								java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
								java.lang.Object messageObject = fromOM(faultElt, messageClass);
								java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
										new java.lang.Class[] { messageClass });
								m.invoke(ex, new java.lang.Object[] { messageObject });

								if (ex instanceof com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) {
									callback.receiveErrorgetVersion(
											(com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault) ex);
									return;
								}

								callback.receiveErrorgetVersion(new java.rmi.RemoteException(ex.getMessage(), ex));
							} catch (java.lang.ClassCastException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetVersion(f);
							} catch (java.lang.ClassNotFoundException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetVersion(f);
							} catch (java.lang.NoSuchMethodException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetVersion(f);
							} catch (java.lang.reflect.InvocationTargetException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetVersion(f);
							} catch (java.lang.IllegalAccessException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetVersion(f);
							} catch (java.lang.InstantiationException e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetVersion(f);
							} catch (org.apache.axis2.AxisFault e) {
								// we cannot intantiate the class - throw the
								// original Axis fault
								callback.receiveErrorgetVersion(f);
							}
						} else {
							callback.receiveErrorgetVersion(f);
						}
					} else {
						callback.receiveErrorgetVersion(f);
					}
				} else {
					callback.receiveErrorgetVersion(error);
				}
			}

			public void onFault(org.apache.axis2.context.MessageContext faultContext) {
				org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
						.getInboundFaultFromMessageContext(faultContext);
				onError(fault);
			}

			public void onComplete() {
				try {
					_messageContext.getTransportOut().getSender().cleanup(_messageContext);
				} catch (org.apache.axis2.AxisFault axisFault) {
					callback.receiveErrorgetVersion(axisFault);
				}
			}
		});

		org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
		if (_operations[33].getMessageReceiver() == null && _operationClient.getOptions().isUseSeparateListener()) {
			_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
			_operations[33].setMessageReceiver(_callbackReceiver);
		}

		// execute the operation client
		_operationClient.execute(false);

	}

	private javax.xml.namespace.QName[] opNameArray = null;

	private boolean optimizeContent(javax.xml.namespace.QName opName) {

		if (opNameArray == null) {
			return false;
		}
		for (int i = 0; i < opNameArray.length; i++) {
			if (opName.equals(opNameArray[i])) {
				return true;
			}
		}
		return false;
	}

	// http://localhost:80/gsoap/gsoap_ssl.dll?sbmappservices72
	private org.apache.axiom.om.OMElement toOM(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetWorkflows param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetWorkflows.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetWorkflowsResponse param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetWorkflowsResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.AEWebservicesFault param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.AEWebservicesFault.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.UpdateFileAttachment param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.UpdateFileAttachment.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.UpdateFileAttachmentResponse param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.UpdateFileAttachmentResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetUsers param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetUsers.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetUsersResponse param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetUsersResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItemsByQuery param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItemsByQuery.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItemsByQueryResponse param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItemsByQueryResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetNoteLoggerInfo param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetNoteLoggerInfo.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetNoteLoggerInfoResponse param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetNoteLoggerInfoResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteItems param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteItems.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteItemsResponse param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteItemsResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteItemsByQuery param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteItemsByQuery.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteItemsByQueryResponse param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteItemsByQueryResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetFileAttachment param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetFileAttachment.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetFileAttachmentResponse param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetFileAttachmentResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetAvailableTransitions param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetAvailableTransitions.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetAvailableTransitionsResponse param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetAvailableTransitionsResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetTables param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetTables.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetTablesResponse param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetTablesResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(com.mitel.ka.kcapp.services.sbm.sbmappservices72.LinkSubtask param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.LinkSubtask.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.LinkSubtaskResponse param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.LinkSubtaskResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetStateChangeHistory param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetStateChangeHistory.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetStateChangeHistoryResponse param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetStateChangeHistoryResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionItem param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionItem.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionItemResponse param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionItemResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetSubmitProjects param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetSubmitProjects.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetSubmitProjectsResponse param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetSubmitProjectsResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetSolutions param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetSolutions.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetSolutionsResponse param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetSolutionsResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionItems param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionItems.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionItemsResponse param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionItemsResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetReports param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetReports.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetReportsResponse param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetReportsResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(com.mitel.ka.kcapp.services.sbm.sbmappservices72.RunReport param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.RunReport.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(com.mitel.ka.kcapp.services.sbm.sbmappservices72.RunReportResponse param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.RunReportResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetFieldSelections param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetFieldSelections.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetFieldSelectionsResponse param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetFieldSelectionsResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(com.mitel.ka.kcapp.services.sbm.sbmappservices72.RunReportXml param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.RunReportXml.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.RunReportXmlResponse param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.RunReportXmlResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreatePrimaryItems param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreatePrimaryItems.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreatePrimaryItemsResponse param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreatePrimaryItemsResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateNoteAttachment param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateNoteAttachment.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateNoteAttachmentResponse param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateNoteAttachmentResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetApplications param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetApplications.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetApplicationsResponse param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetApplicationsResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteAttachment param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteAttachment.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteAttachmentResponse param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteAttachmentResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(com.mitel.ka.kcapp.services.sbm.sbmappservices72.IsUserValid param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.IsUserValid.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.IsUserValidResponse param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.IsUserValidResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateFileAttachment param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateFileAttachment.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateFileAttachmentResponse param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateFileAttachmentResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateAuxItem param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateAuxItem.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateAuxItemResponse param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateAuxItemResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItems param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItems.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItemsResponse param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItemsResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreatePrimaryItem param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreatePrimaryItem.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreatePrimaryItemResponse param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreatePrimaryItemResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItem param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItem.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItemResponse param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItemResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetAvailableSubmitTransitions param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetAvailableSubmitTransitions.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetAvailableSubmitTransitionsResponse param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetAvailableSubmitTransitionsResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(com.mitel.ka.kcapp.services.sbm.sbmappservices72.Logout param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.Logout.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(com.mitel.ka.kcapp.services.sbm.sbmappservices72.LogoutResponse param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.LogoutResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateAuxItems param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateAuxItems.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateAuxItemsResponse param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateAuxItemsResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetVersion param,
			boolean optimizeContent) throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetVersion.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.om.OMElement toOM(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetVersionResponse param, boolean optimizeContent)
			throws org.apache.axis2.AxisFault {

		try {
			return param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetVersionResponse.MY_QNAME,
					org.apache.axiom.om.OMAbstractFactory.getOMFactory());
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetWorkflows param, boolean optimizeContent,
			javax.xml.namespace.QName elementQName) throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param
					.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetWorkflows.MY_QNAME, factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.UpdateFileAttachment param, boolean optimizeContent,
			javax.xml.namespace.QName elementQName) throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.UpdateFileAttachment.MY_QNAME, factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetUsers param, boolean optimizeContent,
			javax.xml.namespace.QName elementQName) throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(
					param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetUsers.MY_QNAME, factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItemsByQuery param, boolean optimizeContent,
			javax.xml.namespace.QName elementQName) throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param
					.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItemsByQuery.MY_QNAME, factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetNoteLoggerInfo param, boolean optimizeContent,
			javax.xml.namespace.QName elementQName) throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetNoteLoggerInfo.MY_QNAME, factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteItems param, boolean optimizeContent,
			javax.xml.namespace.QName elementQName) throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(
					param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteItems.MY_QNAME, factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteItemsByQuery param, boolean optimizeContent,
			javax.xml.namespace.QName elementQName) throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteItemsByQuery.MY_QNAME, factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetFileAttachment param, boolean optimizeContent,
			javax.xml.namespace.QName elementQName) throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetFileAttachment.MY_QNAME, factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetAvailableTransitions param, boolean optimizeContent,
			javax.xml.namespace.QName elementQName) throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetAvailableTransitions.MY_QNAME, factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetTables param, boolean optimizeContent,
			javax.xml.namespace.QName elementQName) throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(
					param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetTables.MY_QNAME, factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.LinkSubtask param, boolean optimizeContent,
			javax.xml.namespace.QName elementQName) throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(
					param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.LinkSubtask.MY_QNAME, factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetStateChangeHistory param, boolean optimizeContent,
			javax.xml.namespace.QName elementQName) throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetStateChangeHistory.MY_QNAME, factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionItem param, boolean optimizeContent,
			javax.xml.namespace.QName elementQName) throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param
					.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionItem.MY_QNAME, factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetSubmitProjects param, boolean optimizeContent,
			javax.xml.namespace.QName elementQName) throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetSubmitProjects.MY_QNAME, factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetSolutions param, boolean optimizeContent,
			javax.xml.namespace.QName elementQName) throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param
					.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetSolutions.MY_QNAME, factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionItems param, boolean optimizeContent,
			javax.xml.namespace.QName elementQName) throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param
					.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionItems.MY_QNAME, factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetReports param, boolean optimizeContent,
			javax.xml.namespace.QName elementQName) throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(
					param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetReports.MY_QNAME, factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.RunReport param, boolean optimizeContent,
			javax.xml.namespace.QName elementQName) throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(
					param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.RunReport.MY_QNAME, factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetFieldSelections param, boolean optimizeContent,
			javax.xml.namespace.QName elementQName) throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetFieldSelections.MY_QNAME, factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.RunReportXml param, boolean optimizeContent,
			javax.xml.namespace.QName elementQName) throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param
					.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.RunReportXml.MY_QNAME, factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreatePrimaryItems param, boolean optimizeContent,
			javax.xml.namespace.QName elementQName) throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreatePrimaryItems.MY_QNAME, factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateNoteAttachment param, boolean optimizeContent,
			javax.xml.namespace.QName elementQName) throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateNoteAttachment.MY_QNAME, factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetApplications param, boolean optimizeContent,
			javax.xml.namespace.QName elementQName) throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param
					.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetApplications.MY_QNAME, factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteAttachment param, boolean optimizeContent,
			javax.xml.namespace.QName elementQName) throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param
					.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteAttachment.MY_QNAME, factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.IsUserValid param, boolean optimizeContent,
			javax.xml.namespace.QName elementQName) throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(
					param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.IsUserValid.MY_QNAME, factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateFileAttachment param, boolean optimizeContent,
			javax.xml.namespace.QName elementQName) throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateFileAttachment.MY_QNAME, factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateAuxItem param, boolean optimizeContent,
			javax.xml.namespace.QName elementQName) throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param
					.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateAuxItem.MY_QNAME, factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItems param, boolean optimizeContent,
			javax.xml.namespace.QName elementQName) throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(
					param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItems.MY_QNAME, factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreatePrimaryItem param, boolean optimizeContent,
			javax.xml.namespace.QName elementQName) throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreatePrimaryItem.MY_QNAME, factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItem param, boolean optimizeContent,
			javax.xml.namespace.QName elementQName) throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(
					param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItem.MY_QNAME, factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetAvailableSubmitTransitions param,
			boolean optimizeContent, javax.xml.namespace.QName elementQName) throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param.getOMElement(
					com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetAvailableSubmitTransitions.MY_QNAME, factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.Logout param, boolean optimizeContent,
			javax.xml.namespace.QName elementQName) throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(
					param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.Logout.MY_QNAME, factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateAuxItems param, boolean optimizeContent,
			javax.xml.namespace.QName elementQName) throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(param
					.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateAuxItems.MY_QNAME, factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetVersion param, boolean optimizeContent,
			javax.xml.namespace.QName elementQName) throws org.apache.axis2.AxisFault {

		try {

			org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
			emptyEnvelope.getBody().addChild(
					param.getOMElement(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetVersion.MY_QNAME, factory));
			return emptyEnvelope;
		} catch (org.apache.axis2.databinding.ADBException e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}

	}

	/* methods to provide back word compatibility */

	/**
	 * get the default envelope
	 */
	private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory) {
		return factory.getDefaultEnvelope();
	}

	private java.lang.Object fromOM(org.apache.axiom.om.OMElement param, java.lang.Class type)
			throws org.apache.axis2.AxisFault {

		try {

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.AEWebservicesFault.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.AEWebservicesFault.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateAuxItem.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateAuxItem.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateAuxItemResponse.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateAuxItemResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateAuxItems.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateAuxItems.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateAuxItemsResponse.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateAuxItemsResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateFileAttachment.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateFileAttachment.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateFileAttachmentResponse.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateFileAttachmentResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateNoteAttachment.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateNoteAttachment.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateNoteAttachmentResponse.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateNoteAttachmentResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreatePrimaryItem.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreatePrimaryItem.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreatePrimaryItemResponse.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreatePrimaryItemResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreatePrimaryItems.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreatePrimaryItems.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreatePrimaryItemsResponse.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreatePrimaryItemsResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteAttachment.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteAttachment.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteAttachmentResponse.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteAttachmentResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteItems.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteItems.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteItemsByQuery.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteItemsByQuery.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteItemsByQueryResponse.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteItemsByQueryResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteItemsResponse.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteItemsResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetApplications.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetApplications.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetApplicationsResponse.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetApplicationsResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetAvailableSubmitTransitions.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetAvailableSubmitTransitions.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetAvailableSubmitTransitionsResponse.class
					.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetAvailableSubmitTransitionsResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetAvailableTransitions.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetAvailableTransitions.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetAvailableTransitionsResponse.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetAvailableTransitionsResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetFieldSelections.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetFieldSelections.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetFieldSelectionsResponse.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetFieldSelectionsResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetFileAttachment.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetFileAttachment.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetFileAttachmentResponse.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetFileAttachmentResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItem.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItem.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItemResponse.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItemResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItems.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItems.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItemsByQuery.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItemsByQuery.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItemsByQueryResponse.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItemsByQueryResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItemsResponse.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItemsResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetNoteLoggerInfo.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetNoteLoggerInfo.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetNoteLoggerInfoResponse.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetNoteLoggerInfoResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetReports.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetReports.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetReportsResponse.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetReportsResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetSolutions.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetSolutions.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetSolutionsResponse.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetSolutionsResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetStateChangeHistory.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetStateChangeHistory.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetStateChangeHistoryResponse.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetStateChangeHistoryResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetSubmitProjects.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetSubmitProjects.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetSubmitProjectsResponse.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetSubmitProjectsResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetTables.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetTables.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetTablesResponse.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetTablesResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetUsers.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetUsers.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetUsersResponse.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetUsersResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetVersion.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetVersion.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetVersionResponse.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetVersionResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetWorkflows.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetWorkflows.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetWorkflowsResponse.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetWorkflowsResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.IsUserValid.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.IsUserValid.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.IsUserValidResponse.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.IsUserValidResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.LinkSubtask.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.LinkSubtask.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.LinkSubtaskResponse.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.LinkSubtaskResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.Logout.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.Logout.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.LogoutResponse.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.LogoutResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.RunReport.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.RunReport.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.RunReportResponse.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.RunReportResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.RunReportXml.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.RunReportXml.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.RunReportXmlResponse.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.RunReportXmlResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionItem.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionItem.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionItemResponse.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionItemResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionItems.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionItems.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionItemsResponse.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionItemsResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.UpdateFileAttachment.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.UpdateFileAttachment.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

			if (com.mitel.ka.kcapp.services.sbm.sbmappservices72.UpdateFileAttachmentResponse.class.equals(type)) {

				return com.mitel.ka.kcapp.services.sbm.sbmappservices72.UpdateFileAttachmentResponse.Factory
						.parse(param.getXMLStreamReaderWithoutCaching());

			}

		} catch (java.lang.Exception e) {
			throw org.apache.axis2.AxisFault.makeFault(e);
		}
		return null;
	}

}
