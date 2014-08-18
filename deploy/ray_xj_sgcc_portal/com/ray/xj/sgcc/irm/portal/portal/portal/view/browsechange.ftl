<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<title>任务管理系统</title>
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
#chartDivs .sec2  {width:290px}
#chartDivs .sec3  {width:290px;}
#chartDivs .sec4  {width:290px;}
#chartDivs .sec5  {width:290px;}
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
</head>
<body>
<div id="pageContent"><div id="chartDivs">
	<#include "/com/ray/nwpn/itsm/report/common/view/nav.ftl">
	<#include "/com/ray/nwpn/itsm/report/common/view/script.ftl">
	<div class="radios"  id="otabs">
		所属分类：
		<a href='#' onclick="page_query('all')" class="<#if data.type=='all'>active</#if>">全部</a>&nbsp;&nbsp;
		<a href='#' onclick="page_query('yyxt')" class="<#if data.type=='yyxt'>active</#if>">应用系统</a>&nbsp;&nbsp;
		<!--<a href='#' onclick="page_query('fyyxt')">非应用系统</a>-->
	</div>
	
	<div class="section sec1">
	<h2><span class="title rep_change_bgnqs">变更年总量统计趋势图</span><span class="more"></span></h2>
	<div class="p8" id="rep_change_bgnqs">
	</div>
	
	<div class="section sec2 sLeft">
	<h2><span class="title tab_change_bgly">变更按来源统计表</span><span class="more"></span></h2>
	<div class="p8" id="tab_change_bgly">
	</div>
	</div>
	
	<div class="section sec3 sLeft">
	<h2><span class="title tab_change_bgjg">变更按结果统计表</span><span class="more"></span></h2>
	<div class="p8" id="tab_change_bgjg">
	</div>
	</div>
	
</div>
</div>

<script>
var ctype = '${data.type}';
function page_load_bgnqs_chart(ctype)
{
	xdate = Date.parse(mform.enddate.value);
	ydate = Date.parse(mform.enddate.value);	
	var _begindate = pub_work_year_begin_date(xdate,_sys_month);
	var _enddate = pub_work_year_end_date(ydate,_sys_month);

	var bbegindate = pub_date_format_value(_begindate);
	var benddate = pub_date_format_value(_enddate);
	
	if(ctype == "yyxt")
	{
		page_load_chart("deepen.change.bgnqs", "change_bgnqs", 0, "MSLine", new Array("type","bbegindate","benddate"), new Array("yyxt",bbegindate,benddate));
	}else if(ctype == "all")
	{
		page_load_chart("change.bgnqs", "change_bgnqs", 0, "MSLine", new Array("bbegindate","benddate"), new Array(bbegindate,benddate));
	}
}

function page_load_bglx_table()
{
	page_load_table("${base}/module/app/report/deepen/change/bgly/report_tab_yyxt_bgly.action", "change_bgly",0, "", "");
}

function page_load_bgjg_table()
{
	page_load_table("${base}/module/app/report/deepen/change/bgjg/report_tab_yyxt_bgjg.action", "change_bgjg",0, "", "");
}

function page_query(type)
{
	ctype = type;
	page_load_bgnqs_chart(ctype);
	page_load_bglx_table();
	page_load_bgjg_table();
}
function page_load()
{
	page_load_bgnqs_chart(ctype);
	page_load_bglx_table();
	page_load_bgjg_table();
}

page_load();

</script>

</body>
</html>
