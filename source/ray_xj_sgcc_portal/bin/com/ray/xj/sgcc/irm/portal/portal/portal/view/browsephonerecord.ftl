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
	<div class="radios" id="otabs">
		所属分类：
		<a href='#' onclick="page_query()" class="<#if data.type=='all'>active</#if>">全部</a>&nbsp;&nbsp;
		<a href='#' onclick="page_query()" class="<#if data.type=='fyyxt'>active</#if>">非应用系统</a>
	</div>
	
	<div class="section sec1">
	<h2><span class="title rep_phonerecord_dhnqs">热线来电年总量趋势图</span><span class="more"></span></h2>
	<div class="p8" id="rep_phonerecord_dhnqs">
	</div>
	
	<div class="section sec2 sLeft">
	<h2><span class="title tab_phonerecord_rxdh">热线电话统计表</span><span class="more"></span></h2>
	<div class="p8" id="tab_phonerecord_rxdh">
	</div>
	</div>
	
	<div class="section sec3 sLeft">
	<h2><span class="title tab_phonerecord_rxdhlx">热线电话类型统计表</span><span class="more"></span></h2>
	<div class="p8" id="tab_phonerecord_rxdhlx">
	</div>
	</div>
	
	<div class="section sec4 sLeft">
	<h2><span class="title tab_phonerecord_ldbm">来电部门统计表</span><span class="more"></span></h2>
	<div class="p8" id="tab_phonerecord_ldbm">
	</div>
	</div>
	
	<div class="section sec4 sLeft">
	<h2><span class="title tab_event_dh_ldzw">客户职位统计表</span><span class="more"></span></h2>
	<div class="p8" id="tab_event_dh_ldzw">
	</div>
	</div>
	
</div>
</div>

<script>
var ctype = '${data.type}';
function page_load_dhnqs_chart()
{
	xdate = Date.parse(mform.enddate.value);
	ydate = Date.parse(mform.enddate.value);	
	var _begindate = pub_work_year_begin_date(xdate,_sys_month);
	var _enddate = pub_work_year_end_date(ydate,_sys_month);

	var bbegindate = pub_date_format_value(_begindate);
	var benddate = pub_date_format_value(_enddate);
	
	page_load_chart("event.dhnqs", "phonerecord_dhnqs", 0, "MSLine", new Array("bbegindate","benddate"), new Array(bbegindate,benddate));
}

function page_load_dhrx_table()
{
	page_load_table("${base}/module/app/report/event/dhrx/report_tab_dhrx.action", "phonerecord_rxdh", 0, 0,0);
}

function page_load_dhrxlx_table()
{
	page_load_table("${base}/module/app/report/event/dhrx/report_tab_dhrxlx.action", "phonerecord_rxdhlx", 0, 0,0);
}

function page_load_ldbm_table()
{
	page_load_table("${base}/module/app/report/event/dh/report_tab_ldbm.action", "phonerecord_ldbm", 0, 0,0);
}

function page_load_ldzw_table()
{
	page_load_table("${base}/module/app/report/event/dh/report_tab_ldzw.action", "event_dh_ldzw", 0, 0,0);
}

function page_query()
{
	page_load_dhnqs_chart();
	page_load_dhrx_table();
	page_load_dhrxlx_table();
	page_load_ldbm_table();
	page_load_ldzw_table();
}
function page_load()
{
	page_load_dhnqs_chart();
	page_load_dhrx_table();
	page_load_dhrxlx_table();
	page_load_ldbm_table();
	page_load_ldzw_table();
}

page_load();

function page_openlx(ptype)
{
	var url ="${base}/module/app/business/customer/phonerecord/phonerecord_browselx.action?_searchname=phonerecord.browselx&ptype="
	url += ptype;
	url += "&begcreatetime=";
	url += mform.begindate.value;
	url += "&endcreatetime=";
	url += mform.enddate.value;
	openwin(url,'dhpage',pub_width_large, pub_height_large);
}

</script>

</body>
</html>
