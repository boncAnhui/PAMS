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
<h2><span class="title tab_jsl">信息共享及时率指标统计</span><span class="more"></span></h2>
<div class="p8" id="tab_jsl">
</div>
</div>

<#include "/com/ray/nwpn/itsm/report/common/view/script.ftl">

<script>

function page_load_jsl_table()
{
	page_load_table("${base}/module/pams/gxgl/kpi/jsl/rep_tab_jsl_gs.action", "jsl", 0, new Array("internal"), new Array("${arg.internal}"));
}

function page_load()
{
	page_load_jsl_table();
}

page_load();

</script>

</div></body>
</html>