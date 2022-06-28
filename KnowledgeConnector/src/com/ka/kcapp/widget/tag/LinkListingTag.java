package com.ka.kcapp.widget.tag;

import com.ka.kcapp.widget.apiUtility.OKAPIUtility;

import com.ka.kcapp.widget.common.JsonSequence;

import com.ka.kcapp.widget.common.LinkedItems;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.log4j.Logger;

import org.json.JSONArray;

public class LinkListingTag extends BaseTag {
    
    private static final long serialVersionUID = 1L;
    final static Logger logger = Logger.getLogger(LinkListingTag.class);
    
    private String incidentId = "";
    private String serverSideTemplate = "linkList";
    private String label_unlink_confirm = labelsBundle.getString("LINKING.unlink_cofirm");
    
    public LinkListingTag() {
        super();
    }

    public String getServerSideTemplate() {
        return serverSideTemplate;
    }
    
    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
        HttpServletResponse response = (HttpServletResponse)pageContext.getResponse();
        String widgetJsPath = getWidgetJsPath("linking/linkList.js");
        String collapseWidgetJsPath = getWidgetJsPath("collapse/collapse.js");
        String widgetId = getWidgetInstanceId("linkList");
        String collapseWidgetId = getWidgetInstanceId("linkList_collapse");
        String collapseScreenId = getWidgetInstanceId("linkList_collapse_screen");
        StringBuilder widgetHtml = new StringBuilder();
        try {
            if (
                request.getSession().getAttribute(OKAPIUtility.USER_TOKEN) != null &&
                request.getParameter("caseID") != null && request.getParameter("caseID") != ""
            ) {
                this.setIncidentId(request.getParameter("caseID"));
                if(!isFileAdded("linking/linkList.js"))
                    widgetHtml = widgetHtml.append("<script src=\"" + widgetJsPath + "\"></script>");
                if(!isFileAdded("collapse/collapse.js"))
                    widgetHtml = widgetHtml.append("<script src=\"" + collapseWidgetJsPath + "\"></script>");
//                String linkedAnswerURL = getResourceBundle().getString("OKCS_IM_API_URL") + "incidents?q=incidentId+eq+'"+URLEncoder.encode(this.getIncidentId(), "UTF-8")+"'";
//                JSONObject linkedArticlesResponse = new JSONObject(OKAPIUtility.callRESTAPI(request, response, linkedAnswerURL, "GET", null));
//                JSONArray linkedItems = linkedArticlesResponse.getJSONArray("items");
//                String recordid = linkedItems.getJSONObject(0).getString("recordId");
//                linkedAnswerURL = getResourceBundle().getString("OKCS_IM_API_URL") + "incidents/"+URLEncoder.encode(recordid, "UTF-8")+"/incidentLinks";
//                linkedArticlesResponse = new JSONObject(OKAPIUtility.callRESTAPI(request, response, linkedAnswerURL, "GET", null));
                
                JSONArray resultList =new JSONArray(LinkedItems.getLinkedAnswersByIncidentID(this.getIncidentId(), request, response));
                logger.debug("LinkListingTag result list = "+resultList.toString());
                String messageDIVID = getWidgetInstanceId("messageDiv");
                Map<String, Object> input = new HashMap<String, Object>();
                input.put("messageID", messageDIVID);
                input.put("incidentID", this.getIncidentId());
                input.put("widgetId", widgetId);
                input.put("collapseWidgetId",collapseWidgetId);
                input.put("collapseScreenId",collapseScreenId);
                input.put("localeid", this.getCurrentLocale().getLocale().toString());
                input.put("linkedItems", resultList == null ? null : new JsonSequence(resultList));
                widgetHtml = widgetHtml.append(getTemplateHtml(input, this.getServerSideTemplate()));
                
                widgetHtml
                    .append("<script>$(\"#")
                    .append(widgetId)
                    .append("\").linkList({ ")
                    .append("\"incidentID\": \"")
                    .append(this.getIncidentId())
                    .append("\",")
                    .append("\"messageID\": \"")
                    .append(messageDIVID)
                    .append("\",")
                    .append("\"label_confirm_remove\":\"")
                    .append(this.label_unlink_confirm)
                    .append("\",")
                    .append("\"hide_when_no_results\": true,")
                    .append("\"target\": \"_self\"")
                    .append("}); </script>");

                widgetHtml
                    .append("<script>$(\"#")
                    .append(collapseWidgetId)
                    .append("\").collapse({ ")
                    .append("\"div_id\": \"")
                    .append(collapseWidgetId)
                    .append("\"")
                    .append("}); </script>");
                

                out.println(widgetHtml);                
            }
        } catch (Exception ex) {
            //logger.error("Error in LinkListingTag.doStartTag: ", ex);
            ex.printStackTrace();
        }
        return SKIP_BODY;
    }
    
}
