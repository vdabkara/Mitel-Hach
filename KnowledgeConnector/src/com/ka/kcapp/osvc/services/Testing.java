package com.ka.kcapp.osvc.services;

import com.ka.kcapp.util.Utilities;

public class Testing {

	public static void main(String[] args) {

		try
		{
			TestCruder cruder = new TestCruder();
			String payLoad = getPayload();
			cruder.fetchUserAccountId(payLoad);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
	public static String getPayload()
	{
		String payload = "";
		try
		{
			String query="SELECT Basic_Warranty_Exp_Date,Contact,LimitedWarrantyExpirationDate,KppExpDate,LifeCycleStatusCde.Code from CO.Vehicle where LifeCycleStatusCde.Code!='8'";
//			String query="Desc Account";
			payload+="<soapenv:Body>\n";
			payload+="<ns7:QueryCSV xmlns:ns7=\"urn:messages.ws.rightnow.com/v1_3\">\n";
			payload+="    <ns7:Query>"+query+"</ns7:Query>\n";            
			payload+="    <ns7:PageSize>10000</ns7:PageSize>\n";
			payload+=" </ns7:QueryCSV>\n";
			payload+=" </soapenv:Body>\n";
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(Testing.class.getName(), "getPayload()", e);
		}
		return payload;
	}
	
}
