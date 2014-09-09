<html>
<script src="script/jquery-1.11.1.js"></script>
<script>

$(document).ready(function(){
	  $("button").click(function(){
	    $("#div1").load("getReq");
	  });
	});
</script>
</head>
<body>

<p class="click">If you click on me, I will disappear not others.</p>
<p>Click me away!</p>
<p>Click me too!</p>


<h5>Csp address validation</h5>

<div id="div1"><h2>CSP request</h2></div>
<button>Get Req Content</button>
</body>
</html>
