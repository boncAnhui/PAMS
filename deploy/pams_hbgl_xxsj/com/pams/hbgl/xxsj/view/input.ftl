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
<div class="formDiv">

<div id="fixedOp">
<button id="bt_save" class="btn2">保存</button>
</div>

<div id="pageContainer" style="display:inline-block;">
	<div class="formDiv">
	<form id="aform" action="apply_create.action" method="post">
		<input type="hidden" id="flowdefid" name="flowdefid" value="HBGL_XXSJ">
		<table class="formGrid">
		<tr>
		<td><label id="lb_bt">标题：</label></td>
		<td><input class="text" id="bt" name="bt" style="width:20em"/></td>
		<td><label id="lb_bt">住宅小区名称：</label></td>
		<td><input class="text" id="zzxqmc" name="zzxqmc" style="width:20em"/></td>
		</tr>
		</table>
	</form>
	</div>
</div>

</div>

<script type="text/javascript">
$("#bt_save").click(function() {page_save()});

function page_save()
{
    $("#aform").attr("target","_parent");
	$("#aform").attr("action","apply_insert.action");
    $("#aform").trigger('submit');
}
</script>
</body>
</html>