/**
 * Copyright (c) 2015, Oracle Corporation and/or its affiliates. All rights reserved.
 *
 * The sample code in this document or accessed through this document is not certified or
 * supported by Oracle. It is intended for educational or testing purposes only. Use of this
 * sample code implies acceptance of the License Agreement
 * (http://www.oracle.com/technetwork/licenses/standard-license-152015.html).
 *
 * File: searchButton
 * Abstract: JS file for searchButton widget
 * Version: 1.0
 */

(function($) {
	$.widget( "okcs.searchButton", {
	  
	  // default widget options
	  options: {
	    label_button: 'Search',
	    pubSub: '',
	    searchText: '',
	    widgetId: '',
	    
	    // callbacks
	    _onClick: null
	    },
	 
	   // widget constructor
	  _create: function() {
		  $(this.element).on("click", '.SubmitButton', this.options, this._onClick);
	  },
	  
	  /** 
	   * This method fires collects filters and requests for search through 'collect' and 'search' events. 
	   * @param {object} event click event
	   */
	  _onClick: function(event) {
		  event = event.data.pubSub;
		  event.addFilter({key : 'type', value : 'search'});
		  var URL = $(location).attr('href');
		  if(URL.indexOf("answerView") != -1){
			  var kw = $('[id*="SearchInput"]')[0].value;
			  window.location.replace(URL.substr(0, URL.indexOf("answerView")) + "default.jsp?kw=" + kw);
		  }
		  //queue events
		  $(this).queue("eventQueue", null, event.fire('collect'));
		  $(this).queue("eventQueue",  null, event.fire('search'));
		  $(this).dequeue("eventQueue");
      }	 
	});

})(jQuery);