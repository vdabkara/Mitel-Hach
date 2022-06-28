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
<link rel="stylesheet" href="../resources/css/jquery-ui.css" /></head>
<div id="${messageID}" name="messages"></div>
<script> 
    var $messagediv = $("#${messageID}").message({"divid":"${messageID}"}); 
    window.message = function (data) {
       $("#${messageID}").message("setMessage",data);
    }
</script>
<div id="${widgetID}">
	<head>
		<title>${title}</title>
	</head>
	<#if hideHeaderHtml == 'false'>
		<div class="headerDiv">
		<#if isHighlighted == 'true'>
			<span style="margin-left:30%;text-align: center;">
				You are viewing a highlighted version of your search result.
			</span>
			<span style="float:right;margin-right:5px;">
		         <a class="leftAnchor" href="javascript:void(0);" onclick="window.prompt('Copy the link using Ctrl+C', '${attachmentUrl}')">Copy Sharable Link</a>|<a class="rightAnchor" href="javascript:void(0);" onclick="window.open('${attachmentUrl}');">View Without Highlighting</a>
		    </span>
		<#elseif viewableAttachment == 'true'>
			<span style="float:right;margin-right:5px;margin-top:5px;">
				<a class="rightAnchor" href="javascript:void(0);" onclick="window.prompt('Copy the URL using Ctrl+C', '${attachmentUrl}')">Copy Sharable Link</a>
		    </span>
		</#if>
                
		<#if showAddLink && false>
			<#if answerID??>
				<button data-id="${answerID}" class="addExternalLink"></button>
			<#else>
				<button data-href="${attachmentUrl}" class="addExternalLink"></button>
			</#if>
		</#if>
		</div>
	</#if>
	<#if fileType??>
		<div class="containerDiv">
			<div class="childDiv">
			<#if fileType == "HighlightedHTML">
				<div class="HTML"><frame>${httpPassThrough}</frame></div>
			<#else>
				<div class="iframeDiv"><iframe src="${attachmentUrl}" width="100%" height="100%" /></div>
			</#if>
			</div>
		</div>
	<#else>
		<#if showCaseLinks>
			<#if answerLinkStatus>
				<div class="linkInfo">
					<div class="linkedText">
						This article is linked to this incident
					</div>
					<#if showAddText>
						<button data-id="${answerID}" class="addText"></button>
					</#if>
					<#if showAddLink >
						<button data-id="${answerID}" data-documentid="${documentId}" class="removeLink"></button>
					</#if>
				</div>
			<#else>
				<div class="linkButtons">
					<#if showAddText>
						<button data-id="${answerID}" class="addText"></button>
					</#if>
					<#if showAddLink >
						<button data-id="${answerID}" data-docid="${documentId}" class="addLink"></button>
					</#if>
				</div>
			</#if>
		</#if>
		<div class="AnswerView">
					<div class="ButtonDiv">
	                	<button id="${widgetID}_RecommendChange" class="SubmitButton" name="showRecommendChangeButton">${recommendChangeLabel}</button>
	            	</div>
	            	<div id="inputBlock" style="display:none;">
	            		<div class="ContentBody"></div>
							<div id="${widgetID}" class="${recommendChangeLabel}">
						        <div id="${widgetID}_RecommendChangeForm" class="">
							        <fieldset class="Fieldset">
										<iframe name="submit_frame" style="display:none;"></iframe>
							            <form id="${widgetID}_submit" name="${widgetID}_submit" method="post"  
						                                        style="margin:0px;" 
						                                        action="/KnowledgeConnector/recommendChange"
						                                        target="submit_frame">
						                <input type="hidden" id='${widgetID}_answerId' name='${widgetID}_answerId' value='${answerId}' />
						                <input type="hidden" id='${widgetID}_caseIdFromParam' name='${widgetID}_caseIdFromParam' value='${caseIdFromParam}' />                        
										<div id="${widgetID}_MsgLocation" aria-live="assertive"></div>
							            <div class="RecommendationHeader">${recommendChangeLabel}</div>
							            <div>
							            	<span class="LabelInput">${titleLabel}</span>
							            	<span class="required">*</span>
							            	<input type="text" 
							            		id='${widgetID}_title' 
						        				name='${widgetID}_title'
						        				data-isrequired='true'
										        data-type='input'
										        data-name='${titleLabel}'
										        width="200px"
										        size="100%"
							            		/>
							            		
												<div>
													<span class="LabelInput">${detailsLabel}</span>
													<span class="required">*</span>
													 <textarea 
															cols="100"
															rows="10"        
															id='${widgetID}_description' 
															name='${widgetID}_description'
															data-type='textarea'
															data-isrequired='true' 
															data-name='${detailsLabel}'
													></textarea>
										        
													<div style="margin-top:10px;">
														<span class="LabelInput">${caseNumberLabel}</span>
														<input type="text" 
															value='${caseNumber}'
															id='${widgetID}_caseNumber' 
															name='${widgetID}_caseNumber'
															data-isrequired='false'
															data-type='input'
															data-name='${caseNumberLabel}'
															width="200px"
															size="100%"
															/>
															
															<div>
																<span class="LabelInput">${priorityLabel}</span>
																<span class="required">*</span>
																	<div>
																		<select id="${widgetID}_priorityType" name="${widgetID}_priorityType"  data-type='select' 
																			data-name='${priorityLabel}' data-isrequired='true' class="ContentType">
																			   <option value="">${selectPriorityLabel}</option>
																		   <#if priorityTypes??> 
																			  <#foreach priorityType in priorityTypes>
																				  <option value="${priorityType.value}" <#if prioritySelected??  && prioritySelected==priorityType.value>selected</#if>>${priorityType.label}</option>
																			  </#foreach>
																		   </#if>
																		</select>
																	</div>
															</div>
														</div>
													</div>	
												</div>
											</div>
							            <div class="ButtonDiv">
							                <button id="${widgetID}_RecommendChangeSubmit" class="SubmitButton" name="recommendChangeSubmitButton">${submitButtonLabel}</button>
							            </div>
							            <div class="ButtonDiv">            
							                <button id="${widgetID}_RecommendChangeCancel" class="CancelButton">${cancelButtonLabel}</button>
							            </div>
							            <div>
							                <span id="rn_<?= $this->instanceID ?>_StatusMessage" class="Hidden">${submittingMessageLabel}</span>
							            </div>
										</form>
							        </fieldset>
						    </div>
	            	</div>
	            	
		            <div class="AnswerTitle" id="AnswerTitle">
		                 <div class="AnswerDetail AnswerHeader"><h1 id="Summary">${title}</h1></div>
		        	</div>
		            <div class="AnswerStatus" id="AnswerStatus">
		                <div class="AnswerDetail AnswerHeader">
		                	<div class="AnswerInfo">
		                   		<div class="AnswerField" id="AnswerField_29">
		            				<section><#if documentIdLabel??>
		            					<span class="Info Bold">${documentIdLabel}:</span></#if>
		            					<#if documentId??>
					    				<span>${documentId}</span>
					    				</#if>
									</section>
								</div>
					 			<div class="AnswerField" id="AnswerField">
					            	<section><#if versionLabel??>
					   					<span class="Info Bold">${versionLabel}:</span></#if>
										<#if version??>
					    				<span>${version}</span></#if>
									</section>
								</div>
					            <div class="AnswerField" id="AnswerField_31">
					            	<section><#if statusLabel??>
					   					<span class="Info Bold">${statusLabel}:</span></#if>
										<#if status??>
					    				<span>${status}</span></#if>
									</section>
								</div>
					            <div class="AnswerField" id="AnswerField_32">
					            	<section><#if publishedDateLabel??>
					     				<span class="Info Bold">${publishedDateLabel}:</span></#if><#if publishedDate??>
					    				<span>${publishedDate}</span></#if>
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
		    	</div
			</div>	
		</div>
	</#if>
</div>
</@compress>