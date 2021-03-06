package com.hach.dataextraction.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.json.JSONException;
import org.json.JSONObject;

import com.hach.dataextraction.dao.AttachmentsTransactionDAO;
import com.hach.dataextraction.dao.TransactionDAO;
import com.hach.dataextraction.utils.common.ApplicationProperties;
import com.hach.dataextraction.utils.common.Utilities;
import com.hach.dataextraction.vo.AttachmentDetails;
import com.hach.dataextraction.vo.AttachmentTypes;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class FetchAttachmentsDataImpl {

	private Logger logger = Logger.getLogger(FetchAttachmentsDataImpl.class.getName());

	private AttachmentsTransactionDAO attachmentDAO = null;

	private String			authenticationToken				= null;
	private String			userToken						= null;

	private List<AttachmentDetails> transactionList = null;

	public static void main(String[] args) {
		// initialize loggers
		File f = new File(FetchAttachmentsDataImpl.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		PropertyConfigurator.configure(f.getParentFile().getAbsolutePath()+"/log.properties");
		f=  null;
		try
		{
			FetchAttachmentsDataImpl impl = new FetchAttachmentsDataImpl();
//			String[] locales="en_US,de_DE,en_GB,it_IT,fr_FR,es_ES,pt_PT".split(",");
			String[] locales="es_ES".split(",");
//			String[] attachmentTypes=(AttachmentTypes.ATT_TYPE_DOCUMENT_ATTACHMENT+","+AttachmentTypes.ATT_TYPE_INLINE_LINK_ATTACHMENT+","+AttachmentTypes.ATT_TYPE_INLINE_IMG_ATTACHMENT).split(",");
			String[] attachmentTypes=(AttachmentTypes.ATT_TYPE_DOCUMENT_ATTACHMENT).split(",");
			TransactionDAO dao  =new TransactionDAO();
//			List<String> channelList = dao.getChannelDetails();
			List<String> channelList = new ArrayList<String>();
			channelList.add("REACTIVOS_OTHER");
			channelList.add("TECHNICAL_QA");
			if(null!=channelList && channelList.size()>0)
			{
				for(int a=0;a<channelList.size();a++)
				{
					for(int b=0;b<locales.length;b++)
					{
						for(int c=0;c<attachmentTypes.length;c++)
						{
							impl.startFetchOperation(channelList.get(a), locales[b], attachmentTypes[c]);
						}
					}
				}
			}
			channelList  =null;
			dao = null;

			//			impl.startFetchOperation("commercial_qa".toUpperCase(),"en_US","DOCUMENT");

			if(null!=impl.transactionList && impl.transactionList.size()>0)
			{
				/*
				 * GENERATE FAILURE REPORT
				 */
				impl.printReports();
			}
			impl.transactionList = null;
			impl = null;
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(FetchAttachmentsDataImpl.class.getName(), "main()", e);
		}
	}

	private void startFetchOperation(String channelRefKey, String locale, String attachmentType)
	{
		logger.info("startFetchOperation :: ###############################################################################");
		logger.info("startFetchOperation :: Start Processing For :: > "+ channelRefKey+" / "+ locale+" / "+ attachmentType);
		try
		{
			/*
			 * FETCH ATTACHMENTS LIST AND THEN FETCH ALL OF THEM ONE BY ONE
			 */
			attachmentDAO = new AttachmentsTransactionDAO();
			List<AttachmentDetails> attachmentsList = attachmentDAO.getAttachmentsList(channelRefKey, locale, attachmentType);
			if(null!=attachmentsList && attachmentsList.size()>0)
			{
				AttachmentDetails details = null;
				String authenticationToken=null;
				String userToken=null;
				InputStream inputStream = null;
				HttpURLConnection connection = null;
				String filePath = null;
				File targetFile = null;
				OutputStream outStream = null;
				byte[] buffer = null;
				for(int a=0;a<attachmentsList.size();a++)
				{
					details = (AttachmentDetails)attachmentsList.get(a);
					if(details.getAttachmentType().equals(AttachmentTypes.ATT_TYPE_DOCUMENT_ATTACHMENT))
					{
						// DOCUMENT ATTACHMENTS
						if(null!=details.getKaResourcePath() && !"".equals(details.getKaResourcePath()))
						{
							try
							{
								filePath=  ApplicationProperties.getProperty("attachments.dir.path")+channelRefKey;
								// create channel directory
								targetFile = new File(filePath);
								if(!targetFile.exists() || !targetFile.isDirectory())
								{
									// create dir
									targetFile.mkdir();
								}
								targetFile = null;
								// create locale directory
								filePath+="/"+locale;
								targetFile = new File(filePath);
								if(!targetFile.exists() || !targetFile.isDirectory())
								{
									// create dir
									targetFile.mkdir();
								}
								targetFile = null;

								// create docId directory
								filePath+="/"+details.getDocumentId();
								targetFile = new File(filePath);
								if(!targetFile.exists() || !targetFile.isDirectory())
								{
									// create dir
									targetFile.mkdir();
								}
								targetFile = null;

								// encode URL
//								details.setKaResourcePath(details.getKaResourcePath().replace(" ", "+"));
								// START FETCHING FILE
								authenticationToken = getAuthenticationToken();
								userToken = getUserToken(authenticationToken);
								JSONObject kmAuthTokenObject = new JSONObject();
								kmAuthTokenObject.put("siteName", ApplicationProperties.getProperty("contentsitename"));
								kmAuthTokenObject.put("integrationUserToken", authenticationToken);
								kmAuthTokenObject.put("locale", locale);
								kmAuthTokenObject.put("userToken", userToken);

								logger.info("startFetchOperation :: KM Auth Token :: > "+ kmAuthTokenObject.toString());
								String temp = details.getKaResourcePath().substring(0, details.getKaResourcePath().lastIndexOf("/"));
								String name = details.getKaResourcePath().substring(details.getKaResourcePath().lastIndexOf("/")+1, details.getKaResourcePath().length());
								name = name.replace(" ", "%20");
//								String uri = temp+"/"+URLEncoder.encode(name);
								String url = temp+"/"+name;
								// INVOKE SERVICE
								connection = (HttpURLConnection)new URL(url).openConnection();
//								connection.setDoOutput(true); // Triggers POST.
								connection.setRequestProperty("Content-Type", "application/octet-stream");
//								connection.setRequestProperty("Accept", "application/octet-stream");
								connection.setRequestProperty("kmauthtoken", kmAuthTokenObject.toString());
								
								int responseCode = connection.getResponseCode();
								System.out.println(" ------------ responseCode :: "+ responseCode);
								
								inputStream = connection.getInputStream();
								
								
								targetFile = new File(filePath+"/"+details.getAttachmentName());
								outStream = new FileOutputStream(targetFile);
								buffer = new byte[8 * 1024];
								int bytesRead;
								while ((bytesRead = inputStream.read(buffer)) != -1) {
									outStream.write(buffer, 0, bytesRead);
								}
								outStream.close();outStream=null;
								logger.info("startFetchOperation :: Attachment :: "+ details.getAttachmentName()+". Written Successfully at Path :: >"+ filePath);
								inputStream.close();
								inputStream = null;

								// set status as Success
								details.setStatus("SUCCESS");
								kmAuthTokenObject = null;
								authenticationToken = null;
								userToken=  null;
								targetFile = null;
								connection = null;
								filePath=  null;
								
							}
							catch(Exception e)
							{
								Utilities.printStackTraceToLogs(FetchAttachmentsDataImpl.class.getName(), "startFetchOperation()", e);
								Writer writer = new StringWriter();
								PrintWriter print = new PrintWriter(writer);
								e.printStackTrace(print);
								details.setStatus("FAILURE");
								details.setErrorMessage(writer.toString());
//								details.setErrorMessage("Failed to Fetch File Details :: >"+ e.getMessage()+".\n"+writer.toString());
								
								logger.info("startFetchOperation :: Failed to fetch file data for :: "+ details.getAttachmentName()+".");
								// set writer & print to null
								writer = null;
								// set print to null
								print = null;
							}
						}
						else
						{
							details.setStatus("FAILURE");
							details.setErrorMessage("KA Resource Path is NULL.");
						}
					}
					
					/*
					 * call function to update status
					 */
					attachmentDAO.updateAttachmentsStatusDetails(details, channelRefKey);
					
					
					// add to transactionList
					if(null==transactionList || transactionList.size()<=0)
					{
						transactionList= new ArrayList<AttachmentDetails>();
					}
					transactionList.add(details);
					
					details = null;
				}
			}
			else
			{
				logger.info("startFetchOperation :: No Attachments Found. Exiting for >> "+channelRefKey+" / "+ locale+" / "+ attachmentType);
			}
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(FetchAttachmentsDataImpl.class.getName(),"startFetchOperation()", e);
		}
		logger.info("startFetchOperation :: End Processing For :: > "+ channelRefKey+" / "+ locale+" / "+ attachmentType);
		logger.info("startFetchOperation :: ###############################################################################");
	}


	/**
	 * Gets the authentication token from KA.
	 * 
	 * @return
	 * @throws JSONException
	 */
	private String getAuthenticationToken() throws JSONException
	{
		if (this.authenticationToken != null)
			return this.authenticationToken;
		logger.info("Getting authentication token");

		JSONObject kmAuthTokenObject = new JSONObject();

		kmAuthTokenObject.put("siteName", ApplicationProperties.getProperty("sitename"));
		kmAuthTokenObject.put("localeId", ApplicationProperties.getProperty("kaLocale"));

		JSONObject payloadJSON = new JSONObject();

		payloadJSON.put("login", ApplicationProperties.getProperty("kaApiUsername"));
		payloadJSON.put("password", ApplicationProperties.getProperty("kaApiPassword"));
		payloadJSON.put("siteName", ApplicationProperties.getProperty("sitename"));

		try
		{
			String charset = "UTF-8";

			String url = ApplicationProperties.getProperty("authenticationURL");

			// System.out.println("url = " + url);
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

			connection.setDoOutput(true); // Triggers POST.
			connection.setRequestProperty("Accept-Charset", charset);
			connection.setRequestProperty("Content-Type", "application/json;charset=" + charset);

			connection.setRequestProperty("accept", "application/json");

			// Response response = client.target(kaConfigurationManager.getAuthenticationURL())
			// .request().header("accept", "application/json")
			// .header("content-type", "application/json")
			// .header("kmauthtoken", kmAuthTokenObject.toString())
			// .post(Entity.json(payloadJSON.toString()));

			// write the payload
			OutputStream output = connection.getOutputStream();
			output.write(payloadJSON.toString().getBytes(charset));

			int responseCode = connection.getResponseCode();
			// System.out.println("response code : " + responseCode);

			if (responseCode != 200)
			{
				System.out.println("response code : " + responseCode);
				logger.info("getAuthenticationToken :: response code : " + responseCode);
				InputStream errorStream = connection.getErrorStream();
				String error = Utilities.toString(errorStream);
				logger.info("getAuthenticationToken :: error :: >"+ error);
				error=null;
				errorStream.close();
				errorStream = null;
			}
			else
			{
				// read the response
				InputStream inputStream = connection.getInputStream();
				StringBuilder sb = new StringBuilder();
				while (inputStream.available() > 0)
					sb.append((char) inputStream.read());

				String response = sb.toString();
				JSONObject responseJSON = new JSONObject(response);
				String authenticationToken = responseJSON.getString("authenticationToken");
				responseJSON = null;
				sb = null;
				inputStream.close();
				inputStream = null;
				this.authenticationToken = authenticationToken;
				authenticationToken = null;
			}


			output.close();
			output=  null;
			connection = null;
			url = null;
			kmAuthTokenObject =null;
			charset=null;
			payloadJSON = null;

			return authenticationToken;
		}
		catch (MalformedURLException e)
		{
			Utilities.printStackTraceToLogs(FetchAttachmentsDataImpl.class.getName(), "getAuthenticationToken()", e);
		}
		catch (IOException e)
		{
			Utilities.printStackTraceToLogs(FetchAttachmentsDataImpl.class.getName(), "getAuthenticationToken()", e);
		}

		return null;
	}

	/**
	 * Gets the user token from KA given the authentication token.
	 * 
	 * @param authenticationToken
	 * @return
	 * @throws JSONException
	 */
	private String getUserToken(String authenticationToken) throws JSONException
	{
		if (this.userToken != null)
			return this.userToken;
		logger.info("Getting user token");
		String payload =
				String.format("userName=%s&password=%s&siteName=%s&userExternalType=ACCOUNT",
						ApplicationProperties.getProperty("accountUsername"), ApplicationProperties.getProperty("accountPassword"),
						ApplicationProperties.getProperty("sitename"));
		JSONObject kmAuthTokenObject = new JSONObject();

		kmAuthTokenObject.put("siteName", ApplicationProperties.getProperty("sitename"));
		kmAuthTokenObject.put("localeId", ApplicationProperties.getProperty("kaLocale"));
		kmAuthTokenObject.put("integrationUserToken", authenticationToken);

		try
		{
			String charset = "UTF-8";
			HttpURLConnection connection = (HttpURLConnection) new URL(ApplicationProperties.getProperty("authorizationURL")).openConnection();
			connection.setDoOutput(true); // Triggers POST.
			connection.setRequestProperty("Accept-Charset", charset);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("accept", "application/json");
			connection.setRequestProperty("kmauthtoken", kmAuthTokenObject.toString());

			// Response response = client.target(kaConfigurationManager.getAuthorizationURL())
			// .request().header("accept", "application/json")
			// .header("kmauthtoken", kmAuthTokenObject.toString()).post(Entity.json(payload));

			OutputStream output = connection.getOutputStream();
			output.write(payload.toString().getBytes(charset));

			int responseCode = connection.getResponseCode();
			// System.out.println("response code : " + responseCode);

			if (responseCode != 200)
			{
				InputStream errorStream = connection.getErrorStream();
				String error = Utilities.toString(errorStream);
				logger.info("getUserToken :: error :: >"+ error);
				error=null;
				errorStream.close();
				errorStream = null;
			}
			else
			{
				// read the response
				InputStream inputStream = connection.getInputStream();

				StringBuilder sb = new StringBuilder();
				while (inputStream.available() > 0)
					sb.append((char) inputStream.read());

				String response = sb.toString();

				JSONObject responseJSON = new JSONObject(response);
				String authObjStr = responseJSON.getString("authenticationToken");
				JSONObject authObj = new JSONObject(authObjStr);
				String userToken = authObj.getString("userToken");
				logger.info("getUserToken :: User Token :: >" + userToken);
				this.userToken = userToken;

				userToken = null;
				authObj = null;
				authObjStr=null;
				response = null;
				inputStream.close();
				inputStream = null;
				responseJSON = null;
			}
			output.close();
			output= null;
			connection = null;
			charset = null;
			kmAuthTokenObject=null;
			payload =null;
			return userToken;
		}
		catch (MalformedURLException e)
		{
			Utilities.printStackTraceToLogs(FetchAttachmentsDataImpl.class.getName(),"getUserToken()" , e);
		}
		catch (IOException e)
		{
			Utilities.printStackTraceToLogs(FetchAttachmentsDataImpl.class.getName(),"getUserToken()" , e);
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(FetchAttachmentsDataImpl.class.getName(),"getUserToken()" , e);
		}
		return null;
	}

	private void printReports()
	{
		try
		{
			logger.info("printReports :: Failure List : >"+ transactionList.size());
			if(null!=transactionList && transactionList.size()>0)
			{
				String path = ApplicationProperties.getProperty("attachments.dir.path");
				String fName = "/ATTACHMENT_FAILURE_REPORT.xlsx";

				File myFile = new File(path + fName);
				fName = null;
				// Create the workbook instance for XLSX file, KEEP 100 ROWS IN MEMMORY AND RET ON DISK
				@SuppressWarnings("resource")
				SXSSFWorkbook myWorkBook = new SXSSFWorkbook(100);

				// Create a new sheet
				Sheet mySheet = myWorkBook.createSheet("Failure_Details");
				/*
				 * Add Header Row
				 */
				Row headerRow = mySheet.createRow(0);
				Cell headerCell = null;

				String headers="CHANNEL,DOCUMENT_ID,LOCALE,ATTACHMENT_NAME,KA_RESOURCE_URL,SOURCE_URL,ATTACHMENT_TYPE,ERROR_MESSAGE";
				String[] tokens=headers.split(",");
				if(null!=tokens && tokens.length>0)
				{
					for(int a=0;a<tokens.length;a++)
					{
						headerCell = headerRow.createCell(a);
						headerCell.setCellValue(tokens[a].replace("_", " "));
						headerCell  = null;
					}
				}
				tokens = null;
				headerCell=null;
				headerRow=null;
				headers=null;

				int rowCount = 0;
				/*
				 * GENERATE MULTIPLE ROWS SUCH THAT
				 */
				String dataRow="";
				Row row=null;
				Cell dataCell=null;

				AttachmentDetails details = null;
				for(int a=0;a<transactionList.size();a++)
				{
					details = (AttachmentDetails)transactionList.get(a);
					dataRow=details.getChannelRefKey()+"<TOK_SEPARATOR>"+details.getDocumentId()+"<TOK_SEPARATOR>"+details.getLocale()+"<TOK_SEPARATOR>";
					dataRow+=details.getAttachmentName()+"<TOK_SEPARATOR>"+details.getKaResourcePath()+"<TOK_SEPARATOR>";
					dataRow+=details.getSourcePath()+"<TOK_SEPARATOR>"+details.getAttachmentType()+"<TOK_SEPARATOR>";
					dataRow+=details.getErrorMessage();


					// increment rowCount by 1
					rowCount++;
					// Create a new Row
					row = mySheet.createRow(rowCount);
					tokens = dataRow.split("<TOK_SEPARATOR>");
					if(null!=tokens && tokens.length>0)
					{
						for(int e=0;e<tokens.length;e++)
						{
							dataCell = row.createCell(e);
							dataCell.setCellValue("");
							if(null!=tokens[e] && !"".equals(tokens[e]) && !"null".equals(tokens[e].trim().toLowerCase()))
							{
								dataCell.setCellValue(tokens[e].trim());
							}
							dataCell =null;
						}
					}
					tokens = null;
					row=null;
					dataRow = null;
					dataCell = null;

					details = null;
				}

				headerRow = null;

				FileOutputStream os = new FileOutputStream(myFile);
				myWorkBook.write(os);
				logger.info("Writing on REPORT XLSX file Finished ...");
				os.flush();
				os.close();

				// set mySheet to null
				mySheet = null;
				// set myWorkBook to null
				myWorkBook = null;
				// set path to null
				path = null;
				// set myFile to null
				myFile = null;
			}
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(FetchAttachmentsDataImpl.class.getName(),"printReports()" , e);
		}
	}

}
