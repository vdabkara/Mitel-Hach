<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--
 Copyright (c) 2015, Oracle Corporation and/or its affiliates. All rights reserved.

 The sample code in this document or accessed through this document is not certified or
 supported by Oracle. It is intended for educational or testing purposes only. Use of this
 sample code implies acceptance of the License Agreement
 (http://www.oracle.com/technetwork/licenses/standard-license-152015.html).

 File: default.jsp
 Abstract: Page used as landing page and used to render search results.
 Version: 1.0
--%>

<%@taglib prefix="OK" uri="/WEB-INF/OKCS.tld"%>

<html>
    <head><title>Knowledge Management</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="../resources/css/jquery-ui.css" /></head>
    <body>
        <div id="load"></div>		
        <OK:navigation show="false" />
        <div class="Hero">
            <div class="HeroInner">                 
                <div class="SearchControls">
                    <div class="SearchInput">
                        <OK:searchText/>
                    </div>
                    <OK:searchButton/>
                </div>
            </div>
        </div>
        <div id="basicModal"></div>
        <script src="../widgets/messages/message.js" ></script>
        <div id="basicMessageObj"></div>
        <script> 
            var $messagediv = $("#basicMessageObj").message({"divid":"basicMessage"}); 
            window.message = function (data) {
               $("#basicMessageObj").message("setMessage",data);
            }
        </script>        
        
        <div class="LinkedList">
            <OK:linkList />
        </div>
        <div class="ResultList">
            <div class="LeftContainer ResultPadding">
                <OK:facet />
            </div>
            <div class="RightContainer">
                <OK:searchResult hideWhenNoResults="false" showCaseLinks="true" showAddLink="true" showAddText="false"/>
            <OK:pagination/>
            </div>
        </div>
 
    </body>
</html>