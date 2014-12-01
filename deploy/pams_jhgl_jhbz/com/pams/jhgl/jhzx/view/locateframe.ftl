<!DOCTYPE HTML>
<html>
<head>
<#include "/decorator/include/include.ftl">
<script type="text/javascript">
var navigationJSON=[ {name:'户表管理',link:'${base}/module/irm/portal/portal/portal/portal_browse.action?ccate=gxgl'}, {name:'户表管理'}];

function initialTabNsHeight(){
	//console.log($('#gTabsContainterN .topTr').height())
	var avialHeight=$('body').height()-40;
	$('#gTabsContainterN iframe').css({height:avialHeight+'px'});
	$('.bottomTr').css({height:avialHeight+'px'})
}

// 设定转发后路径
function page_forward_url(runactkey)
{
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
	
	// 点击标签页
	if(oindex==1){ // 基本信息
		oFrame.attr('src','apply_locate.action?id=${arg.id}&runactkey=${arg.runactkey}');
	}else if(oindex==2){ // 审批意见
		oFrame.attr('src','');
	}else if(oindex==3){ // 流程跟踪
		oFrame.attr('src','');
	}else if(oindex==4){ // 流程指标
		oFrame.attr('src','');
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
	<a class="back2grid" href="#1">关闭</a>
	<ul>
		<li class="c">基本信息</li>
	    <li>审批意见</li>
		<li>流程跟踪</li>
		<li>流程统计</li>
	</ul>	
</td>
</tr>
<tr class="bottomTr">
<td>
<iframe src="apply_locate.action?id=${arg.id}&isinput=${arg.isinput}" frameborder="0"></iframe>

</td>
</tr>
</table>
</body>
</html>
