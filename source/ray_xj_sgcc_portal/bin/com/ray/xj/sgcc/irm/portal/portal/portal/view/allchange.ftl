[
<#list data.rows as row>
	<#if row.state=='结束'>
		{name:'【${row.cno!}】由${row.tuser!}${row.state!}了变更',url:'${base}/module/irm/config/change/configchange/configchange_readpageframe.action?_searchname=config.change.configchange.locate&id=${row.id}&ctype=list',date:'${(row.applytime?string("yyyy/MM/dd"))}'}<#if row_has_next>,</#if>
	<#else>
		{name:'【${row.cno!}】由${row.tuser!}在${row.state!}',url:'${base}/module/irm/config/change/configchange/configchange_readpageframe.action?_searchname=config.change.configchange.locate&id=${row.id}&ctype=list',date:'${(row.applytime?string("yyyy/MM/dd"))}'}<#if row_has_next>,</#if>
	</#if>
</#list>
]