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
import com.hach.dataextraction.vo.ContentDetails;

public class InsertArticleCategoryMappingImpl {

	public static void main(String[] args) {
		// initialize loggers
		File f = new File(InsertArticleCategoryMappingImpl.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		PropertyConfigurator.configure(f.getParentFile().getAbsolutePath()+"/log.properties");
		f=  null;
		try
		{
			InsertArticleCategoryMappingImpl impl = new InsertArticleCategoryMappingImpl();
			impl.startOperation();
			impl = null;
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(InsertArticleCategoryMappingImpl.class.getName(), "main()", e);
		}
	}
	
	private void startOperation()
	{
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		Connection conn =null;
		try
		{
			List<ContentDetails> catList = readSourceData();
			ContentDetails details = null;
			String sql = null;
			if(null!=catList && catList.size()>0)
			{
				conn = DatabaseConnector.getConnection();
				details = null;
				long autoId=0;
				int existingCount=0;
				for(int a=0;a<catList.size();a++)
				{
					details = (ContentDetails)catList.get(a);
					System.out.println("----- processing :: >"+ (a+1));
					try
					{
						/*
						 * BEFORE MAKING INSERT CHECK IF ALREADY EXISTS - SKIP 
						 * ELSE INSERT
						 */
//						sql = "SELECT ID FROM im_article_cat_mapping WHERE DOCUMENT_ID = ? AND LOCALE = ? AND BASE_LOCALE = ? AND KA_CAT_REF_KEY = ? and SF_CAT_REF_KEY = ? ";
//						pstmt = conn.prepareStatement(sql);
//						pstmt.setString(1, details.getDocumentId());
//						pstmt.setString(2, details.getLocale());
//						pstmt.setString(3, details.getBaseLocale());
//						pstmt.setString(4, details.getKaCatRefKey());
//						pstmt.setString(5, details.getSfCatRefKey());
//						rs=  pstmt.executeQuery();
//						if(rs.next())
//						{
//							autoId = rs.getLong("ID");
//						}
//						rs.close();rs=null;
//						pstmt.close();pstmt=null;
//						sql  =null;
						
//						if(autoId<=0)
//						{
							// row does not exist
							sql = "insert into im_article_cat_mapping (CHANNEL,DOCUMENT_ID,LOCALE,BASE_LOCALE,ANSWER_ID,KA_CAT_REF_KEY,SF_CAT_NAME,SF_CAT_REF_KEY,IS_DELETED) "
									+ "VALUES (?,?,?,?,?,?,?,?,'N')";
							pstmt = conn.prepareStatement(sql);
							pstmt.setString(1, details.getChannelRefKey());
							pstmt.setString(2, details.getDocumentId());
							pstmt.setString(3, details.getLocale());
							pstmt.setString(4, details.getBaseLocale());
							pstmt.setString(5, details.getAnswerId());
							pstmt.setString(6, details.getKaCatRefKey());
							pstmt.setString(7, details.getSfCatName());
							pstmt.setString(8, details.getSfCatRefKey());
							
							pstmt.executeUpdate();
							pstmt.close();pstmt=null;
							sql = null;
//						}
//						else
//						{
//							existingCount++;
//						}
					}
					catch(Exception e)
					{
						Utilities.printStackTraceToLogs(AttachmentsTransactionDAO.class.getName(), "startOperation()", e);
					}
				}
				
//				System.out.println("------------ existing rows count :: >"+ existingCount);
			}
			else
			{
				System.out.println("---------- no attachments found ----------");
			}
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(InsertArticleCategoryMappingImpl.class.getName(), "startOperation()", e);
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
				Utilities.printStackTraceToLogs(InsertArticleCategoryMappingImpl.class.getName(), "startOperation()", e);
			}
			pstmt=null;
			rs = null;
			conn = null;
		}
	}

	private List<ContentDetails> readSourceData()
	{
		List<ContentDetails> list = null;
		try
		{
			InputStream is = new FileInputStream(new File("C:\\Users\\Vishal\\Downloads\\Revised_Mapping Products and Categories 2nd import.xlsx"));
			
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
			
			ContentDetails details = null;
			Object dataCell = null;
			while(null!=rowIterator && rowIterator.hasNext())
			{
				Row row = rowIterator.next();
				if(rowCount>0)
				{
					details = new ContentDetails();
					dataCell = Utilities.readCellValue(row.getCell(0));
					if(null!=dataCell && !"".equals(dataCell))
					{
						details.setChannelRefKey(String.valueOf(dataCell).trim());
					}
					dataCell = null;

					dataCell = Utilities.readCellValue(row.getCell(1));
					if(null!=dataCell && !"".equals(dataCell))
					{
						details.setDocumentId(String.valueOf(dataCell).trim());
					}	
					dataCell = null;

					dataCell = Utilities.readCellValue(row.getCell(2));
					if(null!=dataCell && !"".equals(dataCell))
					{
						details.setLocale(String.valueOf(dataCell).trim());
					}
					dataCell = null;

					dataCell = Utilities.readCellValue(row.getCell(3));
					if(null!=dataCell && !"".equals(dataCell))
					{
						details.setBaseLocale(String.valueOf(dataCell).trim());
					}
					dataCell = null;
					
					dataCell = Utilities.readCellValue(row.getCell(4));
					if(null!=dataCell && !"".equals(dataCell))
					{
						details.setAnswerId(String.valueOf(dataCell).trim());
					}
					dataCell = null;
					
					dataCell = Utilities.readCellValue(row.getCell(5));
					if(null!=dataCell && !"".equals(dataCell))
					{
						details.setKaCatRefKey(String.valueOf(dataCell).trim());
					}
					dataCell = null;
					
					dataCell = Utilities.readCellValue(row.getCell(6));
					if(null!=dataCell && !"".equals(dataCell))
					{
						details.setSfCatName(String.valueOf(dataCell).trim());
					}
					dataCell = null;
					
					dataCell = Utilities.readCellValue(row.getCell(7));
					if(null!=dataCell && !"".equals(dataCell))
					{
						details.setSfCatRefKey(String.valueOf(dataCell).trim());
					}
					dataCell = null;
					/*
					 * add details to attachmentsList for fetch
					 */
					if(null==list || list.size()<=0)
					{
						list = new ArrayList<ContentDetails>();
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
			Utilities.printStackTraceToLogs(InsertArticleCategoryMappingImpl.class.getName(),"readSourceData()" , e);
		}
		return list;
	}
	
}
