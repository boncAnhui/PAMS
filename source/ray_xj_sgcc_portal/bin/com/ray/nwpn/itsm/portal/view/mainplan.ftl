<h2><span class="t maintenance">检修计划</span><span class="more"><a href="javascript:redirctmoduleurl('${base}/menu!otherLink.action','${base}/module/app/business/maintenance/maintenance/maintenance_browseissue.action?_searchname=maintenance.browseissue')">更多›</a></span></h2>
<div class="p8">

<table class="gGrid">
	<thead>
	<tr><th>计划部门</th><th>系统名称</th><th>状态</th><th>计划执行人</th><th>计划开始时间</th></tr>
	</thead>
	<tbody>

<#list data.maintenances as maintenance>
<#if maintenance_index lt 6>
	<tr>
		<td class="nowrap">${maintenance.pdept}</td>
		<td>${maintenance.appname}</td>
		<td>${maintenance.state}</td>
		<td>${maintenance.puser}</td>
		<td>${maintenance.pbegintime}</td>
		
	</tr>
</#if>	
</#list>
	</tbody>
</table>

</div>