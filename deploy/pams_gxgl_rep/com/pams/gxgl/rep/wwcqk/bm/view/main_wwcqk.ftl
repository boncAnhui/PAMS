<!DOCTYPE HTML>
<html>
<head>
<#include "/decorator/include/include.ftl">
<script type="text/javascript">
jQuery(function($){
/////////////////////////////////////



/////////////////////////////////////
})
</script>
<style type="text/css">
.sec1 {width:90%;float:left;margin-right:10px;}
</style>
</head>
<body><div id="gContainer">

<#import "com/ray/nwpn/itsm/report/common/view/macros.ftl" as pub_macros>
<@pub_macros.showheader repname="信息共享未完成情况(部门)指标统计" />

<#include "/com/ray/nwpn/itsm/report/common/view/navquery.ftl">

<div style="padding:10px;">
<form id="form_advsearch" method="post">
	<input type="hidden" name="begindate">
	<input type="hidden" name="enddate">
	<input type="hidden" name="internal" value="${arg.internal}">	
</form>
</div>

<br />
<div class="section sec1">
<h2><span class="title tab_wwcqk">信息共享未完成情况(部门)指标统计</span><span class="more"></span></h2>
<div class="p8" id="tab_wwcqk">
</div>
</div>

<#include "/com/ray/nwpn/itsm/report/common/view/script.ftl">

<script>

function page_load_wwcqk_table()
{
	page_load_table("${base}/module/pams/gxgl/rep/wwcqk/bm/rep_tab_wwcqk.action", "wwcqk", 0, new Array("internal"), new Array("${arg.internal}"));
}

function page_load()
{
	page_load_wwcqk_table();
}

function page_query_export()
{
	form_advsearch.target = "_blank";
	form_advsearch.begindate.value = mform.begindate.value;
	form_advsearch.enddate.value = mform.enddate.value;
	form_advsearch.action = "${base}/module/pams/gxgl/rep/wwcqk/bm/rep_xls_wwcqk.action";
	form_advsearch.submit();
}
page_load();

</script>

</div></body>
</html>
