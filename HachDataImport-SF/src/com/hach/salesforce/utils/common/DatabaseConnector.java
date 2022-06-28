package com.hach.salesforce.utils.common;

import java.sql.Connection;
import java.sql.DriverManager;
import org.apache.log4j.Logger;

public class DatabaseConnector {
	static Logger logger = Logger.getLogger(DatabaseConnector.class);

	/**
	 * Function will establish Connection with Local Database and will return
	 * connection Object.
	 * 
	 * @return
	 */
	public static Connection getConnection() throws Exception {
		Connection connection = null;
		try {
			/*
			 * Load all the required parameters from properties file DataBase
			 * Driver ClassName Database URL Database UserName Database Password
			 */
			String databaseClassName = ApplicationProperties.getProperty("jdbc.driverClassName");
			String databaseURL = ApplicationProperties
					.getProperty("jdbc.databaseurl");
			String databaseUserName = ApplicationProperties
					.getProperty("jdbc.databaseUserName");
			String databasePassword = ApplicationProperties
					.getProperty("jdbc.databasePassword");
			/*
			 * Load Database Driver Class
			 */
			Class.forName(databaseClassName);
			// getConnection Object
			connection = DriverManager.getConnection(databaseURL,
					databaseUserName, databasePassword);
			// connection = DriverManager.getConnection(databaseURL);

			// set databaseURL and databaseClassName to null
			databaseClassName = null;
			databaseURL = null;
			// set userName & password to null
			databaseUserName = null;
			databasePassword = null;
		} catch (Exception e) {
			Utilities.printStackTraceToLogs(DatabaseConnector.class.getName(), "getConnection()", e);
			// set connection to null
			connection = null;
		}
		return connection;
	}
}