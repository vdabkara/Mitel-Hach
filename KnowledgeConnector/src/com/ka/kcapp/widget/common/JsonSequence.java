/**
 * Copyright (c) 2015, Oracle Corporation and/or its affiliates. All rights reserved.
 *
 * The sample code in this document or accessed through this document is not certified or
 * supported by Oracle. It is intended for educational or testing purposes only. Use of this
 * sample code implies acceptance of the License Agreement
 * (http://www.oracle.com/technetwork/licenses/standard-license-152015.html).
 *
 * File: JsonSequence.java
 * Abstract: JSONArray Sequence data model
 * Version: 1.0
 */

package com.ka.kcapp.widget.common;


import freemarker.template.SimpleScalar;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateSequenceModel;

import java.io.Serializable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * JSONArray Sequence data model
 * @author Prashant Chaturvedi
 */
public class JsonSequence implements TemplateSequenceModel, Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 9075694172766088987L;
    
    private JSONArray jsonArray;
    
    /**
     * Constructor for JsonSequesnce
     * @param jsonArray Json Array to be processed
     */
    public JsonSequence(JSONArray jsonArray){
        this.jsonArray = jsonArray;
    }

    /**
     * Method to return a Template Model for the given index
     * @param index Index of the key in the JsonSequence
     * @return TemplateModel for the JSON at the index entered
     */
    public TemplateModel get(int index) throws TemplateModelException {
        TemplateModel tm;
        try {
            Object result = this.jsonArray.get(index);
            if(result instanceof JSONArray){
                tm = new JsonSequence((JSONArray)result);
            }else if(result instanceof JSONObject){
                tm = new JsonHash((JSONObject)result);
            }else{
                tm = new SimpleScalar(this.jsonArray.getString(index));
            }
        } catch (JSONException e) {
            throw new TemplateModelException(e);
        }
        return tm;
    }

    /**
     * Method to return the size of the JsonSequence
     * @return int The size of the JsonSequence
     */
    public int size() throws TemplateModelException {
        return this.jsonArray.length();
    }

}