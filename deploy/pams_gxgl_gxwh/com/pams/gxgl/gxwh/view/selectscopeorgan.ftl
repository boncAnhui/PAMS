<!DOCTYPE HTML>
<html>
<head>
<#include "/decorator/include/include.ftl">
</head>
<body>
<div id="fixedOp">
<button id="bt_save" class="btn2">确定</button>
</div>
<table class="dataGrid">
<thead>
	<th width="1"><input class="checkbox"></th>
	<th>名称</th>
</thead>
<tbody>
	<#list data.organs as obj>
	<#assign level = obj.internal?length/4>
	<#assign blank = "">
	<#list 1..level as bo>
		<#if bo_index&gt;1>
			<#assign blank = blank + "　　">
		</#if>
	</#list>
	<tr>
	<td><input class="checkbox" data-id="${obj.id}" data-cname="${obj.cname}" data-ctype="${obj.ctype}" data-internal="${obj.internal}"></td>
	<td>${blank}${obj.cname}</td>
	</tr>
	</#list>
</tbody>
</table>
<script type="text/javascript">
$("#bt_save").click(function() {page_save()});

function page_save()
{
	var oids=[];
	var ocnames=[];
	var octypes=[];
	var ointernals=[];
	$('.dataGrid tbody .checkbox').each(function(j,k)
	{		
		if($(this).val()==1)
		{
			oids.push($(this).attr('data-id'));
			ocnames.push($(this).attr('data-cname'));	
			octypes.push($(this).attr('data-ctype'));
			ointernals.push($(this).attr('data-internal'));
		}		
	})
	
	if(oids.length<=0)
	{
		alert("请选择要共享的部门！");
		return;
	}
	
	var opener=$('body',window.parent.opener.document);
	
	var v_infosharescope = opener.find('#infosharescope').val();
	var v_infosharescopeid = opener.find('#infosharescopeid').val();
	var v_infosharescopectype = opener.find('#infosharescopectype').val();
	var v_infosharescopeinternal = opener.find('#infosharescopeinternal').val();
	
	if(v_infosharescope!="")
	{
		opener.find('#infosharescope').val(v_infosharescope + "," + ocnames);	
	}
	else
	{
		opener.find('#infosharescope').val(ocnames);
	}

	if(v_infosharescopeid!="")
	{
		opener.find('#infosharescopeid').val(v_infosharescopeid + "," + oids);	
	}
	else
	{
		opener.find('#infosharescopeid').val(oids);
	}
	
	if(v_infosharescopectype!="")
	{
		opener.find('#infosharescopectype').val(v_infosharescopectype + "," + octypes);	
	}
	else
	{
		opener.find('#infosharescopectype').val(octypes);
	}
	
	if(v_infosharescopeinternal!="")
	{
		opener.find('#infosharescopeinternal').val(v_infosharescopeinternal + "," + ointernals);	
	}
	else
	{
		opener.find('#infosharescopeinternal').val(ointernals);
	}

	window.parent.close();
}
</script>
</body>
</html>