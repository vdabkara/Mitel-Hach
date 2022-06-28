package com.ka.kcapp.widget.common;

import java.net.URLEncoder;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ka.kcapp.widget.apiUtility.OKAPIUtility;
import com.ka.kcapp.widget.tag.BaseTag;

public class LinkedItems {

	final static Logger logger = Logger.getLogger(LinkedItems.class);

	public final static String RECORDID = "recordId";
	public final static String DESCRIPTION = "description";
	public final static String INCIDENTID = "incidentId";

	private static ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.application");

	/**
	 * Gets the incident object data based on the ID
	 * 
	 * @param incidentID
	 *            - incident ID
	 * @param request
	 *            - HttpServletRequest
	 * @param response
	 *            - HttpServletResponse
	 * @return incident - JSON object
	 */
	public static String getIncidentJSONByID(String incidentID, HttpServletRequest request,
			HttpServletResponse response) {
		try {

			if (request.getAttribute("incident_json_" + incidentID) != null) {
				return request.getAttribute("incident_json_" + incidentID).toString();
			} else {
				String linkedAnswerURL = resourceBundle.getString("OKCS_IM_API_URL")
						.concat("incidents?q=incidentId+eq+'").concat(URLEncoder.encode(incidentID, "UTF-8"))
						.concat("'");
				JSONObject linkedArticlesResponse = new JSONObject(
						OKAPIUtility.callRESTAPI(request, response, linkedAnswerURL, "GET", null));
				JSONObject incident = new JSONObject();
				if (linkedArticlesResponse.getInt("count") > 0) {
					JSONArray linkedItems = linkedArticlesResponse.getJSONArray("items");

					incident.put(LinkedItems.RECORDID, linkedItems.getJSONObject(0).getString("recordId"));
					incident.put(LinkedItems.DESCRIPTION, Normalizer
							.normalize(linkedItems.getJSONObject(0).getString("description"), Normalizer.Form.NFKD));
					incident.put(LinkedItems.INCIDENTID, linkedItems.getJSONObject(0).getString("incidentId"));
				} else {
					incident.put("recordId", "");
					incident.put("description", request.getSession().getAttribute("kw"));
					incident.put("incidentId", incidentID);
				}
				request.setAttribute("incident_json_" + incidentID, incident.toString());
				return incident.toString();
			}
		} catch (Exception ex) {
			logger.error("com.ka.kcapp.widget.common.LinkedItems.getIncidentByID() ", ex);
		}
		return null;
	}

	/**
	 * @param incidentID
	 * @param request
	 * @param response
	 * @return
	 */
	public static String getLinkedAnswersByIncidentID(String incidentID, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (logger.isDebugEnabled())
				logger.debug("LinkedItems.getLinkedAnswersByIncidentID = " + incidentID);
			if (request.getAttribute("linked_items") != null) {
				if (logger.isDebugEnabled())
					logger.debug(
							"LinkedItems.getLinkedAnswersByIncidentID returning linked items from request cache = ");
				return request.getAttribute("linked_items").toString();
			} else {
				if (logger.isDebugEnabled())
					logger.debug("LinkedItems.getLinkedAnswersByIncidentID returning linked items fresh ");
				String incident_json = LinkedItems.getIncidentJSONByID(incidentID, request, response);
				if (incident_json != null) {
					JSONObject incident = new JSONObject(incident_json);
					if (incident.getString(LinkedItems.RECORDID) != null
							&& incident.getString(LinkedItems.RECORDID).length() > 0) {
						String linkedAnswerURL = resourceBundle.getString("OKCS_IM_API_URL") + "incidents/"
								+ URLEncoder.encode(incident.getString(LinkedItems.RECORDID), "UTF-8")
								+ "/incidentLinks";
						JSONObject linkedArticlesResponse = new JSONObject(
								OKAPIUtility.callRESTAPI(request, response, linkedAnswerURL, "GET", null));
						if (logger.isDebugEnabled())
							logger.debug("LinkedItems response 1 " + linkedArticlesResponse.toString());
						JSONArray resultList = linkedArticlesResponse.getJSONArray("items");
						if (logger.isDebugEnabled())
							logger.debug("LinkedItems response " + resultList.toString());
						request.setAttribute("linked_items", resultList.toString());
						return resultList.toString();
					} else {
						request.setAttribute("linked_items", "[]");
					}
				} else {
					request.setAttribute("linked_items", "[]");
				}
			}
		} catch (Exception ex) {
			logger.error("com.ka.kcapp.widget.common.LinkedItems.getLinkedAnswersByIncidentID() ", ex);
		}
		return "[]";
	}

	/**
	 * This methods removes the incident link for the selected answer/document
	 * 
	 * @param request
	 *            - HttpServletRequest
	 * @param response
	 *            - HttpServletResponse
	 * @param postData
	 *            - pay load or input data
	 * @return responseString - response string in JSON format indicating if the
	 *         unlink was successful
	 */
	public static String removeLinkedDocument(HttpServletRequest request, HttpServletResponse response,
			JSONObject postData) {
		try {
			ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.application");
			JSONObject incidentDetails = LinkedItems.createPostData(postData, request, response);
			String urlStr = resourceBundle.getString("OKCS_IM_API_URL") + "incidents/unlink";
			String responseString = OKAPIUtility.callRESTAPI(request, response, urlStr, "POST",
					incidentDetails.toString());
			return ("{\"message\":\" Document Unlinked \"}");
		} catch (Exception ex) {
			logger.error("Exception: General excetpion while unlinking the document +LinkItem-6001-"+request.getSession().getId(), ex.fillInStackTrace());
		}
		return ("{\"error\":\" Error Occurred during link contact Admin with Error Code LinkItem-6001-"+request.getSession().getId()+" \"}");
	}

	/**
	 * 
	 * This method creates a document link
	 * 
	 * @param request
	 * @param response
	 * @param postData
	 * @return
	 */
	public static String addLinkedDocument(HttpServletRequest request, HttpServletResponse response,
			JSONObject postData) {
		String local_response = "{}";
		try {
			if (logger.isDebugEnabled())
				logger.debug("LinkedItems : addLinkedDocument method called for incident id "
						+ postData.getString("incidentId"));
			ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.application");
			JSONObject incidentDetails = LinkedItems.createPostData(postData, request, response);
			String urlStr = resourceBundle.getString("OKCS_IM_API_URL") + "incidents/link";
            if (logger.isDebugEnabled())  logger.debug("URL string for the incidents/link call " + urlStr);
            if (logger.isDebugEnabled())  logger.debug("Details string for the incidents/link call " + incidentDetails.toString());

			String s = OKAPIUtility.callRESTAPI(request, response, urlStr, "POST", incidentDetails.toString());
			// normalize(CharSequence src, Normalizer.Form form)
			JSONObject localresponse = new JSONObject(s);
			logger.debug("localresponse after the incidents/link call " + localresponse.toString());
			if (localresponse.has("error")) {
                local_response = "{\"error\":\" Error Occurred during link contact Admin with Error Code LinkItem-6000-"+request.getSession().getId()+" \"}";
                logger.error("LinkItem-6000-"+request.getSession().getId()+"\n"+s+"\n\n");
			} else {
				JSONObject content = Content.getContentByAnswerID(postData.getString("answerId"), request, response);
				JSONObject json_response = new JSONObject();
				json_response.put("message", " Document Linked ");
				json_response.put("linked_documentId", content.getString("documentId"));
				JSONObject incident = new JSONObject();
				incident.put("incidentId", postData.getString("incidentId"));
				JSONObject document = new JSONObject();
				document.put("answerId", content.getString("answerId"));
				document.put("documentId", content.getString("documentId"));
				document.put("title", content.getString("title"));
				document.put("recordId", content.getString("recordId"));
				document.put("versionId", content.getString("versionId"));
				document.put("version", content.getString("version"));

				JSONObject value = new JSONObject();
				value.put("incident", incident);
				value.put("document", document);

				Map<String, Object> input = new HashMap<String, Object>();
				input.put("value", new JsonHash(value));
				input.put("localeid", "en_US");
				StringBuilder widgetHtml = new StringBuilder();
				logger.debug("\n\n==================================\n");
				widgetHtml.append(
						BaseTag.getTemplateHtml(input, "linkList_item", request.getSession().getServletContext()));
				logger.debug("\n\n==================================\n\n");
				json_response.put("linked_item", widgetHtml.toString());
                logger.info("Returning the final response " + json_response.toString());
				local_response = json_response.toString();

			}
		} catch (JSONException e) {
            logger.error("AddLink JSONException: Document linking failed LinkItem-6010-"+request.getSession().getId(), e.fillInStackTrace());
            local_response = "{\"error\":\" Error Occurred during link contact Admin with Error Code LinkItem-6010-"+request.getSession().getId()+" \"}";
		}
		return local_response;
	}

	/**
	 * Creates payload/post data in JSON format
	 * 
	 * @param filters
	 * @param request
	 * @param response
	 * @return
	 */
	public static JSONObject createPostData(JSONObject filters, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			logger.debug("creating post data with filters =  " + filters.toString());
			JSONObject incident = new JSONObject(
					LinkedItems.getIncidentJSONByID(filters.getString("incidentId"), request, response));
			JSONObject postData = new JSONObject();
			postData.put("incident", incident);
			JSONObject document = new JSONObject();
			if (filters.has("answer_title"))
				document.put("title", Normalizer.normalize(filters.getString("answer_title"), Normalizer.Form.NFKD));
			if (filters.has("recordId"))
				document.put("recordId", filters.getString("recordId"));
			if (filters.has("versionId"))
				document.put("versionId", filters.getString("versionId"));
			if (filters.has("documentId"))
				document.put("documentId", filters.getString("documentId"));
			if (filters.has("version"))
				document.put("version", filters.getString("version"));
			if (filters.has("answerId"))
				document.put("answerId", filters.getString("answerId"));
			postData.put("documents", new JSONArray("[" + document.toString() + "]"));
			logger.debug("Returning post data in JSON for service call" + postData.toString());
			return postData;
		} catch (Exception ex) {
			logger.error("com.ka.kcapp.widget.common.LinkedItems.createPostData() ", ex.fillInStackTrace());
		}
		return null;
	}
}
