<@compress single_line=true>
<script type="text/javascript" src="../resources/application/components/ckeditor/ckeditor.js"></script>
<div class="ContentBody"></div>
	<div id="${widgetId}" class="${contributeContentLabel}">
        <div id="${widgetId}_ContributeContentForm" class="">
	        <fieldset class="Fieldset">
	            <div id="${widgetId}_MsgLocation" aria-live="assertive"></div>
	            <div class="RecommendationHeader">${contributeContentLabel}</div>
	            <#assign contentTypeClass = 'hidden'>
	                <section class="${contentTypeClass}">
	                    <span class="LabelInput">${contentTypeLabel}</span>
	                    <span class="required">*</span>
		                    <div>
		                        <select id="${widgetId}_ContentType" class="ContentType">
		                               <option value="select">Select</option>
		                           <#if contentTypes??> 
                        			  <#foreach contentType in contentTypes>
                       			          <option value="${contentType.recordId}-${contentType.referenceKey}-${contentType.name}">${contentType.name}</option>
                        			  </#foreach>
                        		   </#if>
		                        </select>
                                        <div class="ContentBody" id="${widgetId}_ContentBody">test</div>
		                    </div>
	                </section>
	           
	            <div class="ButtonDiv">
	                <button id="${widgetId}_ContributeContentSubmit" class="SubmitButton">${submitButtonLabel}</button>
	            </div>
	            <div class="ButtonDiv">            
	                <button id="${widgetId}_ContributeContentCancel" class="CancelButton">${cancelButtonLabel}</button>
	            </div>
	            <div>
	                <span id="rn_<?= $this->instanceID ?>_StatusMessage" class="Hidden">${submittingMessageLabel}</span>
	            </div>
	        </fieldset>
    </div>
</@compress>
