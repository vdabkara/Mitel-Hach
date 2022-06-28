package com.softclouds.miteldataloader.osvc;

import java.awt.Point;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.softclouds.miteldataloader.dataStructures.Configuration;
import com.softclouds.miteldataloader.utility.StringUtility;
import com.softclouds.miteldataloader.utility.Table;

/**
 * This class implements CRUD operations on OSvC custom tables by calling Syed's custom exposed
 * webservice APIs.
 * 
 * @author Ray
 *
 */
public class Cruder
{
	final private Logger	logger	= Logger.getLogger(Cruder.class);
	// private static final String ENDPOINT_ADDRESS =
	// "https://opn-softclouds4.rightnowdemo.com/cgi-bin/opn_softclouds4.cfg/services/soap";
	// "https://opn-softclouds02-na-ka.rightnowdemo.com/cgi-bin/opn_softclouds4.cfg/services/soap";

	private final String	endPointAddress;

	private final String	accountUsername;
	private final String	accountPassword;

	/**
	 * Constructor creates a new Cruder with the specified user name and password which is an
	 * Account in OSvC.
	 * 
	 * @param username
	 * @param password
	 */
	public Cruder(Configuration configuration)
	{
		logger.debug("Created new Cruder");

		this.endPointAddress = configuration.get("cruderEndPointAddress");
		this.accountUsername = configuration.get("accountUsername");
		this.accountPassword = configuration.get("accountPassword");

	}

	/**
	 * Endpoint Address
	 * https://opn-softclouds4.rightnowdemo.com/cgi-bin/opn_softclouds4.cfg/services/soap
	 * 
	 * Request Header
	 * 
	 * content-type: text/xml;charset=UTF-8
	 * 
	 * SOAPAction: Create
	 */
	public void create(String payloadBody)
	{
		// logger.debug("--------");
		// logger.debug("Executing create");
		TreeMap<String, String> header =
				getMap("content-type", "text/xml;charset=UTF-8", "SOAPAction", "Create");

		// prepare payload
		String payload = "";

		payload +=
				"    <s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:u=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">";

		payload += getPayloadHeader("Custom Object Samples", false);

		payload += payloadBody;
		payload += "</s:Envelope>";

		soap(endPointAddress, header, payload);

		// logger.debug("Done executing create");
	}

	public void update()
	{

	}

	public void destroyAll(String tableName)
	{
		logger.debug("--------");
		logger.debug("Destroying everything from " + tableName);

		// get the start and end points
		Point boundaries = getStartAndEndIDs(tableName);
		System.out.println("----------------------- proceed for deleting --------");
		if (boundaries == null)
		{
			logger.debug("No data to destroy.");
			return;
		}

		int min = (int) boundaries.x;
		int max = (int) boundaries.y;
		System.out.println(" deleteAll ----------------------------- min :: > "+ min);
		System.out.println(" deleteAll ----------------------------- max :: > "+ max);
		for (int id = min, i = 0; id <= max; id++, i++)
		{
			if (i % 10 == 0)
				logger.debug("destroying " + i + " of " + (max - min + 1));
			
			System.out.println("----------------------- calling delete funtion --------");
			
			this.destroy(id, tableName, false);
		}
		logger.debug("/-------- end destory all of " + min + " to " + max + " IDs in table "
				+ tableName);
	}

	/**
	 * Gets the start and end IDs of the given table name, stored in a Point object. Returns null if
	 * there is no data in the table.
	 * 
	 * @param tableName
	 * @return
	 */
	
	public void getData(String tableName)
	{
		String result = this.queryObjects("SELECT ID FROM KMS." + tableName + " WHERE acct_id='1';", false);
		System.out.println("getData :: result :: > "+ result);
	}
	
	
	private Point getStartAndEndIDs(String tableName)
	{
		System.out.println("getStartAndEndIDs :: Tablee Name :: > " + tableName);
		String minResult = this.queryObjects("SELECT MIN(ID) FROM KMS." + tableName + ";", false);
		String maxResult = this.queryObjects("SELECT MAX(ID) FROM KMS." + tableName + ";", false);

		System.out.println("getStartAndEndIDs :: minResult :: > "+ minResult);
		System.out.println("getStartAndEndIDs :: maxResult :: > "+ maxResult);
		
		// logger.debug("min result = '" + minResult + "'");
		Vector<Table> minTables = this.getTablesFromXML(minResult);
		// logger.debug("min tables = " + minTables);
		Table minTable = minTables.firstElement();
		String minElementStr = minTable.getAllDataAsVector().firstElement();
		if (minElementStr.equals(""))
			return null;
		int min = Integer.parseInt(minElementStr);

		// logger.debug("max result = '" + maxResult + "'");
		Vector<Table> maxTables = this.getTablesFromXML(maxResult);
		// logger.debug("max tables = " + maxTables);
		Table maxTable = maxTables.firstElement();
		String maxElementStr = maxTable.getAllDataAsVector().firstElement();
		if (maxElementStr.equals(""))
			return null;
		int max = Integer.parseInt(maxElementStr);
		System.out.println("getStartAndEndIDs :: Min :: > "+ min);
		System.out.println("getStartAndEndIDs :: Max :: > "+ max);
		Point ret = new Point(min, max);

		return ret;
	}

	public void destroy(int id, String tableName)
	{
		destroy(id, tableName, true);
	}

	/**
	 * Destroys a record with the given id in the given table name.
	 * 
	 * @param id
	 * @param tableName
	 */
	private void destroy(int id, String tableName, boolean echo)
	{
		if (echo)
		{
			logger.debug("--------");
			logger.debug("Destroying " + id + " from " + tableName);
		}

		TreeMap<String, String> header =
				getMap("content-type", "text/xml;charset=UTF-8", "SOAPAction", "Destroy");

		// prepare payload
		String payload = "";

		payload +=
				"<s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:u=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">\n";

		payload += getPayloadHeader("Destroy Custom Object", false);

		payload +=
				"  <s:Body xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n";
		payload += "  <Destroy xmlns=\"urn:messages.ws.rightnow.com/v1_3\">\n";
		payload +=
				"     <RNObjects xmlns:q1=\"urn:generic.ws.rightnow.com/v1_3\" xsi:type=\"q1:GenericObject\">\n";
		payload += "      <ID xmlns=\"urn:base.ws.rightnow.com/v1_3\" id=\"" + id + "\"/>\n";
		payload += "        <q1:ObjectType>\n";
		payload += "          <q1:Namespace>KMS</q1:Namespace>\n";
		payload += "          <q1:TypeName>" + tableName + "</q1:TypeName>\n";
		payload += "        </q1:ObjectType>\n";
		payload += "      </RNObjects>\n";
		payload += "      <ProcessingOptions>\n";
		payload += "        <SuppressExternalEvents>false</SuppressExternalEvents>\n";
		payload += "        <SuppressRules>false</SuppressRules>\n";
		payload += "      </ProcessingOptions>\n";
		payload += "    </Destroy>\n";
		payload += "  </s:Body>\n";
		payload += "</s:Envelope>\n";
		System.out.println(" destroy :: > endPointAddress" + endPointAddress );
		System.out.println(" destroy :: > header :: > " + header);
		System.out.println(" destroy :: > payLoad :: > " + payload);
		soap(endPointAddress, header, payload);
	}

	public String read()
	{
		logger.debug("--------");
		logger.debug("Executing read");

		TreeMap<String, String> header =
				getMap("content-type", "text/xml;charset=UTF-8", "SOAPAction", "Get");

		// prepare payload
		String payload = "";

		payload +=
				" <s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\"xmlns:u=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">";

		payload += getPayloadHeader("Custom Object Samples", false);

		payload +=
				" <s:Body xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">";
		payload += " <Get xmlns=\"urn:messages.ws.rightnow.com/v1_3\">";
		payload +=
				" <RNObjects xmlns:q1=\"urn:generic.ws.rightnow.com/v1_3\" xsi:type=\"q1:GenericObject\">";
		payload += " <ID xmlns=\"urn:base.ws.rightnow.com/v1_3\" id=\"4\" />";
		payload += " <q1:ObjectType>";
		payload += " <q1:Namespace>CO</q1:Namespace>";
		payload += " <q1:TypeName>AllFieldTypes</q1:TypeName>";
		payload += " </q1:ObjectType>";
		payload += " </RNObjects>";
		payload += " <ProcessingOptions>";
		payload += " <FetchAllNames>false</FetchAllNames>";
		payload += " </ProcessingOptions>";
		payload += " </Get>";
		payload += " </s:Body>";
		payload += "</s:Envelope>";

		// payload += "<s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\"
		// xmlns:u=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">";
		// payload += "<s:Header>";
		// payload += <h:ClientInfoHeader xmlns:h="urn:messages.ws.rightnow.com/v1_3"
		// xmlns="urn:messages.ws.rightnow.com/v1_3"
		// xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		// xmlns:xsd="http://www.w3.org/2001/XMLSchema">
		// payload += <AppID>Custom Objects Samples</AppID>
		// </h:ClientInfoHeader>
		// <o:Security
		// xmlns:o="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd"
		// s:mustUnderstand="1">
		// <o:UsernameToken u:Id="uuid-1d99da22-a3d2-4638-81cd-e6e852198f52-1">
		// <o:Username>admin</o:Username>
		// <o:Password>Welcome123</o:Password>
		// </o:UsernameToken>
		// </o:Security>
		// </s:Header>
		// <s:Body xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		// xmlns:xsd="http://www.w3.org/2001/XMLSchema">
		// <Get xmlns="urn:messages.ws.rightnow.com/v1_3">
		// <RNObjects xmlns:q1="urn:generic.ws.rightnow.com/v1_3" xsi:type="q1:GenericObject">
		// <ID xmlns="urn:base.ws.rightnow.com/v1_3" id="4" />
		// <q1:ObjectType>
		// <q1:Namespace>CO</q1:Namespace>
		// <q1:TypeName>AllFieldTypes</q1:TypeName>
		// </q1:ObjectType>
		// </RNObjects>
		// <ProcessingOptions>
		// <FetchAllNames>false</FetchAllNames>
		// </ProcessingOptions>
		// </Get>
		// </s:Body>
		// </s:Envelope>"

		String result = soap(endPointAddress, header, payload);
		return result;
	}

	/**
	 * Executes the following query.
	 * 
	 * @param query
	 * @return
	 */
	public String queryObjects(String query, boolean objectQuery)
	{
		logger.debug("--------");
		logger.debug("Executing query objects");
		logger.debug("query is '" + query + "'");

		TreeMap<String, String> header =
				getMap("content-type", "text/xml;charset=UTF-8", "SOAPAction", "QueryObjects");

		// prepare payload
		String payload = "";

		// payload +=
		// " <s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\"
		// xmlns:u=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">\n";
		//
		// payload += getPayloadHeader("Basic Object Query", true);
		//
		// payload += " <s:Body>\n";
		// payload += " <h:QueryObjects xmlns:h=\"urn:messages.ws.rightnow.com/v1_3\">\n";
		// payload +=
		// " <h:Query>SELECT CO.AllFieldTypes FROM CO.AllFieldTypes WHERE ID > 3;</h:Query>\n";
		// payload +=
		// " <h:ObjectTemplates xmlns:ns4=\"urn:objects.ws.rightnow.com/v1_3\"
		// xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns4:Contact\">\n";
		// payload += " <ns4:Notes />\n";
		// payload += " </h:ObjectTemplates>\n";
		// payload += " <h:PageSize>10000</h:PageSize>\n";
		// payload += " </h:QueryObjects>\n";
		// payload += " </s:Body>\n";
		// payload += "</s:Envelope>\n";

		payload +=
				"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\n";
		payload += "  <soapenv:Header>\n";
		payload +=
				"    <ns7:ClientInfoHeader xmlns:ns7=\"urn:messages.ws.rightnow.com/v1_3\" soapenv:mustUnderstand=\"0\">\n";
		payload += "      <ns7:AppID>Basic Object Query</ns7:AppID>\n";
		payload += "    </ns7:ClientInfoHeader>\n";
		payload +=
				"    <wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" mustUnderstand=\"1\">\n";
		payload += "      <wsse:UsernameToken>\n";
		payload += "        <wsse:Username>" + accountUsername + "</wsse:Username>\n";
		payload += "        <wsse:Password>" + accountPassword + "</wsse:Password>\n";
		payload += "      </wsse:UsernameToken>\n";
		payload += "    </wsse:Security>\n";
		payload += "  </soapenv:Header>\n";
		payload += "  <soapenv:Body>\n";
		if (objectQuery)
			payload += "    <ns7:QueryObjects xmlns:ns7=\"urn:messages.ws.rightnow.com/v1_3\">\n";
		else
			payload += "    <ns7:QueryCSV xmlns:ns7=\"urn:messages.ws.rightnow.com/v1_3\">\n";
		payload += "      <ns7:Query>" + query + "</ns7:Query>\n";

		if (objectQuery)
		{
			payload +=
					"      <ns7:ObjectTemplates xmlns:ns4=\"urn:objects.ws.rightnow.com/v1_3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns4:Contact\">\n";
			payload += "        <ns4:Notes />\n";
			payload += "      </ns7:ObjectTemplates>\n";
		}
		payload += "      <ns7:PageSize>10000</ns7:PageSize>\n";
		if (objectQuery)
			payload += "   </ns7:QueryObjects>\n";
		else
			payload += "    </ns7:QueryCSV>\n";
		payload += "  </soapenv:Body>\n";
		payload += "</soapenv:Envelope>\n";

		// payload +=
		// "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\n";
		// payload += " <soapenv:Header>\n";
		// payload +=
		// " <ns7:ClientInfoHeader xmlns:ns7=\"urn:messages.ws.rightnow.com/v1_3\"
		// soapenv:mustUnderstand=\"0\">\n";
		// payload += " <ns7:AppID>Basic Object Query</ns7:AppID>\n";
		// payload += " </ns7:ClientInfoHeader>\n";
		// payload +=
		// " <wsse:Security
		// xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\"
		// mustUnderstand=\"1\">\n";
		// payload += " <wsse:UsernameToken>\n";
		// payload += " <wsse:Username>" + username + "</wsse:Username>\n";
		// payload += " <wsse:Password>" + password + "</wsse:Password>\n";
		// payload += " </wsse:UsernameToken>\n";
		// payload += " </wsse:Security>\n";
		// payload += " </soapenv:Header>\n";
		// payload += " <soapenv:Body>\n";
		// payload += " <ns7:QueryObjects xmlns:ns7=\"urn:messages.ws.rightnow.com/v1_3\">\n";
		// payload += " <ns7:Query>" + query + "</ns7:Query>\n";
		// payload +=
		// " <ns7:ObjectTemplates xmlns:a=\"urn:objects.ws.rightnow.com/v1_3\"
		// xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns4:Contact\">\n";
		// payload += " <ns4:Notes />\n";
		// payload += " </ns7:ObjectTemplates>\n";
		// payload += " <ns7:PageSize>10000</ns7:PageSize>\n";
		// payload += " </ns7:QueryObjects>\n";
		// payload += " </soapenv:Body>\n";
		// payload += "</soapenv:Envelope>\n";

		String result = soap(endPointAddress, header, payload);
		return result;
	}

	/**
	 * Extracts a list of tables from the returned query object XML.
	 * 
	 * @param xml
	 * @return
	 */
	public Vector<Table> getTablesFromXML(String xml)
	{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try
		{
			dBuilder = dbFactory.newDocumentBuilder();
		}
		catch (ParserConfigurationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			e.printStackTrace();
			return null;
		}

		// optional, but recommended
		// read this -
		// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		doc.getDocumentElement().normalize();

		Element root = doc.getDocumentElement();
		logger.debug("Root element :" + root);

		NodeList tableNodes = getXMLElement(root, "soapenv:Body", "n0:QueryCSVResponse",
				"n0:CSVTableSet", "n0:CSVTables").getChildNodes();

		Vector<Table> tables = new Vector<Table>();
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

			// logger.debug("column data " + columnData);
			// logger.debug("columns = " + columns);
			Table table = new Table();
			// logger.debug(rowChildren.getLength() + " row(s)");

			for (int rowNum = 0; rowNum < rowChildren.getLength(); rowNum++)
			{
				Node rowNode = rowChildren.item(rowNum);

				String dataString = rowNode.getTextContent();
				// logger.debug("dataString = '" + dataString + "'");

				Vector<String> dataValues = StringUtility.tokenizeByCommas(dataString);

				// logger.debug("dataValues = " + dataValues);

				// logger.debug(" dv size = " + dataValues.size());
				// logger.debug(" column size = " + columns.size());
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

		// logger.debug("--tables\n" + tables);
		// logger.debug("----------------------------");

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

	// https://opn-softclouds4.rightnowdemo.com/cgi-bin/opn_softclouds4.cfg/services/soap
	private String soap(String endPointAddress, Map<String, String> header, String payload)
	{
		// logger.debug("Executing soap request");
		// logger.debug("payload\n" + payload);
		// logger.debug("endPointAddress = '" + endPointAddress + "'");
		// logger.debug("headers = " + header);
		try
		{
			String charset = "UTF-8";

			HttpURLConnection connection =
					(HttpURLConnection) new URL(endPointAddress).openConnection();
			connection.setDoOutput(true); // Triggers POST.

			connection.setRequestMethod("POST");

			// set the header
			for (String key : header.keySet())
				connection.setRequestProperty(key, header.get(key));

			// write the payload
			OutputStream output = connection.getOutputStream();
			output.write(payload.getBytes(charset));

			int responseCode = connection.getResponseCode();

			// logger.debug("response code is " + responseCode);

			if (responseCode != 200)
			{
				InputStream errorStream = connection.getErrorStream();
				String errorMsg = StringUtility.toString(errorStream);
				logger.debug("Error '" + errorMsg + "'");
			}

			// read the response
			InputStream inputStream = connection.getInputStream();

			String response = StringUtility.toString(inputStream);

			// logger.debug("response = '" + response + "'");
			return response;
		}
		catch (MalformedURLException e)
		{
			logger.debug(ExceptionUtils.getFullStackTrace(e));
			e.printStackTrace();
		}
		catch (ProtocolException e)
		{
			logger.debug(ExceptionUtils.getFullStackTrace(e));
			e.printStackTrace();
		}
		catch (IOException e)
		{
			logger.debug(ExceptionUtils.getFullStackTrace(e));
			e.printStackTrace();
		}

		return null;
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
}
