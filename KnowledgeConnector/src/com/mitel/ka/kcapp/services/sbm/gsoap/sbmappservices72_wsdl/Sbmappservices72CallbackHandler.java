
/**
 * Sbmappservices72CallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.2  Built on : May 02, 2016 (05:55:18 BST)
 */

package com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl;

/**
 * Sbmappservices72CallbackHandler Callback class, Users can extend this class
 * and implement their own receiveResult and receiveError methods.
 */
public abstract class Sbmappservices72CallbackHandler {

	protected Object clientData;

	/**
	 * User can pass in any object that needs to be accessed once the
	 * NonBlocking Web service call is finished and appropriate method of this
	 * CallBack is called.
	 * 
	 * @param clientData
	 *            Object mechanism by which the user can pass in user data that
	 *            will be avilable at the time this callback is called.
	 */
	public Sbmappservices72CallbackHandler(Object clientData) {
		this.clientData = clientData;
	}

	/**
	 * Please use this constructor if you don't want to set any clientData
	 */
	public Sbmappservices72CallbackHandler() {
		this.clientData = null;
	}

	/**
	 * Get the client data
	 */

	public Object getClientData() {
		return clientData;
	}

	/**
	 * auto generated Axis2 call back method for getWorkflows method override
	 * this method for handling normal response from getWorkflows operation
	 */
	public void receiveResultgetWorkflows(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetWorkflowsResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getWorkflows operation
	 */
	public void receiveErrorgetWorkflows(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for updateFileAttachment method
	 * override this method for handling normal response from
	 * updateFileAttachment operation
	 */
	public void receiveResultupdateFileAttachment(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.UpdateFileAttachmentResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from updateFileAttachment operation
	 */
	public void receiveErrorupdateFileAttachment(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getUsers method override this
	 * method for handling normal response from getUsers operation
	 */
	public void receiveResultgetUsers(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetUsersResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getUsers operation
	 */
	public void receiveErrorgetUsers(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getItemsByQuery method override
	 * this method for handling normal response from getItemsByQuery operation
	 */
	public void receiveResultgetItemsByQuery(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItemsByQueryResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getItemsByQuery operation
	 */
	public void receiveErrorgetItemsByQuery(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getNoteLoggerInfo method
	 * override this method for handling normal response from getNoteLoggerInfo
	 * operation
	 */
	public void receiveResultgetNoteLoggerInfo(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetNoteLoggerInfoResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getNoteLoggerInfo operation
	 */
	public void receiveErrorgetNoteLoggerInfo(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for deleteItems method override
	 * this method for handling normal response from deleteItems operation
	 */
	public void receiveResultdeleteItems(com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteItemsResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from deleteItems operation
	 */
	public void receiveErrordeleteItems(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for deleteItemsByQuery method
	 * override this method for handling normal response from deleteItemsByQuery
	 * operation
	 */
	public void receiveResultdeleteItemsByQuery(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteItemsByQueryResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from deleteItemsByQuery operation
	 */
	public void receiveErrordeleteItemsByQuery(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getFileAttachment method
	 * override this method for handling normal response from getFileAttachment
	 * operation
	 */
	public void receiveResultgetFileAttachment(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetFileAttachmentResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getFileAttachment operation
	 */
	public void receiveErrorgetFileAttachment(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getAvailableTransitions method
	 * override this method for handling normal response from
	 * getAvailableTransitions operation
	 */
	public void receiveResultgetAvailableTransitions(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetAvailableTransitionsResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getAvailableTransitions operation
	 */
	public void receiveErrorgetAvailableTransitions(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getTables method override this
	 * method for handling normal response from getTables operation
	 */
	public void receiveResultgetTables(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetTablesResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getTables operation
	 */
	public void receiveErrorgetTables(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for linkSubtask method override
	 * this method for handling normal response from linkSubtask operation
	 */
	public void receiveResultlinkSubtask(com.mitel.ka.kcapp.services.sbm.sbmappservices72.LinkSubtaskResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from linkSubtask operation
	 */
	public void receiveErrorlinkSubtask(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getStateChangeHistory method
	 * override this method for handling normal response from
	 * getStateChangeHistory operation
	 */
	public void receiveResultgetStateChangeHistory(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetStateChangeHistoryResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getStateChangeHistory operation
	 */
	public void receiveErrorgetStateChangeHistory(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for transitionItem method override
	 * this method for handling normal response from transitionItem operation
	 */
	public void receiveResulttransitionItem(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionItemResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from transitionItem operation
	 */
	public void receiveErrortransitionItem(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getSubmitProjects method
	 * override this method for handling normal response from getSubmitProjects
	 * operation
	 */
	public void receiveResultgetSubmitProjects(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetSubmitProjectsResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getSubmitProjects operation
	 */
	public void receiveErrorgetSubmitProjects(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getSolutions method override
	 * this method for handling normal response from getSolutions operation
	 */
	public void receiveResultgetSolutions(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetSolutionsResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getSolutions operation
	 */
	public void receiveErrorgetSolutions(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for transitionItems method override
	 * this method for handling normal response from transitionItems operation
	 */
	public void receiveResulttransitionItems(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionItemsResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from transitionItems operation
	 */
	public void receiveErrortransitionItems(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getReports method override this
	 * method for handling normal response from getReports operation
	 */
	public void receiveResultgetReports(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetReportsResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getReports operation
	 */
	public void receiveErrorgetReports(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for runReport method override this
	 * method for handling normal response from runReport operation
	 */
	public void receiveResultrunReport(com.mitel.ka.kcapp.services.sbm.sbmappservices72.RunReportResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from runReport operation
	 */
	public void receiveErrorrunReport(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getFieldSelections method
	 * override this method for handling normal response from getFieldSelections
	 * operation
	 */
	public void receiveResultgetFieldSelections(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetFieldSelectionsResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getFieldSelections operation
	 */
	public void receiveErrorgetFieldSelections(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for runReportXml method override
	 * this method for handling normal response from runReportXml operation
	 */
	public void receiveResultrunReportXml(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.RunReportXmlResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from runReportXml operation
	 */
	public void receiveErrorrunReportXml(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for createPrimaryItems method
	 * override this method for handling normal response from createPrimaryItems
	 * operation
	 */
	public void receiveResultcreatePrimaryItems(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreatePrimaryItemsResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from createPrimaryItems operation
	 */
	public void receiveErrorcreatePrimaryItems(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for createNoteAttachment method
	 * override this method for handling normal response from
	 * createNoteAttachment operation
	 */
	public void receiveResultcreateNoteAttachment(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateNoteAttachmentResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from createNoteAttachment operation
	 */
	public void receiveErrorcreateNoteAttachment(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getApplications method override
	 * this method for handling normal response from getApplications operation
	 */
	public void receiveResultgetApplications(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetApplicationsResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getApplications operation
	 */
	public void receiveErrorgetApplications(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for deleteAttachment method
	 * override this method for handling normal response from deleteAttachment
	 * operation
	 */
	public void receiveResultdeleteAttachment(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteAttachmentResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from deleteAttachment operation
	 */
	public void receiveErrordeleteAttachment(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for isUserValid method override
	 * this method for handling normal response from isUserValid operation
	 */
	public void receiveResultisUserValid(com.mitel.ka.kcapp.services.sbm.sbmappservices72.IsUserValidResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from isUserValid operation
	 */
	public void receiveErrorisUserValid(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for createFileAttachment method
	 * override this method for handling normal response from
	 * createFileAttachment operation
	 */
	public void receiveResultcreateFileAttachment(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateFileAttachmentResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from createFileAttachment operation
	 */
	public void receiveErrorcreateFileAttachment(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for createAuxItem method override
	 * this method for handling normal response from createAuxItem operation
	 */
	public void receiveResultcreateAuxItem(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateAuxItemResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from createAuxItem operation
	 */
	public void receiveErrorcreateAuxItem(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getItems method override this
	 * method for handling normal response from getItems operation
	 */
	public void receiveResultgetItems(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItemsResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getItems operation
	 */
	public void receiveErrorgetItems(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for createPrimaryItem method
	 * override this method for handling normal response from createPrimaryItem
	 * operation
	 */
	public void receiveResultcreatePrimaryItem(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreatePrimaryItemResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from createPrimaryItem operation
	 */
	public void receiveErrorcreatePrimaryItem(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getItem method override this
	 * method for handling normal response from getItem operation
	 */
	public void receiveResultgetItem(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItemResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getItem operation
	 */
	public void receiveErrorgetItem(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getAvailableSubmitTransitions
	 * method override this method for handling normal response from
	 * getAvailableSubmitTransitions operation
	 */
	public void receiveResultgetAvailableSubmitTransitions(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetAvailableSubmitTransitionsResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getAvailableSubmitTransitions operation
	 */
	public void receiveErrorgetAvailableSubmitTransitions(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for logout method override this
	 * method for handling normal response from logout operation
	 */
	public void receiveResultlogout(com.mitel.ka.kcapp.services.sbm.sbmappservices72.LogoutResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from logout operation
	 */
	public void receiveErrorlogout(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for createAuxItems method override
	 * this method for handling normal response from createAuxItems operation
	 */
	public void receiveResultcreateAuxItems(
			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateAuxItemsResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from createAuxItems operation
	 */
	public void receiveErrorcreateAuxItems(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for getVersion method override this
	 * method for handling normal response from getVersion operation
	 */
	public void receiveResultgetVersion(com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetVersionResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getVersion operation
	 */
	public void receiveErrorgetVersion(java.lang.Exception e) {
	}

}
