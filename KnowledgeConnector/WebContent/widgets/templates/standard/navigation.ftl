<@compress single_line=true>
    <div class="Navigation">
        <div class="NavInner">
            <input 
                class="backButton" 
                type="button" 
                onclick="window.location.replace('${prev_page}');"
                value="${nav_back_label}" 
                id="${widgetId}"
            />
        </div>
    </div>
<script>
    $("#${widgetId}").navigation(
        {
            "pubSub": ps,
            "prev_page": '${prev_page}'
        }
    );
</script>
</@compress>