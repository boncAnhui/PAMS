<div id="pageContainer">
		<table class="dataGrid">
		<thead>
		<tr>
			<th>任务名称</th><th>目标节点名称</th><th>处理人名称</th><th>是否接单</th><th>发送时间</th>
		</tr>
	</thead>
	<tbody>
		<!-- 显示数据-->
		<#list data.page.result as list>
			<tr>
			<td>
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
			</span><a href="javascript:redirctmoduleurl('','${base}/module/app/business/hdanger/hdanger/hdanger_locateframe.action?status=process&id=${list.bussinessid}&taskid=')">
			<#if list.title?length gt 30>${list.title?substring(0,29)}...<#else>${list.title}</#if></a>
		</#if></td>
			<td>${list.taskname}</td>
			<td>${list.dusername}</td>
			<td>${list.taked}</td>
			<td>${list.tasktime?datetime('yyyy-MM-dd hh:mm')}</td>
			</tr>
		</#list>
		</tbody>
		</table>
		
		<form method="post" name="form_view" id="form_view" action="${base}${vo.formaction}">
		<input type="hidden" name="_searchname" value="${_searchname}">
		<input type="hidden" name="page" value="${apage.currentpage}">
		<input type="hidden" name="_sortfield" value="${apage.sortfield}" />
		<input type="hidden" name="_sorttag" value="${apage.sorttag}" />
		<input type="hidden" name="step" value="${apage.pagesize}" />

<@pub_macros.displaypage vo = vo arg = arg />

</form>
		
		<div class="pager" id="thisPager">
<@pub_macros.displaynavigation apage = apage vo = vo arg = arg></@pub_macros.displaynavigation>
</div>
		
</div>

<script type="text/javascript">
function redirctmoduleurl(a,url){
	openwin(url,'newWin',pub_width_large,pub_height_large);
}

</script>
</body>
</html>


