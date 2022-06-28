package com.hach.dataextraction.impl;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.PropertyConfigurator;

import com.hach.dataextraction.dao.TransactionDAO;
import com.hach.dataextraction.utils.common.DatabaseConnector;
import com.hach.dataextraction.utils.common.Utilities;
import com.hach.dataextraction.vo.CategoryDetails;

public class PopulateCategoriesHierarchyImpl {

	public static void main(String[] args) {
		// initialize loggers
		File f = new File(PopulateCategoriesHierarchyImpl.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		PropertyConfigurator.configure(f.getParentFile().getAbsolutePath()+"/log.properties");
		f=  null;
		try
		{
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Connection conn = DatabaseConnector.getConnection();
			String[] tables="im_category_alerts,im_category_commercial_qa,im_category_danaher_wq_content,im_category_guided_answer,im_category_hach_content,im_category_hexis_other,im_category_market_information,im_category_methods,im_category_podcast,im_category_product_commercialization_,im_category_product_information_bulletin,im_category_reactivos_other,im_category_sales_documents,im_category_self_service,im_category_service_documents,im_category_technical_qa,im_category_termodinamica_other,im_category_troubleshooting,im_category_video".split(",");
			List<CategoryDetails> catList = new ArrayList<CategoryDetails>();
			CategoryDetails catDetails =null;
			TransactionDAO tansDao  = new TransactionDAO();
			for(int a=0;a<tables.length;a++)
			{
				String sql="SELECT DISTINCT REF_KEY FROM "+ tables[a];
				catList = new ArrayList<CategoryDetails>();
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					if(null!=rs.getString("REF_KEY") && !"".equals(rs.getString("REF_KEY")))
					{
						catDetails = new CategoryDetails();
						catDetails.setRefKey(rs.getString("REF_KEY"));
						catDetails.setLocale("en_US");
						catList.add(catDetails);
						catDetails = null;
					}
				}
				pstmt.close();pstmt=null;
				rs.close();rs=null;
				sql=null;
				
				if(null!=catList && catList.size()>0)
				{
					catDetails  =null;
					for(int b=0;b<catList.size();b++)
					{
						catDetails = (CategoryDetails)catList.get(b);
						catDetails=  tansDao.getCategoryHierarchy(catDetails);
						sql="update "+ tables[a]+" SET LEVEL_1_NAME=?,LEVEL_1_REF_KEY=?,LEVEL_2_NAME=?,LEVEL_2_REF_KEY=?,LEVEL_3_NAME=?,LEVEL_3_REF_KEY=?,"
								+ "LEVEL_4_NAME=?,LEVEL_4_REF_KEY=?,LEVEL_5_NAME=?,LEVEL_5_REF_KEY=? WHERE REF_KEY=?";
						pstmt=conn.prepareStatement(sql);
						pstmt.setString(1, catDetails.getLevel1Name());
						pstmt.setString(2, catDetails.getLevel1RefKey());
						pstmt.setString(3, catDetails.getLevel2Name());
						pstmt.setString(4, catDetails.getLevel2RefKey());
						pstmt.setString(5, catDetails.getLevel3Name());
						pstmt.setString(6, catDetails.getLevel3RefKey());
						pstmt.setString(7, catDetails.getLevel4Name());
						pstmt.setString(8, catDetails.getLevel4RefKey());
						pstmt.setString(9, catDetails.getLevel5Name());
						pstmt.setString(10, catDetails.getLevel5RefKey());
						pstmt.setString(11, catDetails.getRefKey());
						pstmt.executeUpdate();
						pstmt.close();pstmt=null;
						sql = null;
					}
				}
			}
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(PopulateCategoriesHierarchyImpl.class.getName(), "main()", e);
		}

	}

}
