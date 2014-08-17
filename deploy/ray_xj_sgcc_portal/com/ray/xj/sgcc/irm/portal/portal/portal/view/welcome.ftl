<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<title>首页</title>
<script type="text/javascript" src="${base}/themes/default/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${base}/themes/default/gex.js"></script>
<script type="text/javascript" src="${base}/themes/default/main.js"></script>
<style type="text/css">@import url(${base}/themes/default/main.css);</style>
<style type="text/css">@import url(${base}/themes/default/welcome.css);</style>


<script type="text/javascript">

//需要的content list

//实际content url（数据库没有，所以在这里手写全部）
var contentArr ={'alltodo':'${base}/module/irm/portal/portal/portal/portal_getAllToDo.action'};



jQuery(function($){
/////////////////////////////////////

function loadContent(){
	var obj=$('#pageContent');
	
	obj.empty();
	$.ajax({
		url:'${base}/module/irm/portal/portal/portal/portal_getAllToDo.action',
		cache:false,
		async:false,
		success:function(d){
			//alert(d);
			//console.log(obj)
			obj.append('<div class="section sec1" ><div class="p8">'+d+'</div></div>');
			$('table.gGrid tbody tr:odd').addClass('odd');
		}
	})
};

loadContent();

//remove portalMenu when homepage
$('#portalMenu',window.parent.document).empty();

/////////////////////////////////////
})

function redirctmoduleurl(a,url){
	//location=url;
	openwin(url,'opennew',pub_width_mid,pub_height_mid);
}

</script>
</head>
<body>

<div id="pageContainer">

<div id="pageContent">



</div>

</div>

</body>
</html>
