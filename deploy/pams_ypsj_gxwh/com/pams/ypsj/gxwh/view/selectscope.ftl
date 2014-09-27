<!DOCTYPE HTML>
<html>
<head>
<#include "/decorator/include/include.ftl">
<script type="text/javascript">

var cindex = 1;

var navigationJSON=[ {name:'共享管理',link:'${base}/module/irm/portal/portal/portal/portal_browse.action?ccate=ypsj'}, {name:'共享管理'}];

function initialTabNsHeight(){
	//console.log($('#gTabsContainterN .topTr').height())
	var avialHeight=$('body').height()-40;
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
	cindex = $(this).index()+1;	
	var oindex=$(this).index()+1;
	$('.topTr li').removeClass('c')
	$(this).addClass('c');
	$('.bottomTr li').removeClass('c')
	$('.bottomTr li:nth-child('+oindex+')').addClass('c');
	
	var oFrame=$('.bottomTr').find('iframe')
	
	// 点击标签页
	if(oindex==1){ // 组织机构
		oFrame.attr('src','apply_selectscopeorgan.action');
	}else if(oindex==2){ // 角色岗位
		oFrame.attr('src','apply_selectscoperole.action');
	}else if(oindex==3){ // 人员
		oFrame.attr('src','apply_selectscopeusermainframe.action');
	}
	
}).hoverClass('hover');

//radius border
$('#gTabsContainterN .topTr li').wrapInner('<span class="r"><span class="m"></span></span>')

$('.back2grid').click(function()
{
	window.close();
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
		<li class="c">组织机构</li>
	    <li>岗位角色</li>
		<li>组织人员</li>
	</ul>	
</td>
</tr>
<tr class="bottomTr">
<td>
<iframe src="apply_selectscopeorgan.action" frameborder="0"></iframe>
</td>
</tr>
</table>
</body>
</html>
