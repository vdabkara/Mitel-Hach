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

public class ContributeContentTag extends BaseTag {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(ContributeContentTag.class);

	private String contentTypeSelected = "";
	private String contributeContentLabel = labelsBundle.getString("ContributeContentTag.contributeContentLabel");
	private String submitButtonLabel = labelsBundle.getString("ContributeContentTag.submitButtonLabel");
	private String cancelButtonLabel = labelsBundle.getString("ContributeContentTag.cancelButtonLabel");
	private String submittingMessageLabel = labelsBundle.getString("ContributeContentTag.submittingMessageLabel");
	private String contentTypeLabel = labelsBundle.getString("ContributeContentTag.contentTypeLabel");
	private String serverSideTemplate = "contributeContent";

	public ContributeContentTag() {
		super();
	}

	public String getServerSideTemplate() {
		return serverSideTemplate;
	}

	public String getContentTypeSelected() {
		return contentTypeSelected;
	}

	public void setContentTypeSelected(String contentTypeSelected) {
		this.contentTypeSelected = contentTypeSelected;
	}

	public String getContributeContentLabel() {
		return contributeContentLabel;
	}

	public void setContributeContentLabel(String contributeContentLabel) {
		this.contributeContentLabel = contributeContentLabel;
	}

	public String getContentTypeLabel() {
		return contentTypeLabel;
	}

	public void setContentTypeLabel(String contentTypeLabel) {
		this.contentTypeLabel = contentTypeLabel;
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

	public int doStartTag() throws JspException {
		logger.info("Contribute content - post processing called ");
		JspWriter out = pageContext.getOut();
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();
		String widgetJsPath = getWidgetJsPath("contribute/contributeContent.js");
		String collapseWidgetJsPath = getWidgetJsPath("collapse/collapse.js");

		String widgetId = getWidgetInstanceId("contributeContent");
		String messageID = getWidgetInstanceId("messageDiv");

		StringBuilder widgetHtml = new StringBuilder(getCommonResourceHtml());
		if (!isFileAdded("contribute/contributeContent.js")) {
			widgetHtml.append("<script src=\"").append(getWidgetJsPath("contribute/cc_Delete.js"))
					.append("\"></script>");
		}
		if (!isFileAdded("contribute/cc_Delete.js")) {
			widgetHtml.append("<script src=\"").append(widgetJsPath).append("\"></script>");
		}
		if (!isFileAdded("message/message.js"))
			widgetHtml = widgetHtml.append("<script src=\"" + getWidgetJsPath("messages/message.js") + "\"></script>");
		if (!isFileAdded("collapse/collapse.js"))
			widgetHtml = widgetHtml.append("<script src=\"" + collapseWidgetJsPath + "\"></script>");
		try {
			if (request.getSession().getAttribute(OKAPIUtility.USER_TOKEN) != null) {
				logger.debug("User session is valid, getting the channel schema");
				String channelid = request.getParameter("channelid");
				JSONArray contentTypes = Content.getContentChannelList(request, response);
				Map<String, Object> input = new HashMap<String, Object>();
				input.put("widgetId", widgetId);
				input.put("localeid", this.getCurrentLocale().getLocale().toString());
				input.put("contentTypes", contentTypes == null ? null : new JsonSequence(contentTypes));
				input.put("submitButtonLabel", this.getSubmitButtonLabel());
				input.put("cancelButtonLabel", this.getCancelButtonLabel());
				input.put("submittingMessageLabel", this.getSubmittingMessageLabel());
				input.put("contributeContentLabel", this.getContributeContentLabel());
				input.put("contentTypeLabel", this.getContentTypeLabel());
				input.put("selected_channelid", channelid != null ? channelid : "");
				input.put("messageID", messageID);
                                input.put("SUBMITTED_BY", request.getSession().getAttribute("username"));
				widgetHtml.append(getTemplateHtml(input, "cc_PAGE_beg.ftl"));
				if (channelid != null) {
					JSONObject channel = Content.getContentSchema(channelid, request, response);
					JSONArray schemaAttributes = channel.getJSONObject("contentSchema")
							.getJSONArray("schemaAttributes");
					Map<String, Object> channel_map = new HashMap<String, Object>();
					channel_map.put("channelid", channelid);
					channel_map.put("referenceKey", channel.getString("referenceKey"));
					channel_map.put("name", channel.getString("name"));
                                        channel_map.put("SUBMITTED_BY", request.getSession().getAttribute("SUBMITTED_BY"));
					widgetHtml.append(getTemplateHtml(channel_map, "cc_CHANNEL.ftl"));
					for (int x = 0; x < schemaAttributes.length(); x++) {
						JSONObject object = schemaAttributes.optJSONObject(x);
						widgetHtml.append(Content.writeObject(object, null, this.pageContext.getServletContext(), request));

					}
				}
				widgetHtml.append(getTemplateHtml(input, "cc_PAGE_end.ftl"));
				out.println(widgetHtml);
			}
			logger.info("Contribute content -  processing complete ");
		} catch (Exception ex) {
			logger.error("Error in ContributeConentTag.doStartTag: ", ex.fillInStackTrace());
		}
		return SKIP_BODY;
	}

}
