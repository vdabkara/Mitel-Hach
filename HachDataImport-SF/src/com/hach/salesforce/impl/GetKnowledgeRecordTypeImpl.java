package com.hach.salesforce.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONObject;

import com.hach.salesforce.dao.TransactionDAO;
import com.hach.salesforce.utils.common.Utilities;
import com.hach.salesforce.utils.sfop.SalesForceAPICaller;
import com.hach.salesforce.utils.sfop.SalesForceConstants;
import com.hach.salesforce.vo.ChannelDetails;


public class GetKnowledgeRecordTypeImpl {


	private Logger logger = Logger.getLogger(GetKnowledgeRecordTypeImpl.class);

	private TransactionDAO transactionDAO = null;
	
	private SalesForceAPICaller caller = null;

	public static void main(String[] args) {
		// initialize loggers
		File f = new File(GetKnowledgeRecordTypeImpl.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		PropertyConfigurator.configure(f.getParentFile().getAbsolutePath()+"/log.properties");
		f=  null;

		GetKnowledgeRecordTypeImpl impl = new GetKnowledgeRecordTypeImpl();
		impl.fetchChannelOperation();
	}

	public void fetchChannelOperation()
	{
		try
		{
			transactionDAO = new TransactionDAO();
			caller = new SalesForceAPICaller();
			
			String tableName="SF_CHANNEL_MASTER";
			boolean tableCreated = transactionDAO.checkTableExists(tableName, "CHANNEL");
			if(tableCreated==true)
			{
				logger.info("fetchChannelOperation :: Channel Master Table Already Exists or Created Successfully. Proceed for Fetching Data.");
				/*
				 * FETCH CHANNELS (RECORD TYPES) LIST FROM SALES FORCE.
				 */
				List<ChannelDetails> channelsList = new ArrayList<ChannelDetails>();
				ChannelDetails details = null;
				
				JSONObject responseObj = caller.callSFRestAPI("describe", SalesForceConstants.METHOD_GET, null);
				if(null!=responseObj)
				{
					if(null!=responseObj.getString("CALL_STATUS") && responseObj.getString("CALL_STATUS").equals("SUCCESS"))
					{
						JSONObject dataObject = responseObj.getJSONObject("FINAL_DATA");
						if(null!=dataObject && null!=dataObject.getJSONArray("recordTypeInfos") && dataObject.getJSONArray("recordTypeInfos").length()>0)
						{
							JSONObject recordTypeObj = null;
							for(int a=0;a<dataObject.getJSONArray("recordTypeInfos").length();a++)
							{
								try
								{
									recordTypeObj = (JSONObject)dataObject.getJSONArray("recordTypeInfos").get(a);
									if(null!=recordTypeObj && recordTypeObj.getBoolean("active")==true)
									{
										details = new ChannelDetails();
										try
										{
											// set Name
											details.setName(recordTypeObj.getString("developerName"));
										}
										catch(Exception e) {}

										try
										{
											// set RecordTypeId
											details.setRecordId(recordTypeObj.getString("recordTypeId"));
										}
										catch(Exception e) {}

										if(null!=details.getRecordId() && !"".equals(details.getRecordId()))
										{
											// add to list
											if(null==channelsList || channelsList.size()<=0)
											{
												channelsList = new ArrayList<ChannelDetails>();
											}
											channelsList.add(details);
										}
										details = null;
									}
									recordTypeObj  =null;
								}
								catch(Exception e)
								{
									Utilities.printStackTraceToLogs(GetKnowledgeRecordTypeImpl.class.getName(), "fetchChannelOperation()", e);
								}
							}
						}
						dataObject  = null;
					}
					else
					{
						logger.info("fetchChannelOperation ::  Failed to retrieve Knowledge Record Types Data from SalesForce. Please refer logs.");
					}
				}
				responseObj = null;


				/*
				 * START SAVING SALES FORCE CHANNELS LIST IN DATABASE
				 */
				if(null!=channelsList && channelsList.size()>0)
				{
					logger.info("fetchChannelOperation :: Total Knowledge Record Types data found from Sales Force are :: >"+ channelsList.size());
					try
					{
						transactionDAO.saveChannelDetails(channelsList);
					}
					catch(Exception e)
					{
						Utilities.printStackTraceToLogs(GetKnowledgeRecordTypeImpl.class.getName(), "fetchChannelOperation()", e);
					}
				}
				else
				{
					logger.info("fetchChannelOperation :: No Knowledge Record Types data found from Sales Force.");
				}
			}
			else
			{
				logger.info("fetchChannelOperation :: Failed to check whether Table {"+tableName+"} Exists and Create if Not.");
			}
			tableName = null;
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(GetKnowledgeRecordTypeImpl.class.getName(), "fetchChannelOperation()", e);
		}
		finally
		{
			try
			{
				if(null!=transactionDAO.conn)
				{
					transactionDAO.conn.close();
				}
			}
			catch(Exception e)
			{
				Utilities.printStackTraceToLogs(GetKnowledgeRecordTypeImpl.class.getName(), "fetchChannelOperation()", e);
			}
			transactionDAO= null;
			caller = null;
		}
	}

}
