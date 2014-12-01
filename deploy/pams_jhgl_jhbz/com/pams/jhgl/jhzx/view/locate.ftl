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

<input type="hidden" id="supid" name="supid" value="${data.plan.supid}">
	<table class="formGrid">
	<tr>
		<td class="r"><label for="planmodelname">计划模板：</label></td>
		<td colspan="3">
		<input type="hidden" id="planmodelid" name="planmodelid" value="${data.plan.planmodelid}">
		<input type="text" readonly id="planmodelname" name="planmodelname" value="${data.plan.planmodelname}" style="width:40em">
		</td>
	</tr>
	<tr>
		<td class="r"><label for="title">计划名称：</label></td>
		<td colspan="3"><input class="text required" id="title" name="title" style="width:45em" value="${data.plan.title}"/></td>
	</tr>
	<tr>
		<td class="r"><label for="planbegintime">计划开始日期：</label></td>
		<td>
		<input class="date required" id="planbegintime" name="planbegintime" style="width:10em" value="${data.plan.planbegintime?string('yyyy-MM-dd')}"/>
		</td>
		<td class="r"><label for="planendtime">计划结束日期：</label></td>
		<td>
		<input class="date required" id="planendtime" name="planendtime" style="width:10em" value="${data.plan.planendtime?string('yyyy-MM-dd')}"/>
		<input readonly class="text" id="planperiodnum" name="planperiodnum" style="width:8em" value="${data.plan.planperiodnum}"/>
		</td>
	</tr>
	<tr>
		<td class="r"><label for="headername">负责人：</label></td>
		<td>
		<input type="hidden" id="header" name="header" value="">
		<input class="text" id="hedername" name="headername" style="width:20em" value="${data.plan.headername}"/>
		</td>
		<td class="r"><label for="headerdeptname">负责部门：</label></td>
		<td>
		<input type="hidden" id="headerdept" name="headerdept" value="${data.plan.headerdept}">
		<input class="text" id="headerdeptname" name="headerdeptname" style="width:20em" value="${data.plan.headerdeptname}"/>
		</td>
	</tr>
	<tr>
		<td class="r"><label for="createrdeptname">编制部门：</label></td>
		<td>
		<input type="hidden" id="createrdept" name="createrdept" value="${data.plan.createrdept}"/>
		<input readonly class="text" id="createrdeptname" name="createrdeptname" style="width:20em" value="${data.plan.createdeptname}"/>
		</td>
		<td class="r"><label for="positionname">岗位：</label></td>
		<td>
		<span class="selectSpan">
		<input type="text" readonly id="positionname" name="positionname" value="" style="width:20em">
		</span>
		</td>
	</tr>
	<tr>
		<td class="r"><label for="creatername">编制人：</label></td>
		<td>
		<input type="hidden" id="creater" name="creater" value="${data.plan.creater}"/>		
		<input readonly type="text" id="creatername" name="creatername" value="${data.plan.creatername}" style="width:20em">
		</td>
		<td class="r"><label for="createtime">编制日期：</label></td>
		<td>
		<input readonly class="text required" id="createtime" name="createtime" style="width:10em" value="${data.plan.createtime?string("yyyy-MM-dd")}"/>
		</td>
	</tr>

	<tr>
		<td class="r"><label for="memo">备注：</label></td>
		<td colspan="3"><textarea id="memo" name="memo" maxlength="500"></textarea></td>
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
	$("#aform").attr("action","apply_update.action");
    $("#aform").trigger('submit');
}
</script>
</body>
</html>