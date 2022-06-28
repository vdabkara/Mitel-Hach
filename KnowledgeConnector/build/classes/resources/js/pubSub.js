(Pubsub = function() {
   topics = {};
   subUid = -1;
   filters = {};
   return {
	   
    fire: function( topic, args ) {
      if ( !topics[topic] )
        return false;
 
      var subscribers = topics[topic],
          len = subscribers ? subscribers.length : 0;
 
      while ( len-- )
        subscribers[len].callback( topic, args, subscribers[len].data );
      return subscribers;
    },
    
    subscribe: function( topic, callback, data) {
      if (!topics[topic])
        topics[topic] = [];
 
      var token = (++subUid).toString();
      topics[topic].push({
        token: token,
        callback: callback,
        data: data
      });
      return token;
   },
   
   unsubscribe: function(token) {
     for( var key in topics ) {
       for (var i = 0; i < topics[key].length; i++ ) {
          if(topics[key][i].token == token) {
            topics[key].splice( i, 1 );
            return token;
          }
        }
      } 
    },
    
    addFilter: function(filter) {
       filters[filter.key] = filter.value; 
    }    
  };
  
})();

ps = new Pubsub();