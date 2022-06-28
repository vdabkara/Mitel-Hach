package com.ka.kcapp.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

public class OutputFile
{

	// final variables
	static final int		EOF					= 0xffff;	// end of file
															// marker
	static final int		FINAL_CONSTANT_1	= 1;		// desc

	// data members
	private boolean			leadingFileDelimiter;
	private String			fileName;						// file name
															// including all
															// paths
	private String			rootPath;						// the root path
	private Vector<String>	paths;							// all subdirectory
															// paths
	private String			name;							// file's name
															// without extension
	private String			ext;							// file's extension

	private BufferedWriter	bwriter;						// buffer to make it
															// faster

	/**
	 * Constructor creatse a
	 */
	public OutputFile(String fileName)
	{
		if (fileName == null)
			throw new UnsupportedOperationException(
					"Given fileName is null, it must be a String and proper directory at that");

		FileUtility.mkDir(fileName);

		this.fileName = fileName;

		leadingFileDelimiter = fileName.startsWith(File.separator);

		String delimiter = File.separator.equals("\\") ? "\\\\" : File.separator;
		delimiter += "|\\.";
		Vector<String> tokenizedName = StringUtility.tokenize(fileName, delimiter, false);
		int pathSize = tokenizedName.size();

		// log.add (fileName);
		// log.add (tokenizedName);

		// System.out.println("fileName = " + fileName);
		// System.out.println("tokenizedName = " + tokenizedName);
		// System.out.println("delim " + delimiter);

		rootPath = FileUtility.getCurrentWorkingDirectory();

		boolean hasExtension = fileName.indexOf(".") != -1;
		if (hasExtension)
		{
			name = tokenizedName.get(pathSize - 2);
			ext = tokenizedName.get(pathSize - 1);

			paths = new Vector<String>(0);
			for (int i = 0; i < pathSize - 2; i++)
				paths.add(tokenizedName.get(i));
		}
		else
		{
			name = tokenizedName.get(pathSize - 1);
			ext = "";

			paths = new Vector<String>(0);
			for (int i = 0; i < pathSize - 1; i++)
				paths.add(tokenizedName.get(i));
		}

		// functionality verification
		information();

		try
		{
			FileOutputStream fs = new FileOutputStream(fileName);
			// System.out.println("created NEW OUTPUT STREAM AT '" + fileName + "'");

			// use the encoding which allows all 256 8-bit char values
			OutputStreamWriter writer; // output writer
			writer = new OutputStreamWriter(fs, "UTF-8"); // also "UTF-8",
															// "US-ASCII",
															// "ISO-8859-1"
			bwriter = new BufferedWriter(writer, 5 * 1024 * 1024);
		}
		catch (IOException e)
		{
			throw new UnsupportedOperationException(e + " (" + fileName + ")");
		}

	}

	/**
	 * Displays information about this file
	 */
	public void information()
	{
		// log.add("output file name is '" + fileName + "'");
		// log.add(" root path is '" + rootPath + "'");
		//
		// for (int i = 0; i < paths.size(); i++)
		// log.add(" path(" + i + ") is '" + paths.get(i) + "'");
		//
		// log.add(" name is '" + name + "'");
		// log.add(" ext is '" + ext + "'");
	}

	/**
	 * Gets the full file name including all paths and the extension.
	 */
	public String getFileName()
	{
		String ret = "";

		if (leadingFileDelimiter)
			ret += File.separator;

		for (String path : paths)
			ret += path + File.separator;
		ret += name + "." + ext;

		// System.out.println("gfn paths " + paths);
		// System.out.println("gfn name " + name);
		// System.out.println("gfn ext " + ext);
		// System.out.println("gfn ret " + ret);
		return ret;
	}

	/**
	 * Gets the file path
	 */
	public String getFilePath()
	{
		String r = rootPath;

		for (String path : paths)
			r += File.separator + path;

		return r;
	}

	/**
	 * Flushes and closes this file.
	 */
	public void close()
	{
		try
		{
			bwriter.flush();
			bwriter.close();
			bwriter = null;
		}
		catch (IOException e)
		{
			throw new UnsupportedOperationException("IOException " + e + " (" + fileName + ")");
		}
	}

	/**
	 * writes standardized octave plot code
	 */
	// this code belongs somewhere else
	public void writePlotCode(String title, String xlabel, String ylabel, String fileName)
	{
		// String figurePath = "~/Desktop/ep10/data/gendata/analysis/";

		// make the directory
		int liDir = fileName.lastIndexOf("/");
		if (liDir == -1)
		{
			FileUtility.mkDir("data/gendata/analysis/");
			// log.add("ioFileIOWriter.writePlotCode : last index of / is -1 for " + fileName);
		}
		else
		{
			FileUtility.mkDir("data/gendata/analysis/" + fileName.substring(0, liDir));
		}

		title = title.replace("_", "\\_");
		xlabel = xlabel.replace("_", "\\_");
		ylabel = ylabel.replace("_", "\\_");
		writeln("title ('" + title + "');");
		writeln("xlabel ('" + xlabel + "');");
		writeln("ylabel ('" + ylabel + "');");

		// ps eps jpg pdf
		// writeln ("print ('" + figurePath + fileName + ".eps', \"-S" +
		// (int)(Plottable.DEFAULT_XS * Plottable.EPS_FACTOR) + "," +
		// (int)(Plottable.DEFAULT_YS * Plottable.EPS_FACTOR) + "\");");
		// writeln ("print ('" + figurePath + fileName + ".jpg', \"-S" +
		// Plottable.DEFAULT_XS + "," + Plottable.DEFAULT_YS + "\");");
	}

	/**
	 * writes a set of strings to the file as lines
	 */
	public void write(TreeSet<String> ss)
	{
		for (String s : ss)
			writeln(s);
	}

	/**
	 * writes a string to the file
	 */
	public void write(String s)
	{
		try
		{
			int len = s.length();
			bwriter.write(s, 0, len);
		}
		catch (IOException e)
		{
			String message = "ioFileIOWriter.write IOException " + e + " (" + fileName + ")\n";
			message += s + ": of length " + s.length() + "into " + fileName + " file";
			throw new UnsupportedOperationException(message);
		}
	}

	/**
	 * writes a character to the file
	 */
	public void write(char c)
	{
		write(c + "");
	}

	/**
	 * writes a blank line to the file
	 */
	public void writeln()
	{
		write("\n");
	}

	/**
	 * Writes a line to the file.
	 * 
	 * @param line The line to write to the file.
	 */
	public void writeln(String line)
	{
		write(line + "\n");
	}

	/**
	 * Writes a line to the file.
	 * 
	 * @param number The number to write to the file.
	 */
	public void writeln(int number)
	{
		writeln(number + "");
	}

	/**
	 * Writes these lines to the file.
	 * 
	 * @param lines The lines to write to the file.
	 */
	public void write(List<String> lines)
	{
		for (String s : lines)
			writeln(s.substring(s.indexOf(":") + 1));
	}

	/**
	 * writes a vector of lines to file in sorted fashion
	 */
	public void sortWrite(List<String> lines)
	{
		Collections.sort(lines);
		write(lines);
	}

	/**
	 * writes a packed two byte integer to the file
	 */
	public void writeTwoByteInteger(int i)
	{
		char c0 = (char) ((i & 0x0000ff00) >> 8);
		char c1 = (char) ((i & 0x000000ff) >> 0);

		write(c0);
		write(c1);
	}

	/**
	 * writes a packed four byte integer to the file
	 */
	public void writeFourByteInteger(int i)
	{
		char c0 = (char) ((i & 0xff000000) >> 24);
		char c1 = (char) ((i & 0x00ff0000) >> 16);
		char c2 = (char) ((i & 0x0000ff00) >> 8);
		char c3 = (char) ((i & 0x000000ff) >> 0);

		write(c0);
		write(c1);
		write(c2);
		write(c3);
	}

	/**
	 * flushes the file
	 */
	public void flush()
	{
		try
		{
			bwriter.flush();
		}
		catch (IOException e)
		{
			throw new UnsupportedOperationException(
					"OutputFile.flush IOException " + e + " (" + fileName + ")");
		}
	}

	/**
	 * reads a newline delimited map of strings to sets of strings
	 */
	public static void writeStringMap(String fileName, TreeMap<String, TreeSet<String>> map)
	{
		OutputFile output = new OutputFile(fileName);

		for (String key : map.keySet())
		{
			output.writeln(key);
			for (String s : map.get(key))
				output.writeln(s);

			output.writeln();
		}
	}

	/**
	 * Counterpart to InputFile.readStandard
	 * 
	 * @param data
	 */
	public void writeStandard(String data)
	{
		write(data + "|");
	}

	public void writeStandard(double data)
	{
		write(data + "|");
	}
}
