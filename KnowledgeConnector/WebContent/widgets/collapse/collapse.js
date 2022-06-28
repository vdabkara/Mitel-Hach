(function($) {
    $.widget( "okcs.collapse", {
                // default widget options
        options: {
            div_id: ""
        },
        // widget constructor
        _create: function() {
            $(this.element).click(this._collapse);
        },
        /** 
         * This method is called when new response is available.
         * @param {object} topic response event
         * @param {object} responseData new response object
         * @param {object} widget Instance ID
        */
        updateWidget: function(topic, responseData, widget) {
        
        },
        _collapse: function(event) {
            $("#"+$(event.target).data("screen")).toggle();
            $(this).toggleClass("expandPanel collapsePanel");
        }
    });   
    
})(jQuery);