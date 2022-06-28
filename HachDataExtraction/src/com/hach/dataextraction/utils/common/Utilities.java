package com.hach.dataextraction.utils.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.regex.Pattern;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.axis.utils.XMLUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.format.CellNumberFormatter;
import org.apache.poi.ss.usermodel.Cell;
import org.joda.time.DateTime;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


public class Utilities {

	static Logger logger = Logger.getLogger(Utilities.class);

	public static void printStackTraceToLogs(String className, String methodName, Exception e)
	{
		try
		{
			Writer writer = new StringWriter();
			PrintWriter print = new PrintWriter(writer);
			e.printStackTrace(print);

			logger.info(className+"::"+methodName+":: Error :: > " + e.getMessage());
			logger.info(className+"::"+methodName+":: Error :: > " + writer.toString());

			print = null;
			writer= null;
		}
		catch(Exception f)
		{
			f.printStackTrace();
		}
	}	

	public static String readInputStramToString(InputStream is) 
	{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		try
		{
			if(null!=is)
			{
				String line;
				br = new BufferedReader(new InputStreamReader(is));
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
			}
		}
		catch (IOException e) 
		{
			printStackTraceToLogs(Utilities.class.getName(), "readInputStramToString()", e);
		} 
		catch(Exception e)
		{
			printStackTraceToLogs(Utilities.class.getName(), "readInputStramToString()", e);
		}
		finally 
		{
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					printStackTraceToLogs(Utilities.class.getName(), "readInputStramToString()", e);
				}
			}
		}
		return sb.toString();
	}

	public static String readNodeValue(Node node)
	{
		String nodeValue="";
		try
		{
		Element valueElement= (Element)node;
		String text = XMLUtils.ElementToString(valueElement);
		if(null!=text && !"".equals(text))
		{
			if(text.contains("<![CDATA["))
			{
				// GET CDATA VALUE OF THE ELEMENT
				nodeValue = Utilities.getCharacterDataFromElement(valueElement);
			}
			else
			{
				nodeValue = valueElement.getTextContent();
			}
		}
		text= null;
		valueElement = null;
		}
		catch(Exception e)
		{
			printStackTraceToLogs(Utilities.class.getName(), "readNodeValue()", e);
		}
		return nodeValue;
	}

	/**
	 * Function will help to get the CDATA value of an Element in the XML Document
	 * @param e
	 * @return
	 */
	private static String getCharacterDataFromElement(Element e) {
		Node child = e.getFirstChild();
		if (child instanceof CharacterData) {
			CharacterData cd = (CharacterData) child;
			return cd.getData();
		}
		return "";
	}

	public static String transformString(Document doc)
	{	
		String stringDoc=null;
		try
		{
			StringWriter stw = new StringWriter();
			Transformer serializer = TransformerFactory.newInstance().newTransformer();
			serializer.transform(new DOMSource(doc), new StreamResult(stw));
			stringDoc=stw.toString();
		}
		catch(TransformerConfigurationException tce)
		{
			return null;
		}
		catch(TransformerFactoryConfigurationError tfc)
		{
			return null;
		}
		catch(TransformerException te)
		{
			return null;
		}
		return stringDoc;
	}

	/**
	 * Reads all you can from an input stream.
	 * 
	 * @param inputStream
	 * @return
	 */
	public static String toString(InputStream inputStream)
	{
		StringWriter writer = new StringWriter();
		try
		{
			if(null!=inputStream)
			{
//				IOUtils.copy(inputStream, writer);
//				String response = writer.toString();
				 int bufferSize = 1024;
				 char[] buffer = new char[bufferSize];
				 StringBuilder out = new StringBuilder();
				 Reader in = new InputStreamReader(inputStream, Charset.forName("UTF-8").toString());
				 for (int numRead; (numRead = in.read(buffer, 0, buffer.length)) > 0; ) {
				     out.append(buffer, 0, numRead);
				 }
				 return out.toString();
//				return response;
			}
		}
		catch (IOException e)
		{
			printStackTraceToLogs(Utilities.class.getName(), "toString()", e);
		}
		return null;
	}

	/**
	 * Function will identify the Cell Value Type and accordingly will read
	 * their values and will return
	 * 
	 * @param cell
	 * @return
	 */
	public static Object readCellValue(Cell cell) {
		Object cellValue = null;
		try {
			if (null != cell) {
				/*
				 * check for Cell Type and format accordingly
				 */
				if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					cellValue = cell;

					/*
					 * Check here, if callValue is 0; THEN return "" as object
					 * else, check for decimal value in cell if decimal value is
					 * .0 or .00, then remove it if more than 0, then pass the
					 * value as it is
					 */

					String val = String.valueOf(cellValue);
					if (null != val && !"".equals(val)) {
						/*
						 * check if val has any value in point to decimal and
						 * that value is 0 then remove the decimal Value
						 */
						if (val.lastIndexOf(".") != -1) {
							String decimal = val.substring(
									val.lastIndexOf(".") + 1, val.length());
							if (null != decimal && !"".equals(decimal)) {
								double dec = new Double(decimal).doubleValue();
								if (dec == 0) {
									// remove decimal from the val and value
									// after decimal
									val = val
											.substring(0, val.lastIndexOf("."));
								} else {
									// GET THE NUMERIC VALUE IN THE FORMAT
									// ########################## AND PASS IT
									CellNumberFormatter cn = new CellNumberFormatter(
											"################################");
									val = cn.simpleFormat(cell);
									cn = null;
								}
								// set dec to null
								//								dec = null;
							}
							// set decimal to null
							decimal = null;
						}
					}

					// set cellValue as val
					cellValue = val;
					// set val to null
					val = null;
				} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
					cellValue = cell.getCellFormula();
					if (null != cellValue) {
						// append a = with this formula
						cellValue = "=" + cellValue;
					}
				} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
					cellValue = cell.getStringCellValue();
				}
			}
		} catch (Exception e) {
			printStackTraceToLogs(Utilities.class.getName(), "readCellValue()", e);
			// set cellValue to null
			cellValue = null;
		}
		return cellValue;
	}

	public static boolean isValid(String email) 
	{
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
				"[a-zA-Z0-9_+&*-]+)*@" + 
				"(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
				"A-Z]{2,7}$"; 

		Pattern pat = Pattern.compile(emailRegex); 
		if (email == null) 
			return false; 
		return pat.matcher(email).matches(); 
	} 

	/**
	 * Method will be used for getting
	 * element value
	 * 
	 * @param elem
	 * @return
	 */
	public final static String getElementValue( Node elem ) 
	{
		Node kid;
		if( elem != null)
		{
			if (elem.hasChildNodes())
			{
				for( kid = elem.getFirstChild(); kid != null; kid = kid.getNextSibling() )
				{
					if( kid.getNodeType() == Node.TEXT_NODE  )
					{
						return kid.getNodeValue();
					}
				}
			}
		}
		return "";
	}
	
	
	public final static Timestamp convertDateTimeToPST(Timestamp timeStamp)
	{
		try
		{
			/*
			 * TICKET 4642 - CHANGE
			 * Let us go ahead and make change to add 20 hours instead of 24 hours to the date provided in 
			 * inbound file in TST2. After this change we can request Eric to give us sample file to and run validate.
			 */
			if(null!=timeStamp)
			{
				// convert this time to DateTime
				DateTime dt = new DateTime(timeStamp.getTime());
				// add 23 hours to make it to PST
				// changed to 20 hours
				DateTime ndt = dt.plusHours(20);
				// set time back in timeStamp value
				timeStamp = new Timestamp(ndt.getMillis());
				dt = null;
				ndt = null;
			}
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(Utilities.class.getName(), "convertDateTimeToPST()", e);
		}
		return timeStamp;
	}
	
	public static String replaceSQLTokens(String value)
	{
		if(null!=value && !"".equals(value))
		{
			value= value.replace("__b", " ");
			value= value.replace("__f", "/");
			value= value.replace("__u", "-");
			value= value.replace("__7", "&");
			value= value.replace("__P", "(");
			value= value.replace("__p", ")");
			value= value.replace("__a", "'");
			value= value.replace("__d", ".");
			value= value.replace("__M", ",");
		}
		return value;
	}
}
