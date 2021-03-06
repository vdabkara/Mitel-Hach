package com.hach.salesforce.impl;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.hach.salesforce.dao.TransactionDAO;
import com.hach.salesforce.utils.common.ApplicationProperties;
import com.hach.salesforce.utils.common.GenerateSFChannelPayloadUtils;
import com.hach.salesforce.utils.common.Utilities;
import com.hach.salesforce.utils.sfop.SalesForceAPICaller;
import com.hach.salesforce.utils.sfop.SalesForceConstants;
import com.hach.salesforce.vo.AttachmentDetails;
import com.hach.salesforce.vo.CategoryDetails;
import com.hach.salesforce.vo.ContentDetails;
import com.hach.salesforce.vo.ManualLinkDetails;

public class StartMigrationImpl {

	private Logger logger = Logger.getLogger(StartMigrationImpl.class);

	private TransactionDAO transactionDAO = null;

	private SalesForceAPICaller caller = null;

	private GenerateSFChannelPayloadUtils payloadUtils = null;

	private Writer writer = null;

	private PrintWriter print = null;
	
	private String translationAPIURL=ApplicationProperties.getProperty("translation.api.url");
	
	private String categoryAPIURL=ApplicationProperties.getProperty("category.api.url");
	
	private String publishMasterArticleURL=ApplicationProperties.getProperty("publish.api.master.url");
	
	private String publishTranslationArticleURL=ApplicationProperties.getProperty("publish.api.translation.url");
	
	private JSONArray lengthArray = null;

	public static void main(String[] args) {
		// initialize loggers
		File f = new File(StartMigrationImpl.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		PropertyConfigurator.configure(f.getParentFile().getAbsolutePath()+"/log.properties");
		f=  null;
		try
		{
			StartMigrationImpl impl = new StartMigrationImpl();
			// PROCESS ALL MASTER IDENTIFIERS
//			impl.startMasterIdentifiersProcessing();
//			 PROCESS ALL TRANSLATIONS
//			impl.startTranslationsProcessing();
			// PROCESS ALL INNERLINKS
//			impl.startInnerLinksProcessing();
			
			/*
			 * re-run for Manual Links as well
			 * idetnify all failure documents in manul links table and re-process those specific docs
			 */
		
			
			// PROCESS ALL CATEGORY UNTAGGING
//			impl.startCategoryUntagging();
			
			// PROCESS ALL CATEGORY TAGGING
//			impl.startCategoryTagging();
			
			// PUBLISH ALL TRANSLATIONS
//			impl.startTranslationsPublishProcessing();
			// PUBLISH ALL MASTER IDENTIFIERS
			impl.startMasterIdentifiersPublishProcessing();
			
			// UNPUBLISH ALL TRANSLATIONS
//			impl.startTranslationsUnPublishProcessing();
			// UNPUBLISH ALL MASTER IDENTIFIETS
//			impl.startMasterIdentifiersUnPublishProcessing();
			
			/*
			 * PRINT LENGTH ARRAY IN AN CSV FILE
			 */
//			if(null!=impl.lengthArray && impl.lengthArray.length()>0)
//			{
//				StringBuilder str = new StringBuilder();
//				str.append("DOCUMENTID,LOCALE,Title,Summary__c,Answer__c,Internal_Notes__c,Answer_2__c,Answer_3__c,Answer_4__c,Links__c,Manual_Links__c");
//				str.append(System.getProperty("line.separator"));
//				
//				JSONObject obj = null;
//				for(int a=0;a<impl.lengthArray.length();a++)
//				{
//					obj = (JSONObject)impl.lengthArray.get(a);
//					str.append(obj.getString("DocumentId")+","+obj.getString("Locale")+","+obj.getInt("Title")+",");
//					str.append(obj.getInt("Summary__c")+","+obj.getInt("Answer__c")+","+obj.getInt("Internal_Notes__c")+",");
//					str.append(obj.getInt("Answer_2__c")+","+obj.getInt("Answer_3__c")+","+obj.getInt("Answer_4__c")+",");
//					str.append(obj.getInt("Links__c")+","+obj.getInt("Manual_Links__c"));
//					str.append(System.getProperty("line.separator"));
//					obj = null;
//				}
//				
//				if(null!=str)
//				{
//					File output = new File("D:\\HACH_WD\\FieldsLength.csv");
//					FileOutputStream fos = new FileOutputStream(output);
//					fos.write(str.toString().getBytes());
//					fos.flush();
//					fos.close();
//					output = null;
//				}
//				str=  null;
//			}
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "main()", e);
		}
	}
	
	private void startMasterIdentifiersProcessing()
	{
		try
		{
			transactionDAO = new TransactionDAO();
			caller = new SalesForceAPICaller();
			payloadUtils = new GenerateSFChannelPayloadUtils();
			List<String> channelList = transactionDAO.getIMChannelDetails();
			if(null!=channelList && channelList.size()>0)
			{
				String channelRefKey=null;
				for(int at=0;at<channelList.size();at++)
				{
					channelRefKey=channelList.get(at).toString();
					/*
					 * GET SF CHANNEL RECORD TYPE ID
					 */
					String sfChannelRecordTypeId=transactionDAO.getSFChannelRecordTypeId(channelRefKey);
					/*
					 * FETCH DATA FROM IM CHANNEL TABLE FOR THE PROCESSING LOCALE
					 * AND START IDENTIFYING IMAGES & LINKS & ATTACHMENTS
					 * AND THEN START CREATING THEM IN SALESFORCE
					 * 
					 * ONLY MASTER IDENTIFIERS FIRST
					 */
					ContentDetails details = null;
					List<CategoryDetails> categoryList = null;
					List<ContentDetails> masterIdentifiersList = transactionDAO.getIMDocumentsList(channelRefKey,"MASTERS");
					if(null!=masterIdentifiersList && masterIdentifiersList.size()>0)
					{
						logger.info("startMasterIdentifiersProcessing :: Total Master Identifiers Found are :: >"+ masterIdentifiersList.size());
						JSONObject contentObj = null;
						String xmlData=null;
						String metaData = null;
						JSONObject salesForceObj = null;
						List<AttachmentDetails> attachmentsList = null;
						AttachmentDetails attachmentDetails = null;
						List<AttachmentDetails> innerLinksList=null;
						List<AttachmentDetails> imagesList = null;
						String htmlContent=null;
						JSONObject responseObject = null;
						JSONObject getResponseObject = null;
						List<ManualLinkDetails> manualLinksList = null;
						ManualLinkDetails manualLinkDetails = null;
						String manualLinkContent=null;
						for(int a=0;a<masterIdentifiersList.size();a++)
						{
							details = (ContentDetails)masterIdentifiersList.get(a);
							// default value of execution method to be update - so that error message gets tracked
							details.setExecutionMethod("UPDATE");
							logger.info("startMasterIdentifiersProcessing :: ######## Starting Master Identifier "+(a+1)+" / "+ masterIdentifiersList.size()+" ############");
							try
							{
								contentObj= transactionDAO.getIMDocumentJSONObject(channelRefKey, details.getAutoId());
								if(null!=contentObj)
								{
									// SALES FORCE CHANNEL ID
									details.setSfChannelId(sfChannelRecordTypeId);
									
									// SCHEMA XML - APPLY A TRY CATCH AS CUSTOM MESSAGE ALREADY TRACKED BELOW IF XML DATA NODE IS NULL
									try
									{
										xmlData = contentObj.getString("xml");
									}
									catch(Exception e) {
										// DO NOTHING IN THIS CASE
									}
									
									// METADATA XML - APPLY A TRY CATCH, AS MANY CHANNELS DO NOT HAVE THIS NODE
									try
									{
										metaData = contentObj.getString("metaDataXml");
									}
									catch(Exception e) {
										// DO NOTHING IN THIS CASE
									}
									/*
									 * PROCEED ONLY WHEN XML IS NOT NULL
									 */
									if(null!=xmlData && !"".equals(xmlData))
									{
										salesForceObj= new JSONObject();
										
										/*
										 * PERFORM TRANSACTION FOR IDENTIFYING OKM INNERLINKS
										 * AND TRACE THEM IN DATABASE.
										 */
										
										
										
										
										/*
										 * RETRIEVE INLINE IMAGES DATA FOR DOCUMENT ID & LOCALE
										 */
										imagesList = transactionDAO.getDocumentInlineImages(channelRefKey, details.getDocumentId(), details.getLocale());

										/*
										 * RETRIEVE INNERLINKS DATA FOR DOCUMENT ID & LOCALE
										 */
										innerLinksList = transactionDAO.getDocumentInnerLinks(channelRefKey, details.getDocumentId(), details.getLocale());

										/*
										 * PERFORM INNERLINKS TRACKING AND REPLACE OPERATION FOR SALES FORCE
										 */
										xmlData = payloadUtils.performInnerLinksOperation(xmlData, innerLinksList, details.getDocumentId(), details.getLocale(), transactionDAO);
										
										/*
										 * PERFORM OKM INNERLINKS TRACKING REPLACE OPERATION FOR SALES FORCE
										 */
										xmlData = payloadUtils.performOKMInnerLinksOperation(xmlData, details.getDocumentId(), details.getLocale(), transactionDAO, channelRefKey);
										
										/*
										 * PERFORM INLINE IMAGES REPLACE OPERATION FOR SALES FORCE
										 */
										xmlData= payloadUtils.performInlineImagesOperation(xmlData, imagesList, details.getDocumentId(), details.getAnswerId());

										/*
										 * RETRIEVE MANUAL LINKS DATA FOR DOCUMENT ID & LOCALE
										 */
										manualLinksList = transactionDAO.getDocumentManualLinks(details.getDocumentId(), details.getLocale());
										
										/*
										 * START PREPARING PAYLOADS
										 * CHANNEL WISE
										 */
										salesForceObj = payloadUtils.createPayload(xmlData, channelRefKey);

										/*
										 * COMMON CODE FOR ATTACHMENTS FOR ALL CHANNELS
										 * RETRIEVE ALL ATTACHMENTS (E.G NODES DATA FOR DOCUMENT  / LOCALE)
										 * CREATE HTML FOR THOSE ATTACHMENTS
										 */
										htmlContent="";
										attachmentsList = transactionDAO.getDocumentAttachments(channelRefKey, details.getDocumentId(), details.getLocale());
										if(null!=attachmentsList && attachmentsList.size()>0)
										{
											for(int b=0;b<attachmentsList.size();b++)
											{
												attachmentDetails  =(AttachmentDetails)attachmentsList.get(b);
												if(null!=attachmentDetails.getCdnUrl() && !"".equals(attachmentDetails.getCdnUrl()))
												{
													htmlContent+="<a href=\""+attachmentDetails.getCdnUrl()+"\" target=\"_blank\">";
												}
												else
												{
													htmlContent+="<a href=\"#\">";
												}
												// add attachmentName
												htmlContent+=attachmentDetails.getAttachmentName();
												htmlContent+="</a><br/>";
												attachmentDetails = null;
											}
										}

										// set attachmentData for field Links__c in salesForceObj Payload Object
										salesForceObj.put("Links__c", htmlContent);
										// set length as well 
										payloadUtils.getLengthObj().put("Links__c", htmlContent.length());
										htmlContent = null;
										attachmentDetails = null;
										attachmentsList = null;
										
										/*
										 * COMMON CODE FOR ALL MANUAL LINKS
										 */
										manualLinkContent = "";
										if(null!=manualLinksList && manualLinksList.size()>0)
										{
											manualLinkDetails = null;
											for(int f=0;f<manualLinksList.size();f++)
											{
												manualLinkDetails= (ManualLinkDetails)manualLinksList.get(f);
												if(null!=manualLinkDetails.getSfRecordId() && !"".equals(manualLinkDetails.getSfRecordId()))
												{
													// instead of document id & article number add - Manual Link Title
													// new URL Link pattern - /lightning/r/Knowledge__kav/ka08G000000CoeHQAS/view
													manualLinkContent+="<a href=\"/lightning/r/Knowledge__kav/"+manualLinkDetails.getSfRecordId()+"/view"+"\">";
													
													if(null!=manualLinkDetails.getTitle() && !"".equals(manualLinkDetails.getTitle()))
													{
														manualLinkContent+=manualLinkDetails.getTitle();
													}
													else
													{
														// add document id
														manualLinkContent+=manualLinkDetails.getDocumentId();
														if(null!=manualLinkDetails.getSfArticleNumber() && !"".equals(manualLinkDetails.getSfArticleNumber()))
														{
															manualLinkContent+=" - "+manualLinkDetails.getSfArticleNumber();
														}
													}
													manualLinkContent+="</a><br/>";
												}
												else
												{
													manualLinkContent+="<a href=\"#\">";
													if(null!=manualLinkDetails.getTitle() && !"".equals(manualLinkDetails.getTitle()))
													{
														manualLinkContent+=manualLinkDetails.getTitle();
													}
													else
													{
														manualLinkContent+=manualLinkDetails.getDocumentId();
													}
													manualLinkContent+="</a><br/>";
												}
												manualLinkDetails = null;
											}
										}
										
										// set manualLinks Data for field Manual_Links__c in salesForceObj Payload Object
										salesForceObj.put("Manual_Links__c", manualLinkContent);
										// set length as well 
										payloadUtils.getLengthObj().put("Manual_Links__c", manualLinkContent.length());
										
										manualLinkContent = null;
										manualLinkDetails = null;
										
										
										// add DocumentId & Locale to lengthObj
										payloadUtils.getLengthObj().put("DocumentId", details.getDocumentId());
										payloadUtils.getLengthObj().put("Locale", details.getLocale());
										if(null==lengthArray || lengthArray.length()<=0)
										{
											lengthArray = new JSONArray();
										}
										lengthArray.put(payloadUtils.getLengthObj());
										payloadUtils.setLengthObj(null);
										
										/*
										 * COMMON FIELDS OPERATION
										 */
										salesForceObj = payloadUtils.performCommonFieldsOperation(details, salesForceObj, sfChannelRecordTypeId);


										/*
										 * META DATA TAG OPERATION
										 */
										if(null!=metaData && !"".equals(metaData) && !metaData.toLowerCase().equals("<META/>".toLowerCase()))
										{
											salesForceObj = metaDataOperation(metaData, salesForceObj);
										}

										/*
										 * SALES FORCE CREATE / UPDATE OPERATION
										 */
										if(null!=salesForceObj)
										{
											if(null!=details.getSfRecordId() && !"".equals(details.getSfRecordId()))
											{
												// perform update Operation in Sales Force
												details = updateSFDocumentOperation(details, responseObject, salesForceObj, getResponseObject);
												// set EXECUTION METHOD AS UPDATE
												details.setExecutionMethod("UPDATE");
												if(null!=details.getSfRecordId() && !"".equals(details.getSfRecordId()))
												{
													/*
													 * RETRIEVE CATEGORY LIST FOR THE PROCESSED DOCUMENT
													 */
													try
													{
														categoryList = transactionDAO.getDocumentCategoryList(channelRefKey, details.getDocumentId(), details.getLocale(),"ADD");
														if(null!=categoryList && categoryList.size()>0)
														{
															logger.info("startMasterIdentifiersProcessing :: Total Categories found for "+details.getDocumentId()+" of "+details.getLocale()+" are :: >"+ categoryList.size());
															/*
															 * START CATEGORY PROCESSING
															 */
															categoryList = addSFCategoriesOperation(details, categoryList);
														}
														else
														{
															logger.info("startMasterIdentifiersProcessing :: Category Processing Not Required, as No Categories found for "+details.getDocumentId()+" of "+details.getLocale());
														}
													}
													catch(Exception e)
													{
														Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "startMasterIdentifiersProcessing()", e);
														// capture error Message
														details = captureErrorMessage(details, "Failed to fetch Categories Data for Processing Document from DB.", e);
													}
												}
											}
											else
											{
												// set EXECUTION METHOD AS CREATE
												details.setExecutionMethod("CREATE");
												// perform create Operation in Sales Force
												details = createSFDocumentOperation(details, responseObject, salesForceObj, getResponseObject);
												if(null!=details.getSfRecordId() && !"".equals(details.getSfRecordId()))
												{
													/*
													 * RETRIEVE CATEGORY LIST FOR THE PROCESSED DOCUMENT
													 */
													try
													{
														categoryList = transactionDAO.getDocumentCategoryList(channelRefKey, details.getDocumentId(), details.getLocale(),"ADD");
														if(null!=categoryList && categoryList.size()>0)
														{
															logger.info("startMasterIdentifiersProcessing :: Total Categories found for "+details.getDocumentId()+" of "+details.getLocale()+" are :: >"+ categoryList.size());
															/*
															 * START CATEGORY PROCESSING
															 */
															categoryList = addSFCategoriesOperation(details, categoryList);
														}
														else
														{
															logger.info("startMasterIdentifiersProcessing :: Category Processing Not Required, as No Categories found for "+details.getDocumentId()+" of "+details.getLocale());
														}
													}
													catch(Exception e)
													{
														Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "startMasterIdentifiersProcessing()", e);
														// capture error Message
														details = captureErrorMessage(details, "Failed to fetch Categories Data for Processing Document from DB.", e);
													}
												}
											}
										}
										salesForceObj = null;
									}
									else
									{
										// capture error Message
										details = captureErrorMessage(details, "Schema Data for the KA Document is NULL.", null);
									}
									xmlData=  null;
									metaData = null;
								}
							}
							catch(Exception e)
							{
								Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "startMasterIdentifiersProcessing()", e);
								// capture error Message
								details = captureErrorMessage(details, "Please refer Error Message, either KA Parsing or Sales Force Processing Failed.", e);
							}
							
							/*
							 * PROCEED FOR SAVING THE DOCUMENT IN DATABASE WITH SALES FORCE OPERATION
							 * ALSO UPDATE THE INNERLINKS DETAILS WITH STATUS
							 */
							transactionDAO.updateSFTransactionDetails(details, payloadUtils.getAllInnerLinksList(), channelRefKey, categoryList, manualLinksList);
							
							// set INNERLINKS LIST TO NULL
							payloadUtils.setAllInnerLinksList(null);
							
							// set CATEGORY LIST TO NULL
							categoryList=  null;
							
							details = null;
							contentObj = null;
							xmlData=null;
							metaData = null;
							salesForceObj = null;
							attachmentsList = null;
							attachmentDetails = null;
							innerLinksList=null;
							imagesList = null;
							htmlContent=null;
							responseObject = null;
							getResponseObject = null;
							manualLinksList=  null;
							logger.info("startMasterIdentifiersProcessing :: ######## Ending Master Identifier "+(a+1)+" / "+ masterIdentifiersList.size()+" ############");
						}
					}
					masterIdentifiersList = null;
					channelRefKey  =null;
				}
			}
			channelList  =null;
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "startMasterIdentifiersProcessing()", e);
		}
		finally
		{
			try
			{
				if(null!=transactionDAO.conn)
				{
					transactionDAO.conn.close();
				}
				transactionDAO.conn  =null;
			}
			catch(Exception e)
			{
				Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "startMasterIdentifiersProcessing()", e);
			}
			transactionDAO= null;
			caller = null;
			payloadUtils = null;
		}
	}

	private void startTranslationsProcessing()
	{
		try
		{
			transactionDAO = new TransactionDAO();
			caller = new SalesForceAPICaller();
			payloadUtils = new GenerateSFChannelPayloadUtils();
			List<String> channelList = transactionDAO.getIMChannelDetails();
			if(null!=channelList && channelList.size()>0)
			{
				String channelRefKey=null;
				for(int at=0;at<channelList.size();at++)
				{
					channelRefKey=channelList.get(at).toString();
					/*
					 * GET SF CHANNEL RECORD TYPE ID
					 */
					String sfChannelRecordTypeId=transactionDAO.getSFChannelRecordTypeId(channelRefKey);
					/*
					 * FETCH DATA FROM IM CHANNEL TABLE FOR THE PROCESSING LOCALE
					 * AND START IDENTIFYING IMAGES & LINKS & ATTACHMENTS
					 * AND THEN START CREATING THEM IN SALESFORCE
					 * 
					 * ONLY MASTER IDENTIFIERS FIRST
					 */
					ContentDetails details = null;
					List<ContentDetails> translationsList = transactionDAO.getIMDocumentsList(channelRefKey,"TRANSLATIONS");
					if(null!=translationsList && translationsList.size()>0)
					{
						logger.info("startTranslationsProcessing :: Total Translations Found are :: >"+ translationsList.size());
						JSONObject contentObj = null;
						String xmlData=null;
						String metaData = null;
						JSONObject salesForceObj = null;
						List<AttachmentDetails> attachmentsList = null;
						AttachmentDetails attachmentDetails = null;
						List<AttachmentDetails> innerLinksList=null;
						List<AttachmentDetails> imagesList = null;
						String htmlContent=null;
						JSONObject responseObject = null;
						JSONObject getResponseObject = null;
						String masterIdentifierArticleId=null;
						List<CategoryDetails> categoryList = null;
						List<ManualLinkDetails> manualLinksList = null;
						ManualLinkDetails manualLinkDetails = null;
						String manualLinkContent=null;
						for(int a=0;a<translationsList.size();a++)
						{
							details = (ContentDetails)translationsList.get(a);
							// default value of execution method to be update - so that error message gets tracked
							details.setExecutionMethod("UPDATE");
							logger.info("startTranslationsProcessing :: ######## Starting Translation "+(a+1)+" / "+ translationsList.size()+" ############");
							try
							{
								/*
								 * RETRIEVE KnowledgeArticleId OF MASTER IDENTIFIER
								 */
								masterIdentifierArticleId = transactionDAO.getMasterIdentifierArticleId(channelRefKey, details.getDocumentId(), details.getBaseLocale());
								if(null!=masterIdentifierArticleId && !"".equals(masterIdentifierArticleId))
								{
									contentObj= transactionDAO.getIMDocumentJSONObject(channelRefKey, details.getAutoId());
									if(null!=contentObj)
									{
										// SALES FORCE CHANNEL ID
										details.setSfChannelId(sfChannelRecordTypeId);

										// SCHEMA XML - APPLY A TRY CATCH AS CUSTOM MESSAGE ALREADY TRACKED BELOW IF XML DATA NODE IS NULL
										try
										{
											xmlData = contentObj.getString("xml");
										}
										catch(Exception e) {
											// DO NOTHING IN THIS CASE
										}
										
										// METADATA XML - APPLY A TRY CATCH, AS MANY CHANNELS DO NOT HAVE THIS NODE
										try
										{
											metaData = contentObj.getString("metaDataXml");
										}
										catch(Exception e) {
											// DO NOTHING IN THIS CASE
										}
										/*
										 * PROCEED ONLY WHEN XML IS NOT NULL
										 */
										if(null!=xmlData && !"".equals(xmlData))
										{
											salesForceObj= new JSONObject();

											/*
											 * RETRIEVE INLINE IMAGES DATA FOR DOCUMENT ID & LOCALE
											 */
											imagesList = transactionDAO.getDocumentInlineImages(channelRefKey, details.getDocumentId(), details.getLocale());

											/*
											 * RETRIEVE INNERLINKS DATA FOR DOCUMENT ID & LOCALE
											 */
											innerLinksList = transactionDAO.getDocumentInnerLinks(channelRefKey, details.getDocumentId(), details.getLocale());

											/*
											 * PERFORM INNERLINKS TRACKING AND REPLACE OPERATION FOR SALES FORCE
											 */
											xmlData = payloadUtils.performInnerLinksOperation(xmlData, innerLinksList, details.getDocumentId(), details.getLocale(), transactionDAO);

											/*
											 * PERFORM OKM INNERLINKS TRACKING REPLACE OPERATION FOR SALES FORCE
											 */
											xmlData = payloadUtils.performOKMInnerLinksOperation(xmlData, details.getDocumentId(), details.getLocale(), transactionDAO, channelRefKey);
											
											/*
											 * PERFORM INLINE IMAGES REPLACE OPERATION FOR SALES FORCE
											 */
											xmlData= payloadUtils.performInlineImagesOperation(xmlData, imagesList, details.getDocumentId(), details.getAnswerId());

											/*
											 * RETRIEVE MANUAL LINKS DATA FOR DOCUMENT ID & LOCALE
											 */
											manualLinksList = transactionDAO.getDocumentManualLinks(details.getDocumentId(), details.getLocale());
											
											
											/*
											 * START PREPARING PAYLOADS
											 * CHANNEL WISE
											 */
											salesForceObj = payloadUtils.createPayload(xmlData, channelRefKey);

											/*
											 * COMMON CODE FOR ATTACHMENTS FOR ALL CHANNELS
											 * RETRIEVE ALL ATTACHMENTS (E.G NODES DATA FOR DOCUMENT  / LOCALE)
											 * CREATE HTML FOR THOSE ATTACHMENTS
											 */
											htmlContent="";
											attachmentsList = transactionDAO.getDocumentAttachments(channelRefKey, details.getDocumentId(), details.getLocale());
											if(null!=attachmentsList && attachmentsList.size()>0)
											{
												for(int b=0;b<attachmentsList.size();b++)
												{
													attachmentDetails  =(AttachmentDetails)attachmentsList.get(b);
													if(null!=attachmentDetails.getCdnUrl() && !"".equals(attachmentDetails.getCdnUrl()))
													{
														htmlContent+="<a href=\""+attachmentDetails.getCdnUrl()+"\" target=\"_blank\">";
													}
													else
													{
														htmlContent+="<a href=\"#\">";
													}
													// add attachmentName
													htmlContent+=attachmentDetails.getAttachmentName();
													htmlContent+="</a><br />";
													attachmentDetails = null;
												}
											}

											// set attachmentData for field Links__c in salesForceObj Payload Object
											salesForceObj.put("Links__c", htmlContent);
											// set length as well
											payloadUtils.getLengthObj().put("Links__c", htmlContent.length());
											htmlContent = null;
											attachmentDetails = null;
											attachmentsList = null;

											/*
											 * COMMON CODE FOR ALL MANUAL LINKS
											 */
											manualLinkContent = "";
											if(null!=manualLinksList && manualLinksList.size()>0)
											{
												manualLinkDetails = null;
												for(int f=0;f<manualLinksList.size();f++)
												{
													manualLinkDetails= (ManualLinkDetails)manualLinksList.get(f);
													if(null!=manualLinkDetails.getSfRecordId() && !"".equals(manualLinkDetails.getSfRecordId()))
													{
														// instead of document id & article number add - Manual Link Title
//														manualLinkContent+="<a href=\"/articles/"+manualLinkDetails.getLocale()+"/Knowledge/"+manualLinkDetails.getSfMappedDocUrl()+"\">";
														// new URL Link pattern - /lightning/r/Knowledge__kav/ka08G000000CoeHQAS/view
														manualLinkContent+="<a href=\"/lightning/r/Knowledge__kav/"+manualLinkDetails.getSfRecordId()+"/view"+"\">";
														
														if(null!=manualLinkDetails.getTitle() && !"".equals(manualLinkDetails.getTitle()))
														{
															manualLinkContent+=manualLinkDetails.getTitle();
														}
														else
														{
															// add document id
															manualLinkContent+=manualLinkDetails.getDocumentId();
															if(null!=manualLinkDetails.getSfArticleNumber() && !"".equals(manualLinkDetails.getSfArticleNumber()))
															{
																manualLinkContent+=" - "+manualLinkDetails.getSfArticleNumber();
															}
														}
														manualLinkContent+="</a><br/>";
													}
													else
													{
														manualLinkContent+="<a href=\"#\">";
														if(null!=manualLinkDetails.getTitle() && !"".equals(manualLinkDetails.getTitle()))
														{
															manualLinkContent+=manualLinkDetails.getTitle();
														}
														else
														{
															manualLinkContent+=manualLinkDetails.getDocumentId();
														}
														manualLinkContent+="</a><br/>";
													}
													manualLinkDetails = null;
												}
											}
											
											// set manualLinks Data for field Manual_Links__c in salesForceObj Payload Object
											salesForceObj.put("Manual_Links__c", manualLinkContent);
											// add length as well
											payloadUtils.getLengthObj().put("Manual_Links__c", manualLinkContent.length());
											manualLinkContent = null;
											manualLinkDetails = null;
											
											// add DocumentId & Locale to lengthObj
											payloadUtils.getLengthObj().put("DocumentId", details.getDocumentId());
											payloadUtils.getLengthObj().put("Locale", details.getLocale());
											if(null==lengthArray || lengthArray.length()<=0)
											{
												lengthArray = new JSONArray();
											}
											lengthArray.put(payloadUtils.getLengthObj());
											payloadUtils.setLengthObj(null);
											
											
											/*
											 * COMMON FIELDS OPERATION
											 */
											salesForceObj = payloadUtils.performCommonFieldsOperation(details, salesForceObj, sfChannelRecordTypeId);


											/*
											 * META DATA TAG OPERATION
											 */
											if(null!=metaData && !"".equals(metaData) && !metaData.toLowerCase().equals("<META/>".toLowerCase()))
											{
												salesForceObj = metaDataOperation(metaData, salesForceObj);
											}

											/*
											 * SALES FORCE CREATE / UPDATE TRANSLATION OPERATION
											 */
											if(null!=salesForceObj)
											{
												/*
												 * if salesForce Record Id for the currentDocument is NOT NULL
												 * 	THEN UPDATE OPERATION
												 * Else
												 * 	Create a New Draft Version for the Translation Locale for Processing Document
												 * 	Read the articleVersionId from the Output
												 * 	Call Update Operation using the articleVersionId from previous Output
												 * 	Make a Get Call to read the other details for the translation created in 
												 */
												
												if(null!=details.getSfRecordId() && !"".equals(details.getSfRecordId()))
												{
													// set EXECUTION METHOD AS UPDATE
													details.setExecutionMethod("UPDATE");
													// perform update Operation in Sales Force
													details = updateSFDocumentOperation(details, responseObject, salesForceObj, getResponseObject);
													/*
													 * NO NEED TO PERFORM CATEGORY OPERATION HERE
													 * AS IN SALES FORCE ALL CATEGORIES WILL BE MAPPED WITH MASTER IDENTIFIERS ONLY
													 * 
													 */
													
//													if(null!=details.getSfRecordId() && !"".equals(details.getSfRecordId()))
//													{
//														/*
//														 * RETRIEVE CATEGORY LIST FOR THE PROCESSED DOCUMENT
//														 */
//														try
//														{
//															categoryList = transactionDAO.getDocumentCategoryList(channelRefKey, details.getDocumentId(), details.getLocale());
//															if(null!=categoryList && categoryList.size()>0)
//															{
//																logger.info("startMasterIdentifiersProcessing :: Total Categories found for "+details.getDocumentId()+" of "+details.getLocale()+" are :: >"+ categoryList.size());
//																/*
//																 * START CATEGORY PROCESSING
//																 */
//																categoryList = addSFCategoriesOperation(details, categoryList);
//															}
//															else
//															{
//																logger.info("startMasterIdentifiersProcessing :: Category Processing Not Required, as No Categories found for "+details.getDocumentId()+" of "+details.getLocale());
//															}
//														}
//														catch(Exception e)
//														{
//															Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "startMasterIdentifiersProcessing()", e);
//															// capture error Message
//															details = captureErrorMessage(details, "Failed to fetch Categories Data for Processing Document from DB.", e);
//														}
//													}
												}
												else
												{
													// set EXECUTION METHOD AS CREATE
													details.setExecutionMethod("CREATE");
													// perform create Translation Operation in Sales Force
													details = createSFTranslationOperation(details, salesForceObj, masterIdentifierArticleId);
													/*
													 * NO NEED TO PERFORM CATEGORY OPERATION HERE
													 * AS IN SALES FORCE ALL CATEGORIES WILL BE MAPPED WITH MASTER IDENTIFIERS ONLY
													 * 
													 */
//													if(null!=details.getSfRecordId() && !"".equals(details.getSfRecordId()))
//													{
//														/*
//														 * RETRIEVE CATEGORY LIST FOR THE PROCESSED DOCUMENT
//														 */
//														try
//														{
//															categoryList = transactionDAO.getDocumentCategoryList(channelRefKey, details.getDocumentId(), details.getLocale());
//															if(null!=categoryList && categoryList.size()>0)
//															{
//																logger.info("startMasterIdentifiersProcessing :: Total Categories found for "+details.getDocumentId()+" of "+details.getLocale()+" are :: >"+ categoryList.size());
//																/*
//																 * START CATEGORY PROCESSING
//																 */
//																categoryList = addSFCategoriesOperation(details, categoryList);
//															}
//															else
//															{
//																logger.info("startMasterIdentifiersProcessing :: Category Processing Not Required, as No Categories found for "+details.getDocumentId()+" of "+details.getLocale());
//															}
//														}
//														catch(Exception e)
//														{
//															Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "startMasterIdentifiersProcessing()", e);
//															// capture error Message
//															details = captureErrorMessage(details, "Failed to fetch Categories Data for Processing Document from DB.", e);
//														}
//													}
												}
											}
											salesForceObj = null;
										}
										else
										{
											// capture error Message
											details = captureErrorMessage(details, "Schema Data for the KA Document is NULL.", null);
										}
										xmlData=  null;
										metaData = null;
									}
									contentObj = null;
								}
								else
								{
									logger.info("startTranslationsProcessing :: Failed to identify Master Identifier Sales Force Article Id for "+ details.getDocumentId()+" of "+ details.getLocale());
									// capture error Message
									details = captureErrorMessage(details, "Failed to create Translation, since Master Identifier is not yet Created.", null);
								}
								masterIdentifierArticleId = null;
							}
							catch(Exception e)
							{
								Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "startTranslationsProcessing()", e);
								// capture error Message
								details = captureErrorMessage(details, "Please refer Error Message, either KA Parsing or Sales Force Processing Failed.", e);
							}
							
							/*
							 * PROCEED FOR SAVING THE DOCUMENT IN DATABASE WITH SALES FORCE OPERATION
							 * ALSO UPDATE THE INNERLINKS DETAILS WITH STATUS
							 */
							transactionDAO.updateSFTransactionDetails(details, payloadUtils.getAllInnerLinksList(), channelRefKey, categoryList, manualLinksList);
							
							// set INNERLINKS LIST TO NULL
							payloadUtils.setAllInnerLinksList(null);
							
							// set CATEGORY LIST TO NULL
							categoryList = null;
							
							details = null;
							contentObj = null;
							xmlData=null;
							metaData = null;
							salesForceObj = null;
							attachmentsList = null;
							attachmentDetails = null;
							innerLinksList=null;
							imagesList = null;
							htmlContent=null;
							responseObject = null;
							getResponseObject = null;
							manualLinkDetails = null;
							manualLinkContent = null;
							manualLinksList = null;

							logger.info("startTranslationsProcessing :: ######## Ending Translation "+(a+1)+" / "+ translationsList.size()+" ############");
						}
					}
					translationsList = null;
					channelRefKey  =null;
				}
			}
			channelList  =null;
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "startTranslationsProcessing()", e);
		}
		finally
		{
			try
			{
				if(null!=transactionDAO.conn)
				{
					transactionDAO.conn.close();
				}
				transactionDAO.conn  =null;
			}
			catch(Exception e)
			{
				Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "startTranslationsProcessing()", e);
			}
			transactionDAO= null;
			caller = null;
			payloadUtils = null;
		}
	}

	private void startInnerLinksProcessing()
	{
		try
		{
			transactionDAO = new TransactionDAO();
			caller = new SalesForceAPICaller();
			payloadUtils = new GenerateSFChannelPayloadUtils();
			List<String> channelList = transactionDAO.getChannelDetailsForInnerLinksProcessing();
			if(null!=channelList && channelList.size()>0)
			{
				String channelRefKey=null;
				for(int at=0;at<channelList.size();at++)
				{
					channelRefKey=channelList.get(at).toString();
					/*
					 * GET SF CHANNEL RECORD TYPE ID
					 */
					String sfChannelRecordTypeId=transactionDAO.getSFChannelRecordTypeId(channelRefKey);
					/*
					 * FETCH DATA FROM IM CHANNEL TABLE FOR THE PROCESSING LOCALE
					 * AND START IDENTIFYING IMAGES & LINKS & ATTACHMENTS
					 * AND THEN START CREATING THEM IN SALESFORCE
					 * 
					 * ONLY DOCUMENTS WITH INNERLINKS AS ARTICLES TO BE MAPPED
					 */
					ContentDetails details = null;
					List<CategoryDetails> categoryList = null;
					List<ContentDetails> documentsList = transactionDAO.getIMDocumentsListForInnerLinksUpdate(channelRefKey);
					if(null!=documentsList && documentsList.size()>0)
					{
						logger.info("startInnerLinksProcessing :: Total Documents Found are :: >"+ documentsList.size());
						JSONObject contentObj = null;
						String xmlData=null;
						String metaData = null;
						JSONObject salesForceObj = null;
						List<AttachmentDetails> attachmentsList = null;
						AttachmentDetails attachmentDetails = null;
						List<AttachmentDetails> innerLinksList=null;
						List<AttachmentDetails> imagesList = null;
						String htmlContent=null;
						JSONObject responseObject = null;
						JSONObject getResponseObject = null;
						List<ManualLinkDetails> manualLinksList = null;
						ManualLinkDetails manualLinkDetails = null;
						String manualLinkContent=null;
						for(int a=0;a<documentsList.size();a++)
						{
							details = (ContentDetails)documentsList.get(a);
							// default value of execution method to be update - so that error message gets tracked
							details.setExecutionMethod("UPDATE");
							logger.info("startInnerLinksProcessing :: ######## Starting Document "+(a+1)+" / "+ documentsList.size()+" ############");
							try
							{
								contentObj= transactionDAO.getIMDocumentJSONObject(channelRefKey, details.getAutoId());
								if(null!=contentObj)
								{
									// SALES FORCE CHANNEL ID
									details.setSfChannelId(sfChannelRecordTypeId);
									
									// SCHEMA XML - APPLY A TRY CATCH AS CUSTOM MESSAGE ALREADY TRACKED BELOW IF XML DATA NODE IS NULL
									try
									{
										xmlData = contentObj.getString("xml");
									}
									catch(Exception e) {
										// DO NOTHING IN THIS CASE
									}
									
									// METADATA XML - APPLY A TRY CATCH, AS MANY CHANNELS DO NOT HAVE THIS NODE
									try
									{
										metaData = contentObj.getString("metaDataXml");
									}
									catch(Exception e) {
										// DO NOTHING IN THIS CASE
									}
									/*
									 * PROCEED ONLY WHEN XML IS NOT NULL
									 */
									if(null!=xmlData && !"".equals(xmlData))
									{
										salesForceObj= new JSONObject();

										/*
										 * RETRIEVE INLINE IMAGES DATA FOR DOCUMENT ID & LOCALE
										 */
										imagesList = transactionDAO.getDocumentInlineImages(channelRefKey, details.getDocumentId(), details.getLocale());

										/*
										 * RETRIEVE INNERLINKS DATA FOR DOCUMENT ID & LOCALE
										 */
										innerLinksList = transactionDAO.getDocumentInnerLinks(channelRefKey, details.getDocumentId(), details.getLocale());

										/*
										 * PERFORM INNERLINKS TRACKING AND REPLACE OPERATION FOR SALES FORCE
										 */
										xmlData = payloadUtils.performInnerLinksOperation(xmlData, innerLinksList, details.getDocumentId(), details.getLocale(), transactionDAO);

										/*
										 * PERFORM OKM INNERLINKS TRACKING REPLACE OPERATION FOR SALES FORCE
										 */
										xmlData = payloadUtils.performOKMInnerLinksOperation(xmlData, details.getDocumentId(), details.getLocale(), transactionDAO, channelRefKey);
										
										/*
										 * PERFORM INLINE IMAGES REPLACE OPERATION FOR SALES FORCE
										 */
										xmlData= payloadUtils.performInlineImagesOperation(xmlData, imagesList, details.getDocumentId(), details.getAnswerId());

										/*
										 * RETRIEVE MANUAL LINKS DATA FOR DOCUMENT ID & LOCALE
										 */
										manualLinksList = transactionDAO.getDocumentManualLinks(details.getDocumentId(), details.getLocale());
										
										/*
										 * START PREPARING PAYLOADS
										 * CHANNEL WISE
										 */
										salesForceObj = payloadUtils.createPayload(xmlData, channelRefKey);

										/*
										 * COMMON CODE FOR ATTACHMENTS FOR ALL CHANNELS
										 * RETRIEVE ALL ATTACHMENTS (E.G NODES DATA FOR DOCUMENT  / LOCALE)
										 * CREATE HTML FOR THOSE ATTACHMENTS
										 */
										htmlContent="";
										attachmentsList = transactionDAO.getDocumentAttachments(channelRefKey, details.getDocumentId(), details.getLocale());
										if(null!=attachmentsList && attachmentsList.size()>0)
										{
											for(int b=0;b<attachmentsList.size();b++)
											{
												attachmentDetails  =(AttachmentDetails)attachmentsList.get(b);
												if(null!=attachmentDetails.getCdnUrl() && !"".equals(attachmentDetails.getCdnUrl()))
												{
													htmlContent+="<a href=\""+attachmentDetails.getCdnUrl()+"\" target=\"_blank\">";
												}
												else
												{
													htmlContent+="<a href=\"#\">";
												}
												// add attachmentName
												htmlContent+=attachmentDetails.getAttachmentName();
												htmlContent+="</a></br>";
												attachmentDetails = null;
											}
										}

										// set attachmentData for field Links__c in salesForceObj Payload Object
										salesForceObj.put("Links__c", htmlContent);
										htmlContent = null;
										attachmentDetails = null;
										attachmentsList = null;
										
										/*
										 * COMMON CODE FOR ALL MANUAL LINKS
										 */
										manualLinkContent = "";
										if(null!=manualLinksList && manualLinksList.size()>0)
										{
											manualLinkDetails = null;
											for(int f=0;f<manualLinksList.size();f++)
											{
												manualLinkDetails= (ManualLinkDetails)manualLinksList.get(f);
												if(null!=manualLinkDetails.getSfRecordId() && !"".equals(manualLinkDetails.getSfRecordId()))
												{
//													manualLinkContent+="<a href=\"/articles/"+manualLinkDetails.getLocale()+"/Knowledge/"+manualLinkDetails.getSfMappedDocUrl()+"\">"+manualLinkDetails.getDocumentId();
													// new URL Link pattern - /lightning/r/Knowledge__kav/ka08G000000CoeHQAS/view
													manualLinkContent+="<a href=\"/lightning/r/Knowledge__kav/"+manualLinkDetails.getSfRecordId()+"/view"+"\">";
													
													if(null!=manualLinkDetails.getSfArticleNumber() && !"".equals(manualLinkDetails.getSfArticleNumber()))
													{
														manualLinkContent+=" - "+manualLinkDetails.getSfArticleNumber();
													}
													manualLinkContent+="</a>";
												}
												else
												{
													manualLinkContent+="<a href=\"#\">"+manualLinkDetails.getDocumentId()+"</a>";
												}
												if(f!=manualLinksList.size()-1)
												{
													manualLinkContent+="<br>";
												}
												manualLinkDetails = null;
											}
										}
										
										// set manualLinks Data for field Manual_Links__c in salesForceObj Payload Object
										salesForceObj.put("Manual_Links__c", manualLinkContent);
										manualLinkContent = null;
										manualLinkDetails = null;
										
										/*
										 * COMMON FIELDS OPERATION
										 */
										salesForceObj = payloadUtils.performCommonFieldsOperation(details, salesForceObj, sfChannelRecordTypeId);


										/*
										 * META DATA TAG OPERATION
										 */
										if(null!=metaData && !"".equals(metaData) && !metaData.toLowerCase().equals("<META/>".toLowerCase()))
										{
											salesForceObj = metaDataOperation(metaData, salesForceObj);
										}

										/*
										 * SALES FORCE CREATE / UPDATE OPERATION
										 */
										if(null!=salesForceObj)
										{
											if(null!=details.getSfRecordId() && !"".equals(details.getSfRecordId()))
											{
												// perform update Operation in Sales Force
												details = updateSFDocumentOperation(details, responseObject, salesForceObj, getResponseObject);
												// set EXECUTION METHOD AS UPDATE
												details.setExecutionMethod("UPDATE");
												/*
												 * NO CATEGORY OPERATION REQUIRED HERE, AS CONTENT IS GETTING UPDATED FOR INNERLINKS
												 */
											}
										}
										salesForceObj = null;
									}
									else
									{
										// capture error Message
										details = captureErrorMessage(details, "Schema Data for the KA Document is NULL.", null);
									}
									xmlData=  null;
									metaData = null;
								}
							}
							catch(Exception e)
							{
								Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "startInnerLinksProcessing()", e);
								// capture error Message
								details = captureErrorMessage(details, "Please refer Error Message, either KA Parsing or Sales Force Processing Failed.", e);
							}
							
							/*
							 * PROCEED FOR SAVING THE DOCUMENT IN DATABASE WITH SALES FORCE OPERATION
							 * ALSO UPDATE THE INNERLINKS DETAILS WITH STATUS
							 */
							transactionDAO.updateSFTransactionDetails(details, payloadUtils.getAllInnerLinksList(), channelRefKey, categoryList, manualLinksList);
							
							// set INNERLINKS LIST TO NULL
							payloadUtils.setAllInnerLinksList(null);
							
							// set CATEGORY LIST TO NULL
							categoryList=  null;
							
							details = null;
							contentObj = null;
							xmlData=null;
							metaData = null;
							salesForceObj = null;
							attachmentsList = null;
							attachmentDetails = null;
							innerLinksList=null;
							imagesList = null;
							htmlContent=null;
							responseObject = null;
							getResponseObject = null;
							manualLinksList=  null;
							logger.info("startInnerLinksProcessing :: ######## Ending Document "+(a+1)+" / "+ documentsList.size()+" ############");
						}
					}
					documentsList = null;
					channelRefKey  =null;
				}
			}
			channelList  =null;
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "startInnerLinksProcessing()", e);
		}
		finally
		{
			try
			{
				if(null!=transactionDAO.conn)
				{
					transactionDAO.conn.close();
				}
				transactionDAO.conn  =null;
			}
			catch(Exception e)
			{
				Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "startInnerLinksProcessing()", e);
			}
			transactionDAO= null;
			caller = null;
			payloadUtils = null;
		}
	}

	
	private JSONObject metaDataOperation(String metaData, JSONObject salesForceObj) throws ParserConfigurationException, SAXException, IOException, JSONException 
	{
		String metaDataContent = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new InputSource(new StringReader(metaData)));
		if(null!=doc)
		{
			doc.normalize();
			Node metaDataNode = doc.getFirstChild();
			if(null!=metaDataNode)
			{
				/*
				 * TWO POSSIBILITIES OF META DATA
				 */
				NodeList childNodeList = metaDataNode.getChildNodes();
				if(null!=childNodeList && childNodeList.getLength()>0)
				{
					Node childNode=null;
					String tempText=null;
					for(int t=0;t<childNodeList.getLength();t++)
					{
						childNode = (Node)childNodeList.item(t);
						if(null!=childNode.getNodeName() && !"".equals(childNode.getNodeName()))
						{
							// READ TEXT VALUE OF THE NODES
							tempText =  Utilities.readNodeValue(childNode);
							if(null!=tempText && !"".equals(tempText))
							{
								if(null!=metaDataContent && !"".equals(metaDataContent))
								{
									metaDataContent+="\n"+tempText;
								}
								else
								{
									metaDataContent=tempText;
								}
							}
							tempText  =null;
						}
						childNode = null;
					}
				}
			}
		}
		doc = null;
		factory=  null;
		builder=null;

		// set MetaData Node
		if(null!=metaDataContent && !"".equals(metaDataContent))
		{
			// set Key_Words_SEO__c
			salesForceObj.put("Key_Words_SEO__c", metaDataContent);
		}
		else
		{
			salesForceObj.put("Key_Words_SEO__c", JSONObject.NULL);
		}
		metaDataContent = null;

		return salesForceObj;
	}

	private ContentDetails captureErrorMessage(ContentDetails details, String customErrorMessage, Exception e)
	{
		try
		{
			// set sfProcessingstatus to Failure
			details.setSfProcessingStatus("FAILURE");

			// set Remarks for Failure Cases
			if(null!=customErrorMessage && !"".equals(customErrorMessage))
			{
				details.setSfRemarks(customErrorMessage);
			}

			// only when Exception is not NULL
			if(null!=e)
			{
				writer = new StringWriter();
				print = new PrintWriter(writer);
				e.printStackTrace();

				// set errorMessage
				details.setSfErrorMessage(writer.toString());
			}

			// clear all printers & writers
			if(null!=writer)
			{
				writer.flush();
				writer.close();
			}
			writer = null;
			if(null!=print)
			{
				print.flush();
				print.close();
			}
			print=  null;
		}
		catch(Exception w)
		{
			Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "captureErrorMessage()", w);
		}
		return details;
	}

	private ContentDetails createSFDocumentOperation(ContentDetails details, JSONObject responseObject, JSONObject salesForceObj, JSONObject getResponseObject)
	{
		try
		{
			/*
			 * KA LOCALES Vs SALES FORCE LOCALES
			 * de_DE = de
			 * fr_FR = fr
			 * it_IT = IT
			 * pt_BR = pt_BR
			 * pt_PT = pt_PT
			 * en_GB = en_GB
			 * es_ES = es
			 * en_US = en_US
			 * 
			 * SET THE LANGUAGE EXPLICILTY FOR CREATING MASTER IDENTIFIERS OF DIFFERENT LOCALES
			 */
			if(details.getLocale().equals("de_DE"))
			{
				salesForceObj.put("language", "de");
			}
			else if(details.getLocale().equals("fr_FR"))
			{
				salesForceObj.put("language", "fr");
			}
			else if(details.getLocale().equals("it_IT"))
			{
				salesForceObj.put("language", "it");
			}
			else if(details.getLocale().equals("es_ES"))
			{
				salesForceObj.put("language", "es");
			}
			else
			{
				salesForceObj.put("language", details.getLocale());
			}
			
//			logger.info("Payload :: >"+ salesForceObj);
			responseObject = caller.callSFRestAPI("", SalesForceConstants.METHOD_POST, salesForceObj.toString());
			if(null!=responseObject)
			{
				if(responseObject.getString("CALL_STATUS").equals("SUCCESS"))
				{
					logger.info("createSFDocumentOperation :: Sales Force Create API Executed Successfully for "+ details.getDocumentId()+" of "+ details.getLocale()+". Proceed for Reading SalesForce Record Id.");
					try
					{
						if(null!=responseObject.getJSONObject("FINAL_DATA").getString("id"))
						{
							details.setSfRecordId(responseObject.getJSONObject("FINAL_DATA").getString("id"));
							/*
							 * NOW PROCEED FOR FETCHING THE OTHER DOCUMENT DETAILS FROM SALES FORCE
							 */
							if(null!=details.getSfRecordId() && !"".equals(details.getSfRecordId()))
							{
								try
								{
									getResponseObject = caller.callSFRestAPI(details.getSfRecordId(), SalesForceConstants.METHOD_GET, null);
									if(null!=getResponseObject)
									{
										if(getResponseObject.getString("CALL_STATUS").equals("SUCCESS"))
										{
											logger.info("createSFDocumentOperation :: Sales Force Get API Executed Successfully for "+ details.getDocumentId()+" of "+ details.getLocale()+" where SF Record Id >> "+details.getSfRecordId()+". Proceed for Reading SalesForce Other Details.");
											try
											{
												// PROCESSING STATUS
												details.setSfProcessingStatus("SUCCESS");
												// SALESFORCE URL
												details.setSfdocumentURL(getResponseObject.getJSONObject("FINAL_DATA").getJSONObject("attributes").getString("url"));
												// UrlName
												details.setSfURLName(getResponseObject.getJSONObject("FINAL_DATA").getString("UrlName"));
												// ArticleNumber
												details.setSfArticleNumber(getResponseObject.getJSONObject("FINAL_DATA").getString("ArticleNumber"));
												// Article Id - Will be used for creating translations
												details.setSfArticleId(getResponseObject.getJSONObject("FINAL_DATA").getString("KnowledgeArticleId"));
												// Language
												details.setSfLocale(getResponseObject.getJSONObject("FINAL_DATA").getString("Language"));
												// IsMasterLanguage
												if(getResponseObject.getJSONObject("FINAL_DATA").getBoolean("IsMasterLanguage")==true)
												{
													details.setSfMasterIdentifier("TRUE");
												}
												else 
												{
													details.setSfMasterIdentifier("FALSE");
												}
											}
											catch(Exception e)
											{
												Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "createSFDocumentOperation()", e);
												// set Error Message as Failed to identify Required Attributes from Sales Force Get API Response.
												details = captureErrorMessage(details, "Failed to identify Required Attributes from Sales Force Get API Response.", e);
											}
										}
										else
										{
											logger.info("createSFDocumentOperation :: Failed to execute Sales Force Get API for "+ details.getDocumentId()+" of "+ details.getLocale()+" where SF Record Id >> "+details.getSfRecordId()+".");
											// capture error Message
											try
											{
												if(null!=getResponseObject.getJSONArray("ERROR_DATA"))
												{
													// set processing Status as Failure
													details.setSfProcessingStatus("FAILURE");
													// set remarks
													details.setSfRemarks("Failed to execute Sales Force Get API.");
													// set Error Message received from Sales Force API Call
													details.setErrorMessage(getResponseObject.getJSONArray("ERROR_DATA").toString());
												}
											}
											catch(Exception e) 
											{
												Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "createSFDocumentOperation()", e);
												// set Error Message as Failed to read Error JSON Array from API Call
												details = captureErrorMessage(details, "Failed to read Error JSON Array from Get API Call.", e);
											}
										}
									}
									getResponseObject = null;
								}
								catch(Exception e)
								{
									Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "createSFDocumentOperation()", e);
									// capture error Message
									details = captureErrorMessage(details, "Failed to read Response Object from Sales Force GET API.", e);
								}
							}
						}
					}
					catch(Exception e)
					{
						Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "createSFDocumentOperation()", e);
						// capture error Message
						details = captureErrorMessage(details, "Failed to read Sales Force Knowledge Article Id for created document.", e);
					}
				}
				else
				{
					logger.info("createSFDocumentOperation :: Failed to execute Sales Force Create API for "+ details.getDocumentId()+" of "+ details.getLocale()+".");
					// capture error Message
					try
					{
						if(null!=responseObject.getJSONArray("ERROR_DATA"))
						{
							// set processing Status as Failure
							details.setSfProcessingStatus("FAILURE");
							// set remarks
							details.setSfRemarks("Failed to execute Sales Force Create API.");
							// set Error Message received from Sales Force API Call
							details.setErrorMessage(responseObject.getJSONArray("ERROR_DATA").toString());
						}
					}
					catch(Exception e) 
					{
						Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "createSFDocumentOperation()", e);
						// set Error Message as Failed to read Error JSON Array from API Call
						details = captureErrorMessage(details, "Failed to read Error JSON Array from Create API Call.", e);
					}
				}
			}
			responseObject  =null;
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "createSFDocumentOperation()", e);
			// capture error Message
			details = captureErrorMessage(details, "Failed to read Response Object from Sales Force Create API.", e);
		}
		return details;
	}

	private ContentDetails updateSFDocumentOperation(ContentDetails details, JSONObject responseObject, JSONObject salesForceObj, JSONObject getResponseObject)
	{
//		logger.info("Payload :: >"+ salesForceObj);
		try
		{
			responseObject = caller.callSFRestAPI(details.getSfRecordId(), SalesForceConstants.METHOD_PATCH, salesForceObj.toString());
			if(null!=responseObject)
			{
				if(responseObject.getString("CALL_STATUS").equals("SUCCESS"))
				{
					logger.info("updateSFDocumentOperation :: Sales Force Update API Executed Successfully for "+ details.getDocumentId()+" of "+ details.getLocale()+" Where Sales Force Id :: >"+ details.getSfRecordId());
					// SET PROCESSING STATUS TO SUCCESS
					details.setSfProcessingStatus("SUCCESS");
				}
				else
				{
					logger.info("updateSFDocumentOperation :: Failed to execute Sales Force Update API for "+ details.getDocumentId()+" of "+ details.getLocale()+" Where Sales Force Id :: >"+details.getSfRecordId());
					// capture error Message
					try
					{
						if(null!=responseObject.getJSONArray("ERROR_DATA"))
						{
							// set processing Status as Failure
							details.setSfProcessingStatus("FAILURE");
							// set remarks
							details.setSfRemarks("Failed to execute Sales Force Update API.");
							// set Error Message received from Sales Force API Call
							details.setErrorMessage(responseObject.getJSONArray("ERROR_DATA").toString());
						}
					}
					catch(Exception e) 
					{
						Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "updateSFDocumentOperation()", e);
						// set Error Message as Failed to read Error JSON Array from API Call
						details = captureErrorMessage(details, "Failed to read Error JSON Array from Update API Call.", e);
					}
				}
			}
			responseObject  =null;
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "updateSFDocumentOperation()", e);
			// capture error Message
			details = captureErrorMessage(details, "Failed to read Response Object from Sales Force Update API.", e);
		}
		return details;
	}
	
	private ContentDetails createSFTranslationOperation(ContentDetails details,  JSONObject salesForceObj, String masterIdentifierArticleId)
	{
		JSONObject getResponseObject = null;
		JSONObject responseObject = null;
		JSONObject updateResponseObject = null;
		try
		{
			logger.info("Translation Payload :: >"+ salesForceObj);
			JSONObject payloadObj = new JSONObject();
			payloadObj.put("articleId", masterIdentifierArticleId);
			/*
			 * KA LOCALES Vs SALES FORCE LOCALES
			 * de_DE = de
			 * fr_FR = fr
			 * it_IT = IT
			 * pt_BR = pt_BR
			 * pt_PT = pt_PT
			 * en_GB = en_GB
			 * es_ES = es
			 * en_US = en_US
			 */
			if(details.getLocale().equals("de_DE"))
			{
				payloadObj.put("language", "de");
			}
			else if(details.getLocale().equals("fr_FR"))
			{
				payloadObj.put("language", "fr");
			}
			else if(details.getLocale().equals("it_IT"))
			{
				payloadObj.put("language", "it");
			}
			else if(details.getLocale().equals("es_ES"))
			{
				payloadObj.put("language", "es");
			}
			else
			{
				payloadObj.put("language", details.getLocale());
			}
			
			payloadObj.put("sendEmailNotification", false);
			logger.info("Submit for Translation :: Payload :: >"+ payloadObj.toString());
			responseObject = caller.callSFRestAPIForOtherOperations(translationAPIURL,SalesForceConstants.METHOD_POST, payloadObj.toString());
			if(null!=responseObject)
			{
				if(responseObject.getString("CALL_STATUS").equals("SUCCESS"))
				{
					logger.info("createSFTranslationOperation :: Sales Force Create Translation API Executed Successfully for "+ details.getDocumentId()+" of "+ details.getLocale()+". Proceed for Reading SalesForce Translation Record Id (articleVersionId).");
					try
					{
						if(null!=responseObject.getJSONObject("FINAL_DATA").getString("articleVersionId"))
						{
							details.setSfRecordId(responseObject.getJSONObject("FINAL_DATA").getString("articleVersionId"));
							/*
							 * NOW PROCEED FOR UPDATING THE TRANSLATION DETAILS USING THE SF RECORD ID IN SALES FORCE
							 */
							if(null!=details.getSfRecordId() && !"".equals(details.getSfRecordId()))
							{
								try
								{
									updateResponseObject = caller.callSFRestAPI(details.getSfRecordId(), SalesForceConstants.METHOD_PATCH, salesForceObj.toString());
									if(null!=updateResponseObject)
									{
										if(updateResponseObject.getString("CALL_STATUS").equals("SUCCESS"))
										{
											logger.info("createSFTranslationOperation :: Sales Force Update Translation API Executed Successfully for "+ details.getDocumentId()+" of "+ details.getLocale()+" Where Sales Force Translation Id :: >"+ details.getSfRecordId());
											
											/*
											 * PROCEED FOR FETCHING OTHER SALES FORCE DETAILS OF THE TRANSLATION
											 */
											try
											{
												getResponseObject = caller.callSFRestAPI(details.getSfRecordId(), SalesForceConstants.METHOD_GET, null);
												if(null!=getResponseObject)
												{
													if(getResponseObject.getString("CALL_STATUS").equals("SUCCESS"))
													{
														logger.info("createSFTranslationOperation :: Sales Force Get Translation API Executed Successfully for "+ details.getDocumentId()+" of "+ details.getLocale()+" where SF Record Id >> "+details.getSfRecordId()+". Proceed for Reading SalesForce Other Translation Details.");
														try
														{
															// PROCESSING STATUS
															details.setSfProcessingStatus("SUCCESS");
															// SALESFORCE URL
															details.setSfdocumentURL(getResponseObject.getJSONObject("FINAL_DATA").getJSONObject("attributes").getString("url"));
															// UrlName
															details.setSfURLName(getResponseObject.getJSONObject("FINAL_DATA").getString("UrlName"));
															// ArticleNumber
															details.setSfArticleNumber(getResponseObject.getJSONObject("FINAL_DATA").getString("ArticleNumber"));
															// Article Id - Will be used for creating translations
															details.setSfArticleId(getResponseObject.getJSONObject("FINAL_DATA").getString("KnowledgeArticleId"));
															// Language
															details.setSfLocale(getResponseObject.getJSONObject("FINAL_DATA").getString("Language"));
															// IsMasterLanguage
															if(getResponseObject.getJSONObject("FINAL_DATA").getBoolean("IsMasterLanguage")==true)
															{
																details.setSfMasterIdentifier("TRUE");
															}
															else 
															{
																details.setSfMasterIdentifier("FALSE");
															}
														}
														catch(Exception e)
														{
															Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "createSFTranslationOperation()", e);
															// set Error Message as Failed to identify Required Attributes from Sales Force Get API Response.
															details = captureErrorMessage(details, "Failed to identify Required Attributes from Sales Force Get Translation API Response.", e);
														}
													}
													else
													{
														logger.info("createSFTranslationOperation :: Failed to execute Sales Force Get Translation API for "+ details.getDocumentId()+" of "+ details.getLocale()+" where SF Record Id >> "+details.getSfRecordId()+".");
														// capture error Message
														try
														{
															if(null!=getResponseObject.getJSONArray("ERROR_DATA"))
															{
																// set processing Status as Failure
																details.setSfProcessingStatus("FAILURE");
																// set remarks
																details.setSfRemarks("Failed to execute Sales Force Get Translation API.");
																// set Error Message received from Sales Force API Call
																details.setErrorMessage(getResponseObject.getJSONArray("ERROR_DATA").toString());
															}
														}
														catch(Exception e) 
														{
															Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "createSFTranslationOperation()", e);
															// set Error Message as Failed to read Error JSON Array from API Call
															details = captureErrorMessage(details, "Failed to read Error JSON Array from Get Translation API Call.", e);
														}
													}
												}
												getResponseObject = null;
											}
											catch(Exception e)
											{
												Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "createSFTranslationOperation()", e);
												// capture error Message
												details = captureErrorMessage(details, "Failed to read Response Object from Sales Force GET Translation API.", e);
											}
										}
										else
										{
											logger.info("createSFTranslationOperation :: Failed to execute Sales Force Update Translation API for "+ details.getDocumentId()+" of "+ details.getLocale()+" Where Sales Force Id :: >"+details.getSfRecordId());
											// capture error Message
											try
											{
												if(null!=updateResponseObject.getJSONArray("ERROR_DATA"))
												{
													// set processing Status as Failure
													details.setSfProcessingStatus("FAILURE");
													// set remarks
													details.setSfRemarks("Failed to execute Sales Force Update Translation API.");
													// set Error Message received from Sales Force API Call
													details.setErrorMessage(updateResponseObject.getJSONArray("ERROR_DATA").toString());
												}
											}
											catch(Exception e) 
											{
												Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "createSFTranslationOperation()", e);
												// set Error Message as Failed to read Error JSON Array from API Call
												details = captureErrorMessage(details, "Failed to read Error JSON Array from Update Translation API Call.", e);
											}
										}
									}
									updateResponseObject  =null;
								}
								catch(Exception e)
								{
									Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "createSFTranslationOperation()", e);
									// capture error Message
									details = captureErrorMessage(details, "Failed to read Response Object from Sales Force Update Translation API.", e);
								}
							}
						}
					}
					catch(Exception e)
					{
						Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "createSFTranslationOperation()", e);
						// capture error Message
						details = captureErrorMessage(details, "Failed to read Sales Force Translation Article Version Id for created document.", e);
					}
				}
				else
				{
					logger.info("createSFTranslationOperation :: Failed to execute Sales Force Create Translation API for "+ details.getDocumentId()+" of "+ details.getLocale()+".");
					// capture error Message
					try
					{
						if(null!=responseObject.getJSONArray("ERROR_DATA"))
						{
							// set processing Status as Failure
							details.setSfProcessingStatus("FAILURE");
							// set remarks
							details.setSfRemarks("Failed to execute Sales Force Create Translation API.");
							// set Error Message received from Sales Force API Call
							details.setErrorMessage(responseObject.getJSONArray("ERROR_DATA").toString());
						}
					}
					catch(Exception e) 
					{
						Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "createSFTranslationOperation()", e);
						// set Error Message as Failed to read Error JSON Array from API Call
						details = captureErrorMessage(details, "Failed to read Error JSON Array from Create Translation API Call.", e);
					}
				}
			}
			responseObject  =null;
			payloadObj = null;
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "createSFTranslationOperation()", e);
			// capture error Message
			details = captureErrorMessage(details, "Failed to read Response Object from Sales Force Create Translation API.", e);
		}
		return details;
	}
	
	private List<CategoryDetails> deleteSFCategoriesOperation(ContentDetails details, List<CategoryDetails> categoryList)
	{
		try
		{
			CategoryDetails catDetails = null;
			JSONObject responseObject = null;
			JSONObject payloadObj = null;
			for(int a=0;a<categoryList.size();a++)
			{
				catDetails = (CategoryDetails)categoryList.get(a);
				/*
				 * ONLY WHEN KA REF KEY IS NOT NULL
				 * AND SF ASSOCIATION ID IS NULL (E.G CATEGORY MAPPING HAS ALREADY HAPPENED)
				 */
				if((null!=catDetails.getRefKey() && !"".equals(catDetails.getRefKey())) && 
						(null!=catDetails.getSfCatAssociationId() && !catDetails.getSfCatAssociationId().equals("")))
				{
					try
					{
						payloadObj = new JSONObject();
						payloadObj.put("DataCategoryGroupName", "Product_and_Categories");
						payloadObj.put("DataCategoryName", catDetails.getRefKey());
						payloadObj.put("ParentId", details.getSfRecordId());
						/*
						 * CALL CATEGORY CREATION ASSOICATION REST API
						 */
						responseObject = caller.callSFRestAPIForOtherOperations(categoryAPIURL+"/"+catDetails.getSfCatAssociationId(),SalesForceConstants.METHOD_DELETE, payloadObj.toString());
						if(null!=responseObject)
						{
							if(responseObject.getString("CALL_STATUS").equals("SUCCESS"))
							{
								logger.info("deleteSFCategoriesOperation :: Sales Force Category Mapping Deletion API Executed Successfully for "+ details.getDocumentId()+" of "+ details.getLocale()+". For Ref Key :: > "+ catDetails.getRefKey());
							}
							else
							{
								logger.info("deleteSFCategoriesOperation :: Failed to execute Sales Force Category Mapping Deletion API for "+ details.getDocumentId()+" of "+ details.getLocale()+" for Ref Key :: >"+catDetails.getRefKey());
								// capture error Message
								try
								{
									if(null!=responseObject.getJSONArray("ERROR_DATA"))
									{
										// set processing Status as Failure
										catDetails.setSfMappingStatus("FAILURE");
										// set Error Message received from Sales Force API Call
										catDetails.setErrorMessage(responseObject.getJSONArray("ERROR_DATA").toString());
									}
								}
								catch(Exception e) 
								{
									Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "deleteSFCategoriesOperation()", e);
									// set Error Message as Failed to read Error JSON Array from API Call
									catDetails = captureCategoryErrorMessage(catDetails, e);
								}
							}
						}
					}
					catch(Exception e)
					{
						Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "deleteSFCategoriesOperation()", e);
						// capture errorMessage
						catDetails = captureCategoryErrorMessage(catDetails, e);
					}
				}
				else
				{
					// set processing Status as Failure
					catDetails.setSfMappingStatus("FAILURE");
					// set error Message Either Ref Key or Category SF Association ID is NULL. E.G Not Mapped Yet
					catDetails.setErrorMessage("Failed to Delete. Either Ref Key or Category SF Association ID is NULL. E.G Not Mapped Yet.");
				}
			}
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "deleteSFCategoriesOperation()", e);
		}
		return categoryList;
	}

	private List<CategoryDetails> addSFCategoriesOperation(ContentDetails details, List<CategoryDetails> categoryList)
	{
		try
		{
			CategoryDetails catDetails = null;
			JSONObject responseObject = null;
			JSONObject payloadObj = null;
			for(int a=0;a<categoryList.size();a++)
			{
				catDetails = (CategoryDetails)categoryList.get(a);
				/*
				 * ONLY WHEN KA REF KEY IS NOT NULL
				 * AND SF ASSOCIATION ID IS NULL (E.G CATEGORY MAPPING HAS ALREADY HAPPENED)
				 */
				if((null!=catDetails.getRefKey() && !"".equals(catDetails.getRefKey())) && 
						(null==catDetails.getSfCatAssociationId() || catDetails.getSfCatAssociationId().equals("")))
				{
					try
					{
						payloadObj = new JSONObject();
						payloadObj.put("DataCategoryGroupName", "Product_and_Categories");
//						payloadObj.put("DataCategoryGroupName", "SFDC");
						payloadObj.put("DataCategoryName", catDetails.getRefKey());
						payloadObj.put("ParentId", details.getSfRecordId());
						
						/*
						 * CALL CATEGORY CREATION ASSOICATION REST API
						 */
						responseObject = caller.callSFRestAPIForOtherOperations(categoryAPIURL,SalesForceConstants.METHOD_POST, payloadObj.toString());
						if(null!=responseObject)
						{
							if(responseObject.getString("CALL_STATUS").equals("SUCCESS"))
							{
								logger.info("addSFCategoriesOperation :: Sales Force Category Mapping API Executed Successfully for "+ details.getDocumentId()+" of "+ details.getLocale()+". For Ref Key :: > "+ catDetails.getRefKey());
								try
								{
									if(null!=responseObject.getJSONObject("FINAL_DATA").getString("id"))
									{
										catDetails.setSfCatAssociationId(responseObject.getJSONObject("FINAL_DATA").getString("id"));
										// set Mapping Status as Success
										catDetails.setSfMappingStatus("SUCCESS");
									}
								}
								catch(Exception e)
								{
									Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "addSFCategoriesOperation()", e);
									// capture error Message
									catDetails = captureCategoryErrorMessage(catDetails, e);
								}
							}
							else
							{
								logger.info("addSFCategoriesOperation :: Failed to execute Sales Force Category Mapping API for "+ details.getDocumentId()+" of "+ details.getLocale()+" for Ref Key :: >"+catDetails.getRefKey());
								// capture error Message
								try
								{
									if(null!=responseObject.getJSONArray("ERROR_DATA"))
									{
										// set processing Status as Failure
										catDetails.setSfMappingStatus("FAILURE");
										// set Error Message received from Sales Force API Call
										catDetails.setErrorMessage(responseObject.getJSONArray("ERROR_DATA").toString());
									}
								}
								catch(Exception e) 
								{
									Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "addSFCategoriesOperation()", e);
									// set Error Message as Failed to read Error JSON Array from API Call
									catDetails = captureCategoryErrorMessage(catDetails, e);
								}
							}
						}
					}
					catch(Exception e)
					{
						Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "addSFCategoriesOperation()", e);
						// capture errorMessage
						catDetails = captureCategoryErrorMessage(catDetails, e);
					}
				}
				else
				{
					// set processing Status as Failure
					catDetails.setSfMappingStatus("FAILURE");
					// set error Message Either Ref Key or Category SF Association ID is NULL. E.G Not Mapped Yet
					catDetails.setErrorMessage("Failed to Add Category Ref Key is NULL.");
				}
			}
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "addSFCategoriesOperation()", e);
		}
		return categoryList;
	}

	
	
	private CategoryDetails captureCategoryErrorMessage(CategoryDetails details, Exception e)
	{
		try
		{
			// set sfProcessingstatus to Failure
			details.setSfMappingStatus("FAILURE");

			// only when Exception is not NULL
			if(null!=e)
			{
				writer = new StringWriter();
				print = new PrintWriter(writer);
				e.printStackTrace();

				// set errorMessage
				details.setErrorMessage(writer.toString());
			}

			// clear all printers & writers
			if(null!=writer)
			{
				writer.flush();
				writer.close();
			}
			writer = null;
			if(null!=print)
			{
				print.flush();
				print.close();
			}
			print=  null;
		}
		catch(Exception w)
		{
			Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "captureCategoryErrorMessage()", w);
		}
		return details;
	}


	private void startMasterIdentifiersPublishProcessing()
	{
		try
		{
			transactionDAO = new TransactionDAO();
			String tableName="SF_PUBLISH_TRANSACTIONS";
			boolean tableCreated = transactionDAO.checkTableExists(tableName, "PUBLISH");
			if(tableCreated==true)
			{
				logger.info("startMasterIdentifiersPublishProcessing :: Publish Transactions Table Already Exists or Created Successfully. Proceed for Publishing Master Identifiers Data.");
				caller = new SalesForceAPICaller();
				List<String> channelList = transactionDAO.getIMChannelDetails();
				if(null!=channelList && channelList.size()>0)
				{
					String channelRefKey=null;
					for(int at=0;at<channelList.size();at++)
					{
						channelRefKey=channelList.get(at).toString();
						/*
						 * FETCH DATA FROM IM CHANNEL TABLE FOR THE PROCESSING LOCALE
						 * AND START IDENTIFYING IMAGES & LINKS & ATTACHMENTS
						 * AND THEN START CREATING THEM IN SALESFORCE
						 * 
						 * ONLY MASTER IDENTIFIERS FIRST
						 */
						ContentDetails details = null;
						List<ContentDetails> masterIdentifiersList = transactionDAO.getIMDocumentsList(channelRefKey,"MASTERS");
						if(null!=masterIdentifiersList && masterIdentifiersList.size()>0)
						{
							logger.info("startMasterIdentifiersPublishProcessing :: Total Master Identifiers Found are :: >"+ masterIdentifiersList.size());
							JSONObject salesForceObj = null;
							JSONObject responseObject = null;
							for(int a=0;a<masterIdentifiersList.size();a++)
							{
								details = (ContentDetails)masterIdentifiersList.get(a);
								// set channel
								details.setChannelRefKey(channelRefKey.toUpperCase());
								// set document as Master Identifier
								details.setSfMasterIdentifier("TRUE");
								logger.info("startMasterIdentifiersPublishProcessing :: ######## Starting Publishing Master Identifier "+(a+1)+" / "+ masterIdentifiersList.size()+" ############");
								if(null!=details.getSfRecordId() && !"".equals(details.getSfRecordId()))
								{
									try
									{
										salesForceObj = new JSONObject();
										salesForceObj.put("publishStatus", "Online");
										/*
										 * SALES FORCE CREATE / UPDATE OPERATION
										 */
										if(null!=salesForceObj)
										{
											responseObject = caller.callSFRestAPIForOtherOperations(publishMasterArticleURL+details.getSfRecordId(), SalesForceConstants.METHOD_PATCH, salesForceObj.toString());
											if(null!=responseObject)
											{
												if(responseObject.getString("CALL_STATUS").equals("SUCCESS"))
												{
													logger.info("startMasterIdentifiersPublishProcessing :: Sales Force Publish Master Article API Executed Successfully for "+ details.getDocumentId()+" of "+ details.getLocale()+" Where Sales Force Id :: >"+ details.getSfRecordId());
													// SET PROCESSING STATUS TO SUCCESS
													details.setSfProcessingStatus("SUCCESS");
												}
												else
												{
													logger.info("startMasterIdentifiersPublishProcessing :: Failed to execute Sales Force Publish Master Article API for "+ details.getDocumentId()+" of "+ details.getLocale()+" Where Sales Force Id :: >"+details.getSfRecordId());
													// capture error Message
													try
													{
														if(null!=responseObject.getJSONArray("ERROR_DATA"))
														{
															// set processing Status as Failure
															details.setSfProcessingStatus("FAILURE");
															// set remarks
															details.setSfRemarks("Failed to execute Sales Force Publish Master Article API.");
															// set Error Message received from Sales Force API Call
															details.setErrorMessage(responseObject.getJSONArray("ERROR_DATA").toString());
														}
													}
													catch(Exception e) 
													{
														Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "startMasterIdentifiersPublishProcessing()", e);
														// set Error Message as Failed to read Error JSON Array from API Call
														details = captureErrorMessage(details, "Failed to read Error JSON Array from Update API Call.", e);
													}
												}
											}
											responseObject  =null;
										}
										salesForceObj = null;
									}
									catch(Exception e)
									{
										Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "startMasterIdentifiersPublishProcessing()", e);
										// capture error Message
										details = captureErrorMessage(details, "Please refer Error Message, Sales Force API Execution Failed.", e);
									}
									salesForceObj = null;
									responseObject = null;
								}
								else
								{
									// set processing status as FAILURE
									details.setSfProcessingStatus("FAILURE");
									// set REMARKS
									details.setSfRemarks("Master Identifier does not exist in Sales Force.");
								}
								/*
								 * PROCEED FOR SAVING THE DOCUMENT IN DATABASE WITH SALES FORCE OPERATION
								 * ALSO UPDATE THE INNERLINKS DETAILS WITH STATUS
								 */
								transactionDAO.updateSFPublishTransactionDetails(details);
								details = null;
							}
						}
						masterIdentifiersList = null;
						channelRefKey  =null;
					}
				}
				channelList  =null;
			}
			else
			{
				logger.info("startMasterIdentifiersPublishProcessing :: Failed to check whether Table {"+tableName+"} Exists and Create if Not.");
			}
			tableName = null;
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "startMasterIdentifiersPublishProcessing()", e);
		}
		finally
		{
			try
			{
				if(null!=transactionDAO.conn)
				{
					transactionDAO.conn.close();
				}
				transactionDAO.conn  =null;
			}
			catch(Exception e)
			{
				Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "startMasterIdentifiersPublishProcessing()", e);
			}
			transactionDAO= null;
			caller = null;
		}
	}

	private void startTranslationsPublishProcessing()
	{
		try
		{
			transactionDAO = new TransactionDAO();
			String tableName="SF_PUBLISH_TRANSACTIONS";
			boolean tableCreated = transactionDAO.checkTableExists(tableName, "PUBLISH");
			if(tableCreated==true)
			{
				logger.info("startTranslationsPublishProcessing :: Publish Transactions Table Already Exists or Created Successfully. Proceed for Publishing Translations Data.");
				caller = new SalesForceAPICaller();
				List<String> channelList = transactionDAO.getIMChannelDetails();
				if(null!=channelList && channelList.size()>0)
				{
					String channelRefKey=null;
					for(int at=0;at<channelList.size();at++)
					{
						channelRefKey=channelList.get(at).toString();
						/*
						 * FETCH DATA FROM IM CHANNEL TABLE FOR THE PROCESSING LOCALE
						 * AND START IDENTIFYING IMAGES & LINKS & ATTACHMENTS
						 * AND THEN START CREATING THEM IN SALESFORCE
						 * 
						 * ONLY TRANSLATIONS
						 */
						ContentDetails details = null;
						List<ContentDetails> translationsList = transactionDAO.getIMDocumentsList(channelRefKey,"TRANSLATIONS");
						if(null!=translationsList && translationsList.size()>0)
						{
							logger.info("startTranslationsPublishProcessing :: Total Translations Found are :: >"+ translationsList.size());
							JSONObject salesForceObj = null;
							JSONObject responseObject = null;
							for(int a=0;a<translationsList.size();a++)
							{
								details = (ContentDetails)translationsList.get(a);
								// set channel
								details.setChannelRefKey(channelRefKey.toUpperCase());
								// set document as Translations
								details.setSfMasterIdentifier("FALSE");
								logger.info("startTranslationsPublishProcessing :: ######## Starting Publishing Translations "+(a+1)+" / "+ translationsList.size()+" ############");
								if(null!=details.getSfRecordId() && !"".equals(details.getSfRecordId()))
								{
									try
									{
										/*
										 * FOR TRANSLATIONS - MARK THEM AS COMPLETE
										 */
										salesForceObj = new JSONObject();
										salesForceObj.put("complete", "true");
										/*
										 * SALES FORCE CREATE / UPDATE OPERATION
										 */
										if(null!=salesForceObj)
										{
											responseObject = caller.callSFRestAPIForOtherOperations(publishTranslationArticleURL+details.getSfRecordId(), SalesForceConstants.METHOD_PATCH, salesForceObj.toString());
											if(null!=responseObject)
											{
												if(responseObject.getString("CALL_STATUS").equals("SUCCESS"))
												{
													logger.info("startTranslationsPublishProcessing :: Sales Force Publish Translations API Executed Successfully for "+ details.getDocumentId()+" of "+ details.getLocale()+" Where Sales Force Id :: >"+ details.getSfRecordId());
													// SET PROCESSING STATUS TO SUCCESS
													details.setSfProcessingStatus("SUCCESS");
												}
												else
												{
													logger.info("startTranslationsPublishProcessing :: Failed to execute Sales Force Translations Article API for "+ details.getDocumentId()+" of "+ details.getLocale()+" Where Sales Force Id :: >"+details.getSfRecordId());
													// capture error Message
													try
													{
														if(null!=responseObject.getJSONArray("ERROR_DATA"))
														{
															// set processing Status as Failure
															details.setSfProcessingStatus("FAILURE");
															// set remarks
															details.setSfRemarks("Failed to execute Sales Force Publish Translation API.");
															// set Error Message received from Sales Force API Call
															details.setErrorMessage(responseObject.getJSONArray("ERROR_DATA").toString());
														}
													}
													catch(Exception e) 
													{
														Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "startTranslationsPublishProcessing()", e);
														// set Error Message as Failed to read Error JSON Array from API Call
														details = captureErrorMessage(details, "Failed to read Error JSON Array from Update API Call.", e);
													}
												}
											}
											responseObject  =null;
										}
										salesForceObj = null;
									}
									catch(Exception e)
									{
										Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "startTranslationsPublishProcessing()", e);
										// capture error Message
										details = captureErrorMessage(details, "Please refer Error Message, Sales Force API Execution Failed.", e);
									}
									salesForceObj = null;
									responseObject = null;
								}
								else
								{
									// set processing status as FAILURE
									details.setSfProcessingStatus("FAILURE");
									// set REMARKS
									details.setSfRemarks("Translation does not exist in Sales Force.");
								}
								/*
								 * PROCEED FOR SAVING THE DOCUMENT IN DATABASE WITH SALES FORCE OPERATION
								 * ALSO UPDATE THE INNERLINKS DETAILS WITH STATUS
								 */
								transactionDAO.updateSFPublishTransactionDetails(details);
								details = null;
							}
						}
						translationsList = null;
						channelRefKey  =null;
					}
				}
				channelList  =null;
			}
			else
			{
				logger.info("startTranslationsPublishProcessing :: Failed to check whether Table {"+tableName+"} Exists and Create if Not.");
			}
			tableName = null;
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "startTranslationsPublishProcessing()", e);
		}
		finally
		{
			try
			{
				if(null!=transactionDAO.conn)
				{
					transactionDAO.conn.close();
				}
				transactionDAO.conn  =null;
			}
			catch(Exception e)
			{
				Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "startTranslationsPublishProcessing()", e);
			}
			transactionDAO= null;
			caller = null;
		}
	}

	private void startMasterIdentifiersUnPublishProcessing()
	{
		try
		{
			transactionDAO = new TransactionDAO();
			String tableName="SF_UNPUBLISH_TRANSACTIONS";
			boolean tableCreated = transactionDAO.checkTableExists(tableName, "UNPUBLISH");
			if(tableCreated==true)
			{
				logger.info("startMasterIdentifiersUnPublishProcessing :: UnPublish Transactions Table Already Exists or Created Successfully. Proceed for UnPublishing Master Identifiers Data.");
				caller = new SalesForceAPICaller();
				List<String> channelList = transactionDAO.getIMChannelDetails();
				if(null!=channelList && channelList.size()>0)
				{
					String channelRefKey=null;
					for(int at=0;at<channelList.size();at++)
					{
						channelRefKey=channelList.get(at).toString();
						/*
						 * FETCH DATA FROM IM CHANNEL TABLE FOR THE PROCESSING LOCALE
						 * AND START IDENTIFYING IMAGES & LINKS & ATTACHMENTS
						 * AND THEN START CREATING THEM IN SALESFORCE
						 * 
						 * ONLY MASTER IDENTIFIERS FIRST
						 */
						ContentDetails details = null;
						List<ContentDetails> masterIdentifiersList = transactionDAO.getIMDocumentsList(channelRefKey,"MASTERS");
						if(null!=masterIdentifiersList && masterIdentifiersList.size()>0)
						{
							logger.info("startMasterIdentifiersUnPublishProcessing :: Total Master Identifiers Found are :: >"+ masterIdentifiersList.size());
							JSONObject salesForceObj = null;
							JSONObject responseObject = null;
							for(int a=0;a<masterIdentifiersList.size();a++)
							{
								details = (ContentDetails)masterIdentifiersList.get(a);
								// set channel
								details.setChannelRefKey(channelRefKey.toUpperCase());
								// set document as Master Identifier
								details.setSfMasterIdentifier("TRUE");
								logger.info("startMasterIdentifiersUnPublishProcessing :: ######## Starting UnPublishing Master Identifier "+(a+1)+" / "+ masterIdentifiersList.size()+" ############");
								if(null!=details.getSfArticleId() && !"".equals(details.getSfArticleId()))
								{
									try
									{
										salesForceObj = new JSONObject();
										salesForceObj.put("articleId", details.getSfArticleId());
										/*
										 * SALES FORCE CREATE / UPDATE OPERATION
										 */
										if(null!=salesForceObj)
										{
											responseObject = caller.callSFRestAPIForOtherOperations(publishMasterArticleURL, SalesForceConstants.METHOD_POST, salesForceObj.toString());
											if(null!=responseObject)
											{
												if(responseObject.getString("CALL_STATUS").equals("SUCCESS"))
												{
													logger.info("startMasterIdentifiersUnPublishProcessing :: Sales Force UnPublish Master Article API Executed Successfully for "+ details.getDocumentId()+" of "+ details.getLocale()+" Where Sales Force Id :: >"+ details.getSfRecordId());
													/*
													 * CHECK FOR NEW SF DOCUMENT ID
													 */
													try
													{
														if(null!=responseObject.getJSONObject("FINAL_DATA").getString("id"))
														{
															details.setSfNewDraftRecordId(responseObject.getJSONObject("FINAL_DATA").getString("id"));
															// SET PROCESSING STATUS TO SUCCESS
															details.setSfProcessingStatus("SUCCESS");
														}
													}
													catch(Exception e)
													{
														// capture error Message
														details = captureErrorMessage(details, "Failed to read Sales Force Knowledge Article Id for new created Draft document.", e);
													}
												}
												else
												{
													logger.info("startMasterIdentifiersUnPublishProcessing :: Failed to execute Sales Force UnPublish Master Article API for "+ details.getDocumentId()+" of "+ details.getLocale()+" Where Sales Force Id :: >"+details.getSfRecordId());
													// capture error Message
													try
													{
														if(null!=responseObject.getJSONArray("ERROR_DATA"))
														{
															// set processing Status as Failure
															details.setSfProcessingStatus("FAILURE");
															// set remarks
															details.setSfRemarks("Failed to execute Sales Force UnPublish Master Article API.");
															// set Error Message received from Sales Force API Call
															details.setErrorMessage(responseObject.getJSONArray("ERROR_DATA").toString());
														}
													}
													catch(Exception e) 
													{
														Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "startMasterIdentifiersUnPublishProcessing()", e);
														// set Error Message as Failed to read Error JSON Array from API Call
														details = captureErrorMessage(details, "Failed to read Error JSON Array from Update API Call.", e);
													}
												}
											}
											responseObject  =null;
										}
										salesForceObj = null;
									}
									catch(Exception e)
									{
										Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "startMasterIdentifiersUnPublishProcessing()", e);
										// capture error Message
										details = captureErrorMessage(details, "Please refer Error Message, Sales Force API Execution Failed.", e);
									}
									salesForceObj = null;
									responseObject = null;
								}
								else
								{
									// set processing status as FAILURE
									details.setSfProcessingStatus("FAILURE");
									// set REMARKS
									details.setSfRemarks("Master Identifier does not exist in Sales Force.");
								}
								/*
								 * PROCEED FOR SAVING THE DOCUMENT IN DATABASE WITH SALES FORCE OPERATION
								 * ALSO UPDATE THE INNERLINKS DETAILS WITH STATUS
								 */
								transactionDAO.updateSFUnPublishTransactionDetails(details);
								details = null;
							}
						}
						masterIdentifiersList = null;
						channelRefKey  =null;
					}
				}
				channelList  =null;
			}
			else
			{
				logger.info("startMasterIdentifiersUnPublishProcessing :: Failed to check whether Table {"+tableName+"} Exists and Create if Not.");
			}
			tableName = null;
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "startMasterIdentifiersUnPublishProcessing()", e);
		}
		finally
		{
			try
			{
				if(null!=transactionDAO.conn)
				{
					transactionDAO.conn.close();
				}
				transactionDAO.conn  =null;
			}
			catch(Exception e)
			{
				Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "startMasterIdentifiersUnPublishProcessing()", e);
			}
			transactionDAO= null;
			caller = null;
		}
	}

	private void startTranslationsUnPublishProcessing()
	{
		try
		{
			transactionDAO = new TransactionDAO();
			String tableName="SF_UNPUBLISH_TRANSACTIONS";
			boolean tableCreated = transactionDAO.checkTableExists(tableName, "UNPUBLISH");
			if(tableCreated==true)
			{
				logger.info("startTranslationsUnPublishProcessing :: UnPublish Transactions Table Already Exists or Created Successfully. Proceed for UnPublishing Translations Data.");
				caller = new SalesForceAPICaller();
				List<String> channelList = transactionDAO.getIMChannelDetails();
				if(null!=channelList && channelList.size()>0)
				{
					String channelRefKey=null;
					for(int at=0;at<channelList.size();at++)
					{
						channelRefKey=channelList.get(at).toString();
						/*
						 * FETCH DATA FROM IM CHANNEL TABLE FOR THE PROCESSING LOCALE
						 * AND START IDENTIFYING IMAGES & LINKS & ATTACHMENTS
						 * AND THEN START CREATING THEM IN SALESFORCE
						 * 
						 * ONLY TRANSLATIONS
						 */
						ContentDetails details = null;
						List<ContentDetails> translationsList = transactionDAO.getIMDocumentsList(channelRefKey,"TRANSLATIONS");
						if(null!=translationsList && translationsList.size()>0)
						{
							logger.info("startTranslationsUnPublishProcessing :: Total Translations Found are :: >"+ translationsList.size());
							JSONObject payloadObj = null;
							JSONObject responseObject = null;
							for(int a=0;a<translationsList.size();a++)
							{
								details = (ContentDetails)translationsList.get(a);
								// set channel
								details.setChannelRefKey(channelRefKey.toUpperCase());
								// set document as Translations
								details.setSfMasterIdentifier("FALSE");
								logger.info("startTranslationsUnPublishProcessing :: ######## Starting UnPublishing Translations "+(a+1)+" / "+ translationsList.size()+" ############");
								if(null!=details.getSfArticleId() && !"".equals(details.getSfArticleId()))
								{
									try
									{
										payloadObj = new JSONObject();
										payloadObj.put("articleId", details.getSfArticleId());
										/*
										 * KA LOCALES Vs SALES FORCE LOCALES
										 * de_DE = de
										 * fr_FR = fr
										 * it_IT = IT
										 * pt_BR = pt_BR
										 * pt_PT = pt_PT
										 * en_GB = en_GB
										 * es_ES = es
										 * en_US = en_US
										 */
										if(details.getLocale().equals("de_DE"))
										{
											payloadObj.put("language", "de");
										}
										else if(details.getLocale().equals("fr_FR"))
										{
											payloadObj.put("language", "fr");
										}
										else if(details.getLocale().equals("it_IT"))
										{
											payloadObj.put("language", "it");
										}
										else if(details.getLocale().equals("es_ES"))
										{
											payloadObj.put("language", "es");
										}
										else
										{
											payloadObj.put("language", details.getLocale());
										}
										
										payloadObj.put("sendEmailNotification", false);
										logger.info("Submit for Translation :: Payload :: >"+ payloadObj.toString());
										responseObject = caller.callSFRestAPIForOtherOperations(translationAPIURL,SalesForceConstants.METHOD_POST, payloadObj.toString());
										if(responseObject.getString("CALL_STATUS").equals("SUCCESS"))
										{
											logger.info("startTranslationsUnPublishProcessing :: Sales Force Create Translation API Executed Successfully for "+ details.getDocumentId()+" of "+ details.getLocale()+". Proceed for Reading SalesForce Translation Record Id (articleVersionId).");
											try
											{
												if(null!=responseObject.getJSONObject("FINAL_DATA").getString("articleVersionId"))
												{
													details.setSfNewDraftRecordId(responseObject.getJSONObject("FINAL_DATA").getString("articleVersionId"));
													logger.info("startTranslationsUnPublishProcessing :: Sales Force UnPublish Translations API Executed Successfully for "+ details.getDocumentId()+" of "+ details.getLocale()+" Where Sales Force Id :: >"+ details.getSfRecordId());
													// SET PROCESSING STATUS TO SUCCESS
													details.setSfProcessingStatus("SUCCESS");
												}
											}
											catch(Exception e)
											{
												Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "createSFTranslationOperation()", e);
												// capture error Message
												details = captureErrorMessage(details, "Failed to read Sales Force Translation Article Version Id for new created document draft.", e);
											}
										}
										else
										{
											logger.info("startTranslationsUnPublishProcessing :: Failed to execute Sales Force Translations Article API for "+ details.getDocumentId()+" of "+ details.getLocale()+" Where Sales Force Id :: >"+details.getSfRecordId());
											// capture error Message
											try
											{
												if(null!=responseObject.getJSONArray("ERROR_DATA"))
												{
													// set processing Status as Failure
													details.setSfProcessingStatus("FAILURE");
													// set remarks
													details.setSfRemarks("Failed to execute Sales Force UnPublish Translation API.");
													// set Error Message received from Sales Force API Call
													details.setErrorMessage(responseObject.getJSONArray("ERROR_DATA").toString());
												}
											}
											catch(Exception e) 
											{
												Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "startTranslationsUnPublishProcessing()", e);
												// set Error Message as Failed to read Error JSON Array from API Call
												details = captureErrorMessage(details, "Failed to read Error JSON Array from Update API Call.", e);
											}
										}
									}
									catch(Exception e)
									{
										Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "startTranslationsUnPublishProcessing()", e);
										// capture error Message
										details = captureErrorMessage(details, "Please refer Error Message, Sales Force API Execution Failed.", e);
									}
									payloadObj = null;
									responseObject = null;
								}
								else
								{
									// set processing status as FAILURE
									details.setSfProcessingStatus("FAILURE");
									// set REMARKS
									details.setSfRemarks("Translation does not exist in Sales Force.");
								}
								/*
								 * PROCEED FOR SAVING THE DOCUMENT IN DATABASE WITH SALES FORCE OPERATION
								 * ALSO UPDATE THE INNERLINKS DETAILS WITH STATUS
								 */
								transactionDAO.updateSFUnPublishTransactionDetails(details);
								details = null;
							}
						}
						translationsList = null;
						channelRefKey  =null;
					}
				}
				channelList  =null;
			}
			else
			{
				logger.info("startTranslationsUnPublishProcessing :: Failed to check whether Table {"+tableName+"} Exists and Create if Not.");
			}
			tableName = null;
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "startTranslationsUnPublishProcessing()", e);
		}
		finally
		{
			try
			{
				if(null!=transactionDAO.conn)
				{
					transactionDAO.conn.close();
				}
				transactionDAO.conn  =null;
			}
			catch(Exception e)
			{
				Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "startTranslationsUnPublishProcessing()", e);
			}
			transactionDAO= null;
			caller = null;
		}
	}

	private void startCategoryUntagging()
	{
		try
		{
			transactionDAO = new TransactionDAO();
			caller = new SalesForceAPICaller();
			payloadUtils = new GenerateSFChannelPayloadUtils();
			List<String> channelList = transactionDAO.getIMChannelDetails();
			if(null!=channelList && channelList.size()>0)
			{
				String channelRefKey=null;
				for(int at=0;at<channelList.size();at++)
				{
					channelRefKey=channelList.get(at).toString();
					/*
					 * FETCH DATA FROM IM CHANNEL TABLE FOR THE PROCESSING LOCALE
					 * AND START IDENTIFYING IMAGES & LINKS & ATTACHMENTS
					 * AND THEN START CREATING THEM IN SALESFORCE
					 * 
					 * ONLY MASTER IDENTIFIERS FIRST
					 */
					ContentDetails details = null;
					List<CategoryDetails> categoryList = null;
					List<ContentDetails> masterIdentifiersList = transactionDAO.getIMDocumentsList(channelRefKey,"MASTERS");
					if(null!=masterIdentifiersList && masterIdentifiersList.size()>0)
					{
						logger.info("startCategoryUntagging :: Total Master Identifiers Found are :: >"+ masterIdentifiersList.size());
						for(int a=0;a<masterIdentifiersList.size();a++)
						{
							details = (ContentDetails)masterIdentifiersList.get(a);
							logger.info("startCategoryUntagging :: ######## Starting Master Identifier "+(a+1)+" / "+ masterIdentifiersList.size()+" ############");
							/*
							 * RETRIEVE CATEGORY LIST FOR THE PROCESSED DOCUMENT
							 */
							try
							{
								categoryList = transactionDAO.getDocumentCategoryList(channelRefKey, details.getDocumentId(), details.getLocale(),"DELETE");
								if(null!=categoryList && categoryList.size()>0)
								{
									logger.info("startCategoryUntagging :: Total Categories found for "+details.getDocumentId()+" of "+details.getLocale()+" are :: >"+ categoryList.size());
									/*
									 * START CATEGORY UNTAGGING PROCESSING
									 */
									categoryList = deleteSFCategoriesOperation(details, categoryList);
								}
								else
								{
									logger.info("startCategoryUntagging :: Category Processing Not Required, as No Categories found for "+details.getDocumentId()+" of "+details.getLocale());
								}
							}
							catch(Exception e)
							{
								Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "startCategoryUntagging()", e);
								// capture error Message
								details = captureErrorMessage(details, "Failed to fetch Categories Data for Processing Document from DB.", e);
							}
							
							/*
							 * PROCEED FOR SAVING THE DOCUMENT IN DATABASE WITH SALES FORCE OPERATION
							 * ALSO UPDATE THE INNERLINKS DETAILS WITH STATUS
							 */
							transactionDAO.updateCategoryDeleteTransactions(details, categoryList);
							
							// set CATEGORY LIST TO NULL
							categoryList=  null;
							
							details = null;
							logger.info("startCategoryUntagging :: ######## Ending Master Identifier "+(a+1)+" / "+ masterIdentifiersList.size()+" ############");
							break;
						}
					}
					masterIdentifiersList = null;
					channelRefKey  =null;
					break;
				}
			}
			channelList  =null;
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "startCategoryUntagging()", e);
		}
		finally
		{
			try
			{
				if(null!=transactionDAO.conn)
				{
					transactionDAO.conn.close();
				}
				transactionDAO.conn  =null;
			}
			catch(Exception e)
			{
				Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "startCategoryUntagging()", e);
			}
			transactionDAO= null;
			caller = null;
			payloadUtils = null;
		}
	}

	private void startCategoryTagging()
	{
		try
		{
			transactionDAO = new TransactionDAO();
			caller = new SalesForceAPICaller();
			payloadUtils = new GenerateSFChannelPayloadUtils();
			List<String> channelList = transactionDAO.getIMChannelDetails();
			if(null!=channelList && channelList.size()>0)
			{
				String channelRefKey=null;
				for(int at=0;at<channelList.size();at++)
				{
					channelRefKey=channelList.get(at).toString();
					/*
					 * FETCH DATA FROM IM CHANNEL TABLE FOR THE PROCESSING LOCALE
					 * AND START IDENTIFYING IMAGES & LINKS & ATTACHMENTS
					 * AND THEN START CREATING THEM IN SALESFORCE
					 * 
					 * ONLY MASTER IDENTIFIERS FIRST
					 */
					ContentDetails details = null;
					List<CategoryDetails> categoryList = null;
					List<ContentDetails> masterIdentifiersList = transactionDAO.getIMDocumentsList(channelRefKey,"MASTERS");
					if(null!=masterIdentifiersList && masterIdentifiersList.size()>0)
					{
						logger.info("startCategoryTagging :: Total Master Identifiers Found are :: >"+ masterIdentifiersList.size());
						for(int a=0;a<masterIdentifiersList.size();a++)
						{
							details = (ContentDetails)masterIdentifiersList.get(a);
							logger.info("startCategoryTagging :: ######## Starting Master Identifier "+(a+1)+" / "+ masterIdentifiersList.size()+" ############");
							/*
							 * RETRIEVE CATEGORY LIST FOR THE PROCESSED DOCUMENT
							 */
							try
							{
								categoryList = transactionDAO.getDocumentCategoryList(channelRefKey, details.getDocumentId(), details.getLocale(),"ADD");
								if(null!=categoryList && categoryList.size()>0)
								{
									logger.info("startCategoryTagging :: Total Categories found for "+details.getDocumentId()+" of "+details.getLocale()+" are :: >"+ categoryList.size());
									/*
									 * START CATEGORY UNTAGGING PROCESSING
									 */
									categoryList = addSFCategoriesOperation(details, categoryList);
								}
								else
								{
									logger.info("startCategoryTagging :: Category Processing Not Required, as No Categories found for "+details.getDocumentId()+" of "+details.getLocale());
								}
							}
							catch(Exception e)
							{
								Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "startCategoryTagging()", e);
								// capture error Message
								details = captureErrorMessage(details, "Failed to fetch Categories Data for Processing Document from DB.", e);
							}
							
							/*
							 * PROCEED FOR SAVING THE DOCUMENT IN DATABASE WITH SALES FORCE OPERATION
							 * ALSO UPDATE THE INNERLINKS DETAILS WITH STATUS
							 */
							transactionDAO.updateCategoryAddTransactions(details, categoryList);
							
							// set CATEGORY LIST TO NULL
							categoryList=  null;
							
							details = null;
							logger.info("startCategoryUntagging :: ######## Ending Master Identifier "+(a+1)+" / "+ masterIdentifiersList.size()+" ############");
						}
					}
					masterIdentifiersList = null;
					channelRefKey  =null;
				}
			}
			channelList  =null;
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "startCategoryTagging()", e);
		}
		finally
		{
			try
			{
				if(null!=transactionDAO.conn)
				{
					transactionDAO.conn.close();
				}
				transactionDAO.conn  =null;
			}
			catch(Exception e)
			{
				Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "startCategoryTagging()", e);
			}
			transactionDAO= null;
			caller = null;
			payloadUtils = null;
		}
	}

	
}
