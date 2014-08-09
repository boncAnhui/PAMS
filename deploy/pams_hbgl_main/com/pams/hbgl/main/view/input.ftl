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
</div>


<div id="pageContainer" style="display:inline-block;">
<div class="formDiv">
<form id="aform" action="apply_create.action" method="post">
<input type="hidden" id="flowdefid" name="flowdefid" value="HBGL">
<table>
<tr>
<td>标题：</td>
<td><input type="text" id="bt" name="bt"/></td>
<td>住宅小区名称：</td>
<td><input type="text" id="zzxqmc" name="zzxqmc"/></td>
</tr>
</table>
</form>
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