<html>
<head>
<meta charset="utf-8" />
<title>户表管理系统</title>
<link rel="shortcut icon" href="favicon.ico">
<script type="text/javascript" src="${base}/themes/default/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${base}/themes/default/gex.js"></script>
<script type="text/javascript" src="${base}/themes/default/main.js"></script>
<script type="text/javascript" src="${base}/resource/default/script/public_complete.js"></script>
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

<div id="pageContainer">

<table class="dataGrid">
<thead>
	<tr>
		<th width="100">业务</th>
		<th width="50">编号</th>
		<th width="450">文件名称</th>
		<th width="50">查阅</th>
		<th width="100">文件类型</th>
		<th width="40">属性</th>
		<th width="40">必要</th>
		<th width="40">完成</th>
	</tr>
</thead>
<tbody>
	<#assign actname_old="">
	<#list data.actfiles as aobj>
	<#assign checkrequired = 0>
	<#assign checkcomplete = 0>
	<#if aobj.required == "Y">
		<#assign checkrequired = 1>
	</#if>
	<#if aobj.num &gt; 0>
		<#assign checkcomplete = 1>
	</#if>
	<tr class="parentact" id="row_${aobj_index}">
		<td><#if aobj_index==0>${aobj.actname}</#if></td>
		<td>${aobj.cno}</td>
		<td>${aobj.cname}</td>
		<td>${aobj.num}</td>
		<td>${aobj.ctype}</td>
		<td>${aobj.property}</td>
		<td><input class="checkbox" value="${checkrequired}"></td>
		<td><input class="checkbox" value="${checkcomplete}"></td>
	</tr>
	<tr class="child_row_${aobj_index}">
		<td style="height:50;"></td>
		<td cno="${aobj.cno}" valign="top"><span class="uploadtr uploadepro" title="上传附件"></span></td>
		<td colspan="6" valign="top" class="attachname" name="attachname">
		<ul class="attachmentUl">
			<#list data.fileattachments as afile>
			<#if afile.runactkey==arg.runactkey && afile.cno==aobj.cno>
			<li data-id="${afile.id}">
			<a target="_blank" class="attachment" href="${base}/module/pams/hbgl/file/fileattachment_downloadbyid.action?id=${afile.id}">【${afile.sfilename}】&nbsp;&nbsp;${afile.creatername}&nbsp;&nbsp;${afile.createtime}</a>&nbsp;&nbsp;<span class="del">X</span>&nbsp;&nbsp;<img src="${base}/themes/default/images/share.png" valign="middle" style="cursor:hand"><br>
			</li>
			</#if>
			</#list>
		</ul>
		</td>
	</tr>	
	<#assign actname_old = aobj.actname>
	</#list>
</tbody>
</table>

<table class="dataGrid">
<thead>
	<tr>
		<th width="100">业务</th>
		<th width="50">编号</th>
		<th width="450">文件名称</th>
		<th width="50">共享</th>
		<th width="100">文件类型</th>
		<th width="40">属性</th>
		<th width="40">必要</th>
		<th width="40">完成</th>
	</tr>
</thead>
<tbody>
	<#assign actname_old="">
	<#list data.flowfiles as aobj>
	<#assign checkrequired = 0>
	<#assign checkcomplete = 0>
	<#if aobj.required == "Y">
		<#assign checkrequired = 1>
	</#if>
	<#if aobj.complete == "N">
		<#assign checkcomplete = 1>
	</#if>
	<tr class="parentflow" id="row_${aobj_index}">
		<td><#if aobj.actname==actname_old><#else>${aobj.actname}</#if></td>
		<td>${aobj.cno}</td>
		<td>${aobj.cname}</td>
		<td></td>
		<td>${aobj.ctype}</td>
		<td>${aobj.property}</td>
		<td><input class="checkbox" value="${checkrequired}"></td>
		<td><input class="checkbox" value="${checkcomplete}"></td>
	</tr>
	<tr class="child_row_${aobj_index}">
		<td style="height:50;"></td>
		<td></td>
		<td colspan="6">&nbsp;</td>
	</tr>
	<#assign actname_old = aobj.actname>
	</#list>
</tbody>
</table>

</div>

<script>
function page_sibling(pid)
{
	$("#"+pid).toggleClass("selected").siblings('.child_'+pid).toggle();
}

$(function()
{
	$('tr.parentflow').click(function()
	{  
		$(this).toggleClass("selected").siblings('.child_'+this.id).toggle();
	}).click();
	
	$('tr.parentact').click(function()
	{  
		$(this).toggleClass("selected").siblings('.child_'+this.id).toggle();
	}).click();
	
	$('.uploadepro').click(function(){page_uploadepro($(this))}); // 附件上传控件
	
	function page_uploadepro(obj)
	{
		var cno = obj.parent().attr("cno");
		openwin('apply_upload.action?runactkey=${arg.runactkey}&cno=' + cno,'upload',pub_width_small,pub_height_small);
	}
	
})

</script>

</body>
</html>