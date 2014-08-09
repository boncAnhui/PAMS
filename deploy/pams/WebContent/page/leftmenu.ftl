while(1); 
<#if data.jsonobj??>

${data.jsonobj}

<#else>

{
	status:'<#if (data.leftPersonalized.state)??>${data.leftPersonalized.state}<#else>closed</#if>',
	items:[
		<#list data.shortCuts as shortCut>
		<#if shortCut.items?size &gt; 0>
		{name:'${shortCut.group.cname}',show:'${shortCut.group.show}',classname:'${shortCut.group.classname}',items:[
			<#list shortCut.items as item>
			{name:'${item.cname}',show:'${item.show}',link:'${item.url}',classname:'${item.classname}'}<#if item_index+1 lt shortCut.items?size>,</#if>
			</#list>
		]}<#if shortCut_index+1 lt data.shortCuts?size>,</#if>
		</#if>
		</#list>
	]
}

</#if>