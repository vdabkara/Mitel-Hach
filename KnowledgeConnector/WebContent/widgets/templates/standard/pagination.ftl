<#--
 Copyright (c) 2015, Oracle Corporation and/or its affiliates. All rights reserved.

 The sample code in this document or accessed through this document is not certified or
 supported by Oracle. It is intended for educational or testing purposes only. Use of this
 sample code implies acceptance of the License Agreement
 (http://www.oracle.com/technetwork/licenses/standard-license-152015.html).

 File: answerView.ftl
 Abstract: Template file to render Knowledge Article Details
 Version: 1.0
 -->
<@compress single_line=true>
<div id="${widgetId}" class = "PaginationLinks">
	<div id="${widgetId}_Content">
		<ul>
		    <#if (currentPage > 0) >
		        <li class="PreviousPage">
		            <a href="javascript:void(0)" data-rel="previous" oncontextmenu="return false;" rel="previous" id="previous_${widgetId}"><span id="previous">${labelPrevious}</span></a>
		        </li>
		    </#if>
		    <#if (pageMore != 0) >
		        <li class="NextPage"> 
		        	<a href="javascript:void(0)" data-rel="next" oncontextmenu="return false;" rel="next" id="next_${widgetId}"><span id="next">${labelNext}</span></a>
		        </li>
		    </#if>
		</ul>
	</div>
</div>
</@compress>