/**
 * Copyright (c) 2015, Oracle Corporation and/or its affiliates. All rights reserved.
 *
 * The sample code in this document or accessed through this document is not certified or
 * supported by Oracle. It is intended for educational or testing purposes only. Use of this
 * sample code implies acceptance of the License Agreement
 * (http://www.oracle.com/technetwork/licenses/standard-license-152015.html).
 *
 * File: IMArticleUtils.java
 * Abstract: Class containing Utility methods to process IM Articles
 * Version: 1.0
 */

package com.ka.kcapp.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ka.kcapp.widget.apiUtility.OKAPIUtility;

/**
 * Class containing Utility methods required to render IM articles
 * @author akshenoy
 */
public class IMArticleUtils {
	private int nodeCount = 0;
	private String answerId;
	private ResourceBundle labelsBundle = ResourceBundle.getBundle("resources.LabelsBundle", new Locale("en"));
	final static Logger logger = Logger.getLogger(IMArticleUtils.class);
	
	public String getAnswerId() {
		return this.answerId;
	}
	
	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}
	
	/**
	 * Method to return the IM Article HTML
	 * @param request Request object used to service the request for the article HTML.
	 * @param response Response object to retrieve API response.
	 * @param answerId Answer ID of the article whose HTML is to be fetched.
	 * @return String Answer HTML for the answer ID provided.
	 */
	public String getIMArticleHtml(HttpServletRequest request, HttpServletResponse response, String answerId) {
		Map<String, Object> input = new HashMap<String, Object>();
        Map<String, Object> error = new HashMap<String, Object>();
        try{
            getAnswerSchemaData(request, response, answerId, input);
        }catch(Exception ex) {
            logger.error("You no longer have permission to view the article or the article has been removed", ex);
            error.put("error", labelsBundle.getString("AnswerView.AnswerUnavailableTitle"));
            error.put("message", labelsBundle.getString("AnswerView.AnswerUnavailableMessage"));
        }
        input.put("hideHeaderHtml", "true");
        StringBuffer answerHtml=new StringBuffer("");
        if(error.containsKey("message")){
            answerHtml.append(getTemplateHtml(error, "answerError"));
        } else {
        	answerHtml.append(getTemplateHtml(input, "addText"));
        }
        return answerHtml.toString();
	}
	
	/**
     * Method to get the Answer Schema in the page context for IM Articles
     * @param request Request Object to process the Rest Api Call
     * @param response Response Object to process the Rest Api Response
     * @param answerJSON JSON containing the Article details
     * @param input Hash Map containing name-Object mappings of Article Schema Attributes. At the end of this method, this Hash Map will have all the details necessary to render the Article on the AnswerView Page
     * @throws JSONException
     * @throws ParseException
     */
	public void getAnswerSchemaData(HttpServletRequest request, HttpServletResponse response, String answerId, Map<String, Object> input) throws JSONException, ParseException{
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.application");
        String imURL=resourceBundle.getString("OKCS_IM_API_URL").concat("content/answers/")+ answerId + "?q=contentState+eq+%22LATESTVALID%22";
        JSONObject answerJSON =  new JSONObject(OKAPIUtility.callRESTAPI(request, response, imURL, "GET", ""));
		
        this.setAnswerId(answerId);
		String status = answerJSON.getBoolean("published") == true? "Published" : "UnPublished";
        String publishedDate = processIMDate(answerJSON.getString("publishDate"));
        
        input.put("documentIdLabel", labelsBundle.getString("AnswerView.documentIdLabel"));
        input.put("statusLabel", labelsBundle.getString("AnswerView.statusLabel"));
        input.put("versionLabel", labelsBundle.getString("AnswerView.versionLabel"));
        input.put("publishedDateLabel", labelsBundle.getString("AnswerView.publishedDateLabel"));
        input.put("title", answerJSON.getString("title"));
        input.put("documentId", answerJSON.getString("documentId"));
        input.put("status", status);
        input.put("version", answerJSON.getString("version"));
        input.put("publishedDate", publishedDate);

        JSONObject contentType = new JSONObject(answerJSON.getString("contentType"));
        LinkedHashMap<String,JSONObject> schemaAttributesMap = getSchemaDetails(request, response, contentType.getString("referenceKey"));
        input.put("SchemaAttributeList", getSchemaHtml(schemaAttributesMap, answerJSON.getString("xml"), answerJSON.getString("resourcePath")));
    }
	
	/**
     * Method to retrieve the Schema details for a Channel
     * @param request Request Object to process the Rest Api Call
     * @param response Response Object to construct the Rest Api Call
     * @param refKey Reference Key for the Channel whose Schema is to be retrieved
     * @return LinkedHashMap<String,JSONObject> Hash Map of Json Objects to Schema attributes
     */
    public LinkedHashMap<String,JSONObject> getSchemaDetails(HttpServletRequest request, HttpServletResponse response,String refKey) {
        String schemaDetail = (String)request.getAttribute("contentTypeData");
        
        if(schemaDetail == null){
            ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.application");
            String url = resourceBundle.getString("OKCS_IM_API_URL").concat("contentTypes?q=referenceKey+in+('"+refKey+"')&mode=FULL");
            schemaDetail= OKAPIUtility.callRESTAPI(request, response, url, "GET",null);
            request.setAttribute("contentTypeData", schemaDetail);
        }
        if (logger.isDebugEnabled()) logger.debug("schema detail: \n"+schemaDetail+"\n");
        LinkedHashMap<String,JSONObject> schemaAttributesMap = new LinkedHashMap<String,JSONObject>();
        JSONObject schemaDetailsJson;
        try {
            schemaDetailsJson = new JSONObject(schemaDetail);
            JSONObject schemaDetailJson = new JSONArray(schemaDetailsJson.getString("items")).getJSONObject(0);
            JSONObject contentSchemaJson = new JSONObject(schemaDetailJson.getString("contentSchema"));
            JSONArray schemaAttributesJsonArray = new JSONArray(contentSchemaJson.getString("schemaAttributes"));
            for (int i = 0, size = schemaAttributesJsonArray.length(); i < size; i++)
            {
                JSONObject objectInArray = schemaAttributesJsonArray.getJSONObject(i);
                AddSchemaNodeAttributesToMap(objectInArray,schemaAttributesMap);
            }
        } catch (JSONException e) {
            logger.error("Error in AnswerViewTag.getSchemaDetails: ", e);
        }
        return schemaAttributesMap;
    }
	
	/**
     * Method to add Schema Nodes to Schema Attributes Map
     * @param schemaAttributeBusinessObject Json Object for Schema Attribute
     * @param schemaAttributesMap Schema Attribute Map
     */
	private void AddSchemaNodeAttributesToMap(JSONObject schemaAttributeBusinessObject, LinkedHashMap<String,JSONObject> schemaAttributesMap) {
        try {
            if (!schemaAttributesMap.containsKey(schemaAttributeBusinessObject.getString("xpath")))
                schemaAttributesMap.put(schemaAttributeBusinessObject.getString("xpath"), schemaAttributeBusinessObject);
            if(schemaAttributeBusinessObject.has("children")) {
                JSONArray schemaAttributesJsonArray = new JSONArray(schemaAttributeBusinessObject.getString("children"));
                for (int i = 0, size = schemaAttributesJsonArray.length(); i < size; i++) {
                    JSONObject objectInArray = schemaAttributesJsonArray.getJSONObject(i);
                    AddSchemaNodeAttributesToMap(objectInArray,schemaAttributesMap);
                }
            }
        } catch (JSONException e) {
            logger.error("Error in AnswerViewTag.AddSchemaNodeAttributesToMap: ", e);
        }
    }
	
	/**
     * Method to retrieve the Schema Attribute List from search response XML 
     * @param schemaAttributesMap Hash Map of Schema Objects to Schema attributes
     * @param xml Search response XML
     * @param resourcePath resource path for the channel 
     * @return List mapping Schema Attributes to the Search Result
     */
	@SuppressWarnings("rawtypes")
	public List<SchemaAttribute> getSchemaHtml(LinkedHashMap<String, JSONObject> schemaAttributesMap, String xml, String resourcePath) {
        List<SchemaAttribute> SchemaAttributeList = new ArrayList<SchemaAttribute>();
        try {
            if (logger.isDebugEnabled()) logger.debug("XML of document = \n"+xml+"\n");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            ByteArrayInputStream input =  new ByteArrayInputStream(xml.getBytes("UTF-8"));
            Document doc = dBuilder.parse(input);

            XPath xPath =  XPathFactory.newInstance().newXPath();
            Iterator it = schemaAttributesMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                JSONObject json = (JSONObject) pair.getValue();    
                String key = (String) pair.getKey();
                String expression = key.replaceFirst("//", "/");
                NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node nNode = nodeList.item(i);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE && getNodeDepth(json) == 0) {
                        if(CheckForNode(json))
                            EvaluateNode(nNode, json, SchemaAttributeList, 0);
                        else
                            ProcessElement(nNode, json, SchemaAttributeList, 0);
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            logger.error("ParserConfigurationException in AnswerViewTag.getSchemaHtml: ", e);
        } catch (SAXException e) {
            logger.error("SAXException in AnswerViewTag.getSchemaHtml: ", e);
        } catch (IOException e) {
            logger.error("IOException in AnswerViewTag.getSchemaHtml: ", e);
        } catch (XPathExpressionException e) {
            logger.error("XPathExpressionException in AnswerViewTag.getSchemaHtml: ", e);
        } catch (JSONException e) {
            logger.error("JSONException in AnswerViewTag.getSchemaHtml: ", e);
        }
        return SchemaAttributeList;
    }
	
	/**
     * Method to get the Depth of the Node given it's Schema Details
     * @param JSONObject JSON Object containing schema details of the Node
     * @return int Depth of the Node
     * @throws JSONException
     */
	private int getNodeDepth(JSONObject json) throws JSONException {
        String[] XPATH = json.getString("xpath").replaceFirst("//", "").split("/");
        return XPATH.length - 2;
    }
	
	/**
     * Method to update value of input Node
     * @param nNode Reference Node
     * @param schemaAttributesJson Schema JSON data
     * @param schemaAttributeList List of Schema Attributes
     * @param level Depth of the Node
     * @throws JSONException
     * @throws UnsupportedEncodingException
     */
	private void ProcessElement(Node nNode, JSONObject schemaAttributesJson, List<SchemaAttribute> schemaAttributeList, int level) throws JSONException, UnsupportedEncodingException {
        SchemaAttribute schemaAttribute= null;
        if (nNode != null) {
            String attributeName = schemaAttributesJson.getString("name");
            String attributeValue = nNode.getTextContent();
            String type = null;
            if(schemaAttributesJson.has("schemaAttrType"))
                type = schemaAttributesJson.getString("schemaAttrType");
            String title = "";
            String value = "";
            if(attributeName != null && type == null) {
                schemaAttribute = new SchemaAttribute(attributeName, "", 0);
            }           
            if (type != null) {
                if ("FILE".equals(type)) {
                	String fileType = attributeValue.substring(attributeValue.lastIndexOf('.') + 1).toUpperCase();
                    String link = "extUrl.jsp?viewAttachment=true&fileType=" + fileType + "&url=" + URLEncoder.encode(this.getAnswerId() + "/" + attributeValue, "UTF-8");
                    value = "<a href='"+link + "' target=\"_blank\")>" + attributeValue + "</a>";
                }
                else if ("BOOLEAN".equals(type)) {
                    value = ( attributeValue == "Y" )? "Y" : "N";
                }
                else if ("DATETIME".equals(type) || "DATE".equals(type) || "TIME".equals(type)) {
                    try{
                        attributeValue = getTime(attributeValue, type);
                    }catch(ParseException e){
                        logger.error("An error occurred while parsing the Time for Attribute " + attributeName, e);
                    }
                }
                else if ("LIST".equals(type)) {
                    nodeCount++;
                    if (nodeCount != 1) {
                        value = "<br>";
                    }
                }
                if("".equals(value))
                    value = attributeValue;
                else if("<br>".equals(value))
                    value = "";
                if("".equals(title))
                    title = attributeName;
                schemaAttribute = new SchemaAttribute(title, value, 20*level);
                schemaAttributeList.add(schemaAttribute);
            }else{
                title = attributeName;
                value = attributeValue;
                schemaAttribute = new SchemaAttribute(title, "", 20*level);
                schemaAttributeList.add(schemaAttribute);
            }
        }
    }
	
	/**
     * Method to get the Hierarchy of Node given as input
     * @param node Parent Node or Node from which traversal is to be started
     * @param json Schema JSON data
     * @param schemaAttributeList List of Schema Attributes
     * @param resourcePath Resource Path for the Channel
     * @param level Depth of the Node
     * @throws UnsupportedEncodingException
     * @throws JSONException
     */
	private void EvaluateNode(Node node, JSONObject json, List<SchemaAttribute> schemaAttributeList, int level) throws UnsupportedEncodingException, JSONException {
        ProcessElement(node, json, schemaAttributeList, level);
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            JSONObject childJson = getChildJson(nNode.getNodeName(), json.getJSONArray("children"));
            if(childJson != null){
                if (childJson.getString("isNode") == "true") {
                    EvaluateNode(nNode, childJson, schemaAttributeList, level+1);
                }else{
                    ProcessElement(nNode, childJson, schemaAttributeList, level+1);
                }
            }
        }
    }
	
	/**
     * Method to get the JSON of the schema details of the Child Node given
     * @param nodeName name of the Child Node
     * @param childJsonArray Array of JSON Elements corresponding to each child of the Parent Node
     * @return JSONObject The JSON corresponding to nodeName
     * @throws JSONException
     */
	private JSONObject getChildJson(String nodeName, JSONArray childJsonArray) throws JSONException {
        for(int i = 0; i < childJsonArray.length(); i++){
            JSONObject childJson = childJsonArray.getJSONObject(i);
            if(childJson.getString("referenceKey").equalsIgnoreCase(nodeName)){
                return childJson;
            }
        }
        return null;
    }
	
	/**
     * Method to check if a node of given key exists in given JSON
     * @param key Key to be searched in JSON
     * @param json JSON Object to be searched
     * @return boolean State variable to indicate whether key was found or not.
     * @throws JSONException
     */
	private boolean CheckForNode(JSONObject json) throws JSONException {
        if (json.has("isNode")){
            if("true".equals(json.getString("isNode"))){
                return true;
            }
            else{
                return false;
            }
        }
        return true;
    }
	
	/**
     * Method to localize the dateAttribute to Time Zone and format it to the DateTime Mask specified for the application
     * @param dateAttribute String containing the Date in yyyy-MM-dd HH:mm:ss z format
     * @param type Type of Mask to be applied to the given date as defined in the application properties. Valid values are<br><ol><li>DATE</li><li>DATETIME</li><li>TIME</li></ol>
     * @return String containing Formatted Time in the application TimeZone
     * @throws ParseException
     */
	private String getTime(String dateAttribute, String type) throws ParseException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.application");
        TimeZone timeZone = TimeZone.getTimeZone(resourceBundle.getString("TZ_INTERFACE"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        sdf.setTimeZone(timeZone);
        Date localDate = sdf.parse(dateAttribute.replace("Etc/", ""));
        String formatString = "";
        if("DATE".equals(type)){
            formatString = resourceBundle.getString("DATEFORMAT");
        } else if("DATETIME".equals(type)){
            formatString = resourceBundle.getString("DATETIMEFORMAT");
        } else if("TIME".equals(type)){
            formatString = resourceBundle.getString("TIMEFORMAT");
        }
        return new SimpleDateFormat(formatString).format(localDate);
    }
	
	/**
     * Method to localize the date formats associated with IM(yyyy-MM-ddTHH:mm:ssz) Article properties and format them to the specified DATE format in the application 
     * @param date String containing a date property of an IM article
     * @return String containing Formatted Time in the application TimeZone
     * @throws ParseException
     */
    public String processIMDate(String date) throws ParseException{
        date = date.replaceFirst("T", " ");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssz");
        return getTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z").format(sdf.parse(date)), "DATE");
    }
    
    /**
	 * Method to retrieve the HTML template for the templateName
	 * @param input Map of the variables to be entered into the template.
	 * @param templateName Name of the templateName to be used to generate the Template
	 * @return HTML template for the given template name and input data
	 */
	public String getTemplateHtml(Map<String, Object> input, String templateName) {
		String templateHtml = "";
		try {
			Configuration cfg = new Configuration();
	        Template template;
	        cfg.setClassForTemplateLoading(this.getClass(), "/widgets/templates/custom");
	        cfg.setIncompatibleImprovements(new Version(2, 3, 20));
	        cfg.setDefaultEncoding("UTF-8");
	        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
	        if(!templateName.endsWith(".ftl"))
	        	templateName = templateName.concat(".ftl");
	        try{
	        	template = cfg.getTemplate(templateName);
	        }catch(Exception ex){
	        	logger.debug("Could not find a copy of the file in the custom folder. Proceeding to check in the standard folder");
	        	cfg.setClassForTemplateLoading(this.getClass(), "/widgets/templates/standard");
	        	template = cfg.getTemplate(templateName);
	        }
			StringWriter output = new StringWriter();
			template.process(input, output);
			templateHtml = output.toString();
		} catch (Exception ex) {
			logger.error("Error in BaseTag.getTemplateHtml: ", ex);
		}
		return templateHtml;
	}
}
