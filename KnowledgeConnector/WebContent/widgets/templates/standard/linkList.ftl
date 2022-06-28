<@compress single_line=true>
<div class="LinkedPanel" id="${widgetId}">
    <div class="LinkedListTitle">Linked Answers for ${incidentID}<button class="collapsePanel"  id="${collapseWidgetId}" data-screen="${collapseScreenId}"></button></div>
    <table  id="${collapseScreenId}"><tbody>
        <#if linkedItems??>
            <#foreach value in linkedItems>
                <#include "linkList_item.ftl" parse=true>
            </#foreach>
        </#if>
    </tbody></table>
    
</div>
</@compress>