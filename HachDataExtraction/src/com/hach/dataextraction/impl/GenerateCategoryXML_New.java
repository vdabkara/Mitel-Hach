package com.hach.dataextraction.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.hach.dataextraction.dao.CategoryLevelFetchDAO;
import com.hach.dataextraction.utils.common.FileReadWriteUtil;
import com.hach.dataextraction.utils.common.Utilities;
import com.hach.dataextraction.vo.CategoryDetails;

public class GenerateCategoryXML_New {

	private static Logger logger = Logger.getLogger(StartExtractionImpl.class);

	public static void main(String[] args) {
		// initialize loggers
		File f = new File(GenerateCategoryXML_New.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		PropertyConfigurator.configure(f.getParentFile().getAbsolutePath()+"/log.properties");
		f=  null;
		try
		{
			String locale="en_US";
			CategoryLevelFetchDAO catDao  =new CategoryLevelFetchDAO();
			List<CategoryDetails> level1List=catDao.getLevel1(locale);
			
			CategoryDetails level1Details = null;
			CategoryDetails level2Details = null;
			CategoryDetails level3Details = null;
			CategoryDetails level4Details = null;
//			CategoryDetails level5Details = null;
			
			List<CategoryDetails> level2List = null;
			List<CategoryDetails> level3List = null;
			List<CategoryDetails> level4List = null;
//			List<CategoryDetails> level5List = null;
			
			
			if(null!=level1List && level1List.size()>0)
			{
				int partitionSize=1;
				ArrayList<List<CategoryDetails>> partitions = new ArrayList<List<CategoryDetails>>();
				for (int i=0; i<level1List.size(); i += partitionSize) {
					partitions.add(level1List.subList(i, Math.min(i + partitionSize, level1List.size())));
				}
				
				if(null!=partitions && partitions.size()>0)
				{
					int count=0;
//					for(List<CategoryDetails> subList : partitions)
					{
						count=320;
//						if(null!=subList && subList.size()>0)
						{
							StringBuilder str = new StringBuilder();
							str.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
							str.append(System.getProperty("line.separator"));
							str.append("<DataCategoryGroup xmlns=\"http://soap.sforce.com/2006/04/metadata\">");
							str.append(System.getProperty("line.separator"));
							str.append("<active>true</active>");
							str.append(System.getProperty("line.separator"));
							str.append("<dataCategory>");
							str.append(System.getProperty("line.separator"));
							
							for(int a=0;a<level1List.size();a++)
							{
								level1Details =(CategoryDetails)level1List.get(a);
								count++;
								
								// create level 1 element
								str.append("<dataCategory>");
								str.append(System.getProperty("line.separator"));
								
								if(null!=level1Details.getRefKey() && !"".equals(level1Details.getRefKey()))
								{
									/*
									 * FETCH LEVEL 2
									 */
									level2List = catDao.getLevel2(locale, level1Details.getRefKey());
									if(null!=level2List && level2List.size()>0)
									{
										level2Details  =null;
										for(int b=0;b<level2List.size();b++)
										{
											level2Details = (CategoryDetails)level2List.get(b);
											
											// create level 2 element
											str.append("<dataCategory>");
											str.append(System.getProperty("line.separator"));
											
											if(null!=level2Details.getRefKey() && !"".equals(level2Details.getRefKey()))
											{
												/*
												 * FETCH LEVEL 3
												 */
												level3List = catDao.getLevel3(locale, level1Details.getRefKey(), level2Details.getRefKey());
												if(null!=level3List && level3List.size()>0)
												{
													level3Details  =null;
													for(int c=0;c<level3List.size();c++)
													{
														level3Details = (CategoryDetails)level3List.get(c);
														
														// create level 3 element
														str.append("<dataCategory>");
														str.append(System.getProperty("line.separator"));
														
														if(null!=level3Details.getRefKey() && !"".equals(level3Details.getRefKey()))
														{
															/*
															 * FETCH LEVEL 4
															 */
															level4List = catDao.getLevel4(locale, level1Details.getRefKey(), level2Details.getRefKey(),level3Details.getRefKey());
															if(null!=level4List && level4List.size()>0)
															{
																level4Details  =null;
																for(int d=0;d<level4List.size();d++)
																{
																	level4Details = (CategoryDetails)level4List.get(d);
																	
																	// create level 4 element
																	str.append("<dataCategory>");
																	str.append(System.getProperty("line.separator"));
																	
																	// add Level 4 Label 
																	str.append("<label>"+level4Details.getName()+"</label>");
																	str.append(System.getProperty("line.separator"));
																	
																	
																	// add Level 4 Name 
																	str.append("<name>"+level4Details.getRefKey()+"</name>");
																	str.append(System.getProperty("line.separator"));
																	
//																	if(null!=level4Details.getRefKey() && !"".equals(level4Details.getRefKey()))
//																	{
																		/*
																		 * FETCH LEVEL 5
																		 */
//																		level5List = catDao.getLevel5(locale, level1Details.getRefKey(), level2Details.getRefKey(),level3Details.getRefKey(), level4Details.getRefKey());
//																		if(null!=level5List && level5List.size()>0)
//																		{
//																			level5Details  =null;
//																			for(int e=0;e<level5List.size();e++)
//																			{
//																				level5Details = (CategoryDetails)level5List.get(e);
//																				
//																				// create level 5 element
//																				str.append("<dataCategory>");
//																				str.append(System.getProperty("line.separator"));
//																				
//																				// add Level 5 Label 
//																				str.append("<label>"+level5Details.getName()+"</label>");
//																				str.append(System.getProperty("line.separator"));
//																				
//																				
//																				// add Level 5 Name 
//																				str.append("<name>"+level5Details.getRefKey()+"</name>");
//																				str.append(System.getProperty("line.separator"));
//																				
//																				level5Details = null;
//																				str.append("</dataCategory>");
//																				str.append(System.getProperty("line.separator"));
//																			}
//																		}
//																		level5List = null;
//																	}
																	level4Details = null;
																	str.append("</dataCategory>");
																	str.append(System.getProperty("line.separator"));
																}
															}
															level4List = null;
														}
														
														// add Level 3 Label 
														str.append("<label>"+level3Details.getName()+"</label>");
														str.append(System.getProperty("line.separator"));
														
														
														// add Level 3 Name 
														str.append("<name>"+level3Details.getRefKey()+"</name>");
														str.append(System.getProperty("line.separator"));
														
														level3Details = null;
														str.append("</dataCategory>");
														str.append(System.getProperty("line.separator"));
													}
												}
												level3List = null;
											}
											
											// add Level 2 Label 
											str.append("<label>"+level2Details.getName()+"</label>");
											str.append(System.getProperty("line.separator"));
											
											
											// add Level 2 Name 
											str.append("<name>"+level2Details.getRefKey()+"</name>");
											str.append(System.getProperty("line.separator"));
											
											level2Details = null;
											str.append("</dataCategory>");
											str.append(System.getProperty("line.separator"));
										}
									}
									level2List = null;
								}
								// add Level 1 Label 
								str.append("<label>"+level1Details.getName()+"</label>");
								str.append(System.getProperty("line.separator"));
								
								// add Level 1 Name 
								str.append("<name>"+level1Details.getRefKey()+"</name>");
								str.append(System.getProperty("line.separator"));
								
								level1Details = null;
								str.append("</dataCategory>");
								str.append(System.getProperty("line.separator"));
							}
							str.append("<label>All</label>");
							str.append(System.getProperty("line.separator"));
							str.append("<name>All</name>");
							str.append(System.getProperty("line.separator"));
							str.append("</dataCategory>");
							str.append(System.getProperty("line.separator"));
							str.append("<description>Product and Categories</description>");
							str.append(System.getProperty("line.separator"));
							str.append("<label>Product and Categories</label>");
							str.append(System.getProperty("line.separator"));
							str.append("<objectUsage><object>KnowledgeArticleVersion</object></objectUsage>");
							str.append(System.getProperty("line.separator"));
							str.append("</DataCategoryGroup>");
							
							if(null!=str)
							{
								FileReadWriteUtil.writeFile(str.toString().getBytes("UTF-8"),"D:\\HACH_WD\\PRODUCT_CATEGORY_"+locale.toUpperCase()+".xml");
//								
//								File file = new File("D:\\HACH_WD\\PRODUCT_CATEGORY_"+locale.toUpperCase()+"_"+count+".xml");
//								FileOutputStream fos = new FileOutputStream(file);
//								fos.write(str.toString().getBytes());
//								fos.flush();
//								fos.close();
//								file= null;
							}
							
							str = null;
						}
					}
				}
				partitions = null;
			}
			level1List = null;
			catDao  =null;
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(GenerateCategoryXML_New.class.getName(), "main()", e);
		}
	}
	
}
