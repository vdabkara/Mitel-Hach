(function($) {
    $.widget( "okcs.message", {
        options: {
            divid:''
        },
        _create: function() {
            //alert("in create message");
        },
            helloWorld: function(){
            alert("in helloWorld message");
            this.element.switchClass("contribute_content_error", "contribute_content_success");
        },
        setMessage : function(data){
            if (data.success){
                this.element.switchClass("contribute_content_error", "contribute_content_success");            
            }else{
                this.element.switchClass("contribute_content_success", "contribute_content_error");            
            }
            this.element.html(data.message);
            document.body.scrollTop = document.documentElement.scrollTop = 10;
            try{ document.getElementById('load').style.display="none";}catch (err){}
            document.body.style.cursor = 'auto';            	
        }
    });
})(jQuery);