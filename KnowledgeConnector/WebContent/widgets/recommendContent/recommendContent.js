
(function($) {
    $.widget( "okcs.recommendContent", {
        // default widget options
        options: {
            pubSub: '',
            widgetId: '',
            // callbacks
            _onClick: null
        },        
        // widget constructor
        _create: function() {
            $(this.element).on("click", '.SubmitButton', this, this._onRecommendContentSubmission);
            $(this.element).on("click", '.CancelButton', this, this._onCancelButtonClick);
        },
        /** 
        * This method fires collects filters and requests for search through 'collect' and 'search' events. 
        * @param {object} event click event
        */
        _onCancelButtonClick: function(evt) {
            var widget = evt.data, event = evt.data.options.pubSub;
            evt.preventDefault();
            widget._selectedFacets = '';
            event.addFilter({key : 'type', value : 'search'});        
            $(this).queue("eventQueue", null, event.fire('nav_back'));
        
//            var widgetId = '#' + evt.data.options.widgetId;
//            $(widgetId + "_RecommendContentButton").removeClass('Hidden');
//            $(widgetId + "_RecommendForm").addClass('Hidden');
        },
        /** 
        * This method fires collects filters and requests for search through 'collect' and 'search' events. 
        * @param {object} event click event
        */
        _onRecommendContentSubmission: function(evt) {
			document.getElementById('load').style.display="block";
            document.body.style.cursor = 'wait';            	
            
            var input_form =$('#'+evt.data.options.widgetId).find('form'); 
            var inputFlds = input_form.find(":input , textarea , select");
            var response = isRequiredFieldsFilled(inputFlds);
			if (response.success==true){
				input_form.submit();
            }else{
                window.message(response);
            }
        }
    });
    
    function isRequiredFieldsFilled(inputFlds){
        var response = {"success":true, "message":""};
        for (x =0 ; x < inputFlds.length ; x++){
             if ($(inputFlds[x]).attr("data-isrequired") && $(inputFlds[x]).attr("data-isrequired")=='true'){
                if ($(inputFlds[x]).attr("data-type")=='input' && $(inputFlds[x]).val()==''){
                    response.success=false;
                    response.message+=(response.message.length>0 ?", ":"");
                    response.message+=$(inputFlds[x]).attr("data-name");
                }
				if ($(inputFlds[x]).attr("data-type")=='textarea' && $(inputFlds[x]).val()==''){
                    response.success=false;
                    response.message+=(response.message.length>0 ?", ":"");
                    response.message+=$(inputFlds[x]).attr("data-name");
                }
				if ($(inputFlds[x]).attr("data-type")=='select' && $(inputFlds[x]).val()==''){
					response.success=false;
                    response.message+=(response.message.length>0 ?", ":"");
                    response.message+=$(inputFlds[x]).attr("data-name");
                }
                if ($(inputFlds[x]).attr("data-type")=='editor' && CKEDITOR.instances[$(inputFlds[x]).attr("id")].getData()==''){
                    response.success=false;
                    response.message+=(response.message.length>0 ?", ":"");
                    response.message+=$(inputFlds[x]).attr("data-name");
                }
            }
        }
        if(response.message.length > 0)
        	response.message = "Please complete required field(s) : " + response.message;
        return response;
    }
    
})(jQuery);
