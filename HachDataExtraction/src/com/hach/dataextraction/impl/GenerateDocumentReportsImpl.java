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
import com.hach.dataextraction.utils.common.DatabaseConnector;
import com.hach.dataextraction.utils.common.Utilities;
import com.hach.dataextraction.vo.ContentDetails;

public class GenerateDocumentReportsImpl {

	private static Logger logger = Logger.getLogger(GenerateDocumentReportsImpl.class);

	public static void main(String[] args) {
		// initialize loggers
		File f = new File(FetchAttachmentsDataImpl.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		PropertyConfigurator.configure(f.getParentFile().getAbsolutePath()+"/log.properties");
		f=  null;
		try
		{
			List<ContentDetails> documentsList = new ArrayList<ContentDetails>();
			List<ContentDetails> tempList = null;
			
			TransactionDAO dao  =new TransactionDAO();
			List<String> channelList = dao.getChannelDetails();
			if(null!=channelList && channelList.size()>0)
			{
				for(int a=0;a<channelList.size();a++)
				{
					tempList = getDocumentsList(channelList.get(a));
					if(null!=tempList && tempList.size()>0)
					{
//						if(channelList.get(a).toString().toUpperCase().equals("TECHNICAL_QA"))
						{
							/*
							 * break into parititions and generate multiple list
							 */
//							int partitionSize=5000;
//							ArrayList<List<ContentDetails>> partitions = new ArrayList<List<ContentDetails>>();
//							for (int i=0; i<tempList.size(); i += partitionSize) {
//								partitions.add(tempList.subList(i, Math.min(i + partitionSize, tempList.size())));
//							}
//							
//							if(null!=partitions && partitions.size()>0)
//							{
//								int count=0;
//								for(List<ContentDetails> subList : partitions)
//								{
//									count++;
//									printReportsCSV(subList,channelList.get(a), count);
//								}
//							}
						}
						documentsList.addAll(tempList);
					}
					tempList = null;
				}
			}
			channelList  =null;
			
			
			if(null!=documentsList && documentsList.size()>0)
			{
				printReports(documentsList);
//				printReportsCSV(documentsList);
			}
			documentsList = null;
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(GenerateDocumentReportsImpl.class.getName(), "main()", e);;
		}
	}

	public static List<ContentDetails> getDocumentsList(String channelRefKey)
	{
		List<ContentDetails> documentList = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		Connection conn =null;
		try
		{
			conn = DatabaseConnector.getConnection();
			String sql="SELECT * FROM IM_DOCUMENT_"+channelRefKey.toUpperCase().trim()+"  ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ContentDetails details =  null;
			documentList = new ArrayList<ContentDetails>();
			while(rs.next())
			{
				details = new ContentDetails();
				details.setChannelRefKey(channelRefKey.toUpperCase());
				details.setDocumentId(rs.getString("DOCUMENT_ID")); 
				details.setBaseLocale(rs.getString("BASE_LOCALE"));
				details.setLocale(rs.getString("DOCUMENT_LOCALE"));
				details.setAnswerId(rs.getString("ANSWER_ID"));
				details.setTitle(rs.getString("TITLE"));
				details.setOwnerName(rs.getString("OWNER_NAME"));
				details.setOwnerEmail(rs.getString("OWNER_EMAIL"));
				details.setLastModifierName(rs.getString("LAST_MODIFIER_NAME"));
				details.setLastModifierEmail(rs.getString("LAST_MODIFIER_EMAIL"));
				details.setLastModifiedDate(rs.getString("LAST_MODIFIED_DATE"));
				details.setPublishDate(rs.getString("PUBLISH_DATE"));
				details.setCreateDate(rs.getString("CREATE_DATE"));
				details.setDisplayEndDate(rs.getString("DISPLAY_END_DATE"));
				details.setDocArchived(rs.getString("DOC_ARCHIVED"));
				
				// SF Fields
				details.setSfRecordId(rs.getString("SF_DOCUMENT_ID"));
				details.setSfLocale(rs.getString("SF_LOCALE"));
				details.setSfArticleId(rs.getString("SF_ARTICLE_ID"));
				details.setSfArticleNumber(rs.getString("SF_ARTICLE_NUMBER"));
				details.setSfChannelId(rs.getString("SF_CHANNEL_ID"));
				details.setSfdocumentURL(rs.getString("SF_DOCUMENT_URL"));
				details.setSfMasterIdentifier(rs.getString("SF_MASTER_IDENTIFIER"));
				details.setSfURLName(rs.getString("SF_URL_NAME"));
				details.setAllCategoriesMapped(rs.getString("SF_ALL_CATEGORIES_MAPPED"));
				details.setAllInnerLinksMapped(rs.getString("SF_ALL_INRLKS_ARTICLES_MAPPED"));
				details.setSfProcessingStatus(rs.getString("SF_PROCESSING_STATUS"));
				if(null!=rs.getBytes("SF_ERROR_MESSAGE"))
				{
					details.setSfErrorMessage(new String(rs.getBytes("SF_ERROR_MESSAGE")));
				}
				details.setSfRemarks(rs.getString("SF_REMARKS"));
				documentList.add(details);
				details = null;
			}
			sql  =null;
			
			logger.info("getDocumentsList ::  Total Documents Found FOR "+channelRefKey+" >> are :: >"+ documentList.size());
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(AttachmentsTransactionDAO.class.getName(), "getDocumentsList()", e);
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
				Utilities.printStackTraceToLogs(AttachmentsTransactionDAO.class.getName(), "getDocumentsList()", e);
			}
			pstmt=null;
			rs = null;
			conn = null;
		}
		return documentList;
	}

	private static void printReports(List<ContentDetails> documentsList)
	{
		try
		{
			logger.info("printReports :: Failure List : >"+ documentsList.size());
			if(null!=documentsList && documentsList.size()>0)
			{
//				String path = ApplicationProperties.getProperty("attachments.dir.path");
				String path="D:/HACH_WD/FULL_INSTANCE_RUN_16_JUNE_2022";
				String fName = "/DOCUMENTS_REPORT.xlsx";

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

				String headers="CHANNEL,DOCUMENT_ID,DOC_LOCALE,BASE_LOCALE,ANSWER_ID,DOC_ARCHIVED,TITLE,OWNER_NAME,OWNER_EMAIL,LAST_MODIFIER_NAME,LAST_MODIFIER_EMAIL,PUBLISH_DATE,CREATE_DATE,DISPLAY_END_DATE,LAST_MODIFIED_DATE,SF_DOCUMENT_ID,SF_LOCALE,SF_MASTER_IDENTIFIER,SF_URL_NAME,SF_ARTICLE_NUMBER,SF_ARTICLE_ID,SF_CHANNEL_ID,SF_ALL_CATEGORIES_MAPPED,SF_ALL_INRLKS_ARTICLES_MAPPED,SF_PROCESSING_STATUS,SF_DOCUMENT_URL,SF_ERROR_MESSAGE,SF_REMARKS"; 
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

				ContentDetails details = null;
				for(int a=0;a<documentsList.size();a++)
				{
					details = (ContentDetails)documentsList.get(a);
					
					dataRow=details.getChannelRefKey()+"<TOK_SEPARATOR>"+details.getDocumentId()+"<TOK_SEPARATOR>"+details.getLocale()+"<TOK_SEPARATOR>";
					dataRow+=details.getBaseLocale()+"<TOK_SEPARATOR>"+details.getAnswerId()+"<TOK_SEPARATOR>"+details.getDocArchived()+"<TOK_SEPARATOR>";
					dataRow+=details.getTitle()+"<TOK_SEPARATOR>"+details.getOwnerName()+"<TOK_SEPARATOR>"+details.getOwnerEmail()+"<TOK_SEPARATOR>";
					dataRow+=details.getLastModifierName()+"<TOK_SEPARATOR>"+details.getLastModifierEmail()+"<TOK_SEPARATOR>"+details.getPublishDate()+"<TOK_SEPARATOR>";
					dataRow+=details.getCreateDate()+"<TOK_SEPARATOR>"+details.getDisplayEndDate()+"<TOK_SEPARATOR>"+details.getLastModifiedDate()+"<TOK_SEPARATOR>";
					dataRow+=details.getSfRecordId()+"<TOK_SEPARATOR>"+details.getSfLocale()+"<TOK_SEPARATOR>"+details.getSfMasterIdentifier()+"<TOK_SEPARATOR>";
					dataRow+=details.getSfURLName()+"<TOK_SEPARATOR>"+details.getSfArticleNumber()+"<TOK_SEPARATOR>"+details.getSfArticleId()+"<TOK_SEPARATOR>";
					dataRow+=details.getSfChannelId()+"<TOK_SEPARATOR>"+details.getAllCategoriesMapped()+"<TOK_SEPARATOR>"+details.getAllInnerLinksMapped()+"<TOK_SEPARATOR>";
					dataRow+=details.getSfProcessingStatus()+"<TOK_SEPARATOR>"+details.getSfdocumentURL()+"<TOK_SEPARATOR>"+details.getSfErrorMessage()+"<TOK_SEPARATOR>";
					dataRow+=details.getSfRemarks();
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
				logger.info("Writing on DOCUMENT REPORT XLSX file Finished ...");
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
			Utilities.printStackTraceToLogs(GenerateDocumentReportsImpl.class.getName(),"printReports()" , e);
		}
	}

//	private static void printReportsCSV(List<ContentDetails> documentsList,String channelRefKey, int count)
//	{
//		try
//		{
//			logger.info("printReportsCSV :: Failure List : >"+ documentsList.size());
//			if(null!=documentsList && documentsList.size()>0)
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
//				ContentDetails details = null;
//				for(int a=0;a<documentsList.size();a++)
//				{
//					details = (ContentDetails)documentsList.get(a);
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
