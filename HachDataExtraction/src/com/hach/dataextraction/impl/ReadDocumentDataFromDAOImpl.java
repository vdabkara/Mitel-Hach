package com.hach.dataextraction.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.hach.dataextraction.dao.TransactionDAO;
import com.hach.dataextraction.utils.common.ApplicationProperties;
import com.hach.dataextraction.utils.common.DatabaseConnector;
import com.hach.dataextraction.utils.common.Utilities;
import com.hach.dataextraction.vo.AttachmentDetails;
import com.hach.dataextraction.vo.ContentDetails;

public class ReadDocumentDataFromDAOImpl extends DatabaseConnector{
	
	private static Logger logger = Logger.getLogger(ReadDocumentDataFromDAOImpl.class);

	public static void main(String[] args) {
		// initialize loggers
		File f = new File(ReadDocumentDataFromDAOImpl.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		PropertyConfigurator.configure(f.getParentFile().getAbsolutePath()+"/log.properties");
		f=  null;
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try
		{
			String channelRefKey = "COMMERCIAL_QA";
			conn = getConnection();
			String sql="SELECT schema_xml,DOCUMENT_ID, DOCUMENT_LOCALE FROM im_document_"+channelRefKey+" "
					+ "WHERE DOCUMENT_ID='CO639' and document_Locale='en_US'";
//			String sql="select ERROR_MESSAGE from hach.im_attachment_commercial_qa where STATUS='Failure' and id=1";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			String xmlData = null;
			File  file = null;
			String path = ApplicationProperties.getProperty("documents.xml.dir.path");
			FileOutputStream fos = null;
			StartExtractionImpl impl = new StartExtractionImpl();
			while(rs.next())
			{
				xmlData= new String(rs.getBytes("schema_xml"));
				System.out.println(xmlData);
//				ContentDetails contentDetails = new ContentDetails();
//				contentDetails.setChannelRefKey("VIDEO");
//				contentDetails.setSchemaData(xmlData);
//				contentDetails = impl.readAttachmentsData(contentDetails);
//				contentDetails = impl.readInlineImagesAndInnerLinks(contentDetails);
				
				
//				if(null!=contentDetails.getAttachmentsList())
//				{
//					AttachmentDetails aDetails = null;
//					for(int a=0;a<contentDetails.getAttachmentsList().size();a++)
//					{
//						aDetails = (AttachmentDetails)contentDetails.getAttachmentsList().get(a);
//						logger.info(aDetails.getAttachmentType()+" >>"+ aDetails.getAttachmentName()+ " >>"+ aDetails.getSourcePath());
//						logger.info("-------------- > "+ aDetails.getSize());
//					}
//				}
				
				
				if(null!=xmlData && !"".equals(xmlData))
				{
					file = new File(path+channelRefKey);
					if(!file.exists() || !file.isDirectory())
					{
						file.mkdir();
					}
					file = null;
					
					file = new File(path+channelRefKey+"/"+rs.getString("DOCUMENT_ID")+"_"+rs.getString("DOCUMENT_LOCALE")+".xml");
					fos = new FileOutputStream(file);
					fos.write(xmlData.getBytes());
					fos.flush();
					fos.close();
					file = null;
				}
				xmlData = null;
			}
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(ReadDocumentDataFromDAOImpl.class.getName(), "main()", e);
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
				Utilities.printStackTraceToLogs(ReadDocumentDataFromDAOImpl.class.getName(), "main()", e);
			}
			pstmt=null;
			rs = null;
			conn = null;
		}
	}

}
