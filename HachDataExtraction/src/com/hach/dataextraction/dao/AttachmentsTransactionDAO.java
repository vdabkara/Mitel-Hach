package com.hach.dataextraction.dao;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hach.dataextraction.utils.common.DatabaseConnector;
import com.hach.dataextraction.utils.common.Utilities;
import com.hach.dataextraction.vo.AttachmentDetails;

public class AttachmentsTransactionDAO extends DatabaseConnector{

	private Logger logger = Logger.getLogger(TransactionDAO.class);

	public List<AttachmentDetails> getAttachmentsList(String channelRefKey, String locale,String attachmentType)
	{
		List<AttachmentDetails> attachmentList = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		Connection conn =null;
		try
		{
			conn = getConnection();
			String sql="SELECT * FROM IM_ATTACHMENT_"+channelRefKey.toUpperCase().trim()+" WHERE LOCALE= ? AND "
					+ " ATTACHMENT_TYPE = ? AND REC_CREATION_TMSTP>'2022-05-27' AND DOCUMENT_ID IN ('RE30','RE29','RE28','RE25','TE2979','TE2979','TE3097','TE4487','TE3174','TE4588','TE3834','TE6189','TE6189','TE6189','TE6189','TE6189','TE6553','TE973','TE973','TE11022','TE2528','TE2533','TE2561','TE2561')";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, locale);
			pstmt.setString(2, attachmentType);
			rs = pstmt.executeQuery();
			AttachmentDetails details =  null;
			attachmentList = new ArrayList<AttachmentDetails>();
			while(rs.next())
			{
				details = new AttachmentDetails();
				details.setAutoId(rs.getLong("ID"));
				details.setChannelRefKey(channelRefKey);
				details.setDocumentId(rs.getString("DOCUMENT_ID"));
				details.setAttachmentName(rs.getString("NAME"));
				details.setLocale(rs.getString("LOCALE"));
				details.setKaResourcePath(rs.getString("KA_RESOURCE_URL"));
				details.setAttachmentType(rs.getString("ATTACHMENT_TYPE"));
				if(null!=rs.getBytes("SOURCE_URL"))
				{
					details.setSourcePath(new String(rs.getBytes("SOURCE_URL")));
				}
				attachmentList.add(details);
				details = null;
			}
			sql  =null;
			
			logger.info("getAttachmentsList ::  Total Attachments Found are :: >"+ attachmentList.size());
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

	
	public void updateAttachmentsStatusDetails(AttachmentDetails details,String channelRefKey)
	{
		PreparedStatement pstmt=null;
		Connection conn =null;
		try
		{
			conn = getConnection();
			String sql="UPDATE IM_ATTACHMENT_"+channelRefKey.toUpperCase()+" SET STATUS=?,ERROR_MESSAGE=? WHERE ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, details.getStatus());
			// errorMessage
			InputStream is = null;
			byte[] data = null;
			String varContent="";
			if(null!=details.getErrorMessage())
			{
				varContent = details.getErrorMessage();
			}
			data=  varContent.getBytes();
			is = new ByteArrayInputStream(data);
			pstmt.setBinaryStream(2, is, data.length);
			
			is.close();is = null;
			varContent=  null;
			data = null;
			
			
			pstmt.setLong(3, details.getAutoId());
			pstmt.executeUpdate();
			sql  =null;
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(AttachmentsTransactionDAO.class.getName(), "updateAttachmentsStatusDetails()", e);
		}
		finally
		{
			try
			{
				if(null!=pstmt)
				{
					pstmt.close();
				}
				if(null!=conn)
				{
					conn.close();
				}
			}
			catch(SQLException e)
			{
				Utilities.printStackTraceToLogs(AttachmentsTransactionDAO.class.getName(), "updateAttachmentsStatusDetails()", e);
			}
			pstmt=null;
			conn = null;
		}
	}
	
}
