[
<#list data.rows as row>
  {name:'【${row.pmstate!}】${row.cname!} ${row.leader!}',url:'${base}/module/irm/project/project/project/project_readpageframe.action?id=${row.id}',date:'${(row.pmdatebegin?string("yyyy/MM/dd"))}'}<#if row_has_next>,</#if>
</#list>
]
