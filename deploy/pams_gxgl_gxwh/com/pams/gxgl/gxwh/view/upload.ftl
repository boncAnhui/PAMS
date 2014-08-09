<#--上传附件-->
<!DOCTYPE HTML>
<html>
<head>
<#include "/decorator/include/include.ftl">
<script type="text/javascript" src="${base}/themes/default/jquery.limit-1.2.source.js"></script>
<script>

jQuery(function(){
///////////////////
// 附件上传
var uploadQueue=[];
var uploadNames=[];
 
$('#file_upload').uploadify({
    'uploader'  : '${base}/uploadify/uploadify.swf',
    'script'    : '${base}/module/pams/gxgl/wjwh/fileattachment_upload.action',
    'scriptData': {'runactkey':'${data.obj_ract.runactkey}','cno':'${data.obj_filetemplate.cno}','cclass':'${data.obj_filetemplate.cclass}'},
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
			var ohtml='<ul class="attachmentUl">';
			var oid=[];
			var oname=[];
			$.each(uploadQueue,function(j,k){				
				ohtml+='<li data-id="'+k[1]+'" data-name="'+k[2]+'"><a target="_blank" class="attachment" href="${base}/module/pams/gxgl/wjwh/fileattachment_downloadbyid.action?id='+k[1]+'">'+uploadNames[j]+'</a><span class="del" title="删除">x</span></li>';
				oid.push(k[1])
				oname.push(k[2])
			})
			ohtml+='</ul>';
			$('.gUploaded').empty().append(ohtml);
			$('#attachid').val(oid.join(','));
			$('#attachname').val(oname.join(','));
			window.location.reload();
		}
	}
  }); 

$('.attachmentUl .del').live('click',function(){
	
	if(window.confirm('你确认要删除这个附件吗？')){
		var oid=$(this).parent().attr('data-id');
		var oname=$(this).parent().attr('data-name');
		var othis=$(this);
		var oparent=othis.parent();
		$.ajax({
			url:'${base}/module/pams/gxgl/wjwh/fileattachment_isdelete.action',
			data:{attachid:oid},
			success:function(d){
				if(d=='done'){//后台确认已经删除	
				    var new_uploadQueue=[];
					var new_uploadNames=[];

					for(i=0;i<uploadNames.length;i++)
					{
						if(uploadNames[i] != oname)
						{	
							new_uploadQueue.push(uploadQueue[i]);
							new_uploadNames.push(uploadNames[i]);
						}
					}		
					uploadQueue=new_uploadQueue;
					uploadNames=new_uploadNames;
					//alert(uploadNames)			
					oparent.animate({opacity:0},'fast','swing',function(){						
						oparent.empty().css({opacity:1,color:'#c00'}).append('该附件已经被删除！');
						setTimeout(function(){
							oparent.remove()
						},1000)							
					})
				}else{ //删除失败
					oparent.find('.error').remove();
					oparent.append(' <span class="error" style="color:#c00">删除操作失败：该文件已经被删除，或者你没有权限</span>');
				}
				
			}	
		})
	}
})


$("#bt_close").click(function() {page_close()});
////////////////////
})

function page_close()
{
	window.opener.location.reload();
	window.close();
}


</script>
</head>
<body>
<div id="fixedOp">

<button id="bt_close">关闭</button>
</div>

<div id="pageContainer">
<form id="aform" method="post">
	<input type="hidden" id="attachid" name="attachid">
	<input type="hidden" id="attachname" name="attachname">
<div class="formSec2">
	<table class="formGrid">
		<tr>
			<td class="r"><label for="">文档名称：</label></td>
			<td colspan="3"><input readonly id= "cname" name="cname" value="${data.obj_filetemplate.cname}" style="width:20em;"/></td>
		</tr>
		<tr>
		   	<td class="r"><label for="">附件：</label></td>
		    <td colspan="3">
				<span id="file_upload"></span>
				<div class="gUploaded"></div> 
	   			<ul class="attachmentUl">
				<#--
		 		<#list data.attachs as aobj>
				<li data-id="${aobj.attachid}">
				<a target="_blank" class="attachment" href="${base}/module/pams/gxgl/wjwh/fileattachment_downloadbyid.action?attachid=${aobj.attachid}">${aobj.attachname}</a><span class="del">X</span><br>
				</li>
				</#list>
				-->
			</td>     
	    </tr>
	</table>
</div>
</form>
</div>
</body>
</html>