/**
 * Copyright (c) 2015, Oracle Corporation and/or its affiliates. All rights reserved.
 *
 * The sample code in this document or accessed through this document is not certified or
 * supported by Oracle. It is intended for educational or testing purposes only. Use of this
 * sample code implies acceptance of the License Agreement
 * (http://www.oracle.com/technetwork/licenses/standard-license-152015.html).
 *
 * File: SearchButtonTag.java
 * Abstract: Tag to instantiate the Search Button widget
 * Version: 1.0
 */


package com.ka.kcapp.widget.tag;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;

import org.apache.log4j.Logger;


/**
 * Tag to instantiate the Search Button widget
 * @author     Prashant
 */

public class SearchButtonTag extends BaseTag {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(SearchButtonTag.class);

	public SearchButtonTag() {
        super();
    }
    
    private String buttonLabel = labelsBundle.getString("SearchTag.inputLabel");
    private String serverSideTemplate = "searchButton";
	
	public String getButtonLabel() {
		return buttonLabel;
	}
	public void setButtonLabel(String buttonLabel) {
		this.buttonLabel = buttonLabel;
	}
	public String getServerSideTemplate() {
		return serverSideTemplate;
	}
	public void setServerSideTemplate(String serverSideTemplate) {
		this.serverSideTemplate = serverSideTemplate;
	}

	public int doStartTag() throws JspException {
		try {
            JspWriter out = pageContext.getOut();
            String widgetJsPath = getWidgetJsPath("searchButton/searchButton.js");
            String widgetId = getWidgetInstanceId("searchButton");
            String commonHtml = getCommonResourceHtml();
            StringBuilder widgetHtml = new StringBuilder();
            
            Map<String, Object> input = new HashMap<String, Object>();
            input.put("widgetId", widgetId);
            input.put("buttonLabel", this.getButtonLabel());
            
            widgetHtml = widgetHtml.append(getTemplateHtml(input, this.getServerSideTemplate()));
            
            if(!commonHtml.isEmpty())
            	widgetHtml = widgetHtml.append(commonHtml);
            
            if(!isFileAdded("searchButton/searchButton.js"))
            	widgetHtml = widgetHtml.append("<script src=\"" + widgetJsPath + "\"></script>");
            
            widgetHtml = widgetHtml.append("<script>$(\"#" + widgetId + "\").searchButton({ \"pubSub\": ps,\"widgetId\":\"" + widgetId + "\", \"label_input\":\""+ this.getButtonLabel() + "\"}); </script>");

			out.println(widgetHtml);
			
			} catch (Exception ex) {
				logger.error("Error in SearchButtonTag.doStartTag: ", ex);
		}

			return SKIP_BODY;
	}
	
}

