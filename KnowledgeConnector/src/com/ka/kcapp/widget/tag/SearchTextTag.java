/**
 * Copyright (c) 2015, Oracle Corporation and/or its affiliates. All rights reserved.
 *
 * The sample code in this document or accessed through this document is not certified or
 * supported by Oracle. It is intended for educational or testing purposes only. Use of this
 * sample code implies acceptance of the License Agreement
 * (http://www.oracle.com/technetwork/licenses/standard-license-152015.html).
 *
 * File: SearchTextTag.java
 * Abstract: Tag to instantiate the Search Text Input widget
 * Version: 1.0
 */

package com.ka.kcapp.widget.tag;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.log4j.Logger;


/**
 * Tag to instantiate the Search Text Input widget
 * @author     Prashant
 */

public class SearchTextTag extends BaseTag {

	private static final long serialVersionUID = -3781853628508788866L;
	final static Logger logger = Logger.getLogger(SearchTextTag.class);
	
    public SearchTextTag() {
        super();
    }
    
    private String searchText="";
    private String inputLabel = labelsBundle.getString("SearchTag.inputLabel");
    private String placeHolderLabel = labelsBundle.getString("SearchTag.placeHolderLabel");
    private String startOverLabel = labelsBundle.getString("SearchTag.startOverLabel");
    private String serverSideTemplate = "searchText";
    
	private String contributeContentLabel = labelsBundle.getString("SearchTag.contributeContentLabel");
	 private String recommendNewArticleLabel = labelsBundle.getString("SearchTag.recommendNewArticleLabel");
    private boolean initialFocus = true;
    
    
	public String getRecommendNewArticleLabel() {
		return recommendNewArticleLabel;
	}
	public void setRecommendNewArticleLabel(String recommendNewArticleLabel) {
		this.recommendNewArticleLabel = recommendNewArticleLabel;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public String getInputLabel() {
		return inputLabel;
	}
	public void setInputLabel(String inputLabel) {
		this.inputLabel = inputLabel;
	}
	public String getPlaceHolderLabel() {
		return placeHolderLabel;
	}
	public void setPlaceHolderLabel(String placeHolderLabel) {
		this.placeHolderLabel = placeHolderLabel;
	}
	public String getStartOverLabel() {
		return startOverLabel;
	}
	public void setStartOverLabel(String startOverLabel) {
		this.startOverLabel = startOverLabel;
	}
	public boolean isInitialFocus() {
		return initialFocus;
	}
	public void setInitialFocus(boolean initialFocus) {
		this.initialFocus = initialFocus;
	}
	public String getServerSideTemplate() {
		return serverSideTemplate;
	}
	public void setServerSideTemplate(String serverSideTemplate) {
		this.serverSideTemplate = serverSideTemplate;
	}
	public String getContributeContentLabel() {
		return contributeContentLabel;
	}

	public void setContributeContentLabel(String contributeContentLabel) {
		this.contributeContentLabel = contributeContentLabel;
	}
	public int doStartTag() throws JspException {
		logger.debug("Search Text...doStartTag() called......");
		try{
            //Get the writer object for output.
            JspWriter out = pageContext.getOut();
            HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
            String widgetJsPath = getWidgetJsPath("searchText/searchText.js");
            String widgetId = getWidgetInstanceId("searchText");
            String searchText = this.getSearchText().isEmpty() ? request.getParameter("kw") : this.getSearchText();
            String widgetHtml = getCommonResourceHtml();
            if(!isFileAdded("searchText/searchText.js")) {
            	widgetHtml += "<script src=\"" + widgetJsPath + "\"></script>";
            }
            
            Map<String, Object> input = new HashMap<String, Object>();
            input.put("instanceID", widgetId);
            input.put("inputLabel", this.getInputLabel());
            input.put("placeHolderLabel", this.getPlaceHolderLabel());
            input.put("searchText", searchText.replaceAll("\"", "&quot;"));
            input.put("initialFocus", this.isInitialFocus());
            input.put("startOverLabel", this.getStartOverLabel());
            input.put("contributeContentLabel", this.getContributeContentLabel());
            input.put("recommendNewArticleLabel", this.getRecommendNewArticleLabel());
            
            /*
             * REMOVE THIS CONDITON FOR A WHILE
             * FOR REDING PARAMETERS FROM FOR CASE ID
             */
//            if (request.getParameter("caseID") != null)
//				input.put("showRecommend", true);
            
//            if (request.getParameter("incidentID") != null)
//            if (request.getParameter("caseID") != null)    
//            	input.put("showContribute", true);

            input.put("showRecommend", true);
            input.put("showContribute", true);
            
			widgetHtml += getTemplateHtml(input, this.getServerSideTemplate());
			widgetHtml += "<script>$(\"#" + widgetId + "\").searchText({ \"pubSub\": ps,\"widgetId\":\"" + widgetId
					+ "\",\"search_text\":\"" + searchText.replaceAll("\"", "\\\\\"") + "\", \"label_input\":\"" + this.getInputLabel()
					+ "\",\"label_placeholder\":\"" + this.getPlaceHolderLabel() + "\",\"initial_focus\":"
					+ this.isInitialFocus() + "}); </script>";
			out.println(widgetHtml);
			
			} catch (Exception ex) {
				logger.error("Error in SearchTextTag.doStartTag: ", ex);
		}
		logger.debug("Search Text...doStartTag() end......");
			return SKIP_BODY;
	}
	
}

