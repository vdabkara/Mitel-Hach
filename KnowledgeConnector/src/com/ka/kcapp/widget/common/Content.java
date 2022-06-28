package com.ka.kcapp.widget.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import com.ka.kcapp.util.Utilities;
import com.ka.kcapp.util.XmlUtilities;
import com.ka.kcapp.vo.RecommendationDetails;
import com.ka.kcapp.widget.apiUtility.OKAPIUtility;
import com.ka.kcapp.widget.tag.BaseTag;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

import org.json.XML;

/**
 * Content class to handle the contribute content requests.It calls the
 * appropriate methods to read/construct a new document in xml format(according
 * to the channel schema) and invokes the REST APIs. It also returns a status
 * message in JSON format based on the output from the API calls i.e if the
 * content creation was successful.
 *
 */
public class Content {
	// logger
	final static Logger logger = Logger.getLogger(Content.class);

	public final static String RECORDID = "recordId";
	public final static String DESCRIPTION = "description";
	public final static String INCIDENTID = "incidentId";
	public final static String CHANNEL_FORM_REFERENCEKEY = "ccform_referenceKey";
	public final static String CHANNEL_FORM_CHANNELID = "ccform_channelid";
	public final static String CHANNEL_FORM_NAME = "ccform_name";
	public final static String CHANNEL_FORM_PREFIX = "ccfield_";
	private static ResourceBundle resourceBundle;
	private static ResourceBundle labelsBundle;

	private final static File upload_dir;
	private final static DiskFileItemFactory factory;

	static {
		resourceBundle = ResourceBundle.getBundle("resources.application");
		upload_dir = new File(resourceBundle.getString("FILEUPLOAD_PATH"));
		factory = new DiskFileItemFactory(0, Content.upload_dir);
		// factory.setRepository(Content.upload_dir);
		labelsBundle = ResourceBundle.getBundle("resources.LabelsBundle", new Locale("en"));
	}

	/**
	 * Default
	 */
	public Content() {
		super();
	}

	/**
	 * 
	 * Gets the content types from the repository
	 * 
	 * @param request
	 *            - HttpServletRequest
	 * @param response
	 *            - HttpServletResponse
	 * @return contentTypes - JSONArray of the content type objects
	 */
	public static JSONArray getContentChannelList(HttpServletRequest request, HttpServletResponse response) {
		JSONArray contentTypes = new JSONArray();
		try {
			String url = resourceBundle.getString("OKCS_IM_API_URL").concat("contentTypes?orderBy=name:asc");
			String contentTypeData = OKAPIUtility.callRESTAPI(request, response, url, "GET", null);
			JSONObject contentTypesJSON = new JSONObject(contentTypeData);
			contentTypes = contentTypesJSON.getJSONArray("items");
			if (logger.isDebugEnabled())
				logger.debug("Content.getContentChannelList " + contentTypes.toString());
		} catch (Exception ex) {
			logger.error("", ex);
		}
		return contentTypes;
	}

    public static JSONObject getUserGroupByREFKEY(
        String ug_REFKEY, 
        HttpServletRequest request,
        HttpServletResponse response
    ) {
        JSONObject usergroup_json = new JSONObject();
        logger.error("in getUserGroupByREFKEY");
        try {
            String url = resourceBundle.getString("OKCS_IM_API_URL").concat("/userGroups/referenceKey/").concat(ug_REFKEY);
            if (logger.isDebugEnabled()) logger.debug("getUserGroupByREFKEY: "+url);
            String contentTypeData = OKAPIUtility.callRESTAPI(request, response, url, "GET", null);
            usergroup_json = new JSONObject(contentTypeData);
            if (logger.isDebugEnabled()) logger.debug("Content.getUserGroupByREFKEY " + usergroup_json.toString());
        } catch (Exception ex) {
            logger.error("", ex);
        }
        return usergroup_json;
    }


	/**
	 * 
	 * Gets the schema for a specific content type
	 * 
	 * @param recordid
	 *            - record ID of the content type
	 * @param request
	 *            - HttpServletRequest
	 * @param response
	 *            - HttpServletResponse
	 * @return contentTypeFullDataJSON - Content type as JSONObject
	 */
	public static JSONObject getContentSchema(String recordid, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject contentTypeFullDataJSON = new JSONObject();
		try {
			// ResourceBundle resourceBundle =
			// ResourceBundle.getBundle("resources.application");
			String url = resourceBundle.getString("OKCS_IM_API_URL").concat("contentTypes/" + recordid);
			String contentTypeFullData = OKAPIUtility.callRESTAPI(request, response, url, "GET", null);
			contentTypeFullDataJSON = new JSONObject(contentTypeFullData);
			if (logger.isDebugEnabled())
				logger.debug("Content.getContentSchema 1 " + contentTypeFullDataJSON.toString());
		} catch (Exception ex) {
			logger.error("", ex);
		}
		return contentTypeFullDataJSON;
	}
	
	
	public static JSONObject createRecommendation(String recommendationXML, 
			RecommendationDetails recommendationDetails, HttpServletRequest request, HttpServletResponse response) throws JSONException
	{
		JSONObject response_object = new JSONObject();
		logger.info("createRecommendation :: Start Creating Recommendation.");
		try
		{
			if(null!=recommendationXML && !"".equals(recommendationXML))
			{
				/*
				 * call KA Web Service
				 */
				String kaURL = resourceBundle.getString("OKCS_IM_API_URL") + "contentRecommendations";
				String authToken = OKAPIUtility.getKMAuthToken(request, response);
				if(null!=authToken && !"".equals(authToken))
				{
					HttpURLConnection conn = null;
					String recommendationCreateRequestData="";
					try
					{
						URL url = new URL(kaURL);
						conn = (HttpURLConnection) url.openConnection();
						conn.setDoOutput(true);
						conn.setRequestMethod("POST");
						conn.setRequestProperty("Accept", "application/xml");
						conn.setRequestProperty("Content-Type", "application/xml");
						conn.setRequestProperty("kmauthtoken", authToken);
						OutputStream os = conn.getOutputStream();
						os.write(recommendationXML.getBytes());
						os.flush();
						url = null;
					}
					catch(Exception e)
					{
						Utilities.printStackTraceToLogs(Content.class.getName(), "createRecommendation()", e);
					}
					
					BufferedReader br;
					if(null!=conn)
					{
						String output;
						if(conn.getResponseCode()==HttpURLConnection.HTTP_CREATED)
						{
							br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
						}
						else
						{
							br = new BufferedReader(new InputStreamReader((conn.getErrorStream())));
						}
						if(null!=br)
						{
							while ((output = br.readLine()) != null) {
								if(null!=output && !"".equals(output))
								{
									recommendationCreateRequestData =recommendationCreateRequestData+output;
								}
							}
							br.close();
							br = null;
						}					
						
						/*
						 * CHECK WHERTHER SUCCESS OR NOT.
						 */
						if(conn.getResponseCode()==HttpURLConnection.HTTP_CREATED)
						{
							logger.info("createRecommendation :: Recommendation Successfully created in KA. Proceed for Identifying Record Id & Saving Data in OSVC Table.");
							String contentRecommendationRecordId = "";
							/*
							 * get Record Id of the Created Content Recommendation
							 */
							try
							{
								DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
								DocumentBuilder builder = factory.newDocumentBuilder();
								InputSource is = new InputSource(new StringReader(recommendationCreateRequestData));
								Document doc = builder.parse(is);
								if(null!=doc)
								{
									Element root = doc.getDocumentElement();
									if(null!=root)
									{
										Node recordIdNode = XmlUtilities.find(root, "recordId");
										if(null!=recordIdNode)
										{
											contentRecommendationRecordId = recordIdNode.getTextContent();
										}
										recordIdNode = null;
									}
									root = null;
								}
								doc = null;
								builder=  null;
								factory = null;
								is = null;
							}
							catch(Exception e)
							{
								Utilities.printStackTraceToLogs(Content.class.getName(), "createRecommendation()", e);
							}
							
							if(null!=contentRecommendationRecordId && !"".equals(contentRecommendationRecordId))
							{
								logger.info("createRecommendation :: contentRecommendationRecordId :: > " + contentRecommendationRecordId);
								// add recommendationId to responseObject
								response_object.put("success", true);
								response_object.put("recordId", contentRecommendationRecordId);
								/*
								 * PROCEED FOR SAVING DATA IN OSVC Table.
								 */
								recommendationDetails.setRecommendationId(contentRecommendationRecordId);
							}
							else
							{
								logger.info("createRecommendation :: Failed to Identify Record Id from Received Response XML.");
								// generic Error
								response_object.put("success", false);
								response_object.put("message", labelsBundle.getString("RecommendContentTag.errorMessage"));
							}
							contentRecommendationRecordId = null;
						}
						else
						{
							logger.info("createRecommendation :: Failed to Create Recommendation Data in KA.");
							logger.info("createRecommendation :: Failure Code :: > " + conn.getResponseCode());
							logger.info("createRecommendation :: Failure Message :: > " + conn.getResponseMessage());
							logger.info("createRecommendation :: Error Response XML :: > " + recommendationCreateRequestData);
							
							/*
							 * CHECK IF THE ERROR RESPONSE CONTAINS ERROR CODE - OKDOM-CR0001
							 * E.G. ContentType does not allow Content recommendations
							 * Throw Custom Message -  Selected ContentType does not allow Content recommendations.
							 */
							if(recommendationCreateRequestData.contains("OKDOM-CR0001"))
							{
								response_object.put("success", false);
								response_object.put("message", "Selected Content Type does not allow Content recommendations.");
							}
							else
							{
								// generic Error
								response_object.put("success", false);
								response_object.put("message", labelsBundle.getString("RecommendContentTag.errorMessage"));
							}
						}
					
						output= null;
						conn.disconnect();
					}
					recommendationCreateRequestData = null;
					conn = null;
				}
				else
				{
					response_object.put("success", false);
					response_object.put("message", labelsBundle.getString("RecommendContentTag.errorMessage"));
				}
				authToken= null;
				kaURL= null;
			}
		}
		catch(Exception e)
		{
			Utilities.printStackTraceToLogs(Content.class.getName(), "createRecommendation()", e);
			response_object.put("success", false);
		}
		return response_object;
	}


	/**
	 * 
	 * Creating content with file attachments
	 * 
	 * @param request
	 *            - HttpServletRequest
	 * @param response
	 *            - HttpServletResponse
	 * @param contentxml
	 *            - new content in xml format
	 * @param uploadFiles
	 *            - file attachments for the new content record
	 * @return response_object - response string based on the create content
	 *         service call output
	 */
	public static JSONObject createContentWithFiles(HttpServletRequest request, HttpServletResponse response,
			String contentxml, List<File> uploadFiles) {
		JSONObject response_object = new JSONObject();
		logger.debug("createContentWithFiles called with content xml " + contentxml);
		try {
			String urlStr = resourceBundle.getString("OKCS_IM_API_URL") + "content";
			logger.debug("connecting to content url  " + urlStr);
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost httpRequest = new HttpPost(urlStr);
			String authToken = OKAPIUtility.getKMAuthToken(request, response);
			if (authToken != null)
				httpRequest.addHeader("kmauthtoken", authToken);
			if (uploadFiles.size() > 0) {
				logger.debug("create content call execution WITH file attachments");
				httpRequest.addHeader("Accept", "application/xml, text/javascript, */*; q=0.01");
				httpRequest.addHeader("Connection", "keep-alive");
				httpRequest.addHeader("Accept-Charset", "UTF-8");
				MultipartEntityBuilder multiPartEntityBuilder = MultipartEntityBuilder.create();
				for (File uploadFile : uploadFiles) {
					multiPartEntityBuilder.setCharset(Charset.forName("UTF-8"));
					StringBody sBody = new StringBody(contentxml, ContentType.APPLICATION_XML);
					multiPartEntityBuilder.addPart("contentBO", sBody);
					if (logger.isDebugEnabled())
						logger.debug("Content.createContentWithFiles Uploading file " + uploadFile.getAbsolutePath());
					FileBody fileBody = new FileBody(uploadFile);
					multiPartEntityBuilder.addPart("filesToUpload", fileBody);
				}
				HttpEntity reqEntity = multiPartEntityBuilder.build();
				if (logger.isDebugEnabled())
					logger.debug("Content.createContentWithFiles entity with files = " + reqEntity.toString());
				httpRequest.setEntity(reqEntity);
			} else {
				logger.debug("create content call execution WITHOUT file attachments");
				httpRequest.addHeader("Accept", "application/xml, text/javascript, */*; q=0.01");
				httpRequest.addHeader("Connection", "keep-alive");
				httpRequest.addHeader("Content-Type", "application/xml; charset=UTF-8");
				httpRequest.addHeader("Accept-Charset", "UTF-8");
				httpRequest.setEntity(new StringEntity(contentxml));
			}
			if (logger.isDebugEnabled())
				logger.debug("Content.createContentWithFiles entity with http_request = " + httpRequest.toString());

			HttpResponse http_response = httpClient.execute(httpRequest);
			int statusCode = http_response.getStatusLine().getStatusCode();
			logger.info("create content service response code " + statusCode);
			HttpEntity entity = http_response.getEntity();
			String responseString = EntityUtils.toString(entity, "UTF-8");
			if (logger.isDebugEnabled())
				logger.debug("Content.createContentWithFiles responseString " + responseString);
//			logger.info("Content.createContentWithFiles responseString " + responseString);
			Reader reader = new StringReader(responseString);
			Document respdoc = XmlUtilities.getDocument(reader);
			if (logger.isDebugEnabled())
				logger.debug("Content.createContentWithFiles nodename " + respdoc.getFirstChild().getNodeName());
			// add the appropriate status messages for display
			if ("ErrorResponse".equals(respdoc.getFirstChild().getNodeName())) {
				response_object.put("success", false);
				response_object.put("message", labelsBundle.getString("ContributeContentTag.errorMessage"));
			}
			if ("Content".equals(respdoc.getFirstChild().getNodeName())) {
				response_object.put("success", true);
				response_object.put("message",
						labelsBundle.getString("ContributeContentTag.successMessage1")
								+ XmlUtilities.getChildText(respdoc.getFirstChild(), "documentId")
								+ labelsBundle.getString("ContributeContentTag.successMessage2"));
				response_object.put("documentId", XmlUtilities.getChildText(respdoc.getFirstChild(), "documentId"));
				response_object.put("answerId", XmlUtilities.getChildText(respdoc.getFirstChild(), "answerId"));
			}
		} catch (Exception ex) {
			logger.error("Exception while saving content : \n", ex);
		}
		logger.debug("create content service returning json response  " + response_object);
		return response_object;
	}

	/**
	 * 
	 * This method cleans up the uploaded files after processing
	 * 
	 * @param items
	 * @param uploadFiles
	 */
	public static void cleanUploadedItems(List<FileItem> items, List<File> uploadFiles) {
		if (items != null) {
			for (FileItem item : items) {
				try {
					item.delete();
				} catch (Exception ex) {
					logger.error("Could not clean up file " + item.getName());
				}
				item = null;
			}
		}
		if (uploadFiles != null) {
			for (File f : uploadFiles) {
				try {
					f.delete();
				} catch (Exception ex) {
					logger.error("Could not clean up file " + f.getPath() + " " + f.getName());
				}
				f = null;
			}
		}
	}

	/**
	 * 
	 * Method to create the file item list
	 * 
	 * @param items
	 *            - list of file items to be processed
	 * @return list of newly created file items
	 * @throws Exception
	 */
	public static List<File> createFileList(List<FileItem> items) throws Exception {
		ArrayList<File> listoffiles = new ArrayList<File>();
		Iterator<FileItem> iter = items.iterator();
		while (iter.hasNext()) {
			FileItem item = iter.next();
			if (item.getFieldName().startsWith(CHANNEL_FORM_PREFIX) && !item.isFormField() && item.getSize() > 0) {
				// TODO need to create proper file and add to list
				if (logger.isDebugEnabled())
					logger.debug("\n\nContent.createFileList " + item.getClass().getName() + " " + item.getContentType()
							+ " " + item.getFieldName() + " " + item.getName() + " " + item.getSize() + " " + "\n\n");
				DiskFileItem fileitem = (DiskFileItem) item;
				File f = new File(upload_dir + "\\" + fileitem.getName());

				if (logger.isDebugEnabled())
					logger.debug("\n\tcreateFile from " + upload_dir + "\\" + fileitem.getName() + "  "
							+ fileitem.getStoreLocation().getName() + "\n");
				fileitem.write(f);
				listoffiles.add(f);
			}
		}
		return listoffiles;
	}

    public static String createContentXMLWithFiles(
        List<FileItem> items, 
        boolean wDefaultUG,
        HttpServletRequest request,
        HttpServletResponse response        
    ) {
        try {
            logger.debug("Creating content xml for post ");
            Document newContentDocument = XmlUtilities.getDocument();
            Node contentNode = XmlUtilities.createNode(newContentDocument, "Content");
            XmlUtilities.createNode(contentNode, "published", "false");
            XmlUtilities.createNode(contentNode, "isForEdit", "true");
            newContentDocument.appendChild(contentNode);
            Node localeNode = XmlUtilities.createNode(contentNode, "locale");
            // TODO: Have to get locale from session
            XmlUtilities.createNode(localeNode, "recordId", "en_US");
            Node viewKeyNode = XmlUtilities.createNode(XmlUtilities.createNode(contentNode, "views"), "ViewKey");

            /*
            * Since there is no REST API to get the Views, the Views need to
            * hard coded and fed from the Properties file
            */

            XmlUtilities.createNode(viewKeyNode, "recordId", resourceBundle.getString("KCA_VIEW_RECORDID"));
            XmlUtilities.createNode(viewKeyNode, "referenceKey", resourceBundle.getString("KCA_VIEW_REFERENCEKEY"));
            XmlUtilities.createNode(viewKeyNode, "name", resourceBundle.getString("KCA_VIEW_NAME"), true);
            Node contentTypeNode = XmlUtilities.createNode(contentNode, "contentType");
            XmlUtilities.createNode(contentTypeNode, "recordId",Content.getStringAttribute(items, CHANNEL_FORM_CHANNELID));
            XmlUtilities.createNode(contentTypeNode, "referenceKey",Content.getStringAttribute(items, CHANNEL_FORM_REFERENCEKEY));
            XmlUtilities.createNode(contentTypeNode, "name", Content.getStringAttribute(items, CHANNEL_FORM_NAME), true);
            if (wDefaultUG){
                Node userGroups = XmlUtilities.createNode(contentNode, "userGroups");
                Node UserGroupKey = XmlUtilities.createNode(userGroups, "UserGroupKey");
                UserGroupKey.setTextContent(XML.toString(Content.getUserGroupByREFKEY(resourceBundle.getString("DEFAULT_USERGROUP_REFKEY"), request, response)));
            }
            Node xmlNode = XmlUtilities.createNode(contentNode, "xml");
            Node channelNode = XmlUtilities.createNode(xmlNode,Content.getStringAttribute(items, CHANNEL_FORM_REFERENCEKEY));
            logger.debug("Creating content xml for post, processing file attachments ");
            Iterator<FileItem> iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = iter.next();
                Content.addContentBody(channelNode, item);
            }

            if (logger.isDebugEnabled()) logger.debug("Content.createContentXMLWithFiles \n" + XmlUtilities.printNode(newContentDocument) + "\n");
            return XmlUtilities.printNode(newContentDocument);
        } catch (Exception ex) {
            logger.error("Exception: while creating the content xml with file attachments", ex);
        }
        return "";
    }


	/**
	 * 
	 * Method to create content xml with file attachment nodes
	 * 
	 * @param items
	 *            - list of file items
	 * @return content xml string formatted as per the schema selected
	 */
	public static String createContentXMLWithFiles(List<FileItem> items) {
            return createContentXMLWithFiles(items, false, null, null);
	}

	private static void addContentBody(Node channelNode, FileItem item) {
		if (item.getFieldName().startsWith(CHANNEL_FORM_PREFIX) && item.getSize() > 0) {
			String fieldname = item.getFieldName().substring(Content.CHANNEL_FORM_PREFIX.length());
			if (fieldname.indexOf("/") != -1) {
				if (logger.isDebugEnabled())
					logger.debug(
							"\nContent.addContentBody fieldname=" + fieldname + "\n\t" + item.getClass().getName());
				DiskFileItem localitem = (DiskFileItem) item;
				localitem.setFieldName(CHANNEL_FORM_PREFIX + fieldname.substring(fieldname.indexOf("/") + 1));
				String parentnodename = fieldname.substring(0, fieldname.indexOf("/"));
				Node parent = XmlUtilities.createNode(channelNode, parentnodename);
				Content.addContentBody(parent, item);
			} else {
				if (item.isFormField()) {
					XmlUtilities.createNode(channelNode, fieldname, item.getString(), true);
				} else {
					Element file = (Element) XmlUtilities.createNode(channelNode, fieldname, item.getName(), true);
					file.setAttribute("SIZE", item.getSize() + "");
				}
			}
		}
	}

	/**
	 * 
	 * Get method to retrieve the file from the list
	 * 
	 * @param items
	 *            - list of file items
	 * @param attr
	 *            - attribute name
	 * @return - string attribute value
	 */
	private static String getStringAttribute(List<FileItem> items, String attr) {
		Iterator<FileItem> iter = items.iterator();
		while (iter.hasNext()) {
			FileItem item = iter.next();
			if (item.isFormField()) {
				if (item.getFieldName().equals(attr))
					return item.getString();
			}
		}
		return "";
	}

	/**
	 * Method to get the file items from the post request
	 * 
	 * @param request
	 *            - HttpServletRequest
	 * @return List<FileItem> - list of file items in the request
	 */
	public static List<FileItem> getPostFileItemsFromRequest(HttpServletRequest request) {
		ServletFileUpload upload = new ServletFileUpload(factory);
		logger.debug("servlet file upload directory " + upload.getFileItemFactory().toString());
                logger.debug("upload directory = "+Content.upload_dir);
	    List<FileItem> returnlist = null;
		try {
		    logger.debug("getPostFileItemsFromRequest 1");
                    returnlist = upload.parseRequest(request);
                    if (logger.isDebugEnabled()){
                        logger.debug("getPostFileItemsFromRequest 2 "+returnlist);
                        logger.debug("getPostFileItemsFromRequest 3 "+returnlist.size());
                    }                    
		} catch (FileUploadException e) {
			logger.error("Multipart requset processing error ", e.fillInStackTrace());
		}
	    logger.debug("getPostFileItemsFromRequest 2");
            return returnlist;
	}

	/**
	 * @param schemaAttributes
	 * @param nodename
	 * @return
	 * @throws Exception
	 */
	public static JSONObject getChildrenByNodeName(JSONArray schemaAttributes, String nodename) throws Exception {
		if (logger.isDebugEnabled())
			logger.debug("\ngetChildrenByNodeName " + nodename);
		JSONObject object = null;
		if (nodename.contains("/")) {
			String local_nodename = nodename.substring(0, nodename.indexOf("/"));
			String local_childname = nodename.substring(nodename.indexOf("/") + 1);
			JSONObject local_object = Content.getChildrenByNodeName(schemaAttributes, local_nodename);
			if (local_object != null)
				return Content.getChildrenByNodeName(local_object.getJSONArray("children"), local_childname);
		} else {
			logger.debug("Iterating the schema Attributes");
			for (int x = 0; x < schemaAttributes.length(); x++) {
				object = schemaAttributes.optJSONObject(x);
				if (nodename.equals(object.getString("referenceKey"))) {
					return object;
				}
			}
		}
		return object;
	}

	/**
	 * 
	 * Method to write the html response for the form action.
	 * 
	 * @param object
	 * @param parent
	 * @param servletContext
	 * @return
	 * @throws Exception
	 */
	public static String writeObject(JSONObject object, String parent, ServletContext servletContext, HttpServletRequest request) throws Exception {
		StringBuilder widgetHtml = new StringBuilder();
		logger.debug("object name/Parent = " + object.getString("name") + ":" + parent);
		if (!object.getBoolean("isNode")) {
			Map<String, Object> schema_input = new HashMap<String, Object>();
			if (logger.isDebugEnabled())
				logger.debug("Contribute Content Tag name = " + object.getString("name"));
			schema_input.put("mytitle", object.getString("name"));
			schema_input.put("isRequired", object.getBoolean("isRequired"));
			schema_input.put("recordId", object.getString("recordId"));
			schema_input.put("referenceKey", (parent == null ? "" : parent + "/") + object.getString("referenceKey"));
                        schema_input.put("SUBMITTED_BY", request.getSession().getAttribute("username"));
			if (object.getString("schemaAttrType").equals("WYSIWYG_EDIT")) {
				schema_input.put("uniqueid", BaseTag.getDefaultNumber(0, 1000000) + "");
				schema_input.put("textHeight", object.getString("textHeight"));
				schema_input.put("wysiwygType", object.getString("wysiwygType"));
				schema_input.put("lang_dir", "ltr");
				schema_input.put("lang", "en");
			}
			if (logger.isDebugEnabled())
				logger.debug("Loading ftl file " + "cc_" + object.getString("schemaAttrType") + ".ftl");
                        // HARDCODED FOR CLIENT
                        if (! "LEGACY_DOCUMENT_ID".equals(object.getString("referenceKey"))){
                            widgetHtml.append(Content.getTemplateHtml(schema_input, "cc_" + object.getString("schemaAttrType") + ".ftl", servletContext));
                        }
		} else {
			logger.debug("This is a node object, name = " + object.getString("name"));
			String collapseWidgetId = BaseTag.getWidgetInstanceId("nodeTitle");
			String collapseScreenId = BaseTag.getWidgetInstanceId("nodeTitle_screen");
			Map<String, Object> schema_input = new HashMap<String, Object>();
			schema_input.put("addingChildren", object.has("addingChildren"));
			schema_input.put("mytitle", object.getString("name"));
			schema_input.put("collapseWidgetId", collapseWidgetId);
			schema_input.put("collapseScreenId", collapseScreenId);
			String local_parent = (parent == null || object.has("addingChildren")) ? object.getString("referenceKey")
					: (parent + "/" + object.getString("referenceKey"));
			schema_input.put("parent_node", local_parent);
			widgetHtml.append(Content.getTemplateHtml(schema_input, "cc_NODE_beg.ftl", servletContext));

			JSONArray children = object.getJSONArray("children");
			if (logger.isDebugEnabled())
				logger.debug("writeObject children " + children.length() + " - " + children.toString());

			if (children.length() > 0) {
				for (int x = 0; x < children.length(); x++) {
					if (logger.isDebugEnabled())
						logger.debug("creating children for local_parent = " + local_parent);
					widgetHtml.append(Content.writeObject(children.optJSONObject(x), local_parent, servletContext, request));
				}
			}
			widgetHtml.append(Content.getTemplateHtml(schema_input, "cc_NODE_end.ftl", servletContext));
		}
		logger.debug("Returning HTML ............\n " + widgetHtml.toString());
		return widgetHtml.toString();
	}

	/**
	 * 
	 * Method to get Template html for response
	 * 
	 * @param input
	 *            - input object
	 * @param templateName
	 * @param servletContext
	 * @return
	 */
	public static String getTemplateHtml(Map<String, Object> input, String templateName,
			ServletContext servletContext) {
		String templateHtml = "";
		try {
			Configuration cfg = new Configuration();
			Template template;
			cfg.setServletContextForTemplateLoading(servletContext, "/widgets/templates/custom");
			cfg.setIncompatibleImprovements(new Version(2, 3, 20));
			cfg.setDefaultEncoding("UTF-8");
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			if (!templateName.endsWith(".ftl"))
				templateName = templateName.concat(".ftl");
			try {
				template = cfg.getTemplate(templateName);
			} catch (Exception ex) {
				logger.debug(
						"Could not find a copy of the file in the custom folder. Proceeding to check in the standard folder");
				cfg.setServletContextForTemplateLoading(servletContext, "/widgets/templates/standard");
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

	/**
	 * 
	 * Get method to retrieve an content record by answer id
	 * 
	 * @param answerid
	 *            - id of the document/answer for retrieval
	 * @param request
	 *            - HttpServletRequest
	 * @param response
	 *            - HttpServletResponse
	 * @return contentFullDataJSON - content record in JSON format
	 */
	public static JSONObject getContentByAnswerID(String answerid, HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject contentFullDataJSON = new JSONObject();
		try {
			String url = resourceBundle.getString("OKCS_IM_API_URL").concat("content/answers/" + answerid);
			String contentTypeFullData = OKAPIUtility.callRESTAPI(request, response, url, "GET", null);
			contentFullDataJSON = new JSONObject(contentTypeFullData);
			if (logger.isDebugEnabled())
				logger.debug("\nContent.getContentByAnswerID 1 " + contentFullDataJSON.toString() + "\n");
		} catch (Exception ex) {
			logger.error("Exception: While getting the content record by answer id ", ex.fillInStackTrace());
		}
		return contentFullDataJSON;
	}

}// end class
