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
	<#list data.roles as obj>
	<#assign level = obj.internal?length/4>
	<#assign blank = "">
	<#list 1..level as bo>
		<#if bo_index&gt;1>
			<#assign blank = blank + "　　">
		</#if>
	</#list>
	<tr>
	<td><input class="checkbox" data-id="${obj.id}" data-cname="${obj.cname}" data-ctype="ROLE" data-internal="${obj.id}"></td>
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
		alert("请选择要共享的角色岗位！");
		return;
	}
	
	var opener=$('body',window.parent.opener.document);
	
	var v_marketpowerscope = opener.find('#marketpowerscope').val();
	var v_marketpowerscopeid = opener.find('#marketpowerscopeid').val();
	var v_marketpowerscopectype = opener.find('#marketpowerscopectype').val();
	var v_marketpowerscopeinternal = opener.find('#marketpowerscopeinternal').val();
		
	if(v_marketpowerscope!="")
	{
		opener.find('#marketpowerscope').val(v_marketpowerscope + "," + ocnames);	
	}
	else
	{
		opener.find('#marketpowerscope').val(ocnames);
	}

	if(v_marketpowerscopeid!="")
	{
		opener.find('#marketpowerscopeid').val(v_marketpowerscopeid + "," + oids);	
	}
	else
	{
		opener.find('#marketpowerscopeid').val(oids);
	}
	
	if(v_marketpowerscopectype!="")
	{
		opener.find('#marketpowerscopectype').val(v_marketpowerscopectype + "," + octypes);	
	}
	else
	{
		opener.find('#marketpowerscopectype').val(octypes);
	}
	
	if(v_marketpowerscopeinternal!="")
	{
		opener.find('#marketpowerscopeinternal').val(v_marketpowerscopeinternal + "," + ointernals);	
	}
	else
	{
		opener.find('#marketpowerscopeinternal').val(ointernals);
	}	
	
	// window.parent.close();
}
</script>
</body>
</html>