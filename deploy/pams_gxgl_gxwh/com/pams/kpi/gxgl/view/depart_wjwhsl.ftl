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
.sec1 {width:200px;float:left;margin-right:10px;}
.sec2 {width:640px;float:left;height:200px;}
.sec3 {width:200px;float:left;margin-right:10px;}
.sec4 {width:640px;float:left;height:200px;}
.sec5 {width:200px;float:left;margin-right:10px;clear:both;}
.sec6 {width:640px;float:left;height:200px;}

</style>
</head>
<body><div id="gContainer">

<#import "com/ray/nwpn/itsm/report/common/view/macros.ftl" as pub_macros>
<@pub_macros.showheader repname="文件共享数量(部门)统计报表" />

<#include "/com/ray/nwpn/itsm/report/common/view/nav.ftl">

<div style="text-align:right;width:850px;padding:10px;">
	<form id="form_advsearch" method="post" target="repframe">
	<input type="hidden" name="begindate">
	<input type="hidden" name="enddate">
	
	来源：<#assign options = stack.findValue("@com.ray.app.query.function.view.SQLSelect@get_data(\"select new map(a.dvalue as dvalue, a.dtext as dtext) from Dictionary a where 1=1 and a.dkey = 'app.event.source' order by ordernum \")") >
	    <select id="source" name="source">
	    <option value=""></option>
	    <#list options as aoption>  
	      <option value="${aoption.dvalue}">${aoption.dtext}</option>
	    </#list>
	    </select>
	    
	公司：<#assign options = stack.findValue("@com.ray.app.query.function.view.SQLSelect@get_data(\"select new map(a.dvalue as dvalue, a.dtext as dtext) from Dictionary a where 1=1 and a.dkey = 'app.event.dept' order by ordernum \")") >
	    <select id="sourcedept" name="sourcedept">
	    <option value=""></option>
	    <#list options as aoption>  
	      <option value="${aoption.dvalue}">${aoption.dtext}</option>
	    </#list>
	    </select>  
	
</form>
</div>

<br />
<div class="section sec2">
<h2><span class="title rep_wjwhslbm">文件共享数量(部门) 图</span><span class="more"><@pub_macros.showspan chartname = "gxgl.pki.xxgxjsl.xxgxjslbm" pname="gxgl.pki.xxgxjsl.xxgxjslbm" /></span></h2>
<div class="p8" id="rep_wjwhslbm">
</div>
</div>

<div class="section sec1">
<h2><span class="title tab_wjwhslbm">文件共享数量(部门)表</span><span class="more"></span></h2>
<div class="p8" id="tab_wjwhslbm">
</div>
</div>



<div style="clear:both;height:15px;"></div>




<#include "/com/ray/nwpn/itsm/report/common/view/script.ftl">

<script>

function page_load_dhrx_table()
{
	page_load_table("${base}/module/pams/kpi/gxgl/kpi_depart_wjwhsl_tb.action", "wjwhslbm", 0, new Array(), new Array());
}

function page_load_dhrx_chart()
{
	page_load_chart("gxgl.pki.wjwh.wjwhslbm", "wjwhslbm", 0, "Column3D", new Array("source","sourcedept"), new Array($("#source").val(),$("#sourcedept").val()));
}


function page_load()
{
	page_load_dhrx_table();
	page_load_dhrx_chart();	
	
}



page_load();



</script>

</div></body>
</html>
