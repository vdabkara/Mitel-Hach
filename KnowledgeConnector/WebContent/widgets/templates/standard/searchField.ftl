<#--
 Copyright (c) 2015, Oracle Corporation and/or its affiliates. All rights reserved.

 The sample code in this document or accessed through this document is not certified or
 supported by Oracle. It is intended for educational or testing purposes only. Use of this
 sample code implies acceptance of the License Agreement
 (http://www.oracle.com/technetwork/licenses/standard-license-152015.html).

 File: searchField.ftl
 Abstract: Template file to render Search Text Field
 Version: 1.0
 -->
<@compress single_line=true>
<div id="${instanceID}_mainDiv" class="rn_Hero">
	<div id="${instanceID}_innerDiv" class="rn_HeroInner">
		<div id="${instanceID}_searchControlDiv" class="rn_SearchControls">
		<h1 class="rn_ScreenReaderOnly">Search</h1>
		<form id="${instanceID}_form">
			<div id="${instanceID}_searchInputDiv" class="rn_SearchInput">
				<div id="${instanceID}_searchFieldDiv" class="rn_SearchField">
					<label for="${instanceID}_searchInput">${inputLabel}</label>
					<input type="search" id="${instanceID}_SearchInput" placeholder="${placeHolderLabel}" maxlength="255">
				</div>
			</div>
			<div id="${instanceID}_searchBttnDiv" class="rn_SearchButton">
				<button type="button" id="${instanceID}_searchButton" name="kw" class="rn_SubmitButton">
					<span class="rn_ButtonText">Search</span>
				</button>
			</div>
		</form>
	</div>
</div>
</@compress>