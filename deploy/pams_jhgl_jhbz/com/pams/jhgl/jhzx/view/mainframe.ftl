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
	<span class="root">共享申请</span>
		<ul>
		<li data-pid="main" data-index="22" data-parent="" data-leaf="0" data-id="jhgl_browsehead" data-url="${base}/module/pams/jhgl/jhzx/apply_browsehead.action?_searchname=jhgl.jhzx.browsehead">
		<span class="folder">岗位负责的计划</span>
		</li>
		<li data-pid="main" data-index="23" data-parent="" data-leaf="0" data-id="jhgl_browsehead" data-url="${base}/module/pams/jhgl/jhzx/apply_browsehead.action?_searchname=jhgl.jhbz.browsehead">
		<span class="folder">岗位主管的计划</span>
		</li>		
		<li data-pid="main" data-index="24" data-parent="" data-leaf="0" data-id="jhgl_browseall" data-url="${base}/module/pams/jhgl/jhzx/apply_browseall.action?_searchname=jhgl.jhbz.browseall&flowcclass=GXGL">
		<span class="folder">岗位所有计划</span>
		</li>
		<li data-pid="main" data-index="25" data-parent="" data-leaf="0" data-id="jhgl_browsegroupall" data-url="${base}/module/pams/jhgl/jhbz/apply_browsegroupall.action?_searchname=jhgl.jhbz.browsegroupall&flowcclass=GXGL">
		<span class="folder">全体所有计划</span>
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