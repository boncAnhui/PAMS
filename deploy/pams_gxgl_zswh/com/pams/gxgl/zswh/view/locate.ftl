<!DOCTYPE HTML>
<html>
<head>
<#include "/decorator/include/include.ftl">
<script type="text/javascript" src="${base}/themes/default/jquery.limit-1.2.source.js"></script>
<style>
textarea {	}
.textLimit {position:relative;background:red;}
.textLimit .numDiv {position:absolute;right:0;margin-right:20px;top:-30px;}
.textLimit .numDiv span {display:inline;white-space:nowrap;font-size:18px;color:#666;font-size:700;font-style:italic}
</style>

<script type="text/javascript">

$(function(){
/////////////////////////////////////////
$('textarea').each(function(){
	if(!isNaN($(this).attr('maxlength'))){
		$(this).after('<span class="textLimit" title="字数限制为：'+$(this).attr('maxlength')+'"><div class="numDiv"><span></span><span class="num"></span></div></span>');
		var textLeft=$(this).parent().find('.textLimit span.num')
		$(this).limit($(this).attr('maxlength'),textLeft);
		//textLeft.after('个字符').before('还剩')
	}
})	

/////////////////////////////////////////
})
</script>

<script type="text/javascript">
var navigationJSON=[{name:'业务知识',link:'portal5.htm'},{name:'新增知识'}];

jQuery(function(){
///////////////////
var uploadQueue=[];
var uploadNames=[];

$('#file_upload').uploadify({
    'uploader'  : '${base}/uploadify/uploadify.swf',
    'script'    : '${base}/module/irm/system/attach/attach/attach_upload.action',
	'buttonImg': '${base}/uploadify/upload.png',
    'cancelImg' : '${base}/uploadify/cancel.png',
    'folder'    : 'uploads',
    'fileDataName':'fupload',
	'multi':true,
	'width':107,
	'height':30,
	'auto':true,
	'onComplete':function(e,ID, fileObj, response, data){
		if(response!='error'){
			eval('response='+response);
			uploadQueue.push(response);
			uploadNames.push(fileObj.name)
			var ohtml='<ul>';
			var oid=[];
			var oname=[];
			$.each(uploadQueue,function(j,k){				
				ohtml+='<li data-id="'+k[1]+'"><a target="_blank" class="attachment" href="${base}/module/pams/gxgl/wjwh/fileattachment_downloadbyid.action?id='+k[1]+'">'+uploadNames[j]+'</a><span class="del" title="删除">x</span></li>';
				oid.push(k[1])
				oname.push(k[2])
			})
			ohtml+='</ul>';
			$('.gUploaded').empty().append(ohtml);
			$('#attachid').val(oid.join(','))
			$('#attachname').val(oname.join(','))


			$('.gUploaded span.del').click(function(){
				oid=[];//清空重算
				var oParent=$(this).parents('.gUploaded');
				var oindex=$(this).index()-1;
				
				$(this).parent().remove();
				oParent.find('li').each(function(){
					oid.push($(this).attr('data-id'))
					oname.push($(this).attr('data-cname'))
				})
				$('#attachid').val(oid.join(','))
				$('#attachname').val(oname.join(','))				
				
				uploadQueue.splice(oindex,1);
				uploadNames.splice(oindex,1);
				
				
			})
		}
	}
  }); 
  

$("#bt_save").click(function() {page_save()});


///////////////////
})

function page_save()
{	
	$("#aform").attr('action','knowledge_update.action?classid=${arg.cclassid}&id=${arg.id}');
    $("#aform").trigger('submit');
}

function page_close()
{
	window.close();
	// window.location = "knowledge_browse.action?_searchname=knowledge.knowledge.knowledge.browse&cclassid=${arg.cclassid}";
	window.opener.location.reload(); 
}

// 根据指定目标活动路由线路转发
function page_forwardto(endactdefid)
{
	var OK=ajaxValidate($('form textarea,input'));
    if(!OK)
    {
	    return;
    }	
    var source = $('#source').val();
 
	$.ajax({
		type:"post",
		url:"knowledge_update.action?updatetype=ajax",
		data:$('form').serialize(),
		success:function(data)
		{
			url = "${base}/module/app/system/workflow/ui/singleroutechoiceowner.action";
			url += "?beginactdefid=${arg.actdefid}";
			url += "&actdefid=" + endactdefid;
			url += "&tableid=${arg.tableid}";
			url += "&dataid=${arg.id}";
			openwin(url,"chargowner",pub_width_mid,pub_height_mid);	
		}
	})
}

</script>
</head>
<body>
<div id="fixedOp">
<button id="bt_save">保存</button>
<#if arg.isforward == true>
	<#list data.routes as aroute>
	<button id="bt_forward_${index_route}" onclick="page_forwardto('${aroute.endactid}')">${aroute.routename}</button>
	</#list>	
</#if>
</div>

<div id="pageContainer">

<h1 class="tabh1"><span class="icon_ccsb"></span>修改知识文档</h1>
<form id="aform" method="post">
<input type="hidden" id="attachid" name="attachid">
<input type="hidden" id="attachname" name="attachname">
<input name="cclassid" id="cclassid" type="hidden" value="${arg.cclassid}" />
<#include "/com/ray/xj/sgcc/irm/knowledge/knowledge/knowledge/view/knowledgeeditform.ftl">
</form>   

</div>
</body>
</html>


