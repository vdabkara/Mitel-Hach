<#--
 Copyright (c) 2015, Oracle Corporation and/or its affiliates. All rights reserved.

 The sample code in this document or accessed through this document is not certified or
 supported by Oracle. It is intended for educational or testing purposes only. Use of this
 sample code implies acceptance of the License Agreement
 (http://www.oracle.com/technetwork/licenses/standard-license-152015.html).

 File: standard.ftl
 Abstract: Template file to render HTML common to all pages
 Version: 1.0
 -->
<@compress single_line=true>
	<script src="${resourcePath}/js/jquery-1.10.2.js"></script>
	<script src="${resourcePath}/js/jquery-ui.js"></script>
	<script src="${resourcePath}/js/common.js"></script>
	<script src="${resourcePath}/js/pubSub.js"></script>
	<script src="${resourcePath}/js/underscore-min.js"></script>
	<#if isTest??>
		<script src="${resourcePath}/js/qunit-1.14.0.js"></script>
	</#if>
	<link  type="text/css" href="${resourcePath}/css/font-awesome.min.css" rel="stylesheet" media="screen"/>
	<style type='text/css'>
        .ok-highlight-title {background-color: #FF0;font-weight: bold;}
        .ok-highlight-sentence {background-color: #D6DAE0;}
        iframe {min-width: 100%; border-top-width: 0; border-left-width: 0; border-style: ridge; width: 100%; height: 100%;}
        .mainDiv {width: 100%;height: 100%;overflow: hidden;}
        .headerDiv {width: 100%;height: 5%;position: fixed;background-color: #40526b;top: 0px;min-height: 50px;color: white;font-size: 1em;line-height: 1.5em;font-weight: bold;padding: 10px 0 0px 15px;min-height: 50px;text-align: left;overflow: hidden;left: 0;}
        .containerDiv {width: 100%;height: 90%;margin-top: 3.3em;overflow: hidden;position: fixed;left: 0;}
        .childDiv {width: 100%;height: 100%;overflow: auto;}
        .iframeDiv {height: 95%;}
        .leftAnchor {padding: 0 10px;color: white;}
        .rightAnchor {padding-left: 10px;color: white; margin-right: 15px;}
        .addExternalLink::after {content: "\f0c1";font-family: FontAwesome;}
		.addExternalLink {background: #3873c4 none repeat scroll 0 0;border: 0 none;border-radius: 0.1875em;color: #fff;cursor: pointer;float: left;font-size: 1em;font-weight: 800;height: 80%;letter-spacing: 1.1px;margin-left: 0.5em;transition: all 0.2s ease-in-out 0s;width: 3em;}
    </style>
</@compress>