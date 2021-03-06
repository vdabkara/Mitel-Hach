package com.hach.salesforce.dao;

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
import org.json.JSONArray;
import org.json.JSONObject;

import com.hach.salesforce.utils.common.ApplicationProperties;
import com.hach.salesforce.utils.common.CommonConstants;
import com.hach.salesforce.utils.common.DatabaseConnector;
import com.hach.salesforce.utils.common.Utilities;
import com.hach.salesforce.vo.AttachmentDetails;
import com.hach.salesforce.vo.CategoryDetails;
import com.hach.salesforce.vo.ChannelDetails;
import com.hach.salesforce.vo.ContentDetails;
import com.hach.salesforce.vo.ManualLinkDetails;


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
						is = TransactionDAO.class.getResourceAsStream("/com/hach/salesforce/queries/"+tableType+"_TABLE.sql");
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

	public void saveChannelDetails(List<ChannelDetails> channelsList)
	{
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{
			System.out.println(" save channel list :: >"+ channelsList.size());
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

			ChannelDetails details = null;
			String sql="INSERT INTO SF_CHANNEL_MASTER(NAME,RECORD_ID,REC_CREATION_TMSTP) VALUES(?,?,?)";
			pstmt=conn.prepareStatement(sql);
			for(int a=0;a<channelsList.size();a++)
			{
				details = (ChannelDetails)channelsList.get(a);
				pstmt.setString(1, details.getName());
				pstmt.setString(2, details.getRecordId());
				pstmt.setTimestamp(3, new Timestamp(new Date().getTime()));

				pstmt.addBatch();
				details = null;
			}
			pstmt.executeBatch();
			pstmt.close();pstmt=null;
			sql = null;

			if(null!=conn)
			{
				conn.commit();
				conn.setAutoCommit(true);
			}
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
			channelsList= null;
		}
	}

	public List<String> getIMChannelDetails()
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
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "getIMChannelDetails()", eq);
			}

			/*
			 * FIRST DELETE ALL MASTER DATA FROM CATEGORY MASTER
			 */
			String tableName="IM_CHANNEL_MASTER";
			String sql="SELECT REF_KEY FROM "+ tableName +" WHERE REF_KEY NOT IN ('MARKET_INFORMATION','GUIDED_ANSWER') ";
//			String sql="SELECT REF_KEY FROM "+ tableName +" WHERE REF_KEY IN ('COMMERCIAL_QA') ";
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
			Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "getIMChannelDetails()", e);
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
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "getIMChannelDetails()", e);
			}
			pstmt=null;
			rs = null;
		}
		return channelsList;
	}

	public List<ContentDetails> getIMDocumentsList(String channelRefKey,String dataType)
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
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "getIMDocumentsList()", eq);
			}

			/*
			 * FETCH ALL MASTER IDENTIFIERS FOR THE CHANNEL, E.G. WHERE BASE LOCALE AND DOCUMENT LOCALE IS SAME.
			 */
			String tableName="IM_DOCUMENT_"+channelRefKey.trim().toUpperCase();
			String sql="SELECT id,DOCUMENT_ID,BASE_LOCALE,DOCUMENT_LOCALE,ANSWER_ID,VERSION_NUMBER,OWNER_NAME,"
					+ "LAST_MODIFIER_NAME,CREATE_DATE,"
					+ " LAST_MODIFIED_DATE,SF_DOCUMENT_ID,SF_ARTICLE_ID,TITLE FROM "+ tableName +" where DOC_ARCHIVED!='YES' AND "
//							+ " DOCUMENT_ID='CO639' AND DOCUMENT_LOCALE='en_US' AND "; 
//							+ "  (REC_CREATION_TMSTP >= '2022-05-25' OR REC_MODIFIED_TMSTP>='2022-05-25') AND  ";
//							+ " DOCUMENT_LOCALE='en_US' AND DOCUMENT_ID IN ('CO321','CO325','CO326','CO327','CO359','CO360','CO361','CO362','CO363','CO373','CO375','CO377','CO379','CO381','CO386','CO405','CO441','CO442','CO443','CO451','CO452','CO511','CO536','CO537','CO538','CO552','CO554','CO582','CO587','CO596','CO617','CO618','CO619','CO620','CO621','CO622','CO623','CO624','CO627','CO634','PR178','PR362','PR395','PR409','SA1002','SA1003','SA1008','SA1012','SA707','SA803','SA805','SA875','SA880','SA898','SA927','SA932','SA953','SA954','SA955','SA965','SA966','SA967','SA968','SA969','SA970','SA971','SA972','SA973','SA974','SA975','SA976','SA977','SA978','SA979','SA980','SA981','SA982','SA983','SA984','SA985','SA986','SA987','SA988','SA989','SA990','SA991','SA992','SA993','SE2044','SE2045','SE2158','TE10048') AND ";
					+ " DOCUMENT_ID in ('CO270')  AND ";
			if(dataType.equals("MASTERS"))
			{
				sql+="DOCUMENT_LOCALE = BASE_LOCALE";
			}
			else if(dataType.equals("TRANSLATIONS"))
			{
				sql+="DOCUMENT_LOCALE != BASE_LOCALE";
			}
			
//			String sql="select distinct a.id,a.DOCUMENT_ID,a.BASE_LOCALE,a.DOCUMENT_LOCALE,a.ANSWER_ID,a.VERSION_NUMBER,a.OWNER_NAME," + 
//					"					a.LAST_MODIFIER_NAME,a.CREATE_DATE," + 
//					"					a.LAST_MODIFIED_DATE,a.SF_DOCUMENT_ID,a.SF_ARTICLE_ID,a.TITLE from"
//					+ " "+tableName+" a, sf_innerlinks_details b where a.DOCUMENT_ID = b.DOCUMENT_ID " + 
//					"and a.DOCUMENT_LOCALE = b.LOCALE and b.ACTION_TAKEN='REPLACE_SF_ARTICLE_ID_FOR_OKM_LINK' "
//					+ " and a.DOC_ARCHIVED!='YES' and " ;
//			if(dataType.equals("MASTERS"))
//			{
//				sql+="a.DOCUMENT_LOCALE = a.BASE_LOCALE";
//			}
//			else if(dataType.equals("TRANSLATIONS"))
//			{
//				sql+="a.DOCUMENT_LOCALE != a.BASE_LOCALE";
//			}
			
			
			logger.info("getIMDocumentsList :: >"+ dataType+" >> "+ sql);
			pstmt =conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ContentDetails details = null;
			while(rs.next())
			{
				if(null!=rs.getString("DOCUMENT_ID") && !"".equals(rs.getString("DOCUMENT_ID")) && 
						null!=rs.getString("DOCUMENT_LOCALE") && !"".equals(rs.getString("DOCUMENT_LOCALE")))
				{
					details=  new ContentDetails();
					details.setAutoId(rs.getLong("ID"));
					details.setDocumentId(rs.getString("DOCUMENT_ID"));
					details.setBaseLocale(rs.getString("BASE_LOCALE"));
					details.setLocale(rs.getString("DOCUMENT_LOCALE"));
					details.setAnswerId(rs.getString("ANSWER_ID"));
					details.setVersionNumber(rs.getString("VERSION_NUMBER"));
					details.setOwnerName(rs.getString("OWNER_NAME"));
					details.setLastModifierName(rs.getString("LAST_MODIFIER_NAME"));
					details.setCreateDate(rs.getString("CREATE_DATE"));
					details.setLastModifiedDate(rs.getString("LAST_MODIFIED_DATE"));
					details.setSfRecordId(rs.getString("SF_DOCUMENT_ID"));
					details.setSfArticleId(rs.getString("SF_ARTICLE_ID"));
					details.setTitle(rs.getString("TITLE"));

					if(null==documentsList || documentsList.size()<=0)
					{
						documentsList = new ArrayList<ContentDetails>();
					}
					documentsList.add(details);
					details = null;
				}
			}
			sql  =null;
			tableName= null;
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "getIMDocumentsList()", e);
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
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "getIMDocumentsList()", e);
			}
			pstmt=null;
			rs = null;
		}
		return documentsList;
	}

	public JSONObject getIMDocumentJSONObject(String channelRefKey, long autoId) throws Exception
	{
		JSONObject contentObj = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;

		if(null==conn || conn.isClosed()==true)
		{
			conn = getConnection();
		}


		/*
		 * FETCH ALL MASTER IDENTIFIERS FOR THE CHANNEL, E.G. WHERE BASE LOCALE AND DOCUMENT LOCALE IS SAME.
		 */
		String tableName="IM_DOCUMENT_"+channelRefKey.trim().toUpperCase();
		String sql="SELECT DOC_JSON FROM "+ tableName +" where ID="+autoId;
		pstmt =conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		String jsonData = null;
		if(rs.next())
		{
			if(null!=rs.getBytes("DOC_JSON"))
			{
				jsonData = new String(rs.getBytes("DOC_JSON"),"UTF-8");
			}
		}
		
		if(null!=jsonData && !"".equals(jsonData))
		{
			/*
			 * convert it to array
			 * before parsing.
			 */
			jsonData="["+jsonData+"]";
			JSONArray array = new JSONArray(jsonData);
			contentObj = array.getJSONObject(0);
		}
		jsonData = null;
		sql  =null;
		tableName= null;
		if(null!=pstmt)
		{
			pstmt.close();
		}
		if(null!=rs)
		{
			rs.close();
		}
		pstmt=null;
		rs=null;
		return contentObj;
	}

	public String getSFChannelRecordTypeId(String kaChannelRefKey)
	{
		String recordTypeId=null;
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
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "getSFChannelRecordTypeId()", eq);
			}

			/*
			 * FIRST DELETE ALL MASTER DATA FROM CATEGORY MASTER
			 */
			String tableName="SF_CHANNEL_MASTER";
			String sql="SELECT RECORD_ID FROM "+ tableName+" WHERE KA_REF_KEY=?";
			pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, kaChannelRefKey);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				if(null!=rs.getString("RECORD_ID") && !"".equals(rs.getString("RECORD_ID")))
				{
					recordTypeId = rs.getString("RECORD_ID");
				}
			}
			sql  =null;

		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "getSFChannelRecordTypeId()", e);
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
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "getSFChannelRecordTypeId()", e);
			}
			pstmt=null;
			rs = null;
		}
		return recordTypeId;
	}

	public List<AttachmentDetails> getDocumentAttachments(String channelRefKey,String documentId, String locale) throws Exception
	{
		List<AttachmentDetails> list = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;

		if(null==conn || conn.isClosed()==true)
		{
			conn = getConnection();
		}


		/*
		 * FIRST DELETE ALL MASTER DATA FROM CATEGORY MASTER
		 */
		String tableName="IM_ATTACHMENT_"+channelRefKey.toUpperCase();
		String sql="select A.NAME,B.CDN_URL from "+ tableName+" A left outer join im_all_attachments B on A.NAME = B.NAME AND"
				+ " A.DOCUMENT_ID = B.DOCUMENT_ID AND A.LOCALE=B.LOCALE where A.ATTACHMENT_TYPE='DOCUMENT' "
				+ " AND A.DOCUMENT_ID=? AND A.LOCALE=?";
		pstmt =conn.prepareStatement(sql);
		pstmt.setString(1, documentId);
		pstmt.setString(2, locale);
		rs = pstmt.executeQuery();
		AttachmentDetails details = null;
		while(rs.next())
		{
			details = new AttachmentDetails();
			details.setAttachmentName(rs.getString("NAME"));
			details.setCdnUrl(rs.getString("CDN_URL"));
			if(null==list || list.size()<=0)
			{
				list = new ArrayList<AttachmentDetails>();
			}
			list.add(details);
			details = null;
		}
		sql  =null;
		if(null!=pstmt)
		{
			pstmt.close();
		}
		if(null!=rs)
		{
			rs.close();
		}
		pstmt=null;
		rs= null;
		return list;
	}

	public List<AttachmentDetails> getDocumentInnerLinks(String channelRefKey,String documentId, String locale) throws Exception
	{
		List<AttachmentDetails> list = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;

		if(null==conn || conn.isClosed()==true)
		{
			conn = getConnection();
		}
		/*
		 * FIRST DELETE ALL MASTER DATA FROM CATEGORY MASTER
		 */
		String tableName="IM_ATTACHMENT_"+channelRefKey.toUpperCase();
		String sql=" select ATTACHMENT_TYPE,SOURCE_URL from "+tableName+" where ATTACHMENT_TYPE = 'INLINE_LINKS' "
				+ " AND DOCUMENT_ID=? AND LOCALE=?";
		pstmt =conn.prepareStatement(sql);
		pstmt.setString(1, documentId);
		pstmt.setString(2, locale);
		rs = pstmt.executeQuery();
		AttachmentDetails details = null;
		while(rs.next())
		{
			if(null!=rs.getBytes("SOURCE_URL"))
			{
				details = new AttachmentDetails();
				details.setAttachmentType(rs.getString("ATTACHMENT_TYPE"));
				details.setChannelRefKey(channelRefKey);
				details.setDocumentId(documentId);
				details.setLocale(locale);
				details.setSourcePath(new String(rs.getBytes("SOURCE_URL")));
				if(null==list || list.size()<=0)
				{
					list = new ArrayList<AttachmentDetails>();
				}
				list.add(details);
				details = null;
			}
		}
		sql  =null;
		if(null!=pstmt)
		{
			pstmt.close();
		}
		if(null!=rs)
		{
			rs.close();
		}
		pstmt = null;
		rs = null;

		return list;
	}

	public List<AttachmentDetails> getDocumentInlineImages(String channelRefKey,String documentId, String locale) throws SQLException, Exception
	{
		List<AttachmentDetails> list = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;

		if(null==conn || conn.isClosed()==true)
		{
			conn = getConnection();
		}

		/*
		 * GET ALL INLINE IMAGES ASSOCIATED WITH DOCUMENT & LOCALE
		 */
		String tableName="IM_ATTACHMENT_"+channelRefKey.toUpperCase();
		String sql="SELECT SOURCE_URL FROM "+ tableName+" WHERE ATTACHMENT_TYPE='INLINE_IMAGES' AND DOCUMENT_ID=? AND LOCALE=?" ;
		pstmt =conn.prepareStatement(sql);
		pstmt.setString(1, documentId);
		pstmt.setString(2, locale);
		rs = pstmt.executeQuery();
		AttachmentDetails details = null;
		while(rs.next()) 
		{
			if(null!=rs.getBytes("SOURCE_URL"))
			{
				details = new AttachmentDetails();
				details.setChannelRefKey(channelRefKey);
				details.setDocumentId(documentId);
				details.setLocale(locale);
				details.setSourcePath(new String(rs.getBytes("SOURCE_URL")));
				if(null==list || list.size()<=0)
				{
					list = new ArrayList<AttachmentDetails>();
				}
				list.add(details);
				details = null;
			}
		}
		pstmt.close(); pstmt=null;
		rs.close();rs=null;
		sql = null;
		
		/*
		 * NOW FOR THE IDENFIEID IMAGES - CHECK 
		 * IF THEY CONTAIN ?tk=
		 *  GET THE URL BEFORE THAT AND TRY TO GET ALL CDN URLS FOR THAT URL
		 *  ELSE USE THE COMPLETE SOURCE URL
		 */
		if(null!=list && list.size()>0)
		{
			details = null;
			String urlToCheck = null;
			for(int a=0;a<list.size();a++)
			{
				details = (AttachmentDetails)list.get(a);
				if(null!=details.getSourcePath() && !"".equals(details.getSourcePath()))
				{
					if(details.getSourcePath().trim().toLowerCase().indexOf("?tk=")!=-1)
					{
						urlToCheck = details.getSourcePath().substring(0, details.getSourcePath().trim().toLowerCase().indexOf("?tk="));
					}
					else
					{
						urlToCheck = details.getSourcePath();
					}
				}
				
				if(null!=urlToCheck && !"".equals(urlToCheck))
				{
					sql = "SELECT CDN_URL FROM im_all_inline_images WHERE SOURCE_URL LIKE ? AND DOCUMENT_ID=? AND LOCALE=?";
					pstmt =conn.prepareStatement(sql);
					pstmt.setString(1, urlToCheck+"%");
					pstmt.setString(2, documentId);
					pstmt.setString(3, locale);
					rs = pstmt.executeQuery();
					while(rs.next())
					{
						if(null!=rs.getString("CDN_URL") && !"".equals(rs.getString("CDN_URL")))
						{
							// set CDN URL for Source Image
							details.setCdnUrl(rs.getString("CDN_URL"));
							break;
						}
					}
					pstmt.close();pstmt=null;
					rs.close();rs=null;
					sql  =null;
				}
				urlToCheck = null;
				details = null;
			}
		}
		pstmt = null;
		rs = null;

		return list;
	}

	public void updateSFTransactionDetails(ContentDetails details, List<AttachmentDetails> innerLinksList, String channelRefKey, List<CategoryDetails> categoryList, List<ManualLinkDetails> manualLinksList)
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
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "updateSFTransactionDetails()", eq);
			}

			if(null!=conn)
			{
				conn.setAutoCommit(false);
			}

			String tableName="IM_DOCUMENT_"+channelRefKey.toUpperCase();
			String sql = "";
			InputStream is = null;
			byte[] data = null;
			String varContent="";
			
			/*
			 * check for Category Operation - all SUCCESS
			 * as well for Innerlinks - ALL FAUILURE
			 */
			AttachmentDetails aDetails = null;
			CategoryDetails catDetails = null;
			
			if(null!=innerLinksList && innerLinksList.size()>0)
			{
				// DEFAULT VALUE = YES
				details.setAllInnerLinksMapped("Y");
				aDetails=  null;
				for(int a=0;a<innerLinksList.size();a++)
				{
					aDetails= (AttachmentDetails)innerLinksList.get(a);
					if(null!=aDetails.getProcessingStatus() && aDetails.getProcessingStatus().equals(CommonConstants.STATUS_REPLACE_SF_ARTICLE_ID))
					{
						if(null==aDetails.getMappingStatus() || (null!=aDetails.getMappingStatus() && !aDetails.getMappingStatus().equals("SUCCESS")))
						{
							// INNER LINKS ARTICLES ALL MAPPING NOT DONE
							details.setSfProcessingStatus("FAILURE");
							details.setAllInnerLinksMapped("N");
							break;
						}
					}
					aDetails = null;
				}
			}
			
			if(null!=categoryList && categoryList.size()>0)
			{
				catDetails= null;
				// DEFAULT VALUE = 
				details.setAllCategoriesMapped("Y");
				for(int a=0;a<categoryList.size();a++)
				{
					catDetails= (CategoryDetails)categoryList.get(a);

					if(null==catDetails.getSfMappingStatus() || (null!=catDetails.getSfMappingStatus() && !catDetails.getSfMappingStatus().equals("SUCCESS")))
					{
						// ALL CATEGORIES MAPPING NOT DONE
						details.setSfProcessingStatus("FAILURE");
						details.setAllCategoriesMapped("N");
						break;
					}
				
					aDetails = null;
				}
			}
			
			/**
			 * IDENTIFY STATUS TO BE SET AS SUCCESS_WITH_ERRORS OR NOT
			 */
			if((null!=details.getAllInnerLinksMapped() && !"".equals(details.getAllInnerLinksMapped())) || 
					(null!=details.getAllCategoriesMapped() && !"".equals(details.getAllCategoriesMapped())))
			{
				// either Inner Links Found or Categories Found
				// check if any SF ERROR MESSAGE OR SF REMARKS (ANY ERRORS FOUND)
				if((null==details.getSfErrorMessage() || "".equals(details.getSfErrorMessage())) && 
						(null==details.getSfRemarks() || "".equals(details.getSfRemarks())))
				{
					// no errors
					if(null!=details.getAllInnerLinksMapped() && details.getAllInnerLinksMapped().equals("N"))
					{
						// set status as SUCCESS WITH ERRORS
						details.setSfProcessingStatus("SUCCESS_WITH_ERRORS");
					}
					if(null!=details.getAllCategoriesMapped() && details.getAllCategoriesMapped().equals("N"))
					{
						// set status as SUCCESS WITH ERRORS
						details.setSfProcessingStatus("SUCCESS_WITH_ERRORS");
					}
				}
			}
			
			if(details.getExecutionMethod().equals("CREATE"))
			{
				sql="UPDATE "+tableName+" SET SF_ARTICLE_NUMBER=?,SF_DOCUMENT_ID=?,SF_LOCALE=?,SF_CHANNEL_ID=?,SF_URL_NAME=?,"
						+ "SF_MASTER_IDENTIFIER=?,SF_DOCUMENT_URL=?,"
						+ "SF_PROCESSING_STATUS = ?, SF_ERROR_MESSAGE = ?,SF_REMARKS=?,SF_ARTICLE_ID=?,"
						+ "SF_ALL_CATEGORIES_MAPPED=?,SF_ALL_INRLKS_ARTICLES_MAPPED=?,REC_MODIFIED_TMSTP=? WHERE ID=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, details.getSfArticleNumber());
				pstmt.setString(2, details.getSfRecordId());
				pstmt.setString(3, details.getSfLocale());
				pstmt.setString(4, details.getSfChannelId());
				pstmt.setString(5, details.getSfURLName());
				pstmt.setString(6, details.getSfMasterIdentifier());
				pstmt.setString(7, details.getSfdocumentURL());
				pstmt.setString(8, details.getSfProcessingStatus());
				// ERROR MESSAGE AS BLOB
				is = null;
				data = null;
				varContent="";
				if(null!=details.getErrorMessage())
				{
					varContent = details.getErrorMessage();
				}
				data=  varContent.getBytes();
				is = new ByteArrayInputStream(data);
				pstmt.setBinaryStream(9, is, data.length);

				pstmt.setString(10, details.getSfRemarks());
				pstmt.setString(11, details.getSfArticleId());
				pstmt.setString(12, details.getAllCategoriesMapped());
				pstmt.setString(13, details.getAllInnerLinksMapped());
				pstmt.setTimestamp(14, new Timestamp(new Date().getTime()));
				pstmt.setLong(15, details.getAutoId());
				pstmt.executeUpdate();

				is.close();is=null;
				data = null;
				varContent = null;
				sql = null;
				pstmt.close();pstmt=null;
			}
			else if(details.getExecutionMethod().equals("UPDATE"))
			{
				sql="UPDATE "+tableName+" SET SF_PROCESSING_STATUS = ?, SF_ERROR_MESSAGE = ?,SF_REMARKS=?,"
						+ " SF_ALL_CATEGORIES_MAPPED=?, SF_ALL_INRLKS_ARTICLES_MAPPED=?,REC_MODIFIED_TMSTP = ? "
						+ " WHERE ID=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, details.getSfProcessingStatus());
				// ERROR MESSAGE AS BLOB
				is = null;
				data = null;
				varContent="";
				if(null!=details.getErrorMessage())
				{
					varContent = details.getErrorMessage();
				}
				data=  varContent.getBytes();
				is = new ByteArrayInputStream(data);
				pstmt.setBinaryStream(2, is, data.length);
				pstmt.setString(3, details.getSfRemarks());
				pstmt.setString(4, details.getAllCategoriesMapped());
				pstmt.setString(5, details.getAllInnerLinksMapped());
				pstmt.setTimestamp(6, new Timestamp(new Date().getTime()));
				pstmt.setLong(7, details.getAutoId());
				pstmt.executeUpdate();

				is.close();is=null;
				data = null;
				varContent = null;
				sql = null;
				pstmt.close();pstmt=null;
			}
			
			

			/*
			 * NOW SF INNERLINKS OPERATION
			 */
			sql = "DELETE FROM SF_INNERLINKS_DETAILS WHERE DOCUMENT_ID=? AND LOCALE=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, details.getDocumentId());
			pstmt.setString(2, details.getLocale());
			pstmt.executeUpdate();
			pstmt.close();pstmt=null;
			sql = null;


			// Start Inserting InnerLinks
			if(null!=innerLinksList && innerLinksList.size()>0)
			{
				aDetails = null;
				sql =" INSERT INTO SF_INNERLINKS_DETAILS (DOCUMENT_ID,LOCALE,CHANNEL,SOURCE_URL,"
						+ "TARGET_URL,ACTION_TAKEN,MAPPING_STATUS,REMARKS) VALUES(?,?,?,?,?,?,?,?) ";
				pstmt = conn.prepareStatement(sql);
				for(int a=0;a<innerLinksList.size();a++)
				{
					aDetails = (AttachmentDetails)innerLinksList.get(a);

					pstmt.setString(1, aDetails.getDocumentId());
					pstmt.setString(2, aDetails.getLocale());
					pstmt.setString(3, aDetails.getChannelRefKey());
					pstmt.setString(4, aDetails.getSourcePath());
					pstmt.setString(5, aDetails.getTargetUrl());
					pstmt.setString(6, aDetails.getProcessingStatus());
					pstmt.setString(7, aDetails.getMappingStatus());
					pstmt.setString(8, aDetails.getErrorMessage());
					pstmt.addBatch();

					aDetails = null;
				}

				pstmt.executeBatch();
				pstmt.close();pstmt=null;
				sql  =null;
			}

			// Start Updating Categories
			if(null!=categoryList && categoryList.size()>0)
			{
				// ERROR MESSAGE AS BLOB
				is = null;
				data = null;
				varContent="";
				catDetails = null;
//				tableName="IM_CATEGORY_"+channelRefKey.trim().toUpperCase();
//				tableName="im_category_transactions";
				tableName="im_article_cat_mapping";
//				sql = "UPDATE "+tableName +" SET SF_CAT_ASSOCIATION_ID = ?,SF_MAPPING_STATUS = ?,SF_ERROR_MESSAGE = ? WHERE ID = ?";
				sql = "UPDATE "+tableName +" SET SF_CAT_ASSOCIATION_ID = ?,SF_MAPPING_STATUS = ?,SF_ERROR_MESSAGE = ? WHERE DOCUMENT_ID = ? AND SF_CAT_REF_KEY = ?";
				pstmt = conn.prepareStatement(sql);
				for(int a=0;a<categoryList.size();a++)
				{
					catDetails = (CategoryDetails)categoryList.get(a);

					pstmt.setString(1, catDetails.getSfCatAssociationId());
					pstmt.setString(2, catDetails.getSfMappingStatus());

					varContent="";
					if(null!=catDetails.getErrorMessage())
					{
						varContent = catDetails.getErrorMessage();
					}
					data=  varContent.getBytes();
					is = new ByteArrayInputStream(data);

					pstmt.setBinaryStream(3, is, data.length);

					is.close();is=null;
					data = null;
					varContent = null;

//					pstmt.setLong(4, catDetails.getAutoId());
					pstmt.setString(4, details.getDocumentId());
					pstmt.setString(5, catDetails.getRefKey());
					pstmt.addBatch();
					catDetails = null;
				}

				pstmt.executeBatch();
				pstmt.close();pstmt=null;
				sql = null;
				catDetails = null;
				is = null;
				data  =null;
				varContent = null;
			}
			
			
			// start Updating Manual Links
			if(null!=manualLinksList && manualLinksList.size()>0)
			{
				ManualLinkDetails mlDetails = null;
				sql = "UPDATE IM_DOCUMENT_MANUAL_LINKS SET SF_MAPPING_STATUS = ?,SF_MAPPED_DOC_URL=?,SF_MAPPING_ERROR=? "
						+ " WHERE ID = ?";
				pstmt = conn.prepareStatement(sql);
				
				for(int r=0;r<manualLinksList.size();r++)
				{
					mlDetails= (ManualLinkDetails)manualLinksList.get(r);
					pstmt.setString(1, mlDetails.getSfMappingStatus());
					pstmt.setString(2, mlDetails.getSfMappedDocUrl());
					pstmt.setString(3, mlDetails.getSfMappingError());
					pstmt.setLong(4, mlDetails.getAutoId());
					pstmt.addBatch();
					mlDetails = null;
				}
				
				pstmt.executeBatch();
				pstmt.close();pstmt=null;
				sql = null;
				mlDetails = null;
			}

			if(null!=conn)
			{
				conn.commit();
				conn.setAutoCommit(true);
			}
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "updateSFTransactionDetails()", e);
			if(null!=conn)
			{
				try
				{
					conn.rollback();
				}
				catch(SQLException eq)
				{
					Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "updateSFTransactionDetails()", eq);
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
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "updateSFTransactionDetails()", e);
			}
			pstmt=null;
			rs = null;
			innerLinksList = null;
			details = null;
			categoryList = null;
			manualLinksList = null;
		}
	}

	public void updateCategoryAddTransactions(ContentDetails details,  List<CategoryDetails> categoryList)
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
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "updateCategoryAddTransactions()", eq);
			}

			if(null!=conn)
			{
				conn.setAutoCommit(false);
			}

			String tableName=null;
			String sql = "";
			InputStream is = null;
			byte[] data = null;
			String varContent="";
			
			/*
			 * check for Category Operation - all SUCCESS
			 * as well for Innerlinks - ALL FAUILURE
			 */
			CategoryDetails catDetails = null;
			// Start Updating Categories
			if(null!=categoryList && categoryList.size()>0)
			{
				// ERROR MESSAGE AS BLOB
				is = null;
				data = null;
				varContent="";
				catDetails = null;
//				tableName="IM_CATEGORY_"+channelRefKey.trim().toUpperCase();
//				tableName="im_category_transactions";
				tableName="im_article_cat_mapping";
//				sql = "UPDATE "+tableName +" SET SF_CAT_ASSOCIATION_ID = ?,SF_MAPPING_STATUS = ?,SF_ERROR_MESSAGE = ? WHERE ID = ?";
				sql = "UPDATE "+tableName +" SET SF_CAT_ASSOCIATION_ID = ?,SF_MAPPING_STATUS = ?,SF_ERROR_MESSAGE = ? WHERE DOCUMENT_ID = ? AND SF_CAT_REF_KEY = ?";
				pstmt = conn.prepareStatement(sql);
				for(int a=0;a<categoryList.size();a++)
				{
					catDetails = (CategoryDetails)categoryList.get(a);

					pstmt.setString(1, catDetails.getSfCatAssociationId());
					pstmt.setString(2, catDetails.getSfMappingStatus());

					varContent="";
					if(null!=catDetails.getErrorMessage())
					{
						varContent = catDetails.getErrorMessage();
					}
					data=  varContent.getBytes();
					is = new ByteArrayInputStream(data);

					pstmt.setBinaryStream(3, is, data.length);

					is.close();is=null;
					data = null;
					varContent = null;

//					pstmt.setLong(4, catDetails.getAutoId());
					pstmt.setString(4, details.getDocumentId());
					pstmt.setString(5, catDetails.getRefKey());
					pstmt.addBatch();
					catDetails = null;
				}

				pstmt.executeBatch();
				pstmt.close();pstmt=null;
				sql = null;
				catDetails = null;
				is = null;
				data  =null;
				varContent = null;
			}
			if(null!=conn)
			{
				conn.commit();
				conn.setAutoCommit(true);
			}
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "updateCategoryAddTransactions()", e);
			if(null!=conn)
			{
				try
				{
					conn.rollback();
				}
				catch(SQLException eq)
				{
					Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "updateCategoryAddTransactions()", eq);
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
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "updateCategoryAddTransactions()", e);
			}
			pstmt=null;
			rs = null;
			details = null;
			categoryList = null;
		}
	}

	
	public void updateCategoryDeleteTransactions(ContentDetails details, List<CategoryDetails> categoryList)
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
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "updateCategoryDeleteTransactions()", eq);
			}

			if(null!=conn)
			{
				conn.setAutoCommit(false);
			}

			String tableName="";
			String sql = "";
			InputStream is = null;
			byte[] data = null;
			String varContent="";
			/*
			 * check for Category Operation - all SUCCESS
			 */
			CategoryDetails catDetails = null;

			
			// Start Updating Categories
			if(null!=categoryList && categoryList.size()>0)
			{
				// ERROR MESSAGE AS BLOB
				is = null;
				data = null;
				varContent="";
				catDetails = null;
				//	tableName="IM_CATEGORY_"+channelRefKey.trim().toUpperCase();
				//	tableName="im_category_transactions";
				tableName="im_article_cat_mapping";
				//	sql = "UPDATE "+tableName +" SET SF_CAT_ASSOCIATION_ID = ?,SF_MAPPING_STATUS = ?,SF_ERROR_MESSAGE = ? WHERE ID = ?";
				sql = "UPDATE "+tableName +" SET IS_DELETED = ?,SF_DELETE_STATUS = ?,SF_DELETE_ERROR_MESSAGE = ? WHERE ID = ?";
				pstmt = conn.prepareStatement(sql);
				String isDeleted=null;
				for(int a=0;a<categoryList.size();a++)
				{
					catDetails = (CategoryDetails)categoryList.get(a);
					isDeleted = null;
					if(null!=catDetails.getSfMappingStatus() && catDetails.getSfMappingStatus().equals("SUCCESS"))
					{
						isDeleted="Y";
					}
					else
					{
						isDeleted ="N";
					}
					pstmt.setString(1, isDeleted);
					pstmt.setString(2, catDetails.getSfMappingStatus());

					varContent="";
					if(null!=catDetails.getErrorMessage())
					{
						varContent = catDetails.getErrorMessage();
					}
					data=  varContent.getBytes();
					is = new ByteArrayInputStream(data);

					pstmt.setBinaryStream(3, is, data.length);

					is.close();is=null;
					data = null;
					varContent = null;
					
//					pstmt.setString(4, details.getDocumentId());
//					pstmt.setString(5, catDetails.getRefKey());
					pstmt.setLong(4, catDetails.getAutoId());
					pstmt.addBatch();
					catDetails = null;
				}

				pstmt.executeBatch();
				pstmt.close();pstmt=null;
				sql = null;
				catDetails = null;
				is = null;
				data  =null;
				varContent = null;
			}
			
			if(null!=conn)
			{
				conn.commit();
				conn.setAutoCommit(true);
			}
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "updateCategoryDeleteTransactions()", e);
			if(null!=conn)
			{
				try
				{
					conn.rollback();
				}
				catch(SQLException eq)
				{
					Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "updateCategoryDeleteTransactions()", eq);
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
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "updateCategoryDeleteTransactions()", e);
			}
			pstmt=null;
			rs = null;
			details = null;
			categoryList = null;
		}
	}

	
	public void updateSFPublishTransactionDetails(ContentDetails details)
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
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "updateSFPublishTransactionDetails()", eq);
			}

			if(null!=conn)
			{
				conn.setAutoCommit(false);
			}

			String tableName="SF_PUBLISH_TRANSACTIONS";
			String sql = "";
			InputStream is = null;
			byte[] data = null;
			String varContent="";
			long autoId=0;
			
			sql="SELECT ID FROM "+ tableName+" WHERE DOCUMENT_ID=? AND DOCUMENT_LOCALE = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, details.getDocumentId());
			pstmt.setString(2, details.getLocale());
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				autoId = rs.getLong("ID");
			}
			pstmt.close();pstmt=null;
			rs.close();rs=null;
			sql = null;
			
			/**
			 * IF AUTO ID IS GREATER THAN 0 - THEN UPDATE
			 * ELSE - CREATE
			 */
			if(autoId > 0)
			{
				sql = "UPDATE "+ tableName+" SET SF_PROCESSING_STATUS = ?,SF_REMARKS=?,SF_ERROR_MESSAGE = ?,REC_MODIFIED_TMSTP=?, "
						+ " SF_DOCUMENT_ID = ? WHERE ID = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, details.getSfProcessingStatus());
				pstmt.setString(2, details.getSfRemarks());
				// ERROR MESSAGE AS BLOB
				is = null;
				data = null;
				varContent="";
				if(null!=details.getSfErrorMessage())
				{
					varContent = details.getSfErrorMessage();
				}
				data=  varContent.getBytes();
				is = new ByteArrayInputStream(data);
				pstmt.setBinaryStream(3, is, data.length);
				pstmt.setTimestamp(4, new Timestamp(new Date().getTime()));
				pstmt.setString(5, details.getSfRecordId());
				pstmt.setLong(6, autoId);
				pstmt.executeUpdate();
				
				is.close();is=null;
				data = null;
				varContent = null;
				sql = null;
				pstmt.close();pstmt=null;
			}
			else
			{
				sql = "INSERT INTO "+tableName+" (DOCUMENT_ID,ANSWER_ID,CHANNEL_REF_KEY,BASE_LOCALE,"
						+ "DOCUMENT_LOCALE,TITLE,REC_CREATION_TMSTP,SF_DOCUMENT_ID,SF_MASTER_IDENTIFIER,SF_PROCESSING_STATUS,"
						+ "SF_REMARKS,SF_ERROR_MESSAGE) values(?,?,?,?,?,?,?,?,?,?,?,?) ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, details.getDocumentId());
				pstmt.setString(2, details.getAnswerId());
				pstmt.setString(3, details.getChannelRefKey());
				pstmt.setString(4, details.getBaseLocale());
				pstmt.setString(5, details.getLocale());
				pstmt.setString(6, details.getTitle());
				pstmt.setTimestamp(7, new Timestamp(new Date().getTime()));
				pstmt.setString(8, details.getSfRecordId());
				pstmt.setString(9, details.getSfMasterIdentifier());
				pstmt.setString(10, details.getSfProcessingStatus());
				pstmt.setString(11, details.getSfRemarks());
				// ERROR MESSAGE AS BLOB
				is = null;
				data = null;
				varContent="";
				if(null!=details.getSfErrorMessage())
				{
					varContent = details.getSfErrorMessage();
				}
				data=  varContent.getBytes();
				is = new ByteArrayInputStream(data);
				pstmt.setBinaryStream(12, is, data.length);
				pstmt.executeUpdate();
				
				is.close();is=null;
				data = null;
				varContent = null;
				sql = null;
				pstmt.close();pstmt=null;
			}
			if(null!=conn)
			{
				conn.commit();
				conn.setAutoCommit(true);
			}
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "updateSFPublishTransactionDetails()", e);
			if(null!=conn)
			{
				try
				{
					conn.rollback();
				}
				catch(SQLException eq)
				{
					Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "updateSFPublishTransactionDetails()", eq);
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
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "updateSFPublishTransactionDetails()", e);
			}
			pstmt=null;
			rs = null;
			details = null;
		}
	}

	public void updateSFUnPublishTransactionDetails(ContentDetails details)
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
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "updateSFUnPublishTransactionDetails()", eq);
			}

			if(null!=conn)
			{
				conn.setAutoCommit(false);
			}

			String tableName="SF_UNPUBLISH_TRANSACTIONS";
			String sql = "";
			InputStream is = null;
			byte[] data = null;
			String varContent="";
			long autoId=0;
			
			sql="SELECT ID FROM "+ tableName+" WHERE DOCUMENT_ID=? AND DOCUMENT_LOCALE = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, details.getDocumentId());
			pstmt.setString(2, details.getLocale());
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				autoId = rs.getLong("ID");
			}
			pstmt.close();pstmt=null;
			rs.close();rs=null;
			sql = null;
			
			/**
			 * IF AUTO ID IS GREATER THAN 0 - THEN UPDATE
			 * ELSE - CREATE
			 */
			if(autoId > 0)
			{
				sql = "UPDATE "+ tableName+" SET SF_PROCESSING_STATUS = ?,SF_REMARKS=?,SF_ERROR_MESSAGE = ?,REC_MODIFIED_TMSTP=?, "
						+ " SF_DOCUMENT_ID = ?,SF_OLD_DOCUMENT_ID = ? WHERE ID = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, details.getSfProcessingStatus());
				pstmt.setString(2, details.getSfRemarks());
				// ERROR MESSAGE AS BLOB
				is = null;
				data = null;
				varContent="";
				if(null!=details.getSfErrorMessage())
				{
					varContent = details.getSfErrorMessage();
				}
				data=  varContent.getBytes();
				is = new ByteArrayInputStream(data);
				pstmt.setBinaryStream(3, is, data.length);
				pstmt.setTimestamp(4, new Timestamp(new Date().getTime()));
				pstmt.setString(5, details.getSfNewDraftRecordId());
				pstmt.setString(6, details.getSfRecordId());
				pstmt.setLong(7, autoId);
				pstmt.executeUpdate();
				
				is.close();is=null;
				data = null;
				varContent = null;
				sql = null;
				pstmt.close();pstmt=null;
			}
			else
			{
				sql = "INSERT INTO "+tableName+" (DOCUMENT_ID,ANSWER_ID,CHANNEL_REF_KEY,BASE_LOCALE,"
						+ "DOCUMENT_LOCALE,TITLE,REC_CREATION_TMSTP,SF_DOCUMENT_ID,SF_MASTER_IDENTIFIER,SF_PROCESSING_STATUS,"
						+ "SF_REMARKS,SF_ERROR_MESSAGE,SF_OLD_DOCUMENT_ID) values(?,?,?,?,?,?,?,?,?,?,?,?,?) ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, details.getDocumentId());
				pstmt.setString(2, details.getAnswerId());
				pstmt.setString(3, details.getChannelRefKey());
				pstmt.setString(4, details.getBaseLocale());
				pstmt.setString(5, details.getLocale());
				pstmt.setString(6, details.getTitle());
				pstmt.setTimestamp(7, new Timestamp(new Date().getTime()));
				pstmt.setString(8, details.getSfNewDraftRecordId());
				pstmt.setString(9, details.getSfMasterIdentifier());
				pstmt.setString(10, details.getSfProcessingStatus());
				pstmt.setString(11, details.getSfRemarks());
				// ERROR MESSAGE AS BLOB
				is = null;
				data = null;
				varContent="";
				if(null!=details.getSfErrorMessage())
				{
					varContent = details.getSfErrorMessage();
				}
				data=  varContent.getBytes();
				is = new ByteArrayInputStream(data);
				pstmt.setBinaryStream(12, is, data.length);
				pstmt.setString(13, details.getSfRecordId());
				pstmt.executeUpdate();
				
				is.close();is=null;
				data = null;
				varContent = null;
				sql = null;
				pstmt.close();pstmt=null;
			}
			
			if(null!=conn)
			{
				conn.commit();
				conn.setAutoCommit(true);
			}
			// COMMIT THE ABOVE TRANSACTION FOR TRACKING
			/*
			 * check if Processing status is NOT SUCCESS
			 * do not update the new Draft Article Id in channel document table
			 */
			if(null!=details.getSfNewDraftRecordId() && !"".equals(details.getSfNewDraftRecordId()))
			{
				// update it in Channel Document Table
				tableName ="IM_DOCUMENT_"+details.getChannelRefKey().toUpperCase();
				sql = "UPDATE "+tableName+" SET SF_DOCUMENT_ID=? WHERE DOCUMENT_ID=? AND DOCUMENT_LOCALE=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, details.getSfNewDraftRecordId());
				pstmt.setString(2, details.getDocumentId());
				pstmt.setString(3, details.getLocale());
				pstmt.executeUpdate();
				pstmt.close();pstmt=null;
				sql = null;
				tableName=  null;
			}
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "updateSFUnPublishTransactionDetails()", e);
			if(null!=conn)
			{
				try
				{
					conn.rollback();
				}
				catch(SQLException eq)
				{
					Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "updateSFUnPublishTransactionDetails()", eq);
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
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "updateSFUnPublishTransactionDetails()", e);
			}
			pstmt=null;
			rs = null;
			details = null;
		}
	}


	public String getMasterIdentifierArticleId(String channelRefKey,String documentId, String baseLocale)
	{
		String articleId=null;
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
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "getSFChannelRecordTypeId()", eq);
			}

			/*
			 * FIRST DELETE ALL MASTER DATA FROM CATEGORY MASTER
			 */
			String tableName="IM_DOCUMENT_"+channelRefKey.toUpperCase();
			String sql="SELECT SF_ARTICLE_ID FROM "+ tableName+" WHERE DOCUMENT_ID=? AND BASE_LOCALE=? AND SF_ARTICLE_ID IS NOT NULL";
			pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, documentId);
			pstmt.setString(2, baseLocale);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				if(null!=rs.getString("SF_ARTICLE_ID") && !"".equals(rs.getString("SF_ARTICLE_ID")))
				{
					articleId = rs.getString("SF_ARTICLE_ID");
				}
			}
			sql  =null;

		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "getMasterIdentifierArticleId()", e);
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
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "getMasterIdentifierArticleId()", e);
			}
			pstmt=null;
			rs = null;
		}
		return articleId;
	}

	public AttachmentDetails getSFURLNameForKADocumentOnAnswerId(String answerId,AttachmentDetails aDetails)
	{
		String sfURLName=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		List<ContentDetails> list=  null;
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
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "getSFURLNameForKADocumentOnAnswerId()", eq);
			}

			String tableName=null;
			String sql=null;
			ContentDetails details = null;
			String[] channels="ALERTS,COMMERCIAL_QA,DANAHER_WQ_CONTENT,HACH_CONTENT,HEXIS_OTHER,METHODS,PODCAST,PRODUCT_COMMERCIALIZATION_,PRODUCT_INFORMATION_BULLETIN,REACTIVOS_OTHER,SALES_DOCUMENTS,SELF_SERVICE,SERVICE_DOCUMENTS,TECHNICAL_QA,TERMODINAMICA_OTHER,TROUBLESHOOTING,VIDEO".split(",");
			if(null!=channels && channels.length>0)
			{
				for(int a=0;a<channels.length;a++)
				{
					tableName=  "IM_DOCUMENT_"+channels[a];
					sql = "SELECT DOCUMENT_ID, DOCUMENT_LOCALE, SF_URL_NAME,SF_DOCUMENT_ID from "+tableName +" where ANSWER_ID=? ";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, answerId);
//					pstmt.setString(2, aDetails.getLocale());
					rs = pstmt.executeQuery();
					while(rs.next())
					{
						details=  new ContentDetails();
						details.setDocumentId(rs.getString("DOCUMENT_ID"));
						details.setLocale(rs.getString("DOCUMENT_LOCALE"));
						details.setSfURLName(rs.getString("SF_URL_NAME"));
						details.setSfRecordId(rs.getString("SF_DOCUMENT_ID"));
						
						if(null==list || list.size()<=0)
						{
							list = new ArrayList<ContentDetails>();
						}
						list.add(details);
						details = null;
					}
					pstmt.close();pstmt=null;
					rs.close();rs=null;
					sql  =null;;
					tableName = null;
				}
			}

			if(null!=list && list.size()>0)
			{
				details = null;
				boolean locFound = false;
				for(int a=0;a<list.size();a++)
				{
					details = (ContentDetails)list.get(a);
					if(aDetails.getLocale().trim().toLowerCase().equals(details.getLocale().toLowerCase()))
					{
						locFound = true;
//						if(null!=details.getSfURLName() && !"".equals(details.getSfURLName()))
//						{
//							// create sfURLName
//							sfURLName = "/articles/"+details.getLocale()+"/Knowledge/"+details.getSfURLName();
//							// set mapping status as Success
//							aDetails.setMappingStatus("SUCCESS");
//							// set target URL
//							aDetails.setTargetUrl(sfURLName);
//							sfURLName= null;
//						}
//						else
//						{
//							// set mapping status as Failure
//							aDetails.setMappingStatus("FAILURE");
//							// set remarks 
//							aDetails.setErrorMessage("Document for Answer Id does not exist in SalesForce for processing Locale. Please refer to All Documents report WHERE SFProcessingStatus=\"FAILURE\".");
//						}
						
						if(null!=details.getSfRecordId() && !"".equals(details.getSfRecordId()))
						{
							// create url= /lightning/r/Knowledge__kav/ka08G000000CpLzQAK/view
							sfURLName = "/lightning/r/Knowledge__kav/"+details.getSfRecordId()+"/view";
							// set mapping status as Success
							aDetails.setMappingStatus("SUCCESS");
							// set target URL
							aDetails.setTargetUrl(sfURLName);
							sfURLName= null;
						}
						else
						{
							// set mapping status as Failure
							aDetails.setMappingStatus("FAILURE");
							// set remarks 
							aDetails.setErrorMessage("Document for Answer Id does not exist in SalesForce for processing Locale. Please refer to All Documents report WHERE SFProcessingStatus=\"FAILURE\".");
						}
						
						break;
					}
					details = null;
				}

				/*
				 * INCASE SAME LOCALE TRANSLATION OR MASTER NOT FOUND THEN CONVERT AS DEAD LINK
				 * SEND NULL IN RETURN
				 */
				
				if(locFound==false)
				{
					// SET PROCESSING STATUS AS FAILURE
					aDetails.setMappingStatus("FAILURE");
					aDetails.setErrorMessage("Answer Id does not exsit for the Document's Locale.");
					sfURLName  =null;
				}
			}
			else
			{
				// set status as Failure - As No document found in Extracted Data.
				aDetails.setMappingStatus("FAILURE");
				aDetails.setErrorMessage("Answer Id does not exsit in Extracted data. Either the document is Unpublished / Draft / Expired in KA.");
			}
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "getSFURLNameForKADocumentOnAnswerId()", e);
			// set status as Failure - As No document found in Extracted Data.
			aDetails.setMappingStatus("FAILURE");
			aDetails.setErrorMessage("Database Exception Occurred: >"+ e.getMessage());
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
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "getSFURLNameForKADocumentOnAnswerId()", e);
			}
			pstmt=null;
			rs = null;
		}
		return aDetails;
	}

	public AttachmentDetails getSFURLNameForKADocumentOnDocumentId(String documentId,AttachmentDetails aDetails)
	{
		String sfURLName=null;
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
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "getSFURLNameForKADocumentOnDocumentId()", eq);
			}

			String tableName=null;
			String sql=null;
			ContentDetails details = null;
			String[] channels="ALERTS,COMMERCIAL_QA,DANAHER_WQ_CONTENT,HACH_CONTENT,HEXIS_OTHER,METHODS,PODCAST,PRODUCT_COMMERCIALIZATION_,PRODUCT_INFORMATION_BULLETIN,REACTIVOS_OTHER,SALES_DOCUMENTS,SELF_SERVICE,SERVICE_DOCUMENTS,TECHNICAL_QA,TERMODINAMICA_OTHER,TROUBLESHOOTING,VIDEO".split(",");
			if(null!=channels && channels.length>0)
			{
				for(int a=0;a<channels.length;a++)
				{
					tableName=  "IM_DOCUMENT_"+channels[a];
					sql = "SELECT SF_URL_NAME,SF_DOCUMENT_ID from "+tableName +" where DOCUMENT_ID=? AND DOCUMENT_LOCALE = ? ";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, documentId);
					pstmt.setString(2, aDetails.getLocale());
					rs = pstmt.executeQuery();
					if(rs.next())
					{
						details=  new ContentDetails();
						details.setSfURLName(rs.getString("SF_URL_NAME"));
						details.setSfRecordId(rs.getString("SF_DOCUMENT_ID"));
					}
					pstmt.close();pstmt=null;
					rs.close();rs=null;
					sql  =null;;
					tableName = null;
				}
			}
			
			if(null!=details && null!=details.getSfRecordId() && !"".equals(details.getSfRecordId()))
			{
				// create url= /lightning/r/Knowledge__kav/ka08G000000CpLzQAK/view
				sfURLName = "/lightning/r/Knowledge__kav/"+details.getSfRecordId()+"/view";
				// set mapping status as Success
				aDetails.setMappingStatus("SUCCESS");
				// set target URL
				aDetails.setTargetUrl(sfURLName);
				sfURLName= null;
			}
			else
			{
				/*
				 * CHECK FOR ANSWER ID IN WHOLE DATABASE, IF NOT FOUND
				 * THEN SET IT AS FAILURE
				 */
				if(null!=channels && channels.length>0)
				{
					List<ContentDetails> list=  null;
					for(int a=0;a<channels.length;a++)
					{
						tableName=  "IM_DOCUMENT_"+channels[a];
						sql = "SELECT DOCUMENT_ID, DOCUMENT_LOCALE, SF_URL_NAME,SF_DOCUMENT_ID from "+tableName +" where ANSWER_ID=? ";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, documentId);
//						pstmt.setString(2, aDetails.getLocale());
						rs = pstmt.executeQuery();
						while(rs.next())
						{
							details=  new ContentDetails();
							details.setDocumentId(rs.getString("DOCUMENT_ID"));
							details.setLocale(rs.getString("DOCUMENT_LOCALE"));
							details.setSfURLName(rs.getString("SF_URL_NAME"));
							details.setSfRecordId(rs.getString("SF_DOCUMENT_ID"));
							
							if(null==list || list.size()<=0)
							{
								list = new ArrayList<ContentDetails>();
							}
							list.add(details);
							details = null;
						}
						pstmt.close();pstmt=null;
						rs.close();rs=null;
						sql  =null;;
						tableName = null;
					}
					
					if(null!=list && list.size()>0)
					{
						details = null;
						boolean locFound = false;
						for(int a=0;a<list.size();a++)
						{
							details = (ContentDetails)list.get(a);
							if(aDetails.getLocale().trim().toLowerCase().equals(details.getLocale().toLowerCase()))
							{
								locFound = true;							
								if(null!=details.getSfRecordId() && !"".equals(details.getSfRecordId()))
								{
									// create url= /lightning/r/Knowledge__kav/ka08G000000CpLzQAK/view
									sfURLName = "/lightning/r/Knowledge__kav/"+details.getSfRecordId()+"/view";
									// set mapping status as Success
									aDetails.setMappingStatus("SUCCESS");
									// set target URL
									aDetails.setTargetUrl(sfURLName);
									sfURLName= null;
								}
								else
								{
									// set mapping status as Failure
									aDetails.setMappingStatus("FAILURE");
									// set remarks 
									aDetails.setErrorMessage("Document OR Answer Id for Locale does not exist in SalesForce for processing Locale. Please refer to All Documents report WHERE SFProcessingStatus=\"FAILURE\".");
								}
								
								break;
							}
							details = null;
						}

						/*
						 * INCASE SAME LOCALE TRANSLATION OR MASTER NOT FOUND THEN CONVERT AS DEAD LINK
						 * SEND NULL IN RETURN
						 */
						
						if(locFound==false)
						{
							// SET PROCESSING STATUS AS FAILURE
							aDetails.setMappingStatus("FAILURE");
							aDetails.setErrorMessage("Document Id / Answer Id does not exsit for the Document's Locale.");
							sfURLName  =null;
						}
					}
					else
					{
						// set status as Failure - As No document found in Extracted Data.
						aDetails.setMappingStatus("FAILURE");
						aDetails.setErrorMessage("Document Id / Answer Id does not exsit in Extracted data. Either the document is Unpublished / Draft / Expired in KA.");
					}
				}
			}
			details =null;
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "getSFURLNameForKADocumentOnDocumentId()", e);
			// set status as Failure - As No document found in Extracted Data.
			aDetails.setMappingStatus("FAILURE");
			aDetails.setErrorMessage("Database Exception Occurred: >"+ e.getMessage());
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
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "getSFURLNameForKADocumentOnDocumentId()", e);
			}
			pstmt=null;
			rs = null;
		}
		return aDetails;
	}

	
	public List<CategoryDetails> getDocumentCategoryList(String channelRefKey,String documentId, String locale,String operationType) throws Exception
	{
		List<CategoryDetails> list = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;

		if(null==conn || conn.isClosed()==true)
		{
			conn = getConnection();
		}
		/*
		 * FETCH ONLY THOSE CATEGORIES WHERE CAT ASSOCIATION ID IS NOT NULL
		 * E.G. SOME UPDATE OPERATION IS GOING ON. NO NEED TO PERFORM EXTRA OPERATIONS
		 * FOR CATEGORY PROCESSING
		 * 
		 * AND FETCH DISTINCT CATEGORIES OF ALL LOCALES FOR THE PROCESSING DOCUMENT 
		 */
		String tableName="im_article_cat_mapping";
//		String tableName="im_category_transactions";
//		String tableName="IM_CATEGORY_"+channelRefKey.toUpperCase();
//		String sql=" select ID,REF_KEY from "+tableName+" where "
//				+ "  DOCUMENT_ID=? AND LOCALE=? AND SF_CAT_ASSOCIATION_ID IS NULL";
//		String sql=" select DISTINCT REF_KEY from "+tableName+" where "
//				+ "  DOCUMENT_ID=?  AND SF_CAT_ASSOCIATION_ID IS NULL";
		String sql="";
		if(operationType.equals("ADD"))
		{
			sql=" select DISTINCT SF_CAT_REF_KEY from "+tableName+" where "
					+ "  DOCUMENT_ID=?  AND SF_CAT_ASSOCIATION_ID IS NULL AND IS_DELETED='N'";
		}
		else
		{
			sql=" select ID, SF_CAT_REF_KEY,SF_CAT_ASSOCIATION_ID from "+tableName+" where "
					+ "  DOCUMENT_ID=?  AND SF_CAT_ASSOCIATION_ID IS NOT NULL AND IS_DELETED='N'";
		}
		pstmt =conn.prepareStatement(sql);
		pstmt.setString(1, documentId);
		rs = pstmt.executeQuery();
		CategoryDetails details = null;
		while(rs.next())
		{
			details = new CategoryDetails();
			details.setRefKey(rs.getString("SF_CAT_REF_KEY"));
			if(!operationType.equals("ADD"))
			{
				details.setAutoId(rs.getLong("ID"));
				details.setSfCatAssociationId(rs.getString("SF_CAT_ASSOCIATION_ID"));
			}
			if(null==list || list.size()<=0)
			{
				list = new ArrayList<CategoryDetails>();
			}
			list.add(details);
			details = null;
		}
		sql  =null;
		if(null!=pstmt)
		{
			pstmt.close();
		}
		if(null!=rs)
		{
			rs.close();
		}
		pstmt = null;
		rs = null;

		return list;
	}

	public List<ManualLinkDetails> getDocumentManualLinks(String documentId, String locale) throws Exception
	{
		List<ManualLinkDetails> list = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;

		if(null==conn || conn.isClosed()==true)
		{
			conn = getConnection();
		}
		/*
		 * FIRST DELETE ALL MASTER DATA FROM CATEGORY MASTER
		 */
		String tableName="im_document_manual_links";
		String sql="SELECT ID, LINK_DOC_ID,LINK_DOC_LOCALE,LINK_DOC_TITLE FROM "+tableName+" WHERE DOCUMENT_ID=? AND LOCALE=?"; 
		pstmt =conn.prepareStatement(sql);
		pstmt.setString(1, documentId);
		pstmt.setString(2, locale);
		rs = pstmt.executeQuery();
		ManualLinkDetails details = null;
		while(rs.next())
		{
			if(null!=rs.getString("LINK_DOC_ID") && !"".equals(rs.getString("LINK_DOC_ID")) && 
					null!=rs.getString("LINK_DOC_LOCALE") && !"".equals(rs.getString("LINK_DOC_LOCALE")))
			{
				details = new ManualLinkDetails();
				details.setAutoId(rs.getLong("ID"));
				details.setDocumentId(rs.getString("LINK_DOC_ID"));
				details.setLocale(rs.getString("LINK_DOC_LOCALE"));
				details.setTitle(rs.getString("LINK_DOC_TITLE"));
				if(null==list || list.size()<=0)
				{
					list = new ArrayList<ManualLinkDetails>();
				}
				list.add(details);
				details = null;
			}
		}
		sql  =null;
		if(null!=pstmt)
		{
			pstmt.close();
		}
		if(null!=rs)
		{
			rs.close();
		}
		pstmt = null;
		rs = null;

		/*
		 * FOR EACH MANUAL LINK - CHECK IF THE SF DOCUMENT EXISTS OR NOT 
		 */
		if(null!=list && list.size()>0)
		{
			logger.info("getDocumentManualLinks :: Total Manual Links found for "+ documentId+" OF "+locale +" are :: >"+ list.size());
			/*
			 * BASED ON DOCUMENT PREFIX - IDENTIFY CHANNEL NAME 
			 */
			String channelRefKey=null;
			details = null;
			for(int a=0;a<list.size();a++)
			{
				details = (ManualLinkDetails)list.get(a);
				channelRefKey = getChannelRefKey(details.getDocumentId());
				if(null!=channelRefKey && !"".equals(channelRefKey))
				{
					sql = "SELECT SF_ARTICLE_NUMBER,SF_DOCUMENT_URL,SF_DOCUMENT_ID FROM IM_DOCUMENT_"+channelRefKey.toUpperCase()+" "
							+ "WHERE DOCUMENT_ID=? AND DOCUMENT_LOCALE=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, details.getDocumentId());
					pstmt.setString(2, details.getLocale());
					rs = pstmt.executeQuery();
					if(rs.next())
					{
//						if(null!=rs.getString("SF_DOCUMENT_URL") && !"".equals(rs.getString("SF_DOCUMENT_URL")))
						if(null!=rs.getString("SF_DOCUMENT_ID") && !"".equals(rs.getString("SF_DOCUMENT_ID")))
						{
							details.setSfMappedDocUrl(rs.getString("SF_DOCUMENT_URL"));
							details.setSfArticleNumber(rs.getString("SF_ARTICLE_NUMBER"));
							details.setSfRecordId(rs.getString("SF_DOCUMENT_ID"));
						}
					}
					pstmt.close();pstmt=null;
					rs.close();rs=null;
					sql  =null;
					
//					if(null!=details.getSfMappedDocUrl() && !"".equals(details.getSfMappedDocUrl()))
					if(null!=details.getSfRecordId() && !"".equals(details.getSfRecordId()))
					{
						details.setSfMappingStatus("SUCCESS");
					}
					else
					{
						details.setSfMappingStatus("FAILURE");
						details.setSfMappingError("FAILED TO LOCATE SALES FORCE DOCUMENT FOR MANUAL LINK.");
					}
				}
				else
				{
					details.setSfMappingStatus("FAILURE");
					details.setSfMappingError("FAILED TO LOCATE CHANNEL TABLE FOR MANUAL LINK.");
				}
				channelRefKey = null;
				details = null;
			}
		}
		else
		{
			logger.info("getDocumentManualLinks :: No Manual Links found for "+ documentId+" OF "+locale);
		}
		
		
		return list;
	}


	private String getChannelRefKey(String documentId)
	{
		String channelRefKey = null;
		if(documentId.startsWith("AL"))
		{
			channelRefKey = "ALERTS";
		}
		else if(documentId.startsWith("CO"))
		{
			channelRefKey="COMMERCIAL_QA";
		}
		else if(documentId.startsWith("DA"))
		{
			channelRefKey="danaher_wq_content".toUpperCase();
		}
		else if(documentId.startsWith("GU"))
		{
			channelRefKey="guided_answer".toUpperCase();
		}
		else if(documentId.startsWith("HA"))
		{
			channelRefKey="hach_content".toUpperCase();
		}
		else if(documentId.startsWith("HE"))
		{
			channelRefKey="hexis_other".toUpperCase();
		}
		else if(documentId.startsWith("ME"))
		{
			channelRefKey="methods".toUpperCase();
		}
		else if(documentId.startsWith("PO"))
		{
			channelRefKey="podcast".toUpperCase();
		}
		else if(documentId.startsWith("PR"))
		{
			channelRefKey="product_commercialization_".toUpperCase();
		}
		else if(documentId.startsWith("PIB"))
		{
			channelRefKey="product_information_bulletin".toUpperCase();
		}
		else if(documentId.startsWith("RE"))
		{
			channelRefKey="reactivos_other".toUpperCase();
		}
		else if(documentId.startsWith("SA"))
		{
			channelRefKey="sales_documents".toUpperCase();
		}
		else if(documentId.startsWith("SES"))
		{
			channelRefKey="self_service".toUpperCase();
		}
		else if(documentId.startsWith("SE"))
		{
			channelRefKey="service_documents".toUpperCase();
		}
		else if(documentId.startsWith("TE"))
		{
			channelRefKey="technical_qa".toUpperCase();
		}
		else if(documentId.startsWith("TD"))
		{
			channelRefKey="termodinamica_other".toUpperCase();
		}
		else if(documentId.startsWith("TR"))
		{
			channelRefKey="troubleshooting".toUpperCase();
		}
		else if(documentId.startsWith("VI"))
		{
			channelRefKey="video".toUpperCase();
		}
		return channelRefKey;
	}
	
	public List<String> getChannelDetailsForInnerLinksProcessing()
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
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "getChannelDetailsForInnerLinksProcessing()", eq);
			}

			/*
			 * FIRST DELETE ALL MASTER DATA FROM CATEGORY MASTER
			 */
			String sql="select distinct CHANNEL from sf_innerlinks_details where action_taken in ('REPLACE_SF_ARTICLE_ID','REPLACE_SF_ARTICLE_ID_FOR_OKM_LINK') and MAPPING_STATUS='FAILURE'";
			pstmt =conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				if(null!=rs.getString("CHANNEL") && !"".equals(rs.getString("CHANNEL")))
				{
					if(null==channelsList || channelsList.size()<=0)
					{
						channelsList = new ArrayList<String>();
					}
					channelsList.add(rs.getString("CHANNEL"));
				}
			}
			sql  =null;

		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "getChannelDetailsForInnerLinksProcessing()", e);
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
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "getChannelDetailsForInnerLinksProcessing()", e);
			}
			pstmt=null;
			rs = null;
		}
		return channelsList;
	}


	public List<ContentDetails> getIMDocumentsListForInnerLinksUpdate(String channelRefKey)
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
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "getIMDocumentsListForInnerLinksUpdate()", eq);
			}

			/*
			 * GET DOCUMENTS DATA FOR MODIFYING FOR BOTH INNERLINKS & MANUAL LINKS
			 */
			String tableName="IM_DOCUMENT_"+channelRefKey.trim().toUpperCase();
			String sql="(SELECT DISTINCT A.id,A.DOCUMENT_ID,A.BASE_LOCALE,A.DOCUMENT_LOCALE,A.ANSWER_ID,A.VERSION_NUMBER,A.OWNER_NAME,A.LAST_MODIFIER_NAME,A.CREATE_DATE," + 
					" A.LAST_MODIFIED_DATE,A.SF_DOCUMENT_ID,A.SF_ARTICLE_ID,A.TITLE FROM "+tableName+" A ," + 
					" sf_innerlinks_details B where A.DOC_ARCHIVED!='YES' and A.DOCUMENT_ID=B.DOCUMENT_ID AND "
					+ " A.DOCUMENT_LOCALE=B.LOCALE AND " + 
					" B.ACTION_TAKEN IN ('REPLACE_SF_ARTICLE_ID','REPLACE_SF_ARTICLE_ID_FOR_OKM_LINK') AND A.SF_DOCUMENT_ID IS NOT NULL ) "
					+ " UNION "
					+ " (SELECT DISTINCT A.id,A.DOCUMENT_ID,A.BASE_LOCALE,A.DOCUMENT_LOCALE,A.ANSWER_ID,A.VERSION_NUMBER,A.OWNER_NAME,A.LAST_MODIFIER_NAME,A.CREATE_DATE,"
					+ " A.LAST_MODIFIED_DATE,A.SF_DOCUMENT_ID,A.SF_ARTICLE_ID,A.TITLE FROM "+tableName+" A ," + 
					" im_document_manual_links B where A.DOC_ARCHIVED!='YES' and A.DOCUMENT_ID=B.DOCUMENT_ID AND  "
					+ "A.DOCUMENT_LOCALE=B.LOCALE AND " + 
					"  B.SF_MAPPING_STATUS='FAILURE' AND A.SF_DOCUMENT_ID IS NOT NULL) ";
//			
			
//			String sql="(SELECT DISTINCT A.id,A.DOCUMENT_ID,A.BASE_LOCALE,A.DOCUMENT_LOCALE,A.ANSWER_ID,A.VERSION_NUMBER,A.OWNER_NAME,A.LAST_MODIFIER_NAME,A.CREATE_DATE," + 
//					" A.LAST_MODIFIED_DATE,A.SF_DOCUMENT_ID,A.SF_ARTICLE_ID,A.TITLE FROM "+tableName+" A ," + 
//					" sf_innerlinks_details B where A.DOC_ARCHIVED!='YES' and A.DOCUMENT_ID=B.DOCUMENT_ID AND "
//					+ " A.DOCUMENT_LOCALE=B.LOCALE AND " + 
//					" B.ACTION_TAKEN IN ('REPLACE_SF_ARTICLE_ID','REPLACE_SF_ARTICLE_ID_FOR_OKM_LINK') AND A.SF_DOCUMENT_ID IS NOT NULL  AND"
//					+ " A.DOCUMENT_ID IN ('CO113','CO4','CO527','PIB460','PR115','PR140','PR175','PR178','PR197','PR212','PR213','PR224','PR320','PR362','PR395','PR396','PR401','PR409','PR439','PR44','PR442','PR449','PR54','PR6','SA880','SA936','SE1682','SE2065','TE10011','TE10230','TE11154','TE11700','TE1200','TE12046','TE12214','TE12324','TE12395','TE12633','TE12651','TE12652','TE12653','TE12772','TE12938','TE3360','TE4126','TE4518','TE5422','TE5438','TE566','TE5760','TE6156','TE6328','TE6360','TE6377','TE6461','TE650','TE6796','TE8258','TE8399','TE8752','TE8895','TE8952','TE927','TE947','TE9518','TE970','TE9714','TE9719','TE9966','VI7','VI86') )";
//			
			logger.info("getIMDocumentsListForInnerLinksUpdate ::  >> "+ sql);
			pstmt =conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ContentDetails details = null;
			boolean alreayAdded=false;
			ContentDetails existDetails = null;
			while(rs.next())
			{
				if(null!=rs.getString("DOCUMENT_ID") && !"".equals(rs.getString("DOCUMENT_ID")) && 
						null!=rs.getString("DOCUMENT_LOCALE") && !"".equals(rs.getString("DOCUMENT_LOCALE")))
				{
					details=  new ContentDetails();
					details.setAutoId(rs.getLong("ID"));
					details.setDocumentId(rs.getString("DOCUMENT_ID"));
					details.setBaseLocale(rs.getString("BASE_LOCALE"));
					details.setLocale(rs.getString("DOCUMENT_LOCALE"));
					details.setAnswerId(rs.getString("ANSWER_ID"));
					details.setVersionNumber(rs.getString("VERSION_NUMBER"));
					details.setOwnerName(rs.getString("OWNER_NAME"));
					details.setLastModifierName(rs.getString("LAST_MODIFIER_NAME"));
					details.setCreateDate(rs.getString("CREATE_DATE"));
					details.setLastModifiedDate(rs.getString("LAST_MODIFIED_DATE"));
					details.setSfRecordId(rs.getString("SF_DOCUMENT_ID"));
					details.setSfArticleId(rs.getString("SF_ARTICLE_ID"));
					details.setTitle(rs.getString("TITLE"));
					
					alreayAdded = false;
					/*
					 * before adding to list check if combination of
					 * documentId + baseLocale + documentLocale already added to List or not
					 */
					if(null!=documentsList && documentsList.size()>0)
					{
						existDetails=  null;
						for(int a=0;a<documentsList.size();a++)
						{
							existDetails = (ContentDetails)documentsList.get(a);
							if(existDetails.getDocumentId().equalsIgnoreCase(details.getDocumentId()) && 
								existDetails.getBaseLocale().equalsIgnoreCase(details.getBaseLocale()) && 
								existDetails.getLocale().equalsIgnoreCase(details.getLocale()))
							{
								alreayAdded=true;
								break;
							}
							existDetails = null;
						}
					}
					
					
					if(alreayAdded==false)
					{
						if(null==documentsList || documentsList.size()<=0)
						{
							documentsList = new ArrayList<ContentDetails>();
						}
						documentsList.add(details);
					}
					details = null;
				}
			}
			sql  =null;
			tableName= null;
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "getIMDocumentsListForInnerLinksUpdate()", e);
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
				Utilities.printStackTraceToLogs(TransactionDAO.class.getName(), "getIMDocumentsListForInnerLinksUpdate()", e);
			}
			pstmt=null;
			rs = null;
		}
		return documentsList;
	}

}
