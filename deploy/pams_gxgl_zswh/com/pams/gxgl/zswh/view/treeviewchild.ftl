{items:[
<#list data.list as treeview>
{name:'${treeview.cname}',leaf:${treeview.islast?default('0')},id:'${treeview.id}',foldertype:'${treeview.foldertype}',type:'<#if treeview.foldertype=="文件库">library<#elseif treeview.foldertype=="文件柜">cabinet<#else>folder</#if>'}<#if treeview_index+1 lt data.list?size>,</#if>
</#list>
]}
