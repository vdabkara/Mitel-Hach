/**
 * Copyright (c) 2015, Oracle Corporation and/or its affiliates. All rights reserved.
 *
 * The sample code in this document or accessed through this document is not certified or
 * supported by Oracle. It is intended for educational or testing purposes only. Use of this
 * sample code implies acceptance of the License Agreement
 * (http://www.oracle.com/technetwork/licenses/standard-license-152015.html).
 *
 * File: SearchMapper.java
 * Abstract: Class for constructing Search Result Objects for the Search Result Widget
 * Version: 1.0
 */

package com.ka.kcapp.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ResourceBundle;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class for constructing Search Result Objects for the Search Result Widget
 * @author Prashant Chaturvedi
 * @author Akshay Shenoy
 */
public class SearchMapper {

    final static Logger logger = Logger.getLogger(SearchMapper.class);
    public static final ResourceBundle resourceBundle= ResourceBundle.getBundle("resources.application");
    public static final int defaultSize = 10;


    public static String getRequestUrl(String searchBaseUrl, JSONObject searchFilters) throws JSONException {
        String apiUrl = "";
        if (searchFilters.has("type")) {
            String requestType = searchFilters.getString("type");
            if (requestType.equalsIgnoreCase("search")) {
                apiUrl = searchBaseUrl.concat("search/question?question=").concat(searchFilters.getString("question"));
            } else if (requestType.equalsIgnoreCase("facet")) {
                apiUrl = 
                    searchBaseUrl
                    .concat("search/navigation?priorTransactionId=")
                    .concat(searchFilters.getString("priorTransactionID"))
                    .concat("&facet=")
                    .concat(searchFilters.getString("facet"))
                    .concat("&facetShowAll=false")
                ;
            } else if (requestType.equalsIgnoreCase("page")) {
                apiUrl = 
                    searchBaseUrl
                    .concat("search/pagination?purposeName=ANSWER&pageDirection=")
                    .concat(searchFilters.getString("direction"))
                    .concat("&pageNumber=")
                    .concat(searchFilters.getString("page"))
                    .concat("&priorTransactionId=")
                    .concat(searchFilters.getString("priorTransactionID"))
                ;
            }
        }
        return apiUrl;
    }




    /**
     * Method to process the Search Results returned by Search
     * @param result JSON String containing search response
     * @param searchId String SearchId
     * @param truncateLength int Length to truncate Search Result titles to
     * @param incidentID String Incident ID or Case Reference No. in the context
     * @return String JSON String containing properly constructed Search Result Objects
     */
    public static String getSearchResult (String result, String searchId, int truncateLength, String incidentID) {
        JSONArray results; JSONObject resultList = null;
        try {
            resultList = new JSONObject(result);
            results = resultList.getJSONObject("results").getJSONArray("results");
            if(results.length() > 0)
                results = results.getJSONObject(0).getJSONArray("resultItems");
            int length = results.length();
            if (length > 0) {
                for (int i=0; i<length; i++){
                    JSONObject summaryContent = results.getJSONObject(i);
                    JSONObject urlInfo = getUrl(summaryContent);
                    String href = urlInfo.getString("answerUrl");
                    if(summaryContent.has("textElements")){
                    	summaryContent.put("textElements", getEscapedExcerpts(summaryContent.getJSONArray("textElements")));
                    }
                    if(incidentID != null && !"".equals(incidentID)){
                    	href += "&incidentID=" + incidentID;
                    }
                    if(searchId != null){
                        href += "&s_id=" + searchId + "#__highlight";
                    }
                    summaryContent.put("href", href);
                    summaryContent.put("title", getAnswerTitle(summaryContent, truncateLength));
                    if (urlInfo.has("answerId")) {
                    	summaryContent.put("globalAnswerId", urlInfo.get("answerId"));
                    }
                }
            }
        } catch (JSONException ex) {
            logger.error("Error in SearchMapper.getSearchResult: ", ex);
        }
        return resultList == null ? null : resultList.toString();
    }
    
    /**
     * Method to escape HTML in excerpts of search results.
     * @param answerTextElements Search result excerpts JSON object.
     * @return JSONArray Escaped search result excerpts.
     */
    public static JSONArray getEscapedExcerpts (JSONArray answerTextElements) throws JSONException{
    	for (int i = 0; i < answerTextElements.length(); i++) {
    		JSONObject excerptSnippet = ((JSONObject)answerTextElements.get(i));
    		JSONArray snippet = excerptSnippet.getJSONArray("snippets");
			for (int j = 0; j < snippet.length(); j++) {
				if(excerptSnippet.get("type") != "HTML"){
					((JSONObject)snippet.get(j)).put("text", StringEscapeUtils.escapeHtml4((String)((JSONObject)snippet.get(j)).get("text")));
				}
			}
		}
    	return answerTextElements;
    }

    /**
    * Method to fetch Title of a Knowledge Answer
    * @param answer Search result JSON object
    * @param length Max length to be displayed in the results. Extra characters will be truncated.
    * @return String Answer title
    */
    public static String getAnswerTitle (JSONObject answer, int length) {
        String linkUrl = "", title = "";
        try {
            if (answer.has("title")) {
                try {
                    if(answer.getJSONObject("title") != null && answer.getJSONObject("title").has("url"))
                        linkUrl = answer.getJSONObject("title").getString("url");
                }catch (JSONException e) {
                    linkUrl = answer.getString("title");
                }
                
                try {
                    if(answer.getJSONObject("title") != null) {
                         title = ((JSONObject)answer.getJSONObject("title").getJSONArray("snippets").get(0)).getString("text");
                        title = StringEscapeUtils.escapeHtml4(title);
                    }
                }catch (JSONException e) {
                    if (linkUrl != null) { 
                        title = getFileName(linkUrl);
                    }
                }
            }
            if(title.trim().equalsIgnoreCase(""))
                title = ResourceBundle.getBundle("resources.LabelsBundle").getString("NO_TTLE_LBL");
        } catch (JSONException ex) {
            logger.error("Error in SearchMapper.getAnswerTitle: ", ex);
        }

        String truncatedTitle = (length == 0) ? title : title.substring(0, Math.min(title.length(), length));
        if(truncatedTitle.length() < title.length()){
        	truncatedTitle += "...";
        }
        return truncatedTitle;
    }

   /**
    * This Method returns file name without extension
    * @param s File Name with extension
    * @return String File Name
    */
    public static String getFileName(String s) {
        String separator = System.getProperty("file.separator");
        String filename;
        // Remove the path upto the filename.
        int lastSeparatorIndex = s.lastIndexOf(separator);
        if (lastSeparatorIndex == -1) {
            filename = s;
        } else {
            filename = s.substring(lastSeparatorIndex + 1);
        }
        // Remove the extension.
        int extensionIndex = filename.lastIndexOf(".");
        if (extensionIndex == -1)
            return filename;
        return filename.substring(0, extensionIndex);
    }

    /**
    * This Method returns answer url.
    * @param result object containing response returned by search
    * @return JSONObject Object containing the URL information of the ResultItems
    */
    public static JSONObject getUrl(JSONObject result) {
        String linkUrl="";
        JSONObject urlInfo = new JSONObject();
        try {
            if (result.has("title")) {
                try {
                    if(result.getJSONObject("title") != null && result.getJSONObject("title").has("url")){
                        linkUrl = result.getJSONObject("title").getString("url");
                    }
                    else if (result.has("link")) {
                        linkUrl = result.getString("link");
                    }
                    else if (result.getString("clickThroughLink").contains("turl=")){
                        linkUrl = result.getString("clickThroughLink").split("turl=")[1];
                    }
                } catch (JSONException e) {
                    linkUrl = result.getString("title");
                }
            }
            String highlightUrl = "";
            if(result.has("highlightedLink")) {
                highlightUrl = result.getString("highlightedLink");
            }
            if (linkUrl.contains("IM:")) {
                //result is an IM answer or is linked to one
                String[] articleData = linkUrl.split(":");
                String answerLocale = articleData[3];
                String answerID = articleData[6];
                if(linkUrl.contains("#")) {
                    //result is an IM Attachment
                    String attachment = linkUrl.split("#")[1];
                    String answerUrl = "extUrl.jsp?searchAnswerId=" + result.getString("answerId") + "&viewAttachment=true&fileType=" + result.getString("fileType") + "&url=" + URLEncoder.encode(answerID + "/" + attachment, "UTF-8");
                    if(highlightUrl.contains("#xml="))
                        attachment += "#xml=" + highlightUrl.split("#xml=")[1];
                    String attachmentUrl = answerID + "/file/" + attachment;
                    return new JSONObject().put("isAttachment", true).put("url", attachmentUrl).put("answerUrl", answerUrl);
                }
                if(answerID != null)
                    linkUrl = "answerView.jsp?searchAnswerId="+result.getString("answerId")+ "&answerId=" + answerID;
                if(answerLocale != null)
                    linkUrl += "&loc=" + answerLocale;
                return new JSONObject().put("isAttachment", false).put("url", linkUrl).put("answerUrl", linkUrl).put("answerId", answerID);
            }else{
                //result is an external answer
                String fileType = result.getString("fileType");
                String URL = URLEncoder.encode(linkUrl, "UTF-8");
                linkUrl = "extUrl.jsp?searchAnswerId=" + result.getString("answerId") + "&fileType=" + fileType + "&url=" + URL;
            }
            urlInfo.put("isAttachment", false).put("answerUrl", linkUrl);
            return urlInfo;
        } catch (JSONException ex) {
            logger.error("Error in SearchMapper.getUrl: ", ex);
        } catch (UnsupportedEncodingException e) {
            logger.error("Error in SearchMapper.getUrl: ", e);
        }
        return null;
    }
}
