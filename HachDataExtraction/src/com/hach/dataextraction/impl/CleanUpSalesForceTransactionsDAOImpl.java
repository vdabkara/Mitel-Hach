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

import com.hach.dataextraction.dao.TransactionDAO;
import com.hach.dataextraction.utils.common.ApplicationProperties;
import com.hach.dataextraction.utils.common.DatabaseConnector;
import com.hach.dataextraction.utils.common.Utilities;
import com.hach.dataextraction.vo.AttachmentDetails;
import com.hach.dataextraction.vo.ContentDetails;

public class CleanUpSalesForceTransactionsDAOImpl extends DatabaseConnector{
	
	private static Logger logger = Logger.getLogger(CleanUpSalesForceTransactionsDAOImpl.class);

	public static void main(String[] args) {
		// initialize loggers
		File f = new File(CleanUpSalesForceTransactionsDAOImpl.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		PropertyConfigurator.configure(f.getParentFile().getAbsolutePath()+"/log.properties");
		f=  null;
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try
		{
			TransactionDAO dao  =new TransactionDAO();
			List<String> channelList = dao.getChannelDetails();
			String tableName=null;
			String sql="";
			if(null!=channelList && channelList.size()>0)
			{
				conn = getConnection();
				for(int a=0;a<channelList.size();a++)
				{
					// clean up all documents table
					tableName ="im_document_"+channelList.get(a).toString();
					sql = "update "+tableName+" set sf_article_number=null,sf_document_id=null,sf_locale=null,sf_article_id=null,sf_channel_id=null,sf_url_name=null,sf_master_identifier=null,sf_document_url=null,sf_processing_status=null,sf_remarks=null,sf_error_message=null,sf_all_categories_mapped=null,sf_all_inrlks_articles_mapped=null";
					pstmt = conn.prepareStatement(sql);
					pstmt.executeUpdate();
					pstmt.close();pstmt=null;
					sql = null;
					tableName = null;
				}
				
				// delete data from 
				sql ="DELETE FROM im_article_cat_mapping";
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
				pstmt.close();pstmt=null;
				sql = null;
				
				// delete data from sf_innerlinks_details
				sql ="DELETE FROM sf_innerlinks_details";
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
				pstmt.close();pstmt=null;
				sql = null;
				
				
				// delete data from sf_publish_transactions
				sql ="DELETE FROM sf_publish_transactions";
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
				pstmt.close();pstmt=null;
				sql = null;
				
				// delete data from sf_unpublish_transactions
				sql ="DELETE FROM sf_unpublish_transactions";
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
				pstmt.close();pstmt=null;
				sql = null;
			}
			channelList  =null;
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(CleanUpSalesForceTransactionsDAOImpl.class.getName(), "main()", e);
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
				Utilities.printStackTraceToLogs(CleanUpSalesForceTransactionsDAOImpl.class.getName(), "main()", e);
			}
			pstmt=null;
			rs = null;
			conn = null;
		}
	}

}
