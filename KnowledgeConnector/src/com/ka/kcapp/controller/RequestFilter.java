/**
 * Copyright (c) 2015, Oracle Corporation and/or its affiliates. All rights reserved.
 *
 * The sample code in this document or accessed through this document is not certified or
 * supported by Oracle. It is intended for educational or testing purposes only. Use of this
 * sample code implies acceptance of the License Agreement
 * (http://www.oracle.com/technetwork/licenses/standard-license-152015.html).
 *
 * File: RequestFilter.java
 * Abstract: This class is responsible for filtering and sanitizing all requests made to this WebApp
 * Version: 1.0
 */
 
package com.ka.kcapp.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;


/**
 * Servlet Filter implementation class RequestFilter
 * This class is responsible for filtering and sanitizing all requests made to this WebApp
 */
public class RequestFilter implements Filter {
	
	final static Logger logger = Logger.getLogger(RequestFilter.class);//Logger for the Filter Class.
	
	/**
	 * Constructor for RequestFilter
	 */
    public RequestFilter() {
    }

    /*
     * @see javax.servlet.Filter#destroy()
     */
	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		chain.doFilter(req, res);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
