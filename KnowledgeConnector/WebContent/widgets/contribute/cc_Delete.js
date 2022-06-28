(function($) {
    $.widget( "okcs.delete", {
                // default widget options
        options: {
            div_id: ""
        },
        // widget constructor
        _create: function() {
            $("#deleteNode_"+this.options.div_id).click(this._delete);
        },
        _delete: function(event) {
            $( "#basicDialog" ).dialog({
                modal: true,
                resizable: false,
                height:140,
                width:450,
                title: "Are you sure you want to Remove?",
                buttons: {
                    "Yes": function() {
                        $("#"+$(event.target).data("childdiv")).remove();
                        $( this ).dialog( "close" );
                    },
                    "No": function() {
                        $( this ).dialog( "close" );
                    }
                }
            });
        }
    });   
    
})(jQuery);