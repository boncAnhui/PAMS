<#macro businessattachupload scriptData>

var uploadQueue=[];
var uploadNames=[];

$('#file_upload').uploadify({
    'uploader'  : '${base}/uploadify/uploadify.swf',
    'script'    : '${base}/module/irm/system/attach/attach/attach_uploadbusiness.action;jsessionid=${request.session.id}',
    'scriptData': ${scriptData},
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
				ohtml+='<li data-id="'+k[1]+'"><a target="_blank" class="attachment" href="${base}/module/irm/system/attach/attach/attach_downloadbyid.action?attachid='+k[1]+'">'+uploadNames[j]+'</a><span class="del" title="删除">x</span></li>';
				oid.push(k[1])
				oname.push(k[2])
			})
			ohtml+='</ul>';
			$('.gUploaded').empty().append(ohtml);
			$('#attachid').val(oid.join(','))
			$('#attachname').val(oname.join(','))


		//	$('.gUploaded span.del').click(function(){
		//		oid=[];//清空重算
		//		var oParent=$(this).parents('.gUploaded');
		//		var oindex=$(this).index()-1;
		//		
		//		$(this).parent().remove();
		//		oParent.find('li').each(function(){
		//			oid.push($(this).attr('data-id'))
		//			oname.push($(this).attr('data-cname'))
		//		})
		//		$('#attachid').val(oid.join(','))
		//		$('#attachname').val(oname.join(','))				
		//		
		//		uploadQueue.splice(oindex,1);
		//		uploadNames.splice(oindex,1);
		//		
		//		
		//	})
		}
	}
  }); 
  
 </#macro>

 
 
<#macro businessattachuploadgeneral idname classname scriptData>

var uploadQueue_${idname}=[];
var uploadNames_${idname}=[];

var upURL='${base}/module/irm/system/attach/attach/attach_uploadbusiness.action';
if($.browser.mozilla){
	upURL='${base}/module/irm/system/attach/attach/attach_uploadbusiness.action;jsessionid=${request.session.id}';
}
$('#${idname}').uploadify({
    'uploader'  : '${base}/uploadify/uploadify.swf',
    'script'    : upURL,
    'scriptData': ${scriptData},
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
			uploadQueue_${idname}.push(response);
			uploadNames_${idname}.push(fileObj.name)
			var ohtml='<ul class="attachmentUl">';
			var oid=[];
			var oname=[];
			$.each(uploadQueue_${idname},function(j,k){				
				ohtml+='<li data-id="'+k[1]+'"><a target="_blank" class="attachment" href="${base}/module/irm/system/attach/attach/attach_downloadbyid.action?attachid='+k[1]+'">'+uploadNames_${idname}[j]+'</a><span class="del" title="删除">x</span></li>';
				oid.push(k[1])
				oname.push(k[2])
			})
			ohtml+='</ul>';
			$('.${classname}').empty().append(ohtml);
			$('#attachid').val(oid.join(','))
			$('#attachname').val(oname.join(','))


	//		$('.${classname} span.del').click(function(){
	//			oid=[];//清空重算
	//			var oParent=$(this).parents('.${classname}');
	//			var oindex=$(this).index()-1;
	//			
	//			$(this).parent().remove();
	//			oParent.find('li').each(function(){
	//				oid.push($(this).attr('data-id'))
	//				oname.push($(this).attr('data-cname'))
	//			})
	//			$('#attachid').val(oid.join(','))
	//			$('#attachname').val(oname.join(','))				
	//			
	//			uploadQueue_${idname}.splice(oindex,1);
	//			uploadNames_${idname}.splice(oindex,1);
	//			
	//			
	//		})
		}
	}
  }); 
  
 </#macro>
 