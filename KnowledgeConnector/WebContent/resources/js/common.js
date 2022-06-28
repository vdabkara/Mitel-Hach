function convertToObject(xml, str) {
   var obj = {
      toObj: function(xml) {
         var arr = {};
         if (xml.nodeType==1) {   // element node
              if (xml.firstChild) { // element has child nodes
            	  var textChild=0, cdataChild=0, hasElementChild=false;
	               for (var n=xml.firstChild; n; n=n.nextSibling) {
	                  if (n.nodeType==1) hasElementChild = true;
	                  else if (n.nodeType==4) cdataChild++; // cdata node
	               	}
	               if (hasElementChild) {
	                  if (textChild < 2 && cdataChild < 2) { // a single text or/and cdata node ..
	                     for (var n=xml.firstChild; n; n=n.nextSibling) {                   		
	                        if (arr[n.nodeName]) {
	                           if(arr[n.nodeName] instanceof Array){
	                              arr[n.nodeName][arr[n.nodeName].length] = obj.toObj(n);
	                           }else{
	                              arr[n.nodeName] = [arr[n.nodeName], obj.toObj(n)];
	                           }
	                        }else{  // first occurence of element
	                           arr[n.nodeName] = obj.toObj(n);  
	                        }
	                     }
	                  }
	               }else if (cdataChild) { // cdata
	            	   for (var n=xml.firstChild; n; n=n.nextSibling){
	            		   if(str=='START'){
	            			   str="";
	            		   }else{
	            			   str+="<strong>"+n.parentNode.nodeName+"</strong>";
	            			   if(n.nodeValue.startsWith('<p>'))
	                    		str+=n.nodeValue;
	            			   else
	                    		str+="<p>"+n.nodeValue+"</p>";
                    	  }
	            	   arr["#cdata"] =n.nodeValue;
                      }
	              }
              }	
         }
         
         return str;
      }
   };
   if (xml.nodeType == 9) // document node
      xml = xml.documentElement;
   return obj.toObj(xml);   
}

function getWidgetId(widgetId) {
	if(widgetId === undefined || widgetId === '' || widgetId === null) {
		for(var i=0; i<32; i++)
			widgetId += Math.floor(Math.random()*16).toString(16).toUpperCase();
	}
	return widgetId;
}

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

function render(tmpl_name, tmpl_data) {
    if ( !render.tmpl_cache ) { 
        render.tmpl_cache = {};
    }

    if ( ! render.tmpl_cache[tmpl_name] ) {
        var tmpl_dir = getBaseUrl() + '/widgets';
        var tmpl_url = tmpl_dir + '/' + tmpl_name + '.html';
        var tmpl_string = '';
        $.ajax({
            url: tmpl_url,
            method: 'GET',
            async: false,
            success: function(data) {
                tmpl_string = data;
            }
        });

        render.tmpl_cache[tmpl_name] = _.template(tmpl_string);
    }
    return render.tmpl_cache[tmpl_name](tmpl_data);
}
function getBaseUrl() { 
	var urlParameters = $(location).attr('pathname').replace(/^\//, " ").split('/');
	return '/' + urlParameters[0].trim();
}

