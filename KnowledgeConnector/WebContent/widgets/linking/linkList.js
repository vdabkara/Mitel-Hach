(function($) {
    $.widget( "okcs.linkList", {
        // default widget options
        options: {
            incidentID: "",
            messageID:""
        },
        // widget constructor
        _create: function() {
           $(this.element).on("click", '.removeLink', this, this._unlink);

        },
        /** 
         * This method is called when new response is available.
         * @param {object} topic response event
         * @param {object} responseData new response object
         * @param {object} widget Instance ID
        */
        updateWidget: function(topic, responseData, widget) {
        
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
                    	//alert("calling unlink in linklist.js");
                        document.getElementById('load').style.display="block";
                        document.body.style.cursor = 'wait';
                        var urlParameters = $(location).attr('pathname').replace(/^\//, " ").split('/');
                        ajaxRequestUrl = '/' + urlParameters[0].trim() + '/kmRequest';
                        var data_id = $(event.target).data("id");
                        var doc_id =  $(event.target).data("docid");
                        var documentID = $(event.target).data("documentid");
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
                            documentid : $(event.target).data("documentid"),
                            im_doc_id: $(event.target).data("documentid")
                        };
                        //console.log(JSON.stringify(postData));
                        $.ajax({
                            url: ajaxRequestUrl,
                            type: "POST",
                            contentType: "application/json; charset=UTF-8",
                            data: JSON.stringify(postData),
                            success: function(response){
                                if (!response.error) {
                                    //location.reload(true);
                                    try{ $("#"+data_id).removeClass("hidden") }catch(e){};
                                    $("#linkedItemRow_"+data_id).remove();
                                }
                                $("#basicModal").dialog( "close" );
                                document.getElementById('load').style.display="none";
                                document.body.style.cursor = 'auto';
                               // alert("Unlinked doc id " + documentID);
                                //alert("# button" + $( "button[data-docid^='"+ documentID + "']").length);
                                $( "button[data-docid^='"+ documentID + "']").removeClass("addLink hidden");
                                $( "button[data-docid^='"+ documentID + "']").addClass("addLink");
                                $("#basicMessageObj").message("setMessage",response);
                            },
                            error:function(error){
                                console.log(error);
                                $("#basicModal").dialog( "close" );
                                document.getElementById('load').style.display="none";
                                document.body.style.cursor = 'auto';
                            }
                        });                        
                    },
                    "No": function() {
                        $( this ).dialog( "close" );
                    }
                }
            });
        }
    });   
    
})(jQuery);