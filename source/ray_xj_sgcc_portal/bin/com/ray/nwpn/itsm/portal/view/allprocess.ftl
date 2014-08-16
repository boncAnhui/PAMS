<h2><span class="t pending">个人已办</span><span class="more"><a href="javascript:redirctmoduleurl('${base}/menu!otherLink.action','${base}/module/irm/portal/portal/portal/portal_browseAllProcess.action?_searchname=portal.browseallprocess')">更多›</a></span></h2>
<div class="h2sec">
<ul class="glist">
	<#assign num=0>
	<#list data.listprocess as list>
		<#assign num=num+1>
		<#if num gt 8>
		<#break>
		</#if>
		<li><span class="date">${list.taskname}&nbsp &nbsp${list.dusername}&nbsp &nbsp${list.taked}&nbsp &nbsp${list.tasktime?datetime('yyyy-MM-dd hh:mm')}</span>
		<span class="cat">
		<#if list.bclass='event'>[事件]
			</span><a href="javascript:redirctmoduleurl('${base}/module/app/business/event/event/event_tree.action','${base}/module/app/business/event/event/event_locateframe.action?status=process&id=${list.bussinessid}&taskid=')">
			<#if list.title?length gt 30>${list.title?substring(0,29)}...<#else>${list.title}</#if></a>
		<#elseif list.bclass='problem'>[问题]
			</span><a href="javascript:redirctmoduleurl('${base}/module/app/business/problem/problem/problem_tree.action','${base}/module/app/business/problem/problem/problem_locateframe.action?status=process&id=${list.bussinessid}&taskid=')">
			<#if list.title?length gt 30>${list.title?substring(0,29)}...<#else>${list.title}</#if></a>
		<#elseif list.bclass='configchange'>[变更预评估]
			</span><a href="javascript:redirctmoduleurl('${base}/menu!otherLink.action','${base}/module/irm/config/change/configchange/configchange_readpageframe.action?_searchname=config.change.configchange.locate&runactkey=${list.taskid}&id=${list.bussinessid}')">
			<#if list.title?length gt 30>${list.title?substring(0,29)}...<#else>${list.title}</#if></a>	
		<#elseif list.bclass='contingencyplan'>[演练计划]
			</span><a href="javascript:redirctmoduleurl('${base}/menu!otherLink.action','${base}/module/app/business/contingency/contingencyplan/contingencyplan_locateframe.action?_searchname=contingencyplan.locate&source=browseall&runactkey=${list.taskid}&id=${list.bussinessid}')">
			<#if list.title?length gt 30>${list.title?substring(0,29)}...<#else>${list.title}</#if></a>
		<#elseif list.bclass='maintenance'>[检修计划]
			</span><a href="javascript:redirctmoduleurl('${base}/menu!otherLink.action','${base}/module/app/business/maintenance/maintenance/maintenance_locateframe.action?_searchname=maintenance.locate&id=${list.bussinessid}')">
			<#if list.title?length gt 30>${list.title?substring(0,29)}...<#else>${list.title}</#if></a>	
		<#elseif list.bclass='audit'>[巡检]
			</span><a href="javascript:redirctmoduleurl('${base}/menu!otherLink.action','${base}/module/app/business/audit/audit/audit_locateframe.action?_searchname=audit.locate&id=${list.bussinessid}')">
			<#if list.title?length gt 30>${list.title?substring(0,29)}...<#else>${list.title}</#if></a>	
		<#elseif list.bclass='ctooperation'>[建转运]
			</span><a href="javascript:redirctmoduleurl('${base}/menu!otherLink.action','${base}/module/app/business/ctooperation/ctooperation/ctooperation_locateframe.action?id=${list.bussinessid}')">
			<#if list.title?length gt 30>${list.title?substring(0,29)}...<#else>${list.title}</#if></a>
		<#elseif list.bclass='alarm'>[告警]
			</span><a href="javascript:redirctmoduleurl('','${base}/module/app/business/alarm/alarm/alarm_locateframe.action?status=process&id=${list.bussinessid}&taskid=')">
			<#if list.title?length gt 30>${list.title?substring(0,29)}...<#else>${list.title}</#if></a>
		<#elseif list.bclass='changerecord'>[变更]
			</span><a href="javascript:redirctmoduleurl('','${base}/module/app/business/change/changerecord/changerecord_locateframe.action?status=process&id=${list.bussinessid}&taskid=')">
			<#if list.title?length gt 30>${list.title?substring(0,29)}...<#else>${list.title}</#if></a>
		<#elseif list.bclass='defect'>[缺陷]
			</span><a href="javascript:redirctmoduleurl('','${base}/module/app/business/defect/defect/defect_locateframe.action?status=process&id=${list.bussinessid}&taskid=')">
			<#if list.title?length gt 30>${list.title?substring(0,29)}...<#else>${list.title}</#if></a>
		<#elseif list.bclass='hdanger'>[隐患]
			</span><a href="javascript:redirctmoduleurl('','${base}/module/app/business/hdanger/hdanger/hdanger_locateframe.action?status=process&id=${list.bussinessid}&runactkey=${list.taskid}')">
			<#if list.title?length gt 30>${list.title?substring(0,29)}...<#else>${list.title}</#if></a>
		<#elseif list.bclass='knowledge'>[知识库]
			</span><a href="javascript:redirctmoduleurl('','${base}/module/irm/knowledge/knowledge/knowledge/knowledge_locateframe.action??status=process&id=${list.bussinessid}&runactkey=${list.taskid}')">
			<#if list.title?length gt 30>${list.title?substring(0,29)}...<#else>${list.title}</#if></a>
		</#if>
		</li>
	</#list>
</ul>
</div>