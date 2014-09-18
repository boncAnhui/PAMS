
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>任务管理系统</title>
<script type="text/javascript" src="/pams/themes/default/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/pams/themes/default/gex.js"></script>
<script type="text/javascript" src="/pams/themes/default/main.js"></script>
<script type="text/javascript" src="/pams/resource/default/script/public_complete.js"></script>
<style type="text/css">@import url(/pams/themes/default/main.css);</style>
<script type="text/javascript">
var  rootID='R0';

//定义似乎需要放出来，因为树上的操作花样太多
function ajax4Tree_pz1(url,num){
var ohtml='';
$.ajax({
    url:url,
    data:{parentorganid:num},
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
//$('#leftTreeDiv').append('<ul id="gAjaxTree">'+ajax4Tree_pz1('/pams/module/irm/system/organ/user/user_tree.action',rootID)+'</ul>');

//树根节点没有padding 禁止选择
$('#gAjaxTree>li').css({'padding-left':0}).disableSelection();

$('#gAjaxTree li span').live('click',function(){
    var obj=$(this).parent();
	$('#gAjaxTree li span').removeClass('selected');
	$(this).addClass('selected');
    if(obj.find('ul').length==0){
			obj.append('<ul>'+ajax4Tree_pz1('${base}/module/irm/system/organ/user/user_treechild.action',obj.attr('data-id'))+'</ul>')
    }else{
        obj.find('ul').toggle();	
    }
    $(this).toggleClass('open');
	//链接到哪里  $(this).attr('data-id') 是当前节点id
	window.rightIframe.location='${base}/module/pams/gxgl/gxwh/apply_selectscopeuser.action?organid='+obj.attr('data-id');	
}).live('mouseenter',function(){
    $(this).addClass('hover');
}).live('mouseleave',function(){
    $(this).removeClass('hover');
})

//默认点击根节点
$('#gAjaxTree>li span').trigger('click');

//设置拖动条位置及交互效果（放出来是因为根据内容可给出不同于 200px 宽度的左侧-下面 #leftTd 也要改！）
$('#dragHandlerLR').draggable({iframeFix: true,scroll:false,containment:'window',stop:function(){$('.leftTd,#leftTreeDiv').css({width:$(this).position().left});}}).disableSelection().css({left:'200px'});

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
	<li data-pid="R0" data-index="1" data-parent="" data-leaf="1" data-id="R0"><span class="root">组织机构</span></li>
        
</ul>
</div>
</td>
<td class="rightTd">
	<iframe src="about:blank" width="100%" height="100%" frameborder="0" allowtransparency="true" id="iframe1" name="rightIframe" border="0"></iframe>
</td>
</tr>
</table>
<script type="text/javascript">
$("#bt_save").click(function() {page_save()});

function page_save()
{
	var oids=[];
	var ocnames=[];
	var octypes=[];
	var ointernals=[];
	$('.dataGrid tbody .checkbox', window.frames[0].document).each(function(j,k)
	{		
		if($(this).val()==1)
		{
			oids.push($(this).attr('data-id'));
			ocnames.push($(this).attr('data-cname'));	
			octypes.push($(this).attr('data-ctype'));
			ointernals.push($(this).attr('data-internal'));
		}		
	})
	
	if(oids.length<=0)
	{
		alert("请选择要共享的人员！");
		return;
	}
	
	var opener=$('body',window.parent.opener.document);
	
	var v_infosharescope = opener.find('#infosharescope').val();
	var v_infosharescopeid = opener.find('#infosharescopeid').val();
	var v_infosharescopectype = opener.find('#infosharescopectype').val();
	var v_infosharescopeinternal = opener.find('#infosharescopeinternal').val();
	
	
	if(v_infosharescope!="")
	{
		opener.find('#infosharescope').val(v_infosharescope + "," + ocnames);	
	}
	else
	{
		opener.find('#infosharescope').val(ocnames);
	}

	if(v_infosharescopeid!="")
	{
		opener.find('#infosharescopeid').val(v_infosharescopeid + "," + oids);	
	}
	else
	{
		opener.find('#infosharescopeid').val(oids);
	}
	
	if(v_infosharescopectype!="")
	{
		opener.find('#infosharescopectype').val(v_infosharescopectype + "," + octypes);	
	}
	else
	{
		opener.find('#infosharescopectype').val(octypes);
	}
	
	if(v_infosharescopeinternal!="")
	{
		opener.find('#infosharescopeinternal').val(v_infosharescopeinternal + "," + octypes);	
	}
	else
	{
		opener.find('#infosharescopeinternal').val(ointernals);
	}
	
	window.parent.close();
}
</script>
</body>
</html>

