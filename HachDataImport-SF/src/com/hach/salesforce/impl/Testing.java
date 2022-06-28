package com.hach.salesforce.impl;

import java.io.File;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import com.hach.salesforce.utils.common.FileReadWriteUtil;
import com.hach.salesforce.utils.common.Utilities;

public class Testing {

	public static void main(String[] args) {
		try
		{
			byte[] xml = FileReadWriteUtil.readFile("D:\\HACH_WD\\Documents\\XML\\TECHNICAL_QA\\TE203_en_US.xml");
			String xmlData = new String(xml, "UTF-8");
			if(null!=xmlData && !"".equals(xmlData))
			{

				org.jsoup.nodes.Document doc = Jsoup.parse(xmlData, "", Parser.xmlParser());
				if(null!=doc)
				{
					Element channelNode = doc.child(0);
					Elements allNodesList = channelNode.getAllElements();
					if(null!=allNodesList && allNodesList.size()>0)
					{
//						AttachmentDetails aDetails = null;
						Element node = null;
						String textContent=null;
						org.jsoup.nodes.Document htmlDoc= null;
						Elements aTagsList=null;
						String hrefSrcVal=null;
						Element aEle=null;
						Elements imagesList = null;
						Element imgEle=null;
						int index=0;
						for(int a=0;a<allNodesList.size();a++)
						{
							node = (Element)allNodesList.get(a);
							if(null!=node.nodeName() && !node.nodeName().toLowerCase().equals("ATTACHMENTS".toLowerCase()) && 
									!node.nodeName().toLowerCase().equals("ATTACHMENT".toLowerCase()))
							{
								textContent = node.ownText();
								if(null!=textContent && !"".equals(textContent))
								{
									index=0;
									while(textContent.toLowerCase().indexOf("ok:doc-link")!=-1)
									{
										
									}
									
									
									
									// convert this to HTML DOC
									htmlDoc = Jsoup.parse(textContent);
									if(null!=htmlDoc)
									{
										// ANCHOR TAGS
										aTagsList = htmlDoc.select("ok:doc-link");
										if(null!=aTagsList && aTagsList.size()>0)
										{
											for(int c=0;c<aTagsList.size();c++)
											{
												aEle = (Element)aTagsList.get(c);
												hrefSrcVal = aEle.attr("doc_id");
												System.out.println("doc id :: >"+ hrefSrcVal);
//												if(null!=hrefSrcVal && !"".equals(hrefSrcVal) && !"#".equals(hrefSrcVal))
//												{
//													hrefSrcVal= hrefSrcVal.replace("\\", "/");
//													// add this as attachment
//													aDetails=  new AttachmentDetails();
//													aDetails.setDocumentId(contentDetails.getDocumentId());
//													aDetails.setLocale(contentDetails.getLocale());
//													if(hrefSrcVal.lastIndexOf("/")!=-1)
//													{
//														aDetails.setAttachmentName(hrefSrcVal.substring(hrefSrcVal.lastIndexOf("/")+1,hrefSrcVal.length()));
//													}
//
//													// prepare Other details
//													aDetails.setAttachmentType(AttachmentTypes.ATT_TYPE_INLINE_LINK_ATTACHMENT);
//													aDetails.setKaResourcePath(contentDetails.getResourcePath());
//													if(null!=aDetails.getKaResourcePath() && !aDetails.getKaResourcePath().endsWith("/"))
//													{
//														aDetails.setKaResourcePath(aDetails.getKaResourcePath()+"/");
//													}
//													// set actualHref Value
//													aDetails.setSourcePath(hrefSrcVal);
//													// add KA Resource Path as Well
//													aDetails.setKaResourcePath(aDetails.getKaResourcePath());
//													// add to contentDetails.AttachmentList
//													if(null==contentDetails.getAttachmentsList() || contentDetails.getAttachmentsList().size()<=0)
//													{
//														contentDetails.setAttachmentsList(new ArrayList<AttachmentDetails>());
//													}
//													contentDetails.getAttachmentsList().add(aDetails);
//													aDetails = null;
//												}
												aEle= null;
												hrefSrcVal = null;
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
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
