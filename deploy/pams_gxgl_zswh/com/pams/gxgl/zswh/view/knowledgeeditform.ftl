<#-- 配置项上传附件文档通用修改表单页面 编辑正文 -->

<table class="formGrid">
	<tr>
	<td class="r"><label for="">标题：</label></td>
	<td colspan="3"><input class="text required" id="title" name="title" maxlength="100" value="${data.knowledge.title}" style="width:600px;"></td>
	</tr>
	<tr>
	<td class="r"><label for="">关键字：</label></td>
	<td colspan="3"><input class="text required" id="keyword" name="keyword" maxlength="100" value="${data.knowledge.keyword}" style="width:600px;" /></td>
	</tr>
    <tr>
    <td class="r"><label for="">摘要：</label></td>
    <td colspan="3"><textarea id="summary" name="summary" class="required" maxlength="500" value="${data.knowledge.summary}" style="width:600px;height:150px;">${data.knowledge.summary}</textarea></td>
    </tr>	
	<tr>
	<td class="r"><label for="">正文：</label></td>
	<td colspan="3"><div><textarea id="content" name="content" maxlength="1000" value="${data.knowledge.content}" style="width:600px;height:300px;">${data.knowledge.content}</textarea></div>
	</td>
	</tr>
	<tr>
	<td class="r">
	<label for="">主作者：</label></td>
	<td>
	<input type="hidden" id="mauthorid" name="mauthorid" value="${data.knowledge.mauthorid}" />
	<input class="text required" id="mauthor" name="mauthor" value="${data.knowledge.mauthor}" readonly />
	<button onclick="page_selectpeople('mauthor','mauthorid','mauthordept','mauthordeptid',0)">选择</button>
	</td>
	<td class="r">
	<label for="">次作者：</label></td><td><input class="text" id="sauthor" name="sauthor" value="${data.knowledge.sauthor}" readonly/>
	<button onclick="page_selectpeople('sauthor','','','',1)">选择</button>
	</td>
	</tr>
	<tr><td class="r"><label for="">主作者部门：</label></td>
	<td>
	<input type="hidden" id="mauthordeptid" name="mauthordeptid" value="${data.knowledge.mauthordeptid}" />
	<input class="text required" id="mauthordept" name="mauthordept" value="${data.knowledge.mauthordept}" readonly/>
<#--	<button onclick="page_selectorgan('mauthordept','mauthordeptid',0)">选择部门</button>-->
	</td>
	<td class="r"><label for="">安全级别：</label></td>
	<td>
	<span class="selectSpan">
	<input type="hidden" id ="slevel" name="slevel" value="${data.knowledge.slevel}" />
	<input class="select required" data-options="10||9||8||7||6||5||4||3||2||1" data-values="10||9||8||7||6||5||4||3||2||1" data-default="${data.knowledge.slevel}" style="width10em" />
	</td>
	</tr>
    <tr>
    <td class="r"><label for="">文档分类：</label></td>
    <td colspan="3"><input class="text" id="title" name="title" id="cclassallname" name="cclassallname" value="${arg.cclassallname}" style="width:600px;" readonly></td>
    </tr>
	<tr>
    <td class="r"><label for="">附件：</label></td>
    <td colspan="3"><span id="file_upload"></span><div class="gUploaded"></div>
    
    <ul class="attachmentUl">
 	<#list arg.list as aobj>
	<li data-id="${aobj.attachid}">
	<a target="_blank" class="attachment" href="${base}/module/irm/system/attach/attach/attach_downloadbyid.action?attachid=${aobj.attachid}">${aobj.attachname}</a><span class="del">X</span><br>
	</li>
	</#list>
	</ul>
	</td>
    </tr>
</table>

<script type="text/javascript">

function page_selectpeople(pname,pid,pdeptname,pdeptid,multi)
{
	openwin('${base}/module/irm/system/organ/user/user_choosepeople.action?pname='+pname+'&pid='+pid+'&pdeptname='+pdeptname+'&pdeptid='+pdeptid+'&multi='+multi,'choosepeople',pub_width_mid,pub_height_mid);
}

function page_selectorgan(pdeptname,pdeptid,multi)
{
	openwin('${base}/module/irm/system/organ/organ/organ_chooseorgan.action?pdeptname='+pdeptname+'&pdeptid='+pdeptid+'&multi='+multi,'chooseorgan',pub_width_mid,pub_height_mid);
}

$(function()
{
/////////////////////////////////////////
$('textarea').each(function(){
	if(!isNaN($(this).attr('maxlength'))){
		$(this).after('<span class="textLimit" title="字数限制为：'+$(this).attr('maxlength')+'"><div class="numDiv"><span></span><span class="num"></span></div></span>');
		var textLeft=$(this).parent().find('.textLimit span.num')
		$(this).limit($(this).attr('maxlength'),textLeft);
		//textLeft.after('个字符').before('还剩')
	}
})	



$('.attachmentUl .del').live('click',function(){
	
	if(window.confirm('你确认要删除这个附件吗？')){
		var oid=$(this).parent().attr('data-id');
		var othis=$(this);
		var oparent=othis.parent();
		$.ajax({
			url:'${base}/module/irm/system/attach/attach/attach_isDelete.action',
			data:{attachid:oid},
			success:function(d){
				if(d=='done'){//后台确认已经删除	
				    			
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


/////////////////////////////////////////
})
</script>
