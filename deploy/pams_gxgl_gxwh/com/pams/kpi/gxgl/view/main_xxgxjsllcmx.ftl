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
<@pub_macros.showheader repname="信息共享及时率指标统计" />

<#include "/com/ray/nwpn/itsm/report/common/view/nav.ftl">

<br />
<div class="section sec1">
<h2><span class="title tab_xxgxjsllcmx">信息共享及时率指标统计（流程明细统计）</span><span class="more"></span></h2>
<div class="p8" id="tab_xxgxjsllcmx">
</div>
</div>

<#include "/com/ray/nwpn/itsm/report/common/view/script.ftl">

<script>

function page_load_xxgxjsl_table()
{
	page_load_table("${base}/module/pams/kpi/gxgl/kpi_kpi_xxgxjsllcmx.action", "xxgxjsllcmx", 0, new Array("loginname"), new Array("${arg.loginname}"));
}

function page_load()
{
	page_load_xxgxjsl_table();
}

page_load();

</script>

</div></body>
</html>
