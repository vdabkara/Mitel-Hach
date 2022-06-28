/**
 * Copyright (c) 2015, Oracle Corporation and/or its affiliates. All rights reserved.
 *
 * The sample code in this document or accessed through this document is not certified or
 * supported by Oracle. It is intended for educational or testing purposes only. Use of this
 * sample code implies acceptance of the License Agreement
 * (http://www.oracle.com/technetwork/licenses/standard-license-152015.html).
 *
 * File: Stopwatch.java
 * Abstract: This class is used to time a section of code
 * Version: 1.0
 */
 
package com.ka.kcapp.widget.common;



/** This class is used to time a section of code
 * @author dov
 */
public class Stopwatch {

	private long startTime;
	private long stopTime;
	
	/**
	 * Constructor for StopWatch
	 */
	public Stopwatch() {
		super();
		this.startTime = System.currentTimeMillis();
	}
	
	/**
	 * Method to stop timing the StopWatch instance
	 */
	public void stop(){
		stopTime = System.currentTimeMillis();
	}
	
	/**
	 * Method to return the time elapsed since instantiation of the object in ms
	 * @return long duration since the object was instantiated in ms
	 */
	public long elapsedTime(){
		return stopTime-startTime;
	}
	
	/**
	 * Method to return the time elapsed since instantiation of the object in ms as a String
	 * @return String duration since the object was instantiated in ms
	 */
	public String elapsedTimeString(){
		return new Long(stopTime-startTime).toString();
	}
	
}
