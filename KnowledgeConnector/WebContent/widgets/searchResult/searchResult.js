/**
 * Copyright (c) 2015, Oracle Corporation and/or its affiliates. All rights reserved.
 *
 * The sample code in this document or accessed through this document is not certified or
 * supported by Oracle. It is intended for educational or testing purposes only. Use of this
 * sample code implies acceptance of the License Agreement
 * (http://www.oracle.com/technetwork/licenses/standard-license-152015.html).
 *
 * File: searchResult.js
 * Abstract: JS file for SearchResult widget
 * Version: 1.0
 */

(function($) {
    $.widget( "okcs.searchResult", {

        // default widget options
        options: {
          label_results: 'Results',
          hide_when_no_results: true,
          label_no_results: 'No Results were found',
          answer_detail_url: '',
          target: "_self",
          truncate_size: 200,
          pubSub: '',
          searchText: '',
          searchSession: '',
          transactionID: 1,
          priorTransactionID: 1,
          widgetId: '',
          client_side_template: 'view',
          showCaseLinks: 'false',
          showAddLink: 'false',
          showAddText: 'false',
          incidentID: '',
          incidentDesc: '',
          // callbacks
          updateWidget: null
        },

        // widget constructor
        _create: function() {
            var event = this.options.pubSub;
            event.subscribe('response', this.updateWidget, this);
            event.subscribe('search', this.performSearch, this);
            event.subscribe('beforeSearch', this._beforeSearch, this);
            event.subscribe('afterSearch', this._afterSearch, this);
            event.subscribe('beforeAnswerClick', this._beforeAnswerClick, this);
            event.subscribe('afterAnswerClick', this._afterAnswerClick, this);
            
            $(this.element).on("click", '.addText', this, this._addText);
            $(this.element).on("click", '.addLink', this, this._addLink);
            $(this.element).on("click", '.linkedAnswer', this, this._unlink);

            window.addEventListener("popstate", function(popEvent) {
                var state = popEvent.state;
                if(state) {
                    event.fire('response', state);
                  }else if(location.href.indexOf('?') === -1){
                      event.fire('response', '');
                  }
                return;
            });
      },

        /** 
         * This method is called when new response is available.
         * @param {object} topic response event
         * @param {object} responseData new response object
         * @param {object} widget Instance ID
        */
        updateWidget: function(topic, responseData, widget) {
            var newContent = "", results = undefined, widgetSelector = $('#' + widget.options.widgetId), widgetContentSelector = $('#' + widget.options.widgetId + "_Content"), widgetNoResultsSelector = $('#' + widget.options.widgetId + "_NoSearchResult");
            if (responseData !== undefined && responseData.data !== undefined && responseData.data.error !== undefined) {
                error = responseData[0].data.error;
                newContent = '<div id="' + widget.baseDomID + '_Error" class="ErrorMessage">' + error.errorCode + ': ' + error.externalMessage + ' - ' + error.source + '</div>';
            }
            else {
                if (responseData && responseData.results !== null) {
                    results = responseData.results.results;
                }
                if (results && results.length > 0) {
                    widgetSelector.removeClass("NoSearchResult");
                    widgetNoResultsSelector.hasClass("Hidden") ? null : widgetNoResultsSelector.addClass("Hidden");
//alert('searchResult/' + widget.options.client_side_template);
                    newContent = render(
                        'searchResult/' + widget.options.client_side_template, 
                        {
                            data: results[0].resultItems, 
                            title: widget.options.label_results, 
                            target: widget.options.target, 
                            showCaseLinks: widget.options.showCaseLinks, 
                            showAddText: widget.options.showAddText, 
                            showAddLink: widget.options.showAddLink, 
                            linkedAnswers: JSON.parse(widget.options.linkedAnswers)
                        }
                    );
                }
                else {
                    if(!widget.options.hide_when_no_results) {
                    	widgetNoResultsSelector.hasClass("Hidden") ? widgetNoResultsSelector.removeClass("Hidden") : null;
                        widgetSelector.addClass("NoSearchResult");
                    }
                }
            }
            if (widgetContentSelector) {
                if(widget.options.hide_when_no_results && newContent === '') {
                    widgetSelector.removeClass("SearchResult");
                }
                else if(!widgetSelector.hasClass("SearchResult")) {
                    widgetSelector.addClass("SearchResult");
                }
                widgetContentSelector.html(newContent);
                widgetSelector.removeClass("Hidden");
            }
        },
        
        _addLink: function(event) {
            var caseLinkingContext = window.parent, message,
            answerTitle = $(event.target).parent().find("a").html(),targetOrigin = "*";
            var doc_id =  $(event.target).data("docid");
            if($(event.target).data("id")){
            	document.getElementById('load').style.display="block";
                document.body.style.cursor = 'wait';
                message = {href:window.location.href.substr(0, window.location.href.indexOf("/jsp")) + "/jsp/answerView.jsp?answerId=" + $(event.target).data("id"), title: answerTitle, action: "addLink"};
                var urlParameters = $(location).attr('pathname').replace(/^\//, " ").split('/'),
                ajaxRequestUrl = '/' + urlParameters[0].trim() + '/kmRequest',
                postData = {
                    action : "addLink", 
                    answerId : $(event.target).data("id"), 
                    description : event.data.options.searchText,
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
                    		linkedAnswers = JSON.parse(event.data.options.linkedAnswers);
                    		linkedAnswers.push($(event.target).data("id"));
                    		event.data.options.linkedAnswers = JSON.stringify(linkedAnswers);
                                $(event.target).addClass("hidden");
                                $( "table[id^='linkList_collapse_screen'] > tbody:last-child" ).append( response.linked_item );
                                document.getElementById('load').style.display="none";
                                document.body.style.cursor = 'normal';
                                $("[data-docid=" + response.linked_documentId +"]").addClass("addLink hidden");
                                $("#basicMessageObj").message("setMessage",response);
                                //location.reload(true);
                    	}else{
                    	        alert(response.error);
                                location.reload(true);
                    	}
                    },
                    error:function(error){
                        console.log(error);
                        location.reload(false);
                        return;
                    }
                });
        	}
        	else{
        		linkedAnswers = JSON.parse(event.data.options.linkedAnswers);
        		linkedAnswers.push(parseInt($(event.target).attr("id")));
        		event.data.options.linkedAnswers = JSON.stringify(linkedAnswers);
        		message = {href:window.location.href.substr(0, window.location.href.indexOf("/jsp")) + "/jsp/" + $(event.target).data("href"), title:answerTitle, action:"addLink"};
        		$(event.target).parent().children().first().remove();
        		$(event.target).parent().prepend("<span class=\"linkedAnswer\"></span>");
            	if (caseLinkingContext != window) {
            		caseLinkingContext.postMessage(message, targetOrigin);
            	}
        	}
        },
        
        _unlink: function(event) {
        	if($(event.target).data("id")) {
        		var urlParameters = $(location).attr('pathname').replace(/^\//, " ").split('/'),
                ajaxRequestUrl = '/' + urlParameters[0].trim() + '/makeRequest',
                postData = {action : "unlink", answerId : $(event.target).data("id"), incidentId : event.data.options.incidentID};
            	
            	$.ajax({
                    url: ajaxRequestUrl,
                    type: "POST",
                    contentType: "application/json; charset=UTF-8",
                    data: JSON.stringify(postData),
                    success: function(response){
                    	if (!response.error) {
                    		linkedAnswers = JSON.parse(event.data.options.linkedAnswers);
                    		linkedAnswers.splice(linkedAnswers.indexOf($(event.target).data("id")), 1);
                    		event.data.options.linkedAnswers = JSON.stringify(linkedAnswers);
                    		var targetParent = $(event.target).parent();
                    		targetParent.children().first().remove();
                    		targetParent.prepend("<span data-id=\"" + $(event.target).data("id") + "\" class=\"unlinkedAnswerIndent\"></span>");
                    	}
                    	else {
                    		
                    	}
                    },
                    error:function(error){
                        console.log(error);
                        return;
                    }
                });
        	}
        	else{
        		linkedAnswers = JSON.parse(event.data.options.linkedAnswers);
        		linkedAnswers.splice(linkedAnswers.indexOf($(event.target).attr("id")), 1);
        		event.data.options.linkedAnswers = JSON.stringify(linkedAnswers);
        		var targetParent = $(event.target).parent();
        		targetParent.children().first().remove();
        		targetParent.prepend("<span data-id=\"" + $(event.target).data("id") + "\" class=\"unlinkedAnswerIndent\"></span>");
        	}
        },

        _addText: function(event, args, widget) {
        	var caseLinkingContext = window.parent, message;
        	var answerTitle = $(event.target).parent().find("a").html();
    		var urlParameters = $(location).attr('pathname').replace(/^\//, " ").split('/'),
            ajaxRequestUrl = '/' + urlParameters[0].trim() + '/makeRequest',
            postData = {action : "addText", answerId : $(event.target).data("id"), incidentId : event.data.options.incidentID};
        	
        	$.ajax({
                url: ajaxRequestUrl,
                type: "POST",
                contentType: "application/json; charset=UTF-8",
                data: JSON.stringify(postData),
                success: function(response){
                	if (!response.error) {
                		message = {action:"addText", html:response.answerHtml};
                		linkedAnswers = JSON.parse(event.data.options.linkedAnswers);
                		linkedAnswers.push($(event.target).data("id"));
                		event.data.options.linkedAnswers = JSON.stringify(linkedAnswers);
                		$(event.target).parent().children().first().remove();
                		$(event.target).parent().prepend("<span data-id=\"" + $(event.target).data("id") + "\" class=\"linkedAnswer\"></span>");
                		var targetOrigin = "*";
                    	if (caseLinkingContext != window) {
                    		caseLinkingContext.postMessage(message, targetOrigin);
                    	}
                	}else {
                		alert(response.error);
                	}
                },
                error:function(error){
                    console.log(error);
                    return;
                }
            });
        },

        /** 
        * This method is called when 'search' event is fired. It fires 'response' event when response is available.
        * @param {object} event search event
        * @param {object} args event data
        * @param {object} widget Instance ID
        */
        performSearch: function(event, args, widget) {
            if(widget === undefined) widget = event.data;
            var urlParameters = $(location).attr('pathname').replace(/^\//, " ").split('/');
            var ajaxRequestUrl = '/' + urlParameters[0].trim() + '/makeSearchRequest';
            var options = widget.options;
            var postData = filters;

            if(
                postData.type === 'search' && 
                (
                    postData.question === undefined || 
                    postData.question === ''
                )
            ) return false;
    
            postData.session = options.searchSession;
            postData.transactionId = (postData.type === 'page') ? (options.transactionID - 1) : options.transactionID;
            postData.priorTransactionID = options.priorTransactionID;
            postData.truncateSize = options.truncate_size;
            postData.incidentID = options.incidentID;
            var newUrl = "";
            $(document).ready(function () {
                $.ajax({
                    async: false,
                    url: ajaxRequestUrl,
                    type: "POST",
                    contentType: "application/json; charset=UTF-8",
                    data: JSON.stringify(postData),
                    complete: function(req, status) {
                        //alert("in complete");
                    },
                    success: function(response){
                        if (response.search_filters_saved){
                            //alert("here in search_filters_saved");
                            newUrl = location.href;
                            if (newUrl.indexOf("#")!=-1){
                                newUrl = newUrl.substring(0, newUrl.length-1);
                            }
                            var url_front = newUrl.split("?")[0];
                            var url_params = ((newUrl.indexOf('?') == -1)?"": newUrl.split("?")[1]).split("&");
                            var url_param = "";
                            for (x = 0 ; x < url_params.length; x++){
                                url_param = url_params[x];
                                if (url_param!=null && url_param.indexOf("s_id=")==-1){
                                    if (
                                        response.type &&
                                        response.type=="search" &&
                                        url_param!=null && 
                                        url_param.indexOf("kw=")!=-1
                                    ){
                                        url_param="kw="+response.question;
                                    }
                                    url_front+=(url_front.indexOf("?")==-1?"?":"&");
                                    url_front+=url_param;
                                }
                            }
//                            url_front+=(url_front.indexOf("?")==-1?"?":"&");
//                            url_front+="s_id=";
//                            url_front+=response.searchId;
                            newUrl = url_front;
                            location.href=newUrl;
                            
                        }else{
                            options.searchSession = response.session;
                            options.transactionID = response.transactionId + 1;
                            options.priorTransactionID = response.priorTransactionId;
                            newUrl = location.href;
                            var url_front = newUrl.split("?")[0];
                            var url_params = ((newUrl.indexOf('?') == -1)?"": newUrl.split("?")[1]).split("&");
                            var url_param = "";
                            for (x = 0 ; x < url_params.length; x++){
                                url_param = url_params[x];
                                if (url_param!=null && url_param.indexOf("s_id=")==-1){
                                    url_front+=(url_front.indexOf("?")==-1?"?":"&");
                                    url_front+=url_param;
                                }
                            }
//                            url_front+=(url_front.indexOf("?")==-1?"?":"&");
//                            url_front+="s_id=";
//                            url_front+=response.searchId;
                            newUrl = url_front;
                            if(postData.type === 'page')
                                window.history.replaceState(response, '', newUrl);
                            else
                                window.history.pushState(response, '', newUrl);
                            options.pubSub.fire('response', response);
                            var nav_postData = {page_link : newUrl, action: 'setNavigation'};
                            var ajaxKMUrl = '/' + urlParameters[0].trim() + '/kmRequest';
                            $.ajax({
                                url: ajaxKMUrl,
                                type: "POST",
                                contentType: "application/json; charset=UTF-8",
                                data: JSON.stringify(nav_postData),
                                success: function(inner_response){
                                
                                },
                                error:function(inner_error){
                                    console.log(inner_error);
                                }
                            });
                        }
                        return;
                    },
                    error:function( error){                    
                        console.log(error);
                        return;
                    }
                });
            });
        }
    });
})(jQuery);