package com.hach.dataextraction.impl;

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.hach.dataextraction.dao.TransactionDAO;
import com.hach.dataextraction.utils.common.Utilities;
import com.hach.dataextraction.utils.osvc.OSVCandKAWebServiceCaller;
import com.hach.dataextraction.vo.AttachmentDetails;
import com.hach.dataextraction.vo.AttachmentTypes;
import com.hach.dataextraction.vo.CategoryDetails;
import com.hach.dataextraction.vo.ContentDetails;
import com.hach.dataextraction.vo.ManualLinkDetails;
import com.hach.dataextraction.vo.UserGroupDetails;
import com.hach.dataextraction.vo.ViewDetails;

public class RetrieveManualLinksImpl {

	private Logger logger = Logger.getLogger(RetrieveManualLinksImpl.class);

	private TransactionDAO transactionDAO = null;

	public static void main(String[] args) {
		// initialize loggers
		File f = new File(RetrieveManualLinksImpl.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		PropertyConfigurator.configure(f.getParentFile().getAbsolutePath()+"/log.properties");
		f=  null;
		try
		{
			RetrieveManualLinksImpl impl = new RetrieveManualLinksImpl();
			TransactionDAO dao  =new TransactionDAO();
			List<String> channelList = dao.getChannelDetails();
			if(null!=channelList && channelList.size()>0)
			{
				for(int a=0;a<channelList.size();a++)
				{
//					if(!channelList.get(a).toString().equals("COMMERCIAL_QA"))
					{
						impl.startExecution(channelList.get(a));
					}
				}
			}
			channelList  =null;
			dao = null;
//			
//			impl.startExecution("COMMERCIAL_QA");
//			impl.singleDocOperation("TECHNICAL_QA");
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(RetrieveManualLinksImpl.class.getName(), "main()", e);
		}
		
	}

	
	private void startExecution(String channelRefKey)
	{
		try
		{
			transactionDAO = new TransactionDAO();
			// BEFORE STARTING EXTRACTION - ENSURE ALL DB TABLES FOR CHANNEL IS CREATED IN DB
			String tableName="IM_DOCUMENT_MANUAL_LINKS";
			boolean tableCreated = transactionDAO.checkTableExists(tableName, "MANUAL_LINKS");
			if(tableCreated==true)
			{
				logger.info("startExecution :: Manual Link Table Already Exists or Created Successfully. Proceed for Extraction.");
				/*
				 * START EXTRACTION
				 */
				int limit=20;
				List<ContentDetails> documentsList = new ArrayList<ContentDetails>();
				documentsList = transactionDAO.getDocumentDetails(channelRefKey);
				List<ManualLinkDetails> manualLinksList = new ArrayList<ManualLinkDetails>();
				if(null!=documentsList && documentsList.size()>0)
				{
					logger.info("startExecution :: Total Documents Found for Channel "+ channelRefKey+" are :: >"+ documentsList.size());
					ContentDetails contentDetails = null;
					
					for(int r=0;r<documentsList.size();r++)
					{
						contentDetails = (ContentDetails)documentsList.get(r);
						if(null!=contentDetails && null!=contentDetails.getDocumentId() && !"".equals(contentDetails.getDocumentId()))
						{
							logger.info("--------------- START FETCHING "+ (r+1) +"/"+ documentsList.size());
							/* 
							 * CALL FUNCTION TO FETCH MANUAL LINKS DATA
							 */
							manualLinksList = new ArrayList<ManualLinkDetails>();
							manualLinksList = getData(contentDetails, limit, 0, manualLinksList);
							
							/*
							 * SAVE MANUAL LINK DETAILS IN DATABASE
							 */
							transactionDAO.saveManualLinkDetails(manualLinksList, contentDetails.getDocumentId(), contentDetails.getLocale());
							
							manualLinksList = null;
							logger.info("--------------- END FETCHING "+ (r+1) +"/"+ documentsList.size());
						}
					}
				}
				else
				{
					logger.info("startExecution :: No Documents Found for Channel "+ channelRefKey);
				}
				documentsList = null;
			
			}
			else
			{
				logger.info("startExecution :: Failed to check whether Table {"+tableName+"} Exists and Create if Not.");
			}
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(RetrieveManualLinksImpl.class.getName(), "startExecution()", e);
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
				Utilities.printStackTraceToLogs(RetrieveManualLinksImpl.class.getName(), "startExecution()", e);
			}
			transactionDAO= null;
		}
	}

	private List<ManualLinkDetails> getData(ContentDetails contentDetails, long limit, long offset, List<ManualLinkDetails> manualLinksList)
	{
		List<ManualLinkDetails> tempList = new ArrayList<ManualLinkDetails>();
		tempList = getManualDocumentsData(contentDetails,tempList, limit, offset);
		if(null!=tempList && tempList.size()>0)
		{
			if(null==manualLinksList || manualLinksList.size()<=0)
			{
				manualLinksList = new ArrayList<ManualLinkDetails>();
			}
			manualLinksList.addAll(tempList);
			/*
			 * call recursive function by adding new offset
			 */
			logger.info("---------------- calling recursive function ------------ >> offset value >>" + (offset+limit));
			manualLinksList = getData(contentDetails, limit, (offset+limit), manualLinksList);
		}
		tempList = null;
		return manualLinksList;
	}
	
	private List<ManualLinkDetails> getManualDocumentsData(ContentDetails contentDetails, List<ManualLinkDetails> manualLinkList, long limit, long offset)
	{
		try
		{
			OSVCandKAWebServiceCaller caller = new OSVCandKAWebServiceCaller();
			JSONObject content = null;
			JSONArray items = null;
			JSONObject item = null;
			ManualLinkDetails manualLinkDetails = null;
			try
			{
				JSONObject serviceObject =caller.callKAWebserviceForManualLinks("/content/docId/"+contentDetails.getDocumentId()+"/documentLinks/manual?limit=" + limit + "&offset=" + offset,contentDetails.getLocale()); 
				if(null!=serviceObject)
				{
					if(null!=serviceObject.get("CALL_STATUS") && serviceObject.get("CALL_STATUS").equals("SUCCESS"))
					{
						if(null!=serviceObject.getJSONObject("FINAL_DATA"))
						{
							content = serviceObject.getJSONObject("FINAL_DATA");
							if(null!=content)
							{
								// check for items
								try
								{
									items = content.getJSONArray("items");
								}
								catch(Exception e) {}
								
								if(null!=items && items.length()>0)
								{
									// iterate each item and get the content specific details for the channel
									for (int i = 0; i < items.length(); i++)
									{
										item = (JSONObject)items.get(i);
										if(null!=item)
										{
											manualLinkDetails = new ManualLinkDetails();
											manualLinkDetails.setContentDetails(new ContentDetails());
											manualLinkDetails.getContentDetails().setDocumentId(contentDetails.getDocumentId());
											manualLinkDetails.getContentDetails().setLocale(contentDetails.getLocale());
											manualLinkDetails.getContentDetails().setChannelRefKey(contentDetails.getChannelRefKey());
											
											try
											{
												// recordId
												manualLinkDetails.setRecordId(item.get("recordId").toString());
											}
											catch(Exception e) {Utilities.printStackTraceToLogs(RetrieveManualLinksImpl.class.getName(), "getManualDocumentsData()", e);}
											try
											{
												// versionId
												manualLinkDetails.setVersionId(item.getString("versionId"));
											}
											catch(Exception e) {Utilities.printStackTraceToLogs(RetrieveManualLinksImpl.class.getName(), "getManualDocumentsData()", e);}
											try
											{
												// documentId
												manualLinkDetails.setDocumentId(item.getString("documentId"));
											}
											catch(Exception e) {Utilities.printStackTraceToLogs(RetrieveManualLinksImpl.class.getName(), "getManualDocumentsData()", e);}
											try
											{
												// title
												manualLinkDetails.setTitle(item.getString("title"));
											}
											catch(Exception e) {Utilities.printStackTraceToLogs(RetrieveManualLinksImpl.class.getName(), "getManualDocumentsData()", e);}
											try
											{
												// version
												manualLinkDetails.setVersionNumber(item.getString("version"));
											}
											catch(Exception e) {Utilities.printStackTraceToLogs(RetrieveManualLinksImpl.class.getName(), "getManualDocumentsData()", e);}
											try
											{
												// answerId
												manualLinkDetails.setAnswerId(item.get("answerId").toString());
											}
											catch(Exception e) {Utilities.printStackTraceToLogs(RetrieveManualLinksImpl.class.getName(), "getManualDocumentsData()", e);}
											try
											{
												// locale
												if(null!=item.getJSONObject("locale"))
												{
													manualLinkDetails.setLocale(item.getJSONObject("locale").getString("recordId"));
												}
											}
											catch(Exception e) {Utilities.printStackTraceToLogs(RetrieveManualLinksImpl.class.getName(), "getManualDocumentsData()", e);}

											item = null;
											// if manualLinkDetails recordId is not null = add to vector
											if(null!=manualLinkDetails && null!=manualLinkDetails.getRecordId() && !"".equals(manualLinkDetails.getRecordId()))
											{
												// add to List
												if(null==manualLinkList || manualLinkList.size()<=0)
												{
													manualLinkList = new ArrayList<ManualLinkDetails>();
												}
												manualLinkList.add(manualLinkDetails);
											}
											manualLinkDetails = null;
										}
									}
									logger.info("getManualDocumentsData :: Total Manual Links Found for Document "+ contentDetails.getDocumentId()+" of Locale "+contentDetails.getLocale()+" From OFFSET :: >"+ offset+" are :: >"+ manualLinkList.size());
								}
								else
								{
									logger.info("getManualDocumentsData :: No Manual Links Found for Document "+ contentDetails.getDocumentId()+" of Locale "+contentDetails.getLocale()+" From OFFSET :: >"+ offset);
								}
								items=  null;
							}
							content = null;
						}
					}
					else
					{
						if(null!=serviceObject.get("ERROR_DATA"))
						{
							logger.info("getManualDocumentsData :: Failed to Invoke KA WebService for Document "+ contentDetails.getDocumentId()+" of Locale "+contentDetails.getLocale()+" FROM OFFSET "+ offset+" :: >"+ serviceObject.getJSONObject("ERROR_DATA").toString());
						}
						else if(null!=serviceObject.get("ERROR_MESSAGE") && !"".equals(serviceObject.get("ERROR_MESSAGE")))
						{
							logger.info("getManualDocumentsData :: Failed to Invoke KA WebService for Document "+ contentDetails.getDocumentId()+" of Locale "+contentDetails.getLocale()+" FROM OFFSET "+ offset+" :: >"+ serviceObject.get("ERROR_MESSAGE"));
						}
					}
				}
				serviceObject = null;
			}
			catch (Exception e)
			{
				Utilities.printStackTraceToLogs(RetrieveManualLinksImpl.class.getName(), "getManualDocumentsData()", e);
			}
		}
		catch (Exception e)
		{
			Utilities.printStackTraceToLogs(RetrieveManualLinksImpl.class.getName(), "getManualDocumentsData()", e);
		}

//		if(hasMore==true)
//		{
//			
//			/*
//			 * call rrecursive function
//			 */
//			// increment offset by adding limit to it
//			documentsList = getManualDocumentsData(channelRefKey, documentsList, limit, (offset+limit));
//		}

		return manualLinkList;
	}


	private ContentDetails getContentData(String documentId, OSVCandKAWebServiceCaller caller, ContentDetails contentDetails)
	{
		JSONObject contentObj = null;
		try
		{
			JSONObject serviceObject = caller.callKAWebservice("/content/docId/"+documentId+"/documentLinks/manual");
			if(null!=serviceObject)
			{
				if(null!=serviceObject.get("CALL_STATUS") && serviceObject.get("CALL_STATUS").equals("SUCCESS"))
				{
					if(null!=serviceObject.getJSONObject("FINAL_DATA"))
					{
						contentObj = serviceObject.getJSONObject("FINAL_DATA");
						if(null!=contentObj)
						{
							// set COMPLETE CONTENT OBJ IN CONTENT DETAILS
							contentDetails.setContentObject(contentObj);
							
							// CHECK FOR BASED LOCALE
							try
							{
								// locale
								if(null!=contentObj.getJSONObject("basedLocale"))
								{
									contentDetails.setBaseLocale(contentObj.getJSONObject("basedLocale").getString("recordId"));
								}
							}catch(Exception e) {}
							
							
							/*
							 * check if basedLocale is NULL OR NOT FOUND - THEN THE PROCESSING DOC IS MASTER IDENTIFIER
							 * SET BOTH LOCALES AS SAME
							 */
							if(null==contentDetails.getBaseLocale() || "".equals(contentDetails.getBaseLocale()))
							{
								contentDetails.setBaseLocale(contentDetails.getLocale());
							}

							// CHECK FOR PUBLISH DATE
							try
							{
								if(null!=contentObj.get("publishDate"))
								{
									contentDetails.setPublishDate(contentObj.getString("publishDate"));
								}
							}catch(Exception e) {}
							
							// CHECK FOR CREATE DATE
							try
							{
								if(null!=contentObj.get("createDate"))
								{
									contentDetails.setCreateDate(contentObj.getString("createDate"));
								}
							}catch(Exception e) {}
							
							// CHECK FOR LAST MODIFIED DATE
							try
							{
								if(null!=contentObj.get("lastModifiedDate"))
								{
									contentDetails.setLastModifiedDate(contentObj.getString("lastModifiedDate"));
								}
							}catch(Exception e) {}
							
							// CHECK FOR DISPLAY END DATE
							try
							{
								if(null!=contentObj.get("displayEndDate"))
								{
									contentDetails.setDisplayEndDate(contentObj.getString("displayEndDate"));
								}
							}catch(Exception e) {}
							
							// CHECK FOR OWNER NAME & OWNER EMAIL
							try
							{
								if(null!=contentObj.getJSONObject("owner"))
								{
									contentDetails.setOwnerName(contentObj.getJSONObject("owner").getString("name"));
									contentDetails.setOwnerEmail(contentObj.getJSONObject("owner").getString("email"));
								}
							}catch(Exception e) {}
							
							// CHECK FOR LAST MODIFIER NAME & LAST MODIFIER EMAIL
							try
							{
								if(null!=contentObj.getJSONObject("lastModifier"))
								{
									contentDetails.setLastModifierName(contentObj.getJSONObject("lastModifier").getString("name"));
									contentDetails.setLastModifierEmail(contentObj.getJSONObject("lastModifier").getString("email"));
								}
							}catch(Exception e) {}
							
							// CHECK FOR RESOURCE PATH
							try
							{
								if(null!=contentObj.getString("resourcePath"))
								{
									contentDetails.setResourcePath(contentObj.getString("resourcePath"));
								}
							}catch(Exception e) {};

							// CHECK FOR CATEGORIES
							if(null!=contentObj.getJSONArray("categories") && contentObj.getJSONArray("categories").length()>0)
							{
								JSONObject catObj = null;
								CategoryDetails catDetail = null;
								for(int a=0;a<contentObj.getJSONArray("categories").length();a++)
								{
									try
									{
										catObj = contentObj.getJSONArray("categories").getJSONObject(a);
										if(null!=catObj)
										{
											catDetail =new CategoryDetails();
											// set DocumentId
											catDetail.setDocumentId(contentDetails.getDocumentId());
											// set Locale
											catDetail.setLocale(contentDetails.getLocale());
											try
											{
												// referenceKey
												catDetail.setRefKey(catObj.getString("referenceKey"));
											}catch(Exception e) {};

											try
											{
												// recordId
												catDetail.setRecordId(catObj.getString("recordId"));
											}catch(Exception e) {};

											try
											{
												// name
												catDetail.setName(catObj.getString("name"));
											}catch(Exception e) {};

											try
											{
												// externalType
												catDetail.setExternalType(catObj.getString("externalType"));
											}catch(Exception e) {};

											if(null!=catDetail.getRefKey() && !"".equals(catDetail.getRefKey()))
											{
												/*
												 * call function to fetch Category Hierarchy
												 */
												catDetail = transactionDAO.getCategoryHierarchy(catDetail);
												// add to categoryList
												if(null==contentDetails.getCategoryList() || contentDetails.getCategoryList().size()<=0)
												{
													contentDetails.setCategoryList(new ArrayList<CategoryDetails>());
												}
												contentDetails.getCategoryList().add(catDetail);
											}

											catDetail = null;
										}
										catObj = null;
									}catch(Exception e) {};
								}
							}

							// CHECK FOR VIEWS
							if(null!=contentObj.getJSONArray("views") && contentObj.getJSONArray("views").length()>0)
							{
								JSONObject viewObj = null;
								ViewDetails viewDetail = null;
								for(int a=0;a<contentObj.getJSONArray("views").length();a++)
								{
									try
									{
										viewObj = contentObj.getJSONArray("views").getJSONObject(a);
										if(null!=viewObj)
										{
											viewDetail =new ViewDetails();
											// set DocumentId
											viewDetail.setDocumentId(contentDetails.getDocumentId());
											// set Locale
											viewDetail.setLocale(contentDetails.getLocale());
											try
											{
												// referenceKey
												viewDetail.setRefKey(viewObj.getString("referenceKey"));
											}catch(Exception e) {};

											try
											{
												// recordId
												viewDetail.setRecordId(viewObj.getString("recordId"));
											}catch(Exception e) {};

											try
											{
												// name
												viewDetail.setName(viewObj.getString("name"));
											}catch(Exception e) {};

											if(null!=viewDetail.getRefKey() && !"".equals(viewDetail.getRefKey()))
											{
												// add to viewList
												if(null==contentDetails.getViewsList() || contentDetails.getViewsList().size()<=0)
												{
													contentDetails.setViewsList(new ArrayList<ViewDetails>());
												}
												contentDetails.getViewsList().add(viewDetail);
											}

											viewDetail = null;
										}
										viewObj = null;
									}catch(Exception e) {};
								}
							}
							
							
							// CHECK FOR USERGROUPS
							if(null!=contentObj.getJSONArray("userGroups") && contentObj.getJSONArray("userGroups").length()>0)
							{
								JSONObject usrObj = null;
								UserGroupDetails ugDetail = null;
								for(int a=0;a<contentObj.getJSONArray("userGroups").length();a++)
								{
									try
									{
										usrObj = contentObj.getJSONArray("userGroups").getJSONObject(a);
										if(null!=usrObj)
										{
											ugDetail =new UserGroupDetails();
											// set DocumentId
											ugDetail.setDocumentId(contentDetails.getDocumentId());
											// set Locale
											ugDetail.setLocale(contentDetails.getLocale());
											try
											{
												// referenceKey
												ugDetail.setRefKey(usrObj.getString("referenceKey"));
											}catch(Exception e) {};

											try
											{
												// recordId
												ugDetail.setRecordId(usrObj.getString("recordId"));
											}catch(Exception e) {};

											try
											{
												// name
												ugDetail.setName(usrObj.getString("name"));
											}catch(Exception e) {};

											try
											{
												// externalType
												ugDetail.setExternalType(usrObj.getString("externalType"));
											}catch(Exception e) {};

											if(null!=ugDetail.getRefKey() && !"".equals(ugDetail.getRefKey()))
											{
												// add to userGroupList
												if(null==contentDetails.getUserGroupsList() || contentDetails.getUserGroupsList().size()<=0)
												{
													contentDetails.setUserGroupsList(new ArrayList<UserGroupDetails>());
												}
												contentDetails.getUserGroupsList().add(ugDetail);
											}

											ugDetail = null;
										}
										usrObj = null;
									}catch(Exception e) {};
								}
							}
							
							
							// SET XML NODE
							try
							{
								contentDetails.setSchemaData(contentObj.getString("xml"));
							}catch(Exception e) {}

							// SET META DATA XML NODE
							try
							{
								contentDetails.setMetaDataXML(contentObj.getString("metaDataXml"));
							}catch(Exception e) {}
							

							// CHECK FOR ATTACHMENTS
							if(null!=contentDetails.getSchemaData() && !"".equals(contentDetails.getSchemaData()))
							{
								// GET INLINE IMAGES AS ATTACHMENTS DATA
								// GET INLINE PDFS / WORD / DOCUMENTS / VIDEOS
								contentDetails = readAttachmentsData(contentDetails);

								// PROCEED FOR INLINE IMAGES AND INLINE LINKS
								contentDetails = readInlineImagesAndInnerLinks(contentDetails);
							}
						}
					}
				}
				else
				{
					contentDetails.setErrorCodes("FAILURE");
					if(null!=serviceObject.get("ERROR_DATA"))
					{
						contentDetails.setErrorMessage(serviceObject.getJSONObject("ERROR_DATA").toString());
					}
					else if(null!=serviceObject.get("ERROR_MESSAGE") && !"".equals(serviceObject.get("ERROR_MESSAGE")))
					{
						contentDetails.setErrorMessage(serviceObject.get("ERROR_MESSAGE").toString());
					}
				
				}
			}
			serviceObject = null;
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(RetrieveManualLinksImpl.class.getName(), "getContentData()", e);
		}
		return contentDetails;
	}

	public ContentDetails readInlineImagesAndInnerLinks(ContentDetails contentDetails)
	{
		try
		{
			org.jsoup.nodes.Document doc = Jsoup.parse(contentDetails.getSchemaData(), "", Parser.xmlParser());
			if(null!=doc)
			{
				Element channelNode = doc.child(0);
				Elements allNodesList = channelNode.getAllElements();
				if(null!=allNodesList && allNodesList.size()>0)
				{
					AttachmentDetails aDetails = null;
					Element node = null;
					String textContent=null;
					org.jsoup.nodes.Document htmlDoc= null;
					Elements aTagsList=null;
					String hrefSrcVal=null;
					Element aEle=null;
					Elements imagesList = null;
					Element imgEle=null;
					for(int a=0;a<allNodesList.size();a++)
					{
						node = (Element)allNodesList.get(a);
						if(null!=node.nodeName() && !node.nodeName().toLowerCase().equals("ATTACHMENTS".toLowerCase()) && 
								!node.nodeName().toLowerCase().equals("ATTACHMENT".toLowerCase()))
						{
							textContent = node.ownText();
							if(null!=textContent && !"".equals(textContent))
							{
								// convert this to HTML DOC
								htmlDoc = Jsoup.parse(textContent);
								if(null!=htmlDoc)
								{
									// ANCHOR TAGS
									aTagsList = htmlDoc.select("a");
									if(null!=aTagsList && aTagsList.size()>0)
									{
										for(int c=0;c<aTagsList.size();c++)
										{
											aEle = (Element)aTagsList.get(c);
											hrefSrcVal = aEle.attr("href");
											if(null!=hrefSrcVal && !"".equals(hrefSrcVal) && !"#".equals(hrefSrcVal))
											{
												hrefSrcVal= hrefSrcVal.replace("\\", "/");
												// add this as attachment
												aDetails=  new AttachmentDetails();
												aDetails.setDocumentId(contentDetails.getDocumentId());
												aDetails.setLocale(contentDetails.getLocale());
												if(hrefSrcVal.lastIndexOf("/")!=-1)
												{
													aDetails.setAttachmentName(hrefSrcVal.substring(hrefSrcVal.lastIndexOf("/")+1,hrefSrcVal.length()));
												}

												// prepare Other details
												aDetails.setAttachmentType(AttachmentTypes.ATT_TYPE_INLINE_LINK_ATTACHMENT);
												aDetails.setKaResourcePath(contentDetails.getResourcePath());
												if(null!=aDetails.getKaResourcePath() && !aDetails.getKaResourcePath().endsWith("/"))
												{
													aDetails.setKaResourcePath(aDetails.getKaResourcePath()+"/");
												}
												// set actualHref Value
												aDetails.setSourcePath(hrefSrcVal);
												// add KA Resource Path as Well
												aDetails.setKaResourcePath(aDetails.getKaResourcePath());
												// add to contentDetails.AttachmentList
												if(null==contentDetails.getAttachmentsList() || contentDetails.getAttachmentsList().size()<=0)
												{
													contentDetails.setAttachmentsList(new ArrayList<AttachmentDetails>());
												}
												contentDetails.getAttachmentsList().add(aDetails);
												aDetails = null;
											}
											aEle= null;
											hrefSrcVal = null;
										}
									}
									aTagsList = null;

									// IMAGES TAGS
									imagesList = htmlDoc.select("img");
									if(null!=imagesList && imagesList.size()>0)
									{
										for(int c=0;c<imagesList.size();c++)
										{
											imgEle = (Element)imagesList.get(c);
											hrefSrcVal = imgEle.attr("src");
											if(null!=hrefSrcVal && !"".equals(hrefSrcVal) && !"#".equals(hrefSrcVal))
											{
												hrefSrcVal= hrefSrcVal.replace("\\", "/");
												// add this as attachment
												// add this as attachment
												aDetails=  new AttachmentDetails();
												aDetails.setDocumentId(contentDetails.getDocumentId());
												aDetails.setLocale(contentDetails.getLocale());
												if(hrefSrcVal.lastIndexOf("/")!=-1)
												{
													aDetails.setAttachmentName(hrefSrcVal.substring(hrefSrcVal.lastIndexOf("/")+1,hrefSrcVal.length()));
												}

												// prepare Other details
												aDetails.setAttachmentType(AttachmentTypes.ATT_TYPE_INLINE_IMG_ATTACHMENT);
												aDetails.setKaResourcePath(contentDetails.getResourcePath());
												if(null!=aDetails.getKaResourcePath() && !aDetails.getKaResourcePath().endsWith("/"))
												{
													aDetails.setKaResourcePath(aDetails.getKaResourcePath()+"/");
												}
												// set actualHref Value
												aDetails.setSourcePath(hrefSrcVal);
												// add KA Resource Path as Well
												aDetails.setKaResourcePath(aDetails.getKaResourcePath());
												// add to contentDetails.AttachmentList
												if(null==contentDetails.getAttachmentsList() || contentDetails.getAttachmentsList().size()<=0)
												{
													contentDetails.setAttachmentsList(new ArrayList<AttachmentDetails>());
												}
												contentDetails.getAttachmentsList().add(aDetails);
												aDetails = null;
											}
											imgEle= null;
											hrefSrcVal = null;
										}
									}
									imagesList = null;
								}
								htmlDoc =  null;
							}
							textContent=null;
						}
						node = null;
					}
				}
				allNodesList = null;
				channelNode = null;
			}
			doc = null;
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(RetrieveManualLinksImpl.class.getName(), "readInlineImagesAndInnerLinks()", e);
		}
		return contentDetails;
	}
	
	public ContentDetails readAttachmentsData(ContentDetails contentDetails)
	{
		try
		{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(contentDetails.getSchemaData())));
			if(null!=doc)
			{
				doc.normalize();
				Node schemaNode = doc.getFirstChild();
				if(null!=schemaNode)
				{
					/*
					 * START ITERATING ALL CHILD NODES AND LOOK FOR ALL ATTACHMENTS DATA
					 * ALSO IDENTIFY THEIR SALES FORCE FIELD NAME
					 */
					NodeList childNodesList = schemaNode.getChildNodes();
					NodeList subChildNodesList = null;
					Node subChildNode = null;
					if(null!=childNodesList && childNodesList.getLength()>0)
					{
						Node childNode = null;
						for(int a=0;a<childNodesList.getLength();a++)
						{
							childNode = (Node)childNodesList.item(a);
							if(null!=childNode)
							{
								if(childNode.getNodeName().equals("ATTACHMENT"))
								{
									subChildNodesList = childNode.getChildNodes();
									if(null!=subChildNodesList && subChildNodesList.getLength()>0)
									{
										for(int b=0;b<subChildNodesList.getLength();b++)
										{
											subChildNode = (Node)subChildNodesList.item(b);
											if(null!=subChildNode)
											{
												if(subChildNode.getNodeName().equals("ATTACHMENT"))
												{
													// add as attachment
													contentDetails = addAttachmentData(subChildNode, contentDetails, "Attachments__c");
												}
												else if(subChildNode.getNodeName().equals("SELF_SERVICE_ATTACHMENTS"))
												{
													// add as attachment
													contentDetails = addAttachmentData(subChildNode, contentDetails, "Attachments__c");
												}
											}
											subChildNode =  null;
										}
									}
									else
									{
										// this is the file node read attachment data from it.
										// add as attachment
										contentDetails = addAttachmentData(childNode, contentDetails, "Attachments__c");
									}
									subChildNodesList = null;
								}
								else if(childNode.getNodeName().equals("ATTACHMENTS"))
								{
									subChildNodesList = childNode.getChildNodes();
									if(null!=subChildNodesList && subChildNodesList.getLength()>0)
									{
										for(int b=0;b<subChildNodesList.getLength();b++)
										{
											subChildNode = (Node)subChildNodesList.item(b);
											if(null!=subChildNode)
											{
												if(subChildNode.getNodeName().equals("ATTACHMENT") || subChildNode.getNodeName().equals("NBSP "))
												{
													// add as attachment
													contentDetails = addAttachmentData(subChildNode, contentDetails, "Attachments__c");
												}
												else if(subChildNode.getNodeName().equals("ATTACHMENTS"))
												{
													if(contentDetails.getChannelRefKey().toLowerCase().equals("PRODUCT_COMMERCIALIZATION_".toLowerCase()))
													{
														// add as attachment
														contentDetails = addAttachmentData(subChildNode, contentDetails, "Product_Information_Attachments__c");
													}
													else
													{
														// add as attachment
														contentDetails = addAttachmentData(subChildNode, contentDetails, "Attachments__c");
													}
												}
											}
											subChildNode =  null;
										}
									}
									subChildNodesList = null;
								}
								else if(childNode.getNodeName().equals("ATTACHMENTS_I"))
								{
									subChildNodesList = childNode.getChildNodes();
									if(null!=subChildNodesList && subChildNodesList.getLength()>0)
									{
										for(int b=0;b<subChildNodesList.getLength();b++)
										{
											subChildNode = (Node)subChildNodesList.item(b);
											if(null!=subChildNode)
											{
												if(subChildNode.getNodeName().equals("COMMERCIAL_QAATTACHMENTS_I "))
												{
													// add as attachment
													contentDetails = addAttachmentData(subChildNode, contentDetails, "Internal_Note_Attachments__c");
												}
											}
											subChildNode = null;
										}
									}
									subChildNodesList = null;
								}
								else if(childNode.getNodeName().equals("INTERNAL_DOCUMENTS"))
								{
									subChildNodesList = childNode.getChildNodes();
									if(null!=subChildNodesList && subChildNodesList.getLength()>0)
									{
										for(int b=0;b<subChildNodesList.getLength();b++)
										{
											subChildNode = (Node)subChildNodesList.item(b);
											if(null!=subChildNode)
											{
												if(subChildNode.getNodeName().equals("NBSP "))
												{
													// add as attachment
													contentDetails = addAttachmentData(subChildNode, contentDetails, "Internal_Note_Attachments__c");
												}
											}
											subChildNode = null;
										}
									}
									subChildNodesList = null;
								}
								else if(childNode.getNodeName().equals("INTERNAL_ATTACHMENTS"))
								{
									subChildNodesList = childNode.getChildNodes();
									if(null!=subChildNodesList && subChildNodesList.getLength()>0)
									{
										for(int b=0;b<subChildNodesList.getLength();b++)
										{
											subChildNode = (Node)subChildNodesList.item(b);
											if(null!=subChildNode)
											{
												if(subChildNode.getNodeName().equals("INTERNAL_ATTACHMENT"))
												{
													// add as attachment
													contentDetails = addAttachmentData(subChildNode, contentDetails, "Internal_Note_Attachments__c");
												}
												else if(subChildNode.getNodeName().equals("ATTACHMENTS"))
												{
													// add as attachment
													contentDetails = addAttachmentData(subChildNode, contentDetails, "Internal_Note_Attachments__c");
												}
												else if(subChildNode.getNodeName().equals("INTERNAL_ATTACHMENTS"))
												{
													// add as attachment
													contentDetails = addAttachmentData(subChildNode, contentDetails, "Internal_Note_Attachments__c");
												}
											}
											subChildNode = null;
										}
									}
									subChildNodesList = null;
								}
								else if(childNode.getNodeName().equals("DISTRIBUTOR_ATTACHMENTS"))
								{
									subChildNodesList = childNode.getChildNodes();
									if(null!=subChildNodesList && subChildNodesList.getLength()>0)
									{
										for(int b=0;b<subChildNodesList.getLength();b++)
										{
											subChildNode = (Node)subChildNodesList.item(b);
											if(null!=subChildNode)
											{
												if(subChildNode.getNodeName().equals("DISTRIBUTOR_ATTACHMENTS"))
												{
													// add as attachment
													contentDetails = addAttachmentData(subChildNode, contentDetails, "Distributor_Attachments__c");
												}
											}
											subChildNode = null;
										}
									}
									subChildNodesList = null;
								}
								else if(childNode.getNodeName().equals("COMMERCIAL_ATTACHMENTS_"))
								{
									subChildNodesList = childNode.getChildNodes();
									if(null!=subChildNodesList && subChildNodesList.getLength()>0)
									{
										for(int b=0;b<subChildNodesList.getLength();b++)
										{
											subChildNode = (Node)subChildNodesList.item(b);
											if(null!=subChildNode)
											{
												if(subChildNode.getNodeName().equals("ATTACHMENTS"))
												{
													// add as attachment
													contentDetails = addAttachmentData(subChildNode, contentDetails, "Commercial_Resources_Attachments__c");
												}
											}
											subChildNode = null;
										}
									}
									subChildNodesList = null;
								}
								else if(childNode.getNodeName().equals("PRODUCT_AND_SERVICE_ATTACHMENTS"))
								{
									subChildNodesList = childNode.getChildNodes();
									if(null!=subChildNodesList && subChildNodesList.getLength()>0)
									{
										for(int b=0;b<subChildNodesList.getLength();b++)
										{
											subChildNode = (Node)subChildNodesList.item(b);
											if(null!=subChildNode)
											{
												if(subChildNode.getNodeName().equals("PRODUCT_AND_SERVICE_ATTACHMENTS"))
												{
													// add as attachment
													contentDetails = addAttachmentData(subChildNode, contentDetails, "Product_Service_and_Support_Attachments__c");
												}
											}
											subChildNode = null;
										}
									}
									subChildNodesList = null;
								}
								else if(childNode.getNodeName().equals("CUSTOMER_ATTACHMENTS"))
								{
									subChildNodesList = childNode.getChildNodes();
									if(null!=subChildNodesList && subChildNodesList.getLength()>0)
									{
										for(int b=0;b<subChildNodesList.getLength();b++)
										{
											subChildNode = (Node)subChildNodesList.item(b);
											if(null!=subChildNode)
											{
												if(subChildNode.getNodeName().equals("CUSTOMER_ATTACHMENTS"))
												{
													// add as attachment
													contentDetails = addAttachmentData(subChildNode, contentDetails, "Customer_Attachments__c");
												}
											}
											subChildNode = null;
										}
									}
									subChildNodesList = null;
								}
								else if(childNode.getNodeName().equals("VIDEO_ATTACHMENT"))
								{
									// add as attachment
									contentDetails = addAttachmentData(childNode, contentDetails, "Video_Attachments__c");
								}
								else if(childNode.getNodeName().equals("OTHER_ATTACHMENT"))
								{
									// add as attachment
									contentDetails = addAttachmentData(childNode, contentDetails, "Other_Attachments__c");
								}
							}
							childNode = null;
						}
					}
					childNodesList = null;
				}
				schemaNode  =null;
			}
			builder = null;
			factory =null;
			doc=null;
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(RetrieveManualLinksImpl.class.getName(), "readAttachmentsData()", e);
		}
		return contentDetails;
	}
	
	private ContentDetails addAttachmentData(Node childNode, ContentDetails contentDetails, String salesForceFieldName)
	{
		try
		{
			// SET ATTACHMENT DATA FOR DOCUMENT
			AttachmentDetails aDetails = new AttachmentDetails();
			aDetails.setDocumentId(contentDetails.getDocumentId());
			aDetails.setLocale(contentDetails.getLocale());
			aDetails.setChannelRefKey(contentDetails.getChannelRefKey());
			aDetails.setSalesForceFieldName(salesForceFieldName);
			
			NamedNodeMap attr = childNode.getAttributes();
			if(null!=attr)
			{
				try
				{
					for(int a=0;a<attr.getLength();a++)
					{
						Node at = (Node)attr.item(a);
						if(null!=at && at.getNodeName().equals("SIZE"))
						{
							aDetails.setSize(at.getTextContent());
						}
						at = null;
					}
					
				}
				catch(Exception e) {
					Utilities.printStackTraceToLogs(RetrieveManualLinksImpl.class.getName(), "addAttachmentData()", e);
				}
			}
			attr = null;
			aDetails.setAttachmentName(Utilities.readNodeValue(childNode));
			if(null!=aDetails.getAttachmentName() && !"".equals(aDetails.getAttachmentName()))
			{
				// prepare Other details
				aDetails.setAttachmentType(AttachmentTypes.ATT_TYPE_DOCUMENT_ATTACHMENT);
				aDetails.setKaResourcePath(contentDetails.getResourcePath());
				if(null!=aDetails.getKaResourcePath() && !aDetails.getKaResourcePath().endsWith("/"))
				{
					aDetails.setKaResourcePath(aDetails.getKaResourcePath()+"/");
				}
				aDetails.setKaResourcePath(aDetails.getKaResourcePath()+aDetails.getAttachmentName());
				// add to contentDetails.AttachmentList
				if(null==contentDetails.getAttachmentsList() || contentDetails.getAttachmentsList().size()<=0)
				{
					contentDetails.setAttachmentsList(new ArrayList<AttachmentDetails>());
				}
				contentDetails.getAttachmentsList().add(aDetails);
			}
			aDetails = null;
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(RetrieveManualLinksImpl.class.getName(), "addAttachmentData()", e);
		}
		return contentDetails;
	}
}
