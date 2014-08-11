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

<style type="text/css">
#chartDivs  .sLeft {float:left;margin-right:10px;}
#chartDivs  .sRight {float:left;}
#chartDivs .sec1  {height:260px;width:180px;}
#chartDivs .sec2  {height:260px;width:660px}
#chartDivs .sec3  {height:160px;width:550px;}
#chartDivs .sec4  {height:160px;width:290px;}

#chartDivs .sec5  {height:160px;width:400px;}
#chartDivs .sec6  {height:160px;width:440px}

#chartDivs .sec7  {height:260px;width:240px;}
#chartDivs .sec8  {height:260px;width:600px}
</style>
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
if('${arg.popupclass}'=='closed'|| ${data.bulletins?size} <= 0)
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
	openwin(url)
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
</script>
</head>
<body>

<div id="siteWizard">
	<ul>
		<li onclick="page_change_model('welcome')">个人工作平台</li>
		<li onclick="page_change_model('manage')">管理平台</li>
		<#--<li onclick="page_change_model('managerep')">管理平台图表</li>-->
		<li onclick="page_change_model('deepen','yyxt')">相关业务查询</li>
		<li class="c" onclick="page_change_model('stat')">报表统计</li>
		<li onclick="page_change_browse('show')">系统拓扑</li>
	</ul>
</div>

<#include "/com/ray/nwpn/itsm/report/common/view/nav.ftl">
<#import "com/ray/nwpn/itsm/report/common/view/macros.ftl" as pub_macros>

<#--
<div style="text-align:right;"><select id="select2content" style="*width:200px;"></select></div>
-->

<div id="pageContent"><div id="chartDivs">

<div class="section sec1 sLeft">
<h2><span class="title tab_event_sjzl_sjzl">本部事件总量统计表</span><span class="more"></span></h2>
<div class="p8" id="tab_event_sjzl_sjzl">
</div>
</div>

<div class="section sec2 sRight">
<h2><span class="title rep_event_sjzl_sjzl">本部事件总量统计图</span><span class="more"><@pub_macros.showspan chartname = "event.sjzl.sjzl" pname="event_sjzl_sjzl" /></span></h2>
<div class="p8" id="rep_event_sjzl_sjzl">
</div>
</div>

<div style="clear:both;height:20px;"></div>

<div class="section sec3 sLeft">
<h2><span class="title tab_event_sjfl_sjfl">本部事件分类统计表</span><span class="more"></span></h2>
<div class="p8" id="tab_event_sjfl_sjfl">
</div>
</div>

<div class="section sec4 sRight">
<h2><span class="title rep_event_sjfl_sjfl">本部事件分类统计图</span><span class="more"><@pub_macros.showspan chartname = "event.sjfl.sjfl" pname="event_sjfl_sjfl" /></span></h2>
<div class="p8" id="rep_event_sjfl_sjfl">
</div>
</div>

<div style="clear:both;height:40px;"></div>

<div class="section sec5 sLeft" >
<h2><span class="title tab_event_gz_gz">本部工作统计表</span><span class="more"></span></h2>
<div style="overflow:auto;height:160px;width:400px;">
<div class="p8" id="tab_event_gz_gz">
</div>
</div>
</div>

<div class="section sec6 sRight">
<h2><span class="title rep_event_gz_gz">本部工作统计图</span><span class="more"><@pub_macros.showspan chartname = "event.gz.gz" pname="event_gz_gz" /></span></h2>
<div class="p8" id="rep_event_gz_gz">
</div>
</div>

<div style="clear:both;height:40px;"></div>

<div class="section sec7 sLeft">
<h2><span class="title tab_event_dhrx_dhrx">本部热线电话统计表</span><span class="more"></span></h2>
<div class="p8" id="tab_event_dhrx_dhrx">
</div>
</div>

<div class="section sec8 sRight">
<h2><span class="title rep_event_dhrx_dhrx">本部热线电话统计图</span><span class="more"><@pub_macros.showspan chartname = "event.dhrx.dhrx" pname="event_dhrx_dhrx" /></span></h2>
<div class="p8" id="rep_event_dhrx_dhrx">
</div>
</div>

<div style="clear:both;height:20px;"></div>

<div class="section sec1 sLeft" style="width:250px">
<h2><span class="title tab_event_sjzl_sjzl">陕西电力公司事件总量统计表</span><span class="more"></span></h2>
<div class="p8" id="tab_event_sjzl_sjzl_sx">
</div>
</div>

<div class="section sec2 sRight" style="width:580px">
<h2><span class="title rep_event_sjzl_sjzl">陕西电力公司事件总量统计图</span><span class="more"><@pub_macros.showspan chartname = "event.sjzl.sjzl" pname="event_sjzl_sjzl" /></span></h2>
<div class="p8" id="rep_event_sjzl_sjzl_sx">
</div>
</div>

<div style="clear:both;height:20px;"></div>

<div class="section sec3 sLeft">
<h2><span class="title tab_event_sjfl_sjfl">陕西电力公司事件分类统计表</span><span class="more"></span></h2>
<div class="p8" id="tab_event_sjfl_sjfl_sx">
</div>
</div>

<div class="section sec4 sRight">
<h2><span class="title rep_event_sjfl_sjfl">陕西电力公司事件分类统计图</span><span class="more"><@pub_macros.showspan chartname = "event.sjfl.sjfl" pname="event_sjfl_sjfl" /></span></h2>
<div class="p8" id="rep_event_sjfl_sjfl_sx">
</div>
</div>

<div style="clear:both;height:40px;"></div>

<div class="section sec5 sLeft">
<div style="overflow:auto;height:160px;width:400px;">
<h2><span class="title tab_event_gz_gz_sx">陕西电力公司工作统计表</span><span class="more"></span></h2>
<div class="p8" id="tab_event_gz_gz_sx">
</div>
</div>
</div>

<div class="section sec6 sRight">
<h2><span class="title rep_event_gz_gz">陕西电力公司工作统计图</span><span class="more"><@pub_macros.showspan chartname = "event.gz.gz" pname="event_gz_gz" /></span></h2>
<div class="p8" id="rep_event_gz_gz_sx">
</div>
</div>

<div style="clear:both;height:40px;"></div>

<div class="section sec7 sLeft">
<h2><span class="title tab_event_dhrx_dhrx">陕西电力公司热线电话统计表</span><span class="more"></span></h2>
<div class="p8" id="tab_event_dhrx_dhrx_sx">
</div>
</div>

<div class="section sec8 sRight">
<h2><span class="title rep_event_dhrx_dhrx">陕西电力公司热线电话统计图</span><span class="more"><@pub_macros.showspan chartname = "event.dhrx.dhrx" pname="event_dhrx_dhrx" /></span></h2>
<div class="p8" id="rep_event_dhrx_dhrx_sx">
</div>
</div>
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
</div>

<#include "/com/ray/nwpn/itsm/report/common/view/script.ftl">

<script>

function page_load_sjzl_table()
{
	page_load_table("${base}/module/app/report/event/sjzl/report_tab_sjzl.action", "event_sjzl_sjzl", 0, new Array("source"),new Array("XBDWYXGS"));
}

function page_load_sjzl_chart()
{
	page_load_chart("event.sjzl.sjzl", "event_sjzl_sjzl", 0, "Column3D", new Array("source"),new Array("XBDWYXGS"));
}

function page_load_sjfl_table()
{
	page_load_table("${base}/module/app/report/event/sjfl/report_tab_sjfl.action", "event_sjfl_sjfl", 0, new Array("ordernum","source"), new Array("01","XBDWYXGS"));
}

function page_load_sjfl_chart()
{
	page_load_chart("event.sjfl.sjfl", "event_sjfl_sjfl", 0, "MSColumn3D", new Array("ordernum","source"), new Array("01","XBDWYXGS"));
}

function page_load_gz_table()
{
	page_load_table("${base}/module/app/report/event/gz/report_tab_gz.action", "event_gz_gz", 0, new Array("source"),new Array("XBDWYXGS"));
}

function page_load_gz_chart()
{
	page_load_chart("event.gz.gz", "event_gz_gz", 0, "MSColumn3D", new Array("source"),new Array("XBDWYXGS"));
}

function page_load_dhrx_table()
{
	page_load_table("${base}/module/app/report/event/dhrx/report_tab_dhrx.action", "event_dhrx_dhrx", 0, new Array("source"),new Array("XBDWYXGS"));
}

function page_load_dhrx_chart()
{
	page_load_chart("event.dhrx.dhrx", "event_dhrx_dhrx", 0, "Column3D", new Array("source"),new Array("XBDWYXGS"));
}

function page_load_sjzl_table_sx()
{
	page_load_table("${base}/module/app/report/event/sjzl/report_tab_sjzl.action", "event_sjzl_sjzl_sx", 0, new Array("source"),new Array("SXDLGS"));
}

function page_load_sjzl_chart_sx()
{
	page_load_chart("event.sjzl.sjzl", "event_sjzl_sjzl_sx", 0, "Column3D", new Array("source"),new Array("SXDLGS"));
}

function page_load_sjfl_table_sx()
{
	page_load_table("${base}/module/app/report/event/sjfl/report_tab_sjfl.action", "event_sjfl_sjfl_sx", 0, new Array("ordernum","source"), new Array("01","SXDLGS"));
}

function page_load_sjfl_chart_sx()
{
	page_load_chart("event.sjfl.sjfl", "event_sjfl_sjfl_sx", 0, "MSColumn3D", new Array("ordernum","source"), new Array("01","SXDLGS"));
}

function page_load_gz_table_sx()
{
	page_load_table("${base}/module/app/report/event/gz/report_tab_gz.action", "event_gz_gz_sx", 0, new Array("source"),new Array("SXDLGS"));
}

function page_load_gz_chart_sx()
{
	page_load_chart("event.gz.gz", "event_gz_gz_sx", 0, "MSColumn3D", new Array("source"),new Array("SXDLGS"));
}

function page_load_dhrx_table_sx()
{
	page_load_table("${base}/module/app/report/event/dhrx/report_tab_dhrx.action", "event_dhrx_dhrx_sx", 0, new Array("source"),new Array("SXDLGS"));
}

function page_load_dhrx_chart_sx()
{
	page_load_chart("event.dhrx.dhrx", "event_dhrx_dhrx_sx", 0, "Column3D", new Array("source"),new Array("SXDLGS"));
}



function page_load()
{
	page_load_sjzl_table();
	page_load_sjzl_chart();
	
	page_load_sjfl_table();
	page_load_sjfl_chart();	

	page_load_gz_table();
	page_load_gz_chart();	

	page_load_dhrx_table();
	page_load_dhrx_chart();
	
	page_load_sjzl_table_sx();
	page_load_sjzl_chart_sx();
	
	page_load_sjfl_table_sx();
	page_load_sjfl_chart_sx();	

	page_load_gz_table_sx();
	page_load_gz_chart_sx();	

	page_load_dhrx_table_sx();
	page_load_dhrx_chart_sx();
}

page_load();

</script>

</div></body>
</html>
