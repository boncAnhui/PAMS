<html>
<head>
<meta charset="utf-8" />
<title>户表管理系统</title>
<script type="text/javascript" src="${base}/themes/default/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${base}/themes/default/gex.js"></script>
<script type="text/javascript" src="${base}/themes/default/main.js"></script>
<style type="text/css">@import url(${base}/themes/default/main.css);</style>
</head>
<body>
<div id="fixedOp">

<#if arg.issave>
	<button id="bt_save">保存</button>
</#if>

<#if arg.isforward == true>
<button id="bt_forward">转发</button>
</#if>

<#if arg.isbackward == true>
<button id="bt_backward">退回</button>
</#if>

</div>
<div id="pageContainer" style="display:inline-block;">
<div class="formDiv">
<form id="aform" action="apply_create.action" method="post">
<input type="hidden" id="runactkey" name="runactkey" value="${arg.runactkey}">
<input type="hidden" id="id" name="id" value="${data.meterapply.id}">

<table>
<tr>
<td>标题：${arg.actcname}</td>
<td><input type="text" id="bt" name="bt" value="${data.meterapply.bt}"/></td>
<td>住宅小区名称：</td>
<td><input type="text" id="zzxqmc" name="zzxqmc" value="${data.meterapply.zzxqmc}"/></td>
</tr>
</table>
</form>
</div>
</div>

<script type="text/javascript">
$("#bt_save").click(function() {page_save()});
$("#bt_forward").click(function() {page_forward()});
$("#bt_backward").click(function() {page_backward()});

$("#bt_relayfrom").click(function() {page_relayfrom()});

function page_save()
{
    $("#aform").attr("target","_parent");
	$("#aform").attr("action","apply_create.action");
    $("#aform").trigger('submit');
}

// 转发
function page_forward()
{
	var url = "${base}/module/app/system/workflow/ui/forwardselectsingleframe.action";
	url += "?runactkey=${arg.runactkey}";
	url += "&tableid=${arg.tableid}";
	openwin(url,"forwardselectsingleframe",pub_width_mid,pub_height_mid);	
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

// 转办
function page_relayfrom()
{
	var url = "${base}/module/pams/hbgl/xxsj/apply_relayfrom.action";
	url += "?runactkey=${arg.runactkey}";
	url += "&tableid=${arg.tableid}";
	openwin(url,"relayfrom",pub_width_mid,pub_height_mid);	
}

</script>
</body>
</html>