<#--
 Copyright (c) 2015, Oracle Corporation and/or its affiliates. All rights reserved.

 The sample code in this document or accessed through this document is not certified or
 supported by Oracle. It is intended for educational or testing purposes only. Use of this
 sample code implies acceptance of the License Agreement
 (http://www.oracle.com/technetwork/licenses/standard-license-152015.html).

 File: addText.ftl
 Abstract: Template file to format IM Article Text when it is being added to an incident context.
 Version: 1.0
-->
<div class="AnswerView">
	<div class="AnswerTitle" id="AnswerTitle">
		 <div class="AnswerDetail AnswerHeader"><h1 id="Summary">${title}</h1></div>
	</div>
	<div class="AnswerStatus" id="AnswerStatus">
		<div class="AnswerDetail AnswerHeader">
			<div class="AnswerInfo">
				<div class="AnswerField" id="AnswerField_29">
					<section>
						<span class="Info Bold">${documentIdLabel}:</span>
						<span>${documentId}</span>
					</section>
				</div>
				<div class="AnswerField" id="AnswerField">
					<section>
						<span class="Info Bold">${versionLabel}:</span>
						<span>${version}</span>
					</section>
				</div>
				<div class="AnswerField" id="AnswerField_31">
					<section>
						<span class="Info Bold">${statusLabel}:</span>
						<span>${status}</span>
					</section>
				</div>
				<div class="AnswerField" id="AnswerField_32">
					<section>
						<span class="Info Bold">${publishedDateLabel}:</span>
						<span>${publishedDate}</span>
					</section>
				</div>
			</div>
		</div>
	</div>
	
	<div style=\"border-bottom: 1px dashed #CCCCCC;margin-bottom: 10px;width: 100%;clear: both;\">&nbsp; </div>
	
	<div class="SectionTitle"></div>
	<div class="AnswerContent" id="AnswerContent">
		<div id="content">
		<#list SchemaAttributeList as schemaAttribute>
			<div class="SchemaAttribute" style="margin-left:${schemaAttribute.depth}px">
				${schemaAttribute.title}</br>
			</div>
			<#if schemaAttribute.value != ''>
				<div class="SchemaAttributeValue" style="margin-left:${schemaAttribute.depth}px">
					${schemaAttribute.value}
				</div>
			</#if>
		</#list>
		</div>
	</div>
</div>