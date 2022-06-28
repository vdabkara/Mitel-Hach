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
	<link  type="text/css" href="${resourcePath}/css/base.css" rel="stylesheet" />
</@compress>