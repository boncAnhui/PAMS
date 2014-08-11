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
<style>
#gsbrpopup {
	position:fixed;
	bottom:0;right:0;_position:absolute;background: #fff;border:solid 1px #ddd;font-size: 12px;*width:380px;}
#gsbrpopup .title {background: 	#ddd;line-height: 30px;padding:0 8px;cursor:pointer;color:#666;white-space: nowrap;}

#gsbrpopup .title .collapse {float:right;border:solid 6px #999;border-right-color:transparent;border-left-color:transparent;border-bottom: 0;margin-top: 	12px; margin-left: 20px;height:0;overflow:hidden;}

#gsbrpopup .title .collapse {_border-right-color:#ddd;_border-left-color:#ddd;}

#gsbrpopup .body {height:200px;overflow: auto}

#gsbrpopup.closed .body {display: none;}
#gsbrpopup.closed .title .collapse {border-right-color:transparent;border-left-color:transparent;border-bottom: solid 6px #999;border-top:0;_border-right-color:#ddd;_border-left-color:#ddd;}
#gsbrpopup ul {margin: 0;	padding: 10px;	list-style-type: none;}
#gsbrpopup ul li {margin: 0;	padding: 0;	list-style-type: none;line-height: 20px;white-space: nowrap;}
#gsbrpopup ul li .date {float: right;color:#999;padding-left:40px;}
#gsbrpopup ul li a {display:inline-block;*display:inline;*zoom:1;white-space:nowrap;width:20em;overflow:hidden;text-overflow:ellipsis;color:#333;text-decoration: none;}
</style>
<script>
jQuery(function($){
////////////////////////

$('#gsbrpopup .title').on('click',function(){
	$('#gsbrpopup').toggleClass('closed');
	
})
if('${arg.popupclass}'=='closed' || ${data.bulletins?size} <= 0)
{
	$('#gsbrpopup').toggleClass('closed');
}
////////////////////////
})
</script>
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
	window.location = "portal_welcome.action?pmodel=" + model+"&popupclass="+$('#gsbrpopup').attr('class');
}


function page_change_model(model,type)
{
	window.location = "portal_welcome.action?pmodel=" + model +"&type="+type+"&popupclass="+$('#gsbrpopup').attr('class');
}

function page_change_browse(ccate)
{
	window.location = "portal_browse.action?ccate=" + ccate+"&popupclass="+$('#gsbrpopup').attr('class');
}

</script>
</head>

<body>

<div id="siteWizard">
	<ul>
		<li onclick="page_change_model('welcome')">个人工作平台</li>
		<li class="c" onclick="page_change_model('manage')">管理平台</li>
		<#--<li onclick="page_change_model('managerep')">管理平台图表</li>-->
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
<h2><span class="title tab_portal_event_pdgd">排队事件工单</span><span class="more"></span></h2>
<div class="p8" id="tab_portal_event_pdgd">
</div>
</div>

<div class="section sec2">
<h2><span class="title tab_portal_event_wgbgd">未关闭事件工单</span><span class="more"></span></h2>
<div class="p8" id="tab_portal_event_wgbgd">
</div>
</div>

<div class="section sec3">
<h2><span class="title tab_portal_event_drgd">本部当日事件工单</span><span class="more"></span></h2>
<div class="p8" id="tab_portal_event_drgd">
</div>
</div>

<div class="section sec4">
<h2><span class="title tab_portal_event_drgd">陕西电力公司当日事件工单</span><span class="more"></span></h2>
<div class="p8" id="tab_portal_event_drgd_sx">
</div>
</div>

<div class="section sec14">
<h2><span class="title tab_portal_dabnormal_ycgj">当日告警记录</span><span class="more"></span></h2>
<div class="p8" id="tab_portal_dabnormal_ycgj">
</div>
</div>

<div class="section sec14">
<h2><span class="title tab_portal_dabnormal_wgbycgj">所有未关闭告警记录</span><span class="more"></span></h2>
<div class="p8" id="tab_portal_dabnormal_wgbycgj">
</div>
</div>

<div class="section sec4">
<h2><span class="title tab_portal_change_wgbbg">未关闭变更</span><span class="more"></span></h2>
<div class="p8" id="tab_portal_change_wgbbg">
</div>
</div>

<div class="section sec5">
<h2><span class="title tab_portal_change_drgbbg">当日关闭变更</span><span class="more"></span></h2>
<div class="p8" id="tab_portal_change_drgbbg">
</div>
</div>

<div class="section sec12">
<h2><span class="title tab_portal_maintenance_qrjxzt">检修计划（上周）</span><span class="more"></span></h2>
<div class="p8" id="tab_portal_maintenance_qrjxzt">
</div>
</div>

<div class="section sec13">
<h2><span class="title tab_portal_maintenance_sywzxjx">检修计划（本周、下周）</span><span class="more"></span></h2>
<div class="p8" id="tab_portal_maintenance_sywzxjx">
</div>
</div>

<div class="section sec6">
<h2><span class="title tab_portal_duty_drzb">值班记录（三日内）</span><span class="more"></span></h2>
<div class="p8" id="tab_portal_duty_drzb">
</div>
</div>

<div class="section sec9">
<h2><span class="title tab_portal_audit_qrjrxj">巡检记录（三日内）</span><span class="more"></span></h2>
<div class="p8" id="tab_portal_audit_qrjrxj">
</div>
</div>

<div class="section sec6" style="float:left;width:40%;">
<h2><span class="title tab_event_dhrx_dhrxlx">当日电话热线</span><span class="more"></span></h2>
<div class="p8" id="tab_event_dhrx_dhrxlx">
</div>
</div>

<div class="section sec12" style="float:left;width:60%;*width:55%;">
<h2><span class="title tab_portal_availability_kyx">停机时长记录</span><span class="more"></span></h2>
<div class="p8" id="tab_portal_availability_kyx">
</div>
</div>

<div style="clear:both;"></div>
<#--
<div class="section sec7">
<h2><span class="title tab_portal_event_yxzt">当日一线工作状态</span><span class="more"></span></h2>
<div class="p8" id="tab_portal_event_yxzt">
</div>
</div>
-->

<div class="section sec7">
<h2><span class="title tab_portal_audit_xjyc">最近一次巡检异常信息</span><span class="more"></span></h2>
<div class="p8" id="tab_portal_audit_xjyc">
</div>
</div>

<div id="gsbrpopup">
	<div class="title"><span class="collapse"></span>公告栏</div>
	<div class="body">
		<ul>
		<#list data.bulletins as bulletin>
			<li><span class="date">${bulletin.createtime?string('yyyy-MM-dd')}</span><a href="javascript:void(0);" title='${bulletin.description}'>${bulletin.description}</a></li>
		</#list>
		</ul>
	</div>
</div>
</div>



<#include "/com/ray/nwpn/itsm/report/common/view/script.ftl">



<script>

function page_load_pdgd_table()
{
	page_load_table("${base}/module/app/report/portal/event/report_tab_pdgd.action", "portal_event_pdgd", 0, "", "");
}

function page_load_wgbgd_table()
{
	page_load_table("${base}/module/app/report/portal/event/report_tab_wgbgd.action", "portal_event_wgbgd", 0, "", "");
}

function page_load_dhrxlx_table()
{
	page_load_table("${base}/module/app/report/event/dhrx/report_tab_dhrxlx.action", "event_dhrx_dhrxlx", 0, "", "");
}

function page_load_drgd_table()
{
	page_load_table("${base}/module/app/report/portal/event/report_tab_drgd.action", "portal_event_drgd", 0, new Array("source"), new Array("XBDWYXGS"));
}

function page_load_drgd_sx_table()
{
	page_load_table("${base}/module/app/report/portal/event/report_tab_drgd.action", "portal_event_drgd_sx", 0, new Array("source"), new Array("SXDLGS"));
}

function page_load_wgbbg_table()
{
	page_load_table("${base}/module/app/report/portal/change/report_tab_wgbbg.action", "portal_change_wgbbg", 0, "", "");
}

function page_load_drgbbg_table()
{
	page_load_table("${base}/module/app/report/portal/change/report_tab_drgbbg.action", "portal_change_drgbbg", 0, "", "");
}

function page_load_drzb_table()
{
	page_load_table("${base}/module/app/report/portal/duty/report_tab_drzb.action", "portal_duty_drzb", 0, "", "");
}

function page_load_yxzt_table()
{
	page_load_table("${base}/module/app/report/portal/event/report_tab_yxzt.action", "portal_event_yxzt", 0, "", "");
}

function page_load_qrjrxj_table()
{
	page_load_table("${base}/module/app/report/portal/audit/report_tab_qrjrxj.action", "portal_audit_qrjrxj", 0, "", "");
}

function page_load_wwcxj_table()
{
	page_load_table("${base}/module/app/report/portal/audit/report_tab_wwcxj.action", "portal_audit_wwcxj", 0, "", "");
}

function page_load_jrjxjh_table()
{
	page_load_table("${base}/module/app/report/portal/maintenance/report_tab_jrjxjh.action", "portal_maintenance_jrjxjh", 0, "", "");
}

function page_load_qrjxzt_table()
{
	page_load_table("${base}/module/app/report/portal/maintenance/report_tab_qrjxzt.action", "portal_maintenance_qrjxzt", 0, "", "");
}

function page_load_sywzxjx_table()
{
	page_load_table("${base}/module/app/report/portal/maintenance/report_tab_sywzxjx.action", "portal_maintenance_sywzxjx", 0, "", "");
}

function page_load_kyx_table()
{
	page_load_table("${base}/module/app/report/portal/kyx/report_tab_kyx.action", "portal_availability_kyx", 0, "", "");
}

function page_load_ycgj_table()
{
	page_load_table("${base}/module/app/report/portal/dabnormal/report_tab_ycgj.action", "portal_dabnormal_ycgj", 0, "", "");
}

function page_load_wgbycgj_table()
{
	page_load_table("${base}/module/app/report/portal/dabnormal/report_tab_wgbycgj.action", "portal_dabnormal_wgbycgj", 0, "", "");
}

function page_load_xjyc_table()
{
	page_load_table("${base}/module/app/report/portal/audit/report_tab_xjyc.action", "portal_audit_xjyc", 0, "", "");
}

function page_load()
{
	page_load_pdgd_table();
	page_load_wgbgd_table();
	page_load_dhrxlx_table();
	page_load_drgd_table();
	page_load_drgd_sx_table();
	page_load_wgbbg_table();
	page_load_drgbbg_table();
	page_load_drzb_table();
	page_load_yxzt_table();
	page_load_qrjrxj_table();
	page_load_jrjxjh_table();
	page_load_qrjxzt_table();
	page_load_sywzxjx_table();
	page_load_kyx_table();
	page_load_ycgj_table();
	page_load_wgbycgj_table();
	page_load_xjyc_table();

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