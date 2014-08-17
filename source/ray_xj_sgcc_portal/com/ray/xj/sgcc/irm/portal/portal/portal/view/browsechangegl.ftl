<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<title>信息运维管理系统</title>
<script type="text/javascript" src="${base}/themes/default/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${base}/themes/default/gex.js"></script>
<script type="text/javascript" src="${base}/themes/default/main.js"></script>
<script type="text/javascript" src="/irm/resource/default/script/public_complete.js"></script>
<style type="text/css">@import url(${base}/themes/default/main.css);</style>
<style type="text/css">@import url(${base}/themes/default/welcome.css);</style>


<style type="text/css">
#chartDivs  .sLeft {float:left;margin-right:10px;}
#chartDivs  .sRight {float:left;}
#chartDivs .sec1  {height:260px;}
#chartDivs .sec2  {height:260px;width:290px}
#chartDivs .sec3  {height:260px;width:290px;}
#chartDivs .sec4  {height:260px;width:290px;}
#chartDivs .sec5  {height:260px;width:290px;}
</style>

<style>
#otabs {padding:10px;}
#otabs a {display:inline-block;*display:inline;*zoom:1;line-height: 25px;margin-right:8px;padding:0 8px;}
#otabs a.active {background:#21759b;color: #fff;}

</style>
<script>
jQuery(function($){
$('#otabs').on('click','a',function(){
$(this).siblings().removeClass('active');
$(this).addClass('active');
})
})

jQuery(function($){
/////////////////////////////////////
$('.changetype').each(function(){
	$(this).removeClass('c');
})
var _ctype = '${arg._ctype}';
if(_ctype=="Y")
{
	$('#site-nav .changetype[data-type="Y"]').addClass("c");
}
else if(_ctype=="HY")
{
	$('#site-nav .changetype[data-type="HY"]').addClass("c");
}
else if(_ctype=="Q")
{
	$('#site-nav .changetype[data-type="Q"]').addClass("c");
}
else if(_ctype=="M")
{
	$('#site-nav .changetype[data-type="M"]').addClass("c");
}
else if(_ctype=="W")
{
	$('#site-nav .changetype[data-type="W"]').addClass("c");
}
else if(_ctype=="D")
{
	$('#site-nav .changetype[data-type="D"]').addClass("c");
}


/////////////////////////////////////
})
</script>
<style type="text/css">
.sec1 {width:420px;float:left;margin-right:10px;}
.sec2 {width:420px;float:left;}
</style>
<style>
#otabs {padding:10px;}
#otabs a {display:inline-block;*display:inline;*zoom:1;line-height: 25px;margin-right:8px;padding:0 8px;}
#otabs a.active {background:#21759b;color: #fff;}

</style>
<script>
jQuery(function($){
$('#otabs').on('click','a',function(){
$(this).siblings().removeClass('active');
$(this).addClass('active');
})
})
</script>
</head>
<body>

<div id="pageContent"><div id="chartDivs">
	<#include "/com/ray/nwpn/itsm/report/common/view/nav.ftl">
	<#include "/com/ray/nwpn/itsm/report/common/view/script.ftl">
	<div class="radios" id="otabs">
		所属分类：
		<a href='#' onclick="page_query('all')" class="<#if data.type=='all'>active</#if>">全部</a>&nbsp;&nbsp;
		<a href='#' onclick="page_query('yyxt')" class="<#if data.type=='yyxt'>active</#if>">应用系统</a>&nbsp;&nbsp;
	</div>
	
	<div id="wrapper">

	<div class="section sec1">
	<h2><span class="title tab_change_bgywgl">后续相关业务统计表</span><span class="more"></span></h2>
	<div class="p8" id="tab_change_bgywgl">
	</div>
	</div>
	
	<div class="section sec2">
	<h2><span class="title rep_change_bgywgl">后续相关业务统计图</span><span class="more"></span></h2>
	<div class="p8" id="rep_change_bgywgl">
	</div>
	
	</div>
	
</div>



<script>
var ctype = '${data.type}';
function page_load_bgywld_table(type)
{
	if(type == 'yyxt')
	{
		page_load_table("${base}/module/app/report/deepen/change/ywld/report_tab_yyxt_bgywld.action", "change_bgywgl",0, "", "");
	}
	else if(type == 'all')
	{
		page_load_table("${base}/module/app/report/deepen/change/ywld/report_tab_yyxt_bgywld.action", "change_bgywgl",0, "", "");
	}
}

function page_load_bgywld_chart(type)
{
	if(type == 'yyxt')
	{
		page_load_chart("change.ywld", "change_bgywgl", 0, "Pie3D", new Array("type"), new Array("yyxt"));
	}
	else if(type == 'all')
	{
		page_load_chart("change.ywld", "change_bgywgl", 0, "Pie3D", new Array("type"), new Array("all"));
	}
}

function page_query(type)
{
	ctype = type;
	page_load_bgywld_table(ctype);
	page_load_bgywld_chart(ctype);
}
function page_load()
{
	page_load_bgywld_table(ctype);
	page_load_bgywld_chart(ctype);
}

page_load();

</script>

