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

	<div id="fixedOp">
	<!--
			<button id="bt_input">新增</button>
			<button id="bt_del">删除</button>
	-->		
	</div>
<div id="pageContainer">

<div id="searchDivContainer">
<form id="form_quicksearch" method="post" action="${base}/module/pams/gxgl/zswh/knowledge_browse.action">
<input type="hidden" name="_searchname" value="gxgl.zswh.browse">
<input type="hidden" name="page" value="1">
<input type="hidden" name="_sortfield" value="">
<input type="hidden" name="_sorttag" value="asc">
<input type="hidden" name="step" value="10">

<input type="hidden" name="cclassid" id="cclassid" value="${arg.cclassid}">

<a href="javascript:void(0);" id="sbtn" hidefocus="true;">搜索</a>
<a href="javascript:void(0);" class="showAdvSearch showAdvSearchOpened" hidefocus="true;">高级</a> 
<div class="adv" style="display:none;">
<div id="clearSearchStr"><span class="t">清除搜索</span></div>
	<input cname="流程分类" name="flowcclass" type="hidden" value="GXGL"> 
	<table class="formGrid">
		<tbody>
		<tr>
		<td class="r" width="15%">标题：</td>
		<td width="35%">
		<input type="text" class="text" id="title" name="title" style="width:20em" size="" datatype="" value="${arg.title}">
		</td>
		<td width="15%" class="r">编号：</td>
		<td width="35%">
		<input type="text" class="text" id="kno" name="kno" style="width:20em" size="35" datatype="" value="${arg.kno}">
		</td>
		</tr>
		<tr>
		<td class="r">主作者：</td>
		<td>
		<input type="text" class="text" id="mauthor" name="mauthor" style="width:20em" size="35" datatype="" value="${arg.mauthor}">
		</td>
		<td class="r">主作者部门：</td>
		<td>
		<input type="text" class="text" id="mauthordept" name="mauthordept" style="width:20em" size="35" datatype="" value="${arg.mauthordept}">
		</td>
		</tr>
		<td class="r">发布时间开始：</td>
		<td>
		<input class="date" type="text" id="beginpublishdate" name="beginpublishdate" size="35" datatype="" value="${arg.beginpublishdate}">
		</td>
		<td class="r">发布时间结束：</td>
		<td>
		<input class="date" type="text" id="endpublishdate" name="endpublishdate" size="35" datatype="" value="${arg.endpublishdate}">
		</td>
		</tr>
	</table>
</div>
</form>

</div>


<#--
<ul class="knowList">
<#list data.knowledges as aobj>
	<li>
    	<span class="t">
    	<input name="abc" class="checkbox"  data-id="${(aobj.id)!}" />
    	<a href="javascript:void(0)" onclick="openwin('knowledge_readpage.action?id=${aobj.id}&cclassid=${arg.cclassid}','locateknowledge', pub_width_large, pub_height_large)"><#if aobj.title=="">无标题<#else>${aobj.title}</#if></a>
    	</span>
        <span class="m">
                           日期:<#if aobj.createtime!="">${aobj.createtime?datetime("yyyy-MM-dd HH:mm")}</#if>
                           文档编号：${aobj.kno} 
                           状态：<#if aobj.iseffective=="Y" >发布<#else>新建</#if>
                           主作者：${aobj.mauthor}
                           部门：${aobj.mauthordept}
                           发布人：${aobj.publisher}
                           文档分类：<#if aobj.restype="InfoShare">信息共享</#if>
       
        </span>
        <span class="e">摘要：<#if aobj.summary?length &gt; 50>${aobj.summary?substring(0,50)}...<#else>${aobj.summary}</#if></span>
    </li>
</#list>
-->

<table class="dataGrid">
<thead>
<tr>
<th>资源编号</th>	
<th width="400">标题</th>
<th>主作者</th>
<th>发布者</th>
<th>发布时间</th>
<th>部门</th>
</tr>
</thead>
<tbody>
<#list data.knowledges as aobj>
<tr>
<td><a href="javascript:void(0)" onclick="openwin('knowledge_readpage.action?id=${aobj.id}&cclassid=${arg.cclassid}','locateknowledge', pub_width_large, pub_height_large)">${aobj.kno}</a></td>
<td>${aobj.title}</td>
<td>${aobj.mauthor}</td>
<td>${aobj.publisher}</td>
<td><#if aobj.publishdate!="">${aobj.publishdate?datetime("yyyy-MM-dd HH:mm")}</#if></td>
<td>${aobj.mauthordept}</td>
</tr>
</#list>
</tbody>
</table>


</ul>
<#if data.knowledges?size &lt; 1>
无记录！
</#if>
</div>

<form method="post" name="form_view" id="form_view" action="${base}/module/pams/gxgl/zswh/knowledge_browse.action">
<input type="hidden" name="_searchname" value="gxgl.zswh.browse">
<input type="hidden" name="page" value="1">
<input type="hidden" name="_sortfield" value="">
<input type="hidden" name="_sorttag" value="asc">
<input type="hidden" name="step" value="10">

	<input cname="标题" name="title" type="hidden" value="${arg.title}">
	<input cname="编号" name="kno" type="hidden" value="${arg.kno}">
	<input cname="作者" name="mauthor" type="hidden" value="${arg.mauthor}">
	<input cname="部门" name="mauthordept" type="hidden" value="${arg.mauthordept}">
	<input cname="发布日期开始" name="beginpublishdate" type="hidden" value="${arg.beginpublishdate}">
	<input cname="发布日期结束" name="endpublishdate" type="hidden" value="${arg.endpublishdate}">

</form>
</div>

</body>
</html>

</script>