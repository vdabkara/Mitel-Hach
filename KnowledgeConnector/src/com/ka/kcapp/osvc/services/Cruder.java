package com.ka.kcapp.osvc.services;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.ka.kcapp.util.StringUtility;
import com.ka.kcapp.util.Table;
import com.ka.kcapp.util.Utilities;

public class Cruder {
	private Logger logger = Logger.getLogger(Cruder.class);
	private ResourceBundle	resourceBundle;
	private final String	endPointAddress;
	private final String	accountUsername;
	private final String	accountPassword;  
	private String soapResponse=null;
	
	public Cruder()
	{
		logger.info("Created new Cruder");
		this.resourceBundle = ResourceBundle.getBundle("resources.application");
		this.endPointAddress = resourceBundle.getString("cruderEndPointAddress");
		this.accountUsername = resourceBundle.getString("accountUsername");
		this.accountPassword = resourceBundle.getString("accountPassword");
		soapResponse=  null;
	}

	public JSONObject create(String payloadBody) throws JSONException
	{
		JSONObject responseJsonobjet = new JSONObject();
		// SET HEADER PAYLOAD
		String payloadHeader = getPayloadHeader("Custom Object Samples", false);
		TreeMap<String, String> header = null;
		try
		{
			header = getMap("content-type", "text/xml;charset=UTF-8", "SOAPAction", "Create");
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(Cruder.class.getName(), "create()", e);
			responseJsonobjet.put("success", false);
		}
		if(null!=header)
		{
			// Prepare SOAP Payload
			String payload="";
			payload +=
					"    <s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:u=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">";
			payload += payloadHeader;
			payload += payloadBody;
			payload += "</s:Envelope>";
			
			/*
			 * call web service 
			 */
			try
			{
				responseJsonobjet = soap(payload, header, responseJsonobjet);
			}
			catch(Exception e)
			{
				Utilities.printStackTraceToLogs(Cruder.class.getName(), "create()", e);
				responseJsonobjet.put("success", false);
			}
		}
		return responseJsonobjet;
	}
	
	public JSONObject fetchUserAccountId(String payloadBody) throws JSONException
	{
		JSONObject responseJsonobjet = new JSONObject();
		responseJsonobjet.put("accountId", "");
		responseJsonobjet.put("fullName", "");
		// SET HEADER PAYLOAD
		String payloadHeader = fetchPayLoadHeader("Basic Object Query");
		TreeMap<String, String> header = null;
		try
		{
			header = getMap("content-type", "text/xml;charset=UTF-8", "SOAPAction", "QueryObjects");
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(Cruder.class.getName(), "fetchUserAccountId()", e);
			responseJsonobjet.put("success", false);
		}
		if(null!=header)
		{
			// Prepare SOAP Payload
			String payload="";
			payload += "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">";
			payload += payloadHeader;
			payload += payloadBody;
			payload += "</soapenv:Envelope>";
			
			/*
			 * call web service 
			 */
			try
			{
				responseJsonobjet = soap(payload, header, responseJsonobjet);
				if(null!=soapResponse && !"".equals(soapResponse))
				{
					Vector<Table> dataTable = getTablesFromXML(soapResponse);
					if(null!=dataTable && dataTable.size()>0)
					{
						// check for the value at row_0
						Table data = dataTable.get(0);
						if(null!=data && data.size()>0)
						{
							Vector<String> idValue = data.getRow("row_0");
							if(null!=idValue && idValue.size()>0)
							{
								// Columns Index Will be - ID,full_name 
								String accId = idValue.get(0).toString();
								String fullName = idValue.get(1).toString();
								responseJsonobjet.put("accountId", accId);
								responseJsonobjet.put("fullName", fullName);
								accId = null;
								fullName=  null;
							}
							idValue = null;
						}
						data = null;
					}
					dataTable=  null;
				}
			}
			catch(Exception e)
			{
				Utilities.printStackTraceToLogs(Cruder.class.getName(), "fetchUserAccountId()", e);
				responseJsonobjet.put("success", false);
			}
		}
		return responseJsonobjet;
	}

	
	/**
	 * Gets a static payload header element used for all these requests
	 * 
	 * @return
	 */
	private String getPayloadHeader(String appID, boolean zeroUnderstand)
	{
		String payload = "";
		payload += "  <s:Header>\n";

		payload +=
				"       <h:ClientInfoHeader xmlns:h=\"urn:messages.ws.rightnow.com/v1_3\" xmlns=\"urn:messages.ws.rightnow.com/v1_3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"";

		if (zeroUnderstand)
			payload += " s:mustUnderstand=\"0\"";

		payload += ">\n";

		payload += "         <AppID>" + appID + "</AppID>\n";
		payload += "    </h:ClientInfoHeader>\n";
		payload +=
				"       <o:Security xmlns:o=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" s:mustUnderstand=\"1\">\n";
		payload +=
				"         <o:UsernameToken u:Id=\"uuid-1d99da22-a3d2-4638-81cd-e6e852198f52-1\">\n";
		payload += "         <o:Username>" + accountUsername + "</o:Username>\n";
		payload += "         <o:Password>" + accountPassword + "</o:Password>\n";
		payload += "      </o:UsernameToken>\n";
		payload += "    </o:Security>\n";
		payload += "  </s:Header>\n";

		return payload;
	}
	
	/**
	 * Get PayLoad Header for Get Requests
	 * @param appId
	 * @return
	 */
	private String fetchPayLoadHeader(String appId)
	{
		String payload="";
		payload += "<soapenv:Header>";
		payload += "<ns7:ClientInfoHeader xmlns:ns7=\"urn:messages.ws.rightnow.com/v1_3\" soapenv:mustUnderstand=\"0\">";
		payload += "<ns7:AppID>"+appId+"</ns7:AppID>";
		payload += "</ns7:ClientInfoHeader>";
		payload += "<wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" mustUnderstand=\"1\">";
		payload += "<wsse:UsernameToken>";
		payload += "<wsse:Username>"+accountUsername+"</wsse:Username>";
		payload += "<wsse:Password>"+accountPassword+"</wsse:Password>";
		payload += "</wsse:UsernameToken>";
		payload += "</wsse:Security>";
		payload += "</soapenv:Header>";

		return payload;
	}

	/**
	 * Returns a map with the given key-value ordered pairs in the map. There must be an even number
	 * of strings given.
	 * 
	 * @param keyValues
	 * @return
	 */
	private TreeMap<String, String> getMap(String... keyValues)
	{
		int length = keyValues.length;

		if (length % 2 != 0)
			throw new IllegalArgumentException(
					"Uneven number of key-value items specified.  Each key must have a value so the number of Strings passed to this must be a multiple of 2.");

		TreeMap<String, String> ret = new TreeMap<String, String>();
		for (int i = 0; i < length / 2; i++)
		{
			String key = keyValues[i * 2 + 0];
			String value = keyValues[i * 2 + 1];
			ret.put(key, value);
		}

		return ret;
	}

	private JSONObject soap(String soapEnvlopePayload, Map<String, String> header, JSONObject responseJSONObject) throws JSONException
	{
		try
		{
			String charset = "UTF-8";
			HttpURLConnection connection = null;
			try
			{
				connection = (HttpURLConnection) new URL(this.endPointAddress).openConnection();
				connection.setDoOutput(true); // Triggers POST.
				connection.setRequestMethod("POST");
				// set the header
				for (String key : header.keySet())
					connection.setRequestProperty(key, header.get(key));

				// write the payload
				OutputStream output = connection.getOutputStream();
				output.write(soapEnvlopePayload.getBytes(charset));
			}
			catch(Exception e)
			{
				Utilities.printStackTraceToLogs(Cruder.class.getName(), "soap()", e);
				responseJSONObject.put("success", false);
			}
			
			if(null!=connection)
			{
				if(connection.getResponseCode()==HttpURLConnection.HTTP_OK)
				{
					logger.info("soap :: WebService Invoked successfully. Response Code :: > " + connection.getResponseCode());
					responseJSONObject.put("success", true);
					// set soapResponse in case if any processing is required to be done.
					try
					{
						if(null!=connection.getInputStream())
						{
							soapResponse = Utilities.convertInputStreamToString(connection.getInputStream());
						}
					}
					catch(Exception e)
					{/* NOTHING TO BE DONE FOR THIS ERROR.*/}
				}
				else
				{
					logger.info("soap :: Failed to Invoke Web Service :: Response Code :: > " + connection.getResponseCode());
					logger.info("soap :: Failed to Invoke Web Service :: Response Message :: > " + connection.getResponseMessage());
					String errorMsg = Utilities.convertInputStreamToString(connection.getErrorStream());
					logger.info("soap :: Failed to Invoke Web Service :: Error :: > " + errorMsg);
					responseJSONObject.put("success", false);
				}
			}
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(Cruder.class.getName(), "soap()", e);
			responseJSONObject.put("success", false);
		}
		return responseJSONObject;
	}
	
	/**
	 * Extracts a list of tables from the returned query object XML.
	 * 
	 * @param xml
	 * @return
	 */
	public Vector<Table> getTablesFromXML(String xml)
	{
		Vector<Table> tables = new Vector<Table>();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try
		{
			dBuilder = dbFactory.newDocumentBuilder();
		}
		catch (ParserConfigurationException e)
		{
			Utilities.printStackTraceToLogs(Cruder.class.getName(), "getTablesFromXML()", e);
			return null;
		}

		InputSource is = new InputSource(new StringReader(xml));
		Document doc;
		try
		{
			doc = dBuilder.parse(is);
		}
		catch (SAXException | IOException e)
		{
			Utilities.printStackTraceToLogs(Cruder.class.getName(), "getTablesFromXML()", e);
			return null;
		}

		if(null!=doc)
		{
			doc.getDocumentElement().normalize();

			Element root = doc.getDocumentElement();
			logger.debug("Root element :" + root);

			NodeList tableNodes = getXMLElement(root, "soapenv:Body", "n0:QueryCSVResponse",
					"n0:CSVTableSet", "n0:CSVTables").getChildNodes();

			
			// logger.debug("found " + tableNodes.getLength() + " tables");
			for (int i = 0; i < tableNodes.getLength(); i++)
			{
				Node tableNode = tableNodes.item(i);

				NodeList tableChildren = tableNode.getChildNodes();

				Node columnNode = tableChildren.item(1);

				String columnData = columnNode.getTextContent();
				Vector<String> columns =
						StringUtility.tokenize(columnData, StringUtility.COMMA_REGEX, true, false);

				Node rowsNode = tableChildren.item(2);

				NodeList rowChildren = rowsNode.getChildNodes();

				Table table = new Table();
				for (int rowNum = 0; rowNum < rowChildren.getLength(); rowNum++)
				{
					Node rowNode = rowChildren.item(rowNum);

					String dataString = rowNode.getTextContent();

					Vector<String> dataValues = StringUtility.tokenizeByCommas(dataString);

					for (int columnNum = 0; columnNum < dataValues.size(); columnNum++)
					{
						String xkey = columns.get(columnNum);
						String ykey = "row_" + rowNum;
						String data = dataValues.get(columnNum);
						table.add(xkey, ykey, data);
					}
				}
				tables.add(table);
			}
		}
		return tables;
	}
	
	private Node getXMLElement(Node docNode, String... nodes)
	{
		if (nodes.length == 0)
			throw new IllegalArgumentException("Need some String nodes.  Got zero.");

		if (nodes.length == 1)
		{
			// logger.debug("gxml1");
			NodeList children = docNode.getChildNodes();

			for (int i = 0; i < children.getLength(); i++)
			{
				Node item = children.item(i);

				String nodeName = item.getNodeName();
				if (nodeName.equals(nodes[0]))
				{
					// logger.debug(" match " + nodeName);
					return item;
				}
				else
				{
					// logger.debug(nodeName + " != " + nodes[0]);
				}
			}

			return null;
		}
		else
		{
			// logger.debug("gxml ");
			NodeList children = docNode.getChildNodes();

			for (int i = 0; i < children.getLength(); i++)
			{
				Node item = children.item(i);

				String nodeName = item.getNodeName();
				if (nodeName.equals(nodes[0]))
				{
					// logger.debug(" match " + nodeName);
					String[] subList = new String[nodes.length - 1];
					for (int j = 0; j < nodes.length - 1; j++)
						subList[j] = nodes[1 + j];

					return getXMLElement(item, subList);
				}
				else
				{
					// logger.debug(nodeName + " != " + nodes[0]);
				}
			}
			return null;
		}
	}


}