[
<#list data.items as item>
	{name:'${item.groupname}',status:'${item.groupstate}',id:'${item.groupid}',items:[
	<#list item.items as titem>
		{name:'${titem.cname}',link:'${base}${titem.url}',icon:'${titem.icon}'}<#if titem_index+1 lt item.items?size>,</#if>
	</#list>
	]}<#if item_index+1 lt data.items?size>,</#if>
</#list>
]
			