package com.softclouds.miteldataloader.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;
import java.util.concurrent.ConcurrentSkipListMap;

import javax.swing.filechooser.FileSystemView;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * Class with static methods for File-related parsing and processing.
 * 
 * @author Ray
 *
 */
public class FileUtility
{
	public static final OsType		osType;

	// public static final String DESKTOP_DIRECTORY;

	/**
	 * Should we delete the source directories on the backup destination? This is useful if we
	 * remove files and do not wish to maintain them anymore.
	 */
	private static final boolean	DELETE_SRC_DIRS_ON_BACKUP		= false;

	public static final String		DATA_STRUCTURES_TEST_DIRECTORY	=
			"data" + File.separator + "data_structures_test";

	public static final String		PROJECT_DIRECTORY;
	public static final String		WORKSPACE_DIRECTORY;

	private static final int		GFL_DEPTH_LIMIT					= 100;

	public static final String		FILE_SEPARATOR_REGEX;

	static
	{
		String osName = System.getProperty("os.name").toLowerCase();

		if (osName.contains("win"))
		{
			PROJECT_DIRECTORY = "C:\\softclouds\\programming\\projects\\";
			WORKSPACE_DIRECTORY = "C:\\Users\\Ray\\workspace\\";
			FILE_SEPARATOR_REGEX = "\\" + File.separator;
			osType = OsType.WINDOWS;
			System.out.println(
					"Initialized FileUtility due to a Windows system.  os.name property is '"
							+ osName + "'");
		}
		else if (osName.contains("nix") || osName.contains("linux"))
		{
			PROJECT_DIRECTORY = "/home/ray/Desktop/programming/projects/";
			WORKSPACE_DIRECTORY = "/home/oracle/eclipse/softclouds/";
			FILE_SEPARATOR_REGEX = "/";
			osType = OsType.UNIX;
			System.out
					.println("Initialized FileUtility due to a Linux system.  os.name property is '"
							+ osName + "'");
			// throw new UnsupportedOperationException("unix... what is the WORKSPACE_DIRECTORY?");
		}
		else
		{
			PROJECT_DIRECTORY = "";
			osType = OsType.UNKNOWN;
			throw new UnsupportedOperationException("Unsupported OS '" + osName + "'");
		}
	}

	/**
	 * Returns true if the given file exists, regardless of whether or not the size is 0. It also
	 * returns true if it is a directory.
	 */
	public static boolean exists(String fileName)
	{
		if (fileName == null)
		{
			throw new IllegalArgumentException("fileName is null");
		}

		File file = new File(fileName);

		boolean ret = file.exists();

		return ret;
	}

	/**
	 * Returns true if all of the given files exist in the given directory.
	 * 
	 * @param directory The directory which the files must exist in.
	 * @param files Variable arguments list of Strings of files which must exist.
	 * @return
	 */
	public static boolean existsInDirectory(String directory, String... files)
	{
		for (String file : files)
		{
			String completeFileName = directory + file;
			if (!exists(completeFileName))
				return false;
		}
		return true;
	}

	/**
	 * Returns true if the given file is empty. Throws an IllegalArgumentException if the fileName
	 * is null. If the file does not exist,
	 */
	public static boolean emptyFile(String fileName)
	{
		if (fileName == null)
			throw new IllegalArgumentException("fileName is null");
		File file = new File(fileName);

		if (file.length() == 0)
		{
			return true;
		}
		return false;
	}

	/**
	 * reads a whitespace delimited file
	 */
	public static Vector<String> readTabDelimitedFile(String fileName)
	{
		Vector<String> lines = readLines(fileName);

		Vector<String> ret = new Vector<String>();

		for (String line : lines)
		{
			if (line.startsWith("//") == false)
				ret.addAll(StringUtility.tokenize(line, "\t"));
		}
		return ret;
	}

	/**
	 * Sees if all of the files specified exist
	 */
	public static boolean allExist(Collection<String> fileNames)
	{
		boolean ret = true;

		for (String fileName : fileNames)
			if (exists(fileName) == false)
				ret = false;

		return ret;
	}

	/**
	 * Gets all the unique directories.
	 */
	public static TreeSet<String> getUniqueDirectories(String dir)
	{
		return getUniqueDirectories(FileUtility.getFileList(dir));
	}

	/**
	 * Gets a list of unique directories from the given files list.
	 */
	public static TreeSet<String> getUniqueDirectories(Vector<String> fileList)
	{
		TreeSet<String> directories = new TreeSet<String>();

		for (String fileName : fileList)
			directories.add(fileName.substring(0, fileName.lastIndexOf(File.separator)));

		return directories;
	}

	/**
	 * Makes a directory. If the given dir is a file name, it creates the path required for it to be
	 * saved.
	 */
	public static void mkDir(String dir)
	{
		// try to truncate the file if it exists
		if ((dir.indexOf(".") != -1) || (dir.endsWith(File.separator) == false))
		{
			int ls = dir.lastIndexOf(File.separator);

			if (ls == -1)
			{
				return;
			}

			dir = dir.substring(0, ls);
		}

		if (exists(dir))
		{
			return;
		}
		// int lastIndex = dir.lastIndexOf ("/");

		// if (lastIndex != -1)
		// mkDir (dir.substring (0, lastIndex));

		try
		{
			boolean success = (new File(dir)).mkdirs();

			if (!success)
				throw new UnsupportedOperationException(
						"FileUtility.mkdir Unable to create directory '" + dir + "'");
			else
				System.out.println("Created directory " + dir);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Returns a list of files in a given directory and of any extension, including subdirectories,
	 * with a limit on the number of files returned.
	 */
	public static Vector<String> getFileList(String dir, int maxFiles)
	{
		boolean recurseSubDirectories = true;
		return getFileList(dir, "any", recurseSubDirectories, maxFiles);
	}

	/**
	 * Returns a list of files in a given directory and of any extension, including subdirectories.
	 * If the directory doesn't exist, throws an UnsupportedOperationException.
	 */
	public static Vector<String> getFileList(String dir)
	{
		boolean recurseSubDirectories = true;
		int maxFiles = -1;
		return getFileList(dir, "any", recurseSubDirectories, maxFiles);
	}

	/**
	 * Returns a list of files in a given directory and of a given extension, including
	 * subdirectories. If the directory doesn't exist, throws an UnsupportedOperationException.
	 */
	public static Vector<String> getFileList(String dir, String ext, int maxFiles)
	{
		boolean recurseSubDirectories = true;
		return getFileList(dir, ext, recurseSubDirectories, maxFiles);
	}

	/**
	 * Returns a list of files in a given directory and of a given extension, including
	 * subdirectories. If the directory doesn't exist, throws an UnsupportedOperationException.
	 */
	public static Vector<String> getFileList(String dir, String ext)
	{
		boolean recurseSubDirectories = true;
		int maxFiles = -1;
		return getFileList(dir, ext, recurseSubDirectories, maxFiles);
	}

	/**
	 * Returns a list of files in a given directory and of any extension, with a parameter for
	 * including subdirectories. If the directory doesn't exist, throws an
	 * UnsupportedOperationException.
	 */
	public static Vector<String> getFileList(String dir, boolean recurseSubDirectories)
	{
		int maxFiles = -1;
		return getFileList(dir, "any", recurseSubDirectories, maxFiles);
	}

	private static int				getFileListDepth	= 0;

	private static TimingUtility	timingUtility		= new TimingUtility();

	/**
	 * Returns a list of files in a given directory and of a given extension, with a parameter for
	 * including subdirectories.
	 * 
	 * Skips past any files which don't have read access.
	 * 
	 * If the directory doesn't exist, throws an UnsupportedOperationException.
	 * 
	 * @param dir The directory from which to get a file list.
	 * @param recurseSubdirectories Should we also include files from subdirectories?
	 * @param maxFiles The maxinum number of files we wish to return. If this is -1, there is no
	 *            maximum. If maxFiles is greater than the total number of files in the directory,
	 *            it will return the full list.
	 */
	public static Vector<String> getFileList(String dir, String ext, boolean recurseSubDirectories,
			int maxFiles)
	{
		// input validation
		if (!StringUtility.isAlpha(ext))
			throw new UnsupportedOperationException("FileUtility.getFileList : ext '" + ext
					+ "' contains non-alphanumeric characters.  Did you put the period by mistake?  i.e. \".java\" instead of \"java\".");
		// System.out.println("getting file list from directory " + dir + "\n...");
		if (!dir.endsWith(File.separator))
			dir += File.separator;

		Vector<String> ret = new Vector<String>(0);
		File directory = new File(dir);

		if (!directory.isDirectory())
			throw new UnsupportedOperationException(
					"Argument dir = '" + dir + "' is not a directory.\ncwd = '"
							+ FileUtility.getCurrentWorkingDirectory() + "'");

		File[] fileList = directory.listFiles();

		if (fileList == null)
		{
			mkDir(dir);
			return ret;
			// System.exit (-1);
		}

		getFileListDepth++;

		String pk = "";
		if (getFileListDepth < GFL_DEPTH_LIMIT)
		{
			// pk = "gfl " + FileUtility.getLastDir(dir);
			pk = "gfl " + dir;
		}

		for (int i = 0; i < fileList.length; i++)
		{
			String fileInDirectory = fileList[i].getName();

			// if it is a file
			if (fileList[i].isDirectory() == false)
			{
				boolean valid = fileList[i].canRead() && !fileList[i].isHidden();
				if (valid)
				{
					int dotIndex = fileInDirectory.lastIndexOf('.');
					String thisExt;

					if (dotIndex == -1)
						thisExt = "";
					else
						thisExt = fileInDirectory.substring(dotIndex + 1);

					thisExt = thisExt.toLowerCase();
					ext = ext.toLowerCase();

					if (("any".equals(ext) == true) || (thisExt.equals(ext) == true))
					{
						String completeFileName = dir + fileInDirectory;

						if (ret.size() >= maxFiles && maxFiles != -1)
							continue;
						ret.add(completeFileName);

						// ret.add (fileInDirectory);
					}
				}
			}
			// else recurse for subdirectories
			else if (recurseSubDirectories == true)
			{
				if (ret.size() >= maxFiles && maxFiles != -1)
					continue;

				boolean valid = fileList[i].canRead() && !fileList[i].isHidden();

				if (valid)
				{
					String sub = dir + fileInDirectory;
					Vector<String> subDirFileList = getFileList(sub, ext);

					for (String subDirFile : subDirFileList)
					{
						if (ret.size() >= maxFiles && maxFiles != -1)
							continue;
						ret.add(subDirFile);
					}
				}
			}

		}

		getFileListDepth--;

		// System.out.println("done. Found " + ret.size() + " files.");
		return ret;
	}

	/**
	 * Gets the last directory in the given dir.
	 * 
	 * @param dir
	 * @return
	 */
	// private static String getLastDir(String dir)
	// {
	// File f = new File(dir);
	//
	// dir = f.getPath();
	//
	// String regex = null;
	// switch (FileUtility.osType)
	// {
	// case UNIX:
	// regex = "/";
	// break;
	// case UNKNOWN:
	// throw new UnsupportedOperationException(
	// "Unknown OS type. Cannot get file delimieter regex");
	// case WINDOWS:
	// regex = "\\\\";
	// break;
	//
	// }
	// Vector<String> tokens = StringUtility.tokenize(dir, regex);
	//
	// return tokens.lastElement();
	// }

	/**
	 * Gets the size of the given file.
	 */
	public static long getFileSize(String fileName)
	{
		if (fileName == null)
			throw new IllegalArgumentException("fileName is null");

		File file = new File(fileName);

		if (file.isDirectory())
			throw new UnsupportedOperationException(
					"given fileName '" + fileName + "' is actually a directory.");
		if (!exists(fileName))
			throw new UnsupportedOperationException("the fileName '" + fileName
					+ "' does not exists, therefore it does not have a size.");

		return file.length();
	}

	/**
	 * Reads the data from a specified file. If the file does not exist, throws an
	 * IllegalArgumentException.
	 * 
	 * @param fileName
	 * @return
	 */
	public static String read(String fileName)
	{
		InputFile input = new InputFile(fileName);
		String ret = input.getData();
		input.close();
		return ret;
	}

	/**
	 * Reads the data from a specified file.
	 * 
	 * @param fileName
	 * @return
	 */
	public static byte[] readAsBytes(String fileName)
	{
		FileInputStream in;
		File file = new File(fileName);
		byte[] ret = new byte[(int) file.length()];

		try
		{
			in = new FileInputStream(fileName);
			in.read(ret);
			in.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return ret;
	}

	/**
	 * Reads all lines in a file, does not include any blank lines. Does not trim each line. If the
	 * file does not exist, returns an empty list.
	 */
	public static Vector<String> readLines(String fileName)
	{
		if (!exists(fileName))
		{
			System.out.println("WARNING: FileUtility.readLines: file " + fileName
					+ " does not exist.  Returning empty list.");
			return new Vector<String>();
		}

		InputFile input = new InputFile(fileName);

		Vector<String> ret =
				StringUtility.tokenize(input.getData(), StringUtility.CARRIAGE_RETURN_REGEX, false);

		input.close();
		return ret;
	}

	/**
	 * Reads a file line by line into a set of strings. If the file does not exist, returns an empty
	 * Set.
	 * 
	 * @param string
	 * @return
	 */
	public static TreeSet<String> readStringSet(String fileName)
	{
		return new TreeSet<String>(readLines(fileName));
	}

	/**
	 * Reads a newline delimited map of strings to sets of strings.
	 * 
	 * @param fileName The name of the file to read from.
	 */
	public static TreeMap<String, TreeSet<String>> readStringMap(String fileName)
	{
		TreeMap<String, TreeSet<String>> ret = new TreeMap<String, TreeSet<String>>();

		if (exists(fileName) == false)
		{
			System.out.println("WARNING: FileUtility.readStringMap: file " + fileName
					+ " does not exist.  Returning empty map.");
			return ret;
		}

		InputFile input = new InputFile(fileName);

		while (input.eof() == false)
		{
			@SuppressWarnings("unused")
			String key = input.readLine().trim();
			TreeSet<String> stringSet = new TreeSet<String>();

			String nextLine = input.readLine().trim();
			while (nextLine.equals("") == false)
			{
				stringSet.add(nextLine);
				if (nextLine.equals("") == false)
					stringSet.add(nextLine);
			}
		}

		return ret;
	}

	/**
	 * Reads all files in a directory of any extension type.
	 * 
	 * @param dir The directory we want to find files in.
	 */
	public static Vector<InputFile> readFiles(String dir)
	{
		return readFiles(dir, "any");
	}

	/**
	 * Reads all files in a directory with a specific file extension.
	 * 
	 * @param dir The directory we want to find files in.
	 * @param ext The extension we wish to read files of.
	 */
	public static Vector<InputFile> readFiles(String dir, String ext)
	{
		Vector<String> fileNames = getFileList(dir, ext);
		return readFiles(fileNames);
	}

	/**
	 * Reads all files in the given directory and returns a map of file name -> file lines.
	 * 
	 * @param useCaseDir
	 * @return
	 */
	public static ConcurrentSkipListMap<String, Vector<String>> readDir(String dir)
	{
		Vector<String> files = getFileList(dir);

		ConcurrentSkipListMap<String, Vector<String>> ret =
				new ConcurrentSkipListMap<String, Vector<String>>();
		for (String file : files)
			ret.put(file, FileUtility.readLines(file));

		return ret;
	}

	/**
	 * Reads all files in a directory with a specific file extension.
	 * 
	 * @param dir The directory we want to find files in.
	 * @param ext The extension we wish to read files of.
	 */
	public static Vector<InputFile> readFiles(Vector<String> fileNames)
	{
		Vector<InputFile> ret = new Vector<InputFile>();

		// System.out.print(" InputFile.openFiles : opening " + fileNames.size()
		// + " files from directory '" + dir + "'");

		String pk = "reading files";

		int readBytes = 0;
		for (String fileName : fileNames)
		{
			InputFile input = new InputFile(fileName);
			ret.add(input);

			readBytes += input.size();

			String rateStr;

		}

		return ret;
	}

	/**
	 * Writes a set of strings to the file as lines.
	 */
	public static void write(String fileName, TreeSet<String> stringSet)
	{

		StringBuilder sb = new StringBuilder();

		for (String str : stringSet)
			sb.append(str + "\n");

		write(fileName, sb.toString());
	}

	/**
	 * Writes a set of things to the file as lines.
	 */
	public static <T> void write(String fileName, Collection<T> data)
	{

		StringBuilder sb = new StringBuilder();

		for (T t : data)
			sb.append(t + "\n");

		write(fileName, sb.toString());
	}

	/**
	 * Writes a vector of strings to the file as lines
	 */
	public static void write(String fileName, Vector<String> stringList)
	{
		// System.out.println("Writing stringSet to " + fileName);
		StringBuilder sb = new StringBuilder();

		for (String str : stringList)
			sb.append(str + "\n");

		write(fileName, sb.toString().trim());
	}

	/**
	 * Writes the given data to the given file name.
	 * 
	 * @param fileName
	 * @param data
	 */
	public static void write(String fileName, String data)
	{
		OutputFile output = new OutputFile(fileName);
		output.write(data);
		output.close();
	}

	/**
	 * Writes the given data to the given file name.
	 * 
	 * @param fileName
	 * @param data
	 */
	public static void write(String fileName, byte[] data)
	{
		FileUtility.mkDir(fileName);
		FileOutputStream out;
		try
		{
			out = new FileOutputStream(fileName);
			out.write(data);
			out.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	public static boolean TIME_SENSITIVE_BACKUP = false;

	/**
	 * Writes a timestamp to the given directory. Overwrites previously existing timestamp.
	 * 
	 * @param destDir
	 */
	private static void writeTimeStamp(String destDir, String tag)
	{
		String fileName = destDir + "timestamp_" + tag + ".txt";

		String timestamp = TimingUtility.getAbsDateString();
		String msg = " timestamp " + timestamp + " to " + fileName;

		FileUtility.write(fileName, timestamp);
	}

	/**
	 * Deletes files which are in the destDir but not in the srcDir.
	 * 
	 * @param srcDir
	 * @param destDir
	 */
	private static void deleteNonExistentFiles(String srcDir, String destDir)
	{
		if (!exists(destDir))
		{
			return;
		}

		System.out.println("getting file list for " + srcDir);
		Vector<String> srcFiles = FileUtility.getFileList(srcDir);
		System.out.println("getting file list for " + destDir);
		Vector<String> destFiles = FileUtility.getFileList(destDir);

		int count = 0;
		for (String destFile : destFiles)
		{
			String relativeDestFile = destFile.substring(destDir.length());

			boolean srcContains = false;
			for (String srcFile : srcFiles)
			{
				if (srcFile.endsWith(relativeDestFile))
				{
					srcContains = true;
					break;
				}
			}
			if (!srcContains)
			{
				FileUtility.delete(destFile);
				count++;
			}
		}
		// ioKeyboard.readLine();

	}

	/**
	 * Returns the path only of the given complete file name. If there is no path, it returns an
	 * empty string.
	 * 
	 * i.e. /home/Desktop/fileName.txt -> /home/Desktop/
	 * 
	 * test.txt -> (empty string)
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getPathNameOnly(String fileName)
	{
		int index = fileName.lastIndexOf(File.separator);

		if (index == -1)
			return "";
		else
			return fileName.substring(0, index);
	}

	/**
	 * Returns the file name only given a specific path. It *does* include the extension.
	 * 
	 * i.e. /home/Desktop/fileName.txt -> fileName.txt
	 * 
	 * C:\Users\Ray\Desktop\filename.txt -> fileName.txt
	 * 
	 * test.txt -> test.txt
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileNameOnly(String fileName)
	{
		int index = fileName.lastIndexOf(File.separator);

		if (index == -1)
			return fileName;
		else
			return fileName.substring(index + 1);
	}

	/**
	 * Returns the file name only without the extension given a specific path.
	 * 
	 * i.e. /home/Desktop/fileName.txt -> fileName
	 * 
	 * C:\Users\Ray\Desktop\filename.txt -> fileName
	 * 
	 * test.txt -> test
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileNameOnlyWithoutExtension(String fileName)
	{
		String fn = getFileNameOnly(fileName);

		int index = fn.lastIndexOf(".");

		if (index == -1)
			return fn;
		else
			return fn.substring(0, index);
	}

	/**
	 * Deletes the specified file or directory.
	 * 
	 * @param fileName The name of the directory / file to delete.
	 */
	public static void delete(String fileName)
	{
		delete(new File(fileName));
	}

	/**
	 * Deletes the specified file.
	 * 
	 * @param file
	 */
	private static void delete(File file)
	{

		if (file.isDirectory())
		{
			if (file.list().length > 0)
			{
				String files[] = file.list();

				for (String fileName : files)
				{
					File f = new File(file, fileName);
					delete(f);
				}
			}
			boolean isDeleted = file.delete();
		}
		else
		{
			file.delete();
		}
	}

	/**
	 * Gets the current working directory.
	 * 
	 * @return
	 */
	public static String getCurrentWorkingDirectory()
	{
		return System.getProperty("user.dir") + File.separator;
	}

	/**
	 * Gets the list of files that contain the given string.
	 * 
	 * @param dir
	 * @param containString
	 * @return
	 */
	public static Vector<String> getFilesThatContain(String dir, String containString)
	{
		Vector<String> allFiles = getFileList(dir);

		Vector<String> ret = new Vector<String>();
		for (String fileName : allFiles)
			if (fileName.contains(containString))
				ret.add(fileName);

		return ret;
	}

	/**
	 * Gets a unique file name in the given directory given the given file tag.
	 * 
	 * @param dir
	 * @param fileTag
	 */
	public static String getUniqueFileName(String dir, String fileTag, String extension)
	{
		if (!StringUtility.isAlphaNum(extension))
			throw new IllegalArgumentException("FileUtility.getUniqueFileName : extension '"
					+ extension + "' is invalid.  It must be alphanumeric.");

		Vector<String> files = FileUtility.getFilesThatContain(dir, fileTag);
		int index = files.size() + 1;

		String indexStr = StringUtility.padZeroes(index, 4);
		String fileName = dir + fileTag + "_" + indexStr + "." + extension;

		return fileName;
	}

	/**
	 * Returns the extension of the given file name. If it is empty,
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getExtension(String fileName)
	{
		int dotIndex = fileName.lastIndexOf('.');

		if (dotIndex == -1)
			return null;
		else
			return fileName.substring(dotIndex + 1);
	}

	/**
	 * 
	 * @param string
	 * @return
	 */
	public static String getDriveLabel(String fileName)
	{
		File file = new File(fileName);
		String label = FileSystemView.getFileSystemView().getSystemDisplayName(file);
		// String description = FileSystemView.getFileSystemView().getSystemTypeDescription(file);
		// System.out.println("getSystemDisplayName : " + label);
		// System.out.println("getSystemTypeDescription : " + description);
		return label;
	}

	/**
	 * Gets a path string of the given variable list of paths. If the path list is empty, throws an
	 * exception.
	 */
	public static String getPath(String... paths)
	{
		if (paths.length == 0)
			throw new IllegalArgumentException("Requires at least one path argument");
		String ret = "";
		for (int i = 0; i < paths.length; i++)
			ret += paths[i] + File.separator;

		return ret;
	}

	/**
	 * Writes the given HSSF workbook to file.
	 * 
	 * @param fileName
	 * @param workbook
	 */
	public static void write(String fileName, HSSFWorkbook workbook)
	{
		FileUtility.mkDir(fileName);
		try
		{
			OutputStream outputStream;
			outputStream = new FileOutputStream(fileName);
			workbook.write(outputStream);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
