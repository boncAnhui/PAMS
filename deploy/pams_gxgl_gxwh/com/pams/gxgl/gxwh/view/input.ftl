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
		<input type="hidden" id="flowdefid" name="flowdefid" value="${data.bflow_values}">
		<input type="hidden" id="flowname" name="flowname" value="${data.bflow_texts}">
	<table class="formGrid">
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
		<span class="selectSpan">
		<input type="hidden" id="sourceid" name="sourceid" value="">
		<input class="select readonly required" id="selectsourceid" data-options="${data.sourcename_texts}" data-values="${data.sourcename_values}" data-default="">
		</span>
	</tr>
	<tr>
		<td class="r"><label for="obtaintime">信息获取时间：</label></td>
		<td>
		<input type="hidden" id="obtaintime" name="obtaintime" value=""> 
		<input class="date required" id="obtaintimed" name="obtaintimed" style="width:10em" value="${data.obtaintimed}"/>
		<input class="time required" id="obtaintimet" name="obtaintimet" style="width:10em" value="${data.obtaintimet}"/>
		</td>
		<td class="r"><label for="publishtime">发布时间：</label></td>
		<td>
		<input type="text" readonly id="publishtime" name="publishtime" value="" style="width:20em">
		</td>		
	</tr>
	<tr>
		<td class="r"><label for="title">信息共享名称：</label></td>
		<td colspan="3"><input class="text required" id="title" name="title" style="width:50em"/></td>
	</tr>	
	<tr>
		<td class="r"><label for="summary">内容摘要：</label></td>
		<td colspan="3"><textarea class="text" id="summary" name="summary" maxlength="500"></textarea></td>
	</tr>
	<tr>
		<td class="r"><label for="cclassname">分类：</label></td>
		<td>
		<input type="hidden" id="cclassid" name="cclassid">		
		<input class="text required" readonly id="cclassname" name="cclassname" style="width:20em"/><button id="bt_cclassname" class="btn2">选择</button></td>
		<#--
		<td class="r"><label id="lb_title">共享权限：</label></td>
		<td>
		<span class="selectSpan">
		<input type="hidden" id="shareauthor" name="shareauthor" value="">
		<input class="select readonly required" id="selectshareauthor" data-options="${data.shareauthor_texts}" data-values="${data.shareauthor_values}" data-default="">
		</span>
		</td>
		-->
		<td class="r">&nbsp;</td>
		<td>
	</tr>
	<tr>
		<td class="r"><label for="infosharescope">共享范围：</label></td>
		<td colspan="3">
		<input type="hidden" id="infosharescopeid" name="infosharescopeid">
		<input type="hidden" id="infosharescopectype" name="infosharescopectype">
		<input class="text" readonly id="infosharescope" name="infosharescope" style="width:45em" />
		<button id="bt_scope" class="btn2">选择</button>
		<button id="bt_scope_clear" class="btn2">清除</button>
		</td>
	</tr>
	<tr>
		<td class="r"><label for="memo">备注：</label></td>
		<td colspan="3"><textarea class="text" id="memo" name="memo" maxlength="500"></textarea></td>
	</tr>	
	<!--	
	<tr>
		<td class="r"><label for="cclassname">文件数量：</label></td>
		<td><input class="text required" id="cclassname" name="cclassname" style="width:20em"/></td>
		<td class="r"><label id="lb_title">文件形式：</label></td>
		<td><input class="text required" id="sharedauthor" name="sharedauthor" style="width:20em"/></td>
	</tr>
	-->				
	</table>
	</form>
	</div>
</div>

</div>

<script type="text/javascript">
$("#bt_save").click(function() {page_save()});
$("#bt_cclassname").click(function() {page_cclassname()});
$("#bt_scope").click(function() {page_scope()});
$("#bt_scope_clear").click(function() {page_scope_clear()});
// 保存
function page_save()
{
	// 检查是否具备共享流程
	if($("#flowdefid").val()=="")
	{
		alert("您的信息共享流程为空，无法发起共享流程，请联系系统管理人员.");
		return;
	}

	$("#obtaintime").val($("#obtaintimed").val() + " " + $("#obtaintimet").val() + ":00");

    $("#aform").attr("target","_parent");
	$("#aform").attr("action","apply_insert.action");
    $("#aform").trigger('submit');
}

// 选择分类
function page_cclassname()
{
	url = "${base}/module/pams/gxgl/gxwh/apply_selectcclassname.action";
	openwin(url,'',pub_width_small,pub_height_small,null,'新增');
}

// 选择共享范围
function page_scope()
{
	url = "${base}/module/pams/gxgl/gxwh/apply_selectscope.action";
	openwin(url,'',pub_width_small,pub_height_small,null,'新增');
}

// 选择共享范围
function page_scope_clear()
{
	$("#infosharescope").val("");
	$("#infosharescopectype").val("");
	$("#infosharescopeid").val("");
}
</script>
</body>
</html>