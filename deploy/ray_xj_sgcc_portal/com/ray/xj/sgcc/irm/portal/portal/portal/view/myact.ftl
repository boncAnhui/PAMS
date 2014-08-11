[
<#list data.rows as row>
  {name:'[${(row.recordtime?string("MM-dd"))}]记录的业务活动,总花费${row.sumcost!"0"}元',url:'${base}/module/irm/business/businessact/businessact/businessact_locate.action?a=1&id=${row.id}',date:'${(row.recordtime?string("MM/dd HH:mm"))}'}<#if row_has_next>,</#if>
</#list>
]