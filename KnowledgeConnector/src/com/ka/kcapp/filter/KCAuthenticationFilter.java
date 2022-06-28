package com.ka.kcapp.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.ResourceBundle;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.ka.kcapp.widget.apiUtility.OKAPIUtility;

/**
 *
 * A filter class to authenticate the incoming integration requests. Depending
 * on the source application,a corresponding system level user account is used
 * to authenticate/authorize the REST API calls for all operations within the
 * application ( Search, Article view, Case link/unlink, contribute content)
 *
 * @author SMarimuthu
 *
 */

public class KCAuthenticationFilter implements Filter {
	// logger
	private static Logger logger = Logger.getLogger(KCAuthenticationFilter.class);
	// application properties/resources file
	public static final ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.application");

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig fc) throws ServletException {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 * 
	 * Changes made in the Filter by Vishal - 
	 * for removing the parameters from URL - E.G. Source, CaseId, IncidentId, userId
	 * please un - comment the code to read values form doFilter and reset Function.
	 * 
	 * Also, visit to SearchTextTag.java and enable conditions (dependency of caseId form request)
	 * to show the Recommend Content & Author a New Article Buttons.
	 * currently it is explicitly set as true
	 * 
	 * DATE 23 JUNE 2018
	 * 
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc)
			throws IOException, ServletException {
		logger.debug("----Authenticating the request ------");
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		String caseId="";
		String userId=resourceBundle.getString("KCA_REMEDY_USERNAME");
		String source="REMEDY";
		/*
		 * set caseID & userID
		 */
//		if(null!=request.getParameter("caseID") && !request.getParameter("caseID").isEmpty())
//		{
//			httpServletRequest.getSession().setAttribute("caseID", request.getParameter("caseID"));
//		}
//		if(null!=request.getParameter("userID") && !request.getParameter("userID").isEmpty())
//		{
//			httpServletRequest.getSession().setAttribute("userID", request.getParameter("userID"));
//		}
		httpServletRequest.getSession().setAttribute("caseID", caseId);
		httpServletRequest.getSession().setAttribute("userID", userId);
		
		String appUser = null;
		String appUserPwd = null;
		String appAccountType=null;
		if (logger.isDebugEnabled())
			logger.debug(" source is " + request.getParameter("source") + " "
					+ (request.getParameter("source") != null && !request.getParameter("source").isEmpty()));
		
		
//		if (request.getParameter("source") != null && !request.getParameter("source").isEmpty()) {
//			httpServletRequest.getSession().setAttribute("requestSource", request.getParameter("source"));
//			httpServletRequest.getSession().setAttribute("source", request.getParameter("source"));
//		}
		
		
		httpServletRequest.getSession().setAttribute("requestSource", source);
		httpServletRequest.getSession().setAttribute("source", source);
			
		if (httpServletRequest.getSession().getAttribute(OKAPIUtility.USER_TOKEN) != null)
			logger.debug(
					"This is a valid(authenticated) user session with ID :" + httpServletRequest.getSession().getId());
		else {
			logger.info("Initial Search request,authenticating the user/request");
			logger.info("kw = " + request.getParameter("kw"));
//			logger.info("caseID = " + request.getParameter("caseID"));
			logger.info("caseID = " + caseId);
			
			
			String paramname = "";
			for (Enumeration<String> paramnames = request.getParameterNames(); paramnames.hasMoreElements();) {
				paramname = paramnames.nextElement();
				if (paramname.equalsIgnoreCase("username")) {
					httpServletRequest.getSession().setAttribute("username", request.getParameter(paramname));
				}
			}
			// identify the source/calling application
			/*
			if (request.getParameter("source") != null
					&& request.getParameter("source").equalsIgnoreCase(resourceBundle.getString("REMEDY_APP_NAME"))) {
				logger.info("Calling application source = " + request.getParameter("source"));
				appUser = resourceBundle.getString("KCA_REMEDY_USERNAME");
				appUserPwd = resourceBundle.getString("KCA_REMEDY_PASSWORD");
				appAccountType = resourceBundle.getString("KCA_REMEDY_ACCOUNT_TYPE");

			} else if (request.getParameter("source") != null
					&& request.getParameter("source").equalsIgnoreCase(resourceBundle.getString("SBM_APP_NAME"))) {
				logger.info("Calling application source = " + request.getParameter("source"));
				appUser = resourceBundle.getString("KCA_SBM_USERNAME");
				appUserPwd = resourceBundle.getString("KCA_SBM_PASSWORD");
				appAccountType = resourceBundle.getString("KCA_SBM_ACCOUNT_TYPE");
			} else {
				// Generate an exception
				throw new ServletException("ERROR:Invalid Request - Missing Source");
			}
			*/
			
			appUser = resourceBundle.getString("KCA_REMEDY_USERNAME");
			appUserPwd = resourceBundle.getString("KCA_REMEDY_PASSWORD");
			appAccountType = resourceBundle.getString("KCA_REMEDY_ACCOUNT_TYPE");

			response.setContentType("application/json;");
			response.setCharacterEncoding("UTF-8");
			String apiUrl = "";
			try {
				// get user token as app user
				String postData = "userName=" + appUser + "&password=" + appUserPwd
						+ "&userExternalType="+appAccountType+"&siteName=" + resourceBundle.getString("SITE_NAME");
				apiUrl = resourceBundle.getString("OKCS_IM_API_URL") + "auth/authorize";

				if (!apiUrl.isEmpty()) {
					String responseString = OKAPIUtility.authenticate(httpServletRequest, httpServletResponse,
							postData);
					JSONObject userObject = new JSONObject(responseString);
					JSONObject userInfo = (JSONObject) userObject.get("user");
					String userToken = new JSONObject(userObject.getString("authenticationToken"))
							.getString("userToken");
					String userLocale = new JSONObject(userInfo.getString("defaultLocale")).getString("recordId");
					if (userToken != null) {
						resetSessionAuthTokens(httpServletRequest, appUser, userToken, userLocale);
					} else {
						logger.error("An Error occurred while authenticating user = " + appUser);
						throw new ServletException("ERROR:Internal Application Error");
					}
				}
			} catch (JSONException ex) {
				logger.error("Error in KCAuthenticationFilter.doFilter: ", ex);
			}
		}
		logger.debug("----Finished request Authentication----------");
		fc.doFilter(httpServletRequest, httpServletResponse);
	}

	/**
	 * Method to reset the Session Authentication tokens
	 * 
	 * @param request
	 *            Request Object used to get the authentication parameters
	 * @param userName
	 *            Username of the user authenticated
	 * @param userToken
	 *            User Authentication Token returned by the API on successful
	 *            authentication
	 */
	public void resetSessionAuthTokens(HttpServletRequest request, String userName, String userToken,
			String userLocale) {
		HttpSession session = request.getSession(true);
		session.setAttribute("userToken", userToken);
		session.setAttribute("userName", userName);
		session.setAttribute("userLocale", userLocale);
//		session.setAttribute("userID", request.getParameter("userID"));
		session.setAttribute("userID", resourceBundle.getString("KCA_REMEDY_USERNAME"));
		// input data
//		session.setAttribute("source", request.getParameter("source"));
		session.setAttribute("source", "REMEDY");
		session.setAttribute("kw", request.getParameter("kw"));
//		session.setAttribute("incidentID", request.getParameter("caseID"));
//		session.setAttribute("caseID", request.getParameter("caseID"));
		session.setAttribute("incidentID", "");
		session.setAttribute("caseID", "");
		session.setAttribute(OKAPIUtility.AUTH_TOKEN, null);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
	}

}
