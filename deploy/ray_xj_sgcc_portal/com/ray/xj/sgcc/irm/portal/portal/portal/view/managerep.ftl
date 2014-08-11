<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<title>首页</title>
<script type="text/javascript" src="${base}/themes/default/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${base}/themes/default/gex.js"></script>
<script type="text/javascript" src="${base}/themes/default/main.js"></script>
<style type="text/css">@import url(${base}/themes/default/main.css);</style>
<style type="text/css">@import url(${base}/themes/default/welcome.css);</style>
<script type="text/javascript">


jQuery(function($){
/////////////////////////////////////



/////////////////////////////////////
})

function redirctmoduleurl(a,url){
	//location=url;
	openwin(url,'opennew',pub_width_mid,pub_height_mid);
}

function page_change_model(model)
{
	window.location = "portal_welcome.action?pmodel=" + model;
}


function page_change_model(model,type)
{
	window.location = "portal_welcome.action?pmodel=" + model +"&type="+type;
}

function page_change_browse(ccate)
{
	window.location = "portal_browse.action?ccate=" + ccate;
}

</script>
</head>

<body>

<div id="siteWizard">
	<ul>
		<li onclick="page_change_model('welcome')">个人工作平台</li>
		<li onclick="page_change_model('manage')">管理平台</li>
		<#--<li class="c" onclick="page_change_model('managerep')">管理平台图表</li>-->
		<li onclick="page_change_model('deepen','all')">相关业务查询</li>
		<li onclick="page_change_model('stat')">报表统计</li>
		<li onclick="page_change_browse('show')">系统拓扑</li>
	</ul>
</div>

<#include "/com/ray/nwpn/itsm/report/common/view/navhidden.ftl">
<#import "com/ray/nwpn/itsm/report/common/view/macros.ftl" as pub_macros>

<div style="text-align:right;"><select id="select2content" style="*width:200px;"></select></div>


<div id="pageContainer"><div id="pageContent" class="haveScrollDivs">

<div class="section sec1">
<h2><span class="title rep_portal_event_pdgd">排队事件工单</span><span class="more"></span></h2>
<div class="p8" id="rep_portal_event_pdgd">
</div>
</div>



<div class="section sec2">
<h2><span class="title rep_portal_event_wgbgd">未关闭事件工单</span><span class="more"></span></h2>
<div class="p8" id="rep_portal_event_wgbgd">
</div>
</div>


<div class="section sec3">
<h2><span class="title rep_portal_event_drgd">本部事件工单</span><span class="more"></span></h2>
<div class="p8" id="rep_portal_event_drgd">
</div>
</div>


<div class="section sec4">
<h2><span class="title rep_portal_event_drgd">陕西电力公司事件工单</span><span class="more"></span></h2>
<div class="p8" id="rep_portal_event_drgd_sx">
</div>
</div>



<div class="section sec14">
<h2><span class="title rep_portal_dabnormal_ycgj">三天告警记录</span><span class="more"></span></h2>
<div class="p8" id="rep_portal_dabnormal_ycgj">
</div>
</div>


<div class="section sec14">
<h2><span class="title rep_portal_dabnormal_wgbycgj">所有未关闭告警记录</span><span class="more"></span></h2>
<div class="p8" id="rep_portal_dabnormal_wgbycgj">
</div>
</div>


<div class="section sec4">
<h2><span class="title rep_portal_change_wgbbg">未关闭变更</span><span class="more"></span></h2>
<div class="p8" id="rep_portal_change_wgbbg">
</div>
</div>

<div class="section sec5">
<h2><span class="title rep_portal_change_drgbbg">三天关闭变更</span><span class="more"></span></h2>
<div class="p8" id="rep_portal_change_drgbbg">
</div>
</div>


<div class="section sec12">
<h2><span class="title rep_portal_maintenance_qrjxzt">检修计划（上周、本周、下周）</span><span class="more"></span></h2>
<div class="p8" id="rep_portal_maintenance_qrjxzt">
</div>
</div>

<#--
<div class="section sec6" style="float:left;width:60%;*width:55%;">
<h2><span class="title rep_portal_duty_drzb">值班记录（三日内）</span><span class="more"></span></h2>
<div class="p8" id="rep_portal_duty_drzb">
</div>
</div>
-->

<div class="section sec9">
<h2><span class="title rep_portal_audit_qrjrxj">巡检记录（三日内）</span><span class="more"></span></h2>
<div class="p8" id="rep_portal_audit_qrjrxj">
</div>
</div>


<div class="section sec6" >
<h2><span class="title rep_portal_event_dhrx_dhrxlx">电话热线（三日内）</span><span class="more"></span></h2>
<div class="p8" id="rep_portal_event_dhrx_dhrxlx">
</div>
</div>


<div class="section sec12" >
<h2><span class="title rep_portal_availability_kyx">停机时长记录</span><span class="more"></span></h2>
<div class="p8" id="rep_portal_availability_kyx">
</div>
</div>


<div class="section sec7">
<h2><span class="title rep_portal_audit_xjyc">巡检异常信息</span><span class="more"></span></h2>
<div class="p8" id="rep_portal_audit_xjyc">
</div>
</div>


</div>



<#include "/com/ray/nwpn/itsm/report/common/view/script.ftl">



<script>

function page_load_pdgd_chart()
{
	page_load_chart("portal.event.pdgd", "portal_event_pdgd", 0, "Column3D", "", "");
}

function page_load_wgbgd_chart()
{
	page_load_chart("portal.event.wgbgd", "portal_event_wgbgd", 0, "Line", "", "");
}

function page_load_dhrxlx_chart()
{
	page_load_chart("portal.event.dhrx.dhrxlx", "portal_event_dhrx_dhrxlx", 0, "MSColumn3D", "", "");
}

function page_load_drgd_chart()
{
	page_load_chart("portal.event.drgd", "portal_event_drgd", 0, "Column3D", new Array("source"), new Array("XBDWYXGS"));
}

function page_load_drgd_sx_chart()
{
	page_load_chart("portal.event.drgd", "portal_event_drgd_sx", 0, "Column3D", new Array("source"), new Array("SXDLGS"));
}

function page_load_wgbbg_chart()
{
	page_load_chart("portal.change.wgbbg", "portal_change_wgbbg", 0, "Line", "", "");
}

function page_load_drgbbg_chart()
{
	page_load_chart("portal.change.drgbbg", "portal_change_drgbbg", 0, "Column3D", "", "");
}

function page_load_qrjrxj_chart()
{
	page_load_chart("portal.audit.qrjrxj", "portal_audit_qrjrxj", 0, "Column3D", "", "");
}

function page_load_qrjxzt_chart()
{
	page_load_chart("portal.maintenance.qrjxzt", "portal_maintenance_qrjxzt", 0, "Column3D", "", "");
}

function page_load_sywzxjx_chart()
{
	page_load_chart("portal.maintenance.sywzxjx", "portal_maintenance_sywzxjx", 0, "Column3D", "", "");
}

function page_load_kyx_chart()
{
	page_load_chart("portal.availability.kyx", "portal_availability_kyx", 0, "Column3D", "", "");
}

function page_load_ycgj_chart()
{
	page_load_chart("portal.dabnormal.ycgj", "portal_dabnormal_ycgj", 0, "Column3D", "", "");
}

function page_load_wgbycgj_chart()
{
	page_load_chart("portal.dabnormal.wgbycgj", "portal_dabnormal_wgbycgj", 0, "Line", "", "");
}

function page_load_xjyc_chart()
{
	page_load_chart("portal.audit.xjyc", "portal_audit_xjyc", 0, "Column3D", "", "");
}

function page_load()
{
	page_load_pdgd_chart();
	page_load_wgbgd_chart();
	page_load_drgd_chart();
	page_load_drgd_sx_chart();
	page_load_ycgj_chart();
	page_load_wgbycgj_chart();
	page_load_wgbbg_chart();
	page_load_drgbbg_chart();
	page_load_qrjxzt_chart();
	page_load_qrjrxj_chart();
	page_load_dhrxlx_chart();
	page_load_kyx_chart();
	page_load_xjyc_chart();

}

$(document).ready(function()
{
	window.setInterval(function(){page_load();}, 600000);
});

page_load();
</script>

<script>
//判断 页面 ajax内容加载完毕
var checkPageTimer=setInterval(checkPageLoaded,2000);

function checkPageLoaded(){
	
	//if($.trim($('#tab_portal_event_yxzt').text())!=''){
		clearInterval(checkPageTimer);
		//alert('ok');
		
			//用来处理ie67的  max-height 问题！===不得超过280的高度
			var optionHtml='';
			$('div.haveScrollDivs .section .p8').each(function(){			
				if($.browser.msie&&parseInt($.browser.version)<8){
					if($(this).height()>280){
						$(this).css({height:'280px'})
					}
				}
				//console.log($(this).parent().scrollTop())
				var sec=$(this).parent();
				var oid=$(this).attr('id');
				var otext=sec.find('h2 .title').text();
				sec.before('<a class="anchor" name="'+oid+'"></a>');
				optionHtml+='<option value="'+oid+'">'+otext+'</option>'
				
			})
		//加载右上角 select
		$('#select2content').append(optionHtml);
		$('#select2content').change(function(){
			location="#"+$(this).val();
		})
	//}
}

</script>

</div></body>
</html>