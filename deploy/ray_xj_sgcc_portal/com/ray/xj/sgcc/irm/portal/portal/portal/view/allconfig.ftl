[
<#list data.rows as row>
  {name:'【${row.xtmc!}】',url:'${base}/module/irm/config/config/configlist/configlist_locateframe.action?_searchname=config.config.configlist.locateframe&id=${row.id}',date:'${(row.updatetime?string("yyyy/MM/dd"))}'}<#if row_has_next>,</#if>
</#list>
]