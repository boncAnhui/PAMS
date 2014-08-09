<!DOCTYPE HTML>
<html>
<head>
<#include "/decorator/include/include.ftl">

<script type="text/javascript">
var navigationJSON=[ {name:'知识库管理',link:'${base}/module/irm//portal/portal/portal/portal_browse.action?ccate=knowledge'}, {name:'知识文档'}];

var pagerJSON={current:1,items:68,pages:15,step:10,url:'k3-right.php?a=1',orderby:'daobaoqixian',order:'desc'}


$(function(){
/////////////////////////////////////////

$("#bt_input").click(function() {page_input()});
$("#bt_del").click(function() {page_delete()});
$("#bt_folder").click(function() {page_folder()});
$("#bt_migration").click(function() {page_migration()});
$("#bt_publish").click(function() {page_publish()});
/////////////////////////////////////////
})

function page_input()
{	
	url = "${base}/module/irm/knowledge/knowledge/knowledge/knowledge_input.action?_searchname=knowledge.knowledge.knowledge.input&cclassid=${arg.cclassid}";
	window.name="browseknowledge";
	openwin(url,'inputknowledge',pub_width_large,pub_height_large);	
	// window.location = url;
}

function page_delete()
{
	var oids=[];
	$('.knowList .checkbox').each(function(j,k)
	{		
		if($(this).val()==1)
		{
			oids.push($(this).attr('data-id'))	
		}		
	})
	
	if(oids.length<=0)
	{
		alert("请先选择要删除的数据！");
		return;
	}
	
	if(confirm("确实要删除选中的记录?"))
	{
		window.location = "knowledge_deletemore.action?cclassid=${arg.cclassid}&ids="+oids.join(',');
	}
}

function page_publish()
{
	var oids=[];
	$('.knowList .checkbox').each(function(j,k)
	{		
		if($(this).val()==1)
		{
			oids.push($(this).attr('data-id'))	
		}		
	})
	
	if(oids.length<=0)
	{
		alert("请先选择要发布的数据！");
		return;
	}
	
	if(confirm("确实要发布选中的记录?"))
	{
		window.location = "knowledge_publish.action?cclassid=${arg.cclassid}&ids="+oids.join(',');
	}
}

function page_migration()
{
	var oids=[];
	$('.knowList .checkbox').each(function(j,k)
	{		
		if($(this).val()==1)
		{
			oids.push($(this).attr('data-id'))	
		}		
	})
	
	if(oids.length<=0)
	{
		alert("请先选择要迁移的数据！");
		return;
	}
	
	if(confirm("确实要迁移选中的记录?"))
	{
		url = "knowledge_migration.action?cclassid=${arg.cclassid}&ids="+oids.join(',');
		openwin(url,'inputknowledge',pub_width_large,pub_height_large);	
	}
}

</script>
</head>
<body>

<form>
	<div id="fixedOp">
	<!--
			<button id="bt_input">新增</button>
			<button id="bt_del">删除</button>
	-->		
	</div>
<div id="pageContainer">

<ul class="knowList">
<#list data.knowledges as aobj>
	<li>
    	<span class="t">
    	<input name="abc" class="checkbox"  data-id="${(aobj.id)!}" />
    	<a href="javascript:void(0)" onclick="openwin('knowledge_readpage.action?id=${aobj.id}&cclassid=${arg.cclassid}','locateknowledge', pub_width_large, pub_height_large)">${aobj.title}</a>
    	</span>
        <span class="m">创建日期:${aobj.createtime} 作者：${aobj.mauthor} 文档编号：${aobj.kno} 安全级别：${aobj.slevel} 状态：<#if aobj.iseffective=="Y" >发布<#else>新建</#if></span>
        <span class="e">摘要：<#if aobj.summary?length &gt; 50>${aobj.summary?substring(0,50)}...<#else>${aobj.summary}</#if></span>
    </li>
</#list>

</ul>
<#if data.knowledges?size &lt; 1>
无记录！
</#if>
</div>

</form>
</body>
</html>

</script>

