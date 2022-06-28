package com.ka.kcapp.widget.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import org.json.JSONObject;

public class Navigation {

    final static Logger logger = Logger.getLogger(Navigation.class);

    public static String setNavigationLocation(HttpServletRequest request, HttpServletResponse response, JSONObject postData) {
        JSONObject action_response = new JSONObject();
        try{
            if (logger.isDebugEnabled()) logger.debug("Navigation.setNavigationLocation");
            if (postData.has("page_link")){
                if (logger.isDebugEnabled()) logger.debug("Navigation.setNavigationLocation "+postData.getString("page_link"));
                request.getSession().setAttribute("prev_page", postData.getString("page_link"));
            }
        }catch(Exception ex){
            try{ action_response.put("ERROR", ex.toString()); } catch(Exception ez){};
            logger.error("", ex);
        }
        return action_response.toString();
    }

}
