package com.hach.dataextraction.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hach.dataextraction.dao.AttachmentsTransactionDAO;
import com.hach.dataextraction.utils.common.DatabaseConnector;
import com.hach.dataextraction.utils.common.Utilities;
import com.hach.dataextraction.vo.AttachmentDetails;

public class InsertInlineImagesSheetWithCDNURL {

	public static void main(String[] args) {
		// initialize loggers
		File f = new File(InsertInlineImagesSheetWithCDNURL.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		PropertyConfigurator.configure(f.getParentFile().getAbsolutePath()+"/log.properties");
		f=  null;
		try
		{
			InsertInlineImagesSheetWithCDNURL impl = new InsertInlineImagesSheetWithCDNURL();
			impl.startOperation();
			impl = null;
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(InsertInlineImagesSheetWithCDNURL.class.getName(), "main()", e);
		}
	}
	
	private void startOperation()
	{
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		Connection conn =null;
		try
		{
			List<AttachmentDetails> attachmentList = readSourceData();
			System.out.println("--------- attachmentList :: >"+ attachmentList.size());
			AttachmentDetails details = null;
			String sql = null;
			if(null!=attachmentList && attachmentList.size()>0)
			{
				conn = DatabaseConnector.getConnection();
				details = null;
				for(int a=0;a<attachmentList.size();a++)
				{
					details = (AttachmentDetails)attachmentList.get(a);
					try
					{
//						sql = "UPDATE hach_prod_service_max.im_all_inline_images SET CDN_URL = ? WHERE DOCUMENT_ID=? AND LOCALE=? AND CHANNEL=?  ";
						sql = "insert into im_all_inline_images (CDN_URL,DOCUMENT_ID,LOCALE,CHANNEL,SOURCE_URL) values(?,?,?,?,?) ";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, details.getCdnURL());
						pstmt.setString(2, details.getDocumentId());
						pstmt.setString(3, details.getLocale());
						pstmt.setString(4, details.getChannelRefKey());
						pstmt.setString(5, details.getSourcePath());
						pstmt.executeUpdate();
						pstmt.close();pstmt=null;
						sql = null;
					}
					catch(Exception e)
					{
						Utilities.printStackTraceToLogs(AttachmentsTransactionDAO.class.getName(), "startOperation()", e);
					}
				}
			}
			else
			{
				System.out.println("---------- no attachments found ----------");
			}
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(InsertInlineImagesSheetWithCDNURL.class.getName(), "startOperation()", e);
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
				Utilities.printStackTraceToLogs(InsertInlineImagesSheetWithCDNURL.class.getName(), "startOperation()", e);
			}
			pstmt=null;
			rs = null;
			conn = null;
		}
	}

	private List<AttachmentDetails> readSourceData()
	{
		List<AttachmentDetails> list = null;
		try
		{
			InputStream is = new FileInputStream(new File("D:\\HACH_WD\\3-missing images 2nd import.xlsx"));
			
			XSSFWorkbook workbook  = null;
			XSSFSheet sheet = null;
			Iterator<Row> rowIterator = null;
			
			//Create Workbook instance holding reference to .xlsx file
			workbook = new XSSFWorkbook(is);
			//Get first/desired sheet from the workbook
			sheet = workbook.getSheetAt(0);
			//Iterate through each rows one by one
			rowIterator = sheet.iterator();
			long rowCount=0;
			
			AttachmentDetails details = null;
			Object dataCell = null;
			while(null!=rowIterator && rowIterator.hasNext())
			{
				Row row = rowIterator.next();
				if(rowCount>0)
				{
					details = new AttachmentDetails();
					dataCell = Utilities.readCellValue(row.getCell(0));
					if(null!=dataCell && !"".equals(dataCell))
					{
						details.setDocumentId(String.valueOf(dataCell).trim());
					}
					dataCell = null;

					dataCell = Utilities.readCellValue(row.getCell(1));
					if(null!=dataCell && !"".equals(dataCell))
					{
						details.setLocale(String.valueOf(dataCell).trim());
					}	
					dataCell = null;

					dataCell = Utilities.readCellValue(row.getCell(2));
					if(null!=dataCell && !"".equals(dataCell))
					{
						details.setChannelRefKey(String.valueOf(dataCell).trim());
					}
					dataCell = null;

					dataCell = Utilities.readCellValue(row.getCell(3));
					if(null!=dataCell && !"".equals(dataCell))
					{
						details.setAttachmentName(String.valueOf(dataCell).trim());
					}
					dataCell = null;
					
					dataCell = Utilities.readCellValue(row.getCell(4));
					if(null!=dataCell && !"".equals(dataCell))
					{
						details.setSourcePath(String.valueOf(dataCell).trim());
					}
					dataCell = null;
					
					dataCell = Utilities.readCellValue(row.getCell(5));
					if(null!=dataCell && !"".equals(dataCell))
					{
						details.setCdnURL(String.valueOf(dataCell).trim());
					}
					dataCell = null;
					/*
					 * add details to attachmentsList for fetch
					 */
					if(null==list || list.size()<=0)
					{
						list = new ArrayList<AttachmentDetails>();
					}
					list.add(details);
					details= null;
				}
				// INCREMENT ROW COUNT BY 1
				rowCount++;
				row = null;
			}
			sheet = null;
			workbook = null;
			is.close();
			is = null;
			rowIterator=  null;
		
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(InsertInlineImagesSheetWithCDNURL.class.getName(),"readSourceData()" , e);
		}
		return list;
	}
	
}
