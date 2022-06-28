package com.ka.kcapp.listner;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.PropertyConfigurator;

/**
 * Application Lifecycle Listener implementation class ContextListner
 *
 */
public class ContextListner implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		try
		{
			// CODE FOR INITIALIZING LOG 4J
			ServletContext context = event.getServletContext();
            String log4jConfigFile = context.getInitParameter("log4j-config-location");
            String fullPath = context.getRealPath("") + File.separator + log4jConfigFile;
            PropertyConfigurator.configure(fullPath);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
}
