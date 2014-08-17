<!DOCTYPE HTML>
<html>
<head>
<#include "/decorator/include/include.ftl">
<script type="text/javascript">

function initialTabNsHeight(){
	//console.log($('#gTabsContainterN .topTr').height())
	var avialHeight=$('body').height()-90;
	$('#gTabsContainterN iframe').css({height:avialHeight+'px'});
	$('.bottomTr').css({height:avialHeight+'px'})
}


$(function(){
/////////////////////////////////////////

//解决ie6 bottomTr 高度问题；ff iframe 100% 问题
initialTabNsHeight()
$(window).resize(function(){initialTabNsHeight();})

//点击标签页
$('.topTr li').click(function(){
	var oindex=$(this).index()+1;
	$('.topTr li').removeClass('c')
	$(this).addClass('c');
	$('.bottomTr li').removeClass('c')
	$('.bottomTr li:nth-child('+oindex+')').addClass('c');
	
	var oFrame=$('.bottomTr').find('iframe')
	
	//点击标签页去哪里，在这里写 开始-------
	if(oindex==1)
	{//第一个标签页
		<#if arg.model=='alarm'>
			oFrame.attr('src','${base}/module/irm/portal/portal/portal/portal_browsealarm.action?begintime=${arg.begintime}&endtime=${arg.endtime}&type=${arg.type}&_ctype=${arg._ctype}');
		<#elseif arg.model='defect'>
			oFrame.attr('src','${base}/module/irm/portal/portal/portal/portal_browsedefect.action?begintime=${arg.begintime}&endtime=${arg.endtime}&type=${arg.type}&_ctype=${arg._ctype}');
		<#elseif arg.model='event'>
			oFrame.attr('src','${base}/module/irm/portal/portal/portal/portal_browseevent.action?begintime=${arg.begintime}&endtime=${arg.endtime}&type=${arg.type}&_ctype=${arg._ctype}');
		<#elseif arg.model='audit'>
			oFrame.attr('src','${base}/module/irm/portal/portal/portal/portal_browseaudit.action?begintime=${arg.begintime}&endtime=${arg.endtime}&type=${arg.type}&_ctype=${arg._ctype}');
		<#elseif arg.model='maintance'>
			oFrame.attr('src','${base}/module/irm/portal/portal/portal/portal_browsemaintance.action?begintime=${arg.begintime}&endtime=${arg.endtime}&type=${arg.type}&_ctype=${arg._ctype}');
		<#elseif arg.model='change'>
			oFrame.attr('src','${base}/module/irm/portal/portal/portal/portal_browsechange.action?begintime=${arg.begintime}&endtime=${arg.endtime}&type=${arg.type}&_ctype=${arg._ctype}');
		<#elseif arg.model='stopapp'>
			oFrame.attr('src','${base}/module/irm/portal/portal/portal/portal_browsestopapp.action?begintime=${arg.begintime}&endtime=${arg.endtime}&type=${arg.type}&_ctype=${arg._ctype}');
		<#elseif arg.model='equip'>
			oFrame.attr('src','${base}/module/irm/portal/portal/portal/portal_browseequip.action?begintime=${arg.begintime}&endtime=${arg.endtime}&type=${arg.type}&_ctype=${arg._ctype}');
		<#elseif arg.model='yjya'>
			oFrame.attr('src','${base}/module/irm/portal/portal/portal/portal_browseyjya.action?begintime=${arg.begintime}&endtime=${arg.endtime}&type=${arg.type}&_ctype=${arg._ctype}');
		<#elseif arg.model='ctooperation'>
			oFrame.attr('src','${base}/module/irm/portal/portal/portal/portal_browsectooperation.action?begintime=${arg.begintime}&endtime=${arg.endtime}&type=${arg.type}&_ctype=${arg._ctype}');
		<#elseif arg.model='phonerecord'>
			oFrame.attr('src','${base}/module/irm/portal/portal/portal/portal_browsephonerecord.action?begintime=${arg.begintime}&endtime=${arg.endtime}&type=${arg.type}&_ctype=${arg._ctype}');
		</#if>
	}else if(oindex==2){
		<#if arg.model=='alarm'>
			oFrame.attr('src','${base}/module/irm/portal/portal/portal/portal_browsealarmgl.action?begintime=${arg.begintime}&endtime=${arg.endtime}&type=${arg.type}&_ctype=${arg._ctype}');
		<#elseif arg.model='defect'>
			oFrame.attr('src','${base}/module/irm/portal/portal/portal/portal_browsedefectgl.action?begintime=${arg.begintime}&endtime=${arg.endtime}&type=${arg.type}&_ctype=${arg._ctype}');
		<#elseif arg.model='event'>
			oFrame.attr('src','${base}/module/irm/portal/portal/portal/portal_browseeventgl.action?begintime=${arg.begintime}&endtime=${arg.endtime}&type=${arg.type}&_ctype=${arg._ctype}');
		<#elseif arg.model='audit'>
			oFrame.attr('src','${base}/module/irm/portal/portal/portal/portal_browseauditgl.action?begintime=${arg.begintime}&endtime=${arg.endtime}&type=${arg.type}&_ctype=${arg._ctype}');
		<#elseif arg.model='maintance'>
			oFrame.attr('src','${base}/module/irm/portal/portal/portal/portal_browsemaintancegl.action?begintime=${arg.begintime}&endtime=${arg.endtime}&type=${arg.type}&_ctype=${arg._ctype}');
		<#elseif arg.model='change'>
			oFrame.attr('src','${base}/module/irm/portal/portal/portal/portal_browsechangegl.action?begintime=${arg.begintime}&endtime=${arg.endtime}&type=${arg.type}&_ctype=${arg._ctype}');
		<#elseif arg.model='stopapp'>
			oFrame.attr('src','${base}/module/irm/portal/portal/portal/portal_browsestopappgl.action?begintime=${arg.begintime}&endtime=${arg.endtime}&type=${arg.type}&_ctype=${arg._ctype}');
		<#elseif arg.model='equip'>
			oFrame.attr('src','${base}/module/irm/portal/portal/portal/portal_browseequipgl.action?begintime=${arg.begintime}&endtime=${arg.endtime}&type=${arg.type}&_ctype=${arg._ctype}');
		<#elseif arg.model='yjya'>
			oFrame.attr('src','${base}/module/irm/portal/portal/portal/portal_browseyjyagl.action?begintime=${arg.begintime}&endtime=${arg.endtime}&type=${arg.type}&_ctype=${arg._ctype}');
		<#elseif arg.model='ctooperation'>
			oFrame.attr('src','${base}/module/irm/portal/portal/portal/portal_browsestopappgl.action?begintime=${arg.begintime}&endtime=${arg.endtime}&type=${arg.type}&_ctype=${arg._ctype}');
		<#elseif arg.model='phonerecord'>
			oFrame.attr('src','${base}/module/irm/portal/portal/portal/portal_browsephonerecordgl.action?begintime=${arg.begintime}&endtime=${arg.endtime}&type=${arg.type}&_ctype=${arg._ctype}');
		</#if>
	}
	
}).hoverClass('hover');

//radius border
$('#gTabsContainterN .topTr li').wrapInner('<span class="r"><span class="m"></span></span>')

$('.back2grid').click(function(){
	window.close();
	window.opener.location.reload();
	
})

/////////////////////////////////////////
})
</script>
</head>
<body style="overflow:hidden;">
<table id="gTabsContainterN">
<tr class="topTr">
<td>
	<a class="back2grid" href="javascript:void(0)">关闭</a>
	<ul>
		<#if arg.model=='alarm'>
			<li class="c">告警统计</li>
		<#elseif arg.model='defect'>
			<li class="c">缺陷统计</li>
		<#elseif arg.model='event'>
			<li class="c">事件统计</li>
		<#elseif arg.model='audit'>
			<li class="c">巡检统计</li>
		<#elseif arg.model='maintance'>
			<li class="c">检修统计</li>
		<#elseif arg.model='change'>
			<li class="c">变更统计</li>
		<#elseif arg.model='stopapp'>
			<li class="c">可用性统计</li>
		<#elseif arg.model='equip'>
			<li class="c">备品备件统计</li>
		<#elseif arg.model='yjya'>
			<li class="c">应急预案统计</li>
		<#elseif arg.model='ctooperation'>
			<li class="c">建转运统计</li>
		<#elseif arg.model='phonerecord'>
			<li class="c">热线电话统计</li>
		</#if>
		<li>业务关联统计</li>
	</ul>
</td>
</tr>
<tr class="bottomTr">
<td>
	<#if arg.model=='alarm'>
		<iframe src="${base}/module/irm/portal/portal/portal/portal_browsealarm.action?begintime=${arg.begintime}&endtime=${arg.endtime}&type=${arg.type}&_ctype=${arg._ctype}" frameborder="0"></iframe>
	<#elseif arg.model='defect'>
		<iframe src="${base}/module/irm/portal/portal/portal/portal_browsedefect.action?begintime=${arg.begintime}&endtime=${arg.endtime}&type=${arg.type}&_ctype=${arg._ctype}" frameborder="0"></iframe>
	<#elseif arg.model='event'>
		<iframe src="${base}/module/irm/portal/portal/portal/portal_browseevent.action?begintime=${arg.begintime}&endtime=${arg.endtime}&type=${arg.type}&_ctype=${arg._ctype}" frameborder="0"></iframe>
	<#elseif arg.model='audit'>
		<iframe src="${base}/module/irm/portal/portal/portal/portal_browseaudit.action?begintime=${arg.begintime}&endtime=${arg.endtime}&type=${arg.type}&_ctype=${arg._ctype}" frameborder="0"></iframe>
	<#elseif arg.model='maintance'>
		<iframe src="${base}/module/irm/portal/portal/portal/portal_browsemaintance.action?begintime=${arg.begintime}&endtime=${arg.endtime}&type=${arg.type}&_ctype=${arg._ctype}" frameborder="0"></iframe>
	<#elseif arg.model='change'>
		<iframe src="${base}/module/irm/portal/portal/portal/portal_browsechange.action?begintime=${arg.begintime}&endtime=${arg.endtime}&type=${arg.type}&_ctype=${arg._ctype}" frameborder="0"></iframe>
	<#elseif arg.model='stopapp'>
		<iframe src="${base}/module/irm/portal/portal/portal/portal_browsestopapp.action?begintime=${arg.begintime}&endtime=${arg.endtime}&type=${arg.type}&_ctype=${arg._ctype}" frameborder="0"></iframe>
	<#elseif arg.model='equip'>
		<iframe src="${base}/module/irm/portal/portal/portal/portal_browseequip.action?begintime=${arg.begintime}&endtime=${arg.endtime}&type=${arg.type}&_ctype=${arg._ctype}" frameborder="0"></iframe>
	<#elseif arg.model='yjya'>
		<iframe src="${base}/module/irm/portal/portal/portal/portal_browseyjya.action?begintime=${arg.begintime}&endtime=${arg.endtime}&type=${arg.type}&_ctype=${arg._ctype}" frameborder="0"></iframe>
	<#elseif arg.model='ctooperation'>
		<iframe src="${base}/module/irm/portal/portal/portal/portal_browsectooperation.action?begintime=${arg.begintime}&endtime=${arg.endtime}&type=${arg.type}&_ctype=${arg._ctype}" frameborder="0"></iframe>
	<#elseif arg.model='phonerecord'>
		<iframe src="${base}/module/irm/portal/portal/portal/portal_browsephonerecord.action?begintime=${arg.begintime}&endtime=${arg.endtime}&type=${arg.type}&_ctype=${arg._ctype}" frameborder="0"></iframe>
	</#if>
</td>
</tr>
</table>
</body>
</html>