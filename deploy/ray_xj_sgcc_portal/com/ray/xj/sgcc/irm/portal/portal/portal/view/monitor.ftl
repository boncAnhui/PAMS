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
jQuery(function($){
/////////////////////////////////////



/////////////////////////////////////
})

function redirctmoduleurl(a,url){
	//location=url;
	openwin(url)
}

function page_change_model(model)
{
	window.location = "portal_welcome.action?pmodel=" + model;
}


function page_change_model(model,type)
{
	window.location = "portal_welcome.action?pmodel=" + model +"&type="+type;
}

function page_change_browse(ccate)
{
	window.location = "portal_browse.action?ccate=" + ccate;
}

</script>
</head>
<body>

<div id="siteWizard">
	<ul>
		<li onclick="page_change_model('welcome')">个人工作平台</li>
		<li onclick="page_change_model('manage')">管理平台</li>
		<#--<li onclick="page_change_model('managerep')">管理平台图表</li>-->
		<li class="c" onclick="page_change_model('deepen','all')">相关业务查询</li>
		<li onclick="page_change_model('stat')">报表统计</li>
		<li onclick="page_change_browse('show')">系统拓扑</li>
	</ul>
</div>

<#include "/com/ray/nwpn/itsm/report/common/view/nav.ftl">
<#import "com/ray/nwpn/itsm/report/common/view/macros.ftl" as pub_macros>

<div id="pageContainer"><div id="guageDivs">

<div class="section sec1">
<h2><span class="title rep_monitor_event_dhzxl">电话占线率</span><span class="more"></span></h2>
<div class="p8" id="rep_monitor_event_dhzxl">
</div>
</div>

<div class="section sec2">
<h2><span class="title rep_monitor_event_zxjjl">在线解决率</span><span class="more"></span></h2>
<div class="p8" id="rep_monitor_event_zxjjl">
</div>
</div>

<div class="section sec3">
<h2><span class="title rep_monitor_event_yxjjl">一线解决率</span><span class="more"></span></h2>
<div class="p8" id="rep_monitor_event_yxjjl">
</div>
</div>

<div class="section sec4">
<h2><span class="title rep_monitor_event_sjgbl">事件关闭率</span><span class="more"></span></h2>
<div class="p8" id="rep_monitor_event_sjgbl">
</div>
</div>

<#--
<div class="section sec1">
<h2><span class="title rep_monitor_event_pdgd">排队工单比率</span><span class="more"></span></h2>
<div class="p8" id="rep_monitor_event_pdgd">
</div>
</div>
-->

<div style="clear:both;height:50px;"></div>

</div>

<#include "/com/ray/nwpn/itsm/report/common/view/script.ftl">

<script>

function page_load_dhzxl_chart()
{
	page_load_chart("monitor.event.dhzxl", "monitor_event_dhzxl", 0, "AngularGauge", "", "");
}

function page_load_zxjjl_chart()
{
	page_load_chart("monitor.event.zxjjl", "monitor_event_zxjjl", 0, "AngularGauge", "", "");
}

function page_load_yxjjl_chart()
{
	page_load_chart("monitor.event.yxjjl", "monitor_event_yxjjl", 0, "AngularGauge", "", "");
}

function page_load_sjgbl_chart()
{
	page_load_chart("monitor.event.sjgbl", "monitor_event_sjgbl", 0, "AngularGauge", "", "");
}

function page_load()
{
	page_load_dhzxl_chart();
	page_load_zxjjl_chart();
	page_load_yxjjl_chart();
	page_load_sjgbl_chart();
}

$(document).ready(function()
{
	window.setInterval(function(){page_load();}, 600000);
});

page_load();

</script>

</body>
</html>
