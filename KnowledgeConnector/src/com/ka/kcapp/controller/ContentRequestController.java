package com.ka.kcapp.controller;

import java.io.BufferedReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ka.kcapp.widget.common.Content;

public class ContentRequestController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Get ContentContribute Schema Servlet
	 */
	final static Logger logger = Logger.getLogger(ContentRequestController.class);

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException {
		// call API to fetch the contentType schema
		PrintWriter out = response.getWriter();
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line = null;
		JSONObject postData = new JSONObject();
		JSONObject respData = new JSONObject();

		try {
			while ((line = reader.readLine()) != null)
				buffer.append(line);
			postData = new JSONObject(buffer.toString());
			StringBuilder widgetHtml = new StringBuilder();
			if (postData.has("action")) {
				String action = postData.getString("action");
				if ("addNode".equals(action)) {
					String nodename = postData.getString("nodename");
					JSONObject channel = Content.getContentSchema(postData.getString("channelid"), request, response);
					JSONArray schemaAttributes = channel.getJSONObject("contentSchema")
							.getJSONArray("schemaAttributes");
					JSONObject object = Content.getChildrenByNodeName(schemaAttributes, nodename);
					object.put("addingChildren", true);
					// for (int x = 0 ; x < schemaAttributes.length();x++){
					if (object != null) {
						// JSONObject object =
						// schemaAttributes.optJSONObject(x);
						widgetHtml.append(Content.writeObject(object, nodename, this.getServletContext(), request));
					}
					respData.put("html", widgetHtml.toString());
				}
			}
		} catch (Exception ex) {
			logger.error("Exception in do post ", ex);
		}
		out.write(respData.toString());
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws java.io.IOException {
		doPost(req, res);
	}
}