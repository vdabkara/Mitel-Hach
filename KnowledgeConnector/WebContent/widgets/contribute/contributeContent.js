


(function($) {
    $.widget( "okcs.contributeContent", {
        // default widget options
        options: {
            contentData: '',
            pubSub: '',
            widgetId: '',
            channelid:'',
            recommendationTitle: '',
            // callbacks
            _onClick: null
        },        
        // widget constructor
        _create: function() {
            $(this.element).on("click", '.SubmitButton', this, this._onContributeContentSubmission);
            $(this.element).on("click", '.CancelButton', this, this._onCancelButtonClick);
            $(this.element).on("change", '.ContentType', this, this._onChange);
            $(this.element).on("click", '.RecommendContentButton', this, this._onClick);
            $(this.element).on("click", '.nodebutton', this, this._addNode);
        },
        _addNode: function(evt) {
           // window.alert($(evt.target).attr("data-parentnode")+" "+evt.data.options.channelid);
            var urlParameters = $(location).attr('pathname').replace(/^\//, " ").split('/');
            var ajaxRequestUrl = '/' + urlParameters[0].trim() + '/contentRequest',
            postData = {
                action : "addNode", 
                channelid : evt.data.options.channelid, 
                nodename : $(evt.target).attr("data-parentnode")
            };            
            $.ajax({
                url: ajaxRequestUrl,
                type: "POST",
                contentType: "application/json; charset=UTF-8",
                data: JSON.stringify(postData),
                success: function(response){
                    var local_result = JSON.parse(response);
                    if (!local_result.error) {
                        var childdiv = "div[name='"+$(evt.target).attr("data-parentnode")+"']";
                        var nodeparent = $(childdiv);
                        //alert(nodeparent);
                        $(nodeparent[nodeparent.length-1]).after(local_result.html);
                        //$(childdiv).after(local_result.html);
                    }
                },
                error:function(error){
                    console.log(error);
                    return;
                }
            });            
        },
	  
        /** 
        * This method fires collects filters and requests for search through 'collect' and 'search' events. 
        * @param {object} event click event
        */
        _onClick: function(evt) {
            var widgetId = '#' + evt.data.options.widgetId;
            $(widgetId + "_Title").val(evt.data.options.recommendationTitle);
            $(widgetId + "_Description").val('');
            $(widgetId + "_CaseNumber").val('');
            $(widgetId + "_MsgLocation").removeClass('required');
            $(widgetId + "_MsgLocation").html('');
            $(widgetId + "_RecommendContentButton").addClass('Hidden');
            $(widgetId + "_RecommendForm").removeClass('Hidden');
        },
        /** 
        * This method fires collects filters and requests for search through 'collect' and 'search' events. 
        * @param {object} event click event
        */
        _onChange: function(evt) {
            var widgetId = '#' + evt.data.options.widgetId;
            var newurl = $(location).attr('pathname');
            if (evt.target.value!='select'){
                newurl+=(newurl.indexOf("?")!=-1?"&":"?");
                newurl+="channelid=";
                newurl+=evt.target.value;
            }
            location.replace(newurl);
            if (true) return;
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
        _onContributeContentSubmission: function(evt) {
            document.getElementById('load').style.display="block";
            document.body.style.cursor = 'wait';            	
            
            var input_form =$('#'+evt.data.options.widgetId).find('form'); 
            var inputFlds = input_form.find(":input");
            var response = isRequiredFieldsFilled(inputFlds);
            if (response.success){
                input_form.submit();
            }else{
                window.message(response);
            }
        }
    });
    
    function isRequiredFieldsFilled(inputFlds){
        var response = {"success":true, "message":""};
        for (x =0 ; x < inputFlds.length ; x++){
            //TODO:  add all types
            if ($(inputFlds[x]).attr("data-isrequired") && $(inputFlds[x]).attr("data-isrequired")=='true'){
                if ($(inputFlds[x]).attr("data-type")=='input' && $(inputFlds[x]).val()==''){
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
