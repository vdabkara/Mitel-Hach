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

import com.hach.dataextraction.utils.common.DatabaseConnector;
import com.hach.dataextraction.utils.common.Utilities;
import com.hach.dataextraction.vo.CategoryDetails;

public class GenerateAllCategoriesReportImpl {

	private static Logger logger = Logger.getLogger(GenerateAllCategoriesReportImpl.class);

	
	public static void main(String[] args) {
		// initialize loggers
		File f = new File(GenerateAllCategoriesReportImpl.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		PropertyConfigurator.configure(f.getParentFile().getAbsolutePath()+"/log.properties");
		f=  null;
		try
		{
			List<CategoryDetails> categoryList = getCategoryList();
			if(null!=categoryList && categoryList.size()>0)
			{
				printReports(categoryList);
			}
			categoryList = null;
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(GenerateAllCategoriesReportImpl.class.getName(), "main()", e);;
		}
	}
	
	
	public static List<CategoryDetails> getCategoryList()
	{
		List<CategoryDetails> categoryList = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		Connection conn =null;
		try
		{
			conn = DatabaseConnector.getConnection();
			String sql="select * from im_article_cat_mapping";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			CategoryDetails details =  null;
			categoryList = new ArrayList<CategoryDetails>();
			while(rs.next())
			{
				details = new CategoryDetails();
				details.setChannelRefKey(rs.getString("CHANNEL"));
				details.setDocumentId(rs.getString("DOCUMENT_ID"));
				details.setLocale(rs.getString("LOCALE"));
				details.setBaseLocale(rs.getString("BASE_LOCALE"));
				details.setAnswerId(rs.getString("ANSWER_ID"));
				details.setRefKey(rs.getString("KA_CAT_REF_KEY"));
				details.setSfCatName(rs.getString("SF_CAT_NAME"));
				details.setSfCatRefKey(rs.getString("SF_CAT_REF_KEY"));
				details.setSfMappingId(rs.getString("SF_CAT_ASSOCIATION_ID"));
				details.setSfProcessingStatus(rs.getString("SF_MAPPING_STATUS"));
				if(null!=rs.getBytes("SF_ERROR_MESSAGE"))
				{
					details.setSfErrorMessage(new String(rs.getBytes("SF_ERROR_MESSAGE")));
				}
				
				if(null==details.getSfProcessingStatus() || "".equals(details.getSfProcessingStatus()))
				{
					if(null!=details.getSfErrorMessage() && !"".equals(details.getSfErrorMessage()))
					{
						details.setSfErrorMessage(details.getSfErrorMessage()+"\n"+"Either Document is Archived or Master Identifier Does not Exist in SalesForce.");
					}
					else
					{
						details.setSfErrorMessage("Either Document is Archived or Master Identifier Does not Exist in SalesForce.");
					}
				}
				
				
				categoryList.add(details);
				details = null;
			}
			sql  =null;
			
			logger.info("getCategoryList ::  Total Categories Found are :: >"+ categoryList.size());
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(GenerateAllCategoriesReportImpl.class.getName(), "getCategoryList()", e);
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
				Utilities.printStackTraceToLogs(GenerateAllCategoriesReportImpl.class.getName(), "getCategoryList()", e);
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
			logger.info("printReports :: Category List : >"+ categoryList.size());
			if(null!=categoryList && categoryList.size()>0)
			{
				String path = "D:/HACH_WD/FULL_INSTANCE_RUN_16_JUNE_2022";
				String fName = "/CATEGORY_MAPPING_REPORT.xlsx";

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

				String headers="CHANNEL,DOCUMENT_ID,DOCUMENT_LOCALE,BASE_LOCALE,ANSWER_ID,KA_CAT_REF_KEY,SF_CAT_NAME,SF_CAT_REF_KEY,SF_CAT_ASSOCIATION_ID,SF_MAPPING_STATUS,SF_ERROR"; 
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
					dataRow+=details.getBaseLocale()+"<TOK_SEPARATOR>"+details.getAnswerId()+"<TOK_SEPARATOR>"+details.getRefKey()+"<TOK_SEPARATOR>";
					dataRow+=details.getSfCatName()+"<TOK_SEPARATOR>"+details.getSfCatRefKey()+"<TOK_SEPARATOR>"+details.getSfMappingId()+"<TOK_SEPARATOR>";
					dataRow+=details.getSfProcessingStatus()+"<TOK_SEPARATOR>"+details.getSfErrorMessage();
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
			Utilities.printStackTraceToLogs(GenerateAllCategoriesReportImpl.class.getName(),"printReports()" , e);
		}
	}

	
	
}
