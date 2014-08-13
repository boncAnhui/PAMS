<!DOCTYPE HTML>
<html>
<head>
<#include "/decorator/include/include.ftl">
<title>流程提示信息</title>
</head>

<body id="pageLike">
<h1>流程提示信息</h1>

<#assign endacts = data.bean_flow.endacts>
<#assign endactsname = data.bean_flow.endactsname>
<#assign actors = data.bean_flow.actors>
<#assign actorstype = data.bean_flow.actorstype>
<#assign actorsname = data.bean_flow.actorsname>
<#assign endrunactkey = data.bean_flow.endrunactkey>
<#assign dataid = data.bean_flow.dataid>
<form name="form1">

<div id="chengangPage">
	<div class="pageSuccess">
	<span id="message">
	<#if arg.endflag == "END">
		<p style="font-size:14px;">文档已转发至【结束】环节，流程结束！</p>		
	<#else>
		<#assign message = "">
		<#list endacts as i>
			<#assign c_actors = actors[i_index]>
			<#assign c_actorstype = actorstype[i_index]>
			<#assign c_actorsname = actorsname[i_index]>
			
			<#assign nameString = "">
		    <#list c_actors as j>
		    <#assign cc_actor = c_actors[j_index]>
		    <#assign cc_actortype = c_actorstype[j_index]>
		    <#assign cc_actorname = c_actorsname[j_index]>
		    
		    <#if nameString=="">
		    	<#assign nameString = nameString + cc_actorname>
		    <#else>
		    	<#assign nameString = nameString + ":" + cc_actorname>	
		    </#if>
		    </#list>			
			
			<#assign message = message + "【" + endactsname[i_index] + "】环节，交给【" + actorsname[i_index] + "】等人员，"> 
		</#list>
		<#assign message = "文档已转发至" + message + "等待处理。">
		<p style="font-size:14px;">${message}</p>
		
	</#if>
	<br/>
	</span>
	</div>
</div>

</form>

<div style="text-align:center;padding-top:10px;">		
	<button onclick="closeWindow()">关闭</button>
</div>

<script>
function closeWindow()
{
	// pujian rem 2013/01/25 
	// 原有关闭前的功能逻辑暂时删除，后期视需要决定是否添加；
	window.close();
	return;
}

function page_load()
{
//	window.top.opener.top.location.reload();
	window.top.opener.top.page_forward_url("${endrunactkey}");
}

jQuery(function($)
{
	page_load();
})

</script>

</body>
</html>