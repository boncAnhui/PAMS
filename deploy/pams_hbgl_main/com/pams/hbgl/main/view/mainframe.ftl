<!DOCTYPE HTML>
<html>
<head>
<#include "/decorator/include/include.ftl">
<script type="text/javascript">
var  rootID='R0';

function ajax4Tree_pz1(url,num){
var ohtml='';
$.ajax({
    url:url,
    data:{supid:num},
    cache:false,
    async:false,
    success:function(d){
        eval('var d='+d);
        //console.log(d);
		var parent=d.parent;
        $.each(d.items,function(j,k){
			ohtml+='<li data-pid="'+num+'" data-index="'+(j+1)+'" data-parent="'+parent+'" data-leaf="'+k.leaf+'" data-id="'+k.id+'"><span class="'+k.type+'">'+k.name+'</span></li>';
        })
    }
})
//console.log(ohtml)
return ohtml;

}

jQuery(function($){
///////////////////

//初始化根节点
//$('#leftTreeDiv').append('<ul id="gAjaxTree">'+ajax4Tree_pz1('config_tree.action',rootID)+'</ul>');

//树根节点没有padding 禁止选择
$('#gAjaxTree>li').css({'padding-left':0}).disableSelection();

$('#gAjaxTree li span').live('click',function(){
    var obj=$(this).parent();
	$('#gAjaxTree li span').removeClass('selected');
	$(this).addClass('selected');
    if(obj.find('ul').length==0){
		if(obj.attr('data-leaf')!=0){//如果不是终点 才 ajax
			obj.append('<ul>'+ajax4Tree_pz1('config_treechild.action',obj.attr('data-id'))+'</ul>')
		}
    }else{
        if(obj.attr('data-id')!='R0')
		{
        	obj.find('ul').toggle();	
        }
    }
    $(this).toggleClass('open');
	//链接到哪里  $(this).attr('data-id') 是当前节点id
	if(obj.attr('data-id')!='R0')
	{
		if(obj.attr('data-url')!='')
		{
			window.rightIframe.location = obj.attr('data-url');
		}
	}
}).live('mouseenter',function(){
    $(this).addClass('hover');
}).live('mouseleave',function(){
    $(this).removeClass('hover');
})

//默认点击根节点
$('#gAjaxTree>li span').trigger('click');
//默认点击根节点下的第一个节点
$('#gAjaxTree>li>ul>li:first span').trigger('click');

//设置拖动条位置及交互效果（放出来是因为根据内容可给出不同于 200px 宽度的左侧-下面 #leftTd 也要改！）
$('#dragHandlerLR').draggable({iframeFix: true,scroll:false,containment:[0,0,400,0],stop:function(){$('.leftTd,#leftTreeDiv').css({width:$(this).position().left});}}).disableSelection().css({left:'200px'});

///////////////////
})
</script>
</head>
<body style="overflow:hidden;">

<div id="dragHandlerLR"></div>
<table id="leftRightTB" height="100%;">
<tr>
<td class="leftTd" style="border-right:solid 4px #ccc;vertical-align:top;">
<div id="leftTreeDiv" style="height:100%;overflow:auto;">
<ul id="gAjaxTree">
	<li data-pid="R0" data-index="1" data-parent="" data-leaf="1" data-id="R0" data-url="">
	<span class="root">户表业务</span>
	<ul>
	<li data-pid="R0" data-index="1" data-parent="" data-leaf="0" data-id="main" data-url="">
	<span class="folder selected">主流程</span>
		<ul>
		<li data-pid="main" data-index="2" data-parent="" data-leaf="0" data-id="main_browsegroupsall" data-url="${base}/module/app/system/workflow/define/define_main.action" target="mainIframe">
		<span class="folder">流程定义</span>
		</li>

		<li data-pid="main" data-index="2" data-parent="" data-leaf="0" data-id="main_browsegroupsall" data-url="${base}/module/pams/hbgl/main/apply_browsegroupsall.action?_searchname=hbgl.main.browsegroupall&flowsno=HBGL">
		<span class="folder">全部工作</span>
		</li>
		</ul>
	</li>
	<li data-pid="R0" data-index="2" data-parent="" data-leaf="0" data-id="gxgl" data-url="">
	<span class="folder">共享管理</span>
		<ul>
		<li data-pid="main" data-index="21" data-parent="" data-leaf="0" data-id="gxgl_browsewait" data-url="${base}/module/pams/gxgl/gxwh/apply_browsewait.action?_searchname=gxgl.gxwh.browsewait&flowsno=GXGL_ZZ">
		<span class="folder">待办工作</span>
		</li>
		<li data-pid="main" data-index="22" data-parent="" data-leaf="0" data-id="gxgl_browsehandle" data-url="${base}/module/pams/gxgl/gxwh/apply_browsehandle.action?_searchname=gxgl.gxwh.browsehandle&flowsno=GXGL_ZZ">
		<span class="folder">我参与的工作</span>
		</li>
		<li data-pid="main" data-index="23" data-parent="" data-leaf="0" data-id="gxgl_browsecomplete" data-url="${base}/module/pams/gxgl/gxwh/apply_browsehandle.action?_searchname=gxgl.gxwh.browsehandle&flowsno=GXGL_ZZ">
		<span class="folder">已结束工作</span>
		</li>
		<li data-pid="main" data-index="24" data-parent="" data-leaf="0" data-id="gxgl_browsegroupall" data-url="${base}/module/pams/gxgl/gxwh/apply_browsegroupall.action?_searchname=gxgl.gxwh.browsegroupall&flowsno=GXGL_ZZ">
		<span class="folder">全部工作</span>
		</li>
		<li data-pid="main" data-index="25" data-parent="" data-leaf="0" data-id="gxgl_kpi_zxsc" data-url="${base}/module/pams/kpi/gxgl/kpi_main_zxsc.action">
		<span class="folder">KPI执行时长</span>
		</li>
		
		<li data-pid="main" data-index="24" data-parent="" data-leaf="0" data-id="gxgl_zswh_mainframe" data-url="${base}/module/pams/gxgl/zswh/knowledge_mainframe.action">
		<span class="folder">共享库</span>
		</li>
		
		
		</ul>
	</li>
	<li data-pid="R0" data-index="2" data-parent="" data-leaf="0" data-id="xxsj" data-url="">
	<span class="folder">信息收集</span>
		<ul>
		<li data-pid="main" data-index="21" data-parent="" data-leaf="0" data-id="xxsj_browsewait" data-url="${base}/module/pams/hbgl/xxsj/apply_browsewait.action?_searchname=hbgl.xxsj.browsewait&flowsno=HBGL_XXSJ">
		<span class="folder">待办工作</span>
		</li>
		<li data-pid="main" data-index="22" data-parent="" data-leaf="0" data-id="xxsj_browsehandle" data-url="${base}/module/pams/hbgl/xxsj/apply_browsehandle.action?_searchname=hbgl.xxsj.browsehandle&flowsno=HBGL_XXSJ">
		<span class="folder">我参与的工作</span>
		</li>
		<li data-pid="main" data-index="22" data-parent="" data-leaf="0" data-id="xxsj_browseindraft" data-url="about:blank">
		<span class="folder">草稿箱</span>
		</li>		
		<li data-pid="main" data-index="22" data-parent="" data-leaf="0" data-id="xxsj_browsedraft" data-url="about:blank">
		<span class="folder">我起草的工作</span>
		</li>
		<li data-pid="main" data-index="22" data-parent="" data-leaf="0" data-id="xxsj_browsecomplete" data-url="${base}/module/pams/hbgl/xxsj/apply_browsehandle.action?_searchname=hbgl.xxsj.browsehandle&flowsno=HBGL_XXSJ">
		<span class="folder">已结束工作</span>
		</li>
		<li data-pid="main" data-index="22" data-parent="" data-leaf="0" data-id="xxsj_browsearchivewait" data-url="${base}/module/pams/hbgl/xxsj/apply_browsehandle.action?_searchname=hbgl.xxsj.browsehandle&flowsno=HBGL_XXSJ">
		<span class="folder">待归档工作</span>
		</li>
		<li data-pid="main" data-index="22" data-parent="" data-leaf="0" data-id="xxsj_browsearchive" data-url="${base}/module/pams/hbgl/xxsj/apply_browsehandle.action?_searchname=hbgl.xxsj.browsehandle&flowsno=HBGL_XXSJ">
		<span class="folder">已归档工作</span>
		</li>
		<li data-pid="main" data-index="23" data-parent="" data-leaf="0" data-id="xxsj_browsegroupall" data-url="${base}/module/pams/hbgl/xxsj/apply_browsegroupall.action?_searchname=hbgl.xxsj.browsegroupall&flowsno=HBGL_XXSJ">
		<span class="folder">全部工作</span>
		</li>
		</ul>
	</li>
	<li data-pid="R0" data-index="3" data-parent="" data-leaf="0" data-id="yhsq" data-url="">
	<span class="folder">用户申请</span>
	
		<ul>
		<li data-pid="main" data-index="21" data-parent="" data-leaf="0" data-id="yhsq_browserelay" data-url="/pams/module/pams/hbgl/yhsq/apply_browserelay.action?_searchname=hbgl.yhsq.browserelay&flowsno=HBGL_XXSJ">
		<span class="folder">收件箱</span>
		</li>
		<li data-pid="main" data-index="21" data-parent="" data-leaf="0" data-id="yhsq_browsewait" data-url="${base}/module/pams/hbgl/yhsq/apply_browsewait.action?_searchname=hbgl.yhsq.browsehandle&flowsno=HBGL_YHSQ">
		<span class="folder">待办工作</span>
		</li>
		<li data-pid="main" data-index="22" data-parent="" data-leaf="0" data-id="yhsq_browsehandle" data-url="">
		<span class="folder">我参与的工作</span>
		</li>
		<li data-pid="main" data-index="23" data-parent="" data-leaf="0" data-id="yhsq_browsegroupsall" data-url="">
		<span class="folder">全部工作</span>
		</li>
		</ul>
	</li>
	<li data-pid="R0" data-index="4" data-parent="" data-leaf="0" data-id="xctk" data-url="">
	<span class="folder">现场踏勘</span>
		<ul>
		<li data-pid="main" data-index="21" data-parent="" data-leaf="0" data-id="xctk_browserelay" data-url="${base}/module/pams/hbgl/xctk/apply_browserelay.action?_searchname=hbgl.xctk.browserelay&flowsno=HBGL_YHSQ">
		<span class="folder">收件箱</span>
		</li>
		<li data-pid="main" data-index="21" data-parent="" data-leaf="0" data-id="xctk_browsewait" data-url="${base}/module/pams/hbgl/xctk/apply_browsewait.action?_searchname=hbgl.xctk.browsewait&flowsno=HBGL_XCTK">
		<span class="folder">待办工作</span>
		</li>
		<li data-pid="main" data-index="22" data-parent="" data-leaf="0" data-id="xctk_browsehandle" data-url="">
		<span class="folder">我参与的工作</span>
		</li>
		<li data-pid="main" data-index="23" data-parent="" data-leaf="0" data-id="xctk_browsegroupsall" data-url="">
		<span class="folder">全部工作</span>
		</li>
		</ul>
	</li>
	
</ul>
</div>
</td>
<td class="rightTd">
	<iframe src="about:blank" width="100%" height="100%" frameborder="0" allowtransparency="true" id="iframe1" name="rightIframe" border="0"></iframe>
</td>
</tr>
</table>
</body>
</html>