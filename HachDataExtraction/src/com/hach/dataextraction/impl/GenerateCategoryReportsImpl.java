package com.hach.dataextraction.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.hach.dataextraction.dao.AttachmentsTransactionDAO;
import com.hach.dataextraction.dao.TransactionDAO;
import com.hach.dataextraction.utils.common.ApplicationProperties;
import com.hach.dataextraction.utils.common.DatabaseConnector;
import com.hach.dataextraction.utils.common.Utilities;
import com.hach.dataextraction.vo.CategoryDetails;

public class GenerateCategoryReportsImpl {

	private static Logger logger = Logger.getLogger(GenerateCategoryReportsImpl.class);

	public static void main(String[] args) {
		// initialize loggers
		File f = new File(FetchAttachmentsDataImpl.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		PropertyConfigurator.configure(f.getParentFile().getAbsolutePath()+"/log.properties");
		f=  null;
		try
		{
			List<CategoryDetails> categoryList = new ArrayList<CategoryDetails>();
			List<CategoryDetails> tempList = null;
			
			TransactionDAO dao  =new TransactionDAO();
			List<String> channelList = dao.getChannelDetails();
			if(null!=channelList && channelList.size()>0)
			{
				for(int a=0;a<channelList.size();a++)
				{
					tempList = getCategoryList(channelList.get(a));
					if(null!=tempList && tempList.size()>0)
					{
//						if(channelList.get(a).toString().toUpperCase().equals("TECHNICAL_QA"))
						{
							/*
							 * break into parititions and generate multiple list
							 */
//							int partitionSize=5000;
//							ArrayList<List<CategoryDetails>> partitions = new ArrayList<List<CategoryDetails>>();
//							for (int i=0; i<tempList.size(); i += partitionSize) {
//								partitions.add(tempList.subList(i, Math.min(i + partitionSize, tempList.size())));
//							}
//							
//							if(null!=partitions && partitions.size()>0)
//							{
//								int count=0;
//								for(List<CategoryDetails> subList : partitions)
//								{
//									count++;
//									printReportsCSV(subList,channelList.get(a), count);
//								}
//							}
						}
						categoryList.addAll(tempList);
					}
					tempList = null;
				}
			}
			channelList  =null;
			
			
			if(null!=categoryList && categoryList.size()>0)
			{
				printReports(categoryList);
//				printReportsCSV(categoryList);
			}
			categoryList = null;
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(GenerateCategoryReportsImpl.class.getName(), "main()", e);;
		}
	}

	public static List<CategoryDetails> getCategoryList(String channelRefKey)
	{
		List<CategoryDetails> categoryList = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		Connection conn =null;
		try
		{
			conn = DatabaseConnector.getConnection();
			String sql="SELECT A.* FROM IM_CATEGORY_"+channelRefKey.toUpperCase().trim()+" A,IM_DOCUMENT_"+channelRefKey.trim().toUpperCase()+" B "
					+ " WHERE A.DOCUMENT_ID=B.DOCUMENT_ID AND A.LOCALE=B.DOCUMENT_LOCALE AND B.DOC_ARCHIVED!='YES'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			CategoryDetails details =  null;
			categoryList = new ArrayList<CategoryDetails>();
			while(rs.next())
			{
				details = new CategoryDetails();
				details.setChannelRefKey(channelRefKey.toUpperCase());
				details.setDocumentId(rs.getString("DOCUMENT_ID"));
				details.setLocale(rs.getString("LOCALE"));
				details.setRefKey(rs.getString("REF_KEY"));
				details.setName(rs.getString("NAME"));
				details.setExternalType(rs.getString("EXTERNAL_TYPE"));
				details.setLevel1Name(rs.getString("LEVEL_1_NAME"));
				details.setLevel1RefKey(rs.getString("LEVEL_1_REF_KEY"));
				details.setLevel2Name(rs.getString("LEVEL_2_NAME"));
				details.setLevel2RefKey(rs.getString("LEVEL_2_REF_KEY"));
				details.setLevel3Name(rs.getString("LEVEL_3_NAME"));
				details.setLevel3RefKey(rs.getString("LEVEL_3_REF_KEY"));
				details.setLevel4Name(rs.getString("LEVEL_4_NAME"));
				details.setLevel4RefKey(rs.getString("LEVEL_4_REF_KEY"));
				details.setLevel5Name(rs.getString("LEVEL_5_NAME"));
				details.setLevel5RefKey(rs.getString("LEVEL_5_REF_KEY"));
				
				
				details.setSfMappingId(rs.getString("SF_CAT_ASSOCIATION_ID"));
				details.setSfProcessingStatus(rs.getString("SF_MAPPING_STATUS"));
				if(null!=rs.getBytes("SF_ERROR_MESSAGE"))
				{
					details.setSfErrorMessage(new String(rs.getBytes("SF_ERROR_MESSAGE")));
				}
				categoryList.add(details);
				details = null;
			}
			sql  =null;
			
			logger.info("getCategoryList ::  Total Categories Found FOR "+channelRefKey+" >> are :: >"+ categoryList.size());
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(AttachmentsTransactionDAO.class.getName(), "getCategoryList()", e);
		}
		finally
		{
			try
			{
				if(null!=pstmt)
				{
					pstmt.close();
				}
				if(null!=rs)
				{
					rs.close();
				}
				if(null!=conn)
				{
					conn.close();
				}
			}
			catch(SQLException e)
			{
				Utilities.printStackTraceToLogs(AttachmentsTransactionDAO.class.getName(), "getCategoryList()", e);
			}
			pstmt=null;
			rs = null;
			conn = null;
		}
		return categoryList;
	}

	private static void printReports(List<CategoryDetails> categoryList)
	{
		try
		{
			logger.info("printReports :: Failure List : >"+ categoryList.size());
			if(null!=categoryList && categoryList.size()>0)
			{
				String path = ApplicationProperties.getProperty("attachments.dir.path");
				String fName = "/CATEGORIES_REPORT.xlsx";

				File myFile = new File(path + fName);	
				fName = null;
				// Create the workbook instance for XLSX file, KEEP 100 ROWS IN MEMMORY AND RET ON DISK
				@SuppressWarnings("resource")
				SXSSFWorkbook myWorkBook = new SXSSFWorkbook(100);

				// Create a new sheet
				Sheet mySheet = myWorkBook.createSheet("Details");
				/*
				 * Add Header Row
				 */
				Row headerRow = mySheet.createRow(0);
				Cell headerCell = null;

				String headers="CHANNEL,DOCUMENT_ID,LOCALE,REF_KEY,NAME,ITEM_TYPE,LEVEL_1_NAME,LEVEL_1_REF_KEY,LEVEL_2_NAME,LEVEL_2_REF_KEY,LEVEL_3_NAME,LEVEL_3_REF_KEY,LEVEL_4_NAME,LEVEL_4_REF_KEY,LEVEL_5_NAME,LEVEL_5_REF_KEY,SF_CAT_ASSOCIATION_ID,SF_MAPPING_STATUS,SF_ERROR"; 
				String[] tokens=headers.split(",");
				if(null!=tokens && tokens.length>0)
				{
					for(int a=0;a<tokens.length;a++)
					{
						headerCell = headerRow.createCell(a);
						headerCell.setCellValue(tokens[a]);
						headerCell  = null;
					}
				}
				tokens = null;
				headerCell=null;
				headerRow=null;
				headers=null;

				int rowCount = 0;
				/*
				 * GENERATE MULTIPLE ROWS SUCH THAT
				 */
				String dataRow="";
				Row row=null;
				Cell dataCell=null;

				CategoryDetails details = null;
				for(int a=0;a<categoryList.size();a++)
				{
					details = (CategoryDetails)categoryList.get(a);
					
					dataRow=details.getChannelRefKey()+"<TOK_SEPARATOR>"+details.getDocumentId()+"<TOK_SEPARATOR>"+details.getLocale()+"<TOK_SEPARATOR>";
					dataRow+=details.getRefKey()+"<TOK_SEPARATOR>"+details.getName()+"<TOK_SEPARATOR>"+details.getExternalType()+"<TOK_SEPARATOR>";
					dataRow+=details.getLevel1Name()+"<TOK_SEPARATOR>"+details.getLevel1RefKey()+"<TOK_SEPARATOR>"+details.getLevel2Name()+"<TOK_SEPARATOR>";
					dataRow+=details.getLevel2RefKey()+"<TOK_SEPARATOR>"+details.getLevel3Name()+"<TOK_SEPARATOR>"+details.getLevel3RefKey()+"<TOK_SEPARATOR>";
					dataRow+=details.getLevel4Name()+"<TOK_SEPARATOR>"+details.getLevel4RefKey()+"<TOK_SEPARATOR>"+details.getLevel5Name()+"<TOK_SEPARATOR>";
					dataRow+=details.getLevel5RefKey()+"<TOK_SEPARATOR>"+details.getSfMappingId()+"<TOK_SEPARATOR>"+details.getSfProcessingStatus()+"<TOK_SEPARATOR>";
					dataRow+=details.getSfErrorMessage();
					// increment rowCount by 1
					rowCount++;
					// Create a new Row
					row = mySheet.createRow(rowCount);
					tokens = dataRow.split("<TOK_SEPARATOR>");
					if(null!=tokens && tokens.length>0)
					{
						for(int e=0;e<tokens.length;e++)
						{
							dataCell = row.createCell(e);
							dataCell.setCellValue("");
							if(null!=tokens[e] && !"".equals(tokens[e]) && !"null".equals(tokens[e].trim().toLowerCase()))
							{
								dataCell.setCellValue(tokens[e].trim());
							}
							dataCell =null;
						}
					}
					tokens = null;
					row=null;
					dataRow = null;
					dataCell = null;

					details = null;
				}

				headerRow = null;

				FileOutputStream os = new FileOutputStream(myFile);
				myWorkBook.write(os);
				logger.info("Writing on CATEGORY REPORT XLSX file Finished ...");
				os.flush();
				os.close();

				// set mySheet to null
				mySheet = null;
				// set myWorkBook to null
				myWorkBook = null;
				// set path to null
				path = null;
				// set myFile to null
				myFile = null;
			}
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(GenerateCategoryReportsImpl.class.getName(),"printReports()" , e);
		}
	}

//	private static void printReportsCSV(List<CategoryDetails> categoryList,String channelRefKey, int count)
//	{
//		try
//		{
//			logger.info("printReportsCSV :: Failure List : >"+ categoryList.size());
//			if(null!=categoryList && categoryList.size()>0)
//			{
//				String path = ApplicationProperties.getProperty("attachments.dir.path");
//				String fName = "/"+channelRefKey.toUpperCase()+"_ATTACHMENT_REPORT_"+count+".txt";
//
//				File myFile = new File(path + fName);
//				fName = null;
//
//				StringBuilder str = new StringBuilder();
//				// header Row
//				str.append("CHANNEL||DOCUMENT_ID||LOCALE||ATTACHMENT_TYPE||ATTACHMENT_NAME||SALESFORCE_FIELD||SIZE||STATUS||KA_RESOURCE_URL||SOURCE_URL");
//				str.append(System.getProperty("line.separator"));
//				/*
//				 * GENERATE MULTIPLE ROWS SUCH THAT
//				 */
//				String dataRow="";
//
//				CategoryDetails details = null;
//				for(int a=0;a<categoryList.size();a++)
//				{
//					details = (CategoryDetails)categoryList.get(a);
//					dataRow=details.getChannelRefKey()+"||"+details.getDocumentId()+"||"+details.getLocale()+"||";
//					dataRow+=details.getAttachmentType()+"||"+details.getAttachmentName()+"||"+details.getSalesForceFieldName()+"||";
//					dataRow+=details.getSize()+"||"+details.getStatus()+"||"+details.getKaResourcePath()+"||";
////					dataRow+=details.getSourcePath()+"||"+details.getErrorMessage();
//					dataRow+=details.getSourcePath();
//					// remove all line breaks
//					dataRow = dataRow.replace("\r\n", "");
//					dataRow = dataRow.replace("\n", "");
//					dataRow = dataRow.replace("NULL", "");
//					dataRow = dataRow.replace("null", "");
//					
//					str.append(dataRow);
//					str.append(System.getProperty("line.separator"));
//					dataRow = null;
//					details = null;
//				}
//
//
//				FileOutputStream os = new FileOutputStream(myFile);
//				os.write(str.toString().getBytes());
//				logger.info("Writing on ATTACHMENT REPORT XLSX file Finished ...");
//				os.flush();
//				os.close();
//				// set path to null
//				path = null;
//				// set myFile to null
//				myFile = null;
//				str=null;
//			}
//		}
//		catch(Exception e)
//		{
//			Utilities.printStackTraceToLogs(GenerateDocumentReportsImpl.class.getName(),"printReportsCSV()" , e);
//		}
//	}

	
}
