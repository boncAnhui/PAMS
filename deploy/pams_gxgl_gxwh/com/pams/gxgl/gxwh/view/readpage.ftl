<html>
<head>
<meta charset="utf-8" />
<title>信息共享系统</title>
<script type="text/javascript" src="${base}/themes/default/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${base}/themes/default/gex.js"></script>
<script type="text/javascript" src="${base}/themes/default/main.js"></script>
<style type="text/css">@import url(${base}/themes/default/main.css);</style>
</head>
<body>
<div id="fixedOp">

<#if arg.isapply == true>
	<button id="bt_apply">签收</button>
</#if>

<#if arg.isforward == true>
<button id="bt_forward">转发</button>
</#if>

<#if arg.iscallback == true>
<button id="bt_callback">收回</button>
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

<table class="formGrid">
	<tr>
		<td class="r"><label for="deptname">信息共享部门：</label></td>
		<td>
		<input type="hidden" id="deptid" name="deptid" value="${data.infoshare.deptid}"/>
		<input readonly class="text required" id="deptname" name="deptname" style="width:20em" value="${data.infoshare.deptname}"/>
		</td>
		<td class="r"><label for="positionname">岗位：</label></td>
		<td>
		<span class="selectSpan">
		<input type="hidden" id="positionname" name="positionname">
		<input readonly class="select required" id="selectpositionname" data-options="${data.userrole_texts}" data-values="${data.userrole_values}" data-default="${data.infoshare.positionname}">
		</span>
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
		<td colspan="3"><textarea class="text" id="summary" name="summary" maxlength="500">${data.infoshare.summary}</textarea></td>
	</tr>
	<tr>
		<td class="r"><label for="cclassname">分类：</label></td>
		<td><input class="text required" id="cclassname" name="cclassname" style="width:20em" value="${data.infoshare.cclassname}"/></td>
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
		<input type="hidden" id="infosharescopeid" name="infosharescopeid">
		<input type="hidden" id="infosharescopectype" name="infosharescopectype">
		<input class="text" id="infosharescope" name="infosharescope" value="${data.infoshare.infosharescope}" style="width:45em" />
		</td>
	</tr>	
	<tr>
		<td class="r"><label for="memo">备注：</label></td>
		<td colspan="3"><textarea class="text" id="memo" name="memo" maxlength="500">${data.infoshare.memo}</textarea></td>
	</tr>	
	<tr>
		<td class="r"><label for="filenums">文件数量：</label></td>
		<td><input class="text" id="filenums" name="filenums" value="${data.infoshare.filenums}" style="width:20em" /></td>
		<td class="r"><label id="filetype">文件形式：</label></td>
		<td><input class="text" id="filetype" name="filetype" value="${data.infoshare.filetype}" style="width:20em"/></td>
	</tr>
	<tr>
		<td class="r">文件列表：</td>
		<td colspan="3" align="left" class="attachname" name="attachname">
		<span class="uploadtr uploadepro" title="上传附件">&nbsp;</span>
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="3">	
		<ul class="attachmentUl">
			<#list data.fileattachments as afile>
			<li data-id="${afile.id}" cno="${afile.cno}">
			<a target="_blank" class="attachment" href="${base}/module/pams/gxgl/wjwh/fileattachment_downloadbyid.action?id=${afile.id}">【${afile.sfilename}】</a>&nbsp;&nbsp;${afile.creatername}&nbsp;于&nbsp;${afile.createtime}&nbsp;在&nbsp; ${afile.actname}&nbsp;业务环节上传。<br>
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
$("#bt_forward").click(function() {page_forward()});
$("#bt_relayfrom").click(function() {page_relayfrom()});
$("#bt_callback").click(function() {page_callback()});
$("#bt_apply").click(function() {page_apply()});
$("#bt_backward").click(function() {page_backward()});

function page_save()
{
    $("#aform").attr("target","_parent");
	$("#aform").attr("action","apply_create.action");
    $("#aform").trigger('submit');
}

// 
function page_relayfrom()
{
	var url = "${base}/module/pams/hbgl/xxsj/apply_relayfrom.action";
	url += "?runactkey=${arg.runactkey}";
	url += "&tableid=${arg.tableid}";
	openwin(url,"relayfrom",pub_width_mid,pub_height_mid);	
}

// 签收
function page_apply()
{
	url = "apply_apply.action?runactkey=${arg.runactkey}";	 
	window.parent.location = url;
}

// 转发
function page_forward()
{
	var url = "${base}/module/app/system/workflow/ui/forwardselectsingleframe.action";
	url += "?runactkey=${arg.runactkey}";
	url += "&tableid=${arg.tableid}";
	openwin(url,"forwardselectsingleframe",pub_width_mid,pub_height_mid);	
}

// 收回
function page_callback() 
{
	url = "${base}/module/app/system/workflow/ui/callback.action";
	url += "?runactkey=${arg.runactkey}";
	url += "&tableid=${arg.tableid}";

	openwin(url,"callback",pub_width_mid,pub_height_mid);	
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
</script>
</body>
</html>