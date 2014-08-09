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
<button id="bt_relayto">办理</button>
</div>
<div id="pageContainer" style="display:inline-block;">
<div class="formDiv">
<form id="aform" action="apply_relayto.action" method="post">
<input type="hidden" id="id" name="id" value="${data.relay.id}">

<table>
<tr>
<td>转办工作：</td>
<td><input type="text" id="workname" name="workname" value="${data.relay.workname}"/></td>
</tr>
<td>流程实例标识：</td>
<td><input type="text" id="runflowkey" name="runflowkey" value="${data.relay.runflowkey}"/></td>
</tr>
<td>流程序列号：</td>
<td><input type="text" id="flowsno" name="flowsno" value="${data.relay.flowsno}"/></td>
</tr>

</table>
</form>
</div>
</div>

<script type="text/javascript">
$("#bt_relayto").click(function() {page_relayto()});

function page_relayto()
{
    $("#aform").attr("target","_parent");
	$("#aform").attr("action","apply_relayto.action");
    $("#aform").trigger('submit');
}
</script>
</body>
</html>