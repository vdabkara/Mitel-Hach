package com.hach.dataextraction.impl;

import java.io.File;
import java.io.FileInputStream;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;
import org.json.JSONObject;

import com.hach.dataextraction.dao.AttachmentsTransactionDAO;
import com.hach.dataextraction.dao.TransactionDAO;
import com.hach.dataextraction.utils.common.ApplicationProperties;
import com.hach.dataextraction.utils.common.Utilities;
import com.hach.dataextraction.vo.AttachmentDetails;
import com.hach.dataextraction.vo.AttachmentTypes;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class FetchInlineImagesDataImpl {

	private Logger logger = Logger.getLogger(FetchInlineImagesDataImpl.class.getName());

	private AttachmentsTransactionDAO attachmentDAO = null;

	private String			authenticationToken				= null;
	private String			userToken						= null;

	private List<AttachmentDetails> transactionList = null;

	public static void main(String[] args) {
		// initialize loggers
		File f = new File(FetchInlineImagesDataImpl.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		PropertyConfigurator.configure(f.getParentFile().getAbsolutePath()+"/log.properties");
		f=  null;
		try
		{
			FetchInlineImagesDataImpl impl = new FetchInlineImagesDataImpl();
			impl.startFetchOperation();
			impl = null;
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(FetchInlineImagesDataImpl.class.getName(), "main()", e);
		}
	}

	private void startFetchOperation()
	{
		logger.info("startFetchOperation :: ###############################################################################");
		try
		{
			Random random = new Random();
			/*
			 * FETCH ATTACHMENTS LIST AND THEN FETCH ALL OF THEM ONE BY ONE
			 */
			attachmentDAO = new AttachmentsTransactionDAO();
			List<AttachmentDetails> attachmentsList = readSourceData();
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
				String attachmentName=null;
				String extension=null;
				for(int a=0;a<attachmentsList.size();a++)
				{
					details = (AttachmentDetails)attachmentsList.get(a);
//					if(details.getAttachmentType().equals(AttachmentTypes.ATT_TYPE_DOCUMENT_ATTACHMENT))
					{
						// DOCUMENT ATTACHMENTS
						if(null!=details.getSourcePath() && !"".equals(details.getSourcePath()))
						{
							try
							{
								filePath=  ApplicationProperties.getProperty("attachments.inline.images.dir.path")+details.getChannelRefKey();
								// create channel directory
								targetFile = new File(filePath);
								if(!targetFile.exists() || !targetFile.isDirectory())
								{
									// create dir
									targetFile.mkdir();
								}
								targetFile = null;
								// create locale directory
								filePath+="/"+details.getLocale();
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

//								details.setKaResourcePath(details.getKaResourcePath().replace(" ", "%20"));
								// START FETCHING FILE
								authenticationToken = getAuthenticationToken();
								userToken = getUserToken(authenticationToken);
								JSONObject kmAuthTokenObject = new JSONObject();
								kmAuthTokenObject.put("siteName", ApplicationProperties.getProperty("contentsitename"));
								kmAuthTokenObject.put("integrationUserToken", authenticationToken);
								kmAuthTokenObject.put("locale", details.getLocale());
								kmAuthTokenObject.put("userToken", userToken);

								logger.info("startFetchOperation :: KM Auth Token :: > "+ kmAuthTokenObject.toString());
								
								
								// INVOKE SERVICE
								connection = (HttpURLConnection)new URL(details.getSourcePath()).openConnection();
//								connection.setDoOutput(true); // Triggers POST.
								connection.setRequestProperty("Content-Type", "application/octet-stream");
//								connection.setRequestProperty("Accept", "application/octet-stream");
								connection.setRequestProperty("kmauthtoken", kmAuthTokenObject.toString());
								
								int responseCode = connection.getResponseCode();
								System.out.println(" ------------ responseCode :: "+ responseCode);
								
								inputStream = connection.getInputStream();
								if(null!=connection.getContentType())
								{
									if(connection.getContentType().lastIndexOf("/")!=-1)
									{
										extension=  connection.getContentType().substring(connection.getContentType().lastIndexOf("/")+1, connection.getContentType().length());
									}
								}
								//get all headers
								Map<String, List<String>> map = connection.getHeaderFields();
								for (Map.Entry<String, List<String>> entry : map.entrySet()) {
//									logger.info("Key : " + entry.getKey() + " ,Value : " + entry.getValue());
									if(null!=entry.getKey() && entry.getKey().toLowerCase().equals("content-disposition"))
									{
										if(null!=entry.getValue() && entry.getValue().size()>0)
										{
											for(String val: entry.getValue())
											{
												if(null!=val)
												{
													// check for fileName
													if(val.indexOf("filename=\"")!=-1)
													{
														attachmentName = val.substring(val.indexOf("filename=\"")+11, val.length()-1);
													}
												}
												val = null;
											}
										}
									}
								}
								
								if(null==attachmentName || "".equals(attachmentName))
								{
									attachmentName = details.getDocumentId()+"_"+details.getLocale()+"_file_"+random.nextInt(10000)+"."+extension;
								}
								logger.info("------------- att name ::> "+ attachmentName);
								// set Attachment Name
								details.setAttachmentName(attachmentName);
								
								targetFile = new File(filePath+"/"+attachmentName);
								outStream = new FileOutputStream(targetFile);
								buffer = new byte[8 * 1024];
								int bytesRead;
								while ((bytesRead = inputStream.read(buffer)) != -1) {
									outStream.write(buffer, 0, bytesRead);
								}
								outStream.close();outStream=null;
								logger.info("startFetchOperation :: Attachment :: "+ attachmentName+". Written Successfully at Path :: >"+ filePath);
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
								extension  =null;
								attachmentName = null;
							}
							catch(Exception e)
							{
								Utilities.printStackTraceToLogs(FetchInlineImagesDataImpl.class.getName(), "startFetchOperation()", e);
								Writer writer = new StringWriter();
								PrintWriter print = new PrintWriter(writer);
								e.printStackTrace(print);
								details.setStatus("FAILURE");
								details.setErrorMessage(writer.toString());
//								details.setErrorMessage("Failed to Fetch File Details :: >"+ e.getMessage()+".\n"+writer.toString());
								
								logger.info("startFetchOperation :: Failed to fetch file data for :: "+ details.getSourcePath()+".");
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
//					attachmentDAO.updateAttachmentsStatusDetails(details, channelRefKey);
					
					
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
				logger.info("startFetchOperation :: No Attachments Found. ");
			}
			

			
			if(null!=transactionList)
			{
				logger.info("--------------- transactionsList :: >"+ transactionList.size());
				printReports();
			}
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(FetchInlineImagesDataImpl.class.getName(),"startFetchOperation()", e);
		}
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
			Utilities.printStackTraceToLogs(FetchInlineImagesDataImpl.class.getName(), "getAuthenticationToken()", e);
		}
		catch (IOException e)
		{
			Utilities.printStackTraceToLogs(FetchInlineImagesDataImpl.class.getName(), "getAuthenticationToken()", e);
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
			Utilities.printStackTraceToLogs(FetchInlineImagesDataImpl.class.getName(),"getUserToken()" , e);
		}
		catch (IOException e)
		{
			Utilities.printStackTraceToLogs(FetchInlineImagesDataImpl.class.getName(),"getUserToken()" , e);
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(FetchInlineImagesDataImpl.class.getName(),"getUserToken()" , e);
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
				String path = ApplicationProperties.getProperty("attachments.inline.images.dir.path");
				String fName = "/ATTACHMENT_INLINE_IMAGES_FAILURE_REPORT.xlsx";

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

				String headers="CHANNEL,DOCUMENT_ID,LOCALE,ATTACHMENT_NAME,SOURCE_URL,STATUS,ERROR_MESSAGE";
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
					dataRow+=details.getAttachmentName()+"<TOK_SEPARATOR>"+details.getSourcePath()+"<TOK_SEPARATOR>";
					dataRow+=details.getStatus()+"<TOK_SEPARATOR>"+details.getErrorMessage();


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
				logger.info("Writing on FAILURE REPORT XLSX file Finished ...");
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
			Utilities.printStackTraceToLogs(FetchInlineImagesDataImpl.class.getName(),"printReports()" , e);
		}
	}

	@SuppressWarnings("resource")
	private List<AttachmentDetails> readSourceData()
	{
		List<AttachmentDetails> list = null;
		try
		{
			InputStream is = new FileInputStream(new File("D:\\HACH_WD\\PROD_DELTA_29_MAY_2022\\REPORTS\\INLINE_IMAGES_REPORT.xlsx"));
			
			XSSFWorkbook workbook  = null;
			XSSFSheet sheet = null;
			Iterator<Row> rowIterator = null;
			
			//Create Workbook instance holding reference to .xlsx file
			workbook = new XSSFWorkbook(is);
			//Get first/desired sheet from the workbook
			sheet = workbook.getSheetAt(0);
			//Iterate through each rows one by one
			rowIterator = sheet.iterator();
			long rowCount=0;
			
			AttachmentDetails details = null;
			Object dataCell = null;
			String imageToBeRead = null;
			while(null!=rowIterator && rowIterator.hasNext())
			{
				Row row = rowIterator.next();
				if(rowCount>0)
				{
					details = new AttachmentDetails();
					dataCell = Utilities.readCellValue(row.getCell(0));
					if(null!=dataCell && !"".equals(dataCell))
					{
						details.setChannelRefKey(String.valueOf(dataCell).trim());
					}
					dataCell = null;

					dataCell = Utilities.readCellValue(row.getCell(1));
					if(null!=dataCell && !"".equals(dataCell))
					{
						details.setDocumentId(String.valueOf(dataCell).trim());
					}	
					dataCell = null;

					dataCell = Utilities.readCellValue(row.getCell(2));
					if(null!=dataCell && !"".equals(dataCell))
					{
						details.setLocale(String.valueOf(dataCell).trim());
					}
					dataCell = null;

					dataCell = Utilities.readCellValue(row.getCell(3));
					if(null!=dataCell && !"".equals(dataCell))
					{
						details.setAttachmentType(String.valueOf(dataCell).trim());
					}
					dataCell = null;
					
					dataCell = Utilities.readCellValue(row.getCell(4));
					if(null!=dataCell && !"".equals(dataCell))
					{
						details.setSourcePath(String.valueOf(dataCell).trim());
					}
					dataCell = null;

					dataCell = Utilities.readCellValue(row.getCell(5));
					if(null!=dataCell && !"".equals(dataCell))
					{
						imageToBeRead = String.valueOf(dataCell).trim();
					}
					dataCell = null;
					
					if(null!=imageToBeRead && imageToBeRead.toLowerCase().equals("yes"))
					{
						/*
						 * add details to attachmentsList for fetch
						 */
						if(null==list || list.size()<=0)
						{
							list = new ArrayList<AttachmentDetails>();
						}
						list.add(details);
					}
					imageToBeRead = null;
					details= null;
				}
				// INCREMENT ROW COUNT BY 1
				rowCount++;
				row = null;
			}
			sheet = null;
			workbook = null;
			is.close();
			is = null;
			rowIterator=  null;
		
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(FetchInlineImagesDataImpl.class.getName(),"readSourceData()" , e);
		}
		return list;
	}
	
}
