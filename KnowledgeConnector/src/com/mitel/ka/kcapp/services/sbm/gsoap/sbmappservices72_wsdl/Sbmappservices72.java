
/**
 * Sbmappservices72.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.2  Built on : May 02, 2016 (05:55:18 BST)
 */

package com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl;

/*
 *  Sbmappservices72 java interface
 */

public interface Sbmappservices72 {

	/**
	 * Auto generated method signature Returns project workflows graphical data.
	 * 
	 * @param getWorkflows0
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetWorkflowsResponse getWorkflows(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetWorkflows getWorkflows0) throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault;

	/**
	 * Auto generated method signature for Asynchronous Invocations Returns
	 * project workflows graphical data.
	 * 
	 * @param getWorkflows0
	 * 
	 */
	public void startgetWorkflows(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetWorkflows getWorkflows0,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature Updates an existing attachment, given
	 * item id, and the file attachment contents.
	 * 
	 * @param updateFileAttachment2
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.UpdateFileAttachmentResponse updateFileAttachment(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.UpdateFileAttachment updateFileAttachment2)
			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault;

	/**
	 * Auto generated method signature for Asynchronous Invocations Updates an
	 * existing attachment, given item id, and the file attachment contents.
	 * 
	 * @param updateFileAttachment2
	 * 
	 */
	public void startupdateFileAttachment(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.UpdateFileAttachment updateFileAttachment2,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature Returns user information one or more
	 * users. If getCurrentUser is true, then information about the current user
	 * is returned.
	 * 
	 * @param getUsers4
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetUsersResponse getUsers(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetUsers getUsers4) throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault;

	/**
	 * Auto generated method signature for Asynchronous Invocations Returns user
	 * information one or more users. If getCurrentUser is true, then
	 * information about the current user is returned.
	 * 
	 * @param getUsers4
	 * 
	 */
	public void startgetUsers(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetUsers getUsers4,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature Gets a list of existing items, given a
	 * table id, a query where clause, an order by clause (optional) and a
	 * maximum return list size.
	 * 
	 * @param getItemsByQuery6
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItemsByQueryResponse getItemsByQuery(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItemsByQuery getItemsByQuery6)
			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault;

	/**
	 * Auto generated method signature for Asynchronous Invocations Gets a list
	 * of existing items, given a table id, a query where clause, an order by
	 * clause (optional) and a maximum return list size.
	 * 
	 * @param getItemsByQuery6
	 * 
	 */
	public void startgetItemsByQuery(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItemsByQuery getItemsByQuery6,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature Returns note logger information.
	 * 
	 * @param getNoteLoggerInfo8
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetNoteLoggerInfoResponse getNoteLoggerInfo(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetNoteLoggerInfo getNoteLoggerInfo8)
			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault;

	/**
	 * Auto generated method signature for Asynchronous Invocations Returns note
	 * logger information.
	 * 
	 * @param getNoteLoggerInfo8
	 * 
	 */
	public void startgetNoteLoggerInfo(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetNoteLoggerInfo getNoteLoggerInfo8,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature Service definition of function
	 * ae__DeleteItems
	 * 
	 * @param deleteItems10
	 * 
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteItemsResponse deleteItems(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteItems deleteItems10) throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature for Asynchronous Invocations Service
	 * definition of function ae__DeleteItems
	 * 
	 * @param deleteItems10
	 * 
	 */
	public void startdeleteItems(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteItems deleteItems10,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature Deletes one or more items, given a table
	 * id and a query where clause.
	 * 
	 * @param deleteItemsByQuery12
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteItemsByQueryResponse deleteItemsByQuery(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteItemsByQuery deleteItemsByQuery12)
			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault;

	/**
	 * Auto generated method signature for Asynchronous Invocations Deletes one
	 * or more items, given a table id and a query where clause.
	 * 
	 * @param deleteItemsByQuery12
	 * 
	 */
	public void startdeleteItemsByQuery(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteItemsByQuery deleteItemsByQuery12,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature Gets an existing file attachment, given
	 * an item id and attachment id.
	 * 
	 * @param getFileAttachment14
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetFileAttachmentResponse getFileAttachment(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetFileAttachment getFileAttachment14)
			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault;

	/**
	 * Auto generated method signature for Asynchronous Invocations Gets an
	 * existing file attachment, given an item id and attachment id.
	 * 
	 * @param getFileAttachment14
	 * 
	 */
	public void startgetFileAttachment(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetFileAttachment getFileAttachment14,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature Return available transitions, given an
	 * item id and attribute name (may be null or empty).
	 * 
	 * @param getAvailableTransitions16
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetAvailableTransitionsResponse getAvailableTransitions(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetAvailableTransitions getAvailableTransitions16)
			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault;

	/**
	 * Auto generated method signature for Asynchronous Invocations Return
	 * available transitions, given an item id and attribute name (may be null
	 * or empty).
	 * 
	 * @param getAvailableTransitions16
	 * 
	 */
	public void startgetAvailableTransitions(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetAvailableTransitions getAvailableTransitions16,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature Gets the list of available tables,
	 * optionally filtered by solution and/or table type.
	 * 
	 * @param getTables18
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetTablesResponse getTables(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetTables getTables18) throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault;

	/**
	 * Auto generated method signature for Asynchronous Invocations Gets the
	 * list of available tables, optionally filtered by solution and/or table
	 * type.
	 * 
	 * @param getTables18
	 * 
	 */
	public void startgetTables(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetTables getTables18,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature Links one item to another as a sub-task.
	 * 
	 * @param linkSubtask20
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.LinkSubtaskResponse linkSubtask(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.LinkSubtask linkSubtask20) throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault;

	/**
	 * Auto generated method signature for Asynchronous Invocations Links one
	 * item to another as a sub-task.
	 * 
	 * @param linkSubtask20
	 * 
	 */
	public void startlinkSubtask(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.LinkSubtask linkSubtask20,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature Gets the state change history of an
	 * existing item, given a table id and internal item id.
	 * 
	 * @param getStateChangeHistory22
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetStateChangeHistoryResponse getStateChangeHistory(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetStateChangeHistory getStateChangeHistory22)
			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault;

	/**
	 * Auto generated method signature for Asynchronous Invocations Gets the
	 * state change history of an existing item, given a table id and internal
	 * item id.
	 * 
	 * @param getStateChangeHistory22
	 * 
	 */
	public void startgetStateChangeHistory(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetStateChangeHistory getStateChangeHistory22,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature Transitions existing item, given the the
	 * item id, plus any data to update, and transition id to use a non-default
	 * transition.
	 * 
	 * @param transitionItem24
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionItemResponse transitionItem(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionItem transitionItem24)
			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault;

	/**
	 * Auto generated method signature for Asynchronous Invocations Transitions
	 * existing item, given the the item id, plus any data to update, and
	 * transition id to use a non-default transition.
	 * 
	 * @param transitionItem24
	 * 
	 */
	public void starttransitionItem(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionItem transitionItem24,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature Gets the list of available projects
	 * available for submitting new items, optionally filtered by table id.
	 * 
	 * @param getSubmitProjects26
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetSubmitProjectsResponse getSubmitProjects(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetSubmitProjects getSubmitProjects26)
			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault;

	/**
	 * Auto generated method signature for Asynchronous Invocations Gets the
	 * list of available projects available for submitting new items, optionally
	 * filtered by table id.
	 * 
	 * @param getSubmitProjects26
	 * 
	 */
	public void startgetSubmitProjects(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetSubmitProjects getSubmitProjects26,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature Gets the list of available solutions.
	 * 
	 * @param getSolutions28
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetSolutionsResponse getSolutions(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetSolutions getSolutions28)
			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault;

	/**
	 * Auto generated method signature for Asynchronous Invocations Gets the
	 * list of available solutions.
	 * 
	 * @param getSolutions28
	 * 
	 */
	public void startgetSolutions(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetSolutions getSolutions28,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature Transitions existing items, given the
	 * item ids, plus any data to update, and transition id to use a non-default
	 * transition.
	 * 
	 * @param transitionItems30
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionItemsResponse transitionItems(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionItems transitionItems30)
			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault;

	/**
	 * Auto generated method signature for Asynchronous Invocations Transitions
	 * existing items, given the item ids, plus any data to update, and
	 * transition id to use a non-default transition.
	 * 
	 * @param transitionItems30
	 * 
	 */
	public void starttransitionItems(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.TransitionItems transitionItems30,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature Gets a filtered list of reports.
	 * 
	 * @param getReports32
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetReportsResponse getReports(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetReports getReports32) throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault;

	/**
	 * Auto generated method signature for Asynchronous Invocations Gets a
	 * filtered list of reports.
	 * 
	 * @param getReports32
	 * 
	 */
	public void startgetReports(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetReports getReports32,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature Runs a specified report.
	 * 
	 * @param runReport34
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.RunReportResponse runReport(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.RunReport runReport34) throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault;

	/**
	 * Auto generated method signature for Asynchronous Invocations Runs a
	 * specified report.
	 * 
	 * @param runReport34
	 * 
	 */
	public void startrunReport(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.RunReport runReport34,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature Returns full list of possible selections
	 * for single- or multi-selection field.
	 * 
	 * @param getFieldSelections36
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetFieldSelectionsResponse getFieldSelections(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetFieldSelections getFieldSelections36)
			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault;

	/**
	 * Auto generated method signature for Asynchronous Invocations Returns full
	 * list of possible selections for single- or multi-selection field.
	 * 
	 * @param getFieldSelections36
	 * 
	 */
	public void startgetFieldSelections(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetFieldSelections getFieldSelections36,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature Service definition of function
	 * ae__RunReportXml
	 * 
	 * @param runReportXml38
	 * 
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.RunReportXmlResponse runReportXml(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.RunReportXml runReportXml38)
			throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature for Asynchronous Invocations Service
	 * definition of function ae__RunReportXml
	 * 
	 * @param runReportXml38
	 * 
	 */
	public void startrunReportXml(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.RunReportXml runReportXml38,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature Creates new item(s), given a project and
	 * item data. Creates the item(s) as sub-items if a parent item is
	 * specified.
	 * 
	 * @param createPrimaryItems40
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreatePrimaryItemsResponse createPrimaryItems(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreatePrimaryItems createPrimaryItems40)
			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault;

	/**
	 * Auto generated method signature for Asynchronous Invocations Creates new
	 * item(s), given a project and item data. Creates the item(s) as sub-items
	 * if a parent item is specified.
	 * 
	 * @param createPrimaryItems40
	 * 
	 */
	public void startcreatePrimaryItems(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreatePrimaryItems createPrimaryItems40,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature attaches note.
	 * 
	 * @param createNoteAttachment42
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateNoteAttachmentResponse createNoteAttachment(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateNoteAttachment createNoteAttachment42)
			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault;

	/**
	 * Auto generated method signature for Asynchronous Invocations attaches
	 * note.
	 * 
	 * @param createNoteAttachment42
	 * 
	 */
	public void startcreateNoteAttachment(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateNoteAttachment createNoteAttachment42,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature Gets the list of available applications.
	 * 
	 * @param getApplications44
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetApplicationsResponse getApplications(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetApplications getApplications44)
			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault;

	/**
	 * Auto generated method signature for Asynchronous Invocations Gets the
	 * list of available applications.
	 * 
	 * @param getApplications44
	 * 
	 */
	public void startgetApplications(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetApplications getApplications44,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature Deletes an existing attachment, which may
	 * be a note, item link, URL attachment or file attachment, given an
	 * attachment id.
	 * 
	 * @param deleteAttachment46
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteAttachmentResponse deleteAttachment(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteAttachment deleteAttachment46)
			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault;

	/**
	 * Auto generated method signature for Asynchronous Invocations Deletes an
	 * existing attachment, which may be a note, item link, URL attachment or
	 * file attachment, given an attachment id.
	 * 
	 * @param deleteAttachment46
	 * 
	 */
	public void startdeleteAttachment(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.DeleteAttachment deleteAttachment46,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature Determine if the specified user is valid.
	 * 
	 * @param isUserValid48
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.IsUserValidResponse isUserValid(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.IsUserValid isUserValid48) throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault;

	/**
	 * Auto generated method signature for Asynchronous Invocations Determine if
	 * the specified user is valid.
	 * 
	 * @param isUserValid48
	 * 
	 */
	public void startisUserValid(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.IsUserValid isUserValid48,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature Creates a new attachment, given item id
	 * of the item to which it is to be attached, and the file attachment
	 * contents.
	 * 
	 * @param createFileAttachment50
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateFileAttachmentResponse createFileAttachment(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateFileAttachment createFileAttachment50)
			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault;

	/**
	 * Auto generated method signature for Asynchronous Invocations Creates a
	 * new attachment, given item id of the item to which it is to be attached,
	 * and the file attachment contents.
	 * 
	 * @param createFileAttachment50
	 * 
	 */
	public void startcreateFileAttachment(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateFileAttachment createFileAttachment50,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature Creates a new aux item, given a table id
	 * and item data.
	 * 
	 * @param createAuxItem52
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateAuxItemResponse createAuxItem(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateAuxItem createAuxItem52)
			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault;

	/**
	 * Auto generated method signature for Asynchronous Invocations Creates a
	 * new aux item, given a table id and item data.
	 * 
	 * @param createAuxItem52
	 * 
	 */
	public void startcreateAuxItem(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateAuxItem createAuxItem52,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature Gets items, given a list of item ids.
	 * 
	 * @param getItems54
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItemsResponse getItems(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItems getItems54) throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault;

	/**
	 * Auto generated method signature for Asynchronous Invocations Gets items,
	 * given a list of item ids.
	 * 
	 * @param getItems54
	 * 
	 */
	public void startgetItems(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItems getItems54,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature Creates a new item, given a project and
	 * item data. Creates the item as a sub-item if a parent item is specified.
	 * 
	 * @param createPrimaryItem56
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreatePrimaryItemResponse createPrimaryItem(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreatePrimaryItem createPrimaryItem56)
			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault;

	/**
	 * Auto generated method signature for Asynchronous Invocations Creates a
	 * new item, given a project and item data. Creates the item as a sub-item
	 * if a parent item is specified.
	 * 
	 * @param createPrimaryItem56
	 * 
	 */
	public void startcreatePrimaryItem(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreatePrimaryItem createPrimaryItem56,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature Gets item, given an item id.
	 * 
	 * @param getItem58
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItemResponse getItem(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItem getItem58) throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault;

	/**
	 * Auto generated method signature for Asynchronous Invocations Gets item,
	 * given an item id.
	 * 
	 * @param getItem58
	 * 
	 */
	public void startgetItem(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetItem getItem58,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature Return available Submit transitions,
	 * given an item id and attribute name (may be null or empty).
	 * 
	 * @param getAvailableSubmitTransitions60
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetAvailableSubmitTransitionsResponse getAvailableSubmitTransitions(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetAvailableSubmitTransitions getAvailableSubmitTransitions60)
			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault;

	/**
	 * Auto generated method signature for Asynchronous Invocations Return
	 * available Submit transitions, given an item id and attribute name (may be
	 * null or empty).
	 * 
	 * @param getAvailableSubmitTransitions60
	 * 
	 */
	public void startgetAvailableSubmitTransitions(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetAvailableSubmitTransitions getAvailableSubmitTransitions60,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature Logs out the current active session,
	 * releasing license.
	 * 
	 * @param logout62
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.LogoutResponse logout(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.Logout logout62) throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault;

	/**
	 * Auto generated method signature for Asynchronous Invocations Logs out the
	 * current active session, releasing license.
	 * 
	 * @param logout62
	 * 
	 */
	public void startlogout(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.Logout logout62,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature Service definition of function
	 * ae__CreateAuxItems
	 * 
	 * @param createAuxItems64
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateAuxItemsResponse createAuxItems(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateAuxItems createAuxItems64)
			throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault;

	/**
	 * Auto generated method signature for Asynchronous Invocations Service
	 * definition of function ae__CreateAuxItems
	 * 
	 * @param createAuxItems64
	 * 
	 */
	public void startcreateAuxItems(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.CreateAuxItems createAuxItems64,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException;

	/**
	 * Auto generated method signature Gets the server version.
	 * 
	 * @param getVersion66
	 * 
	 * @throws com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault
	 *             :
	 */

	public com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetVersionResponse getVersion(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetVersion getVersion66) throws java.rmi.RemoteException

			, com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.AEWebservicesFaultFault;

	/**
	 * Auto generated method signature for Asynchronous Invocations Gets the
	 * server version.
	 * 
	 * @param getVersion66
	 * 
	 */
	public void startgetVersion(

			com.mitel.ka.kcapp.services.sbm.sbmappservices72.GetVersion getVersion66,

			final com.mitel.ka.kcapp.services.sbm.gsoap.sbmappservices72_wsdl.Sbmappservices72CallbackHandler callback)

			throws java.rmi.RemoteException;

	//
}
