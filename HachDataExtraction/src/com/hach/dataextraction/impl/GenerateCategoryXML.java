package com.hach.dataextraction.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.hach.dataextraction.dao.TransactionDAO;
import com.hach.dataextraction.utils.common.Utilities;
import com.hach.dataextraction.vo.CategoryDetails;

public class GenerateCategoryXML {

	private static Logger logger = Logger.getLogger(StartExtractionImpl.class);

	public static void main(String[] args) {
		// initialize loggers
		File f = new File(GenerateCategoryXML.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		PropertyConfigurator.configure(f.getParentFile().getAbsolutePath()+"/log.properties");
		f=  null;
		try
		{
			xmlMethod();
			
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(GenerateCategoryXML.class.getName(), "main()", e);
		}
	}
	
	
	public static void main_1(String[] args) {
		// initialize loggers
		File f = new File(GenerateCategoryXML.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		PropertyConfigurator.configure(f.getParentFile().getAbsolutePath()+"/log.properties");
		f=  null;
		try
		{
			String locale="en_US";
			TransactionDAO transactionDAO = new TransactionDAO();
			List<CategoryDetails> categoryList = transactionDAO.getCategoryHierarchyListForXML(locale,"");
			logger.info("main :: Categories Found are :: >"+ categoryList.size());
			
			
			
			int partitionSize=1;
			ArrayList<List<CategoryDetails>> partitions = new ArrayList<List<CategoryDetails>>();
			for (int i=0; i<categoryList.size(); i += partitionSize) {
				partitions.add(categoryList.subList(i, Math.min(i + partitionSize, categoryList.size())));
			}
			
			
			
			if(null!=partitions && partitions.size()>0)
			{
				CategoryDetails level1Details = null;
				CategoryDetails level2Details = null;
				CategoryDetails level3Details = null;
				CategoryDetails level4Details = null;
				CategoryDetails level5Details = null;
				
				for(List<CategoryDetails> subList: partitions)
				{
					if(null!=subList && subList.size()>0)
					{
						int count=0;
						StringBuilder str = new StringBuilder();
						str.append("<DataCategoryGroup xmlns=\"http://soap.sforce.com/2006/04/metadata\">");
						str.append(System.getProperty("line.separator"));
						str.append("<active>true</active>");
						str.append(System.getProperty("line.separator"));
						
						for(int a=0;a<categoryList.size();a++)
						{
							level1Details = (CategoryDetails)categoryList.get(a);
							// create level 1 element
							str.append("<dataCategory>");
							str.append(System.getProperty("line.separator"));
							
							// add Level 1 Label 
							str.append("<label>"+level1Details.getName()+"</label>");
							str.append(System.getProperty("line.separator"));
							
							// add Level 1 Name 
							str.append("<name>"+level1Details.getRefKey()+"</name>");
							str.append(System.getProperty("line.separator"));
							
							/*
							 * LEVEL 2
							 */
							if(null!=level1Details.getChildList() && level1Details.getChildList().size()>0)
							{
								level2Details = null;
								for(int b=0;b<level1Details.getChildList().size();b++)
								{
									level2Details = (CategoryDetails)level1Details.getChildList().get(b);
									
									// create level 2 element
									str.append("<dataCategory>");
									str.append(System.getProperty("line.separator"));
									
									// add Level 2 Label 
									str.append("<label>"+level2Details.getName()+"</label>");
									str.append(System.getProperty("line.separator"));
									
									
									// add Level 2 Name 
									str.append("<name>"+level2Details.getRefKey()+"</name>");
									str.append(System.getProperty("line.separator"));
									
									/*
									 * LEVEL 3
									 */
									if(null!=level2Details.getChildList() && level2Details.getChildList().size()>0)
									{
										level3Details = null;
										for(int c=0;c<level2Details.getChildList().size();c++)
										{
											level3Details = (CategoryDetails)level2Details.getChildList().get(c);
											
											// create level 3 element
											str.append("<dataCategory>");
											str.append(System.getProperty("line.separator"));
											
											// add Level 3 Label 
											str.append("<label>"+level3Details.getName()+"</label>");
											str.append(System.getProperty("line.separator"));
											
											
											// add Level 3 Name 
											str.append("<name>"+level3Details.getRefKey()+"</name>");
											str.append(System.getProperty("line.separator"));
											
											/*
											 * LEVEL 4
											 */
											if(null!=level3Details.getChildList() && level3Details.getChildList().size()>0)
											{
												level4Details = null;
												for(int d=0;d<level3Details.getChildList().size();d++)
												{
													level4Details = (CategoryDetails)level3Details.getChildList().get(d);
												
													// create level 4 element
													str.append("<dataCategory>");
													str.append(System.getProperty("line.separator"));
													
													// add Level 4 Label 
													str.append("<label>"+level4Details.getName()+"</label>");
													str.append(System.getProperty("line.separator"));
													
													// add Level 4 Name 
													str.append("<name>"+level4Details.getRefKey()+"</name>");
													str.append(System.getProperty("line.separator"));
													
													/*
													 * LEVEL 5
													 */
													if(null!=level4Details.getChildList() && level4Details.getChildList().size()>0)
													{
														level5Details = null;
														for(int e=0;e<level4Details.getChildList().size();e++)
														{
															level5Details = (CategoryDetails)level4Details.getChildList().get(e);
															
															// create level 5 element
															str.append("<dataCategory>");
															str.append(System.getProperty("line.separator"));
															
															// add Level 5 Label 
															str.append("<label>"+level5Details.getName()+"</label>");
															str.append(System.getProperty("line.separator"));
															
															
															// add Level 5 Name 
															str.append("<name>"+level5Details.getRefKey()+"</name>");
															str.append(System.getProperty("line.separator"));
															
															str.append("</dataCategory>");
															str.append(System.getProperty("line.separator"));
															level5Details = null;
														}
													}
													
													
													// append level 4 Element to level 3 Element
													str.append("</dataCategory>");
													str.append(System.getProperty("line.separator"));
													level4Details = null;
												}
											}
											
											// append level 3 Element to level 2 Element
											str.append("</dataCategory>");
											str.append(System.getProperty("line.separator"));
											level3Details = null;
										}
									}
									
									// append level 2 Element to level 1 Element
									str.append("</dataCategory>");
									str.append(System.getProperty("line.separator"));
									level2Details = null;
								}
							}
							
							// append level 1 Element to rootElement
							str.append("</dataCategory>");
							str.append(System.getProperty("line.separator"));
							level1Details = null;
						}
						
						str.append("<description>Information about Solar Panel system installation and maintenance</description>");
						str.append(System.getProperty("line.separator"));
						str.append("<label>Solar Installation &amp; Maintenance</label>");
						str.append(System.getProperty("line.separator"));
						str.append("<objectUsage><object>KnowledgeArticleVersion</object></objectUsage>");
						str.append(System.getProperty("line.separator"));
						str.append("</DataCategoryGroup>");
						
						if(null!=str)
						{
							count++;
							File file = new File("D:\\HACH_WD\\PRODUCT_CATEGORY_"+locale.toUpperCase()+"_"+count+".xml");
							FileOutputStream fos = new FileOutputStream(file);
							fos.write(str.toString().getBytes());
							fos.flush();
							fos.close();
							file= null;
						}
						str = null;
					}
					subList = null;
				}
			}
			
			categoryList = null;
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(GenerateCategoryXML.class.getName(), "main()", e);
		}
	}
	
	private static void xmlMethod()
	{
		try
		{
			String locale="en_US";
			TransactionDAO transactionDAO = new TransactionDAO();
			List<CategoryDetails> categoryList = transactionDAO.getCategoryHierarchyListForXML(locale,"RN_PRODUCT_1658");
			logger.info("main :: Categories Found are :: >"+ categoryList.size());
			DocumentBuilderFactory dbFactory =      DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();
			doc.normalize();
			// create RootElement
			Element rootElement = doc.createElement("DataCategoryGroup");
			Attr attr = doc.createAttribute("xmlns");
			attr.setValue("http://soap.sforce.com/2006/04/metadata");
			rootElement.setAttributeNode(attr);
			attr=null;
			
			Element childElement = doc.createElement("active");
			childElement.appendChild(doc.createTextNode("true"));
			rootElement.appendChild(childElement);

			childElement = null;
			if(null!=categoryList && categoryList.size()>0)
			{
				CategoryDetails level1Details = null;
				CategoryDetails level2Details = null;
				CategoryDetails level3Details = null;
				CategoryDetails level4Details = null;
				CategoryDetails level5Details = null;
				
				Element level1Element = null;
				Element level2Element=  null;
				Element level3Element=  null;
				Element level4Element=  null;
				Element level5Element=  null;
				
				Element labelElement=null;
				Element nameElement=null;
				
//				for(int a=0;a<categoryList.size();a++)
				{
					level1Details = (CategoryDetails)categoryList.get(0);
					// create level 1 element
					level1Element = doc.createElement("dataCategory");
					// add Level 1 Label 
					labelElement = doc.createElement("label");
					labelElement.appendChild(doc.createTextNode(level1Details.getName()));
					level1Element.appendChild(labelElement);
					labelElement = null;
					
					// add Level 1 Name 
					nameElement = doc.createElement("name");
					nameElement.appendChild(doc.createTextNode(level1Details.getRefKey()));
					level1Element.appendChild(nameElement);
					nameElement = null;
					
					/*
					 * LEVEL 2
					 */
					if(null!=level1Details.getChildList() && level1Details.getChildList().size()>0)
					{
						level2Details = null;
						for(int b=0;b<level1Details.getChildList().size();b++)
						{
							level2Details = (CategoryDetails)level1Details.getChildList().get(b);
							
							// create level 2 element
							level2Element = doc.createElement("dataCategory");
							// add Level 2 Label 
							labelElement = doc.createElement("label");
							labelElement.appendChild(doc.createTextNode(level2Details.getName()));
							level2Element.appendChild(labelElement);
							labelElement = null;
							
							// add Level 2 Name 
							nameElement = doc.createElement("name");
							nameElement.appendChild(doc.createTextNode(level2Details.getRefKey()));
							level2Element.appendChild(nameElement);
							nameElement = null;
							
							/*
							 * LEVEL 3
							 */
							if(null!=level2Details.getChildList() && level2Details.getChildList().size()>0)
							{
								level3Details = null;
								for(int c=0;c<level2Details.getChildList().size();c++)
								{
									level3Details = (CategoryDetails)level2Details.getChildList().get(c);
									
									// create level 3 element
									level3Element = doc.createElement("dataCategory");
									// add Level 3 Label 
									labelElement = doc.createElement("label");
									labelElement.appendChild(doc.createTextNode(level3Details.getName()));
									level3Element.appendChild(labelElement);
									labelElement = null;
									
									// add Level 3 Name 
									nameElement = doc.createElement("name");
									nameElement.appendChild(doc.createTextNode(level3Details.getRefKey()));
									level3Element.appendChild(nameElement);
									nameElement = null;
									
									/*
									 * LEVEL 4
									 */
									if(null!=level3Details.getChildList() && level3Details.getChildList().size()>0)
									{
										level4Details = null;
										for(int d=0;d<level3Details.getChildList().size();d++)
										{
											level4Details = (CategoryDetails)level3Details.getChildList().get(d);
										
											// create level 4 element
											level4Element = doc.createElement("dataCategory");
											// add Level 4 Label 
											labelElement = doc.createElement("label");
											labelElement.appendChild(doc.createTextNode(level4Details.getName()));
											level4Element.appendChild(labelElement);
											labelElement = null;
											
											// add Level 4 Name 
											nameElement = doc.createElement("name");
											nameElement.appendChild(doc.createTextNode(level4Details.getRefKey()));
											level4Element.appendChild(nameElement);
											nameElement = null;
											
											/*
											 * LEVEL 5
											 */
											if(null!=level4Details.getChildList() && level4Details.getChildList().size()>0)
											{
												level5Details = null;
												for(int e=0;e<level4Details.getChildList().size();e++)
												{
													level5Details = (CategoryDetails)level4Details.getChildList().get(e);
													
													// create level 5 element
													level5Element = doc.createElement("dataCategory");
													// add Level 5 Label 
													labelElement = doc.createElement("label");
													labelElement.appendChild(doc.createTextNode(level5Details.getName()));
													level5Element.appendChild(labelElement);
													labelElement = null;
													
													// add Level 5 Name 
													nameElement = doc.createElement("name");
													nameElement.appendChild(doc.createTextNode(level5Details.getRefKey()));
													level5Element.appendChild(nameElement);
													nameElement = null;
													
													// append level 5 Element to level 5 Element
													level4Element.appendChild(level5Element);
													level5Element = null;
													level5Details = null;
												}
											}
											
											
											// append level 4 Element to level 3 Element
											level3Element.appendChild(level4Element);
											level4Element = null;
											level4Details = null;
										}
									}
									
									// append level 3 Element to level 2 Element
									level2Element.appendChild(level3Element);
									level3Element = null;
									level3Details = null;
								}
							}
							
							// append level 2 Element to level 1 Element
							level1Element.appendChild(level2Element);
							level2Element = null;
							level2Details = null;
						}
					}
					
					// append level 1 Element to rootElement
					rootElement.appendChild(level1Element);
					level1Element = null;
					level1Details = null;
				}
			}
			
			
			childElement = doc.createElement("description");
			childElement.appendChild(doc.createTextNode("Information about Solar Panel system installation and maintenance"));
			rootElement.appendChild(childElement);
			childElement = null;
			
			childElement = doc.createElement("label");
			childElement.appendChild(doc.createTextNode("Solar Installation & Maintenance"));
			rootElement.appendChild(childElement);
			childElement = null;
			
			childElement = doc.createElement("objectUsage");
			Element subChildElement = doc.createElement("object");
			subChildElement.appendChild(doc.createTextNode("Solar Installation & Maintenance"));
			childElement.appendChild(subChildElement);
			rootElement.appendChild(childElement);
			childElement = null;
			subChildElement = null;
			
			// add rootElement to document
			doc.appendChild(rootElement);
			rootElement = null;
			
			if(null!=doc)
			{
				String xmlData = Utilities.transformString(doc);
				File file = new File("D:\\HACH_WD\\DOM_PRODUCT_CATEGORY_1"+locale.toUpperCase()+".xml");
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(xmlData.getBytes());
				fos.flush();
				fos.close();
				file= null;
				xmlData = null;
			}
			doc = null;
			transactionDAO = null;
			categoryList = null;
			dbFactory =      null;
			dBuilder = null;
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(GenerateCategoryXML.class.getName(), "xmlMethod", e);
		}
	}
}
