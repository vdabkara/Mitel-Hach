<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--
 Copyright (c) 2015 Oracle and/or its affiliates. All rights reserved.

 The sample code in this document or accessed through this document is not certified or
 supported by Oracle. It is intended for educational or testing purposes only. Use of this
 sample code implies acceptance of the License Agreement
 (http://www.oracle.com/technetwork/licenses/standard-license-152015.html).

 File: contributeContent.jsp
 Abstract: Page to display all internal IM articles
 Version: 1.0
--%>

<%@taglib prefix="OK" uri="/WEB-INF/OKCS.tld"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	</head>
	<body>
            <div id="load"></div>
            <OK:navigation show="true" refreshForCurrent="false"/>		
		<div class="AnswerList">
			<div class="ResultList">
                            <div class="AnswerAction">
                                 <OK:contributeContent/>
		            </div>
			</div>
		</div>
	</body>
</html>

