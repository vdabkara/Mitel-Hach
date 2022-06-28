<@compress single_line=true>
                                        </form>
                                        </div>
		                    </div>
	                </section>
	            <div id=contentSchema><section></section></div>
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
<script>
    $("#${widgetId}").contributeContent(
        {
            "pubSub": ps,
            "widgetId":"${widgetId}",
            "channelid":"${selected_channelid}"
        }
    );
</script>
</@compress>