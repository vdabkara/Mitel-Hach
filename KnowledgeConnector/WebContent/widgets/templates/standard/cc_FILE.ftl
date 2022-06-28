<@compress single_line=true>
    <input 
        class="normal submitbutton" 
        type="file" 
        id='${recordId}' 
        data-referenceKey='${referenceKey}'
        data-name='${mytitle}'
        name='ccfield_${referenceKey}'
        data-isrequired='<#if isRequired>true<#else>false</#if>'
        data-type='file'
    /><BR>
</@compress>