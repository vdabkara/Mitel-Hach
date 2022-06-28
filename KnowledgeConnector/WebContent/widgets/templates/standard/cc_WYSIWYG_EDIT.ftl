<@compress single_line=true>
    <!--div -->
    <span class="LabelInput">${mytitle}</span>
    <#if isRequired><span class="required">*</span></#if>
        <textarea 
                cols="100"
                rows="15"        
                id='WYSIWYGID${recordId}_${uniqueid}' 
                name='ccfield_${referenceKey}'
                data-isrequired='<#if isRequired>true<#else>false</#if>' 
                data-recordId='${recordId}'
                data-referenceKey='${referenceKey}' 
                data-name='${mytitle}'
                data-type='editor'
                style='height:${textHeight}px' 
        ></textarea>
        <script language="JavaScript" type="text/javascript">
                CKEDITOR.replace(
                        'WYSIWYGID${recordId}_${uniqueid}',
                        {
                                basePath : '../resources/application/components/ckeditor/',
                                filebrowserImageBrowseUrl : '../resources/application/components/ckfinder/ckfinder.html?type=TENANT',
                                filebrowserWindowHeight : '${textHeight}',
                                toolbar : '<#if wysiwygType?? && wysiwygType=="FULL">Default<#else>${wysiwygType}</#if>',
                                contentsLangDirection : '${lang_dir}', 
                                allowedContent : true, 
                                defaultLanguage : '${lang}',
                                language : '${lang}',
                                filebrowserWindowWidth : '75%', 
                                skin : 'kama' 
                        }
                );
        </script>
    <!-- div-->
</@compress>