<@compress single_line=true>
            </div>
            <#if addingChildren>
                <button
                    id="deleteNode_${collapseScreenId}"
                    type="button" 
                    class="deletebutton" 
                    data-parentnode="${parent_node}"
                    data-childdiv="nodeBody_${collapseScreenId}"
                    data-screen="${collapseScreenId}"
                >Delete ${mytitle}</button>
                <div id="basicDialog" title="Basic dialog"></div>
                <script>$("#deleteNode_${collapseScreenId}").delete({ "div_id": "${collapseScreenId}"}); </script>
            </#if>
        </div>
        <#if !addingChildren>
            <button 
                type="button" 
                class="nodebutton" 
                data-parentnode="${parent_node}"
                data-childdiv="${collapseScreenId}"
            >Add ${mytitle}</button>        
            <script>$("#${collapseWidgetId}").collapse({ "div_id": "${collapseWidgetId}"}); </script>
        </#if>
</@compress>