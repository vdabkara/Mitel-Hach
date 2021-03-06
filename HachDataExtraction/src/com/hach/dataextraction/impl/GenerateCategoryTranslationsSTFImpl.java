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

import com.hach.dataextraction.utils.common.DatabaseConnector;
import com.hach.dataextraction.utils.common.Utilities;
import com.hach.dataextraction.vo.CategoryDetails;

public class GenerateCategoryTranslationsSTFImpl {

	private static Logger logger = Logger.getLogger(GenerateCategoryTranslationsSTFImpl.class);

	public static void main(String[] args) {
		// initialize loggers
		File f = new File(GenerateCategoryTranslationsSTFImpl.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		PropertyConfigurator.configure(f.getParentFile().getAbsolutePath()+"/log.properties");
		f=  null;

		try
		{
			String locale="de_DE";
			List<CategoryDetails> categoryList = new ArrayList<CategoryDetails>();
			List<CategoryDetails> tempList = new ArrayList<CategoryDetails>();
			for(int a=0;a<5;a++)
			{
				tempList = getCategoryData(locale, String.valueOf(a+1));
				if(null!=tempList && tempList.size()>0)
				{
					categoryList.addAll(tempList);
				}
				tempList = null;
			}

			logger.info("-------- total categories found are :: >"+ categoryList.size());
			
			if(null!=categoryList && categoryList.size()>0)
			{
				CategoryDetails catDetails = null;
				StringBuilder str = new StringBuilder();
				str.append("Language code:	"+locale);
				str.append(System.getProperty("line.separator"));
				str.append("Type:	Bilingual");
				str.append(System.getProperty("line.separator"));
				str.append("Translation type:	Metadata");
				str.append(System.getProperty("line.separator"));
				str.append(System.getProperty("line.separator"));
				str.append("------------------TRANSLATED-------------------");
				str.append(System.getProperty("line.separator"));
				str.append(System.getProperty("line.separator"));
				
				str.append("# KEY	LABEL	TRANSLATION	OUT OF DATE");
				str.append(System.getProperty("line.separator"));
				str.append(System.getProperty("line.separator"));
				for(int a=0;a<categoryList.size();a++)
				{
					catDetails=  (CategoryDetails)categoryList.get(a);
					str.append("DataCategory.Product and Categories."+catDetails.getRefKey()+"	"+catDetails.getEnglishName()+"	"+catDetails.getName()+"	-");
					str.append(System.getProperty("line.separator"));
					catDetails=  null;
				}
				str.append(System.getProperty("line.separator"));
				
				FileOutputStream fos = new FileOutputStream(new File("D:\\HACH_WD\\Translations\\Bilingual_"+locale+".stf"));
				fos.write(str.toString().getBytes());
				fos.flush();
				fos.close();
				fos = null;
				str = null;
			}
			categoryList = null;
			
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(GenerateCategoryTranslationsSTFImpl.class.getName(), "main()", e);
		}
	}


	private static List<CategoryDetails> getCategoryData(String locale,String level)
	{
		List<CategoryDetails> categoryList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try
		{
			CategoryDetails catDetails = null;
			conn = DatabaseConnector.getConnection();
			String sql="SELECT DISTINCT A.LEVEL_"+level+"_REF_KEY AS REF_KEY,A.LEVEL_"+level+"_NAME AS NAME,B.LEVEL_"+level+"_NAME AS ENG_NAME FROM "
					+ "im_product_category_master_"+locale.trim().toLowerCase()+" A, im_product_category_master_en_us B "
					+ "WHERE A.LEVEL_"+level+"_REF_KEY IS NOT NULL AND A.LEVEL_"+level+"_REF_KEY=B.LEVEL_"+level+"_REF_KEY  "
							+ "GROUP BY A.LEVEL_"+level+"_REF_KEY,A.LEVEL_"+level+"_NAME ";
			logger.info("getCategoryData :: Sql  For Level "+level+" :: >"+ sql);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				catDetails=  new CategoryDetails();
				catDetails.setName(rs.getString("NAME"));
				if(null!=catDetails.getName() && catDetails.getName().length()>40)
				{
					catDetails.setName(catDetails.getName().substring(0,39));
				}
				catDetails.setRefKey(rs.getString("REF_KEY"));
				catDetails.setEnglishName(rs.getString("ENG_NAME"));
				if(null!=catDetails.getEnglishName() && catDetails.getEnglishName().length()>40)
				{
					catDetails.setEnglishName(catDetails.getEnglishName().substring(0,39));
				}
				if(null==categoryList || categoryList.size()<=0)
				{
					categoryList = new ArrayList<CategoryDetails>();
				}
				categoryList.add(catDetails);
				catDetails=null;
			}
			sql = null;

			if(null!=categoryList && categoryList.size()>0)
			{
				logger.info("getCategoryData :: Total Categories Found  For Level "+level+" :: >"+ categoryList.size());
			}
			else
			{
				logger.info("getCategoryData :: No Categories Found  For Level "+level+".");
			}
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(GenerateCategoryTranslationsSTFImpl.class.getName(), "getCategoryData()", e);
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
				Utilities.printStackTraceToLogs(GenerateCategoryTranslationsSTFImpl.class.getName(), "getCategoryData()", e);
			}
			pstmt=null;
			rs=null;
			conn=null;
		}
		return categoryList;
	}

}
