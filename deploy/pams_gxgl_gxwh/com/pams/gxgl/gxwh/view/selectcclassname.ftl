<!DOCTYPE HTML>
<html>
<head>
<#include "/decorator/include/include.ftl">
<script type="text/javascript">
var cclassname = "";

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
			// window.rightIframe.location = obj.attr('data-url');
			cclassname = obj.attr('data-url');
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
	<li data-pid="R0" data-index="1" data-parent="" data-leaf="1" data-id="R0" data-url="政府">
	<span class="root">分类目录</span>
		<ul>
		<li data-pid="main" data-index="11" data-parent="" data-leaf="1" data-id="zf" data-url="政府">
		<span class="folder">政府</span>
			<ul>
			<li data-pid="zf" data-index="111" data-parent="" data-leaf="0" data-id="zf_gjj" data-url="政府/国家级">
			<span class="folder">国家级</span>
			<li data-pid="zf" data-index="112" data-parent="" data-leaf="0" data-id="zf_shj" data-url="政府/省级">
			<span class="folder">省级</span>
			<li data-pid="zf" data-index="113" data-parent="" data-leaf="0" data-id="zf_sj" data-url="政府/市级">
			<span class="folder">市级</span>
			</ul>					
		</li>
		<li data-pid="main" data-index="12" data-parent="" data-leaf="0" data-id="gwgs" data-url="国网公司">
		<span class="folder">国网公司</span>
			<ul>
			<li data-pid="gwgs" data-index="121" data-parent="" data-leaf="0" data-id="gwgs_gwgs" data-url="国网公司/国网公司">
			<span class="folder">国网公司</span>
			<li data-pid="gwgs" data-index="122" data-parent="" data-leaf="0" data-id="gwgs_sdlgs" data-url="国网公司/省电力公司">
			<span class="folder">省电力公司</span>
			<li data-pid="gwgs" data-index="123" data-parent="" data-leaf="0" data-id="gwgs_qtdlgs" data-url="国网公司/其他电力公司">
			<span class="folder">其他电力公司</span>
			</ul>			
		</li>
		<li data-pid="main" data-index="13" data-parent="" data-leaf="0" data-id="nb" data-url="内部信息">
		<span class="folder">内部信息</span>
			<ul>
			<li data-pid="nb" data-index="131" data-parent="" data-leaf="0" data-id="nb_gsj" data-url="内部信息/公司级">
			<span class="folder">公司级</span>
			<li data-pid="nb" data-index="132" data-parent="" data-leaf="0" data-id="nb_bmj" data-url="内部信息/部门级">
			<span class="folder">部门级</span>
			<li data-pid="nb" data-index="133" data-parent="" data-leaf="0" data-id="nb_zsdw" data-url="内部信息/直属单位">
			<span class="folder">直属单位</span>
			</ul>			
		</li>
		</ul>
	</li>
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
	window.close();
}
</script>
</body>
</html>