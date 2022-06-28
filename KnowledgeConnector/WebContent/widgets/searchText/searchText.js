/**
 * Copyright (c) 2015, Oracle Corporation and/or its affiliates. All rights reserved.
 *
 * The sample code in this document or accessed through this document is not certified or
 * supported by Oracle. It is intended for educational or testing purposes only. Use of this
 * sample code implies acceptance of the License Agreement
 * (http://www.oracle.com/technetwork/licenses/standard-license-152015.html).
 *
 * File: searchText.js
 * Abstract: JS file for SearchText widget
 * Version: 1.0
 */

(function($) {
	$.widget( "okcs.searchText", {
	  
	  // default widget options
	  options: {
	    label_input: 'Search by Keyword',
	    label_placeholder: 'Ask a question...',
	    label_enter_search_keyword: 'Enter keyword',
	    initial_focus: true,
	    search_text: '',
	    pubSub: '',
	    widgetId: '',
	    
	    // callbacks
	    _onClick: null
	    },
	 
	   // widget constructor
	  _create: function() {
		  var event = this.options.pubSub;
		  event.subscribe('collect', this._collectFilter, this);
		  $(this.element).keyup(function(evt) {
			  if(evt.keyCode == 13){
				  event.addFilter({key : 'type', value : 'search'});
				  //queue events
				  $(this).queue("eventQueue", null, event.fire('collect'));
				  $(this).queue("eventQueue",  null, event.fire('search'));
				  $(this).dequeue("eventQueue");
			    }
			});
	  },
	  
	  /**
	   * This method collects search text filter
	   * @param {object} event Event Object
	   * @param {object} args argument object
	   * @param {object} widget widget instance
	   */
	  _collectFilter: function(evt, args, widget) {
		  widget = widget.options;
		  var question = $('#' + widget.widgetId + '_SearchInput').val(),
		  	  event = widget.pubSub;
		  
		  event.addFilter({key : 'question', value : encodeURI(question)});
		  
		  if(question === '') {
			  var errorDiv = $('#' + widget.widgetId + '_errorDiv');
			  errorDiv.html(widget.label_enter_search_keyword).fadeIn(2000, function() { errorDiv.fadeOut(5000); });
		  }
	  },
	  
	  
	});
})(jQuery);