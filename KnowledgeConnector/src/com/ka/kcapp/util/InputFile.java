package com.ka.kcapp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.TreeSet;
import java.util.Vector;
import java.util.concurrent.ConcurrentSkipListMap;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;

/**
 * This class is responsible for opening and reading files at the level of raw data.
 * 
 * Construction reads all of a file's data at once. It caches previously opened file data. These two
 * qualities are to improve throughput and efficiency.
 */
public class InputFile
{
	final private Logger									logger				=
			Logger.getLogger(InputFile.class);

	public static char										NEARBY_DATA_WIDTH	= 40;
	public static char										UNIVERSAL_DELIMITER	= '|';

	/** Whitespace characters including carriage return, space, tab, line feed. **/
	private static final Vector<Character>					WHITESPACE;
	private static final Vector<Character>					SPACE_TAB;

	/** Reserved tokens used as delimiters for file formats. Brackets and parentheses. **/
	private static final Vector<Character>					RESERVED_TOKENS;
	// private Log log = new Log("InputFile", false);

	// final variables
	/** If enabled, we will save all read data into a static map **/
	private static final boolean							ENABLE_CACHING		= false;

	/** Token for end of file **/
	private static final char								EOF					= 0xffff;	// end
																							// of
																							// file
																							// constant
	private static final TreeSet<String>					PLAINTEXT_EXTENSIONS;

	/** Map of previously read data. We use this if ENABLE_CACHING is true **/
	private static ConcurrentSkipListMap<String, String>	dataMap				=
			new ConcurrentSkipListMap<String, String>();

	/** Data for this file **/
	private String											data;

	/** File name information **/
	private String											completeFileName;				// file
																							// name
																							// including
																							// all
																							// paths
	private String											rootPath;						// the
																							// root
																							// path
	private Vector<String>									paths;							// all
																							// subdirectory
																							// paths
	/** File's name without extension or path **/
	public String											name;
	private String											ext;							// file's
																							// extension

	public char												sym;
	private int												position, lineNumber;

	static
	{
		PLAINTEXT_EXTENSIONS = new TreeSet<String>();
		PLAINTEXT_EXTENSIONS.add("txt");
		PLAINTEXT_EXTENSIONS.add("java");

		WHITESPACE = new Vector<Character>();

		WHITESPACE.add((char) 32);
		WHITESPACE.add((char) 10);
		WHITESPACE.add((char) 13);
		WHITESPACE.add((char) 9);
		WHITESPACE.add((char) 0);

		SPACE_TAB = new Vector<Character>();

		SPACE_TAB.add((char) 32);
		SPACE_TAB.add((char) 9);

		RESERVED_TOKENS = new Vector<Character>();
		RESERVED_TOKENS.add('(');
		RESERVED_TOKENS.add(')');
		RESERVED_TOKENS.add('[');
		RESERVED_TOKENS.add(']');
	}

	/**
	 * clears cached data
	 */
	public static void clearCachedFiles()
	{
		for (String key : dataMap.keySet())
			dataMap.remove(key);
	}

	/**
	 * Creates a temporary input file from the given data so we can use our methods for parsing
	 * through the given String.
	 * 
	 * @param data
	 * @return
	 */
	public static InputFile getInputFileFromStringData(String data)
	{
		InputFile ret = new InputFile();

		ret.data = data;

		// the file can have a size of zero
		if (ret.data.length() != 0)
			ret.sym = data.charAt(0);
		else
			ret.sym = EOF;

		return ret;
	}

	/**
	 * Empty argument constructor used to convert String data to an input file.
	 */
	private InputFile()
	{
	}

	/**
	 * Constructor creates a new InputFile reader with the default encoding. If the file does not
	 * exists, throws an IllegalArgumentException.
	 * 
	 * @param fileName
	 */
	public InputFile(String fileName)
	{
		this(fileName, null);
	}

	/**
	 * Constructor creates a new InputFile reader with the default encoding. If the file does not
	 * exists, throws an IllegalArgumentException.
	 * 
	 * @param fileName
	 */
	public InputFile(String fileName, String encoding)
	{
		if (!FileUtility.exists(fileName))
		{
			IllegalArgumentException e = new IllegalArgumentException(
					"Trying to read '" + fileName + "', but it does not exist!");

			logger.debug(ExceptionUtils.getFullStackTrace(e));
			throw e;
		}
		if (FileUtility.emptyFile(fileName))
		{
			logger.debug("InputFile.constructor : file '" + fileName + "' happens to be empty");
		}
		// logger.debug("Loading input file " + fileName);

		this.completeFileName = fileName;
		parseFileName();
		readFile(encoding);

		// seek to the start in case we have already read the file
		// this resets the sym and position
		seek(0);
	}

	/**
	 * Close this file. Sets data to null, which after a while should free via the garbage
	 * collector.
	 */
	public void close()
	{
		// logger.debug("closing " + getFullFileName());
		data = null;
	}

	/**
	 * Parses the file name into data members: rootPath, paths, name, ext.
	 */
	private void parseFileName()
	{
		String delimiter = File.separator.equals("\\") ? "\\\\" : File.separator;
		Vector<String> tokens = StringUtility.tokenize(completeFileName, delimiter);

		// get paths
		rootPath = FileUtility.getCurrentWorkingDirectory();

		paths = new Vector<String>();
		for (int i = 0; i < tokens.size() - 1; i++)
			paths.add(tokens.get(i));

		// cut filename + ext into filename and extension
		String fe = tokens.get(tokens.size() - 1);

		int l = fe.lastIndexOf(".");
		if (l != -1)
		{
			name = fe.substring(0, l);
			ext = fe.substring(l + 1);
		}
		else
		{
			name = fe;
			ext = "";
		}

		// logger.debug(getInformation());
	}

	/**
	 * gets only the file name from the input string (ignoring the directory)
	 */
	public static String parseFileName(String fileName)
	{
		int lsi = fileName.lastIndexOf(File.separator);
		if (lsi == -1)
			return fileName;
		else
			return fileName.substring(lsi + 1);
	}

	/**
	 * gets only the directory from the input string (ignoring the file name)
	 */
	public static String parseFileDir(String fileName)
	{
		int lsi = fileName.lastIndexOf(File.separator);
		if (lsi == -1)
			return "";
		else
			return fileName.substring(0, lsi);
	}

	/**
	 * Gets information about file name, root path, all the sub paths, the total name and the
	 * extension. Gets information about the local data at the current position.
	 */
	public String getInformation()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("input file name is '" + completeFileName + "'\n");
		sb.append("      root path is '" + rootPath + "'\n");

		for (int i = 0; i < paths.size(); i++)
			sb.append("        path(" + i + ") is '" + paths.get(i) + "'\n");

		sb.append("           name is '" + name + "'\n");
		sb.append("            ext is '" + ext + "'\n");

		sb.append(getNearbyDataWithCaret());
		return sb.toString();
	}

	/**
	 * gets the path including root and subpaths
	 */
	public String getPath()
	{
		String r = rootPath;

		for (String path : paths)
			r += File.separator + path;

		return r;
	}

	static TreeSet<String> extensionSet = new TreeSet<String>();

	/**
	 * Reads all of the file into the data map. Note we are *not* using
	 * 
	 * byte[] byteData = Files.readAllBytes(Paths.get(completeFileName));
	 * 
	 * because it only works in Java 1.7+ for which Paths is not compatible with Android SDK.
	 * 
	 * @param encoding The encoding name. If we pass null, we use the default encoding.
	 */
	private synchronized void readFile(String encoding)
	{
		extensionSet.add(ext);

		synchronized (dataMap)
		{
			data = dataMap.get(completeFileName);

			if (data != null)
			{
				// logger.debug("InputFile.readFile : cached already " + completeFileName);
				// logger.debug("data = '" + data.substring(0, 10) + "...'");
				return;
			}
			else
			{
				// open the file
				File file = new File(completeFileName);
				long size = file.length();

				FileInputStream fileInputStream = null;
				// byte[] byteData = new byte[(int) file.length()];
				// try
				// {
				// // convert file into array of bytes
				// fileInputStream = new FileInputStream(file);
				// fileInputStream.read(byteData);
				// fileInputStream.close();
				// }

				// use an InputStreamReader to read it into UTF-16
				char[] byteData = new char[(int) file.length()];
				try
				{
					// convert file into array of bytes
					fileInputStream = new FileInputStream(file);

					// if encoding is null, get default encoding
					Charset charset;

					if (encoding == null)
						charset = Charset.defaultCharset();
					else
						charset = Charset.forName(encoding);

					InputStreamReader isr = new InputStreamReader(fileInputStream, charset);

					isr.read(byteData, 0, (int) file.length());
					isr.close();
					fileInputStream.close();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}

				data = new String(byteData);

				// replace double carriage return with single only in plaintext files
				double delta = 0;
				if (PLAINTEXT_EXTENSIONS.contains(ext))
				{
					int oldDataSize = data.length();
					data = data.replaceAll((char) (13) + "" + (char) (10), (char) (10) + "");
					int newDataSize = data.length();

					delta = oldDataSize - newDataSize;
					if (oldDataSize != newDataSize)
					{
						// logger.debug("InputFile.readFile WARNING data for file shrank from
						// "
						// + oldDataSize + "b to " + newDataSize + "b, delta = "
						// + (newDataSize - oldDataSize)
						// + "b after trying normalizing successive carraige returns. (i.e. replace
						// successive 13 10 carriage returns with 10)");
					}
				}

				if (ENABLE_CACHING)
					dataMap.put(completeFileName, data);

				// notifications if we didn't read everything
				int readBytes = data.length();
				double readRatio = (double) readBytes / (double) (size - delta);

				if (readRatio < 0.90)
				{
					throw new UnsupportedOperationException(
							"InputFile.readFile : read < 90% of file, only read "
									+ StringUtility.round(100 * readRatio) + "% of file '" + name
									+ "' (" + readBytes + "b of " + size + "b, missing "
									+ (size - readBytes) + "b)\n" + getSeekInformation());
				}

				if (readRatio != 1.0 && data.length() > 0)
					logger.debug("InputFile.readFile WARNING only read "
							+ StringUtility.round(100 * readRatio) + "% of file '" + name + "' ("
							+ readBytes + "b of " + size + "b, missing " + (size - readBytes) + "b)"
							+ getSeekInformation());
			}

			// the file can have a size of zero
			if (data.length() != 0)
				sym = data.charAt(0);
			else
				sym = EOF;
		}
	}

	/*******************************************************************************
	 * scanning functions
	 *******************************************************************************/

	/**
	 * Loads the next character from the file into sym
	 */
	public void next()
	{
		position++;
		if (position >= data.length()) // EOF condition
		{
			sym = EOF;
			return;
		}
		sym = data.charAt(position);
		if (sym == '\n')
			lineNumber++;
	}

	/**
	 * Reads until the end of the current line and returns the string. The return value does not
	 * include the carriage return, however it is read past for this InputFile.
	 */
	public String readLine()
	{
		String ret = skipUntil('\n'); // this is number 10
		if (eof() == false)
			read('\n');
		lineNumber++;
		return ret;
	}

	/**
	 * Skips through until sym is not whitespace or EOF
	 */
	public void skipWhiteSpace()
	{
		while (WHITESPACE.contains(sym) && sym != EOF)
			next();
	}

	/**
	 * Skips through until sym is not tab, space, or EOF
	 */
	public void skipSpacesAndTabs()
	{
		while (SPACE_TAB.contains(sym) && sym != EOF)
			next();
	}

	/**
	 * Returns the string formed by skipping until we find whitespace or EOF
	 */
	public String skipUntilWhiteSpace()
	{
		String ret = "";

		while (!WHITESPACE.contains(sym) && !eof())
		{
			ret += sym;
			next();
		}
		return ret;
	}

	

	/**
	 * Returns the string formed by skipping until we find either whitespace or any of the
	 * characters in the given string, or EOF.
	 * 
	 * @param string
	 * @return
	 */
	public String skipUntilWhiteSpaceOr(String string)
	{
		String ret = "";

		while (!WHITESPACE.contains(sym) && !string.contains(sym + "") && !eof())
		{
			ret += sym;
			next();
		}

		return ret;
	}

	/**
	 * Returns the String formed by skipping until the given character reached is reached (or EOF),
	 * it returns the string until that point (implemented twice for speed).
	 * 
	 * If we are at the EOF it returns null.
	 * 
	 * If we do not reach the given skip character, it will return the string up to that point
	 * (EOF).
	 */
	public String skipUntil(char skipChar)
	{
		if (eof() == true)
			return null;

		String ret = "";

		int newPosition = data.indexOf(skipChar, position);
		if (newPosition != -1)
		{
			ret = data.substring(position, newPosition);
			position = newPosition;
			sym = data.charAt(newPosition);
		}
		else
		// eof
		{
			ret = data.substring(position, data.length());
			position = data.length();
			sym = EOF;
		}

		return ret;
	}

	/**
	 * Returns the string formed by skipping until any of the characters in the given string are
	 * reached (or EOF), returns the string until that point.
	 */
	public String skipUntil(String skipString)
	{
		StringBuilder r = new StringBuilder();
		while (sym != EOF)
		{
			// System.out.print ("skip '" + sym + "'");
			if (skipString.contains(sym + ""))
			{
				return r.toString();
			}
			else
			{
				r.append(sym);
				next();
			}
		}
		// log.add("InputFile.skip (String skipString) : skipped to EOF");
		return r.toString();
	}

	/**
	 * Returns the next data in the scanning so long as we match what's in the given string
	 * 
	 * @param skipString
	 * @return
	 */
	public String skipWhile(String whileString)
	{
		StringBuilder r = new StringBuilder();
		while (sym != EOF)
		{
			if (whileString.contains(sym + ""))
			{
				r.append(sym);
				next();
			}
			else
			{
				return r.toString();
			}
		}
		// log.add("InputFile.skipWhile (String whileString) : skipped to EOF");
		return r.toString();
	}

	/**
	 * Skips until whitespace or a semicolon, returns the string read until then.
	 * 
	 * @return
	 */
	public String skipUntilWhitespaceOrSemiColon()
	{
		return skipUntil(" \t\n;");
	}

	/**
	 * Advances position until you found given string (or EOF)
	 */
	public String skipToString(String skipString)
	{
		int op = position;
		int n = data.indexOf(skipString, position);

		if (n == -1)
		{
			position = data.length();
			sym = EOF;
			return "";
		}
		else
		{
			position = n;
			sym = data.charAt(position);
			return data.substring(op, n);
		}
	}

	/**
	 * Skips to the specified string then eats it.
	 */
	public void skipToAndEat(String skipString)
	{
		skipToString(skipString);
		if (eof())
			return;
		read(skipString);
	}

	/**
	 * Skips to a string if it exists, and returns true or false depending we were able to read what
	 * we wanted to.
	 */
	public boolean skipPast(String s)
	{
		while ((eof() == false) && (tryToRead(s) == false))
			next();

		if (eof())
			return false;
		else
			return true;
	}

	/**
	 * Tries to read a String and returns true if it is successful and advances, if false it does
	 * not advance.
	 * 
	 * @param expectedString The string we expected to encounter.
	 */
	public boolean tryToRead(String expectedString)
	{
		boolean advance = true;
		return tryToRead(expectedString, advance);
	}

	/**
	 * Tries to read a String and returns true if it is successful it will advance if the advance
	 * flag is true, if false it does not advance.
	 * 
	 * @param expectedString The string we expected to encounter.
	 * @param boolean advance Should we advance?
	 */
	public boolean tryToRead(String expectedString, boolean advance)
	{
		boolean ret;
		if (position + expectedString.length() > data.length())
		{
			// logger.debug("ttr beyond end of data, sym = " + sym);
			ret = false;
		}
		else
		{
			String dataString = data.substring(position, position + expectedString.length());

			if (dataString.equals(expectedString) == true)
			{
				// advance the position
				if (advance)
					position += expectedString.length();

				// get the new symbol, it could be EOF
				if (position == data.length())
					sym = EOF;
				else
					sym = data.charAt(position);

				ret = true;
			}
			else
			{
				ret = false;
			}

			if (!ret)
			{
				// logger.debug("ttr expects = '" + expectedString
				// + "', position = " + position
				// + ", position + expectedString.length() = "
				// + (position + expectedString.length()));
				// logger.debug("data reveals '" + dataString + "'");
				// logger.debug(getNearbyData());
				// logger.debug("ttr ret = " + ret);
			}
		}
		return ret;
	}

	/**
	 * Tries to read a character and returns true if it is successful and advances, if false it does
	 * not advance.
	 * 
	 * @param expectedString The string we expected to encounter.
	 */
	public boolean tryToRead(char expectedCharacter)
	{
		return tryToRead(expectedCharacter + "");
	}

	/**
	 * Eats a necessary string, use if you know that the file is supposed to be at a particular
	 * string. If we don't get the required character, it will fail.
	 */
	public void read(char expectedCharacter)
	{
		if (expectedCharacter != sym)
		{
			String message = getReadErrorMessage(expectedCharacter + "", sym + "");
			throw new UnsupportedOperationException(message);
		}
		next();
	}

	/**
	 * eats a necessary string, use if you know that the file is supposed to be at a particular
	 * string
	 */
	public void read(String expectedString)
	{
		int len = expectedString.length();

		if (len == 1)
		{
			read(expectedString.charAt(0));
			return;
		}

		if (position + len > data.length())
		{
			StringBuilder message = new StringBuilder();

			message.append("InputFile.read (String expectedString : expectedString '"
					+ expectedString + "' goes past file size of " + data.length() + "\n");
			message.append(getSeekInformation());

			throw new UnsupportedOperationException(message.toString());
		}
		String actualString = data.substring(position, position + len);

		if (actualString.equals(expectedString) == false)
		{
			String message = getReadErrorMessage(expectedString + "", actualString);
			throw new UnsupportedOperationException(message);
		}
		else
		{
			position += len;
			if (position >= data.length())
				sym = EOF;
			else
				sym = data.charAt(position);
		}
	}

	/**
	 * Gets an error message for an read(.) function call.
	 * 
	 * @param expectedString
	 * @param actualString
	 * @return
	 */
	public String getReadErrorMessage(String expectedString, String actualString)
	{
		StringBuilder message = new StringBuilder();
		message.append("\nRead     '" + actualString + "'\n");
		message.append("Expected '" + expectedString + "'\n");
		message.append(getSeekInformation());

		String s = getNearbyData();
		message.append("line is '" + s + "'\n");
		message.append(this.getNearbyDataWithCaret());

		return message.toString();
	}

	/**
	 * Returns a String containing the seek information of where the file is looking at.
	 * 
	 * The position, line number, and completed file name.
	 * 
	 * @return
	 */
	private String getSeekInformation()
	{
		String seekInformation = "";
		seekInformation += "  position " + position + "\n";
		seekInformation += "  line " + lineNumber + "\n";
		String ret = getInformation() + "\n" + seekInformation;
		return ret;
	}

	/**
	 * returns true if we are at the end of the file, else false
	 */
	public boolean eof()
	{
		if (sym == EOF)
			return true;
		else
			return false;
	}

	/**
	 * Reads a string until the UNIVERSAL_DELIMITER, then eats the UNIVERSAL_DELIMITER
	 */
	public String readStandard()
	{
		skipWhiteSpace();

		String ret;
		ret = this.skipUntil(UNIVERSAL_DELIMITER);
		read(UNIVERSAL_DELIMITER);
		return ret;
	}

	/**
	 * reads a number in scientific notation
	 */
	public Double readDouble()
	{
		return readSciNot();
	}

	/**
	 * reads a number in scientific notation
	 */
	private Double readSciNot()
	{
		skipWhiteSpace();
		String n = skipUntil("<>,)(\n\t ;" + UNIVERSAL_DELIMITER);
		// skipWhiteSpace();

		try
		{
			// log.add (" rSN : '" + n + "'");
			Double d = new Double(n);
			return d;
		}
		catch (NumberFormatException e)
		{
			// log.add ("InputFile.readSciNot : " + e);
			return Double.NaN;
		}
	}

	/**
	 * Reads a double, then the delimiter.
	 * 
	 * @return
	 */
	public double readDoubleStandard()
	{
		double data = readDouble();
		read(UNIVERSAL_DELIMITER);

		return data;
	}

	/**
	 * Reads an integer from this file. An integer is a series of characters that are all digits 0
	 * to 9. Does not eat a STANDARD_DELIMETER (a pipe).
	 */
	public int readInteger()
	{
		skipWhiteSpace();
		String stringNumber = skipWhile("0123456789");

		if (stringNumber.equals(""))
			throw new UnsupportedOperationException(
					"No int to read\n" + getNearbyDataWithCaret() + "\n" + getSeekInformation());

		// log.add("InputFile.readInteger : n is '" + stringNumber + "'");
		Integer ret = new Integer(stringNumber);
		return ret;
	}

	

	/**
	 * Returns the data from this file
	 * 
	 * @return
	 */
	public String getData()
	{
		return data;
	}

	/**
	 * Are we at the end of a line?
	 * 
	 * @return
	 */
	public boolean eol()
	{
		return sym == '\n' || sym == EOF;
	}

	/**
	 * Returns the size of this file
	 * 
	 * @return
	 */
	public int size()
	{
		return data.length();
	}

	/**
	 * Returns the current position of the file
	 * 
	 * @return
	 */
	public int getPosition()
	{
		return position;
	}

	/**
	 * Returns the full file name of this input file, including the full path to the file.
	 * 
	 * @return The full path to the file.
	 */
	public String getFullFileName()
	{
		StringBuilder sb = new StringBuilder();

		for (String path : paths)
		{
			sb.append(path);
			sb.append(File.separator);
		}

		sb.append(name);
		if (ext != null)
		{
			if (!ext.equals(""))
			{
				sb.append(".");
				sb.append(ext);
			}
		}

		return sb.toString();
	}

	/**
	 * Reads the rest of the file and returns a String of the data.
	 * 
	 * @return
	 */
	public String readRestOfFile()
	{
		sym = EOF;
		position = data.length();
		return data.substring(position);
	}

	/**
	 * Returns the character n characters past current one. If it is EOF, returns EOF. Does *not*
	 * eat anything or move the position.
	 * 
	 * @return The character at the specfied number of characters ahead of the current position. If
	 *         that position is beyond the data, returns EOF.
	 */
	public char lookaheadChar(int n)
	{
		int index = position + n;

		char ret;
		if (index >= this.data.length())
			ret = EOF;
		else
			ret = data.charAt(index);

		logger.debug("lookahead is " + ret + ", sym is " + sym);
		return ret;
	}

	/**
	 * Gets some of the nearby data.
	 * 
	 * @return
	 */
	public String getNearbyData()
	{
		return StringUtility.getStringWindow(data, position, NEARBY_DATA_WIDTH);
	}

	/**
	 * Gets some of the nearby data with a caret under the current position.
	 * 
	 * @return A string with two lines. The first line contains the nearby data in this position in
	 *         the file. The second line contains the caret indicating the position of the current
	 *         symbol.
	 */
	public String getNearbyDataWithCaret()
	{
		int nspaces = position < NEARBY_DATA_WIDTH ? 3 + position : NEARBY_DATA_WIDTH + 3;
		return "'" + StringUtility.getStringWindow(data, position, NEARBY_DATA_WIDTH) + "'\n"
				+ StringUtility.getSpaces(nspaces) + " ^";
	}

	/**
	 * Seeks to the given position. If it's before the file, seeks to start. If it's after the file,
	 * seeks to EOF.
	 * 
	 * @param position
	 */
	public void seek(int newPosition)
	{
		position = newPosition;
		if (position >= data.length()) // EOF condition
		{
			sym = EOF;
			return;
		}
		else if (position < 0)
			position = 0;

		sym = data.charAt(position);
		if (sym == '\n')
			lineNumber++;
	}

	/**
	 * Accessor for line number.
	 * 
	 * @return The current line number.
	 */
	public Integer getLineNumber()
	{
		return this.lineNumber;
	}

	/**
	 * Reads a long from the file.
	 * 
	 * @return
	 */
	public long readLong()
	{
		skipWhiteSpace();
		String stringNumber = skipWhile("0123456789");

		if (stringNumber.equals(""))
			throw new UnsupportedOperationException(
					"No long to read\n" + getNearbyDataWithCaret() + "\n" + getSeekInformation());

		// log.add("InputFile.readLong : n is '" + stringNumber + "'");
		Long ret = new Long(stringNumber);
		return ret;
	}

	/**
	 * Reads a string until you've got some whitespace.
	 * 
	 * @return
	 */
	public String readString()
	{
		return skipUntilWhiteSpace();
	}

	/**
	 * Reads a quoted string. Quotes may be single or double quotes. Returns what was read without
	 * the quotes.
	 * 
	 * @return
	 */
	public String readQuotedString()
	{
		char quote;
		if (sym == '"')
			quote = '"';
		else if (sym == '\'')
			quote = '\'';
		else
			throw new UnsupportedOperationException("Expected single or double quote.  Got sym = '"
					+ sym + "'.\n" + this.getNearbyDataWithCaret());

		next();
		String ret = this.skipUntil(quote);
		if (this.eof())
			throw new UnsupportedOperationException(
					"Unterminated double quote string.\n" + this.getNearbyDataWithCaret());

		read(quote);
		return ret;
	}

	/**
	 * Reads all data until we hit a tab or EOF.
	 */
	public String readStringUntilTab()
	{
		return skipUntil("\t");
	}

	/**
	 * Returns true if the data near the current position of the file is the given String. Does
	 * *not* advance.
	 */
	public boolean dataIs(String input)
	{
		int len = input.length();

		if (position + len > data.length())
			return false;

		String test = data.substring(position, position + len);

		return test.equals(input);
	}

	/**
	 * Skips past any line comments in the file. Skips past any whitespace before any line comments
	 * and any whitespace after any line comments. And any whitespace in between line comments.
	 */
	public void skipPastLineComments()
	{
		skipWhiteSpace();
		while (this.dataIs("//") && !eof())
		{
			readLine();
			skipWhiteSpace();
		}
		skipWhiteSpace();
	}

	/**
	 * Returns the length of the file.
	 * 
	 * @return
	 */
	public int length()
	{
		return data.length();
	}

}
