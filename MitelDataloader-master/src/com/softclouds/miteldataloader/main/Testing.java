package com.softclouds.miteldataloader.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

public class Testing {

	public static void main(String[] args) {
		
		try
		{

			String charset = "UTF-8";

			URLConnection connection =	new URL("https://hachcompany--tst2-as.custhelp.com/fas/resources/hachcompany__tst2/content/draft/1011003777d5d0016d2daadf9802a65/002234124d25c0175c5796f5e007950/Firmware.zip").openConnection();
			connection.setDoOutput(true); // Triggers POST.
			connection.setRequestProperty("Content-Type", "application/octet-stream");
//			connection.setRequestProperty("accept", "application/json");
			connection.setRequestProperty("kmauthtoken", "{\"siteName\":\"hachcompany__tst2\",\"localeId\":\"en_US\",\"userToken\":\"iMgu+8G6cs5S3hi0PEU7kBFltAWJCOfLIhFUR/G2YcClOy65jXJlnw5XMI4gwWDO1YJqH95Z+zxKjX+fF5AGxpIXoPnwrAHfJ0ZrG6o+A5iBEg4BXWWCsaz3Z/x/f/FGTUc5bQl7noos/628j8LukQ==\",\"integrationUserToken\":\"aGFjaGNvbXBhbnlfX3RzdDI6WldHSlJRRnpWek5TTG1RY1hZTmNnajNsK2R6M3o3VExIOWhVa1RiSnRob3BpWHlGQjdXSGxlankwWjM3NFMrTEc2aGJHZ0NCSkJQSjNBUk51NllJUC9vU05xNXk1NWtheFE4NTliQzBERFovTlVTamFBY1l0NGIyUHlPTzMyOXJTTzl2SjA2a3ltVWIycy9oeHhuTWRlOFl2N3N6Q1V6ZnhqdXRDTVJPNklFPQ\"}");

			// Response response = client.target(kaConfigurationManager.getAuthorizationURL())
			// .request().header("accept", "application/json")
			// .header("kmauthtoken", kmAuthTokenObject.toString()).post(Entity.json(payload));

//			OutputStream output = connection.getOutputStream();
//
//			output.write(payload.toString().getBytes(charset));

			InputStream inputStream = connection.getInputStream();
			File targetFile = new File("D:/Firmware.zip");
		    OutputStream outStream = new FileOutputStream(targetFile);
		    byte[] buffer = new byte[8 * 1024];
		    int bytesRead;
		    while ((bytesRead = inputStream.read(buffer)) != -1) {
		        outStream.write(buffer, 0, bytesRead);
		    }
			
		    inputStream.close();
		    inputStream = null;
		    outStream.close();outStream=null;
			
			

		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
