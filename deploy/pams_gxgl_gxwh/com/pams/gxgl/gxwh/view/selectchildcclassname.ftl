{items:[
<#list data.knowledgeclasses as treeview>
{cname:'${treeview.cname}',leaf:${treeview.islast?default('0')},id:'${treeview.id}'}<#if treeview_index+1 lt data.knowledgeclasses?size>,</#if>
</#list>
]}
