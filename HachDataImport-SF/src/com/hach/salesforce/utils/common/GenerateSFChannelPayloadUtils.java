package com.hach.salesforce.utils.common;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.hach.salesforce.dao.TransactionDAO;
import com.hach.salesforce.vo.AttachmentDetails;
import com.hach.salesforce.vo.ContentDetails;

public class GenerateSFChannelPayloadUtils {

	private List<AttachmentDetails> allInnerLinksList = null;
	
	public List<AttachmentDetails> getAllInnerLinksList() {
		return allInnerLinksList;
	}

	public void setAllInnerLinksList(List<AttachmentDetails> allInnerLinksList) {
		this.allInnerLinksList = allInnerLinksList;
	}
	
	private JSONObject lengthObj = null;
	
	public JSONObject getLengthObj() {
		return lengthObj;
	}

	public void setLengthObj(JSONObject lengthObj) {
		this.lengthObj = lengthObj;
	}

	public JSONObject createPayload(String xmlData,String channelRefKey)
	{
		JSONObject payloadObj = null;
		try
		{
			// initialize length Object
			lengthObj = new JSONObject();
			lengthObj.put("Title", 0);
			lengthObj.put("Summary__c", 0);
			lengthObj.put("Answer__c", 0);
			lengthObj.put("Internal_Notes__c", 0);
			lengthObj.put("Answer_2__c", 0);
			lengthObj.put("Answer_3__c", 0);
			lengthObj.put("Answer_4__c", 0);
			
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(xmlData)));
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
					Node childNode = null;
					if(null!=childNodesList && childNodesList.getLength()>0)
					{
						// initialize payloadObj
						payloadObj = new JSONObject();
						for(int a=0;a<childNodesList.getLength();a++)
						{
							childNode = (Node)childNodesList.item(a);
							if(null!=childNode.getNodeName() && !"".equals(childNode.getNodeName()))
							{
								if(childNode.getNodeName().equalsIgnoreCase("QUESTION") || childNode.getNodeName().equalsIgnoreCase("QUESTION_OR_TITLE") || 
										childNode.getNodeName().equalsIgnoreCase("TITLE"))
								{
									// set Title
									payloadObj.put("Title", Utilities.readNodeValue(childNode));
									// set length as well
									lengthObj.put("Title", payloadObj.get("Title").toString().length());
								}
								else if(childNode.getNodeName().equalsIgnoreCase("SUMMARY") || childNode.getNodeName().equalsIgnoreCase("OVERVIEW") || 
										childNode.getNodeName().equalsIgnoreCase("PRODUCT_OVERVIEW"))
								{
									// set Summary__c
									payloadObj.put("Summary__c", Utilities.readNodeValue(childNode));
									// set length as well
									lengthObj.put("Summary__c", payloadObj.get("Summary__c").toString().length());
								}
								else if(childNode.getNodeName().equalsIgnoreCase("ANSWER") || childNode.getNodeName().equalsIgnoreCase("METHOD_DOCUMENT") || 
										childNode.getNodeName().equalsIgnoreCase("PODCAST_CONTENT") || childNode.getNodeName().equalsIgnoreCase("CUSTOMER_INFORMATION") || 
										childNode.getNodeName().equalsIgnoreCase("LEVEL_ONE_CUSTOMERSDISTRIBUTORSINTERNAL") || childNode.getNodeName().equalsIgnoreCase("SALES_DOCUMENT") || 
										childNode.getNodeName().equalsIgnoreCase("SELF_SERVICE_RESOURCE") || childNode.getNodeName().equalsIgnoreCase("SERVICE_DOCUMENT")) 
								{
									// set Answer__c
									payloadObj.put("Answer__c", Utilities.readNodeValue(childNode));
									// set length as well
									lengthObj.put("Answer__c", payloadObj.get("Answer__c").toString().length());
								}
								else if(childNode.getNodeName().equalsIgnoreCase("INTERNAL_NOTES") || childNode.getNodeName().equalsIgnoreCase("INTERNAL_INFORMATION") || 
										childNode.getNodeName().equalsIgnoreCase("LEVEL_THREE_INTERNAL_ONLY"))
								{
									// set Internal_Notes__c
									payloadObj.put("Internal_Notes__c", Utilities.readNodeValue(childNode));
									// set length as well
									lengthObj.put("Internal_Notes__c", payloadObj.get("Internal_Notes__c").toString().length());
								}
								else if(childNode.getNodeName().equalsIgnoreCase("VIDEO"))
								{
									if(channelRefKey.toLowerCase().equalsIgnoreCase("VIDEO".toLowerCase()))
									{
										// VIDEO CHANNEL - SET IN ANSWER FIELD
										// set Answer__c
										payloadObj.put("Answer__c", Utilities.readNodeValue(childNode));
										// set length as well
										lengthObj.put("Answer__c", payloadObj.get("Answer__c").toString().length());
									}
									else if(channelRefKey.toLowerCase().equalsIgnoreCase("COMMERCIAL_QA".toLowerCase()))
									{
										// COMMERCIAL_QA CHANNEL - SET IN ANSWER_2__C FIELD
										// set Answer_2__c
										payloadObj.put("Answer_2__c", Utilities.readNodeValue(childNode));
										// set length as well
										lengthObj.put("Answer_2__c", payloadObj.get("Answer_2__c").toString().length());
									}
								}
								else if(childNode.getNodeName().equalsIgnoreCase("DISTRIBUTOR_RESOURCES") || childNode.getNodeName().equalsIgnoreCase("LEVEL_TWO_DISTRIBUTORSINTERNAL"))
								{
									// set Answer_2__c
									payloadObj.put("Answer_2__c", Utilities.readNodeValue(childNode));
									// set length as well
									lengthObj.put("Answer_2__c", payloadObj.get("Answer_2__c").toString().length());
								}
								else if(childNode.getNodeName().equalsIgnoreCase("COMMERCIAL_RESOURCES"))
								{
									// set Answer_3__c
									payloadObj.put("Answer_3__c", Utilities.readNodeValue(childNode));
									// set length as well
									lengthObj.put("Answer_3__c", payloadObj.get("Answer_3__c").toString().length());
								}
								else if(childNode.getNodeName().equalsIgnoreCase("SERVICE_AND_SUPPORT"))
								{
									// set Answer_4__c
									payloadObj.put("Answer_4__c", Utilities.readNodeValue(childNode));
									// set length as well
									lengthObj.put("Answer_4__c", payloadObj.get("Answer_4__c").toString().length());
								}
							}
							childNode = null;
						}
					}
					childNodesList = null;
				}
				schemaNode = null;
			}
			doc=null;
			builder = null;
			factory = null;
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(GenerateSFChannelPayloadUtils.class.getName(), "createPayload()", e);
		}
		return payloadObj;
	}

	public String performInnerLinksOperation(String xmlData, List<AttachmentDetails> innerLinksList,String documentId, String locale, TransactionDAO transactionDAO)
	{
		if(null!=innerLinksList && innerLinksList.size()>0)
		{
			String sourcePath=null;
			AttachmentDetails details = null;
			String innerLinkAnswerId=null;
			String sfInnerLinkPath=null;
			for(int a=0;a<innerLinksList.size();a++)
			{
				details = (AttachmentDetails)innerLinksList.get(a);
				if(null!=details.getSourcePath() && !"".equals(details.getSourcePath()))
				{
					sourcePath=details.getSourcePath();
					/*
					 * APPLY ALL THE RULES
					 * 	1. IF URL has hach.com and does not have support.hach.com then pass AS-IS
						2. IF URL Starts with # then pass AS-IS
						3. IF URL does not have hach.com or custhelp.com then pass AS-IS
						4. IF URL has https://support.hach.com/ and /a_id/ then need to repalce with Salesforce article
						5. IF URL has https://hachcompany and custhelp.com and /a_id/ then need to repalce with Salesforce article
						6. IF URL has https://hachcompany and custhelp.com then need to replace with Salesforce URL 
						7. IF URL starts with https://support.hach.com/ and then need to repalce with Salesforce URL
						8. IF any URL which goes outside from condition 7 validation then pass AS-IS
						9. if URL has support.hach.com or custhelp.com and any document extension like 
						word, ppt, xlsx, zip and all possible extension other than images then pass these url AS-IS and 
						capture somewhere with tagging to download these documents. 
						Client or us needs to replace this with third part system URL
						10. IF URL has support.hach.com/ci/okcsFattach, then Needs Download, set target as #

						// all Possible extensions = even Images too - since they are defined on Anchor tags
					 * .aiff,.au,.avi,.bat,.class,.csv,.cvs,.dbf,.dif,.doc,.eps,.exe,.fm3,.gif,.hqx,.htm,.mdb,.mid,.mov,.mtb,.pdf,.ppt,.psd,.psp,.qxd,
					 * .ra,.rtf,.sit,.tar,.tif,.txt,.wav,.wk3,.wks,.wpd,.xls,.zip,.aif,.java,.docx,.html,.midi,.qt,.pptx,.wp5,.xlsx,.bmp,.jpg,.jpeg,.png
					 */
					if((sourcePath.toLowerCase().indexOf("support.hach.com")!=-1 || sourcePath.toLowerCase().indexOf("custhelp.com")!=-1) && 
							(sourcePath.toLowerCase().indexOf(".aiff")!=-1 || sourcePath.toLowerCase().indexOf(".au")!=-1 ||sourcePath.toLowerCase().indexOf(".avi")!=-1 ||sourcePath.toLowerCase().indexOf(".bat")!=-1 ||sourcePath.toLowerCase().indexOf(".class")!=-1 ||sourcePath.toLowerCase().indexOf(".csv")!=-1 ||sourcePath.toLowerCase().indexOf(".cvs")!=-1 ||sourcePath.toLowerCase().indexOf(".dbf")!=-1 ||sourcePath.toLowerCase().indexOf(".dif")!=-1 ||sourcePath.toLowerCase().indexOf(".doc")!=-1 ||sourcePath.toLowerCase().indexOf(".eps")!=-1 ||sourcePath.toLowerCase().indexOf(".exe")!=-1 ||sourcePath.toLowerCase().indexOf(".fm3")!=-1 ||sourcePath.toLowerCase().indexOf(".gif")!=-1 ||sourcePath.toLowerCase().indexOf(".hqx")!=-1 ||sourcePath.toLowerCase().indexOf(".htm")!=-1 ||sourcePath.toLowerCase().indexOf(".mdb")!=-1 ||sourcePath.toLowerCase().indexOf(".mid")!=-1 ||sourcePath.toLowerCase().indexOf(".mov")!=-1 ||sourcePath.toLowerCase().indexOf(".mtb")!=-1 ||sourcePath.toLowerCase().indexOf(".pdf")!=-1 ||sourcePath.toLowerCase().indexOf(".ppt")!=-1 ||sourcePath.toLowerCase().indexOf(".psd")!=-1 ||sourcePath.toLowerCase().indexOf(".psp")!=-1 ||sourcePath.toLowerCase().indexOf(".qxd")!=-1 ||sourcePath.toLowerCase().indexOf(".ra")!=-1 ||sourcePath.toLowerCase().indexOf(".rtf")!=-1 ||sourcePath.toLowerCase().indexOf(".sit")!=-1 ||sourcePath.toLowerCase().indexOf(".tar")!=-1 ||sourcePath.toLowerCase().indexOf(".tif")!=-1 ||sourcePath.toLowerCase().indexOf(".txt")!=-1 ||sourcePath.toLowerCase().indexOf(".wav")!=-1 ||sourcePath.toLowerCase().indexOf(".wk3")!=-1 ||sourcePath.toLowerCase().indexOf(".wks")!=-1 ||sourcePath.toLowerCase().indexOf(".wpd")!=-1 ||sourcePath.toLowerCase().indexOf(".xls")!=-1 ||sourcePath.toLowerCase().indexOf(".zip")!=-1 ||sourcePath.toLowerCase().indexOf(".aif")!=-1 ||sourcePath.toLowerCase().indexOf(".java")!=-1 ||sourcePath.toLowerCase().indexOf(".docx")!=-1 ||sourcePath.toLowerCase().indexOf(".html")!=-1 ||sourcePath.toLowerCase().indexOf(".midi")!=-1 ||sourcePath.toLowerCase().indexOf(".qt")!=-1 ||sourcePath.toLowerCase().indexOf(".pptx")!=-1 ||sourcePath.toLowerCase().indexOf(".wp5")!=-1 ||sourcePath.toLowerCase().indexOf(".xlsx")!=-1) || sourcePath.toLowerCase().indexOf(".bmp")!=-1 || sourcePath.toLowerCase().indexOf(".jpg")!=-1 || sourcePath.toLowerCase().indexOf(".jpeg")!=-1 || sourcePath.toLowerCase().indexOf(".png")!=-1)
					{
						// condition for Rule 9
						details.setProcessingStatus(CommonConstants.STATUS_NEEDS_DOWNLOAD);
						/*
						 * 
						 * to do fetch CDN URL for the InnerLink
						 * & set Target URL
						 * 
						 * For Now set Target URL as #
						 */
						details.setTargetUrl("#");
						// replace with target URL
						xmlData=   xmlData.replace(details.getSourcePath(), details.getTargetUrl());
					}
					else if((sourcePath.toLowerCase().indexOf("://support.hach.com/")!=-1 && sourcePath.toLowerCase().indexOf("/a_id/")!=-1) || 
							(sourcePath.toLowerCase().indexOf("://hachcompany")!=-1 && sourcePath.toLowerCase().indexOf("custhelp.com")!=-1 
							&& sourcePath.toLowerCase().indexOf("/a_id/")!=-1))
					{
						// condition for Rule 4 & 5
						details.setProcessingStatus(CommonConstants.STATUS_REPLACE_SF_ARTICLE_ID);
						/*
						 * IDENTIFY ANSWER ID
						 * URL PATTERNS CAN BE - 
						 * answer_view/a_id/1000294
						 * answer_view/a_id/1027666/session/L3RpbWUvMT
						 * answer_view/a_id/1002488/loc/en_US
						 */
						try
						{
							if(sourcePath.indexOf("/a_id/")!=-1)
							{
								innerLinkAnswerId = sourcePath.substring(sourcePath.indexOf("/a_id/")+6,sourcePath.length());
								if(null!=innerLinkAnswerId && !"".equals(innerLinkAnswerId))
								{
									if(innerLinkAnswerId.indexOf("/")!=-1)
									{
										innerLinkAnswerId = innerLinkAnswerId.substring(0, innerLinkAnswerId.indexOf("/"));
									}
									
									if(null!=innerLinkAnswerId && !"".equals(innerLinkAnswerId))
									{
										/*
										 * FETCH ITS KA URL USED WHILE CREATING DOCUMENT
										 */
										details = transactionDAO.getSFURLNameForKADocumentOnAnswerId(innerLinkAnswerId, details);
										if(null!=details && null!=details.getTargetUrl() && !"".equals(details.getTargetUrl()))
										{
											// set Mapping Status as Success
											details.setMappingStatus("SUCCESS");
										}
										else
										{
											// set Mapping Status as Failure
											details.setMappingStatus("FAILURE");
											/*
											 * SINCE SAME LOCALE SF URL NOT FOUND, CONVERT IT TO DEAD LINK
											 * AND TRACK
											 */
											details.setTargetUrl("#");
										}
									}
								}
								innerLinkAnswerId = null;
							}
						}
						catch(Exception e)
						{
							// set Mapping status as Failure
							details.setMappingStatus("FAILURE");
							if(null==details.getErrorMessage() || "".equals(details.getErrorMessage()))
							{
								details.setErrorMessage("Database Exception Occurred: >"+ e.getMessage());
							}
							details.setTargetUrl("#");
							Utilities.printStackTraceToLogs(GenerateSFChannelPayloadUtils.class.getName(), "performInnerLinksOperation()", e);
						}
						innerLinkAnswerId = null;
						
						// set replace URL source with Target
						xmlData = xmlData.replace(details.getSourcePath(), details.getTargetUrl());
						
						
					}
					else if((sourcePath.toLowerCase().indexOf("://support.hach.com/")!=-1 && sourcePath.toLowerCase().indexOf("/a_id/")==-1 && 
							sourcePath.toLowerCase().indexOf("support.hach.com/ci/okcsFattach".toLowerCase())==-1) || 
							(sourcePath.toLowerCase().indexOf("://hachcompany")!=-1 && sourcePath.toLowerCase().indexOf("custhelp.com")!=-1 
							&& sourcePath.toLowerCase().indexOf("/a_id/")==-1))
					{
						// condition for Rule 6 & 7
						details.setProcessingStatus(CommonConstants.STATUS_REPLACE_SF_URL);
						/*
						 * REMOVE THE HOST NAME FROM THESE URLS and READ VALUES AFTER .com/
						 * SET IS AS TARGET URL
						 */
						if(sourcePath.indexOf(".com/")!=-1)
						{
							sfInnerLinkPath = sourcePath.substring(sourcePath.indexOf(".com/")+4, sourcePath.length());
							if(null!=sfInnerLinkPath && !"".equals(sfInnerLinkPath))
							{
								if(!sfInnerLinkPath.startsWith("/"))
								{
									sfInnerLinkPath="/"+sfInnerLinkPath;
								}
								// replace URL
								xmlData = xmlData.replace(details.getSourcePath(), sfInnerLinkPath);
								
								// set Mapping status as Success
								details.setMappingStatus("SUCCESS");
								details.setTargetUrl(sfInnerLinkPath);
							}
							else
							{
								// set Mapping status as Failure
								details.setMappingStatus("FAILURE");
							}
						}
						else
						{
							// set Mapping status as Failure
							details.setMappingStatus("FAILURE");
						}
						sfInnerLinkPath = null;
					}
					else if(sourcePath.toLowerCase().indexOf("support.hach.com/ci/okcsFattach".toLowerCase())!=-1)
					{
						// condition for Rule 10
						details.setProcessingStatus(CommonConstants.STATUS_NEEDS_DOWNLOAD);
						/*
						 * 
						 * to do fetch CDN URL for the InnerLink
						 * & set Target URL
						 * 
						 * For Now set Target URL as #
						 */
						details.setTargetUrl("#");
						// replace with target URL
						xmlData=  xmlData.replace(details.getSourcePath(), details.getTargetUrl());
					}
					/*
					 * This Condition is Unnecessary as rest all are AS_IS
					 */
					//						else if(sourcePath.startsWith("#") || 
					//								(sourcePath.toLowerCase().indexOf("hach.com")!=-1 && sourcePath.toLowerCase().indexOf("support.hach.com")==-1) || 
					//								(sourcePath.toLowerCase().indexOf("hach.com")==-1 || sourcePath.toLowerCase().indexOf("custhelp.com")==-1))
					//						{
					//							// condition for Rule 1,2 & 3
					//							details.setProcessingStatus(CommonConstants.STATUS_AS_IS);
					//						}
					else 
					{
						// condition for Rule 1,2,3 & 8
						details.setProcessingStatus(CommonConstants.STATUS_AS_IS);
					}

					// add details to common innerLinksList
					if(null==allInnerLinksList || allInnerLinksList.size()<=0)
					{
						allInnerLinksList = new ArrayList<AttachmentDetails>();
					}
					allInnerLinksList.add(details);
					sourcePath = null;
				}
			}
		}
		return xmlData;
	}

	public String performOKMInnerLinksOperation(String xmlData,String documentId, String locale, TransactionDAO transactionDAO,String channel)
	{
		/*
		 * DO THIS ONLY WHEN XML DATA CONTAINS ok:answer-link
		 * 
		 * identify all the OKM INNER LINKS WHICH WILL BE AS FOLLOWS
		 * <ok:answer-link answer_id="1028788" contents="WW Office Hours June 2020: Modeling">WW Office Hours June 2020: Modeling</ok:answer-link>
		 * LOOK UP FOR THE SALESFORCE DOCUMENT ID FOR OKM ANSWER ID ATTRIBUTE AND REPLACE 
		 * THIS OKM LINK WITH NORMAL ANCHOR TAG  
		 */
		if(xmlData.indexOf("ok:answer-link")!=-1)
		{
			xmlData = xmlData.replace("ok:answer-link", "a");
			org.jsoup.nodes.Document doc = Jsoup.parse(xmlData, "", Parser.xmlParser());
			if(null!=doc)
			{
				Element channelNode = doc.child(0);
				Elements allNodesList = channelNode.getAllElements();
				if(null!=allNodesList && allNodesList.size()>0)
				{
					Element node = null;
					String textContent=null;
					org.jsoup.nodes.Document htmlDoc= null;
					Elements aTagsList=null;
					String answerId=null;
					Element aEle=null;
					AttachmentDetails details = null;
					String outerHtmlOfExistingTag=null;
					Element newElementTag = null;
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
											answerId = aEle.attr("answer_id");
											if(null!=answerId && !"".equals(answerId) && !"#".equals(answerId))
											{
												try
												{
													// only when answer id attribute is available
													details = new AttachmentDetails();
													details.setDocumentId(documentId);
													details.setLocale(locale);
													details.setChannelRefKey(channel);
													details.setSourcePath(answerId);
													// set PROCESSING STATUS
													details.setProcessingStatus(CommonConstants.STATUS_REPLACE_SF_ARTICLE_ID_FOR_OKM_LINK);
													/*
													 * LOOK UP FOR SF ID FOR THE RESPECTIVE ANSWER ID
													 */
													details = transactionDAO.getSFURLNameForKADocumentOnAnswerId(answerId, details);
													if(null!=details && null!=details.getTargetUrl() && !"".equals(details.getTargetUrl()))
													{
														// set Mapping Status as Success
														details.setMappingStatus("SUCCESS");
													}
													else
													{
														// set Mapping Status as Failure
														details.setMappingStatus("FAILURE");
														/*
														 * SINCE SAME LOCALE SF URL NOT FOUND, CONVERT IT TO DEAD LINK
														 * AND TRACK
														 */
														details.setTargetUrl("#");
													}
													
													// now get OuterHTML of aELE 
													outerHtmlOfExistingTag = aEle.outerHtml();
													newElementTag = doc.createElement("a");
													newElementTag.attr("href",details.getTargetUrl());
													newElementTag.html(aEle.html());

													// now replace outerHtmlOfExistingTag with newElementTag in XML DATA
													xmlData = xmlData.replace(outerHtmlOfExistingTag, newElementTag.outerHtml());
													
													newElementTag = null;
													outerHtmlOfExistingTag = null;
												}
												catch(Exception e)
												{
													Utilities.printStackTraceToLogs(GenerateSFChannelPayloadUtils.class.getName(), "performOKMInnerLinksOperation()", e);
												}
												
												// add this to default innerLinks list
												if(null==allInnerLinksList || allInnerLinksList.size()<=0)
												{
													allInnerLinksList = new ArrayList<AttachmentDetails>();
												}
												allInnerLinksList.add(details);
												details = null;
											}
											aEle= null;
											answerId = null;
										}
									}
									aTagsList = null;
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
		

		/*
		 * DO THIS ONLY WHEN XML DATA CONTAINS ok:doc-link
		 * 
		 * identify all the OKM INNER LINKS WHICH WILL BE AS FOLLOWS
		 * <ok:doc-link doc_id="TE1439" contents="Why did the sample turn a dark brown after adding the powder pillow when running a DPD Total Chlorine test? ">Why did the sample turn a dark brown after adding the powder pillow when running a DPD Total Chlorine test? </ok:doc-link>]]>
		 * LOOK UP FOR THE SALESFORCE DOCUMENT ID FOR OKM DOCUMENT ID ATTRIBUTE OF SAME LOCALE AND REPLACE 
		 * THIS OKM LINK WITH NORMAL ANCHOR TAG  
		 */
		if(xmlData.indexOf("ok:doc-link")!=-1)
		{
			xmlData = xmlData.replace("ok:doc-link", "a");
			org.jsoup.nodes.Document doc = Jsoup.parse(xmlData, "", Parser.xmlParser());
			if(null!=doc)
			{
				Element channelNode = doc.child(0);
				Elements allNodesList = channelNode.getAllElements();
				if(null!=allNodesList && allNodesList.size()>0)
				{
					Element node = null;
					String textContent=null;
					org.jsoup.nodes.Document htmlDoc= null;
					Elements aTagsList=null;
					String docId=null;
					Element aEle=null;
					AttachmentDetails details = null;
					String outerHtmlOfExistingTag=null;
					Element newElementTag = null;
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
											docId = aEle.attr("doc_id");
											if(null!=docId && !"".equals(docId) && !"#".equals(docId))
											{
												try
												{
													// only when answer id attribute is available
													details = new AttachmentDetails();
													details.setDocumentId(documentId);
													details.setLocale(locale);
													details.setChannelRefKey(channel);
													details.setSourcePath(docId);
													// set PROCESSING STATUS
													details.setProcessingStatus(CommonConstants.STATUS_REPLACE_SF_ARTICLE_ID_FOR_OKM_LINK);
													/*
													 * LOOK UP FOR SF ID FOR THE RESPECTIVE ANSWER ID
													 */
													details = transactionDAO.getSFURLNameForKADocumentOnDocumentId(docId, details);
													if(null!=details && null!=details.getTargetUrl() && !"".equals(details.getTargetUrl()))
													{
														// set Mapping Status as Success
														details.setMappingStatus("SUCCESS");
													}
													else
													{
														// set Mapping Status as Failure
														details.setMappingStatus("FAILURE");
														/*
														 * SINCE SAME LOCALE SF URL NOT FOUND, CONVERT IT TO DEAD LINK
														 * AND TRACK
														 */
														details.setTargetUrl("#");
													}
													
													// now get OuterHTML of aELE 
													outerHtmlOfExistingTag = aEle.outerHtml();
													newElementTag = doc.createElement("a");
													newElementTag.attr("href",details.getTargetUrl());
													newElementTag.html(aEle.html());

													// now replace outerHtmlOfExistingTag with newElementTag in XML DATA
													xmlData = xmlData.replace(outerHtmlOfExistingTag, newElementTag.outerHtml());
													
													newElementTag = null;
													outerHtmlOfExistingTag = null;
												}
												catch(Exception e)
												{
													Utilities.printStackTraceToLogs(GenerateSFChannelPayloadUtils.class.getName(), "performOKMInnerLinksOperation()", e);
												}
												
												// add this to default innerLinks list
												if(null==allInnerLinksList || allInnerLinksList.size()<=0)
												{
													allInnerLinksList = new ArrayList<AttachmentDetails>();
												}
												allInnerLinksList.add(details);
												details = null;
											}
											aEle= null;
											docId = null;
										}
									}
									aTagsList = null;
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
		//		logger.info(xmlData);
		return xmlData;
	}

	
	public String performInlineImagesOperation(String xmlData, List<AttachmentDetails> imagesList,String documentId, String locale)
	{
		if(null!=imagesList && imagesList.size()>0)
		{
			String sourcePath=null;
			AttachmentDetails details = null;
			for(int a=0;a<imagesList.size();a++)
			{
				details = (AttachmentDetails)imagesList.get(a);
				if(null!=details.getSourcePath() && !"".equals(details.getSourcePath()))
				{
					sourcePath = details.getSourcePath();
					/*
					 * IF CDN URL IS NOT NULL - REPLACE SOURCE URL WITH IT
					 * ELSE PASS THE OTHER IMAGES AS IS.
					 */
					if(null!=details.getCdnUrl() && !"".equals(details.getCdnUrl()))
					{
						xmlData = xmlData.replace(sourcePath, details.getCdnUrl());
					}
					sourcePath = null;
				}
				details = null;
			}
		}
		return xmlData;
	}


	public JSONObject performCommonFieldsOperation(ContentDetails details, JSONObject salesForceObj, String sfChannelRecordTypeId) throws JSONException
	{
		// prepare URL as well for the document, which will be KA_AnswerId
		salesForceObj.put("UrlName","KA-"+details.getLocale().replace("_", "-")+"-"+details.getDocumentId()+"-"+details.getAnswerId());
		
		// set recordTypeId - e.g. SalesForce Channel Id
		salesForceObj.put("RecordTypeId", sfChannelRecordTypeId);

		// set Version Number - e.g. KA Version Number
		if(null!=details.getVersionNumber() && !"".equals(details.getVersionNumber()))
		{
			salesForceObj.put("KA_Publish_Version__c", details.getVersionNumber());
		}
		else
		{
			salesForceObj.put("KA_Publish_Version__c", JSONObject.NULL);
		}

		// set Owner Name
		if(null!=details.getOwnerName() && !"".equals(details.getOwnerName()))
		{
			salesForceObj.put("KA_Author_Name__c", details.getOwnerName());
		}
		else
		{
			salesForceObj.put("KA_Author_Name__c", JSONObject.NULL);
		}

		// set Last Modifier Name
		if(null!=details.getLastModifierName() && !"".equals(details.getLastModifierName()))
		{
			salesForceObj.put("KA_Last_Modified_By__c", details.getLastModifierName());
		}
		else 
		{
			salesForceObj.put("KA_Last_Modified_By__c", JSONObject.NULL);
		}

		// set KA Article create date
		if(null!=details.getCreateDate() && !"".equals(details.getCreateDate()))
		{
			salesForceObj.put("KA_Article_Created_Date__c", details.getCreateDate());
		}
		else 
		{
			salesForceObj.put("KA_Article_Created_Date__c", JSONObject.NULL);
		}

		// set KA Article Last Modified Date
		if(null!=details.getLastModifiedDate() && !"".equals(details.getLastModifiedDate()))
		{
			salesForceObj.put("KA_Article_Last_Modified_Date__c", details.getLastModifiedDate());
		}
		else 
		{
			salesForceObj.put("KA_Article_Last_Modified_Date__c", JSONObject.NULL);
		}

		// set KA Document Id
		if(null!=details.getDocumentId() && !"".equals(details.getDocumentId()))
		{
			salesForceObj.put("KA_Document_Id__c", details.getDocumentId());
		}
		else 
		{
			salesForceObj.put("KA_Document_Id__c", JSONObject.NULL);
		}

		// set KA Answer Id
		if(null!=details.getAnswerId() && !"".equals(details.getAnswerId()))
		{
			salesForceObj.put("KA_Answer_Id__c", details.getAnswerId());
		}
		else 
		{
			salesForceObj.put("KA_Answer_Id__c", JSONObject.NULL);
		}

		return salesForceObj;
	}
}
