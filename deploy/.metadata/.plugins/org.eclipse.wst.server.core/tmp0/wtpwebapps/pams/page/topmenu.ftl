while(1); 
<#if data.jsonobj??>
${data.jsonobj}
<#else>
{
	status:'<#if (data.topPersonalized.state)??>${data.topPersonalized.state}<#else>opened</#if>',/*closed opened*/
	skin:<#if (data.topPersonalized.sysskin)??>${data.topPersonalized.sysskin}<#else>1</#if>,
	itemsShow:[
	<#list data.navitems.navitems_top as itemtop>
	{name:'${itemtop.cname}',link:'${base}${itemtop.url}?ccate=${itemtop.ccate}',id:'${itemtop.id}',target:'${itemtop.opentarget}'}<#if itemtop_index+1 lt data.navitems.navitems_top?size>,</#if>
	</#list>
	],
	itemsMore:[
	<#list data.navitems.navitems_more as itemmore>
	{name:'${itemmore.cname}',link:'${base}${itemmore.url}?ccate=${itemmore.ccate}',id:'${itemmore.id}',target:'${itemmore.opentarget}'}<#if itemmore_index+1 lt data.navitems.navitems_more?size>,</#if>
	</#list>
	]
}
</#if>