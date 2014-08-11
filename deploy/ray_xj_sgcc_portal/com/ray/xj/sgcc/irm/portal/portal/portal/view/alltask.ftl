[
<#list data.rows as row>
  {name:'${row.issuer!}下达[${row.workname!}]处于${row.state}',url:'${base}/module/irm/project/task/task/task_readpageframe.action?id=${row.id}',date:'${(row.tbegintime?string("yyyy/MM/dd"))}'}<#if row_has_next>,</#if>
</#list>
]