<!DOCTYPE HTML>
<html>
<head>
<#include "/decorator/include/include.ftl">
<script type="text/javascript">
var cclassname = "";
var cclassid = "";

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
        	
			ohtml+='<li data-pid="'+num+'" data-index="'+(j+1)+'" data-parent="'+parent+'" data-leaf="'+k.leaf+'" data-id="'+k.id+'" data-cname="'+k.cname+'"><span class="'+k.type+'">'+k.cname+'</span></li>';
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
    if(obj.find('ul').length==0)
    {
		if(obj.attr('data-leaf')!=0)
		{
			//如果不是终点 才 ajax
			obj.append('<ul>'+ajax4Tree_pz1('apply_selectchildcclassname.action',obj.attr('data-id'))+'</ul>')
		}
    }
    else
    {
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
			cclassname = obj.attr('data-cname');
			cclassid = obj.attr('data-id');
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
<div id="fixedOp">
<button id="bt_save" class="btn2">确定</button>
</div>
<div id="dragHandlerLR"></div>
<table id="leftRightTB" height="100%;">
<tr>
<td class="leftTd" style="border-right:solid 4px #ccc;vertical-align:top;">
<div id="leftTreeDiv" style="height:100%;overflow:auto;">
<ul id="gAjaxTree">
	<li data-pid="R0" data-index="1" data-parent="" data-leaf="1" data-id="${data.knowledgeclass.id}" data-url="">
	<span class="root">信息共享</span>
		<ul>
		<#list data.knowledgeclasses as treeview>
		<#if arg.isopen!="1">
			<li data-pid="${data.knowledgeclass.id}" data-index="${treeview_index}" data-parent="" data-leaf="1" data-id="${treeview.id}" data-cname="${treeview.cname}" data-url="apply_selectchildcclassname.action">
			<span class="folder">${treeview.cname}</span>
			</li>
		<#else>
			<li data-pid="${data.knowledgeclass.id}" data-index="${treeview_index}" data-parent="" data-leaf="1" data-id="${treeview.id}" data-cname="${treeview.cname}" data-url="apply_selectchildcclassname.action">
			<span class="folder">${treeview.cname}</span>
			</li>
		</#if>
		</#list>
		</ul>
	</span>
</ul>
</div>
</td>
</tr>
</table>

<script type="text/javascript">
$("#bt_save").click(function() {page_save()});

function page_save()
{
	var opener=$('body',window.opener.document);
	opener.find('#cclassname').val(cclassname);
	opener.find('#cclassid').val(cclassid);
	window.close();
}
</script>
</body>
</html>