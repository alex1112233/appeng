<html>
<head>
<script src="script/jquery-1.11.1.js"></script>
<script>

var contentUrl;

$(document).ready(function() {
    $("#driver").click(function(event){
    	 /*     $.getJSON('getReq', function(jd) {
           $('#stage').html('<p> Message: ' + jd.message + '</p>');
           $('#stage').append('<p>requestId : ' + jd.requestId+ '</p>');
       //    $('#stage').append('<p> Message: ' + jd.message + '</p>');
       //    $("#myimg").attr("src", jd.message);
       contentUrl = jd.message; */
       $.get( 
    		   "http://google.be",               
               function(data) {
                  $('#result').html(data);
               }

 //           );
        });
        
    });
 });
 </script>
</head>
<body>
 <p>Click on the button to load result.html file:</p>
 <div id="stage" style="background-color:blue;">
        STAGE
 </div>
 <div>
  <span id="result"> </span>
      <img id="myimg" src="" alt="Sample image" />
   </div>
 <input type="button" id="driver" value="Load Data" />
</body>
</html>

