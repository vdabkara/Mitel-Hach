package com.hach.dataextraction.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hach.dataextraction.utils.common.DatabaseConnector;
import com.hach.dataextraction.utils.common.Utilities;
import com.hach.dataextraction.vo.CategoryDetails;

public class CategoryLevelFetchDAO extends DatabaseConnector{

	private Logger logger = Logger.getLogger(CategoryLevelFetchDAO.class);
	
	public Connection conn = null;

	public List<CategoryDetails> getLevel1(String locale)
	{
		List<CategoryDetails> categoryList=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{
			conn = getConnection();
			String tableName="IM_PRODUCT_CATEGORY_MASTER_"+locale.trim().toUpperCase();
			String sql ="select DISTINCT LEVEL_1_REF_KEY,LEVEL_1_NAME from "+tableName+" where LEVEL_1_REF_KEY is not null ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			CategoryDetails catDetails= null;
			while(rs.next())
			{
				if(null!=rs.getString("LEVEL_1_NAME") && null!=rs.getString("LEVEL_1_REF_KEY"))
				{
					catDetails = new CategoryDetails();
					catDetails.setName(rs.getString("LEVEL_1_NAME"));
					if(null!=catDetails.getName() && !"".equals(catDetails.getName()))
					{
						catDetails.setName(catDetails.getName().replace("&", "&amp;"));
						if(catDetails.getName().length()>40)
						{
							catDetails.setName(catDetails.getName().substring(0,39));
						}
					}
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
			}
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(CategoryLevelFetchDAO.class.getName(), "getLevel1()", e);
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
				Utilities.printStackTraceToLogs(CategoryLevelFetchDAO.class.getName(), "getLevel1()", e);
			}
			pstmt=null;
			rs = null;
			conn = null;
		}
		return categoryList;
	}
	
	public List<CategoryDetails> getLevel2(String locale,String level1RefKey)
	{
		List<CategoryDetails> categoryList=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{
			conn = getConnection();
			
			String tableName="IM_PRODUCT_CATEGORY_MASTER_"+locale.trim().toUpperCase();
			String sql ="select DISTINCT LEVEL_2_REF_KEY,LEVEL_2_NAME from "+tableName +" "
					+ " WHERE LEVEL_1_REF_KEY = ? and LEVEL_2_REF_KEY is not null";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, level1RefKey);
			rs= pstmt.executeQuery();
			CategoryDetails catDetails  =null;
			while(rs.next())
			{
				if(null!=rs.getString("LEVEL_2_NAME") && null!=rs.getString("LEVEL_2_REF_KEY"))
				{
					catDetails = new CategoryDetails();
					catDetails.setName(rs.getString("LEVEL_2_NAME"));
					if(null!=catDetails.getName() && !"".equals(catDetails.getName()))
					{
						catDetails.setName(catDetails.getName().replace("&", "&amp;"));
						if(catDetails.getName().length()>40)
						{
							catDetails.setName(catDetails.getName().substring(0,39));
						}
					}
					catDetails.setRefKey(rs.getString("LEVEL_2_REF_KEY"));
					if(null==categoryList || categoryList.size()<=0)
					{
						categoryList = new ArrayList<CategoryDetails>();
					}
					categoryList.add(catDetails);
					catDetails = null;
				}
			}
			catDetails = null;
			pstmt.close();pstmt=null;
			rs.close();rs=null;
			sql = null;
			
			if(null!=categoryList && categoryList.size()>0)
			{
				logger.info("Total Unique Level 2 Found for {"+level1RefKey+"} are :: >"+ categoryList.size());
			}
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(CategoryLevelFetchDAO.class.getName(), "getLevel2()", e);
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
				Utilities.printStackTraceToLogs(CategoryLevelFetchDAO.class.getName(), "getLevel2()", e);
			}
			pstmt=null;
			rs = null;
			conn = null;
		}
		return categoryList;
	}

	public List<CategoryDetails> getLevel3(String locale,String level1RefKey,String level2RefKey)
	{
		List<CategoryDetails> categoryList=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{
			conn = getConnection();
			
			String tableName="IM_PRODUCT_CATEGORY_MASTER_"+locale.trim().toUpperCase();
			String sql ="select DISTINCT LEVEL_3_REF_KEY,LEVEL_3_NAME from "+tableName +" "
					+ " WHERE LEVEL_1_REF_KEY = ? AND LEVEL_2_REF_KEY = ? and LEVEL_3_REF_KEY is not null";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, level1RefKey);
			pstmt.setString(2, level2RefKey);
			rs= pstmt.executeQuery();
			CategoryDetails catDetails  =null;
			while(rs.next())
			{
				if(null!=rs.getString("LEVEL_3_NAME") && null!=rs.getString("LEVEL_3_REF_KEY"))
				{
					catDetails = new CategoryDetails();
					catDetails.setName(rs.getString("LEVEL_3_NAME"));
					if(null!=catDetails.getName() && !"".equals(catDetails.getName()))
					{
						catDetails.setName(catDetails.getName().replace("&", "&amp;"));
						if(catDetails.getName().length()>40)
						{
							catDetails.setName(catDetails.getName().substring(0,39));
						}
					}
					catDetails.setRefKey(rs.getString("LEVEL_3_REF_KEY"));
					if(null==categoryList || categoryList.size()<=0)
					{
						categoryList = new ArrayList<CategoryDetails>();
					}
					categoryList.add(catDetails);
					catDetails = null;
				}
			}
			catDetails = null;
			pstmt.close();pstmt=null;
			rs.close();rs=null;
			sql = null;
			
			if(null!=categoryList && categoryList.size()>0)
			{
				logger.info("Total Unique Level 3 Found for {"+level1RefKey+" / "+level2RefKey+"} are :: >"+ categoryList.size());
			}
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(CategoryLevelFetchDAO.class.getName(), "getLevel3()", e);
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
				Utilities.printStackTraceToLogs(CategoryLevelFetchDAO.class.getName(), "getLevel3()", e);
			}
			pstmt=null;
			rs = null;
			conn = null;
		}
		return categoryList;
	}

	public List<CategoryDetails> getLevel4(String locale,String level1RefKey,String level2RefKey,String level3RefKey)
	{
		List<CategoryDetails> categoryList=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{
			conn = getConnection();
			
			String tableName="IM_PRODUCT_CATEGORY_MASTER_"+locale.trim().toUpperCase();
			String sql ="select DISTINCT LEVEL_4_REF_KEY,LEVEL_4_NAME from "+tableName +" "
					+ " WHERE LEVEL_1_REF_KEY = ? AND LEVEL_2_REF_KEY = ? and LEVEL_3_REF_KEY = ? and LEVEL_4_REF_KEY is not null";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, level1RefKey);
			pstmt.setString(2, level2RefKey);
			pstmt.setString(3, level3RefKey);
			rs= pstmt.executeQuery();
			CategoryDetails catDetails  =null;
			while(rs.next())
			{
				if(null!=rs.getString("LEVEL_4_NAME") && !"".equals(rs.getString("LEVEL_4_NAME")) && null!=rs.getString("LEVEL_4_REF_KEY") && !"".equals(rs.getString("LEVEL_4_NAME")))
				{
					catDetails = new CategoryDetails();
					catDetails.setName(rs.getString("LEVEL_4_NAME"));
					if(null!=catDetails.getName() && !"".equals(catDetails.getName()))
					{
						catDetails.setName(catDetails.getName().replace("&", "&amp;"));
						if(catDetails.getName().length()>40)
						{
							catDetails.setName(catDetails.getName().substring(0,39));
						}
					}
					catDetails.setRefKey(rs.getString("LEVEL_4_REF_KEY"));
					if(null==categoryList || categoryList.size()<=0)
					{
						categoryList = new ArrayList<CategoryDetails>();
					}
					categoryList.add(catDetails);
					catDetails = null;
				}
			}
			catDetails = null;
			pstmt.close();pstmt=null;
			rs.close();rs=null;
			sql = null;
			
			if(null!=categoryList && categoryList.size()>0)
			{
				logger.info("Total Unique Level 4 Found for {"+level1RefKey+" / "+level2RefKey+" / " +level3RefKey+"} are :: >"+ categoryList.size());
			}
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(CategoryLevelFetchDAO.class.getName(), "getLevel4()", e);
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
				Utilities.printStackTraceToLogs(CategoryLevelFetchDAO.class.getName(), "getLevel4()", e);
			}
			pstmt=null;
			rs = null;
			conn = null;
		}
		return categoryList;
	}

	public List<CategoryDetails> getLevel5(String locale,String level1RefKey,String level2RefKey,String level3RefKey,String level4RefKey)
	{
		List<CategoryDetails> categoryList=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try
		{
			conn = getConnection();
			
			String tableName="IM_PRODUCT_CATEGORY_MASTER_"+locale.trim().toUpperCase();
			String sql ="select DISTINCT LEVEL_5_REF_KEY,LEVEL_5_NAME from "+tableName +" "
					+ " WHERE LEVEL_1_REF_KEY = ? AND LEVEL_2_REF_KEY = ? and LEVEL_3_REF_KEY = ? "
					+ "and LEVEL_4_REF_KEY = ? and LEVEL_5_REF_KEY is not null";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, level1RefKey);
			pstmt.setString(2, level2RefKey);
			pstmt.setString(3, level3RefKey);
			pstmt.setString(4, level4RefKey);
			rs= pstmt.executeQuery();
			CategoryDetails catDetails  =null;
			while(rs.next())
			{
				if(null!=rs.getString("LEVEL_5_NAME") && !"".equals(rs.getString("LEVEL_5_NAME")) && null!=rs.getString("LEVEL_5_REF_KEY") && !"".equals(rs.getString("LEVEL_5_REF_KEY")))
				{
					catDetails = new CategoryDetails();
					catDetails.setName(rs.getString("LEVEL_5_NAME"));
					if(null!=catDetails.getName() && !"".equals(catDetails.getName()))
					{
						catDetails.setName(catDetails.getName().replace("&", "&amp;"));
						if(catDetails.getName().length()>40)
						{
							catDetails.setName(catDetails.getName().substring(0,39));
						}
					}
					catDetails.setRefKey(rs.getString("LEVEL_5_REF_KEY"));
					if(null==categoryList || categoryList.size()<=0)
					{
						categoryList = new ArrayList<CategoryDetails>();
					}
					categoryList.add(catDetails);
					catDetails = null;
				}
			}
			catDetails = null;
			pstmt.close();pstmt=null;
			rs.close();rs=null;
			sql = null;
			
			if(null!=categoryList && categoryList.size()>0)
			{
				logger.info("Total Unique Level 5 Found for {"+level1RefKey+" / "+level2RefKey+" / " +level3RefKey+" / "+level4RefKey+"} are :: >"+ categoryList.size());
			}
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(CategoryLevelFetchDAO.class.getName(), "getLevel5()", e);
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
				Utilities.printStackTraceToLogs(CategoryLevelFetchDAO.class.getName(), "getLevel5()", e);
			}
			pstmt=null;
			rs = null;
			conn = null;
		}
		return categoryList;
	}

	
}
