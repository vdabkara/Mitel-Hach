<@compress single_line=true>
<script type="text/javascript" src="../resources/application/components/ckeditor/ckeditor.js"></script>
<link rel="stylesheet" href="../resources/css/jquery-ui.css" /></head>
<div id="${messageID}"></div>
<script> 
    var $messagediv = $("#${messageID}").message({"divid":"${messageID}"}); 
    window.message = function (data) {
       $("#${messageID}").message("setMessage",data);
    }
</script>
<div class="ContentBody"></div>
	<div id="${widgetId}" class="${contributeContentLabel}">
    	<div id="${widgetId}_ContributeContentForm" class="">
	    	<fieldset class="Fieldset">
	        	<div id="${widgetId}_MsgLocation" aria-live="assertive"></div>
	            <div class="RecommendationHeader">${contributeContentLabel}</div>
	                <span class="LabelInput">${contentTypeLabel}</span>
	                <span class="required">*</span>
		            	<div>
		                	<select id="${widgetId}_ContentType" class="ContentType">
		                    	<option value="select">Select</option>
		                        <#if contentTypes??> 
                        			<#foreach contentType in contentTypes>
                       			    	<option value="${contentType.recordId}"
                                        	<#if selected_channelid??  && selected_channelid==contentType.recordId>selected</#if>
                                        >${contentType.name}</option>                                                  
                        			  </#foreach>
                        		   </#if>
		                        </select>
                                <div class="ContentBody" id="${widgetId}_ContentBody">
                                <iframe name="submit_frame" style="display:none;"></iframe>
                                	<form 
                                    	name="${widgetId}_submit"
                                        id="${widgetId}_submit"
                                        enctype="multipart/form-data" 
                                        method="post"  
                                        style="margin:0px;" 
                                        action="/KnowledgeConnector/contentService"
                                        target="submit_frame"
                                    >
</@compress>