package com.hach.dataextraction.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONArray;
import org.json.JSONObject;

import com.hach.dataextraction.dao.TransactionDAO;
import com.hach.dataextraction.utils.common.Utilities;
import com.hach.dataextraction.utils.osvc.OSVCandKAWebServiceCaller;
import com.hach.dataextraction.vo.ChannelDetails;

public class GetChannelMasterDataExtractionImpl {

	private static Logger logger = Logger.getLogger(StartExtractionImpl.class);

	public static void main(String[] args) {
		// initialize loggers
		File f = new File(GetChannelMasterDataExtractionImpl.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		PropertyConfigurator.configure(f.getParentFile().getAbsolutePath()+"/log.properties");
		f=  null;
		try
		{
			TransactionDAO transactionDAO = new TransactionDAO();
			// BEFORE STARTING EXTRACTION - ENSURE ALL DB TABLES FOR CHANNEL IS CREATED IN DB
			String tableName="IM_CHANNEL_MASTER";
			boolean tableCreated = transactionDAO.checkTableExists(tableName, "CHANNEL");
			if(tableCreated==true)
			{
				logger.info("main :: Document Table Already Exists or Created Successfully. Proceed for Checking Channel Master Table.");
				/*
				 * PROCEED FOR CHANNEL TYPE OPERATIONS
				 */
				List<ChannelDetails> channelsList = null;
				boolean hasMore = true;
				int limit = 100;
				int offset = 0;
				OSVCandKAWebServiceCaller caller = new OSVCandKAWebServiceCaller();
				JSONObject content = null;
				JSONArray items = null;
				JSONObject item = null;
				JSONObject serviceObject = null;
				ChannelDetails channelDetails = null;
				while (hasMore)
				{
					// initialize Channels List
					channelsList = new ArrayList<ChannelDetails>();
					try
					{
						serviceObject = caller.callKAWebservice("/contentTypes?limit=" + limit + "&offset=" + offset);
						if(null!=serviceObject)
						{
							if(null!=serviceObject.get("CALL_STATUS") && serviceObject.get("CALL_STATUS").equals("SUCCESS"))
							{
								if(null!=serviceObject.getJSONObject("FINAL_DATA"))
								{
									content = serviceObject.getJSONObject("FINAL_DATA");
									if(null!=content)
									{
										hasMore = content.getBoolean("hasMore");
										// check for items
										items = content.getJSONArray("items");
										System.out.println("----------items :: ."+items.length());
										if(null!=items && items.length()>0)
										{
											// iterate each item and get the content specific details for the channel
											for (int i = 0; i < items.length(); i++)
											{
												item = items.getJSONObject(i);
												channelDetails = new ChannelDetails();
												try
												{
													// recordId
													channelDetails.setRecordId(item.getString("recordId"));
												}
												catch(Exception e) {}
												try
												{
													// referenceKey
													channelDetails.setRefKey(item.getString("referenceKey"));
												}
												catch(Exception e) {}
												try
												{
													// name
													channelDetails.setName(item.getString("name"));
												}
												catch(Exception e) {}


												item = null;
												// if channelDetails recordId is not null = add to vector
												if(null!=channelDetails && null!=channelDetails.getRecordId() && !"".equals(channelDetails.getRecordId()))
												{
													// add to List
													if(null==channelsList || channelsList.size()<=0)
													{
														channelsList = new ArrayList<ChannelDetails>();
													}
													channelsList.add(channelDetails);
												}
												channelDetails = null;
											}
										}
										items=null;
									}
									content = null;
								}
							}
							else
							{
								if(null!=serviceObject.get("ERROR_DATA"))
								{
									logger.info("main :: Failed to Invoke KA WebService :: >"+ serviceObject.getJSONObject("ERROR_DATA").toString());
								}
								else if(null!=serviceObject.get("ERROR_MESSAGE") && !"".equals(serviceObject.get("ERROR_MESSAGE")))
								{
									logger.info("main :: Failed to Invoke KA WebService :: >"+ serviceObject.get("ERROR_MESSAGE"));
								}
							}
						}
						serviceObject = null;
					}
					catch (Exception e)
					{
						Utilities.printStackTraceToLogs(StartExtractionImpl.class.getName(), "main()", e);
					}

					if(null!=channelsList && channelsList.size()>0)
					{
						logger.info("main :: Total Channels Found From OFFSET :: >"+ offset+" are :: >"+ channelsList.size());
						/*
						 * PROCEED FOR SAVING CHANNEL DETAILS IN DATABASE
						 */
						transactionDAO.saveChannelDetails(channelsList);
					}
					else
					{
						logger.info("main :: No Documents Found From OFFSET :: >"+ offset);
					}

					// increase offset value
					offset += limit;
					channelsList = null;
				}
			}
			else
			{
				logger.info("main :: Failed to check whether Table {"+tableName+"} Exists and Create if Not.");
			}
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(GetChannelMasterDataExtractionImpl.class.getName(), "main()", e);
		}
	}
	
	

}
