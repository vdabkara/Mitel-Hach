package com.ka.kcapp.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.TreeMap;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * Class for a text table with console displaying methods.
 * 
 * @author Ray
 *
 */
public class Table extends ReadableAndWritable
{
	
	

	/**
	 * Map of a map for the data. The outside key is the xkey, the inside key is the ykey.
	 */
	private ConcurrentHashMap<String, ConcurrentHashMap<String, String>>	dataMap;
	private ConcurrentHashMap<String, Integer>								columnWidthMap		=
			new ConcurrentHashMap<String, Integer>();
	private ConcurrentHashMap<String, TableDataType>						columnDataTypeMap	=
			new ConcurrentHashMap<String, TableDataType>();

	/**
	 * Should we display the yKey in the String representation? This affects how it is written and
	 * read to and from file
	 **/
	private final boolean													displayYKey;

	private HashSet<String>													xkeyHashSet			=
			new HashSet<String>();
	private HashSet<String>													ykeyHashSet			=
			new HashSet<String>();
	private Vector<String>													xkeyList			=
			new Vector<String>();
	private Vector<String>													ykeyList			=
			new Vector<String>();

	/**
	 * Creates a new table with default of displaying the legend. By default it *will* display the
	 * ykeys and it will be TAB delimited.
	 * 
	 * Uses the default initialMapSize
	 */
	public Table()
	{
		this(true, StringUtility.TAB_REGEX);
	}

	/**
	 * Creates a new table with the specified displayLegend behavior
	 * 
	 * @param displayYKey
	 * @param delimiter
	 */
	public Table(boolean displayYKey, String delimiter)
	{
		this.displayYKey = displayYKey;
		this.dataMap = new ConcurrentHashMap<String, ConcurrentHashMap<String, String>>();

		if (!delimiter.equals(StringUtility.TAB_REGEX)
				&& !delimiter.equals(StringUtility.COMMA_REGEX))
			throw new IllegalArgumentException("Invalid delimiter '" + delimiter + "'.\nRequires '"
					+ StringUtility.TAB_REGEX + "' or '" + StringUtility.COMMA_REGEX + "'");
	}

	/**
	 * Creates a table from this set and populates it. y-keys are 'entry_#' starting from 1.
	 * 
	 * @param concurrentSkipListSet
	 */
	public Table(String xkey, Collection<String> set)
	{
		this();

		int n = 1;
		for (String data : set)
		{
			String ykey = "entry_" + n;
			this.add(xkey, ykey, data);
			n++;
		}
	}

	/**
	 * Constructor creates a new table populating from a map String -> set(String)
	 * 
	 * @param map
	 */
	public Table(ConcurrentSkipListMap<String, ConcurrentSkipListSet<String>> map)
	{
		this();

		int entryNumber = 0;
		for (String key : map.keySet())
		{
			String ykey = "entry_" + entryNumber;
			add("key", ykey, key);

			int itemNumber = 0;

			for (String setItem : map.get(key))
			{
				String xkey = "item_" + itemNumber;

				add(xkey, ykey, setItem);
				itemNumber++;
			}

			entryNumber++;
		}
	}

	/**
	 * Reads a table from the given input file. The inputfile is tab-delimited. Throws an exception
	 * if the file does not exist.
	 */
	public static Table readTSV(String fileName)
	{
		boolean displayYKey = false;
		String delimiter = StringUtility.TAB_REGEX;
		Table t = new Table(displayYKey, delimiter);

		t.read(fileName);

		return t;
	}

	/**
	 * Reads a table from the given input file. The inputfile is tab-delimited. Throws an exception
	 * if the file does not exist.
	 */
	public static Table readCSV(String fileName)
	{
		boolean displayYKey = false;
		String delimiter = StringUtility.COMMA_REGEX;
		Table t = new Table(displayYKey, delimiter);

		t.read(fileName);
		return t;
	}

	private void read(String fileName)
	{
		System.out.println("reading *sv" + fileName);
		if (!FileUtility.exists(fileName))
			throw new UnsupportedOperationException(
					"Cannot read tab-delimited file '" + fileName + "' because it does not exist");

		// read fields
		InputFile input = new InputFile(fileName);

		Vector<String> columnNames = new Vector<String>();
		while (!input.eof() && input.sym != '\n')
		{
			String columnName;
			if (input.sym == '"')
				columnName = input.readQuotedString().trim();
			else
				columnName = input.skipUntil(",\n").trim();
			// System.out.println("adding '" + columnName + "'");

			columnNames.add(columnName);
			if (input.sym != '\n')
				input.read(",");
		}
		input.skipWhiteSpace();

		// System.out.println("columns = '" + columnNames + "'");
		if (columnNames.size() == 0)
			throw new UnsupportedOperationException("No column names found!");

		int yIndex = 0;
		int xIndex = 0;
		while (!input.eof())
		{
			// read a bit of data
			String data;
			if (input.sym == '"')
			{
				data = input.readQuotedString().trim();
				// System.out.println("read quoted str '" + data + "'");
			}
			else
			{
				// System.out.println("symint = " + (int) input.sym);

				if (xIndex == columnNames.size() - 1)
					data = input.skipUntil("\n");
				else
					data = input.skipUntil(",");
				// System.out.println("read raw str '" + data + "'");
			}

			String xkey = columnNames.get(xIndex);
			String ykey = "item_" + yIndex;

			// System.out.println("adding (" + xkey + ", " + ykey + ") = '" + data + "'");

			this.add(xkey, ykey, data);

			xIndex++;

			// System.out.println("sym is '" + input.sym + "'");
			if (input.sym == (char) 13 || input.sym == (char) 10)
			{
				// System.out.println("CR");
				xIndex = 0;
				yIndex++;
				input.read(input.sym);
			}
			else
			{
				// System.out.println("NCR");
				input.read(",");
			}

		}

	}


	/**
	 * Adds a data element to this table.
	 * 
	 * @param xkey
	 * @param ykey
	 * @param data
	 */
	public void add(String xkey, String ykey, String data)
	{
		if (xkey == null)
			throw new IllegalArgumentException("xkey is null");
		if (ykey == null)
			throw new IllegalArgumentException("ykey is null");
		if (data == null)
			throw new IllegalArgumentException("data is null");

		// add keys if they are unique
		if (!xkeyHashSet.contains(xkey))
		{
			xkeyHashSet.add(xkey);
			xkeyList.add(xkey);
		}
		if (!ykeyHashSet.contains(ykey))
		{
			ykeyHashSet.add(ykey);
			ykeyList.add(ykey);
		}

		ConcurrentHashMap<String, String> subMap = dataMap.get(xkey);

		if (subMap == null)
		{
			subMap = new ConcurrentHashMap<String, String>();
			dataMap.put(xkey, subMap);
		}
		subMap.put(ykey, data);

		updateColumnWidth(xkey, data);
		updateDataType(xkey, data);
	}

	/**
	 * Updates the max column width as necessary.
	 * 
	 * @param xkey
	 * @param data
	 */
	private void updateColumnWidth(String xkey, String data)
	{
		// check column width for a new maximum
		Integer oldWidth = this.columnWidthMap.get(xkey);
		if (oldWidth == null)
		{
			oldWidth = 0;
			this.columnWidthMap.put(xkey, oldWidth);
		}

		if (data.length() > oldWidth)
			this.columnWidthMap.put(xkey, data.length());
	}

	/**
	 * Updates the datatype as necessary.
	 * 
	 * State transition flow:
	 * 
	 * unassigned -> number -> string
	 * 
	 * unassigned -> string
	 * 
	 * @param xkey
	 * @param data
	 */
	private void updateDataType(String xkey, String data)
	{
		// adjust column data type
		TableDataType oldDataType = this.columnDataTypeMap.get(xkey);

		if (oldDataType == null)
		{
			boolean isNumber = true;
			try
			{
				Double.parseDouble(data);
			}
			catch (NumberFormatException e)
			{
				isNumber = false;
			}

			if (isNumber)
				this.columnDataTypeMap.put(xkey, TableDataType.NUMBER);
			else
				this.columnDataTypeMap.put(xkey, TableDataType.STRING);
		}
		else
		{
			switch (oldDataType)
			{
				case STRING:
					// do nothing, it remains a string regardless of whether or not the new data is
					// a number.
					break;
				case NUMBER:
					// if the old datatype is a number, try to parse this new one as a number. If it
					// fails, convert our datatype to a string.
					boolean isNumber = true;
					try
					{
						Double.parseDouble(data);
					}
					catch (NumberFormatException e)
					{
						isNumber = false;
					}

					if (!isNumber)
						this.columnDataTypeMap.put(xkey, TableDataType.STRING);
					break;
				default:
					throw new UnsupportedOperationException(
							"Unknown oldDataType (TableDataType) " + oldDataType);
			}
		}
	}

	/**
	 * Adds a data element to this table.
	 * 
	 * @param xkey
	 * @param yKey
	 * @param include
	 */
	public void add(String xkey, String ykey, char data)
	{
		add(xkey, ykey, data + "");
	}

	/**
	 * Adds a data element to this table.
	 * 
	 * @param xkey
	 * @param yKey
	 * @param include
	 */
	public void add(String xkey, String ykey, int data)
	{
		add(xkey, ykey, data + "");
	}

	/**
	 * Adds a data element to this table.
	 * 
	 * @param xkey
	 * @param yKey
	 * @param include
	 */
	public void add(String xkey, String ykey, double data)
	{
		String dataStr = String.format("%.9f", data);
		add(xkey, ykey, dataStr);
	}

	/**
	 * Composes a single string key from the given xkey and ykey.
	 * 
	 * @param xkey
	 * @param ykey
	 * @return
	 */
	// private String getKey(String xkey, String ykey)
	// {
	// return xkey + "|" + ykey;
	// }

	/**
	 * Gets all the x coordinate keys.
	 * 
	 * @return
	 */
	public Vector<String> getXKeys()
	{
		return xkeyList;
	}

	/**
	 * Gets all the y coordinate keys.
	 * 
	 * @return
	 */
	public Vector<String> getYKeys()
	{
		return ykeyList;
	}

	/**
	 * Returns a String representation of this Table.
	 */
	public String toString()
	{
		if (getXKeys().size() == 0 || getYKeys().size() == 0)
			return "";
		// throw new UnsupportedOperationException("Cannot create string. Table is empty.");
		StringBuilder sb = new StringBuilder();

		StringBuilder xKeySB = new StringBuilder();
		for (String xKey : getXKeys())
			xKeySB.append(xKey + "\t");

		String xKeyStr = xKeySB.toString();
		xKeyStr = StringUtility.trim(xKeyStr, 1);
		sb.append(xKeyStr);
		sb.append("\n");

		for (String yKey : getYKeys())
		{
			StringBuilder rowSB = new StringBuilder();

			for (String xKey : getXKeys())
			{
				String value = dataMap.get(xKey).get(yKey);

				if (value != null)
					rowSB.append(value);
				rowSB.append("\t");
			}

			if (displayYKey)
			{
				String yKeyT = StringUtility.trimLast(yKey, 15);
				rowSB.append(yKeyT + "\t");
			}

			// remove trailing tab
			String rowStr = rowSB.toString();
			rowStr = rowStr.substring(0, rowStr.length() - 1);
			sb.append(rowStr);
			sb.append("\n");
		}

		// sb.append(map);
		return sb.toString();
	}

	/**
	 * Returns the height of the table in elements.
	 * 
	 * @return
	 */
	public int getHeight()
	{
		return getYKeys().size();
	}

	/**
	 * Returns the width of the table in elements.
	 * 
	 * @return
	 */
	public int getWidth()
	{
		return getXKeys().size();
	}

	/**
	 * Gets the data in this table associated to the given xKey and yKey.
	 * 
	 * If there is no value, it returns null.
	 */
	public String get(String xkey, String ykey)
	{
		// if (!xkeyHashSet.contains(xkey))
		// throw new IllegalArgumentException(
		// "Unknown xKey = '" + xkey + "' (quoted), valid keys are " + getXKeys());
		// if (!ykeyHashSet.contains(ykey))
		// throw new IllegalArgumentException(
		// "Unknown yKey = '" + ykey + "' (quoted), valid keys are " + getYKeys());

		ConcurrentHashMap<String, String> xSubMap = dataMap.get(xkey);
		if (xSubMap == null)
			return null;
		String value = xSubMap.get(ykey);
		if (value == null)
			return null;
		return value;
	}

	@Override
	public void read(InputFile input)
	{
		throw new UnsupportedOperationException("Cannot read a table from an input file yet");
	}

	/**
	 * Is this table empty? That is, is the size of the map zero?
	 * 
	 * @return
	 */
	public boolean isEmpty()
	{
		return dataMap.size() == 0;
	}

	/**
	 * Returns the size of this table.
	 */
	public int size()
	{
		return getXKeys().size() * getYKeys().size();
	}

	/**
	 * Gets all data from the specified column name (xKey)
	 * 
	 * @param columnName
	 */
	public Vector<String> getColumn(String columnName)
	{
		if (!getXKeys().contains(columnName))
			throw new UnsupportedOperationException("Request for column '" + columnName
					+ "', but column does not exist.  Valid columns are " + getXKeys());

		Vector<String> ret = new Vector<String>();
		for (String xKey : getXKeys())
		{
			if (xKey.equals(columnName))
				for (String yKey : getYKeys())
					ret.add(get(xKey, yKey));
		}
		return ret;
	}

	/**
	 * Gets the set of unique column values for the given column name.
	 * 
	 * @param columnName
	 */
	public ConcurrentSkipListSet<String> getColumnValueSet(String columnName)
	{
		return StringUtility.getStringSet(getColumn(columnName));
	}

	/**
	 * Gets all data from the specified row name (yKey)
	 * 
	 * @param rowName
	 */
	public Vector<String> getRow(String rowName)
	{
		if (!getYKeys().contains(rowName))
			throw new UnsupportedOperationException("Request for row '" + rowName
					+ "', but row does not exist.  Valid rows are " + getYKeys());

		Vector<String> ret = new Vector<String>();
		for (String yKey : getYKeys())
		{
			if (yKey.equals(rowName))
				for (String xKey : getXKeys())
					ret.add(get(xKey, yKey));
		}
		return ret;
	}

	/**
	 * Converts the table to a map of string-string set.
	 * 
	 * @return
	 */
	public ConcurrentSkipListMap<String, ConcurrentSkipListSet<String>> toStringStringSetMap()
	{
		// System.out.println("converting to string-string set map");

		ConcurrentSkipListMap<String, ConcurrentSkipListSet<String>> ret =
				new ConcurrentSkipListMap<String, ConcurrentSkipListSet<String>>();

		for (String ykey : getYKeys())
		{
			Vector<String> xkeys = getXKeys();

			String key = get(xkeys.get(0), ykey);

			ConcurrentSkipListSet<String> set = new ConcurrentSkipListSet<String>();
			for (int i = 1; i < xkeys.size(); i++)
			{
				String xkey = xkeys.get(i);
				String data = get(xkey, ykey);
				if (data != null)
					set.add(data);
			}

			ret.put(key, set);
		}
		// System.out.println("done");
		return ret;
	}

	/**
	 * Does this table contain this data?
	 * 
	 * @param data
	 * @return
	 */
	public boolean contains(String data)
	{
		for (String xkey : dataMap.keySet())
		{
			Collection<String> values = dataMap.get(xkey).values();

			if (values.contains(data))
				return true;
		}
		return false;
	}

	/**
	 * Gets a subtable of this table for the given start and end row indices. Throws an exception in
	 * illegal boundaries.
	 * 
	 * @param startRow
	 * @param endRow
	 * @return
	 */
	public Table getSubTable(int startRow, int endRow)
	{

		if (startRow >= endRow)
			throw new IllegalArgumentException("Cannot get subtable.  startRow " + startRow
					+ " is greater than or equal to endRow " + endRow);

		// truncate start and end row numbers
		if (startRow < 0)
			startRow = 0;

		if (endRow > this.getYKeys().size())
			endRow = this.getYKeys().size();

		Vector<String> xkeys = this.getXKeys();
		List<String> ykeys = this.getYKeys().subList(startRow, endRow);

		Table ret = new Table();
		for (String xkey : xkeys)
			for (String ykey : ykeys)
				ret.add(xkey, ykey, this.get(xkey, ykey));

		return ret;
	}

	/**
	 * Gets all of the data in this table as a single Vector<String> list.
	 * 
	 * @return
	 */
	public Vector<String> getAllDataAsVector()
	{
		Vector<String> ret = new Vector<String>();

		for (String xkey : getXKeys())
			for (String ykey : getYKeys())
			{
				String data = this.dataMap.get(xkey).get(ykey);
				ret.add(data);
			}
		return ret;
	}

	/**
	 * Reads from an Excel spreadsheet with a .xls extension.
	 */
	public static Table readFromExcel(String fileName, boolean useXKeysFromExcel)
	{
		Table t = new Table();

		try
		{
			FileInputStream fileInputStream = new FileInputStream(fileName);
			HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);

			// get the first sheet
			HSSFSheet worksheet = workbook.getSheetAt(0);

			TreeMap<Integer, String> keyMap = new TreeMap<Integer, String>();
			int startRow;
			if (useXKeysFromExcel)
			{
				// get xkeys
				HSSFRow keyRow = worksheet.getRow(0);

				for (int cellnum = 0; cellnum < keyRow.getLastCellNum(); cellnum++)
				{
					HSSFCell cell = keyRow.getCell(cellnum);

					String keyString = cell.getStringCellValue();

					keyMap.put(cellnum, keyString);
				}
				startRow = 1;
			}
			else
			{
				startRow = 0;
			}

			for (int rowNumber = startRow; rowNumber <= worksheet.getLastRowNum(); rowNumber++)
			{
				HSSFRow row = worksheet.getRow(rowNumber);

				if (row != null)
					for (int cellNumber = 0; cellNumber < row.getLastCellNum(); cellNumber++)
					{
						// System.out.println("---");
						// System.out.println("cell = " + cellNumber);
						// System.out.println("row = " + rowNumber);
						HSSFCell cell = row.getCell(cellNumber);
						if (cell == null)
							continue;

						// try to get the value as a string, if it fails, get it as a numeric
						String data;
						try
						{
							if (HSSFDateUtil.isCellDateFormatted(cell))
							{
								data = cell.getDateCellValue().toString();
								// System.out.println("DATE " + data);
							}
							else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
							{
								data = "" + cell.getNumericCellValue();
								// System.out.println("NUMERIC " + data);

							}
							else
							{
								data = cell.getStringCellValue();
								// System.out.println("STRING " + data);
							}
						}
						catch (IllegalStateException e)
						{
							// e.printStackTrace();
							data = cell.getStringCellValue();
							// System.out.println("ISE STRING " + data);
						}

						String xkey;
						if (useXKeysFromExcel)
							xkey = keyMap.get(cellNumber);
						else
							xkey = "column_" + cellNumber;

						if (xkey == null)
							continue;
						String ykey = "row_" + rowNumber;
						t.add(xkey, ykey, data);
					}
			}

			workbook.close();
		}
		catch (

		FileNotFoundException e)
		{
			e.printStackTrace();
			throw new IllegalArgumentException("File '" + fileName + "' does not exist.");
		}
		catch (IOException e)
		{
			e.printStackTrace();
			throw new IllegalArgumentException("IOException!");
		}

		return t;
	}

	/**
	 * Gets all rows with column name equal to the given value
	 * 
	 * @param string
	 * @param company1
	 * @return
	 */
	public Vector<String> getRows(String columnName, String value)
	{
		Vector<String> ret = new Vector<String>();

		for (String ykey : this.getYKeys())
		{
			String data = this.get(columnName, ykey);

			if (data != null)
				if (data.equals(value))
					ret.add(ykey);
		}

		return ret;
	}

}
