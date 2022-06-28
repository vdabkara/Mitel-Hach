/**
 * Copyright (c) 2015, Oracle Corporation and/or its affiliates. All rights reserved.
 *
 * The sample code in this document or accessed through this document is not certified or
 * supported by Oracle. It is intended for educational or testing purposes only. Use of this
 * sample code implies acceptance of the License Agreement
 * (http://www.oracle.com/technetwork/licenses/standard-license-152015.html).
 *
 * File: facet.js
 * Abstract: JS file for Facet widget
 * Version: 1.0
 */

(function($) {
	$.widget( "okcs.facet", {
	  
	  // default widget options
	  options: {
		  label_filter: 'Filter',
		  label_clear_screenreader: 'Unselect all filters',
		  max_sub_facet_size: 5,
		  toggle_title: false,
		  label_clear: 'Clear',
		  label_active_filter_screenreader: 'Active filter. Click to unselect.',
		  label_more: 'More..',
		  label_more_screenReader : 'Select to show more filters.',
		  client_side_template : 'facetLink',
		  facets: '',
		  pubsSub: '',
		  widgetId: '',
	
	     // callbacks
	     updateWidget: null,
	    _onFacetClick: null
	  },
	 
	  // widget constructor
	  _create: function() {
		  this._facets = JSON.parse(this.options.facets), this._selectedFacets = '';
		  var event = this.options.pubSub, widget = $(this.element);
		  
		  widget.on('click', 'a.FacetLink', this, this._onFacetClick);
		  widget.on('click', 'a.ClearFacets', this, this._onFacetResetClick);
		  widget.on('click', '.ToggleExpandCollapse', this, this.toggleExpandCollapse);
		  
		  event.subscribe('response', this.updateWidget, this);
          event.subscribe('beforeFacet', this._beforeFacet, this);
          event.subscribe('afterFacet', this._afterFacet, this);
          event.subscribe('collect', this._collectFilter, this);
	  },
	 
	  /**
	   * This method collects facet filter
	   * @param {object} event Collect event object
	   * @param {object} args arguments passed as part of the collect event
	   * @param {object} widget widget instance ID
	   */
	  _collectFilter: function(event, args, widget) {
		  var event = widget.options.pubSub;
		  event.addFilter({key : 'facet', value : widget._selectedFacets});
	  },
	  

	  /** 
	   * This method is called when new response is available.
	   * @param {object} topic response event object
	   * @param {object} responseData new response object
	   * @param {object} widget widget instance ID
	   */
	  updateWidget: function(topic, responseData, widget) {
		  var widgetContentSelector = $('#' + widget.options.widgetId + "_Content");
		  	widgetContentSelector.empty();
	        if (responseData != undefined && (responseData.error == undefined || responseData.error.length === 0)) {
	            var facets = responseData.results.facets;
	            if(facets.length > 0) {
	            	widgetContentSelector.removeClass("Hidden");
	            }
	            else {
	            	if(!widgetContentSelector.hasClass("Hidden")) 
	            		widgetContentSelector.addClass("Hidden");
	            }
	            widget._facets = facets;
	            var title = $("<div class='FacetsTitle'></div>");
	            title.append(widget.options.label_filter);
	            var clearLink = $("<span class='ClearContainer'>[<a class='ClearFacets'><span class='ScreenReaderOnly'>" + widget.options.label_clear_screenreader + "</span>" + widget.options.label_clear + "</a>]</span>"); 
	            title.append(clearLink);
	            var list = $("<div class='FacetsList'></div>");
	            var ul = $("<ul></ul>"),
	                parentLi = null,
	                item = null;

	            for (var i = 0; i < facets.length; i++) {
	                var currentFacet = facets[i],
	                    displayText = currentFacet.desc,
	                    facetChildren = currentFacet.children.length;

	                if (facetChildren !== 0) {
	                    item = $("<li>" + displayText + "</li>");
	                    if (displayText) {
	                        parentLi = $("<ul></ul>");
	                        item = item.append(parentLi);
	                    }
	                    ul.append(item);
	                    widget._findChildren(currentFacet, parentLi, widget, widget.options.max_sub_facet_size);
	                }
	            }
	            list.append(ul);
	            widgetContentSelector.append(title).append(list);
	        }
	        else {
	            var error = responseData.data.error,
	                errorMessage = '<div id="' + widget.instanceID + '_Error" class="ErrorMessage">' + error.errorCode + ': ' + error.externalMessage + ' - ' + error.source + '</div>';
	            widgetContentSelector.append(errorMessage);
	        } 
	  },
	  
	  /** 
	    *   This method iterates the child facets recursively
	    *   @param {Object} current selected facet
	    *   @param {String} parent facet node in the Facet List 
	    *   @param {Object} widget instance
	    *   @param {int} maximum depth of facet to be looked
	    */
	  _findChildren : function(currentFacet, parentLi, widget, maxFacetLength) {
	        var currFacet, len = currentFacet.children.length;
	        len = (maxFacetLength !== undefined && maxFacetLength !== '') ? maxFacetLength : len;
	        for (var i = 0; i < len; ++i) {
	            currFacet = currentFacet.children[i];
	            if (currFacet !== undefined) {
	                if (currFacet.children.length !== 0) {
	                    var childLi = widget._processChildren(currFacet, true);
	                    parentLi.append(childLi);
	                    var childUl = $("<ul nodeid='" + currFacet.id + "' class='FacetTreeIndent'></ul>");
	                    childLi.append(childUl);
	                    widget._findChildren(currFacet, childUl, widget, len);
	                } else {
	                    parentLi.append(widget._processChildren(currFacet, false));
	                }
	            }
	        }
	        if (currentFacet.children.length > maxFacetLength) {
	        	var rendered_html = render('facet/' + widget.options.client_side_template, {currentFacet: currentFacet, moreScreenReaderLabel: widget.options.label_more_screenReader, labelMore: widget.options.label_more}),
	            	item = $("<li></li>");
	            item.append(rendered_html);
	            parentLi.append(item);
	        }
	    },
	    
	    /** 
	     *   This method renders child facets
	     *   @param {Object} current selected facet
	     *   @param {boolean} true if current facet has child facet.
	     */
	    _processChildren : function(currentFacet, hasChildren) {
	        var facetLinkClass = currentFacet.inEffect ? 'FacetLink ActiveFacet' : 'FacetLink',
	            selectedFacetText = "<span class='ScreenReaderOnly'>Active filter. Click to unselect.</span>",
	            currentFacetDescription = (facetLinkClass === 'FacetLink ActiveFacet') ? currentFacet.desc + selectedFacetText : currentFacet.desc,
	            item = $("<li></li>"),
	            spanExp = hasChildren ? "<span class='ToggleExpandCollapse CategoryExplorerExpanded'></span>" : "";

	        item.append($("<a class='" + facetLinkClass + "' id='" + currentFacet.id + "' href='javascript:void(0)'>" + spanExp + currentFacetDescription + "</a>"));

	        return item;
	    },
	    
	    /**
	     * Toggles the expand/collapse view for facet hierarchy
	     */
	     toggleExpandCollapse: function(e) {
	         if(e.target.classList.contains('CategoryExplorerExpanded')) {
	             $("ul[nodeid='" + e.target.parentNode.id + "']").hide();
	             e.target.classList.remove('CategoryExplorerExpanded');
	             e.target.classList.add('CategoryExplorerCollapsed');
	         }
	         else if(e.target.classList.contains('CategoryExplorerCollapsed')) {
	             $("ul[nodeid='" + e.target.parentNode.id + "']").show();
	             e.target.classList.remove('CategoryExplorerCollapsed');
	             e.target.classList.add('CategoryExplorerExpanded');
	         }
	         return false;
	     },
	    
	    /** Method to fire collect and search events.
	     *  To fire before and after facet click events, queue this method with others. Sample code to fire these events:
	     *  var event = evt.data.options.pubSub;
    	 *  $(this).queue("facetClick", null, event.fire('beforeFacet'));
    	 *  $(this).queue("facetClick",  null, evt.data._onFacetClick(evt));
    	 *  $(this).queue("facetClick", null, event.fire('afterFacet'));
    	 *  $(this).dequeue("facetClick");
    	 */    
	    _onFacetClick: function(evt) {
	        evt.preventDefault();	        
	        var selectedFacet = evt.target.id,
	        	widget = evt.data,
	        	event = evt.data.options.pubSub;
	        if(selectedFacet === ''){
	        	widget.toggleExpandCollapse(evt);return false;
	        }
	        if (selectedFacet.indexOf('F:') !== -1) {
	            var facets = evt.data._facets;
	            selectedFacet = selectedFacet.substring(2);
	            selectedFacet = selectedFacet.indexOf('.') !== -1 ? selectedFacet.substr(0, selectedFacet.indexOf('.')) : selectedFacet;
	            
	            var list = $("<div class='FacetsList'></div>"),
	            	title = $("<div class='FacetsTitle'></div>");
	            title.append(widget.options.label_filter);
	            var clearLink =$("<span class='ClearContainer'>[<a class='ClearFacets'><span class='ScreenReaderOnly'>" + widget.options.label_clear_screenreader + "</span>" + widget.options.label_clear + "</a>]</span>"); 
	            title.append(clearLink);

	            for (var i = 0; facets[i]; i++) {
	                if (facets[i].id === selectedFacet) {
	                    var parentLi = null,
	                        item = parentLi = $("<li>" + facets[i].desc + "</li>"),
	                        ul = $("<ul></ul>");
	                    ul.append(item);
	                    if (facets[i].children.length !== 0)
	                    	widget._findChildren(facets[i], parentLi, widget);
	                }
	            }
	            list.append(ul);
	            widgetContentSelector = $('#' + widget.options.widgetId + "_Content");
	            widgetContentSelector.empty();
	            widgetContentSelector.append(title).append(list);
	        }
	        else {
	        	widget._selectedFacets = selectedFacet;
	        	event.addFilter({key : 'type', value : 'facet'});
	        	//queue events
	        	$(this).queue("eventQueue", null, event.fire('collect'));
	        	$(this).queue("eventQueue",  null, event.fire('search'));
	        	$(this).dequeue("eventQueue");
	        }
                location.reload();
	    },
	    
	    /**
	     * Event Handler fired when a Clear facet Link is Clicked
	     * @param {Object} evt Event object
	     */
	     _onFacetResetClick: function(evt) {
	    	 var widget = evt.data, event = evt.data.options.pubSub;
	         evt.preventDefault();
	         widget._selectedFacets = '';
	         event.addFilter({key : 'type', value : 'search'});

	        //queue events
	        $(this).queue("eventQueue", null, event.fire('collect'));
	        $(this).queue("eventQueue",  null, event.fire('search'));
	        $(this).dequeue("eventQueue");
	     },
	    
	});
})(jQuery);