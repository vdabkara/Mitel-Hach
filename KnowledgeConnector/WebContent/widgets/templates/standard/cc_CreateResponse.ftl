<@compress single_line=true>
<script src="resources/js/jquery-1.10.2.js"></script>
<script>

    $(function() {
        parent.message(<#if response??>${response}</#if>); 
    });
    
</script>
</@compress>