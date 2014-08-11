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
#chartDivs .sec2  {width:290px;}
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
	<div class="radios" id="otabs">
		所属分类：
		<a href='#' onclick="page_query('all')" class="<#if data.type=='all'>active</#if>">全部</a>&nbsp;&nbsp;
		<a href='#' onclick="page_query('yyxt')" class="<#if data.type=='yyxt'>active</#if>">应用系统</a>&nbsp;&nbsp;
		<a href='#' onclick="page_query('fyyxt')" class="<#if data.type=='fyyxt'>active</#if>">非应用系统</a>
	</div>
	
	<div class="section sec1">
	<h2><span class="title rep_equip_year">备品备件年使用统计趋势图</span><span class="more"></span></h2>
	<div class="p8" id="rep_equip_year">
	</div>
	
	<div class="section sec2 sLeft">
	<h2><span class="title tab_equip_ly">备品备件按来源统计表</span><span class="more"></span></h2>
	<div class="p8" id="tab_equip_ly">
	</div>
	</div>
	
	<div class="section sec3 sLeft">
	<h2><span class="title tab_equip_ckwp">备品备件按出库物品统计表</span><span class="more"></span></h2>
	<div class="p8" id="tab_equip_ckwp">
	</div>
	</div>
</div>
</div>

<script>
var ctype = '${data.type}';
function page_load_year_chart(type)
{
	xdate = Date.parse(mform.enddate.value);
	ydate = Date.parse(mform.enddate.value);	
	var _begindate = pub_work_year_begin_date(xdate,_sys_month);
	var _enddate = pub_work_year_end_date(ydate,_sys_month);

	var bbegindate = pub_date_format_value(_begindate);
	var benddate = pub_date_format_value(_enddate);
	
	if(type == "yyxt")
	{
		page_load_chart("equip.year", "equip_year", 0, "MSLine", new Array("type","bbegindate","benddate"), new Array("yyxt",bbegindate,benddate));
	}
	else if(type == "fyyxt")
	{
		page_load_chart("equip.year", "equip_year", 0, "MSLine", new Array("type","bbegindate","benddate"), new Array("fyyxt",bbegindate,benddate));
	}
	else if(type == "all")
	{
		page_load_chart("equip.year", "equip_year", 0, "MSLine", new Array("type","bbegindate","benddate"), new Array("all",bbegindate,benddate));
	}
}

function page_load_ly_table(type)
{
	if(type == 'yyxt')
	{
		page_load_table("${base}/module/app/report/deepen/equip/source/report_tab_equiply.action", "equip_ly",0, new Array("type"), new Array("yyxt"));
	}
	else if(type == 'fyyxt')
	{
		page_load_table("${base}/module/app/report/deepen/equip/source/report_tab_equiply.action", "equip_ly",0, new Array("type"), new Array("fyyxt"));
	}
	else if(type == 'all')
	{
		page_load_table("${base}/module/app/report/deepen/equip/source/report_tab_equiply.action", "equip_ly",0, new Array("type"), new Array("all"));
	}
	
}

function page_load_ckwp_table(type)
{
	if(type == 'yyxt')
	{
		page_load_table("${base}/module/app/report/deepen/equip/ckwp/report_tab_equipckwp.action", "equip_ckwp",0, new Array("type"), new Array("yyxt"));
	}
	else if(type == 'fyyxt')
	{
		page_load_table("${base}/module/app/report/deepen/equip/ckwp/report_tab_equipckwp.action", "equip_ckwp",0, new Array("type"), new Array("fyyxt"));
	}
	else if(type == 'all')
	{
		page_load_table("${base}/module/app/report/deepen/equip/ckwp/report_tab_equipckwp.action", "equip_ckwp",0, new Array("type"), new Array("all"));
	}
	
}

function page_query(type)
{
	ctype = type;
	page_load_year_chart(ctype);
	page_load_ly_table(ctype);
	page_load_ckwp_table(ctype);
}
function page_load()
{
	page_load_year_chart(ctype);
	page_load_ly_table(ctype);
	page_load_ckwp_table(ctype);
}

page_load();

</script>

</body>
</html>
