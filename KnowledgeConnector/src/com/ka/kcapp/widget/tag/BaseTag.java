/**
 * Copyright (c) 2015, Oracle Corporation and/or its affiliates. All rights reserved.
 *
 * The sample code in this document or accessed through this document is not certified or
 * supported by Oracle. It is intended for educational or testing purposes only. Use of this
 * sample code implies acceptance of the License Agreement
 * (http://www.oracle.com/technetwork/licenses/standard-license-152015.html).
 *
 * File: BaseTag.java
 * Abstract: Base Class for All Tag Classes featuring common utilities
 * Version: 1.0
 */

package com.ka.kcapp.widget.tag;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

/**
 * Base Class for All Tag Classes featuring common utilities
 * @author Prashant Chaturvedi
 */
public class BaseTag extends TagSupport {
	
	final static Logger logger = Logger.getLogger(BaseTag.class);
	
	private static final long serialVersionUID = 1L;
	private static ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.application");
	private static String interfaceLocale = resourceBundle.getString("INTERFACE_LOCALE");
	public ResourceBundle labelsBundle = getCurrentLocale();

	/**
	 * Method to get the resource bundle
	 * @return ResourceBundle resource bundle
	 */
	public static ResourceBundle getResourceBundle() {
		return resourceBundle;
	}
	
	/**
	 * Method to retrieve the interface locale
	 * @return String interface locale
	 */
	public static String getInterfaceLocale() {
		return interfaceLocale;
	}
	
	/**
	 * Method to check if the standard template has been set on the page. This method will also write it to the page in case it has not been already written.
	 * @return String HTML containing the contents of standard.ftl
	 */
	public String getCommonResourceHtml() {
		String resourceHtml = "";
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		boolean isResourceLoaded = false;
		
		if(request.getAttribute("isResourceLoaded") != null )
			isResourceLoaded = ((Boolean)request.getAttribute("isResourceLoaded"));

		if(!isResourceLoaded) {
			String resourcePath = resourceBundle.getString("RESOURCE_PATH");
			Map<String, Object> input = new HashMap<String, Object>();
			String currPageUrl = request.getHeader("referer");
	        if(currPageUrl == null )
	            currPageUrl = request.getRequestURL().toString();
	        if(currPageUrl.contains("/test/")){
	        	resourcePath = "../" + resourcePath;
	        	input.put("isTest", "true");
	        }
            input.put("resourcePath", resourcePath);
            resourceHtml = getTemplateHtml(input, "standard.ftl");
			request.setAttribute("isResourceLoaded", true);
		}
		
		return resourceHtml;
	}
	
	public String getExternalResourceHtml() {
		String resourceHtml = "";
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		boolean isResourceLoaded = false;
		
		if(request.getAttribute("isExternalResourceLoaded") != null )
			isResourceLoaded = ((Boolean)request.getAttribute("isExternalResourceLoaded"));

		if(!isResourceLoaded) {
			String resourcePath = resourceBundle.getString("RESOURCE_PATH");
			Map<String, Object> input = new HashMap<String, Object>();
			String currPageUrl = request.getHeader("referer");
	        if(currPageUrl == null )
	            currPageUrl = request.getRequestURL().toString();
	        if(currPageUrl.contains("/test/")){
	        	resourcePath = "../" + resourcePath;
	        	input.put("isTest", "true");
	        }
            input.put("resourcePath", resourcePath);
            resourceHtml = getTemplateHtml(input, "externalResource.ftl");
			request.setAttribute("isExternalResourceLoaded", true);
		}
		
		return resourceHtml;
	}
	
	/**
	 * Method to retrieve the HTML template for the templateName
	 * @param input Map of the variables to be entered into the template.
	 * @param templateName Name of the templateName to be used to generate the Template
	 * @return HTML template for the given template name and input data
	 */
        
	public static String getTemplateHtml(Map<String, Object> input, String templateName, ServletContext context) {
            String templateHtml = "";
            try {
                Configuration cfg = new Configuration();
                Template template;
                cfg.setServletContextForTemplateLoading(context, "/widgets/templates/custom");
                cfg.setIncompatibleImprovements(new Version(2, 3, 20));
                cfg.setDefaultEncoding("UTF-8");
                cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
                if(!templateName.endsWith(".ftl"))
                templateName = templateName.concat(".ftl");
                try{
                    template = cfg.getTemplate(templateName);
                }catch(Exception ex){
                    logger.debug("Could not find a copy of the file in the custom folder. Proceeding to check in the standard folder");
                    cfg.setServletContextForTemplateLoading(context, "/widgets/templates/standard");
                    template = cfg.getTemplate(templateName);
                }
                StringWriter output = new StringWriter();
                template.process(input, output);
                templateHtml = output.toString();
            } catch (Exception ex) {
                logger.error("Error in BaseTag.getTemplateHtml: ", ex);
            }
            return templateHtml;

        }        
        
	public  String getTemplateHtml(Map<String, Object> input, String templateName) {
            return BaseTag.getTemplateHtml(input, templateName, pageContext.getServletContext());
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * Method to add the widget JS to the widget Html
	 * @param widgetJs Widget javascript file name to be set in the widgetHtml
	 * @return boolean Flag to indicate if the widget JS has been appended to the Html
	 */
	public boolean isFileAdded(String widgetJs) {
		boolean isFileAdded = false;
		HashMap<String, String> widgetJsMap = null;
		
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		widgetJsMap = (HashMap<String, String>)request.getAttribute("widgetJsMap");
		
		if(widgetJsMap != null) {
			if(widgetJsMap.keySet().contains(widgetJs)) {
				isFileAdded = true;
			}
			else {
				widgetJsMap.put(widgetJs, widgetJs);
				request.setAttribute("widgetJsMap", widgetJsMap);
			}
		}else{
			widgetJsMap = new HashMap<String, String>();
			widgetJsMap.put(widgetJs, widgetJs);
			request.setAttribute("widgetJsMap", widgetJsMap);
		}
			return isFileAdded;
	}
	
	/**
	 * Method to get the instance ID of a widget
	 * @param widgetName Name of the widget of which the ID is to be retrieved
	 * @return String widget instance ID
	 */
	public static String getWidgetInstanceId(String widgetName){
	    return widgetName + "_" + getDefaultNumber(0, 1000000);
	}
    
	/**
	 * Method to return a random integer for creating a widget instance ID
	 * @param min The minimum integer allowed
	 * @param max The maximum integer allowed
	 * @return Integer Random instance ID for the widget
	 */
    public static int getDefaultNumber(int min, int max){
		Random rand = new Random();
	    return rand.nextInt((max - min) + 1) + min;
	}
    
    public String getWidgetJsPath(String relativePath){
    	String widgetJsPath = resourceBundle.getString("WIDGET_JS_PATH").concat(relativePath);
    	HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		String currPageUrl = request.getHeader("referer");
        if(currPageUrl == null ) {
            currPageUrl = request.getRequestURL().toString();
        }
        return currPageUrl.contains("/test/") ? "../" + widgetJsPath : widgetJsPath;
    }
    
    /**
     * Method to get the current Locale mappings 
     * @return ResourceBundle Resource Bundle object containing locale mappings 
     */
    public ResourceBundle getCurrentLocale(){
    	return ResourceBundle.getBundle("resources.LabelsBundle", new Locale("en"));
    }
}
