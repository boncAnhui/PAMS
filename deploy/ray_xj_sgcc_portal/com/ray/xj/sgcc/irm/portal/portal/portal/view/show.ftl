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
<script type="text/javascript" src="${base}/themes/default/portal.js"></script>
<style>
#gsbrpopup {
	position:fixed;
	bottom:0;right:0;_position:absolute;background: #fff;border:solid 1px #ddd;font-size: 12px;*width:380px;}
#gsbrpopup .title {background: 	#ddd;line-height: 30px;padding:0 8px;cursor:pointer;color:#666;white-space: nowrap;}

#gsbrpopup .title .collapse {float:right;border:solid 6px #999;border-right-color:transparent;border-left-color:transparent;border-bottom: 0;margin-top: 	12px; margin-left: 20px;height:0;overflow:hidden;}

#gsbrpopup .title .collapse {_border-right-color:#ddd;_border-left-color:#ddd;}

#gsbrpopup .body {height:200px;overflow: auto}

#gsbrpopup.closed .body {display: none;}
#gsbrpopup.closed .title .collapse {border-right-color:transparent;border-left-color:transparent;border-bottom: solid 6px #999;border-top:0;_border-right-color:#ddd;_border-left-color:#ddd;}
#gsbrpopup ul {margin: 0;	padding: 10px;	list-style-type: none;}
#gsbrpopup ul li {margin: 0;	padding: 0;	list-style-type: none;line-height: 20px;white-space: nowrap;}
#gsbrpopup ul li .date {float: right;color:#999;padding-left:40px;}
#gsbrpopup ul li a {display:inline-block;*display:inline;*zoom:1;white-space:nowrap;width:20em;overflow:hidden;text-overflow:ellipsis;color:#333;text-decoration: none;}
</style>
<script>
jQuery(function($){
////////////////////////

$('#gsbrpopup .title').on('click',function(){
	$('#gsbrpopup').toggleClass('closed');
	
})
if('${arg.popupclass}'=='closed' || ${data.bulletins?size} <= 0)
{
	$('#gsbrpopup').toggleClass('closed');
}
////////////////////////
})
</script>
<script type="text/javascript">

jQuery(function($){
/////////////////////////////////////



/////////////////////////////////////
})

function redirctmoduleurl(a,url){
	//location=url;
	openwin(url);
}

function page_change_model(model)
{
	window.location = "portal_welcome.action?pmodel=" + model+"&popupclass="+$('#gsbrpopup').attr('class');
}


function page_change_model(model,type)
{
	window.location = "portal_welcome.action?pmodel=" + model +"&type="+type+"&popupclass="+$('#gsbrpopup').attr('class');
}

function page_change_browse(ccate)
{
	window.location = "portal_browse.action?ccate=" + ccate+"&popupclass="+$('#gsbrpopup').attr('class');
}

var navigationJSON=[{name:'${data.navitem}'}];
var portal_ID='${data.ccate}';
var portal_ajax_url='${base}/module/irm/portal/portal/portal/portal_browsejson.action';
</script>
</head>
<body>


<div id="siteWizard">
	<ul>
		<ul>
		<li onclick="page_change_model('welcome')">个人工作平台</li>
		<li onclick="page_change_model('manage')">管理平台</li>
		<#--<li onclick="page_change_model('managerep')">管理平台图表</li>-->
		<li onclick="page_change_model('deepen','all')">相关业务查询</li>
		<li onclick="page_change_model('stat')">报表统计</li>
		<li class="c" onclick="page_change_browse('show')">系统拓扑</li>
	</ul>
	</ul>
</div>
<div id="pageContent">
<div id="gsbrpopup">
	<div class="title"><span class="collapse"></span>公告栏</div>
	<div class="body">
		<ul>
		<#list data.bulletins as bulletin>
			<li><span class="date">${bulletin.createtime?string('yyyy-MM-dd')}</span><a href="javascript:void(0);" title='${bulletin.description}'>${bulletin.description}</a></li>
		</#list>
		</ul>
	</div>
</div>
<div id="pageContainer" class="portalPage"></div>
</div>
</body>
</html>