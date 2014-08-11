<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>首页</title>
<link rel="shortcut icon" href="favicon.ico" />
<script type="text/javascript" src="${base}/themes/default/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${base}/themes/default/gex.js"></script>
<script type="text/javascript" src="${base}/themes/default/main.js"></script>
<script type="text/javascript" src="${base}/themes/default/home.js"></script>
<style type="text/css">@import url(${base}/themes/default/main.css);</style>
<script type="text/javascript">
var homeJson;

$.ajax({
	url:'${base}/module/irm/portal/portal/portal/portal_subject.action',
//	data:{usrid:1234},
	cache:false,
	async:false,
	success:function(d){
	
		eval('homeJson=('+d+')'); 
	}
})

</script>
<base target="_blank" />
</head>
<body class="homepage">
<div style="padding:10px;">
<!--home start-->
<div id="homepage"></div>
<!--home end-->
</div>
</body>
</html>