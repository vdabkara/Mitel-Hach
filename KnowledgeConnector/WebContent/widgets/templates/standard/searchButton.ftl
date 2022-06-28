<#--
 Copyright (c) 2015, Oracle Corporation and/or its affiliates. All rights reserved.

 The sample code in this document or accessed through this document is not certified or
 supported by Oracle. It is intended for educational or testing purposes only. Use of this
 sample code implies acceptance of the License Agreement
 (http://www.oracle.com/technetwork/licenses/standard-license-152015.html).

 File: searchButton.ftl
 Abstract: Template file to render Search Button
 Version: 1.0
 -->
<@compress single_line=true>
	<div id="${widgetId}">
		<div id="${widgetId}_searchBttnDiv" class="SearchButton">
		    <button type="button" class="SubmitButton" id="${widgetId}_searchButton" name="kw" >
		        <span class="ButtonText">${buttonLabel}</span>
		    </button>
		</div>
	</div>
</@compress>