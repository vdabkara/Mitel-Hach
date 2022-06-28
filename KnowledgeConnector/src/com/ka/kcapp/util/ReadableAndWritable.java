package com.ka.kcapp.util;

/**
 * This defines read and write methods for structured input and output. Generally, any extending
 * subclasses of this class define a constructor
 *
 * Constructor (InputFile input)
 * 
 * which should in turn call read(.).
 * 
 * For writing
 * 
 * write(OutputFile output)
 * 
 * write(String fileName)
 * 
 * both default to writing toString() to file.
 * 
 * @author ray
 *
 */
public abstract class ReadableAndWritable
{
	/**
	 * Reads into this object, generally any class extends ReadableAndWritable should contain a
	 * constructor which calls this.
	 * 
	 * @param input the InputFile to read from.
	 */
	public abstract void read(InputFile input);

	/**
	 * Writes this object to an OutputFile. This is the default implementation which defers to the
	 * toString() method.
	 * 
	 * @param output OutputFile to write to.
	 */
	public void write(OutputFile output)
	{
		output.write(toString());
		output.flush();
	}

	/**
	 * Writes this object's data to the given fileName. This is the default implementation which
	 * defers to the write(OutputFile output) method which in turn defers to the toString() method.
	 * 
	 * @param fileName
	 */
	public void write(String fileName)
	{
		OutputFile output = new OutputFile(fileName);
		write(output);
		output.close();
	}
}
