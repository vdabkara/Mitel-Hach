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
import com.hach.dataextraction.vo.AttachmentDetails;
import com.hach.dataextraction.vo.CategoryDetails;


public class GenerateInlineImagesReportsImpl {

	private static Logger logger = Logger.getLogger(GenerateInlineImagesReportsImpl.class);

	public static void main(String[] args) {
		// initialize loggers
		File f = new File(FetchAttachmentsDataImpl.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		PropertyConfigurator.configure(f.getParentFile().getAbsolutePath()+"/log.properties");
		f=  null;
		try
		{
			List<AttachmentDetails> attachmentsList = new ArrayList<AttachmentDetails>();
			List<AttachmentDetails> tempList = null;
			
			TransactionDAO dao  =new TransactionDAO();
			List<String> channelList = dao.getChannelDetails();
			if(null!=channelList && channelList.size()>0)
			{
				for(int a=0;a<channelList.size();a++)
				{
					tempList = getAttachmentsList(channelList.get(a));
					if(null!=tempList && tempList.size()>0)
					{
//						if(channelList.get(a).toString().toUpperCase().equals("TECHNICAL_QA"))
						{
							/*
							 * break into parititions and generate multiple list
							 */
//							int partitionSize=5000;
//							ArrayList<List<AttachmentDetails>> partitions = new ArrayList<List<AttachmentDetails>>();
//							for (int i=0; i<tempList.size(); i += partitionSize) {
//								partitions.add(tempList.subList(i, Math.min(i + partitionSize, tempList.size())));
//							}
//							
//							if(null!=partitions && partitions.size()>0)
//							{
//								int count=0;
//								for(List<AttachmentDetails> subList : partitions)
//								{
//									count++;
//									printReportsCSV(subList,channelList.get(a), count);
//								}
//							}
						}
						attachmentsList.addAll(tempList);
					}
					tempList = null;
				}
			}
			channelList  =null;
			
			
			if(null!=attachmentsList && attachmentsList.size()>0)
			{
				printReports(attachmentsList);
//				printReportsCSV(attachmentsList);
			}
			attachmentsList = null;
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(GenerateInlineImagesReportsImpl.class.getName(), "main()", e);;
		}
	}

	public static List<AttachmentDetails> getAttachmentsList(String channelRefKey)
	{
		List<AttachmentDetails> attachmentList = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		Connection conn =null;
		try
		{
			conn = DatabaseConnector.getConnection();
//			String sql="SELECT * FROM IM_ATTACHMENT_"+channelRefKey.toUpperCase().trim()+" WHERE ATTACHMENT_TYPE  IN ('INLINE_LINKS','INLINE_LINKS_OKM')";
			String sql="SELECT * FROM IM_ATTACHMENT_"+channelRefKey.toUpperCase().trim()+" WHERE ATTACHMENT_TYPE  IN ('INLINE_IMAGES')";
//			String sql="SELECT * FROM IM_ATTACHMENT_"+channelRefKey.toUpperCase().trim()+" WHERE ATTACHMENT_TYPE  IN ('DOCUMENT')";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			AttachmentDetails details =  null;
			attachmentList = new ArrayList<AttachmentDetails>();
			while(rs.next())
			{
				details = new AttachmentDetails();
				details.setChannelRefKey(channelRefKey.toUpperCase());
				details.setDocumentId(rs.getString("DOCUMENT_ID"));
				details.setAttachmentName(rs.getString("NAME"));
				details.setLocale(rs.getString("LOCALE"));
				details.setKaResourcePath(rs.getString("KA_RESOURCE_URL"));
//				details.setAttachmentType(rs.getString("ATTACHMENT_TYPE"));
				if(null!=rs.getBytes("SOURCE_URL"))
				{
					details.setSourcePath(new String(rs.getBytes("SOURCE_URL")));
					if(null!=details.getSourcePath() && details.getSourcePath().toLowerCase().startsWith("data:image"))
					{
						details.setSourcePath("BASE64 IMAGE URL. THIS URL CAN NOT BE PROVIDED IN THIS REPORT BECAUSE OF URL LENGTH IS TOO BIG");
					}
				}
//				details.setSalesForceFieldName(rs.getString("SALESFORCE_FIELD"));
//				details.setSize(rs.getString("SIZE"));
//				details.setStatus(rs.getString("STATUS"));
//				if(null!=rs.getBytes("ERROR_MESSAGE"))
//				{
//					details.setErrorMessage(new String(rs.getBytes("ERROR_MESSAGE")));
//				}
				attachmentList.add(details);
				details = null;
			}
			sql  =null;
			pstmt.close();pstmt=null;
			rs.close();rs=null;
			
			/*
			 * DO THIS ONLY FOR ATTACHMENT CDN URLS
			 */
			if(null!=attachmentList && attachmentList.size()>0)
			{
				details = null;
				for(int a=0;a<attachmentList.size();a++)
				{
					details = (AttachmentDetails)attachmentList.get(a);
					try
					{
						sql = "SELECT CDN_URL from hach.im_all_inline_images where name=? and document_id=? and locale =? and channel = ? "
								+ " and SOURCE_URL = ?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, details.getAttachmentName());
						pstmt.setString(2, details.getDocumentId());
						pstmt.setString(3, details.getLocale());
						pstmt.setString(4, details.getChannelRefKey());
						pstmt.setString(5, details.getSourcePath());
						rs = pstmt.executeQuery();
						if(rs.next())
						{
							if(null!=rs.getString("CDN_URL") && !"".equals(rs.getString("CDN_URL")))
							{
								details.setCdnURL(rs.getString("CDN_URL"));
							}
						}
						pstmt.close();pstmt=null;
						rs.close();rs=null;
						sql = null;
					}
					catch(Exception e)
					{
						Utilities.printStackTraceToLogs(AttachmentsTransactionDAO.class.getName(), "getAttachmentsList()", e);
					}
				}
			}
					
			logger.info("getAttachmentsList ::  Total Attachments Found FOR "+channelRefKey+" >> are :: >"+ attachmentList.size());
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(AttachmentsTransactionDAO.class.getName(), "getAttachmentsList()", e);
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
				Utilities.printStackTraceToLogs(AttachmentsTransactionDAO.class.getName(), "getAttachmentsList()", e);
			}
			pstmt=null;
			rs = null;
			conn = null;
		}
		return attachmentList;
	}

	private static void printReports(List<AttachmentDetails> attachmentsList)
	{
		try
		{
			logger.info("printReports :: Failure List : >"+ attachmentsList.size());
			if(null!=attachmentsList && attachmentsList.size()>0)
			{
				String path = ApplicationProperties.getProperty("attachments.dir.path");
				String fName = "/INLINE_IMAGES_REPORT.xlsx";

				File myFile = new File(path + fName);
				fName = null;
				// Create the workbook instance for XLSX file, KEEP 100 ROWS IN MEMMORY AND RET ON DISK
				@SuppressWarnings("resource")
				SXSSFWorkbook myWorkBook = new SXSSFWorkbook(100);

				// Create a new sheet
				Sheet mySheet = myWorkBook.createSheet("Images_Details");
				/*
				 * Add Header Row
				 */
				Row headerRow = mySheet.createRow(0);
				Cell headerCell = null;

				String headers="CHANNEL,DOCUMENT_ID,LOCALE,ATTACHMENT_NAME,SOURCE_URL,CDN_URL";
//				String headers="CHANNEL,DOCUMENT_ID,LOCALE,ATTACHMENT_TYPE,SOURCE_URL";
//				String headers="CHANNEL,DOCUMENT_ID,LOCALE,ATTACHMENT_TYPE,ATTACHMENT_NAME,SALESFORCE_FIELD,SIZE,STATUS,KA_RESOURCE_URL,SOURCE_URL,ERROR_MESSAGE";
				String[] tokens=headers.split(",");
				if(null!=tokens && tokens.length>0)
				{
					for(int a=0;a<tokens.length;a++)
					{
						headerCell = headerRow.createCell(a);
						headerCell.setCellValue(tokens[a].replace("_", " "));
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

				AttachmentDetails details = null;
				for(int a=0;a<attachmentsList.size();a++)
				{
					details = (AttachmentDetails)attachmentsList.get(a);
//					dataRow=details.getChannelRefKey()+"<TOK_SEPARATOR>"+details.getDocumentId()+"<TOK_SEPARATOR>"+details.getLocale()+"<TOK_SEPARATOR>";
//					dataRow+=details.getAttachmentType()+"<TOK_SEPARATOR>"+details.getAttachmentName()+"<TOK_SEPARATOR>"+details.getSalesForceFieldName()+"<TOK_SEPARATOR>";
//					dataRow+=details.getSize()+"<TOK_SEPARATOR>"+details.getStatus()+"<TOK_SEPARATOR>"+details.getKaResourcePath()+"<TOK_SEPARATOR>";
//					dataRow+=details.getSourcePath()+"<TOK_SEPARATOR>"+details.getErrorMessage();
					
//					dataRow=details.getChannelRefKey()+"<TOK_SEPARATOR>"+details.getDocumentId()+"<TOK_SEPARATOR>"+details.getLocale()+"<TOK_SEPARATOR>";
//					dataRow+=details.getAttachmentType()+"<TOK_SEPARATOR>"+details.getSourcePath();
					
					dataRow=details.getChannelRefKey()+"<TOK_SEPARATOR>"+details.getDocumentId()+"<TOK_SEPARATOR>"+details.getLocale()+"<TOK_SEPARATOR>";
					dataRow+=details.getAttachmentName()+"<TOK_SEPARATOR>"+details.getSourcePath()+"<TOK_SEPARATOR>"+details.getCdnURL();


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
				logger.info("Writing on INLINE_IMAGES REPORT XLSX file Finished ...");
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
			Utilities.printStackTraceToLogs(GenerateInlineImagesReportsImpl.class.getName(),"printReports()" , e);
		}
	}

	private static void printReportsCSV(List<AttachmentDetails> attachmentsList,String channelRefKey, int count)
	{
		try
		{
			logger.info("printReportsCSV :: Failure List : >"+ attachmentsList.size());
			if(null!=attachmentsList && attachmentsList.size()>0)
			{
				String path = ApplicationProperties.getProperty("attachments.dir.path");
				String fName = "/"+channelRefKey.toUpperCase()+"_ATTACHMENT_REPORT_"+count+".txt";

				File myFile = new File(path + fName);
				fName = null;

				StringBuilder str = new StringBuilder();
				// header Row
				str.append("CHANNEL||DOCUMENT_ID||LOCALE||ATTACHMENT_TYPE||ATTACHMENT_NAME||SALESFORCE_FIELD||SIZE||STATUS||KA_RESOURCE_URL||SOURCE_URL");
				str.append(System.getProperty("line.separator"));
				/*
				 * GENERATE MULTIPLE ROWS SUCH THAT
				 */
				String dataRow="";

				AttachmentDetails details = null;
				for(int a=0;a<attachmentsList.size();a++)
				{
					details = (AttachmentDetails)attachmentsList.get(a);
					dataRow=details.getChannelRefKey()+"||"+details.getDocumentId()+"||"+details.getLocale()+"||";
					dataRow+=details.getAttachmentType()+"||"+details.getAttachmentName()+"||"+details.getSalesForceFieldName()+"||";
					dataRow+=details.getSize()+"||"+details.getStatus()+"||"+details.getKaResourcePath()+"||";
//					dataRow+=details.getSourcePath()+"||"+details.getErrorMessage();
					dataRow+=details.getSourcePath();
					// remove all line breaks
					dataRow = dataRow.replace("\r\n", "");
					dataRow = dataRow.replace("\n", "");
					dataRow = dataRow.replace("NULL", "");
					dataRow = dataRow.replace("null", "");
					
					str.append(dataRow);
					str.append(System.getProperty("line.separator"));
					dataRow = null;
					details = null;
				}


				FileOutputStream os = new FileOutputStream(myFile);
				os.write(str.toString().getBytes());
				logger.info("Writing on ATTACHMENT REPORT XLSX file Finished ...");
				os.flush();
				os.close();
				// set path to null
				path = null;
				// set myFile to null
				myFile = null;
				str=null;
			}
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(GenerateInlineImagesReportsImpl.class.getName(),"printReportsCSV()" , e);
		}
	}

	
}
