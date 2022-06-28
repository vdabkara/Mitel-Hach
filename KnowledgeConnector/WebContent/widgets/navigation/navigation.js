(function($) {
	$.widget( "okcs.navigation", {
	  options: {
	    label_next: 'Back',
            pubSub:'',
            prev_page:''
	  },
	 
	   // widget constructor
	  _create: function() {
            var event = this.options.pubSub;
            event.subscribe('nav_back', this._backButton, this);
	  },
	  
	  _backButton: function(event, args, widget) {
            window.location.replace(this.data.options.prev_page);
	  }
	  
	});
})(jQuery);