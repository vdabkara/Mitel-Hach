/**
 * Copyright (c) 2015, Oracle Corporation and/or its affiliates. All rights reserved.
 *
 * The sample code in this document or accessed through this document is not certified or
 * supported by Oracle. It is intended for educational or testing purposes only. Use of this
 * sample code implies acceptance of the License Agreement
 * (http://www.oracle.com/technetwork/licenses/standard-license-152015.html).
 *
 * File: JsonHash.java
 * Abstract: Class to retrieve Hash Data from JSON Object
 * Version: 1.0
 */

package com.ka.kcapp.widget.common;


import freemarker.template.SimpleScalar;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class to retrieve Hash Data from JSON Object
 * @author Prashant Aug 17, 2015
 */
public class JsonHash implements TemplateHashModel, Serializable {

    private static final long serialVersionUID = 3797802450071110440L;
    final static Logger logger = Logger.getLogger(JsonHash.class);
    
    private JSONObject jsonObject;
    
    /**
     * Constructor Method for JsonHash
     * @param jsonObject Input JSON Object to construct a hash from 
     */
    public JsonHash(JSONObject jsonObject){
        this.jsonObject = jsonObject;
    }

    /**
     * Method to return a template model for the key supplied
     * @param key JSON Key to be searched in the hash
     * @return TemplateModel
     */
    public TemplateModel get(String key) throws TemplateModelException {
        TemplateModel tm;
        try {
            if(this.jsonObject.isNull(key)){
                return null;
            }else{
                Object result = this.jsonObject.get(key);
                if(result instanceof JSONArray){
                    tm = new JsonSequence((JSONArray)result);
                }else if(result instanceof JSONObject){
                    tm = new JsonHash((JSONObject)result);
                }else{
                    tm = new SimpleScalar(this.jsonObject.getString(key));
                }
            }
        } catch (JSONException e) {
            logger.error("A JSON Exception occurred while constructing the Template Model", e);
            return null;
        }
        return tm;
    }

    /**
     * Method to check if JSONObject is empty
     * @return boolean value which indicates whether the JSONObject is empty
     */
    public boolean isEmpty() throws TemplateModelException {
        return this.jsonObject == null;
    }
}