<html>
<head>
<meta charset="utf-8" />
<title></title>
<link rel="shortcut icon" href="favicon.ico">
<script type="text/javascript" src="${base}/themes/default/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${base}/themes/default/gex.js"></script>
<script type="text/javascript" src="${base}/themes/default/main.js"></script>
<script type="text/javascript" src="${base}/resource/default/script/public_complete.js"></script>
<style type="text/css">@import url(${base}/themes/default/main.css);</style>
</head>
<body>
<div class="formDiv">
<form id="aform" action="apply_create.action" method="post">
<br/>
<br/>
<table class="formGrid">
<tbody>
<tr>
	<td class="r"><label for="flowdefid">流程：</label></td>
	<td width="250">
	<span class="selectSpan">
	<input type="hidden" id="flowdefid" name="flowdefid">
	<input readonly class="select required" style="width:200;" id="selectflowdefid" data-options="${data.bflow_texts}" data-values="${data.bflow_values}" data-default="">
	</span>
	</td>
	<td>
	<button id="bt_save">确定</button>
	</td>
</tr>
</tbody>	
</table>
</div>

<script type="text/javascript">
$("#bt_save").click(function() {page_save()});
$("#bt_cclassname").click(function() {page_cclassname()});
$("#bt_scope").click(function() {page_scope()});

function page_save()
{
	var flowdefid = $("#flowdefid").val();
	if(flowdefid=="")
	{
		alert("共享申请单必须指定流程，请您选择流程。");
		return;
	}
	$("#flowdefid", window.opener.document).val(flowdefid);
	window.close();
}
</script>
</body>
</html>