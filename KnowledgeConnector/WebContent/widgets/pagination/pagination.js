/**
 * Copyright (c) 2015, Oracle Corporation and/or its affiliates. All rights reserved.
 *
 * The sample code in this document or accessed through this document is not certified or
 * supported by Oracle. It is intended for educational or testing purposes only. Use of this
 * sample code implies acceptance of the License Agreement
 * (http://www.oracle.com/technetwork/licenses/standard-license-152015.html).
 *
 * File: pagination.js
 * Abstract: JS file for Pagination widget
 * Version: 1.0
 */

(function($) {
    $.widget( "okcs.pagination", {
        options: {
            label_next: 'Next',
            label_previous: 'Previous',
            search_text: '',
            pubSub: '',
            widgetId: '',
            client_side_template: '',
            onClick: null,
            updateWidget: null,
            currentPage: 0
        },	 
        // widget constructor
        _create: function() {
            this.instanceID = this.options.widgetId;
            this._currentPage = this.options.currentPage;
            this._pageDirection = '';
            var event = this.options.pubSub;
            $(this.element).on("click", 'a span', this, this._onClick);
            event.subscribe('response', this.updateWidget, this);
            event.subscribe('collect', this._collectFilters, this);
        },	  
        /**
        * Method to collect page filters
        * @param {object} event object
        * @param {object} args arguments passed by the event
        * @param {object} widget widget instance
        */
        _collectFilters: function(event, args, widget) {
            var event = widget.options.pubSub;
            event.addFilter({key : 'page', value : widget._currentPage});
            event.addFilter({key : 'direction', value : widget._pageDirection});
        },	  
        /** This method is called on next and previous page links.
        *  @param {object} evt Click Event object
        */
        _onClick: function(evt) {
            var dir = evt.currentTarget.getAttribute('id'), 
            widget = evt.data, event = widget.options.pubSub;
            widget._pageDirection = dir;
            event.addFilter({key : 'type', value : 'page'});
            //queue events
            $(this).queue("eventQueue", null, event.fire('collect'));
            $(this).queue("eventQueue",  null, event.fire('search'));
            $(this).dequeue("eventQueue");
        },
        /** 
        * This method is called when new response is available.
        * @param {object} topic response event
        * @param {object} responseData new response object
        * @param {object} widget instance ID
        */
        updateWidget: function(topic, responseData, widget) {
            var widgetContentSelector = $('#' + widget.instanceID + "_Content");
            widgetContentSelector.empty();
            if (responseData && responseData.results !== null && responseData.results.results !== null && responseData.results.results.length > 0) {
                results = responseData.results.results[0];
                this.data._currentPage = results.pageNumber;
                var newContent = render('pagination/' + widget.options.client_side_template, {currentPage: results.pageNumber, pageMore: results.pageMore, widgetInstanceId: widget.options.widgetId, labelPrevious: widget.options.label_previous, labelNext: widget.options.label_next});
                widgetContentSelector.html(newContent);
            }
        }
    });
})(jQuery);