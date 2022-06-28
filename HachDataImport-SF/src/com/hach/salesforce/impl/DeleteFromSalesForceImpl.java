package com.hach.salesforce.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONObject;

import com.hach.salesforce.utils.common.ApplicationProperties;
import com.hach.salesforce.utils.common.DatabaseConnector;
import com.hach.salesforce.utils.common.Utilities;
import com.hach.salesforce.utils.sfop.SalesForceAPICaller;

public class DeleteFromSalesForceImpl {

	private static Logger logger = Logger.getLogger(DeleteFromSalesForceImpl.class);
	
	public static void main(String[] args) {
		// initialize loggers
		File f = new File(StartMigrationImpl.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		PropertyConfigurator.configure(f.getParentFile().getAbsolutePath()+"/log.properties");
		f=  null;
		try
		{
//			String[] tok = "REACTIVOS_OTHER,TERMODINAMICA_OTHER,HEXIS_OTHER,DANAHER_WQ_CONTENT,HACH_CONTENT,PRODUCT_INFORMATION_BULLETIN,SELF_SERVICE,SALES_DOCUMENTS,COMMERCIAL_QA,PODCAST,PRODUCT_COMMERCIALIZATION_,VIDEO,TROUBLESHOOTING,TECHNICAL_QA,SERVICE_DOCUMENTS,METHODS,ALERTS".split(",");
//			for(int a=0;a<tok.length;a++)
//			{
//				deleteOperation(tok[a]);
//			}
			
			deleteOperation("REACTIVOS_OTHER");
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "main()", e);
		}
	}
	
	private static void deleteOperation(String channelKey) throws Exception
	{
		SalesForceAPICaller caller = new SalesForceAPICaller();
		StringBuilder str = new StringBuilder();
		// fetch from database - FIRST MASTERS / TRANSLATIONS
		List<String> documentList = getDocumentsList(channelKey,"TRANSLATIONS");
		// fetch from file - SF_DELETE_LIST.txt
//		List<String> documentList = getDocumentsDataFromText();
		JSONObject responseObject = null;
		if(null!=documentList && documentList.size()>0)
		{
			logger.info("main :: Total Master Identifiers found are :: >"+ documentList.size());
			str.append("SF_RECORD_ID|STATUS");
			str.append(System.getProperty("line.separator"));
			for(int a=0;a<documentList.size();a++)
			{
				str.append(documentList.get(a).toString()+"|");
				try
				{
					// FOR MASTER - USE - delete.api.master.url
					// FOR TRANSLATION - USE - delete.api.translation.url
					responseObject = caller.callSFRestAPIForOtherOperations(ApplicationProperties.getProperty("delete.api.translation.url")+documentList.get(a).toString(), "DELETE", null);
					if(responseObject.getString("CALL_STATUS").equals("SUCCESS"))
					{
						str.append("SUCCESS");
					}
					else
					{
						str.append("FAILURE");
					}
					responseObject = null;
				}
				catch (Exception e) {
					Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "main()", e);
					str.append("FAILURE");
				}
				str.append(System.getProperty("line.separator"));
			}
		}
		
		
		if(null!=str)
		{
			File report = new File("D:\\HACH_WD\\DELETE_SF_DATA_REPORT_"+channelKey.toUpperCase()+".txt");
			FileOutputStream fos = new FileOutputStream(report);
			fos.write(str.toString().getBytes());
			fos.flush();
			fos.close();
			fos = null;
			report = null;
		}
		str = null;
		documentList  =null;
	}
	

	private static List<String> getDocumentsList(String channelRefKey,String dataType)
	{
		List<String> documentsList = null;
		Connection conn = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{
			conn = DatabaseConnector.getConnection();
			String sql = "SELECT SF_DOCUMENT_ID FROM IM_DOCUMENT_"+channelRefKey+" WHERE SF_DOCUMENT_ID IS NOT NULL AND ";
			if(dataType.equals("MASTERS"))
			{
				sql+="DOCUMENT_LOCALE = BASE_LOCALE";
			}
			else if(dataType.equals("TRANSLATIONS"))
			{
				sql+="DOCUMENT_LOCALE != BASE_LOCALE";
			}
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				if(null!=rs.getString("SF_DOCUMENT_ID") && !"".equals(rs.getString("SF_DOCUMENT_ID")))
				{
					if(null==documentsList || documentsList.size()<=0)
					{
						documentsList = new ArrayList<String>();
					}
					documentsList.add(rs.getString("SF_DOCUMENT_ID"));
				}
			}
			pstmt.close();pstmt=null;
			rs.close();rs=null;
			conn.close();conn = null;
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(StartMigrationImpl.class.getName(), "getDocumentsList()", e);
		}
		return documentsList;
	}
	
	private static List<String> getDocumentsDataFromText()
	{
		List<String> documentsList=null;
		FileInputStream inputStream = null;
		Scanner sc = null;
		try 
		{
			File textFile = new File("D:\\HACH_WD\\SF_DELETE_LIST.txt");
			// sample Date in Text as Begin & Cancel Date = 08/24/2018, FORMAT = MM/DD/YYYY
			inputStream = new FileInputStream(textFile);
			sc = new Scanner(inputStream, "UTF-8");
			String line;
			String[] tokens=null;
			int rowCount=0;
			while (sc.hasNextLine()) {
				line = sc.nextLine();
				if(null!=line && !"".equals(line))
				{
					// skip row 1 - as it is header
					if(rowCount>0)
					{
						tokens = line.split("\\|");
						if(null!=tokens && tokens.length>0)
						{
							try
							{
								if(tokens.length>=0)
								{
									if(null!=tokens[0] && !"".equals(tokens[0].trim()))
									{
										if(null==documentsList || documentsList.size()<=0)
										{
											documentsList = new ArrayList<String>();
										}
										documentsList.add(tokens[0].toString());
									}
								}
							}
							catch (Exception e) {
							}
						}
					}
					rowCount++;
				}
			}

			if(null!=documentsList && documentsList.size()>0)
			{
				logger.info("getDealersData() :: Total SF Documents Read From {"+textFile.getName()+"} are :: > "+ documentsList.size());
			}
			else
			{
				logger.info("getDealersData() :: No SF Documents Read From {"+textFile.getName()+"}.");
			}
			textFile = null;
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(DeleteFromSalesForceImpl.class.getName(), "getDeagetDocumentsDataFromTextlersData()", e);
		}
		finally 
		{
			if (null!=inputStream) {
				try {
					inputStream.close();
				} catch (IOException e) {
					Utilities.printStackTraceToLogs(DeleteFromSalesForceImpl.class.getName(), "getDocumentsDataFromText()", e);
				}
			}
			if (sc != null) {
				sc.close();
			}
		}
		return documentsList;
	}
	
}
