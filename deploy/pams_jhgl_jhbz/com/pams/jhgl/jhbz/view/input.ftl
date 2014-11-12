<html>
<head>
<meta charset="utf-8" />
<title>信息共享单</title>
<link rel="shortcut icon" href="favicon.ico">
<script type="text/javascript" src="${base}/themes/default/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${base}/themes/default/gex.js"></script>
<script type="text/javascript" src="${base}/themes/default/main.js"></script>
<script type="text/javascript" src="${base}/resource/default/script/public_complete.js"></script>
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
textarea {}
.textLimit {position:relative;background:red;}
.textLimit .numDiv {position:absolute;right:0;margin-right:20px;top:-30px;}
.textLimit .numDiv span {display:inline;white-space:nowrap;font-size:18px;color:#666;font-weight:700;font-style:italic}
</style>

<div class="formDiv">

<div id="fixedOp">
<button id="bt_save" class="btn2">保存</button>
</div>

<div id="pageContainer" style="display:inline-block;">
	<div class="formDiv">

	<form id="aform" action="apply_create.action" method="post">
	<input type="hidden" id="supid" name="supid" value="${arg.supid}">
	<table class="formGrid">
	<tr>
		<td class="r"><label for="creatername">共享人：</label></td>
		<td colspan="3">
		<input type="hidden" id="planmodelid" name="planmodelid" value="">
		<input type="text" readonly id="planmodelname" name="planmodelname" value="" style="width:20em">
		<button id="bt_selectmodel">模板</button> 
		</td>
	</tr>	
	<tr>
		<td class="r"><label for="deptname">信息共享部门：</label></td>
		<td>
		<input type="hidden" id="deptid" name="deptid" value="${data.deptid}"/>
		<input readonly class="text " id="deptname" name="deptname" style="width:20em" value="${data.deptname}"/>
		</td>
		<td class="r"><label for="positionname">岗位：</label></td>
		<td>
		<span class="selectSpan">
		<#--
		<input type="hidden" id="positionname" name="positionname">
		<input class="select readonly required" id="selectpositionname" data-options="${data.userrole_texts}" data-values="${data.userrole_values}" data-default="">
		-->
		<input type="text" readonly id="positionname" name="positionname" value="${data.position}" style="width:20em">
		
		</span>
		</td>
	</tr>
	<tr>
		<td class="r"><label for="creatername">共享人：</label></td>
		<td>
		<input type="text" readonly id="creatername" name="creatername" value="${data.username}" style="width:20em">
		</td>
		<td class="r"><label for="sourcename">信息来源：</label></td>
		<td>
		</td>
	</tr>
	<tr>
		<td class="r"><label for="title">计划名称：</label></td>
		<td colspan="3"><input class="text required" id="title" name="title" style="width:50em"/></td>
	</tr>	
	<tr>
		<td class="r"><label for="memo">备注：</label></td>
		<td colspan="3"><textarea id="memo" name="memo" maxlength="500"></textarea></td>
	</tr>				
	</table>
	</form>
	</div>
</div>

</div>

<script type="text/javascript">
$("#bt_save").click(function() {page_save()});
$("#bt_selectmodel").click(function() {page_selectmodel()});

// 保存
function page_save()
{
    $("#aform").attr("target","_parent");
	$("#aform").attr("action","apply_insert.action");
    $("#aform").trigger('submit');
}

function page_selectmodel()
{
	url = "${base}/module/pams/jhgl/jhbz/apply_selectmodel.action?_searchname=jhgl.jhbz.selectmodel";
	openwinT(url,'selectmodel',pub_width_large,pub_height_large,null,'选择模板');
}

</script>
</body>
</html>
