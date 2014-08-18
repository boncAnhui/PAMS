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
		<a href='#' onclick="page_query('all')" class="<#if data.type=='fyyxt'>active</#if>">非应用系统</a>&nbsp;&nbsp;
	</div>
	
	<div class="section sec1">
	<h2><span class="title rep_ctooperation_stagenum">项目年总趋势图</span><span class="more"></span></h2>
	<div class="p8" id="rep_ctooperation_jzynqs">
	</div>
	
	<div class="section sec2 sLeft">
	<h2><span class="title tab_ctooperation_stagenum">项目隶属情况统计表</span><span class="more"></span></h2>
	<div class="p8" id="tab_ctooperation_stagenum">
	</div>
	</div>

	
	<div class="section sec3 sLeft">
	<h2><span class="title tab_ctooperation_stagecharge">项目所属阶段统计表</span><span class="more"></span></h2>
	<div class="p8" id="tab_ctooperation_stagecharge">
	</div>
	</div>
	
	<div class="section sec4 sLeft">
	<h2><span class="title tab_ctooperation_docnum">项目进度情况统计表</span><span class="more"></span></h2>
	<div class="p8" id="tab_ctooperation_docnum">
	</div>
	</div>
	

</div>

<script>
var ctype = '${data.type}';

function page_load_jzynqs_chart()
{
	xdate = Date.parse(mform.enddate.value);
	ydate = Date.parse(mform.enddate.value);	
	var _begindate = pub_work_year_begin_date(xdate,_sys_month);
	var _enddate = pub_work_year_end_date(ydate,_sys_month);

	var bbegindate = pub_date_format_value(_begindate);
	var benddate = pub_date_format_value(_enddate);
	
	page_load_chart("ctooperation.ctooperation.jzynqs", "ctooperation_jzynqs", 0, "MSLine", new Array("bbegindate","benddate"), new Array(bbegindate,benddate));
}

function page_load_stagenum_table()
{
	page_load_table("${base}/module/app/report/ctooperation/subjection/report_tab_subjection.action", "ctooperation_stagenum",0, "", "");
}

function page_load_stagecharge_table()
{
	page_load_table("${base}/module/app/report/ctooperation/stage/report_tab_stage.action", "ctooperation_stagecharge",0, "", "");
}

function page_load_docnum_table()
{
	page_load_table("${base}/module/app/report/ctooperation/progress/report_tab_progress.action", "ctooperation_docnum",0, "", "");
}

function page_query()
{
    page_load_jzynqs_chart()
    page_load_stagenum_table();
	page_load_stagecharge_table();
	page_load_docnum_table();
}
function page_load()
{
    page_load_jzynqs_chart()
    page_load_stagenum_table();
	page_load_stagecharge_table();
	page_load_docnum_table();
}

function page_progress(projectnature,zczh)
{
	var begintime = $('#begindate').val();
	var endtime = $('#enddate').val();
	
	var url = "${base}/module/app/business/ctooperation/ctooperation/ctooperation_browsereportlist.action?_searchname=ctooperation.ctooperation.browsereportlist";
	url += "&projectnature=" + projectnature;
	url += "&zczh=" + zczh;
	url += "&begintime=" + begintime;
	url += "&endtime=" + endtime;
	
	openwin(url,'ctooperation_progress',pub_width_large, pub_height_large);
}

function page_subjection(projectnature,projectunder)
{
	var begintime = $('#begindate').val();
	var endtime = $('#enddate').val();
	
	$("#sform").submit();
	
	var url = "${base}/module/app/business/ctooperation/ctooperation/ctooperation_browsereportlist.action?_searchname=ctooperation.ctooperation.browsereportlist";
	url += "&projectnature=" + projectnature;
	url += "&projectunder=" + projectunder;
	url += "&begintime=" + begintime;
	url += "&endtime=" + endtime;
	openwin(url,'ctooperation_subjection',pub_width_large, pub_height_large);
}

function page_stage(stage,lcb)
{
	var url ="${base}/module/app/business/ctooperation/ctooperation/ctooperation_browsereportlist.action?_searchname=ctooperation.ctooperation.browsereportlist&from=report&stage=";
	url += stage ;
	url += "&lcb=";
	url += lcb;
	url += "&begintime=";
	url += mform.begindate.value;
	url += "&endtime=";
	url += mform.enddate.value;
	openwin(url,'listchargeid',pub_width_large, pub_height_large);
}

page_load();

</script>

</body>
</html>
