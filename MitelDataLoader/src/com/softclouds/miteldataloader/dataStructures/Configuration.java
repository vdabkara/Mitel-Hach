package com.softclouds.miteldataloader.dataStructures;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.TreeMap;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Configuration class used to store key-value pairs used for externalized configuration settings
 * stored in a JSON text file format.
 * 
 * @author Ray
 *
 */
public class Configuration
{
	final private static Logger		logger	= Logger.getLogger(Configuration.class);
	private TreeMap<String, String>	map		= new TreeMap<String, String>();

	/**
	 * Creates a new Configuration by reading key-value pairs from the given JSONObject.
	 * 
	 * @param configJson
	 */
	public Configuration(JSONObject configJson)
	{
		Iterator<String> keyIterator = (Iterator<String>) configJson.keys();

		while (keyIterator.hasNext())
		{
			String key = keyIterator.next();

			try
			{
				String value = configJson.getString(key);
				map.put(key, value);
			}
			catch (JSONException e)
			{

			}
		}
	}

	/**
	 * Constructor creates a Configuration based off of key-value string pairs in a given
	 * resourceBundle.
	 * 
	 * @param resourceBundle
	 */
	public Configuration(ResourceBundle resourceBundle)
	{
		Enumeration<String> enumeration = resourceBundle.getKeys();

		while (enumeration.hasMoreElements())
		{
			String key = enumeration.nextElement();
			String value = resourceBundle.getString(key);

			map.put(key, value);
		}
	}

	/**
	 * Gets the value associated to this key.
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key)
	{
		String value = map.get(key);

		if (value == null)
		{
			String msg = "Invalid key '" + key + "'.  Valid keys are " + map.keySet();

			UnsupportedOperationException e = new UnsupportedOperationException();
			logger.debug(ExceptionUtils.getFullStackTrace(e));

			throw e;
		}
		return value;
	}

}
