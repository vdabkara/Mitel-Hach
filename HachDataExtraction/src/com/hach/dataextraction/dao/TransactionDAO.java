package com.hach.dataextraction.dao;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.hach.dataextraction.utils.common.ApplicationProperties;
import com.hach.dataextraction.utils.common.DatabaseConnector;
import com.hach.dataextraction.utils.common.Utilities;
import com.hach.dataextraction.vo.AttachmentDetails;
import com.hach.dataextraction.vo.CategoryDetails;
import com.hach.dataextraction.vo.ChannelDetails;
import com.hach.dataextraction.vo.ContentDetails;
import com.hach.dataextraction.vo.ManualLinkDetails;
import com.hach.dataextraction.vo.UserGroupDetails;
import com.hach.dataextraction.vo.ViewDetails;

public class TransactionDAO extends DatabaseConnector{

	private Logger logger = Logger.getLogger(TransactionDAO.class);
	
	private String schemaName=ApplicationProperties.getProperty("jdbc.schemaName");

	private String tableNamePlaceholder="TABLE_NAME_PLACEHOLDER";
	
	public Connection conn = null;

	/**
	 * Function will check whether DB Table Exists or not, if not will create table at run time
	 * @param tableName
	 * @param tableType
	 * @return
	 */
	public boolean checkTableExists(String tableName, String tableType)
	{
		boolean bool = true;
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			if(null!=tableName && !"".equals(tableName))
			{
				try
				{
					if(null==conn || conn.isClosed()==true)
					{
						conn = getConnection();
					}
				}
				catch(Exception eq)
				{
					Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "checkTableExists()", eq);
					bool = false;
				}

				boolean exists = false;
				String sql="SELECT table_name FROM information_schema.tables WHERE table_schema = '"+schemaName+"' AND "
						+ "table_name = '"+tableName+"'";
				stmt = conn.createStatement();
				rs=stmt.executeQuery(sql);
				if(rs.next())
				{
					if(null!=rs.getString("table_name") && !"".equals(rs.getString("table_name")))
					{
						// table exists
						exists= true;
					}
				}
				sql = null;
				stmt.close();stmt=null;
				rs.close();rs=null;

				if(exists==false)
				{
					logger.info("checkTableExists :: "+tableName+" does not exists under Schema :: > "+ schemaName+". Proceed for creating Table.");
					// read vehicle Table create query
					InputStream is = null;
					String query="";
					try
					{
						is = TransactionDAO.class.getResourceAsStream("/com/hach/dataextraction/queries/"+tableType+"_TABLE.sql");
						if(null!=is)
						{
							// convert to string
							query = Utilities.readInputStramToString(is);
						}
					}
					catch(Exception e)
					{
						Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "checkTableExists()", e);
					}
					finally
					{
						if(null!=is)
						{
							is.close();
						}
					}
					is = null;

					if(null!=query && !"".equals(query))
					{
						query=query.replace(tableNamePlaceholder, tableName);
						stmt = conn.createStatement();
						stmt.executeUpdate(query);
						logger.info("checkTableExists :: "+tableName+" Created Successfully under Schema :: > "+ schemaName+". Proceed for dumping data in Table.");
					}
					else
					{
						logger.info("checkTableExists :: Failed to Read Create Table query for "+tableType+"_TABLE.sql. Cannot create table.");
						bool = false;
					}
					query = null;
				}
				else
				{
					logger.info("checkTableExists :: "+tableName+" already exists under Schema :: > "+ schemaName+". Proceed for dumping data in Table.");
				}
			}
			else
			{
				logger.info("checkTableExists :: Table Name as parameter is null.");
				bool = false;
			}
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "checkTableExists()", e);
			bool = false;
		}
		finally
		{
			try
			{
				if(null!=stmt)
					stmt.close();
				if(null!=rs)
					rs.close();
				stmt=null;
				rs=null;
			}
			catch(SQLException e)
			{
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "checkTableExists()", e);
			}
		}
		return bool;
	}
	
	public void saveDocumentDetails(List<ContentDetails> documentsList, String channelRefKey)
	{
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{
			try
			{
				if(null==conn || conn.isClosed()==true)
				{
					conn = getConnection();
				}
			}
			catch(Exception eq)
			{
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "saveDocumentDetails()", eq);
			}
			
			String tableName="IM_DOCUMENT_"+channelRefKey.trim().toUpperCase();
			ContentDetails contentDetails = null;
			List<ContentDetails> createList=new ArrayList<ContentDetails>();
			List<ContentDetails> updateList=new ArrayList<ContentDetails>();
			String sql="";
			for(int a=0;a<documentsList.size();a++)
			{
				contentDetails  = (ContentDetails)documentsList.get(a);
				sql = "SELECT ID FROM "+ tableName+ " WHERE DOCUMENT_ID = ? AND DOCUMENT_LOCALE=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, contentDetails.getDocumentId());
				pstmt.setString(2, contentDetails.getLocale());
				rs = pstmt.executeQuery();
				if(rs.next())
				{
					contentDetails.setAutoId(rs.getLong("ID"));
				}
				pstmt.close();pstmt=null;
				rs.close();rs=null;
				sql = null;
				
				if(contentDetails.getAutoId()>0)
				{
					// add to updateList
					updateList.add(contentDetails);
				}
				else
				{
					createList.add(contentDetails);
				}
				contentDetails  =null;
			}
			
			logger.info("saveDocumentDetails :: Total Documents Found for Create are :: >"+ createList.size());
			logger.info("saveDocumentDetails :: Total Documents Found for Update are :: >"+ updateList.size());
			
			
			if(null!=createList && createList.size()>0)
			{
				createOperation(createList, channelRefKey);
			}
			if(null!=updateList && updateList.size()>0)
			{
				updateOperation(updateList, channelRefKey);
			}
			createList  =null;
			updateList = null;
			contentDetails  =null;
			sql  =null;
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "saveDocumentDetails()", e);
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
			}
			catch(SQLException e)
			{
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "saveDocumentDetails()", e);
			}
			pstmt=null;
			rs = null;
			documentsList= null;
		}
	}
	
	public void createOperation(List<ContentDetails> documentsList, String channelRefKey)
	{
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{
			try
			{
				if(null==conn || conn.isClosed()==true)
				{
					conn = getConnection();
				}
			}
			catch(Exception eq)
			{
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "createOperation()", eq);
			}
			
			if(null!=conn)
			{
				conn.setAutoCommit(false);
			}
			List<CategoryDetails> categoryList = new ArrayList<CategoryDetails>();
			List<AttachmentDetails> attachmentsList = new ArrayList<AttachmentDetails>();
			List<ViewDetails> viewsList = new ArrayList<ViewDetails>();
			List<UserGroupDetails> userGroupsList = new ArrayList<UserGroupDetails>();
			String tableName="IM_DOCUMENT_"+channelRefKey.trim().toUpperCase();
			ContentDetails contentDetails = null;
			String sql="";
			byte[] data = null;
			String varContent=null;
			InputStream is = null;
			sql = "INSERT INTO "+ tableName+ " (RECORD_ID,VERSION_ID,VERSION_NUMBER,DOCUMENT_ID,ANSWER_ID,CHANNEL_REF_KEY,DOCUMENT_LOCALE,TITLE,RESOURCE_PATH,"
					+ "DOC_JSON,SCHEMA_XML,REC_CREATION_TMSTP,ERROR_CODES,ERROR_MESSAGE,BASE_LOCALE,OWNER_NAME,OWNER_EMAIL,LAST_MODIFIER_NAME,"
					+ "LAST_MODIFIER_EMAIL,PUBLISH_DATE,CREATE_DATE,LAST_MODIFIED_DATE,DISPLAY_END_DATE,METADATA_XML,DOC_ARCHIVED) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			for(int a=0;a<documentsList.size();a++)
			{
				contentDetails  = (ContentDetails)documentsList.get(a);
				pstmt.setString(1, contentDetails.getRecordId());
				pstmt.setString(2, contentDetails.getVersionId());
				pstmt.setString(3, contentDetails.getVersionNumber());
				pstmt.setString(4, contentDetails.getDocumentId());
				pstmt.setString(5, contentDetails.getAnswerId());
				pstmt.setString(6, contentDetails.getChannelRefKey());
				pstmt.setString(7, contentDetails.getLocale());
				pstmt.setString(8, contentDetails.getTitle());
				pstmt.setString(9, contentDetails.getResourcePath());
				
				// JSON OBJECT
				is = null;
				data = null;
				varContent="";
				if(null!=contentDetails.getContentObject())
				{
					varContent = contentDetails.getContentObject().toString();
				}
				data=  varContent.getBytes();
				is = new ByteArrayInputStream(data);
				pstmt.setBinaryStream(10, is, data.length);
				
				is.close();is = null;
				varContent=  null;
				data = null;
				
				// XML SCHEMA OBJECT
				is = null;
				data = null;
				varContent="";
				if(null!=contentDetails.getSchemaData())
				{
					varContent = contentDetails.getSchemaData().toString();
				}
				data=  varContent.getBytes();
				is = new ByteArrayInputStream(data);
				pstmt.setBinaryStream(11, is, data.length);
				
				is.close();is = null;
				varContent=  null;
				data = null;
				
				pstmt.setTimestamp(12, new Timestamp(new Date().getTime()));
				pstmt.setString(13, contentDetails.getErrorCodes());
				
				// ERROR MESSAGE
				is = null;
				data = null;
				varContent="";
				if(null!=contentDetails.getErrorMessage())
				{
					varContent = contentDetails.getErrorMessage().toString();
				}
				data=  varContent.getBytes();
				is = new ByteArrayInputStream(data);
				pstmt.setBinaryStream(14, is, data.length);
				
				is.close();is = null;
				varContent=  null;
				data = null;
				
				pstmt.setString(15, contentDetails.getBaseLocale());
				pstmt.setString(16, contentDetails.getOwnerName());
				pstmt.setString(17, contentDetails.getOwnerEmail());
				pstmt.setString(18, contentDetails.getLastModifierName());
				pstmt.setString(19, contentDetails.getLastModifierEmail());
				pstmt.setString(20, contentDetails.getPublishDate());
				pstmt.setString(21, contentDetails.getCreateDate());
				pstmt.setString(22, contentDetails.getLastModifiedDate());
				pstmt.setString(23, contentDetails.getDisplayEndDate());
				
				// META DATA XML OBJECT
				is = null;
				data = null;
				varContent="";
				if(null!=contentDetails.getMetaDataXML())
				{
					varContent = contentDetails.getMetaDataXML().toString();
				}
				data=  varContent.getBytes();
				is = new ByteArrayInputStream(data);
				pstmt.setBinaryStream(24, is, data.length);
				pstmt.setString(25, contentDetails.getDocArchived());
				is.close();is = null;
				varContent=  null;
				data = null;
				
				pstmt.addBatch();
				
				
				// add each documentCategories to categoryList
				if(null!=contentDetails.getCategoryList())
				{
//					categoryList.addAll(contentDetails.getCategoryList());
				}
				
				// add each attachment
				if(null!=contentDetails.getAttachmentsList())
				{
					attachmentsList.addAll(contentDetails.getAttachmentsList());
				}
				
				// add each view
				if(null!=contentDetails.getViewsList())
				{
					viewsList.addAll(contentDetails.getViewsList());
				}
				
				// add each userGroup
				if(null!=contentDetails.getUserGroupsList())
				{
					userGroupsList.addAll(contentDetails.getUserGroupsList());
				}
				contentDetails  =null;
			}
			
			pstmt.executeBatch();
			pstmt.close();pstmt=null;
			sql  =null;
			tableName = null;
			
			/*
			 * PROCEED FOR CATEGORIES & ATTACHMENTS DATA CREATION
			 * FIRST DELETE ALL CATEGORIES AND RE-INSERT THEM FOR THE PROCESSING DOCUMENTS
			 * 
			 * SAME STEPS FOR ATTACHMENTS, VIEWS & USERGROUP
			 */
//			tableName="IM_CATEGORY_"+channelRefKey.trim().toUpperCase();
//			sql = "DELETE FROM "+ tableName +" WHERE DOCUMENT_ID =? AND LOCALE=?";
//			pstmt = conn.prepareStatement(sql);
//			contentDetails = null;
//			for(int a=0;a<documentsList.size();a++)
//			{
//				contentDetails = (ContentDetails)documentsList.get(a);
//				pstmt.setString(1, contentDetails.getDocumentId());
//				pstmt.setString(2, contentDetails.getLocale());
//				pstmt.addBatch();
//				contentDetails  =null;
//			}
//			pstmt.executeBatch();
//			pstmt.close();pstmt = null;
//			sql = null;
//			tableName  =null;
			
//			if(null!=categoryList && categoryList.size()>0)
//			{
//				tableName="IM_CATEGORY_"+channelRefKey.trim().toUpperCase();
//				
//				CategoryDetails catDetails = null;
//				int partitionSize=100;
//				ArrayList<List<CategoryDetails>> partitions = new ArrayList<List<CategoryDetails>>();
//				for (int i=0; i<categoryList.size(); i += partitionSize) {
//					partitions.add(categoryList.subList(i, Math.min(i + partitionSize, categoryList.size())));
//				}
//				
//				if(null!=partitions && partitions.size()>0)
//				{
//					for(List<CategoryDetails> subList : partitions)
//					{
//						if(null!=subList && subList.size()>0)
//						{
//							catDetails = null;
//							sql = "INSERT INTO "+tableName+" (DOCUMENT_ID,LOCALE,RECORD_ID,REF_KEY,NAME,EXTERNAL_TYPE,REC_CREATION_TMSTP,LEVEL_1_NAME,LEVEL_1_REF_KEY,LEVEL_2_NAME,"
//									+ "LEVEL_2_REF_KEY,LEVEL_3_NAME,LEVEL_3_REF_KEY,LEVEL_4_NAME,LEVEL_4_REF_KEY,LEVEL_5_NAME,LEVEL_5_REF_KEY) "
//									+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//							pstmt = conn.prepareStatement(sql);
//							for(int b=0;b<subList.size();b++)
//							{
//								catDetails  =(CategoryDetails)subList.get(b);
//								pstmt.setString(1, catDetails.getDocumentId());
//								pstmt.setString(2, catDetails.getLocale());
//								pstmt.setString(3, catDetails.getRecordId());
//								pstmt.setString(4, catDetails.getRefKey());
//								pstmt.setString(5, catDetails.getName());
//								pstmt.setString(6, catDetails.getExternalType());
//								pstmt.setTimestamp(7, new Timestamp(new Date().getTime()));
//								pstmt.setString(8, catDetails.getLevel1Name());
//								pstmt.setString(9, catDetails.getLevel1RefKey());
//								pstmt.setString(10, catDetails.getLevel2Name());
//								pstmt.setString(11, catDetails.getLevel2RefKey());
//								pstmt.setString(12, catDetails.getLevel3Name());
//								pstmt.setString(13, catDetails.getLevel3RefKey());
//								pstmt.setString(14, catDetails.getLevel4Name());
//								pstmt.setString(15, catDetails.getLevel4RefKey());
//								pstmt.setString(16, catDetails.getLevel5Name());
//								pstmt.setString(17, catDetails.getLevel5RefKey());
//								pstmt.addBatch();
//								catDetails=  null;
//							}
//							pstmt.executeBatch();
//							pstmt.close();pstmt = null;
//							sql = null;
//						}
//						subList=  null;
//					}
//				}
//				partitions = null;
//				tableName = null;
//				catDetails=  null;
//			}
			
			// ATTACHMENTS OPERATION
			tableName="IM_ATTACHMENT_"+channelRefKey.trim().toUpperCase();
			sql = "DELETE FROM "+ tableName +" WHERE DOCUMENT_ID =? AND LOCALE=?";
			pstmt = conn.prepareStatement(sql);
			contentDetails = null;
			for(int a=0;a<documentsList.size();a++)
			{
				contentDetails = (ContentDetails)documentsList.get(a);
				pstmt.setString(1, contentDetails.getDocumentId());
				pstmt.setString(2, contentDetails.getLocale());
				pstmt.addBatch();
				contentDetails  =null;
			}
			pstmt.executeBatch();
			pstmt.close();pstmt = null;
			sql = null;
			tableName  =null;
			
			if(null!=attachmentsList && attachmentsList.size()>0)
			{
				tableName="IM_ATTACHMENT_"+channelRefKey.trim().toUpperCase();
				
				AttachmentDetails aDetails = null;
				int partitionSize=100;
				ArrayList<List<AttachmentDetails>> partitions = new ArrayList<List<AttachmentDetails>>();
				for (int i=0; i<attachmentsList.size(); i += partitionSize) {
					partitions.add(attachmentsList.subList(i, Math.min(i + partitionSize, attachmentsList.size())));
				}
				
				if(null!=partitions && partitions.size()>0)
				{
					for(List<AttachmentDetails> subList : partitions)
					{
						if(null!=subList && subList.size()>0)
						{
							aDetails = null;
							sql = "INSERT INTO "+tableName+" (DOCUMENT_ID,LOCALE,NAME,KA_RESOURCE_URL,SOURCE_URL,ATTACHMENT_TYPE,REC_CREATION_TMSTP"
									+ ",CHANNEL,SALESFORCE_FIELD,SIZE) VALUES(?,?,?,?,?,?,?,?,?,?)";
							pstmt = conn.prepareStatement(sql);
							for(int b=0;b<subList.size();b++)
							{
								aDetails  =(AttachmentDetails)subList.get(b);
								pstmt.setString(1, aDetails.getDocumentId());
								pstmt.setString(2, aDetails.getLocale());
								pstmt.setString(3, aDetails.getAttachmentName());
								pstmt.setString(4, aDetails.getKaResourcePath());
								// SOURCE URL
								is = null;
								data = null;
								varContent="";
								if(null!=aDetails.getSourcePath())
								{
									varContent = aDetails.getSourcePath();
								}
								data=  varContent.getBytes();
								is = new ByteArrayInputStream(data);
								pstmt.setBinaryStream(5, is, data.length);
								
								is.close();is = null;
								varContent=  null;
								data = null;
								
								pstmt.setString(6, aDetails.getAttachmentType());
								pstmt.setTimestamp(7, new Timestamp(new Date().getTime()));
								pstmt.setString(8, aDetails.getChannelRefKey());
								pstmt.setString(9, aDetails.getSalesForceFieldName());
								pstmt.setString(10, aDetails.getSize());
								pstmt.addBatch();
								aDetails=  null;
							}
							pstmt.executeBatch();
							pstmt.close();pstmt = null;
							sql = null;
						}
						subList=  null;
					}
				}
				partitions = null;
				tableName = null;
				aDetails=  null;
			}
			
			// VIEWS OPERATION
			tableName="IM_VIEW_"+channelRefKey.trim().toUpperCase();
			sql = "DELETE FROM "+ tableName +" WHERE DOCUMENT_ID =? AND LOCALE=?";
			pstmt = conn.prepareStatement(sql);
			contentDetails = null;
			for(int a=0;a<documentsList.size();a++)
			{
				contentDetails = (ContentDetails)documentsList.get(a);
				pstmt.setString(1, contentDetails.getDocumentId());
				pstmt.setString(2, contentDetails.getLocale());
				pstmt.addBatch();
				contentDetails  =null;
			}
			pstmt.executeBatch();
			pstmt.close();pstmt = null;
			sql = null;
			tableName  =null;
			
			if(null!=viewsList && viewsList.size()>0)
			{
				tableName="IM_VIEW_"+channelRefKey.trim().toUpperCase();
				
				ViewDetails viewDetails = null;
				int partitionSize=100;
				ArrayList<List<ViewDetails>> partitions = new ArrayList<List<ViewDetails>>();
				for (int i=0; i<viewsList.size(); i += partitionSize) {
					partitions.add(viewsList.subList(i, Math.min(i + partitionSize, viewsList.size())));
				}
				
				if(null!=partitions && partitions.size()>0)
				{
					for(List<ViewDetails> subList : partitions)
					{
						if(null!=subList && subList.size()>0)
						{
							viewDetails = null;
							sql = "INSERT INTO "+tableName+" (DOCUMENT_ID,LOCALE,RECORD_ID,REF_KEY,NAME,REC_CREATION_TMSTP) "
									+ "VALUES(?,?,?,?,?,?)";
							pstmt = conn.prepareStatement(sql);
							for(int b=0;b<subList.size();b++)
							{
								viewDetails  =(ViewDetails)subList.get(b);
								pstmt.setString(1, viewDetails.getDocumentId());
								pstmt.setString(2, viewDetails.getLocale());
								pstmt.setString(3, viewDetails.getRecordId());
								pstmt.setString(4, viewDetails.getRefKey());
								pstmt.setString(5, viewDetails.getName());
								pstmt.setTimestamp(6, new Timestamp(new Date().getTime()));
								pstmt.addBatch();
								viewDetails=  null;
							}
							pstmt.executeBatch();
							pstmt.close();pstmt = null;
							sql = null;
						}
						subList=  null;
					}
				}
				partitions = null;
				tableName = null;
				viewDetails=  null;
			}
			
			// USER GROUPS OPERATION
			tableName="IM_USERGROUP_"+channelRefKey.trim().toUpperCase();
			sql = "DELETE FROM "+ tableName +" WHERE DOCUMENT_ID =? AND LOCALE=?";
			pstmt = conn.prepareStatement(sql);
			contentDetails = null;
			for(int a=0;a<documentsList.size();a++)
			{
				contentDetails = (ContentDetails)documentsList.get(a);
				pstmt.setString(1, contentDetails.getDocumentId());
				pstmt.setString(2, contentDetails.getLocale());
				pstmt.addBatch();
				contentDetails  =null;
			}
			pstmt.executeBatch();
			pstmt.close();pstmt = null;
			sql = null;
			tableName  =null;
			
			if(null!=userGroupsList && userGroupsList.size()>0)
			{
				tableName="IM_USERGROUP_"+channelRefKey.trim().toUpperCase();
				
				UserGroupDetails ugDetails = null;
				int partitionSize=100;
				ArrayList<List<UserGroupDetails>> partitions = new ArrayList<List<UserGroupDetails>>();
				for (int i=0; i<userGroupsList.size(); i += partitionSize) {
					partitions.add(userGroupsList.subList(i, Math.min(i + partitionSize, userGroupsList.size())));
				}
				
				if(null!=partitions && partitions.size()>0)
				{
					for(List<UserGroupDetails> subList : partitions)
					{
						if(null!=subList && subList.size()>0)
						{
							ugDetails = null;
							sql = "INSERT INTO "+tableName+" (DOCUMENT_ID,LOCALE,RECORD_ID,REF_KEY,NAME,EXTERNAL_TYPE,REC_CREATION_TMSTP) "
									+ "VALUES(?,?,?,?,?,?,?)";
							pstmt = conn.prepareStatement(sql);
							for(int b=0;b<subList.size();b++)
							{
								ugDetails  =(UserGroupDetails)subList.get(b);
								pstmt.setString(1, ugDetails.getDocumentId());
								pstmt.setString(2, ugDetails.getLocale());
								pstmt.setString(3, ugDetails.getRecordId());
								pstmt.setString(4, ugDetails.getRefKey());
								pstmt.setString(5, ugDetails.getName());
								pstmt.setString(6, ugDetails.getExternalType());
								pstmt.setTimestamp(7, new Timestamp(new Date().getTime()));
								pstmt.addBatch();
								ugDetails=  null;
							}
							pstmt.executeBatch();
							pstmt.close();pstmt = null;
							sql = null;
						}
						subList=  null;
					}
				}
				partitions = null;
				tableName = null;
				ugDetails=  null;
			}
			
			if(null!=conn)
			{
				conn.commit();
				conn.setAutoCommit(true);
			}
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "createOperation()", e);
			if(null!=conn)
			{
				try
				{
					conn.rollback();
				}
				catch(SQLException eq)
				{
					Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "createOperation()", eq);
				}
			}
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
			}
			catch(SQLException e)
			{
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "createOperation()", e);
			}
			pstmt=null;
			rs = null;
			documentsList= null;
		}
	}

	public void updateOperation(List<ContentDetails> documentsList, String channelRefKey)
	{
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{
			try
			{
				if(null==conn || conn.isClosed()==true)
				{
					conn = getConnection();
				}
			}
			catch(Exception eq)
			{
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "updateOperation()", eq);
			}
			
			if(null!=conn)
			{
				conn.setAutoCommit(false);
			}
			List<CategoryDetails> categoryList = new ArrayList<CategoryDetails>();
			List<AttachmentDetails> attachmentsList = new ArrayList<AttachmentDetails>();
			List<ViewDetails> viewsList = new ArrayList<ViewDetails>();
			List<UserGroupDetails> userGroupsList = new ArrayList<UserGroupDetails>();
			String tableName="IM_DOCUMENT_"+channelRefKey.trim().toUpperCase();
			ContentDetails contentDetails = null;
			String sql="";
			byte[] data = null;
			String varContent=null;
			InputStream is = null;
			sql = "UPDATE "+ tableName+ " SET RECORD_ID=?,VERSION_ID=?,VERSION_NUMBER=?,DOCUMENT_ID=?,ANSWER_ID=?,CHANNEL_REF_KEY=?,DOCUMENT_LOCALE=?,TITLE=?,RESOURCE_PATH=?,"
					+ "DOC_JSON=?,SCHEMA_XML=?,REC_MODIFIED_TMSTP=?,ERROR_CODES=?,ERROR_MESSAGE=?,BASE_LOCALE=?,OWNER_NAME=?,OWNER_EMAIL=?,LAST_MODIFIER_NAME=?,"
					+ "LAST_MODIFIER_EMAIL=?,PUBLISH_DATE=?,CREATE_DATE=?,LAST_MODIFIED_DATE=?,DISPLAY_END_DATE=?,METADATA_XML=?,DOC_ARCHIVED=? WHERE ID=?";
			pstmt = conn.prepareStatement(sql);
			for(int a=0;a<documentsList.size();a++)
			{
				contentDetails  = (ContentDetails)documentsList.get(a);
				pstmt.setString(1, contentDetails.getRecordId());
				pstmt.setString(2, contentDetails.getVersionId());
				pstmt.setString(3, contentDetails.getVersionNumber());
				pstmt.setString(4, contentDetails.getDocumentId());
				pstmt.setString(5, contentDetails.getAnswerId());
				pstmt.setString(6, contentDetails.getChannelRefKey());
				pstmt.setString(7, contentDetails.getLocale());
				pstmt.setString(8, contentDetails.getTitle());
				pstmt.setString(9, contentDetails.getResourcePath());
				
				// JSON OBJECT
				is = null;
				data = null;
				varContent="";
				if(null!=contentDetails.getContentObject())
				{
					varContent = contentDetails.getContentObject().toString();
				}
				data=  varContent.getBytes();
				is = new ByteArrayInputStream(data);
				pstmt.setBinaryStream(10, is, data.length);
				
				is.close();is = null;
				varContent=  null;
				data = null;
				
				// XML SCHEMA OBJECT
				is = null;
				data = null;
				varContent="";
				if(null!=contentDetails.getSchemaData())
				{
					varContent = contentDetails.getSchemaData().toString();
				}
				data=  varContent.getBytes();
				is = new ByteArrayInputStream(data);
				pstmt.setBinaryStream(11, is, data.length);
				
				is.close();is = null;
				varContent=  null;
				data = null;
				
				pstmt.setTimestamp(12, new Timestamp(new Date().getTime()));
				pstmt.setString(13, contentDetails.getErrorCodes());
				
				// ERROR MESSAGE
				is = null;
				data = null;
				varContent="";
				if(null!=contentDetails.getErrorMessage())
				{
					varContent = contentDetails.getErrorMessage().toString();
				}
				data=  varContent.getBytes();
				is = new ByteArrayInputStream(data);
				pstmt.setBinaryStream(14, is, data.length);
				is.close();is = null;
				varContent=  null;
				data = null;
				
				pstmt.setString(15, contentDetails.getBaseLocale());
				pstmt.setString(16, contentDetails.getOwnerName());
				pstmt.setString(17, contentDetails.getOwnerEmail());
				pstmt.setString(18, contentDetails.getLastModifierName());
				pstmt.setString(19, contentDetails.getLastModifierEmail());
				pstmt.setString(20, contentDetails.getPublishDate());
				pstmt.setString(21, contentDetails.getCreateDate());
				pstmt.setString(22, contentDetails.getLastModifiedDate());
				pstmt.setString(23, contentDetails.getDisplayEndDate());
				
				// META DATA XML OBJECT
				is = null;
				data = null;
				varContent="";
				if(null!=contentDetails.getMetaDataXML())
				{
					varContent = contentDetails.getMetaDataXML().toString();
				}
				data=  varContent.getBytes();
				is = new ByteArrayInputStream(data);
				pstmt.setBinaryStream(24, is, data.length);
				
				is.close();is = null;
				varContent=  null;
				data = null;
				
				pstmt.setString(25, contentDetails.getDocArchived());
				pstmt.setLong(26, contentDetails.getAutoId());
				pstmt.addBatch();
				
				
				// add each documentCategories to categoryList
				if(null!=contentDetails.getCategoryList())
				{
					// for this transaction comment category processing
//					categoryList.addAll(contentDetails.getCategoryList());
				}
				
				// add each attachment
				if(null!=contentDetails.getAttachmentsList())
				{
					attachmentsList.addAll(contentDetails.getAttachmentsList());
				}
				
				// add each view
				if(null!=contentDetails.getViewsList())
				{
					viewsList.addAll(contentDetails.getViewsList());
				}
				
				// add each userGroup
				if(null!=contentDetails.getUserGroupsList())
				{
					userGroupsList.addAll(contentDetails.getUserGroupsList());
				}
					
				contentDetails  =null;
			}
			
			pstmt.executeBatch();
			pstmt.close();pstmt=null;
			sql  =null;
			tableName = null;
			
			/*
			 * PROCEED FOR CATEGORIES & ATTACHMENTS DATA CREATION
			 * FIRST DELETE ALL CATEGORIES AND RE-INSERT THEM FOR THE PROCESSING DOCUMENTS
			 * 
			 * SAME STEPS FOR ATTACHMENTS , VIEWS, USER GROUPS
			 */
//			tableName="IM_CATEGORY_"+channelRefKey.trim().toUpperCase();
//			sql = "DELETE FROM "+ tableName +" WHERE DOCUMENT_ID =? AND LOCALE=?";
//			pstmt = conn.prepareStatement(sql);
//			contentDetails = null;
//			for(int a=0;a<documentsList.size();a++)
//			{
//				contentDetails = (ContentDetails)documentsList.get(a);
//				pstmt.setString(1, contentDetails.getDocumentId());
//				pstmt.setString(2, contentDetails.getLocale());
//				pstmt.addBatch();
//				contentDetails  =null;
//			}
//			pstmt.executeBatch();
//			pstmt.close();pstmt = null;
//			sql = null;
//			tableName  =null;
			
//			if(null!=categoryList && categoryList.size()>0)
//			{
//				tableName="IM_CATEGORY_"+channelRefKey.trim().toUpperCase();
//				
//				CategoryDetails catDetails = null;
//				int partitionSize=100;
//				ArrayList<List<CategoryDetails>> partitions = new ArrayList<List<CategoryDetails>>();
//				for (int i=0; i<categoryList.size(); i += partitionSize) {
//					partitions.add(categoryList.subList(i, Math.min(i + partitionSize, categoryList.size())));
//				}
//				
//				if(null!=partitions && partitions.size()>0)
//				{
//					for(List<CategoryDetails> subList : partitions)
//					{
//						if(null!=subList && subList.size()>0)
//						{
//							catDetails = null;
//							sql = "INSERT INTO "+tableName+" (DOCUMENT_ID,LOCALE,RECORD_ID,REF_KEY,NAME,EXTERNAL_TYPE,REC_CREATION_TMSTP,LEVEL_1_NAME,LEVEL_1_REF_KEY,LEVEL_2_NAME,"
//									+ "LEVEL_2_REF_KEY,LEVEL_3_NAME,LEVEL_3_REF_KEY,LEVEL_4_NAME,LEVEL_4_REF_KEY,LEVEL_5_NAME,LEVEL_5_REF_KEY) "
//									+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//							pstmt = conn.prepareStatement(sql);
//							for(int b=0;b<subList.size();b++)
//							{
//								catDetails  =(CategoryDetails)subList.get(b);
//								pstmt.setString(1, catDetails.getDocumentId());
//								pstmt.setString(2, catDetails.getLocale());
//								pstmt.setString(3, catDetails.getRecordId());
//								pstmt.setString(4, catDetails.getRefKey());
//								pstmt.setString(5, catDetails.getName());
//								pstmt.setString(6, catDetails.getExternalType());
//								pstmt.setTimestamp(7, new Timestamp(new Date().getTime()));
//								pstmt.setString(8, catDetails.getLevel1Name());
//								pstmt.setString(9, catDetails.getLevel1RefKey());
//								pstmt.setString(10, catDetails.getLevel2Name());
//								pstmt.setString(11, catDetails.getLevel2RefKey());
//								pstmt.setString(12, catDetails.getLevel3Name());
//								pstmt.setString(13, catDetails.getLevel3RefKey());
//								pstmt.setString(14, catDetails.getLevel4Name());
//								pstmt.setString(15, catDetails.getLevel4RefKey());
//								pstmt.setString(16, catDetails.getLevel5Name());
//								pstmt.setString(17, catDetails.getLevel5RefKey());
//								pstmt.addBatch();
//								catDetails=  null;
//							}
//							pstmt.executeBatch();
//							pstmt.close();pstmt = null;
//							sql = null;
//						}
//						subList=  null;
//					}
//				}
//				partitions = null;
//				tableName = null;
//				catDetails=  null;
//			}
			
			// ATTACHMENTS OPERATION
			
			tableName="IM_ATTACHMENT_"+channelRefKey.trim().toUpperCase();
			sql = "DELETE FROM "+ tableName +" WHERE DOCUMENT_ID =? AND LOCALE=?";
			pstmt = conn.prepareStatement(sql);
			contentDetails = null;
			for(int a=0;a<documentsList.size();a++)
			{
				contentDetails = (ContentDetails)documentsList.get(a);
				pstmt.setString(1, contentDetails.getDocumentId());
				pstmt.setString(2, contentDetails.getLocale());
				pstmt.addBatch();
				contentDetails  =null;
			}
			pstmt.executeBatch();
			pstmt.close();pstmt = null;
			sql = null;
			tableName  =null;
			
			if(null!=attachmentsList && attachmentsList.size()>0)
			{
				tableName="IM_ATTACHMENT_"+channelRefKey.trim().toUpperCase();
				
				AttachmentDetails aDetails = null;
				int partitionSize=100;
				ArrayList<List<AttachmentDetails>> partitions = new ArrayList<List<AttachmentDetails>>();
				for (int i=0; i<attachmentsList.size(); i += partitionSize) {
					partitions.add(attachmentsList.subList(i, Math.min(i + partitionSize, attachmentsList.size())));
				}
				
				if(null!=partitions && partitions.size()>0)
				{
					for(List<AttachmentDetails> subList : partitions)
					{
						if(null!=subList && subList.size()>0)
						{
							aDetails = null;
							sql = "INSERT INTO "+tableName+" (DOCUMENT_ID,LOCALE,NAME,KA_RESOURCE_URL,SOURCE_URL,ATTACHMENT_TYPE,REC_CREATION_TMSTP"
									+ ",CHANNEL,SALESFORCE_FIELD,SIZE) VALUES(?,?,?,?,?,?,?,?,?,?)";
							pstmt = conn.prepareStatement(sql);
							for(int b=0;b<subList.size();b++)
							{
								aDetails  =(AttachmentDetails)subList.get(b);
								pstmt.setString(1, aDetails.getDocumentId());
								pstmt.setString(2, aDetails.getLocale());
								pstmt.setString(3, aDetails.getAttachmentName());
								pstmt.setString(4, aDetails.getKaResourcePath());
								// SOURCE URL
								is = null;
								data = null;
								varContent="";
								if(null!=aDetails.getSourcePath())
								{
									varContent = aDetails.getSourcePath();
								}
								data=  varContent.getBytes();
								is = new ByteArrayInputStream(data);
								pstmt.setBinaryStream(5, is, data.length);
								
								is.close();is = null;
								varContent=  null;
								data = null;
								
								pstmt.setString(6, aDetails.getAttachmentType());
								pstmt.setTimestamp(7, new Timestamp(new Date().getTime()));
								pstmt.setString(8, aDetails.getChannelRefKey());
								pstmt.setString(9, aDetails.getSalesForceFieldName());
								pstmt.setString(10, aDetails.getSize());
								pstmt.addBatch();
								aDetails=  null;
							}
							pstmt.executeBatch();
							pstmt.close();pstmt = null;
							sql = null;
						}
						subList=  null;
					}
				}
				partitions = null;
				tableName = null;
				aDetails=  null;
			}
			
			// VIEWS OPERATION
						tableName="IM_VIEW_"+channelRefKey.trim().toUpperCase();
						sql = "DELETE FROM "+ tableName +" WHERE DOCUMENT_ID =? AND LOCALE=?";
						pstmt = conn.prepareStatement(sql);
						contentDetails = null;
						for(int a=0;a<documentsList.size();a++)
						{
							contentDetails = (ContentDetails)documentsList.get(a);
							pstmt.setString(1, contentDetails.getDocumentId());
							pstmt.setString(2, contentDetails.getLocale());
							pstmt.addBatch();
							contentDetails  =null;
						}
						pstmt.executeBatch();
						pstmt.close();pstmt = null;
						sql = null;
						tableName  =null;
						
						if(null!=viewsList && viewsList.size()>0)
						{
							tableName="IM_VIEW_"+channelRefKey.trim().toUpperCase();
							
							ViewDetails viewDetails = null;
							int partitionSize=100;
							ArrayList<List<ViewDetails>> partitions = new ArrayList<List<ViewDetails>>();
							for (int i=0; i<viewsList.size(); i += partitionSize) {
								partitions.add(viewsList.subList(i, Math.min(i + partitionSize, viewsList.size())));
							}
							
							if(null!=partitions && partitions.size()>0)
							{
								for(List<ViewDetails> subList : partitions)
								{
									if(null!=subList && subList.size()>0)
									{
										viewDetails = null;
										sql = "INSERT INTO "+tableName+" (DOCUMENT_ID,LOCALE,RECORD_ID,REF_KEY,NAME,REC_CREATION_TMSTP) "
												+ "VALUES(?,?,?,?,?,?)";
										pstmt = conn.prepareStatement(sql);
										for(int b=0;b<subList.size();b++)
										{
											viewDetails  =(ViewDetails)subList.get(b);
											pstmt.setString(1, viewDetails.getDocumentId());
											pstmt.setString(2, viewDetails.getLocale());
											pstmt.setString(3, viewDetails.getRecordId());
											pstmt.setString(4, viewDetails.getRefKey());
											pstmt.setString(5, viewDetails.getName());
											pstmt.setTimestamp(6, new Timestamp(new Date().getTime()));
											pstmt.addBatch();
											viewDetails=  null;
										}
										pstmt.executeBatch();
										pstmt.close();pstmt = null;
										sql = null;
									}
									subList=  null;
								}
							}
							partitions = null;
							tableName = null;
							viewDetails=  null;
						}
						
						// USER GROUPS OPERATION
						tableName="IM_USERGROUP_"+channelRefKey.trim().toUpperCase();
						sql = "DELETE FROM "+ tableName +" WHERE DOCUMENT_ID =? AND LOCALE=?";
						pstmt = conn.prepareStatement(sql);
						contentDetails = null;
						for(int a=0;a<documentsList.size();a++)
						{
							contentDetails = (ContentDetails)documentsList.get(a);
							pstmt.setString(1, contentDetails.getDocumentId());
							pstmt.setString(2, contentDetails.getLocale());
							pstmt.addBatch();
							contentDetails  =null;
						}
						pstmt.executeBatch();
						pstmt.close();pstmt = null;
						sql = null;
						tableName  =null;
						
						if(null!=userGroupsList && userGroupsList.size()>0)
						{
							tableName="IM_USERGROUP_"+channelRefKey.trim().toUpperCase();
							
							UserGroupDetails ugDetails = null;
							int partitionSize=100;
							ArrayList<List<UserGroupDetails>> partitions = new ArrayList<List<UserGroupDetails>>();
							for (int i=0; i<userGroupsList.size(); i += partitionSize) {
								partitions.add(userGroupsList.subList(i, Math.min(i + partitionSize, userGroupsList.size())));
							}
							
							if(null!=partitions && partitions.size()>0)
							{
								for(List<UserGroupDetails> subList : partitions)
								{
									if(null!=subList && subList.size()>0)
									{
										ugDetails = null;
										sql = "INSERT INTO "+tableName+" (DOCUMENT_ID,LOCALE,RECORD_ID,REF_KEY,NAME,EXTERNAL_TYPE,REC_CREATION_TMSTP) "
												+ "VALUES(?,?,?,?,?,?,?)";
										pstmt = conn.prepareStatement(sql);
										for(int b=0;b<subList.size();b++)
										{
											ugDetails  =(UserGroupDetails)subList.get(b);
											pstmt.setString(1, ugDetails.getDocumentId());
											pstmt.setString(2, ugDetails.getLocale());
											pstmt.setString(3, ugDetails.getRecordId());
											pstmt.setString(4, ugDetails.getRefKey());
											pstmt.setString(5, ugDetails.getName());
											pstmt.setString(6, ugDetails.getExternalType());
											pstmt.setTimestamp(7, new Timestamp(new Date().getTime()));
											pstmt.addBatch();
											ugDetails=  null;
										}
										pstmt.executeBatch();
										pstmt.close();pstmt = null;
										sql = null;
									}
									subList=  null;
								}
							}
							partitions = null;
							tableName = null;
							ugDetails=  null;
						}
			
			if(null!=conn)
			{
				conn.commit();
				conn.setAutoCommit(true);
			}
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "updateOperation()", e);
			if(null!=conn)
			{
				try
				{
					conn.rollback();
				}
				catch(SQLException eq)
				{
					Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "updateOperation()", eq);
				}
			}
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
			}
			catch(SQLException e)
			{
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "updateOperation()", e);
			}
			pstmt=null;
			rs = null;
			documentsList= null;
		}
	}

	public void saveChannelDetails(List<ChannelDetails> channelsList)
	{
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{
			try
			{
				if(null==conn || conn.isClosed()==true)
				{
					conn = getConnection();
				}
			}
			catch(Exception eq)
			{
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "saveChannelDetails()", eq);
			}
			
			if(null!=conn)
			{
				conn.setAutoCommit(false);
			}
			
			/*
			 * FIRST DELETE ALL MASTER DATA FROM CATEGORY MASTER
			 */
			String tableName="IM_CHANNEL_MASTER";
			String sql="DELETE FROM "+ tableName;
			pstmt =conn.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.close();pstmt=null;
			sql  =null;
			
			ChannelDetails channelDetails = null;
			sql = "INSERT INTO "+tableName+" (RECORD_ID,REF_KEY,NAME,REC_CREATION_TMSTP) VALUES(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			for(int a=0;a<channelsList.size();a++)
			{
				channelDetails = (ChannelDetails)channelsList.get(a);
				pstmt.setString(1, channelDetails.getRecordId());
				pstmt.setString(2, channelDetails.getRefKey());
				pstmt.setString(3, channelDetails.getName());
				pstmt.setTimestamp(4, new Timestamp(new Date().getTime()));
				pstmt.addBatch();
				channelDetails =null;
			}
			pstmt.executeBatch();
			pstmt.close();pstmt =null;
			sql  =null;
			channelDetails =null;
			tableName  =null;
			conn.commit();
			conn.setAutoCommit(true);
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "saveChannelDetails()", e);
			if(null!=conn)
			{
				try
				{
					conn.rollback();
				}
				catch(SQLException eq)
				{
					Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "saveChannelDetails()", eq);
				}
			}
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
			}
			catch(SQLException e)
			{
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "saveChannelDetails()", e);
			}
			pstmt=null;
			rs = null;
			channelsList = null;
		}
	}
	
	
	public List<String> getChannelDetails()
	{
		List<String> channelsList = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{
			try
			{
				if(null==conn || conn.isClosed()==true)
				{
					conn = getConnection();
				}
			}
			catch(Exception eq)
			{
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "getChannelDetails()", eq);
			}
			
			/*
			 * FIRST DELETE ALL MASTER DATA FROM CATEGORY MASTER
			 */
			String tableName="IM_CHANNEL_MASTER";
			String sql="SELECT REF_KEY FROM "+ tableName+" where REF_KEY NOT IN ('MARKET_INFORMATION','GUIDED_ANSWER')";
//			String sql="SELECT REF_KEY FROM "+ tableName+" where REF_KEY ='COMMERCIAL_QA'";
			pstmt =conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				if(null!=rs.getString("REF_KEY") && !"".equals(rs.getString("REF_KEY")))
				{
					if(null==channelsList || channelsList.size()<=0)
					{
						channelsList = new ArrayList<String>();
					}
					channelsList.add(rs.getString("REF_KEY"));
				}
			}
			sql  =null;
			
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "getChannelDetails()", e);
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
			}
			catch(SQLException e)
			{
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "getChannelDetails()", e);
			}
			pstmt=null;
			rs = null;
		}
		return channelsList;
	}
	
	public CategoryDetails getCategoryHierarchy(CategoryDetails catDetails)
	{
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{
			try
			{
				if(null==conn || conn.isClosed()==true)
				{
					conn = getConnection();
				}
			}
			catch(Exception eq)
			{
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "getCategoryHierarchy()", eq);
			}
			
			String tableName="IM_PRODUCT_CATEGORY_MASTER_"+catDetails.getLocale().trim().toUpperCase();
			String sql ="select * from "+tableName+" where (LEVEL_1_REF_KEY=? OR LEVEL_2_REF_KEY=? OR LEVEL_3_REF_KEY=? OR LEVEL_4_REF_KEY=? OR LEVEL_5_REF_KEY=?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, catDetails.getRefKey());
			pstmt.setString(2, catDetails.getRefKey());
			pstmt.setString(3, catDetails.getRefKey());
			pstmt.setString(4, catDetails.getRefKey());
			pstmt.setString(5, catDetails.getRefKey());
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				/*
				 * FOR EACH LEVEL CHECK IF SOURCE REF KEY MATCHES
				 * ANY LEVEL, THEN STOP THERE
				 * DO NOT ADD ITS COMPLETE HIERARCHY FOR THE MAPPED CATEGORY
				 */
				if(catDetails.getRefKey().toLowerCase().equals(rs.getString("LEVEL_1_REF_KEY")))
				{
					// 1st level mapped
					catDetails.setLevel1Name(rs.getString("LEVEL_1_NAME"));
					catDetails.setLevel1RefKey(rs.getString("LEVEL_1_REF_KEY"));
				}
				else if(catDetails.getRefKey().toLowerCase().equals(rs.getString("LEVEL_2_REF_KEY")))
				{
					// 2nd level mapped
					catDetails.setLevel1Name(rs.getString("LEVEL_1_NAME"));
					catDetails.setLevel1RefKey(rs.getString("LEVEL_1_REF_KEY"));
					catDetails.setLevel2Name(rs.getString("LEVEL_2_NAME"));
					catDetails.setLevel2RefKey(rs.getString("LEVEL_2_REF_KEY"));
				}
				else if(catDetails.getRefKey().toLowerCase().equals(rs.getString("LEVEL_3_REF_KEY")))
				{
					// 3rd level mapped
					catDetails.setLevel1Name(rs.getString("LEVEL_1_NAME"));
					catDetails.setLevel1RefKey(rs.getString("LEVEL_1_REF_KEY"));
					catDetails.setLevel2Name(rs.getString("LEVEL_2_NAME"));
					catDetails.setLevel2RefKey(rs.getString("LEVEL_2_REF_KEY"));
					catDetails.setLevel3Name(rs.getString("LEVEL_3_NAME"));
					catDetails.setLevel3RefKey(rs.getString("LEVEL_3_REF_KEY"));
				}
				else if(catDetails.getRefKey().toLowerCase().equals(rs.getString("LEVEL_4_REF_KEY")))
				{
					// 4th level mapped
					catDetails.setLevel1Name(rs.getString("LEVEL_1_NAME"));
					catDetails.setLevel1RefKey(rs.getString("LEVEL_1_REF_KEY"));
					catDetails.setLevel2Name(rs.getString("LEVEL_2_NAME"));
					catDetails.setLevel2RefKey(rs.getString("LEVEL_2_REF_KEY"));
					catDetails.setLevel3Name(rs.getString("LEVEL_3_NAME"));
					catDetails.setLevel3RefKey(rs.getString("LEVEL_3_REF_KEY"));
					catDetails.setLevel4Name(rs.getString("LEVEL_4_NAME"));
					catDetails.setLevel4RefKey(rs.getString("LEVEL_4_REF_KEY"));
				}
				else if(catDetails.getRefKey().toLowerCase().equals(rs.getString("LEVEL_5_REF_KEY")))
				{
					// 5th level mapped
					catDetails.setLevel1Name(rs.getString("LEVEL_1_NAME"));
					catDetails.setLevel1RefKey(rs.getString("LEVEL_1_REF_KEY"));
					catDetails.setLevel2Name(rs.getString("LEVEL_2_NAME"));
					catDetails.setLevel2RefKey(rs.getString("LEVEL_2_REF_KEY"));
					catDetails.setLevel3Name(rs.getString("LEVEL_3_NAME"));
					catDetails.setLevel3RefKey(rs.getString("LEVEL_3_REF_KEY"));
					catDetails.setLevel4Name(rs.getString("LEVEL_4_NAME"));
					catDetails.setLevel4RefKey(rs.getString("LEVEL_4_REF_KEY"));
					catDetails.setLevel5Name(rs.getString("LEVEL_5_NAME"));
					catDetails.setLevel5RefKey(rs.getString("LEVEL_5_REF_KEY"));
				}
			}
			sql = null;
			tableName=  null;
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "getCategoryHierarchy()", e);
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
			}
			catch(SQLException e)
			{
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "getCategoryHierarchy()", e);
			}
			pstmt=null;
			rs = null;
		}
		return catDetails;
	}

	public List<CategoryDetails> getCategoryHierarchyListForXML(String locale,String level1RefKey)
	{
		List<CategoryDetails> categoryList=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{
			try
			{
				if(null==conn || conn.isClosed()==true)
				{
					conn = getConnection();
				}
			}
			catch(Exception eq)
			{
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "getCategoryHierarchyListForXML()", eq);
			}
			
			String tableName="IM_PRODUCT_CATEGORY_MASTER_"+locale.trim().toUpperCase();
			String sql ="select DISTINCT LEVEL_1_REF_KEY,LEVEL_1_NAME from "+tableName+" WHERE LEVEL_1_REF_KEY = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, level1RefKey);
			rs = pstmt.executeQuery();
			CategoryDetails catDetails= null;
			while(rs.next())
			{
				if(null!=rs.getString("LEVEL_1_NAME") && null!=rs.getString("LEVEL_1_REF_KEY"))
				{
					catDetails = new CategoryDetails();
					catDetails.setName(rs.getString("LEVEL_1_NAME"));
					catDetails.setRefKey(rs.getString("LEVEL_1_REF_KEY"));
					if(null==categoryList || categoryList.size()<=0)
					{
						categoryList = new ArrayList<CategoryDetails>();
					}
					categoryList.add(catDetails);
					catDetails=  null;
				}
			}
			sql = null;
			pstmt.close();pstmt=null;
			rs.close();rs=null;
			
			if(null!=categoryList && categoryList.size()>0)
			{
				logger.info("Total Unique Level 1 Found are :: >"+ categoryList.size());
				catDetails = null;
				CategoryDetails level2Details = null;
				CategoryDetails level3Details = null;
				CategoryDetails level4Details = null;
				CategoryDetails level5Details = null;
				for(int a=0;a<categoryList.size();a++)
				{
					catDetails = (CategoryDetails)categoryList.get(a);
					sql ="select DISTINCT LEVEL_2_REF_KEY,LEVEL_2_NAME from "+tableName +" WHERE LEVEL_1_REF_KEY = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, catDetails.getRefKey());
					rs= pstmt.executeQuery();
					level2Details  =null;
					while(rs.next())
					{
						if(null!=rs.getString("LEVEL_2_NAME") && null!=rs.getString("LEVEL_2_REF_KEY"))
						{
							level2Details = new CategoryDetails();
							level2Details.setName(rs.getString("LEVEL_2_NAME"));
							level2Details.setRefKey(rs.getString("LEVEL_2_REF_KEY"));
							if(null==catDetails.getChildList() || catDetails.getChildList().size()<=0)
							{
								catDetails.setChildList(new ArrayList<CategoryDetails>());
							}
							catDetails.getChildList().add(catDetails);
							level2Details = null;
						}
					}
					level2Details = null;
					pstmt.close();pstmt=null;
					rs.close();rs=null;
					sql = null;
					
					/*
					 * ITERATE LEVEL 2 LIST AND FETCH ALL ITS' LEVEL 3
					 */
					
					if(null!=catDetails.getChildList() && catDetails.getChildList().size()>0)
					{
						level2Details  =null;
						for(int b=0;b<catDetails.getChildList().size();b++)
						{
							level2Details = (CategoryDetails)catDetails.getChildList().get(b);
							
							sql ="select DISTINCT LEVEL_3_REF_KEY,LEVEL_3_NAME from "+tableName +" WHERE LEVEL_1_REF_KEY = ? and LEVEL_2_REF_KEY=?";
							pstmt = conn.prepareStatement(sql);
							pstmt.setString(1, catDetails.getRefKey());
							pstmt.setNString(2, level2Details.getRefKey());
							rs= pstmt.executeQuery();
							level3Details  =null;
							while(rs.next())
							{
								if(null!=rs.getString("LEVEL_3_NAME") && null!=rs.getString("LEVEL_3_REF_KEY"))
								{
									level3Details = new CategoryDetails();
									level3Details.setName(rs.getString("LEVEL_3_NAME"));
									level3Details.setRefKey(rs.getString("LEVEL_3_REF_KEY"));
									if(null==level2Details.getChildList() || level2Details.getChildList().size()<=0)
									{
										level2Details.setChildList(new ArrayList<CategoryDetails>());
									}
									level2Details.getChildList().add(level3Details);
									level3Details = null;
								}
							}
							level3Details = null;
							pstmt.close();pstmt=null;
							rs.close();rs=null;
							sql = null;
							
							/*
							 * ITERATE LEVEL 3 LIST AND FETCH ALL ITS LEVEL 4
							 */
							if(null!=level2Details.getChildList() && level2Details.getChildList().size()>0)
							{
								level3Details = null;
								for(int c=0;c<level2Details.getChildList().size();c++)
								{
									level3Details = (CategoryDetails)level2Details.getChildList().get(c);
									
									sql ="select DISTINCT LEVEL_4_REF_KEY,LEVEL_4_NAME from "+tableName +" WHERE LEVEL_1_REF_KEY = ? and LEVEL_2_REF_KEY=? "
											+ "AND LEVEL_3_REF_KEY = ?";
									pstmt = conn.prepareStatement(sql);
									pstmt.setString(1, catDetails.getRefKey());
									pstmt.setString(2, level2Details.getRefKey());
									pstmt.setString(3, level3Details.getRefKey());
									rs= pstmt.executeQuery();
									level4Details  =null;
									while(rs.next())
									{
										if(null!=rs.getString("LEVEL_4_NAME") && null!=rs.getString("LEVEL_4_REF_KEY"))
										{
											level4Details = new CategoryDetails();
											level4Details.setName(rs.getString("LEVEL_4_NAME"));
											level4Details.setRefKey(rs.getString("LEVEL_4_REF_KEY"));
											if(null==level3Details.getChildList() || level3Details.getChildList().size()<=0)
											{
												level3Details.setChildList(new ArrayList<CategoryDetails>());
											}
											level3Details.getChildList().add(level4Details);
											level4Details = null;
										}
									}
									level4Details = null;
									pstmt.close();pstmt=null;
									rs.close();rs=null;
									sql = null;
									
									/*
									 * ITERATE LEVEL 4 AND GET ALL ITS LEVEL 5
									 */
									if(null!=level3Details.getChildList() && level3Details.getChildList().size()>0)
									{
										level4Details = null;
										for(int d=0;d<level3Details.getChildList().size();d++)
										{
											level4Details = (CategoryDetails)level3Details.getChildList().get(d);
											
											sql ="select DISTINCT LEVEL_5_REF_KEY,LEVEL_5_NAME from "+tableName +" WHERE LEVEL_1_REF_KEY = ? and LEVEL_2_REF_KEY=? "
													+ "AND LEVEL_3_REF_KEY = ? and LEVEL_4_REF_KEY=?";
											pstmt = conn.prepareStatement(sql);
											pstmt.setString(1, catDetails.getRefKey());
											pstmt.setString(2, level2Details.getRefKey());
											pstmt.setString(3, level3Details.getRefKey());
											pstmt.setString(4, level4Details.getRefKey());
											rs= pstmt.executeQuery();
											level5Details  =null;
											while(rs.next())
											{
												if(null!=rs.getString("LEVEL_5_NAME") && null!=rs.getString("LEVEL_5_REF_KEY"))
												{
													level5Details = new CategoryDetails();
													level5Details.setName(rs.getString("LEVEL_5_NAME"));
													level5Details.setRefKey(rs.getString("LEVEL_5_REF_KEY"));
													if(null==level4Details.getChildList() || level4Details.getChildList().size()<=0)
													{
														level4Details.setChildList(new ArrayList<CategoryDetails>());
													}
													level4Details.getChildList().add(level5Details);
													level5Details = null;
												}
											}
											level5Details = null;
											pstmt.close();pstmt=null;
											rs.close();rs=null;
											sql = null;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "getCategoryHierarchyListForXML()", e);
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
			}
			catch(SQLException e)
			{
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "getCategoryHierarchyListForXML()", e);
			}
			pstmt=null;
			rs = null;
		}
		return categoryList;
	}

	public List<ContentDetails> getDocumentDetails(String channelRefKey)
	{
		List<ContentDetails> documentsList = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{
			try
			{
				if(null==conn || conn.isClosed()==true)
				{
					conn = getConnection();
				}
			}
			catch(Exception eq)
			{
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "getDocumentDetails()", eq);
			}
			
			String tableName="IM_DOCUMENT_"+channelRefKey.toUpperCase();
			String sql="SELECT DOCUMENT_ID,DOCUMENT_LOCALE FROM "+ tableName;
			pstmt =conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ContentDetails details = null;
			while(rs.next())
			{
				details=  new ContentDetails();
				details.setDocumentId(rs.getString("DOCUMENT_ID"));
				details.setLocale(rs.getString("DOCUMENT_LOCALE"));
				details.setChannelRefKey(channelRefKey);
				if(null==documentsList || documentsList.size()<=0)
				{
					documentsList = new ArrayList<ContentDetails>();
				}
				documentsList.add(details);
				details=null;
			}
			sql  =null;
			if(null!=documentsList && documentsList.size()>0)
			{
				logger.info("getDocumentDetails ::  Total Documents Found are :: >"+ documentsList.size());
			}
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "getDocumentDetails()", e);
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
			}
			catch(SQLException e)
			{
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "getDocumentDetails()", e);
			}
			pstmt=null;
			rs = null;
		}
		return documentsList;
	}
	
	public void saveManualLinkDetails(List<ManualLinkDetails> manualLinksList,String documentId, String locale)
	{
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{
			try
			{
				if(null==conn || conn.isClosed()==true)
				{
					conn = getConnection();
				}
			}
			catch(Exception eq)
			{
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "saveManualLinkDetails()", eq);
			}
			
			// set AutoCommit to false
			if(null!=conn)
			{
				conn.setAutoCommit(false);
			}
			
			String tableName="IM_DOCUMENT_MANUAL_LINKS";
			ManualLinkDetails details = null;
			
			String sql="DELETE FROM "+ tableName +" WHERE DOCUMENT_ID=? AND LOCALE = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, documentId);
			pstmt.setString(2, locale);
			pstmt.executeUpdate();
			pstmt.close();pstmt=null;
			sql = null;
			
			sql = "INSERT INTO IM_DOCUMENT_MANUAL_LINKS(DOCUMENT_ID,LOCALE,CHANNEL,LINK_DOC_ID,LINK_DOC_LOCALE,LINK_DOC_ANSWER_ID,"
					+ "LINK_DOC_RECORD_ID,LINK_DOC_VERSION_ID,LINK_DOC_VERSION_NO,LINK_DOC_TITLE,REC_CREATION_TMSTP) "
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
			pstmt =conn.prepareStatement(sql);
			for(int a=0;a<manualLinksList.size();a++)
			{
				details  = (ManualLinkDetails)manualLinksList.get(a);
				pstmt.setString(1, details.getContentDetails().getDocumentId());
				pstmt.setString(2, details.getContentDetails().getLocale());
				pstmt.setString(3, details.getContentDetails().getChannelRefKey());
				pstmt.setString(4, details.getDocumentId());
				pstmt.setString(5, details.getLocale());
				pstmt.setString(6, details.getAnswerId());
				pstmt.setString(7, details.getRecordId());
				pstmt.setString(8, details.getVersionId());
				pstmt.setString(9, details.getVersionNumber());
				pstmt.setString(10, details.getTitle());
				pstmt.setTimestamp(11, new Timestamp(new Date().getTime()));
				pstmt.addBatch();
				
				details  =null;
			}
			
			pstmt.executeBatch();
			pstmt.close();pstmt=null;
			sql  =null;
			tableName = null;
			details  =null;
			
			if(null!=conn)
			{
				conn.commit();
				conn.setAutoCommit(true);
			}
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "saveManualLinkDetails()", e);
			if(null!=conn)
			{
				try
				{
					conn.rollback();
				}
				catch(SQLException eq)
				{
					Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "saveManualLinkDetails()", eq);
				}
			}
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
			}
			catch(SQLException e)
			{
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "saveManualLinkDetails()", e);
			}
			pstmt=null;
			rs = null;
			manualLinksList= null;
			documentId = null;
			locale=  null;
		}
	}
}