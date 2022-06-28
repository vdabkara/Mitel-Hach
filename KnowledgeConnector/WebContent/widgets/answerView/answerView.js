/**
 * Copyright (c) 2015, Oracle Corporation and/or its affiliates. All rights reserved.
 *
 * The sample code in this document or accessed through this document is not certified or
 * supported by Oracle. It is intended for educational or testing purposes only. Use of this
 * sample code implies acceptance of the License Agreement
 * (http://www.oracle.com/technetwork/licenses/standard-license-152015.html).
 *
 * File: answerView
 * Abstract: JS file for AnswerView widget
 * Version: 1.0
 */

(function($) {
    $.widget( "okcs.answerView", {
        // default widget options
        options: {
            label_confirm_remove: 'Are you sure you want to Unlink?',
            pubSub: '',
            incidentID: '',
            answerLinkStatus: 'unlinked',
            showCaseLinks: false,
            showAddLink: false,
            showAddText: false,
            widgetId: ''
        },

        // widget constructor
        _create: function() {
            var event = this.options.pubSub;
            $(this.element).on("click", '.addText', this, this._addText);
            $(this.element).on("click", '.addLink', this, this._addLink);
            $(this.element).on("click", '.removeLink', this, this._unlink);
            $(this.element).on("click", "button[name='showRecommendChangeButton']", this, this._onShowRecommendChangeSubmission);
            $(this.element).on("click", "button[name='recommendChangeSubmitButton']", this, this._onRecommendChangeSubmission);
            $(this.element).on("click", '.CancelButton', this, this._onCancelButtonClick);
        },
        
        _addLink: function(event) {
            var caseLinkingContext = window.parent, message;
            var answerTitle = $("#Summary").html();
            var targetOrigin = "*";
            if($(event.target).data("id")){
                var doc_id =  $(event.target).data("docid");
            	document.getElementById('load').style.display="block";
                document.body.style.cursor = 'wait';            	
                message = {href:window.location.href.substr(0, window.location.href.indexOf("/jsp")) + "/jsp/answerView.jsp?answerId=" + $(event.target).data("id"), title: answerTitle, action: "addLink"};
                var urlParameters = $(location).attr('pathname').replace(/^\//, " ").split('/'),
                ajaxRequestUrl = '/' + urlParameters[0].trim() + '/kmRequest',
                postData = {
                    action : "addLink",
                    answerId : $(event.target).data("id"), 
                    incidentId : event.data.options.incidentID,
                    title : answerTitle,
                    im_doc_id: doc_id
                };
                $.ajax({
                    url: ajaxRequestUrl,
                    type: "POST",
                    contentType: "application/json; charset=UTF-8",
                    data: JSON.stringify(postData),
                    success: function(response){
                        if (!response.error) {
                            if (caseLinkingContext != window) {
                               caseLinkingContext.postMessage(message, targetOrigin);
                            }
                            location.reload();
                            //$(".linkButtons").addClass("linkInfo").removeClass("linkButtons");
                            //$(".linkInfo").prepend("<div class=\"linkedText\">This article is linked to this incident</div>");
                            //$(".addLink").remove();
                        }
                        else {
                            alert(response.error);
                        }
                    },
                    error:function(error){
                        console.log(error);
                        location.reload();
                        return;
                    }
                });
            }
            else{
                message = {href:window.location.href.substr(0, window.location.href.indexOf("/jsp")) + "/jsp/" + $(event.target).data("href"), title:document.title, action:"addLink"};
                if (caseLinkingContext != window) {
                    caseLinkingContext.postMessage(message, targetOrigin);
                }
            }
        },
        _unlink_OLD: function(event) {
            var local_confirm = confirm(event.data.options.label_confirm_remove);
            if (local_confirm==true){
            	document.getElementById('load').style.display="block";
                document.body.style.cursor = 'wait';            	
                var urlParameters = $(location).attr('pathname').replace(/^\//, " ").split('/');
                ajaxRequestUrl = '/' + urlParameters[0].trim() + '/kmRequest';
                var data_id = $(event.target).data("id")
                //alert("data-id = "+$(event.target).data("id"));
                //console.log("action : unlink,"+" answerId : "+data_id+", incidentId : "+event.data.options.incidentID+",answer_title : "+$(event.target).data("title")+",answerId : "+$(event.target).data("answerid")+",version : "+$(event.target).data("version")+",documentId : "+$(event.target).data("documentid")+",versionId : "+$(event.target).data("versionid")+"recordId : "+$(event.target).data("recordid"));                
                postData = {
                    action : "unlink", 
                    answerId : data_id, 
                    incidentId : event.data.options.incidentID,
                    answer_title : $(event.target).data("title"),
                    version : $(event.target).data("version"),
                    documentId : $(event.target).data("documentid"),
                    versionId : $(event.target).data("versionid"),
                    recordId : $(event.target).data("recordid"),
                    documentid : $(event.target).data("documentid")
                };
                //console.log(JSON.stringify(postData));
                $.ajax({
                    url: ajaxRequestUrl,
                    type: "POST",
                    contentType: "application/json; charset=UTF-8",
                    data: JSON.stringify(postData),
                    success: function(response){
                        if (!response.error) {
                            //linkedAnswers = JSON.parse(event.data.options.linkedAnswers);
                            //linkedAnswers.splice(linkedAnswers.indexOf($(event.target).data("id")), 1);
                            //event.data.options.linkedAnswers = JSON.stringify(linkedAnswers);
                            //$('#linkedItemRow_'+data_id).remove();
                            location.reload(true);
                        } else {
    
                        }
                    },
                    error:function(error){
                        console.log(error);
                        return;
                    }
                });
                
            }else{ // not unlink
                
            }
        },
        _unlink: function(event) {
          $( "#basicModal" ).dialog({
              modal: true,
              resizable: false,
              height:140,
              width:450,
              title: "Are you sure you want to Unlink?",
              buttons: {
                  "Yes": function() {
                      document.getElementById('load').style.display="block";
                      document.body.style.cursor = 'wait';
                      var urlParameters = $(location).attr('pathname').replace(/^\//, " ").split('/');
                      ajaxRequestUrl = '/' + urlParameters[0].trim() + '/kmRequest';
                      var data_id = $(event.target).data("id")
                      //alert("data-id = "+$(event.target).data("id"));
                      //console.log("action : unlink,"+" answerId : "+data_id+", incidentId : "+event.data.options.incidentID+",answer_title : "+$(event.target).data("title")+",answerId : "+$(event.target).data("answerid")+",version : "+$(event.target).data("version")+",documentId : "+$(event.target).data("documentid")+",versionId : "+$(event.target).data("versionid")+"recordId : "+$(event.target).data("recordid"));                
                      postData = {
                          action : "unlink", 
                          answerId : data_id, 
                          incidentId : event.data.options.incidentID,
                          answer_title : $(event.target).data("title"),
                          version : $(event.target).data("version"),
                          documentId : $(event.target).data("documentid"),
                          versionId : $(event.target).data("versionid"),
                          recordId : $(event.target).data("recordid"),
                          documentid : $(event.target).data("documentid")
                      };
                      //console.log(JSON.stringify(postData));
                      $.ajax({
                          url: ajaxRequestUrl,
                          type: "POST",
                          contentType: "application/json; charset=UTF-8",
                          data: JSON.stringify(postData),
                          success: function(response){
                              if (!response.error) {
                                  //linkedAnswers = JSON.parse(event.data.options.linkedAnswers);
                                  //linkedAnswers.splice(linkedAnswers.indexOf($(event.target).data("id")), 1);
                                  //event.data.options.linkedAnswers = JSON.stringify(linkedAnswers);
                                  //$('#linkedItemRow_'+data_id).remove();
                                  location.reload(true);
                              } else {          
                              }
                          },
                          error:function(error){
                              console.log(error);
                              return;
                          }
                      });                        
                  },
                  "No": function() {
                      $( this ).dialog( "close" );
                  }
              }
          });
      },
        _addText: function(event, args, widget) {
        	var caseLinkingContext = window.parent,
        		answerHTML = $(".AnswerView").html(),
        		message = {html:answerHTML, action: "addText"},
        		targetOrigin = "*",
        		urlParameters = $(location).attr('pathname').replace(/^\//, " ").split('/'),
        		ajaxRequestUrl = '/' + urlParameters[0].trim() + '/makeRequest',
        		postData = {action : "addLink", answerId : $(event.target).data("id"), incidentId : event.data.options.incidentID};
        	
        	$.ajax({
                url: ajaxRequestUrl,
                type: "POST",
                contentType: "application/json; charset=UTF-8",
                data: JSON.stringify(postData),
                success: function(response){
                	if (!response.error) {
                		if (caseLinkingContext != window) {
                    		caseLinkingContext.postMessage(message, targetOrigin);
                    	}
                		$(".linkButtons").addClass("linkInfo").removeClass("linkButtons");
                    	$(".linkInfo").prepend("<div class=\"linkedText\">This article is linked to this incident</div>");
                	}
                	else {
                		alert(response.error);
                	}
                },
                error:function(error){
                    console.log(error);
                    return;
                }
            });
        },
        _onCancelButtonClick: function(evt) {
            var widget = evt.data, event = evt.data.options.pubSub;
            evt.preventDefault();
            widget._selectedFacets = '';
            /*event.addFilter({key : 'type', value : 'search'});        
            $(this).queue("eventQueue", null, event.fire('nav_back'));*/
        
            var widgetId =  evt.data.options.widgetId;
            var buttonId= widgetId+"_RecommendChange";
            widgetId = '#'+ widgetId;
            /* reset input Fields and hide block*/
            $(widgetId+"_title").val('');
            $(widgetId+"_description").val('');
            // restore caseNumber value
            $(widgetId+"_caseNumber").val($(widgetId+"_caseIdFromParam").val());
            $(widgetId+"_priorityType").val("");
            
            
            // Hide Input Block
            $( "#inputBlock" ).fadeOut( "slow", function() {
                // Animation complete.
            	// empty error message
    			$("div[name='messages']").removeClass();
    			$("div[name='messages']").html('');
    			//$("div[name='messages']").fadeOut( "slow");
            	 // Show Recommend Change Button
                document.getElementById(buttonId).style.display="block";
            });
			
        },
        _onShowRecommendChangeSubmission: function(evt) {
        	 var widget = evt.data, event = evt.data.options.pubSub;
             evt.preventDefault();
             var widgetId = evt.data.options.widgetId;
             // Hide Recommend Change Button
             var buttonId=widgetId+"_RecommendChange";
             document.getElementById(buttonId).style.display="none"; 
             // show Input Block
             $( "#inputBlock" ).fadeIn( "slow");
        },
        _onRecommendChangeSubmission: function(evt) {
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