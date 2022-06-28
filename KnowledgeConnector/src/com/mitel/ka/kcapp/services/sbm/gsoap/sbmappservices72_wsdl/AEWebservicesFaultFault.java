
/**
 * AEWebservicesFaultFault.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.2  Built on : May 02, 2016 (05:55:18 BST)
 */

package com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl;

public class AEWebservicesFaultFault extends java.lang.Exception {

	private static final long serialVersionUID = 1464291602556L;

	private com.mitel.ka.kcapp.services.sbm.sbmappservices72.AEWebservicesFault faultMessage;

	public AEWebservicesFaultFault() {
		super("AEWebservicesFaultFault");
	}

	public AEWebservicesFaultFault(java.lang.String s) {
		super(s);
	}

	public AEWebservicesFaultFault(java.lang.String s, java.lang.Throwable ex) {
		super(s, ex);
	}

	public AEWebservicesFaultFault(java.lang.Throwable cause) {
		super(cause);
	}

	public void setFaultMessage(com.mitel.ka.kcapp.services.sbm.sbmappservices72.AEWebservicesFault msg) {
		faultMessage = msg;
	}

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.AEWebservicesFault getFaultMessage() {
		return faultMessage;
	}
}
