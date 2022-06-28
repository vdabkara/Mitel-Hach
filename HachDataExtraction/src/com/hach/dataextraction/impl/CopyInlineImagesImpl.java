package com.hach.dataextraction.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hach.dataextraction.utils.common.Utilities;
import com.hach.dataextraction.vo.AttachmentDetails;

public class CopyInlineImagesImpl {

	private static Logger logger = Logger.getLogger(CopyInlineImagesImpl.class);

	public static void main(String[] args) {
		// initialize loggers
		File f = new File(CopyInlineImagesImpl.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		PropertyConfigurator.configure(f.getParentFile().getAbsolutePath()+"/log.properties");
		f=  null;
		try
		{
			List<AttachmentDetails> attachmentsList = new ArrayList<AttachmentDetails>();
			attachmentsList = readSourceData();
			if(null!=attachmentsList && attachmentsList.size()>0)
			{
				logger.info("total attahcments found are :: >"+ attachmentsList.size());
				AttachmentDetails details = null;
				String folderPath="D:/HACH_WD/PROD_21_MAR_2022/INLINE_IMAGES/";
				String destFolderPath="D:/HACH_WD/SERVICE_MAX_RUN/INLINE_IMAGES/";
				String sourcePath=null;
				File sourceFile = null;
				File destinationFile=null;
				for(int a=0;a<attachmentsList.size();a++)
				{
					details = (AttachmentDetails)attachmentsList.get(a);
					sourcePath=folderPath+details.getChannelRefKey()+"/"+details.getLocale()+"/"+details.getDocumentId()+"/";
					sourcePath+=details.getAttachmentName();
					sourceFile = new File(sourcePath);
					if(sourceFile.exists() && sourceFile.isFile())
					{
						/// move to another location
						// add channelfolder
						destinationFile = new File(destFolderPath+details.getChannelRefKey());
						if(!destinationFile.isDirectory())
						{
							destinationFile.mkdir();
						}
						destinationFile = null;
						// add localefolder
						destinationFile = new File(destFolderPath+details.getChannelRefKey()+"/"+details.getLocale());
						if(!destinationFile.isDirectory())
						{
							destinationFile.mkdir();
						}
						destinationFile = null;
						// add documentfolder
						destinationFile = new File(destFolderPath+details.getChannelRefKey()+"/"+details.getLocale()+"/"+details.getDocumentId());
						if(!destinationFile.isDirectory())
						{
							destinationFile.mkdir();
						}
						destinationFile = null;
						// add filename
						destinationFile = new File(destFolderPath+details.getChannelRefKey()+"/"+details.getLocale()+"/"+details.getDocumentId()+"/"+details.getAttachmentName());
						destinationFile.createNewFile();
						// copy file
						copyFileUsingStream(sourceFile, destinationFile);
						
						destinationFile = null;
					}
					sourceFile = null;
					sourcePath=null;
				}
				
				
			}
			else
			{
				logger.info("No attachments read from excel file.");
			}
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(CopyInlineImagesImpl.class.getName(), "main()", e);;
		}
	}
	
	private static List<AttachmentDetails> readSourceData()
	{
		List<AttachmentDetails> list = null;
		try
		{
			InputStream is = new FileInputStream(new File("D:\\HACH_WD\\SERVICE_MAX_RUN\\FINAL_REPORTS\\ALL_IMAGES_REPORT.xlsx"));
			
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
						details.setCdnURL(String.valueOf(dataCell).trim());
					}
					dataCell = null;
					
					
					/*
					 * add only when CDN URL IS NULL OR EMPTY
					 * add details to attachmentsList for fetch
					 */
					if(null==details.getCdnURL() || "".equals(details.getCdnURL()))
					{
						if(null==list || list.size()<=0)
						{
							list = new ArrayList<AttachmentDetails>();
						}
						list.add(details);
					}
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
			Utilities.printStackTraceToLogs(CopyInlineImagesImpl.class.getName(),"readSourceData()" , e);
		}
		return list;
	}
	
	private static void copyFileUsingStream(File source, File dest) throws IOException {
	    InputStream is = null;
	    OutputStream os = null;
	    try {
	        is = new FileInputStream(source);
	        os = new FileOutputStream(dest);
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	    } finally {
	        is.close();
	        os.close();
	    }
	}
}
