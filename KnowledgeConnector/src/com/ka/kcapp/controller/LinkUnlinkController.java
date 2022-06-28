
package com.ka.kcapp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.ka.kcapp.services.RemedyServiceManager;
import com.ka.kcapp.services.SBMServiceManager;
import com.ka.kcapp.widget.common.LinkedItems;
import com.ka.kcapp.widget.common.Navigation;

/**
 * An controller Servlet to facilitate the link/unlink operation with external
 * applications/integrations.This is a 2 way linking as below 1. Call the
 * external API passing in the selected answer data 2. Call the internal REST
 * API and update the answer/document with the incident number
 * 
 * @author SMarimuthu
 */
public class LinkUnlinkController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.application");

	final static Logger logger = Logger.getLogger(LinkUnlinkController.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LinkUnlinkController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String action = null;

		try {
			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = request.getReader();
			String line = null;
			while ((line = reader.readLine()) != null)
				buffer.append(line);
			JSONObject postData = new JSONObject(buffer.toString());
			action = postData.getString("action");
			// get the application source from session
			String sourceApplication = (String) request.getSession().getAttribute("requestSource");
			logger.debug("Request originating application source " + sourceApplication);
			logger.debug("Post data in LinkUnlinkController.doPost: " + postData.toString());
			JSONObject json_response = null;
			switch (action) {
			case "addLink":
				logger.info("Linking the selected document");
				// call the external system based on application source
				if (sourceApplication.equalsIgnoreCase(resourceBundle.getString("SBM_APP_NAME"))) {
					json_response = new JSONObject(SBMServiceManager.addIncidentLink(postData,request.getSession().getAttribute("userID")));
					logger.info("Json response after service call = " + json_response.toString());
					if (json_response.has("error")) {
						logger.info("SBM Linking error " + json_response.toString());
						// out.write(json_response.toString());
						out.write("{\"message\":\" Document Linking failed - "
								+ json_response.getString("message").toString() + "\"}");
						break;
					}
				} else if (sourceApplication.equalsIgnoreCase(resourceBundle.getString("REMEDY_APP_NAME"))) {
					json_response = new JSONObject(RemedyServiceManager.addIncidentLink(postData, request.getSession().getAttribute("userID")));
					logger.info("Json response after service call = " + json_response.toString());
					if (json_response.has("error")) {
						logger.info("REMEDY Linking error , response " + json_response.toString());
						out.write("{\"message\":\" Document Linking failed - "
								+ json_response.getString("message").toString() + "\"}");
						break;
					}
				}
				// call the internal API if, external linking was successful
				logger.info("calling the internal API for linking");
				out.write(LinkedItems.addLinkedDocument(request, response, postData));
				break;
			case "unlink":
				logger.info("UnLinking the selected document");
				// call the external system based on application source
				if (sourceApplication.equalsIgnoreCase(resourceBundle.getString("SBM_APP_NAME"))) {
					json_response = new JSONObject(SBMServiceManager.removeIncidentLink(postData,request.getSession().getAttribute("userID")));
					if (json_response.has("error")) {
						logger.info("Json response after service call = " + json_response.toString());
						// out.write(json_response.toString());
						out.write("{\"message\":\" Document Unlink failed - "
								+ json_response.getString("message").toString() + "\"}");
						break;
					}
				} else if (sourceApplication.equalsIgnoreCase(resourceBundle.getString("REMEDY_APP_NAME"))) {
					json_response = new JSONObject(RemedyServiceManager.removeIncidentLink(postData, request.getSession().getAttribute("userID")));
					logger.info("Json response after unlink service call " + json_response.toString());
					if (json_response.has("error")) {
						logger.info("REMEDY UnLinking error");
						// out.write(json_response.toString());
						out.write("{\"message\":\" Document Unlink failed - "
								+ json_response.getString("message").toString() + "\"}");
						break;
					}
				}
				// call the internal API if, external unlinking was successful
				logger.info("calling the internal API for unlinking");
				out.write(LinkedItems.removeLinkedDocument(request, response, postData));
				break;
			case "setNavigation":
				out.write(Navigation.setNavigationLocation(request, response, postData));
				break;
			}
		} catch (JSONException e) {
			logger.error("JSONException : User action " + action + " failed " + e.fillInStackTrace());
			out.write("{\"error\":\" Error Occurred during link contact Admin with Error Code LinkItem-0000 \"}");
		}
	}

}// end
