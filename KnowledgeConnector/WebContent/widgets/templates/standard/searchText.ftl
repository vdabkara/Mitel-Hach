<#--
 Copyright (c) 2015, Oracle Corporation and/or its affiliates. All rights reserved.

 The sample code in this document or accessed through this document is not certified or
 supported by Oracle. It is intended for educational or testing purposes only. Use of this
 sample code implies acceptance of the License Agreement
 (http://www.oracle.com/technetwork/licenses/standard-license-152015.html).

 File: answerView.ftl
 Abstract: Template file to render Search Text Field
 Version: 1.0
 -->
<@compress single_line=true>
	<div id="${instanceID}">
		<div id="${instanceID}_searchInputDiv">
			<div id="${instanceID}_searchFieldDiv" class="SearchField">
				<label for="${instanceID}_searchInput">${inputLabel}</label>
				<#if searchText??>
					<input type="search" id="${instanceID}_SearchInput" placeholder="${placeHolderLabel}" maxlength="255" value="${searchText}">
				<#else>
					<input type="search" id="${instanceID}_SearchInput" placeholder="${placeHolderLabel}" maxlength="255" >
				</#if>
			</div>
		</div>
		<div id="${instanceID}_errorDiv" class="errorMsg"></div>
		<#if showRecommend??><div class="ContributeContent"><a href="recommendContent.jsp">${recommendNewArticleLabel}</a></div></#if>
		<#if showContribute??><div class="ContributeContent" style="padding-left:30px !important;"><a href="contributeContent.jsp">${contributeContentLabel}</a></div></#if>
	</div>
</@compress>