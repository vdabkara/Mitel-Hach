package com.ka.kcapp.widget.tag;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ka.kcapp.widget.apiUtility.OKAPIUtility;
import com.ka.kcapp.widget.common.Content;
import com.ka.kcapp.widget.common.JsonSequence;

public class RecommendContentTag extends BaseTag{
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(RecommendContentTag.class);
	
	private String prioritySelected= "";
	private String contentTypeSelected="";
	private String submitButtonLabel = labelsBundle.getString("RecommendContentTag.submitButtonLabel");
	private String cancelButtonLabel = labelsBundle.getString("RecommendContentTag.cancelButtonLabel");
	private String submittingMessageLabel = labelsBundle.getString("RecommendContentTag.submittingMessageLabel");
	private String recommendContentLabel = labelsBundle.getString("RecommendContentTag.recommendContentLabel");
	private String titleLabel = labelsBundle.getString("RecommendContentTag.titleLabel");
	private String detailsLabel = labelsBundle.getString("RecommendContentTag.detailsLabel");
	private String caseNumberLabel = labelsBundle.getString("RecommendContentTag.caseNumberLabel");
	private String priorityLabel = labelsBundle.getString("RecommendContentTag.priorityLabel");
	private String selectPriorityLabel = labelsBundle.getString("RecommendContentTag.selectPriorityLabel");
	private String contentTypeLabel= labelsBundle.getString("RecommendContentTag.contentTypeLabel");
	private String selectContentTypeLabel = labelsBundle.getString("RecommendContentTag.selectContentType");
	private String serverSideTemplate = "recommendContent";
	
	
	public RecommendContentTag()
	{
		super();
	}
	
	
	
	public String getContentTypeLabel() {
		return contentTypeLabel;
	}



	public void setContentTypeLabel(String contentTypeLabel) {
		this.contentTypeLabel = contentTypeLabel;
	}


	public String getSelectContentTypeLabel() {
		return selectContentTypeLabel;
	}



	public void setSelectContentTypeLabel(String selectContentTypeLabel) {
		this.selectContentTypeLabel = selectContentTypeLabel;
	}



	public String getCaseNumberLabel() {
		return caseNumberLabel;
	}



	public String getPrioritySelected() {
		return prioritySelected;
	}



	public void setPrioritySelected(String prioritySelected) {
		this.prioritySelected = prioritySelected;
	}



	public void setCaseNumberLabel(String caseNumberLabel) {
		this.caseNumberLabel = caseNumberLabel;
	}



	public String getPriorityLabel() {
		return priorityLabel;
	}



	public void setPriorityLabel(String priorityLabel) {
		this.priorityLabel = priorityLabel;
	}



	public String getSelectPriorityLabel() {
		return selectPriorityLabel;
	}



	public void setSelectPriorityLabel(String selectPriorityLabel) {
		this.selectPriorityLabel = selectPriorityLabel;
	}



	public String getTitleLabel() {
		return titleLabel;
	}

	public void setTitleLabel(String titleLabel) {
		this.titleLabel = titleLabel;
	}

	public String getDetailsLabel() {
		return detailsLabel;
	}

	public void setDetailsLabel(String detailsLabel) {
		this.detailsLabel = detailsLabel;
	}

	public String getSubmitButtonLabel() {
		return submitButtonLabel;
	}
	public void setSubmitButtonLabel(String submitButtonLabel) {
		this.submitButtonLabel = submitButtonLabel;
	}
	public String getCancelButtonLabel() {
		return cancelButtonLabel;
	}
	public void setCancelButtonLabel(String cancelButtonLabel) {
		this.cancelButtonLabel = cancelButtonLabel;
	}
	public String getSubmittingMessageLabel() {
		return submittingMessageLabel;
	}
	public void setSubmittingMessageLabel(String submittingMessageLabel) {
		this.submittingMessageLabel = submittingMessageLabel;
	}
	public String getRecommendContentLabel() {
		return recommendContentLabel;
	}
	public void setRecommendContentLabel(String recommendContentLabel) {
		this.recommendContentLabel = recommendContentLabel;
	}
	public String getServerSideTemplate() {
		return serverSideTemplate;
	}
	public void setServerSideTemplate(String serverSideTemplate) {
		this.serverSideTemplate = serverSideTemplate;
	}

	public String getContentTypeSelected() {
		return contentTypeSelected;
	}

	public void setContentTypeSelected(String contentTypeSelected) {
		this.contentTypeSelected = contentTypeSelected;
	}

	public int doStartTag() throws JspException {
		logger.info("RecommendContent - post processing called ");
		JspWriter out = pageContext.getOut();
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();
//		String widgetJsPath = getWidgetJsPath("recommendContent/recommendContent.js");
		String collapseWidgetJsPath = getWidgetJsPath("collapse/collapse.js");

		String widgetId = getWidgetInstanceId("recommendContent");
		String messageID = getWidgetInstanceId("messageDiv");

		StringBuilder widgetHtml = new StringBuilder(getCommonResourceHtml());
		if (!isFileAdded("recommendContent/recommendContent.js")) {
			widgetHtml.append("<script src=\"").append(getWidgetJsPath("recommendContent/recommendContent.js"))
					.append("\"></script>");
		}
		if (!isFileAdded("message/message.js"))
			widgetHtml = widgetHtml.append("<script src=\"" + getWidgetJsPath("messages/message.js") + "\"></script>");
		if (!isFileAdded("collapse/collapse.js"))
			widgetHtml = widgetHtml.append("<script src=\"" + collapseWidgetJsPath + "\"></script>");
		try {
			if (request.getSession().getAttribute(OKAPIUtility.USER_TOKEN) != null) {
				
				String caseNumber="";
				if(null!=request.getSession().getAttribute("caseID"))
				{
					caseNumber = (String)request.getSession().getAttribute("caseID");
				}
				
				// create priorotyTypesData
				JSONArray priorityTypes=  new JSONArray();
				JSONObject obj = new JSONObject();
				obj.put("label", "Low");
				obj.put("value", "LOW");
				priorityTypes.put(obj);
				obj = new JSONObject();
				obj.put("label", "Medium");
				obj.put("value", "MEDIUM");
				priorityTypes.put(obj);
				obj = new JSONObject();
				obj.put("label", "High");
				obj.put("value", "HIGH");
				priorityTypes.put(obj);
				obj = null;
				
				JsonSequence js = new JsonSequence(priorityTypes);
				
				JSONArray contentTypes = Content.getContentChannelList(request, response);
				/*
				 * set ContentTypes in SESSION for Identifying the SELECTED ContentType node 
				 * while creating Recommendation
				 */
				if(null!=request.getSession().getAttribute("RC_CONTENT_TYPE_NODES"))
				{
					request.getSession().removeAttribute("RC_CONTENT_TYPE_NODES");
				}
				request.getSession().setAttribute("RC_CONTENT_TYPE_NODES", contentTypes);
				Map<String, Object> input = new HashMap<String, Object>();
				input.put("widgetId", widgetId);
				input.put("localeid", this.getCurrentLocale().getLocale().toString());
				input.put("contentTypes",
						contentTypes == null ? null : new JsonSequence(contentTypes));
				input.put("contentTypeSelected", this.getContentTypeSelected());
				input.put("priorityTypes", js);
				input.put("prioritySelected", prioritySelected);
				input.put("submitButtonLabel", this.getSubmitButtonLabel());
				input.put("cancelButtonLabel", this.getCancelButtonLabel());
				input.put("submittingMessageLabel", this.getSubmittingMessageLabel());
				input.put("recommendContentLabel", this.getRecommendContentLabel());
				input.put("titleLabel", this.getTitleLabel());
				input.put("detailsLabel", this.getDetailsLabel());
				input.put("caseNumberLabel", this.getCaseNumberLabel());
				input.put("caseNumber",caseNumber);
				input.put("priorityLabel", this.getPriorityLabel());
				input.put("selectPriorityLabel", this.getSelectPriorityLabel());
				input.put("contentTypeLabel", this.getContentTypeLabel());
				input.put("selectContentTypeLabel", this.getSelectContentTypeLabel());
				
				input.put("messageID", messageID);
                input.put("SUBMITTED_BY", request.getSession().getAttribute("username"));
                
                widgetHtml.append(getTemplateHtml(input, this.getServerSideTemplate()));
                
				out.println(widgetHtml);
			}
		} catch (Exception ex) {
			logger.error("Error in RecommendConentTag.doStartTag: ", ex.fillInStackTrace());
		}
		return SKIP_BODY;
	}	
}