package com.softclouds.miteldataloader.dataStructures.cruder;

/**
 * Data types for OSvC table values.
 * 
 * @author Ray
 *
 */
public enum DataType
{
	INTEGER_VALUE, MENU_VALUE, TEXT_VALUE, DATE_TIME_VALUE, DATE_VALUE, BOOLEAN_VALUE;

	/**
	 * Gets a payload snippet for the given data type and the given value.
	 * 
	 * @param dataType
	 * @param valueontentType
	 */
	public static String getPayloadSnippet(DataType dataType, String fieldName, String value)
	{

		String payload = "";
		switch (dataType)
		{
			case INTEGER_VALUE:
				payload += getPayloadSnippet(fieldName, value, "IntegerValue");
				break;
			case MENU_VALUE:
				payload += "        <q1:GenericFields name=\"" + fieldName + "\">\n";
				payload += "	      <q1:DataValue>\n";
				payload += "	        <q1:NamedIDValue>\n";
				payload += "                 <ID xmlns=\"urn:base.ws.rightnow.com/v1_3\" id=\""
						+ value + "\" />\n";
				payload += "	        </q1:NamedIDValue>\n";
				payload += "	      </q1:DataValue>\n";
				payload += "	    </q1:GenericFields>\n";
				break;
			case TEXT_VALUE:
				payload += getPayloadSnippet(fieldName, value, "StringValue");
				break;
			case DATE_TIME_VALUE:
				payload += getPayloadSnippet(fieldName, value, "DateTimeValue");
				// payload +=
				// " <q1:DateTimeValue>2013-11-01T21:45:35Z</q1:DateTimeValue>";
				break;
			case DATE_VALUE:
				payload += getPayloadSnippet(fieldName, value, "DateValue");
				// payload += " <q1:DateValue>2013-11-01</q1:DateValue>";
				break;
			case BOOLEAN_VALUE:
				payload += getPayloadSnippet(fieldName, value, "BooleanValue");
				break;
			default:
				throw new IllegalArgumentException(
						"Unsupported data type " + dataType + ", cannot create payload snippet");
		}
		return payload;
	}

	private static String getPayloadSnippet(String fieldName, String value, String dataTypeString)
	{
		String payload = "";
		payload += "        <q1:GenericFields name=\"" + fieldName + "\">\n";
		payload += "          <q1:DataValue>\n";
		payload += "            <q1:" + dataTypeString + ">" + value + "</q1:" + dataTypeString
				+ ">\n";
		payload += "          </q1:DataValue>\n";
		payload += "        </q1:GenericFields>\n";
		return payload;
	}
}
