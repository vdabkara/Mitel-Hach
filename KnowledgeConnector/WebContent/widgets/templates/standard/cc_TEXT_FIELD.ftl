<@compress single_line=true>
    <#if referenceKey!="SUBMITTED_BY">
        <div>
        <span class="LabelInput">${mytitle}</span>
        <#if isRequired ><span class="required">*</span></#if>
    </#if>
    <input
        <#if SUBMITTED_BY?? && referenceKey=="SUBMITTED_BY">
        value='${SUBMITTED_BY}'
        </#if>
        <#if referenceKey!="SUBMITTED_BY">
            type='input'
        <#else>
            type='hidden'        
        </#if>
        id='${recordId}' 
        name='ccfield_${referenceKey}'
        data-isrequired='<#if isRequired>true<#else>false</#if>'
        data-recordId='${recordId}'
        data-type='input'
        data-referenceKey='${referenceKey}'
        data-name='${mytitle}'
        width="200px"
        size="100%"
    />
    <#if referenceKey!="SUBMITTED_BY">
        <div>
    </#if>        
</@compress>