<html>
<head>
<meta charset="utf-8" />
<title>信息共享单</title>
<script type="text/javascript" src="${base}/themes/default/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${base}/themes/default/gex.js"></script>
<script type="text/javascript" src="${base}/themes/default/main.js"></script>
<style type="text/css">@import url(${base}/themes/default/main.css);</style>
</head>
<body>
<style>
textarea {	}
.textLimit {position:relative;background:red;}
.textLimit .numDiv {position:absolute;right:0;margin-right:20px;top:-30px;}
.textLimit .numDiv span {display:inline;white-space:nowrap;font-size:18px;color:#666;font-size:700;font-style:italic}

span.deltr,span.savetr,span.uploadtr,span.back,span.end,span.start ,span.toggleTr_toggle,span.toggleTr_add{
	display:inline-block;*zoom:1;*display:inline;width: 16px;height: 16px;vertical-align: middle;cursor:pointer;margin: 0 6px;
}

span.deltr {background:url(${base}/themes/default/images/delete.gif) 0 0 no-repeat;} 
span.savetr {background:url(${base}/themes/default/images/disk.gif) 0 0 no-repeat;}
span.uploadtr {background:url(${base}/themes/default/images/upload.gif) 0 0 no-repeat;align:top;}
span.back {background:url(${base}/themes/default/images/arrow_undo.gif) 0 0 no-repeat;}
span.end {background:url(${base}/themes/default/images/accept.gif) 0 0 no-repeat;width:35px;}
span.start {background:url(${base}/themes/default/images/arrow_join.gif) 0 0 no-repeat;}

table.dataGrid{border-collapse:collapse;}
.parent {background:'#F0F0F0'} 
.odd{} 
.selected{}

</style>

<div id="fixedOp">

<#if arg.issave>
	<button id="bt_save">保存</button>
</#if>

<#--
<#if arg.ispublish>
<button id="bt_publish">发布</button>
</#if>
-->

<#if arg.isedit>
<button id="bt_opinion">意见</button>
</#if>

<!-- 
<#if arg.isforward == true>
<button id="bt_forward">转发</button>
</#if>
-->

<#if arg.isforward == true>
	<#list data.routes as aobj>
	<button id="bt_route_${aobj_index}" ctype="${aobj.ctype}" onclick="page_forwardto(${aobj_index},'${aobj.endactid}','${aobj.endactname}','${aobj.ctype}')">${aobj.routename}</button>
	</#list>
</#if>

<#if arg.isbackward == true>
<button id="bt_backward">退回</button>
</#if>

</div>
<div id="pageContainer" style="display:inline-block;">
<div class="formDiv">
<form id="aform" action="apply_create.action" method="post">
<input type="hidden" id="runactkey" name="runactkey" value="${arg.runactkey}">
<input type="hidden" id="id" name="id" value="${data.infoshare.id}">
<input type="hidden" id="publishtime" name="publishtime" value="${data.infoshare.publishtime}">

<input type="hidden" id="actcname" name="actcname" value="${arg.actcname}">

<table class="formGrid">
	<tr>
		<td class="r"><label for="deptname">信息共享部门：</label></td>
		<td>
		<input type="hidden" id="deptid" name="deptid" value="${data.infoshare.deptid}"/>
		<input readonly class="text required" id="deptname" name="deptname" style="width:20em" value="${data.infoshare.deptname}"/>
		</td>
		<td class="r"><label for="positionname">岗位：</label></td>
		<td>
		<input type="text" readonly id="positionname" name="positionname" value="${data.infoshare.positionname}" style="width:20em">
		</td>
	</tr>
	<tr>
		<td class="r"><label for="creatername">共享人：</label></td>
		<td>
		<input type="text" readonly id="creatername" name="creatername" value="${data.infoshare.creatername}" style="width:20em">
		</td>
		<td class="r"><label for="sourcename">信息来源：</label></td>
		<td>
		<span class="selectSpan">
		<input type="hidden" id="sourceid" name="sourceid" value="" >
		<input class="select readonly required" id="selectsourcename" name="selectsourcename"  data-options="${data.sourcename_texts}" data-values="${data.sourcename_values}" data-default="${data.infoshare.sourceid}">
		</span>
		</td>
	</tr>
	<tr>
		<td class="r"><label for="obtaintime">获取时间：</label></td>
		<td>
		<input type="hidden" id="obtaintime" name="obtaintime" value="${data.infoshare.obtaintime}"> 
		<input class="date required" id="obtaintimed" name="obtaintimed" style="width:10em" value="${data.infoshare.obtaintimed}"/>
		<input class="time required" id="obtaintimet" name="obtaintimet" style="width:10em" value="${data.infoshare.obtaintimet}"/>
		</td>
		<td class="r"><label for="publishtime">发布时间：</label></td>
		<td>
		<input type="text" readonly id="publishtime" name="publishtime" value="<#if data.infoshare.publishtime!="">${data.infoshare.publishtime?string("yyyy-MM-dd HH:mm")}</#if>" style="width:20em">
		</td>
	</tr>
	<tr>
		<td class="r"><label for="title">信息共享名称：</label></td>
		<td colspan="3"><input class="text required" id="title" name="title" style="width:50em" value="${data.infoshare.title}"/></td>
	</tr>	
	<tr>
		<td class="r"><label for="summary">内容摘要：</label></td>
		<td colspan="3"><textarea class="text" id="summary" name="summary" maxlength="100">${data.infoshare.summary}</textarea></td>
	</tr>
	<tr>
		<td class="r"><label for="cclassname">分类：</label></td>
		<td>
		<input type="hidden" id="cclassid" name="cclassid" value="${data.infoshare.cclassid}">	
		<input class="text required" id="cclassname" name="cclassname" style="width:20em" value="${data.infoshare.cclassname}"/><button id="bt_cclassname" class="btn2">选择</button></td>
		<#--
		<td class="r"><label id="lb_title">共享权限：</label></td>
		<td>
		<span class="selectSpan">
		<input type="hidden" id="shareauthor" name="shareauthor" value="">
		<input class="select readonly required" id="selectshareauthor" data-options="${data.shareauthor_texts}" data-values="${data.shareauthor_values}" data-default="${data.infoshare.shareauthor}">
		</span>
		</td>
		-->
		<td class="r">&nbsp;</td>
		<td>
	</tr>
	<tr>
		<td class="r"><label for="infosharescope">共享范围：</label></td>
		<td colspan="3">
		<input type="hidden" id="infosharescopeid" name="infosharescopeid" value="${data.infoshare.infosharescopeid}">
		<input type="hidden" id="infosharescopectype" name="infosharescopectype" value="${data.infoshare.infosharescopectype}">
		<input type="hidden" id="infosharescopeinternal" name="infosharescopeinternal" value="${data.infoshare.infosharescopeinternal}">
		<input class="text" id="infosharescope" name="infosharescope" value="${data.infoshare.infosharescope}" style="width:45em" />
		<button id="bt_scope" class="btn2">选择</button>
		<button id="bt_scope_clear" class="btn2">清除</button>
		<button id="bt_scope_reset" class="btn2">恢复</button>
		</td>
	</tr>	
	<tr>
		<td class="r"><label for="memo">备注：</label></td>
		<td colspan="3"><textarea class="text" id="memo" name="memo" maxlength="500">${data.infoshare.memo}</textarea></td>
	</tr>	
	<tr>
		<td class="r"><label for="filenums">文件数量：</label></td>
		<td><input class="text" readonly id="filenums" name="filenums" value="${data.fileattachments?size}" style="width:20em" /></td>
		<td class="r"><label id="filetype">文件形式：</label></td>
		<td><input class="text" id="filetype" name="filetype" value="${data.infoshare.filetype}" style="width:20em"/></td>
	</tr>
	<tr>
		<td class="r">文件列表：</td>
		<td colspan="3" align="left" class="attachname" name="attachname">
		<#if arg.isupload><span class="uploadtr uploadepro" title="上传附件">&nbsp;</span></#if>
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="3">	
		<ul class="attachmentUl" id="attachmentUl">
			<#list data.fileattachments as afile>
			<li data-id="${afile.id}" cno="${afile.cno}">
			<a target="_blank" class="attachment" href="${base}/module/pams/gxgl/wjwh/fileattachment_downloadbyid.action?id=${afile.id}">【${afile.sfilename}】</a>&nbsp;&nbsp;${afile.creatername}&nbsp;于&nbsp;${afile.createtime}&nbsp;在&nbsp; ${afile.actname}&nbsp;业务环节上传。
			<#if arg.isupload><span class="del">X</span></#if><br>
			</li>
			</#list>
		</ul>
		</td>
	</tr>
</table>
</form>
</div>
</div>

<script type="text/javascript">
$("#bt_save").click(function() {page_save()});
$("#bt_publish").click(function() {page_publish()});
$("#bt_opinion").click(function() {page_opinion()});

$("#bt_forward").click(function() {page_forward()});
$("#bt_backward").click(function() {page_backward()});

$("#bt_cclassname").click(function() {page_cclassname()});
$("#bt_scope").click(function() {page_scope()});
$("#bt_scope_clear").click(function() {page_scope_clear()});
$("#bt_scope_reset").click(function() {page_scope_reset()});

function page_save()
{

	if($("#summary").val().length > 100)
	{
		alert("摘要仅能输入100个字符.");
		return;
	}
	
	if($("#memo").val().length > 500)
	{
		alert("备注仅能输入500个字符.");
		return;
	}
	
	$("#obtaintime").val($("#obtaintimed").val() + " " + $("#obtaintimet").val() + ":00");

	// 检查信息获取时间是否超过系统当前时间
	var sobtaintime = $("#obtaintime").val();
	sobtaintime = sobtaintime.replace("-","/");//替换字符，变成标准格式  
	var sysdate = new Date();//取今天的日期  
	var dobtaintime = new Date(Date.parse(sobtaintime));
	if(dobtaintime>sysdate)
	{  
	  alert("信息获取时间大于当前时间，请重新输入。");
	  return;
	}	
	
    $("#aform").attr("target","_parent");
	$("#aform").attr("action","apply_update.action");
    $("#aform").trigger('submit');
}

function page_publish()
{
	if($("#attachmentUl li").length==0)
	{
		if(!confirm("您还没有共享文件，确定要发布共享信息吗？"))
		{
			return;
		}		
	};
	
    $("#aform").attr("target","_self");
	$("#aform").attr("action","apply_publish.action");
    $("#aform").trigger('submit');
}


// 转发
function page_forward()
{
	// 检查是否发布文档	
	if($("#actcname").val()=="信息共享" && $("#publishtime").val()=="")
	{
		alert("您还没有发布文档，发布文档后方可进行转发。");
		return;
	}

	var url = "${base}/module/app/system/workflow/ui/forwardselectsingleframe.action";
	url += "?runactkey=${arg.runactkey}";
	url += "&tableid=${arg.tableid}";
	openwin(url,"forwardselectsingleframe",pub_width_mid,pub_height_mid);	
}

//转发
function page_forwardto(index, endactdefid, endactname, ctype)
{
	// 检查是否结束节点
	if(ctype=="END")
	{
		// 检查是否上传过文件
		if($("#attachmentUl li").length==0)
		{
			if(!confirm("转发结束会自动发布共享文件，您还没有上传共享文件，确定要转发结束吗？"))
			{
				return;
			}		
		};
		
		$.ajax({
			type:"POST",
			url:'${base}/module/pams/gxgl/gxwh/apply_ajaxpublish.action',
			data:{"runactkey":"${arg.runactkey}"},
			success:function(d)
			{
				if(d=='success')
				{
					//后台确认已经发布
					var url = "${base}/module/app/system/workflow/ui/forwardselectsingleframe.action";
					url += "?runactkey=${arg.runactkey}";
					url += "&tableid=${arg.tableid}";
					url += "&endactdefid=" + endactdefid;
					openwin(url,"forwardselectsingleframe",pub_width_mid,pub_height_mid, null);
					return;
				}
				else
				{ 
					//提示发布失败
					alert("发布失败，无法转发至结束，请与系统管理员联系。");
					return;
				}
			}	
		})
	}
	else
	{
		//后台确认已经发布
		var url = "${base}/module/app/system/workflow/ui/forwardselectsingleframe.action";
		url += "?runactkey=${arg.runactkey}";
		url += "&tableid=${arg.tableid}";
		url += "&endactdefid=" + endactdefid;
		openwin(url,"forwardselectsingleframe",pub_width_mid,pub_height_mid, null);	
	}
}

// 退回
function page_backward()
{
	if (confirm("确定退回操作吗？")==true)
	{
		url = "${base}/module/app/system/workflow/ui/backward.action";
		url += "?runactkey=${arg.runactkey}";
		url += "&tableid=${arg.tableid}";
		
		openwin(url,"backward",pub_width_mid,pub_height_mid);
	}				   
}

function page_cclassname()
{
	url = "${base}/module/pams/gxgl/gxwh/apply_selectcclassname.action";
	openwin(url,'',pub_width_small,pub_height_small,null,'新增');
}

function page_scope()
{
	url = "${base}/module/pams/gxgl/gxwh/apply_selectscope.action";
	openwinT(url,'',pub_width_large,pub_height_large,null,'');
}

function page_scope_clear()
{
	$("#infosharescope").val("");
	$("#infosharescopectype").val("");
	$("#infosharescopeid").val("");
	$("#infosharescopeinternal").val("");
}

function page_scope_reset()
{
	$("#infosharescope").val("${data.defsharescope}");
	$("#infosharescopectype").val("${data.defsharescopectype}");
	$("#infosharescopeid").val("${data.defsharescopeid}");
	$("#infosharescopeinternal").val("${data.defsharescopeinternal}");
}

//填写意见
function page_opinion()
{
		url = "${base}/module/irm/system/flow/opiniontemplate/opiniontemplate_opinion.action";
		url += "?actdefid=${arg.actdefid}";
		url += "&runactkey=${arg.runactkey}";
		url += "&runflowkey=${arg.runflowkey}";
		url += "&runactname=${arg.runactname}";
		url += "&dataid=${arg.id}";
		url += "&tableid=InfoShare";
		openwin(url,"opinion",pub_width_mid,pub_height_mid);
}


$(function()
{
	// 上传附件
	$('.uploadepro').click(function(){page_uploadepro($(this))}); // 附件上传控件
	
	function page_uploadepro(obj)
	{
		var cno = "";
		openwin('apply_upload.action?runactkey=${arg.runactkey}&cno=' + cno,'upload',pub_width_small,pub_height_small);
	}
	
	// 删除附件
	$('.attachmentUl .del').live('click',function()
	{
		page_delattachment($(this));
	})

	function page_delattachment(obj)
	{
		if(window.confirm('你确认要删除这个附件吗？')){
			var oid=obj.parent().attr('data-id');
			var oparent=obj.parent();
			$.ajax({
				url:'${base}/module/pams/gxgl/wjwh/fileattachment_ajaxdelete.action',
				data:{attachid:oid},
				success:function(d){
					if(d=='done'){//后台确认已经删除	
					    			
						oparent.animate({opacity:0},'fast','swing',function(){						
							oparent.empty().css({opacity:1,color:'#c00'}).append('该附件已经被删除！');
							setTimeout(function(){
								oparent.remove();
							},1000)							
						})
					}else{ //删除失败
						oparent.find('.error').remove();
						oparent.append(' <span class="error" style="color:#c00">删除操作失败：该文件已经被删除，或者你没有权限</span>');
					}
					
				}	
			})
		}

	}
	
	
})

</script>
</body>
</html>