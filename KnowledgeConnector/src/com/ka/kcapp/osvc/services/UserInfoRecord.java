package com.ka.kcapp.osvc.services;

import com.ka.kcapp.util.Utilities;

public class UserInfoRecord {

	public String getUserPayload(String token, String appType)
	{
		String payload = "";
		try
		{
			if(null!=appType && !"".equals(appType) && null!=token && !"".equals(token))
			{
				String columnName="";
				if(appType.trim().toLowerCase().equals("remedy"))
				{
					if(token.contains("@"))
					{
						// COLUMN WILL BE EMAIL ID
						columnName="email_address"; 
					}
					else
					{
						// COLUMN WILL BE ACCT ID
						columnName = "acct_id";
					}
				}
				else if(appType.trim().toLowerCase().equals("teamtrack"))
				{
					// COLUMN WILL BE ACCT ID
					columnName = "acct_id";
				}
				if(null!=columnName && !"".equals(columnName))
				{
					String query="SELECT acct_id, full_name FROM KMS.user_info WHERE "+columnName+" = '"+token+"'";
					payload+="<soapenv:Body>\n";
					payload+="<ns7:QueryCSV xmlns:ns7=\"urn:messages.ws.rightnow.com/v1_3\">\n";
					payload+="    <ns7:Query>"+query+"</ns7:Query>\n";            
					payload+="    <ns7:PageSize>10000</ns7:PageSize>\n";
					payload+=" </ns7:QueryCSV>\n";
					payload+=" </soapenv:Body>\n";
				}
				columnName=  null;
			}
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(UserInfoRecord.class.getName(), "getUserPayload()", e);
		}
		return payload;
	}
}