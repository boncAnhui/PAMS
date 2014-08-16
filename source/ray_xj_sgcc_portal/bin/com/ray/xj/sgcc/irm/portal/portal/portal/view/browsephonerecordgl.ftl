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

<script type="text/javascript">
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

function redirctmoduleurl(a,url){
	//location=url;
	openwin(url)
}

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
		<a href='#' onclick="page_query()" class="<#if data.type=='all'>active</#if>">全部</a>&nbsp;&nbsp;
		<a href='#' onclick="page_query()" class="<#if data.type=='fyyxt'>active</#if>">非应用系统</a>&nbsp;&nbsp;
	</div>
	
	<div id="wrapper">

	<div class="section sec1">
	<h2><span class="title tab_phonerecord_dhywgl">后续相关业务统计表</span><span class="more"></span></h2>
	<div class="p8" id="tab_phonerecord_dhywgl">
	</div>
	</div>
	
	<div class="section sec2">
	<h2><span class="title rep_phonerecord_dhywgl">后续相关业务统计图</span><span class="more"></span></h2>
	<div class="p8" id="rep_phonerecord_dhywgl">
	</div>
	
	</div>
	
</div>



<script>
var ctype = '${data.type}';
function page_load_jxywld_table()
{
	page_load_table("${base}/module/app/report/event/ywld/report_tab_dhywld.action", "phonerecord_dhywgl",0, "", "");
}

function page_load_jxywld_chart()
{
	page_load_chart("dh.ywld", "phonerecord_dhywgl", 0, "Pie3D", 0,0);
}

function page_query(type)
{
	page_load_jxywld_table();
	page_load_jxywld_chart();
}
function page_load()
{
	page_load_jxywld_table();
	page_load_jxywld_chart();
}

page_load();

</script>

</body>
</html>
