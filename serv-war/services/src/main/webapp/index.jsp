<html>
<head>
<script src="script/jquery-1.11.1.js"></script>
<script>

$(document).ready(function() {
    $("#driver").click(function(event){
        $.getJSON('getReq', function(jd) {
           $('#stage').html('<p> Message: ' + jd.message + '</p>');
           $('#stage').append('<p>requestId : ' + jd.requestId+ '</p>');
       //    $('#stage').append('<p> Message: ' + jd.message + '</p>');
           $("#captImg").attr("src", jd.message);
           $("#reqId").attr("value", jd.requestId);          
        });
    });
    
   
    $("#sender").click(function(event){
  
 
    	
    	$.post( "sendResp", { reqId:  $("#reqId").attr("value"), text: $("#captResp").val() })
    	  .done(function( data ) {
  	       alert( "Data Loaded: " + data );
    	  });
    	
    	
    });
   
    
 });
 </script>
</head>
<body>
 <p>Click on the button to load result.html file:</p>
 <div id="stage" style="background-color:grey;">
        STAGE
 </div>
 
 
 <div>
      <img id="captImg" src="" alt="Sample image" />
   </div>
 <input type="button" id="driver" value="Load Data" />
 
 <input id="captResp" type="text" />
 <input id="reqId" type="hidden" />
 <input type="button" id="sender" value="Send Data" />

 
 
</body>
</html>

