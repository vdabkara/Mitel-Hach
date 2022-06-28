package com.hach.dataextraction.impl;

import java.sql.Connection;
import java.sql.Statement;

import com.hach.dataextraction.utils.common.DatabaseConnector;

public class DeleteDocsFromDBImpl {

	public static void main(String[] args) {
		try
		{
			Statement stmt = null;
			Connection conn = DatabaseConnector.getConnection();
			String[] tok = "REACTIVOS_OTHER,TERMODINAMICA_OTHER,HEXIS_OTHER,DANAHER_WQ_CONTENT,HACH_CONTENT,PRODUCT_INFORMATION_BULLETIN,SELF_SERVICE,SALES_DOCUMENTS,COMMERCIAL_QA,PODCAST,PRODUCT_COMMERCIALIZATION_,VIDEO,TROUBLESHOOTING,TECHNICAL_QA,SERVICE_DOCUMENTS,METHODS,ALERTS".split(",");
			for(int a=0;a<tok.length;a++)
			{
				String sql="update im_document_"+tok[a]+" set SF_ARTICLE_NUMBER=null,SF_DOCUMENT_ID=null,SF_LOCALE=null,SF_ARTICLE_ID=null,SF_CHANNEL_ID=null,SF_URL_NAME=null,SF_MASTER_IDENTIFIER=null,SF_DOCUMENT_URL=null,SF_PROCESSING_STATUS=null,SF_REMARKS=null,SF_ERROR_MESSAGE=null,SF_ALL_CATEGORIES_MAPPED=null,SF_ALL_INRLKS_ARTICLES_MAPPED=null";
//				String sql="update im_category_"+tok[a]+" set SF_CAT_ASSOCIATION_ID=NULL,SF_MAPPING_STATUS=NULL,SF_ERROR_MESSAGE=NULL" ; 
				stmt = conn.createStatement();
				stmt.executeUpdate(sql);
				stmt.close();stmt=null;
			}
			conn.close();conn = null;
			System.out.println("----------- finished ------------------");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
