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
<button id="bt_save">保存</button>
<button id="bt_createsubflow">启动流程</button>
<button id="bt_forward">转发</button>
</div>
<div id="pageContainer" style="display:inline-block;">
<div class="formDiv">
<form id="aform" action="apply_create.action" method="post">
<input type="hidden" id="runactkey" name="runactkey" value="${arg.runactkey}">
<input type="hidden" id="id" name="id" value="${data.meterapply.id}">

<table>
<tr>
<td>标题：</td>
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
$("#bt_createsubflow").click(function() {page_createsubflow()});
function page_save()
{
    $("#aform").attr("target","_parent");
	$("#aform").attr("action","apply_save.action");
    $("#aform").trigger('submit');
}

function page_forward()
{
	var url = "${base}/module/app/system/workflow/ui/forwardselectsingleframe.action";
	url += "?runactkey=${arg.runactkey}";
	url += "&tableid=${arg.tableid}";
	openwin(url,"forwardselectsingleframe",pub_width_mid,pub_height_mid);	
}

function page_createsubflow()
{
	var url = "${base}/module/pams/hbgl/main/apply_createsubflow.action";
	url += "?runactkey=${arg.runactkey}";
	url += "&tableid=${arg.tableid}";
	openwin(url,"forwardselectsingleframe",pub_width_mid,pub_height_mid);
}

</script>
</body>
</html>