/**
 * Copyright (c) 2015, Oracle Corporation and/or its affiliates. All rights reserved.
 *
 * The sample code in this document or accessed through this document is not certified or
 * supported by Oracle. It is intended for educational or testing purposes only. Use of this
 * sample code implies acceptance of the License Agreement
 * (http://www.oracle.com/technetwork/licenses/standard-license-152015.html).
 *
 * File: SchemaAttribute.java
 * Abstract: Public Class used to process the Schema Attributes of a Channel
 * Version: 1.0
 */

package com.ka.kcapp.util;

/**
 * Public Class used to process the Schema Attributes of a Channel
 * @author Prashant Chaturvedi
 */
public class SchemaAttribute {
	
	private String title;
	private String value;
	private int depth;
	
	/**
	 * Constructor for the Schema Attribute class
	 * @param title Title of the Schema Attribute
	 * @param value Value of the Schema Attribute
	 */
	public SchemaAttribute(String title, String value, int depth) {
		super();
		this.title = title;
		this.value = value;
		this.depth = depth;
		
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public int getDepth() {
		return depth;
	}
	
	public void setValue(int depth) {
		this.depth = depth;
	}

}
