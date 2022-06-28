package com.ka.kcapp.widget.tag;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;

import javax.servlet.jsp.JspWriter;

import org.apache.log4j.Logger;

public class NavigationTag  extends BaseTag {
    
    private static final long serialVersionUID = 1L;
    final static Logger logger = Logger.getLogger(NavigationTag.class);
    
    private boolean show = true;
    private boolean refeshforcurrent = true;
    private String serverSideTemplate = "navigation";

    private String label_back_btn = labelsBundle.getString("NAV.back_btn");
    
    
    public NavigationTag() {
        super();
    }
    public boolean getShow() {
        return show;
    }
    public void setShow(boolean show) {
        this.show = show;
    }
    public void setRefreshForCurrent(boolean refeshforcurrent){
        this.refeshforcurrent = refeshforcurrent;
    }
    public boolean getRefreshForCurrent(){
        return this.refeshforcurrent;
    }
    
    public String getServerSideTemplate() {
        return serverSideTemplate;
    }
    
    
    public int doStartTag() throws JspException {
        logger.debug("NavigationTag show? ");
        JspWriter out = pageContext.getOut();
        HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
        HttpServletResponse response = (HttpServletResponse)pageContext.getResponse();
        HttpSession session = request.getSession();
        String widgetId = getWidgetInstanceId("navigation");
        StringBuilder widgetHtml = new StringBuilder();
        if (this.getShow()){
            widgetHtml.append(getCommonResourceHtml());
            widgetHtml
                .append("<script src=\"")
                .append(getWidgetJsPath("navigation/navigation.js"))
                .append("\"></script>");
        }
        try{
            String prev_page = session.getAttribute("prev_page")==null ? null: session.getAttribute("prev_page").toString();
            if (logger.isDebugEnabled()){
                logger.debug("NavigationTag setting prev_page from session? "+session.getAttribute("prev_page"));
            }
            String current_url = request.getRequestURL().toString();
            StringBuilder url = new StringBuilder(request.getRequestURL().toString());
            String qryStr = request.getQueryString();
            url
                .append(qryStr==null || qryStr.length()==0 ? "":"?")
                .append(qryStr==null || qryStr.length()==0 ? "":qryStr)
            ;
            if (this.getRefreshForCurrent() || url.indexOf(current_url)==-1){
                if (logger.isDebugEnabled()){
                    logger.debug("NavigationTag setting prev_page? "+url.toString());
                }
                request.getSession().setAttribute("prev_page", url.toString());
            }
            if (logger.isDebugEnabled()){
                logger.debug("NavigationTag getRefreshForCurrent? "+this.getRefreshForCurrent());
                logger.debug("NavigationTag url_orig? "+request.getRequestURL().toString());
                logger.debug("NavigationTag url? "+url);
                logger.debug("NavigationTag current_url? "+current_url);
                logger.debug("NavigationTag indexof? "+url.indexOf(current_url));
                logger.debug("NavigationTag show? "+this.getShow());
                logger.debug("NavigationTag prev_page? "+prev_page);
                logger.debug("NavigationTag nav_back_label? "+this.label_back_btn);
                logger.debug("NavigationTag widgetId? "+widgetId); 
            }
            if (this.getShow()){
                Map<String, Object> input = new HashMap<String, Object>();
                input.put("prev_page", prev_page);
                input.put("nav_back_label", this.label_back_btn);
                input.put("widgetId", widgetId);

                widgetHtml = widgetHtml.append(getTemplateHtml(input, this.getServerSideTemplate()));
                out.println(widgetHtml);
            }
        }catch(Exception ex){
            logger.error("NavigationTag.doStartTag()",ex);
        }
        return SKIP_BODY;
    }
    
}
