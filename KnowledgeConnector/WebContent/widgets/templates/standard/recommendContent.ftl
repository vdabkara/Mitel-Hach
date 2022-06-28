<@compress single_line=true>
<link rel="stylesheet" href="../resources/css/jquery-ui.css" /></head>
<div id="${messageID}"></div>
<script> 
    var $messagediv = $("#${messageID}").message({"divid":"${messageID}"}); 
    window.message = function (data) {
       $("#${messageID}").message("setMessage",data);
    }
</script>
<div class="ContentBody"></div>
	<div id="${widgetId}" class="${recommendContentLabel}">
        <div id="${widgetId}_RecommendContentForm" class="">
	        <fieldset class="Fieldset">
				<iframe name="submit_frame" style="display:none;"></iframe>
	            <form id="${widgetId}_submit" name="${widgetId}_submit" method="post"  
                                        style="margin:0px;" 
                                        action="/KnowledgeConnector/recommendContent"
                                        target="submit_frame">
				<div id="${widgetId}_MsgLocation" aria-live="assertive"></div>
	            <div class="RecommendationHeader">${recommendContentLabel}</div>
	            <div>
	            	<span class="LabelInput">${contentTypeLabel}</span>
					<span class="required">*</span>
                        <div>
	                        <select id="${widgetId}_contentType" name="${widgetId}_contentType"  data-type='select' 
								data-name='${contentTypeLabel}' data-isrequired='true' class="ContentType">
	                               <option value="">${selectContentTypeLabel}</option>
	                           <#if contentTypes??> 
                    			  <#foreach contentType in contentTypes>
                   			          <option value="${contentType.recordId}" <#if contentTypeSelected??  && contentTypeSelected==contentType.recordId>selected</#if>>${contentType.name}</option>
                    			  </#foreach>
                    		   </#if>
	                        </select>
						</div>	
					<div>        
	            	<span class="LabelInput">${titleLabel}</span>
	            	<span class="required">*</span>
	            	<input type="text" 
	            		id='${widgetId}_title' 
        				name='${widgetId}_title'
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
									id='${widgetId}_description' 
									name='${widgetId}_description'
									data-type='textarea'
									data-isrequired='true' 
									data-name='${detailsLabel}'
							></textarea>
				        
							<div style="margin-top:10px;">
								<span class="LabelInput">${caseNumberLabel}</span>
								<input type="text" 
									value='${caseNumber}'
									id='${widgetId}_caseNumber' 
									name='${widgetId}_caseNumber'
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
												<select id="${widgetId}_priorityType" name="${widgetId}_priorityType"  data-type='select' 
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
	                <button id="${widgetId}_RecommendContentSubmit" class="SubmitButton">${submitButtonLabel}</button>
	            </div>
	            <div class="ButtonDiv">            
	                <button id="${widgetId}_RecommendContentCancel" class="CancelButton">${cancelButtonLabel}</button>
	            </div>
	            <div>
	                <span id="rn_<?= $this->instanceID ?>_StatusMessage" class="Hidden">${submittingMessageLabel}</span>
	            </div>
				</form>
	        </fieldset>
    </div>
	<script>
    $("#${widgetId}").recommendContent(
        {
            "pubSub": ps,
            "widgetId":"${widgetId}",
            "channelid":""
        }
    );
</script>
</@compress>
