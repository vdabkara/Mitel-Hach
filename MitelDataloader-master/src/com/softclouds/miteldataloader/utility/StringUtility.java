package com.softclouds.miteldataloader.utility;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.text.DecimalFormat;
import java.util.List;
import java.util.TreeSet;
import java.util.Vector;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;

import org.apache.commons.io.IOUtils;

/**
 * Static string utility class with methods for manipulating strings.
 * 
 * @author Ray
 *
 */
public class StringUtility
{
	// ANSI color variables
	public static final String									ANSI_RESET						=
			"\u001B[0m";
	public static final String									ANSI_WHITE						=
			"\u001B[47m";
	public static final String									ANSI_BLACK						=
			"\u001B[40m" + ANSI_WHITE;
	public static final String									ANSI_RED						=
			"\u001B[41m" + ANSI_WHITE;
	public static final String									ANSI_GREEN						=
			"\u001B[42m" + ANSI_WHITE;
	public static final String									ANSI_YELLOW						=
			"\u001B[43m" + ANSI_WHITE;
	public static final String									ANSI_BLUE						=
			"\u001B[44m" + ANSI_WHITE;
	public static final String									ANSI_PURPLE						=
			"\u001B[45m" + ANSI_WHITE;
	public static final String									ANSI_CYAN						=
			"\u001B[46m" + ANSI_WHITE;

	// final variables
	static final int											WORD_LENGTH_LIMIT				=
			16;
	public static final int										CONSOLE_WIDTH					=
			150;

	static final ConcurrentSkipListMap<String, Vector<String>>	macros							=
			readMacros();
	public static final String									WHITESPACE_REGEX				=
			" |\\n|\\t";
	public static final String									CARRIAGE_RETURN_REGEX			=
			"\\n";
	public static final String									TAB_REGEX						=
			"\\t";
	public static final String									DOUBLE_QUOTE_REGEX				=
			"\"";
	public static final String									COMMA_REGEX						=
			",";
	public static final String									WHITESPACE_TAB_REGEX			=
			WHITESPACE_REGEX + "|" + TAB_REGEX;
	public static final String									WHITESPACE_COMMA_REGEX			=
			WHITESPACE_REGEX + "|" + COMMA_REGEX;
	public static final String									WHITESPACE_PUNCTUATION_REGEX	=
			WHITESPACE_COMMA_REGEX + "|\\p{Punct}";
	public static final String									PIPE_REGEX						=
			"\\|";
	public static final String									CR_1013							=
			((char) 10) + "" + ((char) 13);
	private static final boolean								XML_PREPEND						=
			false;

	/**
	* 
	*/
	public static void searchAndReplace(String directory, String extension, String src, String dest)
	{
		Vector<String> fileNames = FileUtility.getFileList(directory, extension);

		System.out.println("StringUtility.searchAndReplace : " + fileNames
				+ " files to replace from directory " + directory + " of type " + extension);

		int replacements = 0;
		for (String fileName : fileNames)
		{
			Vector<String> lines = FileUtility.readLines(fileName);

			OutputFile outputFile = new OutputFile(fileName);

			for (String s : lines)
			{
				String sr = s.replace(src, dest);

				if (sr.equals(s) == false)
					replacements++;

				outputFile.writeln(sr);
			}
			outputFile.close();
			System.out.println(fileName);
		}
		System.out.println(replacements + " replacements made (" + src + " -> " + dest + ")");
	}

	/**
	 * Reads substitution macros for word groups, categories, etc.
	 */
	private static ConcurrentSkipListMap<String, Vector<String>> readMacros()
	{
		System.out.print("StringUtility.readMacros() : initializing macros... ");
		ConcurrentSkipListMap<String, Vector<String>> ret =
				new ConcurrentSkipListMap<String, Vector<String>>();

		Vector<String> lines = FileUtility.readLines(
				"data" + File.separator + "rundata" + File.separator + "commandLineMacros.txt");

		int lineNumber = 0;
		for (String line : lines)
		{
			if (line.equals("") == false)
			{
				Vector<String> k12 = tokenize(line, "=");

				if (k12.size() != 2)
				{
					System.out.println("invalid input at line " + lineNumber + " (" + k12.size()
							+ " tokens), line is '" + line + "'");
					for (int i = 0; i < k12.size(); i++)
						System.out.println("  token " + i + " : '" + k12.get(i) + "'");
					System.exit(0);
				}

				String key = k12.get(0).trim();
				String value = k12.get(1).trim();

				Vector<String> valueList = tokenize(value, " ");

				ret.put(key, valueList);
				// System.out.println (key + " : " + valueList);
			}
			lineNumber++;
		}

		System.out.println(ret.size() + " macros found");

		return ret;
	}

	/**
	 * Fixes title capitalization i.e. makes "WHATEVER The title is supposed TO BE" ->
	 * "Whatever the Title is Supposed to Be". Does not capitalize words of length 3 or less.
	 */
	public static String titleCapitalization(String s)
	{
		Vector<String> tokens = tokenize(s, " ");

		int n = 0;

		String ret = "";

		for (String token : tokens)
		{
			token = token.toLowerCase();

			if ((token.length() > 3) || (n == 0) || (n == tokens.size() - 1))
			{
				String firstLetter = token.charAt(0) + "";
				String lastPart = token.substring(1);

				ret += firstLetter.toUpperCase() + lastPart + " ";
			}
			else
			{
				ret += token + " ";
			}
			n++;
		}

		ret = trim(ret, 1);
		return ret;
	}

	/**
	 * Fixes title capitalization i.e. makes "WHATEVER The title is supposed TO BE" ->
	 * "Whatever the Title is Supposed to Be". Does not capitalize words of length 3 or less.
	 */
	public static Vector<String> titleCapitalization(Vector<String> strs)
	{
		Vector<String> ret = new Vector<String>();

		for (String s : strs)
			ret.add(titleCapitalization(s));

		return ret;
	}

	/**
	 * skips a given list of index strings, delimited by a vertical bar "|"
	 */
	public static int skipStrings(String input, String skipString)
	{
		Vector<String> skipStrings = tokenize(skipString, "\\|");

		System.out.println("skipping " + skipStrings);

		int ret = 0;

		for (String s : skipStrings)
		{
			ret = input.indexOf(s, ret + 1);

			// System.out.println (" " + ret + " " + s);
			if (ret == -1)
				return ret;
		}

		return ret;
	}

	/**
	 * gets all depth one category lists. performs one level of substitution and Returns substrings
	 */
	public static ConcurrentSkipListMap<String, ConcurrentSkipListSet<String>>
			depthOneCategoryLists(String seed)
	{
		// System.out.println ("finding depth one category sets for seed '" +
		// seed + "'");
		Vector<String> tokenSeed = tokenize(seed, " ");
		Vector<String> categoryLabels = replaceAllOnce(tokenSeed);

		ConcurrentSkipListMap<String, ConcurrentSkipListSet<String>> ret =
				new ConcurrentSkipListMap<String, ConcurrentSkipListSet<String>>();
		for (String categoryLabel : categoryLabels)
		{
			ConcurrentSkipListSet<String> words =
					new ConcurrentSkipListSet<String>(replaceOnce(categoryLabel));
			ret.put(categoryLabel, words);

			// System.out.print (" " + categoryLabel + "\t: ");
			// for (String s: words) System.out.print (s + " ");
			// System.out.println ();
		}
		// System.out.println (" " + ret.size() + " categories in total");
		return ret;
	}

	/**
	 * this is the only replace all function you can use outside this class
	 */
	public static String replaceAll(String input)
	{
		// System.out.println ("replace all called with '" + input + "'");
		Vector<String> newInput = tokenize(input, " ");
		Vector<String> oldInput;

		do
		{
			oldInput = newInput;
			newInput = replaceAllOnce(oldInput);
			// System.out.println (" " + newInput);
		}
		while (oldInput.equals(newInput) == false);

		String ret = "";
		for (String token : newInput)
			ret += token + " ";

		return ret;
	}

	/**
	 * Replaces macros once for a vector of strings
	 */
	private static Vector<String> replaceAllOnce(Vector<String> tokens)
	{
		Vector<String> ret = new Vector<String>();

		for (String token : tokens)
		{
			Vector<String> expansions = replaceOnce(token);
			ret.addAll(expansions);
		}

		return ret;
	}

	/**
	 * Replaces macros once for a given string
	 */
	private static Vector<String> replaceOnce(String token)
	{
		for (String key : macros.keySet())
			if (key.equals(token))
				return macros.get(key);

		Vector<String> ret = new Vector<String>();
		ret.add(token);
		return ret;
	}

	/**
	 * Pads zeroes to the left hand side of the number up to the number of zeroes specified.
	 */
	public static String padZeroes(int numberToPad, int numberOfZeroes)
	{
		return padZeroes((long) numberToPad, numberOfZeroes);
	}

	/**
	 * Pads zeros to the left hand side of the number up to 2 zeroes.
	 * 
	 * @param monthOfYear
	 * @return
	 */
	public static String padZeroes(int numberToPad)
	{
		return padZeroes(numberToPad, 2);
	}

	/**
	 * Pads zeroes to the left hand side of the number up to the number of zeroes specified.
	 */
	public static String padZeroes(long numberToPad, int numberOfZeroes)
	{
		String format = "";

		for (int i = 0; i < numberOfZeroes; i++)
			format += "0";

		DecimalFormat myFormatter = new DecimalFormat(format);

		String output = myFormatter.format(numberToPad);

		return output;
	}

	/**
	 * Pads spaces to this string.
	 */
	public static String padSpaces(String input)
	{
		return padSpaces(input, WORD_LENGTH_LIMIT);
	}

	/**
	 * Prints a line to the right side of the console.
	 */
	public static void printlnR(String input)
	{
		System.out.println(consolePadSpaces(input));
	}

	/**
	 * Pads spaces to this string the length of the console
	 */
	public static String consolePadSpaces(String input)
	{
		return padSpaces(input, CONSOLE_WIDTH);
	}

	/**
	 * Pads spaces to this string the length of the console
	 */
	public static String consolePadSpacesR(String input)
	{
		return padSpacesR(input, CONSOLE_WIDTH);
	}

	/**
	 * Is this string an integer?
	 */
	public static boolean isInteger(String input)
	{
		try
		{
			new Integer(input);
		}
		catch (NumberFormatException e)
		{
			// System.out.println ("ioString.isInteger : NumberFormatException "
			// + e);
			return false;
		}
		return true;
	}

	/**
	 * Pads spaces to the right of the string
	 */
	public static String padSpacesR(String input)
	{
		return padSpacesR(input, WORD_LENGTH_LIMIT);
	}

	/**
	 * Pads spaces to the left of the string. Upto 16 spaces.
	 */
	public static String padSpaces(int numberToPad)
	{
		return padSpaces(numberToPad + "");
	}

	/**
	 * Pads spaces to the left of the string. Upto 16 spaces.
	 */
	public static String padSpaces(Double numberToPad)
	{
		return padSpaces(numberToPad + "");
	}

	/**
	 * Pads spaces to the left of the string given a number of spaces to pad
	 */
	public static String padSpaces(String input, int numberOfSpaces)
	{
		int l = input.length();
		return getSpaces(numberOfSpaces - l) + input;
	}

	/**
	 * Pads spaces to the right of the string given a number of spaces to pad
	 */
	public static String padSpacesR(String input, int numberOfSpaces)
	{
		int l = input.length();
		return input + getSpaces(numberOfSpaces - l);
	}

	/**
	 * Pads spaces to the left of the integer given a number of spaces to pad
	 */
	public static String padSpaces(int input, int numberOfSpaces)
	{
		return padSpaces(input + "", numberOfSpaces);
	}

	/**
	 * Pads spaces to the left of the double given a limit of spaces to pad
	 */
	public static String padSpaces(Double input, int numberOfSpaces)
	{
		return padSpaces(input + "", numberOfSpaces);
	}

	/**
	 * Returns a number of spaces or nothing if n is non-positive.
	 */
	public static String getSpaces(int n)
	{
		StringBuilder ret = new StringBuilder("");
		while (ret.length() < n)
			ret.append(" ");
		return ret.toString();
	}

	/**
	 * Returns a number of tabs or nothing if n is non-positive.
	 */
	public static String getTabs(int n)
	{
		StringBuilder ret = new StringBuilder("");
		while (ret.length() < n)
			ret.append("\t");
		return ret.toString();
	}

	/**
	 * Returns a number of underscores or nothing if n is non-positive
	 */
	public static String getUnderscores(int n)
	{
		StringBuilder ret = new StringBuilder("");
		while (ret.length() < n)
			ret.append("_");
		return ret.toString();
	}

	/**
	 * Replaces all tabs with spaces, replaces all multiple spaces with one space Does *not* Replace
	 * carriage returns
	 * 
	 * @param input
	 * @return
	 */
	public static String reduceAllWhiteSpace(String input)
	{
		String ret = input;
		ret.replace('\t', ' ');

		String oldRet;
		do
		{
			oldRet = ret;
			ret = ret.replaceAll("  ", " ");
		}
		while (!oldRet.equals(ret));

		ret = ret.trim();
		return ret;
	}

	/**
	 * it cuts a fix number of left and right edges unlike String.trim(), by assumption, trim
	 * generally trims from the right
	 */
	public static String trimTrailingWhiteSpace(String input)
	{
		String ret = input;

		if (ret.length() == 0)
			return input;
		char lastChar = ret.charAt(ret.length() - 1);
		while ((lastChar == '\t') || (lastChar == ' '))
		{
			ret = trim(input, 1);
			lastChar = ret.charAt(ret.length() - 1);
		}
		return ret;
	}

	/**
	 * trims stuff before a given character (including the character)
	 */
	public static String trimLC(String s, char c)
	{
		int index = s.indexOf(c);
		return s.substring(index + 1);
	}

	/**
	 * Trims the string from both sides. If the string is less than the leftTrim size, or the
	 * rightTrim size, it will return the whole string and nothing will get trimmed.
	 * 
	 * @param input The string to trim.
	 * @param leftTrim The number of characters to trim on the left hand side.
	 * @param rightTrim The number of characters to trim on the right hand side.
	 */
	public static String trim(String input, int leftTrim, int rightTrim)
	{
		if (input == null)
			throw new IllegalArgumentException("input is null");

		double length = input.length();
		if (length < leftTrim)
			return input;
		if (length < rightTrim)
			return input;

		return input.substring(leftTrim, input.length() - rightTrim);
	}

	/**
	 * trims the string from the right
	 */
	public static String trim(String input, int rightTrim)
	{
		return trim(input, 0, rightTrim);
	}

	/**
	 * Trims leading and trailing single and double quotes from this string.
	 * 
	 * @param input
	 * @return
	 */
	public static String trimQuotes(String input)
	{
		if (input == null)
			throw new IllegalArgumentException("input is null");
		String regex = "'\"";
		String ret = trim(input, regex);

		return ret;
	}

	/**
	 * Performs string tokenization. Note that 'delimiters' is a regular expression.
	 * 
	 * It *does* trim tokens by default.
	 * 
	 * It does *not* include empty strings for consecutive delimiters.
	 */
	public static Vector<String> tokenize(String input, String regex)
	{
		boolean trim = true;
		boolean includeEmpty = false;
		return tokenize(input, regex, includeEmpty, trim);
	}

	/**
	 * Tokenizes by whitespace.
	 * 
	 * It does *not* include empty tokens.
	 * 
	 * It *does* trim tokens.
	 * 
	 * @param input
	 * @return
	 */
	public static Vector<String> tokenizeByWhitespace(String input)
	{
		return tokenize(input, WHITESPACE_REGEX);
	}

	/**
	 * Tokenizes by whitespace or commas.
	 * 
	 * It does *not* include empty tokens.
	 * 
	 * It *does* trim tokens.
	 * 
	 * @param input
	 * @return
	 */

	public static Vector<String> tokenizeByWhiteSpaceAndCommas(String input)
	{
		return tokenize(input, WHITESPACE_COMMA_REGEX);
	}

	/**
	 * Tokenizes by carriage returns.
	 * 
	 * @param input
	 * @return
	 */
	public static Vector<String> tokenizeByLine(String input)
	{
		return tokenize(input, CARRIAGE_RETURN_REGEX);
	}

	/**
	 * Tokenizes by double quoted strings. Does not allow you to escape quotes inside quotes. It
	 * will trim tokens by default.
	 * 
	 * @param input
	 * @return
	 */
	public static Vector<String> tokenizeByQuotedString(String input)
	{
		return tokenize(input, DOUBLE_QUOTE_REGEX);
	}

	/**
	 * Tokenizes by tabs.
	 * 
	 * It *does* include empty strings.
	 *
	 * It *does* trim strings.
	 * 
	 * @param line
	 * @return
	 */
	public static Vector<String> tokenizeByTabs(String input)
	{
		String delimiter = StringUtility.TAB_REGEX;
		boolean includeEmpty = true;
		boolean trim = true;
		return tokenize(input, delimiter, includeEmpty, trim);
	}

	/**
	 * Tokenizes by commas.
	 * 
	 * It *does* include empty strings.
	 * 
	 * It *does* trim strings.
	 * 
	 * @param line
	 * @return
	 */
	public static Vector<String> tokenizeByCommas(String input)
	{
		String delimiter = StringUtility.COMMA_REGEX;
		boolean includeEmpty = true;
		boolean trim = true;
		return tokenize(input, delimiter, includeEmpty, trim);
	}

	/**
	 * Performs string tokenization. Note that 'delimiter' is a single character. does *not* include
	 * delimiters by default *does* trim tokens by default
	 */
	public static Vector<String> tokenize(String input, char delimiter)
	{
		return tokenize(input, delimiter + "");
	}

	/**
	 * Performs string tokenization.
	 * 
	 * It does *not* include empty strings.
	 */
	public static Vector<String> tokenize(String input, String delimiter, boolean trim)
	{
		boolean includeEmpty = false;
		return tokenize(input, delimiter, includeEmpty, trim);
	}

	/**
	 * Performs string tokenization. Note it does included empty tokens.
	 * 
	 * @param input The string to be tokenized.
	 * @param regex The regular expression of delimiters to use.
	 * @param includeEmpty Should we include empty strings?
	 * @param trim Should we trim each token?
	 */
	public static Vector<String> tokenize(String input, String regex, boolean includeEmpty,
			boolean trim)
	{
		if (input == null)
			return new Vector<String>();

		String[] strs = null;
		/** add -1 to not have a limit on results, to include trailing whitespace delimitings **/
		strs = input.split(regex, -1);

		Vector<String> ret = new Vector<String>();
		for (int i = 0; i < strs.length; i++)
			if (!strs[i].equals("") || includeEmpty)
				ret.add(strs[i]);

		return ret;
	}

	/**
	 * Returns scientific notation for a given value
	 */
	public static String getSciNot(double input)
	{
		return getSciNot(input, 3);
	}

	/**
	 * Returns scientific notation for a given value
	 */
	public static String getSciNot(double input, int numberOfDecimalPlaces)
	{
		String format = "0";

		if (numberOfDecimalPlaces > 0)
		{
			format += ".";
			for (int i = 0; i < numberOfDecimalPlaces; i++)
				format += "0";
		}
		format += "E0";

		DecimalFormat myFormatter = new DecimalFormat(format);

		String ret = myFormatter.format(input);

		return ret;
	}

	/**
	 * Rounds the number to given decimal places
	 */
	public static String round(Double input, int numberOfDecimalPlaces)
	{
		if (Double.isInfinite(input))
			return "inf";
		if (Double.isNaN(input))
			return "NaN";
		if (numberOfDecimalPlaces == 0)
			return input.intValue() + "";
		String format = ".";

		for (int i = 0; i < numberOfDecimalPlaces; i++)
			format += "0";

		DecimalFormat myFormatter = new DecimalFormat(format);
		String output = myFormatter.format(input);

		// add leading zero
		Double rf = new Double(output);
		if ((rf >= 0.0) && (rf < 1.0))
			output = "0" + output;
		else if ((rf > -1.0) && (rf < 0))
			output = "-0." + output.substring(2);

		return output;
	}

	/**
	 * Rounds to the nearest 100th place, default behavior for this method to specify precision, add
	 * another integer parameter for # of decimal places
	 * 
	 */
	public static String round(Double x)
	{
		return round(x, 2);
	}

	/**
	 * Prints a map of strings into organized columns
	 */
	public static void printColumns(ConcurrentSkipListMap<String, Vector<String>> input)
	{
		Vector<String> result = toColumns(input);
		for (String s : result)
			System.out.println(s);
	}

	/**
	 * converts a map of column-strings data to a list of strings we can print out directly
	 */
	private static Vector<String> toColumns(ConcurrentSkipListMap<String, Vector<String>> input)
	{
		Vector<String> cutStrings = new Vector<String>();

		int max = 0;

		// calculate max number of rows
		for (Vector<String> columnStrings : input.values())
		{
			int rows = columnStrings.size();
			if (rows > max)
				max = rows;
		}

		for (int i = 0; i < max + 1; i++)
			cutStrings.add("");

		// for all columns
		for (String key : input.keySet())
		{
			Vector<String> column = input.get(key);
			int theseRows = column.size();

			// calculate maximum width
			int maxWidth = 0;
			for (String columnString : column)
			{
				int thisWidth = columnString.length();
				if (thisWidth > maxWidth)
					maxWidth = thisWidth;
			}

			// append key
			String keyStr = cutStrings.remove(0);
			keyStr += StringUtility.padSpacesR(key, maxWidth + 2) + " | ";
			cutStrings.add(0, keyStr);

			// append all strings to their respective row
			for (int row = 1; row < theseRows + 1; row++)
			{
				String oldStr = cutStrings.remove(row);
				oldStr +=
						StringUtility.padSpacesR("  " + column.get(row - 1), maxWidth + 2) + " | ";
				cutStrings.add(row, oldStr);
			}

			// append spaces to remaining rows
			for (int row = theseRows; row < max; row++)
			{
				String oldStr = cutStrings.remove(row);
				oldStr += StringUtility.padSpacesR("", maxWidth + 2) + " | ";
				cutStrings.add(row, oldStr);
			}
		}

		return cutStrings;
	}

	/**
	 * get all combinations of list of strings
	 */
	public static Vector<String> getCombinations(TreeSet<String> elementsSet)
	{
		Vector<String> ret = new Vector<String>();

		Vector<String> elements = new Vector<String>(); // convert set to a list
		for (String element : elementsSet)
			elements.add(element);

		// start at 1 to exclude the empty string case
		for (int i = 1; i < Math.pow(2, elements.size()); i++)
		{
			String combination = "";

			int mask = 1;
			for (int j = 0; j < elements.size(); j++)
			{
				if ((i & mask) != 0)
					combination += elements.get(j);
				// else combination += underscores (elements.get(j).length());
				mask *= 2;
			}
			ret.add(combination);
		}

		return ret;
	}

	/**
	 * Finds the index of a string in a vector of strings, or -1 if it does not exist
	 */
	public static int find(String s, Vector<String> list)
	{
		for (int i = 0; i < list.size(); i++)
			if (s.equals(list.get(i)))
				return i;

		return -1;
	}

	/**
	 * Organize data into an ordered list delimited by a colon
	 */
	public static String orderedList(ConcurrentSkipListMap<String, String> data)
	{
		TreeSet<String> s = new TreeSet<String>();

		for (String key : data.keySet())
		{
			s.add(StringUtility.padSpaces(data.get(key)) + " : " + key);
		}
		String ret = "";

		for (String str : s)
			ret += str + "\n";
		return ret;
	}

	/**
	 * splits a table into two
	 */
	public static Vector<ConcurrentSkipListMap<String, String>>
			splitTable(ConcurrentSkipListMap<String, String> data)
	{
		Vector<String> ykeys = getYKeys(data);

		Vector<ConcurrentSkipListMap<String, String>> ret =
				new Vector<ConcurrentSkipListMap<String, String>>();

		if (ykeys.size() < 2)
		{
			ret.add(data);
			return ret;
		}

		int cut = ykeys.size() / 2;

		ConcurrentSkipListMap<String, String> dataTop = new ConcurrentSkipListMap<String, String>();
		ConcurrentSkipListMap<String, String> dataBottom =
				new ConcurrentSkipListMap<String, String>();

		int n = 0;
		for (String ykey : ykeys)
		{
			for (String key : data.keySet())
				if (key.endsWith(ykey))
				{
					if (n >= cut)
						dataBottom.put(key, data.get(key));
					else
						dataTop.put(key, data.get(key));
				}
			n++;
		}

		// System.out.println ("top : " + dataTop.size());
		// System.out.println ("bot : " + dataBottom.size());
		ret.add(dataTop);
		ret.add(dataBottom);
		return ret;
	}

	/**
	 * writes a LaTeX table given the file name, data, caption, and LaTeX table label
	 */
	public static void writeLatexTable(String fileName, ConcurrentSkipListMap<String, String> data,
			String caption, String label)
	{
		boolean useDouble = true;
		boolean rotateXLabels = false;

		writeLatexTable(fileName, data, caption, label, useDouble, rotateXLabels);
	}

	/**
	 * writes a latex table of integers
	 */
	public static void writeIntegerLatexTable(String fileName,
			ConcurrentSkipListMap<String, String> data, String caption, String label)
	{
		boolean useDouble = false;
		boolean rotateXLabels = false;

		writeLatexTable(fileName, data, caption, label, useDouble, rotateXLabels);
	}

	/**
	 * writes a latex table with x labels rotated
	 */
	public static void writeRotatedLatexTable(String fileName,
			ConcurrentSkipListMap<String, String> data, String caption, String label)
	{
		boolean useDouble = true;
		boolean rotateXLabels = true;

		writeLatexTable(fileName, data, caption, label, useDouble, rotateXLabels);
	}

	/**
	 * writes a latex table with x labels rotated
	 */
	public static void writeRotatedIntegerLatexTable(String fileName,
			ConcurrentSkipListMap<String, String> data, String caption, String label)
	{
		boolean useDouble = false;
		boolean rotateXLabels = true;

		writeLatexTable(fileName, data, caption, label, useDouble, rotateXLabels);
	}

	/**
	 * writes a latex table
	 */
	public static void writeLatexTable(String fileName, ConcurrentSkipListMap<String, String> data,
			String caption, String label, boolean useDouble, boolean rotateXLabels)
	{
		OutputFile latexTableFile = new OutputFile(fileName + ".tex");
		latexTableFile.writeln(latexTable(data, caption, label, useDouble, rotateXLabels));
		latexTableFile.close();

		OutputFile latexTabularFile = new OutputFile(fileName + "_tabular.tex");
		latexTabularFile.writeln(latexTabular(data, useDouble, rotateXLabels));
		latexTabularFile.close();
	}

	/**
	 * transposes the table
	 */
	public static ConcurrentSkipListMap<String, String>
			transpose(ConcurrentSkipListMap<String, String> data)
	{
		ConcurrentSkipListMap<String, String> tdata = new ConcurrentSkipListMap<String, String>();

		for (String key : data.keySet())
			tdata.put(transposeKey(key), data.get(key));

		return tdata;
	}

	/**
	 * transposes the given key
	 */
	private static String transposeKey(String key)
	{
		Vector<String> tokens = tokenize(key, "\\|");
		return tokens.get(1) + "|" + tokens.get(0);
	}

	/**
	 * creates a LaTeX table for viewing data, the x and y fields are delimited by a single vertical
	 * bar
	 */
	public static String latexTable(ConcurrentSkipListMap<String, String> data, String caption,
			String label)
	{
		boolean useDouble = true;
		boolean rotateXLabels = false;

		return latexTable(data, caption, label, useDouble, rotateXLabels);
	}

	/**
	 * gets a latex table
	 */
	public static String latexTable(ConcurrentSkipListMap<String, String> data, String caption,
			String label, boolean useDouble, boolean rotateXLabels)
	{
		caption = transformLatexLine(caption);
		// label = transformLatexLine (label);

		String ret = "";

		ret += "% ------------------------------------------------------------\n";
		ret += "% " + padSpacesR(caption + " (" + label + ") generated latex table", 60) + "\n";
		ret += "\\begin{table}[htb]\n";
		ret += "  \\small\n";
		ret += "  \\centering\n";

		// String spacing = "p{2.25cm}|";

		ret += latexTabular(data, useDouble, rotateXLabels);

		// strings for sets of words
		String wSet1Str = "$\\{";
		for (String xkey : getXKeys(data))
			wSet1Str += xkey.replaceAll("_", "\\_") + ", ";
		wSet1Str = StringUtility.trim(wSet1Str, 2);
		wSet1Str += "\\}$";

		String wSet2Str = "$\\{";
		for (String ykey : getYKeys(data))
			wSet2Str += ykey.replaceAll("_", "\\_") + ", ";
		wSet2Str = StringUtility.trim(wSet2Str, 2);
		wSet2Str += "\\}$";

		// ret += " \\caption{Vector Space Portion for Words " + wSet1Str +
		// " and " + wSet2Str + " \\label{table:data_" + label + "}}}\n";
		ret += "  \\caption{" + caption + " \\label{table:" + label + "}}\n";
		ret += "\\end{table}\n";
		ret += "% ------------------------------------------------------------\n\n";

		return ret;
	}

	/**
	 * gets a latex tabular (no caption, label)
	 */
	public static String latexTabular(ConcurrentSkipListMap<String, String> data, boolean useDouble,
			boolean rotateXLabels)
	{
		Vector<String> xkeys = getXKeys(data);
		Vector<String> ykeys = getYKeys(data);

		String ret = "";

		String spacing = "r";
		ret += "  \\begin{tabular}{" + spacing; // or use 'c' besides 'p{5cm}';
		for (int i = 0; i < xkeys.size(); i++)
			ret += spacing;
		ret += "}\n";

		// add x axis categories
		String firstLine = " & ";
		for (String xkey : xkeys)
		{
			String addStr = transformLatexLine(xkey);
			if (rotateXLabels)
				addStr = "\\rotatebox{90}{" + addStr + "}";

			addStr += " & ";
			firstLine += addStr;
		}
		firstLine = StringUtility.trim(firstLine, 2); // cut off the trailing
														// "& ";
		firstLine += "\\\\";
		ret += "  " + firstLine + "\n";

		ret += "  \\hline\n";

		// get to the data itself
		int ln = 0;

		for (String ykey : ykeys)
		{
			String line = transformLatexLine(ykey) + " & ";

			for (String xkey : xkeys)
			{
				String val = data.get(xkey + "|" + ykey);

				if (val == null)
				{
					val = "--";
					// val = "0.000";
				}
				else
				{
					// try to cast it as a double or an integer first
					try
					{
						Double v = new Double(val);
						val = round(v, 3);

						if (useDouble)
							val = transformLatexLine(val);
						else
							// val = (new Double (val)).intValue() + "";
							val = (new Double(round(new Double(val), 0))).intValue() + "";
					}
					catch (Exception e)
					{
						// if casting it as a double or an int didn't work...
						val = transformLatexLine(val);
					}

				}

				line += val + " & ";
			}
			line = StringUtility.trim(line, 2); // delete trailing ampersand

			line += "\\\\";

			if (line.contains("tabular") && (ln > 0))
				line = "\\hline\n" + line;

			ret += "  " + line + "\n";

			ln++;
		}
		ret += "  \\end{tabular}\n";

		return ret;
	}

	/**
	 * transforms a line if it contains "\n" to a table
	 */
	private static String transformLatexLine(String line)
	{
		if (line.indexOf("_") != -1)
			line = line.replace("_", "\\_");

		if (line.indexOf("%") != -1)
			line = line.replace("%", "\\%");

		if (line.indexOf("#") != -1)
			line = line.replace("#", "\\#");

		if (line.indexOf("\n") != -1)
		{
			line = line.replace("\n", "\\\\");
			// \begin{tabular}{r}punctuation\\conjunction\\lexical
			// verbs\end{tabular}
			line = "\\begin{tabular}{r}" + line + "\\end{tabular}";
		}

		return line;
	}

	/**
	 * writes a table given the data and table tag
	 */
	public static void writeTable(String fileName, ConcurrentSkipListMap<String, String> data,
			String tableTag)
	{
		OutputFile output = new OutputFile(fileName + ".txt");
		output.writeln(tableTag);
		output.writeln();
		output.writeln(StringUtility.table(data));
		output.close();
	}

	/**
	 * converts a String-Integer table to a String-String table
	 */
	public static ConcurrentSkipListMap<String, String>
			convertSItoSSMap(ConcurrentSkipListMap<String, Integer> map)
	{
		ConcurrentSkipListMap<String, String> ret = new ConcurrentSkipListMap<String, String>();
		for (String key : map.keySet())
			ret.put(key, map.get(key) + "");
		return ret;
	}

	/**
	 * converts a String-Double table to a String-String table
	 */
	public static ConcurrentSkipListMap<String, String>
			convertSDtoSSMap(ConcurrentSkipListMap<String, Double> map)
	{
		ConcurrentSkipListMap<String, String> ret = new ConcurrentSkipListMap<String, String>();
		for (String key : map.keySet())
			ret.put(key, map.get(key) + "");
		return ret;
	}

	/**
	 * creates an ascii table for viewing data, the x and y fields are delimited by a single
	 * vertical bar
	 */
	public static String table(ConcurrentSkipListMap<String, String> data)
	{
		// extract x and y keys
		Vector<String> xkeys = getXKeys(data);
		Vector<String> ykeys = getYKeys(data);

		// get averages
		ConcurrentSkipListMap<String, Double> xAvg = new ConcurrentSkipListMap<String, Double>();
		for (String xkey : xkeys)
		{
			Double sum = 0.0;
			int n = 0;

			for (String key : data.keySet())
			{
				if (key.startsWith(xkey))
				{
					String s = data.get(key);
					if (s != null)
					{
						n++;
						if (s.equals("yes"))
							sum += 1.0;
						else if (s.equals("no"))
							;
						else if ((s.startsWith("div") == false) && (s.equals("--") == false)
								&& (s.indexOf("inf") == -1))
							sum += new Double(data.get(key));
						else
							n--;
					}
				}
			}
			sum /= n;
			xAvg.put(xkey, sum);
		}

		ConcurrentSkipListMap<String, Double> yAvg = new ConcurrentSkipListMap<String, Double>();
		for (String ykey : ykeys)
		{
			Double sum = 0.0;
			int n = 0;

			for (String key : data.keySet())
			{
				if (key.endsWith(ykey))
				{
					String s = data.get(key);
					if (s != null)
					{
						n++;
						if (s.equals("yes"))
							sum += 1.0;
						else if (s.equals("no"))
							;
						else if ((s.startsWith("div") == false) && (s.equals("--") == false)
								&& (s.indexOf("inf") == -1))
							sum += new Double(data.get(key));
						else
							n--;
					}
				}
			}
			sum /= n;
			yAvg.put(ykey, sum);
		}

		// get global max and min, total average
		Double gMax = Double.MIN_VALUE;
		String maxKey = "";
		Double gMin = Double.MAX_VALUE;
		String minKey = "";

		Double sum = 0.0;
		int n = 0;
		for (String ykey : ykeys)
		{
			for (String xkey : xkeys)
			{
				String key = xkey + "|" + ykey;
				String s = data.get(key);
				if (s != null)
				{
					if (s.equals("yes"))
					{
						sum += 1.0;
						n++;
					}
					else if (s.equals("no") || s.equals(""))
					{
						n++;
					}
					else if ((s.startsWith("div") == false) && (s.equals("--") == false)
							&& (s.indexOf("inf") == -1))
					{
						Double v = new Double(s);
						if (v >= gMax)
						{
							gMax = v;
							maxKey = key;
						}
						if (v <= gMin)
						{
							gMin = v;
							minKey = key;
						}
						sum += v;
						n++;
					}
				}
			}
		}
		Double totalAvg = sum / n;
		// System.out.println ("gmax is " + gMax);
		// System.out.println ("gmin is " + gMin);

		// fill in table data
		StringBuilder ret = new StringBuilder();
		ret.append("\t");
		for (String xkey : xkeys)
			ret.append(xkey + "\t");
		ret.append("\n");

		for (String ykey : ykeys)
		{
			ret.append(ykey + "\t");
			for (String xkey : xkeys)
			{
				String key = xkey + "|" + ykey;
				String s = data.get(key);
				if (s != null)
				{
					if (s.equals("yes"))
						ret.append("yes\t");
					else if (s.equals("no"))
						ret.append("\t");
					else if ((s.startsWith("div") == false) && (s.equals("--") == false)
							&& (s.indexOf("inf") == -1))
					{
						// Double v = new Double (s);

						ret.append(round(new Double(s), 6));
						if (key.equals(maxKey))
							ret.append(" (M)");
						if (key.equals(minKey))
							ret.append(" (m)");
						ret.append("\t");
					}
					else
						ret.append("--\t");
				}
				else
					ret.append("--\t");
			}

			ret.append(round(new Double(yAvg.get(ykey)), 6)); // put average
			if (getMax(yAvg).equals(ykey))
				ret.append("+");
			if (getMin(yAvg).equals(ykey))
				ret.append("-");

			ret.append("\n");
		}

		ret.append("\t");
		for (String xkey : xkeys)
		{
			ret.append(round(new Double(xAvg.get(xkey)), 6));
			if (getMax(xAvg).equals(xkey))
				ret.append("+");
			if (getMin(xAvg).equals(xkey))
				ret.append("-");
			ret.append("\t");
		}
		ret.append(round(new Double(totalAvg), 6));
		return ret.toString();

	}

	/**
	 * Returns an ordered list of x keys
	 */
	private static Vector<String> getXKeys(ConcurrentSkipListMap<String, String> data)
	{
		Vector<String> ret = new Vector<String>();

		for (String key : data.keySet())
		{
			Vector<String> tokens = tokenize(key, "\\|");
			String xkey = tokens.get(0);
			if (ret.contains(xkey) == false)
				ret.add(xkey);
		}

		// System.out.println ("xkeys " + ret);
		return ret;
	}

	/**
	 * Returns an ordered list of y keys, used in extractorpreptest too
	 */
	public static Vector<String> getYKeys(ConcurrentSkipListMap<String, String> data)
	{
		Vector<String> ret = new Vector<String>();

		for (String key : data.keySet())
		{
			Vector<String> tokens = tokenize(key, "\\|");
			String ykey = tokens.get(1);
			if (ret.contains(ykey) == false)
				ret.add(ykey);
		}

		// System.out.println ("ykeys " + ret);
		return ret;
	}

	/**
	 * gets the minimum valued key from the string-double map
	 */
	public static String getMin(ConcurrentSkipListMap<String, Double> map)
	{
		String minKey = "";
		Double minVal = Double.MAX_VALUE;

		for (String key : map.keySet())
		{
			if (map.get(key) < minVal)
			{
				minVal = map.get(key);
				minKey = key;
			}
		}
		return minKey;
	}

	/**
	 * gets the maximum valued key from the string-double map
	 */
	public static String getMax(ConcurrentSkipListMap<String, Double> map)
	{
		String maxKey = "";
		Double maxVal = Double.MIN_VALUE;

		for (String key : map.keySet())
		{
			if (map.get(key) > maxVal)
			{
				maxVal = map.get(key);
				maxKey = key;
			}
		}
		return maxKey;
	}

	/**
	 * gets a random true/false value
	 */
	public static boolean tf()
	{
		return (Math.random() > 0.5) ? true : false;
	}

	/**
	 * gets a random integer from [0, e], including endpoints
	 */
	public static int getRandomInteger(int e)
	{
		return getRandomInteger(0, e);
	}

	/**
	 * gets a random integer from [s, e], including end points
	 */
	public static int getRandomInteger(int s, int e)
	{
		return (int) (s + Math.random() * (e - s + 1));
	}

	/**
	 * Prints a line of dashes
	 */
	public static void lineBreak()
	{
		for (int i = 0; i < CONSOLE_WIDTH; i++)
			System.out.print("-");
		System.out.println();
	}

	/**
	 * displays a line break with a given string in it
	 */
	public static void lineBreak(String s)
	{
		int l = CONSOLE_WIDTH - 5 - s.length();
		for (int i = 0; i < l; i++)
			System.out.print("-");
		System.out.println(" " + s + " ---");
	}

	/**
	 * Replaces all non alphanumeric characters in a string with an underscore. If the constant
	 * XML_PREPEND is true, it will prepends non-character initial Strings with "n_" so it is valid
	 * XML.
	 */
	@SuppressWarnings("unused")
	public static String replaceNonAlphaNumericCharacters(String str)
	{
		if (str.length() == 0)
			return str;

		for (int i = 0; i < 256; i++)
		{
			char c = (char) i;
			if (!isAlphaNum(c))
				str = str.replace(c, '_');
		}

		// prepend with n_ so that it's valid xml
		if (XML_PREPEND && !isAlpha(str.charAt(0)))
			str = "n_" + str;

		return str;
	}

	/**
	 * Replaces all non alphanumeric characters and non-spaces in a string with an underscore.
	 * Prepends non-character initial Strings with "n_" so it is valid XML.
	 */
	@SuppressWarnings("unused")
	public static String replaceNonAlphaNumericCharactersExceptSpaces(String str)
	{
		if (str.length() == 0)
			return str;

		for (int i = 0; i < 256; i++)
		{
			char c = (char) i;
			if (!isAlphaNum(c) && c != ' ')
				str = str.replace(c, '_');
		}

		// prepend with n_ so that it's valid xml
		if (XML_PREPEND && !isAlpha(str.charAt(0)))
			str = "n_" + str;

		return str;
	}

	/**
	 * Trims the Strings non alphanumeric characters from left to right.
	 * 
	 * @param input
	 * @return
	 */
	public static String trimNonAlphaNumericCharacters(String input)
	{
		int length = input.length();
		if (length == 0)
			return input;

		int left = 0;
		int right = length - 1;

		while (left < length && !isAlphaNum(input.charAt(left)))
			left++;
		while (right > 0 && !isAlphaNum(input.charAt(right)))
			right--;

		if (right < left)
			return "";
		return input.substring(left, right + 1);
	}

	/**
	 * Trims from the left and the right any characters matching the given trim characters.
	 * 
	 * @param input
	 * @param trimCharacters
	 * @return
	 */
	public static String trim(String input, String trimCharacters)
	{
		if (input == null)
			throw new IllegalArgumentException("input cannot be null");
		if (trimCharacters == null)
			throw new IllegalArgumentException("trimCharacters cannot be null");

		int length = input.length();
		if (length == 0)
			return input;

		int left = 0;
		int right = length - 1;

		while (left < length && trimCharacters.contains("" + input.charAt(left)))
			left++;
		while (right > 0 && trimCharacters.contains("" + input.charAt(right)))
			right--;

		if (right < left)
			return "";
		return input.substring(left, right + 1);
	}

	/**
	 * Replaces all non alphanumeric characters in a string with an underscore, with the exception
	 * of directory delimiters.
	 */
	public static String replaceNonAlphaNumericCharactersExceptFileDelimeters(String str)
	{
		return replaceNonAlphaNumericCharactersExceptFileDelimeters(str, false);
	}

	/**
	 * Replaces all non alphanumeric characters in a string with an underscore, with the exception
	 * of directory delimiters. If the first character is not an alpha character, prepends the
	 * string with an "n_". This is to make it a valid XML node string.
	 */
	public static String replaceNonAlphaNumericCharactersExceptFileDelimeters(String str,
			boolean prependXML)
	{
		if (str.length() == 0)
			return str;

		for (int i = 0; i < 256; i++)
		{
			char c = (char) i;
			if (!isAlphaNum(c) && (!File.separator.equals(c)))
				str = str.replace(c, '_');
		}

		// prepend with n_ so that it's valid xml
		if (prependXML)
			if (!isAlpha(str.charAt(0)))
				str = "n_" + str;

		return str;
	}

	/**
	 * Replace all non alphanumeric characters with spaces
	 * 
	 * @param line
	 * @return
	 */
	public static String replaceNonAlphaNumericCharactersWithSpaces(String str)
	{
		if (str.length() == 0)
			return str;

		for (int i = 0; i < 256; i++)
		{
			char c = (char) i;
			if (!isAlphaNum(c))
				str = str.replace(c, ' ');
		}

		// prepend with n_ so that it's valid xml
		// if (!isAlpha(str.charAt(0)))
		// str = "n_" + str;

		return str;
	}

	/**
	 * Is the given string a year? That is, does it have four characters which are all numbers?
	 */
	public static boolean isYear(String input)
	{
		if (input.length() != 4)
			return false;

		for (char c : input.toCharArray())
			if (isNum(c) == false)
				return false;

		return true;
	}

	/**
	 * Does each character in this String consist of either an alphanumeric character or a period or
	 * a comma?
	 * 
	 * @param wordStr
	 * @return
	 */
	public static boolean isAlphaNumPeriodCommaUnderscoreForwardSlashSingleQuote(String input)
	{
		for (char c : input.toCharArray())
			if (!isAlphaNumPeriodCommaUnderscoreForwardSlashSingleQuoteParenthesisTilde(c))
				return false;
		return true;
	}

	/**
	 * Does this character consist of either an alphanumeric character or a period or a comma?
	 * 
	 * @param c
	 * @return
	 */
	public static boolean
			isAlphaNumPeriodCommaUnderscoreForwardSlashSingleQuoteParenthesisTilde(char c)
	{
		return isAlphaNumOrExceptions(c, ".,_/\'()!~#");
		// if (!isAlphaNum(c) && c != '.' && c != ',' && c != '_' && c != '/' && c != '\'')
		// return false;
		// else
		// return true;
	}

	/**
	 * Is this character alphanumeric or one of a string of exception characters?
	 */
	public static boolean isNumOrExceptions(char c, String exceptions)
	{
		if (isNum(c))
			return true;
		for (int i = 0; i < exceptions.length(); i++)
			if (c == exceptions.charAt(i))
				return true;
		return false;
	}

	/**
	 * Is this string alphanumeric or one of a string of exception characters?
	 */
	public static boolean isNumOrExceptions(String input, String exceptions)
	{
		for (char c : input.toCharArray())
			if (!isNumOrExceptions(c, exceptions))
				return false;

		return true;
	}

	/**
	 * Is this character alphanumeric or one of a string of exception characters?
	 */
	public static boolean isAlphaNumOrExceptions(char c, String exceptions)
	{
		if (isAlphaNum(c))
			return true;
		for (int i = 0; i < exceptions.length(); i++)
			if (c == exceptions.charAt(i))
				return true;
		return false;
	}

	/**
	 * Is this string alphanumeric or one of a string of exception characters?
	 */
	public static boolean isAlphaNumOrExceptions(String input, String exceptions)
	{
		for (char c : input.toCharArray())
			if (!isAlphaNumOrExceptions(c, exceptions))
				return false;

		return true;
	}

	/**
	 * Is this string consisting of only alphanumeric characters?
	 * 
	 * @param input
	 * @return
	 */
	public static boolean isAlphaNum(String input)
	{
		for (char c : input.toCharArray())
			if (!isAlphaNum(c))
				return false;

		return true;
	}

	/**
	 * Is this character an alphanumeric character?
	 */
	public static boolean isAlphaNum(char c)
	{
		return isAlpha(c) || isNum(c);
	}

	/**
	 * Is this character an alphanumeric character?
	 */
	public static boolean isAlphaNumOrWhitespace(char c)
	{
		return isAlpha(c) || isNum(c) || isWhitespace(c);
	}

	/**
	 * Does each character in the given String consist of either an alphanumeric characters or
	 * whitespace?
	 */
	public static boolean isAlphaNumOrWhitespace(String s)
	{
		for (int i = 0; i < s.length(); i++)
			if (!isAlphaNumOrWhitespace(s.charAt(i)))
				return false;

		return true;
	}

	/**
	 * Is the given character whitespace?
	 * 
	 * @param c
	 */
	private static boolean isWhitespace(char c)
	{
		return c == ' ' || c == '\n' || c == '\t';
	}

	/**
	 * Is the given character an alphabetic character?
	 * 
	 * @param character
	 * @return
	 */
	public static boolean isAlpha(char character)
	{
		if (isUpperCaseAlpha(character))
			return true;
		else if (isLowerCaseAlpha(character))
			return true;
		else
			return false;
	}

	/**
	 * Is the given character an upper case character?
	 * 
	 * @param character
	 * @return
	 */
	private static boolean isUpperCaseAlpha(char character)
	{
		if ((character >= 'A' && character <= 'Z'))
			return true;
		else
			return false;
	}

	/**
	 * Is the given character an lower case character?
	 * 
	 * @param character
	 * @return
	 */
	private static boolean isLowerCaseAlpha(char character)
	{
		if ((character >= 'a' && character <= 'z'))
			return true;
		else
			return false;
	}

	/**
	 * Is this string only alphabet characters?
	 * 
	 * @param input
	 * @return
	 */
	public static boolean isAlpha(String input)
	{
		for (int index = 0; index < input.length(); index++)
			if (!isAlpha(input.charAt(index)))
				return false;
		return true;
	}

	/**
	 * Is this character only numeric characters 0-9?
	 * 
	 * @param input
	 * @return
	 */
	public static boolean isNum(char input)
	{
		return (input >= '0' && input <= '9');
	}

	/**
	 * Is this string an integer number? It can begin with a negative sign. If the string is null or
	 * empty, it returns false.
	 * 
	 * @param input The string to check.
	 * @return
	 */
	public static boolean isNum(String input)
	{
		if (input == null)
			return false;

		if (input.length() == 0)
			return false;

		if (!isNum(input.charAt(0)) && input.charAt(0) != '-')
			return false;

		if (input.trim().equals("-"))
			return false;
		for (int i = 1; i < input.length(); i++)
			if (!isNum(input.charAt(i)))
				return false;
		return true;
	}

	/**
	 * Returns the number of non-alpha characters in the given token.
	 * 
	 * @param token
	 * @return
	 */
	public static int countNonAlphas(String input)
	{
		int ret = 0;
		for (int i = 0; i < input.length(); i++)
			if (!isAlpha(input.charAt(i)))
				ret++;
		return ret;
	}

	public static boolean hasLowerCase(String trimmed)
	{
		for (int i = 0; i < trimmed.length(); i++)
			if (isLowerCase(trimmed.charAt(i)) == true)
				return true;
		return false;
	}

	private static boolean isLowerCase(char c)
	{
		if ((c >= 'a') && (c <= 'z'))
			return true;
		else
			return false;
	}

	/*
	 * Returns true if all characters in this string are lowercase
	 */
	public static boolean isLowerCase(String key)
	{
		if (key.toLowerCase().equals(key))
			return true;
		else
			return false;
	}

	// returns the number of digits in this number
	public static int getDigits(int number)
	{
		int digits;

		if (number == 0)
			digits = 1;
		else
			digits = (int) (Math.log10(number) + 1);

		return digits;
	}

	public static void writeMap(ConcurrentSkipListMap<String, String> data, String fileName)
	{
		OutputFile output = new OutputFile(fileName);
		for (String key : data.keySet())
			output.writeln(key + "\t" + data.get(key).length() + " bytes");
		output.close();
	}

	/**
	 * Trims the given line to the default WORD_LENGTH_LIMIT
	 * 
	 * @param input
	 * @return
	 */
	public static String trimToLength(String input)
	{
		if (input.length() > WORD_LENGTH_LIMIT)
			input = input.substring(0, WORD_LENGTH_LIMIT);

		return input;
	}

	/**
	 * Gets a string context within the given window at the particular position. Pads spaces to left
	 * and right if it extends beyond the string itself. It will replace newlines and tabs with
	 * respective '\n' and '\t' representations.
	 * 
	 * @param data
	 * @param position
	 * @param window
	 * @return
	 */
	public static String getStringWindow(String data, int position, int window)
	{
		int leftPos = position - window;
		int rightPos = position + window;

		String leftStr;
		if (leftPos < 0)
		{
			leftPos = 0;
			leftStr = getSpaces(-leftPos) + "BOS";
		}
		else
		{
			leftStr = "...";
		}

		String rightStr;
		if (rightPos > data.length())
		{
			rightPos = data.length();
			rightStr = "EOS" + getSpaces(rightPos - data.length());
		}
		else
		{
			rightStr = "...";
		}

		String ret = leftStr + data.substring(leftPos, rightPos) + rightStr;
		ret = ret.replace("\n", " ");
		ret = ret.replace("\t", " ");
		return ret;
	}

	/**
	 * Returns a preview of the given String. If it's less than 20 characters long it returns the
	 * entire String, else it returns the first 20 characters.
	 */
	public static String preview(String input)
	{
		if (input.length() < 20)
			return input;
		else
			return input.substring(0, 20);
	}

	/**
	 * Displays a preview of what's in this list. Useful if it's a large list.
	 * 
	 * @param strings
	 */
	public static void previewList(List<String> strings)
	{
		previewList(strings, 5);
	}

	/**
	 * Previews a list of strings by printing out the first n strings.
	 * 
	 * @param strings
	 * @param n
	 */
	public static void previewList(List<String> strings, int n)
	{
		if (strings.size() < n)
			n = strings.size();

		int i = 0;
		for (String s : strings.subList(0, n))
		{
			System.out.println("  " + i + " = \"" + s + "\"");
			i++;
		}
		System.out.println("preview for list of " + strings.size() + " strings.");
	}

	/**
	 * Returns the tail of the string up to n characters. If the string is less long than that,
	 * returns the whole string.
	 * 
	 * @param input
	 * @param n
	 * @return
	 */
	public static String trimLast(String input, int n)
	{
		return input.length() < n ? input : input.substring(input.length() - n);
	}

	/**
	 * Sorts the given input strings by occurrence of a given key. Used for context alignment in
	 * concurrence finding.
	 */
	public static Vector<String> sortByWord(TreeSet<String> input, String key)
	{
		TreeSet<String> sortedSet = new TreeSet<String>();

		int max = 0;
		for (String s : input)
		{
			int index = s.indexOf(key);

			if (index > max)
				max = index;

			String sKey = index == -1 ? "" : s.substring(index);

			sortedSet.add(sKey + "|" + s);
		}

		Vector<String> ret = new Vector<String>();
		for (String s : sortedSet)
		{
			String sub = s.substring(s.indexOf("|") + 1);
			int n = max - sub.indexOf(key);
			ret.add(StringUtility.getSpaces(n) + sub);
		}

		return ret;
	}

	/**
	 * Sorts the given strings by the string starting at the given index
	 * 
	 * @param input
	 * @param offset
	 * @return
	 */
	public static Vector<String> sortByOffset(TreeSet<String> input, int offset)
	{
		TreeSet<String> sortedSet = new TreeSet<String>();

		for (String s : input)
		{
			String sKey = offset > s.length() ? "" : s.substring(offset);

			sortedSet.add(sKey + "|" + s);
		}

		Vector<String> ret = new Vector<String>();
		for (String s : sortedSet)
			ret.add(s.substring(s.indexOf("|") + 1));

		return ret;
	}

	/**
	 * Splits the string into chunks of n amounts.
	 * 
	 * @param input The string to split.
	 * @param width The number of characters per split.
	 * @return
	 */
	public static Vector<String> hardSplit(String input, int width)
	{
		Vector<String> ret = new Vector<String>();

		int i = 0;

		while (i < input.length())
		{
			int beginIndex = i;
			int endIndex =
					(beginIndex + width) < input.length() ? (beginIndex + width) : input.length();

			ret.add(input.substring(beginIndex, endIndex));
			i += width;
		}

		return ret;
	}

	/**
	 * Splits the string into chunks of n amounts. Does *not* split between words. Preserves
	 * newlines.
	 * 
	 * @param input The string to split.
	 * @param width The number of characters per split.
	 * @return
	 */
	public static Vector<String> softSplit(String input, int width)
	{

		Vector<String> lines = StringUtility.tokenizeByLine(input);

		if (lines.size() > 1)
		{
			Vector<String> ret = new Vector<String>();
			for (String line : lines)
				ret.addAll(softSplit(line, width));
			return ret;
		}
		else
		{
			Vector<String> tokens = tokenize(input, " ");
			Vector<String> ret = new Vector<String>();

			String currentString = "";

			for (String token : tokens)
			{
				if (token.length() > width)
				{
					throw new IllegalArgumentException("StringUtility.softSplit found token '"
							+ token + "' with length > " + width);
				}
				else
				{
					String addedString = currentString + " " + token;

					if (addedString.length() > width)
					{
						ret.add(currentString);

						currentString = token;
					}
					else
					{
						currentString += " " + token;
					}
				}
			}
			ret.add(currentString);

			return ret;
		}
	}

	/**
	 * Appends the given append string to the last directory in the input string.
	 * 
	 * @param input The input String to append to it's last directory.
	 * @param appendString The String to append.
	 */
	public static String directoryAppend(String input, String appendString)
	{
		File f = new File(input);

		if (!f.exists())
		{
			throw new UnsupportedOperationException(
					"StringUtility.directoryAppend Input \"" + input + "\" does not exist.");
		}

		String ret = "";
		if (f.isDirectory())
		{
			if (!input.endsWith(File.separator))
				input += File.separator;

			ret = input.substring(0, input.lastIndexOf(File.separator)) + appendString
					+ File.separator;
		}
		else if (f.isFile())
		{
			int lsi = input.lastIndexOf(File.separator);
			ret = input.substring(0, lsi) + appendString + File.separator
					+ input.substring(lsi + 1);
		}
		else
		{
			throw new UnsupportedOperationException("StringUtility.directoryAppend Input \"" + input
					+ "\" is neither a file nor a directory.");
		}

		return ret;
	}

	/**
	 * Appends the given append string to the file of the given input string. It will append the
	 * given string before the file extension. For example, given input 'data/file.txt' and '_cm',
	 * it will produce 'data/file_cm.txt'
	 * 
	 * @param input The input String to append to it's last directory.
	 * @param appendString The String to append.
	 */
	public static String fileAppend(String input, String appendString)
	{
		File f = new File(input);

		String ret = "";
		// if it's a directory, fail
		// else assume it's a file we may want to create,
		// and return a result
		if (f.isDirectory())
		{
			throw new UnsupportedOperationException(
					"StringUtility.fileAppend Input " + input + " is a directory, not a file.");
		}
		else
		{
			int lsi = input.lastIndexOf(".");

			// if it has a file extension, preserve it
			if (lsi != -1)
				ret = input.substring(0, lsi) + appendString + input.substring(lsi);
			else
				ret = input + appendString;
		}

		return ret;
	}

	/**
	 * Appends the given append string to the file of the given input string. It will append the
	 * given string before the file extension. For example, given input 'data/file.txt' and 'cm_',
	 * it will produce 'data/cm_file.txt'
	 * 
	 * @param input The input String to prepend to it's last directory.
	 * @param prependString The String to prepend.
	 */
	public static String filePrepend(String input, String prependString)
	{
		File f = new File(input);

		String ret = "";
		// if it's a directory, fail
		// else assume it's a file we may want to create,
		// and return a result
		if (f.isDirectory())
		{
			throw new UnsupportedOperationException(
					"StringUtility.filePrepend Input " + input + " is a directory, not a file.");
		}
		else
		{
			int lsi = input.lastIndexOf(File.separator);

			// if it has a file extension, preserve it
			if (lsi != -1)
				ret = input.substring(0, lsi + 1) + prependString + input.substring(lsi + 1);
			else
				ret = prependString + input;
		}

		return ret;
	}

	/**
	 * Takes an input string and replaces the file extension.
	 * 
	 * @param input The string to replace the extension of.
	 * @param newExtension The new file extension.
	 * @return
	 */
	public static String replaceExtension(String input, String newExtension)
	{
		int lsi = input.lastIndexOf(".");

		if (lsi == -1)
			lsi = input.length();

		String ret = input.substring(0, lsi) + "." + newExtension;

		// System.out.println("replaceExtension input = " + input);
		// System.out.println("replaceExtension length = " + input.length());
		// System.out.println("replaceExtension newExtension = " +
		// newExtension);
		// System.out.println("replaceExtension lsi = " + lsi);
		// System.out.println("replaceExtension ret = " + ret);
		// ioKeyboard.readLine();

		return ret;
	}

	/**
	 * Capitalizes the first letter found in the input String.
	 * 
	 * @param input
	 * @return
	 */
	public static String capitalize(String input)
	{
		int index = 0;

		boolean done = false;
		while (index < input.length() && !done)
			if (!isAlpha(input.charAt(index)))
				index++;
			else
				done = true;

		if (index >= input.length())
			return input;

		String lhs = input.substring(0, index);
		String c = (input.charAt(index) + "").toUpperCase();

		String ret = lhs + c;

		if (index < input.length() - 1)
		{
			String rhs = input.substring(index + 1);

			ret += rhs;
		}

		return ret;
	}

	/**
	 * Capitalizes the first letter and makes all subsequent letters lowercase.
	 * 
	 * @param input The String to capitalize and lowercase.
	 * @return
	 */
	public static String capitalizeAndLowerCase(String input)
	{
		String c = input.charAt(0) + "";
		c = c.toUpperCase();

		if (input.length() == 1)
			return c + "";
		else
			return c + input.substring(1).toLowerCase();
	}

	/**
	 * Capitalizes the first letter and makes all subsequent letters lowercase for each word in the
	 * given input string delimited by spaces. Uses tokenize by whitespace, which gets rid
	 * intermediate redundant whitespace.
	 * 
	 * @param input The String to capitalize and lowercase.
	 * @return
	 */
	public static String capitalizeAndLowerCaseAll(String input)
	{
		Vector<String> tokens = tokenizeByWhitespace(input);

		String ret = "";
		for (String token : tokens)
		{
			String c = token.charAt(0) + "";
			c = c.toUpperCase();

			if (token.length() == 1)
				ret += c + " ";
			else
				ret += c + token.substring(1).toLowerCase() + " ";
		}
		ret = ret.trim();
		return ret;
	}

	/**
	 * Gets recommendations for spelling corrections of the given set of possible corrections using
	 * the
	 */
	public static Vector<String> spellCheck(String input, Vector<String> dictionary)
	{
		Vector<String> ret = new Vector<String>();
		for (String candidate : dictionary)
		{
			int dl = damerauLevenshtein(input, candidate);

			ret.add("d(\"" + input + "\", \"" + candidate + "\") = " + dl);
		}
		return ret;
	}

	/**
	 * Calculates the Damerau-Levenshtein distance between two strings.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	private static int damerauLevenshtein(String a, String b)
	{
		return damerauLevenshtein(a, b, a.length() - 1, b.length() - 1);
	}

	private static int damerauLevenshtein(String a, String b, int i, int j)
	{
		if (Math.min(i, j) == 0)
		{
			return Math.max(i, j);
		}
		else if ((i > 1) && (j > 1) && (a.charAt(i) == b.charAt(j - 1))
				&& (a.charAt(i - 1) == b.charAt(j)))
		{
			int d1 = damerauLevenshtein(a, b, i - 1, j) + 1;
			int d2 = damerauLevenshtein(a, b, i, j - 1) + 1;
			int d3 = damerauLevenshtein(a, b, i - 1, j - 1)
					+ ((a.charAt(i) == b.charAt(j)) ? 0 : 1);
			int d4 = damerauLevenshtein(a, b, i - 2, j - 2) + 1;

			return Math.min(Math.min(d1, d2), Math.min(d3, d4));
		}
		else
		{
			int d1 = damerauLevenshtein(a, b, i - 1, j) + 1;
			int d2 = damerauLevenshtein(a, b, i, j - 1) + 1;
			int d3 = damerauLevenshtein(a, b, i - 1, j - 1)
					+ ((a.charAt(i) == b.charAt(j)) ? 0 : 1);

			return Math.min(Math.min(d1, d2), d3);
		}
	}

	/**
	 * Does line contain the whole word in the given line?
	 * 
	 * @param line
	 * @param word
	 * @return
	 */
	public static boolean containsWholeWord(String line, String word)
	{
		String rLine = reduceAllWhiteSpace(line);
		String rWord = reduceAllWhiteSpace(word);

		boolean ret = rLine.contains(" " + rWord + " ") || rLine.startsWith(rWord + " ")
				|| rLine.endsWith(" " + rWord) || rLine.equals(rWord);

		return ret;
	}

	/**
	 * Counts the instances of the whole word in the given line.
	 * 
	 * @param line The line from which to read whole words.
	 * @param word The word we are looking for.
	 * @return
	 */
	public static int countWholeWords(String line, String word)
	{
		// String query = reduceAllWhiteSpace(line);

		int ret = 0;

		// get bookends
		if (line.startsWith(word + " "))
			ret++;
		if (line.endsWith(" " + word))
			ret++;

		// get whole words in the middle
		int index = line.indexOf(" " + word + " ");
		while (index != -1)
		{
			ret++;
			index = line.indexOf(" " + word + " ", index + 1);
		}

		// if (ret > 0)
		// System.out.println("countWholeWords " + ret + " instances of '" + word + "' in\nline '"
		// + line + "'");

		// get if the whole line is the word
		if (line.equals(word))
			ret++;
		return ret;
	}

	/**
	 * Converts a variable list of arguments of strings into a TreeSet of Strings.
	 * 
	 * @param arguments
	 * @return
	 */
	public static ConcurrentSkipListSet<String> getStringSet(String... arguments)
	{
		ConcurrentSkipListSet<String> ret = new ConcurrentSkipListSet<String>();
		for (int i = 0; i < arguments.length; i++)
			ret.add(arguments[i]);
		return ret;
	}

	/**
	 * Gets a numeric hex representation of the given string.
	 * 
	 * @param input
	 * @return
	 */
	public static String toNumericHex(String input)
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < input.length(); i++)
		{
			char c = input.charAt(i);

			int ci = (int) c;

			int hb = ci / 16;
			int lb = ci % 16;

			String hex = toHex(hb) + "" + toHex(lb);
			sb.append(hex + " ");
		}

		return sb.toString();
	}

	/**
	 * Returns a hex character of the given int which should range from 0 to 15.
	 * 
	 * @param hb
	 * @return
	 */
	private static char toHex(int hb)
	{
		if (hb < 0 || hb > 15)
			throw new IllegalArgumentException(
					"Cannot convert " + hb + " to hex, it must be within [0, 15].");

		if (hb < 10)
			return (char) ('0' + hb);
		else
			return (char) ('a' + (hb - 10));
	}

	/**
	 * Converts a vector of strings to a single string.
	 * 
	 * @param flatStrings
	 * @return
	 */
	public static String toString(String... args)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (String s : args)
			sb.append("'" + s + "' ");
		sb.append("]");
		return sb.toString();
	}

	/**
	 * Converts a vector of strings to a single string.
	 * 
	 * @param strings
	 * @return
	 */
	public static String toString(Vector<String> strings)
	{
		StringBuilder sb = new StringBuilder();

		sb.append("[");

		for (String s : strings)
			sb.append(s + " ");
		sb.append("]");

		return sb.toString();
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
			IOUtils.copy(inputStream, writer, "UTF-8");
			String response = writer.toString();
			return response;
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Returns an idented version of the given String as delimited by opening and closing curly
	 * braces (chicken lips).
	 * 
	 * @param resultStr
	 * @return
	 */
	public static String indent(String resultStr)
	{
		String ret = "";

		int depth = 0;
		for (int i = 0; i < resultStr.length(); i++)
		{
			char c = resultStr.charAt(i);

			String tabs = StringUtility.getTabs(depth);
			String tabsPlus = StringUtility.getTabs(depth + 1);
			String tabsEnd = StringUtility.getTabs(depth - 1);
			switch (c)
			{
				case '{':
					ret += "\n" + tabs + c + "\n" + tabsPlus;
					depth++;
					break;
				case '}':
					ret += "\n" + tabsEnd + c + "\n" + tabsEnd;
					depth--;
					break;
				case ',':
					ret += ",\n" + tabs;
					break;
				default:
					ret += c;
					break;

			}
		}
		return ret;
	}

	/**
	 * Returns an indented version of the given String with the given identation depth. If the input
	 * string is multiple lines, it will tokenize it and add indentation to each line.
	 * 
	 * @param string The String to indent.
	 * @param depth The level of indentation.
	 * @return
	 */
	public static String indent(String string, int depth)
	{
		Vector<String> lines = tokenizeByLine(string);

		String ret = "";
		for (String line : lines)
			ret += getTabs(depth) + line + "\n";

		return ret;
	}

	/**
	 * Gets a reasonable string representing this count value.
	 * 
	 * For example:
	 * 
	 * input output
	 * 
	 * 123 123
	 * 
	 * 3245 3.2k
	 * 
	 * 294552 294k
	 * 
	 * 1503042 1.6m
	 * 
	 * @param count
	 * @return
	 */
	public static String getRangedValue(int count)
	{
		// System.out.println("\ninput = " + count);

		if (count < 1000)
			return count + "";
		else if (count < 10000)
			return round((double) count / 1000, 2) + "k";
		else if (count < 100000)
			return round((double) count / 1000, 1) + "k";
		else if (count < 1000000)
			return (count / 1000) + "k";
		else if (count < 10000000)
			return round((double) count / 1000000, 2) + "m";
		else if (count < 100000000)
			return round((double) count / 1000000, 1) + "m";
		else
			return (count / 1000000) + "m";
	}

	/**
	 * Returns a comma-seperated list without a trailing comma of the given list of Strings.
	 * 
	 * @param xKeys
	 * @return
	 */
	public static String getCommaSeperatedList(Vector<String> list)
	{
		StringBuilder sb = new StringBuilder();
		for (String item : list)
			sb.append(item + ", ");
		String ret = sb.toString();

		// trim the trailing space and comma
		ret = trim(ret, 2);
		return ret;
	}

	/**
	 * Converts a month string to a number representing the month number. January is 1, December is
	 * 12. Handles abbreviations as well. Ignores case. If the string is invalid, returns null.
	 * 
	 * @param ms
	 * @return
	 */
	public static Integer convertMonthToNumber(String input)
	{
		input = input.toLowerCase();
		if (input.equals("jan") || input.equals("january"))
			return 1;
		else if (input.equals("feb") || input.equals("february"))
			return 2;
		else if (input.equals("mar") || input.equals("march"))
			return 3;
		else if (input.equals("apr") || input.equals("april"))
			return 4;
		else if (input.equals("may") || input.equals("may"))
			return 5;
		else if (input.equals("jun") || input.equals("june"))
			return 6;
		else if (input.equals("jul") || input.equals("july"))
			return 7;
		else if (input.equals("aug") || input.equals("august"))
			return 8;
		else if (input.equals("sep") || input.equals("sept") || input.equals("september"))
			return 9;
		else if (input.equals("oct") || input.equals("october"))
			return 10;
		else if (input.equals("nov") || input.equals("november"))
			return 11;
		else if (input.equals("dec") || input.equals("december"))
			return 12;
		else if (StringUtility.isNum(input))
		{
			int n = Integer.parseInt(input);

			if (n >= 1 && n <= 12)
				return n;
			else
				return null;
		}
		else
			return null;
	}

	/**
	 * Converts a day string to a number representing the day number. Sunday is 1, Monday is 2, etc
	 * until Saturday which is 7. If the string is invalid, throws an exception. It handles
	 * abbreviations and is not case sensitive.
	 * 
	 * @param dayString
	 * @return
	 */
	public static int convertDayToNumber(String input) throws IllegalArgumentException
	{
		input = input.toLowerCase().trim();
		if (input.equals("sun") || input.equals("sunday") || input.equals("sundays"))
			return 1;
		else if (input.equals("mon") || input.equals("monday") || input.equals("mondays"))
			return 2;
		else if (input.equals("tue") || input.equals("tuesday") || input.equals("tuesdays"))
			return 3;
		else if (input.equals("wed") || input.equals("wednesday") || input.equals("wednesdays"))
			return 4;
		else if (input.equals("thu") || input.equals("thursday") || input.equals("thursdays"))
			return 5;
		else if (input.equals("fri") || input.equals("friday") || input.equals("fridays"))
			return 6;
		else if (input.equals("sat") || input.equals("saturday") || input.equals("saturdays"))
			return 7;
		else
		{
			String msg = "Cannot convert '" + input + "' to a day number.";
			System.out.println(msg);
			throw new IllegalArgumentException(msg);
		}
	}

	/**
	 * Is the given string a day? i.e. sunday, monday ... saturday? Includes abbreviations. It is
	 * not case sensitive.
	 * 
	 * @param input
	 * @return
	 */
	@SuppressWarnings("unused")
	public static boolean isDay(String input)
	{
		try
		{
			int dayNumber = convertDayToNumber(input);
		}
		catch (IllegalArgumentException e)
		{
			return false;
		}
		return true;
	}

	/**
	 * Is this string a number?
	 * 
	 * @param token
	 * @return
	 */
	public static boolean isNumberString(String token)
	{
		return convertStringToNumber(token) != null;
	}

	/**
	 * Converts a string to a number if we recognize it.
	 * 
	 * @param str
	 * @return
	 */
	public static Integer convertStringToNumber(String str)
	{
		if (str.equals("one"))
			return 1;
		else if (str.equals("two"))
			return 2;
		else if (str.equals("three"))
			return 3;
		else if (str.equals("four"))
			return 4;
		else if (str.equals("five"))
			return 5;
		else if (str.equals("six"))
			return 6;
		else if (str.equals("seven"))
			return 7;
		else if (str.equals("eight"))
			return 8;
		else if (str.equals("nine"))
			return 9;
		else if (str.equals("ten"))
			return 10;
		else if (str.equals("eleven"))
			return 11;
		else if (str.equals("twelve"))
			return 12;
		else if (str.equals("thirteen"))
			return 13;
		else if (str.equals("fourteen"))
			return 14;
		else if (str.equals("fifteen"))
			return 15;
		else if (str.equals("sixteen"))
			return 16;
		else if (str.equals("seventeen"))
			return 17;
		else if (str.equals("eighteen"))
			return 18;
		else if (str.equals("nineteen"))
			return 19;
		else if (str.equals("twenty"))
			return 20;
		else
			return null;
	}

	/**
	 * Cuts the given string into substrings of length n. Preserves any newlines in the input
	 * string.
	 * 
	 * @param input
	 * @param i
	 * @return
	 */
	public static Vector<String> cutString(String input, int n)
	{
		Vector<String> lines = StringUtility.tokenizeByLine(input);

		Vector<String> ret = new Vector<String>();
		if (lines.size() == 1)
		{
			int index = 0;

			while (index < input.length())
			{
				int endIndex = index + n;
				if (endIndex > input.length())
					endIndex = input.length();

				ret.add(input.substring(index, endIndex));
				index += n;
			}
		}
		else
		{
			for (String line : lines)
				ret.addAll(cutString(line, n));
		}
		return ret;
	}

	/**
	 * Removes strings from the given string. Does this for whole tokens using tokenization by
	 * whitespace. Trims trailing and leading whitespace.
	 * 
	 * @param input The string from which to replace.
	 * @param string... The strings to remove.
	 * @return
	 */
	public static String removeStrings(String input, String... removeStrings)
	{
		// validate removeStrings, they cannot contain spaces
		for (String removeString : removeStrings)
			if (removeString.contains(" "))
				throw new IllegalArgumentException(
						"Cannot remove a string which contains a space.  String is '" + removeString
								+ "'");
		Vector<String> tokens = tokenizeByWhitespace(input);

		String ret = "";
		for (String token : tokens)
		{
			boolean use = true;
			for (String removeString : removeStrings)
				if (removeString.equals(token))
					use = false;

			if (use)
				ret += token + " ";
		}

		return ret.trim();
	}

	/**
	 * Replaces the whole word. Meaning, only replaces in instances preceeded by a space or
	 * following a space.
	 * 
	 * @param input The string to replace upon.
	 * @param src The substring to replace.
	 * @param dest The replacement substring.
	 * @return
	 */
	public static String replaceWholeWord(String input, String src, String dest)
	{
		Vector<String> tokens = StringUtility.tokenizeByWhitespace(input);

		String ret = "";
		for (String token : tokens)
		{
			if (token.equals(src))
				ret += dest + " ";
			else
				ret += token + " ";
		}
		return ret;
	}

	/**
	 * Gets the distance between two Strings. This is defined as follows:
	 * 
	 * If the distance is zero, both strings have the exact same tokens in them.
	 */
	public static double fScore(String s1, String s2)
	{
		double p = precision(s1, s2);
		double r = recall(s1, s2);

		double f = 2 * p * r / (p + r);

		// System.out.println("p('" + s1 + "', '" + s2 + "') = " + p);
		// System.out.println("r('" + s1 + "', '" + s2 + "') = " + r);
		return f;
	}

	/**
	 * Calculates the precision of the given two strings. That is, what percentage of s1's tokens
	 * show up in s2's tokens.
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static double precision(String s1, String s2)
	{
		String s1Lower = s1.toLowerCase();
		String s2Lower = s2.toLowerCase();
		Vector<String> s1Tokens = tokenizeByWhitespace(s1Lower);
		Vector<String> s2Tokens = tokenizeByWhitespace(s2Lower);

		int n = 0;
		for (String s1Token : s1Tokens)
			if (s2Tokens.contains(s1Token))
				n++;
		return (double) n / s2Tokens.size();
	}

	public static double recall(String s1, String s2)
	{
		return precision(s2, s1);
	}

	/**
	 * Returns a list of uppercased strings.
	 */
	public static Vector<String> toUpperCase(Vector<String> input)
	{
		Vector<String> ret = new Vector<String>();
		for (String s : input)
			ret.add(s.toUpperCase());
		return ret;
	}

	/**
	 * Returns a list of lowercased strings.
	 */
	public static Vector<String> toLowerCase(Vector<String> input)
	{
		Vector<String> ret = new Vector<String>();
		for (String s : input)
			ret.add(s.toLowerCase());
		return ret;
	}

	/**
	 * Returns the permutations of concatenating two lists of strings. If the first set contains
	 * zero elements, returns the second set, and vice versa.
	 */
	public static Vector<String> permute(Vector<String> listOne, Vector<String> listTwo)
	{
		if (listOne.size() == 0)
			return listTwo;
		if (listTwo.size() == 0)
			return listOne;

		Vector<String> ret = new Vector<String>();

		for (String s1 : listOne)
			for (String s2 : listTwo)
				ret.add(s1 + s2);
		return ret;
	}

	/**
	 * Gets capitalization permutations of the given input's tokens with a '*' between each token in
	 * the return denoting zero or more characters.
	 * 
	 * @param input
	 * @return
	 */
	public static Vector<String> getCapitalizationPermutationsWithZeroOrMore(String input)
	{
		return getCapitalizationPermutations(input, "*");
	}

	/**
	 * Gets capitalization permutations of the given input's tokens with a space between each token
	 * in the return.
	 * 
	 * @param input
	 * @return
	 */
	public static Vector<String> getCapitalizationPermutations(String input)
	{
		return getCapitalizationPermutations(input, " ");
	}

	/**
	 * Tokenizes the input by whitespace. Gets all combinations of capitalized, all caps, and
	 * lowercase iterations of the tokens.
	 *
	 * @param input The String to get permutations of.
	 * @return
	 */
	public static Vector<String> getCapitalizationPermutations(String input, String medialToken)
	{
		Vector<String> tokens = StringUtility.tokenizeByWhitespace(input);

		if (tokens.size() == 0)
			return new Vector<String>();
		else if (tokens.size() == 1)
		{
			// return three representations of the string.
			Vector<String> ret = new Vector<String>();
			String token = tokens.get(0);

			// add to a set, they might be the same depending on their content
			TreeSet<String> set = new TreeSet<String>();

			set.add(token.toLowerCase());
			set.add(capitalize(token));
			set.add(token.toUpperCase());

			// convert set to list, return!
			for (String setItem : set)
				ret.add(setItem);

			return ret;
		}
		else
		{
			String firstToken = tokens.get(0);
			Vector<String> remainingTokens = new Vector<String>(tokens.subList(1, tokens.size()));
			String remainingString = "";

			for (String token : remainingTokens)
				remainingString += token + " ";

			Vector<String> lhs = getCapitalizationPermutations(firstToken, medialToken);
			Vector<String> rhs = getCapitalizationPermutations(remainingString, medialToken);

			// combine
			Vector<String> ret = new Vector<String>();
			for (String lhsi : lhs)
				for (String rhsi : rhs)
					ret.add(lhsi + medialToken + rhsi);
			return ret;
		}
	}

	/**
	 * Gets a wild-card injected version of this string.
	 * 
	 * Example: "zip code" -> "*zip*code*";
	 * 
	 * @param input
	 * @return
	 */
	public static String getStarString(String input)
	{
		return "*" + input.replace(" ", "*") + "*";
	}

	/**
	 * Returns the first capitalized alpha or number in the given input string. If none exists,
	 * returns 0;
	 * 
	 * @param input
	 * @return
	 */
	public static char getFirstCapAlphaNum(String input)
	{
		if (input == null)
			throw new IllegalArgumentException("Input cannot be null.");

		for (int i = 0; i < input.length(); i++)
		{
			char c = input.charAt(i);

			if (isNum(c))
				return c;
			if (isUpperCaseAlpha(c))
				return c;
		}

		return 0;
	}

	/**
	 * Escapes a string which may contain single quotes for an SQL query.
	 * 
	 * @param token
	 * @return
	 */
	public static String singleQuoteSQLEscape(String token)
	{
		return token.replace("'", "''");
	}

	/**
	 * 
	 * @param tokens
	 */
	public static Vector<Vector<String>> getPermutationsWithSpaces(List<String> tokens)
	{
		Vector<Vector<String>> ret = new Vector<Vector<String>>();
		switch (tokens.size())
		{
			case 0:
				return ret;
			case 1:
				// System.out.println("ONE");
				Vector<String> single = new Vector<String>();
				single.add(tokens.get(0));
				ret.add(single);
				return ret;
			default:
			{
				// System.out.println("tokens size " + tokens.size());
				// for all binary cuts
				String noCutString = "";
				for (String token : tokens)
					noCutString += token + " ";
				noCutString = noCutString.trim();

				Vector<String> noCutVector = new Vector<String>();
				noCutVector.add(noCutString);
				ret.add(noCutVector);

				// try all binary cuts
				for (int cut = 1; cut <= tokens.size() - 1; cut++)
				{
					List<String> left = tokens.subList(0, cut);
					List<String> right = tokens.subList(cut, tokens.size());

					Vector<Vector<String>> leftPermutations = getPermutationsWithSpaces(left);
					Vector<Vector<String>> rightPermutations = getPermutationsWithSpaces(right);

					Vector<Vector<String>> product =
							StringUtility.crossProduct(leftPermutations, rightPermutations);

					// System.out.println("-----x");
					// System.out.println("left = " + leftPermutations);
					// System.out.println("right = " + rightPermutations);
					// System.out.println("product = " + product);
					// System.out.println("-----x/");
					ret.addAll(product);
				}

				return ret;
			}
		}
	}

	/**
	 * Gets the crossproduct of two vectors of vectors of strings.
	 * 
	 * i.e.
	 * 
	 * leftPermutation = [ ["The man", "ate"], ["The woman", "walked"] ];
	 *
	 * rightPermutation = [ ["the apple"], ["the dog"] ];
	 * 
	 * ret = [ ["The man", "ate", "the apple"];
	 * 
	 * ret = [ ["The man", "ate", "the dog"];
	 * 
	 * ret = [ ["The woman", "walked", "the apple"];
	 * 
	 * ret = [ ["The woman", "walked", "the dog"];
	 * 
	 * @param leftPermutations
	 * @param rightPermutations
	 */
	private static Vector<Vector<String>> crossProduct(Vector<Vector<String>> leftPermutations,
			Vector<Vector<String>> rightPermutations)
	{
		Vector<Vector<String>> ret = new Vector<Vector<String>>();
		for (Vector<String> left : leftPermutations)
			for (Vector<String> right : rightPermutations)
			{
				Vector<String> result = new Vector<String>();
				result.addAll(left);
				result.addAll(right);
				ret.add(result);
			}
		return ret;
	}

	/**
	 * Escapes chracters used in XML strings.
	 * 
	 * https://www.liquid-technologies.com/XML/EscapingData.aspx
	 * 
	 * Char Escape String
	 * 
	 * < &lt;
	 * 
	 * > &gt;
	 * 
	 * " &quot;
	 * 
	 * ' &apos;
	 *
	 * & &amp;
	 *
	 * 
	 * @param input
	 * @return
	 */

	public static String xmlEscape(String input)
	{
		input = input.replace("<", "&lt;");
		input = input.replace(">", "&gt;");
		input = input.replace("\"", "&quot;");
		input = input.replace("'", "&apos;");
		input = input.replace("&", "&amp;");

		return input;
	}

	public static ConcurrentSkipListSet<String> getStringSet(Vector<String> tokens)
	{
		ConcurrentSkipListSet<String> ret = new ConcurrentSkipListSet<String>();
		for (String token : tokens)
			ret.add(token);
		return ret;
	}

	public static ConcurrentSkipListSet<String> getStringSet(Vector<String> tokens1,
			Vector<String> tokens2)
	{
		ConcurrentSkipListSet<String> ret = new ConcurrentSkipListSet<String>();
		for (String token : tokens1)
			ret.add(token);
		for (String token : tokens2)
			ret.add(token);
		return ret;
	}

	/**
	 * Gets star permutations. Here a star represents *ONE* character. Not zero or more. Also,
	 * permutations are only alphanumeric characters.
	 * 
	 * @param str
	 * @return
	 */
	public static Vector<String> getStarPermutations(String str)
	{
		if (str.length() == 0)
			return new Vector<String>();
		else if (str.length() == 1)
		{
			char c = str.charAt(0);

			Vector<String> ret = new Vector<String>();

			// permutation character
			if (c == '*')
			{
				return getAlphaNums();
			}
			// nonpermutation character
			else
			{
				ret.add(c + "");
				return ret;
			}
		}
		else
		{

			char c = str.charAt(0);

			Vector<String> lhs = new Vector<String>();

			// permutation character
			if (c == '*')
			{
				for (String s : getAlphaNums())
					lhs.add(s);
			}
			// nonpermutation character
			else
			{
				lhs.add(c + "");
			}

			Vector<String> rhs = getStarPermutations(str.substring(1));

			// combine!
			Vector<String> ret = new Vector<String>();
			for (String lhsi : lhs)
				for (String rhsi : rhs)
					ret.add(lhsi + rhsi);
			return ret;
		}
	}

	/**
	 * Gets all alphanumeric characters
	 * 
	 * @return
	 */
	private static Vector<String> getAlphaNums()
	{
		Vector<String> ret = new Vector<String>();
		for (char p = 'a'; p <= 'z'; p++)
			ret.add(p + "");
		for (char p = 'A'; p <= 'Z'; p++)
			ret.add(p + "");
		for (int n = 0; n < 9; n++)
			ret.add(n + "");
		return ret;
	}

	/**
	 * Calculates the numeric density of this word. That is, the ratio of the number of number
	 * characters in the given string to the total length of the string. It will throw an
	 * IllegalArgumentException if the input string has length zero.
	 * 
	 * @param wordStr
	 * @return
	 */
	public static double getNumericDensity(String input)
	{
		if (input.length() == 0)
			throw new IllegalArgumentException(
					"Numeric density undefined for zero length string '" + input + "'");

		int n = 0;
		int d = 0;

		for (int i = 0; i < input.length(); i++)
		{
			char c = input.charAt(i);
			if (isNum(c))
				n++;
			d++;
		}
		return (double) n / (double) d;
	}

	/**
	 * Converts the given string value to hexadecimal
	 * 
	 * @param input
	 * @return
	 */
	public static byte[] toHex(byte[] input)
	{
		byte[] ret = new byte[input.length * 2];

		for (int i = 0; i < input.length; i++)
		{
			// get the high order 4 bits
			byte val = input[i];

			char highByte = getBytesHighHex(val);
			char lowByte = getBytesLowHex(val);

			ret[i * 2 + 0] = (byte) highByte;
			ret[i * 2 + 1] = (byte) lowByte;
		}
		return ret;
	}

	/**
	 * Gets the hexadecimal character (lowercase) of the low order 4 bits of the given byte.
	 * 
	 * @param val
	 * @return
	 */
	private static char getBytesLowHex(byte val)
	{
		int n = (val & 0x0f);
		return intToHexChar(n);
	}

	/**
	 * Gets the hexadecimal character (lowercase) of the high order 4 bits of the given byte.
	 * 
	 * @param val
	 * @return
	 */
	private static char getBytesHighHex(byte val)
	{
		int n = (val >> 4) & 0x0f;
		return intToHexChar(n);
	}

	/**
	 * Converts an int to a hex character (0-9, a-f in lowercase)
	 * 
	 * @param n
	 * @return
	 */
	private static char intToHexChar(int n)
	{
		switch (n)
		{
			case 0:
				return '0';
			case 1:
				return '1';
			case 2:
				return '2';
			case 3:
				return '3';
			case 4:
				return '4';
			case 5:
				return '5';
			case 6:
				return '6';
			case 7:
				return '7';
			case 8:
				return '8';
			case 9:
				return '9';
			case 10:
				return 'a';
			case 11:
				return 'b';
			case 12:
				return 'c';
			case 13:
				return 'd';
			case 14:
				return 'e';
			case 15:
				return 'f';
			default:
				throw new IllegalArgumentException("Integer input n must be in range [0, 15]");
		}
	}

	/**
	 * Converts an int to a hex character (0-9, a-f in lowercase)
	 * 
	 * @param n
	 * @return
	 */
	private static int hexToInt(char c)
	{
		switch (c)
		{
			case '0':
				return 0;
			case '1':
				return 1;
			case '2':
				return 2;
			case '3':
				return 3;
			case '4':
				return 4;
			case '5':
				return 5;
			case '6':
				return 6;
			case '7':
				return 7;
			case '8':
				return 8;
			case '9':
				return 9;
			case 'a':
				return 10;
			case 'b':
				return 11;
			case 'c':
				return 12;
			case 'd':
				return 13;
			case 'e':
				return 14;
			case 'f':
				return 15;
			default:
				throw new IllegalArgumentException(
						"Character input c = '" + c + "' must be in range [0-9, a-f]");
		}
	}

	public static byte[] fromHex(byte[] input)
	{
		byte[] ret = new byte[input.length / 2];

		for (int i = 0; i < input.length / 2; i++)
		{
			int highInt = hexToInt((char) input[i * 2 + 0]);
			int lowInt = hexToInt((char) input[i * 2 + 1]);
			// get the high order 4 bits
			byte val = (byte) ((highInt << 4) + lowInt);

			ret[i] = val;
		}
		return ret;
	}

}
