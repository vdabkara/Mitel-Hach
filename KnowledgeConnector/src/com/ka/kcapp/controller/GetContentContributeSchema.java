package com.ka.kcapp.controller;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ka.kcapp.widget.apiUtility.OKAPIUtility;

import java.io.BufferedReader;

public class GetContentContributeSchema extends HttpServlet{
    /**
    * Get ContentContribute Schema Servlet
    */
    final static Logger logger = Logger.getLogger(GetContentContributeSchema.class);
    
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws java.io.IOException {
        // call API to fetch the contentType schema
        
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line = null;
        JSONObject postData = new JSONObject();
        try{
            while ((line = reader.readLine()) != null) buffer.append(line);
            postData = new JSONObject(buffer.toString());
            if (postData.has("recordid")){
                String record=postData.getString("recordid");
                ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.application");
                String url = resourceBundle.getString("OKCS_IM_API_URL").concat("contentTypes/"+record);
                String contentTypeFullData = OKAPIUtility.callRESTAPI(req, res, url, "GET", null);
                JSONObject contentTypeFullDataJSON;
                JSONArray schemaAttributes = null;
                try {
                    contentTypeFullDataJSON = new JSONObject(contentTypeFullData);
                    schemaAttributes = contentTypeFullDataJSON.getJSONObject("contentSchema").getJSONArray("schemaAttributes");     
                } catch (JSONException e) {
                    logger.debug(e.getLocalizedMessage());
                }
                res.setContentType("application/json");
                res.getWriter().write(schemaAttributes.toString());            
            }else{
                
            }
        }catch(Exception ex){
            logger.error("",ex);
        }
        
    }
    
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws java.io.IOException {
        doPost(req, res);
    }
}