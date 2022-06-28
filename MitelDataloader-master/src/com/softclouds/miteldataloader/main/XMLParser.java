package com.softclouds.miteldataloader.main;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class XMLParser
{
	private Document document;

	/**
	 * Creates a new XML Parser for the given data to be parsed.
	 * 
	 * @param xml
	 */
	public XMLParser(String xml)
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(true);
		factory.setIgnoringElementContentWhitespace(true);
		try
		{
			DocumentBuilder builder = factory.newDocumentBuilder();
			File file = new File("test.xml");
			document = builder.parse(file);
		}
		catch (ParserConfigurationException e)
		{
		}
		catch (SAXException e)
		{
		}
		catch (IOException e)
		{
		}
	}

	public String getElement(String... path)
	{
		Element docElement = document.getDocumentElement();

		return getElement(docElement, path);
	}

	private String getElement(Element docElement, String... path)
	{
		if (path.length < 1)
			throw new IllegalArgumentException(
					"No paths specified, there must be at least one element in the path list");

		String nodeName = document.getNodeName();

		if (!path[0].equals(nodeName))
			return null;

		else
		{
		}

		return null;
	}
}
