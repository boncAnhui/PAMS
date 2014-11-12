<html>
<head>
<meta charset="utf-8" />
<title>信息共享单</title>
<script type="text/javascript" src="${base}/themes/default/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${base}/themes/default/gex.js"></script>
<script type="text/javascript" src="${base}/themes/default/main.js"></script>
<script type="text/javascript" src="${base}/themes/default/jquery.limit-1.2.source.js"></script>
<style type="text/css">@import url(${base}/themes/default/main.css);</style>

<script>
$(function()
{
	$('textarea').each(function()
	{
		if(!isNaN($(this).attr('maxlength')))
		{
			$(this).after('<span class="textLimit" title="字数限制为：'+$(this).attr('maxlength')+'"><div class="numDiv"><span></span><span class="num"></span></div></span>');
			var textLeft=$(this).parent().find('.textLimit span.num');
			$(this).limit($(this).attr('maxlength'),textLeft);
			//textLeft.after('个字符').before('还剩');
		}
	})
})
</script>
</head>
<body>
<style>
textarea {	}
.textLimit {position:relative;background:red;}
.textLimit .numDiv {position:absolute;right:0;margin-right:20px;top:-30px;}
.textLimit .numDiv span {display:inline;white-space:nowrap;font-size:18px;color:#666;font-weight:700;font-style:italic}

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

</div>
<div id="pageContainer" style="display:inline-block;">
<div class="formDiv">
<form id="aform" action="apply_create.action" method="post">
<input type="hidden" id="id" name="id" value="${data.plan.id}">

<input type="hidden" id="actcname" name="actcname" value="${arg.actcname}">

<table class="formGrid">
	<tr>
		<td class="r"><label for="deptname">信息共享部门：</label></td>
		<td>
		<input type="hidden" id="deptid" name="deptid" value="${data.plan.deptid}"/>
		<input readonly class="text required" id="deptname" name="deptname" style="width:20em" value="${data.plan.deptname}"/>
		</td>
		<td class="r"><label for="positionname">岗位：</label></td>
		<td>
		<input type="text" readonly id="positionname" name="positionname" value="${data.plan.positionname}" style="width:20em">
		</td>
	</tr>
	<tr>
		<td class="r"><label for="creatername">共享人：</label></td>
		<td>
		<input type="text" readonly id="creatername" name="creatername" value="${data.plan.creatername}" style="width:20em">
		</td>
		<td class="r"><label for="sourcename">信息来源：</label></td>
		<td>
		</td>
	</tr>
	<tr>
		<td class="r"><label for="title">计划名称：</label></td>
		<td colspan="3"><input class="text required" id="title" name="title" style="width:50em" value="${data.plan.title}"/></td>
	</tr>	
	<tr>
		<td class="r"><label for="memo">备注：</label></td>
		<td colspan="3"><textarea class="text" id="memo" name="memo" maxlength="500">${data.plan.memo}</textarea></td>
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
    $("#aform").attr("target","_parent");
	$("#aform").attr("action","apply_update.action");
    $("#aform").trigger('submit');
}
</script>
</body>
</html>