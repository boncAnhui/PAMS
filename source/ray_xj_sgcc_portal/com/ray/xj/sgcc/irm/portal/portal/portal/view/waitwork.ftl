[

<#if (data.waitworks?size>8)>
<#assign results=data.waitworks[0..7]>
<#else>
<#assign results=data.waitworks>
</#if>
    <#list results as waitwork>
    	<#if waitwork.title?length =0>
		<#assign title = "无标题">
		<#else>
		<#assign title = "${waitwork.title}">
		</#if>
		<#assign cclass = waitwork.cclass>
		
		<#if waitwork.cclass="change">
			<#assign cclass = "变更">
		<#elseif waitwork.cclass="project">
			<#assign cclass = "项目">
		<#elseif waitwork.cclass="task">
			<#assign cclass = "任务">
		<#elseif waitwork.cclass="tasktail">
			<#assign cclass = "任务跟踪">
		<#elseif waitwork.cclass="businessact">
			<#assign cclass = "业务活动">
		</#if>
		
		<#assign cname = waitwork.cclass >
    	{signstate:'${waitwork.signstate}',name:'${waitwork.susername}发来关于【${title}】的待办工作，【${cclass}】',url:'${base}${waitwork.curl}',date:'${(waitwork.createtime?string("MM/dd HH:mm"))}'}<#if waitwork_has_next>,</#if>
    </#list>
]